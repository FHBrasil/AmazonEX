/**
 *
 */
package com.prudsys.services.impl;

import de.hybris.platform.converters.Converters;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.flieger.recommendation.data.RecommendationRequest;
import com.flieger.recommendation.data.RecommendationResultValueData;
import com.flieger.recommendation.services.RecommendationService;
import com.prudsys.communicator.PrudsysCommunicator;
import com.prudsys.data.PrudsysBasketRecommendationRequest;
import com.prudsys.data.PrudsysBrandRecommendationRequest;
import com.prudsys.data.PrudsysCategoryRecommendationRequest;
import com.prudsys.data.PrudsysErrorPageRecommendationRequest;
import com.prudsys.data.PrudsysHomePageRecommendationRequest;
import com.prudsys.data.PrudsysProductRecommendationRequest;
import com.prudsys.data.PrudsysRecommendationRequest;
import com.prudsys.data.PrudsysRecommendationResponse;
import com.prudsys.data.PrudsysSearchResultEmptyRecommendationRequest;


/**
 * @author franthescollymaneira
 *
 */
public class PrudsysRecommendationService implements RecommendationService
{
	private PrudsysCommunicator prudsysCommunicator;

	private Converter<PrudsysRecommendationResponse, RecommendationResultValueData> prudsysRecommendationResultValueConverter;

	private Converter<RecommendationRequest, PrudsysRecommendationRequest> prudsysRecommendationRequestConverter;

	@Override
	public List<RecommendationResultValueData> getRecommendationsForProduct(final RecommendationRequest request)
	{
		final PrudsysProductRecommendationRequest prudsysRequest = new PrudsysProductRecommendationRequest();
		getPrudsysRecommendationRequestConverter().convert(request, prudsysRequest);

		final List<PrudsysRecommendationResponse> recommendations = getPrudsysCommunicator().getRecommendationsForProduct(
				prudsysRequest);

		return Converters.convertAll(recommendations, getPrudsysRecommendationResultValueConverter());
	}

	@Override
	public List<RecommendationResultValueData> getRecommendationsForCategory(final RecommendationRequest request)
	{
		final PrudsysCategoryRecommendationRequest prudsysRequest = new PrudsysCategoryRecommendationRequest();
		getPrudsysRecommendationRequestConverter().convert(request, prudsysRequest);

		final List<PrudsysRecommendationResponse> recommendations = getPrudsysCommunicator().getRecommendationsForCategory(
				prudsysRequest);

		return Converters.convertAll(recommendations, getPrudsysRecommendationResultValueConverter());
	}

	@Override
	public List<RecommendationResultValueData> getRecommendationsForBrand(final RecommendationRequest request)
	{
		final PrudsysBrandRecommendationRequest prudsysRequest = new PrudsysBrandRecommendationRequest();
		getPrudsysRecommendationRequestConverter().convert(request, prudsysRequest);

		final List<PrudsysRecommendationResponse> recommendations = getPrudsysCommunicator().getRecommendationsForBrand(
				prudsysRequest);

		return Converters.convertAll(recommendations, getPrudsysRecommendationResultValueConverter());
	}

	@Override
	public List<RecommendationResultValueData> getRecommendationsForHomePage(final RecommendationRequest request)
	{
		final PrudsysHomePageRecommendationRequest prudsysRequest = new PrudsysHomePageRecommendationRequest();
		getPrudsysRecommendationRequestConverter().convert(request, prudsysRequest);

		final List<PrudsysRecommendationResponse> recommendations = getPrudsysCommunicator().getRecommendationsForHomePage(
				prudsysRequest);

		return Converters.convertAll(recommendations, getPrudsysRecommendationResultValueConverter());
	}

	@Override
	public List<RecommendationResultValueData> getRecommendationsForBasket(final RecommendationRequest request)
	{
		final PrudsysBasketRecommendationRequest prudsysRequest = new PrudsysBasketRecommendationRequest();
		getPrudsysRecommendationRequestConverter().convert(request, prudsysRequest);

		final List<PrudsysRecommendationResponse> recommendations = getPrudsysCommunicator().getRecommendationsForBasket(
				prudsysRequest);

		return Converters.convertAll(recommendations, getPrudsysRecommendationResultValueConverter());
	}

	@Override
	public List<RecommendationResultValueData> getRecommendationsForSearchResultEmptyPage(final RecommendationRequest request)
	{
		final PrudsysSearchResultEmptyRecommendationRequest prudsysRequest = new PrudsysSearchResultEmptyRecommendationRequest();
		getPrudsysRecommendationRequestConverter().convert(request, prudsysRequest);

		final List<PrudsysRecommendationResponse> recommendations = getPrudsysCommunicator()
				.getRecommendationsForSearchResultEmptyPage(prudsysRequest);

		return Converters.convertAll(recommendations, getPrudsysRecommendationResultValueConverter());
	}

	@Override
	public List<RecommendationResultValueData> getRecommendationsForErrorPage(final RecommendationRequest request)
	{
		final PrudsysErrorPageRecommendationRequest prudsysRequest = new PrudsysErrorPageRecommendationRequest();
		getPrudsysRecommendationRequestConverter().convert(request, prudsysRequest);

		final List<PrudsysRecommendationResponse> recommendations = getPrudsysCommunicator().getRecommendationsForErrorPage(
				prudsysRequest);

		return Converters.convertAll(recommendations, getPrudsysRecommendationResultValueConverter());
	}

	public PrudsysCommunicator getPrudsysCommunicator()
	{
		return prudsysCommunicator;
	}

	@Required
	public void setPrudsysCommunicator(final PrudsysCommunicator prudsysCommunicator)
	{
		this.prudsysCommunicator = prudsysCommunicator;
	}

	public Converter<PrudsysRecommendationResponse, RecommendationResultValueData> getPrudsysRecommendationResultValueConverter()
	{
		return prudsysRecommendationResultValueConverter;
	}

	@Required
	public void setPrudsysRecommendationResultValueConverter(
			final Converter<PrudsysRecommendationResponse, RecommendationResultValueData> prudsysRecommendationResultValueConverter)
	{
		this.prudsysRecommendationResultValueConverter = prudsysRecommendationResultValueConverter;
	}

	public Converter<RecommendationRequest, PrudsysRecommendationRequest> getPrudsysRecommendationRequestConverter()
	{
		return prudsysRecommendationRequestConverter;
	}

	@Required
	public void setPrudsysRecommendationRequestConverter(
			final Converter<RecommendationRequest, PrudsysRecommendationRequest> prudsysRecommendationRequestConverter)
	{
		this.prudsysRecommendationRequestConverter = prudsysRecommendationRequestConverter;
	}
}