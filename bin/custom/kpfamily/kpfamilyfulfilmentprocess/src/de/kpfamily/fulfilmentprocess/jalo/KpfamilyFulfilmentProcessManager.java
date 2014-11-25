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
package de.kpfamily.fulfilmentprocess.jalo;

import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import de.kpfamily.fulfilmentprocess.constants.KpfamilyFulfilmentProcessConstants;

import org.apache.log4j.Logger;

@SuppressWarnings("PMD")
public class KpfamilyFulfilmentProcessManager extends GeneratedKpfamilyFulfilmentProcessManager
{
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger( KpfamilyFulfilmentProcessManager.class.getName() );
	
	public static final KpfamilyFulfilmentProcessManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (KpfamilyFulfilmentProcessManager) em.getExtension(KpfamilyFulfilmentProcessConstants.EXTENSIONNAME);
	}
	
}
