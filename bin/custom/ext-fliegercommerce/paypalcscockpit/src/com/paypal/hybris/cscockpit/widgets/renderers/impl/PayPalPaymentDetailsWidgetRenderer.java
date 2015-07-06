/**
 *
 */
package com.paypal.hybris.cscockpit.widgets.renderers.impl;

import com.paypal.hybris.constants.PaypalConstants;
import com.paypal.hybris.cscockpit.widgets.controllers.PayPalOrderController;
import com.paypal.hybris.data.GetTransactionDetailsResultData;
import de.hybris.platform.cockpit.model.meta.TypedObject;
import de.hybris.platform.cockpit.widgets.ListboxWidget;
import de.hybris.platform.cockpit.widgets.Widget;
import de.hybris.platform.cscockpit.utils.LabelUtils;
import de.hybris.platform.cscockpit.widgets.renderers.impl.ConfigurableCsMasterDetailListboxWidgetRenderer;
import de.hybris.platform.cscockpit.widgets.renderers.utils.PopupWidgetHelper;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;


/**
 * @author Aliaksei_Sery (EPAM Systems)
 *
 */
public class PayPalPaymentDetailsWidgetRenderer extends ConfigurableCsMasterDetailListboxWidgetRenderer
{
	private PopupWidgetHelper popupWidgetHelper;

	public PopupWidgetHelper getPopupWidgetHelper()
	{
		return this.popupWidgetHelper;
	}

	@Required
	public void setPopupWidgetHelper(final PopupWidgetHelper popupWidgetHelper)
	{
		this.popupWidgetHelper = popupWidgetHelper;
	}

	@Override
	protected Object populateHeaderRow(final ListboxWidget widget, final Listhead row)
	{
		super.populateHeaderRow(widget, row, this.getColumnConfigurations());

		final Listheader actions = new Listheader(LabelUtils.getLabel((Widget) widget, "actions", new Object[0]));
		actions.setWidth("80px");
		actions.setParent(row);

		return null;
	}

	@Override
	protected void populateMasterRow(final ListboxWidget widget, final Listitem row, final Object context, final TypedObject item)
	{
		super.populateMasterRow(widget, row, context, item);

		final Listcell actionCell = new Listcell();
		actionCell.setParent(row);
		final Button actionButton = new Button(LabelUtils.getLabel((Widget) widget, "detailsBtn", new Object[0]));
		actionButton.setParent(actionCell);
		actionButton.setSclass("btngreen");

		if (item.getObject() instanceof PaymentTransactionEntryModel)
		{
			PaymentTransactionEntryModel transactionEntryModel = (PaymentTransactionEntryModel) item.getObject();
			String paymentProvider = transactionEntryModel.getPaymentTransaction().getPaymentProvider();
			String requestId = transactionEntryModel.getRequestId();
			if ((StringUtils.isNotBlank(paymentProvider) && !paymentProvider.equals(PaypalConstants.PAYMENT_PROVIDER_NAME)) || StringUtils.isBlank(requestId))
			{
				actionButton.setDisabled(true);
			}
		}

		final EventListener selectItemEventListener = createGetPaymentDetailsEventListener(widget, item);
		row.addEventListener("onOK", selectItemEventListener);
		actionButton.addEventListener("onClick", selectItemEventListener);
	}

	/**
	 * @param widget
	 * @param item
	 * @return
	 */
	protected EventListener createGetPaymentDetailsEventListener(final ListboxWidget widget, final TypedObject item)
	{
		return new GetPaymentDetailsEventListener(widget, item);
	}

	protected class GetPaymentDetailsEventListener implements EventListener
	{
		private final Widget widget;
		private final TypedObject item;

		/**
		 * @param widget
		 * @param item
		 */
		public GetPaymentDetailsEventListener(final Widget widget, final TypedObject item)
		{
			this.widget = widget;
			this.item = item;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.zkoss.zk.ui.event.EventListener#onEvent(org.zkoss.zk.ui.event.Event)
		 */
		@Override
		public void onEvent(final Event event) throws Exception
		{
			if (event != null)
			{
				getPopupWidgetHelper().dismissCurrentPopup();
				final PaymentTransactionEntryModel paymentModel = (PaymentTransactionEntryModel) item.getObject();

				PayPalOrderController controller = (PayPalOrderController) widget.getWidgetController();
				GetTransactionDetailsResultData detailsData = controller.getTransactionDetails(paymentModel.getRequestId());

				if (PaypalConstants.STATUS_SUCCESS.equals(detailsData.getAck()))
				{
					StringBuilder detailsMessage = new StringBuilder();
					detailsMessage.append("Transaction ID: ").append(detailsData.getTransactionId()).append('\n');
					detailsMessage.append("Status: ").append(detailsData.getPaymentStatus()).append('\n');
					detailsMessage.append("Type: ").append(detailsData.getPaymentType()).append('\n');
					detailsMessage.append("Payer email: ").append(detailsData.getPayerEmail());
					Messagebox.show(detailsMessage.toString());
				}
				else
				{
					Messagebox.show("Can't get transaction details. Error(s):\n " + StringUtils.join(detailsData.getErrorMessagesList(), '\n') );
				}
			}
		}
	}
}
