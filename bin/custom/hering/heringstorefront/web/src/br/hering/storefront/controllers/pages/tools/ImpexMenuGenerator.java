/**
 * 
 */
package br.hering.storefront.controllers.pages.tools;

import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.contents.components.CMSLinkComponentModel;
import de.hybris.platform.cms2.model.navigation.CMSNavigationNodeModel;
import de.hybris.platform.cms2.servicelayer.services.CMSNavigationService;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.search.ProductSearchFacade;
import de.hybris.platform.commercefacades.search.data.SearchQueryData;
import de.hybris.platform.commercefacades.search.data.SearchStateData;
import de.hybris.platform.commerceservices.search.facetdata.FacetData;
import de.hybris.platform.commerceservices.search.facetdata.FacetValueData;
import de.hybris.platform.commerceservices.search.facetdata.ProductSearchPageData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.store.services.BaseStoreService;

import java.io.PrintWriter;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.WordUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.hering.storefront.controllers.pages.AbstractSearchPageController;

@Controller
@RequestMapping("/impex")
public class ImpexMenuGenerator extends AbstractSearchPageController
{
	@Resource
	private FlexibleSearchService flexibleSearchService;
	
	@Resource
	private ProductService productService;
	
	@Resource
	private ProductSearchFacade<ProductData> productSearchFacade;
	
	@Resource
	private	CMSNavigationService cmsNavigationService;
	
	@Resource
	private	BaseStoreService baseStoreService;
	
	private Map<String, Map<String, String>> linkNameMapping;
	
	private Map<String, String> searchQueryMapping;
	
	private static final String IMPEX_LINK_COMPONENT_HEADER = "INSERT_UPDATE CMSLinkComponent;$contentCV[unique=true];linkName[lang=pt];uid[unique=true];name;url[default='${LINK_HOLDER}'];category(code, $productCV);target(code)[default='sameWindow'];;;";
	private static final String IMPEX_LINK_NO_CATEGORY_LINE = ";;\"{linkName}\";{type}Link;{type} Link;;;;;";
	private static final String IMPEX_LINK_WITH_CATEGORY_LINE = ";;\"{cat_name}\";{type}{cat_name_no_space}{cat_code}Link;{type} {cat_name} Link;;{cat_code};;;";
	private static final String IMPEX_LINK_CUSTOM_SUBLINK_LINE = ";;\"{custom_name}\";{type}{custom_name_no_space}Link;{type} {custom_name} Link;{custom_url};;;;";

	private static final String IMPEX_NAVIGATION_NODE_HEADER = "INSERT_UPDATE CMSNavigationNode;uid[unique=true];$contentCV[unique=true];name;parent(uid, $contentCV);children(uid,$contentCV)[mode=append];links(uid, $contentCV);&nodeRef";
	private static final String IMPEX_NAVIGATION_NODE_LINE = ";{type}NavNodeMenu;;{type} Menu;;;{holder};{type}NavNodeMenu;";
	
	private static final String LINK_TYPE_JEANS = "jeans";
	private static final String LINK_TYPE_ACCESSORIES = "acessorios";
	private static final String LINK_TYPE_SALES = "rapa";
	private static final String LINK_TYPE_MALE = "masculino";
	private static final String LINK_TYPE_FEMALE = "feminino";
	private static final String LINK_TYPE_KIDS = "kids";
	private static final String LINK_TYPE_BASIC = "basic";
	private static final String LINK_TYPE_ACTIVEWEAR = "activewear";
	private static final String LINK_TYPE_BEACHWEAR = "beachwear";
	private static final String LINK_TYPE_UNDERWEAR = "underwear";
	private static final String LINK_TYPE_LOUNGEWEAR = "loungewear";
	private static final String LINK_TYPE_SLEEPWEAR = "sleepwear";
	
	private static final String LINK_TYPE_DEFAULT_SORTING = "name-asc";
	private static final String LINK_TYPE_CUSTOM_SORTING = "relevance";
	
	{
		initSearchQueryMapping();
		initLinkNameMapping();
	}

