package com.pixi.api.exceptions;

import javax.xml.soap.SOAPException;

/**
 * @author jfelipe
 *
 */
public class SOAPResponseErrorException extends SOAPException {

    /**
     * 
     */
    public SOAPResponseErrorException() {
        super();
    }


    /**
     * 
     * @param message
     */
    public SOAPResponseErrorException(String message) {
        super(message);
    }
}
