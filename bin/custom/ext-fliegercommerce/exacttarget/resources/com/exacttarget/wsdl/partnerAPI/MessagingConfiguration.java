/**
 * MessagingConfiguration.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;


/**
 * Deprecated.
 */
public class MessagingConfiguration  extends com.exacttarget.wsdl.partnerAPI.APIObject  implements java.io.Serializable {
    private java.lang.String code;

    private com.exacttarget.wsdl.partnerAPI.MessagingVendorKind messagingVendorKind;

    private boolean isActive;

    private java.lang.String url;

    private java.lang.String userName;

    private java.lang.String password;

    private java.lang.String profileID;

    private java.lang.String callbackUrl;

    private java.lang.String mediaTypes;

    public MessagingConfiguration() {
    }

    public MessagingConfiguration(
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
           java.lang.String code,
           com.exacttarget.wsdl.partnerAPI.MessagingVendorKind messagingVendorKind,
           boolean isActive,
           java.lang.String url,
           java.lang.String userName,
           java.lang.String password,
           java.lang.String profileID,
           java.lang.String callbackUrl,
           java.lang.String mediaTypes) {
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
        this.code = code;
        this.messagingVendorKind = messagingVendorKind;
        this.isActive = isActive;
        this.url = url;
        this.userName = userName;
        this.password = password;
        this.profileID = profileID;
        this.callbackUrl = callbackUrl;
        this.mediaTypes = mediaTypes;
    }


    /**
     * Gets the code value for this MessagingConfiguration.
     * 
     * @return code
     */
    public java.lang.String getCode() {
        return code;
    }


    /**
     * Sets the code value for this MessagingConfiguration.
     * 
     * @param code
     */
    public void setCode(java.lang.String code) {
        this.code = code;
    }


    /**
     * Gets the messagingVendorKind value for this MessagingConfiguration.
     * 
     * @return messagingVendorKind
     */
    public com.exacttarget.wsdl.partnerAPI.MessagingVendorKind getMessagingVendorKind() {
        return messagingVendorKind;
    }


    /**
     * Sets the messagingVendorKind value for this MessagingConfiguration.
     * 
     * @param messagingVendorKind
     */
    public void setMessagingVendorKind(com.exacttarget.wsdl.partnerAPI.MessagingVendorKind messagingVendorKind) {
        this.messagingVendorKind = messagingVendorKind;
    }


    /**
     * Gets the isActive value for this MessagingConfiguration.
     * 
     * @return isActive
     */
    public boolean isIsActive() {
        return isActive;
    }


    /**
     * Sets the isActive value for this MessagingConfiguration.
     * 
     * @param isActive
     */
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }


    /**
     * Gets the url value for this MessagingConfiguration.
     * 
     * @return url
     */
    public java.lang.String getUrl() {
        return url;
    }


    /**
     * Sets the url value for this MessagingConfiguration.
     * 
     * @param url
     */
    public void setUrl(java.lang.String url) {
        this.url = url;
    }


    /**
     * Gets the userName value for this MessagingConfiguration.
     * 
     * @return userName
     */
    public java.lang.String getUserName() {
        return userName;
    }


    /**
     * Sets the userName value for this MessagingConfiguration.
     * 
     * @param userName
     */
    public void setUserName(java.lang.String userName) {
        this.userName = userName;
    }


    /**
     * Gets the password value for this MessagingConfiguration.
     * 
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this MessagingConfiguration.
     * 
     * @param password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the profileID value for this MessagingConfiguration.
     * 
     * @return profileID
     */
    public java.lang.String getProfileID() {
        return profileID;
    }


    /**
     * Sets the profileID value for this MessagingConfiguration.
     * 
     * @param profileID
     */
    public void setProfileID(java.lang.String profileID) {
        this.profileID = profileID;
    }


    /**
     * Gets the callbackUrl value for this MessagingConfiguration.
     * 
     * @return callbackUrl
     */
    public java.lang.String getCallbackUrl() {
        return callbackUrl;
    }


    /**
     * Sets the callbackUrl value for this MessagingConfiguration.
     * 
     * @param callbackUrl
     */
    public void setCallbackUrl(java.lang.String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }


    /**
     * Gets the mediaTypes value for this MessagingConfiguration.
     * 
     * @return mediaTypes
     */
    public java.lang.String getMediaTypes() {
        return mediaTypes;
    }


    /**
     * Sets the mediaTypes value for this MessagingConfiguration.
     * 
     * @param mediaTypes
     */
    public void setMediaTypes(java.lang.String mediaTypes) {
        this.mediaTypes = mediaTypes;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MessagingConfiguration)) return false;
        MessagingConfiguration other = (MessagingConfiguration) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.code==null && other.getCode()==null) || 
             (this.code!=null &&
              this.code.equals(other.getCode()))) &&
            ((this.messagingVendorKind==null && other.getMessagingVendorKind()==null) || 
             (this.messagingVendorKind!=null &&
              this.messagingVendorKind.equals(other.getMessagingVendorKind()))) &&
            this.isActive == other.isIsActive() &&
            ((this.url==null && other.getUrl()==null) || 
             (this.url!=null &&
              this.url.equals(other.getUrl()))) &&
            ((this.userName==null && other.getUserName()==null) || 
             (this.userName!=null &&
              this.userName.equals(other.getUserName()))) &&
            ((this.password==null && other.getPassword()==null) || 
             (this.password!=null &&
              this.password.equals(other.getPassword()))) &&
            ((this.profileID==null && other.getProfileID()==null) || 
             (this.profileID!=null &&
              this.profileID.equals(other.getProfileID()))) &&
            ((this.callbackUrl==null && other.getCallbackUrl()==null) || 
             (this.callbackUrl!=null &&
              this.callbackUrl.equals(other.getCallbackUrl()))) &&
            ((this.mediaTypes==null && other.getMediaTypes()==null) || 
             (this.mediaTypes!=null &&
              this.mediaTypes.equals(other.getMediaTypes())));
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
        if (getCode() != null) {
            _hashCode += getCode().hashCode();
        }
        if (getMessagingVendorKind() != null) {
            _hashCode += getMessagingVendorKind().hashCode();
        }
        _hashCode += (isIsActive() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getUrl() != null) {
            _hashCode += getUrl().hashCode();
        }
        if (getUserName() != null) {
            _hashCode += getUserName().hashCode();
        }
        if (getPassword() != null) {
            _hashCode += getPassword().hashCode();
        }
        if (getProfileID() != null) {
            _hashCode += getProfileID().hashCode();
        }
        if (getCallbackUrl() != null) {
            _hashCode += getCallbackUrl().hashCode();
        }
        if (getMediaTypes() != null) {
            _hashCode += getMediaTypes().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MessagingConfiguration.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MessagingConfiguration"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("code");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messagingVendorKind");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MessagingVendorKind"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MessagingVendorKind"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isActive");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsActive"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("url");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Url"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "UserName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("password");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Password"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("profileID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ProfileID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("callbackUrl");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "CallbackUrl"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mediaTypes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MediaTypes"));
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
