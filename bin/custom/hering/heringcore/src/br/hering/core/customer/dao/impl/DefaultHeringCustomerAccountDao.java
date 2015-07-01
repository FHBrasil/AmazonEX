/**
 * 
 */
package br.hering.core.customer.dao.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import de.hybris.platform.commerceservices.customer.dao.impl.DefaultCustomerAccountDao;
import de.hybris.platform.commerceservices.search.flexiblesearch.data.SortQueryData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.PK;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.hering.core.customer.dao.HeringCustomerAccountDao;

import de.hybris.platform.util.Config;

/**
 * @author Antony P
 *
 */
public class DefaultHeringCustomerAccountDao extends DefaultCustomerAccountDao implements HeringCustomerAccountDao
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.hering.core.customer.dao.HeringCustomerAccountDao#getCustomerByCpf(java.lang.String)
	 */
	@Override
	public CustomerModel getCustomerByCpf(String cpfCnpj)
	{
		
		if (Config.getBoolean("fliegercommerce.feature.enable.cpf", false))
		{
			ServicesUtil.validateParameterNotNull(cpfCnpj, "CPF must not be null");
			Map queryParams = new HashMap();
			queryParams.put("cpfcnpj", cpfCnpj);

			SearchResult result = getFlexibleSearchService().search("SELECT {pk} FROM {Customer} WHERE {cpfCnpj} = ?cpfCnpj",
				queryParams);
			return ((result.getCount() > 0) ? (CustomerModel) result.getResult().get(0) : null);
		}
		
		return null;
		
	}

	@Override
	public PaymentInfoModel findPaymentInfoByCustomer(CustomerModel customerModel, String code)
	{
		ServicesUtil.validateParameterNotNull(customerModel, "Customer must not be null");
		Map queryParams = new HashMap();
		queryParams.put("customer", customerModel);
		queryParams.put("duplicate", Boolean.FALSE);
		queryParams.put("pk", PK.parse(code));
		
//		...
		SearchResult result = getFlexibleSearchService().search(
				"SELECT {pk} FROM {PaymentInfo} WHERE {user} = ?customer AND {pk} = ?pk AND {duplicate} = ?duplicate",
				queryParams);
		return ((result.getCount() > 0) ? (PaymentInfoModel) result.getResult().get(0) : null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.hering.core.customer.dao.HeringCustomerAccountDao#findOrdersByCustomerAndConditions(java.util.Date,
	 * java.util.Date, java.util.List, de.hybris.platform.core.enums.OrderStatus)
	 */
	@Override
	public List<OrderModel> findOrdersByCustomerAndDateAndStatus(	final UserModel userModel, 
   																					final Date initDate,
   																					final Date endDate, 
   																					final OrderStatus... statuses)
	{	
//		statuses = new OrderStatus[]{OrderStatus.CANCELLED};
		
		validateParameterNotNull(userModel, "customerModel must not be null!");
		validateParameterNotNull(initDate, "initDate must not be null!");
		validateParameterNotNull(endDate, "endDate must not be null!");
		
		final Map queryParams = new HashMap();
		queryParams.put("userModel", userModel);
		queryParams.put("initDate", initDate);
		queryParams.put("endDate", endDate);
		
		final StringBuilder queryString = new StringBuilder();
		queryString.append("SELECT {pk},{subtotal},{totalDiscounts} FROM {Order}")
		.append(" WHERE {").append(OrderModel.DATE).append("} >= ?initDate ")
		.append(" AND {").append(OrderModel.DATE).append("} <= ?endDate ")
		.append(" AND {").append(OrderModel.USER).append("} = ?userModel");
		
		if ((statuses != null) && (statuses.length > 0))
		{
			queryParams.put("statusList", Arrays.asList(statuses));
			queryString.append(" AND {status} IN (?statusList)");
		}
		
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString(), queryParams);
		query.setResultClassList(Collections.singletonList(OrderModel.class));
		final SearchResult<OrderModel> res = getFlexibleSearchService().search(query);
		final List<OrderModel> result = res.getResult();
		return result == null ? Collections.EMPTY_LIST : result;
	}

	@Override
	public List<OrderModel> findOrdersByCustomer(CustomerModel customerModel, OrderStatus[] status)
	{
		ServicesUtil.validateParameterNotNull(customerModel, "Customer must not be null");

		Map queryParams = new HashMap();
		queryParams.put("customer", customerModel);

		String query;

		if ((status != null) && (status.length > 0))
		{
			queryParams.put("statusList", Arrays.asList(status));
			query = "SELECT {pk}, {creationtime}, {code} FROM {Order} WHERE {user} = ?customer AND {versionID} IS NULL AND {status} IN (?statusList)";
		}
		else
		{
			query = "SELECT {pk}, {creationtime}, {code} FROM {Order} WHERE {user} = ?customer AND {versionID} IS NULL";
		}

		SearchResult result = getFlexibleSearchService().search(query, queryParams);
		return result.getResult();
	}

	@Override
	public SearchPageData<OrderModel> findOrdersByCustomer(CustomerModel customerModel, OrderStatus[] status,
			PageableData pageableData)
	{
		ServicesUtil.validateParameterNotNull(customerModel, "Customer must not be null");

		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("customer", customerModel);

		List sortQueries;

		if ((status != null) && (status.length > 0))
		{
			queryParams.put("statusList", Arrays.asList(status));

			sortQueries = Arrays.asList(new SortQueryData[] { 
					createSortQueryData("byDate", "SELECT {pk}, {creationtime}, {code} FROM {Order} WHERE {user} = ?customer AND {versionID} IS NULL AND {status} IN (?statusList) ORDER BY {creationtime} DESC, {pk}")
					, createSortQueryData("byOrderNumber", "SELECT {pk}, {creationtime}, {code} FROM {Order} WHERE {user} = ?customer AND {versionID} IS NULL AND {status} IN (?statusList) ORDER BY {code},{creationtime} DESC, {pk}") });
		}
		else
		{
			sortQueries = Arrays.asList(new SortQueryData[] {
					createSortQueryData("byDate", "SELECT {pk}, {creationtime}, {code} FROM {Order} WHERE {user} = ?customer AND {versionID} IS NULL ORDER BY {creationtime} DESC, {pk}")
					, createSortQueryData("byOrderNumber", "SELECT {pk}, {creationtime}, {code} FROM {Order} WHERE {user} = ?customer AND {versionID} IS NULL ORDER BY {code},{creationtime} DESC, {pk}") });
		}

		return getPagedFlexibleSearchService().search(sortQueries, "byDate", queryParams, pageableData);
	}

	@Override
	public OrderModel findOrderByCustomerAndCode(CustomerModel customerModel, String code)
	{
		ServicesUtil.validateParameterNotNull(customerModel, "Customer must not be null");
		ServicesUtil.validateParameterNotNull(code, "Code must not be null");
		Map queryParams = new HashMap();
		queryParams.put("customer", customerModel);
		queryParams.put("code", code);
		OrderModel result = (OrderModel)getFlexibleSearchService().searchUnique(
				new FlexibleSearchQuery("SELECT {pk}, {creationtime}, {code} FROM {Order} WHERE {code} = ?code AND {versionID} IS NULL AND {user} = ?customer", queryParams));
		return result;
	}
}
