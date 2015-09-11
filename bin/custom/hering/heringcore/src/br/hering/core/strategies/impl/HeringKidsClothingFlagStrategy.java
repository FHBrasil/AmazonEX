/**
 * 
 */
package br.hering.core.strategies.impl;

import de.hybris.platform.core.model.product.ProductModel;

/**
 * @author franthescollymaneira
 *
 */
public interface HeringKidsClothingFlagStrategy
{
	boolean isKidsClothing(ProductModel product);
	
	boolean isBabyClothing(ProductModel product);
}