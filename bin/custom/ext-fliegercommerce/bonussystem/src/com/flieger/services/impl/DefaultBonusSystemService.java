/**
 * 
 */
package com.flieger.services.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateIfSingleResult;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.price.DiscountModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.order.CalculationService;
import de.hybris.platform.order.exceptions.CalculationException;
import de.hybris.platform.servicelayer.exceptions.SystemException;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import com.flieger.constants.BonussystemConstants;
import com.flieger.dao.BonusSystemDao;
import com.flieger.model.user.BonusSystemConfigModel;
import com.flieger.model.user.BonusSystemDiscountModel;
import com.flieger.model.user.BonusSystemEntryModel;
import com.flieger.model.user.BonusSystemModel;
import com.flieger.services.BonusSystemService;


/**
 * @author franthescollymaneira
 * 
 */
public class DefaultBonusSystemService implements BonusSystemService
{
	private BonusSystemDao bonusSystemDao;

	private ModelService modelService;

	private CalculationService calculationService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.flieger.services.BonusSystemService#createBonusSystem(de.hybris.platform.core.model.user.CustomerModel)
	 */
	@Override
	public BonusSystemModel createBonusSystem(final CustomerModel customer, BonusSystemConfigModel configuration)
	{
		Assert.notNull(customer, "Parameter customer cannot be null.");
		Assert.notNull(configuration, "Parameter configuration cannot be null.");

		if (!isValidUser(customer))
		{
			throw new SystemException("Anonymous customers are not allowed to have a BonusSystem");
		}

		if (hasBonusSystem(customer))
		{
			throw new SystemException((new StringBuilder("A bonusSystem for the customer <")).append(customer.getName())
					.append("> already exists").toString());
		}

		final BonusSystemModel bonusSystemModel = getModelService().create(BonusSystemModel.class);
		bonusSystemModel.setConfiguration(configuration);
		bonusSystemModel.setReservedPoints(0D);
		getModelService().save(bonusSystemModel);

		customer.setBonusSystem(bonusSystemModel);
		getModelService().save(customer);

		return bonusSystemModel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.flieger.services.BonusSystemService#removeBonusSystem(de.hybris.platform.core.model.user.CustomerModel)
	 */
	@Override
	public boolean removeBonusSystem(final BonusSystemModel system)
	{
		Assert.notNull(system , "Parameter system cannot be null.");
		CustomerModel customer = getBonusSystemDao().getCustomerForBonusSystem(system);

		getModelService().remove(system);
		if (customer != null)
		{
			customer.setBonusSystem(null);
			getModelService().save(customer);
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.flieger.services.BonusSystemService#addBonusSystemLog(com.flieger.model.user.BonusSystemModel,
	 * com.flieger.model.user.BonusSystemLogModel)
	 */
	@Override
	public boolean addBonusSystemEntry(final BonusSystemModel system, final BonusSystemEntryModel entry)
	{
		Assert.notNull(system, "Parameter system cannot be null.");
		Assert.notNull(entry, "Parameter log cannot be null.");

		entry.setBonusSystem(system);
		getModelService().save(entry);

		final List<BonusSystemEntryModel> entryList = new ArrayList<>(system.getLogEntries());
		entryList.add(entry);
		system.setLogEntries(entryList);

		getModelService().save(system);

//		recalculateBonusSystemPoints(bonusSystem);
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.flieger.services.BonusSystemService#addBonusSystemLog(com.flieger.model.user.BonusSystemModel,
	 * com.flieger.model.user.BonusSystemLogModel)
	 */
	@Override
	public boolean addBonusSystemEntry(final BonusSystemModel system, final BonusSystemEntryModel entry, BonusSystemDiscountModel discount)
	{
		Assert.notNull(system, "Parameter bonusSystem cannot be null.");
		Assert.notNull(entry, "Parameter log cannot be null.");

		entry.setBonusSystem(system);
		getModelService().save(entry);

		final List<BonusSystemEntryModel> entryList = new ArrayList<>(system.getLogEntries());
		entryList.add(entry);
		entry.setAppliedDiscount(discount);
		system.setLogEntries(entryList);

		getModelService().save(system);

//		recalculateBonusSystemPoints(bonusSystem);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.flieger.services.BonusSystemService#removeBonusSystemLog(com.flieger.model.user.BonusSystemModel,
	 * com.flieger.model.user.BonusSystemLogModel)
	 */
	@Override
	public void removeBonusSystemEntry(final BonusSystemModel bonusSystem, final BonusSystemEntryModel entry)
	{
		Assert.notNull(bonusSystem, "Parameter bonusSystem cannot be null.");
		Assert.notNull(entry, "Parameter log cannot be null.");

		final List<BonusSystemEntryModel> entryList = new ArrayList<BonusSystemEntryModel>(bonusSystem.getLogEntries());
		entryList.remove(entry);

		bonusSystem.setLogEntries(entryList);

		getModelService().remove(entry);
		getModelService().save(bonusSystem);
	}

//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see com.flieger.services.BonusSystemService#recalculateBonusSystemPoints(com.flieger.model.user.BonusSystemModel)
//	 */
//	@Override
//	public void recalculateBonusSystemPoints(final BonusSystemModel bonusSystem)
//	{
//		Assert.notNull(bonusSystem, "Parameter bonusSystem cannot be null.");
//
//		double totalPoints = 0;
//
//		if (CollectionUtils.isNotEmpty(bonusSystem.getLogEntries()))
//		{
//			for (final BonusSystemEntryModel entry : bonusSystem.getLogEntries())
//			{
//				totalPoints += entry.getPoints();
//			}
//		}
//
//		bonusSystem.setPoints(totalPoints);
//
//		modelService.save(bonusSystem);
//	}

	@Override
	public BonusSystemDiscountModel applyDiscount(final AbstractOrderModel abstractOrder, final BonusSystemModel system) throws CalculationException
	{
		return applyDiscount(abstractOrder, system, system.getAvailablePoints());
	}

	@Override
	public BonusSystemDiscountModel applyDiscount(final AbstractOrderModel abstractOrder, final BonusSystemModel system, double points) throws CalculationException
	{
		Assert.notNull(abstractOrder, "Parameter abstractOrder cannot be null.");
		Assert.notNull(system, "Parameter system cannot be null.");
		Assert.isTrue(points > 0, "Points cannot be 0 or negative.");
		double availablePoints = system.getAvailablePoints() != null ? system.getAvailablePoints().doubleValue() : 0;
		Assert.isTrue(availablePoints > 0, "Available points in system cannot be 0 or negative.");
		
		final BonusSystemDiscountModel bsDiscount = getModelService().create(BonusSystemDiscountModel.class);
		bsDiscount.setCode(BonussystemConstants.BS_DISCOUNT_PREFIX + abstractOrder.getCode() + "_" + System.currentTimeMillis());
		bsDiscount.setCurrency(abstractOrder.getCurrency());
		bsDiscount.setPriority(1);
		bsDiscount.setGlobal(true);

		final double total = abstractOrder.getSubtotal();
		final double value = Math.min(availablePoints, points) / 100.0;

		bsDiscount.setValue(value > total ? total : value);

		getModelService().save(bsDiscount);

		final List<DiscountModel> discounts = new ArrayList<DiscountModel>(abstractOrder.getDiscounts());

		discounts.add(bsDiscount);

		abstractOrder.setDiscounts(discounts);

		getCalculationService().recalculate(abstractOrder);

		getModelService().save(abstractOrder);
		
		return bsDiscount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.flieger.services.BonusSystemService#getAppliedBSDiscounts(de.hybris.platform.core.model.order.AbstractOrderModel
	 * )
	 */
	@Override
	public List<BonusSystemDiscountModel> getAppliedBonusSystemDiscounts(final AbstractOrderModel model)
	{
		Assert.notNull(model, "Parameter model cannot be null.");

		if (CollectionUtils.isEmpty(model.getDiscounts()))
		{
			return Collections.emptyList();
		}

		final List<BonusSystemDiscountModel> bsDiscounts = new ArrayList<BonusSystemDiscountModel>();

		for (final DiscountModel discount : model.getDiscounts())
		{
			if (discount instanceof BonusSystemDiscountModel && discount.getCode().startsWith(BonussystemConstants.BS_DISCOUNT_PREFIX))
			{
				bsDiscounts.add((BonusSystemDiscountModel)discount);
			}
		}

		return bsDiscounts;
	}

	public boolean hasBonusSystem(final CustomerModel customer)
	{
		Assert.notNull(customer, "Parameter customer cannot be null.");

		return customer.getBonusSystem() != null;
	}

	protected boolean isValidUser(final CustomerModel user)
	{
		if (user == null)
		{
			return false;
		}

		final boolean isAnonymous = de.hybris.platform.core.Constants.USER.ANONYMOUS_CUSTOMER.equals(user.getUid());

		return !isAnonymous;
	}

	protected ModelService getModelService()
	{
		return modelService;
	}

	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	protected BonusSystemDao getBonusSystemDao()
	{
		return bonusSystemDao;
	}

	@Required
	public void setBonusSystemDao(final BonusSystemDao bonusSystemDao)
	{
		this.bonusSystemDao = bonusSystemDao;
	}

	protected CalculationService getCalculationService()
	{
		return calculationService;
	}

	@Required
	public void setCalculationService(final CalculationService calculationService)
	{
		this.calculationService = calculationService;
	}
}