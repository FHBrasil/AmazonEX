package de.fliegersoftware.amazon.payment.addon.facades;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import de.fliegersoftware.amazon.core.model.AmazonPaymentInfoModel;
import de.fliegersoftware.amazon.payment.dto.AmazonTransactionStatus;
import de.fliegersoftware.amazon.payment.services.AmazonCommerceCheckoutService;
import de.hybris.platform.acceleratorfacades.order.impl.DefaultAcceleratorCheckoutFacade;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.payment.PaymentModeModel;
import de.hybris.platform.order.InvalidCartException;
import de.hybris.platform.order.OrderService;
import de.hybris.platform.order.PaymentModeService;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;


/**
 * @author taylor.savegnago
 *
 */
public class AmazonCheckoutFacade extends DefaultAcceleratorCheckoutFacade
{
	private static final Logger LOG = Logger.getLogger(AmazonCheckoutFacade.class);

	private OrderService orderService;
	private PaymentModeService paymentModeService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.commercefacades.order.impl.DefaultCheckoutFacade#authorizePayment(java.lang.String)
	 */
	@Override
	public boolean authorizePayment(final String securityCode)
	{
		final CartModel cartModel = getCart();
		if (checkIfCurrentUserIsTheCartUser())
		{
			if(cartModel.getPaymentInfo() instanceof AmazonPaymentInfoModel) {
				final AmazonPaymentInfoModel amazonPaymentInfoModel = (AmazonPaymentInfoModel) cartModel.getPaymentInfo();
				if (amazonPaymentInfoModel != null && StringUtils.isNotBlank(amazonPaymentInfoModel.getAmazonOrderReferenceId()))
				{
					final PaymentTransactionEntryModel paymentTransactionEntryModel = getCommerceCheckoutService().authorizeAmazonPayment(cartModel, getPaymentProvider());
	
					return paymentTransactionEntryModel != null
							&& (AmazonTransactionStatus.Pending.name().equals(paymentTransactionEntryModel.getTransactionStatus())
								|| AmazonTransactionStatus.Open.name().equals(paymentTransactionEntryModel.getTransactionStatus())
								|| AmazonTransactionStatus.Closed.name().equals(paymentTransactionEntryModel.getTransactionStatus()));
				}
			}
		}
		return false;
	}

	@Override
	public boolean setPaymentDetails(final String paymentInfoId)
	{
		validateParameterNotNullStandardMessage("paymentInfoId", paymentInfoId);

		final CartModel cartModel = getCart();
		if (checkIfCurrentUserIsTheCartUser())
		{
			if (StringUtils.isNotBlank(paymentInfoId))
			{
				final AmazonPaymentInfoModel amazonPaymentInfoModel = new AmazonPaymentInfoModel();
				amazonPaymentInfoModel.setAmazonOrderReferenceId(paymentInfoId);
				amazonPaymentInfoModel.setCode(paymentInfoId);
				amazonPaymentInfoModel.setUser(getCurrentUserForCheckout());
				PaymentModeModel paymentMode = getPaymentModeService().getPaymentModeForCode("amazon");
				cartModel.setPaymentMode(paymentMode);
				return getCommerceCheckoutService().setPaymentInfo(cartModel, amazonPaymentInfoModel);
			}
		}

		return false;
	}

	public OrderData createOrderFromCart() {
		try {
			CartModel cartModel = getCart();
			OrderModel orderModel = getOrderService().createOrderFromCart(cartModel);
			cartModel.setPreCreatedOrder(orderModel);
			getModelService().saveAll(cartModel, orderModel);
			return getOrderConverter().convert(orderModel);
		} catch (InvalidCartException e) {
			e.printStackTrace();
		}
		return null;
	}

	protected AmazonCommerceCheckoutService getCommerceCheckoutService()
	{
		return (AmazonCommerceCheckoutService) super.getCommerceCheckoutService();
	}

	protected OrderService getOrderService() {
		return orderService;
	}

	@Required
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	protected PaymentModeService getPaymentModeService() {
		return paymentModeService;
	}

	@Required
	public void setPaymentModeService(PaymentModeService paymentModeService) {
		this.paymentModeService = paymentModeService;
	}
}
