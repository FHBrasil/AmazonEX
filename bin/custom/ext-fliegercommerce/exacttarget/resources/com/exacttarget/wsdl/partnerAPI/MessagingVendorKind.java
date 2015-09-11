/**
 * MessagingVendorKind.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;


/**
 * Deprecated.
 */
public class MessagingVendorKind  extends com.exacttarget.wsdl.partnerAPI.APIObject  implements java.io.Serializable {
    private java.lang.String vendor;

    private java.lang.String kind;

    private boolean isUsernameRequired;

    private boolean isPasswordRequired;

    private boolean isProfileRequired;

    public MessagingVendorKind() {
    }

    public MessagingVendorKind(
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
           java.lang.String vendor,
           java.lang.String kind,
           boolean isUsernameRequired,
           boolean isPasswordRequired,
           boolean isProfileRequired) {
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
        this.vendor = vendor;
        this.kind = kind;
        this.isUsernameRequired = isUsernameRequired;
        this.isPasswordRequired = isPasswordRequired;
        this.isProfileRequired = isProfileRequired;
    }


    /**
     * Gets the vendor value for this MessagingVendorKind.
     * 
     * @return vendor
     */
    public java.lang.String getVendor() {
        return vendor;
    }


    /**
     * Sets the vendor value for this MessagingVendorKind.
     * 
     * @param vendor
     */
    public void setVendor(java.lang.String vendor) {
        this.vendor = vendor;
    }


    /**
     * Gets the kind value for this MessagingVendorKind.
     * 
     * @return kind
     */
    public java.lang.String getKind() {
        return kind;
    }


    /**
     * Sets the kind value for this MessagingVendorKind.
     * 
     * @param kind
     */
    public void setKind(java.lang.String kind) {
        this.kind = kind;
    }


    /**
     * Gets the isUsernameRequired value for this MessagingVendorKind.
     * 
     * @return isUsernameRequired
     */
    public boolean isIsUsernameRequired() {
        return isUsernameRequired;
    }


    /**
     * Sets the isUsernameRequired value for this MessagingVendorKind.
     * 
     * @param isUsernameRequired
     */
    public void setIsUsernameRequired(boolean isUsernameRequired) {
        this.isUsernameRequired = isUsernameRequired;
    }


    /**
     * Gets the isPasswordRequired value for this MessagingVendorKind.
     * 
     * @return isPasswordRequired
     */
    public boolean isIsPasswordRequired() {
        return isPasswordRequired;
    }


    /**
     * Sets the isPasswordRequired value for this MessagingVendorKind.
     * 
     * @param isPasswordRequired
     */
    public void setIsPasswordRequired(boolean isPasswordRequired) {
        this.isPasswordRequired = isPasswordRequired;
    }


    /**
     * Gets the isProfileRequired value for this MessagingVendorKind.
     * 
     * @return isProfileRequired
     */
    public boolean isIsProfileRequired() {
        return isProfileRequired;
    }


    /**
     * Sets the isProfileRequired value for this MessagingVendorKind.
     * 
     * @param isProfileRequired
     */
    public void setIsProfileRequired(boolean isProfileRequired) {
        this.isProfileRequired = isProfileRequired;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MessagingVendorKind)) return false;
        MessagingVendorKind other = (MessagingVendorKind) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.vendor==null && other.getVendor()==null) || 
             (this.vendor!=null &&
              this.vendor.equals(other.getVendor()))) &&
            ((this.kind==null && other.getKind()==null) || 
             (this.kind!=null &&
              this.kind.equals(other.getKind()))) &&
            this.isUsernameRequired == other.isIsUsernameRequired() &&
            this.isPasswordRequired == other.isIsPasswordRequired() &&
            this.isProfileRequired == other.isIsProfileRequired();
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
        if (getVendor() != null) {
            _hashCode += getVendor().hashCode();
        }
        if (getKind() != null) {
            _hashCode += getKind().hashCode();
        }
        _hashCode += (isIsUsernameRequired() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isIsPasswordRequired() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isIsProfileRequired() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MessagingVendorKind.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MessagingVendorKind"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vendor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Vendor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("kind");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Kind"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isUsernameRequired");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsUsernameRequired"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isPasswordRequired");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsPasswordRequired"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isProfileRequired");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsProfileRequired"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
