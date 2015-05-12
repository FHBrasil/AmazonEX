/**
 * SaveAction.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class SaveAction implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected SaveAction(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _AddOnly = "AddOnly";
    public static final java.lang.String _Default = "Default";
    public static final java.lang.String _Nothing = "Nothing";
    public static final java.lang.String _UpdateAdd = "UpdateAdd";
    public static final java.lang.String _UpdateOnly = "UpdateOnly";
    public static final java.lang.String _Delete = "Delete";
    public static final SaveAction AddOnly = new SaveAction(_AddOnly);
    public static final SaveAction Default = new SaveAction(_Default);
    public static final SaveAction Nothing = new SaveAction(_Nothing);
    public static final SaveAction UpdateAdd = new SaveAction(_UpdateAdd);
    public static final SaveAction UpdateOnly = new SaveAction(_UpdateOnly);
    public static final SaveAction Delete = new SaveAction(_Delete);
    public java.lang.String getValue() { return _value_;}
    public static SaveAction fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        SaveAction enumeration = (SaveAction)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static SaveAction fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(SaveAction.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SaveAction"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