	/**
	 * 
	 */
	private void initSearchQueryMapping()
	{
		searchQueryMapping = new HashMap<String, String>();
		searchQueryMapping.put(LINK_TYPE_JEANS, ":" + LINK_TYPE_DEFAULT_SORTING + ":jeans:true");
		searchQueryMapping.put(LINK_TYPE_ACCESSORIES, ":" + LINK_TYPE_DEFAULT_SORTING + ":grupo:Acessórios");
		searchQueryMapping.put(LINK_TYPE_SALES, ":" + LINK_TYPE_DEFAULT_SORTING + ":sales:true");
		searchQueryMapping.put(LINK_TYPE_MALE, ":" + LINK_TYPE_DEFAULT_SORTING + ":gender:MALE");
		searchQueryMapping.put(LINK_TYPE_FEMALE, ":" + LINK_TYPE_DEFAULT_SORTING + ":gender:FEMALE");
		searchQueryMapping.put(LINK_TYPE_KIDS, ":" + LINK_TYPE_DEFAULT_SORTING + ":kidsClothing:true");
		searchQueryMapping.put(LINK_TYPE_BASIC, ":" + LINK_TYPE_DEFAULT_SORTING + ":linha:Básico");
		searchQueryMapping.put(LINK_TYPE_ACTIVEWEAR, ":" + LINK_TYPE_DEFAULT_SORTING + ":gender:FEMALE");
		searchQueryMapping.put(LINK_TYPE_BEACHWEAR, ":" + LINK_TYPE_DEFAULT_SORTING + ":gender:FEMALE");
		searchQueryMapping.put(LINK_TYPE_UNDERWEAR, ":" + LINK_TYPE_DEFAULT_SORTING + ":gender:FEMALE");
		searchQueryMapping.put(LINK_TYPE_LOUNGEWEAR, ":" + LINK_TYPE_DEFAULT_SORTING + ":gender:FEMALE");
		searchQueryMapping.put(LINK_TYPE_SLEEPWEAR, ":" + LINK_TYPE_DEFAULT_SORTING + ":gender:FEMALE");
	}

	/**
	 * 
	 */
	private void initLinkNameMapping()
	{
		final Map<String,String> dzarmMap = new HashMap<String, String>();
		dzarmMap.put(LINK_TYPE_JEANS, "Jeans");
		dzarmMap.put(LINK_TYPE_ACCESSORIES, "Accessories");
		dzarmMap.put(LINK_TYPE_SALES, "Sale");
//		dzarmMap.put(LINK_TYPE_MALE, "Masculino");
		dzarmMap.put(LINK_TYPE_FEMALE, "Feminino");
		dzarmMap.put(LINK_TYPE_BASIC, "Essentials");
		
		final Map<String,String> heringMap = new HashMap<String, String>();
		heringMap.put(LINK_TYPE_JEANS, "Jeans");
		heringMap.put(LINK_TYPE_ACCESSORIES, "Acessórios");
		heringMap.put(LINK_TYPE_SALES, "Promoção");
		heringMap.put(LINK_TYPE_MALE, "Masculino");
		heringMap.put(LINK_TYPE_FEMALE, "Feminino");
		heringMap.put(LINK_TYPE_KIDS, "Hering Kids");
		heringMap.put(LINK_TYPE_BASIC, "Básicos");
		
		final Map<String,String> foryouMap = new HashMap<String, String>();
		foryouMap.put(LINK_TYPE_ACTIVEWEAR, "Activewear");
		foryouMap.put(LINK_TYPE_BEACHWEAR, "Beachwear");
		foryouMap.put(LINK_TYPE_UNDERWEAR, "Underwear");
		foryouMap.put(LINK_TYPE_LOUNGEWEAR, "Loungewear");
		foryouMap.put(LINK_TYPE_SLEEPWEAR, "Sleepwear");
		
		linkNameMapping = new HashMap<String, Map<String,String>>();
		linkNameMapping.put("dzarm", dzarmMap);
		linkNameMapping.put("hering", heringMap);
		linkNameMapping.put("foryou", foryouMap);
	}
	
