/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 * 
 *  
 */
package br.hering.storefront.controllers.pages;

import de.hybris.platform.acceleratorcms.model.components.SearchBoxComponentModel;
import de.hybris.platform.acceleratorservices.customer.CustomerLocationService;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.impl.SearchBreadcrumbBuilder;
import de.hybris.platform.acceleratorstorefrontcommons.constants.WebConstants;
import de.hybris.platform.acceleratorstorefrontcommons.util.XSSFilterUtil;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.cms2.servicelayer.services.CMSComponentService;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.product.data.ImageData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.search.ProductSearchFacade;
import de.hybris.platform.commercefacades.search.data.AutocompleteResultData;
import de.hybris.platform.commercefacades.search.data.SearchQueryData;
import de.hybris.platform.commercefacades.search.data.SearchStateData;
import de.hybris.platform.commerceservices.search.facetdata.BreadcrumbData;
import de.hybris.platform.commerceservices.search.facetdata.FacetData;
import de.hybris.platform.commerceservices.search.facetdata.FacetRefinement;
import de.hybris.platform.commerceservices.search.facetdata.ProductSearchPageData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.hering.facades.product.HeringProductFacade;
import br.hering.facades.product.preview.data.ProductPreviewData;
import br.hering.storefront.controllers.ControllerConstants;
import br.hering.storefront.util.HeringPageType;
import br.hering.storefront.util.MetaSanitizerUtil;
import bsh.This;

@Controller
@Scope("tenant")
@RequestMapping("/search")
public class SearchPageController extends AbstractSearchPageController
{
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(SearchPageController.class);

	private static final String COMPONENT_UID_PATH_VARIABLE_PATTERN = "{componentUid:.*}";

	private static final String SEARCH_CMS_PAGE_ID = "search";
	private static final String NO_RESULTS_CMS_PAGE_ID = "searchEmpty";
	protected static final String SEARCH_LIST_PAGE = "search/searchListPage";
	
	private boolean isShowListEnabled = false;
	private boolean isShowListHeringEnabled = false;
	private boolean isShowInfoEnabled = true;	

	@Resource(name = "productSearchFacade")
	private ProductSearchFacade<ProductData> productSearchFacade;

	@Resource(name = "searchBreadcrumbBuilder")
	private SearchBreadcrumbBuilder searchBreadcrumbBuilder;

	@Resource(name = "customerLocationService")
	private CustomerLocationService customerLocationService;

	@Resource(name = "cmsComponentService")
	private CMSComponentService cmsComponentService;

	@Resource(name = "productFacade")	
	private HeringProductFacade productFacade;	

