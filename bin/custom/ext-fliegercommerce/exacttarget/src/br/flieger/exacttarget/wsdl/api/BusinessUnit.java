/**
 * BusinessUnit.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.flieger.exacttarget.wsdl.api;

public class BusinessUnit  extends br.flieger.exacttarget.wsdl.api.Account  implements java.io.Serializable {
    private java.lang.String description;

    private br.flieger.exacttarget.wsdl.api.SendClassification defaultSendClassification;

    private br.flieger.exacttarget.wsdl.api.LandingPage defaultHomePage;

    private br.flieger.exacttarget.wsdl.api.FilterPart subscriberFilter;

    private br.flieger.exacttarget.wsdl.api.UnsubscribeBehaviorEnum masterUnsubscribeBehavior;

    public BusinessUnit() {
    }

    public BusinessUnit(
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
           br.flieger.exacttarget.wsdl.api.AccountTypeEnum accountType,
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
           br.flieger.exacttarget.wsdl.api.AccountDataItem[] children,
           br.flieger.exacttarget.wsdl.api.Subscription subscription,
           br.flieger.exacttarget.wsdl.api.PrivateLabel[] privateLabels,
           br.flieger.exacttarget.wsdl.api.BusinessRule[] businessRules,
           br.flieger.exacttarget.wsdl.api.AccountUser[] accountUsers,
           java.lang.Boolean inheritAddress,
           java.lang.Boolean isTrialAccount,
           br.flieger.exacttarget.wsdl.api.Locale locale,
           br.flieger.exacttarget.wsdl.api.Account parentAccount,
           br.flieger.exacttarget.wsdl.api.TimeZone timeZone,
           br.flieger.exacttarget.wsdl.api.Role[] roles,
           br.flieger.exacttarget.wsdl.api.Locale languageLocale,
           java.lang.String description,
           br.flieger.exacttarget.wsdl.api.SendClassification defaultSendClassification,
           br.flieger.exacttarget.wsdl.api.LandingPage defaultHomePage,
           br.flieger.exacttarget.wsdl.api.FilterPart subscriberFilter,
           br.flieger.exacttarget.wsdl.api.UnsubscribeBehaviorEnum masterUnsubscribeBehavior) {
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
    public br.flieger.exacttarget.wsdl.api.SendClassification getDefaultSendClassification() {
        return defaultSendClassification;
    }


    /**
     * Sets the defaultSendClassification value for this BusinessUnit.
     * 
     * @param defaultSendClassification
     */
    public void setDefaultSendClassification(br.flieger.exacttarget.wsdl.api.SendClassification defaultSendClassification) {
        this.defaultSendClassification = defaultSendClassification;
    }


    /**
     * Gets the defaultHomePage value for this BusinessUnit.
     * 
     * @return defaultHomePage
     */
    public br.flieger.exacttarget.wsdl.api.LandingPage getDefaultHomePage() {
        return defaultHomePage;
    }


    /**
     * Sets the defaultHomePage value for this BusinessUnit.
     * 
     * @param defaultHomePage
     */
    public void setDefaultHomePage(br.flieger.exacttarget.wsdl.api.LandingPage defaultHomePage) {
        this.defaultHomePage = defaultHomePage;
    }


    /**
     * Gets the subscriberFilter value for this BusinessUnit.
     * 
     * @return subscriberFilter
     */
    public br.flieger.exacttarget.wsdl.api.FilterPart getSubscriberFilter() {
        return subscriberFilter;
    }


    /**
     * Sets the subscriberFilter value for this BusinessUnit.
     * 
     * @param subscriberFilter
     */
    public void setSubscriberFilter(br.flieger.exacttarget.wsdl.api.FilterPart subscriberFilter) {
        this.subscriberFilter = subscriberFilter;
    }


    /**
     * Gets the masterUnsubscribeBehavior value for this BusinessUnit.
     * 
     * @return masterUnsubscribeBehavior
     */
    public br.flieger.exacttarget.wsdl.api.UnsubscribeBehaviorEnum getMasterUnsubscribeBehavior() {
        return masterUnsubscribeBehavior;
    }


    /**
     * Sets the masterUnsubscribeBehavior value for this BusinessUnit.
     * 
     * @param masterUnsubscribeBehavior
     */
    public void setMasterUnsubscribeBehavior(br.flieger.exacttarget.wsdl.api.UnsubscribeBehaviorEnum masterUnsubscribeBehavior) {
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
