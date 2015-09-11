/**
 * 
 */
package br.hering.core.search.solrfacetsearch.processors;

import de.hybris.platform.solrfacetsearch.search.SolrQueryPostProcessor;

import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;

/**
 * @author franthescollymaneira
 *
 */
public abstract class AbstractHeringFacetAppender implements SolrQueryPostProcessor
{
	private static final String[] FREE_TEXT_FILTERS = {"freetextquery", "tag"};
	
	/**
	 * 
	 * @param query
	 * @return
	 */
	protected boolean isFreeTextQuery(SolrQuery query)
	{
		final String q = StringUtils.lowerCase(query.get("q"));
		
		for(final String filter : FREE_TEXT_FILTERS) 
		{
			if(StringUtils.contains(q, filter))
			{
				return true;
			}
		}
		
		return false;
	}
}