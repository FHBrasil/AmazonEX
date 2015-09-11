/**
 * 
 */
package br.hering.facades.populators;

import de.hybris.platform.commercefacades.product.data.BaseOptionData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.product.data.VariantOptionData;
import de.hybris.platform.converters.Converters;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.variants.model.VariantProductModel;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;

import br.hering.core.model.HeringProductModel;
import br.hering.core.model.HeringStyleVariantProductModel;
import br.hering.core.search.solrfacetsearch.provider.impl.VariantsUtils;

/**
 * @author herbert
 *
 */
public class VariantAvailablePopulator<SOURCE extends ProductModel, TARGET extends ProductData> implements Populator<SOURCE, TARGET>
{
	private Converter<VariantProductModel, VariantOptionData> variantOptionDataConverter;
	private Converter<VariantProductModel, BaseOptionData> baseOptionDataConverter;
	private VariantsUtils variantsUtils;

	@Override
	public void populate(final SOURCE productModel, final TARGET productData) throws ConversionException
	{
		// Populate the list of available child variant options
		if (productModel.getVariantType() != null && CollectionUtils.isNotEmpty(productModel.getVariants()))
		{
			final List<VariantOptionData> variantOptions = new ArrayList<VariantOptionData>();
			for (final VariantProductModel variantProductModel : variantsUtils.getAvailableChildVariants(productModel))
			{
				variantOptions.add(getVariantOptionDataConverter().convert(variantProductModel));
			}
			productData.setVariantOptions(variantOptions);
		}

		// Populate the list of base options
		final List<BaseOptionData> baseOptions = new ArrayList<BaseOptionData>();
		ProductModel currentProduct = productModel;

		while (currentProduct instanceof VariantProductModel)
		{
			final ProductModel baseProduct = ((VariantProductModel) currentProduct).getBaseProduct();

			final BaseOptionData baseOptionData = getBaseOptionDataConverter().convert((VariantProductModel) currentProduct);

			// Fill out the list of available product options
			if (baseProduct instanceof HeringProductModel)
			{
				baseOptionData.setOptions(Converters.convertAll(variantsUtils.getAvailableChildVariants(baseProduct), getVariantOptionDataConverter()));
			}
			else
			{
				baseOptionData.setOptions(Converters.convertAll(baseProduct.getVariants(), getVariantOptionDataConverter()));
			}

			baseOptions.add(baseOptionData);
			currentProduct = baseProduct;
		}
		productData.setBaseOptions(baseOptions);
	}


	protected Converter<VariantProductModel, VariantOptionData> getVariantOptionDataConverter()
	{
		return variantOptionDataConverter;
	}

	@Required
	public void setVariantOptionDataConverter(final Converter<VariantProductModel, VariantOptionData> variantOptionDataConverter)
	{
		this.variantOptionDataConverter = variantOptionDataConverter;
	}

	protected Converter<VariantProductModel, BaseOptionData> getBaseOptionDataConverter()
	{
		return baseOptionDataConverter;
	}

	@Required
	public void setBaseOptionDataConverter(final Converter<VariantProductModel, BaseOptionData> baseOptionDataConverter)
	{
		this.baseOptionDataConverter = baseOptionDataConverter;
	}


	/**
	 * @return the variantsUtils
	 */
	public VariantsUtils getVariantsUtils()
	{
		return variantsUtils;
	}


	/**
	 * @param variantsUtils the variantsUtils to set
	 */
	@Required
	public void setVariantsUtils(VariantsUtils variantsUtils)
	{
		this.variantsUtils = variantsUtils;
	}
	
	
}