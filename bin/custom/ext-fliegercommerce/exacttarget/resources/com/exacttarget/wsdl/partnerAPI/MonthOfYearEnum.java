/**
 * MonthOfYearEnum.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class MonthOfYearEnum implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected MonthOfYearEnum(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _January = "January";
    public static final java.lang.String _February = "February";
    public static final java.lang.String _March = "March";
    public static final java.lang.String _April = "April";
    public static final java.lang.String _May = "May";
    public static final java.lang.String _June = "June";
    public static final java.lang.String _July = "July";
    public static final java.lang.String _August = "August";
    public static final java.lang.String _September = "September";
    public static final java.lang.String _October = "October";
    public static final java.lang.String _November = "November";
    public static final java.lang.String _December = "December";
    public static final MonthOfYearEnum January = new MonthOfYearEnum(_January);
    public static final MonthOfYearEnum February = new MonthOfYearEnum(_February);
    public static final MonthOfYearEnum March = new MonthOfYearEnum(_March);
    public static final MonthOfYearEnum April = new MonthOfYearEnum(_April);
    public static final MonthOfYearEnum May = new MonthOfYearEnum(_May);
    public static final MonthOfYearEnum June = new MonthOfYearEnum(_June);
    public static final MonthOfYearEnum July = new MonthOfYearEnum(_July);
    public static final MonthOfYearEnum August = new MonthOfYearEnum(_August);
    public static final MonthOfYearEnum September = new MonthOfYearEnum(_September);
    public static final MonthOfYearEnum October = new MonthOfYearEnum(_October);
    public static final MonthOfYearEnum November = new MonthOfYearEnum(_November);
    public static final MonthOfYearEnum December = new MonthOfYearEnum(_December);
    public java.lang.String getValue() { return _value_;}
    public static MonthOfYearEnum fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        MonthOfYearEnum enumeration = (MonthOfYearEnum)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static MonthOfYearEnum fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(MonthOfYearEnum.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MonthOfYearEnum"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
