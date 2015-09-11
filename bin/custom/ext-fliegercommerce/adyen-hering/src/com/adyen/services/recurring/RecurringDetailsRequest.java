/**
 * RecurringDetailsRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.adyen.services.recurring;

public class RecurringDetailsRequest  implements java.io.Serializable {
    private java.lang.String customReference;

    private java.lang.String merchantAccount;

    private com.adyen.services.payment.Recurring recurring;

    private java.lang.String shopperReference;

    public RecurringDetailsRequest() {
    }

    public RecurringDetailsRequest(
           java.lang.String customReference,
           java.lang.String merchantAccount,
           com.adyen.services.payment.Recurring recurring,
           java.lang.String shopperReference) {
           this.customReference = customReference;
           this.merchantAccount = merchantAccount;
           this.recurring = recurring;
           this.shopperReference = shopperReference;
    }


    /**
     * Gets the customReference value for this RecurringDetailsRequest.
     * 
     * @return customReference
     */
    public java.lang.String getCustomReference() {
        return customReference;
    }


    /**
     * Sets the customReference value for this RecurringDetailsRequest.
     * 
     * @param customReference
     */
    public void setCustomReference(java.lang.String customReference) {
        this.customReference = customReference;
    }


    /**
     * Gets the merchantAccount value for this RecurringDetailsRequest.
     * 
     * @return merchantAccount
     */
    public java.lang.String getMerchantAccount() {
        return merchantAccount;
    }


    /**
     * Sets the merchantAccount value for this RecurringDetailsRequest.
     * 
     * @param merchantAccount
     */
    public void setMerchantAccount(java.lang.String merchantAccount) {
        this.merchantAccount = merchantAccount;
    }


    /**
     * Gets the recurring value for this RecurringDetailsRequest.
     * 
     * @return recurring
     */
    public com.adyen.services.payment.Recurring getRecurring() {
        return recurring;
    }


    /**
     * Sets the recurring value for this RecurringDetailsRequest.
     * 
     * @param recurring
     */
    public void setRecurring(com.adyen.services.payment.Recurring recurring) {
        this.recurring = recurring;
    }


    /**
     * Gets the shopperReference value for this RecurringDetailsRequest.
     * 
     * @return shopperReference
     */
    public java.lang.String getShopperReference() {
        return shopperReference;
    }


    /**
     * Sets the shopperReference value for this RecurringDetailsRequest.
     * 
     * @param shopperReference
     */
    public void setShopperReference(java.lang.String shopperReference) {
        this.shopperReference = shopperReference;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RecurringDetailsRequest)) return false;
        RecurringDetailsRequest other = (RecurringDetailsRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.customReference==null && other.getCustomReference()==null) || 
             (this.customReference!=null &&
              this.customReference.equals(other.getCustomReference()))) &&
            ((this.merchantAccount==null && other.getMerchantAccount()==null) || 
             (this.merchantAccount!=null &&
              this.merchantAccount.equals(other.getMerchantAccount()))) &&
            ((this.recurring==null && other.getRecurring()==null) || 
             (this.recurring!=null &&
              this.recurring.equals(other.getRecurring()))) &&
            ((this.shopperReference==null && other.getShopperReference()==null) || 
             (this.shopperReference!=null &&
              this.shopperReference.equals(other.getShopperReference())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getCustomReference() != null) {
            _hashCode += getCustomReference().hashCode();
        }
        if (getMerchantAccount() != null) {
            _hashCode += getMerchantAccount().hashCode();
        }
        if (getRecurring() != null) {
            _hashCode += getRecurring().hashCode();
        }
        if (getShopperReference() != null) {
            _hashCode += getShopperReference().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RecurringDetailsRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "RecurringDetailsRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customReference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "customReference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("merchantAccount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "merchantAccount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recurring");
        elemField.setXmlName(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "recurring"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://payment.services.adyen.com", "Recurring"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shopperReference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "shopperReference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
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
