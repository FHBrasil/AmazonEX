/**
 * 
 */
package br.flieger.exacttarget.events.listeners;

import java.io.IOException;

import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;

import br.flieger.exacttarget.events.ForgotPasswordEvent;
import br.flieger.exacttarget.wsdl.Util;
import br.flieger.exacttarget.wsdl.api.APIObject;
import br.flieger.exacttarget.wsdl.api.Attribute;
import br.flieger.exacttarget.wsdl.api.CreateOptions;
import br.flieger.exacttarget.wsdl.api.CreateRequest;
import br.flieger.exacttarget.wsdl.api.CreateResponse;
import br.flieger.exacttarget.wsdl.api.CreateResult;
import br.flieger.exacttarget.wsdl.api.RequestType;
import br.flieger.exacttarget.wsdl.api.Soap;
import br.flieger.exacttarget.wsdl.api.TriggeredSend;
import br.flieger.exacttarget.wsdl.api.TriggeredSendDefinition;

/**
 * @author Vinicius de Souza
 *
 */
public class ForgotPasswordEventListener extends AbstractExacttargetEventListener<ForgotPasswordEvent>
{
	private static final Logger LOG = Logger.getLogger(ForgotPasswordEventListener.class);
	
	@Override
	protected void shooting(ForgotPasswordEvent event)
	{
		try
		{
			Soap soap = Util.initSoap();
			
			final TriggeredSendDefinition tsd = new TriggeredSendDefinition();
			tsd.setCustomerKey(event.getTrigger());
			
			final TriggeredSend ts = new TriggeredSend();
			ts.setSubscribers(event.getSubscribers());
			ts.setTriggeredSendDefinition(tsd);
			
			final CreateOptions opt = new CreateOptions();
			opt.setRequestType(RequestType.Asynchronous);
			
			CreateRequest createRequest = new CreateRequest();
			
			createRequest.setOptions(opt);
			createRequest.setObjects(new APIObject[]{ts});
			
			CreateResponse createResponse = soap.create(createRequest);
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
	}
}