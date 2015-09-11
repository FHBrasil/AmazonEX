/**
 * VersionInfoResponseMsg.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class VersionInfoResponseMsg  implements java.io.Serializable {
    private com.exacttarget.wsdl.partnerAPI.VersionInfoResponse versionInfo;

    private java.lang.String requestID;

    public VersionInfoResponseMsg() {
    }

    public VersionInfoResponseMsg(
           com.exacttarget.wsdl.partnerAPI.VersionInfoResponse versionInfo,
           java.lang.String requestID) {
           this.versionInfo = versionInfo;
           this.requestID = requestID;
    }


    /**
     * Gets the versionInfo value for this VersionInfoResponseMsg.
     * 
     * @return versionInfo
     */
    public com.exacttarget.wsdl.partnerAPI.VersionInfoResponse getVersionInfo() {
        return versionInfo;
    }


    /**
     * Sets the versionInfo value for this VersionInfoResponseMsg.
     * 
     * @param versionInfo
     */
    public void setVersionInfo(com.exacttarget.wsdl.partnerAPI.VersionInfoResponse versionInfo) {
        this.versionInfo = versionInfo;
    }


    /**
     * Gets the requestID value for this VersionInfoResponseMsg.
     * 
     * @return requestID
     */
    public java.lang.String getRequestID() {
        return requestID;
    }


    /**
     * Sets the requestID value for this VersionInfoResponseMsg.
     * 
     * @param requestID
     */
    public void setRequestID(java.lang.String requestID) {
        this.requestID = requestID;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof VersionInfoResponseMsg)) return false;
        VersionInfoResponseMsg other = (VersionInfoResponseMsg) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.versionInfo==null && other.getVersionInfo()==null) || 
             (this.versionInfo!=null &&
              this.versionInfo.equals(other.getVersionInfo()))) &&
            ((this.requestID==null && other.getRequestID()==null) || 
             (this.requestID!=null &&
              this.requestID.equals(other.getRequestID())));
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
        if (getVersionInfo() != null) {
            _hashCode += getVersionInfo().hashCode();
        }
        if (getRequestID() != null) {
            _hashCode += getRequestID().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(VersionInfoResponseMsg.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">VersionInfoResponseMsg"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("versionInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "VersionInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "VersionInfoResponse"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RequestID"));
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
