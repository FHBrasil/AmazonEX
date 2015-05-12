/**
 * 
 */
package com.flieger.clearsale.order.interceptors;

import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.enums.PaymentStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.payment.dto.TransactionStatusDetails;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.util.Config;

import java.util.Currency;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.adyen.services.payment.AdyenCaptureRequest;
import com.adyen.services.payment.AdyenCaptureResult;
import com.adyen.services.payment.impl.AdyenCardPaymentService;
import com.adyen.services.payment.impl.DefaultAdyenPaymentService;
import com.flieger.main.Credentials;

/**
 * @author flieger
 * 
 */
public class CompleteOrderValueValidator implements PrepareInterceptor
{
	private static final String DEFAULT_STORE_UID = "dzarm";

	protected final Logger LOG = Logger.getLogger(this.getClass());
	@Resource
	private ModelService modelService;
	@Resource
	private CustomerAccountService customerAccountService;
	@Resource
	private CommonI18NService commonI18NService;
	private DefaultAdyenPaymentService defaultAdyenPaymentService;
	private FlexibleSearchService flexibleSearchService;
	private String paymentProvider;

	/**
	 * @return the paymentProvider
	 */
	public String getPaymentProvider()
	{
		return paymentProvider;
	}

	/**
	 * @param paymentProvider
	 *           the paymentProvider to set
	 */
	public void setPaymentProvider(final String paymentProvider)
	{
		this.paymentProvider = paymentProvider;
	}

	/**
	 * @return the customerAccountService
	 */
	public CustomerAccountService getCustomerAccountService()
	{
		return customerAccountService;
	}

	/**
	 * @param customerAccountService
	 *           the customerAccountService to set
	 */
	public void setCustomerAccountService(final CustomerAccountService customerAccountService)
	{
		this.customerAccountService = customerAccountService;
	}

	/**
	 * @return the flexibleSearchService
	 */
	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}

	/**
	 * @param flexibleSearchService
	 *           the flexibleSearchService to set
	 */
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}

	/**
	 * @return the defaultAdyenPaymentService
	 */
	public DefaultAdyenPaymentService getDefaultAdyenPaymentService()
	{
		return defaultAdyenPaymentService;
	}

	/**
	 * @param defaultAdyenPaymentService
	 *           the defaultAdyenPaymentService to set
	 */
	public void setDefaultAdyenPaymentService(final DefaultAdyenPaymentService defaultAdyenPaymentService)
	{
		this.defaultAdyenPaymentService = defaultAdyenPaymentService;
	}

	/**
	 * @return the commonI18NService
	 */
	public CommonI18NService getCommonI18NService()
	{
		return commonI18NService;
	}

	/**
	 * @param commonI18NService
	 *           the commonI18NService to set
	 */
	public void setCommonI18NService(final CommonI18NService commonI18NService)
	{
		this.commonI18NService = commonI18NService;
	}

	/**
	 * @return the modelService
	 */
	public ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * @param modelService
	 *           the modelService to set
	 */
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.servicelayer.interceptor.ValidateInterceptor#onValidate(java.lang.Object,
	 * de.hybris.platform.servicelayer.interceptor.InterceptorContext)
	 */
	@Override
	public void onPrepare(final Object model, final InterceptorContext ctx) throws InterceptorException
	{
		if (!(model instanceof OrderModel))
		{
			return;
		}

		final OrderModel orderModel = (OrderModel) model;
		if (ctx.isModified(orderModel, OrderModel.STATUS))
		{
			LOG.info("Intercepting order status change. order:{code:" 
					+ orderModel.getCode() 
					+ ", status:" 
					+ (orderModel.getStatus() != null ? orderModel.getStatus().getCode() : "null") 
					+ "}");
			
			if(	OrderStatus.CAPTURE_PAYMENT.equals(orderModel.getStatus())
				&& PaymentStatus.PENDING_APPROVAL.equals(orderModel.getPaymentStatus()))
   		{
   			LOG.info(String.format("Trying to capture. order:{code:%s}",
   					orderModel.getCode()));
   			
   			final boolean retorno = capturePayment(orderModel);
   			
   			LOG.info(String.format("Order: [%s] was captured? [%s]",
   					orderModel.getCode(),
   					String.valueOf(retorno)));
   			
   			if(retorno)
   			{
   				orderModel.setStatus(OrderStatus.PAYMENT_CAPTURED);
   			} else {
   				orderModel.setStatus(OrderStatus.PAYMENT_NOT_CAPTURED);
   			}
   			getModelService().save(orderModel);
   		} 
		}
	}

	private boolean capturePayment(final OrderModel order)
	{
		final String newEntryCode = getNewEntryCode();

		PaymentTransactionEntryModel auth = null;
		PaymentTransactionModel transaction = order.getPaymentTransactions().get(0);
		for (final PaymentTransactionEntryModel model : transaction.getEntries())
		{
			if (model.getType().equals(PaymentTransactionType.AUTHORIZATION))
			{
				auth = model;
				break;
			}
		}
		
		if(auth == null){
			LOG.info("Transação de Authorização não existe para a Order: " + order.getCode());
			return false;
		}

		final AdyenCardPaymentService cardPaymentService = 
				(AdyenCardPaymentService)getDefaultAdyenPaymentService().getCardPaymentService();

		String baseStoreUid = DEFAULT_STORE_UID;
		if(order.getStore() != null 
				&& StringUtils.isNotBlank(order.getStore().getUid()))
		{
			baseStoreUid = order.getStore().getUid();
		}	
		
		final AdyenCaptureResult result = cardPaymentService.capture(
				new AdyenCaptureRequest(getPaymentProvider(), 
						newEntryCode,
						Config.getParameter(Credentials.MERCHANT_ACCOUNT + "." + baseStoreUid), 
						Currency.getInstance(auth.getCurrency().getIsocode()), 
						auth.getAmount(), 
						auth.getAdyenReference(),//adyenReference de autorizacao 
						auth.getAdyenAuthorizationCode()));
		
		final PaymentTransactionEntryModel entry = 
				(PaymentTransactionEntryModel) this.modelService.create(PaymentTransactionEntryModel.class);
		entry.setAmount(result.getTotalAmount());

		if (TransactionStatusDetails.SUCCESFULL.equals(
				result.getTransactionStatusDetails()))
		{
			if (result.getCurrency() != null)
			{
				entry.setCurrency(
						this.commonI18NService.getCurrency(
								result.getCurrency().getCurrencyCode()));
			}
			entry.setType(PaymentTransactionType.CAPTURE);
			entry.setRequestId(result.getRequestId());
			entry.setRequestToken(result.getRequestToken());
			entry.setTime(result.getRequestTime() == null ? new Date() : result.getRequestTime());
			entry.setTransactionStatus(result.getTransactionStatus().toString());
			entry.setTransactionStatusDetails(result.getTransactionStatusDetails().toString());
			entry.setCode(newEntryCode);
			entry.setAdyenReference(result.getAdyenReference());
			entry.setAdyenMerchantReference(auth.getAdyenMerchantReference());
			entry.setPaymentTransaction(transaction);
			this.modelService.save(entry);
			this.modelService.refresh(transaction);
		} else {
			return false;
		}
		return true;
	}

	protected String getNewEntryCode()
	{
		return System.currentTimeMillis() + "";
	}
}
