/**
 * 
 */
package br.hering.facades.populators;

import de.hybris.platform.commercefacades.order.converters.populator.CreditCardPaymentInfoPopulator;
import de.hybris.platform.commercefacades.order.data.CCPaymentInfoData;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;

/**
 * @author flieger
 *
 */
public class HeringCreditCardPaymentInfoPopulator extends CreditCardPaymentInfoPopulator
{

   @Override
   public void populate(final CreditCardPaymentInfoModel source, final CCPaymentInfoData target)
   {
   	super.populate(source, target);
   	if(source.getCardBrand() != null) {
   		target.setCardBrand(source.getCardBrand());
   	}
   	if(source.getInstallment() != null) {
   		target.setInstallment(source.getInstallment().toString());
   	}
   }
}
