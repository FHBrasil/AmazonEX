/**
 * 
 */
package com.paypal.hybris.dao.impl;


import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.paypal.hybris.dao.PaypalOperationDao;
import com.paypal.hybris.model.PaypalOperationModel;


/**
 * @author Christina Romashchenko
 * 
 */
@Component("paypalOperationDao")
public class DefaultPaypalOperationDao implements PaypalOperationDao {


@Resource
private FlexibleSearchService flexibleSearchService;

@Resource
private ModelService modelService;


@Override
public void saveOperation(final PaypalOperationModel operation) {

	modelService.save(operation);

}


@Override
public List<PaypalOperationModel> getOperations() {

	final String query = "select {pk} from { PaypalOperation } ";
	final SearchResult<PaypalOperationModel> list = flexibleSearchService
			.search(query);
	final List<PaypalOperationModel> resultList = list.getResult();
	return resultList;
}


}
