/**
 * RecurringPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.adyen.services.recurring;

public interface RecurringPortType extends java.rmi.Remote {
    public com.adyen.services.recurring.RecurringResult deactivateRecurring(com.adyen.services.recurring.RecurringRequest recurringRequest) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException;
    public com.adyen.services.recurring.DisableResult disable(com.adyen.services.recurring.DisableRequest request) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException;
    public com.adyen.services.recurring.RecurringResult initialiseRecurring(com.adyen.services.recurring.RecurringRequest recurringRequest) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException;
    public com.adyen.services.recurring.RecurringDetailsResult listRecurringDetails(com.adyen.services.recurring.RecurringDetailsRequest request) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException;
    public com.adyen.services.recurring.StoreTokenResult storeToken(com.adyen.services.recurring.StoreTokenRequest request) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException;
    public com.adyen.services.recurring.RecurringResult submitRecurring(com.adyen.services.recurring.RecurringRequest recurringRequest) throws java.rmi.RemoteException, com.adyen.services.common.ServiceException;
}
