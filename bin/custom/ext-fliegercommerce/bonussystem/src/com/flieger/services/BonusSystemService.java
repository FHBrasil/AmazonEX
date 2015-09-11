/**
 * 
 */
package com.flieger.services;

import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.order.exceptions.CalculationException;

import java.util.List;

import com.flieger.model.user.BonusSystemConfigModel;
import com.flieger.model.user.BonusSystemDiscountModel;
import com.flieger.model.user.BonusSystemEntryModel;
import com.flieger.model.user.BonusSystemModel;



/**
 * @author franthescollymaneira
 * 
 */
public interface BonusSystemService
{
	BonusSystemModel createBonusSystem(CustomerModel customer, BonusSystemConfigModel configuration);

	boolean removeBonusSystem(BonusSystemModel system);

	boolean addBonusSystemEntry(BonusSystemModel system, BonusSystemEntryModel entry);

	boolean addBonusSystemEntry(BonusSystemModel system, BonusSystemEntryModel entry, BonusSystemDiscountModel discount);

	void removeBonusSystemEntry(BonusSystemModel system, BonusSystemEntryModel entry);
	
	BonusSystemDiscountModel applyDiscount(AbstractOrderModel abstractOrder, BonusSystemModel system) throws CalculationException;

	BonusSystemDiscountModel applyDiscount(AbstractOrderModel abstractOrder, BonusSystemModel system, double points) throws CalculationException;

	List<BonusSystemDiscountModel> getAppliedBonusSystemDiscounts(AbstractOrderModel abstractOrder);
}