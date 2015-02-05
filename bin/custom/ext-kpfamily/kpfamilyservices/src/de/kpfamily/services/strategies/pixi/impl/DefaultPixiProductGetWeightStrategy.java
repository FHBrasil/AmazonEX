package de.kpfamily.services.strategies.pixi.impl;

import java.math.BigDecimal;

import org.springframework.util.Assert;

import com.pixi.core.strategies.PixiProductGetWeightStrategy;

import de.hybris.platform.core.model.product.ProductModel;
import de.kpfamily.core.model.BabyartikelProductModel;

public class DefaultPixiProductGetWeightStrategy implements PixiProductGetWeightStrategy 
{
	@Override
	public BigDecimal getWeight(ProductModel product) 
	{
		Assert.notNull(product);
		Assert.isInstanceOf(BabyartikelProductModel.class, product);
		
		BabyartikelProductModel babyartikelProduct = (BabyartikelProductModel) product;
		
		double weight = babyartikelProduct.getWeight();

		return BigDecimal.valueOf(weight);
	}
}