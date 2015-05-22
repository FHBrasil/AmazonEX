package de.kpfamily.services.strategies.impl;

import com.fliegersoftware.media.strategies.FindMediaFormatFromImageStrategy;

import de.hybris.platform.util.Config;

public class DefaultKPFindMediaFormatFromImage implements
		FindMediaFormatFromImageStrategy {
	
	/**
	 * 
	 */
	@Override
	public String findMediaFormatFromImage(String fullImagePath) {
		String format = Config.getParameter("kpfamilycore.media.format.default");
		return format;
	}
}
