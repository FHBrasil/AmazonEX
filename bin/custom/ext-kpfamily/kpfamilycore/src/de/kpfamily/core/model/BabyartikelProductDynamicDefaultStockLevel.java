/**
 *
 */
package de.kpfamily.core.model;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.servicelayer.model.attribute.AbstractDynamicAttributeHandler;

import java.util.Set;

import org.apache.commons.collections.CollectionUtils;


/**
 * @author franthescollymaneira
 *
 */
public class BabyartikelProductDynamicDefaultStockLevel extends AbstractDynamicAttributeHandler<StockLevelModel, ProductModel>
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.servicelayer.model.attribute.AbstractDynamicAttributeHandler#get(de.hybris.platform.servicelayer
	 * .model.AbstractItemModel)
	 */
	@Override
	public StockLevelModel get(final ProductModel product)
	{
		final Set<StockLevelModel> stockLevels = product.getStockLevels();

		if (CollectionUtils.isEmpty(stockLevels))
		{
			return null;
		}

		final StockLevelModel defaultStockLevel = filterDefaultStockLevel(stockLevels);

		return defaultStockLevel;
	}

	private StockLevelModel filterDefaultStockLevel(final Set<StockLevelModel> stockLevels)
	{
		return stockLevels.iterator().next();
	}
}
