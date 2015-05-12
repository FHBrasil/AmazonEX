/**
 * 
 */
package com.paypal.hybris.dao;


import java.util.List;

import com.paypal.hybris.model.PaypalOperationModel;


/**
 * @author Christina Romashchenko
 * 
 */
public interface PaypalOperationDao {


public void saveOperation(PaypalOperationModel operation);


public List<PaypalOperationModel> getOperations();

}
