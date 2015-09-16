package de.fliegersoftware.amazon.payment.ipn.impl;

import org.apache.commons.lang.math.NumberUtils;

import com.amazonservices.mws.offamazonpaymentsipn.model.OrderReference;
import com.amazonservices.mws.offamazonpaymentsipn.model.OrderReferenceNotification;
import com.amazonservices.mws.offamazonpaymentsipn.model.OrderTotal;

import de.fliegersoftware.amazon.core.model.AmazonPaymentInfoModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;

public class OrderReferenceNotificationHandler extends BaseAmazonNotificationHandler<OrderReferenceNotification> {

	@Override
	public void handle(OrderReferenceNotification notification) {
		OrderReference details = notification.getOrderReference();
		
		PaymentTransactionModel transaction = getAmazonPaymentNotificationService().getPaymentTransactionForCode(details.getAmazonOrderReferenceId());
		if(transaction != null && transaction.getInfo() != null) {
			AmazonPaymentInfoModel paymentInfo = (AmazonPaymentInfoModel) transaction.getInfo();
			paymentInfo.setAmazonOrderReferenceId(details.getAmazonOrderReferenceId());
			paymentInfo.setAmazonOrderStatus(details.getOrderReferenceStatus().getState());
			paymentInfo.setAmazonOrderReasonCode(details.getOrderReferenceStatus().getReasonCode());
			paymentInfo.setAmazonOrderAmount(getDoubleValue(details.getOrderTotal()));
			paymentInfo.setCurrencyRefundRequestAmount(details.getOrderTotal().getCurrencyCode());

			getModelService().save(paymentInfo);
		}
	}

	private Double getDoubleValue(final OrderTotal orderTotal)
	{
		if (NumberUtils.isNumber(orderTotal.getAmount()))
		{
			return Double.valueOf(orderTotal.getAmount());
		}
		return null;
	}
}