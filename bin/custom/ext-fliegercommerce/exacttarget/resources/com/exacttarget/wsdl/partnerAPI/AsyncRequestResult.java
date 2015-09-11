/**
 * AsyncRequestResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class AsyncRequestResult  extends com.exacttarget.wsdl.partnerAPI.APIObject  implements java.io.Serializable {
    private java.lang.String status;

    private java.util.Calendar completeDate;

    private java.lang.String callStatus;

    private java.lang.String callMessage;

    public AsyncRequestResult() {
    }

    public AsyncRequestResult(
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
           java.lang.String status,
           java.util.Calendar completeDate,
           java.lang.String callStatus,
           java.lang.String callMessage) {
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
        this.status = status;
        this.completeDate = completeDate;
        this.callStatus = callStatus;
        this.callMessage = callMessage;
    }


    /**
     * Gets the status value for this AsyncRequestResult.
     * 
     * @return status
     */
    public java.lang.String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this AsyncRequestResult.
     * 
     * @param status
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }


    /**
     * Gets the completeDate value for this AsyncRequestResult.
     * 
     * @return completeDate
     */
    public java.util.Calendar getCompleteDate() {
        return completeDate;
    }


    /**
     * Sets the completeDate value for this AsyncRequestResult.
     * 
     * @param completeDate
     */
    public void setCompleteDate(java.util.Calendar completeDate) {
        this.completeDate = completeDate;
    }


    /**
     * Gets the callStatus value for this AsyncRequestResult.
     * 
     * @return callStatus
     */
    public java.lang.String getCallStatus() {
        return callStatus;
    }


    /**
     * Sets the callStatus value for this AsyncRequestResult.
     * 
     * @param callStatus
     */
    public void setCallStatus(java.lang.String callStatus) {
        this.callStatus = callStatus;
    }


    /**
     * Gets the callMessage value for this AsyncRequestResult.
     * 
     * @return callMessage
     */
    public java.lang.String getCallMessage() {
        return callMessage;
    }


    /**
     * Sets the callMessage value for this AsyncRequestResult.
     * 
     * @param callMessage
     */
    public void setCallMessage(java.lang.String callMessage) {
        this.callMessage = callMessage;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AsyncRequestResult)) return false;
        AsyncRequestResult other = (AsyncRequestResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.completeDate==null && other.getCompleteDate()==null) || 
             (this.completeDate!=null &&
              this.completeDate.equals(other.getCompleteDate()))) &&
            ((this.callStatus==null && other.getCallStatus()==null) || 
             (this.callStatus!=null &&
              this.callStatus.equals(other.getCallStatus()))) &&
            ((this.callMessage==null && other.getCallMessage()==null) || 
             (this.callMessage!=null &&
              this.callMessage.equals(other.getCallMessage())));
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
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getCompleteDate() != null) {
            _hashCode += getCompleteDate().hashCode();
        }
        if (getCallStatus() != null) {
            _hashCode += getCallStatus().hashCode();
        }
        if (getCallMessage() != null) {
            _hashCode += getCallMessage().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AsyncRequestResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AsyncRequestResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("completeDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "CompleteDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("callStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "CallStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("callMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "CallMessage"));
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
