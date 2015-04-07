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
public class InvoicePaidPixiApiExporter extends AbstractPixiApiExporter {

    /**
     * 
     */
    public InvoicePaidPixiApiExporter() {
        super();
        validParameters.add(PixiParameterType.INVOICE_NUMBER);
        validParameters.add(PixiParameterType.PAID);
        validParameters.add(PixiParameterType.PAID_DATE);
        validParameters.add(PixiParameterType.EVENT_ID);
    }


    /**
     * Sets the estimated delivery days in the given order. <br/>
     * 
     * @param functionParameters
     *            Parameters to be passed to Pixi API. Valid parameters are: <br/>
     *            - {@link PixiParameterType#INVOICE_NUMBER}<br/>
     *            - {@link PixiParameterType#PAID}<br/>
     *            - {@link PixiParameterType#PAID_DATE}<br/>
     *            - {@link PixiParameterType#EVENT_ID}<br/>
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
        exportInvoicePaid(functionParameters);
    }


    /**
     * Sets the estimated delivery days in the given order. <br/>
     * 
     * @param functionParameters
     *            Parameters to be passed to Pixi API. Valid parameters are: <br/>
     *            - {@link PixiParameterType#INVOICE_NUMBER}<br/>
     *            - {@link PixiParameterType#PAID}<br/>
     *            - {@link PixiParameterType#PAID_DATE}<br/>
     *            - {@link PixiParameterType#EVENT_ID}<br/>
     * @throws SOAPException
     *             in case there any errors occurred during the Pixi API call
     *
     * @author jfelipe
     */
    private void exportInvoicePaid(List<PixiFunctionParameter> functionParameters)
            throws SOAPException {
        checkValidParameters(functionParameters);
        SOAPMessage request = getPixiSoapApi().buildMessage(PixiFunction.SET_INVOICE_PAID,
                functionParameters);
        getPixiSoapApi().sendPixiWebServiceRequest(request);
    }
}
