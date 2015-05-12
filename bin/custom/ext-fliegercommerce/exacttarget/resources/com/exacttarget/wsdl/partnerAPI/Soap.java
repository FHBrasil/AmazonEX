/**
 * Soap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public interface Soap extends java.rmi.Remote {

    /**
     * Create objects
     */
    public com.exacttarget.wsdl.partnerAPI.CreateResponse create(com.exacttarget.wsdl.partnerAPI.CreateRequest parameters) throws java.rmi.RemoteException;

    /**
     * Retrieve objects
     */
    public com.exacttarget.wsdl.partnerAPI.RetrieveResponseMsg retrieve(com.exacttarget.wsdl.partnerAPI.RetrieveRequestMsg parameters) throws java.rmi.RemoteException;

    /**
     * Update objects
     */
    public com.exacttarget.wsdl.partnerAPI.UpdateResponse update(com.exacttarget.wsdl.partnerAPI.UpdateRequest parameters) throws java.rmi.RemoteException;

    /**
     * Delete objects
     */
    public com.exacttarget.wsdl.partnerAPI.DeleteResponse delete(com.exacttarget.wsdl.partnerAPI.DeleteRequest parameters) throws java.rmi.RemoteException;
    public com.exacttarget.wsdl.partnerAPI.QueryResponseMsg query(com.exacttarget.wsdl.partnerAPI.QueryRequestMsg parameters) throws java.rmi.RemoteException;
    public com.exacttarget.wsdl.partnerAPI.DefinitionResponseMsg describe(com.exacttarget.wsdl.partnerAPI.DefinitionRequestMsg parameters) throws java.rmi.RemoteException;
    public com.exacttarget.wsdl.partnerAPI.ExecuteResponseMsg execute(com.exacttarget.wsdl.partnerAPI.ExecuteRequest[] parameters) throws java.rmi.RemoteException;
    public com.exacttarget.wsdl.partnerAPI.PerformResponseMsg perform(com.exacttarget.wsdl.partnerAPI.PerformRequestMsg parameters) throws java.rmi.RemoteException;
    public com.exacttarget.wsdl.partnerAPI.ConfigureResponseMsg configure(com.exacttarget.wsdl.partnerAPI.ConfigureRequestMsg parameters) throws java.rmi.RemoteException;
    public com.exacttarget.wsdl.partnerAPI.ScheduleResponseMsg schedule(com.exacttarget.wsdl.partnerAPI.ScheduleRequestMsg parameters) throws java.rmi.RemoteException;
    public com.exacttarget.wsdl.partnerAPI.VersionInfoResponseMsg versionInfo(com.exacttarget.wsdl.partnerAPI.VersionInfoRequestMsg parameters) throws java.rmi.RemoteException;

    /**
     * Perform ad hoc data extracts
     */
    public com.exacttarget.wsdl.partnerAPI.ExtractResponseMsg extract(com.exacttarget.wsdl.partnerAPI.ExtractRequest[] parameters) throws java.rmi.RemoteException;

    /**
     * Get Current System Status
     */
    public com.exacttarget.wsdl.partnerAPI.SystemStatusResponseMsg getSystemStatus(com.exacttarget.wsdl.partnerAPI.SystemStatusRequestMsg parameters) throws java.rmi.RemoteException;
}
