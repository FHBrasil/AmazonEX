/**
 * PartnerAPI.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.flieger.exacttarget.wsdl.api;

public interface PartnerAPI extends javax.xml.rpc.Service {

/**
 * ExactTarget Partner API
 */
    public java.lang.String getSoapAddress();

    public br.flieger.exacttarget.wsdl.api.Soap getSoap() throws javax.xml.rpc.ServiceException;

    public br.flieger.exacttarget.wsdl.api.Soap getSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
