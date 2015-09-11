/**
 * 
 */
package com.flieger.bonussystem;

import de.hybris.platform.core.model.user.CustomerModel;

import com.flieger.model.user.BonusSystemConfigModel;


/**
 * @author herbert
 * 
 */
public interface BonusSystemCreationStrategy
{

	BonusSystemConfigModel getDefaultBonusSystemConfiguration();

	/**
	 * @param customer
	 */
	void createBonusSystemForCustomer(CustomerModel customer);

}