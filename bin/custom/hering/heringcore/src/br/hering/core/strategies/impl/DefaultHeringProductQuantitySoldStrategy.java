/**
 * 
 */
package br.hering.core.strategies.impl;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;

import br.hering.core.model.HeringProductModel;
import br.hering.core.model.HeringSizeVariantProductModel;
import br.hering.core.model.HeringStyleVariantProductModel;

/**
 * @author franthescollymaneira
 *
 */
public class DefaultHeringProductQuantitySoldStrategy implements HeringProductQuantitySoldStrategy
{
	private static final Long DEFAULT = Long.valueOf(0);
	
	@Resource
	private FlexibleSearchService flexibleSearchService;

	/* (non-Javadoc)
	 * @see br.hering.core.strategies.impl.HeringProductQuantitySoldStrategy#getQuantitySold(de.hybris.platform.core.model.product.ProductModel)
	 */
	@Override
	@SuppressWarnings("boxing")
	public <T extends ProductModel> long getQuantitySold(T product)
	{
		final FlexibleSearchQuery query = createFlexibleSearchQuery(product);
		
		final List<Long> result = flexibleSearchService.<Long>search(query).getResult();
		
		if(CollectionUtils.isNotEmpty(result))
		{
			final Object rs = result.iterator().next();
			return (long) ObjectUtils.defaultIfNull(rs, DEFAULT);
		}
		
		return 0;
	}

	/**
	 * @param product
	 * @return
	 */
	private final <T extends ProductModel> FlexibleSearchQuery createFlexibleSearchQuery(final T product)
	{
		final String sql = getQuery(product.getClass());
		final Map<String, T> param = Collections.singletonMap("pp", product);
		
		final FlexibleSearchQuery query = new FlexibleSearchQuery(sql, param);
		query.setResultClassList(Arrays.asList(Long.class));
		
		return query;
	}
	
	private final String getQuery(final Class<? extends ProductModel> typeClass) 
	{
		final StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT SUM({e:quantity}) AS qty FROM { ");
		
		if(typeClass.equals(HeringProductModel.class))
		{
			sql.append("	Product as p ");
			sql.append("	JOIN VariantProduct AS bv ON {bv:baseProduct} = {p:PK} ");
			sql.append("	JOIN VariantProduct AS v ON {v:baseProduct} = {bv:PK} ");
			sql.append("   JOIN OrderEntry AS e ON {e:product} = {v:PK} ");
			sql.append("} WHERE {p:PK} = ?pp ");
		}
		else if(typeClass.equals(HeringStyleVariantProductModel.class))
		{
			sql.append("	Product as p ");
			sql.append("	JOIN VariantProduct AS v ON {v:baseProduct} = {p:PK} ");
			sql.append("   JOIN OrderEntry AS e ON {e:product} = {v:PK} ");
			sql.append("} WHERE {p:PK} = ?pp ");
		}
		else if (typeClass.equals(HeringSizeVariantProductModel.class))
		{
			sql.append("   OrderEntry AS e ");
			sql.append("} WHERE {e:product} = ?pp ");
		}
		
		return sql.toString();
	}
}