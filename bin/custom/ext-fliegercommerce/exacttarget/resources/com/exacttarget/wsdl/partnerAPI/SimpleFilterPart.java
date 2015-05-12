/**
 * SimpleFilterPart.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class SimpleFilterPart  extends com.exacttarget.wsdl.partnerAPI.FilterPart  implements java.io.Serializable {
    private java.lang.String property;

    private com.exacttarget.wsdl.partnerAPI.SimpleOperators simpleOperator;

    private java.lang.String[] value;

    private java.util.Calendar[] dateValue;

    public SimpleFilterPart() {
    }

    public SimpleFilterPart(
           java.lang.String property,
           com.exacttarget.wsdl.partnerAPI.SimpleOperators simpleOperator,
           java.lang.String[] value,
           java.util.Calendar[] dateValue) {
        this.property = property;
        this.simpleOperator = simpleOperator;
        this.value = value;
        this.dateValue = dateValue;
    }


    /**
     * Gets the property value for this SimpleFilterPart.
     * 
     * @return property
     */
    public java.lang.String getProperty() {
        return property;
    }


    /**
     * Sets the property value for this SimpleFilterPart.
     * 
     * @param property
     */
    public void setProperty(java.lang.String property) {
        this.property = property;
    }


    /**
     * Gets the simpleOperator value for this SimpleFilterPart.
     * 
     * @return simpleOperator
     */
    public com.exacttarget.wsdl.partnerAPI.SimpleOperators getSimpleOperator() {
        return simpleOperator;
    }


    /**
     * Sets the simpleOperator value for this SimpleFilterPart.
     * 
     * @param simpleOperator
     */
    public void setSimpleOperator(com.exacttarget.wsdl.partnerAPI.SimpleOperators simpleOperator) {
        this.simpleOperator = simpleOperator;
    }


    /**
     * Gets the value value for this SimpleFilterPart.
     * 
     * @return value
     */
    public java.lang.String[] getValue() {
        return value;
    }


    /**
     * Sets the value value for this SimpleFilterPart.
     * 
     * @param value
     */
    public void setValue(java.lang.String[] value) {
        this.value = value;
    }

    public java.lang.String getValue(int i) {
        return this.value[i];
    }

    public void setValue(int i, java.lang.String _value) {
        this.value[i] = _value;
    }


    /**
     * Gets the dateValue value for this SimpleFilterPart.
     * 
     * @return dateValue
     */
    public java.util.Calendar[] getDateValue() {
        return dateValue;
    }


    /**
     * Sets the dateValue value for this SimpleFilterPart.
     * 
     * @param dateValue
     */
    public void setDateValue(java.util.Calendar[] dateValue) {
        this.dateValue = dateValue;
    }

    public java.util.Calendar getDateValue(int i) {
        return this.dateValue[i];
    }

    public void setDateValue(int i, java.util.Calendar _value) {
        this.dateValue[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SimpleFilterPart)) return false;
        SimpleFilterPart other = (SimpleFilterPart) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.property==null && other.getProperty()==null) || 
             (this.property!=null &&
              this.property.equals(other.getProperty()))) &&
            ((this.simpleOperator==null && other.getSimpleOperator()==null) || 
             (this.simpleOperator!=null &&
              this.simpleOperator.equals(other.getSimpleOperator()))) &&
            ((this.value==null && other.getValue()==null) || 
             (this.value!=null &&
              java.util.Arrays.equals(this.value, other.getValue()))) &&
            ((this.dateValue==null && other.getDateValue()==null) || 
             (this.dateValue!=null &&
              java.util.Arrays.equals(this.dateValue, other.getDateValue())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getProperty() != null) {
            _hashCode += getProperty().hashCode();
        }
        if (getSimpleOperator() != null) {
            _hashCode += getSimpleOperator().hashCode();
        }
        if (getValue() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getValue());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getValue(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDateValue() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDateValue());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDateValue(), i);
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
        new org.apache.axis.description.TypeDesc(SimpleFilterPart.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SimpleFilterPart"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("property");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Property"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("simpleOperator");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SimpleOperator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SimpleOperators"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("value");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Value"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dateValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DateValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
