package com.pixi.api.importers.impl;

import java.security.InvalidParameterException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.apache.log4j.Logger;

import com.pixi.api.core.PixiFunction;
import com.pixi.api.core.PixiFunctionParameter;
import com.pixi.api.core.PixiParameterType;
import com.pixi.api.exceptions.SOAPResponseErrorException;
import com.pixi.api.importers.AbstractPixiApiImporter;
import com.pixi.api.result.IntegerResult;

/**
 * Instantiate this class you should not! See
 * {@link com.pixi.api.PixiApiFactory} you must. <br/>
 * <br/>
 * 
 * @author jfelipe
 */
public class OrderNumberByOrderNumberExternalPixiApiImporter extends
		AbstractPixiApiImporter {

	private static final Logger LOG = Logger
			.getLogger(OrderNumberByOrderNumberExternalPixiApiImporter.class);

	/**
     * 
     */
	public OrderNumberByOrderNumberExternalPixiApiImporter() {
		super();
		validParameters.add(PixiParameterType.ORDER_NUMBER_EXTERNAL);
	}

	/**
	 * Imports the orders from Pixi. <br/>
	 * 
	 * @param functionParameters
	 *            Parameters to be passed to Pixi API. Valid parameters are: <br/>
	 *            - {@link PixiParameterType#ORDER_NUMBER_EXTERNAL}<br/>
	 * @return A List of {@link Integer} containing the results from Pixi API
	 * @throws SOAPException
	 *             in case errors occurred during the Pixi API request
	 * @throws SOAPResponseErrorException
	 *             in case errors ocurred during the Pixi API response
	 *
	 * @author jfelipe
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	@Override
	public List<IntegerResult> importData(
			List<PixiFunctionParameter> functionParameters)
			throws SOAPResponseErrorException, SOAPException,
			KeyManagementException, NoSuchAlgorithmException {
		if (functionParameters == null || functionParameters.isEmpty()) {
			throw new InvalidParameterException(
					"The PixiAPI function parameter should not be null.");
		}
		return importOrders(functionParameters);
	}

	/**
	 * Imports the orders from Pixi. <br/>
	 * 
	 * @param functionParameters
	 *            Parameters to be passed to Pixi API. Valid parameters are: <br/>
	 *            - {@link PixiParameterType#ORDER_NUMBER_EXTERNAL}<br/>
	 * @return A List of {@link Integer} containing the results from Pixi API
	 * @throws SOAPException
	 *             in case errors occurred during the Pixi API request
	 * @throws SOAPResponseErrorException
	 *             in case errors ocurred during the Pixi API response
	 *
	 * @author jfelipe
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	private List<IntegerResult> importOrders(
			List<PixiFunctionParameter> functionParameters)
			throws SOAPResponseErrorException, SOAPException,
			KeyManagementException, NoSuchAlgorithmException {
		List<IntegerResult> results = new ArrayList<IntegerResult>();
		SOAPMessage request = getPixiSoapApi().buildMessage(
				PixiFunction.GET_ORDER_NUMBER_BY_ORDER_NUMBER_EXTERNAL,
				functionParameters);
		SOAPMessage response = getPixiSoapApi().sendPixiWebServiceRequest(
				request);
		if (response != null) {
			String responseValue = response.getSOAPBody().getFirstChild()
					.getTextContent();
			IntegerResult result = new IntegerResult(
					Integer.parseInt(responseValue));
			results.add(result);
		}
		return results;
	}
}
