/**
 * 
 */
package br.hering.core.order.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import de.hybris.platform.acceleratorservices.order.impl.DefaultCartServiceForAccelerator;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.order.CommerceCartService;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.RelationQuery;
import de.hybris.platform.servicelayer.search.RelationQuery.ORDERING;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

/**
 * @author herbert
 *
 */
public class HeringCartService extends DefaultCartServiceForAccelerator
{
	private static final Logger LOG = Logger.getLogger(HeringCartService.class);

	private UserService userService;

	private FlexibleSearchService flexibleSearchService;

//	private CommerceCartService commerceCartService;

	@Override
	public void changeCurrentCartUser(UserModel user)
	{
		validateParameterNotNull(user, "user must not be null!");
		if (hasSessionCart()) {
			final CartModel sessionCart = getSessionCart();
			if (getUserService().isAnonymousUser(sessionCart.getUser()) && !getUserService().isAnonymousUser(user)) {
				CartModel userCart = getUserCart(user, sessionCart.getSite());
				if(userCart != null && userCart != sessionCart) {
					mergeCarts(userCart, sessionCart);
					setSessionCart(userCart);
					getModelService().save(userCart);
					getModelService().remove(sessionCart);
					return;
				}
			}
			super.changeCurrentCartUser(user);
		}
	}

	protected CartModel getUserCart(UserModel user, BaseSiteModel site) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user", user);
		params.put("site", site);
		SearchResult<CartModel> result = getFlexibleSearchService().search("SELECT {pk} FROM {Cart} WHERE {user} = ?user AND {site} = ?site ORDER BY {modifiedtime} DESC", params);
		if (result.getTotalCount() > 0) {
			CartModel cart = result.getResult().get(0);
			if (cart != getSessionCart()) {
				return cart;
			} else if (result.getTotalCount() > 1) {
				return result.getResult().get(1);
			}
		}
		return null;
	}

	protected void mergeCarts(CartModel userCart, CartModel sessionCart) {
		for(AbstractOrderEntryModel entry : sessionCart.getEntries())
		{
			if(!cartContainProduct(userCart, entry.getProduct().getCode())){
				addNewEntry(userCart, entry.getProduct(), entry.getQuantity().longValue(), entry.getUnit());
			}
		}
	}

	public boolean cartContainProduct(CartModel cart, String productId){
		for(AbstractOrderEntryModel entry : cart.getEntries()){
			if(entry.getProduct().getCode().equals(productId)){
				return true;
			}
		}
		return false;
	}

	protected UserService getUserService()
	{
		return userService;
	}

	@Required
	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}

	protected FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}

	@Required
	public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}

//	protected CommerceCartService getCommerceCartService()
//	{
//		return commerceCartService;
//	}
//
//	@Required
//	public void setCommerceCartService(CommerceCartService commerceCartService)
//	{
//		this.commerceCartService = commerceCartService;
//	}
}