package com.pixi.webservices.controller.order;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pixi.webservices.controller.AbstractPixiController;
import com.pixi.webservices.jaxb.orderstatus.response.ImportOrderStatusResponse;

import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.core.enums.ExportStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.store.BaseStoreModel;

@Controller
public class ConfirmOrderController extends AbstractPixiController
{
	private static final String ACTION = "confirm_order";

	@Resource
	private CustomerAccountService customerAccountService;
	
	/**
	 * PIXI should access this method to confirm that received our order from export process.<br/>
	 * This method just confirms the received order, the code to export is within the class {@link ExportOrdersController}
	 * @param orderId Order id to be marked as exported
	 * @param session
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, produces = "text/xml", params="action=" + ACTION)
	public @ResponseBody ImportOrderStatusResponse handle(@RequestParam(value = "order_id") final String orderId, @RequestParam final String session)
	{
		final BaseStoreModel store = getBaseStoreService().getCurrentBaseStore();
		final OrderModel order = getCustomerAccountService().getOrderForCode(orderId, store);
		
		if(order == null) 
		{
			return getResponse(orderId, session, "Internal Error while updating exported status");
		}
		
		order.setExportStatus(ExportStatus.EXPORTED);
		
		getModelService().save(order);
		
		return getResponse(orderId, session, null);
	}
	
	private ImportOrderStatusResponse getResponse(final String orderId, final String session, final String errorMsg)
	{
		final ImportOrderStatusResponse response = new ImportOrderStatusResponse();
		response.setORDERID(orderId);
		response.setSessionID(session);
		response.setDESCRIPTION(errorMsg);
		response.setSTATUS(StringUtils.isBlank(errorMsg) ? "SUCCESS" : "ERROR");
		
		return response;
	}
	
	public CustomerAccountService getCustomerAccountService() 
	{
		return customerAccountService;
	}
}