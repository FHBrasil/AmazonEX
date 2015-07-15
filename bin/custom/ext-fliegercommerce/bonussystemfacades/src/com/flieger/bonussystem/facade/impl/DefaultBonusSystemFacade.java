/**
 * 
 */
package com.flieger.bonussystem.facade.impl;

import de.hybris.platform.commercefacades.order.price.data.DiscountData;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.price.DiscountModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.order.exceptions.CalculationException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import com.flieger.bonussystem.BonusSystemCreationStrategy;
import com.flieger.bonussystem.data.BonusSystemData;
import com.flieger.bonussystem.facade.BonusSystemFacade;
import com.flieger.facades.converters.BonusSystemConverter;
import com.flieger.model.user.BonusSystemConfigModel;
import com.flieger.model.user.BonusSystemDiscountModel;
import com.flieger.model.user.BonusSystemEntryModel;
import com.flieger.model.user.BonusSystemModel;
import com.flieger.services.BonusSystemService;


/**
 * @author herbert
 * 
 */
public class DefaultBonusSystemFacade implements BonusSystemFacade
{
	private static final String DESCRIPTION_ORDER_REWARD = "Order Payment Points Reward";
	private static final String DESCRIPTION_ORDER_CANCELLED = "Order Cancellation Points Revoked";
	private static final String DESCRIPTION_ORDER_DISCOUNT = "Order Discount";
	private static final String DESCRIPTION_ORDER_PLACEHOLDER = "Order Temporary Points Reward";

	private static final String TYPE_REWARD = "reward";
	private static final String TYPE_CANCEL = "cancel";
	private static final String TYPE_DISCOUNT = "discount";
	private static final String TYPE_PLACEHOLDER = "placeholder";

	private UserService userService;
	private CartService cartService;
	private BaseStoreService baseStoreService;
	private CustomerAccountService customerAccoutService;
	private ModelService modelService;
	private BonusSystemService bonusSystemService;
	private BonusSystemConverter bonusSystemConverter;
	private BonusSystemCreationStrategy bonusSystemCreationStrategy;

	@Override
	public BonusSystemData getCurrentUserBonusSystem()
	{
		final BonusSystemModel bonusSystem = getUserBonusSystem();
		if (bonusSystem != null)
		{
			return getBonusSystemConverter().convert(bonusSystem);
		}
		else
		{
			return null;
		}
	}

	@Override
	public Double getPoints(final ProductModel product)
	{
		if (product == null)
		{
			return Double.valueOf(0);
		}

		final BonusSystemModel bonusSystem = getUserBonusSystem();

		return Double.valueOf(getPoints(bonusSystem != null ? bonusSystem.getConfiguration() : null, product));
	}

	@Override
	public Double getCartPoints()
	{
		return Double.valueOf(getOrderPoints(getCartService().getSessionCart()));
	}

	@Override
	public void generateCartDiscount(final double points) throws CalculationException
	{
		final CartModel cart = getCartService().getSessionCart();
		generateDiscount(cart, points);
	}

	@Override
	public void clearCartDiscount()
	{
		final CartModel cart = getCartService().getSessionCart();
		final BonusSystemModel bonusSystem = getUserBonusSystem(cart.getUser());
		if (bonusSystem != null)
		{
			// add all discounts and entries to separated sets, avoiding double deletion or crazy iterators
			final Set<DiscountModel> discounts = new HashSet<DiscountModel>();
			final Set<BonusSystemEntryModel> entries = new HashSet<BonusSystemEntryModel>();
			for (final DiscountModel discount : cart.getDiscounts())
			{
				for (final BonusSystemEntryModel entry : bonusSystem.getLogEntries())
				{
					if (entry.getAppliedDiscount() == discount)
					{
						discounts.add(discount);
						entries.add(entry);
					}
				}
			}
			for (final BonusSystemEntryModel entry : entries)
			{
				getBonusSystemService().removeBonusSystemEntry(bonusSystem, entry);
			}
			for (final DiscountModel discount : discounts)
			{
				getModelService().remove(discount);
			}
		}
	}

	@Override
	public DiscountData getCartDiscount()
	{
		final BonusSystemModel bonusSystem = getUserBonusSystem();
		final Double points = bonusSystem.getAvailablePoints();

		final DiscountData discount = new DiscountData();
		discount.setValue(points.doubleValue() / 100.0);
		return discount;
	}

