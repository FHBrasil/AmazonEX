package de.fliegersoftware.amazon.payment.services;

import java.util.List;

import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;

public interface AmazonPaymentNotificationService {

	public List<PaymentTransactionEntryModel> getPaymentTransactionEntriesForReferenceId(String ref);

	public PaymentTransactionModel getPaymentTransactionForCode(String code);
}