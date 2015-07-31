package de.fliegersoftware.amazon.payment.addon.facades;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import de.fliegersoftware.amazon.core.model.AmazonPaymentInfoModel;
import de.fliegersoftware.amazon.payment.services.AmazonCommerceCheckoutService;
import de.hybris.platform.acceleratorfacades.order.impl.DefaultAcceleratorCheckoutFacade;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;


/**
 * @author taylor.savegnago
 *
 */
public class AmazonCheckoutFacade extends DefaultAcceleratorCheckoutFacade
{
	private static final Logger LOG = Logger.getLogger(AmazonCheckoutFacade.class);

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
				if (amazonPaymentInfoModel != null && StringUtils.isNotBlank(amazonPaymentInfoModel.getToken()))
				{
					final PaymentTransactionEntryModel paymentTransactionEntryModel = getAmazonCommerceCheckoutService().authorizePayment(
							cartModel, securityCode, getPaymentProvider());
	
					return paymentTransactionEntryModel != null
							&& (TransactionStatus.ACCEPTED.name().equals(paymentTransactionEntryModel.getTransactionStatus()) || TransactionStatus.REVIEW
									.name().equals(paymentTransactionEntryModel.getTransactionStatus()));
				}
			}
		}
		return false;
	}	
	
	protected AmazonCommerceCheckoutService getAmazonCommerceCheckoutService()
	{
		return (AmazonCommerceCheckoutService) getCommerceCheckoutService();
	}

}
