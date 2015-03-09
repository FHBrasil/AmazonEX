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
     * @param resolver
     * @return
     */
    Document apply(Set<T> type);


    /**
     * 
     * @return
     */
    String getCode();
}