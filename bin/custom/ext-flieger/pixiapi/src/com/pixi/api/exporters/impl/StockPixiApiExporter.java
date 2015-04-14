package com.pixi.api.exporters.impl;

import java.io.IOException;
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
 * Instantiate this class you should not! See {@link com.pixi.api.PixiApiFactory} you
 * must. <br/>
 * <br/>
 * 
 * @author jfelipe
 */
public class StockPixiApiExporter extends AbstractPixiApiExporter {

    /**
     * 
     */
    public StockPixiApiExporter() {
        super();
        validParameters.add(PixiParameterType.EAN_UPC);
        validParameters.add(PixiParameterType.BIN_NAME);
        validParameters.add(PixiParameterType.NEW_STOCK_QUANTITY);
    }


    /**
     * Sets the the stock quantity. <br/>
     * 
     * @param functionParameters
     *            Parameters to be passed to Pixi API. Valid parameters are: <br/>
     *            - {@link PixiParameterType#EAN_UPC}<br/>
     *            - {@link PixiParameterType#BIN_NAME}<br/>
     *            - {@link PixiParameterType#NEW_STOCK_QUANTITY}<br/>
     * @throws SOAPException
     *             in case there any errors occurred during the Pixi API call
     *
     * @author jfelipe
     */
    @Override
    public void exportData(List<PixiFunctionParameter> functionParameters)
            throws SOAPResponseErrorException, SOAPException {
        if (functionParameters == null || functionParameters.isEmpty()) {
            throw new InvalidParameterException(
                    "The PixiAPI function parameter should not be null.");
        }
        exportStock(functionParameters);
    }


    /**
     * Sets the the stock quantity. <br/>
     * 
     * @param functionParameters
     *            Parameters to be passed to Pixi API. Valid parameters are: <br/>
     *            - {@link PixiParameterType#EAN_UPC}<br/>
     *            - {@link PixiParameterType#BIN_NAME}<br/>
     *            - {@link PixiParameterType#NEW_STOCK_QUANTITY}<br/>
     * @throws SOAPException
     *             in case there any errors occurred during the Pixi API call
     *
     * @author jfelipe
     */
    private void exportStock(List<PixiFunctionParameter> functionParameters)
            throws SOAPResponseErrorException, SOAPException {
        checkValidParameters(functionParameters);
        SOAPMessage request = getPixiSoapApi().buildMessage(PixiFunction.SET_STOCK,
                functionParameters);
        getPixiSoapApi().sendPixiWebServiceRequest(request);
    }
}
