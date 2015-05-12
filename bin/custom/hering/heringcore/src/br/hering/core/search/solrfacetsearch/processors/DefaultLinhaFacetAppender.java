/**
 * 
 */
package br.hering.core.search.solrfacetsearch.processors;

import de.hybris.platform.solrfacetsearch.search.SearchQuery;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;

/**
 * @author herbert
 *
 */
public class DefaultLinhaFacetAppender extends AbstractHeringFacetAppender
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

		
		Matcher matcher = Pattern.compile("\\((linha_pt_string_mv:.+?)\\)").matcher(query.getQuery());
		boolean foundFilter = matcher.find();

		if(foundFilter) {
			query.addFilterQuery(matcher.group(1));
		}

		return query;
	}

}
