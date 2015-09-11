/**
 * SMSTriggeredSendDefinition.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.flieger.exacttarget.wsdl.api;

public class SMSTriggeredSendDefinition  extends br.flieger.exacttarget.wsdl.api.SendDefinition  implements java.io.Serializable {
    private br.flieger.exacttarget.wsdl.api.List publication;

    private br.flieger.exacttarget.wsdl.api.DataExtension dataExtension;

    private br.flieger.exacttarget.wsdl.api.ContentArea content;

    private java.lang.Boolean sendToList;

    public SMSTriggeredSendDefinition() {
    }

    public SMSTriggeredSendDefinition(
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
           java.lang.String description,
           java.lang.String keyword,
           java.lang.String interactionObjectID,
           java.lang.Integer categoryID,
           br.flieger.exacttarget.wsdl.api.SendClassification sendClassification,
           br.flieger.exacttarget.wsdl.api.SenderProfile senderProfile,
           java.lang.String fromName,
           java.lang.String fromAddress,
           br.flieger.exacttarget.wsdl.api.DeliveryProfile deliveryProfile,
           br.flieger.exacttarget.wsdl.api.DeliveryProfileSourceAddressTypeEnum sourceAddressType,
           br.flieger.exacttarget.wsdl.api.PrivateIP privateIP,
           br.flieger.exacttarget.wsdl.api.DeliveryProfileDomainTypeEnum domainType,
           br.flieger.exacttarget.wsdl.api.PrivateDomain privateDomain,
           br.flieger.exacttarget.wsdl.api.SalutationSourceEnum headerSalutationSource,
           br.flieger.exacttarget.wsdl.api.ContentArea headerContentArea,
           br.flieger.exacttarget.wsdl.api.SalutationSourceEnum footerSalutationSource,
           br.flieger.exacttarget.wsdl.api.ContentArea footerContentArea,
           java.lang.Boolean suppressTracking,
           java.lang.Boolean isSendLogging,
           br.flieger.exacttarget.wsdl.api.List publication,
           br.flieger.exacttarget.wsdl.api.DataExtension dataExtension,
           br.flieger.exacttarget.wsdl.api.ContentArea content,
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
    public br.flieger.exacttarget.wsdl.api.List getPublication() {
        return publication;
    }


    /**
     * Sets the publication value for this SMSTriggeredSendDefinition.
     * 
     * @param publication
     */
    public void setPublication(br.flieger.exacttarget.wsdl.api.List publication) {
        this.publication = publication;
    }


    /**
     * Gets the dataExtension value for this SMSTriggeredSendDefinition.
     * 
     * @return dataExtension
     */
    public br.flieger.exacttarget.wsdl.api.DataExtension getDataExtension() {
        return dataExtension;
    }


    /**
     * Sets the dataExtension value for this SMSTriggeredSendDefinition.
     * 
     * @param dataExtension
     */
    public void setDataExtension(br.flieger.exacttarget.wsdl.api.DataExtension dataExtension) {
        this.dataExtension = dataExtension;
    }


    /**
     * Gets the content value for this SMSTriggeredSendDefinition.
     * 
     * @return content
     */
    public br.flieger.exacttarget.wsdl.api.ContentArea getContent() {
        return content;
    }


    /**
     * Sets the content value for this SMSTriggeredSendDefinition.
     * 
     * @param content
     */
    public void setContent(br.flieger.exacttarget.wsdl.api.ContentArea content) {
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
