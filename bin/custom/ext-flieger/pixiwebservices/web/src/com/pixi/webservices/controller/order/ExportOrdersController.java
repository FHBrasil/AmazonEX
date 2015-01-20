package com.pixi.webservices.controller.order;

import java.util.List;

import javax.annotation.Resource;

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
		
		//TODO falta tratamento de exception
		
		OrderModel orderToExport = orders.iterator().next();
		Order wsOrder = pixiOrderConverter.convert(orderToExport);
	
		return wsOrder;
	}
}