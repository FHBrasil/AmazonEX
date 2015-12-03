package de.fliegersoftware.amazon.payment.ipn.impl;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.amazonservices.mws.offamazonpaymentsipn.model.IdList;
import com.amazonservices.mws.offamazonpaymentsipn.model.OrderReferenceStatus;
import com.amazonservices.mws.offamazonpaymentsipn.model.OrderTotal;
import com.amazonservices.mws.offamazonpaymentsipn.model.Price;
import com.amazonservices.mws.offamazonpaymentsipn.model.Status;
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

	protected static void logInfo(Logger LOG, String prefix, String text) {
		if(StringUtils.isNotEmpty(text)) {
			LOG.info(prefix + ": " + text);
		} else {
			logNull(LOG, prefix);
		}
	}
	protected static void logInfo(Logger LOG, String prefix, boolean b) {
		LOG.info(prefix + ": " + Boolean.toString(b));
	}
	protected static void logNull(Logger LOG, String prefix) {
		LOG.info(prefix + " is null");
	}
	protected static void logTimestamp(Logger LOG, String prefix, XMLGregorianCalendar timestamp) {
		if(timestamp != null) {
			LOG.info(prefix + ": " + timestamp.toGregorianCalendar().getTime());
		} else {
			logNull(LOG, prefix);
		}
	}
	protected static void logStatus(Logger LOG, String prefix, Status status) {
		if(status != null) {
			logTimestamp(LOG, prefix + ": Last Update Timestamp", status.getLastUpdateTimestamp());
			logInfo(LOG, prefix + ": Reason Code", status.getReasonCode());
			logInfo(LOG, prefix + ": Reason Description", status.getReasonDescription());
			logInfo(LOG, prefix + ": State", status.getState());
		} else {
			logNull(LOG, prefix);
		}
	}
	protected static void logStatus(Logger LOG, String prefix, OrderReferenceStatus status) {
		if(status != null) {
			logTimestamp(LOG, prefix + ": Last Update Timestamp", status.getLastUpdateTimestamp());
			logInfo(LOG, prefix + ": Reason Code", status.getReasonCode());
			logInfo(LOG, prefix + ": Reason Description", status.getReasonDescription());
			logInfo(LOG, prefix + ": State", status.getState());
		} else {
			logNull(LOG, prefix);
		}
	}
	protected static void logIdList(Logger LOG, IdList idList) {
		if(idList != null && idList.isSetId()) {
			for(String id : idList.getId()) {
				LOG.info("Id List: " + id);
			}
		} else {
			logNull(LOG, "Id List");
		}
	}
	protected static void logPrice(Logger LOG, String prefix, Price price) {
		if(price != null) {
			logInfo(LOG, prefix + ": Amount", price.getAmount());
			logInfo(LOG, prefix + ": Currency Code", price.getCurrencyCode());
		} else {
			logNull(LOG, prefix);
		}
	}
	protected static void logPrice(Logger LOG, String prefix, OrderTotal price) {
		if(price != null) {
			logInfo(LOG, prefix + ": Amount", price.getAmount());
			logInfo(LOG, prefix + ": Currency Code", price.getCurrencyCode());
		} else {
			logNull(LOG, prefix);
		}
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