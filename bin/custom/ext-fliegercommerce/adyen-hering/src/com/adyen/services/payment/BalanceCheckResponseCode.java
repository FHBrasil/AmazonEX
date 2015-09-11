/**
 * BalanceCheckResponseCode.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.adyen.services.payment;

public class BalanceCheckResponseCode implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected BalanceCheckResponseCode(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _OK = "OK";
    public static final java.lang.String _NO_BALANCE = "NO_BALANCE";
    public static final java.lang.String _NOT_CHECKED = "NOT_CHECKED";
    public static final java.lang.String _NOT_ALLOWED = "NOT_ALLOWED";
    public static final BalanceCheckResponseCode OK = new BalanceCheckResponseCode(_OK);
    public static final BalanceCheckResponseCode NO_BALANCE = new BalanceCheckResponseCode(_NO_BALANCE);
    public static final BalanceCheckResponseCode NOT_CHECKED = new BalanceCheckResponseCode(_NOT_CHECKED);
    public static final BalanceCheckResponseCode NOT_ALLOWED = new BalanceCheckResponseCode(_NOT_ALLOWED);
    public java.lang.String getValue() { return _value_;}
    public static BalanceCheckResponseCode fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        BalanceCheckResponseCode enumeration = (BalanceCheckResponseCode)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static BalanceCheckResponseCode fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(BalanceCheckResponseCode.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://payment.services.adyen.com", "BalanceCheckResponseCode"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
