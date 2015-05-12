/**
 * Subscriber.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class Subscriber  extends com.exacttarget.wsdl.partnerAPI.APIObject  implements java.io.Serializable {
    private java.lang.String emailAddress;

    private com.exacttarget.wsdl.partnerAPI.Attribute[] attributes;

    private java.lang.String subscriberKey;

    private java.util.Calendar unsubscribedDate;

    private com.exacttarget.wsdl.partnerAPI.SubscriberStatus status;

    private java.lang.String partnerType;

    private com.exacttarget.wsdl.partnerAPI.EmailType emailTypePreference;

    private com.exacttarget.wsdl.partnerAPI.SubscriberList[] lists;

    private com.exacttarget.wsdl.partnerAPI.GlobalUnsubscribeCategory globalUnsubscribeCategory;

    private com.exacttarget.wsdl.partnerAPI.SubscriberTypeDefinition subscriberTypeDefinition;

    private com.exacttarget.wsdl.partnerAPI.SubscriberAddress[] addresses;

    private com.exacttarget.wsdl.partnerAPI.SMSAddress primarySMSAddress;

    private com.exacttarget.wsdl.partnerAPI.SubscriberAddressStatus primarySMSPublicationStatus;

    private com.exacttarget.wsdl.partnerAPI.EmailAddress primaryEmailAddress;

    private com.exacttarget.wsdl.partnerAPI.Locale locale;

    public Subscriber() {
    }

    public Subscriber(
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
           java.lang.String emailAddress,
           com.exacttarget.wsdl.partnerAPI.Attribute[] attributes,
           java.lang.String subscriberKey,
           java.util.Calendar unsubscribedDate,
           com.exacttarget.wsdl.partnerAPI.SubscriberStatus status,
           java.lang.String partnerType,
           com.exacttarget.wsdl.partnerAPI.EmailType emailTypePreference,
           com.exacttarget.wsdl.partnerAPI.SubscriberList[] lists,
           com.exacttarget.wsdl.partnerAPI.GlobalUnsubscribeCategory globalUnsubscribeCategory,
           com.exacttarget.wsdl.partnerAPI.SubscriberTypeDefinition subscriberTypeDefinition,
           com.exacttarget.wsdl.partnerAPI.SubscriberAddress[] addresses,
           com.exacttarget.wsdl.partnerAPI.SMSAddress primarySMSAddress,
           com.exacttarget.wsdl.partnerAPI.SubscriberAddressStatus primarySMSPublicationStatus,
           com.exacttarget.wsdl.partnerAPI.EmailAddress primaryEmailAddress,
           com.exacttarget.wsdl.partnerAPI.Locale locale) {
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
        this.emailAddress = emailAddress;
        this.attributes = attributes;
        this.subscriberKey = subscriberKey;
        this.unsubscribedDate = unsubscribedDate;
        this.status = status;
        this.partnerType = partnerType;
        this.emailTypePreference = emailTypePreference;
        this.lists = lists;
        this.globalUnsubscribeCategory = globalUnsubscribeCategory;
        this.subscriberTypeDefinition = subscriberTypeDefinition;
        this.addresses = addresses;
        this.primarySMSAddress = primarySMSAddress;
        this.primarySMSPublicationStatus = primarySMSPublicationStatus;
        this.primaryEmailAddress = primaryEmailAddress;
        this.locale = locale;
    }


    /**
     * Gets the emailAddress value for this Subscriber.
     * 
     * @return emailAddress
     */
    public java.lang.String getEmailAddress() {
        return emailAddress;
    }


    /**
     * Sets the emailAddress value for this Subscriber.
     * 
     * @param emailAddress
     */
    public void setEmailAddress(java.lang.String emailAddress) {
        this.emailAddress = emailAddress;
    }


    /**
     * Gets the attributes value for this Subscriber.
     * 
     * @return attributes
     */
    public com.exacttarget.wsdl.partnerAPI.Attribute[] getAttributes() {
        return attributes;
    }


    /**
     * Sets the attributes value for this Subscriber.
     * 
     * @param attributes
     */
    public void setAttributes(com.exacttarget.wsdl.partnerAPI.Attribute[] attributes) {
        this.attributes = attributes;
    }

    public com.exacttarget.wsdl.partnerAPI.Attribute getAttributes(int i) {
        return this.attributes[i];
    }

    public void setAttributes(int i, com.exacttarget.wsdl.partnerAPI.Attribute _value) {
        this.attributes[i] = _value;
    }


    /**
     * Gets the subscriberKey value for this Subscriber.
     * 
     * @return subscriberKey
     */
    public java.lang.String getSubscriberKey() {
        return subscriberKey;
    }


    /**
     * Sets the subscriberKey value for this Subscriber.
     * 
     * @param subscriberKey
     */
    public void setSubscriberKey(java.lang.String subscriberKey) {
        this.subscriberKey = subscriberKey;
    }


    /**
     * Gets the unsubscribedDate value for this Subscriber.
     * 
     * @return unsubscribedDate
     */
    public java.util.Calendar getUnsubscribedDate() {
        return unsubscribedDate;
    }


    /**
     * Sets the unsubscribedDate value for this Subscriber.
     * 
     * @param unsubscribedDate
     */
    public void setUnsubscribedDate(java.util.Calendar unsubscribedDate) {
        this.unsubscribedDate = unsubscribedDate;
    }


    /**
     * Gets the status value for this Subscriber.
     * 
     * @return status
     */
    public com.exacttarget.wsdl.partnerAPI.SubscriberStatus getStatus() {
        return status;
    }


    /**
     * Sets the status value for this Subscriber.
     * 
     * @param status
     */
    public void setStatus(com.exacttarget.wsdl.partnerAPI.SubscriberStatus status) {
        this.status = status;
    }


    /**
     * Gets the partnerType value for this Subscriber.
     * 
     * @return partnerType
     */
    public java.lang.String getPartnerType() {
        return partnerType;
    }


    /**
     * Sets the partnerType value for this Subscriber.
     * 
     * @param partnerType
     */
    public void setPartnerType(java.lang.String partnerType) {
        this.partnerType = partnerType;
    }


    /**
     * Gets the emailTypePreference value for this Subscriber.
     * 
     * @return emailTypePreference
     */
    public com.exacttarget.wsdl.partnerAPI.EmailType getEmailTypePreference() {
        return emailTypePreference;
    }


    /**
     * Sets the emailTypePreference value for this Subscriber.
     * 
     * @param emailTypePreference
     */
    public void setEmailTypePreference(com.exacttarget.wsdl.partnerAPI.EmailType emailTypePreference) {
        this.emailTypePreference = emailTypePreference;
    }


    /**
     * Gets the lists value for this Subscriber.
     * 
     * @return lists
     */
    public com.exacttarget.wsdl.partnerAPI.SubscriberList[] getLists() {
        return lists;
    }


    /**
     * Sets the lists value for this Subscriber.
     * 
     * @param lists
     */
    public void setLists(com.exacttarget.wsdl.partnerAPI.SubscriberList[] lists) {
        this.lists = lists;
    }

    public com.exacttarget.wsdl.partnerAPI.SubscriberList getLists(int i) {
        return this.lists[i];
    }

    public void setLists(int i, com.exacttarget.wsdl.partnerAPI.SubscriberList _value) {
        this.lists[i] = _value;
    }


    /**
     * Gets the globalUnsubscribeCategory value for this Subscriber.
     * 
     * @return globalUnsubscribeCategory
     */
    public com.exacttarget.wsdl.partnerAPI.GlobalUnsubscribeCategory getGlobalUnsubscribeCategory() {
        return globalUnsubscribeCategory;
    }


    /**
     * Sets the globalUnsubscribeCategory value for this Subscriber.
     * 
     * @param globalUnsubscribeCategory
     */
    public void setGlobalUnsubscribeCategory(com.exacttarget.wsdl.partnerAPI.GlobalUnsubscribeCategory globalUnsubscribeCategory) {
        this.globalUnsubscribeCategory = globalUnsubscribeCategory;
    }


    /**
     * Gets the subscriberTypeDefinition value for this Subscriber.
     * 
     * @return subscriberTypeDefinition
     */
    public com.exacttarget.wsdl.partnerAPI.SubscriberTypeDefinition getSubscriberTypeDefinition() {
        return subscriberTypeDefinition;
    }


    /**
     * Sets the subscriberTypeDefinition value for this Subscriber.
     * 
     * @param subscriberTypeDefinition
     */
    public void setSubscriberTypeDefinition(com.exacttarget.wsdl.partnerAPI.SubscriberTypeDefinition subscriberTypeDefinition) {
        this.subscriberTypeDefinition = subscriberTypeDefinition;
    }


    /**
     * Gets the addresses value for this Subscriber.
     * 
     * @return addresses
     */
    public com.exacttarget.wsdl.partnerAPI.SubscriberAddress[] getAddresses() {
        return addresses;
    }


    /**
     * Sets the addresses value for this Subscriber.
     * 
     * @param addresses
     */
    public void setAddresses(com.exacttarget.wsdl.partnerAPI.SubscriberAddress[] addresses) {
        this.addresses = addresses;
    }


    /**
     * Gets the primarySMSAddress value for this Subscriber.
     * 
     * @return primarySMSAddress
     */
    public com.exacttarget.wsdl.partnerAPI.SMSAddress getPrimarySMSAddress() {
        return primarySMSAddress;
    }


    /**
     * Sets the primarySMSAddress value for this Subscriber.
     * 
     * @param primarySMSAddress
     */
    public void setPrimarySMSAddress(com.exacttarget.wsdl.partnerAPI.SMSAddress primarySMSAddress) {
        this.primarySMSAddress = primarySMSAddress;
    }


    /**
     * Gets the primarySMSPublicationStatus value for this Subscriber.
     * 
     * @return primarySMSPublicationStatus
     */
    public com.exacttarget.wsdl.partnerAPI.SubscriberAddressStatus getPrimarySMSPublicationStatus() {
        return primarySMSPublicationStatus;
    }


    /**
     * Sets the primarySMSPublicationStatus value for this Subscriber.
     * 
     * @param primarySMSPublicationStatus
     */
    public void setPrimarySMSPublicationStatus(com.exacttarget.wsdl.partnerAPI.SubscriberAddressStatus primarySMSPublicationStatus) {
        this.primarySMSPublicationStatus = primarySMSPublicationStatus;
    }


    /**
     * Gets the primaryEmailAddress value for this Subscriber.
     * 
     * @return primaryEmailAddress
     */
    public com.exacttarget.wsdl.partnerAPI.EmailAddress getPrimaryEmailAddress() {
        return primaryEmailAddress;
    }


    /**
     * Sets the primaryEmailAddress value for this Subscriber.
     * 
     * @param primaryEmailAddress
     */
    public void setPrimaryEmailAddress(com.exacttarget.wsdl.partnerAPI.EmailAddress primaryEmailAddress) {
        this.primaryEmailAddress = primaryEmailAddress;
    }


    /**
     * Gets the locale value for this Subscriber.
     * 
     * @return locale
     */
    public com.exacttarget.wsdl.partnerAPI.Locale getLocale() {
        return locale;
    }


    /**
     * Sets the locale value for this Subscriber.
     * 
     * @param locale
     */
    public void setLocale(com.exacttarget.wsdl.partnerAPI.Locale locale) {
        this.locale = locale;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Subscriber)) return false;
        Subscriber other = (Subscriber) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.emailAddress==null && other.getEmailAddress()==null) || 
             (this.emailAddress!=null &&
              this.emailAddress.equals(other.getEmailAddress()))) &&
            ((this.attributes==null && other.getAttributes()==null) || 
             (this.attributes!=null &&
              java.util.Arrays.equals(this.attributes, other.getAttributes()))) &&
            ((this.subscriberKey==null && other.getSubscriberKey()==null) || 
             (this.subscriberKey!=null &&
              this.subscriberKey.equals(other.getSubscriberKey()))) &&
            ((this.unsubscribedDate==null && other.getUnsubscribedDate()==null) || 
             (this.unsubscribedDate!=null &&
              this.unsubscribedDate.equals(other.getUnsubscribedDate()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.partnerType==null && other.getPartnerType()==null) || 
             (this.partnerType!=null &&
              this.partnerType.equals(other.getPartnerType()))) &&
            ((this.emailTypePreference==null && other.getEmailTypePreference()==null) || 
             (this.emailTypePreference!=null &&
              this.emailTypePreference.equals(other.getEmailTypePreference()))) &&
            ((this.lists==null && other.getLists()==null) || 
             (this.lists!=null &&
              java.util.Arrays.equals(this.lists, other.getLists()))) &&
            ((this.globalUnsubscribeCategory==null && other.getGlobalUnsubscribeCategory()==null) || 
             (this.globalUnsubscribeCategory!=null &&
              this.globalUnsubscribeCategory.equals(other.getGlobalUnsubscribeCategory()))) &&
            ((this.subscriberTypeDefinition==null && other.getSubscriberTypeDefinition()==null) || 
             (this.subscriberTypeDefinition!=null &&
              this.subscriberTypeDefinition.equals(other.getSubscriberTypeDefinition()))) &&
            ((this.addresses==null && other.getAddresses()==null) || 
             (this.addresses!=null &&
              java.util.Arrays.equals(this.addresses, other.getAddresses()))) &&
            ((this.primarySMSAddress==null && other.getPrimarySMSAddress()==null) || 
             (this.primarySMSAddress!=null &&
              this.primarySMSAddress.equals(other.getPrimarySMSAddress()))) &&
            ((this.primarySMSPublicationStatus==null && other.getPrimarySMSPublicationStatus()==null) || 
             (this.primarySMSPublicationStatus!=null &&
              this.primarySMSPublicationStatus.equals(other.getPrimarySMSPublicationStatus()))) &&
            ((this.primaryEmailAddress==null && other.getPrimaryEmailAddress()==null) || 
             (this.primaryEmailAddress!=null &&
              this.primaryEmailAddress.equals(other.getPrimaryEmailAddress()))) &&
            ((this.locale==null && other.getLocale()==null) || 
             (this.locale!=null &&
              this.locale.equals(other.getLocale())));
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
        if (getEmailAddress() != null) {
            _hashCode += getEmailAddress().hashCode();
        }
        if (getAttributes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAttributes());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAttributes(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSubscriberKey() != null) {
            _hashCode += getSubscriberKey().hashCode();
        }
        if (getUnsubscribedDate() != null) {
            _hashCode += getUnsubscribedDate().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getPartnerType() != null) {
            _hashCode += getPartnerType().hashCode();
        }
        if (getEmailTypePreference() != null) {
            _hashCode += getEmailTypePreference().hashCode();
        }
        if (getLists() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getLists());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getLists(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getGlobalUnsubscribeCategory() != null) {
            _hashCode += getGlobalUnsubscribeCategory().hashCode();
        }
        if (getSubscriberTypeDefinition() != null) {
            _hashCode += getSubscriberTypeDefinition().hashCode();
        }
        if (getAddresses() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAddresses());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAddresses(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPrimarySMSAddress() != null) {
            _hashCode += getPrimarySMSAddress().hashCode();
        }
        if (getPrimarySMSPublicationStatus() != null) {
            _hashCode += getPrimarySMSPublicationStatus().hashCode();
        }
        if (getPrimaryEmailAddress() != null) {
            _hashCode += getPrimaryEmailAddress().hashCode();
        }
        if (getLocale() != null) {
            _hashCode += getLocale().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Subscriber.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Subscriber"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("emailAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "EmailAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("attributes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Attributes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Attribute"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subscriberKey");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SubscriberKey"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unsubscribedDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "UnsubscribedDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SubscriberStatus"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("partnerType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PartnerType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("emailTypePreference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "EmailTypePreference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "EmailType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lists");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Lists"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SubscriberList"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("globalUnsubscribeCategory");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "GlobalUnsubscribeCategory"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "GlobalUnsubscribeCategory"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subscriberTypeDefinition");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SubscriberTypeDefinition"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SubscriberTypeDefinition"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addresses");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Addresses"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SubscriberAddress"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Address"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("primarySMSAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PrimarySMSAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SMSAddress"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("primarySMSPublicationStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PrimarySMSPublicationStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SubscriberAddressStatus"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("primaryEmailAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PrimaryEmailAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "EmailAddress"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("locale");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Locale"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Locale"));
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
