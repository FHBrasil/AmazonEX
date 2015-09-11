/**
 * RetrieveSingleRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class RetrieveSingleRequest  extends com.exacttarget.wsdl.partnerAPI.Request  implements java.io.Serializable {
    private com.exacttarget.wsdl.partnerAPI.APIObject requestedObject;

    private com.exacttarget.wsdl.partnerAPI.Options retrieveOption;

    public RetrieveSingleRequest() {
    }

    public RetrieveSingleRequest(
           com.exacttarget.wsdl.partnerAPI.APIObject requestedObject,
           com.exacttarget.wsdl.partnerAPI.Options retrieveOption) {
        this.requestedObject = requestedObject;
        this.retrieveOption = retrieveOption;
    }


    /**
     * Gets the requestedObject value for this RetrieveSingleRequest.
     * 
     * @return requestedObject
     */
    public com.exacttarget.wsdl.partnerAPI.APIObject getRequestedObject() {
        return requestedObject;
    }


    /**
     * Sets the requestedObject value for this RetrieveSingleRequest.
     * 
     * @param requestedObject
     */
    public void setRequestedObject(com.exacttarget.wsdl.partnerAPI.APIObject requestedObject) {
        this.requestedObject = requestedObject;
    }


    /**
     * Gets the retrieveOption value for this RetrieveSingleRequest.
     * 
     * @return retrieveOption
     */
    public com.exacttarget.wsdl.partnerAPI.Options getRetrieveOption() {
        return retrieveOption;
    }


    /**
     * Sets the retrieveOption value for this RetrieveSingleRequest.
     * 
     * @param retrieveOption
     */
    public void setRetrieveOption(com.exacttarget.wsdl.partnerAPI.Options retrieveOption) {
        this.retrieveOption = retrieveOption;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RetrieveSingleRequest)) return false;
        RetrieveSingleRequest other = (RetrieveSingleRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.requestedObject==null && other.getRequestedObject()==null) || 
             (this.requestedObject!=null &&
              this.requestedObject.equals(other.getRequestedObject()))) &&
            ((this.retrieveOption==null && other.getRetrieveOption()==null) || 
             (this.retrieveOption!=null &&
              this.retrieveOption.equals(other.getRetrieveOption())));
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
        if (getRequestedObject() != null) {
            _hashCode += getRequestedObject().hashCode();
        }
        if (getRetrieveOption() != null) {
            _hashCode += getRetrieveOption().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RetrieveSingleRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RetrieveSingleRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestedObject");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RequestedObject"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "APIObject"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("retrieveOption");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RetrieveOption"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Options"));
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
