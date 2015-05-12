/**
 * QueryDefinition.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class QueryDefinition  extends com.exacttarget.wsdl.partnerAPI.InteractionDefinition  implements java.io.Serializable {
    private java.lang.String queryText;

    private java.lang.String targetType;

    private com.exacttarget.wsdl.partnerAPI.InteractionBaseObject dataExtensionTarget;

    private java.lang.String targetUpdateType;

    private java.lang.String fileSpec;

    private java.lang.String fileType;

    private java.lang.String status;

    private java.lang.Integer categoryID;

    public QueryDefinition() {
    }

    public QueryDefinition(
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
           java.lang.String name,
           java.lang.String description,
           java.lang.String keyword,
           java.lang.String interactionObjectID,
           java.lang.String queryText,
           java.lang.String targetType,
           com.exacttarget.wsdl.partnerAPI.InteractionBaseObject dataExtensionTarget,
           java.lang.String targetUpdateType,
           java.lang.String fileSpec,
           java.lang.String fileType,
           java.lang.String status,
           java.lang.Integer categoryID) {
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
            description,
            keyword,
            interactionObjectID);
        this.queryText = queryText;
        this.targetType = targetType;
        this.dataExtensionTarget = dataExtensionTarget;
        this.targetUpdateType = targetUpdateType;
        this.fileSpec = fileSpec;
        this.fileType = fileType;
        this.status = status;
        this.categoryID = categoryID;
    }


    /**
     * Gets the queryText value for this QueryDefinition.
     * 
     * @return queryText
     */
    public java.lang.String getQueryText() {
        return queryText;
    }


    /**
     * Sets the queryText value for this QueryDefinition.
     * 
     * @param queryText
     */
    public void setQueryText(java.lang.String queryText) {
        this.queryText = queryText;
    }


    /**
     * Gets the targetType value for this QueryDefinition.
     * 
     * @return targetType
     */
    public java.lang.String getTargetType() {
        return targetType;
    }


    /**
     * Sets the targetType value for this QueryDefinition.
     * 
     * @param targetType
     */
    public void setTargetType(java.lang.String targetType) {
        this.targetType = targetType;
    }


    /**
     * Gets the dataExtensionTarget value for this QueryDefinition.
     * 
     * @return dataExtensionTarget
     */
    public com.exacttarget.wsdl.partnerAPI.InteractionBaseObject getDataExtensionTarget() {
        return dataExtensionTarget;
    }


    /**
     * Sets the dataExtensionTarget value for this QueryDefinition.
     * 
     * @param dataExtensionTarget
     */
    public void setDataExtensionTarget(com.exacttarget.wsdl.partnerAPI.InteractionBaseObject dataExtensionTarget) {
        this.dataExtensionTarget = dataExtensionTarget;
    }


    /**
     * Gets the targetUpdateType value for this QueryDefinition.
     * 
     * @return targetUpdateType
     */
    public java.lang.String getTargetUpdateType() {
        return targetUpdateType;
    }


    /**
     * Sets the targetUpdateType value for this QueryDefinition.
     * 
     * @param targetUpdateType
     */
    public void setTargetUpdateType(java.lang.String targetUpdateType) {
        this.targetUpdateType = targetUpdateType;
    }


    /**
     * Gets the fileSpec value for this QueryDefinition.
     * 
     * @return fileSpec
     */
    public java.lang.String getFileSpec() {
        return fileSpec;
    }


    /**
     * Sets the fileSpec value for this QueryDefinition.
     * 
     * @param fileSpec
     */
    public void setFileSpec(java.lang.String fileSpec) {
        this.fileSpec = fileSpec;
    }


    /**
     * Gets the fileType value for this QueryDefinition.
     * 
     * @return fileType
     */
    public java.lang.String getFileType() {
        return fileType;
    }


    /**
     * Sets the fileType value for this QueryDefinition.
     * 
     * @param fileType
     */
    public void setFileType(java.lang.String fileType) {
        this.fileType = fileType;
    }


    /**
     * Gets the status value for this QueryDefinition.
     * 
     * @return status
     */
    public java.lang.String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this QueryDefinition.
     * 
     * @param status
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }


    /**
     * Gets the categoryID value for this QueryDefinition.
     * 
     * @return categoryID
     */
    public java.lang.Integer getCategoryID() {
        return categoryID;
    }


    /**
     * Sets the categoryID value for this QueryDefinition.
     * 
     * @param categoryID
     */
    public void setCategoryID(java.lang.Integer categoryID) {
        this.categoryID = categoryID;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof QueryDefinition)) return false;
        QueryDefinition other = (QueryDefinition) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.queryText==null && other.getQueryText()==null) || 
             (this.queryText!=null &&
              this.queryText.equals(other.getQueryText()))) &&
            ((this.targetType==null && other.getTargetType()==null) || 
             (this.targetType!=null &&
              this.targetType.equals(other.getTargetType()))) &&
            ((this.dataExtensionTarget==null && other.getDataExtensionTarget()==null) || 
             (this.dataExtensionTarget!=null &&
              this.dataExtensionTarget.equals(other.getDataExtensionTarget()))) &&
            ((this.targetUpdateType==null && other.getTargetUpdateType()==null) || 
             (this.targetUpdateType!=null &&
              this.targetUpdateType.equals(other.getTargetUpdateType()))) &&
            ((this.fileSpec==null && other.getFileSpec()==null) || 
             (this.fileSpec!=null &&
              this.fileSpec.equals(other.getFileSpec()))) &&
            ((this.fileType==null && other.getFileType()==null) || 
             (this.fileType!=null &&
              this.fileType.equals(other.getFileType()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.categoryID==null && other.getCategoryID()==null) || 
             (this.categoryID!=null &&
              this.categoryID.equals(other.getCategoryID())));
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
        if (getQueryText() != null) {
            _hashCode += getQueryText().hashCode();
        }
        if (getTargetType() != null) {
            _hashCode += getTargetType().hashCode();
        }
        if (getDataExtensionTarget() != null) {
            _hashCode += getDataExtensionTarget().hashCode();
        }
        if (getTargetUpdateType() != null) {
            _hashCode += getTargetUpdateType().hashCode();
        }
        if (getFileSpec() != null) {
            _hashCode += getFileSpec().hashCode();
        }
        if (getFileType() != null) {
            _hashCode += getFileType().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getCategoryID() != null) {
            _hashCode += getCategoryID().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(QueryDefinition.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "QueryDefinition"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("queryText");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "QueryText"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("targetType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TargetType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataExtensionTarget");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataExtensionTarget"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "InteractionBaseObject"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("targetUpdateType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TargetUpdateType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fileSpec");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FileSpec"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fileType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FileType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("categoryID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "CategoryID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
