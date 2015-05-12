/**
 * AuthoriseReferralResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.adyen.services.payment;

public class AuthoriseReferralResponse  implements java.io.Serializable {
    private com.adyen.services.payment.ModificationResult authoriseReferralResult;

    public AuthoriseReferralResponse() {
    }

    public AuthoriseReferralResponse(
           com.adyen.services.payment.ModificationResult authoriseReferralResult) {
           this.authoriseReferralResult = authoriseReferralResult;
    }


    /**
     * Gets the authoriseReferralResult value for this AuthoriseReferralResponse.
     * 
     * @return authoriseReferralResult
     */
    public com.adyen.services.payment.ModificationResult getAuthoriseReferralResult() {
        return authoriseReferralResult;
    }


    /**
     * Sets the authoriseReferralResult value for this AuthoriseReferralResponse.
     * 
     * @param authoriseReferralResult
     */
    public void setAuthoriseReferralResult(com.adyen.services.payment.ModificationResult authoriseReferralResult) {
        this.authoriseReferralResult = authoriseReferralResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AuthoriseReferralResponse)) return false;
        AuthoriseReferralResponse other = (AuthoriseReferralResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.authoriseReferralResult==null && other.getAuthoriseReferralResult()==null) || 
             (this.authoriseReferralResult!=null &&
              this.authoriseReferralResult.equals(other.getAuthoriseReferralResult())));
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
        if (getAuthoriseReferralResult() != null) {
            _hashCode += getAuthoriseReferralResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AuthoriseReferralResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://payment.services.adyen.com", ">authoriseReferralResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authoriseReferralResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "authoriseReferralResult"));
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
