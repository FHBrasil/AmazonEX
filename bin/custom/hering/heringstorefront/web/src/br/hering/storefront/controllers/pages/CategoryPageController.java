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

import de.hybris.platform.acceleratorcms.data.RequestContextData;
import de.hybris.platform.acceleratorservices.customer.CustomerLocationService;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.impl.SearchBreadcrumbBuilder;
import de.hybris.platform.acceleratorstorefrontcommons.constants.WebConstants;
import de.hybris.platform.acceleratorstorefrontcommons.util.XSSFilterUtil;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.cms2.model.pages.CategoryPageModel;
import de.hybris.platform.cms2.servicelayer.services.CMSPageService;
import de.hybris.platform.commercefacades.product.data.CategoryData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.search.ProductSearchFacade;
import de.hybris.platform.commercefacades.search.data.SearchQueryData;
import de.hybris.platform.commercefacades.search.data.SearchStateData;
import de.hybris.platform.commerceservices.category.CommerceCategoryService;
import de.hybris.platform.commerceservices.search.facetdata.BreadcrumbData;
import de.hybris.platform.commerceservices.search.facetdata.FacetData;
import de.hybris.platform.commerceservices.search.facetdata.FacetRefinement;
import de.hybris.platform.commerceservices.search.facetdata.ProductCategorySearchPageData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.url.UrlResolver;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jfree.util.Log;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.hering.facades.product.HeringProductFacade;
import br.hering.storefront.controllers.ControllerConstants;
import br.hering.storefront.util.HeringPageType;
import br.hering.storefront.util.MetaSanitizerUtil;

import com.flieger.eventtracking.facade.EventTrackerFacade;

/**
 * Controller for a category page
 */
@Controller
@Scope("tenant")
@RequestMapping(value = "/**/c")
public class CategoryPageController extends AbstractSearchPageController
{
	protected static final Logger LOG = Logger.getLogger(CategoryPageController.class);

	private static final String PRODUCT_GRID_PAGE = "category/productGridPage";
	private static final String PRODUCT_LIST_PAGE = "category/productListPage";
	
	public boolean isShowListEnabled = false;
	private boolean isShowInfoEnabled = true;
	private boolean isShowListHeringEnabled = false;
	
	/**
	 * We use this suffix pattern because of an issue with Spring 3.1 where a Uri value is incorrectly extracted if it
	 * contains on or more '.' characters. Please see https://jira.springsource.org/browse/SPR-6164 for a discussion on
	 * the issue and future resolution.
	 */
	private static final String CATEGORY_CODE_PATH_VARIABLE_PATTERN = "/{categoryCode:.*}";

	@Resource(name = "productSearchFacade")
	private ProductSearchFacade<ProductData> productSearchFacade;

	@Resource(name = "cmsPageService")
	private CMSPageService cmsPageService;

	@Resource(name = "commerceCategoryService")
	private CommerceCategoryService commerceCategoryService;

	@Resource(name = "searchBreadcrumbBuilder")
	private SearchBreadcrumbBuilder searchBreadcrumbBuilder;

	@Resource(name = "categoryModelUrlResolver")
	private UrlResolver<CategoryModel> categoryModelUrlResolver;

	@Resource(name = "customerLocationService")
	private CustomerLocationService customerLocationService;
	
	@Resource(name = "productFacade")	
	private HeringProductFacade productFacade;	
	
	@Resource
	private EventTrackerFacade eventTrackerFacade;

