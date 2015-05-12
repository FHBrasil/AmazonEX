/**
 * 
 */
package br.hering.core.strategies.impl;

import de.hybris.platform.commerceservices.order.CommerceCartModification;
import de.hybris.platform.commerceservices.strategies.impl.DefaultCartValidationStrategy;
import de.hybris.platform.core.model.order.CartEntryModel;
import de.hybris.platform.core.model.order.CartModel;

/**
 * @author herbert
 *
 */
public class HeringCartValidationStrategy extends DefaultCartValidationStrategy
{

	
	@Override
	protected CommerceCartModification validateCartEntry(CartModel cartModel, CartEntryModel cartEntryModel)
	{
		Long stockLevel = getStockLevel(cartEntryModel);
		long cartLevel = getCartLevel(cartEntryModel, cartModel);
		long cartEntryLevel = cartEntryModel.getQuantity().longValue();

		Long stockLevelForProductInBaseStore = null;
		long newOrderEntryLevel;
		if (stockLevel != null)
		{
			if (isProductNotAvailableInPOS(cartEntryModel, stockLevel))
			{
				stockLevelForProductInBaseStore = getCommerceStockService().getStockLevelForProductAndBaseStore(
						cartEntryModel.getProduct(), getBaseStoreService().getCurrentBaseStore());
				if (stockLevelForProductInBaseStore != null)
				{
					newOrderEntryLevel = Math.min(cartEntryLevel, stockLevelForProductInBaseStore.longValue());
				}
				else
				{
					newOrderEntryLevel = Math.min(cartEntryLevel, cartLevel);
				}
			}
			else
			{
				newOrderEntryLevel = Math.min(cartEntryLevel, stockLevel.longValue());
			}

		}
		else
		{
			newOrderEntryLevel = Math.min(cartEntryLevel, cartLevel);
		}

		if ((stockLevelForProductInBaseStore != null) && (stockLevelForProductInBaseStore.longValue() != 0L))
		{
			CommerceCartModification modification = new CommerceCartModification();
			modification.setStatusCode("movedFromPOSToStore");
			CartEntryModel existingEntryForProduct = getExistingShipCartEntryForProduct(cartModel, cartEntryModel.getProduct());
			if (existingEntryForProduct != null)
			{
				getModelService().remove(cartEntryModel);
				long quantityAdded = stockLevelForProductInBaseStore.longValue() >= cartLevel ? newOrderEntryLevel : cartLevel - 
						stockLevelForProductInBaseStore.longValue();
				modification.setQuantityAdded(quantityAdded);
				long updatedQuantity = stockLevelForProductInBaseStore.longValue() <= cartLevel ? stockLevelForProductInBaseStore
						.longValue() : cartLevel;
						modification.setQuantity(updatedQuantity);
						existingEntryForProduct.setQuantity(Long.valueOf(updatedQuantity));
						getModelService().save(existingEntryForProduct);
						modification.setEntry(existingEntryForProduct);
			}
			else
			{
				modification.setQuantityAdded(newOrderEntryLevel);
				modification.setQuantity(cartEntryLevel);
				cartEntryModel.setDeliveryPointOfService(null);
				modification.setEntry(cartEntryModel);
				getModelService().save(cartEntryModel);
			}

			getModelService().refresh(cartModel);

			return modification;
		}
		if (((stockLevel != null) && (stockLevel.longValue() <= 0L)) || (newOrderEntryLevel < 0L))
		{

			CommerceCartModification modification = new CommerceCartModification();
			modification.setStatusCode("noStock");
			modification.setQuantityAdded(0L);
			modification.setQuantity(cartEntryLevel);
			CartEntryModel entry = new CartEntryModel();
			entry.setProduct(cartEntryModel.getProduct());
			modification.setEntry(entry);
			getModelService().remove(cartEntryModel);
			getModelService().refresh(cartModel);

			return modification;
		}
		if (cartEntryLevel != newOrderEntryLevel)
		{

			CommerceCartModification modification = new CommerceCartModification();
			modification.setStatusCode("lowStock");
			modification.setQuantityAdded(newOrderEntryLevel);
			modification.setQuantity(cartEntryLevel);
			modification.setEntry(cartEntryModel);
			cartEntryModel.setQuantity(Long.valueOf(newOrderEntryLevel));
			getModelService().save(cartEntryModel);
			getModelService().refresh(cartModel);

			return modification;
		}


		CommerceCartModification modification = new CommerceCartModification();
		modification.setStatusCode("success");
		modification.setQuantityAdded(cartEntryLevel);
		modification.setQuantity(cartEntryLevel);
		modification.setEntry(cartEntryModel);

		return modification;
	}
}
