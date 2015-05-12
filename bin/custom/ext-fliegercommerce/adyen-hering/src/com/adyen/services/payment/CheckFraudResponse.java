/**
 * CheckFraudResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.adyen.services.payment;

public class CheckFraudResponse  implements java.io.Serializable {
    private com.adyen.services.payment.PaymentResult paymentResult;

    public CheckFraudResponse() {
    }

    public CheckFraudResponse(
           com.adyen.services.payment.PaymentResult paymentResult) {
           this.paymentResult = paymentResult;
    }


    /**
     * Gets the paymentResult value for this CheckFraudResponse.
     * 
     * @return paymentResult
     */
    public com.adyen.services.payment.PaymentResult getPaymentResult() {
        return paymentResult;
    }


    /**
     * Sets the paymentResult value for this CheckFraudResponse.
     * 
     * @param paymentResult
     */
    public void setPaymentResult(com.adyen.services.payment.PaymentResult paymentResult) {
        this.paymentResult = paymentResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CheckFraudResponse)) return false;
        CheckFraudResponse other = (CheckFraudResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.paymentResult==null && other.getPaymentResult()==null) || 
             (this.paymentResult!=null &&
              this.paymentResult.equals(other.getPaymentResult())));
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
        if (getPaymentResult() != null) {
            _hashCode += getPaymentResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CheckFraudResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://payment.services.adyen.com", ">checkFraudResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paymentResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "paymentResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://payment.services.adyen.com", "PaymentResult"));
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
