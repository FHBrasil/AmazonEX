/**
 * 
 */
package br.flieger.exacttarget.events.listeners;

import java.io.IOException;
import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;

import br.flieger.exacttarget.events.NotifyMeSimilarProductEvent;
import br.flieger.exacttarget.events.listeners.AbstractExacttargetEventListener;
import br.flieger.exacttarget.wsdl.Util;
import br.flieger.exacttarget.wsdl.api.APIObject;
import br.flieger.exacttarget.wsdl.api.CreateOptions;
import br.flieger.exacttarget.wsdl.api.CreateRequest;
import br.flieger.exacttarget.wsdl.api.CreateResponse;
import br.flieger.exacttarget.wsdl.api.CreateResult;
import br.flieger.exacttarget.wsdl.api.Soap;
import br.flieger.exacttarget.wsdl.api.TriggeredSend;
import br.flieger.exacttarget.wsdl.api.TriggeredSendDefinition;

/**
 * @author Vinicius de Souza
 *
 */
public class NotifyMeSimilarProductEventListener extends AbstractExacttargetEventListener<NotifyMeSimilarProductEvent>
{
	private final Logger LOG = Logger.getLogger(NotifyMeSimilarProductEventListener.class);
	
	@Override
	protected void shooting(NotifyMeSimilarProductEvent event)
	{
		try
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
						case "OK":
							event.setResultOk(true);
							break;
						case "Error":
							event.setResultOk(false);
							LOG.info(event.getSubscribers()[0].getEmailAddress()+":::"+result.getStatusMessage()+":::"+result.getResultType()+"::"+result.getStatusCode()+"::"+result.getErrorCode());
							break;
						default:
							break;
					}
				}
			}
		}
		catch (IOException | ServiceException e)
		{
			LOG.error("Erro ao disparar e-mail", e);
		}		
	}
}