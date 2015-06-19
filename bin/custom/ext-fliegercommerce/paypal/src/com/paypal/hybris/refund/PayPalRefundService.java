/**
 *
 */
package com.paypal.hybris.refund;

import br.hering.core.model.order.payment.PayPalPaymentInfoModel;

import com.ebay.api.RefundTransactionRequestType;
import com.ebay.api.RefundTransactionResponseType;
import com.paypal.hybris.constants.PaypalConstants;
import com.paypal.hybris.converters.impl.PayPalRequestDataConverter;
import com.paypal.hybris.converters.impl.PayPalResponseConverter;
import com.paypal.hybris.data.RefundTransactionRequestData;
import com.paypal.hybris.data.RefundTransactionResultData;

import de.hybris.platform.basecommerce.enums.ReturnStatus;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.dto.TransactionStatusDetails;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.refund.impl.DefaultRefundService;
import de.hybris.platform.returns.model.RefundEntryModel;

import javax.annotation.Resource;

import com.paypal.hybris.service.PaypalPaymentService;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import java.math.BigDecimal;
import java.util.*;


/**
 * @author Aliaksei_Sery (EPAM Systems)
 *
 */
public class PayPalRefundService extends DefaultRefundService
{
	private static final Logger LOG = Logger.getLogger(PayPalRefundService.class);

	@Resource(name = "paypalPaymentService")
	private PaypalPaymentService paypalPaymentService;

	private PayPalRequestDataConverter requestDataConverter;
	private PayPalResponseConverter responseConverter;


	@Override public void apply(List<RefundEntryModel> refunds, OrderModel order)
	{
		super.apply(refunds, order);
		refundAllPayPalPayments(refunds, order);
	}

	private void refundAllPayPalPayments(List<RefundEntryModel> refunds, OrderModel order)
	{
		if (order.getPaymentInfo() instanceof PayPalPaymentInfoModel)
		{
			List<PaymentTransactionModel> paymentTransactionList = order.getPaymentTransactions();

			boolean isAllSuccess = true;
			// cancel all created capture transaction in order
			for (PaymentTransactionModel paymentTransaction: paymentTransactionList)
			{
				List<PaymentTransactionEntryModel> paymentTransactionEntryList = paymentTransaction.getEntries();
				if (CollectionUtils.isNotEmpty(paymentTransactionEntryList))
				{
					for (PaymentTransactionEntryModel paymentTransactionEntry: paymentTransactionEntryList)
					{
						if (PaymentTransactionType.CAPTURE.equals(paymentTransactionEntry.getType()) &&  PaypalConstants.STATUS_ACCEPTED.equals(paymentTransactionEntry.getTransactionStatus()))
						{
							isAllSuccess &= makeRefundServiceCall(order, paymentTransactionEntry);
						}
					}
				}
			}
			if (isAllSuccess && CollectionUtils.isNotEmpty(refunds))
			{
				for (RefundEntryModel refund: refunds)
				{
					refund.setStatus(ReturnStatus.RECEIVED);
					getModelService().save(refund);
				}
			}
		}
	}

	private boolean makeRefundServiceCall(OrderModel orderModel, PaymentTransactionEntryModel paymentTransactionEntry)
	{
		boolean isRefundSuccess = false;
		PayPalPaymentInfoModel paymentInfo = (PayPalPaymentInfoModel) orderModel.getPaymentInfo();

		final RefundTransactionRequestData requestData = new RefundTransactionRequestData();
		requestData.setTransactionId(paymentTransactionEntry.getRequestId());

		RefundTransactionRequestType request = (RefundTransactionRequestType) requestDataConverter.convert(requestData);
		RefundTransactionResponseType response = paypalPaymentService.refundTransaction(request);
		RefundTransactionResultData resultData = (RefundTransactionResultData)responseConverter.convert(response);

		if (PaypalConstants.STATUS_SUCCESS.equals(resultData.getAck()))
		{
			isRefundSuccess = true;
		}

		return  isRefundSuccess;
	}

	public PayPalRequestDataConverter getRequestDataConverter()
	{
		return requestDataConverter;
	}

	@Required
	public void setRequestDataConverter(PayPalRequestDataConverter requestDataConverter)
	{
		this.requestDataConverter = requestDataConverter;
	}

	public PayPalResponseConverter getResponseConverter()
	{
		return responseConverter;
	}

	@Required
	public void setResponseConverter(PayPalResponseConverter responseConverter)
	{
		this.responseConverter = responseConverter;
	}
}