	@RequestMapping(method = RequestMethod.GET, params = "!q")
	public String textSearch(
			@RequestParam(value = "page", defaultValue = "0") final int page,
			@RequestParam(value = "show", defaultValue = "Page") final ShowMode showMode,
			@RequestParam(value = "sort", required = false) final String sortCode,
			@RequestParam(value = "text", defaultValue = "") final String searchText,
			@RequestParam(value = "isShowListEnabled", required = false) final String isShowListEnabled,
			@RequestParam(value = "isShowInfo", required = false, defaultValue = "true") final String isShowInfo,
			@RequestParam(value = "isShowListHeringEnabled", required = false) final String isShowListHeringEnabled,
			final HttpServletRequest request, 
			final Model model) throws CMSItemNotFoundException
	{		
		if (StringUtils.isNotBlank(searchText))
		{
			final PageableData pageableData = createPageableData(0, getSearchPageSize(), null, ShowMode.Page);

			final SearchStateData searchState = new SearchStateData();
			final SearchQueryData searchQueryData = new SearchQueryData();
			searchQueryData.setValue(XSSFilterUtil.filter(searchText));
			searchState.setQuery(searchQueryData);

			final ProductSearchPageData<SearchStateData, ProductData> searchPageData = productSearchFacade.textSearch(searchState,
					pageableData);
			
			if (searchPageData == null)
			{
				storeCmsPageInModel(model, getContentPageForLabelOrId(NO_RESULTS_CMS_PAGE_ID));
			}
			else if (searchPageData.getKeywordRedirectUrl() != null)
			{
				// if the search engine returns a redirect, just
				return "redirect:" + searchPageData.getKeywordRedirectUrl();
			}
			else if (searchPageData.getPagination().getTotalNumberOfResults() == 0)
			{
				model.addAttribute("searchPageData", searchPageData);
				storeCmsPageInModel(model, getContentPageForLabelOrId(NO_RESULTS_CMS_PAGE_ID));
				updatePageTitle(searchText, searchPageData.getBreadcrumbs(), model);
			}
			else if (searchPageData.getPagination().getTotalNumberOfResults() == 1) 
			{
				final ProductData product = searchPageData.getResults().iterator().next();
				return "redirect:" + product.getUrl();
			}
			else
			{
				storeContinueUrl(request);
				populateModel(model, searchPageData, ShowMode.Page);
				storeCmsPageInModel(model, getContentPageForLabelOrId(SEARCH_CMS_PAGE_ID));
				updatePageTitle(searchText, searchPageData.getBreadcrumbs(), model);
			}
			this.isShowInfoEnabled = StringUtils.isEmpty(isShowInfo) ? true
					: Boolean.valueOf(isShowInfo).booleanValue();
			model.addAttribute("isShowInfo", isShowInfoEnabled);
			model.addAttribute("searchText", searchText);
			model.addAttribute("userLocation", customerLocationService.getUserLocation());			
			
			getRequestContextData(request).setSearch(searchPageData);
            if(searchPageData != null)
            {
			    model.addAttribute(WebConstants.BREADCRUMBS_KEY,
					searchBreadcrumbBuilder.getBreadcrumbs(null, searchText, CollectionUtils.isEmpty(searchPageData.getBreadcrumbs())));
            }
		}
		else
		{
			storeCmsPageInModel(model, getContentPageForLabelOrId(NO_RESULTS_CMS_PAGE_ID));
		}
 
		model.addAttribute("metaRobots", "no-index,follow");
		model.addAttribute("pageType", HeringPageType.PRODUCTSEARCH.name());

		final String metaDescription = MetaSanitizerUtil.sanitizeDescription(getMessageSource().getMessage(
				"search.meta.description.results", null, getI18nService().getCurrentLocale())
				+ " "
				+ searchText
				+ " "
				+ getMessageSource().getMessage("search.meta.description.on", null, getI18nService().getCurrentLocale())
				+ " "
				+ getSiteName());
		final String metaKeywords = MetaSanitizerUtil.sanitizeKeywords(searchText);
		setUpMetaData(model, metaKeywords, metaDescription);

		return getViewForPage(model);
	}

