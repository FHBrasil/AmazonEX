/**
 * 
 */
package br.flieger.exacttarget.events;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.util.Config;

import java.util.Date;
import java.util.Set;

import br.flieger.exacttarget.wsdl.api.APIProperty;
import br.flieger.exacttarget.wsdl.api.Attribute;
import br.flieger.exacttarget.wsdl.api.Subscriber;

/**
 * @author Vinicius de Souza
 *
 */
@SuppressWarnings("serial")
public class NotifyMePaymentEvent extends AbstractExacttargetEvent
{
	private Set<OrderModel> orders;
	private String urlSite;
	
	/**
	 * @param orders
	 * @param expirationDate
	 */
	public NotifyMePaymentEvent(final Set<OrderModel> orders)
	{
		this.orders = orders;
		this.baseStore = orders.iterator().next().getSite().getStores().iterator().next().getUid();
		this.urlSite = Config.getParameter("exacttarget.site."+baseStore)+"/pt/"+getLinkTag();
	}

	@Override
	public Attribute[] getAttributes()
	{
		return null;
	}
	
	public Attribute[] getAttributes(final OrderModel order)
	{
		final Attribute att1 = createAttribute("Nome", order.getUser().getName());
		final Attribute att2 = createAttribute("Pedido", order.getCode());
		//final Attribute att3 = createAttribute("Expiration", expirationDate);
		final Attribute att4 = createAttribute("TotalPedido", order.getTotalPrice());
		final Attribute att5 = createLinkPayment(order);
		final Attribute att6 = createAttribute("DataPedido", order.getDate());
		
		return new Attribute[]{att1, att2, att4, att5, att6};
	}

	/**
	 * @param order 
	 * @return Link de acesso ao boleto de pagamento.
	 */
	private Attribute createLinkPayment(OrderModel order)
	{
		final String baseStore = order.getStore().getUid();
		final String site = Config.getParameter("exacttarget.site."+baseStore)+"/pt/"+getLinkTag();
		return createAttribute("Link", "<a href=\""+site.replaceAll("%%ORDER_CODE%%", order.getCode())+"\">");
	}

	@Override
	public APIProperty[] getAPIProperty()
	{
		if(orders != null && orders.size() > 0)
		{
			final APIProperty[] props = new APIProperty[orders.size()];
			int i = 0;
			for (OrderModel order : orders)
			{
				props[i] = new APIProperty("Pedido", order.getCode());
				i++;
			}
			
			return props;
		}
		
		return null;
	}

	@Override
	public Subscriber[] getSubscribers()
	{
		if(orders != null && orders.size() > 0)
		{
			final Subscriber[] subscribers = new Subscriber[orders.size()];
			int i = 0;
			for (OrderModel order : orders)
			{
				final Subscriber s = new Subscriber();
				
				String auxEmail = order.getUser().getUid();

				String email = auxEmail.replaceAll("[a-z0-9\\-]*\\|", "");
				s.setEmailAddress(email);
				s.setSubscriberKey(email);

				s.setAttributes(getAttributes(order));
				
				subscribers[i] = s;
				i++;
			}
			return subscribers;
		}		
		return null;
	}

	@Override
	protected String getLinkTag()
	{
		return Config.getParameter("exacttarget.tag.notifyme-payment");
	}

	@Override
	public String getTrigger()
	{
		return Config.getParameter("exacttarget.notifyme-payment.trigger."+baseStore);
	}

	public String getData()
	{
		return Config.getParameter("exacttarget.notifyme-payment.data."+baseStore);
	}
}