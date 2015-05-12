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
package de.kpfamily.core.jalo;

import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import de.kpfamily.core.constants.KpfamilyCoreConstants;
import de.kpfamily.core.setup.KPFamilyCoreSystemSetup;


/**
 * Do not use, please use {@link KPFamilyCoreSystemSetup} instead.
 * 
 */
@SuppressWarnings("PMD")
public class KpfamilyCoreManager extends GeneratedKpfamilyCoreManager
{
	public static final KpfamilyCoreManager getInstance()
	{
		final ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (KpfamilyCoreManager) em.getExtension(KpfamilyCoreConstants.EXTENSIONNAME);
	}
}
