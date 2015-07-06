/**
 *
 */
package com.paypal.hybris.cscockpit.widgets.renderers.impl;

import de.hybris.platform.cockpit.model.meta.TypedObject;
import de.hybris.platform.cockpit.widgets.Widget;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.cscockpit.utils.LabelUtils;
import de.hybris.platform.cscockpit.widgets.renderers.impl.AbstractCreateWidgetRenderer;
import de.hybris.platform.cscockpit.widgets.renderers.utils.PopupWidgetHelper;

import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.zkoss.zk.ui.api.HtmlBasedComponent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import com.paypal.hybris.cscockpit.widgets.controllers.PayPalReauthorizationController;


/**
 * @author Aliaksei_Sery (EPAM Systems)
 *
 */
public class ReauthorizeRequestCreateWidgetRenderer extends AbstractCreateWidgetRenderer
{

	private static final Logger LOG = Logger.getLogger(ReauthorizeRequestCreateWidgetRenderer.class);

	private PopupWidgetHelper popupWidgetHelper;

	protected PopupWidgetHelper getPopupWidgetHelper()
	{
		return this.popupWidgetHelper;
	}

	@Required
	public void setPopupWidgetHelper(final PopupWidgetHelper popupWidgetHelper)
	{
		this.popupWidgetHelper = popupWidgetHelper;
	}



	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.cscockpit.widgets.renderers.impl.AbstractCsWidgetRenderer#createCaption(de.hybris.platform.
	 * cockpit.widgets.Widget)
	 */
	@Override
	public HtmlBasedComponent createCaption(final Widget widget)
	{
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.cscockpit.widgets.renderers.impl.AbstractCsWidgetRenderer#createContentInternal(de.hybris.platform
	 * .cockpit.widgets.Widget, org.zkoss.zk.ui.api.HtmlBasedComponent)
	 */
	@Override
	protected HtmlBasedComponent createContentInternal(final Widget widget, final HtmlBasedComponent rootContainer)
	{
		final Div container = new Div();
		container.setSclass("reauthorizeOrderWidget");

		final Div orderReauthorizeRequestBox = new Div();
		orderReauthorizeRequestBox.setParent(container);

		final Label fieldLabel = new Label(LabelUtils.getLabel(widget, "authorizeAmount", new Object[0]));
		final Textbox fieldTextBox = new Textbox("0.0");
		if (widget.getWidgetController() instanceof PayPalReauthorizationController)
		{
			final TypedObject order = ((PayPalReauthorizationController) widget.getWidgetController()).getOrder();
			if (order != null && order.getObject() instanceof OrderModel)
			{
				final OrderModel orderModel = (OrderModel) order.getObject();
				fieldTextBox.setValue(orderModel.getTotalPrice().toString());
			}
		}
		orderReauthorizeRequestBox.appendChild(fieldLabel);
		orderReauthorizeRequestBox.appendChild(fieldTextBox);

		final Div reauthorizationOrderButtonBox = new Div();
		reauthorizationOrderButtonBox.setParent(container);
		reauthorizationOrderButtonBox.setSclass("reauthorizationOrderButtonBox");
		final Button attemptReauthorizationButton = new Button(LabelUtils.getLabel(widget, "reauthorizeOrderButton", new Object[0]));
		attemptReauthorizationButton.setParent(reauthorizationOrderButtonBox);
		attemptReauthorizationButton.addEventListener(
				"onClick",
				this.createConfirmBeforeCompletingRequestEventListener(widget,
						this.createAttemptReauthorizationEventListener(widget, fieldTextBox)));
		return container;
	}

	/**
	 * @param widget
	 * @param orderCancelRequestObjectValueContainer
	 * @return
	 */
	private EventListener createAttemptReauthorizationEventListener(final Widget widget, final Textbox valueContainer)
	{
		return new AttemptReauthorizationEventListener(widget, valueContainer);
	}

	/**
	 * @param widget2
	 * @param event
	 * @param orderCancelRequestObjectValueContainer2
	 * @throws InterruptedException
	 */
	protected void handleAttemptReauthorizationEvent(final Widget widget, final Event event, final Textbox valueContainer)
			throws InterruptedException
	{
		if ("onOK".equals(event.getName()))
		{
			try
			{
				final BigDecimal amount = new BigDecimal(valueContainer.getText().replace(",", ""));
				if (amount.compareTo(BigDecimal.valueOf(10000)) == 1)
				{
					LOG.error("Reauthorization amount can't be greater than 10000");
					Messagebox.show(LabelUtils.getLabel(widget, "amountError", new Object[0]),
							LabelUtils.getLabel(widget, "errorCreatingRequest", new Object[0]), 1, "z-msgbox z-msgbox-error");
				}
				else
				{
					final TypedObject e = ((PayPalReauthorizationController) widget.getWidgetController())
							.createOrderReauthorizationRequest(valueContainer);

					if (e != null)
					{
						this.getPopupWidgetHelper().dismissCurrentPopup();
						final OrderModel orderModel = (OrderModel) e.getObject();
						Messagebox.show(LabelUtils.getLabel(widget, "success", new Object[]
						{ orderModel.getCode() }), LabelUtils.getLabel(widget, "reauthorizationTitle", new Object[0]), 1,
								"z-msgbox z-msgbox-information");
						((PayPalReauthorizationController) widget.getWidgetController()).dispatchEvent(null, widget, null);
					}
					else
					{
						Messagebox.show(LabelUtils.getLabel(widget, "errorCreatingRequest", new Object[0]),
								LabelUtils.getLabel(widget, "failed", new Object[0]), 1, "z-msgbox z-msgbox-error");
					}
				}
			}
			catch (final InterruptedException ie)
			{
				// YTODO Auto-generated catch block
				ie.printStackTrace();
			}
		}

	}

	protected class AttemptReauthorizationEventListener implements EventListener
	{
		private final Widget widget;
		private final Textbox valueContainer;

		public AttemptReauthorizationEventListener(final Widget widget, final Textbox valueContainer)
		{
			this.widget = widget;
			this.valueContainer = valueContainer;
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see org.zkoss.zk.ui.event.EventListener#onEvent(org.zkoss.zk.ui.event.Event)
		 */
		@Override
		public void onEvent(final Event event) throws Exception
		{
			handleAttemptReauthorizationEvent(this.widget, event, this.valueContainer);
		}
	}

}
