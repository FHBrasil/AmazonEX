/**
 *
 */
package com.paypal.hybris.cscockpit.services.search.impl;

import de.hybris.platform.cscockpit.model.data.impl.DefaultDataObject;
import de.hybris.platform.cscockpit.services.search.CsSearchCommand;
import de.hybris.platform.cscockpit.services.search.CsSearchResult;
import de.hybris.platform.cscockpit.services.search.CsSearchService;
import de.hybris.platform.cscockpit.services.search.Pageable;
import de.hybris.platform.cscockpit.services.search.SearchException;
import de.hybris.platform.cscockpit.services.search.impl.AbstractCsSearchService;
import de.hybris.platform.cscockpit.services.search.impl.DefaultCsSearchResult;
import de.hybris.platform.cscockpit.services.search.impl.DefaultCsTextSearchCommand;
import de.hybris.platform.cscockpit.services.search.meta.PostSearchMetaProcessor;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.servicelayer.session.SessionService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.ebay.api.TransactionSearchRequestType;
import com.ebay.api.TransactionSearchResponseType;
import com.paypal.hybris.cockpit.model.data.PayPalTransactionEntry;
import com.paypal.hybris.cockpit.services.search.query.PayPalTransactionSearchQueryBuilder;
import com.paypal.hybris.constants.PaypalConstants;
import com.paypal.hybris.constants.PaypalcscockpitConstants;
import com.paypal.hybris.converters.impl.PayPalRequestDataConverter;
import com.paypal.hybris.converters.impl.PayPalResponseConverter;
import com.paypal.hybris.data.PaymentTransaction;
import com.paypal.hybris.data.TransactionSearchRequestData;
import com.paypal.hybris.data.TransactionSearchResultData;
import com.paypal.hybris.service.PaypalPaymentService;


/**
 * @author Aliaksei_Sery (EPAM Systems)
 *
 */
public class PayPalTransactionSearchService extends AbstractCsSearchService implements CsSearchService
{
	private static final Logger LOG = Logger.getLogger(PayPalTransactionSearchService.class);

	private PayPalRequestDataConverter transactionSearchConverter;
	private PayPalResponseConverter transactionSearchRevertConverter;

