/**
 * StoreTokenResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.adyen.services.recurring;

public class StoreTokenResult  implements java.io.Serializable {
    private java.lang.String rechargeReference;

    private java.lang.String recurringDetailReference;

    private java.lang.String result;

    public StoreTokenResult() {
    }

    public StoreTokenResult(
           java.lang.String rechargeReference,
           java.lang.String recurringDetailReference,
           java.lang.String result) {
           this.rechargeReference = rechargeReference;
           this.recurringDetailReference = recurringDetailReference;
           this.result = result;
    }


    /**
     * Gets the rechargeReference value for this StoreTokenResult.
     * 
     * @return rechargeReference
     */
    public java.lang.String getRechargeReference() {
        return rechargeReference;
    }


    /**
     * Sets the rechargeReference value for this StoreTokenResult.
     * 
     * @param rechargeReference
     */
    public void setRechargeReference(java.lang.String rechargeReference) {
        this.rechargeReference = rechargeReference;
    }


    /**
     * Gets the recurringDetailReference value for this StoreTokenResult.
     * 
     * @return recurringDetailReference
     */
    public java.lang.String getRecurringDetailReference() {
        return recurringDetailReference;
    }


    /**
     * Sets the recurringDetailReference value for this StoreTokenResult.
     * 
     * @param recurringDetailReference
     */
    public void setRecurringDetailReference(java.lang.String recurringDetailReference) {
        this.recurringDetailReference = recurringDetailReference;
    }


    /**
     * Gets the result value for this StoreTokenResult.
     * 
     * @return result
     */
    public java.lang.String getResult() {
        return result;
    }


    /**
     * Sets the result value for this StoreTokenResult.
     * 
     * @param result
     */
    public void setResult(java.lang.String result) {
        this.result = result;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof StoreTokenResult)) return false;
        StoreTokenResult other = (StoreTokenResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.rechargeReference==null && other.getRechargeReference()==null) || 
             (this.rechargeReference!=null &&
              this.rechargeReference.equals(other.getRechargeReference()))) &&
            ((this.recurringDetailReference==null && other.getRecurringDetailReference()==null) || 
             (this.recurringDetailReference!=null &&
              this.recurringDetailReference.equals(other.getRecurringDetailReference()))) &&
            ((this.result==null && other.getResult()==null) || 
             (this.result!=null &&
              this.result.equals(other.getResult())));
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
        if (getRechargeReference() != null) {
            _hashCode += getRechargeReference().hashCode();
        }
        if (getRecurringDetailReference() != null) {
            _hashCode += getRecurringDetailReference().hashCode();
        }
        if (getResult() != null) {
            _hashCode += getResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(StoreTokenResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "StoreTokenResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rechargeReference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "rechargeReference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recurringDetailReference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "recurringDetailReference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("result");
        elemField.setXmlName(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "result"));
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
