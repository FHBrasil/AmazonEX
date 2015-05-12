/**
 * SendClassification.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class SendClassification  extends com.exacttarget.wsdl.partnerAPI.APIObject  implements java.io.Serializable {
    private com.exacttarget.wsdl.partnerAPI.SendClassificationTypeEnum sendClassificationType;

    private java.lang.String name;

    private java.lang.String description;

    private com.exacttarget.wsdl.partnerAPI.SenderProfile senderProfile;

    private com.exacttarget.wsdl.partnerAPI.DeliveryProfile deliveryProfile;

    private java.lang.Boolean honorPublicationListOptOutsForTransactionalSends;

    private com.exacttarget.wsdl.partnerAPI.SendPriorityEnum sendPriority;

    private java.lang.Boolean archiveEmail;

    public SendClassification() {
    }

    public SendClassification(
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
           com.exacttarget.wsdl.partnerAPI.SendClassificationTypeEnum sendClassificationType,
           java.lang.String name,
           java.lang.String description,
           com.exacttarget.wsdl.partnerAPI.SenderProfile senderProfile,
           com.exacttarget.wsdl.partnerAPI.DeliveryProfile deliveryProfile,
           java.lang.Boolean honorPublicationListOptOutsForTransactionalSends,
           com.exacttarget.wsdl.partnerAPI.SendPriorityEnum sendPriority,
           java.lang.Boolean archiveEmail) {
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
        this.sendClassificationType = sendClassificationType;
        this.name = name;
        this.description = description;
        this.senderProfile = senderProfile;
        this.deliveryProfile = deliveryProfile;
        this.honorPublicationListOptOutsForTransactionalSends = honorPublicationListOptOutsForTransactionalSends;
        this.sendPriority = sendPriority;
        this.archiveEmail = archiveEmail;
    }


    /**
     * Gets the sendClassificationType value for this SendClassification.
     * 
     * @return sendClassificationType
     */
    public com.exacttarget.wsdl.partnerAPI.SendClassificationTypeEnum getSendClassificationType() {
        return sendClassificationType;
    }


    /**
     * Sets the sendClassificationType value for this SendClassification.
     * 
     * @param sendClassificationType
     */
    public void setSendClassificationType(com.exacttarget.wsdl.partnerAPI.SendClassificationTypeEnum sendClassificationType) {
        this.sendClassificationType = sendClassificationType;
    }


    /**
     * Gets the name value for this SendClassification.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this SendClassification.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the description value for this SendClassification.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this SendClassification.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the senderProfile value for this SendClassification.
     * 
     * @return senderProfile
     */
    public com.exacttarget.wsdl.partnerAPI.SenderProfile getSenderProfile() {
        return senderProfile;
    }


    /**
     * Sets the senderProfile value for this SendClassification.
     * 
     * @param senderProfile
     */
    public void setSenderProfile(com.exacttarget.wsdl.partnerAPI.SenderProfile senderProfile) {
        this.senderProfile = senderProfile;
    }


    /**
     * Gets the deliveryProfile value for this SendClassification.
     * 
     * @return deliveryProfile
     */
    public com.exacttarget.wsdl.partnerAPI.DeliveryProfile getDeliveryProfile() {
        return deliveryProfile;
    }


    /**
     * Sets the deliveryProfile value for this SendClassification.
     * 
     * @param deliveryProfile
     */
    public void setDeliveryProfile(com.exacttarget.wsdl.partnerAPI.DeliveryProfile deliveryProfile) {
        this.deliveryProfile = deliveryProfile;
    }


    /**
     * Gets the honorPublicationListOptOutsForTransactionalSends value for this SendClassification.
     * 
     * @return honorPublicationListOptOutsForTransactionalSends
     */
    public java.lang.Boolean getHonorPublicationListOptOutsForTransactionalSends() {
        return honorPublicationListOptOutsForTransactionalSends;
    }


    /**
     * Sets the honorPublicationListOptOutsForTransactionalSends value for this SendClassification.
     * 
     * @param honorPublicationListOptOutsForTransactionalSends
     */
    public void setHonorPublicationListOptOutsForTransactionalSends(java.lang.Boolean honorPublicationListOptOutsForTransactionalSends) {
        this.honorPublicationListOptOutsForTransactionalSends = honorPublicationListOptOutsForTransactionalSends;
    }


    /**
     * Gets the sendPriority value for this SendClassification.
     * 
     * @return sendPriority
     */
    public com.exacttarget.wsdl.partnerAPI.SendPriorityEnum getSendPriority() {
        return sendPriority;
    }


    /**
     * Sets the sendPriority value for this SendClassification.
     * 
     * @param sendPriority
     */
    public void setSendPriority(com.exacttarget.wsdl.partnerAPI.SendPriorityEnum sendPriority) {
        this.sendPriority = sendPriority;
    }


    /**
     * Gets the archiveEmail value for this SendClassification.
     * 
     * @return archiveEmail
     */
    public java.lang.Boolean getArchiveEmail() {
        return archiveEmail;
    }


    /**
     * Sets the archiveEmail value for this SendClassification.
     * 
     * @param archiveEmail
     */
    public void setArchiveEmail(java.lang.Boolean archiveEmail) {
        this.archiveEmail = archiveEmail;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SendClassification)) return false;
        SendClassification other = (SendClassification) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.sendClassificationType==null && other.getSendClassificationType()==null) || 
             (this.sendClassificationType!=null &&
              this.sendClassificationType.equals(other.getSendClassificationType()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.senderProfile==null && other.getSenderProfile()==null) || 
             (this.senderProfile!=null &&
              this.senderProfile.equals(other.getSenderProfile()))) &&
            ((this.deliveryProfile==null && other.getDeliveryProfile()==null) || 
             (this.deliveryProfile!=null &&
              this.deliveryProfile.equals(other.getDeliveryProfile()))) &&
            ((this.honorPublicationListOptOutsForTransactionalSends==null && other.getHonorPublicationListOptOutsForTransactionalSends()==null) || 
             (this.honorPublicationListOptOutsForTransactionalSends!=null &&
              this.honorPublicationListOptOutsForTransactionalSends.equals(other.getHonorPublicationListOptOutsForTransactionalSends()))) &&
            ((this.sendPriority==null && other.getSendPriority()==null) || 
             (this.sendPriority!=null &&
              this.sendPriority.equals(other.getSendPriority()))) &&
            ((this.archiveEmail==null && other.getArchiveEmail()==null) || 
             (this.archiveEmail!=null &&
              this.archiveEmail.equals(other.getArchiveEmail())));
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
        if (getSendClassificationType() != null) {
            _hashCode += getSendClassificationType().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getSenderProfile() != null) {
            _hashCode += getSenderProfile().hashCode();
        }
        if (getDeliveryProfile() != null) {
            _hashCode += getDeliveryProfile().hashCode();
        }
        if (getHonorPublicationListOptOutsForTransactionalSends() != null) {
            _hashCode += getHonorPublicationListOptOutsForTransactionalSends().hashCode();
        }
        if (getSendPriority() != null) {
            _hashCode += getSendPriority().hashCode();
        }
        if (getArchiveEmail() != null) {
            _hashCode += getArchiveEmail().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SendClassification.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendClassification"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sendClassificationType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendClassificationType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendClassificationTypeEnum"));
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
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("senderProfile");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SenderProfile"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SenderProfile"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deliveryProfile");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DeliveryProfile"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DeliveryProfile"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("honorPublicationListOptOutsForTransactionalSends");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "HonorPublicationListOptOutsForTransactionalSends"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sendPriority");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendPriority"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendPriorityEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("archiveEmail");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ArchiveEmail"));
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
