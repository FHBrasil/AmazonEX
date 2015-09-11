package br.hering.core.strategies;

import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.store.BaseStoreModel;

public interface FeatureSplitShippingAllowedStrategy 
{
	boolean isEnabledInBaseStore(BaseStoreModel store);
	
	boolean isEnabledInCurrentBaseStore();
	
	boolean isCartAllowedToUseSplitShipping(AbstractOrderModel abstractOrder);
}