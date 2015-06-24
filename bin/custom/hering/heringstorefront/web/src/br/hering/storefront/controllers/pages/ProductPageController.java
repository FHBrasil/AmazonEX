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

import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.impl.ProductBreadcrumbBuilder;
import de.hybris.platform.acceleratorstorefrontcommons.constants.WebConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.acceleratorstorefrontcommons.forms.ReviewForm;
import de.hybris.platform.acceleratorstorefrontcommons.forms.validation.ReviewValidator;
import de.hybris.platform.acceleratorstorefrontcommons.util.XSSFilterUtil;
import de.hybris.platform.acceleratorstorefrontcommons.variants.VariantSortStrategy;
import de.hybris.platform.catalog.enums.ProductReferenceTypeEnum;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.cms2.servicelayer.services.CMSPageService;
import de.hybris.platform.commercefacades.order.CartFacade;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.order.data.DeliveryModeData;
import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.BaseOptionData;
import de.hybris.platform.commercefacades.product.data.ImageData;
import de.hybris.platform.commercefacades.product.data.ImageDataType;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.product.data.ProductReferenceData;
import de.hybris.platform.commercefacades.product.data.ReviewData;
import de.hybris.platform.commerceservices.url.UrlResolver;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.store.services.BaseStoreService;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.noggit.JSONUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.hering.core.model.HeringProductModel;
import br.hering.core.model.HeringSizeVariantProductModel;
import br.hering.core.model.HeringStyleVariantProductModel;
import br.hering.core.search.solrfacetsearch.provider.impl.VariantsUtils;
import br.hering.core.util.HeringComparatorFactory;
import br.hering.facades.wishlist.impl.DefaultHeringWishlistFacade;
import br.hering.storefront.controllers.ControllerConstants;
import br.hering.storefront.util.HeringPageType;
import br.hering.storefront.util.MetaSanitizerUtil;

/**
 * Controller for product details page
 */
@Controller
@Scope("tenant")
@RequestMapping(value = "/**/p")
public class ProductPageController extends AbstractPageController
{
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(ProductPageController.class);
	
	private static final String PRODUCT = "product";

	/**
	 * We use this suffix pattern because of an issue with Spring 3.1 where a Uri value is incorrectly extracted if it
	 * contains on or more '.' characters. Please see https://jira.springsource.org/browse/SPR-6164 for a discussion on
	 * the issue and future resolution.
	 */
	private static final String PRODUCT_CODE_PATH_VARIABLE_PATTERN = "/{productCode:.*}";
	private static final String REVIEWS_PATH_VARIABLE_PATTERN = "{numberOfReviews:.*}";

	@Resource(name = "productModelUrlResolver")
	private UrlResolver<ProductModel> productModelUrlResolver;

	@Resource(name = "heringProductFacade")
	private ProductFacade productFacade;

	@Resource(name = "productService")
	private ProductService productService;

	@Resource(name = "productBreadcrumbBuilder")
	private ProductBreadcrumbBuilder productBreadcrumbBuilder;

	@Resource(name = "cmsPageService")
	private CMSPageService cmsPageService;

	@Resource(name = "variantSortStrategy")
	private VariantSortStrategy variantSortStrategy;

	@Resource(name = "reviewValidator")
	private ReviewValidator reviewValidator;
	
	@Resource(name = "heringWishlistFacade")
	private DefaultHeringWishlistFacade heringWishlistFacade;
	
	@Resource
	private VariantsUtils variantsUtils;
	
	@Resource
	private CartFacade cartFacade;
	
	@Resource
	private CartService cartService;
	
	@Resource
	protected BaseStoreService baseStoreService;
	
