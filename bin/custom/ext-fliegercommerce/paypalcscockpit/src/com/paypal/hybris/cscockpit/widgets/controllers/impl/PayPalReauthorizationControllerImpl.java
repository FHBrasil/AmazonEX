/**
 *
 */
package com.paypal.hybris.cscockpit.widgets.controllers.impl;

import de.hybris.platform.cockpit.model.meta.TypedObject;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.cscockpit.widgets.controllers.OrderManagementActionsWidgetController;
import de.hybris.platform.cscockpit.widgets.controllers.impl.AbstractCsWidgetController;

import java.util.Map;

import org.springframework.beans.factory.annotation.Required;
import org.zkoss.zul.Textbox;

import com.paypal.hybris.cscockpit.widgets.controllers.PayPalReauthorizationController;
import com.paypal.hybris.reauthorization.PayPalOrderReauthorizationService;


/**
 * @author Aliaksei_Sery (EPAM Systems)
 *
 */
public class PayPalReauthorizationControllerImpl extends AbstractCsWidgetController implements PayPalReauthorizationController
{
	private OrderManagementActionsWidgetController orderManagementActionsWidgetController;
	private PayPalOrderReauthorizationService orderReauthorizationService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.cockpit.widgets.controllers.impl.AbstractWidgetController#dispatchEvent(java.lang.String,
	 * java.lang.Object, java.util.Map)
	 */
	@Override
	public void dispatchEvent(final String context, final Object source, final Map<String, Object> data)
	{
		getOrderManagementActionsWidgetController().dispatchEvent((String) null, source, data);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.paypal.hybris.cscockpit.widgets.controllers.PayPalReauthorizationController#getOrder()
	 */
	@Override
	public TypedObject getOrder()
	{
		// YTODO Auto-generated method stub
		return getOrderManagementActionsWidgetController().getOrder();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.paypal.hybris.cscockpit.widgets.controllers.PayPalReauthorizationController#createOrderReauthorizationRequest
	 * (de.hybris.platform.cockpit.services.values.ObjectValueContainer)
	 */
	@Override
	public TypedObject createOrderReauthorizationRequest(final Textbox container)
	{
		final TypedObject order = this.getOrder();

		if (order != null && order.getObject() instanceof OrderModel)
		{
			OrderModel orderModel = (OrderModel) order.getObject();
			orderModel = getOrderReauthorizationService().reauthorize(orderModel, container.getText());
			return getCockpitTypeService().wrapItem(orderModel);
		}
		return null;
	}



	protected OrderManagementActionsWidgetController getOrderManagementActionsWidgetController()
	{
		return this.orderManagementActionsWidgetController;
	}

	@Required
	public void setOrderManagementActionsWidgetController(
			final OrderManagementActionsWidgetController orderManagementActionsWidgetController)
	{
		this.orderManagementActionsWidgetController = orderManagementActionsWidgetController;
	}

	public PayPalOrderReauthorizationService getOrderReauthorizationService()
	{
		return orderReauthorizationService;
	}


	public void setOrderReauthorizationService(final PayPalOrderReauthorizationService orderReauthorizationService)
	{
		this.orderReauthorizationService = orderReauthorizationService;
	}

}
