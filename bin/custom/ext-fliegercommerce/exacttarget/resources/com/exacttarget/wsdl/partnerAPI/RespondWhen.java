/**
 * RespondWhen.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class RespondWhen implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected RespondWhen(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _Never = "Never";
    public static final java.lang.String _OnError = "OnError";
    public static final java.lang.String _Always = "Always";
    public static final java.lang.String _OnConversationError = "OnConversationError";
    public static final java.lang.String _OnConversationComplete = "OnConversationComplete";
    public static final java.lang.String _OnCallComplete = "OnCallComplete";
    public static final RespondWhen Never = new RespondWhen(_Never);
    public static final RespondWhen OnError = new RespondWhen(_OnError);
    public static final RespondWhen Always = new RespondWhen(_Always);
    public static final RespondWhen OnConversationError = new RespondWhen(_OnConversationError);
    public static final RespondWhen OnConversationComplete = new RespondWhen(_OnConversationComplete);
    public static final RespondWhen OnCallComplete = new RespondWhen(_OnCallComplete);
    public java.lang.String getValue() { return _value_;}
    public static RespondWhen fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        RespondWhen enumeration = (RespondWhen)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static RespondWhen fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(RespondWhen.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RespondWhen"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
