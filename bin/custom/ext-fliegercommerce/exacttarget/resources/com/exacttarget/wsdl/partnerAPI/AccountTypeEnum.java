/**
 * AccountTypeEnum.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class AccountTypeEnum implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected AccountTypeEnum(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _None = "None";
    public static final java.lang.String _EXACTTARGET = "EXACTTARGET";
    public static final java.lang.String _PRO_CONNECT = "PRO_CONNECT";
    public static final java.lang.String _CHANNEL_CONNECT = "CHANNEL_CONNECT";
    public static final java.lang.String _CONNECT = "CONNECT";
    public static final java.lang.String _PRO_CONNECT_CLIENT = "PRO_CONNECT_CLIENT";
    public static final java.lang.String _LP_MEMBER = "LP_MEMBER";
    public static final java.lang.String _DOTO_MEMBER = "DOTO_MEMBER";
    public static final java.lang.String _ENTERPRISE_2 = "ENTERPRISE_2";
    public static final java.lang.String _BUSINESS_UNIT = "BUSINESS_UNIT";
    public static final AccountTypeEnum None = new AccountTypeEnum(_None);
    public static final AccountTypeEnum EXACTTARGET = new AccountTypeEnum(_EXACTTARGET);
    public static final AccountTypeEnum PRO_CONNECT = new AccountTypeEnum(_PRO_CONNECT);
    public static final AccountTypeEnum CHANNEL_CONNECT = new AccountTypeEnum(_CHANNEL_CONNECT);
    public static final AccountTypeEnum CONNECT = new AccountTypeEnum(_CONNECT);
    public static final AccountTypeEnum PRO_CONNECT_CLIENT = new AccountTypeEnum(_PRO_CONNECT_CLIENT);
    public static final AccountTypeEnum LP_MEMBER = new AccountTypeEnum(_LP_MEMBER);
    public static final AccountTypeEnum DOTO_MEMBER = new AccountTypeEnum(_DOTO_MEMBER);
    public static final AccountTypeEnum ENTERPRISE_2 = new AccountTypeEnum(_ENTERPRISE_2);
    public static final AccountTypeEnum BUSINESS_UNIT = new AccountTypeEnum(_BUSINESS_UNIT);
    public java.lang.String getValue() { return _value_;}
    public static AccountTypeEnum fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        AccountTypeEnum enumeration = (AccountTypeEnum)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static AccountTypeEnum fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(AccountTypeEnum.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AccountTypeEnum"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
