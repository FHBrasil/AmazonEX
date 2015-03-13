package de.kpfamily.services.strategies.pixi.impl;

import java.math.BigDecimal;

import org.springframework.util.Assert;

import com.pixi.core.strategies.PixiProductGetWeightStrategy;

import de.hybris.platform.core.model.product.ProductModel;

public class DefaultPixiProductGetWeightStrategy implements PixiProductGetWeightStrategy 
{
	@Override
	public BigDecimal getWeight(ProductModel product) 
	{
		Assert.notNull(product);
		
		double weight = product.getWeight();

		return BigDecimal.valueOf(weight);
	}
}