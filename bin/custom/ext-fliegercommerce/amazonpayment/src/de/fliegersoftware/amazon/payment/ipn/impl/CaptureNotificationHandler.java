package de.fliegersoftware.amazon.payment.ipn.impl;

import static de.fliegersoftware.amazon.payment.util.PaymentTransactionEntryUtil.setPaymentTransactionEntryStatus;

import java.math.BigDecimal;
import java.util.Date;

import com.amazonservices.mws.offamazonpaymentsipn.model.CaptureDetails;
import com.amazonservices.mws.offamazonpaymentsipn.model.CaptureNotification;

import de.fliegersoftware.amazon.core.model.AmazonPaymentInfoModel;
import de.fliegersoftware.amazon.payment.dto.AmazonTransactionStatus;
import de.fliegersoftware.amazon.payment.ipn.AmazonNotificationHandler;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;

public class CaptureNotificationHandler extends BaseAmazonNotificationHandler<CaptureNotification> {

	@Override
	public void handle(CaptureNotification notification) {
		CaptureDetails details = notification.getCaptureDetails();
		AmazonPaymentInfoModel paymentInfo = null;
		PaymentTransactionModel transaction = null;
		PaymentTransactionEntryModel entry = null;
		for(PaymentTransactionEntryModel e : getAmazonPaymentNotificationService().getPaymentTransactionEntriesForReferenceId(details.getCaptureReferenceId())) {
			if(PaymentTransactionType.CAPTURE.equals(e.getType())) {
				entry = e;
				break;
			}
		}

		if(entry == null) {
			String code = details.getAmazonCaptureId();
			code = code.substring(0, code.lastIndexOf('-'));
			transaction = getAmazonPaymentNotificationService().getPaymentTransactionForCode(code);
			if(transaction != null) {
				paymentInfo = (AmazonPaymentInfoModel) transaction.getInfo();
				entry = getModelService().create(PaymentTransactionEntryModel.class);
				entry.setPaymentTransaction(transaction);
				entry.setCode(getNewEntryCode(transaction));
			}
		} else {
			transaction = entry.getPaymentTransaction();
			paymentInfo = (AmazonPaymentInfoModel) transaction.getInfo();
		}

		if(entry != null) {
			entry.setAmount(BigDecimal.valueOf(Double.parseDouble(details.getCaptureAmount().getAmount())));
			if (details.getCaptureAmount() != null) {
				entry.setCurrency(getCommonI18NService().getCurrency(details.getCaptureAmount().getCurrencyCode()));
			}
			entry.setType(PaymentTransactionType.CAPTURE);
			entry.setTime((details.getCaptureStatus().getLastUpdateTimestamp() == null) ? new Date()
					: details.getCaptureStatus().getLastUpdateTimestamp().toGregorianCalendar().getTime());
			entry.setRequestId(details.getCaptureReferenceId());
			entry.setRequestToken(details.getAmazonCaptureId());

			setPaymentTransactionEntryStatus(entry, AmazonTransactionStatus.valueOf(details.getCaptureStatus().getState()), details.getCaptureStatus().getReasonCode());

			getModelService().save(entry);
			getModelService().refresh(transaction);

			paymentInfo.setAmazonCaptureId(details.getAmazonCaptureId());
			paymentInfo.setAmazonCaptureReasonCode(details.getCaptureStatus().getReasonCode());
			paymentInfo.setAmazonCaptureRefundedAmount(Double.parseDouble(details.getRefundedAmount().getAmount()));
			paymentInfo.setAmazonCaptureStatus(details.getCaptureStatus().getState());

			getModelService().save(paymentInfo);

			if(transaction.getOrder() != null
				&& transaction.getOrder() instanceof OrderModel) {
				OrderModel order = (OrderModel) transaction.getOrder();
				if(OrderStatus.PAYMENT_AMOUNT_RESERVED.equals(order.getStatus())
					&& AmazonTransactionStatus.Completed.name().equals(details.getCaptureStatus().getState())) {
					// update order status
					order.setStatus(OrderStatus.PAYMENT_CAPTURED);
					getModelService().save(order);
				}
			}
		}
	}
}