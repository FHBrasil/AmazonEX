package br.hering.core.search.impl;

import de.hybris.platform.commerceservices.search.solrfacetsearch.populators.SearchTextPopulator;
import de.hybris.platform.commerceservices.search.solrfacetsearch.querybuilder.FreeTextQueryBuilder;
import de.hybris.platform.solrfacetsearch.search.SearchQuery;


public class HeringSearchTextPopulator<FACET_SEARCH_CONFIG_TYPE, INDEXED_TYPE_TYPE, INDEXED_PROPERTY_TYPE, INDEXED_TYPE_SORT_TYPE>
		extends SearchTextPopulator<FACET_SEARCH_CONFIG_TYPE, INDEXED_TYPE_TYPE, INDEXED_PROPERTY_TYPE, INDEXED_TYPE_SORT_TYPE>
{
	@Override
	protected void addFreeTextQuery(SearchQuery searchQuery, String cleanedFreeTextSearch)
	{
		//String[] words = cleanedFreeTextSearch.split("\\s+");
		
		for (FreeTextQueryBuilder freeTextQueryBuilder : getFreeTextQueryBuilders())
		{
			freeTextQueryBuilder.addFreeTextQuery(searchQuery, cleanedFreeTextSearch, null);
		}
	}
}