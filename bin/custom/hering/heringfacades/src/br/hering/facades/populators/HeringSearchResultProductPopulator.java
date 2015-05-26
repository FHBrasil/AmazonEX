/**
 * 
 */
package br.hering.facades.populators;

import de.hybris.platform.acceleratorservices.search.comparators.SizeAttributeComparator;
import de.hybris.platform.commercefacades.product.data.ImageData;
import de.hybris.platform.commercefacades.product.data.ImageDataType;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.search.converters.populator.SearchResultProductPopulator;
import de.hybris.platform.commerceservices.search.resultdata.SearchResultValueData;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.store.services.BaseStoreService;
import de.hybris.platform.util.Config;
import de.hybris.platform.util.PriceValue;

import br.hering.facades.product.data.SizeNodeData;
import br.hering.facades.product.data.VariantSizeData;
import br.hering.facades.product.data.VariantColorData;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.adyen.services.payment.impl.AdyenCardInstallmentsService;

/**
 * @author franthescollymaneira
 *
 */
public class HeringSearchResultProductPopulator extends SearchResultProductPopulator
{
	@Resource
	protected BaseStoreService baseStoreService;

	@Resource
	protected AdyenCardInstallmentsService adyenCardInstallmentsService;

	@Resource
	protected CommonI18NService commonI18NService;

	private SizeAttributeComparator sizeAttributeComparator;

	private static final Logger LOG = Logger.getLogger(HeringSearchResultProductPopulator.class);

	/**
	 * @param sizeAttributeComparator the sizeAttributeComparator to set
	 */
	public void setSizeAttributeComparator(SizeAttributeComparator sizeAttributeComparator)
	{
		this.sizeAttributeComparator = sizeAttributeComparator;
	}

	@Override
	public void populate(final SearchResultValueData source, final ProductData target)
	{
		super.populate(source, target);
		populateOldPrice(source, target);
		populateInstallments(source, target);
		populateColorAndSize(source, target);
		populatePreviewImages(source, target);

		Boolean boollFreeShipping = isFreeShipping(this.<Double> getValue(source, "priceValue"));
		Boolean boolNewProduct = isNewProduct(this.<java.util.Date> getValue(source, "onlineDate"));
		Boolean boolBlackfriday = isBlackfriday(this.<java.lang.Boolean> getValue(source, "blackfriday"));
		
		target.setFreeShipping(boollFreeShipping.booleanValue());
		target.setNewProduct(boolNewProduct.booleanValue());
		target.setBlackfriday(boolBlackfriday.booleanValue());
	}

	private void populatePreviewImages(final SearchResultValueData source, final ProductData target)
	{
		String urlPrefix = Config.getParameter("website.img");
		String imageFormat = "img-" + getImageFormatMapping().getMediaFormatQualifierForImageFormat("store");
		List<String> images = getValue(source, imageFormat);
		Collection<ImageData> imageList = new ArrayList<ImageData>(); 
		
		if(images != null ){
			
			for( String image : images ){
				ImageData imageData = new ImageData();
				imageData.setUrl(urlPrefix + image);
				imageData.setAltText(target.getName());
				imageList.add(imageData);
			}
		}

		target.setPreviewImages(imageList);
	}
	
	
	private Boolean isBlackfriday(Boolean value)
	{
		Boolean result = Boolean.FALSE;
		
		if(value != null)
		{
			return value;
		}
		
		return result;
	}

	private void populateColorAndSize(final SearchResultValueData source, final ProductData target)
	{
		final List<String> allVariants = getValue(source, "codigoCor");
		
		Collection<VariantColorData> colors = new ArrayList<VariantColorData>();
		Collection<SizeNodeData> sizes = new ArrayList<SizeNodeData>();

		if(allVariants != null){
			
			for( String variant : allVariants ){
				
				String[] variants = variant.split(":");
				
				String  color = variants[0];
				String[] colorOptions = color.split(";");
				
				String  size = variants[1];
				String[] sizeOptions = size.split(";");
				
				VariantColorData populatedColor = populateColor(colorOptions);
				SizeNodeData populatedSize = populateSizes(populatedColor.getColorCode(), sizeOptions);
				
				colors.add( populatedColor );
				sizes.add( populatedSize );
			}
			
			target.setColors(colors);
			target.setSizes(sizes);
		}
	}
	
	private VariantColorData populateColor( String[] colorOptions )
	{
		VariantColorData color = new VariantColorData();
		
		color.setColorCode(colorOptions[0]);
		color.setRGB(colorOptions[1]);
		color.setColorName(colorOptions[2]);
		
		return color;
	}

	private SizeNodeData populateSizes(String colorCode, String[] sizeOptions)
	{
		Collection<VariantSizeData> sizeColletion = new ArrayList<VariantSizeData>(); 
		Iterator<String> iteratorList = Arrays.asList(sizeOptions).iterator();
		SizeNodeData node = new SizeNodeData();

		while(iteratorList.hasNext()){

			VariantSizeData variantSize = new VariantSizeData();
			String value = iteratorList.next();
			String[] sizeValues = value.split("-");

			variantSize.setProductCode(sizeValues[0]);
			variantSize.setSizeValue(sizeValues[1]);

			sizeColletion.add(variantSize);
		}

		node.setColorCode(colorCode);
		node.setSizeValue(sizeColletion);

		return node;
	}

