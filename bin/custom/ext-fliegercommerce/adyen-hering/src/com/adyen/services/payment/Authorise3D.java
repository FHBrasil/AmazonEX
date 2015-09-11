/**
 * Authorise3D.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.adyen.services.payment;

public class Authorise3D  implements java.io.Serializable {
    private com.adyen.services.payment.PaymentRequest3D paymentRequest3D;

    public Authorise3D() {
    }

    public Authorise3D(
           com.adyen.services.payment.PaymentRequest3D paymentRequest3D) {
           this.paymentRequest3D = paymentRequest3D;
    }


    /**
     * Gets the paymentRequest3D value for this Authorise3D.
     * 
     * @return paymentRequest3D
     */
    public com.adyen.services.payment.PaymentRequest3D getPaymentRequest3D() {
        return paymentRequest3D;
    }


    /**
     * Sets the paymentRequest3D value for this Authorise3D.
     * 
     * @param paymentRequest3D
     */
    public void setPaymentRequest3D(com.adyen.services.payment.PaymentRequest3D paymentRequest3D) {
        this.paymentRequest3D = paymentRequest3D;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Authorise3D)) return false;
        Authorise3D other = (Authorise3D) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.paymentRequest3D==null && other.getPaymentRequest3D()==null) || 
             (this.paymentRequest3D!=null &&
              this.paymentRequest3D.equals(other.getPaymentRequest3D())));
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
        if (getPaymentRequest3D() != null) {
            _hashCode += getPaymentRequest3D().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Authorise3D.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://payment.services.adyen.com", ">authorise3d"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paymentRequest3D");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "paymentRequest3d"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://payment.services.adyen.com", "PaymentRequest3d"));
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
