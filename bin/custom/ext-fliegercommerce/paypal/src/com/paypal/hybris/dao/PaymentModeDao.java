/**
 * 
 */
package com.paypal.hybris.dao;


import de.hybris.platform.core.model.order.payment.PaymentModeModel;

import java.util.List;


/**
 * @author christina
 * 
 */
public interface PaymentModeDao {

public List<PaymentModeModel> getListOfModesByCode(String code);
}
