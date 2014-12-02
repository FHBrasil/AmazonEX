/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2014 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *  
 */
package de.genkpfamily.core.jalo;

import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import de.genkpfamily.core.constants.GenkpfamilyCoreConstants;
import de.genkpfamily.core.setup.CoreSystemSetup;


/**
 * Do not use, please use {@link CoreSystemSetup} instead.
 * 
 */
@SuppressWarnings("PMD")
public class GenkpfamilyCoreManager extends GeneratedGenkpfamilyCoreManager
{
	public static final GenkpfamilyCoreManager getInstance()
	{
		final ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (GenkpfamilyCoreManager) em.getExtension(GenkpfamilyCoreConstants.EXTENSIONNAME);
	}
}
