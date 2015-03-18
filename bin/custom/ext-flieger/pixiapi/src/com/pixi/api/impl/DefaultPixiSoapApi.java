/**
 * 
 */
package com.pixi.api.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.AbstractMap.SimpleEntry;
import java.util.Date;
import java.util.List;

import javax.xml.soap.MessageFactory;
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
import com.pixi.api.core.PropertiesUtil;

/**
 * 
 * 
 * @author jfelipe
 *
 */
public class DefaultPixiSoapApi implements PixiSOAPAPI {

    public static final Logger LOG = Logger.getLogger(DefaultPixiSoapApi.class.getName());


    /**
     * Sets the authentication for sending the SOAP messages.
     * 
     * @param message
     * 
     */
    private String getAuthorization() {
        PropertiesUtil prop = PropertiesUtil.getInstance();
        String userAndPass = String.valueOf(prop.getUsername() + ":" + prop.getPassword());
        String authorization = Base64.encodeBase64String(userAndPass.getBytes());
        // FIXME: install a trustManager to bypass the certificate
        return authorization;
    }


    /**
     * Builds the message that will be sent to Pixi API.
     * 
     * @param requestFunction
     *            The function to be executed in the end-point
     * @param functionParameters
     *            The values that requestFunction needs.
     * @return
     *         A SOAPMessage object containing the response from end-point.
     * @throws SOAPException
     *
     * @author jfelipe
     */
    @Override
    public SOAPMessage buildMessage(String requestFunction,
            List<SimpleEntry<String, String>> functionParameters) throws SOAPException {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        SOAPMessage message = MessageFactory.newInstance().createMessage();
        message.getMimeHeaders().addHeader("Authorization", "Basic " + getAuthorization());
        SOAPFactory soapFactory = SOAPFactory.newInstance();
        SOAPBody messageBody = message.getSOAPBody();
        PropertiesUtil properties = PropertiesUtil.getInstance();
        Name bodyName = soapFactory.createName(requestFunction, properties.getPrefix(),
                properties.getNameSpace());
        SOAPBodyElement messageFunction = messageBody.addBodyElement(bodyName);
        for (SimpleEntry<String, String> parameter : functionParameters) {
            Name attributeName = soapFactory.createName(parameter.getKey(),
                    properties.getPrefix(), properties.getNameSpace());
            SOAPElement requestFunctionAttribute = messageFunction.addChildElement(attributeName);
            Object attributeValue = parameter.getValue();
            if (attributeValue != null) {
                if (attributeValue instanceof String) {
                    requestFunctionAttribute.addTextNode((String) attributeValue);
                } else if (attributeValue instanceof Date) {
                    requestFunctionAttribute.addTextNode(dateFormatter
                            .format((Date) attributeValue));
                } else if (attributeValue instanceof Integer) {
                    requestFunctionAttribute.addTextNode(((Integer) attributeValue).toString());
                } else {
                    requestFunctionAttribute.addTextNode(attributeValue + "");
                }
            }
        }
        return message;
    }


    /**
     * Send a request to Pixi API Webservice, accordingly to the given message.
     * 
     * @param message
     *            The SOAPMessage containing the function that will be sent to Pixi API.
     * @return
     *         A SOAPMessage object containing the response from end-point.
     * @throws MalformedURLException
     *             In case the URL from message parameter is not correct.
     * @author jfelipe
     */
    @Override
    public SOAPMessage sendPixiWebServiceRequest(final SOAPMessage message)
            throws MalformedURLException {
        if (message == null) {
            throw new InvalidParameterException("No request to sendo to Pixi Webservice API");
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
        SOAPMessage response = null;
        try {
            connection = SOAPConnectionFactory.newInstance().createConnection();
            response = connection.call(message, new URL(domain, ws, handler));
            LOG.info("Request sent to Pixi Webservice API: "
                    + message.getSOAPBody().getFirstChild().getLocalName());
        } catch (SOAPException se) {
            LOG.error("Error sending Pixi Webservice API request: ", se);
        } finally {
            try {
                connection.close();
            } catch (SOAPException se) {
                // nothing to do in this case...
            }
        }
        return response;
    }
}
