/**
 * 
 */
package com.flieger.carrier.services.strategies;

import de.hybris.platform.core.model.order.AbstractOrderModel;

/**
 * @author franthescollymaneira
 *
 */
public interface FindPostalCodeStrategy
{
	String findPostalCode(final AbstractOrderModel order);
}