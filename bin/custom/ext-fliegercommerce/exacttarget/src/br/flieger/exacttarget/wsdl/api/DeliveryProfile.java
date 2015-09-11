/**
 * DeliveryProfile.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.flieger.exacttarget.wsdl.api;

public class DeliveryProfile  extends br.flieger.exacttarget.wsdl.api.APIObject  implements java.io.Serializable {
    private java.lang.String name;

    private java.lang.String description;

    private br.flieger.exacttarget.wsdl.api.DeliveryProfileSourceAddressTypeEnum sourceAddressType;

    private br.flieger.exacttarget.wsdl.api.PrivateIP privateIP;

    private br.flieger.exacttarget.wsdl.api.DeliveryProfileDomainTypeEnum domainType;

    private br.flieger.exacttarget.wsdl.api.PrivateDomain privateDomain;

    private br.flieger.exacttarget.wsdl.api.SalutationSourceEnum headerSalutationSource;

    private br.flieger.exacttarget.wsdl.api.ContentArea headerContentArea;

    private br.flieger.exacttarget.wsdl.api.SalutationSourceEnum footerSalutationSource;

    private br.flieger.exacttarget.wsdl.api.ContentArea footerContentArea;

    private java.lang.Boolean subscriberLevelPrivateDomain;

    private br.flieger.exacttarget.wsdl.api.Certificate SMIMESignatureCertificate;

    private br.flieger.exacttarget.wsdl.api.PrivateDomainSet privateDomainSet;

    public DeliveryProfile() {
    }

    public DeliveryProfile(
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
           br.flieger.exacttarget.wsdl.api.DeliveryProfileSourceAddressTypeEnum sourceAddressType,
           br.flieger.exacttarget.wsdl.api.PrivateIP privateIP,
           br.flieger.exacttarget.wsdl.api.DeliveryProfileDomainTypeEnum domainType,
           br.flieger.exacttarget.wsdl.api.PrivateDomain privateDomain,
           br.flieger.exacttarget.wsdl.api.SalutationSourceEnum headerSalutationSource,
           br.flieger.exacttarget.wsdl.api.ContentArea headerContentArea,
           br.flieger.exacttarget.wsdl.api.SalutationSourceEnum footerSalutationSource,
           br.flieger.exacttarget.wsdl.api.ContentArea footerContentArea,
           java.lang.Boolean subscriberLevelPrivateDomain,
           br.flieger.exacttarget.wsdl.api.Certificate SMIMESignatureCertificate,
           br.flieger.exacttarget.wsdl.api.PrivateDomainSet privateDomainSet) {
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
    public br.flieger.exacttarget.wsdl.api.DeliveryProfileSourceAddressTypeEnum getSourceAddressType() {
        return sourceAddressType;
    }


    /**
     * Sets the sourceAddressType value for this DeliveryProfile.
     * 
     * @param sourceAddressType
     */
    public void setSourceAddressType(br.flieger.exacttarget.wsdl.api.DeliveryProfileSourceAddressTypeEnum sourceAddressType) {
        this.sourceAddressType = sourceAddressType;
    }


    /**
     * Gets the privateIP value for this DeliveryProfile.
     * 
     * @return privateIP
     */
    public br.flieger.exacttarget.wsdl.api.PrivateIP getPrivateIP() {
        return privateIP;
    }


    /**
     * Sets the privateIP value for this DeliveryProfile.
     * 
     * @param privateIP
     */
    public void setPrivateIP(br.flieger.exacttarget.wsdl.api.PrivateIP privateIP) {
        this.privateIP = privateIP;
    }


    /**
     * Gets the domainType value for this DeliveryProfile.
     * 
     * @return domainType
     */
    public br.flieger.exacttarget.wsdl.api.DeliveryProfileDomainTypeEnum getDomainType() {
        return domainType;
    }


    /**
     * Sets the domainType value for this DeliveryProfile.
     * 
     * @param domainType
     */
    public void setDomainType(br.flieger.exacttarget.wsdl.api.DeliveryProfileDomainTypeEnum domainType) {
        this.domainType = domainType;
    }


    /**
     * Gets the privateDomain value for this DeliveryProfile.
     * 
     * @return privateDomain
     */
    public br.flieger.exacttarget.wsdl.api.PrivateDomain getPrivateDomain() {
        return privateDomain;
    }


    /**
     * Sets the privateDomain value for this DeliveryProfile.
     * 
     * @param privateDomain
     */
    public void setPrivateDomain(br.flieger.exacttarget.wsdl.api.PrivateDomain privateDomain) {
        this.privateDomain = privateDomain;
    }


    /**
     * Gets the headerSalutationSource value for this DeliveryProfile.
     * 
     * @return headerSalutationSource
     */
    public br.flieger.exacttarget.wsdl.api.SalutationSourceEnum getHeaderSalutationSource() {
        return headerSalutationSource;
    }


    /**
     * Sets the headerSalutationSource value for this DeliveryProfile.
     * 
     * @param headerSalutationSource
     */
    public void setHeaderSalutationSource(br.flieger.exacttarget.wsdl.api.SalutationSourceEnum headerSalutationSource) {
        this.headerSalutationSource = headerSalutationSource;
    }


    /**
     * Gets the headerContentArea value for this DeliveryProfile.
     * 
     * @return headerContentArea
     */
    public br.flieger.exacttarget.wsdl.api.ContentArea getHeaderContentArea() {
        return headerContentArea;
    }


    /**
     * Sets the headerContentArea value for this DeliveryProfile.
     * 
     * @param headerContentArea
     */
    public void setHeaderContentArea(br.flieger.exacttarget.wsdl.api.ContentArea headerContentArea) {
        this.headerContentArea = headerContentArea;
    }


    /**
     * Gets the footerSalutationSource value for this DeliveryProfile.
     * 
     * @return footerSalutationSource
     */
    public br.flieger.exacttarget.wsdl.api.SalutationSourceEnum getFooterSalutationSource() {
        return footerSalutationSource;
    }


    /**
     * Sets the footerSalutationSource value for this DeliveryProfile.
     * 
     * @param footerSalutationSource
     */
    public void setFooterSalutationSource(br.flieger.exacttarget.wsdl.api.SalutationSourceEnum footerSalutationSource) {
        this.footerSalutationSource = footerSalutationSource;
    }


    /**
     * Gets the footerContentArea value for this DeliveryProfile.
     * 
     * @return footerContentArea
     */
    public br.flieger.exacttarget.wsdl.api.ContentArea getFooterContentArea() {
        return footerContentArea;
    }


    /**
     * Sets the footerContentArea value for this DeliveryProfile.
     * 
     * @param footerContentArea
     */
    public void setFooterContentArea(br.flieger.exacttarget.wsdl.api.ContentArea footerContentArea) {
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
    public br.flieger.exacttarget.wsdl.api.Certificate getSMIMESignatureCertificate() {
        return SMIMESignatureCertificate;
    }


    /**
     * Sets the SMIMESignatureCertificate value for this DeliveryProfile.
     * 
     * @param SMIMESignatureCertificate
     */
    public void setSMIMESignatureCertificate(br.flieger.exacttarget.wsdl.api.Certificate SMIMESignatureCertificate) {
        this.SMIMESignatureCertificate = SMIMESignatureCertificate;
    }


    /**
     * Gets the privateDomainSet value for this DeliveryProfile.
     * 
     * @return privateDomainSet
     */
    public br.flieger.exacttarget.wsdl.api.PrivateDomainSet getPrivateDomainSet() {
        return privateDomainSet;
    }


    /**
     * Sets the privateDomainSet value for this DeliveryProfile.
     * 
     * @param privateDomainSet
     */
    public void setPrivateDomainSet(br.flieger.exacttarget.wsdl.api.PrivateDomainSet privateDomainSet) {
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
