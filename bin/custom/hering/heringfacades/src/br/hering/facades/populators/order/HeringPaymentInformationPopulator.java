/**
 * 
 */
package br.hering.facades.populators.order;

import de.hybris.platform.commercefacades.order.converters.populator.AbstractOrderPopulator;
import de.hybris.platform.commercefacades.order.data.AbstractOrderData;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import com.flieger.payment.data.BoletoPaymentInfoData;
import com.flieger.payment.model.BoletoPaymentInfoModel;


/**
 * @author flieger
 * 
 */
public abstract class HeringPaymentInformationPopulator extends AbstractOrderPopulator<AbstractOrderModel, AbstractOrderData>
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
	 * @param boletoPaymentInfoConverter
	 *           the boletoPaymentInfoConverter to set
	 */
	public void setBoletoPaymentInfoConverter(Converter<BoletoPaymentInfoModel, BoletoPaymentInfoData> boletoPaymentInfoConverter)
	{
		this.boletoPaymentInfoConverter = boletoPaymentInfoConverter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.commercefacades.order.converters.populator.AbstractOrderPopulator#addPaymentInformation(de.
	 * hybris.platform.core.model.order.AbstractOrderModel,
	 * de.hybris.platform.commercefacades.order.data.AbstractOrderData)
	 */
	@Override
	protected void addPaymentInformation(AbstractOrderModel source, AbstractOrderData prototype)
	{
		final PaymentInfoModel paymentInfo = source.getPaymentInfo();
		if (paymentInfo instanceof CreditCardPaymentInfoModel)
		{
			super.addPaymentInformation(source, prototype);
		}
		else if (paymentInfo instanceof BoletoPaymentInfoModel)
		{
			final BoletoPaymentInfoData paymentInfoData = getBoletoPaymentInfoConverter().convert(
					(BoletoPaymentInfoModel) paymentInfo);

			prototype.setCustomPaymentInfo(paymentInfoData);
		}
	}

}
