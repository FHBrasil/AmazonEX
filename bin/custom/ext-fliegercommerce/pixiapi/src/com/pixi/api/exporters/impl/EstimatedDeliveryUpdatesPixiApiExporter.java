package com.pixi.api.exporters.impl;

import java.security.InvalidParameterException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.apache.log4j.Logger;

import com.pixi.api.core.PixiFunction;
import com.pixi.api.core.PixiFunctionParameter;
import com.pixi.api.core.PixiParameterType;
import com.pixi.api.exceptions.SOAPResponseErrorException;
import com.pixi.api.exporters.AbstractPixiApiExporter;

/**
 * Instantiate this class you should not! See
 * {@link com.pixi.api.PixiApiFactory} you must. <br/>
 * <br/>
 * 
 * @deprecated Use this exporter you must not! This export is not used in
 *             version 4.
 * 
 * @author jfelipe
 */
@Deprecated
public class EstimatedDeliveryUpdatesPixiApiExporter extends
		AbstractPixiApiExporter {

	private static final Logger LOG = Logger
			.getLogger(EstimatedDeliveryUpdatesPixiApiExporterTest.class);

	/**
     * 
     */
	public EstimatedDeliveryUpdatesPixiApiExporter() {
		super();
		validParameters.add(PixiParameterType.ORDER_NUMBER);
		validParameters.add(PixiParameterType.SHOP_ORDER_NUMBER);
		validParameters.add(PixiParameterType.ITEM_REF);
	}

	/**
	 * Sets the estimated delivery days in the given order. <br/>
	 * 
	 * @param functionParameters
	 *            Parameters to be passed to Pixi API. Valid parameters are: <br/>
	 *            - {@link PixiParameterType#ORDER_NUMBER}<br/>
	 *            - {@link PixiParameterType#SHOP_ORDER_NUMBER}<br/>
	 *            - {@link PixiParameterType#ITEM_REF}<br/>
	 * @throws SOAPException
	 *             in case there any errors occurred during the Pixi API call
	 * @throws SOAPResponseErrorException
	 *             in case any errors ocurred during the Pixi API response
	 *
	 * @author jfelipe
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	@Override
	@Deprecated
	public void exportData(List<PixiFunctionParameter> functionParameters)
			throws SOAPResponseErrorException, SOAPException,
			KeyManagementException, NoSuchAlgorithmException {
		if (functionParameters == null || functionParameters.isEmpty()) {
			throw new InvalidParameterException(
					"The PixiAPI function parameter should not be null.");
		}
		exportEstimatedDeliveryUpdates(functionParameters);
	}

	/**
	 * Sets the estimated delivery days in the given order. <br/>
	 * 
	 * @param functionParameters
	 *            Parameters to be passed to Pixi API. Valid parameters are: <br/>
	 *            - {@link PixiParameterType#ORDER_NUMBER}<br/>
	 *            - {@link PixiParameterType#SHOP_ORDER_NUMBER}<br/>
	 *            - {@link PixiParameterType#ITEM_REF}<br/>
	 * @throws SOAPException
	 *             in case there any errors occurred during the Pixi API call
	 * @throws SOAPResponseErrorException
	 *             in case any errors ocurred during the Pixi API response
	 *
	 * @author jfelipe
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	@Deprecated
	private void exportEstimatedDeliveryUpdates(
			List<PixiFunctionParameter> functionParameters)
			throws SOAPResponseErrorException, SOAPException,
			KeyManagementException, NoSuchAlgorithmException {
		checkValidParameters(functionParameters);
		SOAPMessage request = getPixiSoapApi().buildMessage(
				PixiFunction.ESTIMATED_DELIVERY_UPDATES, functionParameters);
		getPixiSoapApi().sendPixiWebServiceRequest(request);
	}
}
