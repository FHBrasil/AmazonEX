/**
 * 
 */
package com.paypal.hybris.dao.impl;


import de.hybris.platform.core.model.order.payment.PaymentModeModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.paypal.hybris.dao.PaymentModeDao;


/**
 * @author christina
 * 
 */
@Component("paypalPaymentModeDao")
public class DefaultPaymentModeDao implements PaymentModeDao {

@Resource
private FlexibleSearchService flexibleSearchService;


public void setFlexibleSearchService(
		final FlexibleSearchService flexibleSearchService) {

	this.flexibleSearchService = flexibleSearchService;
}


@Override
public List<PaymentModeModel> getListOfModesByCode(final String code) {

	final String query = "select {pk} from {PaymentMode as pm} where {pm.code} = '"
			+ code + "'";
	final SearchResult<PaymentModeModel> list = flexibleSearchService
			.search(query);
	final List<PaymentModeModel> resultList = list.getResult();
	return resultList;
}

}
