/**
 * 
 */
package br.flieger.exacttarget.events.listeners;

import java.io.IOException;

import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;

import br.flieger.exacttarget.events.NotifyMePaymentEvent;
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
public class NotifyMePaymentEventListener extends AbstractExacttargetEventListener<NotifyMePaymentEvent>
{
	private final Logger LOG = Logger.getLogger(NotifyMePaymentEventListener.class);
	
	@Override
	protected void shooting(NotifyMePaymentEvent event)
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
							/*if(result.getErrorCode().compareTo(180008) == 0 && event.isContinuousNotifications())
							{
								deleteAndSendNotify(soap, event);
							}
							else
							{
								LOG.info(event.getSubscribers()[0].getEmailAddress()+":::"+result.getStatusMessage()+":::"+result.getResultType()+"::"+result.getStatusCode()+"::"+result.getErrorCode());
								
							}*/
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

	/**
	 * @param soap 
	 * @param event
	 * @throws RemoteException 

	private void deleteAndSendNotify(Soap soap, NotifyMePaymentEvent event) throws RemoteException
	{
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
						event.setResultOk(false);
						LOG.error(event.getSubscribers()[0].getEmailAddress()+":::"+result.getResultType()+"::"+result.getStatusCode()+"::"+result.getErrorCode());
						break;
					case "OK":
						shooting(event);
						break;
					default:
						break;
				}
				event.setMsgRequest(result.getStatusMessage());
			}
		}
	} */
}