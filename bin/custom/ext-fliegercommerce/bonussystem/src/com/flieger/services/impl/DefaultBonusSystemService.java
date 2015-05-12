/**
 * 
 */
package com.flieger.services.impl;

import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.CartModel;
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
import org.springframework.util.Assert;

import com.flieger.constants.BonussystemConstants;
import com.flieger.dao.BonusSystemDao;
import com.flieger.model.user.BonusSystemLogModel;
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
	public BonusSystemModel createBonusSystem(final CustomerModel customer)
	{
		Assert.notNull(customer, "Parameter customer cannot be null.");

		if (!isValidUser(customer))
		{
			throw new SystemException("Anonymous customers are not allowed to have a BonusSystem");
		}

		if (hasBonusSystem(customer))
		{
			throw new SystemException((new StringBuilder("A bonusSystem for the customer <")).append(customer.getName())
					.append("> already exists").toString());
		}

		final BonusSystemModel bonusSystemModel = modelService.create(BonusSystemModel.class);
		bonusSystemModel.setPoints(0D);

		modelService.save(bonusSystemModel);

		customer.setBonusSystem(bonusSystemModel);
		modelService.save(customer);

		return bonusSystemModel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.flieger.services.BonusSystemService#removeBonusSystem(de.hybris.platform.core.model.user.CustomerModel)
	 */
	@Override
	public void removeBonusSystem(final CustomerModel customer)
	{
		Assert.notNull(customer, "Parameter customer cannot be null.");

		if (!hasBonusSystem(customer))
		{
			throw new SystemException((new StringBuilder("There's no bonusSystem for the customer <")).append(customer.getName())
					.append(">").toString());
		}

		final BonusSystemModel bonusSystem = customer.getBonusSystem();
		customer.setBonusSystem(null);
		modelService.remove(bonusSystem);
		modelService.save(customer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.flieger.services.BonusSystemService#addBonusSystemLog(com.flieger.model.user.BonusSystemModel,
	 * com.flieger.model.user.BonusSystemLogModel)
	 */
	@Override
	public void addBonusSystemLog(final BonusSystemModel bonusSystem, final BonusSystemLogModel log)
	{
		Assert.notNull(bonusSystem, "Parameter bonusSystem cannot be null.");
		Assert.notNull(log, "Parameter log cannot be null.");

		modelService.save(log);

		final List<BonusSystemLogModel> logList = new ArrayList<>(bonusSystem.getLog());
		logList.add(log);
		bonusSystem.setLog(logList);

		modelService.save(bonusSystem);

		recalculateBonusSystemPoints(bonusSystem);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.flieger.services.BonusSystemService#removeBonusSystemLog(com.flieger.model.user.BonusSystemModel,
	 * com.flieger.model.user.BonusSystemLogModel)
	 */
	@Override
	public void removeBonusSystemLog(final BonusSystemModel bonusSystem, final BonusSystemLogModel log)
	{
		Assert.notNull(bonusSystem, "Parameter bonusSystem cannot be null.");
		Assert.notNull(log, "Parameter log cannot be null.");

		final List<BonusSystemLogModel> logList = new ArrayList<BonusSystemLogModel>(bonusSystem.getLog());
		logList.remove(log);

		bonusSystem.setLog(logList);

		modelService.remove(log);
		modelService.save(bonusSystem);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.flieger.services.BonusSystemService#recalculateBonusSystemPoints(com.flieger.model.user.BonusSystemModel)
	 */
	@Override
	public void recalculateBonusSystemPoints(final BonusSystemModel bonusSystem)
	{
		Assert.notNull(bonusSystem, "Parameter bonusSystem cannot be null.");

		double totalPoints = 0;

		if (CollectionUtils.isNotEmpty(bonusSystem.getLog()))
		{
			for (final BonusSystemLogModel log : bonusSystem.getLog())
			{
				totalPoints += log.getPoints();
			}
		}

		bonusSystem.setPoints(totalPoints);

		modelService.save(bonusSystem);
	}

	@Override
	public void applyDiscount(final CartModel cart, final BonusSystemModel bonusSystemModel) throws CalculationException
	{
		final DiscountModel bsDiscount = modelService.create(DiscountModel.class);
		bsDiscount.setCode(BonussystemConstants.BS_DISCOUNT_PREFIX + cart.getCode() + "_" + System.currentTimeMillis());
		bsDiscount.setCurrency(cart.getCurrency());
		bsDiscount.setPriority(1);
		bsDiscount.setGlobal(true);

		final double total = cart.getSubtotal();
		final double value = bonusSystemModel.getPoints() / 100.0;

		bsDiscount.setValue(value > total ? total : value);

		modelService.save(bsDiscount);

		final List<DiscountModel> discounts = new ArrayList<DiscountModel>(cart.getDiscounts());

		discounts.add(bsDiscount);

		cart.setDiscounts(discounts);

		calculationService.recalculate(cart);

		modelService.save(cart);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.flieger.services.BonusSystemService#getAppliedBSDiscounts(de.hybris.platform.core.model.order.AbstractOrderModel
	 * )
	 */
	@Override
	public List<DiscountModel> getAppliedBSDiscounts(final AbstractOrderModel model)
	{
		Assert.notNull(model, "Parameter model cannot be null.");

		if (CollectionUtils.isEmpty(model.getDiscounts()))
		{
			return Collections.emptyList();
		}

		final List<DiscountModel> bsDiscounts = new ArrayList<DiscountModel>();

		for (final DiscountModel discount : model.getDiscounts())
		{
			if (discount.getCode().startsWith(BonussystemConstants.BS_DISCOUNT_PREFIX))
			{
				bsDiscounts.add(discount);
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

	public ModelService getModelService()
	{
		return modelService;
	}

	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	public BonusSystemDao getBonusSystemDao()
	{
		return bonusSystemDao;
	}

	public void setBonusSystemDao(final BonusSystemDao bonusSystemDao)
	{
		this.bonusSystemDao = bonusSystemDao;
	}

	public CalculationService getCalculationService()
	{
		return calculationService;
	}

	public void setCalculationService(final CalculationService calculationService)
	{
		this.calculationService = calculationService;
	}
}