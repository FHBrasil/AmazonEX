/**
 * DataExtensionField.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.flieger.exacttarget.wsdl.api;

public class DataExtensionField  extends br.flieger.exacttarget.wsdl.api.PropertyDefinition  implements java.io.Serializable {
    private java.lang.Integer ordinal;

    private java.lang.Boolean isPrimaryKey;

    private br.flieger.exacttarget.wsdl.api.DataExtensionFieldType fieldType;

    private br.flieger.exacttarget.wsdl.api.DataExtension dataExtension;

    public DataExtensionField() {
    }

    public DataExtensionField(
           br.flieger.exacttarget.wsdl.api.ClientID client,
           java.lang.String partnerKey,
           br.flieger.exacttarget.wsdl.api.APIProperty[] partnerProperties,
           java.util.Calendar createdDate,
           java.util.Calendar modifiedDate,
           java.lang.Integer ID,
           java.lang.String objectID,
           java.lang.String customerKey,
           br.flieger.exacttarget.wsdl.api.Owner owner,
           java.lang.String correlationID,
           java.lang.String objectState,
           java.lang.String name,
           java.lang.String dataType,
           br.flieger.exacttarget.wsdl.api.SoapType valueType,
           br.flieger.exacttarget.wsdl.api.PropertyType propertyType,
           java.lang.Boolean isCreatable,
           java.lang.Boolean isUpdatable,
           java.lang.Boolean isRetrievable,
           java.lang.Boolean isQueryable,
           java.lang.Boolean isFilterable,
           java.lang.Boolean isPartnerProperty,
           java.lang.Boolean isAccountProperty,
           java.lang.String partnerMap,
           br.flieger.exacttarget.wsdl.api.AttributeMap[] attributeMaps,
           br.flieger.exacttarget.wsdl.api.APIProperty[] markups,
           java.lang.Integer precision,
           java.lang.Integer scale,
           java.lang.String label,
           java.lang.String description,
           java.lang.String defaultValue,
           java.lang.Integer minLength,
           java.lang.Integer maxLength,
           java.lang.String minValue,
           java.lang.String maxValue,
           java.lang.Boolean isRequired,
           java.lang.Boolean isViewable,
           java.lang.Boolean isEditable,
           java.lang.Boolean isNillable,
           java.lang.Boolean isRestrictedPicklist,
           br.flieger.exacttarget.wsdl.api.PicklistItem[] picklistItems,
           java.lang.Boolean isSendTime,
           java.lang.Integer displayOrder,
           br.flieger.exacttarget.wsdl.api.APIObject[] references,
           java.lang.String relationshipName,
           java.lang.String status,
           java.lang.Boolean isContextSpecific,
           java.lang.Integer ordinal,
           java.lang.Boolean isPrimaryKey,
           br.flieger.exacttarget.wsdl.api.DataExtensionFieldType fieldType,
           br.flieger.exacttarget.wsdl.api.DataExtension dataExtension) {
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
            objectState,
            name,
            dataType,
            valueType,
            propertyType,
            isCreatable,
            isUpdatable,
            isRetrievable,
            isQueryable,
            isFilterable,
            isPartnerProperty,
            isAccountProperty,
            partnerMap,
            attributeMaps,
            markups,
            precision,
            scale,
            label,
            description,
            defaultValue,
            minLength,
            maxLength,
            minValue,
            maxValue,
            isRequired,
            isViewable,
            isEditable,
            isNillable,
            isRestrictedPicklist,
            picklistItems,
            isSendTime,
            displayOrder,
            references,
            relationshipName,
            status,
            isContextSpecific);
        this.ordinal = ordinal;
        this.isPrimaryKey = isPrimaryKey;
        this.fieldType = fieldType;
        this.dataExtension = dataExtension;
    }


    /**
     * Gets the ordinal value for this DataExtensionField.
     * 
     * @return ordinal
     */
    public java.lang.Integer getOrdinal() {
        return ordinal;
    }


    /**
     * Sets the ordinal value for this DataExtensionField.
     * 
     * @param ordinal
     */
    public void setOrdinal(java.lang.Integer ordinal) {
        this.ordinal = ordinal;
    }


    /**
     * Gets the isPrimaryKey value for this DataExtensionField.
     * 
     * @return isPrimaryKey
     */
    public java.lang.Boolean getIsPrimaryKey() {
        return isPrimaryKey;
    }


    /**
     * Sets the isPrimaryKey value for this DataExtensionField.
     * 
     * @param isPrimaryKey
     */
    public void setIsPrimaryKey(java.lang.Boolean isPrimaryKey) {
        this.isPrimaryKey = isPrimaryKey;
    }


    /**
     * Gets the fieldType value for this DataExtensionField.
     * 
     * @return fieldType
     */
    public br.flieger.exacttarget.wsdl.api.DataExtensionFieldType getFieldType() {
        return fieldType;
    }


    /**
     * Sets the fieldType value for this DataExtensionField.
     * 
     * @param fieldType
     */
    public void setFieldType(br.flieger.exacttarget.wsdl.api.DataExtensionFieldType fieldType) {
        this.fieldType = fieldType;
    }


    /**
     * Gets the dataExtension value for this DataExtensionField.
     * 
     * @return dataExtension
     */
    public br.flieger.exacttarget.wsdl.api.DataExtension getDataExtension() {
        return dataExtension;
    }


    /**
     * Sets the dataExtension value for this DataExtensionField.
     * 
     * @param dataExtension
     */
    public void setDataExtension(br.flieger.exacttarget.wsdl.api.DataExtension dataExtension) {
        this.dataExtension = dataExtension;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DataExtensionField)) return false;
        DataExtensionField other = (DataExtensionField) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.ordinal==null && other.getOrdinal()==null) || 
             (this.ordinal!=null &&
              this.ordinal.equals(other.getOrdinal()))) &&
            ((this.isPrimaryKey==null && other.getIsPrimaryKey()==null) || 
             (this.isPrimaryKey!=null &&
              this.isPrimaryKey.equals(other.getIsPrimaryKey()))) &&
            ((this.fieldType==null && other.getFieldType()==null) || 
             (this.fieldType!=null &&
              this.fieldType.equals(other.getFieldType()))) &&
            ((this.dataExtension==null && other.getDataExtension()==null) || 
             (this.dataExtension!=null &&
              this.dataExtension.equals(other.getDataExtension())));
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
        if (getOrdinal() != null) {
            _hashCode += getOrdinal().hashCode();
        }
        if (getIsPrimaryKey() != null) {
            _hashCode += getIsPrimaryKey().hashCode();
        }
        if (getFieldType() != null) {
            _hashCode += getFieldType().hashCode();
        }
        if (getDataExtension() != null) {
            _hashCode += getDataExtension().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DataExtensionField.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataExtensionField"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ordinal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Ordinal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isPrimaryKey");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsPrimaryKey"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fieldType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FieldType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataExtensionFieldType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataExtension");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataExtension"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataExtension"));
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
