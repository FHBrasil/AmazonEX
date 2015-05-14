/**
 * 
 */
package de.kpfamily.services.strategies.pixi.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.util.Assert;

import com.pixi.core.strategies.PixiOrderGetTransportRemarksStrategy;

import de.hybris.platform.core.model.order.OrderModel;
import de.kpfamily.services.logistics.ExpressDeliveryService;

/**
 * @author franthescollymaneira
 *
 */
public class DefaultPixiOrderGetTransportRemarksStrategy implements PixiOrderGetTransportRemarksStrategy  
{
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
	
	@Resource
	private ExpressDeliveryService expressDeliveryService;
	
	/* (non-Javadoc)
	 * @see com.pixi.core.strategies.PixiOrderGetTransportRemarksStrategy#getTransportRemarks(de.hybris.platform.core.model.order.OrderModel)
	 */
	@Override
	public String getTransportRemarks(final OrderModel order) 
	{
		Assert.notNull(order);
		
		if(getExpressDeliveryService().isApplicableForExpressDelivery(order)) 
		{
			String formatedDate = DATE_FORMAT.format(getExpressDeliverySuitableDate(order));
			
			return "DHL;;;|||" + formatedDate;
		}
		
		return null;
	}
	
	private Date getExpressDeliverySuitableDate(final OrderModel order) 
	{
		final Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 15);
		calendar.set(Calendar.MINUTE, 00);
		calendar.set(Calendar.SECOND, 00);
		calendar.set(Calendar.MILLISECOND, 00);
		
		if(order.getDate().after(calendar.getTime())) 
		{
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}
		
		return calendar.getTime();
	}

	/**
	 * @return the expressDeliveryService
	 */
	public ExpressDeliveryService getExpressDeliveryService() 
	{
		return expressDeliveryService;
	}

	/**
	 * @param expressDeliveryService the expressDeliveryService to set
	 */
	public void setExpressDeliveryService(ExpressDeliveryService expressDeliveryService) 
	{
		this.expressDeliveryService = expressDeliveryService;
	}
}