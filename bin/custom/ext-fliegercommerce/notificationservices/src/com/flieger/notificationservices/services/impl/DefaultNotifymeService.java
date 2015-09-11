/**
 * 
 */
package com.flieger.notificationservices.services.impl;

import de.hybris.platform.servicelayer.model.ModelService;

import java.util.Set;

import javax.annotation.Resource;

import com.flieger.notificationservices.dao.NotifymeDao;
import com.flieger.notificationservices.model.NotifymeModel;
import com.flieger.notificationservices.services.NotifymeService;


/**
 * @author Vinicius de Souza
 * 
 */
public class DefaultNotifymeService implements NotifymeService
{
	@Resource
	private NotifymeDao notifymeDao;

	@Override
	public void notifyme(final NotifymeModel model) throws Exception
	{
		notifymeDao.create(model);
	}

	@Override
	public Set<NotifymeModel> findByEmail(final String email) throws Exception
	{
		Set<NotifymeModel> setModel = null;

		if (email != null)
		{
			setModel = notifymeDao.findByEmail(email);
		}
		else
		{
			throw new Exception("E-mail nulo.");
		}

		return setModel;
	}

	@Override
	public Set<NotifymeModel> findByCode(final String code) throws Exception
	{
		// YTODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(final NotifymeModel model) throws Exception
	{
		notifymeDao.update(model);
	}

	@Override
	public Set<NotifymeModel> getAll(final boolean notified) throws Exception
	{
		final Set<NotifymeModel> result = notifymeDao.getAll(notified);
		return result;
	}
}