/**
 * ImportDefinitionUpdateType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class ImportDefinitionUpdateType implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected ImportDefinitionUpdateType(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _AddAndUpdate = "AddAndUpdate";
    public static final java.lang.String _AddAndDoNotUpdate = "AddAndDoNotUpdate";
    public static final java.lang.String _UpdateButDoNotAdd = "UpdateButDoNotAdd";
    public static final java.lang.String _Merge = "Merge";
    public static final java.lang.String _Overwrite = "Overwrite";
    public static final java.lang.String _ColumnBased = "ColumnBased";
    public static final ImportDefinitionUpdateType AddAndUpdate = new ImportDefinitionUpdateType(_AddAndUpdate);
    public static final ImportDefinitionUpdateType AddAndDoNotUpdate = new ImportDefinitionUpdateType(_AddAndDoNotUpdate);
    public static final ImportDefinitionUpdateType UpdateButDoNotAdd = new ImportDefinitionUpdateType(_UpdateButDoNotAdd);
    public static final ImportDefinitionUpdateType Merge = new ImportDefinitionUpdateType(_Merge);
    public static final ImportDefinitionUpdateType Overwrite = new ImportDefinitionUpdateType(_Overwrite);
    public static final ImportDefinitionUpdateType ColumnBased = new ImportDefinitionUpdateType(_ColumnBased);
    public java.lang.String getValue() { return _value_;}
    public static ImportDefinitionUpdateType fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        ImportDefinitionUpdateType enumeration = (ImportDefinitionUpdateType)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static ImportDefinitionUpdateType fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(ImportDefinitionUpdateType.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ImportDefinitionUpdateType"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
