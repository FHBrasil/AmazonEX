/**
 * 
 */
package com.flieger.services;

import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.price.DiscountModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.order.exceptions.CalculationException;

import java.util.List;

import com.flieger.model.user.BonusSystemLogModel;
import com.flieger.model.user.BonusSystemModel;



/**
 * @author franthescollymaneira
 * 
 */
public interface BonusSystemService
{
	BonusSystemModel createBonusSystem(CustomerModel customer);

	void removeBonusSystem(CustomerModel customer);

	void addBonusSystemLog(BonusSystemModel bonusSystem, BonusSystemLogModel log);

	void removeBonusSystemLog(BonusSystemModel bonusSystem, BonusSystemLogModel log);

	void recalculateBonusSystemPoints(BonusSystemModel bonusSystem);

	void applyDiscount(CartModel cart, BonusSystemModel bonusSystemModel) throws CalculationException;

	List<DiscountModel> getAppliedBSDiscounts(AbstractOrderModel cart);
}