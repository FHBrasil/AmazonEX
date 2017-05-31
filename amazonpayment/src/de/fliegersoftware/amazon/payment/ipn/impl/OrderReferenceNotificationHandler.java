package de.fliegersoftware.amazon.payment.ipn.impl;

import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;  import org.slf4j.LoggerFactory;

import com.amazonservices.mws.offamazonpaymentsipn.model.OrderReference;
import com.amazonservices.mws.offamazonpaymentsipn.model.OrderReferenceNotification;
import com.amazonservices.mws.offamazonpaymentsipn.model.OrderTotal;
import com.amazonservices.mws.offamazonpaymentsipn.model.SellerOrderAttributes;

import de.fliegersoftware.amazon.core.model.AmazonPaymentPaymentInfoModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;

public class OrderReferenceNotificationHandler extends BaseAmazonNotificationHandler<OrderReferenceNotification> {

	private static final Logger LOG = LoggerFactory.getLogger(OrderReferenceNotificationHandler.class);

	@Override
	public void log(OrderReferenceNotification notification) {
		OrderReference details = notification.getOrderReference();
		logInfo(LOG, "Amazon Order Reference Id", details.getAmazonOrderReferenceId());
		logTimestamp(LOG, "Creation Timestamp", details.getCreationTimestamp());
		logTimestamp(LOG, "Expiration Timestamp", details.getExpirationTimestamp());
		logStatus(LOG, "", details.getOrderReferenceStatus());
		logPrice(LOG, "Order Total", details.getOrderTotal());
		if(details.getSellerOrderAttributes() != null) {
			SellerOrderAttributes attribs = details.getSellerOrderAttributes();
			logInfo(LOG, "Seller Order Attributes: Seller Id", attribs.getSellerId());
			logInfo(LOG, "Seller Order Attributes: Seller Order Id", attribs.getSellerOrderId());
			if(attribs.getOrderItemCategories() != null && attribs.getOrderItemCategories().isSetOrderItemCategory()) {
				for(String attr : attribs.getOrderItemCategories().getOrderItemCategory()) {
					logInfo(LOG, "Seller Order Attributes: Order Item Categories", attr);
				}
			} else {
				logNull(LOG, "Seller Order Attributes: Order Item Categories");
			}
		} else {
			logNull(LOG, "Seller Order Attributes");
		}
	}

	@Override
	public void handle(OrderReferenceNotification notification) {
		OrderReference details = notification.getOrderReference();
		
		PaymentTransactionModel transaction = getAmazonPaymentNotificationService().getPaymentTransactionForCode(details.getAmazonOrderReferenceId());
		if(transaction != null && transaction.getInfo() != null) {
			AmazonPaymentPaymentInfoModel paymentInfo = (AmazonPaymentPaymentInfoModel) transaction.getInfo();
			paymentInfo.setAmazonOrderReferenceId(details.getAmazonOrderReferenceId());
			paymentInfo.setAmazonOrderStatus(details.getOrderReferenceStatus().getState());
			paymentInfo.setAmazonOrderReasonCode(details.getOrderReferenceStatus().getReasonCode());
			paymentInfo.setAmazonOrderAmount(getDoubleValue(details.getOrderTotal()));
			paymentInfo.setCurrencyRefundRequestAmount(details.getOrderTotal().getCurrencyCode());

			getModelService().save(paymentInfo);
			logInfo(LOG, "Save Order Reference Id", details.getAmazonOrderReferenceId());
		}else{
			logInfo(LOG, "Amazon Order Reference Id Not Found", details.getAmazonOrderReferenceId());

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