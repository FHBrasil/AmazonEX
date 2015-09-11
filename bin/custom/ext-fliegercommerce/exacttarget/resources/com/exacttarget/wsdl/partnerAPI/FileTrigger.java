/**
 * FileTrigger.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class FileTrigger  extends com.exacttarget.wsdl.partnerAPI.APIObject  implements java.io.Serializable {
    private java.lang.String externalReference;

    private java.lang.String type;

    private java.lang.String status;

    private java.lang.String statusMessage;

    private java.lang.String requestParameterDetail;

    private java.lang.String responseControlManifest;

    private java.lang.String fileName;

    private java.lang.String description;

    private java.lang.String name;

    private java.util.Calendar lastPullDate;

    private java.util.Calendar scheduledDate;

    private java.lang.Boolean isActive;

    private java.lang.String fileTriggerProgramID;

    public FileTrigger() {
    }

    public FileTrigger(
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
           java.lang.String externalReference,
           java.lang.String type,
           java.lang.String status,
           java.lang.String statusMessage,
           java.lang.String requestParameterDetail,
           java.lang.String responseControlManifest,
           java.lang.String fileName,
           java.lang.String description,
           java.lang.String name,
           java.util.Calendar lastPullDate,
           java.util.Calendar scheduledDate,
           java.lang.Boolean isActive,
           java.lang.String fileTriggerProgramID) {
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
        this.externalReference = externalReference;
        this.type = type;
        this.status = status;
        this.statusMessage = statusMessage;
        this.requestParameterDetail = requestParameterDetail;
        this.responseControlManifest = responseControlManifest;
        this.fileName = fileName;
        this.description = description;
        this.name = name;
        this.lastPullDate = lastPullDate;
        this.scheduledDate = scheduledDate;
        this.isActive = isActive;
        this.fileTriggerProgramID = fileTriggerProgramID;
    }


    /**
     * Gets the externalReference value for this FileTrigger.
     * 
     * @return externalReference
     */
    public java.lang.String getExternalReference() {
        return externalReference;
    }


    /**
     * Sets the externalReference value for this FileTrigger.
     * 
     * @param externalReference
     */
    public void setExternalReference(java.lang.String externalReference) {
        this.externalReference = externalReference;
    }


    /**
     * Gets the type value for this FileTrigger.
     * 
     * @return type
     */
    public java.lang.String getType() {
        return type;
    }


    /**
     * Sets the type value for this FileTrigger.
     * 
     * @param type
     */
    public void setType(java.lang.String type) {
        this.type = type;
    }


    /**
     * Gets the status value for this FileTrigger.
     * 
     * @return status
     */
    public java.lang.String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this FileTrigger.
     * 
     * @param status
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }


    /**
     * Gets the statusMessage value for this FileTrigger.
     * 
     * @return statusMessage
     */
    public java.lang.String getStatusMessage() {
        return statusMessage;
    }


    /**
     * Sets the statusMessage value for this FileTrigger.
     * 
     * @param statusMessage
     */
    public void setStatusMessage(java.lang.String statusMessage) {
        this.statusMessage = statusMessage;
    }


    /**
     * Gets the requestParameterDetail value for this FileTrigger.
     * 
     * @return requestParameterDetail
     */
    public java.lang.String getRequestParameterDetail() {
        return requestParameterDetail;
    }


    /**
     * Sets the requestParameterDetail value for this FileTrigger.
     * 
     * @param requestParameterDetail
     */
    public void setRequestParameterDetail(java.lang.String requestParameterDetail) {
        this.requestParameterDetail = requestParameterDetail;
    }


    /**
     * Gets the responseControlManifest value for this FileTrigger.
     * 
     * @return responseControlManifest
     */
    public java.lang.String getResponseControlManifest() {
        return responseControlManifest;
    }


    /**
     * Sets the responseControlManifest value for this FileTrigger.
     * 
     * @param responseControlManifest
     */
    public void setResponseControlManifest(java.lang.String responseControlManifest) {
        this.responseControlManifest = responseControlManifest;
    }


    /**
     * Gets the fileName value for this FileTrigger.
     * 
     * @return fileName
     */
    public java.lang.String getFileName() {
        return fileName;
    }


    /**
     * Sets the fileName value for this FileTrigger.
     * 
     * @param fileName
     */
    public void setFileName(java.lang.String fileName) {
        this.fileName = fileName;
    }


    /**
     * Gets the description value for this FileTrigger.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this FileTrigger.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the name value for this FileTrigger.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this FileTrigger.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the lastPullDate value for this FileTrigger.
     * 
     * @return lastPullDate
     */
    public java.util.Calendar getLastPullDate() {
        return lastPullDate;
    }


    /**
     * Sets the lastPullDate value for this FileTrigger.
     * 
     * @param lastPullDate
     */
    public void setLastPullDate(java.util.Calendar lastPullDate) {
        this.lastPullDate = lastPullDate;
    }


    /**
     * Gets the scheduledDate value for this FileTrigger.
     * 
     * @return scheduledDate
     */
    public java.util.Calendar getScheduledDate() {
        return scheduledDate;
    }


    /**
     * Sets the scheduledDate value for this FileTrigger.
     * 
     * @param scheduledDate
     */
    public void setScheduledDate(java.util.Calendar scheduledDate) {
        this.scheduledDate = scheduledDate;
    }


    /**
     * Gets the isActive value for this FileTrigger.
     * 
     * @return isActive
     */
    public java.lang.Boolean getIsActive() {
        return isActive;
    }


    /**
     * Sets the isActive value for this FileTrigger.
     * 
     * @param isActive
     */
    public void setIsActive(java.lang.Boolean isActive) {
        this.isActive = isActive;
    }


    /**
     * Gets the fileTriggerProgramID value for this FileTrigger.
     * 
     * @return fileTriggerProgramID
     */
    public java.lang.String getFileTriggerProgramID() {
        return fileTriggerProgramID;
    }


    /**
     * Sets the fileTriggerProgramID value for this FileTrigger.
     * 
     * @param fileTriggerProgramID
     */
    public void setFileTriggerProgramID(java.lang.String fileTriggerProgramID) {
        this.fileTriggerProgramID = fileTriggerProgramID;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FileTrigger)) return false;
        FileTrigger other = (FileTrigger) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.externalReference==null && other.getExternalReference()==null) || 
             (this.externalReference!=null &&
              this.externalReference.equals(other.getExternalReference()))) &&
            ((this.type==null && other.getType()==null) || 
             (this.type!=null &&
              this.type.equals(other.getType()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.statusMessage==null && other.getStatusMessage()==null) || 
             (this.statusMessage!=null &&
              this.statusMessage.equals(other.getStatusMessage()))) &&
            ((this.requestParameterDetail==null && other.getRequestParameterDetail()==null) || 
             (this.requestParameterDetail!=null &&
              this.requestParameterDetail.equals(other.getRequestParameterDetail()))) &&
            ((this.responseControlManifest==null && other.getResponseControlManifest()==null) || 
             (this.responseControlManifest!=null &&
              this.responseControlManifest.equals(other.getResponseControlManifest()))) &&
            ((this.fileName==null && other.getFileName()==null) || 
             (this.fileName!=null &&
              this.fileName.equals(other.getFileName()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.lastPullDate==null && other.getLastPullDate()==null) || 
             (this.lastPullDate!=null &&
              this.lastPullDate.equals(other.getLastPullDate()))) &&
            ((this.scheduledDate==null && other.getScheduledDate()==null) || 
             (this.scheduledDate!=null &&
              this.scheduledDate.equals(other.getScheduledDate()))) &&
            ((this.isActive==null && other.getIsActive()==null) || 
             (this.isActive!=null &&
              this.isActive.equals(other.getIsActive()))) &&
            ((this.fileTriggerProgramID==null && other.getFileTriggerProgramID()==null) || 
             (this.fileTriggerProgramID!=null &&
              this.fileTriggerProgramID.equals(other.getFileTriggerProgramID())));
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
        if (getExternalReference() != null) {
            _hashCode += getExternalReference().hashCode();
        }
        if (getType() != null) {
            _hashCode += getType().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getStatusMessage() != null) {
            _hashCode += getStatusMessage().hashCode();
        }
        if (getRequestParameterDetail() != null) {
            _hashCode += getRequestParameterDetail().hashCode();
        }
        if (getResponseControlManifest() != null) {
            _hashCode += getResponseControlManifest().hashCode();
        }
        if (getFileName() != null) {
            _hashCode += getFileName().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getLastPullDate() != null) {
            _hashCode += getLastPullDate().hashCode();
        }
        if (getScheduledDate() != null) {
            _hashCode += getScheduledDate().hashCode();
        }
        if (getIsActive() != null) {
            _hashCode += getIsActive().hashCode();
        }
        if (getFileTriggerProgramID() != null) {
            _hashCode += getFileTriggerProgramID().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FileTrigger.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FileTrigger"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("externalReference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExternalReference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("type");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Type"));
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
        elemField.setFieldName("statusMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "StatusMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestParameterDetail");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RequestParameterDetail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("responseControlManifest");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ResponseControlManifest"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fileName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FileName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Description"));
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
        elemField.setFieldName("lastPullDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "LastPullDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("scheduledDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ScheduledDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isActive");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsActive"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fileTriggerProgramID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FileTriggerProgramID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