	@SuppressWarnings("boxing")
	@RequestMapping(value = CATEGORY_CODE_PATH_VARIABLE_PATTERN, method = RequestMethod.GET)
	public String category(@PathVariable("categoryCode") final String categoryCode,
			@RequestParam(value = "q", required = false) final String searchQuery,
			@RequestParam(value = "page", defaultValue = "0") final int page,
			@RequestParam(value = "show", defaultValue = "Page") final ShowMode showMode,
			@RequestParam(value = "sort", required = false) final String sortCode,
			@RequestParam(value = "isShowListHeringEnabled", required = false) final String isShowListHeringEnabled,
			@RequestParam(value = "isShowListEnabled", required = false) final String isShowListEnabled,
			@RequestParam(value = "isShowInfo", required = false, defaultValue = "true") final String isShowInfo,
			final Model model,
			final HttpServletRequest request, final HttpServletResponse response) throws UnsupportedEncodingException
	{
		this.isShowListEnabled = StringUtils.isEmpty(isShowListEnabled) ? false 
				: Boolean.valueOf(isShowListEnabled).booleanValue();
		
		this.isShowInfoEnabled = StringUtils.isEmpty(isShowInfo) ? true 
				: Boolean.valueOf(isShowInfo).booleanValue();
		
		this.isShowListHeringEnabled = StringUtils.isEmpty(isShowListHeringEnabled) ? false 
				: Boolean.valueOf(isShowListHeringEnabled).booleanValue();
		//TODO
		//HORA E UM HR E OUTRO
		this.isShowListEnabled = this.isShowListEnabled || this.isShowListHeringEnabled;
		
		final CategoryModel category = commerceCategoryService.getCategoryForCode(categoryCode);

		final String redirection = checkRequestUrl(request, response, categoryModelUrlResolver.resolve(category));
		if (StringUtils.isNotEmpty(redirection))
		{
			return redirection;
		}
		
		final CategoryPageModel categoryPage = getCategoryPage(category);

		final CategorySearchEvaluator categorySearch = new CategorySearchEvaluator(categoryCode, XSSFilterUtil.filter(searchQuery),
				page, showMode, sortCode, categoryPage);
		categorySearch.doSearch();

		final ProductCategorySearchPageData<SearchStateData, ProductData, CategoryData> searchPageData = categorySearch
				.getSearchPageData();
		final boolean showCategoriesOnly = categorySearch.isShowCategoriesOnly();
		
		storeCmsPageInModel(model, categorySearch.categoryPage);
		storeContinueUrl(request);

		populateModel(model, searchPageData, showMode);
		model.addAttribute(WebConstants.BREADCRUMBS_KEY, searchBreadcrumbBuilder.getBreadcrumbs(categoryCode, searchPageData));
		model.addAttribute("showCategoriesOnly", Boolean.valueOf(showCategoriesOnly));
		model.addAttribute("categoryName", category.getName());
		model.addAttribute("categoryCOde", category.getCode());
		model.addAttribute("categoryGender", !searchPageData.getBreadcrumbs().isEmpty()
				? searchPageData.getBreadcrumbs().get(0).getFacetValueName() : "");
		model.addAttribute("pageType", HeringPageType.CATEGORY.name());
		model.addAttribute("userLocation", customerLocationService.getUserLocation());
		model.addAttribute("isShowListEnabled", this.isShowListEnabled);
		model.addAttribute("isShowInfo", this.isShowInfoEnabled);	
		model.addAttribute("isShowListHeringEnabled", this.isShowListHeringEnabled);

		updatePageTitle(category, searchPageData.getBreadcrumbs(), model);
		final RequestContextData requestContextData = getRequestContextData(request);
		requestContextData.setCategory(category);
		requestContextData.setSearch(searchPageData);

		if (searchQuery != null)
		{
			model.addAttribute("metaRobots", "no-index,follow");
		}

		final String metaKeywords = MetaSanitizerUtil.sanitizeKeywords(category.getKeywords());
		final String metaDescription = MetaSanitizerUtil.sanitizeDescription(category.getDescription());
		setUpMetaData(model, metaKeywords, metaDescription);
		
		if(request.getServerName().toLowerCase().indexOf("hering") >= 0  ) {
			return getViewPage(categorySearch.categoryPage, this.isShowListHeringEnabled);
		}		
		
		eventTrackerFacade.trackCategoryView(convertCategoryModelIntoData(category));
		
		return getViewPage(categorySearch.categoryPage, this.isShowListEnabled);
	}

