/**
 * RecurringLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.adyen.services.recurring;

import com.flieger.main.Credentials;
import com.flieger.utils.ServiceConfig;

public class RecurringLocator extends org.apache.axis.client.Service implements com.adyen.services.recurring.Recurring {

    public RecurringLocator() {
    }


    public RecurringLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public RecurringLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for RecurringHttpPort
    private java.lang.String RecurringHttpPort_address = ServiceConfig.getParameter(Credentials.RECURRING_ADDRESS);

    public java.lang.String getRecurringHttpPortAddress() {
        return RecurringHttpPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String RecurringHttpPortWSDDServiceName = "RecurringHttpPort";

    public java.lang.String getRecurringHttpPortWSDDServiceName() {
        return RecurringHttpPortWSDDServiceName;
    }

    public void setRecurringHttpPortWSDDServiceName(java.lang.String name) {
        RecurringHttpPortWSDDServiceName = name;
    }

    public com.adyen.services.recurring.RecurringPortType getRecurringHttpPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(RecurringHttpPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getRecurringHttpPort(endpoint);
    }

    public com.adyen.services.recurring.RecurringPortType getRecurringHttpPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.adyen.services.recurring.RecurringHttpBindingStub _stub = new com.adyen.services.recurring.RecurringHttpBindingStub(portAddress, this);
            _stub.setPortName(getRecurringHttpPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setRecurringHttpPortEndpointAddress(java.lang.String address) {
        RecurringHttpPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.adyen.services.recurring.RecurringPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.adyen.services.recurring.RecurringHttpBindingStub _stub = new com.adyen.services.recurring.RecurringHttpBindingStub(new java.net.URL(RecurringHttpPort_address), this);
                _stub.setPortName(getRecurringHttpPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("RecurringHttpPort".equals(inputPortName)) {
            return getRecurringHttpPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://recurring.services.adyen.com", "Recurring");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "RecurringHttpPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("RecurringHttpPort".equals(portName)) {
            setRecurringHttpPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
