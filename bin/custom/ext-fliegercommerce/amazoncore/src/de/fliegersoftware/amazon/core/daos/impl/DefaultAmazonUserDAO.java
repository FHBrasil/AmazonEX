package de.fliegersoftware.amazon.core.daos.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.fliegersoftware.amazon.core.daos.AmazonUserDao;
import de.fliegersoftware.amazon.core.model.AmazonCustomerModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

@Component(value = "amazonUserDAO")
public class DefaultAmazonUserDAO implements AmazonUserDao 
{
	@Autowired
	private FlexibleSearchService flexibleSearchService;

	@Override
	public AmazonCustomerModel getAmazonCustomer(String customerId) 
	{		
		final Map<String, Object> params = new HashMap<String, Object>();
		params.put("customerId", customerId);
		
		final SearchResult<AmazonCustomerModel> result = flexibleSearchService.search(
				"SELECT {p:" + AmazonCustomerModel.PK + "} "
				 + "FROM {" + AmazonCustomerModel._TYPECODE + " AS p} "
				 + "WHERE {p:"+AmazonCustomerModel.AMAZONCUSTOMERID+"}=?customerId", params);

		if (result.getCount() > 0)
		{
			return result.getResult().get(0);
		}
		return null;
	}

	@Override
	public boolean isAmazonCustomerExisting(String customerId) {
		final Map<String, Object> params = new HashMap<String, Object>();
		params.put("customerId", customerId);
		
		final SearchResult<AmazonCustomerModel> result = flexibleSearchService.search(
				"SELECT {p:" + AmazonCustomerModel.PK + "} "
				 + "FROM {" + AmazonCustomerModel._TYPECODE + " AS p} "
				 + "WHERE {p:"+AmazonCustomerModel.AMAZONCUSTOMERID+"}=?customerId", params);

		if (result.getCount() > 0)
		{
			return true;
		}
		return false;
	}
}