/**
 * 
 */
package com.pixi.api.importers.impl;

import java.net.MalformedURLException;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;

import com.pixi.api.PixiSOAPAPI;
import com.pixi.api.constants.PixiapiConstants;
import com.pixi.api.core.PixiParameter;
import com.pixi.api.importers.PixiApiImporter;

/**
 * @author jfelipe
 *
 */
public class SOrderLineTagPixiApiImporter implements PixiApiImporter {

    private static final Logger LOG = Logger
            .getLogger(SOrderLineTagPixiApiImporter.class.getName());
    private PixiSOAPAPI defaultPixiSoapApi;


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


    /**
     * 
     */
    @Override
    public Node importFromPixiApi(String... values) throws SOAPException, MalformedURLException {
        return importSOrderLineTags(values);
    }


    /**
     * Retrieves a XML Node from Pixi API web service containing a list of <SOrderLine>
     * tags accordingly to the given list of SOrderKey
     * 
     * @param values
     *            The list of SOrderKeys to retrieve its respective SOrderLine
     * @return
     *         A XML Node containg the list of SOrderLine accordingly to the given
     *         SOrderKey list
     * @throws SOAPException
     * @throws MalformedURLException
     *
     * @author jfelipe
     */
    private Node importSOrderLineTags(String... values) throws SOAPException, MalformedURLException {
        SOAPMessage message;
        SOAPMessage response;
        String sOrderReference = values != null && values.length > 0 ? values[0] : "";
        List<SimpleEntry<String, String>> functionParameters = new ArrayList<SimpleEntry<String, String>>();
        functionParameters.add(new SimpleEntry<String, String>(
                PixiParameter.LOCATION_ID.getValue(), "001"));
        functionParameters.add(new SimpleEntry<String, String>(PixiParameter.SHOW_ORDER.getValue(),
                "1"));
        functionParameters.add(new SimpleEntry<String, String>(PixiParameter.OPEN_ORDER_LINES
                .getValue(), null));
        functionParameters.add(new SimpleEntry<String, String>(PixiParameter.S_ORDER_REFERENCE
                .getValue(), sOrderReference));
        message = getDefaultPixiSoapApi().buildMessage(
                PixiapiConstants.PIXIAPI_FUNCTION_GET_ORDER_LINES, functionParameters);
        response = getDefaultPixiSoapApi().sendPixiWebServiceRequest(message);
        return response.getSOAPBody().getElementsByTagName("SqlRowSet1").item(0);
    }
}
