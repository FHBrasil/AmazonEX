/**
 * 
 */
package br.hering.facades.cart;

import de.hybris.platform.commercefacades.order.data.CartModificationData;
import de.hybris.platform.commercefacades.order.data.CartRestorationData;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.commercefacades.order.impl.DefaultCartFacade;
import de.hybris.platform.commerceservices.order.CommerceCartModification;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.commerceservices.order.CommerceCartRestorationException;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.store.services.BaseStoreService;
import de.hybris.platform.europe1.jalo.PriceRow;
import de.hybris.platform.europe1.model.PriceRowModel;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

/**
 * @author guilhermeshayashi
 *
 */
public class DefaultHeringCartFacade extends DefaultCartFacade
{
	private static final Logger LOG = Logger.getLogger(DefaultHeringCartFacade.class);
	
	@Resource
	private UserService userService;
	
	@Resource
	private BaseStoreService baseStoreService;
	
	@Override
	public CartModificationData addToCart(final String code, final long quantity) throws CommerceCartModificationException
	{
		final ProductModel product = getProductService().getProductForCode(code);
		
		if(!verifyPrices(product)){
			throw new CommerceCartModificationException("Doesn't exist, at least, one price bigger than zero.");
		}
		
		final CartModel cartModel = getCartService().getSessionCart();

		final CommerceCartModification modification = getCommerceCartService().addToCart(cartModel, product, quantity,
				product.getUnit(), false);

		return getCartModificationConverter().convert(modification);
	}
	
	private boolean verifyPrices(ProductModel product){
		boolean isValid = false;
		for(PriceRowModel price : product.getEurope1Prices()){
			if(price.getPrice().doubleValue() > 0){
				isValid = true;
			}
		}
		return isValid;
	}
}
