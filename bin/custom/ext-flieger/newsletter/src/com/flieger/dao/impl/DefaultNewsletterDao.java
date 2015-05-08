/**
 * 
 */
package com.flieger.dao.impl;

import de.hybris.platform.servicelayer.internal.dao.AbstractItemDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;

import com.flieger.dao.NewsletterDao;
import com.flieger.model.NewsletterSubscriberModel;


/**
 * Implementação default da Interface NewsletterDao para acesso aos dados.
 * 
 * @author Vinicius de Souza
 * 
 */
public class DefaultNewsletterDao extends AbstractItemDao implements NewsletterDao
{
	private final String fsDefault = "SELECT {PK} FROM {NewsletterSubscriber}";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.flieger.dao.NewsletterDao#getAllSubscriber()
	 */
	@Override
	public List<NewsletterSubscriberModel> getAllSubscribe()
	{
		return getSubscribeFS(fsDefault, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.flieger.dao.NewsletterDao#getSubscribeByRegistered(boolean, boolean)
	 */
	@Override
	public List<NewsletterSubscriberModel> getSubscribeByRegistered(final boolean registered, final boolean receive)
	{
		final StringBuilder fs = new StringBuilder(fsDefault);
		fs.append(" WHERE {registered} = ?registered AND {receive} = ?receive");

		final Map<String, Object> param = new HashMap<String, Object>();
		param.put("registered", new Boolean(registered));
		param.put("receive", new Boolean(receive));

		return getSubscribeFS(fs.toString(), param);
	}

	/**
	 * Método padrão de execução Flexible Search.
	 * 
	 * @param fs
	 *           Query Flexible Search a ser executada.
	 * @param param
	 *           Mapa de parâmetros.
	 * @return Retorna uma <code>List</code> de <code>NewsletterSubscriberModel</code>.
	 */
	private List<NewsletterSubscriberModel> getSubscribeFS(final String fs, final Map<String, Object> param)
	{
		final FlexibleSearchQuery query = (MapUtils.isEmpty(param) ? new FlexibleSearchQuery(fs) : new FlexibleSearchQuery(fs,
				param));

		final SearchResult<NewsletterSubscriberModel> result = getFlexibleSearchService().search(query);

		return result.getResult();
	}

	@Override
	public void update(final NewsletterSubscriberModel model)
	{
		if (model != null)
		{
			getModelService().save(model);
		}
	}

	@Override
	public void delete(final NewsletterSubscriberModel model)
	{
		if (model != null)
		{
			getModelService().remove(model);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.flieger.dao.NewsletterDao#findByEmail(java.lang.String)
	 */
	@Override
	public NewsletterSubscriberModel findByEmail(final String email)
	{
		final StringBuilder fs = new StringBuilder(fsDefault);
		fs.append(" WHERE {email} = ?email");

		final Map<String, Object> param = new HashMap<String, Object>();
		param.put("email", email);

		for (final NewsletterSubscriberModel model : getSubscribeFS(fs.toString(), param))
		{
			return model;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.flieger.dao.NewsletterDao#getNewsletterSubscriberForEmail(java.lang.String)
	 */
	@Override
	public List<NewsletterSubscriberModel> getNewsletterSubscriberForEmail(final String email)
	{
		final StringBuilder fs = new StringBuilder(fsDefault);
		fs.append(" WHERE {email} = ?email");

		final Map<String, Object> param = new HashMap<String, Object>();
		param.put("email", email);

		return getSubscribeFS(fs.toString(), param);
	}
}