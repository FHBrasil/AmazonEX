/**
 * 
 */
package com.pixi.api.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPMessage;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import com.pixi.api.PixiSOAPAPI;
import com.pixi.api.core.PixiParameter;
import com.pixi.api.core.PropertiesUtil;

/**
 * 
 * 
 * @author jfelipe
 *
 */
public class DefaultPixiSOAPAPI implements PixiSOAPAPI {

    public static final Logger LOG = Logger.getLogger(DefaultPixiSOAPAPI.class.getName());


    /**
     * Sets the authentication for sending the SOAP messages.
     * 
     * @param message
     * 
     */
    private void setAutentication(SOAPMessage message) {
        PropertiesUtil prop = PropertiesUtil.getInstance();
        String userAndPass = String.valueOf(prop.getUsername() + ":" + prop.getPassword());
        String authorization = Base64.encodeBase64String(userAndPass.getBytes());
        MimeHeaders hd = message.getMimeHeaders();
        hd.addHeader("Authorization", "Basic " + authorization);
    }


    /**
     * Builds the message that will be sent to Pixi API.
     * 
     * @param pixiFunction
     *            The function to be executed in the end-point
     * @param values
     *            The values the given function needs.
     * @return
     *         A SOAPMessage object containing the response from end-point.
     * @throws SOAPException
     *
     * @author jfelipe
     */
    @Override
    public SOAPMessage buildMessage(String pixiFunction, List<PixiParameter> values)
            throws SOAPException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        SOAPMessage message = MessageFactory.newInstance().createMessage();
        setAutentication(message);
        SOAPFactory soapFactory = SOAPFactory.newInstance();
        SOAPBody body = message.getSOAPBody();
        PropertiesUtil prop = PropertiesUtil.getInstance();
        Name bodyName = soapFactory.createName(pixiFunction, prop.getPrefix(), prop.getNameSpace());
        SOAPBodyElement fPixi = body.addBodyElement(bodyName);
        for (PixiParameter pixiAttribut : values) {
            Name attribute = soapFactory.createName(pixiAttribut.getName(), prop.getPrefix(),
                    prop.getNameSpace());
            SOAPElement attributeElement = fPixi.addChildElement(attribute);
            Object value = pixiAttribut.getValue();
            if (value != null) {
                if (value instanceof String) {
                    attributeElement.addTextNode((String) value);
                } else if (value instanceof Date) {
                    attributeElement.addTextNode(sf.format((Date) value));
                } else if (value instanceof Integer) {
                    attributeElement.addTextNode(((Integer) value).toString());
                } else {
                    attributeElement.addTextNode(value + "");
                }
            }
        }
        return message;
    }


    /**
     * Calls SOAP Pixi API.
     * 
     * @param message
     *            The SOAPMessage containing the function that will be sent to Pixi API.
     * @return
     *         A SOAPMessage object containing the response from end-point.
     * @throws SOAPException
     * @throws MalformedURLException
     *
     * @author jfelipe
     */
    @Override
    public SOAPMessage callPixi(final SOAPMessage message) throws SOAPException,
            MalformedURLException {
        if (message == null) {
            throw new InvalidParameterException("message is null");
        }
        PropertiesUtil prop = PropertiesUtil.getInstance();
        final URL domain = new URL(prop.getDomain());
        final String ws = prop.getServicePath();
        final URLStreamHandler handler = new URLStreamHandler() {

            @Override
            protected URLConnection openConnection(URL url) throws IOException {
                URLConnection connection = new URL(url.toString()).openConnection();
                connection.setConnectTimeout(10000);
                connection.setReadTimeout(60000);
                return (connection);
            }
        };
        SOAPConnection connection = null;
        try {
            connection = SOAPConnectionFactory.newInstance().createConnection();
            SOAPMessage response = connection.call(message, new URL(domain, ws, handler));
            logSoapMessage("Request:", message);
            logSoapMessage("Response:", response);
            return response;
        } finally {
            try {
                connection.close();
            } catch (SOAPException se) {
                // nothing to do in this case...
            }
        }
    }


    /**
     * Logs the SOAP message to a (file? paper? stone?)
     * 
     * @param logMessage
     * @param message
     */
    private void logSoapMessage(String logMessage, SOAPMessage message) {
        try {
            if (message == null) {
                return;
            }
            OutputStream outStream = null;
            // if (false) { // FIXME: ???
            outStream = new ByteArrayOutputStream();
            message.writeTo(outStream);
            LOG.info(logMessage + " " + outStream.toString());
            outStream.close();
            // }
        } catch (Exception e) {
            LOG.error("Error logging message", e);
        }
    }
}
