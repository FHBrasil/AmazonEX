/**
 * 
 */
package br.flieger.exacttarget.services.impl;

import de.hybris.platform.core.enums.Gender;

import java.util.List;

import org.apache.log4j.Logger;
import org.fest.util.Collections;

import br.flieger.exacttarget.services.ExacttargetSyncNewsletterService;
import br.flieger.exacttarget.wsdl.Util;
import br.flieger.exacttarget.wsdl.api.APIProperty;
import br.flieger.exacttarget.wsdl.api.CreateOptions;
import br.flieger.exacttarget.wsdl.api.CreateRequest;
import br.flieger.exacttarget.wsdl.api.CreateResponse;
import br.flieger.exacttarget.wsdl.api.CreateResult;
import br.flieger.exacttarget.wsdl.api.DataExtensionObject;
import br.flieger.exacttarget.wsdl.api.Soap;

import com.flieger.data.NewsletterSubscriberData;

/**
 * @author Vinicius de Souza
 *
 */
public class DefaultExacttargetSyncNewsletterService implements ExacttargetSyncNewsletterService
{
	private static final Logger LOG = Logger.getLogger(DefaultExacttargetSyncNewsletterService.class);
	
	@Override
	public void syncNewsletter(List<NewsletterSubscriberData> listNewsletters) throws Exception
	{
		if(!Collections.isEmpty(listNewsletters))
		{
			Soap soap = Util.initSoap();
			
			final CreateOptions opCreate = new CreateOptions();
			
			final CreateRequest opRequest = new CreateRequest(opCreate, createDataExtensionObjects(listNewsletters));
			
			final CreateResponse response = soap.create(opRequest);
			
			for (CreateResult result : response.getResults())
			{
				LOG.info("RESULT::" + result.getErrorCode() + "::" + result.getStatusCode() + "::" + result.getStatusMessage()+"::"+result.getErrorCode()+"::"+((DataExtensionObject) result.getObject()).getKeys()[0]);
			}
		}
	}

	/**
	 * @param listNewsletters
	 * @return
	 */
	private DataExtensionObject[] createDataExtensionObjects(List<NewsletterSubscriberData> listNewsletters)
	{
		final DataExtensionObject [] dataObjects = new DataExtensionObject[listNewsletters.size()];
		
		int count = 0;
		
		for (NewsletterSubscriberData data : listNewsletters)
		{
			final DataExtensionObject obj = new DataExtensionObject();
			
			obj.setCustomerKey("NEWSLETTER_DATA");
			obj.setKeys(new APIProperty[]{new APIProperty("EmailKey", data.getEmail())});
			obj.setProperties(createAPIProperty(data));
			
			dataObjects[count] = obj;
			count++;
		}
		
		return dataObjects;
	}

	/**
	 * @param data
	 * @return
	 */
	private APIProperty[] createAPIProperty(NewsletterSubscriberData data)
	{
		final APIProperty subscriberKey = new APIProperty("SubscriberKey", data.getEmail());
		final APIProperty emailAddress = new APIProperty("EmailAddress", data.getEmail());
		final APIProperty name = new APIProperty("Name", data.getName());
		final APIProperty gender = new APIProperty("Gender", data.getGender().compareTo(Gender.MALE)==0?"Masculino":"Feminino");
		final APIProperty emailKey = new APIProperty("EmailKey", data.getEmail());
				
		return new APIProperty[]{subscriberKey, emailAddress, name, gender, emailKey};
	}
}