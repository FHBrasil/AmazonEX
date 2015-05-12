/**
 * 
 */
package br.hering.core.product.dao;

import de.hybris.platform.servicelayer.internal.dao.Dao;

import br.hering.core.model.HeringProductModel;
import br.hering.core.model.ProductMeasurementsModel;

/**
 * @author franthescollymaneira
 *
 */
public interface ProductMeasurementsDao extends Dao
{
	/**
	 * @param product
	 * @param size
	 * @param type
	 * @return
	 */
	ProductMeasurementsModel findMeasurements(HeringProductModel product, String size, String type);
}