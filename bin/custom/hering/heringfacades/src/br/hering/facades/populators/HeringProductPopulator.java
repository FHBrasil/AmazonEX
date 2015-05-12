/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 * 
 *  
 */
package br.hering.facades.populators;

import de.hybris.platform.acceleratorservices.search.comparators.SizeAttributeComparator;
import de.hybris.platform.classification.ClassificationService;
import de.hybris.platform.classification.features.Feature;
import de.hybris.platform.classification.features.FeatureList;
import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.commercefacades.product.ImageFormatMapping;
import de.hybris.platform.commercefacades.product.data.ImageData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.enums.Gender;
import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.media.MediaFormatModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.media.MediaContainerService;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.store.services.BaseStoreService;
import de.hybris.platform.util.Config;
import de.hybris.platform.util.PriceValue;
import de.hybris.platform.variants.model.VariantProductModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import br.hering.core.model.HeringProductModel;
import br.hering.core.model.HeringSizeVariantProductModel;
import br.hering.core.model.HeringStyleVariantProductModel;
import br.hering.core.model.ProductMeasurementsModel;
import br.hering.core.search.solrfacetsearch.provider.impl.VariantsUtils;
import br.hering.facades.product.data.ColorData;
import br.hering.facades.product.data.GenderData;
import br.hering.facades.product.data.MeasurementsData;
import br.hering.facades.product.data.SizeNodeData;
import br.hering.facades.product.data.VariantColorData;
import br.hering.facades.product.data.VariantSizeData;

import com.adyen.services.payment.impl.AdyenCardInstallmentsService;


/**
 * Populates {@link ProductData} with genders
 */
public class HeringProductPopulator implements Populator<ProductModel, ProductData>
{
	private static final Logger LOG = Logger.getLogger(HeringProductPopulator.class);
	
	@Resource
	private ImageFormatMapping imageFormatMapping;
	
	@Resource(name = "cmsSiteService")
	private CMSSiteService cmsSiteService;
	
	@Resource
	private MediaService mediaService;
	
	@Resource
	private MediaContainerService mediaContainerService;

	@Resource
	private Converter<Gender, GenderData> genderConverter;
	
	@Resource
	private Converter<ProductMeasurementsModel , MeasurementsData> measurementsConverter;
	
	@Resource
	protected BaseStoreService baseStoreService;
	
	@Resource
	private VariantsUtils variantsUtils;
	
	@Resource
	protected ClassificationService classificationService;
	
	@Resource
	protected CommonI18NService commonI18NService;
	
	@Resource
	protected AdyenCardInstallmentsService adyenCardInstallmentsService;
	
	private SizeAttributeComparator sizeAttributeComparator;
	
	protected Converter<Gender, GenderData> getGenderConverter()
	{
		return genderConverter;
	}

	@Required
	public void setGenderConverter(final Converter<Gender, GenderData> genderConverter)
	{
		this.genderConverter = genderConverter;
	}

