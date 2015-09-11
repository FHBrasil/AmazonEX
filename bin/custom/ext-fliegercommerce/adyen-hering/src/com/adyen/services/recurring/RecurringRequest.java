/**
 * RecurringRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.adyen.services.recurring;

public class RecurringRequest  implements java.io.Serializable {
    private com.adyen.services.common.Amount amount;

    private java.lang.String merchantAccount;

    private java.lang.String originalReference;

    private java.lang.String recurringReference;

    private java.lang.String reference;

    private java.lang.String shopperEmail;

    private java.lang.String shopperReference;

    public RecurringRequest() {
    }

    public RecurringRequest(
           com.adyen.services.common.Amount amount,
           java.lang.String merchantAccount,
           java.lang.String originalReference,
           java.lang.String recurringReference,
           java.lang.String reference,
           java.lang.String shopperEmail,
           java.lang.String shopperReference) {
           this.amount = amount;
           this.merchantAccount = merchantAccount;
           this.originalReference = originalReference;
           this.recurringReference = recurringReference;
           this.reference = reference;
           this.shopperEmail = shopperEmail;
           this.shopperReference = shopperReference;
    }


    /**
     * Gets the amount value for this RecurringRequest.
     * 
     * @return amount
     */
    public com.adyen.services.common.Amount getAmount() {
        return amount;
    }


    /**
     * Sets the amount value for this RecurringRequest.
     * 
     * @param amount
     */
    public void setAmount(com.adyen.services.common.Amount amount) {
        this.amount = amount;
    }


    /**
     * Gets the merchantAccount value for this RecurringRequest.
     * 
     * @return merchantAccount
     */
    public java.lang.String getMerchantAccount() {
        return merchantAccount;
    }


    /**
     * Sets the merchantAccount value for this RecurringRequest.
     * 
     * @param merchantAccount
     */
    public void setMerchantAccount(java.lang.String merchantAccount) {
        this.merchantAccount = merchantAccount;
    }


    /**
     * Gets the originalReference value for this RecurringRequest.
     * 
     * @return originalReference
     */
    public java.lang.String getOriginalReference() {
        return originalReference;
    }


    /**
     * Sets the originalReference value for this RecurringRequest.
     * 
     * @param originalReference
     */
    public void setOriginalReference(java.lang.String originalReference) {
        this.originalReference = originalReference;
    }


    /**
     * Gets the recurringReference value for this RecurringRequest.
     * 
     * @return recurringReference
     */
    public java.lang.String getRecurringReference() {
        return recurringReference;
    }


    /**
     * Sets the recurringReference value for this RecurringRequest.
     * 
     * @param recurringReference
     */
    public void setRecurringReference(java.lang.String recurringReference) {
        this.recurringReference = recurringReference;
    }


    /**
     * Gets the reference value for this RecurringRequest.
     * 
     * @return reference
     */
    public java.lang.String getReference() {
        return reference;
    }


    /**
     * Sets the reference value for this RecurringRequest.
     * 
     * @param reference
     */
    public void setReference(java.lang.String reference) {
        this.reference = reference;
    }


    /**
     * Gets the shopperEmail value for this RecurringRequest.
     * 
     * @return shopperEmail
     */
    public java.lang.String getShopperEmail() {
        return shopperEmail;
    }


    /**
     * Sets the shopperEmail value for this RecurringRequest.
     * 
     * @param shopperEmail
     */
    public void setShopperEmail(java.lang.String shopperEmail) {
        this.shopperEmail = shopperEmail;
    }


    /**
     * Gets the shopperReference value for this RecurringRequest.
     * 
     * @return shopperReference
     */
    public java.lang.String getShopperReference() {
        return shopperReference;
    }


    /**
     * Sets the shopperReference value for this RecurringRequest.
     * 
     * @param shopperReference
     */
    public void setShopperReference(java.lang.String shopperReference) {
        this.shopperReference = shopperReference;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RecurringRequest)) return false;
        RecurringRequest other = (RecurringRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.amount==null && other.getAmount()==null) || 
             (this.amount!=null &&
              this.amount.equals(other.getAmount()))) &&
            ((this.merchantAccount==null && other.getMerchantAccount()==null) || 
             (this.merchantAccount!=null &&
              this.merchantAccount.equals(other.getMerchantAccount()))) &&
            ((this.originalReference==null && other.getOriginalReference()==null) || 
             (this.originalReference!=null &&
              this.originalReference.equals(other.getOriginalReference()))) &&
            ((this.recurringReference==null && other.getRecurringReference()==null) || 
             (this.recurringReference!=null &&
              this.recurringReference.equals(other.getRecurringReference()))) &&
            ((this.reference==null && other.getReference()==null) || 
             (this.reference!=null &&
              this.reference.equals(other.getReference()))) &&
            ((this.shopperEmail==null && other.getShopperEmail()==null) || 
             (this.shopperEmail!=null &&
              this.shopperEmail.equals(other.getShopperEmail()))) &&
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
        if (getAmount() != null) {
            _hashCode += getAmount().hashCode();
        }
        if (getMerchantAccount() != null) {
            _hashCode += getMerchantAccount().hashCode();
        }
        if (getOriginalReference() != null) {
            _hashCode += getOriginalReference().hashCode();
        }
        if (getRecurringReference() != null) {
            _hashCode += getRecurringReference().hashCode();
        }
        if (getReference() != null) {
            _hashCode += getReference().hashCode();
        }
        if (getShopperEmail() != null) {
            _hashCode += getShopperEmail().hashCode();
        }
        if (getShopperReference() != null) {
            _hashCode += getShopperReference().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RecurringRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "RecurringRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("amount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://common.services.adyen.com", "Amount"));
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
        elemField.setFieldName("originalReference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "originalReference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recurringReference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "recurringReference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "reference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shopperEmail");
        elemField.setXmlName(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "shopperEmail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
