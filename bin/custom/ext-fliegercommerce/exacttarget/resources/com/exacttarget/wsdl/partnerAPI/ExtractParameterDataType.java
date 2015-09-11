/**
 * ExtractParameterDataType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class ExtractParameterDataType implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected ExtractParameterDataType(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _datetime = "datetime";
    public static final java.lang.String _bool = "bool";
    public static final java.lang.String _string = "string";
    public static final java.lang.String _integer = "integer";
    public static final java.lang.String _dropdown = "dropdown";
    public static final ExtractParameterDataType datetime = new ExtractParameterDataType(_datetime);
    public static final ExtractParameterDataType bool = new ExtractParameterDataType(_bool);
    public static final ExtractParameterDataType string = new ExtractParameterDataType(_string);
    public static final ExtractParameterDataType integer = new ExtractParameterDataType(_integer);
    public static final ExtractParameterDataType dropdown = new ExtractParameterDataType(_dropdown);
    public java.lang.String getValue() { return _value_;}
    public static ExtractParameterDataType fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        ExtractParameterDataType enumeration = (ExtractParameterDataType)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static ExtractParameterDataType fromString(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        return fromValue(value);
    }
    public boolean equals(java.lang.Object obj) {return (obj == this);}
    public int hashCode() { return toString().hashCode();}
    public java.lang.String toString() { return _value_;}
    public java.lang.Object readResolve() throws java.io.ObjectStreamException { return fromValue(_value_);}
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumSerializer(
            _javaType, _xmlType);
    }
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumDeserializer(
            _javaType, _xmlType);
    }
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ExtractParameterDataType.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExtractParameterDataType"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
