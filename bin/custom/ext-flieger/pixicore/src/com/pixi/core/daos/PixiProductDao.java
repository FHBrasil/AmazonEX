/**
 *
 */
package com.pixi.core.daos;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.internal.dao.Dao;

import java.util.List;


/**
 * @author franthescollymaneira
 *
 */
public interface PixiProductDao extends Dao
{
	List<ProductModel> findProductsToExport();
}