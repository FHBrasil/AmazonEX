/**
 * 
 */
package br.flieger.exacttarget.events.listeners;

import java.io.IOException;

import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;

import br.flieger.exacttarget.events.NewsletterEvent;
import br.flieger.exacttarget.wsdl.Util;
import br.flieger.exacttarget.wsdl.api.APIObject;
import br.flieger.exacttarget.wsdl.api.CreateOptions;
import br.flieger.exacttarget.wsdl.api.CreateRequest;
import br.flieger.exacttarget.wsdl.api.CreateResponse;
import br.flieger.exacttarget.wsdl.api.CreateResult;
import br.flieger.exacttarget.wsdl.api.DataExtensionObject;
import br.flieger.exacttarget.wsdl.api.DeleteOptions;
import br.flieger.exacttarget.wsdl.api.DeleteRequest;
import br.flieger.exacttarget.wsdl.api.DeleteResponse;
import br.flieger.exacttarget.wsdl.api.DeleteResult;
import br.flieger.exacttarget.wsdl.api.Soap;
import br.flieger.exacttarget.wsdl.api.TriggeredSend;
import br.flieger.exacttarget.wsdl.api.TriggeredSendDefinition;

/**
 * @author Vinicius de Souza
 *
 */
public class NewsletterEventListener extends AbstractExacttargetEventListener<NewsletterEvent>
{
	private static final Logger LOG = Logger.getLogger(NewsletterEventListener.class);

	@Override
	protected void shooting(NewsletterEvent event)
	{
		try
		{
			if(event.isReceive())
				register(event);	
			else
				unregister(event);
		} 
		catch(Exception e)
		{
			LOG.error(e);
		}
	}

	/**
	 * @param event
	 * @throws ServiceException 
	 * @throws IOException 
	 */
	private void register(NewsletterEvent event) throws IOException, ServiceException
	{
		Soap soap = Util.initSoap();
		final TriggeredSendDefinition tsd = new TriggeredSendDefinition();
		tsd.setCustomerKey(event.getTrigger());
		final TriggeredSend ts = new TriggeredSend();
		ts.setSubscribers(event.getSubscribers());
		ts.setTriggeredSendDefinition(tsd);

		CreateRequest createRequest = new CreateRequest();

		createRequest.setOptions(new CreateOptions());
		createRequest.setObjects(new APIObject[]{ts});

		CreateResponse createResponse = soap.create(createRequest);

		if (createResponse != null) {				
			for (CreateResult result : createResponse.getResults())
			{
				switch (result.getStatusCode())
				{
					case "Error":
						event.setResultOk(new Integer(180008).equals(result.getErrorCode()));
						LOG.info(event.getSubscribers()[0].getEmailAddress()+":::"+result.getStatusMessage()+":::"+result.getResultType()+"::"+result.getStatusCode()+"::"+result.getErrorCode());
						break;
					case "OK":
						event.setResultOk(true);
						break;
					default:
						break;
				}
				event.setMsgRequest(result.getStatusMessage());
			}
		}
	}

	/**
	 * @param event
	 * @throws ServiceException 
	 * @throws IOException 
	 */
	private void unregister(NewsletterEvent event) throws IOException, ServiceException
	{
		Soap soap = Util.initSoap();

		DataExtensionObject de = new DataExtensionObject();
		de.setCustomerKey(event.getData());

		de.setKeys(event.getAPIProperty());

		final DeleteOptions opDelete = new DeleteOptions();

		DeleteRequest detele = new DeleteRequest(opDelete, new APIObject[] {de});

		DeleteResponse response = soap.delete(detele);


		if (response != null) {				
			for (DeleteResult result : response.getResults())
			{
				switch (result.getStatusCode())
				{
					case "Error":
						if(result.getErrorCode().compareTo(2)==0 && result.getErrorCode().compareTo(0)==0)
						{
							event.setResultOk(true);
						}
						else
						{
							LOG.info(event.getSubscribers()[0].getEmailAddress()+":::"+result.getResultType()+"::"+result.getStatusCode()+"::"+result.getErrorCode()+"::"+result.getStatusMessage());
						}
						break;
					case "OK":
						event.setResultOk(true);
						break;
					default:
						break;
				}
				event.setMsgRequest(result.getStatusMessage());
			}
		}
	}
}