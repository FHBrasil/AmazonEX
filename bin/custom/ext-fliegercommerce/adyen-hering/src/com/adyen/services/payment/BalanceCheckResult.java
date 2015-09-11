/**
 * BalanceCheckResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.adyen.services.payment;

public class BalanceCheckResult  implements java.io.Serializable {
    private com.adyen.services.payment.AnyType2AnyTypeMapEntry[] additionalData;

    private com.adyen.services.common.Amount currentBalance;

    private java.lang.String pspReference;

    private com.adyen.services.payment.BalanceCheckResponseCode responseCode;

    public BalanceCheckResult() {
    }

    public BalanceCheckResult(
           com.adyen.services.payment.AnyType2AnyTypeMapEntry[] additionalData,
           com.adyen.services.common.Amount currentBalance,
           java.lang.String pspReference,
           com.adyen.services.payment.BalanceCheckResponseCode responseCode) {
           this.additionalData = additionalData;
           this.currentBalance = currentBalance;
           this.pspReference = pspReference;
           this.responseCode = responseCode;
    }


    /**
     * Gets the additionalData value for this BalanceCheckResult.
     * 
     * @return additionalData
     */
    public com.adyen.services.payment.AnyType2AnyTypeMapEntry[] getAdditionalData() {
        return additionalData;
    }


    /**
     * Sets the additionalData value for this BalanceCheckResult.
     * 
     * @param additionalData
     */
    public void setAdditionalData(com.adyen.services.payment.AnyType2AnyTypeMapEntry[] additionalData) {
        this.additionalData = additionalData;
    }


    /**
     * Gets the currentBalance value for this BalanceCheckResult.
     * 
     * @return currentBalance
     */
    public com.adyen.services.common.Amount getCurrentBalance() {
        return currentBalance;
    }


    /**
     * Sets the currentBalance value for this BalanceCheckResult.
     * 
     * @param currentBalance
     */
    public void setCurrentBalance(com.adyen.services.common.Amount currentBalance) {
        this.currentBalance = currentBalance;
    }


    /**
     * Gets the pspReference value for this BalanceCheckResult.
     * 
     * @return pspReference
     */
    public java.lang.String getPspReference() {
        return pspReference;
    }


    /**
     * Sets the pspReference value for this BalanceCheckResult.
     * 
     * @param pspReference
     */
    public void setPspReference(java.lang.String pspReference) {
        this.pspReference = pspReference;
    }


    /**
     * Gets the responseCode value for this BalanceCheckResult.
     * 
     * @return responseCode
     */
    public com.adyen.services.payment.BalanceCheckResponseCode getResponseCode() {
        return responseCode;
    }


    /**
     * Sets the responseCode value for this BalanceCheckResult.
     * 
     * @param responseCode
     */
    public void setResponseCode(com.adyen.services.payment.BalanceCheckResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BalanceCheckResult)) return false;
        BalanceCheckResult other = (BalanceCheckResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.additionalData==null && other.getAdditionalData()==null) || 
             (this.additionalData!=null &&
              java.util.Arrays.equals(this.additionalData, other.getAdditionalData()))) &&
            ((this.currentBalance==null && other.getCurrentBalance()==null) || 
             (this.currentBalance!=null &&
              this.currentBalance.equals(other.getCurrentBalance()))) &&
            ((this.pspReference==null && other.getPspReference()==null) || 
             (this.pspReference!=null &&
              this.pspReference.equals(other.getPspReference()))) &&
            ((this.responseCode==null && other.getResponseCode()==null) || 
             (this.responseCode!=null &&
              this.responseCode.equals(other.getResponseCode())));
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
        if (getAdditionalData() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAdditionalData());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAdditionalData(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCurrentBalance() != null) {
            _hashCode += getCurrentBalance().hashCode();
        }
        if (getPspReference() != null) {
            _hashCode += getPspReference().hashCode();
        }
        if (getResponseCode() != null) {
            _hashCode += getResponseCode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BalanceCheckResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://payment.services.adyen.com", "BalanceCheckResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("additionalData");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "additionalData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://payment.services.adyen.com", ">anyType2anyTypeMap>entry"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "entry"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("currentBalance");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "currentBalance"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://common.services.adyen.com", "Amount"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pspReference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "pspReference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("responseCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "responseCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://payment.services.adyen.com", "BalanceCheckResponseCode"));
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
