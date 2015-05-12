/**
 * SMSTriggeredSendDefinition.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class SMSTriggeredSendDefinition  extends com.exacttarget.wsdl.partnerAPI.SendDefinition  implements java.io.Serializable {
    private com.exacttarget.wsdl.partnerAPI.List publication;

    private com.exacttarget.wsdl.partnerAPI.DataExtension dataExtension;

    private com.exacttarget.wsdl.partnerAPI.ContentArea content;

    private java.lang.Boolean sendToList;

    public SMSTriggeredSendDefinition() {
    }

    public SMSTriggeredSendDefinition(
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
           java.lang.Integer categoryID,
           com.exacttarget.wsdl.partnerAPI.SendClassification sendClassification,
           com.exacttarget.wsdl.partnerAPI.SenderProfile senderProfile,
           java.lang.String fromName,
           java.lang.String fromAddress,
           com.exacttarget.wsdl.partnerAPI.DeliveryProfile deliveryProfile,
           com.exacttarget.wsdl.partnerAPI.DeliveryProfileSourceAddressTypeEnum sourceAddressType,
           com.exacttarget.wsdl.partnerAPI.PrivateIP privateIP,
           com.exacttarget.wsdl.partnerAPI.DeliveryProfileDomainTypeEnum domainType,
           com.exacttarget.wsdl.partnerAPI.PrivateDomain privateDomain,
           com.exacttarget.wsdl.partnerAPI.SalutationSourceEnum headerSalutationSource,
           com.exacttarget.wsdl.partnerAPI.ContentArea headerContentArea,
           com.exacttarget.wsdl.partnerAPI.SalutationSourceEnum footerSalutationSource,
           com.exacttarget.wsdl.partnerAPI.ContentArea footerContentArea,
           java.lang.Boolean suppressTracking,
           java.lang.Boolean isSendLogging,
           com.exacttarget.wsdl.partnerAPI.List publication,
           com.exacttarget.wsdl.partnerAPI.DataExtension dataExtension,
           com.exacttarget.wsdl.partnerAPI.ContentArea content,
           java.lang.Boolean sendToList) {
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
            interactionObjectID,
            categoryID,
            sendClassification,
            senderProfile,
            fromName,
            fromAddress,
            deliveryProfile,
            sourceAddressType,
            privateIP,
            domainType,
            privateDomain,
            headerSalutationSource,
            headerContentArea,
            footerSalutationSource,
            footerContentArea,
            suppressTracking,
            isSendLogging);
        this.publication = publication;
        this.dataExtension = dataExtension;
        this.content = content;
        this.sendToList = sendToList;
    }


    /**
     * Gets the publication value for this SMSTriggeredSendDefinition.
     * 
     * @return publication
     */
    public com.exacttarget.wsdl.partnerAPI.List getPublication() {
        return publication;
    }


    /**
     * Sets the publication value for this SMSTriggeredSendDefinition.
     * 
     * @param publication
     */
    public void setPublication(com.exacttarget.wsdl.partnerAPI.List publication) {
        this.publication = publication;
    }


    /**
     * Gets the dataExtension value for this SMSTriggeredSendDefinition.
     * 
     * @return dataExtension
     */
    public com.exacttarget.wsdl.partnerAPI.DataExtension getDataExtension() {
        return dataExtension;
    }


    /**
     * Sets the dataExtension value for this SMSTriggeredSendDefinition.
     * 
     * @param dataExtension
     */
    public void setDataExtension(com.exacttarget.wsdl.partnerAPI.DataExtension dataExtension) {
        this.dataExtension = dataExtension;
    }


    /**
     * Gets the content value for this SMSTriggeredSendDefinition.
     * 
     * @return content
     */
    public com.exacttarget.wsdl.partnerAPI.ContentArea getContent() {
        return content;
    }


    /**
     * Sets the content value for this SMSTriggeredSendDefinition.
     * 
     * @param content
     */
    public void setContent(com.exacttarget.wsdl.partnerAPI.ContentArea content) {
        this.content = content;
    }


    /**
     * Gets the sendToList value for this SMSTriggeredSendDefinition.
     * 
     * @return sendToList
     */
    public java.lang.Boolean getSendToList() {
        return sendToList;
    }


    /**
     * Sets the sendToList value for this SMSTriggeredSendDefinition.
     * 
     * @param sendToList
     */
    public void setSendToList(java.lang.Boolean sendToList) {
        this.sendToList = sendToList;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SMSTriggeredSendDefinition)) return false;
        SMSTriggeredSendDefinition other = (SMSTriggeredSendDefinition) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.publication==null && other.getPublication()==null) || 
             (this.publication!=null &&
              this.publication.equals(other.getPublication()))) &&
            ((this.dataExtension==null && other.getDataExtension()==null) || 
             (this.dataExtension!=null &&
              this.dataExtension.equals(other.getDataExtension()))) &&
            ((this.content==null && other.getContent()==null) || 
             (this.content!=null &&
              this.content.equals(other.getContent()))) &&
            ((this.sendToList==null && other.getSendToList()==null) || 
             (this.sendToList!=null &&
              this.sendToList.equals(other.getSendToList())));
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
        if (getPublication() != null) {
            _hashCode += getPublication().hashCode();
        }
        if (getDataExtension() != null) {
            _hashCode += getDataExtension().hashCode();
        }
        if (getContent() != null) {
            _hashCode += getContent().hashCode();
        }
        if (getSendToList() != null) {
            _hashCode += getSendToList().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SMSTriggeredSendDefinition.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SMSTriggeredSendDefinition"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("publication");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Publication"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "List"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("content");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Content"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ContentArea"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sendToList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendToList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
