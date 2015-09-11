/**
 * PlatformApplicationPackage.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class PlatformApplicationPackage  extends com.exacttarget.wsdl.partnerAPI.APIObject  implements java.io.Serializable {
    private com.exacttarget.wsdl.partnerAPI.ResourceSpecification resourceSpecification;

    private com.exacttarget.wsdl.partnerAPI.PublicKeyManagement signingKey;

    private java.lang.Boolean isUpgrade;

    private java.lang.String developerVersion;

    public PlatformApplicationPackage() {
    }

    public PlatformApplicationPackage(
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
           com.exacttarget.wsdl.partnerAPI.ResourceSpecification resourceSpecification,
           com.exacttarget.wsdl.partnerAPI.PublicKeyManagement signingKey,
           java.lang.Boolean isUpgrade,
           java.lang.String developerVersion) {
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
        this.resourceSpecification = resourceSpecification;
        this.signingKey = signingKey;
        this.isUpgrade = isUpgrade;
        this.developerVersion = developerVersion;
    }


    /**
     * Gets the resourceSpecification value for this PlatformApplicationPackage.
     * 
     * @return resourceSpecification
     */
    public com.exacttarget.wsdl.partnerAPI.ResourceSpecification getResourceSpecification() {
        return resourceSpecification;
    }


    /**
     * Sets the resourceSpecification value for this PlatformApplicationPackage.
     * 
     * @param resourceSpecification
     */
    public void setResourceSpecification(com.exacttarget.wsdl.partnerAPI.ResourceSpecification resourceSpecification) {
        this.resourceSpecification = resourceSpecification;
    }


    /**
     * Gets the signingKey value for this PlatformApplicationPackage.
     * 
     * @return signingKey
     */
    public com.exacttarget.wsdl.partnerAPI.PublicKeyManagement getSigningKey() {
        return signingKey;
    }


    /**
     * Sets the signingKey value for this PlatformApplicationPackage.
     * 
     * @param signingKey
     */
    public void setSigningKey(com.exacttarget.wsdl.partnerAPI.PublicKeyManagement signingKey) {
        this.signingKey = signingKey;
    }


    /**
     * Gets the isUpgrade value for this PlatformApplicationPackage.
     * 
     * @return isUpgrade
     */
    public java.lang.Boolean getIsUpgrade() {
        return isUpgrade;
    }


    /**
     * Sets the isUpgrade value for this PlatformApplicationPackage.
     * 
     * @param isUpgrade
     */
    public void setIsUpgrade(java.lang.Boolean isUpgrade) {
        this.isUpgrade = isUpgrade;
    }


    /**
     * Gets the developerVersion value for this PlatformApplicationPackage.
     * 
     * @return developerVersion
     */
    public java.lang.String getDeveloperVersion() {
        return developerVersion;
    }


    /**
     * Sets the developerVersion value for this PlatformApplicationPackage.
     * 
     * @param developerVersion
     */
    public void setDeveloperVersion(java.lang.String developerVersion) {
        this.developerVersion = developerVersion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PlatformApplicationPackage)) return false;
        PlatformApplicationPackage other = (PlatformApplicationPackage) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.resourceSpecification==null && other.getResourceSpecification()==null) || 
             (this.resourceSpecification!=null &&
              this.resourceSpecification.equals(other.getResourceSpecification()))) &&
            ((this.signingKey==null && other.getSigningKey()==null) || 
             (this.signingKey!=null &&
              this.signingKey.equals(other.getSigningKey()))) &&
            ((this.isUpgrade==null && other.getIsUpgrade()==null) || 
             (this.isUpgrade!=null &&
              this.isUpgrade.equals(other.getIsUpgrade()))) &&
            ((this.developerVersion==null && other.getDeveloperVersion()==null) || 
             (this.developerVersion!=null &&
              this.developerVersion.equals(other.getDeveloperVersion())));
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
        if (getResourceSpecification() != null) {
            _hashCode += getResourceSpecification().hashCode();
        }
        if (getSigningKey() != null) {
            _hashCode += getSigningKey().hashCode();
        }
        if (getIsUpgrade() != null) {
            _hashCode += getIsUpgrade().hashCode();
        }
        if (getDeveloperVersion() != null) {
            _hashCode += getDeveloperVersion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PlatformApplicationPackage.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PlatformApplicationPackage"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resourceSpecification");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ResourceSpecification"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ResourceSpecification"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("signingKey");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SigningKey"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PublicKeyManagement"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isUpgrade");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsUpgrade"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("developerVersion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DeveloperVersion"));
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
