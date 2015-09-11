/**
 * FraudCheckResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.adyen.services.payment;

public class FraudCheckResult  implements java.io.Serializable {
    private java.lang.Integer accountScore;

    private java.lang.Integer checkId;

    private java.lang.String name;

    public FraudCheckResult() {
    }

    public FraudCheckResult(
           java.lang.Integer accountScore,
           java.lang.Integer checkId,
           java.lang.String name) {
           this.accountScore = accountScore;
           this.checkId = checkId;
           this.name = name;
    }


    /**
     * Gets the accountScore value for this FraudCheckResult.
     * 
     * @return accountScore
     */
    public java.lang.Integer getAccountScore() {
        return accountScore;
    }


    /**
     * Sets the accountScore value for this FraudCheckResult.
     * 
     * @param accountScore
     */
    public void setAccountScore(java.lang.Integer accountScore) {
        this.accountScore = accountScore;
    }


    /**
     * Gets the checkId value for this FraudCheckResult.
     * 
     * @return checkId
     */
    public java.lang.Integer getCheckId() {
        return checkId;
    }


    /**
     * Sets the checkId value for this FraudCheckResult.
     * 
     * @param checkId
     */
    public void setCheckId(java.lang.Integer checkId) {
        this.checkId = checkId;
    }


    /**
     * Gets the name value for this FraudCheckResult.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this FraudCheckResult.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FraudCheckResult)) return false;
        FraudCheckResult other = (FraudCheckResult) obj;
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
            ((this.checkId==null && other.getCheckId()==null) || 
             (this.checkId!=null &&
              this.checkId.equals(other.getCheckId()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName())));
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
        if (getCheckId() != null) {
            _hashCode += getCheckId().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FraudCheckResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://payment.services.adyen.com", "FraudCheckResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountScore");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "accountScore"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("checkId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "checkId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "name"));
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