	@Override
	public void populate(final ProductModel source, final ProductData target) throws ConversionException
	{
		target.setBrand(source.getCatalogVersion().getCatalog().getBaseStores().iterator().next().getUid());
		final ProductModel baseProduct = getBaseProduct(source);

		if (baseProduct instanceof HeringProductModel)
		{
			final HeringProductModel heringProductModel = (HeringProductModel) baseProduct;
			if (CollectionUtils.isNotEmpty(heringProductModel.getGenders()))
			{
				populatePriceInfo(source, target);
				populateGender(target, heringProductModel);
				populateColorAndSize(heringProductModel, target);
				populatePreviewImages(heringProductModel,target);
				populateUrl(heringProductModel,target);

				target.setOnlinedate(source.getOnlineDate());
				target.setNewProduct(isNewProduct(source.getOnlineDate()).booleanValue());
				target.setFreeShipping(isFreeShipping(source.getEurope1Prices()).booleanValue());
				target.setBlackfriday(isBlackfriday(source).booleanValue());
			}
			
			if(CollectionUtils.isNotEmpty(heringProductModel.getMeasurementList()))
			{
				List<MeasurementsData> measurementsList = new ArrayList<MeasurementsData>();
				
				for(ProductMeasurementsModel measurement :  heringProductModel.getMeasurementList())
				{
					measurementsList.add(measurementsConverter.convert(measurement) );
				}
				
				target.setMeasurements(measurementsList);
			}
		}
		
		if(source instanceof HeringSizeVariantProductModel)
		{
			HeringSizeVariantProductModel sv = (HeringSizeVariantProductModel) source;
			ColorData colorData = new ColorData();
			colorData.setBasicDescription(sv.getColorBasicDescription());
			colorData.setFullDescription(sv.getColorFullDescription());
			colorData.setRGB(getSanitizedColor(sv));
			target.setColor(colorData);
			target.setSize(sv.getSize());
		}
		
		if (source instanceof HeringStyleVariantProductModel)
		{
			List<VariantProductModel> variants1 = (List) ((HeringStyleVariantProductModel) source).getVariants();
			if (CollectionUtils.isNotEmpty(variants1))
			{				
				HeringSizeVariantProductModel sv = (HeringSizeVariantProductModel) variants1.get(0);
				ColorData colorData = new ColorData();
				colorData.setBasicDescription(sv.getColorBasicDescription());
				colorData.setFullDescription(sv.getColorFullDescription());
				colorData.setRGB(getSanitizedColor(sv));
				target.setColor(colorData);
				target.setSize(sv.getSize());
			}
		}
	}
	
	private void populateUrl(HeringProductModel baseProduct, final ProductData target) 
	{
		List<HeringStyleVariantProductModel> colors = variantsUtils.getAvailableStyleVariants(baseProduct);
		if(colors.size() > 0) {
			List<HeringSizeVariantProductModel> sizesAvailable = variantsUtils.getAvailableSizeVariants(colors.get(0));
			String productCode = sizesAvailable.get(0).getCode();
			String[] url = target.getUrl().split("/");
			url = Arrays.copyOf(url, url.length-1);
			target.setUrl(StringUtils.join(url,"/").concat("/").concat(productCode));
		}
	}
	
	private void populatePreviewImages(HeringProductModel baseProduct, final ProductData target)
	{
		List<HeringStyleVariantProductModel> styleVariants = variantsUtils.getAvailableStyleVariants(baseProduct);
		Collection<ImageData> imageList = new ArrayList<ImageData>();
		String urlPrefix = Config.getParameter("website.img");
		String imageDimension = imageFormatMapping.getMediaFormatQualifierForImageFormat("store");
		MediaFormatModel mediaFormatModel = mediaService.getFormat(imageDimension);
		for (VariantProductModel styleVariant : styleVariants)
		{
			ImageData variantMedia = getVariantMedia(styleVariant, urlPrefix, mediaFormatModel);
			CollectionUtils.addIgnoreNull(imageList, variantMedia);
		}
		target.setPreviewImages(imageList);
	}
	
	private ImageData getVariantMedia( final VariantProductModel variant,
			final String urlPrefix,	final MediaFormatModel mediaFormatModel )
	{
		List<MediaContainerModel> galleryImages = variant.getGalleryImages();
		
		try{
			for (MediaContainerModel container : galleryImages)
			{
				MediaModel media = mediaContainerService.getMediaForFormat(container, mediaFormatModel);
				if (media != null )
				{
					return transformImageSrctoImageData(urlPrefix+media.getURL());
				}
			}
		}
		catch(Exception e)
		{
			LOG.error("error on get image:" + variant.getCode());
		}
		return null;
	}
	
	private void populateColorAndSize(final HeringProductModel source, final ProductData target)
	{
		Collection<VariantColorData> colors = new ArrayList<VariantColorData>();
		Collection<SizeNodeData> sizes = new ArrayList<SizeNodeData>();
		List<HeringStyleVariantProductModel> colorsAvailable = variantsUtils.getAvailableStyleVariants(source);
		
		for(HeringStyleVariantProductModel variant : colorsAvailable)
		{
			List<HeringSizeVariantProductModel> sizesAvailable = variantsUtils.getAvailableSizeVariants(variant);
			if(CollectionUtils.isNotEmpty(sizesAvailable))
			{
				VariantColorData populatedColor = populateColor(variant, sizesAvailable);
				SizeNodeData populatedSize = populateSizes( populatedColor.getColorCode(), sizesAvailable );
				colors.add( populatedColor );
				sizes.add( populatedSize );
			}
		}
		target.setColors(colors);
		target.setSizes(sizes);
	}
	
