/**
 * SenderProfile.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class SenderProfile  extends com.exacttarget.wsdl.partnerAPI.APIObject  implements java.io.Serializable {
    private java.lang.String name;

    private java.lang.String description;

    private java.lang.String fromName;

    private java.lang.String fromAddress;

    private java.lang.Boolean useDefaultRMMRules;

    private java.lang.String autoForwardToEmailAddress;

    private java.lang.String autoForwardToName;

    private java.lang.Boolean directForward;

    private com.exacttarget.wsdl.partnerAPI.TriggeredSendDefinition autoForwardTriggeredSend;

    private java.lang.Boolean autoReply;

    private com.exacttarget.wsdl.partnerAPI.TriggeredSendDefinition autoReplyTriggeredSend;

    private java.lang.String senderHeaderEmailAddress;

    private java.lang.String senderHeaderName;

    private java.lang.Short dataRetentionPeriodLength;

    private com.exacttarget.wsdl.partnerAPI.RecurrenceTypeEnum dataRetentionPeriodUnitOfMeasure;

    private com.exacttarget.wsdl.partnerAPI.APIObject replyManagementRuleSet;

    private java.lang.String replyToAddress;

    private java.lang.String replyToDisplayName;

    public SenderProfile() {
    }

    public SenderProfile(
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
           java.lang.String fromName,
           java.lang.String fromAddress,
           java.lang.Boolean useDefaultRMMRules,
           java.lang.String autoForwardToEmailAddress,
           java.lang.String autoForwardToName,
           java.lang.Boolean directForward,
           com.exacttarget.wsdl.partnerAPI.TriggeredSendDefinition autoForwardTriggeredSend,
           java.lang.Boolean autoReply,
           com.exacttarget.wsdl.partnerAPI.TriggeredSendDefinition autoReplyTriggeredSend,
           java.lang.String senderHeaderEmailAddress,
           java.lang.String senderHeaderName,
           java.lang.Short dataRetentionPeriodLength,
           com.exacttarget.wsdl.partnerAPI.RecurrenceTypeEnum dataRetentionPeriodUnitOfMeasure,
           com.exacttarget.wsdl.partnerAPI.APIObject replyManagementRuleSet,
           java.lang.String replyToAddress,
           java.lang.String replyToDisplayName) {
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
        this.name = name;
        this.description = description;
        this.fromName = fromName;
        this.fromAddress = fromAddress;
        this.useDefaultRMMRules = useDefaultRMMRules;
        this.autoForwardToEmailAddress = autoForwardToEmailAddress;
        this.autoForwardToName = autoForwardToName;
        this.directForward = directForward;
        this.autoForwardTriggeredSend = autoForwardTriggeredSend;
        this.autoReply = autoReply;
        this.autoReplyTriggeredSend = autoReplyTriggeredSend;
        this.senderHeaderEmailAddress = senderHeaderEmailAddress;
        this.senderHeaderName = senderHeaderName;
        this.dataRetentionPeriodLength = dataRetentionPeriodLength;
        this.dataRetentionPeriodUnitOfMeasure = dataRetentionPeriodUnitOfMeasure;
        this.replyManagementRuleSet = replyManagementRuleSet;
        this.replyToAddress = replyToAddress;
        this.replyToDisplayName = replyToDisplayName;
    }


    /**
     * Gets the name value for this SenderProfile.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this SenderProfile.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the description value for this SenderProfile.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this SenderProfile.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the fromName value for this SenderProfile.
     * 
     * @return fromName
     */
    public java.lang.String getFromName() {
        return fromName;
    }


    /**
     * Sets the fromName value for this SenderProfile.
     * 
     * @param fromName
     */
    public void setFromName(java.lang.String fromName) {
        this.fromName = fromName;
    }


    /**
     * Gets the fromAddress value for this SenderProfile.
     * 
     * @return fromAddress
     */
    public java.lang.String getFromAddress() {
        return fromAddress;
    }


    /**
     * Sets the fromAddress value for this SenderProfile.
     * 
     * @param fromAddress
     */
    public void setFromAddress(java.lang.String fromAddress) {
        this.fromAddress = fromAddress;
    }


    /**
     * Gets the useDefaultRMMRules value for this SenderProfile.
     * 
     * @return useDefaultRMMRules
     */
    public java.lang.Boolean getUseDefaultRMMRules() {
        return useDefaultRMMRules;
    }


    /**
     * Sets the useDefaultRMMRules value for this SenderProfile.
     * 
     * @param useDefaultRMMRules
     */
    public void setUseDefaultRMMRules(java.lang.Boolean useDefaultRMMRules) {
        this.useDefaultRMMRules = useDefaultRMMRules;
    }


    /**
     * Gets the autoForwardToEmailAddress value for this SenderProfile.
     * 
     * @return autoForwardToEmailAddress
     */
    public java.lang.String getAutoForwardToEmailAddress() {
        return autoForwardToEmailAddress;
    }


    /**
     * Sets the autoForwardToEmailAddress value for this SenderProfile.
     * 
     * @param autoForwardToEmailAddress
     */
    public void setAutoForwardToEmailAddress(java.lang.String autoForwardToEmailAddress) {
        this.autoForwardToEmailAddress = autoForwardToEmailAddress;
    }


    /**
     * Gets the autoForwardToName value for this SenderProfile.
     * 
     * @return autoForwardToName
     */
    public java.lang.String getAutoForwardToName() {
        return autoForwardToName;
    }


    /**
     * Sets the autoForwardToName value for this SenderProfile.
     * 
     * @param autoForwardToName
     */
    public void setAutoForwardToName(java.lang.String autoForwardToName) {
        this.autoForwardToName = autoForwardToName;
    }


    /**
     * Gets the directForward value for this SenderProfile.
     * 
     * @return directForward
     */
    public java.lang.Boolean getDirectForward() {
        return directForward;
    }


    /**
     * Sets the directForward value for this SenderProfile.
     * 
     * @param directForward
     */
    public void setDirectForward(java.lang.Boolean directForward) {
        this.directForward = directForward;
    }


    /**
     * Gets the autoForwardTriggeredSend value for this SenderProfile.
     * 
     * @return autoForwardTriggeredSend
     */
    public com.exacttarget.wsdl.partnerAPI.TriggeredSendDefinition getAutoForwardTriggeredSend() {
        return autoForwardTriggeredSend;
    }


    /**
     * Sets the autoForwardTriggeredSend value for this SenderProfile.
     * 
     * @param autoForwardTriggeredSend
     */
    public void setAutoForwardTriggeredSend(com.exacttarget.wsdl.partnerAPI.TriggeredSendDefinition autoForwardTriggeredSend) {
        this.autoForwardTriggeredSend = autoForwardTriggeredSend;
    }


    /**
     * Gets the autoReply value for this SenderProfile.
     * 
     * @return autoReply
     */
    public java.lang.Boolean getAutoReply() {
        return autoReply;
    }


    /**
     * Sets the autoReply value for this SenderProfile.
     * 
     * @param autoReply
     */
    public void setAutoReply(java.lang.Boolean autoReply) {
        this.autoReply = autoReply;
    }


    /**
     * Gets the autoReplyTriggeredSend value for this SenderProfile.
     * 
     * @return autoReplyTriggeredSend
     */
    public com.exacttarget.wsdl.partnerAPI.TriggeredSendDefinition getAutoReplyTriggeredSend() {
        return autoReplyTriggeredSend;
    }


    /**
     * Sets the autoReplyTriggeredSend value for this SenderProfile.
     * 
     * @param autoReplyTriggeredSend
     */
    public void setAutoReplyTriggeredSend(com.exacttarget.wsdl.partnerAPI.TriggeredSendDefinition autoReplyTriggeredSend) {
        this.autoReplyTriggeredSend = autoReplyTriggeredSend;
    }


    /**
     * Gets the senderHeaderEmailAddress value for this SenderProfile.
     * 
     * @return senderHeaderEmailAddress
     */
    public java.lang.String getSenderHeaderEmailAddress() {
        return senderHeaderEmailAddress;
    }


    /**
     * Sets the senderHeaderEmailAddress value for this SenderProfile.
     * 
     * @param senderHeaderEmailAddress
     */
    public void setSenderHeaderEmailAddress(java.lang.String senderHeaderEmailAddress) {
        this.senderHeaderEmailAddress = senderHeaderEmailAddress;
    }


    /**
     * Gets the senderHeaderName value for this SenderProfile.
     * 
     * @return senderHeaderName
     */
    public java.lang.String getSenderHeaderName() {
        return senderHeaderName;
    }


    /**
     * Sets the senderHeaderName value for this SenderProfile.
     * 
     * @param senderHeaderName
     */
    public void setSenderHeaderName(java.lang.String senderHeaderName) {
        this.senderHeaderName = senderHeaderName;
    }


    /**
     * Gets the dataRetentionPeriodLength value for this SenderProfile.
     * 
     * @return dataRetentionPeriodLength
     */
    public java.lang.Short getDataRetentionPeriodLength() {
        return dataRetentionPeriodLength;
    }


    /**
     * Sets the dataRetentionPeriodLength value for this SenderProfile.
     * 
     * @param dataRetentionPeriodLength
     */
    public void setDataRetentionPeriodLength(java.lang.Short dataRetentionPeriodLength) {
        this.dataRetentionPeriodLength = dataRetentionPeriodLength;
    }


    /**
     * Gets the dataRetentionPeriodUnitOfMeasure value for this SenderProfile.
     * 
     * @return dataRetentionPeriodUnitOfMeasure
     */
    public com.exacttarget.wsdl.partnerAPI.RecurrenceTypeEnum getDataRetentionPeriodUnitOfMeasure() {
        return dataRetentionPeriodUnitOfMeasure;
    }


    /**
     * Sets the dataRetentionPeriodUnitOfMeasure value for this SenderProfile.
     * 
     * @param dataRetentionPeriodUnitOfMeasure
     */
    public void setDataRetentionPeriodUnitOfMeasure(com.exacttarget.wsdl.partnerAPI.RecurrenceTypeEnum dataRetentionPeriodUnitOfMeasure) {
        this.dataRetentionPeriodUnitOfMeasure = dataRetentionPeriodUnitOfMeasure;
    }


    /**
     * Gets the replyManagementRuleSet value for this SenderProfile.
     * 
     * @return replyManagementRuleSet
     */
    public com.exacttarget.wsdl.partnerAPI.APIObject getReplyManagementRuleSet() {
        return replyManagementRuleSet;
    }


    /**
     * Sets the replyManagementRuleSet value for this SenderProfile.
     * 
     * @param replyManagementRuleSet
     */
    public void setReplyManagementRuleSet(com.exacttarget.wsdl.partnerAPI.APIObject replyManagementRuleSet) {
        this.replyManagementRuleSet = replyManagementRuleSet;
    }


    /**
     * Gets the replyToAddress value for this SenderProfile.
     * 
     * @return replyToAddress
     */
    public java.lang.String getReplyToAddress() {
        return replyToAddress;
    }


    /**
     * Sets the replyToAddress value for this SenderProfile.
     * 
     * @param replyToAddress
     */
    public void setReplyToAddress(java.lang.String replyToAddress) {
        this.replyToAddress = replyToAddress;
    }


    /**
     * Gets the replyToDisplayName value for this SenderProfile.
     * 
     * @return replyToDisplayName
     */
    public java.lang.String getReplyToDisplayName() {
        return replyToDisplayName;
    }


    /**
     * Sets the replyToDisplayName value for this SenderProfile.
     * 
     * @param replyToDisplayName
     */
    public void setReplyToDisplayName(java.lang.String replyToDisplayName) {
        this.replyToDisplayName = replyToDisplayName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SenderProfile)) return false;
        SenderProfile other = (SenderProfile) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.fromName==null && other.getFromName()==null) || 
             (this.fromName!=null &&
              this.fromName.equals(other.getFromName()))) &&
            ((this.fromAddress==null && other.getFromAddress()==null) || 
             (this.fromAddress!=null &&
              this.fromAddress.equals(other.getFromAddress()))) &&
            ((this.useDefaultRMMRules==null && other.getUseDefaultRMMRules()==null) || 
             (this.useDefaultRMMRules!=null &&
              this.useDefaultRMMRules.equals(other.getUseDefaultRMMRules()))) &&
            ((this.autoForwardToEmailAddress==null && other.getAutoForwardToEmailAddress()==null) || 
             (this.autoForwardToEmailAddress!=null &&
              this.autoForwardToEmailAddress.equals(other.getAutoForwardToEmailAddress()))) &&
            ((this.autoForwardToName==null && other.getAutoForwardToName()==null) || 
             (this.autoForwardToName!=null &&
              this.autoForwardToName.equals(other.getAutoForwardToName()))) &&
            ((this.directForward==null && other.getDirectForward()==null) || 
             (this.directForward!=null &&
              this.directForward.equals(other.getDirectForward()))) &&
            ((this.autoForwardTriggeredSend==null && other.getAutoForwardTriggeredSend()==null) || 
             (this.autoForwardTriggeredSend!=null &&
              this.autoForwardTriggeredSend.equals(other.getAutoForwardTriggeredSend()))) &&
            ((this.autoReply==null && other.getAutoReply()==null) || 
             (this.autoReply!=null &&
              this.autoReply.equals(other.getAutoReply()))) &&
            ((this.autoReplyTriggeredSend==null && other.getAutoReplyTriggeredSend()==null) || 
             (this.autoReplyTriggeredSend!=null &&
              this.autoReplyTriggeredSend.equals(other.getAutoReplyTriggeredSend()))) &&
            ((this.senderHeaderEmailAddress==null && other.getSenderHeaderEmailAddress()==null) || 
             (this.senderHeaderEmailAddress!=null &&
              this.senderHeaderEmailAddress.equals(other.getSenderHeaderEmailAddress()))) &&
            ((this.senderHeaderName==null && other.getSenderHeaderName()==null) || 
             (this.senderHeaderName!=null &&
              this.senderHeaderName.equals(other.getSenderHeaderName()))) &&
            ((this.dataRetentionPeriodLength==null && other.getDataRetentionPeriodLength()==null) || 
             (this.dataRetentionPeriodLength!=null &&
              this.dataRetentionPeriodLength.equals(other.getDataRetentionPeriodLength()))) &&
            ((this.dataRetentionPeriodUnitOfMeasure==null && other.getDataRetentionPeriodUnitOfMeasure()==null) || 
             (this.dataRetentionPeriodUnitOfMeasure!=null &&
              this.dataRetentionPeriodUnitOfMeasure.equals(other.getDataRetentionPeriodUnitOfMeasure()))) &&
            ((this.replyManagementRuleSet==null && other.getReplyManagementRuleSet()==null) || 
             (this.replyManagementRuleSet!=null &&
              this.replyManagementRuleSet.equals(other.getReplyManagementRuleSet()))) &&
            ((this.replyToAddress==null && other.getReplyToAddress()==null) || 
             (this.replyToAddress!=null &&
              this.replyToAddress.equals(other.getReplyToAddress()))) &&
            ((this.replyToDisplayName==null && other.getReplyToDisplayName()==null) || 
             (this.replyToDisplayName!=null &&
              this.replyToDisplayName.equals(other.getReplyToDisplayName())));
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
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getFromName() != null) {
            _hashCode += getFromName().hashCode();
        }
        if (getFromAddress() != null) {
            _hashCode += getFromAddress().hashCode();
        }
        if (getUseDefaultRMMRules() != null) {
            _hashCode += getUseDefaultRMMRules().hashCode();
        }
        if (getAutoForwardToEmailAddress() != null) {
            _hashCode += getAutoForwardToEmailAddress().hashCode();
        }
        if (getAutoForwardToName() != null) {
            _hashCode += getAutoForwardToName().hashCode();
        }
        if (getDirectForward() != null) {
            _hashCode += getDirectForward().hashCode();
        }
        if (getAutoForwardTriggeredSend() != null) {
            _hashCode += getAutoForwardTriggeredSend().hashCode();
        }
        if (getAutoReply() != null) {
            _hashCode += getAutoReply().hashCode();
        }
        if (getAutoReplyTriggeredSend() != null) {
            _hashCode += getAutoReplyTriggeredSend().hashCode();
        }
        if (getSenderHeaderEmailAddress() != null) {
            _hashCode += getSenderHeaderEmailAddress().hashCode();
        }
        if (getSenderHeaderName() != null) {
            _hashCode += getSenderHeaderName().hashCode();
        }
        if (getDataRetentionPeriodLength() != null) {
            _hashCode += getDataRetentionPeriodLength().hashCode();
        }
        if (getDataRetentionPeriodUnitOfMeasure() != null) {
            _hashCode += getDataRetentionPeriodUnitOfMeasure().hashCode();
        }
        if (getReplyManagementRuleSet() != null) {
            _hashCode += getReplyManagementRuleSet().hashCode();
        }
        if (getReplyToAddress() != null) {
            _hashCode += getReplyToAddress().hashCode();
        }
        if (getReplyToDisplayName() != null) {
            _hashCode += getReplyToDisplayName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SenderProfile.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SenderProfile"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setFieldName("fromName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FromName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fromAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FromAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("useDefaultRMMRules");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "UseDefaultRMMRules"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("autoForwardToEmailAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutoForwardToEmailAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("autoForwardToName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutoForwardToName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("directForward");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DirectForward"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("autoForwardTriggeredSend");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutoForwardTriggeredSend"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TriggeredSendDefinition"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("autoReply");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutoReply"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("autoReplyTriggeredSend");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutoReplyTriggeredSend"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TriggeredSendDefinition"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("senderHeaderEmailAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SenderHeaderEmailAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("senderHeaderName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SenderHeaderName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataRetentionPeriodLength");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataRetentionPeriodLength"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataRetentionPeriodUnitOfMeasure");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataRetentionPeriodUnitOfMeasure"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RecurrenceTypeEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("replyManagementRuleSet");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ReplyManagementRuleSet"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "APIObject"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("replyToAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ReplyToAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("replyToDisplayName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ReplyToDisplayName"));
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
