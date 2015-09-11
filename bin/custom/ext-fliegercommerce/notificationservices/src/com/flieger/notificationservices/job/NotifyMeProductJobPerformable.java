/**
 * 
 */
package com.flieger.notificationservices.job;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.commerceservices.url.UrlResolver;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.ordersplitting.WarehouseService;
import de.hybris.platform.acceleratorservices.urlresolver.SiteBaseUrlResolutionService;
import de.hybris.platform.basecommerce.enums.StockLevelStatus;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.stock.StockService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.util.Config;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import br.flieger.exacttarget.events.NotifyMeProductEvent;

import com.flieger.notificationservices.model.NotifymeModel;
import com.flieger.notificationservices.model.job.NotifyMeProductJobModel;
import com.flieger.notificationservices.services.NotifymeService;


/**
 * @author Vinicius de Souza
 * 
 */
public class NotifyMeProductJobPerformable extends AbstractJobPerformable<NotifyMeProductJobModel>
{
	private static final String STOCK_DEFAULT = "notificationservices.default-stock";

	@Resource
	private NotifymeService notifymeService;

	@Resource
	private ProductService productService;

	@Resource
	private StockService stockService;
	
	@Resource
	private CatalogVersionService catalogVersionService;
	
	@Resource
	private WarehouseService warehouseService;
	
	@Resource
	private CommonI18NService commonI18NService;
	
	@Resource
	private NotifyMeProductEvent notifyMeProductEvent;
	
	@Resource
	private EventService eventService;
	
	@Resource
	private UrlResolver<ProductModel> productModelUrlResolver;
	
	@Resource
	private SiteBaseUrlResolutionService siteBaseUrlResolutionService;
	
	@Resource
	private ModelService modelService;

	private final Logger LOG = Logger.getLogger(NotifyMeProductJobPerformable.class.getName());

	@Override
	public PerformResult perform(final NotifyMeProductJobModel arg)
	{
		LOG.info("CronJob Notifyme Product has started...");
		try
		{
			final Set<NotifymeModel> setModel = notifymeService.getAll(false);
			
			final Set<NotifymeModel> notifications = hasStock(verifyExpired(setModel));
			
			LOG.info("QTD notifyme: "+notifications.size());
			
			final Map<String, Set<NotifymeModel>> mapNotifications = splitForBaseStore(notifications);
			
			sendNotifications(mapNotifications);
		}
		catch (final Exception e)
		{
			LOG.error("Erro Notifyme...", e);
			return new PerformResult(CronJobResult.ERROR, CronJobStatus.FINISHED);
		}
		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}

	/**
	 * @param mapNotifications
	 */
	private void sendNotifications(Map<String, Set<NotifymeModel>> mapNotifications)
	{
		for (String baseStore : mapNotifications.keySet())
		{
			notifyMeProductEvent.setBaseStore(baseStore);
			notifyMeProductEvent.setNotifications(mapNotifications.get(baseStore));
			eventService.publishEvent(notifyMeProductEvent);
			
			modelService.saveAll(notifyMeProductEvent.getNotifications());
		}		
	}

	/**
	 * @param notifications
	 * @return Set of NotifymeModel that have in stock.
	 */
	private Set<NotifymeModel> hasStock(Set<NotifymeModel> notifications)
	{
		final Set<NotifymeModel> result = new HashSet<>();
		
		for (NotifymeModel notifymeModel : notifications)
		{
			if(hasStock(notifymeModel.getProduct(), getHeringWarehouse()))
			{
				result.add(notifymeModel);
			}
		}
		
		return result;
	}

	/**
	 * @param notifications
	 * @return Map of BaseStore
	 */
	private Map<String, Set<NotifymeModel>> splitForBaseStore(Set<NotifymeModel> notifications)
	{
		final Map<String, Set<NotifymeModel>> map = new HashMap<>();
		
		for (NotifymeModel notifymeModel : notifications)
		{
			final String baseStore = notifymeModel.getBaseStore().getUid();
			if(!map.containsKey(baseStore))
			{
				final Set<NotifymeModel> set = new HashSet<>();
				map.put(baseStore, set);
			}
			
			map.get(baseStore).add(notifymeModel);
		}
		
		return map;
	}

	/**
	 * @param setModel
	 * @return Not expired
	 * @throws Exception 
	 */
	private Set<NotifymeModel> verifyExpired(Set<NotifymeModel> setModel) throws Exception
	{
		final Set<NotifymeModel> result = new HashSet<NotifymeModel>();
		
		for (NotifymeModel notify : setModel)
		{
			if(isExpired(notify))
			{
				notify.setExpired(new Date(System.currentTimeMillis()));
				modelService.save(notify);
			}
			else
			{
				result.add(notify);
			}
		}
		LOG.info(result.size()+" notificações a serem verificadas.");
		return result;
	}

	/**
	 * @param notify
	 * @return If the notification has expired.
	 */
	private boolean isExpired(NotifymeModel notify)
	{
		if(notify.getDays() == null)
			return true;
		
		final long days = Long.parseLong(notify.getDays()+"") * 86400000l;
		Date dateExpired = new Date(notify.getCreated().getTime() + days);
		Date dateCurrent = new Date(System.currentTimeMillis());
		
		return dateCurrent.after(dateExpired);
	}
	
	private boolean hasStock(ProductModel product, final WarehouseModel warehouse) 
	{
		Assert.notNull(product, "product is null");
		Assert.notNull(warehouse, "warehouse is null");
		
		final StockLevelStatus status = stockService.getProductStatus(product, warehouse);
		
		final boolean outOfStock = StockLevelStatus.OUTOFSTOCK.equals(status);
		
		return !outOfStock;
	}
	
	private WarehouseModel getHeringWarehouse()
	{
		return warehouseService.getWarehouseForCode(Config.getParameter(STOCK_DEFAULT));
	}
	
	protected String getUrl(final ProductModel product)
	{
		final String productURL = productModelUrlResolver.resolve(product);
		BaseStoreModel store = product.getCatalogVersion().getCatalog()
				.getBaseStores().iterator().next();
		BaseSiteModel site = store.getCmsSites().iterator().next();
		final String urlForSite = siteBaseUrlResolutionService.getWebsiteUrlForSite(site, false, productURL);
		return urlForSite;
	}
}