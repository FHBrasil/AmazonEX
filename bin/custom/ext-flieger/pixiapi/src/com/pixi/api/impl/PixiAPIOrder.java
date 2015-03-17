/**
 * 
 */
package com.pixi.api.impl;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;

import com.pixi.api.constants.PixiapiConstants;
import com.pixi.api.core.PixiParameter;


/**
 * @author jfelipe
 *
 */
public class PixiAPIOrder {
    
    private static final Logger LOG = Logger.getLogger(PixiAPIOrder.class.getName());
    
    /**
     * 
     * 
     * @author jfelipe
     */
    public Node getOrders() {
        SOAPMessage message;
        SOAPMessage response;
        // FIXME: Better inject this PixiAPI with spring?
        DefaultPixiSOAPAPI defaultPixiAPI = new DefaultPixiSOAPAPI();
        try {
            List<PixiParameter> values = new ArrayList<PixiParameter>();
            values.add(new PixiParameter(PixiParameter.LOCATION_ID, "001"));
            values.add(new PixiParameter(PixiParameter.SHOW_ORDER, "1"));
            message = defaultPixiAPI.buildMessage(PixiapiConstants.PIXIAPI_FUNCTION_GET_ORDERS,
                    values);
            try {
                response = defaultPixiAPI.callPixi(message);
                return response.getSOAPBody().getElementsByTagName("SqlRowSet1").item(0);
            } catch (SOAPException | MalformedURLException e) {
                LOG.error("Error sending SOAP Message.", e);
            }
        } catch (SOAPException se) {
            LOG.error("Error creating SOAP Message.", se);
        }
        return null;
    }
}
