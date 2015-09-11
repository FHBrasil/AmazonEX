/**
 * 
 */
package br.hering.core.product.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateIfSingleResult;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;
import static java.lang.String.format;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.impl.DefaultProductService;

import java.util.List;

import org.apache.log4j.Logger;

/**
 * @author Thyago
 *
 */
public class DefaultHeringProductService extends DefaultProductService
{
	private static final Logger LOG = 
			Logger.getLogger(DefaultHeringProductService.class);

	@Override
	public ProductModel getProductForCode(final String code)
	{
		validateParameterNotNull(code, "Codigo do parametro nao pode ser null");
		final List<ProductModel> products = getProductDao().findProductsByCode(code);

		validateIfSingleResult(products, format("Produto com o codigo '%s' nao foi encontrado!", code),
				format("Codigo de produto '%s' nao e unico, %d produtos encontrados!", code, Integer.valueOf(products.size())));		
		return products.get(0);
	}	
}