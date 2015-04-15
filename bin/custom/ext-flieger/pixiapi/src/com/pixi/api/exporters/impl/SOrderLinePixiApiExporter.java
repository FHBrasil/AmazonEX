package com.pixi.api.exporters.impl;

import java.security.InvalidParameterException;
import java.util.List;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import com.pixi.api.core.PixiFunction;
import com.pixi.api.core.PixiFunctionParameter;
import com.pixi.api.core.PixiParameterType;
import com.pixi.api.exceptions.SOAPResponseErrorException;
import com.pixi.api.exporters.AbstractPixiApiExporter;

/**
 * @author jfelipe
 *
 */
public class SOrderLinePixiApiExporter extends AbstractPixiApiExporter {

    /**
     * 
     */
    public SOrderLinePixiApiExporter() {
        super();
        validParameters.add(PixiParameterType.S_ORDER_LINE_KEY);
        validParameters.add(PixiParameterType.USERNAME);
        validParameters.add(PixiParameterType.NOTE);
        validParameters.add(PixiParameterType.QUANTITY_NOT_DELIVERED);
        validParameters.add(PixiParameterType.ESTIMATED_DELIVERY);
    }


    /**
     * Sets the the item supplier. <br/>
     * 
     * @param functionParameters
     *            Parameters to be passed to Pixi API. Valid parameters are: <br/>
     *            - {@link PixiParameterType#S_ORDER_LINE_KEY}<br/>
     *            - {@link PixiParameterType#USERNAME}<br/>
     *            - {@link PixiParameterType#NOTE}<br/>
     *            - {@link PixiParameterType#QUANTITY_NOT_DELIVERED}<br/>
     *            - {@link PixiParameterType#ESTIMATED_DELIVERY}<br/>
     * @throws SOAPException
     *             in case any errors occurred during the Pixi API call
     * @throws SOAPResponseErrorException
     *             in case any errors ocurred during the Pixi API response
     * @author jfelipe
     */
    @Override
    public void exportData(List<PixiFunctionParameter> functionParameters)
            throws SOAPResponseErrorException, SOAPException {
        if (functionParameters == null || functionParameters.isEmpty()) {
            throw new InvalidParameterException(
                    "The PixiAPI function parameter should not be null.");
        }
        exportSOrderLines(functionParameters);
    }


    /**
     * Sets the the item supplier. <br/>
     * 
     * @param functionParameters
     *            Parameters to be passed to Pixi API. Valid parameters are: <br/>
     *            - {@link PixiParameterType#S_ORDER_LINE_KEY}<br/>
     *            - {@link PixiParameterType#USERNAME}<br/>
     *            - {@link PixiParameterType#NOTE}<br/>
     *            - {@link PixiParameterType#QUANTITY_NOT_DELIVERED}<br/>
     *            - {@link PixiParameterType#ESTIMATED_DELIVERY}<br/>
     * @throws SOAPException
     *             in case any errors occurred during the Pixi API call
     * @throws SOAPResponseErrorException
     *             in case any errors ocurred during the Pixi API response
     * @author jfelipe
     */
    private void exportSOrderLines(List<PixiFunctionParameter> functionParameters)
            throws SOAPResponseErrorException, SOAPException {
        // checkValidParameters(functionParameters);
        SOAPMessage request = getPixiSoapApi().buildMessage(PixiFunction.UPDATE_S_ORDER_LINE,
                functionParameters);
        getPixiSoapApi().sendPixiWebServiceRequest(request);
    }
}