	@RequestMapping(value = PRODUCT_CODE_PATH_VARIABLE_PATTERN, method = RequestMethod.GET)
	public String productDetail(@PathVariable("productCode") final String productCode, final Model model,
			final HttpServletRequest request, final HttpServletResponse response) throws CMSItemNotFoundException,
			UnsupportedEncodingException
	{
		final ProductModel productModel = productService.getProductForCode(productCode);
		final String redirection = checkRequestUrl(request, response, productModel);
		final CartData cartData = getCartFacade().getSessionCart();
		model.addAttribute("cartData", cartData);
		
		if (StringUtils.isNotEmpty(redirection))
		{
			return redirection;
		}
		
		if (StringUtils.isNotBlank((String) request.getSession().getAttribute("productWish"))
				&& request.getSession().getAttribute("productWish").equals(productCode))
		{

			heringWishlistFacade.addWishlistEntry(productCode);
			request.getSession().removeAttribute("productWish");

		}
		
		updatePageTitle(productModel, model);
		populateProductDetailForDisplay(productModel, model, request);
		model.addAttribute(new ReviewForm());
		final List<ProductReferenceData> productReferences = productFacade.getProductReferencesForCode(productCode,
				Arrays.asList(ProductReferenceTypeEnum.SIMILAR, ProductReferenceTypeEnum.ACCESSORIES),
				Arrays.asList(ProductOption.BASIC,ProductOption.PRICE), null);
		model.addAttribute("productReferences", productReferences);
		model.addAttribute("pageType", HeringPageType.PRODUCT.name());
		
		final boolean inWishlist = heringWishlistFacade.hasWishlisEntryForProduct(productCode);
		model.addAttribute("added", inWishlist);		
		final String metaKeywords = MetaSanitizerUtil.sanitizeKeywords(productModel.getKeywords());
		final String metaDescription = MetaSanitizerUtil.sanitizeDescription(productModel.getDescription());
		setUpMetaData(model, metaKeywords, metaDescription);
		return getViewForPage(model);
	}
	
	private String checkRequestUrl(final HttpServletRequest request, final HttpServletResponse response, final ProductModel productModel) throws UnsupportedEncodingException
	{
		if(!(productModel instanceof HeringSizeVariantProductModel))
		{
			ProductModel alt = getFirstSizeAvailable(productModel);
			
			if(alt == null)
			{
				alt = getFirstSize(productModel);
			}
			
			if(alt instanceof HeringSizeVariantProductModel)
			{
				return "redirect:" + productModelUrlResolver.resolve(alt);
			}
		}
		
		final String redirection = checkRequestUrl(request, response, productModelUrlResolver.resolve(productModel));
		if (StringUtils.isNotEmpty(redirection))
		{
			return redirection;
		}
		
		return null;
	}

	/**
	 * @param productModel
	 * @return
	 */
	private ProductModel getFirstSize(ProductModel product)
	{
		if(product instanceof HeringProductModel && CollectionUtils.isNotEmpty(product.getVariants()))
		{
			product = product.getVariants().iterator().next();
		}
		
		if(product instanceof HeringStyleVariantProductModel && CollectionUtils.isNotEmpty(product.getVariants()))
		{
			product = product.getVariants().iterator().next();
		}
		
		return product;
	}

	/**
	 * @param alt
	 */
	private ProductModel getFirstSizeAvailable(ProductModel product)
	{
		if(product instanceof HeringProductModel)
		{
			product = variantsUtils.firstAvailableChild(product.getVariants());
		}
		
		if(product instanceof HeringStyleVariantProductModel)
		{
			product = variantsUtils.firstAvailableChild(product.getVariants());
		}
		
		return product;
	}

	@RequestMapping(value = PRODUCT_CODE_PATH_VARIABLE_PATTERN + "/zoomImages", method = RequestMethod.GET)
	public String showZoomImages(@PathVariable("productCode") final String productCode,
			@RequestParam(value = "galleryPosition", required = false) final String galleryPosition, final Model model)
	{
		final ProductModel productModel = productService.getProductForCode(productCode);
		final ProductData productData = productFacade.getProductForOptions(productModel,
				Collections.singleton(ProductOption.GALLERY));
		final List<Map<String, ImageData>> images = getGalleryImages(productData);
		populateProductData(productData,model);
		if (galleryPosition != null)
		{
			try
			{
				Map<String, ImageData> map = images.get(Integer.parseInt(galleryPosition));
				model.addAttribute("zoomImageUrl", map.get("superZoom").getUrl());
			}
			catch (final IndexOutOfBoundsException | NumberFormatException ioebe)
			{
				model.addAttribute("zoomImageUrl", "");
			}
        }
		return ControllerConstants.Views.Fragments.Product.ZoomImagesPopup;
	}

	@RequestMapping(value = PRODUCT_CODE_PATH_VARIABLE_PATTERN + "/quickView", method = RequestMethod.GET)
	public String showQuickView(@PathVariable("productCode") final String productCode, final Model model,
			final HttpServletRequest request)
	{
		final ProductModel productModel = productService.getProductForCode(productCode);
		final ProductData productData = productFacade.getProductForOptions(productModel, Arrays.asList(ProductOption.BASIC,
				ProductOption.PRICE, ProductOption.SUMMARY, ProductOption.DESCRIPTION, ProductOption.CATEGORIES,
				ProductOption.PROMOTIONS, ProductOption.STOCK, ProductOption.REVIEW, ProductOption.DELIVERY_MODE_AVAILABILITY));

		populateProductData(productData, model);
		getRequestContextData(request).setProduct(productModel);
		final CartData cartData = cartFacade.getSessionCart();
		model.addAttribute("cartData", cartData);
		return ControllerConstants.Views.Fragments.Product.QuickViewPopup;
	}
	
