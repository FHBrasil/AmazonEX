/**
 *
 */
package com.fliegersoftware.media.interceptors;

import static de.hybris.platform.core.model.media.MediaContainerModel.MEDIAS;
import static de.hybris.platform.servicelayer.model.ModelContextUtils.getItemModelContext;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.mediaconversion.MediaConversionService;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;
import de.hybris.platform.servicelayer.interceptor.ValidateInterceptor;
import de.hybris.platform.servicelayer.model.ItemModelContext;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.BooleanUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;


/**
 * ValidateInterceptor that validates a {@link MediaContainerModel} checking if the medias attribute is not empty.
 *
 * @author Franthescolly Maneira
 *
 */
public class MediaContainerModificationsInterceptor implements ValidateInterceptor, PrepareInterceptor
{
	private MediaConversionService mediaConversionService;

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.servicelayer.interceptor.PrepareInterceptor#onPrepare(java.lang.Object,
	 * de.hybris.platform.servicelayer.interceptor.InterceptorContext)
	 */
	@Override
	public void onPrepare(final Object object, final InterceptorContext context) throws InterceptorException
	{
		Assert.isInstanceOf(MediaContainerModel.class, object);

		final MediaContainerModel container = (MediaContainerModel) object;
		
		if (!allowInterception(container))
		{
			return;
		}

		final ItemModelContext modelContext = getItemModelContext(container);

		if (modelContext.isNew() || !context.isModified(container, MEDIAS))
		{
			return;
		}

		getMediaConversionService().deleteConvertedMedias(container);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.servicelayer.interceptor.ValidateInterceptor#onValidate(java.lang.Object,
	 * de.hybris.platform.servicelayer.interceptor.InterceptorContext)
	 */
	@Override
	public void onValidate(final Object object, final InterceptorContext context) throws InterceptorException
	{
		Assert.isInstanceOf(MediaContainerModel.class, object);

		final MediaContainerModel container = (MediaContainerModel) object;

		if (!allowInterception(container))
		{
			return;
		}

		if (CollectionUtils.isEmpty(container.getMedias()))
		{
			throw new InterceptorException(String.format("Container %s has no medias", container.getQualifier()));
		}
	}

	/**
	 * Verifies if the interceptor is allowed to run, by the default we verify if the container
	 * {@link CatalogVersionModel} is not active.
	 *
	 * @param container
	 *           The intercepted {@link MediaContainerModel}
	 * @return true if the interception is allowed, false otherwise.
	 */
	protected boolean allowInterception(final MediaContainerModel container)
	{
		final CatalogVersionModel catalogVersion = container.getCatalogVersion();
		if (catalogVersion != null && BooleanUtils.isTrue(catalogVersion.getActive()))
		{
			return false;
		}

		return true;
	}

	/**
	 * @return the mediaConversionService
	 */
	public MediaConversionService getMediaConversionService()
	{
		return mediaConversionService;
	}

	/**
	 * @param mediaConversionService
	 *           the mediaConversionService to set
	 */
	@Required
	public void setMediaConversionService(final MediaConversionService mediaConversionService)
	{
		this.mediaConversionService = mediaConversionService;
	}
}