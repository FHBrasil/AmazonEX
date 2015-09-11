package de.fliegersoftware.amazon.payment.ipn.impl;

import org.springframework.beans.factory.annotation.Required;

import com.amazonservices.mws.offamazonpaymentsipn.notifications.INotification;

import de.fliegersoftware.amazon.payment.ipn.AmazonNotificationHandler;
import de.fliegersoftware.amazon.payment.services.AmazonPaymentNotificationService;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;

public abstract class BaseAmazonNotificationHandler<T extends INotification> implements AmazonNotificationHandler<T> {

	private AmazonPaymentNotificationService amazonPaymentNotificationService;
	private ModelService modelService;
	private CommonI18NService commonI18NService;

	protected String getNewEntryCode(PaymentTransactionModel transaction) {
		if (transaction.getEntries() == null) {
			return transaction.getCode() + "-1";
		}
		return transaction.getCode() + "-" + (transaction.getEntries().size() + 1);
	}

	protected AmazonPaymentNotificationService getAmazonPaymentNotificationService() {
		return amazonPaymentNotificationService;
	}

	@Required
	public void setAmazonPaymentNotificationService(
			AmazonPaymentNotificationService amazonPaymentNotificationService) {
		this.amazonPaymentNotificationService = amazonPaymentNotificationService;
	}

	protected ModelService getModelService() {
		return modelService;
	}

	@Required
	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}

	protected CommonI18NService getCommonI18NService() {
		return commonI18NService;
	}

	@Required
	public void setCommonI18NService(CommonI18NService commonI18NService) {
		this.commonI18NService = commonI18NService;
	}
}