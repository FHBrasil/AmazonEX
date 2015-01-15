/**
 *
 */
package com.pixi.core.services.impl;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.daos.ProductDao;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.site.BaseSiteService;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.util.Assert;

import com.pixi.core.daos.PixiProductDao;
import com.pixi.core.model.PixiSyncRecordModel;
import com.pixi.core.services.PixiProductService;


/**
 * @author franthescollymaneira
 *
 */
public class DefaultPixiProductService implements PixiProductService
{
	@Resource
	private PixiProductDao pixiProductDao;

	@Resource
	private ModelService modelService;

	@Resource
	private ProductDao productDao;

	@Resource
	private BaseSiteService baseSiteService;

	@Resource
	private CatalogVersionService catalogVersionService;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.pixi.core.services.PixiProductService#saveExportConfirmationDate(java.lang.String, java.util.Date,
	 * java.util.Date)
	 */
	@Override
	public void saveExportConfirmationDate(final String productCode, final Date exportDate, final Date confirmationDate)
	{
		final List<ProductModel> products = productDao.findProductsByCode(productCode);

		if (CollectionUtils.isEmpty(products))
		{
			return;
		}

		final ProductModel product = products.iterator().next();

		final boolean permissionGranted = exportDate == null || product.getModifiedtime().before(exportDate);

		if (permissionGranted)
		{
			saveSyncRecord(productCode, null, confirmationDate);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pixi.core.services.PixiProductService#findProductsToExport(de.hybris.platform.catalog.model.CatalogVersionModel
	 * )
	 */
	@Override
	public List<ProductModel> findProductsToExport(final CatalogVersionModel catalogVersion)
	{
		return pixiProductDao.findProductsToExport(catalogVersion);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pixi.core.services.PixiProductService#findExportedProductsBySessionID(java.lang.String)
	 */
	@Override
	public List<String> findExportedProductsBySessionID(final String sessionID)
	{
		Assert.notNull(sessionID);

		return pixiProductDao.findExportedProductsBySessionID(sessionID);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.pixi.core.services.PixiProductService#getAllProductsCode(java.lang.String)
	 */
	@Override
	public List<String> findAllProductsByStore(final String shopId)
	{
		final BaseSiteModel baseSite = baseSiteService.getBaseSiteForUID(shopId);
		final List<CatalogModel> productCatalogs = baseSiteService.getProductCatalogs(baseSite);
		final CatalogModel catalog = productCatalogs.iterator().next();
		final CatalogVersionModel stagedVersion = catalogVersionService.getCatalogVersion(catalog.getId(), "Staged");

		return pixiProductDao.getAllProductsCode(stagedVersion);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.pixi.core.services.PixiProductService#saveSyncRecord(de.hybris.platform.core.model.product.ProductModel,
	 * java.lang.String, java.util.Date)
	 */
	@Override
	public void saveSyncRecord(final String productCode, final String sessionID, final Date exportDate)
	{
		PixiSyncRecordModel syncRecord = pixiProductDao.findSyncRecord(productCode);

		if (syncRecord == null)
		{
			syncRecord = modelService.create(PixiSyncRecordModel.class);
			syncRecord.setProductCode(productCode);
		}

		syncRecord.setExportDate(exportDate);
		syncRecord.setSessionID(sessionID);

		modelService.save(syncRecord);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.pixi.core.services.PixiProductService#releaseProductsFromSession(java.lang.String)
	 */
	@Override
	public void releaseProductsFromSession(final String sessionID)
	{
		final Collection<String> productCodeList = findExportedProductsBySessionID(sessionID);

		if (CollectionUtils.isEmpty(productCodeList))
		{
			return;
		}

		final Iterator<String> it = productCodeList.iterator();
		PixiSyncRecordModel syncRecord = null;

		while (it.hasNext())
		{
			syncRecord = pixiProductDao.findSyncRecord(it.next());
			syncRecord.setSessionID(null);

			modelService.save(syncRecord);
		}
	}
}