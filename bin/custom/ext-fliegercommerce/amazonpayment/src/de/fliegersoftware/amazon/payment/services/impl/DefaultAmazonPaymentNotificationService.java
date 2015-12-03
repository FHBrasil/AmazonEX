package de.fliegersoftware.amazon.payment.services.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.fliegersoftware.amazon.payment.services.AmazonPaymentNotificationService;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

public class DefaultAmazonPaymentNotificationService implements
		AmazonPaymentNotificationService {

	private FlexibleSearchService flexibleSearchService;

	@Override
	public List<PaymentTransactionEntryModel> getPaymentTransactionEntriesForReferenceId(String ref) {
		final Map<String, Object> values = new HashMap<String, Object>();
		values.put("ref", ref);

		final StringBuilder queryString = new StringBuilder();
		queryString.append("SELECT {e.PK} FROM {PaymentTransactionEntry as e} ") //
		.append("WHERE {e.requestId} = ?ref"); //
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString(), values);
		query.setResultClassList(Collections.singletonList(PaymentTransactionEntryModel.class));
		final SearchResult<PaymentTransactionEntryModel> res = flexibleSearchService.search(query);

		return res.getResult();
	}

	@Override
	public PaymentTransactionModel getPaymentTransactionForCode(String code) {
		final Map<String, Object> values = new HashMap<String, Object>();
		values.put("code", code);

		final StringBuilder queryString = new StringBuilder();
		queryString.append("SELECT {t.PK} FROM {PaymentTransaction as t ") //
		.append("join PaymentInfo as i on {i.pk} = {t.info}} ") //
		.append("WHERE {i.code} = ?code"); //
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString(), values);
		query.setResultClassList(Collections.singletonList(PaymentTransactionModel.class));
		final SearchResult<PaymentTransactionModel> res = flexibleSearchService.search(query);

		if(res.getResult().size() > 0)
			return res.getResult().get(0);
		else
			return null;
	}

	protected FlexibleSearchService getFlexibleSearchService() {
		return flexibleSearchService;
	}

	public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService) {
		this.flexibleSearchService = flexibleSearchService;
	}
}
