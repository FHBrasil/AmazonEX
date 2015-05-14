package com.pixi.webservices.converters.populators.bmecat.product;

import java.math.BigDecimal;
import java.util.Collection;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.pixi.core.strategies.PixiProductGetSupplierPriceStrategy;
import com.pixi.webservices.constants.PixiwebservicesConstants;
import com.pixi.webservices.jaxb.product.export.Article;
import com.pixi.webservices.jaxb.product.export.ArticlePrice;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class DefaultPixiBMEcatProductPricePopulator implements Populator<ProductModel, Article>
{
	private Logger LOG = Logger.getLogger(DefaultPixiBMEcatProductPricePopulator.class);
	
	@Resource
	private PixiProductGetSupplierPriceStrategy pixiProductGetSupplierPriceStrategy;
	
	@Override
	public void populate(ProductModel source, Article target) throws ConversionException 
	{
		LOG.info("populating");
		
		final Collection<PriceRowModel> priceList = source.getEurope1Prices();
		
		if(CollectionUtils.isEmpty(priceList))
		{
			return;
		}
		
		for(PriceRowModel priceRow : priceList)
		{
			final ArticlePrice price = new ArticlePrice();
			price.setPriceType(getPriceType(priceRow));
			price.setPRICECURRENCY(priceRow.getCurrency().getIsocode());
			price.setPRICEAMOUNT(BigDecimal.valueOf(priceRow.getPrice()));
			price.setSUPPLPRICEAMOUNT(getPixiProductGetSupplierPriceStrategy().getSupplierPrice(source));
			
			target.getARTICLEPRICE().add(price);
		}
	}
	
	private String getPriceType(final PriceRowModel priceRow) 
	{
		boolean isNet = priceRow.getNet().booleanValue();
		
		return isNet ? 
				PixiwebservicesConstants.Product.PRICE_TYPE_NET : 
					PixiwebservicesConstants.Product.PRICE_TYPE_GROS;
	}

	/**
	 * @return the pixiProductGetSupplierPriceStrategy
	 */
	public PixiProductGetSupplierPriceStrategy getPixiProductGetSupplierPriceStrategy() 
	{
		return pixiProductGetSupplierPriceStrategy;
	}

	/**
	 * @param pixiProductGetSupplierPriceStrategy the pixiProductGetSupplierPriceStrategy to set
	 */
	public void setPixiProductGetSupplierPriceStrategy(PixiProductGetSupplierPriceStrategy pixiProductGetSupplierPriceStrategy) 
	{
		this.pixiProductGetSupplierPriceStrategy = pixiProductGetSupplierPriceStrategy;
	}
}