/**
 * 
 */
package br.hering.core.search.impl;

import de.hybris.platform.commerceservices.search.solrfacetsearch.querybuilder.impl.DefaultFreeTextQueryBuilder;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.search.SearchQuery;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.util.ClientUtils;

/**
 * @author franthescollymaneira
 *
 */
public class DefaultHeringFreeTextQueryBuilder extends DefaultFreeTextQueryBuilder
{
	private final Logger LOG = Logger.getLogger(DefaultHeringFreeTextQueryBuilder.class);
	
	@Override
	protected void addFreeTextQuery(SearchQuery searchQuery, IndexedProperty indexedProperty, String value, double boost) 
	{
		final String field = indexedProperty.getName();
		if (!(indexedProperty.isFacet())) 
		{
			if ("text".equalsIgnoreCase(indexedProperty.getType())) 
			{
				addFreeTextQuery(searchQuery, field, value.toLowerCase(), "~10000000", boost / 4.0D);
			} 
			else 
			{
				addFreeTextQuery(searchQuery, field, value.toLowerCase(), "", boost);
			}
		} 
		else
		{
			LOG.warn("Not searching "
					+ indexedProperty.getName()
					+ ". Free text search not available in facet property. Configure an additional text property for searching.");
		}
	}
	
	@Override
	protected void addFreeTextQuery(SearchQuery searchQuery, String field, String value, String suffixOp, double boost) 
	{
		final String escapeQueryChars = "\"" + ClientUtils.escapeQueryChars(value) + "\"";
		searchQuery.searchInField(field, escapeQueryChars + suffixOp + "^" + boost, SearchQuery.Operator.OR);
	}
}