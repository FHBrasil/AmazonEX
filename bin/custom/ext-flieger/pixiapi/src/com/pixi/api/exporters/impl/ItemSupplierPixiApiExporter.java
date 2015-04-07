package com.pixi.api.exporters.impl;

import java.security.InvalidParameterException;
import java.util.List;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import com.pixi.api.core.PixiFunction;
import com.pixi.api.core.PixiFunctionParameter;
import com.pixi.api.core.PixiParameterType;
import com.pixi.api.exporters.AbstractPixiApiExporter;

/**
 * Instantiate this class you should not! See {@link com.pixi.api.PixiApiFactory} you
 * must. <br/>
 * <br/>
 * 
 * @author jfelipe
 */
public class ItemSupplierPixiApiExporter extends AbstractPixiApiExporter {

    /**
     * 
     */
    public ItemSupplierPixiApiExporter() {
        super();
        validParameters.add(PixiParameterType.ITEM_KEY);
        validParameters.add(PixiParameterType.ITEM_NUMBER_INTERNAL);
        validParameters.add(PixiParameterType.SUPPLIER_NUMBER);
        validParameters.add(PixiParameterType.SUPPLIER_PRICE);
        validParameters.add(PixiParameterType.EAN);
        validParameters.add(PixiParameterType.ITEM_SUPPLIER_NUMBER);
    }


    /**
     * Sets the the item supplier. <br/>
     * 
     * @param functionParameters
     *            Parameters to be passed to Pixi API. Valid parameters are: <br/>
     *            - {@link PixiParameterType#ITEM_KEY}<br/>
     *            - {@link PixiParameterType#ITEM_NUMBER_INTERNAL}<br/>
     *            - {@link PixiParameterType#SUPPLIER_NUMBER}<br/>
     *            - {@link PixiParameterType#SUPPLIER_PRICE}<br/>
     *            - {@link PixiParameterType#EAN}<br/>
     *            - {@link PixiParameterType#ITEM_SUPPLIER_NUMBER}<br/>
     * @throws SOAPException
     *             in case there any errors occurred during the Pixi API call
     *
     * @author jfelipe
     */
    @Override
    public void exportData(List<PixiFunctionParameter> functionParameters) throws SOAPException {
        if (functionParameters == null || functionParameters.isEmpty()) {
            throw new InvalidParameterException(
                    "The PixiAPI function parameter should not be null.");
        }
        exportItemSupplier(functionParameters);
    }


    /**
     * Sets the the item supplier. <br/>
     * 
     * @param functionParameters
     *            Parameters to be passed to Pixi API. Valid parameters are: <br/>
     *            - {@link PixiParameterType#ITEM_KEY}<br/>
     *            - {@link PixiParameterType#ITEM_NUMBER_INTERNAL}<br/>
     *            - {@link PixiParameterType#SUPPLIER_NUMBER}<br/>
     *            - {@link PixiParameterType#SUPPLIER_PRICE}<br/>
     *            - {@link PixiParameterType#EAN}<br/>
     *            - {@link PixiParameterType#ITEM_SUPPLIER_NUMBER}<br/>
     * @throws SOAPException
     *             in case there any errors occurred during the Pixi API call
     *
     * @author jfelipe
     */
    private void exportItemSupplier(List<PixiFunctionParameter> functionParameters)
            throws SOAPException {
        checkValidParameters(functionParameters);
        SOAPMessage request = getPixiSoapApi().buildMessage(PixiFunction.SET_ITEM_SUPPLIER,
                functionParameters);
        getPixiSoapApi().sendPixiWebServiceRequest(request);
    }
}
