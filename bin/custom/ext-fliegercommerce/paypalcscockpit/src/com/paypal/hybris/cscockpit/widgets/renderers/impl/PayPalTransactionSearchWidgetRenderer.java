/**
 *
 */
package com.paypal.hybris.cscockpit.widgets.renderers.impl;

import de.hybris.platform.cockpit.model.meta.TypedObject;
import de.hybris.platform.cockpit.widgets.ListboxWidget;
import de.hybris.platform.cockpit.widgets.Widget;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.cscockpit.model.data.DataObject;
import de.hybris.platform.cscockpit.services.search.generic.query.DefaultOrderSearchQueryBuilder;
import de.hybris.platform.cscockpit.services.search.impl.DefaultCsTextSearchCommand;
import de.hybris.platform.cscockpit.utils.LabelUtils;
import de.hybris.platform.cscockpit.widgets.controllers.search.SearchCommandController;
import de.hybris.platform.cscockpit.widgets.models.impl.TextSearchWidgetModel;
import de.hybris.platform.cscockpit.widgets.renderers.impl.AbstractSearcherWidgetRenderer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.zkoss.zk.ui.AbstractComponent;
import org.zkoss.zk.ui.api.HtmlBasedComponent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.impl.InputElement;

import com.paypal.hybris.cockpit.model.data.PayPalTransactionEntry;
import com.paypal.hybris.cockpit.services.search.query.PayPalTransactionSearchQueryBuilder;
import com.paypal.hybris.constants.PaypalcscockpitConstants;


/**
 * @author Aliaksei_Sery (EPAM Systems)
 *
 */
public class PayPalTransactionSearchWidgetRenderer extends AbstractSearcherWidgetRenderer
{

