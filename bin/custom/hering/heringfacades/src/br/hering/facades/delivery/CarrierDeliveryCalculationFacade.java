/**
 * 
 */
package br.hering.facades.delivery;

import de.hybris.platform.commercefacades.order.data.DeliveryModeData;


/**
 * 
 * @author franthescollymaneira
 *
 */
public interface CarrierDeliveryCalculationFacade
{
	<T extends DeliveryModeData> T getCheaperDeliveryMode(final String zipCode, final double weight, final double itemsTotalCost);
	
	boolean setCheaperDeliveryMode(final String zipCode, final double weight, final double itemsTotalCost);
}