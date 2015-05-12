/**
 * 
 */
package br.hering.core.customer.impl;

import de.hybris.platform.commerceservices.customer.dao.CustomerAccountDao;
import de.hybris.platform.commerceservices.customer.impl.DefaultCustomerAccountService;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import java.util.List;

import br.hering.core.customer.HeringCustomerAccountService;
import br.hering.core.customer.dao.HeringCustomerAccountDao;

/**
 * @author herbert
 *
 */
public class DefaultHeringCustomerAccountService extends DefaultCustomerAccountService implements HeringCustomerAccountService
{
	@Override
	public OrderModel getOrderForCode(CustomerModel customerModel, String code)
	{
		ServicesUtil.validateParameterNotNull(customerModel, "Customer model cannot be null"); /* 549 */
		ServicesUtil.validateParameterNotNull(code, "Order code cannot be null");
		return getCustomerAccountDao().findOrderByCustomerAndCode(customerModel, code);
	}

	@Override
	public List<OrderModel> getOrderList(CustomerModel customerModel, OrderStatus[] status)
	{
		ServicesUtil.validateParameterNotNull(customerModel, "Customer model cannot be null");
		return getCustomerAccountDao().findOrdersByCustomer(customerModel, status);
	}

	@Override
	public SearchPageData<OrderModel> getOrderList(CustomerModel customerModel, OrderStatus[] status, PageableData pageableData)
	{
		ServicesUtil.validateParameterNotNull(customerModel, "Customer model cannot be null");
		ServicesUtil.validateParameterNotNull(pageableData, "PageableData must not be null");
		return getCustomerAccountDao().findOrdersByCustomer(customerModel, status, pageableData);
	}

	@Override
	protected HeringCustomerAccountDao getCustomerAccountDao()
	{
		return (HeringCustomerAccountDao) super.getCustomerAccountDao();
	}
}
