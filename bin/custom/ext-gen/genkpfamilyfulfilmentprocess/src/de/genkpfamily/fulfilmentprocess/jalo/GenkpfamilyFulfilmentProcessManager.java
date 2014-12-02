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
package de.genkpfamily.fulfilmentprocess.jalo;

import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import de.genkpfamily.fulfilmentprocess.constants.GenkpfamilyFulfilmentProcessConstants;

import org.apache.log4j.Logger;

@SuppressWarnings("PMD")
public class GenkpfamilyFulfilmentProcessManager extends GeneratedGenkpfamilyFulfilmentProcessManager
{
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger( GenkpfamilyFulfilmentProcessManager.class.getName() );
	
	public static final GenkpfamilyFulfilmentProcessManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (GenkpfamilyFulfilmentProcessManager) em.getExtension(GenkpfamilyFulfilmentProcessConstants.EXTENSIONNAME);
	}
	
}
