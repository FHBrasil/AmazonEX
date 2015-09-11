/**
 * DataSourceTypeEnum.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class DataSourceTypeEnum implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected DataSourceTypeEnum(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _List = "List";
    public static final java.lang.String _CustomObject = "CustomObject";
    public static final java.lang.String _DomainExclusion = "DomainExclusion";
    public static final java.lang.String _SalesForceReport = "SalesForceReport";
    public static final java.lang.String _SalesForceCampaign = "SalesForceCampaign";
    public static final java.lang.String _FilterDefinition = "FilterDefinition";
    public static final java.lang.String _OptOutList = "OptOutList";
    public static final DataSourceTypeEnum List = new DataSourceTypeEnum(_List);
    public static final DataSourceTypeEnum CustomObject = new DataSourceTypeEnum(_CustomObject);
    public static final DataSourceTypeEnum DomainExclusion = new DataSourceTypeEnum(_DomainExclusion);
    public static final DataSourceTypeEnum SalesForceReport = new DataSourceTypeEnum(_SalesForceReport);
    public static final DataSourceTypeEnum SalesForceCampaign = new DataSourceTypeEnum(_SalesForceCampaign);
    public static final DataSourceTypeEnum FilterDefinition = new DataSourceTypeEnum(_FilterDefinition);
    public static final DataSourceTypeEnum OptOutList = new DataSourceTypeEnum(_OptOutList);
    public java.lang.String getValue() { return _value_;}
    public static DataSourceTypeEnum fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        DataSourceTypeEnum enumeration = (DataSourceTypeEnum)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static DataSourceTypeEnum fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(DataSourceTypeEnum.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataSourceTypeEnum"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
