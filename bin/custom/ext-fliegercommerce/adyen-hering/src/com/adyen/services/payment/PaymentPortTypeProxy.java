package com.adyen.services.payment;

import org.apache.axis.client.Call;
import org.apache.axis.client.Stub;

import com.flieger.main.Credentials;

public class PaymentPortTypeProxy implements com.adyen.services.payment.PaymentPortType {
  private String _endpoint = null;
  private com.adyen.services.payment.PaymentPortType paymentPortType = null;
  
  public PaymentPortTypeProxy() {
    _initPaymentPortTypeProxy();
  }
  
  public PaymentPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initPaymentPortTypeProxy();
  }
  
  private void _initPaymentPortTypeProxy() {
    try {
      paymentPortType = (new com.adyen.services.payment.PaymentLocator()).getPaymentHttpPort();
      if (paymentPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)paymentPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)paymentPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
	((Stub) paymentPortType)._setProperty(Call.USERNAME_PROPERTY, Credentials.WS_USER);
	((Stub) paymentPortType)._setProperty(Call.PASSWORD_PROPERTY, Credentials.PWD_USER);
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (paymentPortType != null)
      ((javax.xml.rpc.Stub)paymentPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.adyen.services.payment.PaymentPortType getPaymentPortType() {
    if (paymentPortType == null)
      _initPaymentPortTypeProxy();
    return paymentPortType;
  }
  
  public com.adyen.services.payment.PaymentResult checkFraud(com.adyen.services.payment.PaymentRequest paymentRequest) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException{
    if (paymentPortType == null)
      _initPaymentPortTypeProxy();
    return paymentPortType.checkFraud(paymentRequest);
  }
  
  public com.adyen.services.payment.PaymentResult refundWithData(com.adyen.services.payment.PaymentRequest request) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException{
    if (paymentPortType == null)
      _initPaymentPortTypeProxy();
    return paymentPortType.refundWithData(request);
  }
  
  public com.adyen.services.payment.PaymentResult authorise(com.adyen.services.payment.PaymentRequest paymentRequest) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException{
    if (paymentPortType == null)
      _initPaymentPortTypeProxy();
    return paymentPortType.authorise(paymentRequest);
  }
  
  public com.adyen.services.payment.ModificationResult authoriseReferral(com.adyen.services.payment.ModificationRequest modificationRequest) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException{
    if (paymentPortType == null)
      _initPaymentPortTypeProxy();
    return paymentPortType.authoriseReferral(modificationRequest);
  }
  
  public com.adyen.services.payment.DirectDebitResult directdebit(com.adyen.services.payment.DirectDebitRequest request) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException{
    if (paymentPortType == null)
      _initPaymentPortTypeProxy();
    return paymentPortType.directdebit(request);
  }
  
  public com.adyen.services.payment.ModificationResult cancel(com.adyen.services.payment.ModificationRequest modificationRequest) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException{
    if (paymentPortType == null)
      _initPaymentPortTypeProxy();
    return paymentPortType.cancel(modificationRequest);
  }
  
  public com.adyen.services.payment.BalanceCheckResult balanceCheck(com.adyen.services.payment.BalanceCheckRequest request) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException{
    if (paymentPortType == null)
      _initPaymentPortTypeProxy();
    return paymentPortType.balanceCheck(request);
  }
  
  public com.adyen.services.payment.AddressCheckResult addressCheck(com.adyen.services.payment.AddressCheckRequest request) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException{
    if (paymentPortType == null)
      _initPaymentPortTypeProxy();
    return paymentPortType.addressCheck(request);
  }
  
  public com.adyen.services.payment.PaymentResult authorise3D(com.adyen.services.payment.PaymentRequest3D paymentRequest3D) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException{
    if (paymentPortType == null)
      _initPaymentPortTypeProxy();
    return paymentPortType.authorise3D(paymentRequest3D);
  }
  
  public com.adyen.services.payment.ModificationResult refund(com.adyen.services.payment.ModificationRequest modificationRequest) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException{
    if (paymentPortType == null)
      _initPaymentPortTypeProxy();
    return paymentPortType.refund(modificationRequest);
  }
  
  public com.adyen.services.payment.ModificationResult capture(com.adyen.services.payment.ModificationRequest modificationRequest) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException{
    if (paymentPortType == null)
      _initPaymentPortTypeProxy();
    return paymentPortType.capture(modificationRequest);
  }
  
  public com.adyen.services.payment.ModificationResult cancelOrRefund(com.adyen.services.payment.ModificationRequest modificationRequest) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException{
    if (paymentPortType == null)
      _initPaymentPortTypeProxy();
    return paymentPortType.cancelOrRefund(modificationRequest);
  }
  
  public com.adyen.services.payment.FundTransferResult fundTransfer(com.adyen.services.payment.FundTransferRequest request) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException{
    if (paymentPortType == null)
      _initPaymentPortTypeProxy();
    return paymentPortType.fundTransfer(request);
  }
  
  
}