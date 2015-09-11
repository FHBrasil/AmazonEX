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
public class DefaultAccessoriesFacetAppender extends AbstractHeringFacetAppender
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
		
		final String accessoriesFilter = "(grupo_pt_string_mv:Acessórios)";
		// check if "Acessórios" is in query
		final boolean foundInQuery = StringUtils.containsIgnoreCase(query.getQuery(), accessoriesFilter);
		boolean foundInFilter = false;
		if (!foundInQuery) {
			// check if "Acessórios" is in filters
			for(String filterQuery : query.getFilterQueries()) {
				foundInFilter = filterQuery.contains(accessoriesFilter);
				if(foundInFilter) break;
			}
		}
		if(foundInQuery) {
			// if "Acessórios" found in query, remove from query and add to filter query
			String queryString = query.getQuery();
			queryString = queryString.replace(accessoriesFilter + " OR ", "").replace(" OR " + accessoriesFilter, "");
			query.setQuery(queryString);
			query.addFilterQuery("grupo_pt_string_mv:Acessórios");
		}
		
		// if "Acessórios" not found, don't want accessories in results
		boolean hasAccessoriesFilter = foundInQuery || foundInFilter;
		if(!hasAccessoriesFilter) {
			query.addFilterQuery("-grupo_pt_string_mv:Acessórios");
		}
		
		return query;
	}
}