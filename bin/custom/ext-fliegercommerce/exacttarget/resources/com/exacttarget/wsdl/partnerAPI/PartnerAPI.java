/**
 * PartnerAPI.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public interface PartnerAPI extends javax.xml.rpc.Service {

/**
 * ExactTarget Partner API
 */
    public java.lang.String getSoapAddress();

    public com.exacttarget.wsdl.partnerAPI.Soap getSoap() throws javax.xml.rpc.ServiceException;

    public com.exacttarget.wsdl.partnerAPI.Soap getSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
