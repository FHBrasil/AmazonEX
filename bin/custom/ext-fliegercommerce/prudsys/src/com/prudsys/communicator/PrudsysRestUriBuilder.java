/**
 *
 */
package com.prudsys.communicator;

import java.util.List;

import org.apache.commons.lang.StringUtils;

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
import com.prudsys.data.PrudsysSearchResultEmptyRecommendationRequest;
import com.prudsys.data.PrudsysUserToSessionEventTrackRequest;


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
					getStringRepresentation(((PrudsysProductRecommendationRequest) request).getBlackList()));
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
					getStringRepresentation(((PrudsysBasketRecommendationRequest) request).getProductList()));
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

		return "http://www.babyartikel.de/prudsys/PrudsysProxy/?link=" + link;
	}

	public String buildUriForRequest(final PrudsysEventTrackRequest request)
	{
		String link = null;
		if (request instanceof PrudsysProductViewEventTrackRequest)
		{
			link = PrudsysConfiguration.track_event_productview.getString();
			link = link.replaceAll(CONSTANT_HOST, host);
			link = link.replaceAll(CONSTANT_PORT, port);
			link = link.replaceAll(CONSTANT_RE_ID, reid);
			link = link.replaceAll(CONSTANT_SESSION_ID, request.getSessionId());
			link = link.replaceAll(CONSTANT_VALUE, ((PrudsysProductViewEventTrackRequest) request).getProductId());
		}

		if (request instanceof PrudsysCategoryViewEventTrackRequest)
		{
			link = PrudsysConfiguration.track_event_categoryview.getString();
			link = link.replaceAll(CONSTANT_HOST, host);
			link = link.replaceAll(CONSTANT_PORT, port);
			link = link.replaceAll(CONSTANT_RE_ID, reid);
			link = link.replaceAll(CONSTANT_SESSION_ID, request.getSessionId());
			link = link.replaceAll(CONSTANT_VALUE, ((PrudsysCategoryViewEventTrackRequest) request).getCategoryId());
		}

		if (request instanceof PrudsysClickEventTrackRequest)
		{
			link = PrudsysConfiguration.track_event_click.getString();
			link = link.replaceAll(CONSTANT_HOST, host);
			link = link.replaceAll(CONSTANT_PORT, port);
			link = link.replaceAll(CONSTANT_RE_ID, reid);
			link = link.replaceAll(CONSTANT_SESSION_ID, request.getSessionId());
			link = link.replaceAll(CONSTANT_VALUE, ((PrudsysClickEventTrackRequest) request).getItemId());
			link = link.replaceAll(CONSTANT_TEMPLATE, ((PrudsysClickEventTrackRequest) request).getTemplate());
			link = link.replaceAll(CONSTANT_BOX, ((PrudsysClickEventTrackRequest) request).getBox());
		}

		if (request instanceof PrudsysBasketEventTrackRequest)
		{
			link = PrudsysConfiguration.track_event_basket.getString();
			link = link.replaceAll(CONSTANT_HOST, host);
			link = link.replaceAll(CONSTANT_PORT, port);
			link = link.replaceAll(CONSTANT_RE_ID, reid);
			link = link.replaceAll(CONSTANT_SESSION_ID, request.getSessionId());
			link = link
					.replaceAll(CONSTANT_VALUES, getStringRepresentation(((PrudsysBasketEventTrackRequest) request).getItemids()));
			link = link.replaceAll(CONSTANT_QUANTITY,
					getStringRepresentation(((PrudsysBasketEventTrackRequest) request).getQuantities()));
		}

		if (request instanceof PrudsysOrderEventTrackRequest)
		{
			link = PrudsysConfiguration.track_event_order.getString();
			link = link.replaceAll(CONSTANT_HOST, host);
			link = link.replaceAll(CONSTANT_PORT, port);
			link = link.replaceAll(CONSTANT_RE_ID, reid);
			link = link.replaceAll(CONSTANT_SESSION_ID, request.getSessionId());
			link = link.replaceAll(CONSTANT_VALUES, getStringRepresentation(((PrudsysOrderEventTrackRequest) request).getItemids()));
			link = link.replaceAll(CONSTANT_QUANTITY,
					getStringRepresentation(((PrudsysOrderEventTrackRequest) request).getQuantities()));
			link = link.replaceAll(CONSTANT_UTP, getStringRepresentation(((PrudsysOrderEventTrackRequest) request).getUtp()));
		}

		if (request instanceof PrudsysUserToSessionEventTrackRequest)
		{
			link = PrudsysConfiguration.event_usertosession.getString();
			link = link.replaceAll(CONSTANT_HOST, host);
			link = link.replaceAll(CONSTANT_PORT, port);
			link = link.replaceAll(CONSTANT_RE_ID, reid);
			link = link.replaceAll(CONSTANT_SESSION_ID, request.getSessionId());
			link = link.replaceAll(CONSTANT_USER_ID, ((PrudsysUserToSessionEventTrackRequest) request).getUserId());
		}

		return "http://www.babyartikel.de/prudsys/PrudsysProxy/?link=" + link;
	}

	public String getStringRepresentation(final List<String> list)
	{
		return StringUtils.join(list, ",");
	}
}