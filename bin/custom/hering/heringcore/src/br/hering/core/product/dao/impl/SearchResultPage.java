/**
 * 
 */
package br.hering.core.product.dao.impl;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.Collection;

import org.apache.log4j.Logger;

/**
 * @author franthescollymaneira
 *
 */
@SuppressWarnings("boxing")
public class SearchResultPage<E>
{
	private Logger LOG = Logger.getLogger(SearchResultPage.class);
	
	private final FlexibleSearchService fsService;

	private final FlexibleSearchQuery fsQuery;
	
	private int currentPage = 0;
	
	public SearchResultPage(final FlexibleSearchService fsService, final FlexibleSearchQuery fsQuery)
	{
		super();
		this.fsService = fsService;
		this.fsQuery = fsQuery;
	}
	
	public Collection<E> nextPage() 
	{
		LOG.info(String.format("Requesting page %s", ++currentPage));
		return fsService.<E>search(fsQuery).getResult();
	}
}