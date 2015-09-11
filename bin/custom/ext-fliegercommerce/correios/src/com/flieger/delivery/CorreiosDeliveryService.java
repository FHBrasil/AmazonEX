/**
 * 
 */
package com.flieger.delivery;

import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.servicelayer.exceptions.ModelSavingException;
import de.hybris.platform.util.PriceValue;


/**
 * @author alexandresantos
 * 
 */
public interface CorreiosDeliveryService
{
	PriceValue calculateDeliveryCosts(final AbstractOrderModel order, double peso) throws ModelSavingException;
}
