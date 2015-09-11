/**
 * FraudResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.adyen.services.payment;

public class FraudResult  implements java.io.Serializable {
    private java.lang.Integer accountScore;

    private com.adyen.services.payment.FraudCheckResult[] results;

    public FraudResult() {
    }

    public FraudResult(
           java.lang.Integer accountScore,
           com.adyen.services.payment.FraudCheckResult[] results) {
           this.accountScore = accountScore;
           this.results = results;
    }


    /**
     * Gets the accountScore value for this FraudResult.
     * 
     * @return accountScore
     */
    public java.lang.Integer getAccountScore() {
        return accountScore;
    }


    /**
     * Sets the accountScore value for this FraudResult.
     * 
     * @param accountScore
     */
    public void setAccountScore(java.lang.Integer accountScore) {
        this.accountScore = accountScore;
    }


    /**
     * Gets the results value for this FraudResult.
     * 
     * @return results
     */
    public com.adyen.services.payment.FraudCheckResult[] getResults() {
        return results;
    }


    /**
     * Sets the results value for this FraudResult.
     * 
     * @param results
     */
    public void setResults(com.adyen.services.payment.FraudCheckResult[] results) {
        this.results = results;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FraudResult)) return false;
        FraudResult other = (FraudResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.accountScore==null && other.getAccountScore()==null) || 
             (this.accountScore!=null &&
              this.accountScore.equals(other.getAccountScore()))) &&
            ((this.results==null && other.getResults()==null) || 
             (this.results!=null &&
              java.util.Arrays.equals(this.results, other.getResults())));
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
        if (getAccountScore() != null) {
            _hashCode += getAccountScore().hashCode();
        }
        if (getResults() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getResults());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getResults(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FraudResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://payment.services.adyen.com", "FraudResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountScore");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "accountScore"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("results");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "results"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://payment.services.adyen.com", "FraudCheckResult"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "FraudCheckResult"));
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
