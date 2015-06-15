/**
 *
 */
package com.paypal.hybris.ordercancel;

import com.ebay.api.*;
import com.ebay.utils.PaypalStringUtils;
import de.hybris.platform.ordercancel.OrderCancelException;
import de.hybris.platform.ordercancel.OrderCancelRequest;
import de.hybris.platform.ordercancel.impl.executors.ImmediateCancelRequestExecutor;
import de.hybris.platform.ordercancel.model.OrderCancelRecordEntryModel;

import javax.annotation.Resource;

import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.jalo.PaymentTransaction;
import de.hybris.platform.payment.jalo.PaymentTransactionEntry;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.paypal.hybris.constants.PaypalConstants;
import br.hering.core.model.order.payment.PayPalPaymentInfoModel;
import com.paypal.hybris.service.PaypalPaymentService;

import java.util.List;


/**
 * @author Aliaksei_Sery (EPAM Systems)
 *
 */
public class PayPalCancelRequestExecutor extends ImmediateCancelRequestExecutor
{
	private final static Logger LOG = Logger.getLogger(PayPalCancelRequestExecutor.class);

	@Resource(name = "paypalPaymentService")
	private PaypalPaymentService paypalPaymentService;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.ordercancel.impl.executors.ImmediateCancelRequestExecutor#processCancelRequest(de.hybris.platform
	 * .ordercancel.OrderCancelRequest, de.hybris.platform.ordercancel.model.OrderCancelRecordEntryModel)
	 */
	@Override
	public void processCancelRequest(final OrderCancelRequest orderCancelRequest,
			final OrderCancelRecordEntryModel cancelRequestRecordEntry) throws OrderCancelException
	{
		LOG.debug("Process cancel request through PayPal");

		if (orderCancelRequest.getOrder().getPaymentInfo() instanceof PayPalPaymentInfoModel)
		{
			List<PaymentTransactionModel> paymentTransactionList = orderCancelRequest.getOrder().getPaymentTransactions();

			// cancel all created transaction in order
			for (PaymentTransactionModel paymentTransaction: paymentTransactionList)
			{
				List<PaymentTransactionEntryModel> paymentTransactionEntryList = paymentTransaction.getEntries();
				if (CollectionUtils.isNotEmpty(paymentTransactionEntryList))
				{
					for (PaymentTransactionEntryModel paymentTransactionEntry: paymentTransactionEntryList)
					{
						if (PaymentTransactionType.AUTHORIZATION.equals(paymentTransactionEntry.getType()))
						{
							//final PayPalPaymentInfoModel paymentInfo = (PayPalPaymentInfoModel) orderCancelRequest.getOrder().getPaymentInfo();
							final DoVoidRequestType request = new DoVoidRequestType();

							request.setAuthorizationID(paymentTransactionEntry.getRequestId());
							request.setNote(orderCancelRequest.getCancelReason().getCode() + ": " + orderCancelRequest.getNotes());
							request.setVersion(PaypalConstants.SOAP_API_VERSION);

							DoVoidResponseType res = paypalPaymentService.doVoid(request);

							if (res.getAck() != AckCodeType.SUCCESS)
							{
								String errorsMessage = StringUtils.join(PaypalStringUtils.getErrorMessagesList(res.getErrors()), "; ");
								LOG.error("Error during doVoid web service call: " + errorsMessage);

								throw new OrderCancelException(errorsMessage);
							}

						}
					}
				}
			}
		}

		super.processCancelRequest(orderCancelRequest, cancelRequestRecordEntry);
	}
}
