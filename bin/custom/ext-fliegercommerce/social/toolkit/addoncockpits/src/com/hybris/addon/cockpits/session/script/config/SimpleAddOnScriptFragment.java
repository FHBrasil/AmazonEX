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
package com.hybris.addon.cockpits.session.script.config;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.addon.cockpits.session.script.AddOnScriptGroup;


/**
 * @author rmcotton
 * 
 */
public class SimpleAddOnScriptFragment implements AddOnScriptFragment
{
	protected AddOnScriptGroup group;
	protected String script;

	@Override
	public String getScript()
	{
		return this.script;
	}

	@Required
	public void setScript(final String script)
	{
		this.script = script;
	}

	@Required
	public void setGroup(final AddOnScriptGroup type)
	{
		this.group = type;
	}

	@Override
	public AddOnScriptGroup getGroup()
	{
		return this.group;
	}
}
