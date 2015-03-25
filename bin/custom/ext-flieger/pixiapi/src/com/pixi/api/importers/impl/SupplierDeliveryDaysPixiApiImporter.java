package com.pixi.api.importers.impl;

import java.net.MalformedURLException;
import java.security.InvalidParameterException;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.pixi.api.PixiSOAPAPI;
import com.pixi.api.constants.PixiapiConstants;
import com.pixi.api.core.PixiParameter;
import com.pixi.api.importers.PixiApiImporter;

/**
 * @author jfelipe
 *
 */
public class SupplierDeliveryDaysPixiApiImporter implements PixiApiImporter {

    private static final Logger LOG = Logger.getLogger(SupplierDeliveryDaysPixiApiImporter.class
            .getName());
    private PixiSOAPAPI defaultPixiSoapApi;


    /**
     * Not used in this case
     * 
     */
    @Override
    public Node importXml(String... supplierNumbers) throws SOAPException, MalformedURLException {
        // Not used
        return null;
    }


    /**
     * 
     */
    @Override
    public int importInteger(String value) {
        if (value == null) {
            throw new InvalidParameterException(
                    "The PixiAPI function parameter should not be null.");
        }
        return importDefaultDeliveryDays(value);
    }


    /**
     * 
     * Reponse XML:
     * 
     * {@code
     * <SqlRowSet1>
     *   <row>
     *     <SupplNr>SUPPLIER_CODE</SupplNr>
     *     <SupplName>SUPPLIER_NAME</SupplName>
     *     <DefaultDeliveryDays>DEFAULT_DELIVERY_DAYS</DefaultDeliveryDays>
     *     <SupplFaxNR>SUPPLIER_FAX_NUMBER</SupplFaxNR>
     *     <CustNR>SUPPLIER_DUNNO</CustNR>
     *     <eMail>SUPPLIER_EMAIL</eMail>
     *     <SupAddress>SUPPLIER_ADDRESS</SupAddress>
     *     <SupZip>SUPPLIER_POSTAL_CODE</SupZip>
     *     <SupPhone1>SUPPLIER_PHONE_NUMBER</SupPhone1>
     *     <CanDoDirectFulfillment>SUPPLIER_DUNNO_2</CanDoDirectFulfillment>
     *   </row>
     * </SqlRowSet1>
     * }
     * 
     * @param supplierNumber
     * @return
     *
     * @author jfelipe
     */
    private int importDefaultDeliveryDays(String supplierNumber) {
        List<SimpleEntry<String, String>> functionParameters = new ArrayList<SimpleEntry<String, String>>();
        SimpleEntry parameter = new SimpleEntry(PixiParameter.SUPPLIER_NUMBER.getValue(),
                supplierNumber);
        functionParameters.add(parameter);
        SOAPMessage request = null;
        SOAPMessage response = null;
        Node responseXml = null;
        try {
            request = getDefaultPixiSoapApi().buildMessage(
                    PixiapiConstants.PIXIAPI_FUNCTION_GET_SUPPLIERS, functionParameters);
            response = getDefaultPixiSoapApi().sendPixiWebServiceRequest(request);
            responseXml = response.getSOAPBody()
                    .getElementsByTagName(PixiapiConstants.PIXIAPI_RESPONSE_TAG_SQLROWSET1).item(0);
        } catch (MalformedURLException me) {
            LOG.error("Mal formed URL calling Pixi API function: "
                    + PixiapiConstants.PIXIAPI_FUNCTION_GET_SUPPLIERS, me);
        } catch (SOAPException se) {
            LOG.error("Error requesting Pixi API function: "
                    + PixiapiConstants.PIXIAPI_FUNCTION_GET_SUPPLIERS, se);
        }
        if (responseXml != null) {
            NodeList rowTags = responseXml.getChildNodes();
            for (int i = 0; i < rowTags.getLength(); i++) {
                NodeList row = rowTags.item(i).getChildNodes();
                for (int j = 0; j < row.getLength(); j++) {
                    Node deliveryDaysTag = row.item(j);
                    String deliveryDaysTagName = deliveryDaysTag.getNodeName();
                    if (PixiapiConstants.PIXIAPI_RESPONSE_TAG_DEFAULT_DELIVERY_DAYS
                            .equalsIgnoreCase(deliveryDaysTagName)) {
                        return deliveryDaysTag.getTextContent() != null ? Integer
                                .parseInt(deliveryDaysTag.getTextContent()) : -1;
                    }
                }
            }
        }
        return -1;
    }


    /**
     * @return the defaultPixiSoapApi
     */
    public PixiSOAPAPI getDefaultPixiSoapApi() {
        return defaultPixiSoapApi;
    }


    /**
     * @param defaultPixiSoapApi
     *            the defaultPixiSoapApi to set
     */
    public void setDefaultPixiSoapApi(PixiSOAPAPI defaultPixiSoapApi) {
        this.defaultPixiSoapApi = defaultPixiSoapApi;
    }
}
