/**
 * Recurring.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.adyen.services.payment;

public class Recurring  implements java.io.Serializable {
    private java.lang.String contract;

    private java.lang.String recurringDetailName;

    public Recurring() {
    }

    public Recurring(
           java.lang.String contract,
           java.lang.String recurringDetailName) {
           this.contract = contract;
           this.recurringDetailName = recurringDetailName;
    }


    /**
     * Gets the contract value for this Recurring.
     * 
     * @return contract
     */
    public java.lang.String getContract() {
        return contract;
    }


    /**
     * Sets the contract value for this Recurring.
     * 
     * @param contract
     */
    public void setContract(java.lang.String contract) {
        this.contract = contract;
    }


    /**
     * Gets the recurringDetailName value for this Recurring.
     * 
     * @return recurringDetailName
     */
    public java.lang.String getRecurringDetailName() {
        return recurringDetailName;
    }


    /**
     * Sets the recurringDetailName value for this Recurring.
     * 
     * @param recurringDetailName
     */
    public void setRecurringDetailName(java.lang.String recurringDetailName) {
        this.recurringDetailName = recurringDetailName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Recurring)) return false;
        Recurring other = (Recurring) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.contract==null && other.getContract()==null) || 
             (this.contract!=null &&
              this.contract.equals(other.getContract()))) &&
            ((this.recurringDetailName==null && other.getRecurringDetailName()==null) || 
             (this.recurringDetailName!=null &&
              this.recurringDetailName.equals(other.getRecurringDetailName())));
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
        if (getContract() != null) {
            _hashCode += getContract().hashCode();
        }
        if (getRecurringDetailName() != null) {
            _hashCode += getRecurringDetailName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Recurring.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://payment.services.adyen.com", "Recurring"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contract");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "contract"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recurringDetailName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "recurringDetailName"));
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
