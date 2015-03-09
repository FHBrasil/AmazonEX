package br.flieger.storecatalogfeedparser.support;

import java.io.Serializable;

/**
 * Notifiable classes
 * 
 * @author franthescolly
 *
 */
public interface Notifiable extends Serializable {

    /**
     * Notify method
     * 
     * @param message
     *            Message to notify
     */
    void notify(String message);
}