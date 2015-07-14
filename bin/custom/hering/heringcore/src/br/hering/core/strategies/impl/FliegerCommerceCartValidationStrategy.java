package br.hering.core.strategies.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import br.hering.core.strategies.FeatureSplitShippingAllowedStrategy;
import de.hybris.platform.commerceservices.order.CommerceCartModification;
import de.hybris.platform.commerceservices.strategies.impl.DefaultCartValidationStrategy;
import de.hybris.platform.core.model.order.CartModel;

public class FliegerCommerceCartValidationStrategy extends DefaultCartValidationStrategy 
{
	public static final Logger LOG = Logger.getLogger(FliegerCommerceCartValidationStrategy.class);
	
	private FeatureSplitShippingAllowedStrategy featureSplitShippingAllowedStrategy;
	
	@Override
	public List<CommerceCartModification> validateCart(final CartModel cartModel) 
	{
		List<CommerceCartModification> modifications = super.validateCart(cartModel);
		
		if(cartModel.isSplitDeliveryEnabled())
		{
			if(CollectionUtils.isEmpty(cartModel.getEntries()))
			{
				LOG.debug(String.format("Cart %s is empty, turning off SplitDeliveryEnabled flag", cartModel.getCode()));
				
				modifications.add(createDisabledSplitDeliveryModification());
				turnOffCartSplitDeliveryOption(cartModel);
				
				return modifications;
			}
			
			if(!getFeatureSplitShippingAllowedStrategy().isEnabledInBaseStore(cartModel.getStore()))
			{
				LOG.debug(String.format("Store %s does not support delivery splitting, turning off SplitDeliveryEnabled flag for cart %s", 
						cartModel.getStore().getUid(), cartModel.getCode()));
				
				modifications.add(createDisabledSplitDeliveryModification());
				turnOffCartSplitDeliveryOption(cartModel);
				
				return modifications;
			}
			
			if(!getFeatureSplitShippingAllowedStrategy().isCartAllowedToUseSplitShipping(cartModel))
			{
				LOG.debug(String.format("Cart %s is not allowed to use the splitting feature, turning off SplitDeliveryEnabled flag for cart %s", 
						cartModel.getStore().getUid(), cartModel.getCode()));
				
				modifications.add(createDisabledSplitDeliveryModification());
				turnOffCartSplitDeliveryOption(cartModel);
				
				return modifications;
			}
		}
		
		return modifications;
	}

	private CommerceCartModification createDisabledSplitDeliveryModification() 
	{
		final CommerceCartModification modification = new CommerceCartModification();
		modification.setStatusCode("splitdeliverydisabled");
		modification.setDeliveryModeChanged(true);
	
		return modification;
	}

	private void turnOffCartSplitDeliveryOption(final CartModel cartModel) 
	{
		cartModel.setSplitDeliveryEnabled(false);
		getModelService().save(cartModel);
		getModelService().refresh(cartModel);
	}

	public FeatureSplitShippingAllowedStrategy getFeatureSplitShippingAllowedStrategy() {
		return featureSplitShippingAllowedStrategy;
	}

	@Required
	public void setFeatureSplitShippingAllowedStrategy(
			FeatureSplitShippingAllowedStrategy featureSplitShippingAllowedStrategy) {
		this.featureSplitShippingAllowedStrategy = featureSplitShippingAllowedStrategy;
	}
}