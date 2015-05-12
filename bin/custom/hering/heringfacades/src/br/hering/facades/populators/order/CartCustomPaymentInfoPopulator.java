package br.hering.facades.populators.order;

/**
 * @author flieger
 *
 */
import de.hybris.platform.commercefacades.order.data.AbstractOrderData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import com.flieger.payment.data.BoletoPaymentInfoData;
import com.flieger.payment.model.BoletoPaymentInfoModel;


public class CartCustomPaymentInfoPopulator implements Populator<AbstractOrderModel, AbstractOrderData>
{
	private Converter<BoletoPaymentInfoModel, BoletoPaymentInfoData> boletoPaymentInfoConverter;

	/**
	 * @return the boletoPaymentInfoConverter
	 */
	public Converter<BoletoPaymentInfoModel, BoletoPaymentInfoData> getBoletoPaymentInfoConverter()
	{
		return boletoPaymentInfoConverter;
	}

	/**
	 * @param boletoPaymentInfoConverter the boletoPaymentInfoConverter to set
	 */
	public void setBoletoPaymentInfoConverter(Converter<BoletoPaymentInfoModel, BoletoPaymentInfoData> boletoPaymentInfoConverter)
	{
		this.boletoPaymentInfoConverter = boletoPaymentInfoConverter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.converters.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final AbstractOrderModel source, final AbstractOrderData target) throws ConversionException
	{
		final PaymentInfoModel paymentInfo = source.getPaymentInfo();
		if (paymentInfo == null || paymentInfo instanceof CreditCardPaymentInfoModel)
		{
			return;
		}

		if (paymentInfo instanceof BoletoPaymentInfoModel)
		{

			final BoletoPaymentInfoModel boletoPaymentInfo = (BoletoPaymentInfoModel) paymentInfo;
			final BoletoPaymentInfoData boletoPaymentInfoData = boletoPaymentInfoConverter.convert(boletoPaymentInfo);

			target.setCustomPaymentInfo(boletoPaymentInfoData);

		}
	}

}