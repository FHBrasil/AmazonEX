/**
 * 
 */
package com.pixi.api;

import java.net.MalformedURLException;
import java.util.List;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import com.pixi.api.core.PixiParameter;

/**
 * @author jfelipe
 *
 */
public interface PixiSOAPAPI {

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
    SOAPMessage buildMessage(String pixiFunction, List<PixiParameter> values) throws SOAPException;


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
    SOAPMessage callPixi(final SOAPMessage message) throws SOAPException, MalformedURLException;
}
