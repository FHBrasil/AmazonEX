package br.hering.core.search.solrfacetsearch.provider.impl;

import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.media.MediaFormatModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.media.MediaContainerService;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractPropertyFieldValueProvider;
import de.hybris.platform.variants.model.VariantProductModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import br.hering.core.model.HeringProductModel;
import br.hering.core.model.HeringSizeVariantProductModel;
import br.hering.core.model.HeringStyleVariantProductModel;

/**
 * @author Wagner Ribeiro
 */
public class HeringShowCaseImageProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider, Serializable
{
	private static final Logger LOG = Logger.getLogger(HeringShowCaseImageProvider.class);
	private String mediaFormat;
	private MediaService mediaService;
	private MediaContainerService mediaContainerService;
	private FieldNameProvider fieldNameProvider;

	@Resource
	private VariantsUtils variantsUtils;

	protected String getMediaFormat()
	{
		return this.mediaFormat;
	}

	@Required
	public void setMediaFormat(String mediaFormat)
	{
		this.mediaFormat = mediaFormat;
	}

	protected MediaService getMediaService()
	{
		return this.mediaService;
	}

	@Required
	public void setMediaService(MediaService mediaService)
	{
		this.mediaService = mediaService;
	}

	protected MediaContainerService getMediaContainerService()
	{
		return this.mediaContainerService;
	}

	@Required
	public void setMediaContainerService(MediaContainerService mediaContainerService)
	{
		this.mediaContainerService = mediaContainerService;
	}

	protected FieldNameProvider getFieldNameProvider()
	{
		return this.fieldNameProvider;
	}

	@Required
	public void setFieldNameProvider(FieldNameProvider fieldNameProvider)
	{
		this.fieldNameProvider = fieldNameProvider;
	}

	@Override
	public Collection<FieldValue> getFieldValues(IndexConfig indexConfig, IndexedProperty indexedProperty, Object model)
			throws FieldValueProviderException
	{
		MediaFormatModel mediaFormatModel = getMediaService().getFormat(getMediaFormat());

		if (mediaFormatModel != null)
		{
			Collection<String> medias = findMedia((ProductModel) model, mediaFormatModel);

			final Collection<String> fieldNames = fieldNameProvider.getFieldNames(indexedProperty, null);
			final List<FieldValue> values = new ArrayList<FieldValue>();

			for (String item : medias)
			{
				for (final String fieldName : fieldNames)
				{
					values.add(new FieldValue(fieldName, item));
				}
			}

			return values;
		}

		return Collections.emptyList();
	}

	protected Collection<String> findMedia(ProductModel product, MediaFormatModel mediaFormat)
	{
		Collection<String> imageList = new ArrayList<String>();

		if (product instanceof HeringSizeVariantProductModel)
		{
			HeringSizeVariantProductModel sizeVariant = (HeringSizeVariantProductModel) product;
			HeringStyleVariantProductModel styleVariant = (HeringStyleVariantProductModel) sizeVariant.getBaseProduct();
			HeringProductModel model = (HeringProductModel) styleVariant.getBaseProduct();
			Collection<HeringStyleVariantProductModel> variants = variantsUtils.getAvailableStyleVariants(model);

			for (VariantProductModel variant : variants)
			{
				List<MediaContainerModel> galleryImages = variant.getGalleryImages();
				for (MediaContainerModel container : galleryImages)
				{
					MediaModel media = null;
					try
					{
						media = getMediaContainerService().getMediaForFormat(container, mediaFormat);
					}

					catch (Exception e)
					{
						LOG.info("Error on findMedia():exception:{" + e 
								+ "}, product:{code:" + product.getCode() 
								+ "mediaFormat:{" + mediaFormat.getQualifier() + "}");
						e.printStackTrace();
					}

					if (media != null)
					{
						Collection<String> imageListTemp = imageList;
						String imageSourceNameBase = variant.getCode().replace("_", "-");
						boolean existImageForThisColor = false;

						for (String value : imageListTemp)
						{
							if (value.contains(imageSourceNameBase))
							{
								existImageForThisColor = true;
								break;
							}
						}

						if (!existImageForThisColor)
						{
							imageList.add(media.getURL());
						}
					}
				}
			}
		}

		if (imageList.isEmpty())
		{
			MediaModel productMedia = findProductMedia(product, mediaFormat);
			if (productMedia != null)
			{
				imageList.add(productMedia.getURL());
			}
		}

		return imageList;
	}

	private MediaModel findProductMedia(ProductModel product, MediaFormatModel mediaFormat)
	{
		if ((product != null) && (mediaFormat != null))
		{
			List<MediaContainerModel> galleryImages = product.getGalleryImages();

			if ((galleryImages != null) && (!(galleryImages.isEmpty())))
			{
				for (MediaContainerModel container : galleryImages)
				{
					try
					{
						MediaModel media = getMediaContainerService().getMediaForFormat(container, mediaFormat);
						if (media != null)
						{
							return media;
						}
					}
					catch (ModelNotFoundException localModelNotFoundException)
					{
						LOG.info("Error on findProductMedia():exception:{" + localModelNotFoundException 
								+ "}, product:{code:" + product.getCode() 
								+ "mediaFormat:{" + mediaFormat.getQualifier() + "}");
					}
				}
			}

			if (product instanceof VariantProductModel)
			{
				return findProductMedia(((VariantProductModel) product).getBaseProduct(), mediaFormat);
			}
		}
		return null;
	}

	protected Collection<FieldValue> createFieldValues(IndexedProperty indexedProperty, MediaModel media)
	{
		return createFieldValues(indexedProperty, media.getURL());
	}

	protected Collection<FieldValue> createFieldValues(IndexedProperty indexedProperty, String value)
	{
		List fieldValues = new ArrayList();

		Collection<String> fieldNames = getFieldNameProvider().getFieldNames(indexedProperty, null);

		for (String fieldNameEx : fieldNames)
		{
			fieldValues.add(new FieldValue(fieldNameEx, value));
		}

		return fieldValues;
	}
}