	/**
	 * @param source
	 * @param target
	 */
	private void populateOldPrice(SearchResultValueData source, ProductData target)
	{
		final Double priceValue = this.<Double> getValue(source, "priceValue");
		final Double oldPrice = this.<Double> getValue(source, "oldPrice");
		if (oldPrice != null && priceValue != null)
		{
			if (priceValue.doubleValue() < oldPrice.doubleValue())
			{
				target.setOldPrice(BigDecimal.valueOf(oldPrice.doubleValue()));
			}
		}
	}

	protected void populateInstallments(final SearchResultValueData source, final ProductData target)
	{
		final Double priceValue = this.<Double> getValue(source, "priceValue");

		if (priceValue != null)
		{
			String currencyIso = commonI18NService.getCurrentCurrency().getIsocode();
			boolean net = baseStoreService.getCurrentBaseStore().isNet();
			PriceValue productPrice = new PriceValue(currencyIso, priceValue.doubleValue(), net);

			int installments = adyenCardInstallmentsService.calculateAvailableInstallments(productPrice);
			PriceValue installmentPrice = adyenCardInstallmentsService.calculateInstallmentCost(productPrice);

			target.setPriceParcels(installments);
			target.setParcelUnitPrice(installmentPrice.getValue());
		}
	}

	@Override
	protected List<ImageData> createImageData(SearchResultValueData source)
	{
		List<ImageData> result = super.createImageData(source);

		addImageData(source, "store", result);

		return result;
	}

	@Override
	public void addImageData(final SearchResultValueData source, 
			final String imageFormat, final String mediaFormatQualifier,
			final ImageDataType type, final List<ImageData> images)
	{
		String facetId = "img-" + mediaFormatQualifier;
		Object imgValue = getValue(source, facetId);
		
		if( imgValue instanceof String )
		{
			final String SingleImgValue = (String) imgValue;
			
			if (StringUtils.isNotBlank(SingleImgValue))
			{
				images.add(getImage(type, imageFormat, SingleImgValue));
			}
		}
		else if( imgValue instanceof ArrayList )
		{
			ArrayList<String> multiImgValue = (ArrayList<String>) imgValue;
			
			for( String value : multiImgValue )
			{
				images.add(getImage(type, imageFormat, value));
			}
		}
	}
	
	private ImageData getImage(final ImageDataType type, final String imageFormat, String SingleImgValue)
	{
		final ImageData imageData = createImageData();
		String urlPrefix = Config.getParameter("website.img");
		imageData.setImageType(type);
		imageData.setFormat(imageFormat);
		imageData.setUrl(urlPrefix + SingleImgValue);
		return imageData;
	}

	private Boolean isFreeShipping(Double priceList) // Verify if the product are eligible for Free Shipping FLAG
	{
		Double threshold = baseStoreService.getCurrentBaseStore().getFreeDeliveryThreshold();
		Boolean boolProductfree = Boolean.valueOf(false);

		try
		{
			if (priceList.doubleValue() > threshold.doubleValue()) // Free Shipping 
			{
				boolProductfree = Boolean.valueOf(true);
				return boolProductfree;
			}
			else
			{
				boolProductfree = Boolean.valueOf(false); // Not Free Shipping 
				return boolProductfree;
			}
		}
		catch (Exception e)
		{
			boolProductfree = Boolean.valueOf(false);
			return boolProductfree;
		}
	}

	protected Boolean isNewProduct(final java.util.Date date) // Verify if the product are eligible for NEW FLAG
	{
		Boolean boolProductOnlineDate = Boolean.valueOf(false);
		try
		{
			if (date.equals(null))
			{
				boolProductOnlineDate = Boolean.valueOf(false);
				return boolProductOnlineDate;
			}
			else
			{
				java.util.Date dateProductOnlineDate = date;
				java.util.Calendar calendarAtualDate = java.util.Calendar.getInstance();

				calendarAtualDate.add(java.util.Calendar.DAY_OF_YEAR, Integer.parseInt(Config.getParameter("heringfacades.isNewProduct"))); // lower then 30 days are new product
				Calendar calendarProductOnlineDate = new GregorianCalendar();
				calendarProductOnlineDate.setTime(dateProductOnlineDate);

				if (calendarAtualDate.compareTo(calendarProductOnlineDate) == -1)// The product is new
				{
					boolProductOnlineDate = Boolean.valueOf(true);
					return boolProductOnlineDate;
				}
				else
				{
					boolProductOnlineDate = Boolean.valueOf(false);
					return boolProductOnlineDate;
				}
			}

		}
		catch (Exception e)
		{
			boolProductOnlineDate = Boolean.valueOf(false);
			return boolProductOnlineDate;
		}
	}
}