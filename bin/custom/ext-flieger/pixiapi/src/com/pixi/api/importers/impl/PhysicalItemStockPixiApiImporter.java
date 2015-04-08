package com.pixi.api.importers.impl;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.apache.log4j.Logger;

import com.google.common.base.Strings;
import com.pixi.api.core.PixiFunction;
import com.pixi.api.core.PixiFunctionParameter;
import com.pixi.api.core.PixiParameterType;
import com.pixi.api.exceptions.SOAPResponseErrorException;
import com.pixi.api.importers.AbstractPixiApiImporter;
import com.pixi.api.result.IntegerResult;

/**
 * Instantiate this class you should not! See {@link com.pixi.api.PixiApiFactory} you
 * must. <br/>
 * <br/>
 * 
 * @author jfelipe
 */
public class PhysicalItemStockPixiApiImporter extends AbstractPixiApiImporter {

    private static final Logger LOG = Logger.getLogger(PhysicalItemStockPixiApiImporter.class);


    /**
     * 
     */
    public PhysicalItemStockPixiApiImporter() {
        super();
        validParameters.add(PixiParameterType.ITEM_KEY);
        validParameters.add(PixiParameterType.EAN);
        validParameters.add(PixiParameterType.ITEM_SUPPLIER_NUMBER);
        validParameters.add(PixiParameterType.ITEM_NUMBER_INTERNAL);
        validParameters.add(PixiParameterType.LOCATION_ID);
    }


    /**
     * Imports the physical stock item from Pixi. <br/>
     * 
     * @param functionParameters
     *            Parameters to be passed to Pixi API. Valid parameters are: <br/>
     *            - {@link PixiParameterType#ITEM_KEY}<br/>
     *            - {@link PixiParameterType#EAN}<br/>
     *            - {@link PixiParameterType#ITEM_SUPPLIER_NUMBER}<br/>
     *            - {@link PixiParameterType#ITEM_NUMBER_INTERNAL}<br/>
     *            - {@link PixiParameterType#LOCATION_ID}<br/>
     * @return
     *         A List of {@link Integer} containing the results from Pixi
     *         API
     * @throws SOAPException
     *             in case errors occurred during the Pixi API request
     * @throws SOAPResponseErrorException
     *             in case errors ocurred during the Pixi API response
     *
     * @author jfelipe
     */
    @Override
    public List<IntegerResult> importData(List<PixiFunctionParameter> functionParameters)
            throws SOAPResponseErrorException, SOAPException {
        if (functionParameters == null || functionParameters.isEmpty()) {
            throw new InvalidParameterException(
                    "The PixiAPI function parameter should not be null.");
        }
        return importPhysicalItemStock(functionParameters);
    }


    /**
     * Imports the physical stock item from Pixi. <br/>
     * 
     * @param functionParameters
     *            Parameters to be passed to Pixi API. Valid parameters are: <br/>
     *            - {@link PixiParameterType#ITEM_KEY}<br/>
     *            - {@link PixiParameterType#EAN}<br/>
     *            - {@link PixiParameterType#ITEM_SUPPLIER_NUMBER}<br/>
     *            - {@link PixiParameterType#ITEM_NUMBER_INTERNAL}<br/>
     *            - {@link PixiParameterType#LOCATION_ID}<br/>
     * @return
     *         A List of {@link Integer} containing the results from Pixi
     *         API
     * @throws SOAPException
     *             in case errors occurred during the Pixi API request
     * @throws SOAPResponseErrorException
     *             in case errors ocurred during the Pixi API response
     *
     * @author jfelipe
     */
    private List<IntegerResult> importPhysicalItemStock(
            List<PixiFunctionParameter> functionParameters) throws SOAPResponseErrorException,
            SOAPException {
        List<IntegerResult> results = new ArrayList<IntegerResult>();
        checkValidParameters(functionParameters);
        SOAPMessage request = getPixiSoapApi().buildMessage(PixiFunction.GET_PHYSICAL_ITEM_STOCK,
                functionParameters);
        SOAPMessage response = getPixiSoapApi().sendPixiWebServiceRequest(request);
        if (response != null) {
            String responseValue = response.getSOAPBody().getFirstChild().getTextContent();
            if (!Strings.isNullOrEmpty(responseValue)) {
                IntegerResult result = new IntegerResult(Integer.parseInt(responseValue));
                results.add(result);
            }
        }
        return results;
    }
}
