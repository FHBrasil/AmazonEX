/**
 *
 */
package com.prudsys.communicator;

import org.apache.commons.lang.ArrayUtils;

import com.prudsys.data.PrudsysBasketRecommendationRequest;
import com.prudsys.data.PrudsysBrandRecommendationRequest;
import com.prudsys.data.PrudsysCategoryRecommendationRequest;
import com.prudsys.data.PrudsysErrorPageRecommendationRequest;
import com.prudsys.data.PrudsysHomePageRecommendationRequest;
import com.prudsys.data.PrudsysProductRecommendationRequest;
import com.prudsys.data.PrudsysRecommendationRequest;
import com.prudsys.data.PrudsysSearchResultEmptyRecommendationRequest;


/**
 * @author franthescollymaneira
 *
 */
public class PrudsysRestUriBuilder
{
	private static String host = PrudsysConfiguration.prudsys_host.getString();
	private static String port = PrudsysConfiguration.prudsys_port.getString();
	private static String reid = PrudsysConfiguration.prudsys_reid.getString();

	private static final String CONSTANT_HOST = "<HOST>";
	private static final String CONSTANT_PORT = "<PORT>";
	private static final String CONSTANT_RE_ID = "<RE_ID>";
	private static final String CONSTANT_SESSION_ID = "<SESSION_ID>";
	private static final String CONSTANT_VALUE = "<VALUE>";
	private static final String CONSTANT_QUANTITY = "<QUANTITY>";
	private static final String CONSTANT_VALUES = "<ITEMS>";
	private static final String CONSTANT_BLACKLIST_VALUES = "<BLACKLIST>";
	private static final String CONSTANT_USER_ID = "<USER_ID>";

	private static final String CONSTANT_BOX = "<BOX>";
	private static final String CONSTANT_TEMPLATE = "<TEMPLATE>";
	private static final String CONSTANT_UTP = "<UTP>";

	public String buildUriForRequest(final PrudsysRecommendationRequest request)
	{
		String link = null;
		if (request instanceof PrudsysCategoryRecommendationRequest)
		{
			link = PrudsysConfiguration.track_event_catTops.getString();
			link = link.replaceAll(CONSTANT_HOST, host);
			link = link.replaceAll(CONSTANT_PORT, port);
			link = link.replaceAll(CONSTANT_RE_ID, reid);
			link = link.replaceAll(CONSTANT_SESSION_ID, request.getSessionId());
			link = link.replaceAll(CONSTANT_VALUE, ((PrudsysCategoryRecommendationRequest) request).getCategoryId());
		}

		if (request instanceof PrudsysProductRecommendationRequest)
		{
			link = PrudsysConfiguration.track_event_productPage.getString();
			link = link.replaceAll(CONSTANT_HOST, host);
			link = link.replaceAll(CONSTANT_PORT, port);
			link = link.replaceAll(CONSTANT_RE_ID, reid);
			link = link.replaceAll(CONSTANT_SESSION_ID, request.getSessionId());
			link = link.replaceAll(CONSTANT_VALUE, ((PrudsysProductRecommendationRequest) request).getProductId());
			link = link.replaceAll(CONSTANT_BLACKLIST_VALUES,
					ArrayUtils.toString(((PrudsysProductRecommendationRequest) request).getBlackList()));
		}

		if (request instanceof PrudsysBrandRecommendationRequest)
		{
			link = PrudsysConfiguration.track_event_brandPage.getString();
			link = link.replaceAll(CONSTANT_HOST, host);
			link = link.replaceAll(CONSTANT_PORT, port);
			link = link.replaceAll(CONSTANT_RE_ID, reid);
			link = link.replaceAll(CONSTANT_SESSION_ID, request.getSessionId());
			link = link.replaceAll(CONSTANT_VALUE, ((PrudsysBrandRecommendationRequest) request).getBrandId());
		}

		if (request instanceof PrudsysBasketRecommendationRequest)
		{
			link = PrudsysConfiguration.track_event_basketpage.getString();
			link = link.replaceAll(CONSTANT_HOST, host);
			link = link.replaceAll(CONSTANT_PORT, port);
			link = link.replaceAll(CONSTANT_RE_ID, reid);
			link = link.replaceAll(CONSTANT_SESSION_ID, request.getSessionId());
			link = link.replaceAll(CONSTANT_VALUE,
					ArrayUtils.toString(((PrudsysBasketRecommendationRequest) request).getProductList()));
		}

		if (request instanceof PrudsysHomePageRecommendationRequest)
		{
			link = PrudsysConfiguration.track_event_homePage.getString();
			link = link.replaceAll(CONSTANT_HOST, host);
			link = link.replaceAll(CONSTANT_PORT, port);
			link = link.replaceAll(CONSTANT_RE_ID, reid);
			link = link.replaceAll(CONSTANT_SESSION_ID, request.getSessionId());
		}

		if (request instanceof PrudsysErrorPageRecommendationRequest)
		{
			link = PrudsysConfiguration.track_event_errorPage.getString();
			link = link.replaceAll(CONSTANT_HOST, host);
			link = link.replaceAll(CONSTANT_PORT, port);
			link = link.replaceAll(CONSTANT_RE_ID, reid);
			link = link.replaceAll(CONSTANT_SESSION_ID, request.getSessionId());
		}

		if (request instanceof PrudsysSearchResultEmptyRecommendationRequest)
		{
			link = PrudsysConfiguration.track_event_noResultPage.getString();
			link = link.replaceAll(CONSTANT_HOST, host);
			link = link.replaceAll(CONSTANT_PORT, port);
			link = link.replaceAll(CONSTANT_RE_ID, reid);
			link = link.replaceAll(CONSTANT_SESSION_ID, request.getSessionId());
		}

		return link;
	}
}