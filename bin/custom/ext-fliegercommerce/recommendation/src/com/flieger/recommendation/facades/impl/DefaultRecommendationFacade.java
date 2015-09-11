/**
 *
 */
package com.flieger.recommendation.facades.impl;

import de.hybris.platform.category.CategoryService;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.commercefacades.product.data.CategoryData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.converters.Converters;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import com.flieger.recommendation.data.RecommendationRequest;
import com.flieger.recommendation.data.RecommendationResultValueData;
import com.flieger.recommendation.facades.RecommendationFacade;
import com.flieger.recommendation.services.RecommendationService;
import com.flieger.recommendation.strategies.FindCustomerUUIDForRecommendations;
import com.flieger.recommendation.strategies.FindProductsBlacklistStrategy;


/**
 * @author franthescollymaneira
 *
 */
public class DefaultRecommendationFacade implements RecommendationFacade
{
	private RecommendationService recommendationService;

	private Converter<RecommendationResultValueData, ProductData> recommendationResultProductConverter;

	private FindProductsBlacklistStrategy findProductsBlacklistStrategy;

	private FindCustomerUUIDForRecommendations findCustomerUUIDForRecommendations;

	private ProductService productService;

	private CategoryService categoryService;

	private CartService cartService;

	private UserService userService;

	@Override
	public List<ProductData> getRecommendationsForProduct(final ProductData product)
	{
		Assert.notNull(product);

		final ProductModel productModel = getProductService().getProductForCode(product.getCode());

		final RecommendationRequest request = createRecommendationRequest();
		final List<ProductModel> blacklist = getFindProductsBlacklistStrategy().findBlacklistByProduct(productModel);

		request.getValues().put("product", productModel);
		request.getValues().put("blacklist", blacklist);

		final List<RecommendationResultValueData> recommendations = recommendationService.getRecommendationsForProduct(request);

		return Converters.convertAll(recommendations, getRecommendationResultProductConverter());
	}

	@Override
	public List<ProductData> getRecommendationsForCategory(final CategoryData category)
	{
		Assert.notNull(category);

		final CategoryModel categoryModel = categoryService.getCategoryForCode(category.getCode());

		final RecommendationRequest request = createRecommendationRequest();
		request.getValues().put("category", categoryModel);

		final List<RecommendationResultValueData> recommendations = recommendationService.getRecommendationsForCategory(request);

		return Converters.convertAll(recommendations, getRecommendationResultProductConverter());
	}

	@Override
	public List<ProductData> getRecommendationsForBrand(final CategoryData brand)
	{
		Assert.notNull(brand);

		final CategoryModel brandModel = categoryService.getCategoryForCode(brand.getCode());

		final RecommendationRequest request = createRecommendationRequest();
		request.getValues().put("brand", brandModel);

		final List<RecommendationResultValueData> recommendations = recommendationService.getRecommendationsForBrand(request);

		return Converters.convertAll(recommendations, getRecommendationResultProductConverter());
	}

	@Override
	public List<ProductData> getRecommendationsForHomePage()
	{
		final RecommendationRequest request = createRecommendationRequest();
		request.getValues().put("blacklist", getFindProductsBlacklistStrategy().findBlacklist());

		final List<RecommendationResultValueData> recommendations = recommendationService.getRecommendationsForHomePage(request);

		return Converters.convertAll(recommendations, getRecommendationResultProductConverter());
	}

	@Override
	public List<ProductData> getRecommendationsForBasket()
	{
		final CartModel cartModel = getCartService().getSessionCart();

		final RecommendationRequest request = createRecommendationRequest();
		request.getValues().put("basketEntries", cartModel.getEntries());

		final List<RecommendationResultValueData> recommendations = recommendationService.getRecommendationsForBasket(request);

		return Converters.convertAll(recommendations, getRecommendationResultProductConverter());
	}

	@Override
	public List<ProductData> getRecommendationsForSearchResultEmptyPage()
	{
		final RecommendationRequest request = createRecommendationRequest();
		final List<RecommendationResultValueData> recommendations = recommendationService
				.getRecommendationsForSearchResultEmptyPage(request);

		return Converters.convertAll(recommendations, getRecommendationResultProductConverter());
	}

	@Override
	public List<ProductData> getRecommendationsForErrorPage()
	{
		final RecommendationRequest request = createRecommendationRequest();
		final List<RecommendationResultValueData> recommendations = recommendationService.getRecommendationsForErrorPage(request);

		return Converters.convertAll(recommendations, getRecommendationResultProductConverter());
	}

	private RecommendationRequest createRecommendationRequest()
	{
		final CustomerModel customer = (CustomerModel) getUserService().getCurrentUser();

		final RecommendationRequest request = new RecommendationRequest();
		request.setValues(new HashMap<String, Object>());
		request.getValues().put("sessionId", getFindCustomerUUIDForRecommendations().findCustomerUUID(customer));

		return request;
	}

	public RecommendationService getRecommendationService()
	{
		return recommendationService;
	}

	@Required
	public void setRecommendationService(final RecommendationService recommendationService)
	{
		this.recommendationService = recommendationService;
	}

	public Converter<RecommendationResultValueData, ProductData> getRecommendationResultProductConverter()
	{
		return recommendationResultProductConverter;
	}

	@Required
	public void setRecommendationResultProductConverter(
			final Converter<RecommendationResultValueData, ProductData> recommendationResultProductConverter)
	{
		this.recommendationResultProductConverter = recommendationResultProductConverter;
	}

	public FindProductsBlacklistStrategy getFindProductsBlacklistStrategy()
	{
		return findProductsBlacklistStrategy;
	}

	@Required
	public void setFindProductsBlacklistStrategy(final FindProductsBlacklistStrategy findProductsBlacklistStrategy)
	{
		this.findProductsBlacklistStrategy = findProductsBlacklistStrategy;
	}

	public ProductService getProductService()
	{
		return productService;
	}

	@Required
	public void setProductService(final ProductService productService)
	{
		this.productService = productService;
	}

	public CategoryService getCategoryService()
	{
		return categoryService;
	}

	@Required
	public void setCategoryService(final CategoryService categoryService)
	{
		this.categoryService = categoryService;
	}

	public CartService getCartService()
	{
		return cartService;
	}

	@Required
	public void setCartService(final CartService cartService)
	{
		this.cartService = cartService;
	}

	public FindCustomerUUIDForRecommendations getFindCustomerUUIDForRecommendations()
	{
		return findCustomerUUIDForRecommendations;
	}

	@Required
	public void setFindCustomerUUIDForRecommendations(final FindCustomerUUIDForRecommendations findCustomerUUIDForRecommendations)
	{
		this.findCustomerUUIDForRecommendations = findCustomerUUIDForRecommendations;
	}

	public UserService getUserService()
	{
		return userService;
	}

	@Required
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}
}