/**
 * Soap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.flieger.exacttarget.wsdl.api;

public interface Soap extends java.rmi.Remote {

    /**
     * Create objects
     */
    public br.flieger.exacttarget.wsdl.api.CreateResponse create(br.flieger.exacttarget.wsdl.api.CreateRequest parameters) throws java.rmi.RemoteException;

    /**
     * Retrieve objects
     */
    public br.flieger.exacttarget.wsdl.api.RetrieveResponseMsg retrieve(br.flieger.exacttarget.wsdl.api.RetrieveRequestMsg parameters) throws java.rmi.RemoteException;

    /**
     * Update objects
     */
    public br.flieger.exacttarget.wsdl.api.UpdateResponse update(br.flieger.exacttarget.wsdl.api.UpdateRequest parameters) throws java.rmi.RemoteException;

    /**
     * Delete objects
     */
    public br.flieger.exacttarget.wsdl.api.DeleteResponse delete(br.flieger.exacttarget.wsdl.api.DeleteRequest parameters) throws java.rmi.RemoteException;
    public br.flieger.exacttarget.wsdl.api.QueryResponseMsg query(br.flieger.exacttarget.wsdl.api.QueryRequestMsg parameters) throws java.rmi.RemoteException;
    public br.flieger.exacttarget.wsdl.api.DefinitionResponseMsg describe(br.flieger.exacttarget.wsdl.api.DefinitionRequestMsg parameters) throws java.rmi.RemoteException;
    public br.flieger.exacttarget.wsdl.api.ExecuteResponseMsg execute(br.flieger.exacttarget.wsdl.api.ExecuteRequest[] parameters) throws java.rmi.RemoteException;
    public br.flieger.exacttarget.wsdl.api.PerformResponseMsg perform(br.flieger.exacttarget.wsdl.api.PerformRequestMsg parameters) throws java.rmi.RemoteException;
    public br.flieger.exacttarget.wsdl.api.ConfigureResponseMsg configure(br.flieger.exacttarget.wsdl.api.ConfigureRequestMsg parameters) throws java.rmi.RemoteException;
    public br.flieger.exacttarget.wsdl.api.ScheduleResponseMsg schedule(br.flieger.exacttarget.wsdl.api.ScheduleRequestMsg parameters) throws java.rmi.RemoteException;
    public br.flieger.exacttarget.wsdl.api.VersionInfoResponseMsg versionInfo(br.flieger.exacttarget.wsdl.api.VersionInfoRequestMsg parameters) throws java.rmi.RemoteException;

    /**
     * Perform ad hoc data extracts
     */
    public br.flieger.exacttarget.wsdl.api.ExtractResponseMsg extract(br.flieger.exacttarget.wsdl.api.ExtractRequest[] parameters) throws java.rmi.RemoteException;

    /**
     * Get Current System Status
     */
    public br.flieger.exacttarget.wsdl.api.SystemStatusResponseMsg getSystemStatus(br.flieger.exacttarget.wsdl.api.SystemStatusRequestMsg parameters) throws java.rmi.RemoteException;
}
