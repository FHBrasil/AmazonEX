/**
 * 
 */
package com.flieger.notificationservices.dao.impl;

import de.hybris.platform.servicelayer.internal.dao.AbstractItemDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.MapUtils;

import com.flieger.notificationservices.dao.NotifymeDao;
import com.flieger.notificationservices.model.NotifymeModel;


/**
 * @author Vinicius de Souza
 * 
 */
public class DefaultNotifymeDao extends AbstractItemDao implements NotifymeDao
{
	private final String FS_DEFAULT = "SELECT {PK} FROM {Notifyme}";

	@Override
	public Set<NotifymeModel> findByEmail(final String email) throws Exception
	{
		final StringBuilder fs = new StringBuilder(FS_DEFAULT);
		fs.append(" WHERE {email} = ?email ");

		final Map<String, Object> param = new HashMap<String, Object>();
		param.put("email", email);

		return getSubscribeFS(fs.toString(), param);
	}

	private Set<NotifymeModel> getSubscribeFS(final String fs, final Map<String, Object> param)
	{
		final FlexibleSearchQuery query = (MapUtils.isEmpty(param) ? new FlexibleSearchQuery(fs) : new FlexibleSearchQuery(fs,
				param));

		final SearchResult<NotifymeModel> result = getFlexibleSearchService().search(query);

		return new HashSet<NotifymeModel>(result.getResult());
	}

	@Override
	public Set<NotifymeModel> getAll(final boolean notified)
	{
		final StringBuilder fs = new StringBuilder(FS_DEFAULT);
		fs.append(" WHERE {notified} is " + (notified ? "not" : "") + " null AND {expired} is null");

		return getSubscribeFS(fs.toString(), null);
	}

	@Override
	public void update(NotifymeModel model) throws Exception
	{
		if(model != null)
		{
			getModelService().save(model);
			getModelService().refresh(model);
		}
		else
		{
			throw new Exception("Model nula.");
		}		
	}

	@Override
	public void create(NotifymeModel model) throws Exception
	{
		update(model);		
	}
}