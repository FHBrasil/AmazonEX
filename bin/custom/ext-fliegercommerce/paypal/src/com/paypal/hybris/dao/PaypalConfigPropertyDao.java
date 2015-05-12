/**
 * 
 */
package com.paypal.hybris.dao;


import java.util.List;

import com.paypal.hybris.model.PaypalConfigPropertyModel;


/**
 * @author christina
 * 
 */
public interface PaypalConfigPropertyDao {

public List<PaypalConfigPropertyModel> getListOfProperties();


public void deletePaypalSettings(List<PaypalConfigPropertyModel> list,
		int startIndex, int endIndex);


public void deletePaypalSettings(final List<PaypalConfigPropertyModel> list);


public void addProperties(List<PaypalConfigPropertyModel> properties);


public PaypalConfigPropertyModel getPropertyByKey(String key);


public void removeAll();

}
