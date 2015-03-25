package com.pixi.api.importers.impl;

import java.net.MalformedURLException;
import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.common.base.Strings;
import com.pixi.api.constants.PixiapiConstants;
import com.pixi.api.core.PixiParameter;
import com.pixi.api.importers.AbstractPixiApiImporter;

/**
 * 
 * @author jfelipe
 */
public class UpdatedInvoicesPixiApiImporter extends AbstractPixiApiImporter {

    private static final Logger LOG = Logger.getLogger(UpdatedInvoicesPixiApiImporter.class
            .getName());


    /**
     * 
     */
    @Override
    public List<SimpleEntry<String, String>> importListKeyValuePair(Object parameter) {
        if (!(parameter instanceof Date)) {
            throw new InvalidParameterException("Parameter should be a Date object.");
        }
        Date sinceDate = (Date) parameter;
        List<SimpleEntry<String, String>> updatedInvoices = importUpdatedInvoices(sinceDate);
        return updatedInvoices;
    }


    /**
     * 
     * @param sinceDate
     * @return
     *
     * @author jfelipe
     */
    public List<SimpleEntry<String, String>> importUpdatedInvoices(Date sinceDate) {
        List<SimpleEntry<String, String>> updatedInvoices = new ArrayList<SimpleEntry<String, String>>();
        SOAPMessage request = null;
        SOAPMessage response = null;
        List<SimpleEntry<String, String>> functionParameters = new ArrayList<SimpleEntry<String, String>>();
        String dateSinceFormatted = new SimpleDateFormat("yyyy-MM-dd").format(sinceDate);
        functionParameters.add(new SimpleEntry<String, String>(PixiParameter.SINCE.getValue(),
                dateSinceFormatted));
        try {
            request = getDefaultPixiSoapApi().buildMessage(
                    PixiapiConstants.PIXIAPI_FUNCTION_GET_UPDATED_INVOICES, functionParameters);
            response = getDefaultPixiSoapApi().sendPixiWebServiceRequest(request);
            //
            SOAPBody responseBody = response.getSOAPBody();
            NodeList responseXml = responseBody
                    .getElementsByTagName(PixiapiConstants.PIXIAPI_RESPONSE_TAG_SQLROWSET1);
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
                        if (PixiapiConstants.PIXIAPI_RESPONSE_TAG_INVOICE_KEY
                                .equalsIgnoreCase(currentNodeName)) {
                            invoiceKey = updatedInvoice.getTextContent().trim();
                        } else if (PixiapiConstants.PIXIAPI_RESPONSE_TAG_INVOICE_NUMBER
                                .equalsIgnoreCase(currentNodeName)) {
                            invoiceNumber = updatedInvoice.getTextContent().trim();
                        }
                        if (!Strings.isNullOrEmpty(invoiceKey)
                                && !Strings.isNullOrEmpty(invoiceNumber)) {
                            updatedInvoices.add(new SimpleEntry<String, String>(invoiceKey,
                                    invoiceNumber));
                        }
                    }
                }
            }
        } catch (SOAPException | MalformedURLException e) {
            LOG.error("Error requesting SOAP Pixi API. Function: "
                    + PixiapiConstants.PIXIAPI_FUNCTION_GET_UPDATED_INVOICES, e);
        }
        return updatedInvoices;
    }
}
