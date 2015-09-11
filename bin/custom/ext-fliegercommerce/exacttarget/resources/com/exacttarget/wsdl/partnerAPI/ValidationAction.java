/**
 * ValidationAction.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class ValidationAction  implements java.io.Serializable {
    private java.lang.String validationType;

    private com.exacttarget.wsdl.partnerAPI.APIProperty[] validationOptions;

    public ValidationAction() {
    }

    public ValidationAction(
           java.lang.String validationType,
           com.exacttarget.wsdl.partnerAPI.APIProperty[] validationOptions) {
           this.validationType = validationType;
           this.validationOptions = validationOptions;
    }


    /**
     * Gets the validationType value for this ValidationAction.
     * 
     * @return validationType
     */
    public java.lang.String getValidationType() {
        return validationType;
    }


    /**
     * Sets the validationType value for this ValidationAction.
     * 
     * @param validationType
     */
    public void setValidationType(java.lang.String validationType) {
        this.validationType = validationType;
    }


    /**
     * Gets the validationOptions value for this ValidationAction.
     * 
     * @return validationOptions
     */
    public com.exacttarget.wsdl.partnerAPI.APIProperty[] getValidationOptions() {
        return validationOptions;
    }


    /**
     * Sets the validationOptions value for this ValidationAction.
     * 
     * @param validationOptions
     */
    public void setValidationOptions(com.exacttarget.wsdl.partnerAPI.APIProperty[] validationOptions) {
        this.validationOptions = validationOptions;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ValidationAction)) return false;
        ValidationAction other = (ValidationAction) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.validationType==null && other.getValidationType()==null) || 
             (this.validationType!=null &&
              this.validationType.equals(other.getValidationType()))) &&
            ((this.validationOptions==null && other.getValidationOptions()==null) || 
             (this.validationOptions!=null &&
              java.util.Arrays.equals(this.validationOptions, other.getValidationOptions())));
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
        if (getValidationType() != null) {
            _hashCode += getValidationType().hashCode();
        }
        if (getValidationOptions() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getValidationOptions());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getValidationOptions(), i);
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
        new org.apache.axis.description.TypeDesc(ValidationAction.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ValidationAction"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("validationType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ValidationType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("validationOptions");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ValidationOptions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "APIProperty"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ValidationOption"));
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
