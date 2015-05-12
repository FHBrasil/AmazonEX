/**
 * 
 */
package br.hering.core.jobs;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.jalo.CatalogManager;
import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.cms2.model.contents.ContentCatalogModel;
import de.hybris.platform.cms2lib.model.components.ProductCarouselComponentModel;
import de.hybris.platform.commercefacades.search.data.SearchQueryData;
import de.hybris.platform.commercefacades.search.data.SearchStateData;
import de.hybris.platform.commerceservices.search.ProductSearchService;
import de.hybris.platform.commerceservices.search.facetdata.ProductSearchPageData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.resultdata.SearchResultValueData;
import de.hybris.platform.commerceservices.search.solrfacetsearch.data.SolrSearchQueryData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.site.BaseSiteService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import br.hering.core.model.jobs.ProductCarouselAddProductsJobModel;

/**
 * @author franthescollymaneira
 *
 */
public class ProductCarouselAddProductsJobPerformable extends AbstractJobPerformable<ProductCarouselAddProductsJobModel>
{
	private Logger LOG = Logger.getLogger(ProductCarouselAddProductsJobPerformable.class);

	private static final int DEFAULT_RESULT_SIZE = 9;
	
	private static final String SORT_BY_BEST_SELLER = "bestSeller";
	
	private static final String SORT_BY_RELEVANCE = "relevance";
	
	@Resource
	private ProductService productService;
	
	@Resource
	private CatalogVersionService catalogVersionService;
	
	@Resource
	private BaseSiteService baseSiteService;

	@Resource(name = "commerceProductSearchService")
	private ProductSearchService productSearchService;
	
	@Resource(name = "solrSearchQueryDecoder")
	private Converter<SearchQueryData, SolrSearchQueryData> searchQueryDecoder;
	
	private ProductCarouselAddProductsJobModel job;
	
	/* (non-Javadoc)
	 * @see de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable#perform(de.hybris.platform.cronjob.model.CronJobModel)
	 */
	@Override
	public PerformResult perform(final ProductCarouselAddProductsJobModel job)
	{
		LOG.info("Aguardando");

		synchronized (this)
		{
   		LOG.info("Iniciando...");

   		this.job = job;
   		
   		sessionService.setAttribute("dont.change.existing.links", Boolean.FALSE);
   		
   		baseSiteService.setCurrentBaseSite(job.getCmsSite(), true);
   		
   		final List<ProductCarouselComponentModel> carouselList = findProductCarouselComponents();
   		
   		execution: 
   		{
   			if(CollectionUtils.isEmpty(carouselList))
   			{
   				LOG.info("Nenhum carrossel encontrado ");
   				break execution;
   			}
   			
   			for(final ProductCarouselComponentModel carousel : carouselList)
   			{
   				searchAndAddProductsToCarousel(carousel);
   			}
   		}
   			
   		LOG.info("Finalizando");
		}
		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}
	
	/**
	 * @param carousel
	 */
	private void searchAndAddProductsToCarousel(final ProductCarouselComponentModel carousel)
	{
		final String sortBy = getSortBy(carousel);

		final List<ProductModel> products = new ArrayList<ProductModel>();

		products.addAll(collectProducts(carousel, sortBy));

		if(CollectionUtils.isEmpty(products))
		{
			LOG.info(String.format("Não existem produtos para o filtro: %s, ordenado por %s ao carrossel %s", 
					carousel.getSearchQuery(), sortBy, carousel.getUid()));
			return;
		}
		
		int total = products.size();
		LOG.info(String.format("Relacionando %s produtos filtrados por %s, ordenados por %s ao carrossel %s", 
				String.valueOf(total), carousel.getSearchQuery(), sortBy, carousel.getUid()));
		
		carousel.setProducts(products);
		modelService.save(carousel);
	}

	/**
	 * @param carousel
	 * @return
	 */
	private String getSortBy(final ProductCarouselComponentModel carousel)
	{
		if(carousel.isOnlyNewArrivals())
		{
			return SORT_BY_RELEVANCE;
		}
		
		if(carousel.isOnlyBestSellers())
		{
			return SORT_BY_BEST_SELLER;
		}
		
		throw new IllegalArgumentException("Carrossel possui configuração de filtros inválida para esta JOB: " + carousel.getUid());
	}
	
	/**
	 * 
	 * @param carousel
	 * @param sortCode
	 * @return
	 */
	private Collection<ProductModel> collectProducts(final ProductCarouselComponentModel carousel, final String sortCode)
	{
		final String searchQuery = carousel.getSearchQuery();
		if (StringUtils.isBlank(searchQuery))
		{
			return Collections.emptyList();
		}
		
		int resultSize = carousel.getCarouselResultsSize();

		List<SearchResultValueData> result = performSearch(searchQuery, sortCode, resultSize);
		
		return convertAll(result);
	}
	
	private List<SearchResultValueData> performSearch(final String searchQuery, final String sortCode, final int pageSize)
	{
   	final PageableData pageableData = createPageableData(0, pageSize, sortCode);
   	
   	final SearchStateData searchState = new SearchStateData();
   	final SearchQueryData searchQueryData = new SearchQueryData();
   	searchQueryData.setValue(searchQuery);
   	searchState.setQuery(searchQueryData);
   	
   	ProductSearchPageData searchPageData = productSearchService.searchAgain(decodeState(searchState), pageableData);
   	
   	return searchPageData.getResults();
	}
	
	private List<ProductModel> convertAll(final List<SearchResultValueData> searchResult)
	{
		final List<ProductModel> products = new ArrayList<ProductModel>();

		CatalogVersionModel sv = getStagedVersion(job.getCmsSite().getDefaultCatalog());
		
		for(SearchResultValueData data : searchResult)
		{
			String code = (String) data.getValues().get(ProductModel.CODE);
			products.add(productService.getProductForCode(sv, code));
		}
		
		return products;
	}
	
	private SolrSearchQueryData decodeState(final SearchStateData searchState)
	{
		final SolrSearchQueryData searchQueryData = searchQueryDecoder.convert(searchState.getQuery());
		
		return searchQueryData;
	}
	
	private PageableData createPageableData(int pageNumber, int pageSize, String sortCode)
	{
		if(pageSize <= 0)
		{
			pageSize = DEFAULT_RESULT_SIZE;
		}

		final PageableData pageableData = new PageableData();
		pageableData.setCurrentPage(pageNumber);
		pageableData.setSort(sortCode);
		pageableData.setPageSize(pageSize);
		
		return pageableData;
	}

	private List<ProductCarouselComponentModel> findProductCarouselComponents()
	{
		ContentCatalogModel contentCatalog = job.getCmsSite().getContentCatalogs().iterator().next();
		CatalogVersionModel stagedContent = getStagedVersion(contentCatalog);
		
		final String sql = "SELECT {PK} FROM {ProductCarouselComponent} WHERE ({onlyNewArrivals} = 1 OR {onlyBestSellers} = 1) AND {visible} = 1 AND {catalogVersion} = ?cv";
		
		final Map<String, ? extends Object> params = Collections.singletonMap("cv", stagedContent);
		
		return flexibleSearchService.<ProductCarouselComponentModel>search(sql, params).getResult();
	}
	
	private CatalogVersionModel getStagedVersion(final CatalogModel catalog)
	{
		String productCatalogId = catalog.getId();
		
		String staged = CatalogManager.OFFLINE_VERSION;
		
		return catalogVersionService.getCatalogVersion(productCatalogId, staged);
	}
}