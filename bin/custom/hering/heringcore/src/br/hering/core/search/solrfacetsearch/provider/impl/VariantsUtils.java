/**
 * 
 */
package br.hering.core.search.solrfacetsearch.provider.impl;

import de.hybris.platform.basecommerce.enums.StockLevelStatus;
import de.hybris.platform.catalog.enums.ArticleApprovalStatus;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.ordersplitting.WarehouseService;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.stock.StockService;
import de.hybris.platform.variants.model.VariantProductModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import br.hering.core.model.HeringProductModel;
import br.hering.core.model.HeringSizeVariantProductModel;
import br.hering.core.model.HeringStyleVariantProductModel;
import bsh.ParseException;

import java.util.Calendar;

/**
 * @author franthescollymaneira
 *
 */
public class VariantsUtils
{
	protected final Logger LOG = Logger.getLogger(this.getClass());
	
	@Resource
	private WarehouseService warehouseService;
	
	@Resource
	private StockService stockService;
	
	public HeringProductModel getAvailableBaseProduct(Object model)
	{
		if (model instanceof HeringSizeVariantProductModel)
		{
			final HeringSizeVariantProductModel sizeModel = (HeringSizeVariantProductModel) model;
			model = sizeModel.getBaseProduct();
		}

		if (model instanceof HeringStyleVariantProductModel)
		{
			final HeringStyleVariantProductModel styleModel = (HeringStyleVariantProductModel) model;
			model = styleModel.getBaseProduct();
		}

		if (model instanceof HeringProductModel)
		{
			HeringProductModel base = (HeringProductModel) model;
			
			if(getFirstAvailableStyleVariant(base) != null)
			{
				return base;
			}
		}

		return null;
	}
	
	public List<HeringStyleVariantProductModel> getAvailableStyleVariants(HeringProductModel base)
	{
		if(base == null || CollectionUtils.isEmpty(base.getVariants()))
		{
			return Collections.emptyList();
		}
		
		final List<HeringStyleVariantProductModel> allStyle = new ArrayList<HeringStyleVariantProductModel>();
		
		for(VariantProductModel style: base.getVariants())
		{
			if(firstAvailableChild(style.getVariants()) != null)
			{
				allStyle.add((HeringStyleVariantProductModel) style);
			}
		}
		
		return allStyle;
	}
	
	public List<HeringSizeVariantProductModel> getAvailableSizeVariants(HeringProductModel base)
	{
		if (base == null)
		{
			return Collections.emptyList();
		}
		
		List<HeringStyleVariantProductModel> styles = getAvailableStyleVariants(base);
		
		final List<HeringSizeVariantProductModel> allSizes = new ArrayList<HeringSizeVariantProductModel>();
		
		for (HeringStyleVariantProductModel style : styles)
		{
			allSizes.addAll(this.getAvailableSizeVariants(style));
		}
		
		return allSizes;
	}
	
	
	public List<HeringSizeVariantProductModel> getAvailableSizeVariants(final HeringStyleVariantProductModel style)
	{		
		final List<HeringSizeVariantProductModel> allSizes = new ArrayList<HeringSizeVariantProductModel>();

		if (style == null || CollectionUtils.isEmpty(style.getVariants()))
		{
			return Collections.emptyList();
		}

		for (VariantProductModel size : style.getVariants())
		{
			if (isAvailable(size))
			{
				allSizes.add((HeringSizeVariantProductModel) size);
			}
		}

		return allSizes;
	}
	
	public List<VariantProductModel> getAvailableChildVariants(ProductModel product)
	{
		List<VariantProductModel> children = new ArrayList<>();
		for(VariantProductModel child : product.getVariants()) {
			if(isAvailable(child) || (firstAvailableChild(child.getVariants()) != null)) {
				children.add(child);
			}
		}
		return children;
	}
	
	public HeringStyleVariantProductModel getFirstAvailableStyleVariant(final HeringProductModel base)
	{
		if(base == null)
		{
			return null;
		}
		
		return (HeringStyleVariantProductModel) firstAvailableChild(base.getVariants());
	}
	
