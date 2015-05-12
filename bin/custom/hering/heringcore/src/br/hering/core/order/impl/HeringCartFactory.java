/**
 * 
 */
package br.hering.core.order.impl;

import de.hybris.platform.commerceservices.order.CommerceCartService;
import de.hybris.platform.commerceservices.order.impl.CommerceCartFactory;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.servicelayer.user.UserService;

import org.springframework.beans.factory.annotation.Required;


/**
 * @author herbert
 * 
 */
public class HeringCartFactory extends CommerceCartFactory
{
	private UserService userService;
	private CommerceCartService commerceCartService;

	@Override
	protected CartModel createCartInternal()
	{
		CartModel cart = null;
		if(!getUserService().isAnonymousUser(getUserService().getCurrentUser())) {
			cart = getCommerceCartService().getCartForGuidAndSiteAndUser(null, getBaseSiteService().getCurrentBaseSite(), getUserService().getCurrentUser());
			if(cart != null)
				return cart;
		}
		cart = super.createCartInternal();
		cart.setSite(getBaseSiteService().getBaseSiteForUID(HeringCartConstants.COMMON_SITE));
		cart.setStore(getBaseStoreService().getBaseStoreForUid(HeringCartConstants.COMMON_STORE));
		return cart;
	}

	protected UserService getUserService()
	{
		return userService;
	}

	@Override
	public void setUserService(UserService userService)
	{
		this.userService = userService;
		super.setUserService(userService);
	}

	protected CommerceCartService getCommerceCartService()
	{
		return commerceCartService;
	}

	@Required
	public void setCommerceCartService(CommerceCartService commerceCartService)
	{
		this.commerceCartService = commerceCartService;
	}
}
