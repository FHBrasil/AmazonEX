package br.hering.core.strategies;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.product.ProductModel;

public interface ShippingEstimatesStrategy 
{
	boolean isImmediateShipping(AbstractOrderEntryModel entry);
	
	boolean isImmediateShipping(ProductModel product);
}