package de.fliegersoftware.amazon.payment.ipn.impl;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;

import com.amazonservices.mws.offamazonpaymentsipn.model.AuthorizationDetails;
import com.amazonservices.mws.offamazonpaymentsipn.model.AuthorizationNotification;
import com.amazonservices.mws.offamazonpaymentsipn.model.IdList;
import com.amazonservices.mws.offamazonpaymentsipn.model.OrderItemCategories;
import com.amazonservices.mws.offamazonpaymentsipn.model.Price;
import com.amazonservices.mws.offamazonpaymentsipn.model.Status;

import de.fliegersoftware.amazon.core.model.AmazonPaymentPaymentInfoModel;
import de.fliegersoftware.amazon.payment.dto.AmazonTransactionStatus;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import static de.fliegersoftware.amazon.payment.util.PaymentTransactionEntryUtil.setPaymentTransactionEntryStatus;

public class AuthorizationNotificationHandler extends BaseAmazonNotificationHandler<AuthorizationNotification> {

	private static final Logger LOG = Logger.getLogger(AuthorizationNotificationHandler.class);

	@Override
	public void log(AuthorizationNotification notification) {
		AuthorizationDetails details = notification.getAuthorizationDetails();
		logInfo(LOG, "Address Verification Code", details.getAddressVerificationCode());
		logInfo(LOG, "Amazon Authorization Id", details.getAmazonAuthorizationId());
		logPrice(LOG, "Authorization Amount", details.getAuthorizationAmount());
		logPrice(LOG, "Authorization Fee", details.getAuthorizationFee());
		logInfo(LOG, "Authorization Reference Id", details.getAuthorizationReferenceId());
		logStatus(LOG, "Authorization Status", details.getAuthorizationStatus());
		logPrice(LOG, "Captured Amount", details.getCapturedAmount());
		logTimestamp(LOG, "Creation Timestamp", details.getCreationTimestamp());
		logTimestamp(LOG, "Expiration Timestamp", details.getExpirationTimestamp());
		logIdList(LOG, details.getIdList());
		if(details.isSetOrderItemCategories()) {
			for(String cat : details.getOrderItemCategories().getOrderItemCategory()) {
				logInfo(LOG, "Order Item Category", cat);
			}
		} else {
			logNull(LOG, "Order Item Categories");
		}
		logInfo(LOG, "Soft Descriptor", details.getSoftDescriptor());
		logInfo(LOG, "Capture Now", details.isCaptureNow());
	}

	@Override
	public void handle(AuthorizationNotification notification) {
		AuthorizationDetails details = notification.getAuthorizationDetails();
		AmazonPaymentPaymentInfoModel paymentInfo = null;
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
				paymentInfo = (AmazonPaymentPaymentInfoModel) transaction.getInfo();
				aEntry = getModelService().create(PaymentTransactionEntryModel.class);
				aEntry.setPaymentTransaction(transaction);
				aEntry.setCode(getNewEntryCode(transaction));
			}
		} else {
			transaction = aEntry.getPaymentTransaction();
			paymentInfo = (AmazonPaymentPaymentInfoModel) transaction.getInfo();
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