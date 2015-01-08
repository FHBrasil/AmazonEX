package com.pixi.webservices.converters.populators.bmecat.product;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

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
	
	@Override
	public void populate(ProductModel source, Article target) throws ConversionException 
	{
		LOG.info("populating");
		
		final ArticleFeatures features = new ArticleFeatures();
		
		features.setSerialNumberRequired(false);

		addCustoms(source, features);
		
		addTaxes(source, features);
		
		addSuppliers(source, features);
		
		//TODO integrar min stock
//		Integer minStock = (Integer) product.getAttribute(Attributes.Product.MINDESTBESTAND);
//		articleBean.addFeature("MIN_STOCK_QTY", String.valueOf(minStock == null ? 0 : minStock));

		features.getFEATURE().add(getFeature("MIN_STOCK_QTY", "666"));
		
		target.setARTICLEFEATURES(features);
	}

	private void addSuppliers(ProductModel source, ArticleFeatures features) 
	{
		//TODO integrar suppliers
//		boolean newSuppliersAvalible = false;
//		try {
//			List<Supplier> suppliers = (List<Supplier>) product.getAttribute("kpsuppliers");
//			for (Supplier supplier : suppliers) {
//				articleBean.addFeature("ID " + supplier.getPixiSupplierCode(), (String) product.getAttribute("manufacturerAID"));
//			}
//			newSuppliersAvalible = CollectionUtils.isNotEmpty(suppliers);
//		} catch(Exception e) {
//			newSuppliersAvalible = false;
//			log.error(e);
//		}
//		
//		// get old supplier code if new not set
//		KPBrand kpBrand = (KPBrand) product.getAttribute(Attributes.Product.BRAND);
//		if (!newSuppliersAvalible && kpBrand != null) {
//			articleBean.addFeature(addCData("ID " + kpBrand.getPixiSupplierCode()), addCData((String)product.getAttribute("manufacturerAID")));
//		}
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

	private Feature getFeature(final String name, final String value) 
	{
		final Feature feature = new Feature();
		feature.setFNAME(name);
		feature.setFVALUE(value);
		
		return feature;
	}
}