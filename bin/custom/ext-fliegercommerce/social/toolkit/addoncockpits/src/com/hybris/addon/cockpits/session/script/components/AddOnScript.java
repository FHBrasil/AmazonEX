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
package com.hybris.addon.cockpits.session.script.components;

import org.zkoss.lang.Objects;
import org.zkoss.spring.SpringUtil;
import org.zkoss.zul.Script;

import com.hybris.addon.cockpits.session.script.AddOnScriptGroup;
import com.hybris.addon.cockpits.session.script.config.AddOnScriptFragment;


/**
 * Extends the script component sorting the ability to include dynamic script content linked to a logical group.
 * 
 * @author rmcotton
 * 
 */
public class AddOnScript extends Script
{
	protected String _group;

	public AddOnScript()
	{
	}

	public String getGroup()
	{
		return _group;
	}


	public void setGroup(final String group)
	{
		if (group != null && group.length() == 0)
		{
			throw new IllegalArgumentException("non-empty is required");
		}


		if (!Objects.equals(_group, group))
		{
			_group = group;

			final AddOnScriptGroup type = AddOnScriptGroup.valueOf(group);

			if (type == null)
			{
				throw new IllegalArgumentException(group + " is not a valid AddOnScriptGroup group");
			}

			setContent(getScriptWithGroup(type));
		}

	}

	protected String getScriptWithGroup(final AddOnScriptGroup group)
	{
		final StringBuilder script = new StringBuilder();
		for (final AddOnScriptFragment fragment : SpringUtil.getApplicationContext().getBeansOfType(AddOnScriptFragment.class)
				.values())
		{
			if (fragment.getGroup().equals(group))
			{
				script.append(fragment.getScript());
				script.append("\n");
			}
		}
		return script.toString();

	}
}
