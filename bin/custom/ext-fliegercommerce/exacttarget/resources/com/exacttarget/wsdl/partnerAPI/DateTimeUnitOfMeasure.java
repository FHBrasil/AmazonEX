/**
 * DateTimeUnitOfMeasure.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class DateTimeUnitOfMeasure implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected DateTimeUnitOfMeasure(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _Days = "Days";
    public static final java.lang.String _Weeks = "Weeks";
    public static final java.lang.String _Months = "Months";
    public static final java.lang.String _Years = "Years";
    public static final DateTimeUnitOfMeasure Days = new DateTimeUnitOfMeasure(_Days);
    public static final DateTimeUnitOfMeasure Weeks = new DateTimeUnitOfMeasure(_Weeks);
    public static final DateTimeUnitOfMeasure Months = new DateTimeUnitOfMeasure(_Months);
    public static final DateTimeUnitOfMeasure Years = new DateTimeUnitOfMeasure(_Years);
    public java.lang.String getValue() { return _value_;}
    public static DateTimeUnitOfMeasure fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        DateTimeUnitOfMeasure enumeration = (DateTimeUnitOfMeasure)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static DateTimeUnitOfMeasure fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(DateTimeUnitOfMeasure.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DateTimeUnitOfMeasure"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
