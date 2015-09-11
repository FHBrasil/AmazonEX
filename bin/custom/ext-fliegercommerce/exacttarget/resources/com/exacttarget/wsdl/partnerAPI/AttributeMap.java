/**
 * AttributeMap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class AttributeMap  extends com.exacttarget.wsdl.partnerAPI.APIProperty  implements java.io.Serializable {
    private java.lang.String entityName;

    private java.lang.String columnName;

    private java.lang.String columnNameMappedTo;

    private java.lang.String entityNameMappedTo;

    private com.exacttarget.wsdl.partnerAPI.APIProperty[] additionalData;

    public AttributeMap() {
    }

    public AttributeMap(
           java.lang.String name,
           java.lang.String value,
           java.lang.String entityName,
           java.lang.String columnName,
           java.lang.String columnNameMappedTo,
           java.lang.String entityNameMappedTo,
           com.exacttarget.wsdl.partnerAPI.APIProperty[] additionalData) {
        super(
            name,
            value);
        this.entityName = entityName;
        this.columnName = columnName;
        this.columnNameMappedTo = columnNameMappedTo;
        this.entityNameMappedTo = entityNameMappedTo;
        this.additionalData = additionalData;
    }


    /**
     * Gets the entityName value for this AttributeMap.
     * 
     * @return entityName
     */
    public java.lang.String getEntityName() {
        return entityName;
    }


    /**
     * Sets the entityName value for this AttributeMap.
     * 
     * @param entityName
     */
    public void setEntityName(java.lang.String entityName) {
        this.entityName = entityName;
    }


    /**
     * Gets the columnName value for this AttributeMap.
     * 
     * @return columnName
     */
    public java.lang.String getColumnName() {
        return columnName;
    }


    /**
     * Sets the columnName value for this AttributeMap.
     * 
     * @param columnName
     */
    public void setColumnName(java.lang.String columnName) {
        this.columnName = columnName;
    }


    /**
     * Gets the columnNameMappedTo value for this AttributeMap.
     * 
     * @return columnNameMappedTo
     */
    public java.lang.String getColumnNameMappedTo() {
        return columnNameMappedTo;
    }


    /**
     * Sets the columnNameMappedTo value for this AttributeMap.
     * 
     * @param columnNameMappedTo
     */
    public void setColumnNameMappedTo(java.lang.String columnNameMappedTo) {
        this.columnNameMappedTo = columnNameMappedTo;
    }


    /**
     * Gets the entityNameMappedTo value for this AttributeMap.
     * 
     * @return entityNameMappedTo
     */
    public java.lang.String getEntityNameMappedTo() {
        return entityNameMappedTo;
    }


    /**
     * Sets the entityNameMappedTo value for this AttributeMap.
     * 
     * @param entityNameMappedTo
     */
    public void setEntityNameMappedTo(java.lang.String entityNameMappedTo) {
        this.entityNameMappedTo = entityNameMappedTo;
    }


    /**
     * Gets the additionalData value for this AttributeMap.
     * 
     * @return additionalData
     */
    public com.exacttarget.wsdl.partnerAPI.APIProperty[] getAdditionalData() {
        return additionalData;
    }


    /**
     * Sets the additionalData value for this AttributeMap.
     * 
     * @param additionalData
     */
    public void setAdditionalData(com.exacttarget.wsdl.partnerAPI.APIProperty[] additionalData) {
        this.additionalData = additionalData;
    }

    public com.exacttarget.wsdl.partnerAPI.APIProperty getAdditionalData(int i) {
        return this.additionalData[i];
    }

    public void setAdditionalData(int i, com.exacttarget.wsdl.partnerAPI.APIProperty _value) {
        this.additionalData[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AttributeMap)) return false;
        AttributeMap other = (AttributeMap) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.entityName==null && other.getEntityName()==null) || 
             (this.entityName!=null &&
              this.entityName.equals(other.getEntityName()))) &&
            ((this.columnName==null && other.getColumnName()==null) || 
             (this.columnName!=null &&
              this.columnName.equals(other.getColumnName()))) &&
            ((this.columnNameMappedTo==null && other.getColumnNameMappedTo()==null) || 
             (this.columnNameMappedTo!=null &&
              this.columnNameMappedTo.equals(other.getColumnNameMappedTo()))) &&
            ((this.entityNameMappedTo==null && other.getEntityNameMappedTo()==null) || 
             (this.entityNameMappedTo!=null &&
              this.entityNameMappedTo.equals(other.getEntityNameMappedTo()))) &&
            ((this.additionalData==null && other.getAdditionalData()==null) || 
             (this.additionalData!=null &&
              java.util.Arrays.equals(this.additionalData, other.getAdditionalData())));
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
        if (getEntityName() != null) {
            _hashCode += getEntityName().hashCode();
        }
        if (getColumnName() != null) {
            _hashCode += getColumnName().hashCode();
        }
        if (getColumnNameMappedTo() != null) {
            _hashCode += getColumnNameMappedTo().hashCode();
        }
        if (getEntityNameMappedTo() != null) {
            _hashCode += getEntityNameMappedTo().hashCode();
        }
        if (getAdditionalData() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAdditionalData());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAdditionalData(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AttributeMap.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AttributeMap"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entityName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "EntityName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("columnName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ColumnName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("columnNameMappedTo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ColumnNameMappedTo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entityNameMappedTo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "EntityNameMappedTo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("additionalData");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AdditionalData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "APIProperty"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
