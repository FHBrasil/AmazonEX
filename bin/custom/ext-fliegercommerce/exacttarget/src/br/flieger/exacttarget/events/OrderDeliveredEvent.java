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
public class OrderDeliveredEvent extends AbstractExacttargetOrderEvent
{
	@Override
	public Attribute[] getAttributes()
	{
		final Attribute att1 = createAttribute("Nome", getOrder().getUser().getName());
		final Attribute att2 = createAttribute("Pedido", getOrder().getCode());
		
		return new Attribute[]{att1, att2};
	}

	@Override
	public APIProperty[] getAPIProperty()
	{
		return null;
	}

	/* (non-Javadoc)
	 * @see br.flieger.exacttarget.events.AbstractExacttargetEvent#getLinkTag()
	 */
	@Override
	protected String getLinkTag()
	{
		return "";
	}
}