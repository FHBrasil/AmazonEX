/**
 * 
 */
package com.flieger.dao.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.internal.dao.AbstractItemDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;

import com.flieger.dao.BonusSystemDao;
import com.flieger.model.user.BonusSystemConfigModel;
import com.flieger.model.user.BonusSystemModel;


/**
 * @author franthescollymaneira
 * 
 */
public class DefaultBonusSystemDao extends AbstractItemDao implements BonusSystemDao
{
	@Override
	public CustomerModel getCustomerForBonusSystem(BonusSystemModel system) {
		validateParameterNotNull(system, "system must not be null!");
		
		final Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("systemModel", system);

		final StringBuilder queryString = new StringBuilder();
		queryString.append("SELECT {pk} FROM {Customer}")
		.append(" WHERE {").append(CustomerModel.BONUSSYSTEM).append("} = ?systemModel ");

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString(), queryParams);
		query.setResultClassList(Collections.singletonList(CustomerModel.class));

		return getFlexibleSearchService().searchUnique(query);
	}

	@Override
	public List<BonusSystemConfigModel> getConfigurations() {
		final StringBuilder queryString = new StringBuilder();
		queryString.append("SELECT {pk} FROM {BonusSystemConfig}");

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		query.setResultClassList(Collections.singletonList(BonusSystemConfigModel.class));

		return getFlexibleSearchService().<BonusSystemConfigModel>search(query).getResult();
	}
}