/**
 * 
 */
package br.flieger.exacttarget.events;

import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.util.Config;

import java.util.Set;

import br.flieger.exacttarget.wsdl.api.APIProperty;
import br.flieger.exacttarget.wsdl.api.Attribute;
import br.flieger.exacttarget.wsdl.api.Subscriber;

/**
 * @author Vinicius de Souza
 *
 */
@SuppressWarnings("serial")
public class NotifyMeAbandonedCartEvent extends AbstractExacttargetEvent
{
	private Set<CartModel> carts;
	
	public NotifyMeAbandonedCartEvent(Set<CartModel> carts)
	{
		this.carts = carts;
		this.baseStore = carts.iterator().next().getSite().getStores().iterator().next().getUid();
	}
	
	public Attribute[] getAttributes(final CartModel cart)
	{
		final Attribute name = createAttribute("Nome", cart.getUser().getName());
		final Attribute link = createAttribute("Link", "<a href=\""+Config.getParameter("exacttarget.site."+baseStore)+"/pt/cart"+getLinkTag()+"\" class=\"cart\">");
		final Attribute pedido = createAttribute("Pedido", cart.getCode());
		final Attribute totalPedido = createAttribute("TotalPedido", cart.getTotalPrice());
		final Attribute dataPedido = createAttribute("DataPedido", cart.getDate());
		
		return new Attribute[] {name, link, pedido, totalPedido, dataPedido};
	}
	
	@Override
	public Attribute[] getAttributes()
	{
		return null;
	}
	
	public Subscriber[] getSubscribers()
	{
		if(carts != null && carts.size() > 0)
		{
			final Subscriber[] subscribers = new Subscriber[carts.size()];
			
			int i = 0;
			for (CartModel cart : carts)
			{
				final Subscriber s = new Subscriber();

				String auxEmail = cart.getUser().getUid();

				String email = auxEmail.replaceAll("[a-z0-9\\-]*\\|", "");
				s.setEmailAddress(email);
				s.setSubscriberKey(email);

				s.setAttributes(getAttributes(cart));
				subscribers[i] = s;

				i++;
			}
			return subscribers;
		}
		
		return null;
	}

	@Override
	public APIProperty[] getAPIProperty()
	{
		return null;
	}

	@Override
	protected String getLinkTag()
	{
		return Config.getParameter("exacttarget.tag.notifyme-abandoned-cart");
	}

	@Override
	public String getTrigger()
	{
		return Config.getParameter("exacttarget.notifyme-abandoned-cart.trigger."+baseStore);
	}
}