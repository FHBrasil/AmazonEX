package de.fliegersoftware.amazon.payment.addon.facades;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import de.fliegersoftware.amazon.core.model.AmazonPaymentInfoModel;
import de.fliegersoftware.amazon.payment.dto.AmazonTransactionStatus;
import de.fliegersoftware.amazon.payment.services.AmazonCommerceCheckoutService;
import de.hybris.platform.acceleratorfacades.order.impl.DefaultAcceleratorCheckoutFacade;
import de.hybris.platform.core.model.order.CartModel;
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
				if (amazonPaymentInfoModel != null && StringUtils.isNotBlank(amazonPaymentInfoModel.getAmazonOrderReferenceId()))
				{
					final PaymentTransactionEntryModel paymentTransactionEntryModel = getCommerceCheckoutService().authorizeAmazonPayment(cartModel, getPaymentProvider());
	
					return paymentTransactionEntryModel != null
							&& (AmazonTransactionStatus.Pending.name().equals(paymentTransactionEntryModel.getTransactionStatus()));
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
				return getCommerceCheckoutService().setPaymentInfo(cartModel, amazonPaymentInfoModel);
			}
		}

		return false;
	}

	protected AmazonCommerceCheckoutService getCommerceCheckoutService()
	{
		return (AmazonCommerceCheckoutService) super.getCommerceCheckoutService();
	}

}
