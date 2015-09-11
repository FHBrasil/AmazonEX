/**
 * 
 */
package br.hering.core.strategies;

import de.hybris.platform.core.model.product.ProductModel;

/**
 * @author franthescollymaneira
 *
 */
public interface HeringCheckProductStrategy
{
	void check(final ProductModel product, final boolean deactivateChildren);
}