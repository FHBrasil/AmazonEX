/**
 * 
 */
package br.flieger.storecatalogfeed.xml.template;

import java.util.Set;

import org.jdom.Document;

/**
 * @author franthescolly
 *
 */
public interface XMLTemplate<T> {

    /**
     * 
     * @param type
     */
    Document apply(Set<T> type);
    
    
    /**
     * 
     * @param items
     *
     * @author jfelipe
     */
    Document createXMLDocument(Set<T> items);


    /**
     * 
     */
    String getCode();
}