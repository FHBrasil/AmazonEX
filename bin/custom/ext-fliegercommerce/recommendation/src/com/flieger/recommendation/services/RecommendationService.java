/**
 *
 */
package com.flieger.recommendation.services;

import java.util.List;

import com.flieger.recommendation.data.RecommendationRequest;
import com.flieger.recommendation.data.RecommendationResultValueData;


/**
 * @author franthescollymaneira
 *
 */
public interface RecommendationService
{
	List<RecommendationResultValueData> getRecommendationsForProduct(final RecommendationRequest request);

	List<RecommendationResultValueData> getRecommendationsForCategory(final RecommendationRequest request);

	List<RecommendationResultValueData> getRecommendationsForBrand(final RecommendationRequest request);

	List<RecommendationResultValueData> getRecommendationsForHomePage(final RecommendationRequest request);

	List<RecommendationResultValueData> getRecommendationsForBasket(final RecommendationRequest request);

	List<RecommendationResultValueData> getRecommendationsForSearchResultEmptyPage(final RecommendationRequest request);

	List<RecommendationResultValueData> getRecommendationsForErrorPage(final RecommendationRequest request);
}