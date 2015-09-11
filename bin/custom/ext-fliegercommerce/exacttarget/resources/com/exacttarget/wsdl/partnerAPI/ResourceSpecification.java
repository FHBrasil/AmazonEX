/**
 * ResourceSpecification.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class ResourceSpecification  extends com.exacttarget.wsdl.partnerAPI.APIObject  implements java.io.Serializable {
    private java.lang.String URN;

    private com.exacttarget.wsdl.partnerAPI.Authentication authentication;

    public ResourceSpecification() {
    }

    public ResourceSpecification(
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
           java.lang.String URN,
           com.exacttarget.wsdl.partnerAPI.Authentication authentication) {
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
        this.URN = URN;
        this.authentication = authentication;
    }


    /**
     * Gets the URN value for this ResourceSpecification.
     * 
     * @return URN
     */
    public java.lang.String getURN() {
        return URN;
    }


    /**
     * Sets the URN value for this ResourceSpecification.
     * 
     * @param URN
     */
    public void setURN(java.lang.String URN) {
        this.URN = URN;
    }


    /**
     * Gets the authentication value for this ResourceSpecification.
     * 
     * @return authentication
     */
    public com.exacttarget.wsdl.partnerAPI.Authentication getAuthentication() {
        return authentication;
    }


    /**
     * Sets the authentication value for this ResourceSpecification.
     * 
     * @param authentication
     */
    public void setAuthentication(com.exacttarget.wsdl.partnerAPI.Authentication authentication) {
        this.authentication = authentication;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResourceSpecification)) return false;
        ResourceSpecification other = (ResourceSpecification) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.URN==null && other.getURN()==null) || 
             (this.URN!=null &&
              this.URN.equals(other.getURN()))) &&
            ((this.authentication==null && other.getAuthentication()==null) || 
             (this.authentication!=null &&
              this.authentication.equals(other.getAuthentication())));
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
        if (getURN() != null) {
            _hashCode += getURN().hashCode();
        }
        if (getAuthentication() != null) {
            _hashCode += getAuthentication().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResourceSpecification.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ResourceSpecification"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("URN");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "URN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authentication");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Authentication"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Authentication"));
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
