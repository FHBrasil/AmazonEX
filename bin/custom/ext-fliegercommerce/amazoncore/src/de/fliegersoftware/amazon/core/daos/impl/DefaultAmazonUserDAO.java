package de.fliegersoftware.amazon.core.daos.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.fliegersoftware.amazon.core.daos.AmazonUserDao;
import de.hybris.platform.commerceservices.enums.CustomerType;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

@Component(value = "amazonUserDAO")
public class DefaultAmazonUserDAO implements AmazonUserDao 
{
	@Autowired
	private FlexibleSearchService flexibleSearchService;

	@Override
	public CustomerModel getAmazonCustomer(String customerId) 
	{		
		List<CustomerModel> result = new ArrayList<CustomerModel>();
        String query = " SELECT {p:" + CustomerModel.PK + "} "
                + " FROM {" + CustomerModel._TYPECODE + " AS p } "
                + " WHERE {p:"+ CustomerModel.AMAZONCUSTOMERID + "} = ?customerId "
                + " AND ({p:" + CustomerModel.TYPE + "} IS NULL OR {p:" + CustomerModel.TYPE + "} <> ?guest)";
        
        FlexibleSearchQuery flexSearch = new FlexibleSearchQuery(query);
        flexSearch.addQueryParameter("customerId", customerId);
        flexSearch.addQueryParameter("guest", CustomerType.GUEST);
        flexSearch.setResultClassList(Collections.singletonList(CustomerModel.class));
        result = flexibleSearchService.<CustomerModel> search(flexSearch).getResult();

		if (result.size() > 0)
		{
			return result.get(0);
		}
		return null;
	}

	@Override
	public boolean isAmazonCustomerExisting(String customerId) 
	{
		List<CustomerModel> result = new ArrayList<CustomerModel>();
        String query = " SELECT {p:" + CustomerModel.PK + "} "
                + " FROM {" + CustomerModel._TYPECODE + " AS p } "
                + " WHERE {p:"+ CustomerModel.AMAZONCUSTOMERID + "} = ?customerId "
                + " AND ({p:" + CustomerModel.TYPE + "} IS NULL OR {p:" + CustomerModel.TYPE + "} <> ?guest)";
        
        FlexibleSearchQuery flexSearch = new FlexibleSearchQuery(query);
        flexSearch.addQueryParameter("customerId", customerId);
        flexSearch.addQueryParameter("guest", CustomerType.GUEST);
        flexSearch.setResultClassList(Collections.singletonList(CustomerModel.class));
        result = flexibleSearchService.<CustomerModel> search(flexSearch).getResult();

		if (result.size() > 0)
		{
			return true;
		}
		return false;
	}
	
	@Override
	public boolean isAmazonCustomer(String email) 
	{
		List<CustomerModel> result = new ArrayList<CustomerModel>();
        String query = " SELECT {p:" + CustomerModel.PK + "} "
                + " FROM {" + CustomerModel._TYPECODE + " AS p } "
                + " WHERE {p:"+ CustomerModel.UID + "} = ?email "
                + " AND {p:"+ CustomerModel.AMAZONCUSTOMERID + "} IS NOT NULL "
                + " AND ({p:" + CustomerModel.TYPE + "} IS NULL OR {p:" + CustomerModel.TYPE + "} <> ?guest)";
        
        FlexibleSearchQuery flexSearch = new FlexibleSearchQuery(query);
        flexSearch.addQueryParameter("email", email);
        flexSearch.addQueryParameter("guest", CustomerType.GUEST);
        flexSearch.setResultClassList(Collections.singletonList(CustomerModel.class));
        result = flexibleSearchService.<CustomerModel> search(flexSearch).getResult();

		if (result.size() > 0)
		{
			return true;
		}
		return false;
	}
}