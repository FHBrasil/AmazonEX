package com.pixi.webservices.converters.populators.bmecat.product;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.pixi.webservices.jaxb.product.export.Article;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.kpfamily.core.model.BabyartikelProductModel;
import de.kpfamily.core.model.PackageSizeModel;
import de.kpfamily.services.logistics.LogisticsService;

public class DefaultPixiBMEcatProductTagPopulator implements Populator<ProductModel, Article>
{
	private Logger LOG = Logger.getLogger(DefaultPixiBMEcatProductTagPopulator.class);
	
	@Resource
	private LogisticsService logisticsService;
	
	@Override
	public void populate(ProductModel source, Article target) throws ConversionException 
	{
		LOG.info("populating");
		
		if(source instanceof BabyartikelProductModel)
		{
			BabyartikelProductModel babyModel = (BabyartikelProductModel) source;
			PackageSizeModel packageSize = logisticsService.getPackageSizeFromProduct(babyModel);

			if(packageSize != null)
			{
				target.getITEMTAG().add(packageSize.getTagCode());
			}
		}
		else
		{
			target.getITEMTAG().add("kleines");
		}
	}
}