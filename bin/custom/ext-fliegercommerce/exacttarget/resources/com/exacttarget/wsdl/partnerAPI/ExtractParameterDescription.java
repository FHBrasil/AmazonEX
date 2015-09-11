/**
 * ExtractParameterDescription.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class ExtractParameterDescription  extends com.exacttarget.wsdl.partnerAPI.ParameterDescription  implements java.io.Serializable {
    private java.lang.String name;

    private com.exacttarget.wsdl.partnerAPI.ExtractParameterDataType dataType;

    private java.lang.String defaultValue;

    private boolean isOptional;

    private java.lang.String dropDownList;

    public ExtractParameterDescription() {
    }

    public ExtractParameterDescription(
           java.lang.String name,
           com.exacttarget.wsdl.partnerAPI.ExtractParameterDataType dataType,
           java.lang.String defaultValue,
           boolean isOptional,
           java.lang.String dropDownList) {
        this.name = name;
        this.dataType = dataType;
        this.defaultValue = defaultValue;
        this.isOptional = isOptional;
        this.dropDownList = dropDownList;
    }


    /**
     * Gets the name value for this ExtractParameterDescription.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this ExtractParameterDescription.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the dataType value for this ExtractParameterDescription.
     * 
     * @return dataType
     */
    public com.exacttarget.wsdl.partnerAPI.ExtractParameterDataType getDataType() {
        return dataType;
    }


    /**
     * Sets the dataType value for this ExtractParameterDescription.
     * 
     * @param dataType
     */
    public void setDataType(com.exacttarget.wsdl.partnerAPI.ExtractParameterDataType dataType) {
        this.dataType = dataType;
    }


    /**
     * Gets the defaultValue value for this ExtractParameterDescription.
     * 
     * @return defaultValue
     */
    public java.lang.String getDefaultValue() {
        return defaultValue;
    }


    /**
     * Sets the defaultValue value for this ExtractParameterDescription.
     * 
     * @param defaultValue
     */
    public void setDefaultValue(java.lang.String defaultValue) {
        this.defaultValue = defaultValue;
    }


    /**
     * Gets the isOptional value for this ExtractParameterDescription.
     * 
     * @return isOptional
     */
    public boolean isIsOptional() {
        return isOptional;
    }


    /**
     * Sets the isOptional value for this ExtractParameterDescription.
     * 
     * @param isOptional
     */
    public void setIsOptional(boolean isOptional) {
        this.isOptional = isOptional;
    }


    /**
     * Gets the dropDownList value for this ExtractParameterDescription.
     * 
     * @return dropDownList
     */
    public java.lang.String getDropDownList() {
        return dropDownList;
    }


    /**
     * Sets the dropDownList value for this ExtractParameterDescription.
     * 
     * @param dropDownList
     */
    public void setDropDownList(java.lang.String dropDownList) {
        this.dropDownList = dropDownList;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ExtractParameterDescription)) return false;
        ExtractParameterDescription other = (ExtractParameterDescription) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.dataType==null && other.getDataType()==null) || 
             (this.dataType!=null &&
              this.dataType.equals(other.getDataType()))) &&
            ((this.defaultValue==null && other.getDefaultValue()==null) || 
             (this.defaultValue!=null &&
              this.defaultValue.equals(other.getDefaultValue()))) &&
            this.isOptional == other.isIsOptional() &&
            ((this.dropDownList==null && other.getDropDownList()==null) || 
             (this.dropDownList!=null &&
              this.dropDownList.equals(other.getDropDownList())));
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
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getDataType() != null) {
            _hashCode += getDataType().hashCode();
        }
        if (getDefaultValue() != null) {
            _hashCode += getDefaultValue().hashCode();
        }
        _hashCode += (isIsOptional() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getDropDownList() != null) {
            _hashCode += getDropDownList().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ExtractParameterDescription.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExtractParameterDescription"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExtractParameterDataType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("defaultValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DefaultValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isOptional");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsOptional"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dropDownList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DropDownList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
