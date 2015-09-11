/**
 * CancelOrRefundResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.adyen.services.payment;

public class CancelOrRefundResponse  implements java.io.Serializable {
    private com.adyen.services.payment.ModificationResult cancelOrRefundResult;

    public CancelOrRefundResponse() {
    }

    public CancelOrRefundResponse(
           com.adyen.services.payment.ModificationResult cancelOrRefundResult) {
           this.cancelOrRefundResult = cancelOrRefundResult;
    }


    /**
     * Gets the cancelOrRefundResult value for this CancelOrRefundResponse.
     * 
     * @return cancelOrRefundResult
     */
    public com.adyen.services.payment.ModificationResult getCancelOrRefundResult() {
        return cancelOrRefundResult;
    }


    /**
     * Sets the cancelOrRefundResult value for this CancelOrRefundResponse.
     * 
     * @param cancelOrRefundResult
     */
    public void setCancelOrRefundResult(com.adyen.services.payment.ModificationResult cancelOrRefundResult) {
        this.cancelOrRefundResult = cancelOrRefundResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CancelOrRefundResponse)) return false;
        CancelOrRefundResponse other = (CancelOrRefundResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cancelOrRefundResult==null && other.getCancelOrRefundResult()==null) || 
             (this.cancelOrRefundResult!=null &&
              this.cancelOrRefundResult.equals(other.getCancelOrRefundResult())));
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
        if (getCancelOrRefundResult() != null) {
            _hashCode += getCancelOrRefundResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CancelOrRefundResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://payment.services.adyen.com", ">cancelOrRefundResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cancelOrRefundResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "cancelOrRefundResult"));
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
