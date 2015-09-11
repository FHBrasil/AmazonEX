/**
 * SystemStatusResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class SystemStatusResult  extends com.exacttarget.wsdl.partnerAPI.Result  implements java.io.Serializable {
    private com.exacttarget.wsdl.partnerAPI.SystemStatusType systemStatus;

    private com.exacttarget.wsdl.partnerAPI.SystemOutage[] outages;

    public SystemStatusResult() {
    }

    public SystemStatusResult(
           java.lang.String statusCode,
           java.lang.String statusMessage,
           java.lang.Integer ordinalID,
           java.lang.Integer errorCode,
           java.lang.String requestID,
           java.lang.String conversationID,
           java.lang.String overallStatusCode,
           com.exacttarget.wsdl.partnerAPI.RequestType requestType,
           java.lang.String resultType,
           java.lang.String resultDetailXML,
           com.exacttarget.wsdl.partnerAPI.SystemStatusType systemStatus,
           com.exacttarget.wsdl.partnerAPI.SystemOutage[] outages) {
        super(
            statusCode,
            statusMessage,
            ordinalID,
            errorCode,
            requestID,
            conversationID,
            overallStatusCode,
            requestType,
            resultType,
            resultDetailXML);
        this.systemStatus = systemStatus;
        this.outages = outages;
    }


    /**
     * Gets the systemStatus value for this SystemStatusResult.
     * 
     * @return systemStatus
     */
    public com.exacttarget.wsdl.partnerAPI.SystemStatusType getSystemStatus() {
        return systemStatus;
    }


    /**
     * Sets the systemStatus value for this SystemStatusResult.
     * 
     * @param systemStatus
     */
    public void setSystemStatus(com.exacttarget.wsdl.partnerAPI.SystemStatusType systemStatus) {
        this.systemStatus = systemStatus;
    }


    /**
     * Gets the outages value for this SystemStatusResult.
     * 
     * @return outages
     */
    public com.exacttarget.wsdl.partnerAPI.SystemOutage[] getOutages() {
        return outages;
    }


    /**
     * Sets the outages value for this SystemStatusResult.
     * 
     * @param outages
     */
    public void setOutages(com.exacttarget.wsdl.partnerAPI.SystemOutage[] outages) {
        this.outages = outages;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SystemStatusResult)) return false;
        SystemStatusResult other = (SystemStatusResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.systemStatus==null && other.getSystemStatus()==null) || 
             (this.systemStatus!=null &&
              this.systemStatus.equals(other.getSystemStatus()))) &&
            ((this.outages==null && other.getOutages()==null) || 
             (this.outages!=null &&
              java.util.Arrays.equals(this.outages, other.getOutages())));
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
        if (getSystemStatus() != null) {
            _hashCode += getSystemStatus().hashCode();
        }
        if (getOutages() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getOutages());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getOutages(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SystemStatusResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SystemStatusResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("systemStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SystemStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SystemStatusType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("outages");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Outages"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SystemOutage"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Outage"));
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
