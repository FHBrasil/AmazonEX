/**
 * PaymentLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.adyen.services.payment;

public class PaymentLocator extends org.apache.axis.client.Service implements com.adyen.services.payment.Payment {

    public PaymentLocator() {
    }


    public PaymentLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public PaymentLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for PaymentHttpPort
    private java.lang.String PaymentHttpPort_address = "https://pal-test.adyen.com/pal/servlet/soap/Payment";

    public java.lang.String getPaymentHttpPortAddress() {
        return PaymentHttpPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String PaymentHttpPortWSDDServiceName = "PaymentHttpPort";

    public java.lang.String getPaymentHttpPortWSDDServiceName() {
        return PaymentHttpPortWSDDServiceName;
    }

    public void setPaymentHttpPortWSDDServiceName(java.lang.String name) {
        PaymentHttpPortWSDDServiceName = name;
    }

    public com.adyen.services.payment.PaymentPortType getPaymentHttpPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(PaymentHttpPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getPaymentHttpPort(endpoint);
    }

    public com.adyen.services.payment.PaymentPortType getPaymentHttpPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.adyen.services.payment.PaymentHttpBindingStub _stub = new com.adyen.services.payment.PaymentHttpBindingStub(portAddress, this);
            _stub.setPortName(getPaymentHttpPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setPaymentHttpPortEndpointAddress(java.lang.String address) {
        PaymentHttpPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.adyen.services.payment.PaymentPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.adyen.services.payment.PaymentHttpBindingStub _stub = new com.adyen.services.payment.PaymentHttpBindingStub(new java.net.URL(PaymentHttpPort_address), this);
                _stub.setPortName(getPaymentHttpPortWSDDServiceName());
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
        if ("PaymentHttpPort".equals(inputPortName)) {
            return getPaymentHttpPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://payment.services.adyen.com", "Payment");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://payment.services.adyen.com", "PaymentHttpPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("PaymentHttpPort".equals(portName)) {
            setPaymentHttpPortEndpointAddress(address);
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