	private Date fromDate;
	private Date toDate;
	private String payer;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.cscockpit.widgets.renderers.impl.AbstractSearcherWidgetRenderer#createSearchPane(de.hybris.
	 * platform.cockpit.widgets.ListboxWidget)
	 */
	@Override
	protected HtmlBasedComponent createSearchPane(
			final ListboxWidget<TextSearchWidgetModel, SearchCommandController<DefaultCsTextSearchCommand>> widget)
	{
		final Div searchPane = new Div();
		searchPane.setStyle("padding-bottom: 20px;");

		final Label startDateLabel = new Label(LabelUtils.getLabel((Widget) widget, "startDate", new Object[0]));
		searchPane.appendChild(startDateLabel);
		final Datebox startDate = new Datebox();
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		if (fromDate == null)
		{
			startDate.setValue(cal.getTime());
		}
		else
		{
			startDate.setValue(fromDate);
		}
		startDate.setFormat(PaypalcscockpitConstants.TRANSACTION_SEARCH_DATE_FORMAT);
		startDate.setParent(searchPane);
		startDate.setConstraint("no empty");

		final Label endDateLabel = new Label(LabelUtils.getLabel((Widget) widget, "endDate", new Object[0]));
		searchPane.appendChild(endDateLabel);
		final Datebox endDate = new Datebox();
		if (toDate == null)
		{
			endDate.setValue(new Date());
		}
		else
		{
			endDate.setValue(toDate);
		}
		endDate.setFormat(PaypalcscockpitConstants.TRANSACTION_SEARCH_DATE_FORMAT);
		endDate.setParent(searchPane);
		endDate.setConstraint("no empty");

		final Label payerLable = new Label(LabelUtils.getLabel((Widget) widget, "payer", new Object[0]));
		searchPane.appendChild(payerLable);
		final Textbox payerEmail = new Textbox("");
		if (payer != null)
		{
			payerEmail.setValue(payer);
		}
		payerEmail.setParent(searchPane);

		final Textbox transactionDateInput = createPayerEmailSearchField(widget, searchPane);

		final Button searchBtn = createSearchButton(widget, searchPane);
		searchBtn.setStyle("top: -11px; position: relative;");
		this.attachSearchEventListener(widget,
				createSearchEventListener(widget, transactionDateInput, payerEmail, startDate, endDate), new AbstractComponent[]
				{ transactionDateInput, searchBtn });

		transactionDateInput.addEventListener("onFocus", new EventListener()
		{
			@Override
			public void onEvent(final Event event) throws Exception
			{
				transactionDateInput.focus();
			}
		});
		return searchPane;
	}

	protected Textbox createPayerEmailSearchField(final ListboxWidget widget, final Div parent)
	{
		return createSearchTextField(widget, parent, "transactionId", DefaultOrderSearchQueryBuilder.TextField.OrderId,
				"SearchForOrders_OrderIDCustomerName_input");
	}

	protected Button createSearchButton(final ListboxWidget widget, final Div parent)
	{
		return createButton(widget, parent, "searchBtn", "SearchForTransaction_Search_button");
	}

	protected EventListener createSearchEventListener(final ListboxWidget widget, final InputElement transactionIdInput,
			final InputElement payer, final InputElement startDate, final InputElement endDate)
	{
		return new PayPalTransactionSearchWidgetRenderer.SearchEventListener(widget, transactionIdInput, payer, startDate, endDate);
	}

	protected class SearchEventListener extends AbstractSearchEventListener
	{
		private final transient InputElement transactionId;
		private final transient InputElement payer;
		private final transient InputElement startDate;
		private final transient InputElement endDate;

		public SearchEventListener(final ListboxWidget widget, final InputElement transactionId, final InputElement payer,
				final InputElement startDate, final InputElement endDate)
		{
			super(widget);
			this.transactionId = transactionId;
			this.payer = payer;
			this.startDate = startDate;
			this.endDate = endDate;

		}

		@Override
		protected void fillSearchCommand(final DefaultCsTextSearchCommand command)
		{
			if (this.transactionId != null)
			{
				command.setText(PayPalTransactionSearchQueryBuilder.TextField.TransactionId, this.transactionId.getText());
				command.setText(PayPalTransactionSearchQueryBuilder.TextField.Payer, this.payer.getText());
				command.setText(PayPalTransactionSearchQueryBuilder.TextField.StartDate, this.startDate.getText());
				fromDate = new Date(this.startDate.getText());
				command.setText(PayPalTransactionSearchQueryBuilder.TextField.EndDate, this.endDate.getText());
				toDate = new Date(this.endDate.getText());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.cscockpit.widgets.renderers.impl.AbstractSearcherWidgetRenderer#renderListbox(org.zkoss.zul
	 * .Listbox, de.hybris.platform.cockpit.widgets.ListboxWidget, org.zkoss.zk.ui.api.HtmlBasedComponent)
	 */
	@Override
	protected void renderListbox(final Listbox listBox, final ListboxWidget widget, final HtmlBasedComponent rootContainer)
	{
		final TextSearchWidgetModel widgetModel = (TextSearchWidgetModel) widget.getWidgetModel();
		if (widgetModel != null)
		{
			final List items = widgetModel.getItems();
			if (items != null && !items.isEmpty())
			{
				final Listhead headRow = new Listhead();
				headRow.setParent(listBox);

				populateHeaderRow(widget, headRow, prepareColumnsHeader());

				final Listheader colHeader = new Listheader(LabelUtils.getLabel((Widget) widget, "actions", new Object[0]));
				colHeader.setWidth("80px");
				colHeader.setParent(headRow);
				final Iterator itemsIterator = items.iterator();

				while (itemsIterator.hasNext())
				{
					final DataObject metaItem = (DataObject) itemsIterator.next();
					final TypedObject order = getCockpitTypeService().wrapItem(metaItem.getMeta(ItemModel.class));

					final Listitem row = new Listitem();
					row.setParent(listBox);
					row.setSclass("csSearchResultRow");

					populateDataRow(widget, row, (PayPalTransactionEntry) metaItem.getItem());

					final Listcell actionCell = new Listcell();
					actionCell.setParent(row);
					final Button actionButton = new Button(LabelUtils.getLabel((Widget) widget, "selectBtn", new Object[0]));
					actionButton.setParent(actionCell);
					actionButton.setSclass("btngreen");

					final EventListener selectItemEventListener = createSelectItemEventListener(widget, order);
					row.addEventListener("onOK", selectItemEventListener);
					actionButton.addEventListener("onClick", selectItemEventListener);
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.cscockpit.widgets.renderers.impl.AbstractConfigurableCsListboxWidgetRenderer#populateDataRow
	 * (de.hybris.platform.cockpit.widgets.ListboxWidget, org.zkoss.zul.Listitem, java.util.List,
	 * de.hybris.platform.cockpit.model.meta.TypedObject)
	 */
	protected void populateDataRow(final Widget widget, final Listitem row, final PayPalTransactionEntry item)
	{
		// timestamp
		final Listcell timestampCell = new Listcell(item.getPaymentDate());
		timestampCell.setParent(row);

		// transaction id
		final Listcell transactionCell = new Listcell(item.getTransactionId());
		transactionCell.setParent(row);

		// type
		final Listcell typeCell = new Listcell(item.getTransactionType());
		typeCell.setParent(row);

		// payer name
		final Listcell payerCell = new Listcell(item.getPayerId());
		payerCell.setParent(row);

		// sales app
		/*
		 * final Listcell appCell = new Listcell(""); appCell.setParent(row);
		 */
	}

	protected List<ColumnModel> prepareColumnsHeader()
	{
		final List<ColumnModel> columns = new ArrayList<ColumnModel>();
		columns.add(new ColumnModel("timestamp"));
		columns.add(new ColumnModel("transactionId"));
		columns.add(new ColumnModel("type"));
		columns.add(new ColumnModel("payer"));
		//columns.add(new ColumnModel("salesapp"));

		return columns;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.cscockpit.widgets.renderers.impl.AbstractConfigurableCsListboxWidgetRenderer#populateHeaderRow
	 * (de.hybris.platform.cockpit.widgets.ListboxWidget, org.zkoss.zul.Listhead, java.util.List)
	 */
	protected void populateHeaderRow(final Widget widget, final Listhead row, final List<ColumnModel> columns)
	{
		if (CollectionUtils.isNotEmpty(columns))
		{
			final Iterator it = columns.iterator();

			while (it.hasNext())
			{
				final ColumnModel col = (ColumnModel) it.next();
				final Listheader header = new Listheader(LabelUtils.getLabel(widget, col.getName(), new Object[0]));
				header.setTooltiptext(col.getName());
				row.appendChild(header);
			}
		}
	}

	private class ColumnModel
	{
		private String name;

		/**
		 * @param name
		 */
		public ColumnModel(final String name)
		{
			super();
			this.name = name;
		}

		/**
		 * @return the name
		 */
		public String getName()
		{
			return name;
		}

		/**
		 * @param name
		 *           the name to set
		 */
		public void setName(final String name)
		{
			this.name = name;
		}


	}

}
