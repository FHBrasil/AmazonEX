/**
 *
 */
package com.prudsys.communicator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.client.RestTemplate;

import com.prudsys.data.PrudsysBasketEventTrackRequest;
import com.prudsys.data.PrudsysBasketRecommendationRequest;
import com.prudsys.data.PrudsysBrandRecommendationRequest;
import com.prudsys.data.PrudsysCategoryRecommendationRequest;
import com.prudsys.data.PrudsysCategoryViewEventTrackRequest;
import com.prudsys.data.PrudsysClickEventTrackRequest;
import com.prudsys.data.PrudsysErrorPageRecommendationRequest;
import com.prudsys.data.PrudsysEventTrackRequest;
import com.prudsys.data.PrudsysHomePageRecommendationRequest;
import com.prudsys.data.PrudsysOrderEventTrackRequest;
import com.prudsys.data.PrudsysProductRecommendationRequest;
import com.prudsys.data.PrudsysProductViewEventTrackRequest;
import com.prudsys.data.PrudsysRecommendationRequest;
import com.prudsys.data.PrudsysRecommendationResponse;
import com.prudsys.data.PrudsysSearchResultEmptyRecommendationRequest;
import com.prudsys.data.PrudsysUserToSessionEventTrackRequest;


/**
 * @author franthescollymaneira
 *
 */
public class PrudsysCommunicator
{
	private PrudsysRestUriBuilder prudsysRestUriBuilder;

	private final Logger LOG = Logger.getLogger(PrudsysCommunicator.class);

	public void trackProductView(final PrudsysProductViewEventTrackRequest request)
	{
		callPrudsysEventTrackingRest(request);
	}

	public void trackCategoryView(final PrudsysCategoryViewEventTrackRequest request)
	{
		callPrudsysEventTrackingRest(request);
	}

	public void trackClickEvent(final PrudsysClickEventTrackRequest request)
	{
		callPrudsysEventTrackingRest(request);
	}

	public void trackBasketEvent(final PrudsysBasketEventTrackRequest request)
	{
		callPrudsysEventTrackingRest(request);
	}

	public void trackOrderEvent(final PrudsysOrderEventTrackRequest request)
	{
		callPrudsysEventTrackingRest(request);
	}

	public void trackUserToSessionEvent(final PrudsysUserToSessionEventTrackRequest request)
	{
		callPrudsysEventTrackingRest(request);
	}

	public List<PrudsysRecommendationResponse> getRecommendationsForCategory(final PrudsysCategoryRecommendationRequest request)
	{
		final String result = callPrudsysRecommendationRest(request);

		return convertToResponse(result);
	}

	public List<PrudsysRecommendationResponse> getRecommendationsForProduct(final PrudsysProductRecommendationRequest request)
	{
		final String result = callPrudsysRecommendationRest(request);
		return convertToResponse(result);
	}

	public List<PrudsysRecommendationResponse> getRecommendationsForBrand(final PrudsysBrandRecommendationRequest request)
	{
		final String result = callPrudsysRecommendationRest(request);
		return convertToResponse(result);
	}

	public List<PrudsysRecommendationResponse> getRecommendationsForBasket(final PrudsysBasketRecommendationRequest request)
	{
		final String result = callPrudsysRecommendationRest(request);
		return convertToResponse(result);
	}

	public List<PrudsysRecommendationResponse> getRecommendationsForHomePage(final PrudsysHomePageRecommendationRequest request)
	{
		final String result = callPrudsysRecommendationRest(request);
		return convertToResponse(result);
	}

	public List<PrudsysRecommendationResponse> getRecommendationsForSearchResultEmptyPage(
			final PrudsysSearchResultEmptyRecommendationRequest request)
	{
		final String result = callPrudsysRecommendationRest(request);
		return convertToResponse(result);
	}

	public List<PrudsysRecommendationResponse> getRecommendationsForErrorPage(final PrudsysErrorPageRecommendationRequest request)
	{
		final String result = callPrudsysRecommendationRest(request);
		return convertToResponse(result);
	}

