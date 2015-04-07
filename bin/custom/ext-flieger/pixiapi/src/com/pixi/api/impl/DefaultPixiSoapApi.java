package com.pixi.api.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.security.InvalidParameterException;
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
import org.w3c.dom.Node;

import com.google.common.base.Strings;
import com.pixi.api.PixiSOAPAPI;
import com.pixi.api.core.PixiApiResponseTags;
import com.pixi.api.core.PixiFunction;
import com.pixi.api.core.PixiFunctionParameter;
import com.pixi.api.core.PropertiesUtil;
import com.pixi.api.exceptions.SOAPResponseErrorException;

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
     * @return
     *         The authorization string
     * @author jfelipe
     */
    private String getAuthorization() {
        PropertiesUtil prop = PropertiesUtil.getInstance();
        String userAndPass = String.valueOf(prop.getUsername() + ":" + prop.getPassword());
        String authorization = Base64.encodeBase64String(userAndPass.getBytes());
        // FIXME: code a trustManager to bypass the certificate
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
     *
     * @throws SOAPException
     * @author jfelipe
     */
    @Override
    public SOAPMessage buildMessage(PixiFunction requestFunction,
            List<PixiFunctionParameter> functionParameters) throws SOAPException {
        if (requestFunction == null) {
            throw new InvalidParameterException("Request function should not be null.");
        }
        if (functionParameters == null || functionParameters.isEmpty()) {
            throw new InvalidParameterException("Function parameters should not be empty.");
        }
        SOAPMessage message = MessageFactory.newInstance().createMessage();
        message.getMimeHeaders().addHeader("Authorization", "Basic " + getAuthorization());
        SOAPFactory soapFactory = SOAPFactory.newInstance();
        SOAPBody messageBody = message.getSOAPBody();
        PropertiesUtil properties = PropertiesUtil.getInstance();
        Name bodyName = soapFactory.createName(requestFunction.getValue(), properties.getPrefix(),
                properties.getNameSpace());
        SOAPBodyElement messageFunction = messageBody.addBodyElement(bodyName);
        for (PixiFunctionParameter parameter : functionParameters) {
            Name attributeName = soapFactory.createName(parameter.getType().getValue(),
                    properties.getPrefix(), properties.getNameSpace());
            SOAPElement requestFunctionAttribute = messageFunction.addChildElement(attributeName);
            String paramValue = !Strings.isNullOrEmpty(parameter.getValue()) ? parameter.getValue()
                    : "";
            requestFunctionAttribute.addTextNode(paramValue);
        }
        return message;
    }


    /**
     * 
     * Send a request to Pixi API Webservice, accordingly to the given message.
     * 
     * @param message
     *            The SOAPMessage containing the function that will be sent to Pixi API.
     * @return
     *         A SOAPMessage object containing the response from end-point.
     * 
     * @throws SOAPException
     * @author jfelipe
     */
    @Override
    public SOAPMessage sendPixiWebServiceRequest(final SOAPMessage message)
            throws SOAPResponseErrorException, SOAPException {
        if (message == null) {
            throw new InvalidParameterException("No request to sendo to Pixi Webservice API");
        }
        PropertiesUtil prop = PropertiesUtil.getInstance();
        URL domain;
        try {
            domain = new URL(prop.getDomain());
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
            connection = SOAPConnectionFactory.newInstance().createConnection();
            response = connection.call(message, new URL(domain, ws, handler));
            checkResponseErrors(response.getSOAPBody());
            LOG.info("Request sent to Pixi Webservice API: "
                    + message.getSOAPBody().getFirstChild().getLocalName());
            try {
                connection.close();
            } catch (SOAPException se) {
                // nothing to do in this case...
            }
            return response;
        } catch (MalformedURLException me) {
            LOG.error("Mal Formed URL: " + prop.getDomain(), me);
        }
        return null;
    }


    /**
     * 
     * @param responseBody
     * @throws SOAPResponseErrorException
     *
     * @author jfelipe
     */
    private void checkResponseErrors(SOAPBody responseBody) throws SOAPResponseErrorException {
        Node errorClassNode = responseBody.getElementsByTagName(
                PixiApiResponseTags.SQL_CLASS.getValue()).item(0);
        if (errorClassNode != null && !"0".equals(errorClassNode.getTextContent())) {
            Node errorMessageNode = responseBody.getElementsByTagName(
                    PixiApiResponseTags.SQL_MESSAGE.getValue()).item(0);
            String errorMessage = errorMessageNode.getTextContent();
            if (!Strings.isNullOrEmpty(errorMessage)) {
                throw new SOAPResponseErrorException(errorMessage);
            }
        }
    }
}
