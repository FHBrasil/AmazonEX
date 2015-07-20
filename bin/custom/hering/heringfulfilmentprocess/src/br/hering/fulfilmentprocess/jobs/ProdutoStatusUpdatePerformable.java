package br.hering.fulfilmentprocess.jobs;

import static de.hybris.platform.catalog.enums.ArticleApprovalStatus.APPROVED;
import static de.hybris.platform.catalog.enums.ArticleApprovalStatus.UNAPPROVED;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.log4j.Logger;

import br.hering.core.model.HeringSizeVariantProductModel;
import br.hering.fulfilmentprocess.model.jobs.ProductStatusUpdateJobModel;
import de.hybris.platform.basecommerce.enums.StockLevelStatus;
import de.hybris.platform.catalog.enums.ArticleApprovalStatus;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.ordersplitting.WarehouseService;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.stock.StockService;
import de.hybris.platform.variants.model.VariantProductModel;

public class ProdutoStatusUpdatePerformable extends AbstractJobPerformable<ProductStatusUpdateJobModel> 
{
	private Logger LOG = Logger.getLogger(ProdutoStatusUpdatePerformable.class);
	
	@Resource
	private WarehouseService warehouseService;
	
	@Resource
	private StockService stockService;
	
	@Override
	public PerformResult perform(ProductStatusUpdateJobModel arg0) 
	{
		disableProducts();
		enableProducts();
		updateBaseProductsStatus();

//		setAllToApproved();
	
		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}
	
	private void disableProducts()
	{
		//filter by approved and set as unapproved
		verifyApprovalStatusConsistency(APPROVED);
	}
	
	private void enableProducts()
	{
		//filter by unapproved and set as approved
		verifyApprovalStatusConsistency(UNAPPROVED);
	}

	private void verifyApprovalStatusConsistency(ArticleApprovalStatus currentProductStatus)
	{
		LOG.info("Product Status Update to " + currentProductStatus + " - INICIO");
		
		final WarehouseModel warehouse = getHeringWarehouse();
		
		String sql = "select {p.pk} from {HeringSizeVariantProduct as p} WHERE {p:approvalStatus} = ?status";
		
		Map<String, ArticleApprovalStatus> params = Collections.singletonMap("status", currentProductStatus);
		
		List<HeringSizeVariantProductModel> list = flexibleSearchService.<HeringSizeVariantProductModel>search(sql, params).getResult();

		if(CollectionUtils.isEmpty(list))
		{
			LOG.info("nothing to process");
			return;
		}
		
		Iterator<HeringSizeVariantProductModel> iterator = list.iterator();
		
		while(iterator.hasNext())
		{
			HeringSizeVariantProductModel product = iterator.next();
			
			boolean hasStock = hasStock(product, warehouse);
			
			if(currentProductStatus == APPROVED && !hasStock)
			{
				setApprovalStatus(product, UNAPPROVED);
			}
			
			if(currentProductStatus == UNAPPROVED && hasStock)
			{
				setApprovalStatus(product, APPROVED);
			}
		}
		
		LOG.info("Product Status Update - FIM");
	}
	
	private void updateBaseProductsStatus()
	{
		LOG.info("Base Product Status Update to Unapproved- INICIO");
		
		final WarehouseModel warehouse = getHeringWarehouse();
		
		final String sql = "SELECT {p:PK} FROM {HeringProduct AS p} WHERE {p:varianttype} IS NOT NULL AND {p:approvalStatus} = ?status";
		Map<String, ArticleApprovalStatus> params = Collections.singletonMap("status", APPROVED);
		
		final List<ProductModel> list = flexibleSearchService.<ProductModel>search(sql, params).getResult();

		if(CollectionUtils.isEmpty(list))
		{
			LOG.info("nothing to process");
			return;
		}
		
		Iterator<ProductModel> iterator = list.iterator();
		
		while(iterator.hasNext())
		{
			disableBaseProduct(iterator.next());
		}
		
		LOG.info("Base Product Status Update - FIM");
	}
	
	private void disableBaseProduct(final ProductModel base)
	{
		boolean hasAtLeastOneApproved = false;
		for(final VariantProductModel variant : base.getVariants())
		{
			//If I have children, then we need to check them
			if(CollectionUtils.isNotEmpty(variant.getVariants()))
			{
				disableBaseProduct(variant);
			}
			
			hasAtLeastOneApproved |= isApproved(variant);
		}
		
		if(!hasAtLeastOneApproved) 
		{
			setApprovalStatus(base, UNAPPROVED);
		}
	}
	
	private void setApprovalStatus(final ProductModel product, final ArticleApprovalStatus approvalStatus) 
	{
		if(product == null)
		{
			LOG.error("product is null, skipping");
			return;
		}
		
		if(approvalStatus == null)
		{
			LOG.error("approvalStatus is null, skipping");
			return;
		}
		
		if(ObjectUtils.notEqual(product.getApprovalStatus(), approvalStatus))
		{
			product.setApprovalStatus(approvalStatus);
			modelService.save(product);
			modelService.refresh(product);

			LOG.info(product.getCode() + " " + product.getCatalogVersion() + " changed to: " + approvalStatus);
		}
		
		//if this variant is approved, then we need to approve its base product too
		if(APPROVED == approvalStatus && product instanceof VariantProductModel)
		{
			ProductModel baseProduct = ((VariantProductModel) product).getBaseProduct();
			setApprovalStatus(baseProduct, approvalStatus);
		}
	}
	
	private void enableAllProducts()
	{
		LOG.info("Product Status Update - INICIO");
		
		final WarehouseModel warehouse = getHeringWarehouse();
		
		String sql = "select {p.pk} from {Product as p} WHERE {p:approvalStatus} = ?unapproved";
		
		Map<String, ArticleApprovalStatus> params = Collections.singletonMap("unapproved", UNAPPROVED);

		List<HeringSizeVariantProductModel> list = flexibleSearchService.<HeringSizeVariantProductModel>search(sql, params).getResult();

		if(CollectionUtils.isEmpty(list))
		{
			return;
		}
		
		Iterator<HeringSizeVariantProductModel> iterator = list.iterator();
		
		while(iterator.hasNext())
		{
			setApprovalStatus(iterator.next(), APPROVED);
		}
		
		LOG.info("Product Status Update - FIM");
	}
	
	private boolean isApproved(ProductModel product)
	{
		if(product == null)
		{
			return false;
		}
		
		return APPROVED == product.getApprovalStatus();
	}
	
	private boolean hasStock(ProductModel product, final WarehouseModel warehouse) 
	{
		final StockLevelStatus status = stockService.getProductStatus(product, warehouse);
		
		final boolean outOfStock = StockLevelStatus.OUTOFSTOCK.equals(status);
		
		return !outOfStock;
	}
	
	private WarehouseModel getHeringWarehouse()
	{
		return warehouseService.getWarehouseForCode("hering_estoque_default");
	}
}