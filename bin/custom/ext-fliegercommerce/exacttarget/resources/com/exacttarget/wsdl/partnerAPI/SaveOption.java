/**
 * SaveOption.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class SaveOption  implements java.io.Serializable {
    private java.lang.String propertyName;

    private com.exacttarget.wsdl.partnerAPI.SaveAction saveAction;

    public SaveOption() {
    }

    public SaveOption(
           java.lang.String propertyName,
           com.exacttarget.wsdl.partnerAPI.SaveAction saveAction) {
           this.propertyName = propertyName;
           this.saveAction = saveAction;
    }


    /**
     * Gets the propertyName value for this SaveOption.
     * 
     * @return propertyName
     */
    public java.lang.String getPropertyName() {
        return propertyName;
    }


    /**
     * Sets the propertyName value for this SaveOption.
     * 
     * @param propertyName
     */
    public void setPropertyName(java.lang.String propertyName) {
        this.propertyName = propertyName;
    }


    /**
     * Gets the saveAction value for this SaveOption.
     * 
     * @return saveAction
     */
    public com.exacttarget.wsdl.partnerAPI.SaveAction getSaveAction() {
        return saveAction;
    }


    /**
     * Sets the saveAction value for this SaveOption.
     * 
     * @param saveAction
     */
    public void setSaveAction(com.exacttarget.wsdl.partnerAPI.SaveAction saveAction) {
        this.saveAction = saveAction;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SaveOption)) return false;
        SaveOption other = (SaveOption) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.propertyName==null && other.getPropertyName()==null) || 
             (this.propertyName!=null &&
              this.propertyName.equals(other.getPropertyName()))) &&
            ((this.saveAction==null && other.getSaveAction()==null) || 
             (this.saveAction!=null &&
              this.saveAction.equals(other.getSaveAction())));
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
        if (getPropertyName() != null) {
            _hashCode += getPropertyName().hashCode();
        }
        if (getSaveAction() != null) {
            _hashCode += getSaveAction().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SaveOption.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SaveOption"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("propertyName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PropertyName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("saveAction");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SaveAction"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SaveAction"));
        elemField.setNillable(false);
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
