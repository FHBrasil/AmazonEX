/**
 * SendDefinitionList.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class SendDefinitionList  extends com.exacttarget.wsdl.partnerAPI.AudienceItem  implements java.io.Serializable {
    private com.exacttarget.wsdl.partnerAPI.FilterDefinition filterDefinition;

    private java.lang.Boolean isTestObject;

    private java.lang.String salesForceObjectID;

    private java.lang.String name;

    private com.exacttarget.wsdl.partnerAPI.APIProperty[] parameters;

    public SendDefinitionList() {
    }

    public SendDefinitionList(
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
           com.exacttarget.wsdl.partnerAPI.DataSourceTypeEnum dataSourceTypeID,
           com.exacttarget.wsdl.partnerAPI.FilterDefinition filterDefinition,
           java.lang.Boolean isTestObject,
           java.lang.String salesForceObjectID,
           java.lang.String name,
           com.exacttarget.wsdl.partnerAPI.APIProperty[] parameters) {
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
            list,
            sendDefinitionListType,
            customObjectID,
            dataSourceTypeID);
        this.filterDefinition = filterDefinition;
        this.isTestObject = isTestObject;
        this.salesForceObjectID = salesForceObjectID;
        this.name = name;
        this.parameters = parameters;
    }


    /**
     * Gets the filterDefinition value for this SendDefinitionList.
     * 
     * @return filterDefinition
     */
    public com.exacttarget.wsdl.partnerAPI.FilterDefinition getFilterDefinition() {
        return filterDefinition;
    }


    /**
     * Sets the filterDefinition value for this SendDefinitionList.
     * 
     * @param filterDefinition
     */
    public void setFilterDefinition(com.exacttarget.wsdl.partnerAPI.FilterDefinition filterDefinition) {
        this.filterDefinition = filterDefinition;
    }


    /**
     * Gets the isTestObject value for this SendDefinitionList.
     * 
     * @return isTestObject
     */
    public java.lang.Boolean getIsTestObject() {
        return isTestObject;
    }


    /**
     * Sets the isTestObject value for this SendDefinitionList.
     * 
     * @param isTestObject
     */
    public void setIsTestObject(java.lang.Boolean isTestObject) {
        this.isTestObject = isTestObject;
    }


    /**
     * Gets the salesForceObjectID value for this SendDefinitionList.
     * 
     * @return salesForceObjectID
     */
    public java.lang.String getSalesForceObjectID() {
        return salesForceObjectID;
    }


    /**
     * Sets the salesForceObjectID value for this SendDefinitionList.
     * 
     * @param salesForceObjectID
     */
    public void setSalesForceObjectID(java.lang.String salesForceObjectID) {
        this.salesForceObjectID = salesForceObjectID;
    }


    /**
     * Gets the name value for this SendDefinitionList.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this SendDefinitionList.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the parameters value for this SendDefinitionList.
     * 
     * @return parameters
     */
    public com.exacttarget.wsdl.partnerAPI.APIProperty[] getParameters() {
        return parameters;
    }


    /**
     * Sets the parameters value for this SendDefinitionList.
     * 
     * @param parameters
     */
    public void setParameters(com.exacttarget.wsdl.partnerAPI.APIProperty[] parameters) {
        this.parameters = parameters;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SendDefinitionList)) return false;
        SendDefinitionList other = (SendDefinitionList) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.filterDefinition==null && other.getFilterDefinition()==null) || 
             (this.filterDefinition!=null &&
              this.filterDefinition.equals(other.getFilterDefinition()))) &&
            ((this.isTestObject==null && other.getIsTestObject()==null) || 
             (this.isTestObject!=null &&
              this.isTestObject.equals(other.getIsTestObject()))) &&
            ((this.salesForceObjectID==null && other.getSalesForceObjectID()==null) || 
             (this.salesForceObjectID!=null &&
              this.salesForceObjectID.equals(other.getSalesForceObjectID()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.parameters==null && other.getParameters()==null) || 
             (this.parameters!=null &&
              java.util.Arrays.equals(this.parameters, other.getParameters())));
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
        if (getFilterDefinition() != null) {
            _hashCode += getFilterDefinition().hashCode();
        }
        if (getIsTestObject() != null) {
            _hashCode += getIsTestObject().hashCode();
        }
        if (getSalesForceObjectID() != null) {
            _hashCode += getSalesForceObjectID().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getParameters() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getParameters());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getParameters(), i);
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
        new org.apache.axis.description.TypeDesc(SendDefinitionList.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendDefinitionList"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("filterDefinition");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FilterDefinition"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FilterDefinition"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isTestObject");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsTestObject"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("salesForceObjectID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SalesForceObjectID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parameters");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Parameters"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "APIProperty"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Parameter"));
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
