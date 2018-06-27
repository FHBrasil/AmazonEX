package de.fliegersoftware.amazon.payment.services.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Currency;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.fliegersoftware.amazon.core.model.AmazonPaymentPaymentInfoModel;
import de.fliegersoftware.amazon.payment.dto.AmazonTransactionStatus;
import de.fliegersoftware.amazon.payment.services.AmazonCommerceCheckoutService;
import de.fliegersoftware.amazon.payment.services.AmazonPaymentService;
import de.hybris.platform.commerceservices.order.impl.DefaultCommerceCheckoutService;
import de.hybris.platform.commerceservices.strategies.GenerateMerchantTransactionCodeStrategy;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.enums.PaymentStatus;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.servicelayer.util.ServicesUtil;

/**
 * @author taylor.savegnago
 *
 */
public class DefaultAmazonCommerceCheckoutService extends DefaultCommerceCheckoutService implements AmazonCommerceCheckoutService
{
	
	private static final Logger LOG = LoggerFactory.getLogger(DefaultAmazonCommerceCheckoutService.class.getName());
	@Resource
	private GenerateMerchantTransactionCodeStrategy generateMerchantTransactionCodeStrategy;

	@Override
	public PaymentTransactionEntryModel authorizeAmazonPayment(CartModel cartModel, 
			String paymentProvider) {
		ServicesUtil.validateParameterNotNull(cartModel,
				"Cart model cannot be null");
		ServicesUtil.validateParameterNotNull(cartModel.getPaymentInfo(),
				"Payment information on cart cannot be null");

		Double totalPrice = cartModel.getTotalPrice();

		Double totalTax = ((cartModel.getNet().booleanValue())
				&& (cartModel.getStore() != null) && (cartModel.getStore()
				.getExternalTaxEnabled().booleanValue())) ? cartModel
				.getTotalTax() : Double.valueOf(0.0D);

		BigDecimal totalPriceWithoutTaxBD = new BigDecimal(
				(totalPrice == null) ? 0.0D : totalPrice.doubleValue())
				.setScale(2, RoundingMode.HALF_EVEN);

		BigDecimal totalPriceBD = new BigDecimal((totalTax == null) ? 0.0D
				: totalTax.doubleValue()).setScale(2, RoundingMode.HALF_EVEN)
				.add(totalPriceWithoutTaxBD);

		return authorizeAmazonPaymentAmount(cartModel, paymentProvider, totalPriceBD);
	}
	
	@Override
	public PaymentTransactionEntryModel authorizeAmazonPaymentAmount(final CartModel cartModel,
			final String paymentProvider,
			final BigDecimal amount) {
		PaymentTransactionEntryModel transactionEntryModel = null;
		PaymentInfoModel paymentInfo = cartModel.getPaymentInfo();
		if ((paymentInfo instanceof AmazonPaymentPaymentInfoModel) && (StringUtils.isNotBlank(((AmazonPaymentPaymentInfoModel) paymentInfo).getAmazonOrderReferenceId()))) {
			AmazonPaymentPaymentInfoModel amazonPaymentPaymentInfo = (AmazonPaymentPaymentInfoModel) paymentInfo;
			Currency currency = getI18nService().getBestMatchingJavaCurrency(cartModel.getCurrency().getIsocode());
			String merchantTransactionCode = getGenerateMerchantTransactionCodeStrategy().generateCode(cartModel);
			
			transactionEntryModel = getPaymentService().authorize(amazonPaymentPaymentInfo, merchantTransactionCode, amount, currency, 
					paymentProvider);
			if (transactionEntryModel != null) {
				PaymentTransactionModel paymentTransaction = transactionEntryModel
						.getPaymentTransaction();

				if (TransactionStatus.ACCEPTED.name().equals(transactionEntryModel.getTransactionStatus())) {

					if(amazonPaymentPaymentInfo.getAmazonCaptureStatus() != null
							&& AmazonTransactionStatus.Completed.name().equals(amazonPaymentPaymentInfo.getAmazonCaptureStatus())) {
						// update order status
						cartModel.setStatus(OrderStatus.PAYMENT_CAPTURED);
						cartModel.setPaymentStatus(PaymentStatus.PAID);
					} else {
						cartModel.setStatus(OrderStatus.PAYMENT_AUTHORIZED);
					}
					paymentTransaction.setOrder(cartModel);
					paymentTransaction.setInfo(amazonPaymentPaymentInfo);
					if(amazonPaymentPaymentInfo.getBillingAddress() == null)
						amazonPaymentPaymentInfo.setBillingAddress(cartModel.getDeliveryAddress());
					getModelService().saveAll(
							new Object[] { cartModel, paymentTransaction });
				} else if ("Declined".equals(transactionEntryModel.getTransactionStatus()) &&
						"TransactionTimedOut".equals(transactionEntryModel.getTransactionStatusDetails())) {
					LOG.info("if Ominichromous must call authorizePayment in Assync way");
				} else {
					getModelService().removeAll(
							Arrays.asList(new ItemModel[] { paymentTransaction,
									transactionEntryModel }));
				}
			}
		}
		return transactionEntryModel;
	}

	@Override
	protected AmazonPaymentService getPaymentService()
	{
		return (AmazonPaymentService)super.getPaymentService();
	}

	public GenerateMerchantTransactionCodeStrategy getGenerateMerchantTransactionCodeStrategy()
	{
		return this.generateMerchantTransactionCodeStrategy;
	}
}
