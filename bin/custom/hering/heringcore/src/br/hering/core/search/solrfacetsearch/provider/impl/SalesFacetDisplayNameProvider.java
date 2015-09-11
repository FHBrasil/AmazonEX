/**
 * 
 */
package br.hering.core.search.solrfacetsearch.provider.impl;

import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractFacetValueDisplayNameProvider;
import de.hybris.platform.solrfacetsearch.search.SearchQuery;

import org.apache.commons.lang.StringUtils;

/**
 * @author franthescollymaneira
 *
 */
public class SalesFacetDisplayNameProvider extends AbstractFacetValueDisplayNameProvider
{
	/* (non-Javadoc)
	 * @see de.hybris.platform.solrfacetsearch.provider.impl.AbstractFacetValueDisplayNameProvider#getDisplayName(de.hybris.platform.solrfacetsearch.search.SearchQuery, de.hybris.platform.solrfacetsearch.config.IndexedProperty, java.lang.String)
	 */
	@Override
	public String getDisplayName(final SearchQuery query, final IndexedProperty property, final String facetValue)
	{
		if(StringUtils.isBlank(facetValue))
		{
			return "";
		}
		
		return "Sales";
	}
}