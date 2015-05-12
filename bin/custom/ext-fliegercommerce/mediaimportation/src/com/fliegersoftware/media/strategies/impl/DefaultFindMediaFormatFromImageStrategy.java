/**
 *
 */
package com.fliegersoftware.media.strategies.impl;

import java.io.File;

import org.apache.commons.lang.StringUtils;

import com.fliegersoftware.media.strategies.FindMediaFormatFromImageStrategy;


/**
 * @author franthescollymaneira
 *
 */
public class DefaultFindMediaFormatFromImageStrategy implements FindMediaFormatFromImageStrategy
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.fliegersoftware.media.strategies.FindMediaFormatFromImageStrategy#findMediaFormatFromImage(java.lang.String)
	 */
	@Override
	public String findMediaFormatFromImage(final String fullImagePath)
	{
		final File temp = new File(fullImagePath);

		final String[] slices = StringUtils.split(temp.getName(), "-");

		//the second position must have the image format
		return slices[1];
	}
}