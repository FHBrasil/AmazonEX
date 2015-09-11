package de.fliegersoftware.amazon.payment.addon.facades;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import de.fliegersoftware.amazon.core.constants.AmazoncoreConstants;
import de.fliegersoftware.amazon.core.model.AmazonPaymentInfoModel;
import de.fliegersoftware.amazon.payment.dto.AmazonTransactionStatus;
import de.fliegersoftware.amazon.payment.services.AmazonCommerceCheckoutService;
import de.hybris.platform.acceleratorfacades.order.impl.DefaultAcceleratorCheckoutFacade;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commercefacades.user.data.CountryData;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.payment.PaymentModeModel;
import de.hybris.platform.order.InvalidCartException;
import de.hybris.platform.order.OrderService;
import de.hybris.platform.order.PaymentModeService;
import de.hybris.platform.payment.dto.TransactionStatus;
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
			if(cartModel.getPaymentInfo() instanceof AmazonPaymentInfoModel
					&& StringUtils.isNotBlank(((AmazonPaymentInfoModel)cartModel.getPaymentInfo()).getAmazonOrderReferenceId())) {
					final PaymentTransactionEntryModel paymentTransactionEntryModel = getCommerceCheckoutService().authorizeAmazonPayment(cartModel, AmazoncoreConstants.PAYMENT_PROVIDER_NAME);
	
					return paymentTransactionEntryModel != null
							&& TransactionStatus.ACCEPTED.name().equals(paymentTransactionEntryModel.getTransactionStatus());
			}
		}
		return super.authorizePayment(securityCode);
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

	public String createOrderCodeFromCart() {
		try {
			CartModel cartModel = getCart();
			OrderModel orderModel = getOrderService().createOrderFromCart(cartModel);
			cartModel.setPreCreatedOrderCode(orderModel.getCode());
			getModelService().save(cartModel);
			return orderModel.getCode();
		} catch (InvalidCartException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean isDeliveryCountrySupported(CountryData deliveryCountry) {
		if (deliveryCountry != null) {
			List<CountryData> deliveryCountriesSupported = super.getDeliveryCountries();
			for (CountryData countryData : deliveryCountriesSupported) {
				if (countryData.getIsocode().equalsIgnoreCase(deliveryCountry.getIsocode())) {
					return true;
				}
			}
		}
		return false;
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