	@RequestMapping(value = PRODUCT_CODE_PATH_VARIABLE_PATTERN + "/calculateDelivery", method = {RequestMethod.POST })
	public String calculateDelivery(@PathVariable("productCode") final String productCode, @RequestParam("zipCode") final String zipCode, final RedirectAttributes redirectAttrs) 
	{
		final ProductModel productModel = productService.getProductForCode(productCode);
		
		String msg = "<font color=\"#FF0000\">Informa����o indispon��vel no momento</font>";
		
		if(productModel instanceof HeringSizeVariantProductModel)
		{
			HeringSizeVariantProductModel sizeVariant = (HeringSizeVariantProductModel) productModel;
			PriceRowModel priceRow = sizeVariant.getEurope1Prices().iterator().next();
			double weight = sizeVariant.getWeight();
			Double threshold = baseStoreService.getCurrentBaseStore().getFreeDeliveryThreshold();
			
			if(threshold != null && priceRow.getPrice().doubleValue() >= threshold.doubleValue())
			{
				msg = "<font color=\"#3E9C00\">Frete: Gr��tis</font>";
			}
			//XXX remoção de dependencia com extension carrier
//			else{
//				
//				DeliveryModeData deliveryMode = calculateDeliveryFacade.getCheaperDeliveryMode(zipCode, weight, priceRow.getPrice().doubleValue());
//				if(deliveryMode != null)
//				{
//					msg = "<font color=\"#3E9C00\">Frete: " + deliveryMode.getDeliveryCost().getFormattedValue()+"</font>";
//				}
//			}
		}
		
		
		GlobalMessages.addFlashMessage(redirectAttrs, GlobalMessages.CONF_MESSAGES_HOLDER, msg);
		return REDIRECT_PREFIX + productModelUrlResolver.resolve(productModel);
	}

//	@RequestMapping(value = PRODUCT_CODE_PATH_VARIABLE_PATTERN + "/review", method = { RequestMethod.GET, RequestMethod.POST })
//	public String postReview(@PathVariable final String productCode, final ReviewForm form, final BindingResult result,
//			final Model model, final RedirectAttributes redirectAttrs)
//			throws CMSItemNotFoundException
//	{
//		getReviewValidator().validate(form, result);
//
//		final ProductModel productModel = productService.getProductForCode(productCode);
//
//		if (result.hasErrors())
//		{
//			GlobalMessages.addFlashMessage(redirectAttrs, GlobalMessages.INFO_MESSAGES_HOLDER, "review.general.error");
//			GlobalMessages.addFlashMessage(redirectAttrs, GlobalMessages.ERROR_MESSAGES_HOLDER, "review.general.error");
//			model.addAttribute("showReviewForm", Boolean.TRUE);
//		}
//		else
//		{
//			final ReviewData review = new ReviewData();
//			review.setHeadline(XSSFilterUtil.filter(form.getHeadline()));
//			review.setComment(XSSFilterUtil.filter(form.getComment()));
//			review.setRating(form.getRating());
//			review.setAlias(XSSFilterUtil.filter(form.getAlias()));
//			productFacade.postReview(productCode, review);
//			GlobalMessages.addFlashMessage(redirectAttrs, GlobalMessages.ERROR_MESSAGES_HOLDER, "review.confirmation.thank.you.title");
//			GlobalMessages.addFlashMessage(redirectAttrs, GlobalMessages.INFO_MESSAGES_HOLDER, "review.confirmation.thank.you.title");
//		}
//
//		return REDIRECT_PREFIX + productModelUrlResolver.resolve(productModel);
//	}
	
