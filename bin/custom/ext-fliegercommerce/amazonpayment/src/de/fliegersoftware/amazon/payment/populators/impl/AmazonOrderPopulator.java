package de.fliegersoftware.amazon.payment.populators.impl;

import de.fliegersoftware.amazon.core.model.AmazonPaymentPaymentInfoModel;
import de.hybris.platform.commercefacades.order.converters.populator.OrderPopulator;
import de.hybris.platform.commercefacades.order.data.AbstractOrderData;
import de.hybris.platform.commercefacades.order.data.CCPaymentInfoData;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;

/**
 * @author taylor.savegnago
 */
public class AmazonOrderPopulator extends OrderPopulator {
    
    private Converter<AmazonPaymentPaymentInfoModel, CCPaymentInfoData> amazonPaymentPaymentInfoConverter;
    
    
    /*
     * (non-Javadoc)
     * @see
     * de.hybris.platform.commercefacades.order.converters.populator.AbstractOrderPopulator#
     * addPaymentInformation(de.hybris.platform.core.model.order.AbstractOrderModel, de.hybris.platform.commercefacades.order.data.AbstractOrderData)
     */
    @Override
    protected void addPaymentInformation(final AbstractOrderModel source,
            final AbstractOrderData prototype) {
        final PaymentInfoModel paymentInfo = source.getPaymentInfo();
        if (paymentInfo instanceof CreditCardPaymentInfoModel) {
            final CCPaymentInfoData paymentInfoData =
                    getCreditCardPaymentInfoConverter().convert(
                            (CreditCardPaymentInfoModel) paymentInfo);
            prototype.setPaymentInfo(paymentInfoData);
        } else if (paymentInfo instanceof AmazonPaymentPaymentInfoModel) {
            final CCPaymentInfoData paymentInfoData = getAmazonPaymentPaymentInfoConverter().convert((AmazonPaymentPaymentInfoModel) paymentInfo);
            prototype.setPaymentInfo(paymentInfoData);
        }
    }


	public Converter<AmazonPaymentPaymentInfoModel, CCPaymentInfoData> getAmazonPaymentPaymentInfoConverter() {
		return amazonPaymentPaymentInfoConverter;
	}


	public void setAmazonPaymentPaymentInfoConverter(
			Converter<AmazonPaymentPaymentInfoModel, CCPaymentInfoData> amazonPaymentPaymentInfoConverter) {
		this.amazonPaymentPaymentInfoConverter = amazonPaymentPaymentInfoConverter;
	}
    
    
}
