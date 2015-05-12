/**
 * 
 */
package br.hering.facades.product;

import de.hybris.platform.commercefacades.product.ProductFacade;

import java.util.List;

import br.hering.facades.product.preview.data.ProductPreviewData;


/**
 * @author wellington
 *
 */
public interface HeringProductFacade extends ProductFacade
{
	List<ProductPreviewData> getAvailableStyleVariants(String code);

	List<ProductPreviewData> getAvailableSizeVariants(String code);

	List<ProductPreviewData> getAvailableChildVariants(String code);	
	
}
