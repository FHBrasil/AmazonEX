/**
 *
 */
package com.flieger.eventtracking.strategies;

import de.hybris.platform.core.model.user.CustomerModel;


/**
 * @author franthescollymaneira
 *
 */
public interface FindCustomerUUIDForEventTracking
{
	String findCustomerUUID(CustomerModel customer);
}
