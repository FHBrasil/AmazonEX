/**
 *
 */
package com.paypal.hybris.cscockpit.widgets.renderers.impl;

import com.paypal.hybris.constants.PaypalConstants;
import de.hybris.platform.cockpit.widgets.Widget;
import de.hybris.platform.cockpit.widgets.models.impl.DefaultItemWidgetModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.cscockpit.widgets.controllers.OrderManagementActionsWidgetController;
import de.hybris.platform.cscockpit.widgets.renderers.impl.OrderManagementActionsWidgetRenderer;

import org.zkoss.zk.ui.api.HtmlBasedComponent;
import org.zkoss.zul.Div;

import com.paypal.hybris.cscockpit.widgets.controllers.PayPalOrderManagementActionsWidgetController;


/**
 * @author Aliaksei_Sery (EPAM Systems)
 *
 */
public class PayPalOrderManagementActionsWidgetRenderer extends OrderManagementActionsWidgetRenderer
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.cscockpit.widgets.renderers.impl.OrderManagementActionsWidgetRenderer#createContentInternal
	 * (de.hybris.platform.cockpit.widgets.Widget, org.zkoss.zk.ui.api.HtmlBasedComponent)
	 */
	@Override
	protected HtmlBasedComponent createContentInternal(
			final Widget<DefaultItemWidgetModel, OrderManagementActionsWidgetController> widget,
			final HtmlBasedComponent rootContainer)
	{
		final Div component = new Div();
		component.setSclass("orderManagementActionsWidget");

		createButton(widget, component, "cancelWholeOrder", "csFullOrderCancellationWidgetConfig", "csFullOrderCancel-Popup",
				"csFullCancelPopup", "popup.fullCancellationRequestCreate", !widget.getWidgetController().isFullCancelPossible());
		createButton(widget, component, "cancelPartialOrder", "csPartialOrderCancellationWidgetConfig",
				"csPartialOrderCancellationWidgetConfig-Popup", "csPartialCancelPopup", "popup.partialCancellationRequestCreate",
				!isPartialCancelPossible(widget));
		createButton(widget, component, "refundOrder", "csRefundRequestCreateWidgetConfig", "csRefundRequestCreateWidget-Popup",
				"csReturnRequestCreateWidget", "popup.refundRequestCreate", !widget.getWidgetController().isRefundPossible());
		createButton(widget, component, "replaceOrder", "csReplacementRequestCreateWidgetConfig",
				"csReplacementRequestCreateWidget-Popup", "csReturnRequestCreateWidget", "popup.replacementRequestCreate", !widget
						.getWidgetController().isReplacePossible());

		// PayPal extension
		if (widget.getWidgetController() instanceof PayPalOrderManagementActionsWidgetController)
		{
			createButton(widget, component, "reauthorizeOrder", "csReauthorizeRequestCreateWidgetConfig",
					"csReauthorizeRequestCreateWidget-Popup", "csReauthorizeRequestCreateWidget", "popup.reauthorizeRequestCreate",
					!((PayPalOrderManagementActionsWidgetController) widget.getWidgetController()).isReauthorizationPossible());
		}
		else
		{
			createButton(widget, component, "reauthorizeOrder", "csReauthorizeRequestCreateWidgetConfig",
					"csReauthorizeRequestCreateWidget-Popup", "csReauthorizeRequestCreateWidget", "popup.reauthorizeRequestCreate",
					true);
		}
		return component;
	}

	private boolean isPartialCancelPossible(Widget<DefaultItemWidgetModel, OrderManagementActionsWidgetController> widget){
		OrderManagementActionsWidgetController widgetController = widget.getWidgetController();
		if(widgetController.getOrder().getObject() instanceof OrderModel){
			OrderModel orderModel = (OrderModel)widgetController.getOrder().getObject();
			if(orderModel != null && orderModel.getPaymentInfo() != null){
				String paymentCode = orderModel.getPaymentInfo().getCode();
				return  !PaypalConstants.PAYPAL_PAYMENT_INFO_CODE.equals(paymentCode) &&
					    !PaypalConstants.PAYPAL_CREDIT_PAYMENT_INFO_CODE.equals(paymentCode) &&
					   	widgetController.isPartialCancelPossible();
			}
		}
		return widget.getWidgetController().isPartialCancelPossible();
	}
}
