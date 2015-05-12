/**
 * 
 */
package br.flieger.exacttarget.events;

import de.hybris.platform.core.enums.Gender;
import de.hybris.platform.util.Config;

import br.flieger.exacttarget.wsdl.api.APIProperty;
import br.flieger.exacttarget.wsdl.api.Attribute;
import br.flieger.exacttarget.wsdl.api.Subscriber;

import com.flieger.data.NewsletterSubscriberData;

/**
 * @author Vinicius de Souza
 *
 */
public class NewsletterEvent extends AbstractExacttargetEvent
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private NewsletterSubscriberData newsletterData;

	/**
	 * 
	 */
	public NewsletterEvent(final NewsletterSubscriberData data)
	{
		this.newsletterData = data;
		this.baseStore = data.getBaseStore();
	}

	@Override
	public Attribute[] getAttributes()
	{
		//Propriedades a serem enviadas.
		final Attribute p1 = createAttribute("Nome", newsletterData.getName());
		final Attribute p2 = createAttribute("Name", newsletterData.getName());
		final Attribute p3 = createAttribute("Gender", newsletterData.getGender().compareTo(Gender.MALE)==0?"Masculino":"Feminino");
		final Attribute p4 = createAttribute("EmailKey", newsletterData.getEmail());
		LOG.info("Parâmetros: "+p1.getValue()+"/"+p2.getValue()+"/"+p3.getValue()+"/"+p4.getValue()+"/");
		return new Attribute[]{p1, p2, p3, p4};
	}

	@Override
	public Subscriber[] getSubscribers()
	{
		Subscriber subscriber1 = new Subscriber();

      subscriber1.setEmailAddress(newsletterData.getEmail());
      subscriber1.setSubscriberKey(newsletterData.getEmail());
      
      subscriber1.setAttributes(getAttributes());
      
		return new Subscriber[] {subscriber1};
	}

	/**
	 * @return Flag.
	 */
	public boolean isReceive()
	{
		return newsletterData.getReceive();
	}

	@Override
	public APIProperty[] getAPIProperty()
	{
		APIProperty p = new APIProperty("EmailKey", newsletterData.getEmail());
		return new APIProperty[]{ p };
	}

	/* (non-Javadoc)
	 * @see br.flieger.exacttarget.events.AbstractExacttargetEvent#getLinkTag()
	 */
	@Override
	protected String getLinkTag()
	{
		return "";
	}

	/* (non-Javadoc)
	 * @see br.flieger.exacttarget.events.AbstractExacttargetEvent#getTrigger()
	 */
	@Override
	public String getTrigger()
	{
		return Config.getParameter("exacttarget.newletter.trigger."+getBaseStore());
	}

	public String getData()
	{
		return Config.getParameter("exacttarget.newletter.data."+getBaseStore());
	}
}