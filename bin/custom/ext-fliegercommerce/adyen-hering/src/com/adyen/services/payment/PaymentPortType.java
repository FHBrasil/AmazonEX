/**
 * PaymentPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.adyen.services.payment;

public interface PaymentPortType extends java.rmi.Remote {
    public com.adyen.services.payment.PaymentResult checkFraud(com.adyen.services.payment.PaymentRequest paymentRequest) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException;
    public com.adyen.services.payment.PaymentResult refundWithData(com.adyen.services.payment.PaymentRequest request) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException;
    public com.adyen.services.payment.PaymentResult authorise(com.adyen.services.payment.PaymentRequest paymentRequest) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException;
    public com.adyen.services.payment.ModificationResult authoriseReferral(com.adyen.services.payment.ModificationRequest modificationRequest) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException;
    public com.adyen.services.payment.DirectDebitResult directdebit(com.adyen.services.payment.DirectDebitRequest request) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException;
    public com.adyen.services.payment.ModificationResult cancel(com.adyen.services.payment.ModificationRequest modificationRequest) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException;
    public com.adyen.services.payment.BalanceCheckResult balanceCheck(com.adyen.services.payment.BalanceCheckRequest request) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException;
    public com.adyen.services.payment.AddressCheckResult addressCheck(com.adyen.services.payment.AddressCheckRequest request) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException;
    public com.adyen.services.payment.PaymentResult authorise3D(com.adyen.services.payment.PaymentRequest3D paymentRequest3D) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException;
    public com.adyen.services.payment.ModificationResult refund(com.adyen.services.payment.ModificationRequest modificationRequest) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException;
    public com.adyen.services.payment.ModificationResult capture(com.adyen.services.payment.ModificationRequest modificationRequest) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException;
    public com.adyen.services.payment.ModificationResult cancelOrRefund(com.adyen.services.payment.ModificationRequest modificationRequest) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException;
    public com.adyen.services.payment.FundTransferResult fundTransfer(com.adyen.services.payment.FundTransferRequest request) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException;
}
