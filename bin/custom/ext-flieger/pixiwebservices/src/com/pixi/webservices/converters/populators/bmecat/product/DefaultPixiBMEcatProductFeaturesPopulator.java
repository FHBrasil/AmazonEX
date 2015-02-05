package com.pixi.webservices.converters.populators.bmecat.product;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.pixi.core.strategies.PixiProductGetMinimumStockLevelStrategy;
import com.pixi.core.strategies.PixiProductGetSuppliersCodeStrategy;
import com.pixi.webservices.jaxb.product.export.Article;
import com.pixi.webservices.jaxb.product.export.ArticleFeatures;
import com.pixi.webservices.jaxb.product.export.Feature;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.europe1.model.TaxRowModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class DefaultPixiBMEcatProductFeaturesPopulator implements Populator<ProductModel, Article>
{
	private Logger LOG = Logger.getLogger(DefaultPixiBMEcatProductFeaturesPopulator.class);
	
	@Resource
	private PixiProductGetMinimumStockLevelStrategy pixiProductGetMinimumStockLevelStrategy;
	
	@Resource
	private PixiProductGetSuppliersCodeStrategy pixiProductGetSuppliersCodeStrategy;
	
	@Override
	public void populate(ProductModel source, Article target) throws ConversionException 
	{
		LOG.info("populating");
		
		final ArticleFeatures features = new ArticleFeatures();
		
		features.setSerialNumberRequired(false);

		addCustoms(source, features);
		
		addTaxes(source, features);
		
		addSuppliers(source, features);
		
		int minStock = pixiProductGetMinimumStockLevelStrategy.getMinimumStockLevel(source);
		features.getFEATURE().add(getFeature("MIN_STOCK_QTY", minStock));
		
		target.setARTICLEFEATURES(features);
	}

	private void addSuppliers(ProductModel source, ArticleFeatures features) 
	{
		final List<String> suppliersCode = pixiProductGetSuppliersCodeStrategy.getSuppliersCode(source);
		
		final String manufacturerAID = source.getManufacturerAID();
		
		for(String code : suppliersCode)
		{
			features.getFEATURE().add(getFeature("ID " + code, manufacturerAID));
		}
	}
	
	private void addTaxes(ProductModel source, final ArticleFeatures features) 
	{
		Collection<TaxRowModel> taxes = source.getEurope1Taxes();
		
		if(CollectionUtils.isEmpty(taxes))
		{
			return;
		}
		
		if(hasVat(taxes, "vat-full"))
		{
			features.getFEATURE().add(getFeature("Europe1PriceFactory.PTG", "MwSt_High"));
		}
		else if(hasVat(taxes, "vat-half"))
		{
			features.getFEATURE().add(getFeature("Europe1PriceFactory.PTG", "MwSt_Low"));
		}
	}
	
	private boolean hasVat(Collection<TaxRowModel> taxes, String vatType) 
	{
		for(final TaxRowModel taxRow : taxes)
		{
			final String taxCode = taxRow.getTax().getCode();
			
			if(StringUtils.containsIgnoreCase(taxCode, vatType))
			{
				return true;
			}
		}
		
		return false;
	}

	private void addCustoms(ProductModel source, ArticleFeatures features) 
	{
		//customs, I think that we are not going to use it
//		features.setCustomsCountryOfOrigin("country");
//		features.setCustomsTariffNumber("tariff number");
//		features.setCustomsTariffText("tariff text");
	}

	private Feature getFeature(final String name, final Object value) 
	{
		final Feature feature = new Feature();
		feature.setFNAME(name);
		feature.setFVALUE(String.valueOf(value));
		
		return feature;
	}

	/**
	 * @return the pixiProductGetMinimumStockLevelStrategy
	 */
	public PixiProductGetMinimumStockLevelStrategy getPixiProductGetMinimumStockLevelStrategy() 
	{
		return pixiProductGetMinimumStockLevelStrategy;
	}

	/**
	 * @param pixiProductGetMinimumStockLevelStrategy the pixiProductGetMinimumStockLevelStrategy to set
	 */
	public void setPixiProductGetMinimumStockLevelStrategy(
			PixiProductGetMinimumStockLevelStrategy pixiProductGetMinimumStockLevelStrategy) 
	{
		this.pixiProductGetMinimumStockLevelStrategy = pixiProductGetMinimumStockLevelStrategy;
	}

	/**
	 * @return the pixiProductGetSuppliersCodeStrategy
	 */
	public PixiProductGetSuppliersCodeStrategy getPixiProductGetSuppliersCodeStrategy() 
	{
		return pixiProductGetSuppliersCodeStrategy;
	}

	/**
	 * @param pixiProductGetSuppliersCodeStrategy the pixiProductGetSuppliersCodeStrategy to set
	 */
	public void setPixiProductGetSuppliersCodeStrategy(
			PixiProductGetSuppliersCodeStrategy pixiProductGetSuppliersCodeStrategy) 
	{
		this.pixiProductGetSuppliersCodeStrategy = pixiProductGetSuppliersCodeStrategy;
	}
}