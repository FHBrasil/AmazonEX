/**
 * 
 */
package com.pixi.api.importers.impl;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.w3c.dom.Node;

import com.pixi.api.PixiSOAPAPI;
import com.pixi.api.constants.PixiapiConstants;
import com.pixi.api.core.PixiParameter;
import com.pixi.api.importers.PixiApiImporter;

/**
 * @author jfelipe
 *
 */
public class SOrderTagPixiApiImporter implements PixiApiImporter {

    private static final Logger LOG = Logger.getLogger(SOrderTagPixiApiImporter.class.getName());
    private PixiSOAPAPI defaultPixiSoapApi;


    /**
     * @return the defaultPixiSoapApi
     */
    public PixiSOAPAPI getDefaultPixiSoapApi() {
        return defaultPixiSoapApi;
    }


    /**
     * @param defaultPixiSoapApi
     */
    @Required
    public void setDefaultPixiSoapApi(PixiSOAPAPI defaultPixiSoapApi) {
        this.defaultPixiSoapApi = defaultPixiSoapApi;
    }


    /**
     * 
     */
    @Override
    public int importInteger(String value) {
        // Not used
        return 0;
    }


    /**
     * 
     * 
     */
    @Override
    public Node importXml(String... values) throws SOAPException, MalformedURLException {
        return importSOrdersTag();
    }


    /**
     * Import the file that has all <SOrderKey> from Pixi API.
     * 
     * @return
     *         A XML Node containing the <SOrderKey> tags.
     * @throws SOAPException
     * @throws MalformedURLException
     *
     * @author jfelipe
     */
    private Node importSOrdersTag() throws SOAPException, MalformedURLException {
        SOAPMessage message;
        SOAPMessage response;
        List<SimpleEntry<String, String>> values = new ArrayList<SimpleEntry<String, String>>();
        values.add(new SimpleEntry<String, String>(PixiParameter.LOCATION_ID.getValue(), "001"));
        values.add(new SimpleEntry<String, String>(PixiParameter.SHOW_ORDER.getValue(), "1"));
        message = getDefaultPixiSoapApi().buildMessage(
                PixiapiConstants.PIXIAPI_FUNCTION_GET_ORDERS, values);
        response = getDefaultPixiSoapApi().sendPixiWebServiceRequest(message);
        return response.getSOAPBody().getElementsByTagName("SqlRowSet1").item(0);
    }
}
