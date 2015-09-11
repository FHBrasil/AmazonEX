package de.fliegersoftware.amazon.payment.ipn.impl;

import java.math.BigDecimal;
import java.util.Date;

import com.amazonservices.mws.offamazonpaymentsipn.model.AuthorizationDetails;
import com.amazonservices.mws.offamazonpaymentsipn.model.AuthorizationNotification;

import de.fliegersoftware.amazon.core.model.AmazonPaymentInfoModel;
import de.fliegersoftware.amazon.payment.dto.AmazonTransactionStatus;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;

import static de.fliegersoftware.amazon.payment.util.PaymentTransactionEntryUtil.setPaymentTransactionEntryStatus;

public class AuthorizationNotificationHandler extends BaseAmazonNotificationHandler<AuthorizationNotification> {

	@Override
	public void handle(AuthorizationNotification notification) {
		AuthorizationDetails details = notification.getAuthorizationDetails();
		AmazonPaymentInfoModel paymentInfo = null;
		PaymentTransactionModel transaction = null;
		PaymentTransactionEntryModel aEntry = null;
		PaymentTransactionEntryModel cEntry = null;
		for(PaymentTransactionEntryModel entry : getAmazonPaymentNotificationService().getPaymentTransactionEntriesForReferenceId(details.getAuthorizationReferenceId())) {
			if(PaymentTransactionType.AUTHORIZATION.equals(entry.getType()))
				aEntry = entry;
			if(PaymentTransactionType.CAPTURE.equals(entry.getType()))
				cEntry = entry;
		}

		if(aEntry == null) {
			String code = details.getAmazonAuthorizationId();
			code = code.substring(0, code.lastIndexOf('-'));
			transaction = getAmazonPaymentNotificationService().getPaymentTransactionForCode(code);
			if(transaction != null) {
				paymentInfo = (AmazonPaymentInfoModel) transaction.getInfo();
				aEntry = getModelService().create(PaymentTransactionEntryModel.class);
				aEntry.setPaymentTransaction(transaction);
				aEntry.setCode(getNewEntryCode(transaction));
			}
		} else {
			transaction = aEntry.getPaymentTransaction();
			paymentInfo = (AmazonPaymentInfoModel) transaction.getInfo();
		}

		if(aEntry != null) {
			if (details.getAuthorizationAmount() != null) {
				aEntry.setAmount(BigDecimal.valueOf(Double.parseDouble(details.getAuthorizationAmount().getAmount())));
				aEntry.setCurrency(getCommonI18NService().getCurrency(details.getAuthorizationAmount().getCurrencyCode()));
			}
			aEntry.setType(PaymentTransactionType.AUTHORIZATION);
			aEntry.setTime((details.getAuthorizationStatus().getLastUpdateTimestamp() == null) ? new Date()
					: details.getAuthorizationStatus().getLastUpdateTimestamp().toGregorianCalendar().getTime());
			aEntry.setRequestId(details.getAuthorizationReferenceId());
			aEntry.setRequestToken(details.getAmazonAuthorizationId());

			setPaymentTransactionEntryStatus(aEntry, AmazonTransactionStatus.valueOf(details.getAuthorizationStatus().getState()), details.getAuthorizationStatus().getReasonCode());

			getModelService().save(aEntry);
			getModelService().refresh(transaction);

			paymentInfo.setAmazonLastAuthorizationId(details.getAuthorizationReferenceId());
			paymentInfo.setAmazonAuthorizationStatus(details.getAuthorizationStatus().getState());
			paymentInfo.setAmazonAuthorizationReasonCode(details.getAuthorizationStatus().getReasonCode());

			getModelService().save(paymentInfo);
		}

		if(details.isCaptureNow()) {
			if(cEntry == null && transaction != null) {
				cEntry = getModelService().create(PaymentTransactionEntryModel.class);
				cEntry.setPaymentTransaction(transaction);
				cEntry.setCode(getNewEntryCode(transaction));
			}
			if(cEntry != null) {
				if (details.getCapturedAmount() != null) {
					cEntry.setAmount(BigDecimal.valueOf(Double.parseDouble(details.getCapturedAmount().getAmount())));
					cEntry.setCurrency(getCommonI18NService().getCurrency(details.getCapturedAmount().getCurrencyCode()));
				}
				cEntry.setType(PaymentTransactionType.CAPTURE);
				cEntry.setTime((details.getAuthorizationStatus().getLastUpdateTimestamp() == null) ? new Date()
						: details.getAuthorizationStatus().getLastUpdateTimestamp().toGregorianCalendar().getTime());
				cEntry.setRequestId(details.getAuthorizationReferenceId() + "-c");
				cEntry.setRequestToken(details.getAmazonAuthorizationId());

				setPaymentTransactionEntryStatus(cEntry, AmazonTransactionStatus.valueOf(details.getAuthorizationStatus().getState()), details.getAuthorizationStatus().getReasonCode());

				getModelService().save(cEntry);
				getModelService().refresh(transaction);

				paymentInfo.setAmazonCaptureId(details.getAmazonAuthorizationId());
				paymentInfo.setAmazonCaptureReasonCode(details.getAuthorizationStatus().getReasonCode());
				paymentInfo.setAmazonCaptureStatus(details.getAuthorizationStatus().getState());
			}
		}
	}
}