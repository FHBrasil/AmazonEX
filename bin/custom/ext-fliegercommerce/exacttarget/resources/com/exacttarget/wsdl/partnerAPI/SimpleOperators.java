/**
 * SimpleOperators.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class SimpleOperators implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected SimpleOperators(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _equals = "equals";
    public static final java.lang.String _notEquals = "notEquals";
    public static final java.lang.String _greaterThan = "greaterThan";
    public static final java.lang.String _lessThan = "lessThan";
    public static final java.lang.String _isNull = "isNull";
    public static final java.lang.String _isNotNull = "isNotNull";
    public static final java.lang.String _greaterThanOrEqual = "greaterThanOrEqual";
    public static final java.lang.String _lessThanOrEqual = "lessThanOrEqual";
    public static final java.lang.String _between = "between";
    public static final java.lang.String _IN = "IN";
    public static final java.lang.String _like = "like";
    public static final java.lang.String _existsInString = "existsInString";
    public static final java.lang.String _existsInStringAsAWord = "existsInStringAsAWord";
    public static final java.lang.String _notExistsInString = "notExistsInString";
    public static final java.lang.String _beginsWith = "beginsWith";
    public static final java.lang.String _endsWith = "endsWith";
    public static final java.lang.String _contains = "contains";
    public static final java.lang.String _notContains = "notContains";
    public static final java.lang.String _isAnniversary = "isAnniversary";
    public static final java.lang.String _isNotAnniversary = "isNotAnniversary";
    public static final java.lang.String _greaterThanAnniversary = "greaterThanAnniversary";
    public static final java.lang.String _lessThanAnniversary = "lessThanAnniversary";
    public static final SimpleOperators equals = new SimpleOperators(_equals);
    public static final SimpleOperators notEquals = new SimpleOperators(_notEquals);
    public static final SimpleOperators greaterThan = new SimpleOperators(_greaterThan);
    public static final SimpleOperators lessThan = new SimpleOperators(_lessThan);
    public static final SimpleOperators isNull = new SimpleOperators(_isNull);
    public static final SimpleOperators isNotNull = new SimpleOperators(_isNotNull);
    public static final SimpleOperators greaterThanOrEqual = new SimpleOperators(_greaterThanOrEqual);
    public static final SimpleOperators lessThanOrEqual = new SimpleOperators(_lessThanOrEqual);
    public static final SimpleOperators between = new SimpleOperators(_between);
    public static final SimpleOperators IN = new SimpleOperators(_IN);
    public static final SimpleOperators like = new SimpleOperators(_like);
    public static final SimpleOperators existsInString = new SimpleOperators(_existsInString);
    public static final SimpleOperators existsInStringAsAWord = new SimpleOperators(_existsInStringAsAWord);
    public static final SimpleOperators notExistsInString = new SimpleOperators(_notExistsInString);
    public static final SimpleOperators beginsWith = new SimpleOperators(_beginsWith);
    public static final SimpleOperators endsWith = new SimpleOperators(_endsWith);
    public static final SimpleOperators contains = new SimpleOperators(_contains);
    public static final SimpleOperators notContains = new SimpleOperators(_notContains);
    public static final SimpleOperators isAnniversary = new SimpleOperators(_isAnniversary);
    public static final SimpleOperators isNotAnniversary = new SimpleOperators(_isNotAnniversary);
    public static final SimpleOperators greaterThanAnniversary = new SimpleOperators(_greaterThanAnniversary);
    public static final SimpleOperators lessThanAnniversary = new SimpleOperators(_lessThanAnniversary);
    public java.lang.String getValue() { return _value_;}
    public static SimpleOperators fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        SimpleOperators enumeration = (SimpleOperators)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static SimpleOperators fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(SimpleOperators.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SimpleOperators"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
