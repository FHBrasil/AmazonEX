/**
 * 
 */
package br.hering.facades.populators;

import de.hybris.platform.commercefacades.order.data.AbstractOrderData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.payment.PaymentModeModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import br.hering.facades.order.data.PaymentModeData;


/**
 * @author flieger
 *
 */
public class CartPaymentModePopulator implements Populator<AbstractOrderModel, AbstractOrderData> 
{
	//XXX PAYMENT_MODE OK
	private Converter<PaymentModeModel, PaymentModeData> paymentModeConverter;
		
	/* (non-Javadoc)
	 * @see de.hybris.platform.converters.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(AbstractOrderModel source, AbstractOrderData target) throws ConversionException
	{
		PaymentModeModel paymentMode = source.getPaymentMode();

		if(paymentMode == null) 
		{
			return;
		}
		
		PaymentModeData paymentModeData = paymentModeConverter.convert(paymentMode);
		
		target.setPaymentMode(paymentModeData);
		target.setEstimatedDeliveryDays(new Integer(source.getEstimatedDeliveryDays()));
	}

	public Converter<PaymentModeModel, PaymentModeData> getPaymentModeConverter() 
	{
		return paymentModeConverter;
	}

	public void setPaymentModeConverter(Converter<PaymentModeModel, PaymentModeData> paymentModeConverter) 
	{
		this.paymentModeConverter = paymentModeConverter;
	}
}