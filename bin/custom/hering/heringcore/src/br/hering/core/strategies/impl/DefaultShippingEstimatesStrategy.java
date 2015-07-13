package br.hering.core.strategies.impl;

import org.springframework.util.Assert;

import br.hering.core.strategies.ShippingEstimatesStrategy;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.product.ProductModel;

public class DefaultShippingEstimatesStrategy implements ShippingEstimatesStrategy 
{
	@Override
	public boolean isImmediateShipping(AbstractOrderEntryModel entry) 
	{
		Assert.notNull(entry);
		return isImmediateShipping(entry.getProduct());
	}

	@Override
	public boolean isImmediateShipping(ProductModel product) 
	{
		Assert.notNull(product);
		
		//TODO
		
		return true;
	}
}