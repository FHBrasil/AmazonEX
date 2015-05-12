/**
 * Capture.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.adyen.services.payment;

public class Capture  implements java.io.Serializable {
    private com.adyen.services.payment.ModificationRequest modificationRequest;

    public Capture() {
    }

    public Capture(
           com.adyen.services.payment.ModificationRequest modificationRequest) {
           this.modificationRequest = modificationRequest;
    }


    /**
     * Gets the modificationRequest value for this Capture.
     * 
     * @return modificationRequest
     */
    public com.adyen.services.payment.ModificationRequest getModificationRequest() {
        return modificationRequest;
    }


    /**
     * Sets the modificationRequest value for this Capture.
     * 
     * @param modificationRequest
     */
    public void setModificationRequest(com.adyen.services.payment.ModificationRequest modificationRequest) {
        this.modificationRequest = modificationRequest;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Capture)) return false;
        Capture other = (Capture) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.modificationRequest==null && other.getModificationRequest()==null) || 
             (this.modificationRequest!=null &&
              this.modificationRequest.equals(other.getModificationRequest())));
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
        if (getModificationRequest() != null) {
            _hashCode += getModificationRequest().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Capture.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://payment.services.adyen.com", ">capture"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modificationRequest");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "modificationRequest"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://payment.services.adyen.com", "ModificationRequest"));
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
