/**
 * 
 */
package br.flieger.exacttarget.events.listeners;


import de.hybris.platform.servicelayer.model.ModelService;

import java.io.IOException;
import java.util.Date;
import java.util.Set;

import javax.annotation.Resource;
import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;

import com.flieger.notificationservices.model.NotifymeModel;

import br.flieger.exacttarget.events.NotifyMeProductEvent;
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
public class NotifyMeProductEventListener extends AbstractExacttargetEventListener<NotifyMeProductEvent>
{
	private final Logger LOG = Logger.getLogger(NotifyMeProductEventListener.class);
	
	@Resource
	private ModelService modelService;
	
	@Override
	protected void shooting(NotifyMeProductEvent event)
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
			
			validateResult(createResponse, event.getNotifications());
		}
		catch (IOException | ServiceException e)
		{
			LOG.error("Erro ao disparar e-mail", e);
		}
		catch (Exception e)
		{
			LOG.error("Send e-mail error", e);
		}
		
	}

	/**
	 * @param createResponse
	 * @param notifications
	 */
	private void validateResult(CreateResponse createResponse, Set<NotifymeModel> notifications)
	{
		final Date currentDate = new Date(System.currentTimeMillis());
		if (createResponse != null) 
		{
			if(createResponse.getOverallStatus().compareToIgnoreCase("OK") == 0)
			{
				for (NotifymeModel notifymeModel : notifications)
				{
					notifymeModel.setNotified(currentDate);
				}
				
				modelService.saveAll(notifications);
			}
			else
			{
				int count = 0;
				
				for (NotifymeModel notifymeModel : notifications)
				{
					for (CreateResult result : createResponse.getResults())
					{
						if(result.getOrdinalID().intValue() == count)
						{
							if(result.getStatusCode().compareToIgnoreCase("OK") == 0)
							{
								notifymeModel.setNotified(currentDate);
								modelService.save(notifymeModel);
								break;
							}
							else
							{
								LOG.error("Send e-mail failed: "+notifymeModel.getEmail());
							}
						}
					}
					count++;
				}
			}
		}
	}

}