/**
 * 
 */
package br.flieger.exacttarget.events;

import br.flieger.exacttarget.wsdl.api.APIProperty;
import br.flieger.exacttarget.wsdl.api.Attribute;

/**
 * @author Vinicius de Souza
 *
 */
@SuppressWarnings("serial")
public class OrderCancelledEvent extends AbstractExacttargetOrderEvent
{
	@Override
	public Attribute[] getAttributes()
	{
		final Attribute att1 = new Attribute("Nome", getOrder().getUser().getName(), null);
		final Attribute att2 = new Attribute("Pedido", getOrder().getCode(), null);
		final Attribute att3 = new Attribute("DataPedido", DATE_FORMAT.format(getOrder().getDate()), null);
		final Attribute att4 = new Attribute("TotalPedido", DECIAL_FORMAT.format(getOrder().getTotalPrice()), null);
		
		return new Attribute[] {att1, att2, att3, att4};
	}

	@Override
	public APIProperty[] getAPIProperty()
	{
		return null;
	}

	@Override
	protected String getLinkTag()
	{
		return "";
	}
}