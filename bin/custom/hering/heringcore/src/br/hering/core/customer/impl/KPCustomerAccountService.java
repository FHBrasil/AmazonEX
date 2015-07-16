package br.hering.core.customer.impl;

import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.payment.dto.BillingInfo;

import java.util.Date;
import java.util.List;

import com.flieger.payment.jalo.HeringDebitPaymentInfo;
import com.flieger.payment.model.HeringDebitPaymentInfoModel;

public interface KPCustomerAccountService extends CustomerAccountService
{
	public CustomerModel getCustomerByCpfCnpj(String cpfCnpj);
	
	public CustomerModel getCustomerByEmail(final String email);
	
	HeringDebitPaymentInfoModel createPaymentSubscription(	final CustomerModel customerModel, 
																				final HeringDebitPaymentInfoModel model,
																				final BillingInfo billing, 
																				final String titleCode, 
																				final String paymentProvider, 
																				final boolean saveInAccount);
	
	PaymentInfoModel getPaymentInfoForCode(CustomerModel customerModel, String code);
	
	List<OrderModel> getOrderListByCustomerAndDateAndStatus(	final UserModel customerModel, 
   																			final Date initDate,
   																			final Date endDate, 
   																			final OrderStatus... statuses);
}