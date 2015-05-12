/**
 * 
 */
package br.hering.core.customer.dao;

import de.hybris.platform.commerceservices.customer.dao.CustomerAccountDao;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.store.BaseStoreModel;

import java.util.Date;
import java.util.List;

/**
 * @author Antony P
 *
 */
public interface HeringCustomerAccountDao extends CustomerAccountDao
{
	public CustomerModel getCustomerByCpf(String cpf);
	
	PaymentInfoModel findPaymentInfoByCustomer(CustomerModel customerModel, String code);
	
	List<OrderModel> findOrdersByCustomerAndDateAndStatus(final UserModel customerModel,
   																		final Date initDate,
   																		final Date endDate,
   																		final OrderStatus... statuses);

	/**
	 * @param customerModel
	 * @param status
	 * @return
	 */
	public List<OrderModel> findOrdersByCustomer(CustomerModel customerModel, OrderStatus[] status);

	/**
	 * @param customerModel
	 * @param status
	 * @param pageableData
	 * @return
	 */
	public SearchPageData<OrderModel> findOrdersByCustomer(CustomerModel customerModel, OrderStatus[] status,
			PageableData pageableData);

	/**
	 * @param customerModel
	 * @param code
	 * @return
	 */
	public OrderModel findOrderByCustomerAndCode(CustomerModel customerModel, String code);
}
