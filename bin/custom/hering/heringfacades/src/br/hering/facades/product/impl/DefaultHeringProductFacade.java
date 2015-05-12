/**
 * 
 */
package br.hering.facades.product.impl;

import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.product.impl.DefaultProductFacade;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.variants.model.VariantProductModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import br.hering.core.model.HeringProductModel;
import br.hering.core.model.HeringSizeVariantProductModel;
import br.hering.core.model.HeringStyleVariantProductModel;
import br.hering.core.search.solrfacetsearch.provider.impl.VariantsUtils;
import br.hering.facades.product.HeringProductFacade;
import br.hering.facades.product.preview.data.ProductPreviewData;


/**
 * @author wellington
 *
 */
public class DefaultHeringProductFacade extends DefaultProductFacade implements HeringProductFacade
{
	@Resource
	private Converter<ProductModel, ProductPreviewData> productPreviewConverter;
	
	private VariantsUtils variantsUtils;
	
	/**
	 * @return the variantsUtils
	 */
	public VariantsUtils getVariantsUtils()
	{
		return variantsUtils;
	}

	/**
	 * @param variantsUtils
	 *           the variantsUtils to set
	 */
	public void setVariantsUtils(VariantsUtils variantsUtils)
	{
		this.variantsUtils = variantsUtils;
	}
	
	@Override
	public List<ProductPreviewData> getAvailableStyleVariants(String code)
	{		
		HeringProductModel product = getBase(getProductService().getProductForCode(code));
		
		List<HeringStyleVariantProductModel> listStyles = variantsUtils.getAvailableStyleVariants(product);
		
		final List<ProductPreviewData> listProducts = new ArrayList<ProductPreviewData>();
		for (HeringStyleVariantProductModel source : listStyles)
		{
			listProducts.add(getProductPreviewConverter().convert(source));
		}

		return listProducts;
	}
	
	@Override
	public List<ProductPreviewData> getAvailableSizeVariants(String code)
	{		
		HeringProductModel product = getBase(getProductService().getProductForCode(code));			

		List<HeringSizeVariantProductModel> listStyles = variantsUtils.getAvailableSizeVariants(product);
		
		final List<ProductPreviewData> listProducts = new ArrayList<ProductPreviewData>();
		
		for (HeringSizeVariantProductModel source : listStyles)
		{
			listProducts.add(getProductPreviewConverter().convert(source));
		}

		return listProducts;
	}
	
	@Override
	public List<ProductPreviewData> getAvailableChildVariants(String code) {
		ProductModel product = getProductService().getProductForCode(code);
		List<ProductPreviewData> list = new ArrayList<>();
		for(VariantProductModel child : variantsUtils.getAvailableChildVariants(product)) {
			list.add(getProductPreviewConverter().convert(child));
		}
		return list;
	}
	
	private HeringProductModel getBase(ProductModel product)
	{
		if(product == null)
		{
			return null;
		}
		
		if(product instanceof HeringSizeVariantProductModel)
		{
			product = ((VariantProductModel) product).getBaseProduct();
		}
		
		if(product instanceof HeringStyleVariantProductModel)
		{
			product = ((VariantProductModel) product).getBaseProduct();
		}
		
		return (HeringProductModel) product;
	}

	/**
	 * @return the productPreviewConverter
	 */
	public Converter<ProductModel, ProductPreviewData> getProductPreviewConverter()
	{
		return productPreviewConverter;
	}

	/**
	 * @param productPreviewConverter the productPreviewConverter to set
	 */
	public void setProductPreviewConverter(Converter<ProductModel, ProductPreviewData> productPreviewConverter)
	{
		this.productPreviewConverter = productPreviewConverter;
	}
}
