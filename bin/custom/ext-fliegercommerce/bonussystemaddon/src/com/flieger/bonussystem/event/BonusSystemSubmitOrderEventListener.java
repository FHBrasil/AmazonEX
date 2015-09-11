/**
 *
 */
package com.flieger.bonussystem.event;

import de.hybris.platform.order.CartService;
import de.hybris.platform.order.events.SubmitOrderEvent;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;

import org.springframework.beans.factory.annotation.Required;

import com.flieger.bonussystem.facade.BonusSystemFacade;


/**
 * @author herbert
 * 
 */
public class BonusSystemSubmitOrderEventListener extends AbstractEventListener<SubmitOrderEvent>
{

	private CartService cartService;
	private BonusSystemFacade bonusSystemFacade;

	@Override
	protected void onEvent(final SubmitOrderEvent event)
	{
		// after cart is converted to order, change entry's reference
		bonusSystemFacade.changeFromCartToOrder(cartService.getSessionCart(), event.getOrder());
	}

	protected CartService getCartService()
	{
		return cartService;
	}

	@Required
	public void setCartService(final CartService cartService)
	{
		this.cartService = cartService;
	}

	protected BonusSystemFacade getBonusSystemFacade()
	{
		return bonusSystemFacade;
	}

	@Required
	public void setBonusSystemFacade(final BonusSystemFacade bonusSystemFacade)
	{
		this.bonusSystemFacade = bonusSystemFacade;
	}
}
