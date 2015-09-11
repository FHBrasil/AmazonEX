/**
 * 
 */
package br.hering.facades.populators;

import de.hybris.platform.commercefacades.order.data.CCPaymentInfoData;
import de.hybris.platform.commercefacades.user.converters.populator.CCPaymentInfoReversePopulator;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/**
 * @author flieger
 *
 */
public class HeringCreditCardPaymentInfoReversePopulator extends CCPaymentInfoReversePopulator
{

	/* (non-Javadoc)
	 * @see de.hybris.platform.commercefacades.user.converters.populator.CCPaymentInfoReversePopulator#populate(de.hybris.platform.commercefacades.order.data.CCPaymentInfoData, de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel)
	 */
	@Override
	public void populate(CCPaymentInfoData ccPaymentInfoData, CreditCardPaymentInfoModel cardPaymentInfoModel)
			throws ConversionException
	{
		cardPaymentInfoModel.setInstallment(new Integer(ccPaymentInfoData.getInstallment()));
		cardPaymentInfoModel.setCardBrand(ccPaymentInfoData.getCardBrand());
		super.populate(ccPaymentInfoData, cardPaymentInfoModel);
	}


}
