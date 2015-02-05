package com.pixi.webservices.controller.order;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pixi.core.services.PixiOrderService;
import com.pixi.webservices.controller.AbstractPixiSecuredController;
import com.pixi.webservices.jaxb.order.export.Order;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;

@Controller
public class ExportOrdersController extends AbstractPixiSecuredController
{
	private static final String ACTION = "export_order";
	
	@Resource
	private Converter<OrderModel, Order> pixiOrderConverter;
	
	@Resource
	private PixiOrderService pixiOrderService;

	@RequestMapping(method = RequestMethod.GET, produces = "text/xml", params="action=" + ACTION)
	public @ResponseBody Order handle(@RequestParam final String session)
	{
		List<OrderModel> orders = pixiOrderService.findNotExportedOrders();
		
		if(CollectionUtils.isEmpty(orders))
		{
			return null;
		}

		//we export only one order for each PIXI request
		Iterator<OrderModel> iterator = orders.iterator();
		while(iterator.hasNext())
		{
			OrderModel order = iterator.next();

			try 
			{
				LOG.error("Exporting order: " + order.getCode());
				
				//converting the Hybris Order model to a webservice bean
				return pixiOrderConverter.convert(order);
			} 
			catch (Exception e) 
			{
				LOG.error("Error exporting order: " + order.getCode(), e);
			}
		}
		
		return null;
	}
}