	@RequestMapping(value = "cms-menu", method = RequestMethod.GET)
	public void generateWholeMenu(HttpServletResponse response)
	{
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		
		genericGenerator(response, LINK_TYPE_JEANS);
		genericGenerator(response, LINK_TYPE_ACCESSORIES);
		genericGenerator(response, LINK_TYPE_SALES);
		genericGenerator(response, LINK_TYPE_MALE);
		genericGenerator(response, LINK_TYPE_FEMALE);

		genericGenerator(response, LINK_TYPE_KIDS);

		genericGenerator(response, LINK_TYPE_ACTIVEWEAR);
		genericGenerator(response, LINK_TYPE_BEACHWEAR);
		genericGenerator(response, LINK_TYPE_UNDERWEAR);
		genericGenerator(response, LINK_TYPE_LOUNGEWEAR);
		genericGenerator(response, LINK_TYPE_SLEEPWEAR);
	}

	/**
	 * 
	 * @param response
	 * @param type
	 */
	private void genericGenerator(HttpServletResponse response, String type)
	{
		if (!storeContainsType(type))
			return;
		try
		{
			final String filterLink = type + "FilterLink";
			List<String> results = refineSearch(getSearchQuery(type));
			type = WordUtils.capitalize(type.toLowerCase()).replace(" ", "");
   		String header = IMPEX_LINK_COMPONENT_HEADER.replaceAll("\\{LINK_HOLDER\\}", filterLink);
   		List<String> values = new ArrayList<String>();
			
   		String noCategoryLink = IMPEX_LINK_NO_CATEGORY_LINE
   				.replaceAll("\\{linkName\\}", generateLinkName(type))
   				.replaceAll("\\{type\\}", type);
   		
			values.add(noCategoryLink);
			
			addCustomSubLinks(type, values);
			
			if(CollectionUtils.isNotEmpty(results))
   		{
   			for(String categoryLine : results)
   			{
   				String categoryName = WordUtils.capitalize(categoryLine.split(";")[0].toLowerCase()).trim();
   				String categoryCode = categoryLine.split(";")[1];
   				String withCategoryLink = IMPEX_LINK_WITH_CATEGORY_LINE 
   						.replaceAll("\\{cat_name\\}", categoryName)
   						.replaceAll("\\{cat_name_no_space\\}", removeAcentos(categoryName.replace(" ", "")))
   						.replaceAll("\\{type\\}", type)
   						.replaceAll("\\{cat_code\\}", categoryCode);
   				values.add(withCategoryLink);
   			}
   		}
   		
   		List<String> navNodeValues = new ArrayList<String>();
			prepareNavNode(type, navNodeValues, values);

			printList(type + " related links", response.getWriter(), header, values);
			printList(type + " navigation menu", response.getWriter(), IMPEX_NAVIGATION_NODE_HEADER, navNodeValues);
		}
   	catch (Exception e)
   	{
   		LOG.error("error", e);
   	}
	}

	/**
	 * @param type
	 * @return
	 */
	private boolean storeContainsType(String type)
	{
		String storeId = getStoreId();
		return linkNameMapping.containsKey(storeId)
				&& linkNameMapping.get(storeId).containsKey(type);
	}
	
	/**
	 * @param type
	 * @param values
	 */
	private void addCustomSubLinks(String type, List<String> values)
	{
		if(!(type.equalsIgnoreCase(LINK_TYPE_FEMALE) || type.equalsIgnoreCase(LINK_TYPE_MALE)))
		{
			return;
		}
		
		String name = generateLinkName(LINK_TYPE_BASIC);
		String basicLink = IMPEX_LINK_CUSTOM_SUBLINK_LINE 
				.replaceAll("\\{custom_name\\}", name)
				.replaceAll("\\{custom_name_no_space\\}", removeAcentos(name.replace(" ", "")))
				.replaceAll("\\{custom_url\\}", generateCustomLinkUrl(type, LINK_TYPE_BASIC))
				.replaceAll("\\{type\\}", type);
		
		values.add(basicLink);
	}

