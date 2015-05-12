/**
 * 
 */
package br.flieger.exacttarget.events.listeners;

import java.io.IOException;

import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;

import groovy.transform.Synchronized;
import br.flieger.exacttarget.events.AbstractExacttargetOrderEvent;
import br.flieger.exacttarget.wsdl.Util;
import br.flieger.exacttarget.wsdl.api.APIObject;
import br.flieger.exacttarget.wsdl.api.Attribute;
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
public class OrderSendEmailEventListener extends AbstractExacttargetEventListener<AbstractExacttargetOrderEvent>
{
	private static final Logger LOG = Logger.getLogger(OrderSendEmailEventListener.class); 

	@Override
	@Synchronized
	protected void shooting(AbstractExacttargetOrderEvent event)
	{		
		try
		{
			Soap soap = Util.initSoap();
			
			final TriggeredSendDefinition tsd = new TriggeredSendDefinition();
			tsd.setCustomerKey(event.getTrigger());
			
			final TriggeredSend ts = new TriggeredSend();
			ts.setSubscribers(event.getSubscribers());
			
			ts.setTriggeredSendDefinition(tsd);
			
			final CreateRequest createRequest = new CreateRequest();
			
			createRequest.setOptions(new CreateOptions());
			createRequest.setObjects(new APIObject[]{ts});
			
			final CreateResponse createResponse = soap.create(createRequest);
			
			if (createResponse != null) {				
				for (CreateResult result : createResponse.getResults())
				{
					event.setResultOk(result.getStatusCode().equalsIgnoreCase("OK"));
					event.setMsgRequest(result.getStatusMessage());
					
					if(!event.isResultOk())
					{
						LOG.error("RESULTADO DO SEND EMAIL: "+result.getStatusCode()+":::"+result.getStatusMessage()+"::"+result.getErrorCode());
					
						LOG.info("Atributos...");
						for(Attribute a : event.getAttributes())
						{
							LOG.info(a.getName()+"="+a.getValue());
						}
					}					
				}
			}
		}
		catch (IOException e)
		{
			LOG.error("Erro de Comunicação.", e);
		}
		catch (ServiceException e)
		{
			LOG.error("Erro ao acessar API Exacttarget.", e);
		}
		catch (Exception e)
		{
			LOG.error("Erro de execução Exacttarget.", e);
		}		
	}
}