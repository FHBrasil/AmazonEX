/**
 * RetrieveRequestMsg.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class RetrieveRequestMsg  implements java.io.Serializable {
    private com.exacttarget.wsdl.partnerAPI.RetrieveRequest retrieveRequest;

    public RetrieveRequestMsg() {
    }

    public RetrieveRequestMsg(
           com.exacttarget.wsdl.partnerAPI.RetrieveRequest retrieveRequest) {
           this.retrieveRequest = retrieveRequest;
    }


    /**
     * Gets the retrieveRequest value for this RetrieveRequestMsg.
     * 
     * @return retrieveRequest
     */
    public com.exacttarget.wsdl.partnerAPI.RetrieveRequest getRetrieveRequest() {
        return retrieveRequest;
    }


    /**
     * Sets the retrieveRequest value for this RetrieveRequestMsg.
     * 
     * @param retrieveRequest
     */
    public void setRetrieveRequest(com.exacttarget.wsdl.partnerAPI.RetrieveRequest retrieveRequest) {
        this.retrieveRequest = retrieveRequest;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RetrieveRequestMsg)) return false;
        RetrieveRequestMsg other = (RetrieveRequestMsg) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.retrieveRequest==null && other.getRetrieveRequest()==null) || 
             (this.retrieveRequest!=null &&
              this.retrieveRequest.equals(other.getRetrieveRequest())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getRetrieveRequest() != null) {
            _hashCode += getRetrieveRequest().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RetrieveRequestMsg.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">RetrieveRequestMsg"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("retrieveRequest");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RetrieveRequest"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RetrieveRequest"));
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