	@ResponseBody
	@RequestMapping(value = CATEGORY_CODE_PATH_VARIABLE_PATTERN + "/facets", method = RequestMethod.GET)
	public FacetRefinement<SearchStateData> getFacets(@PathVariable("categoryCode") final String categoryCode,
			@RequestParam(value = "q", required = false) final String searchQuery,
			@RequestParam(value = "page", defaultValue = "0") final int page,
			@RequestParam(value = "show", defaultValue = "Page") final ShowMode showMode,
			@RequestParam(value = "sort", required = false) final String sortCode,
			@RequestParam(value = "isShowInfo", required = false, defaultValue = "true") final String isShowInfo,
			@RequestParam(value = "isShowListHeringEnabled", required = false) final String isShowListHeringEnabled,
			@RequestParam(value = "isShowListEnabled", required = false) final String isShowListEnabled) throws UnsupportedEncodingException
	{
		final CategoryModel category = commerceCategoryService.getCategoryForCode(categoryCode);
		final CategoryPageModel categoryPage = getCategoryPage(category);
		
		this.isShowListEnabled = StringUtils.isEmpty(isShowListEnabled) ? false 
				: Boolean.valueOf(isShowListEnabled).booleanValue();

		
		this.isShowListHeringEnabled = StringUtils.isEmpty(isShowListHeringEnabled) ? false 
				: Boolean.valueOf(isShowListHeringEnabled).booleanValue();
		
		final CategorySearchEvaluator categorySearch = new CategorySearchEvaluator(categoryCode, searchQuery, page, showMode,
				sortCode, categoryPage);
		categorySearch.doSearch();

		final ProductCategorySearchPageData<SearchStateData, ProductData, CategoryData> searchPageData = categorySearch
				.getSearchPageData();

		final List<FacetData<SearchStateData>> facets = refineFacets(searchPageData.getFacets(),
				convertBreadcrumbsToFacets(searchPageData.getBreadcrumbs()));
		final FacetRefinement<SearchStateData> refinement = new FacetRefinement<>();
		refinement.setFacets(facets);
		refinement.setCount(searchPageData.getPagination().getTotalNumberOfResults());
		refinement.setBreadcrumbs(searchPageData.getBreadcrumbs());		
		return refinement;
	}

//	@ResponseBody
	@SuppressWarnings("boxing")
	@RequestMapping(value = CATEGORY_CODE_PATH_VARIABLE_PATTERN + "/results", method = RequestMethod.GET)
	public String getResults(@PathVariable("categoryCode") final String categoryCode,
			@RequestParam(value = "q", required = false) final String searchQuery,
			@RequestParam(value = "page", defaultValue = "0") final int page,
			@RequestParam(value = "show", defaultValue = "Page") final ShowMode showMode,
			@RequestParam(value = "sort", required = false) final String sortCode,
			@RequestParam(value = "isShowInfo", required = false, defaultValue = "true") final String isShowInfo,
			@RequestParam(value = "isShowListHeringEnabled", required = false) final String isShowListHeringEnabled,
			@RequestParam(value = "isShowListEnabled", required = false, defaultValue="false") final String isShowListEnabled, final Model model) throws UnsupportedEncodingException
	{
		final CategoryModel category = commerceCategoryService.getCategoryForCode(categoryCode);
		final CategoryPageModel categoryPage = getCategoryPage(category);
		this.isShowListEnabled = StringUtils.isEmpty(isShowListEnabled) ? false 
				: Boolean.valueOf(isShowListEnabled).booleanValue();
		
		this.isShowInfoEnabled = StringUtils.isEmpty(String.valueOf(isShowInfoEnabled)) ? true 
				: Boolean.valueOf(isShowInfoEnabled).booleanValue();
		
		this.isShowListHeringEnabled = StringUtils.isEmpty(isShowListHeringEnabled) ? false 
				: Boolean.valueOf(isShowListHeringEnabled).booleanValue();
		
		final CategorySearchEvaluator categorySearch = new CategorySearchEvaluator(categoryCode, searchQuery, page, showMode,
				sortCode, categoryPage);
		categorySearch.doSearch();

		final ProductCategorySearchPageData<SearchStateData, ProductData, CategoryData> searchPageData = categorySearch
				.getSearchPageData();

		final SearchResultsData<ProductData> searchResultsData = new SearchResultsData<>();
		searchResultsData.setResults(searchPageData.getResults());
		searchResultsData.setPagination(searchPageData.getPagination());
		model.addAttribute("searchResultsData", searchResultsData);
		//model.addAttribute("isShowListEnabled", isShowListEnabled);		
		model.addAttribute("isShowInfo", this.isShowInfoEnabled);
		model.addAttribute("isShowListHeringEnabled", this.isShowListHeringEnabled);

		
		return ControllerConstants.Views.Fragments.Product.ProductLister;
	}

	protected boolean categoryHasDefaultPage(final CategoryPageModel categoryPage)
	{
		return Boolean.TRUE.equals(categoryPage.getDefaultPage());
	}