	@SuppressWarnings("boxing")
	@RequestMapping(method = RequestMethod.GET, params = "q")
	public String refineSearch(@RequestParam("q") final String searchQuery,
			@RequestParam(value = "page", defaultValue = "0") final int page,
			@RequestParam(value = "show", defaultValue = "Page") final ShowMode showMode,
			@RequestParam(value = "sort", required = false) final String sortCode,
			@RequestParam(value = "text", required = false) final String searchText,
			@RequestParam(value = "isShowListEnabled", required = false) final String isShowListEnabled,
			@RequestParam(value = "isShowInfo", required = false, defaultValue = "true") final String isShowInfo,
			@RequestParam(value = "isShowListHeringEnabled", required = false) final String isShowListHeringEnabled,
			final HttpServletRequest request,
			final Model model) throws CMSItemNotFoundException
	{
		this.isShowListEnabled = StringUtils.isEmpty(isShowListEnabled) ? false 
				: Boolean.valueOf(isShowListEnabled).booleanValue();
		
		this.isShowInfoEnabled = StringUtils.isEmpty(isShowInfo) ? true
				: Boolean.valueOf(isShowInfo).booleanValue();
		
		this.isShowListHeringEnabled = StringUtils.isEmpty(isShowListHeringEnabled) ? false 
				: Boolean.valueOf(isShowListHeringEnabled).booleanValue();
		
		final ProductSearchPageData<SearchStateData, ProductData> searchPageData = performSearch(searchQuery, page, showMode, sortCode, getSearchPageSize());
			
		populateModel(model, searchPageData, showMode);
		model.addAttribute("userLocation", customerLocationService.getUserLocation());

		if (searchPageData.getPagination().getTotalNumberOfResults() == 0)
		{
			storeCmsPageInModel(model, getContentPageForLabelOrId(NO_RESULTS_CMS_PAGE_ID));
			updatePageTitle(searchPageData.getFreeTextSearch(), searchPageData.getBreadcrumbs(), model);
		}
		else
		{
			storeContinueUrl(request);
			storeCmsPageInModel(model, getContentPageForLabelOrId(SEARCH_CMS_PAGE_ID));
			updatePageTitle(searchPageData.getFreeTextSearch(), searchPageData.getBreadcrumbs(), model);
		}
		model.addAttribute(WebConstants.BREADCRUMBS_KEY, searchBreadcrumbBuilder.getBreadcrumbs(null, searchPageData));
		model.addAttribute("pageType", HeringPageType.PRODUCTSEARCH.name());
		model.addAttribute("isShowListHeringEnabled", this.isShowListHeringEnabled);		
		model.addAttribute("isShowInfo", this.isShowInfoEnabled);		

		final String metaDescription = MetaSanitizerUtil.sanitizeDescription(getMessageSource().getMessage(
				"search.meta.description.results", null, getI18nService().getCurrentLocale())
				+ " "
				+ searchText
				+ " "
				+ getMessageSource().getMessage("search.meta.description.on", null, getI18nService().getCurrentLocale())
				+ " "
				+ getSiteName());
		final String metaKeywords = MetaSanitizerUtil.sanitizeKeywords(searchText);
		setUpMetaData(model, metaKeywords, metaDescription);
		
		if( this.isShowListHeringEnabled  ) {
			AbstractPageModel modelPage = (AbstractPageModel) model.asMap().get(CMS_PAGE_MODEL);
			model.asMap().remove(CMS_PAGE_MODEL);
			modelPage.getMasterTemplate().setFrontendTemplateName(SEARCH_LIST_PAGE);
			model.asMap().put(CMS_PAGE_MODEL, modelPage);
			
			return getViewForPage(model);
		}
		
		if( !this.isShowListHeringEnabled  ) {
			return getViewForPage(model);
		}

		if(this.isShowListEnabled) {
			AbstractPageModel modelPage = (AbstractPageModel) model.asMap().get(CMS_PAGE_MODEL);
			model.asMap().remove(CMS_PAGE_MODEL);
			modelPage.getMasterTemplate().setFrontendTemplateName(SEARCH_LIST_PAGE);
			model.asMap().put(CMS_PAGE_MODEL, modelPage);
		}		
		
		return getViewForPage(model);
	}

