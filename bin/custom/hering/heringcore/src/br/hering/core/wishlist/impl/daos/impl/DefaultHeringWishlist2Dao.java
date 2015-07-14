/**
 * 
 */
package br.hering.core.wishlist.impl.daos.impl;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.servicelayer.session.SessionExecutionBody;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.wishlist2.impl.daos.impl.DefaultWishlist2Dao;
import de.hybris.platform.wishlist2.model.Wishlist2Model;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import br.hering.core.wishlist.impl.daos.HeringWishlist2Dao;

/**
 * @author herbert
 *
 */
public class DefaultHeringWishlist2Dao extends DefaultWishlist2Dao implements HeringWishlist2Dao
{
	
	private SessionService sessionService;

	@Override
	public List findWishlistEntryByProduct(final String productCode, final Wishlist2Model wishlist)
	{
		return getSessionService().executeInLocalView(new SessionExecutionBody()
		{
			@Override
			public List execute()
			{
				StringBuilder query = new StringBuilder("SELECT {entry.pk} FROM ");
				query.append("{Wishlist2Entry as entry JOIN Product as p ON {entry.product} = {p.pk}} WHERE ");
				query.append("{p.code} = ?productCode AND ");
				query.append("{wishlist} = ?wishlist");
				FlexibleSearchQuery fQuery = new FlexibleSearchQuery(query.toString());
				fQuery.addQueryParameter("productCode", productCode);
				fQuery.addQueryParameter("wishlist", wishlist);
				
				getSessionService().setAttribute("disableRestrictions", Boolean.TRUE);
				SearchResult result = search(fQuery);
				List entries = result.getResult();
				return entries;
			}
		});
	}
	
	public SessionService getSessionService()
	{
		return sessionService;
	}
	
	@Required
	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
	}
}