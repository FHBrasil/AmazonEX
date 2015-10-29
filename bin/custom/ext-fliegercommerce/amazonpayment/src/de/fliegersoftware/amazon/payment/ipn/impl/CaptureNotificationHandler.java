package de.fliegersoftware.amazon.payment.ipn.impl;

import static de.fliegersoftware.amazon.payment.util.PaymentTransactionEntryUtil.setPaymentTransactionEntryStatus;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.log4j.Logger;

import com.amazonservices.mws.offamazonpaymentsipn.model.CaptureDetails;
import com.amazonservices.mws.offamazonpaymentsipn.model.CaptureNotification;
import com.amazonservices.mws.offamazonpaymentsipn.model.ProviderCreditSummary;

import de.fliegersoftware.amazon.core.model.AmazonPaymentPaymentInfoModel;
import de.fliegersoftware.amazon.payment.dto.AmazonTransactionStatus;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.enums.PaymentStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;

public class CaptureNotificationHandler extends BaseAmazonNotificationHandler<CaptureNotification> {

	private static final Logger LOG = Logger.getLogger(CaptureNotificationHandler.class);

	@Override
	public void log(CaptureNotification notification) {
		CaptureDetails details = notification.getCaptureDetails();
		logInfo(LOG, "Amazon Capture Id", details.getAmazonCaptureId());
		logPrice(LOG, "Capture Amount", details.getCaptureAmount());
		logPrice(LOG, "Capture Fee", details.getCaptureFee());
		logInfo(LOG, "Capture Reference Id",  details.getCaptureReferenceId());
		logStatus(LOG, "Capture Status", details.getCaptureStatus());
		logTimestamp(LOG, "Creation Timestamp", details.getCreationTimestamp());
		logIdList(LOG, details.getIdList());
		if(details.getProviderCreditSummaryList() != null && details.getProviderCreditSummaryList().isSetProviderCreditSummary()) {
			for(ProviderCreditSummary summary : details.getProviderCreditSummaryList().getProviderCreditSummary()) {
				logInfo(LOG, "Provider Credit Summary List: Provider Credit Id",  summary.getProviderCreditId());
				logInfo(LOG, "Provider Credit Summary List: Provider Seller Id",  summary.getProviderSellerId());
			}
		} else {
			logNull(LOG, "Provider Credit Summary List");
		}
		logPrice(LOG, "Refunded Amount", details.getRefundedAmount());
		logInfo(LOG, "Soft Descriptor", details.getSoftDescriptor());
	}

	@Override
	public void handle(CaptureNotification notification) {
		CaptureDetails details = notification.getCaptureDetails();
		AmazonPaymentPaymentInfoModel paymentInfo = null;
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
				paymentInfo = (AmazonPaymentPaymentInfoModel) transaction.getInfo();
				entry = getModelService().create(PaymentTransactionEntryModel.class);
				entry.setPaymentTransaction(transaction);
				entry.setCode(getNewEntryCode(transaction));
			}
		} else {
			transaction = entry.getPaymentTransaction();
			paymentInfo = (AmazonPaymentPaymentInfoModel) transaction.getInfo();
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
					order.setPaymentStatus(PaymentStatus.PAID);
					getModelService().save(order);
				}
			}
		}
	}
}