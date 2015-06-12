package com.paypal.hybris.cscockpit.widgets.controllers.impl;

import de.hybris.platform.cockpit.model.meta.TypedObject;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.cscockpit.widgets.controllers.impl.DefaultOrderManagementActionsWidgetController;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.paypal.hybris.cscockpit.widgets.controllers.PayPalOrderManagementActionsWidgetController;
import com.paypal.hybris.reauthorization.PayPalOrderReauthorizationService;


/**
 * @author Dzmitry_Rasolka (EPAM Systems)
 */
public class PayPalOrderManagementActionsWidgetControllerImpl extends DefaultOrderManagementActionsWidgetController implements
		PayPalOrderManagementActionsWidgetController
{

	private static final Logger LOG = Logger.getLogger(PayPalOrderManagementActionsWidgetControllerImpl.class);

	private PayPalOrderReauthorizationService payPalOrderReauthorizationService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.paypal.hybris.cscockpit.widgets.controllers.PayPalOrderManagementActionsWidgetController#isReauthorizationPossible
	 * ()
	 */
	@Override
	public boolean isReauthorizationPossible()
	{
		final TypedObject order = getOrder();
		if ((order != null) && (order.getObject() instanceof OrderModel)
				&& (StringUtils.isBlank(((OrderModel) order.getObject()).getVersionID())))
		{
			try
			{
				final OrderModel orderModel = (OrderModel) order.getObject();
				return getPayPalOrderReauthorizationService().isReauthorizationPossible(orderModel);
			}
			catch (final Exception e)
			{
				LOG.error("failed to work out if reauthorization of order is possible", e);
			}
		}
		return false;
	}

	public PayPalOrderReauthorizationService getPayPalOrderReauthorizationService()
	{
		return payPalOrderReauthorizationService;
	}

	public void setPayPalOrderReauthorizationService(final PayPalOrderReauthorizationService payPalOrderReauthorizationService)
	{
		this.payPalOrderReauthorizationService = payPalOrderReauthorizationService;
	}

}
