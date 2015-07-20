/**
 * 
 */
package com.flieger.carrier.facades;

import java.util.List;

import com.flieger.carrier.model.CarrierZoneDeliveryModeModel;

import de.hybris.platform.commercefacades.order.data.DeliveryModeData;
import de.hybris.platform.core.model.order.CartModel;


/**
 * 
 * @author franthescollymaneira
 *
 */
public interface CarrierDeliveryCalculationFacade
{
	<T extends DeliveryModeData> T getCheaperDeliveryMode(final String zipCode, final double weight, final double itemsTotalCost);
	
	boolean setCheaperDeliveryMode(final String zipCode, final double weight, final double itemsTotalCost);

	List<DeliveryModeData> getSupportedDeliveryModes(CartModel cartModel);

	DeliveryModeData convert(CarrierZoneDeliveryModeModel deliveryModeModel,
			CartModel cartModel);
}