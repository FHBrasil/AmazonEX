/**
 * 
 */
package br.hering.core.order.impl;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.order.CommerceCartRestoration;
import de.hybris.platform.commerceservices.order.CommerceCartRestorationException;
import de.hybris.platform.commerceservices.order.impl.DefaultCommerceCartService;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.storelocator.model.PointOfServiceModel;

/**
 * @author herbert
 *
 */
public class HeringCommerceCartService extends DefaultCommerceCartService
{
	@Override
	public CartModel getCartForGuidAndSiteAndUser(String guid, BaseSiteModel site, UserModel user)
	{
		return super.getCartForGuidAndSiteAndUser(guid, getBaseSiteService().getBaseSiteForUID(HeringCartConstants.COMMON_SITE), user);
	}

	@Override
	public CommerceCartRestoration restoreCart(CartModel oldCart) throws CommerceCartRestorationException
	{
		if(oldCart != null) {
			BaseSiteModel originalSite = oldCart.getSite();
			try
			{
				oldCart.setSite(getBaseSiteService().getCurrentBaseSite());
				return super.restoreCart(oldCart);
			} finally {
				oldCart.setSite(originalSite);
				getModelService().save(oldCart);
			}
		}
		return super.restoreCart(oldCart);
	}

	@Override
	protected long getAvailableStockLevel(ProductModel productModel, PointOfServiceModel pointOfServiceModel)
	{
		BaseStoreModel baseStore = null;
		for(BaseStoreModel store : productModel.getCatalogVersion().getCatalog().getBaseStores()) {
			baseStore = store;
		}
		if (!getCommerceStockService().isStockSystemEnabled(baseStore))
		{
			return getForceInStockMaxQuantity();
		}
		Long availableStockLevel = null;
		if (pointOfServiceModel == null)
		{
			availableStockLevel = getCommerceStockService().getStockLevelForProductAndBaseStore(productModel, baseStore);
		}
		else
		{
			availableStockLevel = getCommerceStockService().getStockLevelForProductAndPointOfService(productModel, pointOfServiceModel);
		}

		if (availableStockLevel == null)
		{
			return getForceInStockMaxQuantity();
		}

		return availableStockLevel.longValue();
	}
}