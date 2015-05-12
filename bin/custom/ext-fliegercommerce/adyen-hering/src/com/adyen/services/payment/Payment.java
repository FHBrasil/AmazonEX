/**
 * Payment.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.adyen.services.payment;

public interface Payment extends javax.xml.rpc.Service {
    public java.lang.String getPaymentHttpPortAddress();

    public com.adyen.services.payment.PaymentPortType getPaymentHttpPort() throws javax.xml.rpc.ServiceException;

    public com.adyen.services.payment.PaymentPortType getPaymentHttpPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
