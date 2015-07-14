package br.hering.core.strategies.impl;

import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import br.hering.core.strategies.ShippingEstimatesStrategy;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.product.ProductModel;

public class DefaultShippingEstimatesStrategy implements ShippingEstimatesStrategy 
{
	private static final Logger LOG = Logger.getLogger(DefaultShippingEstimatesStrategy.class);
	
	@Override
	public boolean isImmediateShipping(AbstractOrderEntryModel entry) 
	{
		Assert.notNull(entry);
	
		LOG.warn("ISSO EH MOCK, TEMOS Q PROGRAMAR DE VERDADE DEPOIS NA BABY-99");
		
		return entry.getEntryNumber().intValue() % 2 != 0;
		
		//return isImmediateShipping(entry.getProduct());
	}

	@Override
	public boolean isImmediateShipping(ProductModel product) 
	{
		Assert.notNull(product);
		
		return true;
	}
}