	public List<String> refineSearch(final String searchQuery)
	{
		final PageableData pageableData = createPageableData(0, getSearchPageSize(), null, ShowMode.Page);

		final SearchQueryData searchQueryData = new SearchQueryData();
		searchQueryData.setValue(searchQuery);

		final SearchStateData searchState = new SearchStateData();
		searchState.setQuery(searchQueryData);

		ProductSearchPageData<SearchStateData, ProductData> searchPageData = productSearchFacade.textSearch(searchState, pageableData);

		if (searchPageData.getPagination().getTotalNumberOfResults() == 0)
		{
			LOG.info("nada encontrado");
			return Collections.emptyList();
		}
		
		for(FacetData<SearchStateData> facet : searchPageData.getFacets())
		{
			if("category".equalsIgnoreCase(facet.getCode()))
			{
				List<String> result = new ArrayList<String>();

				for(FacetValueData<SearchStateData> category : facet.getValues()) 
				{
					result.add(category.getName() + ";" + category.getCode());
				}
				
				return result;
			}
		}
		
		return Collections.emptyList();
	}
	
	/**
	 * 
	 * @param type
	 * @param navNodeValues
	 * @param values
	 */
	private void prepareNavNode(String type, List<String> navNodeValues, Collection<String> values) 
	{
		String allLinks = extractAllLinks(values).replaceAll(type + "Link,", "");
		String idNavigationNode = type + "NavNodeMenu";

		try
		{
			//Adding Fixed New Link Lines to Menu	
			CMSNavigationNodeModel menuCMSnode = cmsNavigationService.getNavigationNodeForId(idNavigationNode);
			if(menuCMSnode != null)
			{      				
   				List<CMSLinkComponentModel> lista = menuCMSnode.getLinks();
   				
      		if(CollectionUtils.isNotEmpty(lista))
      			{
         			for (CMSLinkComponentModel cmsLinkComponentModel : lista)
         			{
         				if(cmsLinkComponentModel.getUid().contains("Fixed"))
         				{	
         					String strUid = cmsLinkComponentModel.getUid();
         					LOG.info("Fixed Menu Item Founded --> Adding Link to Menu Node: " + strUid + " to Menu: " + idNavigationNode);
         					allLinks = allLinks + strUid + ",";
         				}
         			}
      			}
			}
		}
		catch(CMSItemNotFoundException e)
		{
			//
		}
   	catch(Exception e)
   	{
   		LOG.error("error", e);
   	}
		
		String value = IMPEX_NAVIGATION_NODE_LINE
				.replaceAll("\\{type\\}", type)
				.replaceAll("\\{holder\\}", allLinks);
		
		navNodeValues.add(value);
	}

	/**
	 * 
	 * @param values
	 * @return
	 */
	private String extractAllLinks(Collection<String> values)
	{
		if(CollectionUtils.isEmpty(values))
		{
			return "";
		}
		
		StringBuilder allLinks = new StringBuilder();
		
		for(String s : values)
		{
			allLinks.append(s.split(";")[3]).append(",");
		}
		
		return allLinks.toString();
	}
	
	/**
	 * 
	 * @param type
	 * @param printWriter
	 * @param header
	 * @param values
	 */
	private void printList(String type, PrintWriter printWriter, String header, Collection<String> values)
	{
		printWriter.println("\n#" + type);
		printWriter.println(header);
		
		for(String value : values)
		{
			printWriter.println(value);
		}
	}
	
	public String removeAcentos(String str) 
	{
	  str = Normalizer.normalize(str, Normalizer.Form.NFD);
	  str = str.replaceAll("[^\\p{ASCII}]", "");
	  return str;
	}
	
	private String getStoreId()
	{
		return baseStoreService.getCurrentBaseStore().getUid();
	}
	
	/**
	 * @param type
	 * @return
	 */
	private String generateLinkName(String type)
	{
		String key = type.toLowerCase();
		
		String storeId = getStoreId();
		
		return linkNameMapping.get(storeId).get(key);
	}
	
	private String generateCustomLinkUrl(String type, String customType)
	{
		String parentSearchQuery = getSearchQuery(type)
				.replaceAll(LINK_TYPE_DEFAULT_SORTING, LINK_TYPE_CUSTOM_SORTING);
		
		String childrenSearchQuery = getSearchQuery(customType)
				.replaceAll(":" + LINK_TYPE_DEFAULT_SORTING, parentSearchQuery);

		return "/search?q=" + childrenSearchQuery;
	}
	
	private String getSearchQuery(String type)
	{
		type = type.toLowerCase();
		return searchQueryMapping.get(type);
	}
}