/**
 * 
 */
package br.hering.core.customer;

import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;

import java.util.Date;
import java.util.List;


/**
 * @author herbert
 *
 */
public interface HeringCustomerAccountService extends CustomerAccountService
{

	public OrderModel getOrderForCode(CustomerModel customerModel, String orderCode);

	public List<OrderModel> getOrderList(CustomerModel customerModel, OrderStatus[] status);

	public SearchPageData<OrderModel> getOrderList(CustomerModel customerModel, OrderStatus[] status, PageableData pageableData);
	
	public void deleteAccount(CustomerModel customerModel);
	
	public CustomerModel changePhoneNumber(CustomerModel customerModel, String phone);
	
	public CustomerModel subscribeTipsNewsletter(CustomerModel customerModel, Boolean tipsNewsletter, Date dateOfBirth);
	
}