	@Override
	public Double getCartUsedPoints()
	{
		return getUsedPoints(getCartService().getSessionCart());
	}

	@Override
	public Double getUsedPoints(final AbstractOrderModel order)
	{
		final BonusSystemModel bonusSystem = getUserBonusSystem(order.getUser());
		if (bonusSystem != null)
		{
			for (final BonusSystemEntryModel entry : bonusSystem.getLogEntries())
			{
				if (order.equals(entry.getReference()))
				{
					return Double.valueOf(Math.abs(entry.getPoints().doubleValue()));
				}
			}
		}
		return Double.valueOf(0);
	}

	@Override
	public void rewardPoints(final String orderCode)
	{
		final UserModel user = getUserService().getCurrentUser();
		if (user instanceof CustomerModel)
		{
			final OrderModel order = getCustomerAccoutService().getOrderForCode((CustomerModel) user, orderCode,
					getBaseStoreService().getCurrentBaseStore());
			rewardPoints(order);
		}
	}

	@Override
	public void rewardPoints(final OrderModel order)
	{
		final BonusSystemModel bonusSystem = getUserBonusSystem(order.getUser());
		if (bonusSystem != null)
		{
			final double points = getOrderPoints(bonusSystem.getConfiguration(), order);
			final BonusSystemEntryModel entry = createNewEntry(order, TYPE_REWARD, points, null);
			getBonusSystemService().addBonusSystemEntry(bonusSystem, entry);
		}
	}

	@Override
	public void generateMaxDiscount(final AbstractOrderModel order) throws CalculationException
	{
		final BonusSystemModel bonusSystem = getUserBonusSystem(order.getUser());

		if (bonusSystem != null)
		{
			generateDiscount(order, bonusSystem.getAvailablePoints().doubleValue());
		}
	}

	@Override
	public void generateDiscount(final AbstractOrderModel order, final double points) throws CalculationException
	{
		final BonusSystemModel bonusSystem = getUserBonusSystem(order.getUser());
		if (bonusSystem != null)
		{
			final BonusSystemDiscountModel discount = getBonusSystemService().applyDiscount(order, bonusSystem, points);
			final double actualPoints = discount.getValue().doubleValue() * 100;
			final BonusSystemEntryModel entry = createNewEntry(order, TYPE_DISCOUNT, -actualPoints, discount);
			getBonusSystemService().addBonusSystemEntry(bonusSystem, entry, discount);
		}
	}

	@Override
	public void cancelPoints(final OrderModel order)
	{
		final BonusSystemModel bonusSystem = getUserBonusSystem(order.getUser());
		if (bonusSystem != null)
		{
			final double points = -getOrderPoints(bonusSystem.getConfiguration(), order);
			final BonusSystemEntryModel entry = createNewEntry(order, TYPE_CANCEL, points, null);
			getBonusSystemService().addBonusSystemEntry(bonusSystem, entry);
		}
	}

	@Override
	public void changeFromCartToOrder(final CartModel cart, final OrderModel order)
	{
		final BonusSystemModel bonusSystem = getUserBonusSystem(order.getUser());
		if (bonusSystem != null)
		{
			for (final BonusSystemEntryModel entry : bonusSystem.getLogEntries())
			{
				if (entry.getReference() == cart)
				{
					entry.setReference(order);
					getModelService().save(entry);
				}
			}
		}
	}

	// internal logic

	private BonusSystemModel getUserBonusSystem()
	{
		return getUserBonusSystem(getUserService().getCurrentUser());
	}

	private BonusSystemModel getUserBonusSystem(final UserModel user)
	{
		if (user instanceof CustomerModel)
		{
			final CustomerModel customer = (CustomerModel) user;
			if (customer.getBonusSystem() == null && getBonusSystemCreationStrategy() != null)
			{
				getBonusSystemCreationStrategy().createBonusSystemForCustomer(customer);
			}
			return customer.getBonusSystem();
		}
		return null;
	}

