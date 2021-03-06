package de.fliegersoftware.amazon.hmc;

import de.hybris.platform.hmc.attribute.AutocompleteReferenceEditorChip;
import de.hybris.platform.hmc.webchips.Chip;
import de.hybris.platform.hmc.webchips.DisplayState;
import de.hybris.platform.jalo.type.ComposedType;

import de.fliegersoftware.amazon.core.jalo.media.AmazonLog;


public class AmazonDownloadMediaEditorChip extends AutocompleteReferenceEditorChip
{
	private static final String JSP_URI = "/ext/amazonhmcaddon/amazonDownloadMediaEditorChip.jsp";
	private static final String TYPE = "amazondownloadmediaeditor";
	private boolean refreshWithoutSubmitAfterSelect = false;

	/**
	 * 
	 */
	public AmazonDownloadMediaEditorChip(final DisplayState displayState, final Chip parent)
	{
		super(displayState, parent);
	}

	public AmazonDownloadMediaEditorChip(final DisplayState displayState, final Chip parent, final ComposedType type)
	{
		super(displayState, parent, type);
	}

	public static String getEditorType()
	{
		return TYPE;
	}

	@Override
	public String getJSPURI()
	{
		return JSP_URI;
	}

	public String getCode()
	{
		return ((AmazonLog) getValue()).getCode();
	}

	public String getURL()
	{
		return ((AmazonLog) getValue()).getURL();
	}

	public void setRefreshWithoutSubmitAfterSelect(final boolean refreshWithoutSubmitAfterSelect)
	{
		this.refreshWithoutSubmitAfterSelect = refreshWithoutSubmitAfterSelect;
	}

	public boolean isRefreshWithoutSubmitAfterSelect()
	{
		return this.refreshWithoutSubmitAfterSelect;
	}
}