/**
 * 
 */
package br.flieger.exacttarget.events;

import br.flieger.exacttarget.wsdl.api.APIProperty;
import br.flieger.exacttarget.wsdl.api.Attribute;

/**
 * @author flieger
 *
 */
@SuppressWarnings("serial")
@Deprecated
public class OrderInvoiceEvent extends AbstractExacttargetOrderEvent
{

	@Override
	public Attribute[] getAttributes()
	{
		final Attribute att1 = new Attribute("Name", getOrder().getUser().getName(), null);
		final Attribute att2 = new Attribute("Code", getOrder().getCode(), null);
		final Attribute att3 = new Attribute("Data", DATE_FORMAT.format(getOrder().getDate()), null);
		final Attribute att4 = new Attribute("Total", DECIAL_FORMAT.format(getOrder().getTotalPrice()), null);
		final Attribute att5 = new Attribute("PaymentMethod", getPaymentMethod(), null);
		
		return new Attribute[] {att1, att2, att3, att4, att5};
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