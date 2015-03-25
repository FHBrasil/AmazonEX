/**
 * 
 */
package com.pixi.api.importers;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;

import org.w3c.dom.Node;

/**
 * @author jfelipe
 *
 */
public interface PixiApiImporter {

    /**
     * Imports an XML file from Pixi API.
     * 
     * @param values
     *            Array of values to be send with the request function
     * @return
     *         The XML file requested to the server
     *
     * @author jfelipe
     */
    Node importXml(String... values);


    /**
     * Returns a single int value from Pixi API.
     * 
     * @param value
     *            The value to send with the request function
     * @return
     *         An integer, according to the request function
     *
     * @author jfelipe
     */
    int importInteger(String value);


    /**
     * Returns a list of value-key pair from Pixi API.
     * 
     * @param parameter
     * @return
     *         A list of value-key pair.
     * @author jfelipe
     */
    List<SimpleEntry<String, String>> importListKeyValuePair(Object parameter);
}
