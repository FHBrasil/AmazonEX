/**
 * SendDefinition.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class SendDefinition  extends com.exacttarget.wsdl.partnerAPI.InteractionDefinition  implements java.io.Serializable {
    private java.lang.Integer categoryID;

    private com.exacttarget.wsdl.partnerAPI.SendClassification sendClassification;

    private com.exacttarget.wsdl.partnerAPI.SenderProfile senderProfile;

    /* As of Fall 2007 SenderProfile.FromName should be used. */
    private java.lang.String fromName;

    /* As of Fall 2007 SenderProfile.FromAddress should be used. */
    private java.lang.String fromAddress;

    private com.exacttarget.wsdl.partnerAPI.DeliveryProfile deliveryProfile;

    /* As of Fall 2007 DeliveryProfile.SourceAddressType should be
     * used. */
    private com.exacttarget.wsdl.partnerAPI.DeliveryProfileSourceAddressTypeEnum sourceAddressType;

    /* As of Fall 2007 DeliveryProfile.PrivateIP should be used. */
    private com.exacttarget.wsdl.partnerAPI.PrivateIP privateIP;

    /* As of Fall 2007 DeliveryProfile.DomainType should be used. */
    private com.exacttarget.wsdl.partnerAPI.DeliveryProfileDomainTypeEnum domainType;

    /* As of Fall 2007 DeliveryProfile.PrivateDomain should be used. */
    private com.exacttarget.wsdl.partnerAPI.PrivateDomain privateDomain;

    /* As of Fall 2007 DeliveryProfile.HeaderSalutationSource should
     * be used. */
    private com.exacttarget.wsdl.partnerAPI.SalutationSourceEnum headerSalutationSource;

    /* As of Fall 2007 DeliveryProfile.HeaderContentArea should be
     * used. */
    private com.exacttarget.wsdl.partnerAPI.ContentArea headerContentArea;

    /* As of Fall 2007 DeliveryProfile.FooterSalutationSource should
     * be used. */
    private com.exacttarget.wsdl.partnerAPI.SalutationSourceEnum footerSalutationSource;

    /* As of Fall 2007 DeliveryProfile.FooterContentArea should be
     * used. */
    private com.exacttarget.wsdl.partnerAPI.ContentArea footerContentArea;

    private java.lang.Boolean suppressTracking;

    private java.lang.Boolean isSendLogging;

    public SendDefinition() {
    }

    public SendDefinition(
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
           java.lang.Boolean isSendLogging) {
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
        this.categoryID = categoryID;
        this.sendClassification = sendClassification;
        this.senderProfile = senderProfile;
        this.fromName = fromName;
        this.fromAddress = fromAddress;
        this.deliveryProfile = deliveryProfile;
        this.sourceAddressType = sourceAddressType;
        this.privateIP = privateIP;
        this.domainType = domainType;
        this.privateDomain = privateDomain;
        this.headerSalutationSource = headerSalutationSource;
        this.headerContentArea = headerContentArea;
        this.footerSalutationSource = footerSalutationSource;
        this.footerContentArea = footerContentArea;
        this.suppressTracking = suppressTracking;
        this.isSendLogging = isSendLogging;
    }


    /**
     * Gets the categoryID value for this SendDefinition.
     * 
     * @return categoryID
     */
    public java.lang.Integer getCategoryID() {
        return categoryID;
    }


    /**
     * Sets the categoryID value for this SendDefinition.
     * 
     * @param categoryID
     */
    public void setCategoryID(java.lang.Integer categoryID) {
        this.categoryID = categoryID;
    }


    /**
     * Gets the sendClassification value for this SendDefinition.
     * 
     * @return sendClassification
     */
    public com.exacttarget.wsdl.partnerAPI.SendClassification getSendClassification() {
        return sendClassification;
    }


    /**
     * Sets the sendClassification value for this SendDefinition.
     * 
     * @param sendClassification
     */
    public void setSendClassification(com.exacttarget.wsdl.partnerAPI.SendClassification sendClassification) {
        this.sendClassification = sendClassification;
    }


    /**
     * Gets the senderProfile value for this SendDefinition.
     * 
     * @return senderProfile
     */
    public com.exacttarget.wsdl.partnerAPI.SenderProfile getSenderProfile() {
        return senderProfile;
    }


    /**
     * Sets the senderProfile value for this SendDefinition.
     * 
     * @param senderProfile
     */
    public void setSenderProfile(com.exacttarget.wsdl.partnerAPI.SenderProfile senderProfile) {
        this.senderProfile = senderProfile;
    }


    /**
     * Gets the fromName value for this SendDefinition.
     * 
     * @return fromName   * As of Fall 2007 SenderProfile.FromName should be used.
     */
    public java.lang.String getFromName() {
        return fromName;
    }


    /**
     * Sets the fromName value for this SendDefinition.
     * 
     * @param fromName   * As of Fall 2007 SenderProfile.FromName should be used.
     */
    public void setFromName(java.lang.String fromName) {
        this.fromName = fromName;
    }


    /**
     * Gets the fromAddress value for this SendDefinition.
     * 
     * @return fromAddress   * As of Fall 2007 SenderProfile.FromAddress should be used.
     */
    public java.lang.String getFromAddress() {
        return fromAddress;
    }


    /**
     * Sets the fromAddress value for this SendDefinition.
     * 
     * @param fromAddress   * As of Fall 2007 SenderProfile.FromAddress should be used.
     */
    public void setFromAddress(java.lang.String fromAddress) {
        this.fromAddress = fromAddress;
    }


    /**
     * Gets the deliveryProfile value for this SendDefinition.
     * 
     * @return deliveryProfile
     */
    public com.exacttarget.wsdl.partnerAPI.DeliveryProfile getDeliveryProfile() {
        return deliveryProfile;
    }


    /**
     * Sets the deliveryProfile value for this SendDefinition.
     * 
     * @param deliveryProfile
     */
    public void setDeliveryProfile(com.exacttarget.wsdl.partnerAPI.DeliveryProfile deliveryProfile) {
        this.deliveryProfile = deliveryProfile;
    }


    /**
     * Gets the sourceAddressType value for this SendDefinition.
     * 
     * @return sourceAddressType   * As of Fall 2007 DeliveryProfile.SourceAddressType should be
     * used.
     */
    public com.exacttarget.wsdl.partnerAPI.DeliveryProfileSourceAddressTypeEnum getSourceAddressType() {
        return sourceAddressType;
    }


    /**
     * Sets the sourceAddressType value for this SendDefinition.
     * 
     * @param sourceAddressType   * As of Fall 2007 DeliveryProfile.SourceAddressType should be
     * used.
     */
    public void setSourceAddressType(com.exacttarget.wsdl.partnerAPI.DeliveryProfileSourceAddressTypeEnum sourceAddressType) {
        this.sourceAddressType = sourceAddressType;
    }


    /**
     * Gets the privateIP value for this SendDefinition.
     * 
     * @return privateIP   * As of Fall 2007 DeliveryProfile.PrivateIP should be used.
     */
    public com.exacttarget.wsdl.partnerAPI.PrivateIP getPrivateIP() {
        return privateIP;
    }


    /**
     * Sets the privateIP value for this SendDefinition.
     * 
     * @param privateIP   * As of Fall 2007 DeliveryProfile.PrivateIP should be used.
     */
    public void setPrivateIP(com.exacttarget.wsdl.partnerAPI.PrivateIP privateIP) {
        this.privateIP = privateIP;
    }


    /**
     * Gets the domainType value for this SendDefinition.
     * 
     * @return domainType   * As of Fall 2007 DeliveryProfile.DomainType should be used.
     */
    public com.exacttarget.wsdl.partnerAPI.DeliveryProfileDomainTypeEnum getDomainType() {
        return domainType;
    }


    /**
     * Sets the domainType value for this SendDefinition.
     * 
     * @param domainType   * As of Fall 2007 DeliveryProfile.DomainType should be used.
     */
    public void setDomainType(com.exacttarget.wsdl.partnerAPI.DeliveryProfileDomainTypeEnum domainType) {
        this.domainType = domainType;
    }


    /**
     * Gets the privateDomain value for this SendDefinition.
     * 
     * @return privateDomain   * As of Fall 2007 DeliveryProfile.PrivateDomain should be used.
     */
    public com.exacttarget.wsdl.partnerAPI.PrivateDomain getPrivateDomain() {
        return privateDomain;
    }


    /**
     * Sets the privateDomain value for this SendDefinition.
     * 
     * @param privateDomain   * As of Fall 2007 DeliveryProfile.PrivateDomain should be used.
     */
    public void setPrivateDomain(com.exacttarget.wsdl.partnerAPI.PrivateDomain privateDomain) {
        this.privateDomain = privateDomain;
    }


    /**
     * Gets the headerSalutationSource value for this SendDefinition.
     * 
     * @return headerSalutationSource   * As of Fall 2007 DeliveryProfile.HeaderSalutationSource should
     * be used.
     */
    public com.exacttarget.wsdl.partnerAPI.SalutationSourceEnum getHeaderSalutationSource() {
        return headerSalutationSource;
    }


    /**
     * Sets the headerSalutationSource value for this SendDefinition.
     * 
     * @param headerSalutationSource   * As of Fall 2007 DeliveryProfile.HeaderSalutationSource should
     * be used.
     */
    public void setHeaderSalutationSource(com.exacttarget.wsdl.partnerAPI.SalutationSourceEnum headerSalutationSource) {
        this.headerSalutationSource = headerSalutationSource;
    }


    /**
     * Gets the headerContentArea value for this SendDefinition.
     * 
     * @return headerContentArea   * As of Fall 2007 DeliveryProfile.HeaderContentArea should be
     * used.
     */
    public com.exacttarget.wsdl.partnerAPI.ContentArea getHeaderContentArea() {
        return headerContentArea;
    }


    /**
     * Sets the headerContentArea value for this SendDefinition.
     * 
     * @param headerContentArea   * As of Fall 2007 DeliveryProfile.HeaderContentArea should be
     * used.
     */
    public void setHeaderContentArea(com.exacttarget.wsdl.partnerAPI.ContentArea headerContentArea) {
        this.headerContentArea = headerContentArea;
    }


    /**
     * Gets the footerSalutationSource value for this SendDefinition.
     * 
     * @return footerSalutationSource   * As of Fall 2007 DeliveryProfile.FooterSalutationSource should
     * be used.
     */
    public com.exacttarget.wsdl.partnerAPI.SalutationSourceEnum getFooterSalutationSource() {
        return footerSalutationSource;
    }


    /**
     * Sets the footerSalutationSource value for this SendDefinition.
     * 
     * @param footerSalutationSource   * As of Fall 2007 DeliveryProfile.FooterSalutationSource should
     * be used.
     */
    public void setFooterSalutationSource(com.exacttarget.wsdl.partnerAPI.SalutationSourceEnum footerSalutationSource) {
        this.footerSalutationSource = footerSalutationSource;
    }


    /**
     * Gets the footerContentArea value for this SendDefinition.
     * 
     * @return footerContentArea   * As of Fall 2007 DeliveryProfile.FooterContentArea should be
     * used.
     */
    public com.exacttarget.wsdl.partnerAPI.ContentArea getFooterContentArea() {
        return footerContentArea;
    }


    /**
     * Sets the footerContentArea value for this SendDefinition.
     * 
     * @param footerContentArea   * As of Fall 2007 DeliveryProfile.FooterContentArea should be
     * used.
     */
    public void setFooterContentArea(com.exacttarget.wsdl.partnerAPI.ContentArea footerContentArea) {
        this.footerContentArea = footerContentArea;
    }


    /**
     * Gets the suppressTracking value for this SendDefinition.
     * 
     * @return suppressTracking
     */
    public java.lang.Boolean getSuppressTracking() {
        return suppressTracking;
    }


    /**
     * Sets the suppressTracking value for this SendDefinition.
     * 
     * @param suppressTracking
     */
    public void setSuppressTracking(java.lang.Boolean suppressTracking) {
        this.suppressTracking = suppressTracking;
    }


    /**
     * Gets the isSendLogging value for this SendDefinition.
     * 
     * @return isSendLogging
     */
    public java.lang.Boolean getIsSendLogging() {
        return isSendLogging;
    }


    /**
     * Sets the isSendLogging value for this SendDefinition.
     * 
     * @param isSendLogging
     */
    public void setIsSendLogging(java.lang.Boolean isSendLogging) {
        this.isSendLogging = isSendLogging;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SendDefinition)) return false;
        SendDefinition other = (SendDefinition) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.categoryID==null && other.getCategoryID()==null) || 
             (this.categoryID!=null &&
              this.categoryID.equals(other.getCategoryID()))) &&
            ((this.sendClassification==null && other.getSendClassification()==null) || 
             (this.sendClassification!=null &&
              this.sendClassification.equals(other.getSendClassification()))) &&
            ((this.senderProfile==null && other.getSenderProfile()==null) || 
             (this.senderProfile!=null &&
              this.senderProfile.equals(other.getSenderProfile()))) &&
            ((this.fromName==null && other.getFromName()==null) || 
             (this.fromName!=null &&
              this.fromName.equals(other.getFromName()))) &&
            ((this.fromAddress==null && other.getFromAddress()==null) || 
             (this.fromAddress!=null &&
              this.fromAddress.equals(other.getFromAddress()))) &&
            ((this.deliveryProfile==null && other.getDeliveryProfile()==null) || 
             (this.deliveryProfile!=null &&
              this.deliveryProfile.equals(other.getDeliveryProfile()))) &&
            ((this.sourceAddressType==null && other.getSourceAddressType()==null) || 
             (this.sourceAddressType!=null &&
              this.sourceAddressType.equals(other.getSourceAddressType()))) &&
            ((this.privateIP==null && other.getPrivateIP()==null) || 
             (this.privateIP!=null &&
              this.privateIP.equals(other.getPrivateIP()))) &&
            ((this.domainType==null && other.getDomainType()==null) || 
             (this.domainType!=null &&
              this.domainType.equals(other.getDomainType()))) &&
            ((this.privateDomain==null && other.getPrivateDomain()==null) || 
             (this.privateDomain!=null &&
              this.privateDomain.equals(other.getPrivateDomain()))) &&
            ((this.headerSalutationSource==null && other.getHeaderSalutationSource()==null) || 
             (this.headerSalutationSource!=null &&
              this.headerSalutationSource.equals(other.getHeaderSalutationSource()))) &&
            ((this.headerContentArea==null && other.getHeaderContentArea()==null) || 
             (this.headerContentArea!=null &&
              this.headerContentArea.equals(other.getHeaderContentArea()))) &&
            ((this.footerSalutationSource==null && other.getFooterSalutationSource()==null) || 
             (this.footerSalutationSource!=null &&
              this.footerSalutationSource.equals(other.getFooterSalutationSource()))) &&
            ((this.footerContentArea==null && other.getFooterContentArea()==null) || 
             (this.footerContentArea!=null &&
              this.footerContentArea.equals(other.getFooterContentArea()))) &&
            ((this.suppressTracking==null && other.getSuppressTracking()==null) || 
             (this.suppressTracking!=null &&
              this.suppressTracking.equals(other.getSuppressTracking()))) &&
            ((this.isSendLogging==null && other.getIsSendLogging()==null) || 
             (this.isSendLogging!=null &&
              this.isSendLogging.equals(other.getIsSendLogging())));
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
        if (getCategoryID() != null) {
            _hashCode += getCategoryID().hashCode();
        }
        if (getSendClassification() != null) {
            _hashCode += getSendClassification().hashCode();
        }
        if (getSenderProfile() != null) {
            _hashCode += getSenderProfile().hashCode();
        }
        if (getFromName() != null) {
            _hashCode += getFromName().hashCode();
        }
        if (getFromAddress() != null) {
            _hashCode += getFromAddress().hashCode();
        }
        if (getDeliveryProfile() != null) {
            _hashCode += getDeliveryProfile().hashCode();
        }
        if (getSourceAddressType() != null) {
            _hashCode += getSourceAddressType().hashCode();
        }
        if (getPrivateIP() != null) {
            _hashCode += getPrivateIP().hashCode();
        }
        if (getDomainType() != null) {
            _hashCode += getDomainType().hashCode();
        }
        if (getPrivateDomain() != null) {
            _hashCode += getPrivateDomain().hashCode();
        }
        if (getHeaderSalutationSource() != null) {
            _hashCode += getHeaderSalutationSource().hashCode();
        }
        if (getHeaderContentArea() != null) {
            _hashCode += getHeaderContentArea().hashCode();
        }
        if (getFooterSalutationSource() != null) {
            _hashCode += getFooterSalutationSource().hashCode();
        }
        if (getFooterContentArea() != null) {
            _hashCode += getFooterContentArea().hashCode();
        }
        if (getSuppressTracking() != null) {
            _hashCode += getSuppressTracking().hashCode();
        }
        if (getIsSendLogging() != null) {
            _hashCode += getIsSendLogging().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SendDefinition.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendDefinition"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("categoryID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "CategoryID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sendClassification");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendClassification"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendClassification"));
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
        elemField.setFieldName("deliveryProfile");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DeliveryProfile"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DeliveryProfile"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sourceAddressType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SourceAddressType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DeliveryProfileSourceAddressTypeEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("privateIP");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PrivateIP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PrivateIP"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("domainType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DomainType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DeliveryProfileDomainTypeEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("privateDomain");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PrivateDomain"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PrivateDomain"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("headerSalutationSource");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "HeaderSalutationSource"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SalutationSourceEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("headerContentArea");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "HeaderContentArea"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ContentArea"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("footerSalutationSource");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FooterSalutationSource"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SalutationSourceEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("footerContentArea");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FooterContentArea"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ContentArea"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("suppressTracking");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SuppressTracking"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isSendLogging");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsSendLogging"));
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
