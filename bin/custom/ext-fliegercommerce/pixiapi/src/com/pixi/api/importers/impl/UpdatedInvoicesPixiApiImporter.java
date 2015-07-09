package com.pixi.api.importers.impl;

import java.security.InvalidParameterException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
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
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
    @Override
    public List<KeyValuePairResult> importData(List<PixiFunctionParameter> functionParameters)
            throws SOAPResponseErrorException, SOAPException, KeyManagementException, NoSuchAlgorithmException {
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
     * @throws NoSuchAlgorithmException 
     * @throws KeyManagementException 
     */
    private List<KeyValuePairResult> importUpdatedInvoices(
            List<PixiFunctionParameter> functionParameters) throws SOAPResponseErrorException,
            SOAPException, KeyManagementException, NoSuchAlgorithmException {
        List<KeyValuePairResult> updatedInvoices = new ArrayList<KeyValuePairResult>();
        checkValidParameters(functionParameters);
        SOAPMessage request = getPixiSoapApi().buildMessage(
                PixiFunction.GET_UPDATED_INVOICES, functionParameters);
        SOAPMessage response = getPixiSoapApi().sendPixiWebServiceRequest(request);
        SOAPBody responseBody = response.getSOAPBody();
        NodeList responseXml = responseBody.getElementsByTagName(PixiApiResponseTags.SQLROWSET1
                .getValue());
        if (responseXml != null) {
            NodeList rowTags = responseXml.item(0).getChildNodes();
            for (int i = 0; i < rowTags.getLength(); i++) {
                NodeList row = rowTags.item(i).getChildNodes();
                String invoiceKey = null;
                String invoiceNumber = null;
                for (int j = 0; j < row.getLength(); j++) {
                    Node currentTag = row.item(j);
                    if (INVOICE_KEY.equalsIgnoreCase(currentTag.getNodeName())) {
                        invoiceKey = currentTag.getTextContent().trim();
                    } else if (INVOICE_NUMBER.equalsIgnoreCase(currentTag.getNodeName())) {
                        invoiceNumber = currentTag.getTextContent().trim();
                    }
                }
                if (!Strings.isNullOrEmpty(invoiceKey) && !Strings.isNullOrEmpty(invoiceNumber)) {
                    updatedInvoices.add(new KeyValuePairResult(invoiceKey, invoiceNumber));
                }
            }
        }
        return updatedInvoices;
    }
}
