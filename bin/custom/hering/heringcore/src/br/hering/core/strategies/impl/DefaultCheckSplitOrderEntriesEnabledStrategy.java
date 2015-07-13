package br.hering.core.strategies.impl;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;
import br.hering.core.strategies.CheckSplitOrderEntriesEnabledStrategy;

public class DefaultCheckSplitOrderEntriesEnabledStrategy implements CheckSplitOrderEntriesEnabledStrategy 
{
	private BaseStoreService baseStoreService;
	
	@Override
	public boolean isEnabled(final BaseStoreModel store) 
	{
		Assert.notNull(store);
		
		return store.isSplitOrderEntriesByDeliveryEnabled();
	}

	@Override
	public boolean isEnabled() 
	{
		final BaseStoreModel currentBaseStore = getBaseStoreService().getCurrentBaseStore();
		
		return isEnabled(currentBaseStore);
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
}