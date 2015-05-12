/**
 * 
 */
package com.paypal.hybris.dao;


import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.servicelayer.internal.dao.Dao;


/**
 * @author Valentyn Markovych, Gorilla
 * 
 */
public interface PaypalCartDao extends Dao {

/**
 * @param sid
 *          Session ID
 * @return Cart from the DB with given session ID
 */
public CartModel getCartBySid(String sid);

}
