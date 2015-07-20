/**
 *
 */
package com.flieger.recommendation.facades;

import de.hybris.platform.commercefacades.product.data.CategoryData;
import de.hybris.platform.commercefacades.product.data.ProductData;

import java.util.List;


/**
 * @author franthescollymaneira
 *
 */
public interface RecommendationFacade
{
	List<ProductData> getRecommendationsForProduct(final ProductData product);

	List<ProductData> getRecommendationsForCategory(final CategoryData category);

	List<ProductData> getRecommendationsForBrand(final CategoryData brand);

	List<ProductData> getRecommendationsForHomePage();

	List<ProductData> getRecommendationsForBasket();

	List<ProductData> getRecommendationsForSearchResultEmptyPage();

	List<ProductData> getRecommendationsForErrorPage();
}