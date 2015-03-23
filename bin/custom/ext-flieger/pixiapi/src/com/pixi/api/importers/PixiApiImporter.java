/**
 * 
 */
package com.pixi.api.importers;

import java.net.MalformedURLException;

import javax.xml.soap.SOAPException;

import org.w3c.dom.Node;

/**
 * @author jfelipe
 *
 */
public interface PixiApiImporter {

    /**
     * Import an XML file from the FTP server.
     * 
     * @param values
     *            Array of values to be send with the request function
     * @return
     *         The XML file requested to the server
     * @throws SOAPException
     * @throws MalformedURLException
     *
     * @author jfelipe
     */
    Node importXml(String... values) throws SOAPException, MalformedURLException;


    /**
     * Return a single int value from the server.
     * 
     * @param value
     *            The value to send with the request function
     * @return
     *         An integer, according to the request function
     * @throws SOAPException
     * @throws MalformedURLException
     *
     * @author jfelipe
     */
    int importInteger(String value) throws SOAPException, MalformedURLException;
}
