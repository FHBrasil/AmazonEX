/**
 * 
 */
package br.hering.core.strategies.impl;

import de.hybris.platform.core.model.product.ProductModel;

/**
 * @author franthescollymaneira
 *
 */
public interface HeringProductQuantitySoldStrategy
{
	<T extends ProductModel> long getQuantitySold(T product);
}