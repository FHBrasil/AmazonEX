/**
 * SendSummary.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class SendSummary  extends com.exacttarget.wsdl.partnerAPI.APIObject  implements java.io.Serializable {
    private java.lang.Integer accountID;

    private java.lang.String accountName;

    private java.lang.String accountEmail;

    private java.lang.Boolean isTestAccount;

    private java.lang.Integer sendID;

    private java.lang.String deliveredTime;

    private java.lang.Integer totalSent;

    private java.lang.Integer transactional;

    private java.lang.Integer nonTransactional;

    public SendSummary() {
    }

    public SendSummary(
           com.exacttarget.wsdl.partnerAPI.ClientID client,
           java.lang.String partnerKey,
           com.exacttarget.wsdl.partnerAPI.APIProperty[] partnerProperties,
           java.util.Calendar createdDate,
           java.util.Calendar modifiedDate,
           java.lang.Integer ID,
           java.lang.String objectID,
           java.lang.String customerKey,
           com.exacttarget.wsdl.partnerAPI.Owner owner,
           java.lang.String correlationID,
           java.lang.String objectState,
           java.lang.Integer accountID,
           java.lang.String accountName,
           java.lang.String accountEmail,
           java.lang.Boolean isTestAccount,
           java.lang.Integer sendID,
           java.lang.String deliveredTime,
           java.lang.Integer totalSent,
           java.lang.Integer transactional,
           java.lang.Integer nonTransactional) {
        super(
            client,
            partnerKey,
            partnerProperties,
            createdDate,
            modifiedDate,
            ID,
            objectID,
            customerKey,
            owner,
            correlationID,
            objectState);
        this.accountID = accountID;
        this.accountName = accountName;
        this.accountEmail = accountEmail;
        this.isTestAccount = isTestAccount;
        this.sendID = sendID;
        this.deliveredTime = deliveredTime;
        this.totalSent = totalSent;
        this.transactional = transactional;
        this.nonTransactional = nonTransactional;
    }


    /**
     * Gets the accountID value for this SendSummary.
     * 
     * @return accountID
     */
    public java.lang.Integer getAccountID() {
        return accountID;
    }


    /**
     * Sets the accountID value for this SendSummary.
     * 
     * @param accountID
     */
    public void setAccountID(java.lang.Integer accountID) {
        this.accountID = accountID;
    }


    /**
     * Gets the accountName value for this SendSummary.
     * 
     * @return accountName
     */
    public java.lang.String getAccountName() {
        return accountName;
    }


    /**
     * Sets the accountName value for this SendSummary.
     * 
     * @param accountName
     */
    public void setAccountName(java.lang.String accountName) {
        this.accountName = accountName;
    }


    /**
     * Gets the accountEmail value for this SendSummary.
     * 
     * @return accountEmail
     */
    public java.lang.String getAccountEmail() {
        return accountEmail;
    }


    /**
     * Sets the accountEmail value for this SendSummary.
     * 
     * @param accountEmail
     */
    public void setAccountEmail(java.lang.String accountEmail) {
        this.accountEmail = accountEmail;
    }


    /**
     * Gets the isTestAccount value for this SendSummary.
     * 
     * @return isTestAccount
     */
    public java.lang.Boolean getIsTestAccount() {
        return isTestAccount;
    }


    /**
     * Sets the isTestAccount value for this SendSummary.
     * 
     * @param isTestAccount
     */
    public void setIsTestAccount(java.lang.Boolean isTestAccount) {
        this.isTestAccount = isTestAccount;
    }


    /**
     * Gets the sendID value for this SendSummary.
     * 
     * @return sendID
     */
    public java.lang.Integer getSendID() {
        return sendID;
    }


    /**
     * Sets the sendID value for this SendSummary.
     * 
     * @param sendID
     */
    public void setSendID(java.lang.Integer sendID) {
        this.sendID = sendID;
    }


    /**
     * Gets the deliveredTime value for this SendSummary.
     * 
     * @return deliveredTime
     */
    public java.lang.String getDeliveredTime() {
        return deliveredTime;
    }


    /**
     * Sets the deliveredTime value for this SendSummary.
     * 
     * @param deliveredTime
     */
    public void setDeliveredTime(java.lang.String deliveredTime) {
        this.deliveredTime = deliveredTime;
    }


    /**
     * Gets the totalSent value for this SendSummary.
     * 
     * @return totalSent
     */
    public java.lang.Integer getTotalSent() {
        return totalSent;
    }


    /**
     * Sets the totalSent value for this SendSummary.
     * 
     * @param totalSent
     */
    public void setTotalSent(java.lang.Integer totalSent) {
        this.totalSent = totalSent;
    }


    /**
     * Gets the transactional value for this SendSummary.
     * 
     * @return transactional
     */
    public java.lang.Integer getTransactional() {
        return transactional;
    }


    /**
     * Sets the transactional value for this SendSummary.
     * 
     * @param transactional
     */
    public void setTransactional(java.lang.Integer transactional) {
        this.transactional = transactional;
    }


    /**
     * Gets the nonTransactional value for this SendSummary.
     * 
     * @return nonTransactional
     */
    public java.lang.Integer getNonTransactional() {
        return nonTransactional;
    }


    /**
     * Sets the nonTransactional value for this SendSummary.
     * 
     * @param nonTransactional
     */
    public void setNonTransactional(java.lang.Integer nonTransactional) {
        this.nonTransactional = nonTransactional;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SendSummary)) return false;
        SendSummary other = (SendSummary) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.accountID==null && other.getAccountID()==null) || 
             (this.accountID!=null &&
              this.accountID.equals(other.getAccountID()))) &&
            ((this.accountName==null && other.getAccountName()==null) || 
             (this.accountName!=null &&
              this.accountName.equals(other.getAccountName()))) &&
            ((this.accountEmail==null && other.getAccountEmail()==null) || 
             (this.accountEmail!=null &&
              this.accountEmail.equals(other.getAccountEmail()))) &&
            ((this.isTestAccount==null && other.getIsTestAccount()==null) || 
             (this.isTestAccount!=null &&
              this.isTestAccount.equals(other.getIsTestAccount()))) &&
            ((this.sendID==null && other.getSendID()==null) || 
             (this.sendID!=null &&
              this.sendID.equals(other.getSendID()))) &&
            ((this.deliveredTime==null && other.getDeliveredTime()==null) || 
             (this.deliveredTime!=null &&
              this.deliveredTime.equals(other.getDeliveredTime()))) &&
            ((this.totalSent==null && other.getTotalSent()==null) || 
             (this.totalSent!=null &&
              this.totalSent.equals(other.getTotalSent()))) &&
            ((this.transactional==null && other.getTransactional()==null) || 
             (this.transactional!=null &&
              this.transactional.equals(other.getTransactional()))) &&
            ((this.nonTransactional==null && other.getNonTransactional()==null) || 
             (this.nonTransactional!=null &&
              this.nonTransactional.equals(other.getNonTransactional())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getAccountID() != null) {
            _hashCode += getAccountID().hashCode();
        }
        if (getAccountName() != null) {
            _hashCode += getAccountName().hashCode();
        }
        if (getAccountEmail() != null) {
            _hashCode += getAccountEmail().hashCode();
        }
        if (getIsTestAccount() != null) {
            _hashCode += getIsTestAccount().hashCode();
        }
        if (getSendID() != null) {
            _hashCode += getSendID().hashCode();
        }
        if (getDeliveredTime() != null) {
            _hashCode += getDeliveredTime().hashCode();
        }
        if (getTotalSent() != null) {
            _hashCode += getTotalSent().hashCode();
        }
        if (getTransactional() != null) {
            _hashCode += getTransactional().hashCode();
        }
        if (getNonTransactional() != null) {
            _hashCode += getNonTransactional().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SendSummary.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendSummary"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AccountID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AccountName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountEmail");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AccountEmail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isTestAccount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsTestAccount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sendID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deliveredTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DeliveredTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalSent");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TotalSent"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactional");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Transactional"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nonTransactional");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "NonTransactional"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
