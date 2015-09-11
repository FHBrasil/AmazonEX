/**
 * 
 */
package br.flieger.exacttarget.events;

import de.hybris.platform.commercefacades.user.data.RegisterData;
import de.hybris.platform.core.enums.Gender;
import de.hybris.platform.util.Config;

import br.flieger.exacttarget.wsdl.api.APIProperty;
import br.flieger.exacttarget.wsdl.api.Attribute;
import br.flieger.exacttarget.wsdl.api.Subscriber;

/**
 * @author Vinicius de Souza
 *
 */
@SuppressWarnings("serial")
public class CustomerRegisterEvent extends AbstractExacttargetEvent
{
	private RegisterData registerData;
	
	/**
	 * @param data
	 */
	public CustomerRegisterEvent(RegisterData data)
	{
		this.registerData = data;
		this.baseStore = data.getBaseStore();
	}
	
	public Attribute[] getAttributes()
	{		
		final Attribute att1 = createAttribute("Nome", registerData.getFirstName()+" "+registerData.getLastName());
		final Attribute att2 = createAttribute("DataNasc", registerData.getBirthday());
		final Attribute att3 = createAttribute("Cpf", registerData.getCpfcnpj());
//		final Attribute att4 = createAttribute("Sexo", registerData.getGender().compareTo(Gender.MALE)==0?"Masculino":"Feminino");
//		final Attribute att5 = createAttribute("Data de Nascimento", registerData.getBirthday());		
//		final Attribute att6 = createAttribute("Genero", registerData.getGender().name());
		
		return new Attribute[]{att1, att3, att2/*, att4, att5, att6*/};
	}

	@Override
	public Subscriber[] getSubscribers()
	{
		Subscriber s = new Subscriber();
		s.setSubscriberKey(registerData.getLogin());
		s.setEmailAddress(registerData.getLogin());
		s.setAttributes(getAttributes());
		
		return new Subscriber[] {s};
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
		return Config.getParameter("exacttarget.customer-register-trigger."+baseStore);
	}
}