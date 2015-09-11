/**
 * EventType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class EventType implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected EventType(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _Open = "Open";
    public static final java.lang.String _Click = "Click";
    public static final java.lang.String _HardBounce = "HardBounce";
    public static final java.lang.String _SoftBounce = "SoftBounce";
    public static final java.lang.String _OtherBounce = "OtherBounce";
    public static final java.lang.String _Unsubscribe = "Unsubscribe";
    public static final java.lang.String _Sent = "Sent";
    public static final java.lang.String _NotSent = "NotSent";
    public static final java.lang.String _Survey = "Survey";
    public static final java.lang.String _ForwardedEmail = "ForwardedEmail";
    public static final java.lang.String _ForwardedEmailOptIn = "ForwardedEmailOptIn";
    public static final java.lang.String _DeliveredEvent = "DeliveredEvent";
    public static final EventType Open = new EventType(_Open);
    public static final EventType Click = new EventType(_Click);
    public static final EventType HardBounce = new EventType(_HardBounce);
    public static final EventType SoftBounce = new EventType(_SoftBounce);
    public static final EventType OtherBounce = new EventType(_OtherBounce);
    public static final EventType Unsubscribe = new EventType(_Unsubscribe);
    public static final EventType Sent = new EventType(_Sent);
    public static final EventType NotSent = new EventType(_NotSent);
    public static final EventType Survey = new EventType(_Survey);
    public static final EventType ForwardedEmail = new EventType(_ForwardedEmail);
    public static final EventType ForwardedEmailOptIn = new EventType(_ForwardedEmailOptIn);
    public static final EventType DeliveredEvent = new EventType(_DeliveredEvent);
    public java.lang.String getValue() { return _value_;}
    public static EventType fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        EventType enumeration = (EventType)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static EventType fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(EventType.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "EventType"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
