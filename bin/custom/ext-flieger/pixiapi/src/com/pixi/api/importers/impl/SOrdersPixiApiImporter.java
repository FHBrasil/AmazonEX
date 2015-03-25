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

import com.pixi.api.constants.PixiapiConstants;
import com.pixi.api.core.PixiParameter;
import com.pixi.api.importers.AbstractPixiApiImporter;

/**
 * @author jfelipe
 *
 */
public class SOrdersPixiApiImporter extends AbstractPixiApiImporter {

    private static final Logger LOG = Logger.getLogger(SOrdersPixiApiImporter.class.getName());


    /**
     * 
     * 
     */
    @Override
    public Node importXml(String... values) {
        return importSOrdersTag();
    }


    /**
     * Import the file that has all <SOrderKey> from Pixi API.
     * 
     * @return
     *         A XML Node containing the <SOrderKey> tags.
     *
     * @author jfelipe
     */
    private Node importSOrdersTag() {
        SOAPMessage message;
        SOAPMessage response;
        List<SimpleEntry<String, String>> values = new ArrayList<SimpleEntry<String, String>>();
        values.add(new SimpleEntry<String, String>(PixiParameter.LOCATION_ID.getValue(), "001"));
        values.add(new SimpleEntry<String, String>(PixiParameter.SHOW_ORDER.getValue(), "1"));
        try {
            message = getDefaultPixiSoapApi().buildMessage(
                    PixiapiConstants.PIXIAPI_FUNCTION_GET_ORDERS, values);
            response = getDefaultPixiSoapApi().sendPixiWebServiceRequest(message);
            return response.getSOAPBody()
                    .getElementsByTagName(PixiapiConstants.PIXIAPI_RESPONSE_TAG_SQLROWSET1).item(0);
        } catch (MalformedURLException me) {
            LOG.error("Could not resolve Pixi API URL.", me);
        } catch (SOAPException se) {
            LOG.error("Error requesting SOAP Message.", se);
        }
        return null;
    }
}
