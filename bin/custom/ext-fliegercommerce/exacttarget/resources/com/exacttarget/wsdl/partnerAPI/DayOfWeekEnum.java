/**
 * DayOfWeekEnum.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class DayOfWeekEnum implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected DayOfWeekEnum(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _Sunday = "Sunday";
    public static final java.lang.String _Monday = "Monday";
    public static final java.lang.String _Tuesday = "Tuesday";
    public static final java.lang.String _Wednesday = "Wednesday";
    public static final java.lang.String _Thursday = "Thursday";
    public static final java.lang.String _Friday = "Friday";
    public static final java.lang.String _Saturday = "Saturday";
    public static final DayOfWeekEnum Sunday = new DayOfWeekEnum(_Sunday);
    public static final DayOfWeekEnum Monday = new DayOfWeekEnum(_Monday);
    public static final DayOfWeekEnum Tuesday = new DayOfWeekEnum(_Tuesday);
    public static final DayOfWeekEnum Wednesday = new DayOfWeekEnum(_Wednesday);
    public static final DayOfWeekEnum Thursday = new DayOfWeekEnum(_Thursday);
    public static final DayOfWeekEnum Friday = new DayOfWeekEnum(_Friday);
    public static final DayOfWeekEnum Saturday = new DayOfWeekEnum(_Saturday);
    public java.lang.String getValue() { return _value_;}
    public static DayOfWeekEnum fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        DayOfWeekEnum enumeration = (DayOfWeekEnum)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static DayOfWeekEnum fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(DayOfWeekEnum.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DayOfWeekEnum"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
