package com.pixi.api;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import com.pixi.api.core.PixiFunction;
import com.pixi.api.core.PixiFunctionParameter;

/**
 * @author jfelipe
 *
 */
public interface PixiSOAPAPI {

	/**
	 * Builds the message that will be sent to Pixi API.
	 * 
	 * @param pixiFunction
	 *            The function to be executed in the end-point
	 * @param values
	 *            The values the given function needs.
	 * @return A SOAPMessage object containing the response from end-point.
	 *
	 * @author jfelipe
	 */
	SOAPMessage buildMessage(PixiFunction pixiFunction,
			List<PixiFunctionParameter> values) throws SOAPException,
			NoSuchAlgorithmException, KeyManagementException;

	/**
	 * Calls SOAP Pixi API.
	 * 
	 * @param message
	 *            The SOAPMessage containing the function that will be sent to
	 *            Pixi API.
	 * @return A SOAPMessage object containing the response from end-point.
	 *
	 * @author jfelipe
	 */
	SOAPMessage sendPixiWebServiceRequest(final SOAPMessage message)
			throws SOAPException, NoSuchAlgorithmException,
			KeyManagementException;
}
