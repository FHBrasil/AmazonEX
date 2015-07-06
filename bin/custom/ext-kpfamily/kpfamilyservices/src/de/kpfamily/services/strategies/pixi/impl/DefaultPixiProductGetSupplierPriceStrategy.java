package de.kpfamily.services.strategies.pixi.impl;

import java.math.BigDecimal;

import org.springframework.util.Assert;

import com.pixi.core.strategies.PixiProductGetSupplierPriceStrategy;

import de.hybris.platform.core.model.product.ProductModel;

public class DefaultPixiProductGetSupplierPriceStrategy implements PixiProductGetSupplierPriceStrategy 
{
	/* (non-Javadoc)
	 * @see com.pixi.core.strategies.PixiProductGetSupplierPriceStrategy#getSupplierPrice(de.hybris.platform.core.model.product.ProductModel)
	 */
	@Override
	public BigDecimal getSupplierPrice(final ProductModel product) 
	{
		Assert.notNull(product);
		
		double supplierPrice = product.getSupplierPrice();
		
		return BigDecimal.valueOf(supplierPrice);
	}
}