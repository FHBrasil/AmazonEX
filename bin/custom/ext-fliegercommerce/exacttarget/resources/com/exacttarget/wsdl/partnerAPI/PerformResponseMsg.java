/**
 * PerformResponseMsg.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class PerformResponseMsg  implements java.io.Serializable {
    private com.exacttarget.wsdl.partnerAPI.PerformResult[] results;

    private java.lang.String overallStatus;

    private java.lang.String overallStatusMessage;

    private java.lang.String requestID;

    public PerformResponseMsg() {
    }

    public PerformResponseMsg(
           com.exacttarget.wsdl.partnerAPI.PerformResult[] results,
           java.lang.String overallStatus,
           java.lang.String overallStatusMessage,
           java.lang.String requestID) {
           this.results = results;
           this.overallStatus = overallStatus;
           this.overallStatusMessage = overallStatusMessage;
           this.requestID = requestID;
    }


    /**
     * Gets the results value for this PerformResponseMsg.
     * 
     * @return results
     */
    public com.exacttarget.wsdl.partnerAPI.PerformResult[] getResults() {
        return results;
    }


    /**
     * Sets the results value for this PerformResponseMsg.
     * 
     * @param results
     */
    public void setResults(com.exacttarget.wsdl.partnerAPI.PerformResult[] results) {
        this.results = results;
    }


    /**
     * Gets the overallStatus value for this PerformResponseMsg.
     * 
     * @return overallStatus
     */
    public java.lang.String getOverallStatus() {
        return overallStatus;
    }


    /**
     * Sets the overallStatus value for this PerformResponseMsg.
     * 
     * @param overallStatus
     */
    public void setOverallStatus(java.lang.String overallStatus) {
        this.overallStatus = overallStatus;
    }


    /**
     * Gets the overallStatusMessage value for this PerformResponseMsg.
     * 
     * @return overallStatusMessage
     */
    public java.lang.String getOverallStatusMessage() {
        return overallStatusMessage;
    }


    /**
     * Sets the overallStatusMessage value for this PerformResponseMsg.
     * 
     * @param overallStatusMessage
     */
    public void setOverallStatusMessage(java.lang.String overallStatusMessage) {
        this.overallStatusMessage = overallStatusMessage;
    }


    /**
     * Gets the requestID value for this PerformResponseMsg.
     * 
     * @return requestID
     */
    public java.lang.String getRequestID() {
        return requestID;
    }


    /**
     * Sets the requestID value for this PerformResponseMsg.
     * 
     * @param requestID
     */
    public void setRequestID(java.lang.String requestID) {
        this.requestID = requestID;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PerformResponseMsg)) return false;
        PerformResponseMsg other = (PerformResponseMsg) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.results==null && other.getResults()==null) || 
             (this.results!=null &&
              java.util.Arrays.equals(this.results, other.getResults()))) &&
            ((this.overallStatus==null && other.getOverallStatus()==null) || 
             (this.overallStatus!=null &&
              this.overallStatus.equals(other.getOverallStatus()))) &&
            ((this.overallStatusMessage==null && other.getOverallStatusMessage()==null) || 
             (this.overallStatusMessage!=null &&
              this.overallStatusMessage.equals(other.getOverallStatusMessage()))) &&
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
        if (getResults() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getResults());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getResults(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getOverallStatus() != null) {
            _hashCode += getOverallStatus().hashCode();
        }
        if (getOverallStatusMessage() != null) {
            _hashCode += getOverallStatusMessage().hashCode();
        }
        if (getRequestID() != null) {
            _hashCode += getRequestID().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PerformResponseMsg.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">PerformResponseMsg"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("results");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Results"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PerformResult"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Result"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("overallStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "OverallStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("overallStatusMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "OverallStatusMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
