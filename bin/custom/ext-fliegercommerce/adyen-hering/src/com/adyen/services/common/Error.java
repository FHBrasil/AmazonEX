/**
 * Error.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.adyen.services.common;

public class Error implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected Error(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _Unknown = "Unknown";
    public static final java.lang.String _NotAllowed = "NotAllowed";
    public static final java.lang.String _NoAmountSpecified = "NoAmountSpecified";
    public static final java.lang.String _UnableToDetermineVariant = "UnableToDetermineVariant";
    public static final java.lang.String _InvalidMerchantAccount = "InvalidMerchantAccount";
    public static final java.lang.String _RequestMissing = "RequestMissing";
    public static final java.lang.String _InternalError = "InternalError";
    public static final java.lang.String _UnableToProcess = "UnableToProcess";
    public static final java.lang.String _PaymentDetailsAreNotSupported = "PaymentDetailsAreNotSupported";
    public static final Error Unknown = new Error(_Unknown);
    public static final Error NotAllowed = new Error(_NotAllowed);
    public static final Error NoAmountSpecified = new Error(_NoAmountSpecified);
    public static final Error UnableToDetermineVariant = new Error(_UnableToDetermineVariant);
    public static final Error InvalidMerchantAccount = new Error(_InvalidMerchantAccount);
    public static final Error RequestMissing = new Error(_RequestMissing);
    public static final Error InternalError = new Error(_InternalError);
    public static final Error UnableToProcess = new Error(_UnableToProcess);
    public static final Error PaymentDetailsAreNotSupported = new Error(_PaymentDetailsAreNotSupported);
    public java.lang.String getValue() { return _value_;}
    public static Error fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        Error enumeration = (Error)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static Error fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(Error.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://common.services.adyen.com", "Error"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