	private String callPrudsysEventTrackingRest(final PrudsysEventTrackRequest request)
	{
		final RestTemplate restTemplate = new RestTemplate();
		final String uri = getPrudsysRestUriBuilder().buildUriForRequest(request);
		final String result = restTemplate.getForObject(uri, String.class);

		LOG.debug("Tracking URI:" + uri + "\n" + result);

		return result;
	}

	private String callPrudsysRecommendationRest(final PrudsysRecommendationRequest request)
	{
		final RestTemplate restTemplate = new RestTemplate();
		final String uri = getPrudsysRestUriBuilder().buildUriForRequest(request);
		final String result = restTemplate.getForObject(uri, String.class);

		LOG.debug("Recommendation URI:" + uri + "\n" + result);

		return result;
	}

	/**
	 * @param result
	 * @return
	 */
	private List<PrudsysRecommendationResponse> convertToResponse(final String result)
	{
		final List<PrudsysRecommendationResponse> responseList = new ArrayList<PrudsysRecommendationResponse>();
		try
		{
			final JSONArray jsonArrayResult = new JSONArray(result);

			parseJsonObj(responseList, jsonArrayResult.getJSONObject(0).getJSONArray("box1"), "box1");

			if (jsonArrayResult.length() > 1)
			{
				parseJsonObj(responseList, jsonArrayResult.getJSONObject(1).getJSONArray("box2"), "box2");
			}
		}
		catch (final JSONException e)
		{
			LOG.error("error", e);
		}

		return responseList;
	}

	private void parseJsonObj(final List<PrudsysRecommendationResponse> responseList, final JSONArray obj, final String boxId)
	{
		PrudsysRecommendationResponse responseItem;
		JSONObject obj2 = null;
		for (int i = 0; i < obj.length(); i++)
		{
			try
			{
				responseItem = new PrudsysRecommendationResponse();

				obj2 = obj.getJSONObject(i);

				responseItem.setBoxId(boxId);
				responseItem.setBrand(obj2.getString("brand"));
				responseItem.setDescription(obj2.getString("description"));
				responseItem.setImageUrl(obj2.getString("imageURL"));
				responseItem.setManufacturer(obj2.getString("manufacturer"));
				responseItem.setName(obj2.getString("name"));
				responseItem.setNetPurchasePrice(parseDouble(obj2.getDouble("netPurchasePrice")));
				responseItem.setNetUnitPrice(parseDouble(obj2.getDouble("netUnitPrice")));
				responseItem.setOnlineFlag(obj2.getDouble("onlineFlag") == 1);
				responseItem.setProductNr(obj2.getString("product_nr"));
				responseItem.setQuantityUnit(obj2.getString("quantityUnit"));
				responseItem.setReason(obj2.getString("reason"));
				responseItem.setReward(parseDouble(obj2.getDouble("reward")));
				responseItem.setSku(obj2.getString("SKU"));
				responseItem.setStrikeOutPrice(parseDouble(obj2.getDouble("strikeOutPrice")));
				responseItem.setUid(obj2.getString("UID"));
				responseItem.setUrl(obj2.getString("URL"));

				responseList.add(responseItem);
			}
			catch (final JSONException e)
			{
				LOG.error("error", e);
			}
		}
	}

	private BigDecimal parseDouble(final double value)
	{
		if (Double.isNaN(value))
		{
			return BigDecimal.valueOf(0);
		}

		if (Double.isInfinite(value))
		{
			return BigDecimal.valueOf(0);
		}

		return BigDecimal.valueOf(value);
	}

	public PrudsysRestUriBuilder getPrudsysRestUriBuilder()
	{
		return prudsysRestUriBuilder;
	}

	@Required
	public void setPrudsysRestUriBuilder(final PrudsysRestUriBuilder prudsysRestUriBuilder)
	{
		this.prudsysRestUriBuilder = prudsysRestUriBuilder;
	}
}