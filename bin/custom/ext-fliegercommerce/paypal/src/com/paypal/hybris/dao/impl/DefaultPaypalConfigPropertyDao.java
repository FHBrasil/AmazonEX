/**
 * 
 */
package com.paypal.hybris.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.paypal.hybris.dao.PaypalConfigPropertyDao;
import com.paypal.hybris.model.PaypalConfigPropertyModel;

import de.hybris.platform.servicelayer.internal.dao.AbstractItemDao;
import de.hybris.platform.servicelayer.search.SearchResult;

/**
 * @author christina
 * 
 */
public class DefaultPaypalConfigPropertyDao extends AbstractItemDao implements
		PaypalConfigPropertyDao {

	private static final Logger LOG = Logger.getLogger(DefaultPaypalConfigPropertyDao.class);

	@Override
	public List<PaypalConfigPropertyModel> getListOfProperties() {

		final String query = "select {pk} from { PaypalConfigProperty } order by {key} desc";
		final SearchResult<PaypalConfigPropertyModel> list = getFlexibleSearchService().search(query);
		final List<PaypalConfigPropertyModel> resultList = list.getResult();
		return resultList;
	}

	@Override
	public void deletePaypalSettings(
			final List<PaypalConfigPropertyModel> list, final int startIndex,
			final int endIndex) {

		if (startIndex > endIndex || startIndex >= list.size() || endIndex <= 0) {
			return;
		}
		for (int i = startIndex; i < endIndex; i++) {
			getModelService().remove(list.get(i));
		}
	}

	@Override
	public void addProperties(final List<PaypalConfigPropertyModel> properties) {

		for (final PaypalConfigPropertyModel prop : properties) {
			getModelService().save(prop);
		}

	}

	@Override
	public PaypalConfigPropertyModel getPropertyByKey(final String key) {

		final List<PaypalConfigPropertyModel> list = getListOfProperties();
		for (final PaypalConfigPropertyModel model : list) {
			if (model.getKey().equals(key)) {
				return model;
			}
		}
		return null;
	}

	@Override
	public void removeAll() {

		deletePaypalSettings(getListOfProperties());

	}

	@Override
	public void deletePaypalSettings(final List<PaypalConfigPropertyModel> list) {

		final int startIndex = 0;
		final int endIndex = list.size();
		deletePaypalSettings(list, startIndex, endIndex);
	}
}