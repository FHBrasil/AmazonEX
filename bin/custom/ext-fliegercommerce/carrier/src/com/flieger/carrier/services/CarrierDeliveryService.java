/**
 * 
 */
package com.flieger.carrier.services;

import de.hybris.platform.commercefacades.order.data.DeliveryModeData;
import de.hybris.platform.commerceservices.delivery.DeliveryService;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.util.PriceValue;

import com.flieger.carrier.model.CarrierZoneDeliveryModeModel;

/**
 * @author franthescollymaneira
 * 
 */
public interface CarrierDeliveryService extends DeliveryService
{
	double calculateTotalWeight(final AbstractOrderModel abstractOrder);

	PriceValue getZipDeliveryCostAndDays(
			final CarrierZoneDeliveryModeModel deliveryMode, 
			final double totalCost, 
			CurrencyModel currency, 
			final String postalcode, 
			final double weight, 
			DeliveryModeData data);
}