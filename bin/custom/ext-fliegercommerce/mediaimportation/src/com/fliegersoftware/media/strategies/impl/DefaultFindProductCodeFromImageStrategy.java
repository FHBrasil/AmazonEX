/**
 *
 */
package com.fliegersoftware.media.strategies.impl;

import java.io.File;

import org.apache.commons.lang.StringUtils;

import com.fliegersoftware.media.strategies.FindProductCodeFromImageStrategy;


/**
 * @author franthescollymaneira
 *
 */
public class DefaultFindProductCodeFromImageStrategy implements FindProductCodeFromImageStrategy
{
	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.fliegersoftware.media.strategies.FindProductCodeFromImageStrategy#findProductCodeFromImage(java.lang.String)
	 */
	@Override
	public String findProductCodeFromImage(final String fullImagePath)
	{
		final File temp = new File(fullImagePath);

		final String[] slices = StringUtils.split(temp.getName(), "-");

		//the first position must have the productCode
		return slices[0];
	}
}