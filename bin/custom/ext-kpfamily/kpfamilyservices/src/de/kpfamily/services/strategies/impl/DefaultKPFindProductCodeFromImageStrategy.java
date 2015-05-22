package de.kpfamily.services.strategies.impl;

import java.io.File;

import org.apache.commons.lang.StringUtils;

import com.fliegersoftware.media.strategies.FindProductCodeFromImageStrategy;

public class DefaultKPFindProductCodeFromImageStrategy implements
		FindProductCodeFromImageStrategy {
	
	/**
	 * 
	 */
	@Override
	public String findProductCodeFromImage(String fullImagePath) {
		final File temp = new File(fullImagePath);
		final String[] slices = StringUtils.split(temp.getName(), "_");
		// First position must have the productCode
		return slices[0];
	}
}
