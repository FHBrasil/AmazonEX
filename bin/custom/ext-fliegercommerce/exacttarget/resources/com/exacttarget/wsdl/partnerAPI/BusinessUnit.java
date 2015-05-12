/**
 * BusinessUnit.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class BusinessUnit  extends com.exacttarget.wsdl.partnerAPI.Account  implements java.io.Serializable {
    private java.lang.String description;

    private com.exacttarget.wsdl.partnerAPI.SendClassification defaultSendClassification;

    private com.exacttarget.wsdl.partnerAPI.LandingPage defaultHomePage;

    private com.exacttarget.wsdl.partnerAPI.FilterPart subscriberFilter;

    private com.exacttarget.wsdl.partnerAPI.UnsubscribeBehaviorEnum masterUnsubscribeBehavior;

    public BusinessUnit() {
    }

    public BusinessUnit(
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
           com.exacttarget.wsdl.partnerAPI.AccountTypeEnum accountType,
           java.lang.Integer parentID,
           java.lang.Integer brandID,
           java.lang.Integer privateLabelID,
           java.lang.Integer reportingParentID,
           java.lang.String name,
           java.lang.String email,
           java.lang.String fromName,
           java.lang.String businessName,
           java.lang.String phone,
           java.lang.String address,
           java.lang.String fax,
           java.lang.String city,
           java.lang.String state,
           java.lang.String zip,
           java.lang.String country,
           java.lang.Integer isActive,
           java.lang.Boolean isTestAccount,
           java.lang.Integer orgID,
           java.lang.Integer DBID,
           java.lang.String parentName,
           java.lang.Long customerID,
           java.util.Calendar deletedDate,
           java.lang.Integer editionID,
           com.exacttarget.wsdl.partnerAPI.AccountDataItem[] children,
           com.exacttarget.wsdl.partnerAPI.Subscription subscription,
           com.exacttarget.wsdl.partnerAPI.PrivateLabel[] privateLabels,
           com.exacttarget.wsdl.partnerAPI.BusinessRule[] businessRules,
           com.exacttarget.wsdl.partnerAPI.AccountUser[] accountUsers,
           java.lang.Boolean inheritAddress,
           java.lang.Boolean isTrialAccount,
           com.exacttarget.wsdl.partnerAPI.Locale locale,
           com.exacttarget.wsdl.partnerAPI.Account parentAccount,
           com.exacttarget.wsdl.partnerAPI.TimeZone timeZone,
           com.exacttarget.wsdl.partnerAPI.Role[] roles,
           com.exacttarget.wsdl.partnerAPI.Locale languageLocale,
           java.lang.String description,
           com.exacttarget.wsdl.partnerAPI.SendClassification defaultSendClassification,
           com.exacttarget.wsdl.partnerAPI.LandingPage defaultHomePage,
           com.exacttarget.wsdl.partnerAPI.FilterPart subscriberFilter,
           com.exacttarget.wsdl.partnerAPI.UnsubscribeBehaviorEnum masterUnsubscribeBehavior) {
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
            accountType,
            parentID,
            brandID,
            privateLabelID,
            reportingParentID,
            name,
            email,
            fromName,
            businessName,
            phone,
            address,
            fax,
            city,
            state,
            zip,
            country,
            isActive,
            isTestAccount,
            orgID,
            DBID,
            parentName,
            customerID,
            deletedDate,
            editionID,
            children,
            subscription,
            privateLabels,
            businessRules,
            accountUsers,
            inheritAddress,
            isTrialAccount,
            locale,
            parentAccount,
            timeZone,
            roles,
            languageLocale);
        this.description = description;
        this.defaultSendClassification = defaultSendClassification;
        this.defaultHomePage = defaultHomePage;
        this.subscriberFilter = subscriberFilter;
        this.masterUnsubscribeBehavior = masterUnsubscribeBehavior;
    }


    /**
     * Gets the description value for this BusinessUnit.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this BusinessUnit.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the defaultSendClassification value for this BusinessUnit.
     * 
     * @return defaultSendClassification
     */
    public com.exacttarget.wsdl.partnerAPI.SendClassification getDefaultSendClassification() {
        return defaultSendClassification;
    }


    /**
     * Sets the defaultSendClassification value for this BusinessUnit.
     * 
     * @param defaultSendClassification
     */
    public void setDefaultSendClassification(com.exacttarget.wsdl.partnerAPI.SendClassification defaultSendClassification) {
        this.defaultSendClassification = defaultSendClassification;
    }


    /**
     * Gets the defaultHomePage value for this BusinessUnit.
     * 
     * @return defaultHomePage
     */
    public com.exacttarget.wsdl.partnerAPI.LandingPage getDefaultHomePage() {
        return defaultHomePage;
    }


    /**
     * Sets the defaultHomePage value for this BusinessUnit.
     * 
     * @param defaultHomePage
     */
    public void setDefaultHomePage(com.exacttarget.wsdl.partnerAPI.LandingPage defaultHomePage) {
        this.defaultHomePage = defaultHomePage;
    }


    /**
     * Gets the subscriberFilter value for this BusinessUnit.
     * 
     * @return subscriberFilter
     */
    public com.exacttarget.wsdl.partnerAPI.FilterPart getSubscriberFilter() {
        return subscriberFilter;
    }


    /**
     * Sets the subscriberFilter value for this BusinessUnit.
     * 
     * @param subscriberFilter
     */
    public void setSubscriberFilter(com.exacttarget.wsdl.partnerAPI.FilterPart subscriberFilter) {
        this.subscriberFilter = subscriberFilter;
    }


    /**
     * Gets the masterUnsubscribeBehavior value for this BusinessUnit.
     * 
     * @return masterUnsubscribeBehavior
     */
    public com.exacttarget.wsdl.partnerAPI.UnsubscribeBehaviorEnum getMasterUnsubscribeBehavior() {
        return masterUnsubscribeBehavior;
    }


    /**
     * Sets the masterUnsubscribeBehavior value for this BusinessUnit.
     * 
     * @param masterUnsubscribeBehavior
     */
    public void setMasterUnsubscribeBehavior(com.exacttarget.wsdl.partnerAPI.UnsubscribeBehaviorEnum masterUnsubscribeBehavior) {
        this.masterUnsubscribeBehavior = masterUnsubscribeBehavior;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BusinessUnit)) return false;
        BusinessUnit other = (BusinessUnit) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.defaultSendClassification==null && other.getDefaultSendClassification()==null) || 
             (this.defaultSendClassification!=null &&
              this.defaultSendClassification.equals(other.getDefaultSendClassification()))) &&
            ((this.defaultHomePage==null && other.getDefaultHomePage()==null) || 
             (this.defaultHomePage!=null &&
              this.defaultHomePage.equals(other.getDefaultHomePage()))) &&
            ((this.subscriberFilter==null && other.getSubscriberFilter()==null) || 
             (this.subscriberFilter!=null &&
              this.subscriberFilter.equals(other.getSubscriberFilter()))) &&
            ((this.masterUnsubscribeBehavior==null && other.getMasterUnsubscribeBehavior()==null) || 
             (this.masterUnsubscribeBehavior!=null &&
              this.masterUnsubscribeBehavior.equals(other.getMasterUnsubscribeBehavior())));
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
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getDefaultSendClassification() != null) {
            _hashCode += getDefaultSendClassification().hashCode();
        }
        if (getDefaultHomePage() != null) {
            _hashCode += getDefaultHomePage().hashCode();
        }
        if (getSubscriberFilter() != null) {
            _hashCode += getSubscriberFilter().hashCode();
        }
        if (getMasterUnsubscribeBehavior() != null) {
            _hashCode += getMasterUnsubscribeBehavior().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BusinessUnit.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "BusinessUnit"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("defaultSendClassification");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DefaultSendClassification"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendClassification"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("defaultHomePage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DefaultHomePage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "LandingPage"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subscriberFilter");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SubscriberFilter"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FilterPart"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("masterUnsubscribeBehavior");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MasterUnsubscribeBehavior"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "UnsubscribeBehaviorEnum"));
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
