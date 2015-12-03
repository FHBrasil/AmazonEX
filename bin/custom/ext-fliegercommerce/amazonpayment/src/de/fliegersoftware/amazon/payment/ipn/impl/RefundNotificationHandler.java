package de.fliegersoftware.amazon.payment.ipn.impl;

import static de.fliegersoftware.amazon.payment.util.PaymentTransactionEntryUtil.setPaymentTransactionEntryStatus;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.log4j.Logger;

import com.amazonservices.mws.offamazonpaymentsipn.model.ProviderCreditReversalSummary;
import com.amazonservices.mws.offamazonpaymentsipn.model.RefundDetails;
import com.amazonservices.mws.offamazonpaymentsipn.model.RefundNotification;

import de.fliegersoftware.amazon.core.model.AmazonPaymentPaymentInfoModel;
import de.fliegersoftware.amazon.core.model.AmazonRefundModel;
import de.fliegersoftware.amazon.payment.dto.AmazonTransactionStatus;
import de.fliegersoftware.amazon.payment.ipn.AmazonNotificationHandler;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;

public class RefundNotificationHandler extends BaseAmazonNotificationHandler<RefundNotification> {

	private static final Logger LOG = Logger.getLogger(RefundNotificationHandler.class);

	@Override
	public void log(RefundNotification notification) {
		RefundDetails details = notification.getRefundDetails();
		logInfo(LOG, "Amazon Refund Id", details.getAmazonRefundId());
		logTimestamp(LOG, "Creation Timestamp", details.getCreationTimestamp());
		logPrice(LOG, "Fee Refunded", details.getFeeRefunded());
		if(details.getProviderCreditReversalSummaryList() != null && details.getProviderCreditReversalSummaryList().isSetProviderCreditReversalSummary()) {
			for(ProviderCreditReversalSummary summary : details.getProviderCreditReversalSummaryList().getProviderCreditReversalSummary()) {
				logInfo(LOG, "Provider Credit Reversal Summary: Provider Credit Reversal Id", summary.getProviderCreditReversalId());
				logInfo(LOG, "Provider Credit Reversal Summary: Provider Seller Id", summary.getProviderSellerId());
			}
		} else {
			logNull(LOG, "Provider Credit Reversal Summary");
		}
		logPrice(LOG, "Refunded Amount", details.getRefundAmount());
		logInfo(LOG, "Refund Reference Id", details.getRefundReferenceId());
		logStatus(LOG, "Refund Status", details.getRefundStatus());
		logInfo(LOG, "Refund Type", details.getRefundType());
		logInfo(LOG, "Soft Descriptor", details.getSoftDescriptor());
	}

	@Override
	public void handle(RefundNotification notification) {
		RefundDetails details = notification.getRefundDetails();
		AmazonPaymentPaymentInfoModel paymentInfo = null;
		PaymentTransactionModel transaction = null;
		PaymentTransactionEntryModel entry = null;
		for(PaymentTransactionEntryModel e : getAmazonPaymentNotificationService().getPaymentTransactionEntriesForReferenceId(details.getRefundReferenceId())) {
			if(PaymentTransactionType.REFUND.equals(e.getType())) {
				entry = e;
				break;
			}
		}

		if(entry == null) {
			String code = details.getAmazonRefundId();
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
			entry.setAmount(BigDecimal.valueOf(Double.parseDouble(details.getRefundAmount().getAmount())));
			if (details.getRefundAmount() != null) {
				entry.setCurrency(getCommonI18NService().getCurrency(details.getRefundAmount().getCurrencyCode()));
			}
			entry.setType(PaymentTransactionType.REFUND);
			entry.setTime((details.getRefundStatus().getLastUpdateTimestamp() == null) ? new Date()
					: details.getRefundStatus().getLastUpdateTimestamp().toGregorianCalendar().getTime());
			entry.setRequestId(details.getRefundReferenceId());
			entry.setRequestToken(details.getAmazonRefundId());

			setPaymentTransactionEntryStatus(entry, AmazonTransactionStatus.valueOf(details.getRefundStatus().getState()), details.getRefundStatus().getReasonCode());

			getModelService().save(entry);
			getModelService().refresh(transaction);

			AmazonRefundModel refund = null;
			for(AmazonRefundModel r : paymentInfo.getRefund()) {
				if(r.getRefundReferenceId().equals(details.getRefundReferenceId())) {
					refund = r;
					break;
				}
			}
			if(refund == null) {
				refund = getModelService().create(AmazonRefundModel.class);
				refund.setAmazonPaymentPaymentInfo(paymentInfo);
			}
			refund.setAmazonRefundId(details.getAmazonRefundId());
			refund.setRefundStatus(details.getRefundStatus().getState());
			refund.setRefundAmount(Double.parseDouble(details.getRefundAmount().getAmount()));
			refund.setRefundReferenceId(details.getRefundReferenceId());
			refund.setRefundReasonCode(details.getRefundStatus().getReasonCode());

			getModelService().save(refund);
		}
	}
}