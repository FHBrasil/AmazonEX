/**
 * 
 */
package com.flieger.notificationservices.services.impl;

import de.hybris.platform.basecommerce.enums.StockLevelStatus;
import de.hybris.platform.category.CategoryService;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.classification.ClassificationService;
import de.hybris.platform.classification.features.Feature;
import de.hybris.platform.classification.features.FeatureList;
import de.hybris.platform.classification.features.FeatureValue;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.ordersplitting.WarehouseService;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.stock.StockService;
import de.hybris.platform.util.Config;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import br.flieger.exacttarget.events.NotifyMeSimilarProductEvent;

import com.flieger.notificationservices.data.NotifymeData;
import com.flieger.notificationservices.services.NotifyMeSimilarProductService;

/**
 * @author Vinicius de Souza
 *
 */
public class DefaultNotifyMeSimilarProductService extends AbstractNotifyMe implements NotifyMeSimilarProductService
{
	@Resource
	private ProductService productService;
	
	@Resource
	private CategoryService categoryService;
	
	@Resource
	private ClassificationService classificationService;
	
	@Resource
	private StockService stockService;
	
	@Resource
	private WarehouseService warehouseService;
	
	@Resource
	private NotifyMeSimilarProductEvent notifyMeSimilarProductEvent;
	
	@Resource
	private EventService eventService;
	
	private final String COLOR_CLASSIFICATION = "Descrição cor";
	
	private final Logger LOG = Logger.getLogger(DefaultNotifyMeSimilarProductService.class.getName());

	@Override
	public List<ProductModel> getSimilarProduct(ProductModel product) throws Exception
	{
		
		for (CategoryModel category : product.getSupercategories())
		{
			if(category.getDescription() != null)
			{
				return getSimilarProduct(product, category);
			}
		}
		
		return new LinkedList<>();
	}	
	
	@Override
	public List<ProductModel> getSimilarProduct(ProductModel product, CategoryModel superCategoryModel) throws Exception
	{
		List<ProductModel> result = new LinkedList<>();
		for (CategoryModel category : product.getSupercategories())
		{
			if(category.equals(superCategoryModel))
			{
				for (ProductModel similarProduct : productService.getProductsForCategory(category))
				{
					if(isSimilarProduct(product, similarProduct) && hasStock(similarProduct))
					{
						result.add(similarProduct);
					}
				}
			}
		}
		
		return result;
	}
	
	private boolean hasStock(ProductModel product) 
	{
		Assert.notNull(product, "product is null");
		Assert.notNull(getHeringWarehouse(), "warehouse is null");
		
		final StockLevelStatus status = stockService.getProductStatus(product, getHeringWarehouse());
		
		final boolean outOfStock = StockLevelStatus.OUTOFSTOCK.equals(status);
		
		return !outOfStock;
	}
	
	private WarehouseModel getHeringWarehouse()
	{
		return warehouseService.getWarehouseForCode(Config.getParameter("notificationservices.default-stock"));
	}

	@Override
	public List<ProductModel> getSimilarProduct(String productCode, String superCategoryCode) throws Exception
	{
		final ProductModel product = productService.getProductForCode(productCode);
		final CategoryModel category = categoryService.getCategoryForCode(superCategoryCode);
		
		
		return getSimilarProduct(product, category);
	}

	@Override
	public boolean isSimilarProduct(ProductModel product, ProductModel similar) throws Exception
	{
		try
		{
   		//Incluir outras comparações aqui.
   		return product.getCode().compareTo(similar.getCode()) != 0 && isColor(product, similar);
		}
		catch(IllegalArgumentException e)
		{
			LOG.error("Product: "+product.getCode()+", Similar: "+similar.getCode(), e);
		}
		return false;
	}

	/**
	 * @param product
	 * @param similar
	 * @return
	 */
	private boolean isColor(ProductModel product, ProductModel similar)
	{		
		return getFeatureValue(product, COLOR_CLASSIFICATION).compareTo(getFeatureValue(similar, COLOR_CLASSIFICATION)) == 0;
	}

	/**
	 * @param product
	 * @param cOLOR_CLASSIFICATION2
	 * @return
	 */
	private String getFeatureValue(ProductModel product, final String featureCode)
	{
		final FeatureList features = classificationService.getFeatures(product);
		
		for (Feature feature : features)
		{
			//LOG.info(feature.getName()+"::"+feature.getCode());
			if(feature.getName().compareToIgnoreCase(featureCode) == 0)
			{
				for (FeatureValue value : feature.getValues())
				{
					return (String) value.getValue();
				}
			}
			//LOG.info(feature.getName()+"::"+feature.getCode()+"-->"+feature.getValue().getDescription()+"-->"+feature.getValue().getValue());
		}
		return "";
	}

	@Override
	public List<ProductModel> getSimilarProduct(String productCode) throws Exception
	{
		return getSimilarProduct(productService.getProductForCode(productCode));
	}

	@Override
	public void notifyMe(List<ProductModel> similars, NotifymeData notifymeData, final String siteUrl)
	{
		if(CollectionUtils.isNotEmpty(similars))
		{
			notifyMeSimilarProductEvent.setNotifymeData(notifymeData);
			notifyMeSimilarProductEvent.setSimilars(similars);
			notifyMeSimilarProductEvent.setSiteUrl(siteUrl);
			notifyMeSimilarProductEvent.setProduct(productService.getProductForCode(notifymeData.getCode()));
						
			eventService.publishEvent(notifyMeSimilarProductEvent);
		}
	}
	
	/**
	 * @return the productService
	 */
	protected ProductService getProductService()
	{
		return productService;
	}
}