/**
 * 
 */
package br.flieger.exacttarget.events;

import de.hybris.platform.util.Config;

import br.flieger.exacttarget.wsdl.api.APIProperty;
import br.flieger.exacttarget.wsdl.api.Attribute;
import br.flieger.exacttarget.wsdl.api.Subscriber;

/**
 * @author Vinicius de Souza
 *
 */
@SuppressWarnings("serial")
public class ForgotPasswordEvent extends AbstractExacttargetEvent
{
	private String email, link, nome;

	public ForgotPasswordEvent(String email, String nome, String link, String baseStore)
	{
		this.email = email;
		this.nome = nome;
		this.link = link;
		this.baseStore = baseStore;
	}

	@Override
	public Attribute[] getAttributes()
	{
		final Attribute att1 = createAttribute("Nome", nome);
		final Attribute att2 = createAttribute("Link", link);
		return new Attribute[]{ att1, att2 };
	}

	@Override
	public Subscriber[] getSubscribers()
	{
		Subscriber sub = new Subscriber();
		sub.setEmailAddress(email);
		sub.setSubscriberKey(email);
		sub.setAttributes(getAttributes());
		
		return new Subscriber[] {sub};
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

	@Override
	public String getTrigger()
	{
		return Config.getParameter("exacttarget.forgot-password.trigger."+baseStore);
	}
}