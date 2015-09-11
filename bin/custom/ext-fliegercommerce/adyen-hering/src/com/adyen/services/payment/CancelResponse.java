/**
 * CancelResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.adyen.services.payment;

public class CancelResponse  implements java.io.Serializable {
    private com.adyen.services.payment.ModificationResult cancelResult;

    public CancelResponse() {
    }

    public CancelResponse(
           com.adyen.services.payment.ModificationResult cancelResult) {
           this.cancelResult = cancelResult;
    }


    /**
     * Gets the cancelResult value for this CancelResponse.
     * 
     * @return cancelResult
     */
    public com.adyen.services.payment.ModificationResult getCancelResult() {
        return cancelResult;
    }


    /**
     * Sets the cancelResult value for this CancelResponse.
     * 
     * @param cancelResult
     */
    public void setCancelResult(com.adyen.services.payment.ModificationResult cancelResult) {
        this.cancelResult = cancelResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CancelResponse)) return false;
        CancelResponse other = (CancelResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cancelResult==null && other.getCancelResult()==null) || 
             (this.cancelResult!=null &&
              this.cancelResult.equals(other.getCancelResult())));
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
        if (getCancelResult() != null) {
            _hashCode += getCancelResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CancelResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://payment.services.adyen.com", ">cancelResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cancelResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "cancelResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://payment.services.adyen.com", "ModificationResult"));
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
