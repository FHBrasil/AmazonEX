/**
 * AudienceItem.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class AudienceItem  extends com.exacttarget.wsdl.partnerAPI.APIObject  implements java.io.Serializable {
    private com.exacttarget.wsdl.partnerAPI.List list;

    private com.exacttarget.wsdl.partnerAPI.SendDefinitionListTypeEnum sendDefinitionListType;

    private java.lang.String customObjectID;

    private com.exacttarget.wsdl.partnerAPI.DataSourceTypeEnum dataSourceTypeID;

    public AudienceItem() {
    }

    public AudienceItem(
           com.exacttarget.wsdl.partnerAPI.ClientID client,
           java.lang.String partnerKey,
           com.exacttarget.wsdl.partnerAPI.APIProperty[] partnerProperties,
           java.util.Calendar createdDate,
           java.util.Calendar modifiedDate,
           java.lang.Integer ID,
           java.lang.String objectID,
           java.lang.String customerKey,
           com.exacttarget.wsdl.partnerAPI.Owner owner,
           java.lang.String correlationID,
           java.lang.String objectState,
           com.exacttarget.wsdl.partnerAPI.List list,
           com.exacttarget.wsdl.partnerAPI.SendDefinitionListTypeEnum sendDefinitionListType,
           java.lang.String customObjectID,
           com.exacttarget.wsdl.partnerAPI.DataSourceTypeEnum dataSourceTypeID) {
        super(
            client,
            partnerKey,
            partnerProperties,
            createdDate,
            modifiedDate,
            ID,
            objectID,
            customerKey,
            owner,
            correlationID,
            objectState);
        this.list = list;
        this.sendDefinitionListType = sendDefinitionListType;
        this.customObjectID = customObjectID;
        this.dataSourceTypeID = dataSourceTypeID;
    }


    /**
     * Gets the list value for this AudienceItem.
     * 
     * @return list
     */
    public com.exacttarget.wsdl.partnerAPI.List getList() {
        return list;
    }


    /**
     * Sets the list value for this AudienceItem.
     * 
     * @param list
     */
    public void setList(com.exacttarget.wsdl.partnerAPI.List list) {
        this.list = list;
    }


    /**
     * Gets the sendDefinitionListType value for this AudienceItem.
     * 
     * @return sendDefinitionListType
     */
    public com.exacttarget.wsdl.partnerAPI.SendDefinitionListTypeEnum getSendDefinitionListType() {
        return sendDefinitionListType;
    }


    /**
     * Sets the sendDefinitionListType value for this AudienceItem.
     * 
     * @param sendDefinitionListType
     */
    public void setSendDefinitionListType(com.exacttarget.wsdl.partnerAPI.SendDefinitionListTypeEnum sendDefinitionListType) {
        this.sendDefinitionListType = sendDefinitionListType;
    }


    /**
     * Gets the customObjectID value for this AudienceItem.
     * 
     * @return customObjectID
     */
    public java.lang.String getCustomObjectID() {
        return customObjectID;
    }


    /**
     * Sets the customObjectID value for this AudienceItem.
     * 
     * @param customObjectID
     */
    public void setCustomObjectID(java.lang.String customObjectID) {
        this.customObjectID = customObjectID;
    }


    /**
     * Gets the dataSourceTypeID value for this AudienceItem.
     * 
     * @return dataSourceTypeID
     */
    public com.exacttarget.wsdl.partnerAPI.DataSourceTypeEnum getDataSourceTypeID() {
        return dataSourceTypeID;
    }


    /**
     * Sets the dataSourceTypeID value for this AudienceItem.
     * 
     * @param dataSourceTypeID
     */
    public void setDataSourceTypeID(com.exacttarget.wsdl.partnerAPI.DataSourceTypeEnum dataSourceTypeID) {
        this.dataSourceTypeID = dataSourceTypeID;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AudienceItem)) return false;
        AudienceItem other = (AudienceItem) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.list==null && other.getList()==null) || 
             (this.list!=null &&
              this.list.equals(other.getList()))) &&
            ((this.sendDefinitionListType==null && other.getSendDefinitionListType()==null) || 
             (this.sendDefinitionListType!=null &&
              this.sendDefinitionListType.equals(other.getSendDefinitionListType()))) &&
            ((this.customObjectID==null && other.getCustomObjectID()==null) || 
             (this.customObjectID!=null &&
              this.customObjectID.equals(other.getCustomObjectID()))) &&
            ((this.dataSourceTypeID==null && other.getDataSourceTypeID()==null) || 
             (this.dataSourceTypeID!=null &&
              this.dataSourceTypeID.equals(other.getDataSourceTypeID())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getList() != null) {
            _hashCode += getList().hashCode();
        }
        if (getSendDefinitionListType() != null) {
            _hashCode += getSendDefinitionListType().hashCode();
        }
        if (getCustomObjectID() != null) {
            _hashCode += getCustomObjectID().hashCode();
        }
        if (getDataSourceTypeID() != null) {
            _hashCode += getDataSourceTypeID().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AudienceItem.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AudienceItem"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("list");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "List"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "List"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sendDefinitionListType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendDefinitionListType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendDefinitionListTypeEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customObjectID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "CustomObjectID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataSourceTypeID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataSourceTypeID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataSourceTypeEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
