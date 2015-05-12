package com.adyen.services.recurring;

import de.hybris.platform.util.Config;

import org.apache.axis.client.Call;
import org.apache.axis.client.Stub;

import com.flieger.main.Credentials;

public class RecurringPortTypeProxy implements com.adyen.services.recurring.RecurringPortType {
  private String _endpoint = null;
  private com.adyen.services.recurring.RecurringPortType recurringPortType = null;
  
  public RecurringPortTypeProxy() {
    _initRecurringPortTypeProxy();
  }
  
  public RecurringPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initRecurringPortTypeProxy();
  }
  
  private void _initRecurringPortTypeProxy() {
    try {
      recurringPortType = (new com.adyen.services.recurring.RecurringLocator()).getRecurringHttpPort();
      if (recurringPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)recurringPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)recurringPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
  	((Stub) recurringPortType)._setProperty(Call.USERNAME_PROPERTY, Config.getParameter(Credentials.WS_USER));
  	((Stub) recurringPortType)._setProperty(Call.PASSWORD_PROPERTY, Credentials.PWD_USER);
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (recurringPortType != null)
      ((javax.xml.rpc.Stub)recurringPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.adyen.services.recurring.RecurringPortType getRecurringPortType() {
    if (recurringPortType == null)
      _initRecurringPortTypeProxy();
    return recurringPortType;
  }
  
  public com.adyen.services.recurring.RecurringResult deactivateRecurring(com.adyen.services.recurring.RecurringRequest recurringRequest) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException{
    if (recurringPortType == null)
      _initRecurringPortTypeProxy();
    return recurringPortType.deactivateRecurring(recurringRequest);
  }
  
  public com.adyen.services.recurring.DisableResult disable(com.adyen.services.recurring.DisableRequest request) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException{
    if (recurringPortType == null)
      _initRecurringPortTypeProxy();
    return recurringPortType.disable(request);
  }
  
  public com.adyen.services.recurring.RecurringResult initialiseRecurring(com.adyen.services.recurring.RecurringRequest recurringRequest) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException{
    if (recurringPortType == null)
      _initRecurringPortTypeProxy();
    return recurringPortType.initialiseRecurring(recurringRequest);
  }
  
  public com.adyen.services.recurring.RecurringDetailsResult listRecurringDetails(com.adyen.services.recurring.RecurringDetailsRequest request) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException{
    if (recurringPortType == null)
      _initRecurringPortTypeProxy();
    return recurringPortType.listRecurringDetails(request);
  }
  
  public com.adyen.services.recurring.StoreTokenResult storeToken(com.adyen.services.recurring.StoreTokenRequest request) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException{
    if (recurringPortType == null)
      _initRecurringPortTypeProxy();
    return recurringPortType.storeToken(request);
  }
  
  public com.adyen.services.recurring.RecurringResult submitRecurring(com.adyen.services.recurring.RecurringRequest recurringRequest) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException{
    if (recurringPortType == null)
      _initRecurringPortTypeProxy();
    return recurringPortType.submitRecurring(recurringRequest);
  }
  
  
}