/**
 *
 */
package com.flieger.eventtracking.facade;

import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commercefacades.product.data.CategoryData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.user.data.CustomerData;

import java.util.Map;


/**
 * @author franthescollymaneira
 *
 */
public interface EventTrackerFacade
{
	void trackProductView(final ProductData product);

	void trackCategoryView(final CategoryData category);

	void trackClickEvent(final Map<String, Object> clickData);

	void trackBasketEvent();

	void trackOrderEvent(final OrderData order);

	void trackUserToSessionEvent(final CustomerData customer);
}