/**
 *
 */
package com.pixi.core.strategies;

import de.hybris.platform.core.model.product.ProductModel;

import java.util.Date;


/**
 * @author franthescollymaneira
 *
 */
public interface PixiProductUpdateNextDeliveryDateStrategy
{
	boolean updateNextDeliveryDate(final ProductModel product, final Date nextDeliveryDate);
}