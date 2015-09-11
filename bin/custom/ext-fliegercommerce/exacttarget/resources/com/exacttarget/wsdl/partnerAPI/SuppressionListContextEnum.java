/**
 * SuppressionListContextEnum.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class SuppressionListContextEnum implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected SuppressionListContextEnum(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _Enterprise = "Enterprise";
    public static final java.lang.String _BusinessUnit = "BusinessUnit";
    public static final java.lang.String _SendClassification = "SendClassification";
    public static final java.lang.String _Send = "Send";
    public static final java.lang.String _Global = "Global";
    public static final java.lang.String _SenderProfile = "SenderProfile";
    public static final SuppressionListContextEnum Enterprise = new SuppressionListContextEnum(_Enterprise);
    public static final SuppressionListContextEnum BusinessUnit = new SuppressionListContextEnum(_BusinessUnit);
    public static final SuppressionListContextEnum SendClassification = new SuppressionListContextEnum(_SendClassification);
    public static final SuppressionListContextEnum Send = new SuppressionListContextEnum(_Send);
    public static final SuppressionListContextEnum Global = new SuppressionListContextEnum(_Global);
    public static final SuppressionListContextEnum SenderProfile = new SuppressionListContextEnum(_SenderProfile);
    public java.lang.String getValue() { return _value_;}
    public static SuppressionListContextEnum fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        SuppressionListContextEnum enumeration = (SuppressionListContextEnum)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static SuppressionListContextEnum fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(SuppressionListContextEnum.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SuppressionListContextEnum"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