	private VariantColorData populateColor( HeringStyleVariantProductModel colorOptions, List<HeringSizeVariantProductModel> sizesAvailable )
	{
		VariantColorData color = new VariantColorData();
		HeringSizeVariantProductModel firstSize = sizesAvailable.iterator().next();

		color.setColorCode(colorOptions.getCode().replace("_", ""));
		color.setRGB(getSanitizedColor(firstSize));
		color.setColorName(colorOptions.getStyle());
		
		return color;
	}

	private SizeNodeData populateSizes(String colorCode, Collection<HeringSizeVariantProductModel> sizeOptions)
	{
		Collection<VariantSizeData> sizeColletion = new ArrayList<VariantSizeData>(); 
		SizeNodeData node = new SizeNodeData();

		for( HeringSizeVariantProductModel heringSize : sizeOptions )
		{
			VariantSizeData variantSize = new VariantSizeData();
			variantSize.setProductCode(heringSize.getCode());
			variantSize.setSizeValue(heringSize.getSize());
			sizeColletion.add(variantSize);
		}

		node.setColorCode(colorCode);
		node.setSizeValue(sizeColletion);

		return node;
	}
	
	private ImageData transformImageSrctoImageData(String imageSrc)
	{
		final ImageData imageData = new ImageData();
		imageData.setUrl(imageSrc);
		return imageData;
	}
	

	/**
	 * @param source
	 * @return Boolean
	 */
	private Boolean isBlackfriday(ProductModel source)
	{
		final String rawValue = getFeatureValue(source, "Tag");
		final String strTagBF = Config.getParameter("heringfacades.tag.black-friday");
		boolean showBF = Boolean.valueOf(Config.getParameter("heringfacades.show.black-friday")).booleanValue();
		
		if(StringUtils.isBlank(rawValue) || StringUtils.isBlank(strTagBF) || !showBF)
		{
			return Boolean.FALSE;
		}	
		
		Set<String> tagsBF = new HashSet<String>(Arrays.asList(strTagBF.split(";")));
		String listTags [] = rawValue.split(";");
		
		for (String tag : listTags)
		{
			if(tagsBF.contains(tag)) 
			{
				return Boolean.TRUE;
			}
		}
		
		return Boolean.FALSE;
	}

	/**
	 * @param target
	 * @param heringProductModel
	 */
	private void populateGender(final ProductData target, final HeringProductModel heringProductModel)
	{
		final List<GenderData> genders = new ArrayList<GenderData>();
		for (final Gender gender : heringProductModel.getGenders())
		{
			genders.add(getGenderConverter().convert(gender));
		}

		target.setGenders(genders);
	}

	/**
	 * @param source
	 * @param target
	 */
	private void populatePriceInfo(final ProductModel source, final ProductData target)
	{
		if(CollectionUtils.isEmpty(source.getEurope1Prices()))
		{
			return;
		}
		
		PriceRowModel priceModel = source.getEurope1Prices().iterator().next();
		double price = priceModel.getPrice().doubleValue();
		
		target.setFromPrice(hasFromPrice(source.getOldPrice(), price));
		target.setOldPrice(source.getOldPrice());
		
		int installments = adyenCardInstallmentsService.calculateAvailableInstallments(priceModel);
		PriceValue installmentPrice = adyenCardInstallmentsService.calculateInstallmentCost(priceModel);
		
		target.setPriceParcels(installments);
		target.setParcelUnitPrice(installmentPrice.getValue());
		
		target.setAllParcelsSimpleSplitedArray(getInstallmentsAsString(priceModel));
	}

	/**
	 * 
	 * @param priceModel
	 * @return String
	 */
	private String getInstallmentsAsString(PriceRowModel priceModel)
	{
		final List<String> installments = adyenCardInstallmentsService.formatInstallmentsCost(priceModel);
		
		return StringUtils.join(installments, "/");
	}
	
