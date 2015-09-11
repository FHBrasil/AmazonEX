package com.prudsys.strategies.impl;

import de.hybris.platform.core.model.user.CustomerModel;

import com.flieger.eventtracking.strategies.FindCustomerUUIDForEventTracking;
import com.flieger.recommendation.strategies.FindCustomerUUIDForRecommendations;


public class DefaultPrudsysFindCustomerUUID implements FindCustomerUUIDForRecommendations,
		FindCustomerUUIDForEventTracking
{
	@Override
	public String findCustomerUUID(final CustomerModel customer)
	{
		return "TESTE_FLIEGER_FRANTHESCOLLY";
	}
}