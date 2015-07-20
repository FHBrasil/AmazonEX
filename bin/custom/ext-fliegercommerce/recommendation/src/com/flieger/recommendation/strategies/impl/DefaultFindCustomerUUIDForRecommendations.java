/**
 *
 */
package com.flieger.recommendation.strategies.impl;

import de.hybris.platform.core.model.user.CustomerModel;

import com.flieger.recommendation.strategies.FindCustomerUUIDForRecommendations;


/**
 * @author franthescollymaneira
 *
 */
public class DefaultFindCustomerUUIDForRecommendations implements FindCustomerUUIDForRecommendations
{
	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.flieger.recommendation.strategies.FindCustomerUUIDForRecommendations#findCustomerUUIDForRecommendations(de
	 * .hybris.platform.core.model.user.CustomerModel)
	 */
	@Override
	public String findCustomerUUIDForRecommendations(final CustomerModel customer)
	{
		//TODO implementar o cookie que vai salvar o id do usuario
		return "teste_thesko";
	}
}