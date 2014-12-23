/**
 *
 */
package com.pixi.core.services.impl;

import de.hybris.platform.core.model.product.ProductModel;

import java.util.List;

import javax.annotation.Resource;

import com.pixi.core.daos.PixiProductDao;
import com.pixi.core.services.PixiProductService;


/**
 * @author franthescollymaneira
 *
 */
public class DefaultPixiProductService implements PixiProductService
{
	@Resource
	private PixiProductDao pixiProductDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pixi.core.services.PixiProductService#findProductsToExport()
	 */
	@Override
	public List<ProductModel> findProductsToExport()
	{
		return pixiProductDao.findProductsToExport();
	}
}