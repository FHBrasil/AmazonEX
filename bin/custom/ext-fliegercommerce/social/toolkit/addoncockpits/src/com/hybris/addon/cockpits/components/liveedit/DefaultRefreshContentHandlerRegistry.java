package com.hybris.addon.cockpits.components.liveedit;

import de.hybris.platform.cmscockpit.components.liveedit.LiveEditView;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;


/**
 * author: dariusz.malachowski
 */
public class DefaultRefreshContentHandlerRegistry implements RefreshContentHandlerRegistry<LiveEditView>
{

	private List<RefreshContentHandler<LiveEditView>> handlers;

	@Override
	public List<RefreshContentHandler<LiveEditView>> getRefreshContentHandlers()
	{
		return handlers;
	}

	@Required
	public void setHandlers(final List<RefreshContentHandler<LiveEditView>> handlers)
	{
		this.handlers = handlers;
	}
}