	protected CategoryPageModel getCategoryPage(final CategoryModel category)
	{
		try
		{
			return cmsPageService.getPageForCategory(category);
		}
		catch (final CMSItemNotFoundException ignore)
		{
			// Ignore
		}
		return null;
	}

	protected CategoryPageModel getDefaultCategoryPage()
	{
		try
		{
			return cmsPageService.getPageForCategory(null);
		}
		catch (final CMSItemNotFoundException ignore)
		{
			// Ignore
		}
		return null;
	}

	protected <QUERY> void updatePageTitle(final CategoryModel category, final List<BreadcrumbData<QUERY>> appliedFacets,
			final Model model)
	{
		storeContentPageTitleInModel(model, getPageTitleResolver().resolveCategoryPageTitle(category, appliedFacets));
	}

	protected String getViewPage(final CategoryPageModel categoryPage, final boolean isShowListEnabled)
	{
		if(isShowListEnabled)
		{
			return PAGE_ROOT + PRODUCT_LIST_PAGE;
		}
		if (categoryPage != null)
		{
			final String targetPage = getViewForPage(categoryPage);
			if (targetPage != null && !targetPage.isEmpty())
			{
				return targetPage;
			}
		}
		return PAGE_ROOT + PRODUCT_GRID_PAGE;
	}
	
	private CategoryData convertCategoryModelIntoData(final CategoryModel categoryModel)
	{
		final CategoryData categoryData = new CategoryData();
		if(categoryModel != null){
			categoryData.setCode(categoryModel.getCode());
			categoryData.setName(categoryModel.getName());
		}
		return categoryData;
	}

	@ExceptionHandler(UnknownIdentifierException.class)
	public String handleUnknownIdentifierException(final UnknownIdentifierException exception, final HttpServletRequest request)
	{
		request.setAttribute("message", exception.getMessage());
		return FORWARD_PREFIX + "/404";
	}

	private class CategorySearchEvaluator
	{
		private final String categoryCode;
		private final SearchQueryData searchQueryData = new SearchQueryData();
		private final int page;
		private final ShowMode showMode;
		private final String sortCode;
		private CategoryPageModel categoryPage;
		private boolean showCategoriesOnly;
		private ProductCategorySearchPageData<SearchStateData, ProductData, CategoryData> searchPageData;

		public CategorySearchEvaluator(final String categoryCode, final String searchQuery, final int page,
				final ShowMode showMode, final String sortCode, final CategoryPageModel categoryPage)
		{
			this.categoryCode = categoryCode;
			this.searchQueryData.setValue(searchQuery);
			this.page = page;
			this.showMode = showMode;
			this.sortCode = sortCode;
			this.categoryPage = categoryPage;
		}

		public boolean isShowCategoriesOnly()
		{
			return showCategoriesOnly;
		}

		public ProductCategorySearchPageData<SearchStateData, ProductData, CategoryData> getSearchPageData()
		{
			return searchPageData;
		}

		public void doSearch()
		{
			showCategoriesOnly = false;
			if (searchQueryData.getValue() == null)
			{
				// Direct category link without filtering
				searchPageData = productSearchFacade.categorySearch(categoryCode);
				if (categoryPage != null)
				{
					showCategoriesOnly = !categoryHasDefaultPage(categoryPage)
							&& CollectionUtils.isNotEmpty(searchPageData.getSubCategories());
				}
			}
			else
			{
				// We have some search filtering
				if (categoryPage == null || !categoryHasDefaultPage(categoryPage))
				{
					// Load the default category page
					categoryPage = getDefaultCategoryPage();
				}

				final SearchStateData searchState = new SearchStateData();
				searchState.setQuery(searchQueryData);

				final PageableData pageableData = createPageableData(page, getSearchPageSize(), sortCode, showMode);
				searchPageData = productSearchFacade.categorySearch(categoryCode, searchState, pageableData);
			}
			
			if(searchPageData != null && CollectionUtils.isNotEmpty(searchPageData.getFacets()))
			{
				Iterator<FacetData<SearchStateData>> iterator = searchPageData.getFacets().iterator();
				while(iterator.hasNext())
				{
					if(iterator.next().isCategory())
					{
						iterator.remove();
						break;
					}
				}
			}
		}
	}
}
