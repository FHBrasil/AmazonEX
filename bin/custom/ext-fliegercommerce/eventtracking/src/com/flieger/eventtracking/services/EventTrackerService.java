/**
 *
 */
package com.flieger.eventtracking.services;

import com.flieger.eventtracking.data.EventTrackRequest;


/**
 * @author franthescollymaneira
 *
 */
public interface EventTrackerService
{
	void trackProductView(final EventTrackRequest request);

	void trackCategoryView(final EventTrackRequest request);

	void trackClickEvent(final EventTrackRequest request);

	void trackBasketEvent(final EventTrackRequest request);

	void trackOrderEvent(final EventTrackRequest request);

	void trackUserToSessionEvent(final EventTrackRequest request);
}