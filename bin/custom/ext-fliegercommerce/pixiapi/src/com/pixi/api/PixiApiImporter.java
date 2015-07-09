package com.pixi.api;

import java.security.InvalidParameterException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.List;

import javax.xml.soap.SOAPException;

import com.pixi.api.core.PixiFunctionParameter;
import com.pixi.api.exceptions.SOAPResponseErrorException;
import com.pixi.api.result.PixiApiResult;

/**
 * @author jfelipe
 *
 */
public interface PixiApiImporter {

	/**
	 * 
	 * @param functionParameters
	 *
	 * @author jfelipe
	 */
	Collection<? extends PixiApiResult> importData(
			List<PixiFunctionParameter> functionParameters)
			throws InvalidParameterException, SOAPResponseErrorException,
			SOAPException, NoSuchAlgorithmException, KeyManagementException;
}