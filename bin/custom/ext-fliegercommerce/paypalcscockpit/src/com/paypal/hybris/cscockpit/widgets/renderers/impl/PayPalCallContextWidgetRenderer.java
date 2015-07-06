/**
 *
 */
package com.paypal.hybris.cscockpit.widgets.renderers.impl;

import de.hybris.platform.cockpit.events.CockpitEventAcceptor;
import de.hybris.platform.cockpit.session.UISessionUtils;
import de.hybris.platform.cockpit.util.UITools;
import de.hybris.platform.cockpit.widgets.Widget;
import de.hybris.platform.cscockpit.session.impl.OrderedConfigurableBrowserArea;
import de.hybris.platform.cscockpit.utils.CssUtils;
import de.hybris.platform.cscockpit.utils.LabelUtils;
import de.hybris.platform.cscockpit.widgets.controllers.CallContextController;
import de.hybris.platform.cscockpit.widgets.models.impl.CallContextWidgetModel;
import de.hybris.platform.cscockpit.widgets.renderers.impl.CallContextWidgetRenderer;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.api.HtmlBasedComponent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Div;
import org.zkoss.zul.Toolbarbutton;


/**
 * @author Aliaksei_Sery (EPAM Systems)
 *
 */
public class PayPalCallContextWidgetRenderer extends CallContextWidgetRenderer
{
	private Toolbarbutton searchPayPalTransactionBtn;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.cscockpit.widgets.renderers.impl.CallContextWidgetRenderer#createContentInternal(de.hybris.
	 * platform.cockpit.widgets.Widget, org.zkoss.zk.ui.api.HtmlBasedComponent)
	 */
	@Override
	protected HtmlBasedComponent createContentInternal(final Widget<CallContextWidgetModel, CallContextController> widget,
			final HtmlBasedComponent rootContainer)
	{
		final CockpitEventAcceptor addressNotificationEventAcceptor = this.createAddressNotificationEventAcceptor(widget);
		final OrderedConfigurableBrowserArea browserArea = (OrderedConfigurableBrowserArea) UISessionUtils.getCurrentSession()
				.getCurrentPerspective().getBrowserArea();
		browserArea.addNotificationListener(widget.getWidgetCode(), addressNotificationEventAcceptor);
		final Div content = new Div();
		content.appendChild(createSiteContent(widget));
		content.appendChild(createCustomerContent(widget));
		content.appendChild(createOrderContent(widget));
		content.appendChild(createPayPalContent(widget));
		content.appendChild(createTicketContent(widget));
		content.appendChild(createCurrencyContent(widget));
		content.appendChild(createEndCallContent(widget));
		return content;
	}

	/**
	 * @param widget
	 * @return
	 */
	protected Component createPayPalContent(final Widget<CallContextWidgetModel, CallContextController> widget)
	{
		final Div container = new Div();
		final Div orderInfoContainer = new Div();
		orderInfoContainer.setParent(container);
		createSearchPayPalTransactionButton(widget, container);

		return container;
	}

	/**
	 * @param widget
	 * @param container
	 */
	protected void createSearchPayPalTransactionButton(final Widget<CallContextWidgetModel, CallContextController> widget,
			final Div container)
	{
		searchPayPalTransactionBtn = new Toolbarbutton(LabelUtils.getLabel(widget, "findTransaction", new Object[0]));
		searchPayPalTransactionBtn.setParent(container);
		searchPayPalTransactionBtn.setSclass(CssUtils.combine(new String[]
		{ "csCallContextContainerPopupBtn", "blueLink" }));

		if (UISessionUtils.getCurrentSession().isUsingTestIDs())
		{
			UITools.applyTestID(this.searchPayPalTransactionBtn, "CallContext_FindOrder_link");
		}

		searchPayPalTransactionBtn.addEventListener("onClick", new EventListener()
		{
			@Override
			public void onEvent(final Event event) throws Exception
			{
				handleOpenPayPalTransactionSearchEvent(widget, event, container);
			}
		});
	}

	protected void handleOpenPayPalTransactionSearchEvent(final Widget widget, final Event event, final Div container)
	{
		this.getPopupWidgetHelper().createPopupWidget(container, "csPayPalTransactionSearchWidgetConfig",
				"csPayPalTransactionSearchWidget-Popup", "csPayPalTransactionSearchPopup",
				LabelUtils.getLabel(widget, "popup.ppTransactionSearchTitle", new Object[0]), 990);
	}
}