	/**
	 * 
	 * @param oldPrice
	 * @param curr
	 * @return boolean
	 */
	private boolean hasFromPrice(BigDecimal oldPrice, double curr)
	{
		double old = oldPrice != null ? oldPrice.doubleValue() : 0;
		return old > 0 && curr < old;
	}

	/**
	 * @return Boolean
	 */
	private Boolean isFreeShipping(Collection<PriceRowModel> priceList) // Verify if the product are eligible for Free Shipping FLAG
	{	
		Double threshold = baseStoreService.getCurrentBaseStore().getFreeDeliveryThreshold();
		Boolean boolProductfree = Boolean.valueOf(false);
		try{
			
   		Collection<PriceRowModel> list = new ArrayList<PriceRowModel>();
   		list.addAll(priceList);
   		for (Iterator<PriceRowModel> iterator = priceList.iterator(); iterator.hasNext();)
   		{
   			PriceRowModel price = iterator.next();
   			if (price.getPrice().doubleValue() > threshold.doubleValue()) // Free Shipping 
   			{
   				boolProductfree = Boolean.valueOf(true);
   				return boolProductfree; 
   			}
   			else{
   				boolProductfree = Boolean.valueOf(false); // Not Free Shipping 
   				return boolProductfree; 
   			}
   		}
   		return boolProductfree; 
		}
		catch (Exception e)
		{
			boolProductfree = Boolean.valueOf(false);
			return boolProductfree;
		}
		
	}

	protected ProductModel getBaseProduct(final ProductModel productModel)
	{

		ProductModel currentProduct = productModel;
		while (currentProduct instanceof VariantProductModel)
		{
			final VariantProductModel variant = (VariantProductModel) currentProduct;
			currentProduct = variant.getBaseProduct();
		}
		return currentProduct;
	}
	

	
	protected Boolean isNewProduct (final java.util.Date productOnlineDate) // Verify if the product are eligible for NEW FLAG
	{ 
		Boolean boolproductOnlineDate = Boolean.valueOf(false);
		try{
		
		if(productOnlineDate.equals(null)) 
		{
			boolproductOnlineDate = Boolean.valueOf(false);
			return boolproductOnlineDate;
		}
		else
		{
				java.util.Date dateproductOnlineDate = productOnlineDate;
				java.util.Calendar calendarAtualDate = java.util.Calendar.getInstance(); 
				
				calendarAtualDate.add(java.util.Calendar.DAY_OF_YEAR, Integer.parseInt(Config.getParameter("heringfacades.isNewProduct"))); // lower then 30 days are new product --> re-Definido pela Alessandra
				Calendar calendarproductOnlineDate = new GregorianCalendar();
				calendarproductOnlineDate.setTime(dateproductOnlineDate);
				
				if(calendarAtualDate.compareTo(calendarproductOnlineDate) == -1)// The product is new
				{	
					boolproductOnlineDate = Boolean.valueOf(true);
					return boolproductOnlineDate;
				}
				else{
					boolproductOnlineDate = Boolean.valueOf(false);
					return boolproductOnlineDate;
				}
			}

		}
		catch (Exception e)
		{
			boolproductOnlineDate = Boolean.valueOf(false);
			return boolproductOnlineDate;
		}
	}
	
	private String getSanitizedColor(final Object model)
	{
		String rawValue = getFeatureValue((ProductModel) model, "RGB");
		
		if(StringUtils.isBlank(rawValue))
		{
			return null;
		}
		
		String keyPrefix = "color.match.";
		
		String key = keyPrefix + rawValue.replaceAll("#", "");
		
		return Config.getString(key, rawValue);
	}
	
	protected <T> T getFeatureValue(final ProductModel product, final String featureName)
	{
		final FeatureList featureList = classificationService.getFeatures(product);

		final Feature feature = featureList.getFeatureByName(featureName);

		if (feature == null || feature.getValue() == null)
		{
			return null;
		}
		
		Object value = feature.getValue().getValue();
		
		if(value == null)
		{
			return null;
		}
		
		return (T) value;
	}
	
	public void setSizeAttributeComparator(SizeAttributeComparator sizeAttributeComparator)
	{
		this.sizeAttributeComparator = sizeAttributeComparator;
	}

}


