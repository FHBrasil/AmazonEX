/**
 * MessageDeliveryTypeEnum.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class MessageDeliveryTypeEnum implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected MessageDeliveryTypeEnum(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _Standard = "Standard";
    public static final java.lang.String _DelayedDeliveryByMTAQueue = "DelayedDeliveryByMTAQueue";
    public static final java.lang.String _DelayedDeliveryByOMMQueue = "DelayedDeliveryByOMMQueue";
    public static final MessageDeliveryTypeEnum Standard = new MessageDeliveryTypeEnum(_Standard);
    public static final MessageDeliveryTypeEnum DelayedDeliveryByMTAQueue = new MessageDeliveryTypeEnum(_DelayedDeliveryByMTAQueue);
    public static final MessageDeliveryTypeEnum DelayedDeliveryByOMMQueue = new MessageDeliveryTypeEnum(_DelayedDeliveryByOMMQueue);
    public java.lang.String getValue() { return _value_;}
    public static MessageDeliveryTypeEnum fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        MessageDeliveryTypeEnum enumeration = (MessageDeliveryTypeEnum)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static MessageDeliveryTypeEnum fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(MessageDeliveryTypeEnum.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MessageDeliveryTypeEnum"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
