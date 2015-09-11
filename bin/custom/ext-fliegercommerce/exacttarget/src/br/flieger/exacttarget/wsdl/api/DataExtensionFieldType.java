/**
 * DataExtensionFieldType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.flieger.exacttarget.wsdl.api;

public class DataExtensionFieldType implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected DataExtensionFieldType(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _Text = "Text";
    public static final java.lang.String _Number = "Number";
    public static final java.lang.String _Date = "Date";
    public static final java.lang.String _Boolean = "Boolean";
    public static final java.lang.String _EmailAddress = "EmailAddress";
    public static final java.lang.String _Phone = "Phone";
    public static final java.lang.String _Decimal = "Decimal";
    public static final java.lang.String _Locale = "Locale";
    public static final DataExtensionFieldType Text = new DataExtensionFieldType(_Text);
    public static final DataExtensionFieldType Number = new DataExtensionFieldType(_Number);
    public static final DataExtensionFieldType Date = new DataExtensionFieldType(_Date);
    public static final DataExtensionFieldType Boolean = new DataExtensionFieldType(_Boolean);
    public static final DataExtensionFieldType EmailAddress = new DataExtensionFieldType(_EmailAddress);
    public static final DataExtensionFieldType Phone = new DataExtensionFieldType(_Phone);
    public static final DataExtensionFieldType Decimal = new DataExtensionFieldType(_Decimal);
    public static final DataExtensionFieldType Locale = new DataExtensionFieldType(_Locale);
    public java.lang.String getValue() { return _value_;}
    public static DataExtensionFieldType fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        DataExtensionFieldType enumeration = (DataExtensionFieldType)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static DataExtensionFieldType fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(DataExtensionFieldType.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataExtensionFieldType"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
