/**
 * 
 */
package br.flieger.exacttarget.events;

import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.util.Config;
import de.hybris.platform.util.PriceValue;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import com.adyen.services.payment.impl.AdyenCardInstallmentsService;

import br.flieger.exacttarget.wsdl.api.Subscriber;

/**
 * @author Vinicius de Souza
 *
 */
public abstract class AbstractExacttargetOrderEvent extends AbstractExacttargetEvent
{
	@Resource
	protected AdyenCardInstallmentsService adyenCardInstallmentsService;
	
	private OrderModel order;
	
	/**
	 * @param order the order to set
	 */
	public void setOrder(OrderModel order)
	{
		this.order = order;
		this.baseStore = order.getStore().getUid();
	}
	
	protected OrderModel getOrder()
	{
		return this.order;
	}
	
	public OrderStatus getOrderStatus()
	{
		if(order != null)
		{
			return order.getStatus();
		}
		return null;
	}
	
	@Override
	public Subscriber[] getSubscribers()
	{
		final Subscriber s = new Subscriber();
		
		if(order != null)
		{
			String auxEmail = order.getUser().getUid();
			
			String email = auxEmail.replaceAll("[a-z0-9\\-]*\\|", "");
			s.setEmailAddress(email);
			s.setSubscriberKey(email);
			
			s.setAttributes(getAttributes());
		}
		
		return new Subscriber[] {s};
	}
	
	/**
	 * @return
	 */
	protected Double getTotalProdutos()
	{
		Double total = 0d;
		
		for(AbstractOrderEntryModel entry : getOrder().getEntries())
		{
			total  += entry.getTotalPrice();
		}
		
		return total;
	}

	/**
	 * @return Retorna o Método de Pagamento.
	 */
	protected String getPaymentMethod()
	{
		return getOrder().getPaymentInfo() instanceof CreditCardPaymentInfoModel?"Cartão de Crédito":"Boleto Bancário";
	}
	
	/**
	 * @param paymentInfo
	 * @return Numero de parcelas.
	 */
	protected String getQuantityParc()
	{
		String qtde = "1";
		if(getOrder().getPaymentInfo() instanceof CreditCardPaymentInfoModel)
		{
			qtde = ((CreditCardPaymentInfoModel) getOrder().getPaymentInfo()).getInstallment()+"";
		}
		return qtde;
	}
	
	/**
	 * 
	 * @param order
	 * @return
	 */
	protected String getPriceParc()
	{
		String currencyIso = order.getCurrency().getIsocode();
		boolean net = order.getNet().booleanValue();
		double priceValue = order.getTotalPrice().doubleValue();
		
		final PriceValue productPrice = new PriceValue(currencyIso, priceValue, net);
		List<String> installments = adyenCardInstallmentsService.formatInstallmentsCost(productPrice);
		
		int qtdeParc = Integer.parseInt(getQuantityParc());
	
		return installments.get(qtdeParc - 1);
	}
	
	/* (non-Javadoc)
	 * @see br.flieger.exacttarget.events.AbstractExacttargetEvent#getTrigger()
	 */
	@Override
	public String getTrigger()
	{
		if(order != null)
		{
			String trigger = null;
			switch (order.getStatus())
			{
				case CANCELLED:
					trigger = Config.getParameter("exacttarget.order-cancelled.trigger."+baseStore);
					break;
				case CHECKED_VALID:
					trigger = Config.getParameter("exacttarget.order-confirmed.trigger."+baseStore);
					break;
				case DISPATCHED:
					trigger = Config.getParameter("exacttarget.order-dispatched.trigger."+baseStore);
					break;
				case COMPLETED:
					trigger = Config.getParameter("exacttarget.order-payed.trigger."+baseStore);
					break;
				case DELIVERED:
					trigger = Config.getParameter("exacttarget.order-delivered.trigger."+baseStore);;
					break;
				default:
					LOG.info("Status " + order.getStatus().name() + " hasn't defined to send e-mail.");
			}
			return trigger;
		}
		return null;
	}
}