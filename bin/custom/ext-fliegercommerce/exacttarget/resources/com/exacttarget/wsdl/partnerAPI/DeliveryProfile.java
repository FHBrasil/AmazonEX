/**
 * DeliveryProfile.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class DeliveryProfile  extends com.exacttarget.wsdl.partnerAPI.APIObject  implements java.io.Serializable {
    private java.lang.String name;

    private java.lang.String description;

    private com.exacttarget.wsdl.partnerAPI.DeliveryProfileSourceAddressTypeEnum sourceAddressType;

    private com.exacttarget.wsdl.partnerAPI.PrivateIP privateIP;

    private com.exacttarget.wsdl.partnerAPI.DeliveryProfileDomainTypeEnum domainType;

    private com.exacttarget.wsdl.partnerAPI.PrivateDomain privateDomain;

    private com.exacttarget.wsdl.partnerAPI.SalutationSourceEnum headerSalutationSource;

    private com.exacttarget.wsdl.partnerAPI.ContentArea headerContentArea;

    private com.exacttarget.wsdl.partnerAPI.SalutationSourceEnum footerSalutationSource;

    private com.exacttarget.wsdl.partnerAPI.ContentArea footerContentArea;

    private java.lang.Boolean subscriberLevelPrivateDomain;

    private com.exacttarget.wsdl.partnerAPI.Certificate SMIMESignatureCertificate;

    private com.exacttarget.wsdl.partnerAPI.PrivateDomainSet privateDomainSet;

    public DeliveryProfile() {
    }

    public DeliveryProfile(
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
           com.exacttarget.wsdl.partnerAPI.DeliveryProfileSourceAddressTypeEnum sourceAddressType,
           com.exacttarget.wsdl.partnerAPI.PrivateIP privateIP,
           com.exacttarget.wsdl.partnerAPI.DeliveryProfileDomainTypeEnum domainType,
           com.exacttarget.wsdl.partnerAPI.PrivateDomain privateDomain,
           com.exacttarget.wsdl.partnerAPI.SalutationSourceEnum headerSalutationSource,
           com.exacttarget.wsdl.partnerAPI.ContentArea headerContentArea,
           com.exacttarget.wsdl.partnerAPI.SalutationSourceEnum footerSalutationSource,
           com.exacttarget.wsdl.partnerAPI.ContentArea footerContentArea,
           java.lang.Boolean subscriberLevelPrivateDomain,
           com.exacttarget.wsdl.partnerAPI.Certificate SMIMESignatureCertificate,
           com.exacttarget.wsdl.partnerAPI.PrivateDomainSet privateDomainSet) {
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
        this.sourceAddressType = sourceAddressType;
        this.privateIP = privateIP;
        this.domainType = domainType;
        this.privateDomain = privateDomain;
        this.headerSalutationSource = headerSalutationSource;
        this.headerContentArea = headerContentArea;
        this.footerSalutationSource = footerSalutationSource;
        this.footerContentArea = footerContentArea;
        this.subscriberLevelPrivateDomain = subscriberLevelPrivateDomain;
        this.SMIMESignatureCertificate = SMIMESignatureCertificate;
        this.privateDomainSet = privateDomainSet;
    }


    /**
     * Gets the name value for this DeliveryProfile.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this DeliveryProfile.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the description value for this DeliveryProfile.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this DeliveryProfile.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the sourceAddressType value for this DeliveryProfile.
     * 
     * @return sourceAddressType
     */
    public com.exacttarget.wsdl.partnerAPI.DeliveryProfileSourceAddressTypeEnum getSourceAddressType() {
        return sourceAddressType;
    }


    /**
     * Sets the sourceAddressType value for this DeliveryProfile.
     * 
     * @param sourceAddressType
     */
    public void setSourceAddressType(com.exacttarget.wsdl.partnerAPI.DeliveryProfileSourceAddressTypeEnum sourceAddressType) {
        this.sourceAddressType = sourceAddressType;
    }


    /**
     * Gets the privateIP value for this DeliveryProfile.
     * 
     * @return privateIP
     */
    public com.exacttarget.wsdl.partnerAPI.PrivateIP getPrivateIP() {
        return privateIP;
    }


    /**
     * Sets the privateIP value for this DeliveryProfile.
     * 
     * @param privateIP
     */
    public void setPrivateIP(com.exacttarget.wsdl.partnerAPI.PrivateIP privateIP) {
        this.privateIP = privateIP;
    }


    /**
     * Gets the domainType value for this DeliveryProfile.
     * 
     * @return domainType
     */
    public com.exacttarget.wsdl.partnerAPI.DeliveryProfileDomainTypeEnum getDomainType() {
        return domainType;
    }


    /**
     * Sets the domainType value for this DeliveryProfile.
     * 
     * @param domainType
     */
    public void setDomainType(com.exacttarget.wsdl.partnerAPI.DeliveryProfileDomainTypeEnum domainType) {
        this.domainType = domainType;
    }


    /**
     * Gets the privateDomain value for this DeliveryProfile.
     * 
     * @return privateDomain
     */
    public com.exacttarget.wsdl.partnerAPI.PrivateDomain getPrivateDomain() {
        return privateDomain;
    }


    /**
     * Sets the privateDomain value for this DeliveryProfile.
     * 
     * @param privateDomain
     */
    public void setPrivateDomain(com.exacttarget.wsdl.partnerAPI.PrivateDomain privateDomain) {
        this.privateDomain = privateDomain;
    }


    /**
     * Gets the headerSalutationSource value for this DeliveryProfile.
     * 
     * @return headerSalutationSource
     */
    public com.exacttarget.wsdl.partnerAPI.SalutationSourceEnum getHeaderSalutationSource() {
        return headerSalutationSource;
    }


    /**
     * Sets the headerSalutationSource value for this DeliveryProfile.
     * 
     * @param headerSalutationSource
     */
    public void setHeaderSalutationSource(com.exacttarget.wsdl.partnerAPI.SalutationSourceEnum headerSalutationSource) {
        this.headerSalutationSource = headerSalutationSource;
    }


    /**
     * Gets the headerContentArea value for this DeliveryProfile.
     * 
     * @return headerContentArea
     */
    public com.exacttarget.wsdl.partnerAPI.ContentArea getHeaderContentArea() {
        return headerContentArea;
    }


    /**
     * Sets the headerContentArea value for this DeliveryProfile.
     * 
     * @param headerContentArea
     */
    public void setHeaderContentArea(com.exacttarget.wsdl.partnerAPI.ContentArea headerContentArea) {
        this.headerContentArea = headerContentArea;
    }


    /**
     * Gets the footerSalutationSource value for this DeliveryProfile.
     * 
     * @return footerSalutationSource
     */
    public com.exacttarget.wsdl.partnerAPI.SalutationSourceEnum getFooterSalutationSource() {
        return footerSalutationSource;
    }


    /**
     * Sets the footerSalutationSource value for this DeliveryProfile.
     * 
     * @param footerSalutationSource
     */
    public void setFooterSalutationSource(com.exacttarget.wsdl.partnerAPI.SalutationSourceEnum footerSalutationSource) {
        this.footerSalutationSource = footerSalutationSource;
    }


    /**
     * Gets the footerContentArea value for this DeliveryProfile.
     * 
     * @return footerContentArea
     */
    public com.exacttarget.wsdl.partnerAPI.ContentArea getFooterContentArea() {
        return footerContentArea;
    }


    /**
     * Sets the footerContentArea value for this DeliveryProfile.
     * 
     * @param footerContentArea
     */
    public void setFooterContentArea(com.exacttarget.wsdl.partnerAPI.ContentArea footerContentArea) {
        this.footerContentArea = footerContentArea;
    }


    /**
     * Gets the subscriberLevelPrivateDomain value for this DeliveryProfile.
     * 
     * @return subscriberLevelPrivateDomain
     */
    public java.lang.Boolean getSubscriberLevelPrivateDomain() {
        return subscriberLevelPrivateDomain;
    }


    /**
     * Sets the subscriberLevelPrivateDomain value for this DeliveryProfile.
     * 
     * @param subscriberLevelPrivateDomain
     */
    public void setSubscriberLevelPrivateDomain(java.lang.Boolean subscriberLevelPrivateDomain) {
        this.subscriberLevelPrivateDomain = subscriberLevelPrivateDomain;
    }


    /**
     * Gets the SMIMESignatureCertificate value for this DeliveryProfile.
     * 
     * @return SMIMESignatureCertificate
     */
    public com.exacttarget.wsdl.partnerAPI.Certificate getSMIMESignatureCertificate() {
        return SMIMESignatureCertificate;
    }


    /**
     * Sets the SMIMESignatureCertificate value for this DeliveryProfile.
     * 
     * @param SMIMESignatureCertificate
     */
    public void setSMIMESignatureCertificate(com.exacttarget.wsdl.partnerAPI.Certificate SMIMESignatureCertificate) {
        this.SMIMESignatureCertificate = SMIMESignatureCertificate;
    }


    /**
     * Gets the privateDomainSet value for this DeliveryProfile.
     * 
     * @return privateDomainSet
     */
    public com.exacttarget.wsdl.partnerAPI.PrivateDomainSet getPrivateDomainSet() {
        return privateDomainSet;
    }


    /**
     * Sets the privateDomainSet value for this DeliveryProfile.
     * 
     * @param privateDomainSet
     */
    public void setPrivateDomainSet(com.exacttarget.wsdl.partnerAPI.PrivateDomainSet privateDomainSet) {
        this.privateDomainSet = privateDomainSet;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DeliveryProfile)) return false;
        DeliveryProfile other = (DeliveryProfile) obj;
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
            ((this.subscriberLevelPrivateDomain==null && other.getSubscriberLevelPrivateDomain()==null) || 
             (this.subscriberLevelPrivateDomain!=null &&
              this.subscriberLevelPrivateDomain.equals(other.getSubscriberLevelPrivateDomain()))) &&
            ((this.SMIMESignatureCertificate==null && other.getSMIMESignatureCertificate()==null) || 
             (this.SMIMESignatureCertificate!=null &&
              this.SMIMESignatureCertificate.equals(other.getSMIMESignatureCertificate()))) &&
            ((this.privateDomainSet==null && other.getPrivateDomainSet()==null) || 
             (this.privateDomainSet!=null &&
              this.privateDomainSet.equals(other.getPrivateDomainSet())));
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
        if (getSubscriberLevelPrivateDomain() != null) {
            _hashCode += getSubscriberLevelPrivateDomain().hashCode();
        }
        if (getSMIMESignatureCertificate() != null) {
            _hashCode += getSMIMESignatureCertificate().hashCode();
        }
        if (getPrivateDomainSet() != null) {
            _hashCode += getPrivateDomainSet().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DeliveryProfile.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DeliveryProfile"));
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
        elemField.setFieldName("subscriberLevelPrivateDomain");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SubscriberLevelPrivateDomain"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SMIMESignatureCertificate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SMIMESignatureCertificate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Certificate"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("privateDomainSet");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PrivateDomainSet"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PrivateDomainSet"));
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
