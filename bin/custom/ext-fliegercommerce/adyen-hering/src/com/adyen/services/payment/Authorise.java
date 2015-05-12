/**
 * Authorise.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.adyen.services.payment;

public class Authorise  implements java.io.Serializable {
    private com.adyen.services.payment.PaymentRequest paymentRequest;

    public Authorise() {
    }

    public Authorise(
           com.adyen.services.payment.PaymentRequest paymentRequest) {
           this.paymentRequest = paymentRequest;
    }


    /**
     * Gets the paymentRequest value for this Authorise.
     * 
     * @return paymentRequest
     */
    public com.adyen.services.payment.PaymentRequest getPaymentRequest() {
        return paymentRequest;
    }


    /**
     * Sets the paymentRequest value for this Authorise.
     * 
     * @param paymentRequest
     */
    public void setPaymentRequest(com.adyen.services.payment.PaymentRequest paymentRequest) {
        this.paymentRequest = paymentRequest;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Authorise)) return false;
        Authorise other = (Authorise) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.paymentRequest==null && other.getPaymentRequest()==null) || 
             (this.paymentRequest!=null &&
              this.paymentRequest.equals(other.getPaymentRequest())));
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
        if (getPaymentRequest() != null) {
            _hashCode += getPaymentRequest().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Authorise.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://payment.services.adyen.com", ">authorise"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paymentRequest");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "paymentRequest"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://payment.services.adyen.com", "PaymentRequest"));
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
