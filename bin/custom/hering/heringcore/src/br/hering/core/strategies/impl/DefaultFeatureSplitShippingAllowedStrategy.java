package br.hering.core.strategies.impl;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import br.hering.core.strategies.FeatureSplitShippingAllowedStrategy;
import br.hering.core.strategies.ShippingEstimatesStrategy;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;

public class DefaultFeatureSplitShippingAllowedStrategy implements FeatureSplitShippingAllowedStrategy 
{
	private BaseStoreService baseStoreService;
	
	private ShippingEstimatesStrategy shippingEstimatesStrategy;
	
	@Override
	public boolean isEnabledInBaseStore(final BaseStoreModel store) 
	{
		Assert.notNull(store);
		
		return store.isSplitOrderEntriesByDeliveryEnabled();
	}

	@Override
	public boolean isEnabledInCurrentBaseStore() 
	{
		final BaseStoreModel currentBaseStore = getBaseStoreService().getCurrentBaseStore();
		
		return isEnabledInBaseStore(currentBaseStore);
	}
	
	@Override
	public boolean isCartAllowedToUseSplitShipping(final AbstractOrderModel cart) 
	{
		Assert.notNull(cart);
		
		if(!isEnabledInBaseStore(cart.getStore()))
		{
			return false;
		}
		
		if(CollectionUtils.isNotEmpty(cart.getEntries()) && cart.getEntries().size() > 2)
		{
			Boolean found = null;
			boolean isImmediateShipping;
			for(AbstractOrderEntryModel entry : cart.getEntries())
			{
				isImmediateShipping = getShippingEstimatesStrategy().isImmediateShipping(entry);
				if(found != null && found.booleanValue() != isImmediateShipping)
				{
					return true;
				}
				else
				{
					found =  isImmediateShipping;
				}
			}
		}
		
		return false;
	}

	public BaseStoreService getBaseStoreService() 
	{
		return baseStoreService;
	}

	@Required
	public void setBaseStoreService(BaseStoreService baseStoreService) 
	{
		this.baseStoreService = baseStoreService;
	}

	public ShippingEstimatesStrategy getShippingEstimatesStrategy() 
	{
		return shippingEstimatesStrategy;
	}

	@Required
	public void setShippingEstimatesStrategy(ShippingEstimatesStrategy shippingEstimatesStrategy) 
	{
		this.shippingEstimatesStrategy = shippingEstimatesStrategy;
	}
}