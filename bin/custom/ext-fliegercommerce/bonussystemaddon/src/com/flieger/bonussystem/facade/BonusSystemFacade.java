/**
 * 
 */
package com.flieger.bonussystem.facade;

import de.hybris.platform.commercefacades.order.price.data.DiscountData;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.order.exceptions.CalculationException;

import com.flieger.bonussystem.data.BonusSystemData;


/**
 * @author herbert
 * 
 */
public interface BonusSystemFacade
{

	/**
	 * @return
	 */
	BonusSystemData getCurrentUserBonusSystem();

	/**
	 * @param product
	 * @return
	 */
	Double getPoints(ProductModel product);

	/**
	 * @return
	 */
	Double getCartPoints();

	/**
	 * @param points
	 * @throws CalculationException
	 */
	void generateCartDiscount(double points) throws CalculationException;

	/**
	 * 
	 */
	void clearCartDiscount();

	/**
	 * @return
	 */
	DiscountData getCartDiscount();

	/**
	 * @param order
	 */
	void rewardPoints(String orderCode);

	/**
	 * @param order
	 */
	void rewardPoints(OrderModel order);

	/**
	 * @param order
	 * @throws CalculationException
	 */
	void generateMaxDiscount(AbstractOrderModel order) throws CalculationException;

	/**
	 * @param order
	 * @param points
	 * @throws CalculationException
	 */
	void generateDiscount(AbstractOrderModel order, double points) throws CalculationException;

	/**
	 * @param order
	 */
	void cancelPoints(OrderModel order);

	/**
	 * @return
	 */
	Double getCartUsedPoints();

	/**
	 * @param cartData
	 * @return
	 */
	Double getUsedPoints(AbstractOrderModel order);
}