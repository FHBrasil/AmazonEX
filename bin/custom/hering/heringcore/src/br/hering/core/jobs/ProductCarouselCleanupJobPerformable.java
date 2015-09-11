/**
 * 
 */
package br.hering.core.jobs;

import de.hybris.platform.basecommerce.enums.StockLevelStatus;
import de.hybris.platform.cms2lib.model.components.ProductCarouselComponentModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.ordersplitting.WarehouseService;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.stock.StockService;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.log4j.Logger;

import br.hering.core.model.jobs.ProductCarouselCleanupJobModel;

/**
 * @author franthescollymaneira
 *
 */
public class ProductCarouselCleanupJobPerformable extends AbstractJobPerformable<ProductCarouselCleanupJobModel>
{
	@Resource
	private WarehouseService warehouseService;
	
	@Resource
	private StockService stockService;

	private Logger LOG = Logger.getLogger(ProductCarouselCleanupJobPerformable.class);

	/* (non-Javadoc)
	 * @see de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable#perform(de.hybris.platform.cronjob.model.CronJobModel)
	 */
	@Override
	public PerformResult perform(final ProductCarouselCleanupJobModel job)
	{
		LOG.info("Iniciando cronjob");

		sessionService.setAttribute("dont.change.existing.links", Boolean.FALSE);

		List<ProductCarouselComponentModel> carouselList = findProductCarouselComponents();
		
		for(ProductCarouselComponentModel carousel : carouselList)
		{
			final List<ProductModel> aProducts = filterAvailableProducts(carousel);
			
			if(aProducts.size() != carousel.getProducts().size())
			{
				int total = carousel.getProducts().size() - aProducts.size();
				LOG.info(String.format("Removendo %s produtos do carrossel %s", String.valueOf(total), carousel.getUid()));
				carousel.setProducts(aProducts);
				modelService.save(carousel);
			}
		}
		
		LOG.info("fim da execução");
		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}
	
	/**
	 * 
	 * @return
	 */
	private List<ProductCarouselComponentModel> findProductCarouselComponents()
	{
		final String sql = "SELECT {PK} FROM {ProductCarouselComponent} WHERE {visible} = 1";
		return flexibleSearchService.<ProductCarouselComponentModel>search(sql).getResult();
	}
	
	private List<ProductModel> filterAvailableProducts(final ProductCarouselComponentModel carousel)
	{
		final List<ProductModel> products = new ArrayList<ProductModel>(carousel.getProducts());
		
		CollectionUtils.filter(products, new Predicate()
		{
			@Override
			public boolean evaluate(Object obj)
			{
				return !isOutOfStock((ProductModel) obj);
			}
		});
		
		return products;
	}
	
	public boolean isOutOfStock(ProductModel product) 
	{
		final StockLevelStatus status = stockService.getProductStatus(product, getDefaultWarehouse());
		
		return StockLevelStatus.OUTOFSTOCK.equals(status);
	}
	
	private WarehouseModel getDefaultWarehouse()
	{
		return warehouseService.getWarehouseForCode("hering_estoque_default");
	}
}