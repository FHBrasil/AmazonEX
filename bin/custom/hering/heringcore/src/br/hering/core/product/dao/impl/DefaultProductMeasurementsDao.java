/**
 * 
 */
package br.hering.core.product.dao.impl;

import de.hybris.platform.servicelayer.internal.dao.AbstractItemDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.util.Assert;

import br.hering.core.model.HeringProductModel;
import br.hering.core.model.ProductMeasurementsModel;
import br.hering.core.product.dao.ProductMeasurementsDao;

/**
 * @author franthescollymaneira
 *
 */
public class DefaultProductMeasurementsDao extends AbstractItemDao implements ProductMeasurementsDao
{

	/* (non-Javadoc)
	 * @see br.hering.core.product.dao.ProductMeasurementsDao#findMeasurements(br.hering.core.model.HeringProductModel, java.lang.String, java.lang.String)
	 */
	@Override
	public ProductMeasurementsModel findMeasurements(HeringProductModel product, String size, String type)
	{
		Assert.notNull(product, "product is null");
		Assert.notNull(size, "size is null");
		Assert.notNull(type, "type is null");
		
		final String queryString = "SELECT {PK} FROM {ProductMeasurements} WHERE {product} = ?pproduct AND {type} LIKE ?mtype AND {size} LIKE ?msize";

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		query.addQueryParameter("pproduct", product);
		query.addQueryParameter("mtype", type);
		query.addQueryParameter("msize", size);

		List<ProductMeasurementsModel> result = getFlexibleSearchService().<ProductMeasurementsModel> search(query).getResult();
		
		if(CollectionUtils.isEmpty(result))
		{
			return null;
		}
		
		return result.iterator().next();
	}
}