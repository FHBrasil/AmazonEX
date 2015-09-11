/**
 *
 */
package com.prudsys.services.impl;

import de.hybris.platform.servicelayer.dto.converter.Converter;

import org.springframework.beans.factory.annotation.Required;

import com.flieger.eventtracking.data.EventTrackRequest;
import com.flieger.eventtracking.services.EventTrackerService;
import com.prudsys.communicator.PrudsysCommunicator;
import com.prudsys.data.PrudsysBasketEventTrackRequest;
import com.prudsys.data.PrudsysCategoryViewEventTrackRequest;
import com.prudsys.data.PrudsysClickEventTrackRequest;
import com.prudsys.data.PrudsysEventTrackRequest;
import com.prudsys.data.PrudsysOrderEventTrackRequest;
import com.prudsys.data.PrudsysProductViewEventTrackRequest;
import com.prudsys.data.PrudsysUserToSessionEventTrackRequest;


/**
 * @author franthescollymaneira
 *
 */
public class PrudsysEventTrackerService implements EventTrackerService
{
	private Converter<EventTrackRequest, PrudsysEventTrackRequest> prudsysEventTrackRequestConverter;

	private PrudsysCommunicator prudsysCommunicator;

	@Override
	public void trackProductView(final EventTrackRequest request)
	{
		final PrudsysProductViewEventTrackRequest prudsysRequest = new PrudsysProductViewEventTrackRequest();
		getPrudsysEventTrackRequestConverter().convert(request, prudsysRequest);
		getPrudsysCommunicator().trackProductView(prudsysRequest);
	}

	@Override
	public void trackCategoryView(final EventTrackRequest request)
	{
		final PrudsysCategoryViewEventTrackRequest prudsysRequest = new PrudsysCategoryViewEventTrackRequest();
		getPrudsysEventTrackRequestConverter().convert(request, prudsysRequest);
		getPrudsysCommunicator().trackCategoryView(prudsysRequest);
	}

	@Override
	public void trackClickEvent(final EventTrackRequest request)
	{
		final PrudsysClickEventTrackRequest prudsysRequest = new PrudsysClickEventTrackRequest();
		getPrudsysEventTrackRequestConverter().convert(request, prudsysRequest);
		getPrudsysCommunicator().trackClickEvent(prudsysRequest);
	}

	@Override
	public void trackBasketEvent(final EventTrackRequest request)
	{
		final PrudsysBasketEventTrackRequest prudsysRequest = new PrudsysBasketEventTrackRequest();
		getPrudsysEventTrackRequestConverter().convert(request, prudsysRequest);
		getPrudsysCommunicator().trackBasketEvent(prudsysRequest);
	}

	@Override
	public void trackOrderEvent(final EventTrackRequest request)
	{
		final PrudsysOrderEventTrackRequest prudsysRequest = new PrudsysOrderEventTrackRequest();
		getPrudsysEventTrackRequestConverter().convert(request, prudsysRequest);
		getPrudsysCommunicator().trackOrderEvent(prudsysRequest);
	}

	@Override
	public void trackUserToSessionEvent(final EventTrackRequest request)
	{
		final PrudsysUserToSessionEventTrackRequest prudsysRequest = new PrudsysUserToSessionEventTrackRequest();
		getPrudsysEventTrackRequestConverter().convert(request, prudsysRequest);
		getPrudsysCommunicator().trackUserToSessionEvent(prudsysRequest);
	}

	public Converter<EventTrackRequest, PrudsysEventTrackRequest> getPrudsysEventTrackRequestConverter()
	{
		return prudsysEventTrackRequestConverter;
	}

	@Required
	public void setPrudsysEventTrackRequestConverter(
			final Converter<EventTrackRequest, PrudsysEventTrackRequest> prudsysEventTrackRequestConverter)
	{
		this.prudsysEventTrackRequestConverter = prudsysEventTrackRequestConverter;
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
}