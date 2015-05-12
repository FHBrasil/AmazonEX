/**
 * 
 */
package br.hering.facades.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.payment.PaymentModeModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import org.springframework.util.Assert;

import br.hering.facades.order.data.PaymentModeData;
/**
 * @author flieger
 *
 */
public class PaymentModePopulator implements Populator<PaymentModeModel, PaymentModeData> 
{
	/* (non-Javadoc)
	 * @see de.hybris.platform.converters.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(PaymentModeModel source, PaymentModeData target) throws ConversionException 
	{
		Assert.notNull(source, "source is null");
		
		target.setCode(source.getCode());
		target.setName(source.getName());
		target.setDescription(source.getDescription());
	}
}
