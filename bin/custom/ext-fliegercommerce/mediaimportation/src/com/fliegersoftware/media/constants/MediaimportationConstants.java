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
package com.fliegersoftware.media.constants;

import de.hybris.platform.core.model.media.MediaContainerModel;

import com.fliegersoftware.media.listeners.MediaContainerAfterSaveListener;


/**
 * Global class for all Mediaimportation constants. You can add global constants for your extension into this class.
 */
public final class MediaimportationConstants extends GeneratedMediaimportationConstants
{
	public static final String EXTENSIONNAME = "mediaimportation";

	private MediaimportationConstants()
	{
		//empty to avoid instantiating this constant class
	}

	/**
	 * key used to enable/disable the execution of the {@link MediaContainerAfterSaveListener}
	 */
	public static final String LISTENER_ENABLED_KEY = "mediaimportation.mediacontainer.aftersavelistener.enabled";

	/**
	 * {@link MediaContainerModel} type code
	 */
	public static final int MEDIACONTAINER_TYPE_CODE = 50;
}