	private double getOrderPoints(final BonusSystemConfigModel configuration, final AbstractOrderModel order)
	{
		double totalPoints = 0;
		for (final AbstractOrderEntryModel entry : order.getEntries())
		{
			totalPoints += getPoints(configuration, entry.getProduct()) * entry.getQuantity().longValue();
		}
		return totalPoints;
	}

	private double getOrderPoints(final AbstractOrderModel order)
	{
		double totalPoints = 0;
		for (final AbstractOrderEntryModel entry : order.getEntries())
		{
			totalPoints += getPoints(entry.getProduct()).doubleValue() * entry.getQuantity().longValue();
		}
		return totalPoints;
	}

	private double getPoints(final BonusSystemConfigModel configuration, final ProductModel product)
	{
		// get multiplier
		Double multiplier = null;
		if (configuration != null)
		{
			multiplier = configuration.getMultiplier();
		}
		else if (getBonusSystemCreationStrategy() != null)
		{
			final BonusSystemConfigModel defaultConfiguration = getBonusSystemCreationStrategy()
					.getDefaultBonusSystemConfiguration();
			if (defaultConfiguration != null)
			{
				multiplier = defaultConfiguration.getMultiplier();
			}
		}

		// get price
		final Collection<PriceRowModel> priceList = product.getEurope1Prices();
		if (CollectionUtils.isEmpty(priceList))
		{
			return 0;
		}
		final Double price = priceList.iterator().next().getPrice();

		// calculate points
		double points;
		if (price == null || multiplier == null)
		{
			points = 0;
		}
		else
		{
			points = (price.doubleValue() * multiplier.doubleValue());
		}
		return points;
	}

	private BonusSystemEntryModel createNewEntry(final AbstractOrderModel order, final String type, final double points,
			final BonusSystemDiscountModel discount)
	{
		final BonusSystemEntryModel entry = getModelService().create(BonusSystemEntryModel.class);
		entry.setDate(new Date());
		entry.setReference(order);
		entry.setType(type);
		entry.setPoints(Double.valueOf(points));
		entry.setAppliedDiscount(discount);
		switch (type)
		{
			case TYPE_REWARD:
				entry.setDescription(DESCRIPTION_ORDER_REWARD);
				break;
			case TYPE_DISCOUNT:
				entry.setDescription(DESCRIPTION_ORDER_DISCOUNT);
				break;
			case TYPE_CANCEL:
				entry.setDescription(DESCRIPTION_ORDER_CANCELLED);
				break;
			case TYPE_PLACEHOLDER:
				entry.setDescription(DESCRIPTION_ORDER_PLACEHOLDER);
			default:
				entry.setDescription("Custom entry type");
				break;
		}
		return entry;
	}

	// dependencies

	protected UserService getUserService()
	{
		return userService;
	}

	@Required
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	protected CartService getCartService()
	{
		return cartService;
	}

	@Required
	public void setCartService(final CartService cartService)
	{
		this.cartService = cartService;
	}

	protected BaseStoreService getBaseStoreService()
	{
		return baseStoreService;
	}

	@Required
	public void setBaseStoreService(final BaseStoreService baseStoreService)
	{
		this.baseStoreService = baseStoreService;
	}

	protected CustomerAccountService getCustomerAccoutService()
	{
		return customerAccoutService;
	}

	@Required
	public void setCustomerAccoutService(final CustomerAccountService customerAccoutService)
	{
		this.customerAccoutService = customerAccoutService;
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

	protected BonusSystemService getBonusSystemService()
	{
		return bonusSystemService;
	}

	@Required
	public void setBonusSystemService(final BonusSystemService bonusSystemService)
	{
		this.bonusSystemService = bonusSystemService;
	}

	protected BonusSystemConverter getBonusSystemConverter()
	{
		return bonusSystemConverter;
	}

	@Required
	public void setBonusSystemConverter(final BonusSystemConverter bonusSystemConverter)
	{
		this.bonusSystemConverter = bonusSystemConverter;
	}

	protected BonusSystemCreationStrategy getBonusSystemCreationStrategy()
	{
		return bonusSystemCreationStrategy;
	}

	@Autowired(required = false)
	public void setBonusSystemCreationStrategy(final BonusSystemCreationStrategy bonusSystemCreationStrategy)
	{
		this.bonusSystemCreationStrategy = bonusSystemCreationStrategy;
	}
}
