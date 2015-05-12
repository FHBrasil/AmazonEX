/**
 *
 */
package com.fliegersoftware.media.impex.translators;

import de.hybris.platform.impex.jalo.header.AbstractImpExCSVCellDecorator;

import java.util.Map;

import org.apache.log4j.Logger;


/**
 * @author franthescollymaneira
 *
 */
public class MediaContainerQualifierDecorator extends AbstractImpExCSVCellDecorator
{
	private static final Logger LOG = Logger.getLogger(MediaContainerQualifierDecorator.class);

	private static final String SPLIT_SIGN = ",";

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.util.CSVCellDecorator#decorate(int, java.util.Map)
	 */
	@Override
	public String decorate(final int position, final Map<Integer, String> srcLine)
	{
		final String medias = srcLine.get(Integer.valueOf(position));
		final String containerId = medias.split(SPLIT_SIGN)[0];

		LOG.debug(String.format("medias: %s resulted in container id: %s ", medias, containerId));

		return containerId;
	}
}