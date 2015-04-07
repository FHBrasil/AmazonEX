package com.pixi.api.importers.impl;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.common.base.Strings;
import com.pixi.api.core.PixiApiResponseTags;
import com.pixi.api.core.PixiFunction;
import com.pixi.api.core.PixiFunctionParameter;
import com.pixi.api.core.PixiParameterType;
import com.pixi.api.exceptions.SOAPResponseErrorException;
import com.pixi.api.importers.AbstractPixiApiImporter;
import com.pixi.api.result.KeyValuePairResult;

/**
 * Instantiate this class you should not! See {@link com.pixi.api.PixiApiFactory} you
 * must. <br/>
 * <br/>
 * 
 * @author jfelipe
 */
public class UpdatedInvoicesPixiApiImporter extends AbstractPixiApiImporter {

    private static final Logger LOG = Logger.getLogger(UpdatedInvoicesPixiApiImporter.class
            .getName());
    private static final String INVOICE_KEY = "InvoiceKey";
    private static final String INVOICE_NUMBER = "InvoiceNr";


    /**
     * 
     */
    public UpdatedInvoicesPixiApiImporter() {
        super();
        validParameters.add(PixiParameterType.SINCE_DATE);
    }


    /**
     * 
     * @param functionParameters
     *            Parameters to be passed to Pixi API. Valid parameters are: <br/>
     *            - {@link PixiParameterType#SINCE_DATE}<br/>
     * @return
     * 
     * @throws SOAPException
     *             in case errors occurred during the Pixi API request
     * @throws SOAPResponseErrorException
     *             in case errors ocurred during the Pixi API response
     *
     * @author jfelipe
     */
    @Override
    public List<KeyValuePairResult> importData(List<PixiFunctionParameter> functionParameters)
            throws SOAPResponseErrorException, SOAPException {
        if (functionParameters == null || functionParameters.isEmpty()) {
            throw new InvalidParameterException(
                    "The PixiAPI function parameter should not be null.");
        }
        return importUpdatedInvoices(functionParameters);
    }


    /**
     * 
     * @param functionParameters
     *            Parameters to be passed to Pixi API. Valid parameters are: <br/>
     *            - {@link PixiParameterType#SINCE_DATE}<br/>
     * 
     * @return
     * 
     * @throws SOAPException
     *             in case errors occurred during the Pixi API request
     * @throws SOAPResponseErrorException
     *             in case errors ocurred during the Pixi API response
     *
     * @author jfelipe
     */
    private List<KeyValuePairResult> importUpdatedInvoices(
            List<PixiFunctionParameter> functionParameters) throws SOAPResponseErrorException,
            SOAPException {
        List<KeyValuePairResult> updatedInvoices = new ArrayList<KeyValuePairResult>();
        checkValidParameters(functionParameters);
        SOAPMessage request = getPixiSoapApi().buildMessage(
                PixiFunction.GET_UPDATED_INVOICES, functionParameters);
        SOAPMessage response = getPixiSoapApi().sendPixiWebServiceRequest(request);
        SOAPBody responseBody = response.getSOAPBody();
        NodeList responseXml = responseBody.getElementsByTagName(PixiApiResponseTags.SQLROWSET1
                .getValue());
        Node xmlUpdatedInvoices = responseXml.item(0);
        if (xmlUpdatedInvoices != null) {
            NodeList rowTags = xmlUpdatedInvoices.getChildNodes();
            for (int i = 0; i < rowTags.getLength(); i++) {
                NodeList row = rowTags.item(i).getChildNodes();
                for (int j = 0; j < row.getLength(); j++) {
                    Node updatedInvoice = row.item(j);
                    String invoiceKey = null;
                    String invoiceNumber = null;
                    String currentNodeName = updatedInvoice.getNodeName();
                    if (INVOICE_KEY.equalsIgnoreCase(currentNodeName)) {
                        invoiceKey = updatedInvoice.getTextContent().trim();
                    } else if (INVOICE_NUMBER.equalsIgnoreCase(currentNodeName)) {
                        invoiceNumber = updatedInvoice.getTextContent().trim();
                    }
                    if (!Strings.isNullOrEmpty(invoiceKey) && !Strings.isNullOrEmpty(invoiceNumber)) {
                        updatedInvoices.add(new KeyValuePairResult(invoiceKey, invoiceNumber));
                    }
                }
            }
        }
        return updatedInvoices;
    }
}
