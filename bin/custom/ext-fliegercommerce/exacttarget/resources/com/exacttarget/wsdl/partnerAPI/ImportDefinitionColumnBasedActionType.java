/**
 * ImportDefinitionColumnBasedActionType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class ImportDefinitionColumnBasedActionType implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected ImportDefinitionColumnBasedActionType(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _AddAndUpdate = "AddAndUpdate";
    public static final java.lang.String _AddButDoNotUpdate = "AddButDoNotUpdate";
    public static final java.lang.String _Delete = "Delete";
    public static final java.lang.String _Skip = "Skip";
    public static final java.lang.String _UpdateButDoNotAdd = "UpdateButDoNotAdd";
    public static final ImportDefinitionColumnBasedActionType AddAndUpdate = new ImportDefinitionColumnBasedActionType(_AddAndUpdate);
    public static final ImportDefinitionColumnBasedActionType AddButDoNotUpdate = new ImportDefinitionColumnBasedActionType(_AddButDoNotUpdate);
    public static final ImportDefinitionColumnBasedActionType Delete = new ImportDefinitionColumnBasedActionType(_Delete);
    public static final ImportDefinitionColumnBasedActionType Skip = new ImportDefinitionColumnBasedActionType(_Skip);
    public static final ImportDefinitionColumnBasedActionType UpdateButDoNotAdd = new ImportDefinitionColumnBasedActionType(_UpdateButDoNotAdd);
    public java.lang.String getValue() { return _value_;}
    public static ImportDefinitionColumnBasedActionType fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        ImportDefinitionColumnBasedActionType enumeration = (ImportDefinitionColumnBasedActionType)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static ImportDefinitionColumnBasedActionType fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(ImportDefinitionColumnBasedActionType.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ImportDefinitionColumnBasedActionType"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