	public HeringSizeVariantProductModel getFirstAvailableSizeVariant(HeringProductModel base)
	{
		HeringStyleVariantProductModel style = getFirstAvailableStyleVariant(base);
		
		if(style == null)
		{
			return null;
		}
		
		return (HeringSizeVariantProductModel) firstAvailableChild(style.getVariants());
	}
	
	public <T extends VariantProductModel> T firstAvailableChild(Collection<T> childList)
	{
		if(CollectionUtils.isEmpty(childList))
		{
			return null;
		}
		
		final Iterator<T> iterator = childList.iterator();
		
		while (iterator.hasNext())
		{
			T variant = iterator.next();
			
			VariantProductModel firstAvailableChild = firstAvailableChild(variant.getVariants());
			if(firstAvailableChild != null)
			{
				return variant;
			}
			
			if(isAvailable(variant))
			{
				return variant;
			}
		}
		
		return null;
	}
	
	public boolean isAvailable(ProductModel product) 
	{
		WarehouseModel defaultWarehouse = getDefaultWarehouse();
		
		boolean hasStock = hasStock(product, defaultWarehouse);
		
		boolean hasPrice = hasPrice(product);
		
		ArticleApprovalStatus approvalStatus = product.getApprovalStatus();
		
		boolean isApproved = approvalStatus != null && approvalStatus == ArticleApprovalStatus.APPROVED;
		
		boolean isValidDateRange = isValidDateRange(product.getOnlineDate(), product.getOfflineDate()); 
		
		//LOG.info("###VariantsUtils###"+product.getCode()+"hasStock="+hasStock+"hasPrice="+hasPrice+"isApproved="+isApproved+"isValidDateRange="+isValidDateRange);
		
		return hasStock && hasPrice && isApproved && isValidDateRange;
	}
	

	/**
	 * @param onlineDate
	 * @param offlineDate
	 * @return
	 */
	private boolean isValidDateRange(Date onlineDate, Date offlineDate)
	{//spj
		boolean boolOnlineDate = false;
		boolean boolOfflineDate = false;
		Calendar calendarAtualDate = Calendar.getInstance();
		
   		if(onlineDate != null)
   		{
   			Calendar calendarOnlineDate = (DateUtils.toCalendar(onlineDate));
   			if(calendarAtualDate.compareTo(calendarOnlineDate) >= 0 )
   			{	
   				boolOnlineDate = true; // Actual Date is same or newest then product online date. 
   			}
   		}
   		else
   		{
   			boolOnlineDate = true; //No online date restriction.
   		}
   		
   		if(offlineDate != null)
   		{		
      		Calendar calendarOfflineDate = (DateUtils.toCalendar(offlineDate));
      		if(calendarAtualDate.compareTo(calendarOfflineDate) <= 0)
      			{
      				boolOfflineDate = true; // Actual date is same or oldest then product offline date.
      			}
   		}
   		else
   		{
   			boolOfflineDate = true; //No offline date restriction.
   		}
		
		
		return boolOnlineDate && boolOfflineDate;
	}

	/**
	 * @param product
	 * @return
	 */
	private boolean hasPrice(ProductModel product)
	{
		Assert.notNull(product, "product is null");
		
		Collection<PriceRowModel> priceList = product.getEurope1Prices();
		
		boolean hasPriceList = CollectionUtils.isNotEmpty(priceList);
		
		if(!hasPriceList) 
		{
			return false;
		}
		
		Double price = priceList.iterator().next().getPrice();
		
		return price != null && price.doubleValue() > 0;
	}

	public boolean hasStock(ProductModel product, final WarehouseModel warehouse) 
	{
		Assert.notNull(product, "product is null");
		Assert.notNull(warehouse, "warehouse is null");
		
		final StockLevelStatus status = stockService.getProductStatus(product, warehouse);
		
		final boolean outOfStock = StockLevelStatus.OUTOFSTOCK.equals(status);
		
		return !outOfStock;
	}
	
	private WarehouseModel getDefaultWarehouse()
	{
		return warehouseService.getWarehouseForCode("hering_estoque_default");
	}
}