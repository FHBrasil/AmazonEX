package de.fliegersoftware.amazon.payment.util;

import de.fliegersoftware.amazon.payment.dto.AmazonTransactionStatus;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.dto.TransactionStatusDetails;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;

public final class PaymentTransactionEntryUtil {

	private PaymentTransactionEntryUtil() {
	}

	public static void setPaymentTransactionEntryStatus(PaymentTransactionEntryModel entry, AmazonTransactionStatus status, String reasonCode) {
		switch(entry.getType()) {
		case AUTHORIZATION:
			handleAuthorization(entry, status, reasonCode);
			break;
		case CAPTURE:
			handleCapture(entry, status, reasonCode);
			break;
		case REFUND:
			handleRefund(entry, status, reasonCode);
			break;
		default:
			handleOther(entry, status, reasonCode);
			break;
		}
	}

	private static void handleAuthorization(PaymentTransactionEntryModel entry,
			AmazonTransactionStatus status, String reasonCode) {
		switch (status) {
		case Open:
		case Pending: {
			entry.setTransactionStatus(TransactionStatus.ACCEPTED.name());
			entry.setTransactionStatusDetails(status.name());
			break;
		}
		case Closed: {
			entry.setTransactionStatus(TransactionStatus.ACCEPTED.name());
			entry.setTransactionStatusDetails(TransactionStatusDetails.SUCCESFULL.name());
			break;
		}
		case Declined: {
			entry.setTransactionStatus(TransactionStatus.REJECTED.name());
			entry.setTransactionStatusDetails(TransactionStatusDetails.AUTHORIZATION_REJECTED_BY_PSP.name());
			break;
		}
		default: {
			entry.setTransactionStatus(TransactionStatus.ERROR.name());
			entry.setTransactionStatusDetails(TransactionStatusDetails.UNKNOWN_CODE.name());
			break;
		}
		}
	}

	private static void handleCapture(PaymentTransactionEntryModel entry,
			AmazonTransactionStatus status, String reasonCode) {
		switch (status) {
		case Pending: {
			entry.setTransactionStatus(TransactionStatus.ACCEPTED.name());
			entry.setTransactionStatusDetails(status.name());
			break;
		}
		case Completed:
		case Closed: {
			entry.setTransactionStatus(TransactionStatus.ACCEPTED.name());
			entry.setTransactionStatusDetails(TransactionStatusDetails.SUCCESFULL.name());
			break;
		}
		case Declined: {
			switch(DeclinedReasonCodes.valueOf(reasonCode)) {
			case PROCESSINGFAILURE:
			case TRANSACTIONTIMEDOUT: {
				entry.setTransactionStatus(TransactionStatus.REVIEW.name());
				entry.setTransactionStatusDetails(TransactionStatusDetails.COMMUNICATION_PROBLEM.name());
			}
			default: {
				entry.setTransactionStatus(TransactionStatus.REJECTED.name());
				entry.setTransactionStatusDetails(reasonCode);
			}
			}
			break;
		}
		default: {
			entry.setTransactionStatus(TransactionStatus.ERROR.name());
			entry.setTransactionStatusDetails(TransactionStatusDetails.UNKNOWN_CODE.name());
			break;
		}
		}
	}

	private static void handleRefund(PaymentTransactionEntryModel entry,
			AmazonTransactionStatus status, String reasonCode) {
		switch (status) {
		case Pending: {
			entry.setTransactionStatus(TransactionStatus.ACCEPTED.name());
			entry.setTransactionStatusDetails(status.name());
			break;
		}
		case Completed: {
			entry.setTransactionStatus(TransactionStatus.ACCEPTED.name());
			entry.setTransactionStatusDetails(TransactionStatusDetails.SUCCESFULL.name());
			break;
		}
		case Declined: {
			entry.setTransactionStatus(TransactionStatus.REJECTED.name());
			entry.setTransactionStatusDetails(reasonCode);
			break;
		}
		default: {
			entry.setTransactionStatus(TransactionStatus.ERROR.name());
			entry.setTransactionStatusDetails(TransactionStatusDetails.UNKNOWN_CODE.name());
			break;
		}
		}
	}

	private static void handleOther(PaymentTransactionEntryModel entry,
			AmazonTransactionStatus status, String reasonCode) {
		entry.setTransactionStatus(status.name());
		entry.setTransactionStatusDetails(reasonCode);
	}
}