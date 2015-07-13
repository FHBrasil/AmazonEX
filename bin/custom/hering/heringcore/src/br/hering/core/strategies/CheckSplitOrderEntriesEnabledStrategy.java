package br.hering.core.strategies;

import de.hybris.platform.store.BaseStoreModel;

public interface CheckSplitOrderEntriesEnabledStrategy 
{
	boolean isEnabled(BaseStoreModel store);
	
	boolean isEnabled();
}