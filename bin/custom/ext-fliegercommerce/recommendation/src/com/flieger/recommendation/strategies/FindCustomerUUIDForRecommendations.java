/**
 *
 */
package com.flieger.recommendation.strategies;

import de.hybris.platform.core.model.user.CustomerModel;


/**
 * @author franthescollymaneira
 *
 */
public interface FindCustomerUUIDForRecommendations
{
	String findCustomerUUIDForRecommendations(CustomerModel customer);
}