/**
 * PlatformApplication.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class PlatformApplication  extends com.exacttarget.wsdl.partnerAPI.APIObject  implements java.io.Serializable {
    private com.exacttarget.wsdl.partnerAPI.PlatformApplicationPackage _package;

    private com.exacttarget.wsdl.partnerAPI.PlatformApplicationPackage[] packages;

    private com.exacttarget.wsdl.partnerAPI.ResourceSpecification resourceSpecification;

    private java.lang.String developerVersion;

    public PlatformApplication() {
    }

    public PlatformApplication(
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
           com.exacttarget.wsdl.partnerAPI.PlatformApplicationPackage _package,
           com.exacttarget.wsdl.partnerAPI.PlatformApplicationPackage[] packages,
           com.exacttarget.wsdl.partnerAPI.ResourceSpecification resourceSpecification,
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
        this._package = _package;
        this.packages = packages;
        this.resourceSpecification = resourceSpecification;
        this.developerVersion = developerVersion;
    }


    /**
     * Gets the _package value for this PlatformApplication.
     * 
     * @return _package
     */
    public com.exacttarget.wsdl.partnerAPI.PlatformApplicationPackage get_package() {
        return _package;
    }


    /**
     * Sets the _package value for this PlatformApplication.
     * 
     * @param _package
     */
    public void set_package(com.exacttarget.wsdl.partnerAPI.PlatformApplicationPackage _package) {
        this._package = _package;
    }


    /**
     * Gets the packages value for this PlatformApplication.
     * 
     * @return packages
     */
    public com.exacttarget.wsdl.partnerAPI.PlatformApplicationPackage[] getPackages() {
        return packages;
    }


    /**
     * Sets the packages value for this PlatformApplication.
     * 
     * @param packages
     */
    public void setPackages(com.exacttarget.wsdl.partnerAPI.PlatformApplicationPackage[] packages) {
        this.packages = packages;
    }

    public com.exacttarget.wsdl.partnerAPI.PlatformApplicationPackage getPackages(int i) {
        return this.packages[i];
    }

    public void setPackages(int i, com.exacttarget.wsdl.partnerAPI.PlatformApplicationPackage _value) {
        this.packages[i] = _value;
    }


    /**
     * Gets the resourceSpecification value for this PlatformApplication.
     * 
     * @return resourceSpecification
     */
    public com.exacttarget.wsdl.partnerAPI.ResourceSpecification getResourceSpecification() {
        return resourceSpecification;
    }


    /**
     * Sets the resourceSpecification value for this PlatformApplication.
     * 
     * @param resourceSpecification
     */
    public void setResourceSpecification(com.exacttarget.wsdl.partnerAPI.ResourceSpecification resourceSpecification) {
        this.resourceSpecification = resourceSpecification;
    }


    /**
     * Gets the developerVersion value for this PlatformApplication.
     * 
     * @return developerVersion
     */
    public java.lang.String getDeveloperVersion() {
        return developerVersion;
    }


    /**
     * Sets the developerVersion value for this PlatformApplication.
     * 
     * @param developerVersion
     */
    public void setDeveloperVersion(java.lang.String developerVersion) {
        this.developerVersion = developerVersion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PlatformApplication)) return false;
        PlatformApplication other = (PlatformApplication) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this._package==null && other.get_package()==null) || 
             (this._package!=null &&
              this._package.equals(other.get_package()))) &&
            ((this.packages==null && other.getPackages()==null) || 
             (this.packages!=null &&
              java.util.Arrays.equals(this.packages, other.getPackages()))) &&
            ((this.resourceSpecification==null && other.getResourceSpecification()==null) || 
             (this.resourceSpecification!=null &&
              this.resourceSpecification.equals(other.getResourceSpecification()))) &&
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
        if (get_package() != null) {
            _hashCode += get_package().hashCode();
        }
        if (getPackages() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPackages());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPackages(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getResourceSpecification() != null) {
            _hashCode += getResourceSpecification().hashCode();
        }
        if (getDeveloperVersion() != null) {
            _hashCode += getDeveloperVersion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PlatformApplication.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PlatformApplication"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("_package");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Package"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PlatformApplicationPackage"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("packages");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Packages"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PlatformApplicationPackage"));
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resourceSpecification");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ResourceSpecification"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ResourceSpecification"));
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