	private PaypalPaymentService paypalPaymentService;
	private SessionService theSessionService;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.cscockpit.services.search.CsSearchService#search(de.hybris.platform.cscockpit.services.search
	 * .CsSearchCommand, de.hybris.platform.cscockpit.services.search.Pageable)
	 */
	@Override
	public CsSearchResult search(final CsSearchCommand searchCommand, final Pageable pageable) throws SearchException
	{

		final DefaultCsSearchResult searchResult = new DefaultCsSearchResult();
		searchResult.setSearchCommand(searchCommand);
		searchResult.setPageable(pageable);
		final List r = searchTransactionEntries(searchCommand, pageable);
		final List items = r;
		searchResult.setTotalResultCount(r.size());
		searchResult.setResult(createResultMetaData(limitByPage(items, pageable), searchResult));

		return searchResult;
	}

	/**
	 * @param items
	 * @param pageable
	 * @return
	 */
	private List limitByPage(final List items, final Pageable pageable)
	{
		final int start = pageable.getPageNumber() * pageable.getPageSize();
		final int count = pageable.getPageSize();
		final int end = items.size() > start + count ? start + count : items.size();

		return items.subList(start, end);
	}

	@Override
	protected List createResultMetaData(final List items, final Object providerSearchResult)
	{
		final ArrayList metaItems = new ArrayList(items.size());
		final Iterator it = items.iterator();

		while (it.hasNext())
		{
			final PayPalTransactionEntry item = (PayPalTransactionEntry) it.next();
			metaItems.add(new DefaultDataObject(item));
		}

		populateMetaData(metaItems, providerSearchResult);
		return metaItems;
	}

	@Override
	protected void populateMetaData(final List metaItems, final Object providerSearchResult)
	{
		final List processors = this.getPostSearchMetaProcessors();
		if (processors != null && !processors.isEmpty())
		{
			final Iterator it = processors.iterator();

			while (it.hasNext())
			{
				final PostSearchMetaProcessor processor = (PostSearchMetaProcessor) it.next();
				processor.populateMetaData(metaItems, providerSearchResult);
			}
		}
	}

	private Date fromDate;
	private Date toDate;
	private String transactionId;
	private String payer;

	public List<PayPalTransactionEntry> searchTransactionEntries(final CsSearchCommand searchCommand, final Pageable pageable)
			throws SearchException
	{
		final TransactionSearchRequestData requestData = new TransactionSearchRequestData();

		final TimeZone userTimeZone = JaloSession.getCurrentSession().getSessionContext().getTimeZone();
		final Locale userLocale = JaloSession.getCurrentSession().getSessionContext().getLocale();

		final Calendar now = Calendar.getInstance(userTimeZone);
		final Calendar defaultStartDate = Calendar.getInstance(userTimeZone);
		// set default start day to yesterday
		defaultStartDate.set(Calendar.DATE, -1);
		// set default end day to now
		final Calendar defaultEndDate = now;

		final Calendar startDate = getInputDate(searchCommand, PayPalTransactionSearchQueryBuilder.TextField.StartDate,
				defaultStartDate);
		if (fromDate == null || !fromDate.equals(startDate.getTime()))
		{
			theSessionService.removeAttribute(PaypalConstants.TRANSACTION_SEARCH_RESULT);
			fromDate = startDate.getTime();
		}
		final Calendar endDate = getInputDate(searchCommand, PayPalTransactionSearchQueryBuilder.TextField.EndDate, defaultEndDate);
		if (toDate == null || !toDate.equals(endDate.getTime()))
		{
			theSessionService.removeAttribute(PaypalConstants.TRANSACTION_SEARCH_RESULT);
			toDate = endDate.getTime();
		}

		if (startDate.before(now))
		{
			requestData.setStartDate(startDate);
		}
		else
		{
			LOG.warn("Start date is after current moment. Implicitly set to yesterday date");
			requestData.setStartDate(defaultStartDate);
		}

		if (endDate.after(startDate) && endDate.before(now))
		{
			requestData.setEndDate(endDate);
		}
		else
		{
			LOG.warn("End date is before start date or after current moment. Implicitly set to current date");
			requestData.setEndDate(defaultEndDate);
		}

		final String transactionId = getInputString(searchCommand, PayPalTransactionSearchQueryBuilder.TextField.TransactionId);
		if (this.transactionId == null || !this.transactionId.equals(transactionId))
		{
			theSessionService.removeAttribute(PaypalConstants.TRANSACTION_SEARCH_RESULT);
			this.transactionId = transactionId;
		}
		requestData.setTransactionId(transactionId);

		final String payer = getInputString(searchCommand, PayPalTransactionSearchQueryBuilder.TextField.Payer);
		if (this.payer == null || !this.payer.equals(payer))
		{
			theSessionService.removeAttribute(PaypalConstants.TRANSACTION_SEARCH_RESULT);
			this.payer = payer;
		}
		requestData.setPayerEmail(payer);

		if (theSessionService.getAttribute(PaypalConstants.TRANSACTION_SEARCH_RESULT) != null)
		{
			return theSessionService.getAttribute(PaypalConstants.TRANSACTION_SEARCH_RESULT);
		}
		final TransactionSearchRequestType request = (TransactionSearchRequestType) getTransactionSearchConverter().convert(
				requestData);
		final TransactionSearchResponseType response = getPaypalPaymentService().transactionSearch(request);
		final TransactionSearchResultData resultData = (TransactionSearchResultData) getTransactionSearchRevertConverter().convert(
				response);

		final ArrayList<PayPalTransactionEntry> entries = new ArrayList<>();
		if (PaypalConstants.STATUS_SUCCESS.equals(resultData.getAck())
				|| PaypalConstants.STATUS_SUCCESS_WITH_WARNINGS.equals(resultData.getAck()))
		{
			if (PaypalConstants.STATUS_SUCCESS_WITH_WARNINGS.equals(resultData.getAck()))
			{
				LOG.warn(resultData.getErrors().iterator().next().getLongMessage());
			}

			final List<PaymentTransaction> transactionLists = resultData.getTransactionList();

			if (CollectionUtils.isNotEmpty(transactionLists))
			{
				for (final PaymentTransaction transactionData : transactionLists)
				{
					final Calendar paymentDate = transactionData.getTimestamp();
					paymentDate.setTimeZone(userTimeZone);
					final SimpleDateFormat dateFormat = new SimpleDateFormat(
							PaypalcscockpitConstants.TRANSACTION_SEARCH_RESULT_DATE_FORMAT, userLocale);
					final PayPalTransactionEntry entry = new PayPalTransactionEntry(transactionData.getTransactionId(),
							transactionData.getType(), transactionData.getPayerEmail(), dateFormat.format(paymentDate.getTime()));
					entries.add(entry);
				}
			}
		}
		else
		{
			final String errorCode = resultData.getErrors().iterator().next().getErrorCode();
			LOG.error("TransactionSearch call is not success");
			LOG.error("First error code: " + errorCode);
			throw new SearchException("Paypal TransactionSearch call error code:" + errorCode);
		}
		theSessionService.setAttribute(PaypalConstants.TRANSACTION_SEARCH_RESULT, entries);
		return entries;
	}

	private String getInputString(final CsSearchCommand searchCommand,
			final PayPalTransactionSearchQueryBuilder.TextField textField)
	{
		String fieldValue = null;
		if (searchCommand instanceof DefaultCsTextSearchCommand)
		{
			fieldValue = ((DefaultCsTextSearchCommand) searchCommand).getText(textField);
		}
		return fieldValue;
	}

	private Calendar getInputDate(final CsSearchCommand searchCommand,
			final PayPalTransactionSearchQueryBuilder.TextField fieldName, final Calendar defaultDate) throws SearchException
	{
		Calendar inputDate = defaultDate;
		if (searchCommand instanceof DefaultCsTextSearchCommand)
		{
			try
			{
				final TimeZone userTimeZone = JaloSession.getCurrentSession().getSessionContext().getTimeZone();
				final Locale userLocale = JaloSession.getCurrentSession().getSessionContext().getLocale();

				final String fieldValue = ((DefaultCsTextSearchCommand) searchCommand).getText(fieldName);

				final DateFormat dateFormat = new SimpleDateFormat(PaypalcscockpitConstants.TRANSACTION_SEARCH_DATE_FORMAT,
						userLocale);

				if (StringUtils.isNotBlank(fieldValue))
				{
					final Date date = dateFormat.parse(fieldValue);
					inputDate = Calendar.getInstance(userTimeZone);
					inputDate.setTime(date);
				}
			}
			catch (final ParseException e)
			{
				LOG.error("Can't parse date:");
				LOG.error(e);
				throw new SearchException(e);
			}
		}
		return inputDate;
	}

	public PayPalRequestDataConverter getTransactionSearchConverter()
	{
		return transactionSearchConverter;
	}

	@Required
	public void setTransactionSearchConverter(final PayPalRequestDataConverter transactionSearchConverter)
	{
		this.transactionSearchConverter = transactionSearchConverter;
	}

	public PayPalResponseConverter getTransactionSearchRevertConverter()
	{
		return transactionSearchRevertConverter;
	}

	@Required
	public void setTransactionSearchRevertConverter(final PayPalResponseConverter transactionSearchRevertConverter)
	{
		this.transactionSearchRevertConverter = transactionSearchRevertConverter;
	}

	public PaypalPaymentService getPaypalPaymentService()
	{
		return paypalPaymentService;
	}

	public void setPaypalPaymentService(final PaypalPaymentService paypalPaymentService)
	{
		this.paypalPaymentService = paypalPaymentService;
	}


	public SessionService getTheSessionService()
	{
		return theSessionService;
	}

	public void setTheSessionService(final SessionService theSessionService)
	{
		this.theSessionService = theSessionService;
	}
}
