/**
 * 
 */
package com.flieger.notificationservices.facades.impl;

import java.util.Set;

import javax.annotation.Resource;

import org.fest.util.Collections;

import com.flieger.notificationservices.data.NotifymeData;
import com.flieger.notificationservices.facades.NotifymeFacade;
import com.flieger.notificationservices.facades.populators.NotifymePopulator;
import com.flieger.notificationservices.facades.populators.NotifymeReversePopulator;
import com.flieger.notificationservices.model.NotifymeModel;
import com.flieger.notificationservices.services.NotifymeService;


/**
 * @author Vinicius de Souza
 * 
 */
public class DefaultNotifymeFacade implements NotifymeFacade
{
	@Resource
	private NotifymeService notifymeService;
	
	@Resource
	private NotifymePopulator defaultNotifymePopulator;
	
	@Resource
	private NotifymeReversePopulator defaultNotifymeReversePopulator;

	@Override
	public void notifyme(final NotifymeData data) throws Exception
	{
		if (data != null)
		{
			final NotifymeModel model = new NotifymeModel();
			
			defaultNotifymeReversePopulator.populate(data, model);

			notifymeService.notifyme(model);
		}
		else
		{
			throw new Exception("Objeto nulo.");
		}
	}

	@Override
	public NotifymeData find(final String email, final String code) throws Exception
	{
		final Set<NotifymeModel> setCodes = notifymeService.findByEmail(email);

		NotifymeData data = null;

		if (!Collections.isEmpty(setCodes))
		{
			for (final NotifymeModel notifymeModel : setCodes)
			{
				if (notifymeModel.getProduct() != null && notifymeModel.getProduct().getCode().equals(code))
				{
					data = new NotifymeData();
					
					defaultNotifymePopulator.populate(notifymeModel, data);
					
					break;
				}
			}
		}
		return data;
	}
}