	protected ProductSearchPageData<SearchStateData, ProductData> performSearch(final String searchQuery, final int page,
			final ShowMode showMode, final String sortCode, final int pageSize)
	{
		final PageableData pageableData = createPageableData(page, pageSize, sortCode, showMode);

		final SearchStateData searchState = new SearchStateData();
		final SearchQueryData searchQueryData = new SearchQueryData();
		searchQueryData.setValue(searchQuery);
		searchState.setQuery(searchQueryData);

		return productSearchFacade.textSearch(searchState, pageableData);
	}
	
//	@ResponseBody
	@RequestMapping(value = "/results", method = RequestMethod.GET)
	public String jsonSearchResults(@RequestParam("q") final String searchQuery,
			@RequestParam(value = "page", defaultValue = "0") final int page,
			@RequestParam(value = "show", defaultValue = "Page") final ShowMode showMode,
			@RequestParam(value = "sort", required = false)  final String sortCode,
			@RequestParam(value = "isShowInfo", required = false, defaultValue="true") final String isShowInfo,
			@RequestParam(value = "isShowListHeringEnabled", required = false, defaultValue="false") final String isShowListHeringEnabled,
			@RequestParam(value = "isShowListEnabled", required = false, defaultValue="false") final String isShowListEnabled, final Model model) throws CMSItemNotFoundException
	{
		final ProductSearchPageData<SearchStateData, ProductData> searchPageData = performSearch(searchQuery, page, showMode,
				sortCode, getSearchPageSize());
		final SearchResultsData<ProductData> searchResultsData = new SearchResultsData<>();
		searchResultsData.setResults(searchPageData.getResults());
		searchResultsData.setPagination(searchPageData.getPagination());
		model.addAttribute("searchResultsData", searchResultsData);
		//model.addAttribute("isShowListEnabled", isShowListEnabled);		
		model.addAttribute("isShowListHeringEnabled", isShowListHeringEnabled);
		model.addAttribute("isShowInfo", isShowInfo);
		
		return ControllerConstants.Views.Fragments.Product.ProductLister;
		
	}

	@ResponseBody
	@RequestMapping(value = "/facets", method = RequestMethod.GET)
	public FacetRefinement<SearchStateData> getFacets(@RequestParam("q") final String searchQuery,
			@RequestParam(value = "page", defaultValue = "0") final int page,
			@RequestParam(value = "show", defaultValue = "Page") final ShowMode showMode,
			@RequestParam(value = "sort", required = false) final String sortCode) throws CMSItemNotFoundException
	{
		final SearchStateData searchState = new SearchStateData();
		final SearchQueryData searchQueryData = new SearchQueryData();
		searchQueryData.setValue(searchQuery);
		searchState.setQuery(searchQueryData);

		final ProductSearchPageData<SearchStateData, ProductData> searchPageData = productSearchFacade.textSearch(searchState,
				createPageableData(page, getSearchPageSize(), sortCode, showMode));
		final List<FacetData<SearchStateData>> facets = refineFacets(searchPageData.getFacets(),
				convertBreadcrumbsToFacets(searchPageData.getBreadcrumbs()));
		final FacetRefinement<SearchStateData> refinement = new FacetRefinement<>();
		refinement.setFacets(facets);
		refinement.setCount(searchPageData.getPagination().getTotalNumberOfResults());
		refinement.setBreadcrumbs(searchPageData.getBreadcrumbs());
		return refinement;
	}

	@ResponseBody
	@RequestMapping(value = "/autocomplete/" + COMPONENT_UID_PATH_VARIABLE_PATTERN, method = RequestMethod.GET)
	public AutocompleteResultData getAutocompleteSuggestions(@PathVariable final String componentUid,
			@RequestParam("term") final String term) throws CMSItemNotFoundException
	{
		final AutocompleteResultData resultData = new AutocompleteResultData();

		final SearchBoxComponentModel component = (SearchBoxComponentModel) cmsComponentService.getSimpleCMSComponent(componentUid);

		if (component.isDisplaySuggestions())
		{
			resultData.setSuggestions(subList(productSearchFacade.getAutocompleteSuggestions(term), component.getMaxSuggestions()));
		}

		if (component.isDisplayProducts())
		{
			resultData.setProducts(subList(productSearchFacade.textSearch(term).getResults(), component.getMaxProducts()));
		}

		return resultData;
	}

	protected <E> List<E> subList(final List<E> list, final int maxElements)
	{
		if (CollectionUtils.isEmpty(list))
		{
			return Collections.emptyList();
		}

		if (list.size() > maxElements)
		{
			return list.subList(0, maxElements);
		}

		return list;
	}

