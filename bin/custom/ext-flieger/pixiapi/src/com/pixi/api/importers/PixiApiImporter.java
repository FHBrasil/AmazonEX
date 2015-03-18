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
    
    Node importFromPixiApi(String... values) throws SOAPException, MalformedURLException; 
}
