/**
 *
 */
package com.fliegersoftware.media.daos.impl;

import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.mediaconversion.conversion.DefaultMediaConversionServiceDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.fliegersoftware.media.daos.MediaImportationDao;


/**
 * Default implementation of the {@link MediaImportationDao}
 *
 * Finds data related to {@link MediaContainerModel}
 *
 * @author Franthescolly Maneira
 *
 */
public class DefaultMediaImportationDao extends DefaultMediaConversionServiceDao implements MediaImportationDao
{
	private static final Logger LOG = Logger.getLogger(DefaultMediaImportationDao.class);

	/*
	 * (non-Javadoc)
	 *
	 * @see com.fliegersoftware.media.daos.MediaImportationDao#findProductsUsingMediaContainer(de.hybris.platform
	 * .core.model.media.MediaContainerModel)
	 */
	@Override
	public List<ProductModel> findProductsUsingMediaContainer(final MediaContainerModel mediaContainer)
	{
		final StringBuilder query = new StringBuilder();

		query.append("SELECT {p:PK} FROM { ");
		query.append("	Product AS p JOIN MediaContainer AS mc ");
		query.append("	ON {p:galleryImages} LIKE CONCAT( '%', CONCAT( {mc.PK} , '%' ) ) ");
		query.append("} ");
		query.append("WHERE {mc:PK} = ?mediaContainerPk ");

		final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(query);
		searchQuery.setResultClassList(Arrays.asList(ProductModel.class));
		searchQuery.addQueryParameter("mediaContainerPk", mediaContainer.getPk());

		final SearchResult<ProductModel> search = getFlexibleSearchService().search(searchQuery);

		return search.getResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.mediaconversion.conversion.DefaultMediaConversionServiceDao#retrieveMaster(de.hybris.platform
	 * .core.model.media.MediaContainerModel)
	 */
	@Override
	public MediaModel retrieveMaster(final MediaContainerModel model)
	{
		final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(
				"SELECT {m.pk} FROM {Media as m} WHERE {m.mediaContainer} = ?container AND {m.original} IS NULL AND {m.originalDataPK} IS NULL");
		searchQuery.setResultClassList(Arrays.asList(MediaModel.class));
		searchQuery.addQueryParameter("container", model);

		final SearchResult<MediaModel> search = getFlexibleSearchService().search(searchQuery);

		if (search.getTotalCount() == 0)
		{
			return null;
		}

		if (search.getTotalCount() > 1)
		{
			LOG.debug(String.format("MediaContainer %s has %s possible Master media items, returning the first one",
					model.getQualifier(), String.valueOf(search.getTotalCount())));
		}

		return search.getResult().iterator().next();
	}
}