	protected <QUERY> void updatePageTitle(final String searchText, final List<BreadcrumbData<QUERY>> appliedFacets, final Model model)
	{
		if(CollectionUtils.isEmpty(appliedFacets)) {
			storeContentPageTitleInModel(
					model,
					getPageTitleResolver().resolveContentPageTitle(
							getMessageSource().getMessage("search.meta.title", null, getI18nService().getCurrentLocale()) + " "
									+ searchText));
		} else {
			storeContentPageTitleInModel(
					model,
					getPageTitleResolver().resolveSearchPageTitle(searchText, appliedFacets));
		}
	}
		
	@RequestMapping(value = "/images", method = RequestMethod.GET)
	public @ResponseBody String getImagensJson(@RequestParam(value="products[]", required = true) String[] products)
	{
		if(true) {
			return "";
		}
		
		HashSet<ProductPreviewData> set = new HashSet<ProductPreviewData>();
		HashSet<String> productCodes = new HashSet<String>(Arrays.asList(products));
		for(String code : productCodes)
		{
			set.addAll(productFacade.getAvailableStyleVariants(code));
		}
		
		final List<ProductPreviewData> productVariants = new ArrayList<ProductPreviewData>(set);
		productCodes = null;
		set = null;

		if (CollectionUtils.isEmpty(productVariants))
		{	
			return "";
		}

		List<ProductPreviewData> availableSizes = null;
		final StringBuilder sb = new StringBuilder("{");
		final String ch = "\"";
		
		for (ProductPreviewData variant : productVariants)
		{
			String imgUrl = "";
			if (CollectionUtils.isNotEmpty(variant.getImages()))
			{
				for (ImageData img : variant.getImages())
				{
					if (img != null && "store".equals(img.getFormat()))
					{
						imgUrl = img.getUrl();
						break;
					}
				}
			}

			sb.append(ch).append(variant.getCode().split("_")[0]);

			if (variant.getColor() != null)
			{
				sb.append(variant.getColor().getRGB());
			}

			sb.append(ch).append(":").append("[").append(ch).append(imgUrl).append(ch).append(",").append(ch);

			if (variant.getUrl() != null)
			{
				sb.append(variant.getUrl());
			}

			sb.append(ch).append(",[");
			
			availableSizes = productFacade.getAvailableSizeVariants(variant.getCode());
			if (CollectionUtils.isNotEmpty(availableSizes)) 
			{	
				for (final ProductPreviewData s : availableSizes) 
				{					
					if (s.getColor().getRGB().equals(variant.getColor().getRGB()))
					{			
						sb.append(ch).append(s.getSize()).append(ch).append(",");
					}
				}	
				sb.deleteCharAt(sb.lastIndexOf(","));
			}
			
			sb.append("],").append(ch).append(variant.getCode()).append(ch).append("],");					
		}

		sb.append("}");

		return sb.toString().replace(",}", "}");
	}

	@RequestMapping(value = "/urlfromsize/{code}/{size}/{color}", method = RequestMethod.GET)
	public @ResponseBody String getUrlFromSize(@PathVariable String code, @PathVariable String size, @PathVariable String color)
	{
		if(true) {
			return "";
		}
		
		String json = "";

		if (StringUtils.isNotEmpty(code) && StringUtils.isNotEmpty(size) && StringUtils.isNotEmpty(color))
		{
			final List<ProductPreviewData> lista = productFacade.getAvailableSizeVariants(code);

			for (ProductPreviewData p : lista)
			{				
				if (size.equals(p.getSize()) && p.getColor().getRGB().equals("#" + color))
				{
					json = "{\"url\": \"" + p.getUrl() + "\", \"sku\": \"" + p.getCode() + "\"}";

					break;
				}
			}
		}

		return json;
	}
	
