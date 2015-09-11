/**
 * 
 */
package br.hering.core.search.solrfacetsearch.processors;

import de.hybris.platform.solrfacetsearch.search.SearchQuery;

import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;

/**
 * @author franthescollymaneira
 *
 */
public class DefaultKidsClothingFacetAppender extends AbstractHeringFacetAppender
{
	/* (non-Javadoc)
	 * @see de.hybris.platform.solrfacetsearch.search.SolrQueryPostProcessor#process(org.apache.solr.client.solrj.SolrQuery, de.hybris.platform.solrfacetsearch.search.SearchQuery)
	 */
	@Override
	public SolrQuery process(SolrQuery query, SearchQuery solrSearchQuery)
	{
		if(isFreeTextQuery(query)) 
		{
			return query;
		}
		
		boolean hasKidsClothingFilter = StringUtils.containsIgnoreCase(query.getQuery(), "kidsClothing_boolean:true");

		query.addFilterQuery("kidsClothing_boolean:" + hasKidsClothingFilter);
		
		return query;
	}
}