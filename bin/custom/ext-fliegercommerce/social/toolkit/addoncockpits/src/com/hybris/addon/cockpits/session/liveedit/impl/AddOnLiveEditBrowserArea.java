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
package com.hybris.addon.cockpits.session.liveedit.impl;


import de.hybris.platform.cockpit.events.CockpitEvent;

import java.util.List;

import br.hering.cockpits.cmscockpit.session.impl.DefaultLiveEditBrowserArea;
import br.hering.cockpits.cmscockpit.session.impl.DefaultLiveEditBrowserModel;

import com.hybris.addon.cockpits.cms.events.AddOnLiveEditBrowserCockpitEventHandler;


/**
 * Represents a browser area of <b>Live Edit Perspective</b>
 */
public class AddOnLiveEditBrowserArea<B extends DefaultLiveEditBrowserModel> extends DefaultLiveEditBrowserArea
{
	private List<AddOnLiveEditBrowserCockpitEventHandler> liveEditBrowserCockpitEventHandlers;


	@Override
	protected DefaultLiveEditBrowserModel newDefaultLiveEditBrowserModel()
	{
		return new AddOnLiveEditBrowserModel();
	}

	@Override
	public void onCockpitEvent(final CockpitEvent event)
	{
		super.onCockpitEvent(event);
		if (getLiveEditBrowserCockpitEventHandlers() != null)
		{
			for (final AddOnLiveEditBrowserCockpitEventHandler eventHandler : getLiveEditBrowserCockpitEventHandlers())
			{
				if (eventHandler.canHandleEvent(event, this))
				{
					eventHandler.handleCockpitEvent(event, this);
				}
			}
		}
	}


	public List<AddOnLiveEditBrowserCockpitEventHandler> getLiveEditBrowserCockpitEventHandlers()
	{
		return liveEditBrowserCockpitEventHandlers;
	}


	public void setLiveEditBrowserCockpitEventHandlers(
			final List<AddOnLiveEditBrowserCockpitEventHandler> liveEditBrowserCockpitEventHandlers)
	{
		this.liveEditBrowserCockpitEventHandlers = liveEditBrowserCockpitEventHandlers;
	}


}