	@RequestMapping(value = "/previews", method = RequestMethod.GET)
	public @ResponseBody String getVariantData(@RequestParam(value = "products", required = true) String[] products) throws JsonProcessingException, IOException
	{
		// searches for all style variants available for each product parameter
		Map<String, ProductPreviewData> styleMap = new HashMap<>();
		Map<String, List<ProductPreviewData>> sizeMap = new HashMap<>();
		Set<String> codeCache = new HashSet<>();
		for (String product : products) {
			// don't search twice for the same product code
			if(!codeCache.contains(product)) {
				codeCache.add(product);
				for (ProductPreviewData variant : productFacade.getAvailableStyleVariants(product)) {
					if (!styleMap.containsKey(variant.getCode())) {
						String variantCode = variant.getCode();
						styleMap.put(variantCode, variant);
						List<ProductPreviewData> availableSizes = productFacade.getAvailableChildVariants(variantCode);
						sizeMap.put(variantCode, availableSizes);
						// search result may include codes in products
						codeCache.add(variantCode);
						for(ProductPreviewData sizeVariant : availableSizes) {
							codeCache.add(sizeVariant.getCode());
						}
					}
				}
			}
		}

		/* creates response object
		 * example:
		 * {
		 * 	"1234_5678": {
		 * 		"rgb": "#FFFFFF",
		 * 		"url": "/p/1234_5678",
		 * 		"imageUrl": "/img/img.jpg",
		 * 		"availableSizes:[
		 * 			{
		 * 				"code": "12345678s1",
		 * 				"size": "XG",
		 * 				"url": "/p/12345678s1"
		 * 			},
		 * 			{
		 * 				"code": "12345678s2",
		 * 				"size": "XXG",
		 * 				"url": "/p/12345678s2"
		 * 			}
		 * 		]
		 * 	}
		 * }
		*/ 
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode previewResponse = mapper.createObjectNode();
		for (String product : products)
		{
			ProductPreviewData variant = styleMap.get(product);
			if (variant != null)
			{
				ObjectNode productNode = mapper.createObjectNode();
				productNode.put("rgb", variant.getColor().getRGB());
				productNode.put("url", variant.getUrl());
				String imageUrl = "";
				for (ImageData img : variant.getImages())
				{
					if (img != null && "store".equals(img.getFormat()))
					{
						imageUrl = img.getUrl();
						break;
					}
				}
				productNode.put("imageUrl", imageUrl);
				ArrayNode availableSizes = mapper.createArrayNode();
				for (ProductPreviewData sizeVariant : sizeMap.get(variant.getCode())) {
					ObjectNode sizeNode = mapper.createObjectNode();
					sizeNode.put("code", sizeVariant.getCode());
					sizeNode.put("size", sizeVariant.getSize());
					sizeNode.put("url", sizeVariant.getUrl());
					availableSizes.add(sizeNode);
				}
				productNode.put("availableSizes", availableSizes);
				previewResponse.put(product, productNode);
			}
		}

		return mapper.writeValueAsString(previewResponse);
	}
	
	@RequestMapping(value = "/select-size-color/{selectedProduct:.*}",
			method = RequestMethod.GET)
	public String doSelectSizeColor(final Model model,
			@PathVariable("selectedProduct") final String selectedProduct) {
		List<ProductPreviewData> productSelected = this.productFacade.getAvailableSizeVariants(selectedProduct);
		List<ProductPreviewData> resultData = new LinkedList<ProductPreviewData>();
		String firstProduct =  "";
		
		for (ProductPreviewData productPreviewData : productSelected)
		{
			if(productPreviewData.getCode().startsWith(selectedProduct.replace("_", "")))
			{
				if(firstProduct.isEmpty())
				{
					firstProduct = productPreviewData.getCode();
				}
				
				resultData.add(productPreviewData);
			}
		}
		
		model.addAttribute("productSelected", resultData);
		model.addAttribute("firstProduct", firstProduct);
		return ControllerConstants.Views.Pages.Product.ProductSizeColor;
	}
	
	
}