	@RequestMapping(value = PRODUCT_CODE_PATH_VARIABLE_PATTERN + "/review", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String postProductReview(@PathVariable final String productCode, final String headline, final String comment, final Double rating, final String alias) 
	{
		boolean error = false;
      if (StringUtils.isEmpty(headline) || StringUtils.length(headline) > 255)
		{
      	error = true;
		}

		if (StringUtils.isEmpty(comment) || StringUtils.length(comment) > 4000)
		{
			error = true;
		}

		if (rating == null || rating.doubleValue() < 1 || rating.doubleValue() > 5)
		{
			error = true;
		}
		
		Map<String, String> response = new HashMap<String, String>();
		
		if (error) {
			response.put("success", "false");
			response.put("msg", "Por favor complete todos os campos mandat��rios da avalia����o");
		} else {
			
			final ReviewData review = new ReviewData();
			review.setHeadline(XSSFilterUtil.filter(headline));
			review.setComment(XSSFilterUtil.filter(comment));
			review.setRating(rating);
			review.setAlias(XSSFilterUtil.filter(alias));
			productFacade.postReview(productCode, review);
			
			response.put("success", "true");
			response.put("msg", "Obrigado, caso sua avalia����o seja aprovada n��s iremos public��-la.");
		}
			
		return JSONUtil.toJSON(response);
	}

	@RequestMapping(value = PRODUCT_CODE_PATH_VARIABLE_PATTERN + "/reviewhtml/" + REVIEWS_PATH_VARIABLE_PATTERN, method = RequestMethod.GET)
	public String reviewHtml(@PathVariable("productCode") final String productCode,
			@PathVariable("numberOfReviews") final String numberOfReviews, final Model model, final HttpServletRequest request)
	{
		final ProductModel productModel = productService.getProductForCode(productCode);
		final List<ReviewData> reviews;
		final ProductData productData = productFacade.getProductForOptions(productModel,
				Arrays.asList(ProductOption.BASIC, ProductOption.REVIEW));

		if ("all".equals(numberOfReviews))
		{
			reviews = productFacade.getReviews(productCode);
		}
		else
		{
			final int reviewCount = Math.min(Integer.parseInt(numberOfReviews), (productData.getNumberOfReviews() == null ? 0
					: productData.getNumberOfReviews().intValue()));
			reviews = productFacade.getReviews(productCode, Integer.valueOf(reviewCount));
		}

		getRequestContextData(request).setProduct(productModel);
		model.addAttribute("reviews", reviews);
		model.addAttribute("reviewsTotal", productData.getNumberOfReviews());
		model.addAttribute(new ReviewForm());

		return ControllerConstants.Views.Fragments.Product.ReviewsTab;
	}

	@RequestMapping(value = PRODUCT_CODE_PATH_VARIABLE_PATTERN + "/writeReview", method = RequestMethod.GET)
	public String writeReview(@PathVariable final String productCode, final Model model)
			throws CMSItemNotFoundException
	{
		final ProductModel productModel = productService.getProductForCode(productCode);
		model.addAttribute(new ReviewForm());
		setUpReviewPage(model, productModel);
		return ControllerConstants.Views.Pages.Product.WriteReview;
	}

	protected void setUpReviewPage(final Model model, final ProductModel productModel) throws CMSItemNotFoundException
	{
		final String metaKeywords = MetaSanitizerUtil.sanitizeKeywords(productModel.getKeywords());
		final String metaDescription = MetaSanitizerUtil.sanitizeDescription(productModel.getDescription());
		setUpMetaData(model, metaKeywords, metaDescription);
		storeCmsPageInModel(model, getPageForProduct(productModel));
		model.addAttribute("product", productFacade.getProductForOptions(productModel, Arrays.asList(ProductOption.BASIC)));
		updatePageTitle(productModel, model);
	}

	@RequestMapping(value = PRODUCT_CODE_PATH_VARIABLE_PATTERN + "/writeReview", method = RequestMethod.POST)
	public String writeReview(@PathVariable final String productCode, final ReviewForm form, final BindingResult result,
										final Model model, final HttpServletRequest request, final RedirectAttributes redirectAttrs)
			throws CMSItemNotFoundException
	{
		getReviewValidator().validate(form, result);

		final ProductModel productModel = productService.getProductForCode(productCode);

		if (result.hasErrors())
		{
			GlobalMessages.addErrorMessage(model, "review.general.error");
			populateProductDetailForDisplay(productModel, model, request);
			setUpReviewPage(model, productModel);
			return ControllerConstants.Views.Pages.Product.WriteReview;
		}

		final ReviewData review = new ReviewData();
		review.setHeadline(XSSFilterUtil.filter(form.getHeadline()));
		review.setComment(XSSFilterUtil.filter(form.getComment()));
		review.setRating(form.getRating());
		review.setAlias(XSSFilterUtil.filter(form.getAlias()));
		productFacade.postReview(productCode, review);
		GlobalMessages.addFlashMessage(redirectAttrs, GlobalMessages.CONF_MESSAGES_HOLDER, "review.confirmation.thank.you.title");

		return REDIRECT_PREFIX + productModelUrlResolver.resolve(productModel);
	}

	@ExceptionHandler(UnknownIdentifierException.class)
	public String handleUnknownIdentifierException(final UnknownIdentifierException exception, final HttpServletRequest request)
	{
		request.setAttribute("message", exception.getMessage());
		return FORWARD_PREFIX + "/404";
	}

	protected void updatePageTitle(final ProductModel productModel, final Model model)
	{
		storeContentPageTitleInModel(model, getPageTitleResolver().resolveProductPageTitle(productModel));
	}

	protected void populateProductDetailForDisplay(final ProductModel productModel, final Model model,
			final HttpServletRequest request) throws CMSItemNotFoundException
	{
		getRequestContextData(request).setProduct(productModel);

		final ProductData productData = productFacade.getProductForOptions(productModel, Arrays.asList(ProductOption.BASIC,
				ProductOption.PRICE, ProductOption.SUMMARY, ProductOption.DESCRIPTION, ProductOption.GALLERY,
				ProductOption.CATEGORIES, ProductOption.REVIEW, ProductOption.PROMOTIONS, ProductOption.CLASSIFICATION,
				ProductOption.VARIANT_AVAILABLE, ProductOption.STOCK, ProductOption.VOLUME_PRICES,
				ProductOption.DELIVERY_MODE_AVAILABILITY));

		sortVariantOptionData(productData);
		storeCmsPageInModel(model, getPageForProduct(productModel));
		populateProductData(productData, model);
		model.addAttribute(WebConstants.BREADCRUMBS_KEY, productBreadcrumbBuilder.getBreadcrumbs(productModel));
	}

	protected void populateProductData(final ProductData productData, final Model model)
	{
		model.addAttribute("galleryImages", getGalleryImages(productData));
		model.addAttribute("product", productData);
	}

	protected void sortVariantOptionData(final ProductData productData)
	{
		if (CollectionUtils.isNotEmpty(productData.getBaseOptions()))
		{
			for (final BaseOptionData baseOptionData : productData.getBaseOptions())
			{
				if (CollectionUtils.isNotEmpty(baseOptionData.getOptions()))
				{
					Collections.sort(baseOptionData.getOptions(), variantSortStrategy);
				}
			}
		}

		if (CollectionUtils.isNotEmpty(productData.getVariantOptions()))
		{
			Collections.sort(productData.getVariantOptions(), variantSortStrategy);
		}
	}

	protected List<Map<String, ImageData>> getGalleryImages(final ProductData productData)
	{
		final List<Map<String, ImageData>> galleryImages = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(productData.getImages()))
		{
			final List<ImageData> images = new ArrayList<>();
			for (final ImageData image : productData.getImages())
			{
				if (ImageDataType.GALLERY.equals(image.getImageType()))
				{
					images.add(image);
				}
			}
			if (CollectionUtils.isNotEmpty(images))
			{
				int currentIndex = images.get(0).getGalleryIndex().intValue();
				Map<String, ImageData> formats = new HashMap<String, ImageData>();
				for (final ImageData image : images)
				{
					if (currentIndex != image.getGalleryIndex().intValue())
					{
						galleryImages.add(formats);
						formats = new HashMap<>();
						currentIndex = image.getGalleryIndex().intValue();
					}
					formats.put(image.getFormat(), image);
				}
				if (!formats.isEmpty())
				{
					galleryImages.add(formats);
				}
			}
		}
				
		Collections.sort(galleryImages, HeringComparatorFactory.getComparatorReverseMapKeyProductImageDataIndex());
		
		try
		{
			String lastObjList = galleryImages.get(galleryImages.size() - 1).get(PRODUCT).getUrl();
			
		    if (lastObjList.matches("(.*)[a-zA-Z].jpg(.*)"))
		    {
		    	Map<String, ImageData> obj = galleryImages.get(galleryImages.size() - 1);
		    	galleryImages.remove(obj);
		    	galleryImages.add(0, obj);
		    }
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return galleryImages;
	}
	
	

	/**
	 * @return the cartFacade
	 */
	public CartFacade getCartFacade()
	{
		return cartFacade;
	}

	/**
	 * @param cartFacade the cartFacade to set
	 */
	public void setCartFacade(CartFacade cartFacade)
	{
		this.cartFacade = cartFacade;
	}
	
	

	/**
	 * @return the cartService
	 */
	public CartService getCartService()
	{
		return cartService;
	}

	/**
	 * @param cartService the cartService to set
	 */
	public void setCartService(CartService cartService)
	{
		this.cartService = cartService;
	}

	protected ReviewValidator getReviewValidator()
	{
		return reviewValidator;
	}

	protected AbstractPageModel getPageForProduct(final ProductModel product) throws CMSItemNotFoundException
	{
		return cmsPageService.getPageForProduct(product);
	}



}
