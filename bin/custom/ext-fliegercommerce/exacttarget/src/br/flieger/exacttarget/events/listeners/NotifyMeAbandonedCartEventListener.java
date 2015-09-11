/**
 * 
 */
package br.flieger.exacttarget.events.listeners;

import java.io.IOException;

import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;

import br.flieger.exacttarget.events.NotifyMeAbandonedCartEvent;
import br.flieger.exacttarget.events.listeners.AbstractExacttargetEventListener;
import br.flieger.exacttarget.wsdl.Util;
import br.flieger.exacttarget.wsdl.api.APIObject;
import br.flieger.exacttarget.wsdl.api.CreateOptions;
import br.flieger.exacttarget.wsdl.api.CreateRequest;
import br.flieger.exacttarget.wsdl.api.CreateResponse;
import br.flieger.exacttarget.wsdl.api.CreateResult;
import br.flieger.exacttarget.wsdl.api.Soap;
import br.flieger.exacttarget.wsdl.api.Subscriber;
import br.flieger.exacttarget.wsdl.api.TriggeredSend;
import br.flieger.exacttarget.wsdl.api.TriggeredSendDefinition;

/**
 * @author Vinicius de Souza
 *
 */
public class NotifyMeAbandonedCartEventListener extends AbstractExacttargetEventListener<NotifyMeAbandonedCartEvent>
{
	private final Logger LOG = Logger.getLogger(NotifyMeAbandonedCartEventListener.class);
	
	@Override
	protected void shooting(NotifyMeAbandonedCartEvent event)
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
							event.setMsgRequest("#"+result.getErrorCode()+"# "+result.getStatusMessage());
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