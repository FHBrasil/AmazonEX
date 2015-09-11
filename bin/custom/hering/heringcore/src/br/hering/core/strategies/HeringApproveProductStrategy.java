/**
 * 
 */
package br.hering.core.strategies;

import de.hybris.platform.core.model.product.ProductModel;

/**
 * @author franthescollymaneira
 *
 */
public interface HeringApproveProductStrategy
{
	void approve(final ProductModel product, final boolean activateChildren);
}
