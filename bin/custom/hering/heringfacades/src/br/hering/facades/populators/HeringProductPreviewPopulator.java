/**
 * 
 */
package br.hering.facades.populators;

import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commerceservices.url.UrlResolver;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.variants.model.VariantProductModel;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import br.hering.core.model.HeringSizeVariantProductModel;
import br.hering.core.model.HeringStyleVariantProductModel;
import br.hering.facades.product.data.ColorData;
import br.hering.facades.product.preview.data.ProductPreviewData;

/**
 * @author franthescollymaneira
 *
 */
public class HeringProductPreviewPopulator implements Populator<ProductModel, ProductPreviewData>
{
	@Resource
	private UrlResolver<ProductModel> productModelUrlResolver;
	
	@Resource
	private Populator<ProductModel, ProductData> productPrimaryImagePopulator;

	/* (non-Javadoc)
	 * @see de.hybris.platform.converters.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(ProductModel source, ProductPreviewData target) throws ConversionException
	{
		target.setCode(source.getCode());
		populateColors(source, target);
		populateImages(source, target);
		populateURL(source, target);
	}

	/**
	 * @param target
	 */
	private void populateColors(ProductModel source, ProductPreviewData target)
	{
		if(source instanceof HeringSizeVariantProductModel)
		{
			HeringSizeVariantProductModel sv = (HeringSizeVariantProductModel) source;
			
			ColorData colorData = new ColorData();
			colorData.setRGB(sv.getColorRGB());

			target.setColor(colorData);
			target.setSize(sv.getSize());
		}		
		
		if (source instanceof HeringStyleVariantProductModel)
		{
			List<VariantProductModel> variants = (List) ((HeringStyleVariantProductModel) source).getVariants();

			if (CollectionUtils.isNotEmpty(variants))
			{				
				HeringSizeVariantProductModel sv = (HeringSizeVariantProductModel) variants.get(0);

				ColorData colorData = new ColorData();
				colorData.setRGB(sv.getColorRGB());

				target.setColor(colorData);
				target.setSize(sv.getSize());
			}
		}
	}

	/**
	 * @param target
	 */
	private void populateImages(ProductModel source, ProductPreviewData target)
	{
		final ProductData dummyData = new ProductData();
		productPrimaryImagePopulator.populate(source, dummyData);
		target.setImages(dummyData.getImages());
	}

	/**
	 * @param target
	 */
	private void populateURL(ProductModel source, ProductPreviewData target)
	{
		target.setUrl(getProductModelUrlResolver().resolve(source));
	}
	
	protected UrlResolver<ProductModel> getProductModelUrlResolver()
	{
		return productModelUrlResolver;
	}

	public void setProductModelUrlResolver(final UrlResolver<ProductModel> productModelUrlResolver)
	{
		this.productModelUrlResolver = productModelUrlResolver;
	}

	/**
	 * @return the productPrimaryImagePopulator
	 */
	public Populator<ProductModel, ProductData> getProductPrimaryImagePopulator()
	{
		return productPrimaryImagePopulator;
	}

	/**
	 * @param productPrimaryImagePopulator the productPrimaryImagePopulator to set
	 */
	public void setProductPrimaryImagePopulator(Populator<ProductModel, ProductData> productPrimaryImagePopulator)
	{
		this.productPrimaryImagePopulator = productPrimaryImagePopulator;
	}
}