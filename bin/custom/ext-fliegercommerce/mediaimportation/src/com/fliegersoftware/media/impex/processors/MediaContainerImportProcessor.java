/**
 *
 */
package com.fliegersoftware.media.impex.processors;

import de.hybris.platform.core.Registry;
import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.impex.jalo.header.StandardColumnDescriptor;
import de.hybris.platform.impex.jalo.imp.DefaultImportProcessor;
import de.hybris.platform.impex.jalo.imp.ValueLine;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.Map;

import org.apache.log4j.Logger;

import com.fliegersoftware.media.strategies.ConvertMediaContainerMediasStrategy;


/**
 * Impex import processor used to run business logic after each {@link MediaContainerModel} item imported via impex.<br />
 * The interceptor must be specified in the import header of your impex file.<br />
 * This processor is responsible for converting the medias of the current {@link MediaContainerModel}.
 *
 * @author Franthescolly Maneira
 */
public class MediaContainerImportProcessor extends DefaultImportProcessor
{
	/**
	 * Logger
	 */
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(MediaContainerImportProcessor.class);

	/**
	 * model service
	 */
	private ModelService modelService;

	/**
	 * Strategy used to convert the {@link MediaContainerModel} medias
	 */
	private ConvertMediaContainerMediasStrategy convertMediaContainerMediasStrategy;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.impex.jalo.imp.DefaultImportProcessor#processItemCreation(de.hybris.platform.jalo.type.ComposedType
	 * , java.util.Map, de.hybris.platform.impex.jalo.imp.ValueLine)
	 */
	@Override
	protected Item processItemCreation(final ComposedType targetType,
			final Map<StandardColumnDescriptor, Object> attributeValueMappings, final ValueLine valueLine) throws ImpExException
	{
		final Item item = super.processItemCreation(targetType, attributeValueMappings, valueLine);

		convertMedias(item);

		return item;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.impex.jalo.imp.DefaultImportProcessor#processItemUpdate(de.hybris.platform.jalo.Item,
	 * java.util.Map, de.hybris.platform.impex.jalo.imp.ValueLine)
	 */
	@Override
	protected void processItemUpdate(final Item toUpdate, final Map<StandardColumnDescriptor, Object> attributeValueMappings,
			final ValueLine valueLine) throws ImpExException
	{
		super.processItemUpdate(toUpdate, attributeValueMappings, valueLine);

		convertMedias(toUpdate);
	}

	/**
	 * Converts the medias of a {@link MediaContainerModel}. <br/>
	 *
	 * @param item
	 *           instance of a {@link MediaContainerModel}
	 */
	private void convertMedias(final Item item)
	{
		final MediaContainerModel container = getModelService().get(item);

		getConvertMediaContainerMediasStrategy().convertMedias(container);
	}

	/**
	 * @return the model service
	 */
	private ModelService getModelService()
	{
		if (modelService == null)
		{
			modelService = Registry.getApplicationContext().getBean("modelService", ModelService.class);
		}

		return modelService;
	}

	/**
	 * @param modelService
	 *           the modelService to set
	 */
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	/**
	 * @return the convertMediaContainerMediasStrategy
	 */
	public ConvertMediaContainerMediasStrategy getConvertMediaContainerMediasStrategy()
	{
		if (convertMediaContainerMediasStrategy == null)
		{
			convertMediaContainerMediasStrategy = Registry.getApplicationContext().getBean("convertMediaContainerMediasStrategy",
					ConvertMediaContainerMediasStrategy.class);
		}

		return convertMediaContainerMediasStrategy;
	}

	/**
	 * @param convertMediaContainerMediasStrategy
	 *           the convertMediaContainerMediasStrategy to set
	 */
	public void setConvertMediaContainerMediasStrategy(
			final ConvertMediaContainerMediasStrategy convertMediaContainerMediasStrategy)
	{
		this.convertMediaContainerMediasStrategy = convertMediaContainerMediasStrategy;
	}
}