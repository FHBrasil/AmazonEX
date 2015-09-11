/**
 * Result.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class Result  implements java.io.Serializable {
    private java.lang.String statusCode;

    private java.lang.String statusMessage;

    private java.lang.Integer ordinalID;

    private java.lang.Integer errorCode;

    private java.lang.String requestID;

    private java.lang.String conversationID;

    private java.lang.String overallStatusCode;

    private com.exacttarget.wsdl.partnerAPI.RequestType requestType;

    private java.lang.String resultType;

    private java.lang.String resultDetailXML;

    public Result() {
    }

    public Result(
           java.lang.String statusCode,
           java.lang.String statusMessage,
           java.lang.Integer ordinalID,
           java.lang.Integer errorCode,
           java.lang.String requestID,
           java.lang.String conversationID,
           java.lang.String overallStatusCode,
           com.exacttarget.wsdl.partnerAPI.RequestType requestType,
           java.lang.String resultType,
           java.lang.String resultDetailXML) {
           this.statusCode = statusCode;
           this.statusMessage = statusMessage;
           this.ordinalID = ordinalID;
           this.errorCode = errorCode;
           this.requestID = requestID;
           this.conversationID = conversationID;
           this.overallStatusCode = overallStatusCode;
           this.requestType = requestType;
           this.resultType = resultType;
           this.resultDetailXML = resultDetailXML;
    }


    /**
     * Gets the statusCode value for this Result.
     * 
     * @return statusCode
     */
    public java.lang.String getStatusCode() {
        return statusCode;
    }


    /**
     * Sets the statusCode value for this Result.
     * 
     * @param statusCode
     */
    public void setStatusCode(java.lang.String statusCode) {
        this.statusCode = statusCode;
    }


    /**
     * Gets the statusMessage value for this Result.
     * 
     * @return statusMessage
     */
    public java.lang.String getStatusMessage() {
        return statusMessage;
    }


    /**
     * Sets the statusMessage value for this Result.
     * 
     * @param statusMessage
     */
    public void setStatusMessage(java.lang.String statusMessage) {
        this.statusMessage = statusMessage;
    }


    /**
     * Gets the ordinalID value for this Result.
     * 
     * @return ordinalID
     */
    public java.lang.Integer getOrdinalID() {
        return ordinalID;
    }


    /**
     * Sets the ordinalID value for this Result.
     * 
     * @param ordinalID
     */
    public void setOrdinalID(java.lang.Integer ordinalID) {
        this.ordinalID = ordinalID;
    }


    /**
     * Gets the errorCode value for this Result.
     * 
     * @return errorCode
     */
    public java.lang.Integer getErrorCode() {
        return errorCode;
    }


    /**
     * Sets the errorCode value for this Result.
     * 
     * @param errorCode
     */
    public void setErrorCode(java.lang.Integer errorCode) {
        this.errorCode = errorCode;
    }


    /**
     * Gets the requestID value for this Result.
     * 
     * @return requestID
     */
    public java.lang.String getRequestID() {
        return requestID;
    }


    /**
     * Sets the requestID value for this Result.
     * 
     * @param requestID
     */
    public void setRequestID(java.lang.String requestID) {
        this.requestID = requestID;
    }


    /**
     * Gets the conversationID value for this Result.
     * 
     * @return conversationID
     */
    public java.lang.String getConversationID() {
        return conversationID;
    }


    /**
     * Sets the conversationID value for this Result.
     * 
     * @param conversationID
     */
    public void setConversationID(java.lang.String conversationID) {
        this.conversationID = conversationID;
    }


    /**
     * Gets the overallStatusCode value for this Result.
     * 
     * @return overallStatusCode
     */
    public java.lang.String getOverallStatusCode() {
        return overallStatusCode;
    }


    /**
     * Sets the overallStatusCode value for this Result.
     * 
     * @param overallStatusCode
     */
    public void setOverallStatusCode(java.lang.String overallStatusCode) {
        this.overallStatusCode = overallStatusCode;
    }


    /**
     * Gets the requestType value for this Result.
     * 
     * @return requestType
     */
    public com.exacttarget.wsdl.partnerAPI.RequestType getRequestType() {
        return requestType;
    }


    /**
     * Sets the requestType value for this Result.
     * 
     * @param requestType
     */
    public void setRequestType(com.exacttarget.wsdl.partnerAPI.RequestType requestType) {
        this.requestType = requestType;
    }


    /**
     * Gets the resultType value for this Result.
     * 
     * @return resultType
     */
    public java.lang.String getResultType() {
        return resultType;
    }


    /**
     * Sets the resultType value for this Result.
     * 
     * @param resultType
     */
    public void setResultType(java.lang.String resultType) {
        this.resultType = resultType;
    }


    /**
     * Gets the resultDetailXML value for this Result.
     * 
     * @return resultDetailXML
     */
    public java.lang.String getResultDetailXML() {
        return resultDetailXML;
    }


    /**
     * Sets the resultDetailXML value for this Result.
     * 
     * @param resultDetailXML
     */
    public void setResultDetailXML(java.lang.String resultDetailXML) {
        this.resultDetailXML = resultDetailXML;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Result)) return false;
        Result other = (Result) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.statusCode==null && other.getStatusCode()==null) || 
             (this.statusCode!=null &&
              this.statusCode.equals(other.getStatusCode()))) &&
            ((this.statusMessage==null && other.getStatusMessage()==null) || 
             (this.statusMessage!=null &&
              this.statusMessage.equals(other.getStatusMessage()))) &&
            ((this.ordinalID==null && other.getOrdinalID()==null) || 
             (this.ordinalID!=null &&
              this.ordinalID.equals(other.getOrdinalID()))) &&
            ((this.errorCode==null && other.getErrorCode()==null) || 
             (this.errorCode!=null &&
              this.errorCode.equals(other.getErrorCode()))) &&
            ((this.requestID==null && other.getRequestID()==null) || 
             (this.requestID!=null &&
              this.requestID.equals(other.getRequestID()))) &&
            ((this.conversationID==null && other.getConversationID()==null) || 
             (this.conversationID!=null &&
              this.conversationID.equals(other.getConversationID()))) &&
            ((this.overallStatusCode==null && other.getOverallStatusCode()==null) || 
             (this.overallStatusCode!=null &&
              this.overallStatusCode.equals(other.getOverallStatusCode()))) &&
            ((this.requestType==null && other.getRequestType()==null) || 
             (this.requestType!=null &&
              this.requestType.equals(other.getRequestType()))) &&
            ((this.resultType==null && other.getResultType()==null) || 
             (this.resultType!=null &&
              this.resultType.equals(other.getResultType()))) &&
            ((this.resultDetailXML==null && other.getResultDetailXML()==null) || 
             (this.resultDetailXML!=null &&
              this.resultDetailXML.equals(other.getResultDetailXML())));
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
        if (getStatusCode() != null) {
            _hashCode += getStatusCode().hashCode();
        }
        if (getStatusMessage() != null) {
            _hashCode += getStatusMessage().hashCode();
        }
        if (getOrdinalID() != null) {
            _hashCode += getOrdinalID().hashCode();
        }
        if (getErrorCode() != null) {
            _hashCode += getErrorCode().hashCode();
        }
        if (getRequestID() != null) {
            _hashCode += getRequestID().hashCode();
        }
        if (getConversationID() != null) {
            _hashCode += getConversationID().hashCode();
        }
        if (getOverallStatusCode() != null) {
            _hashCode += getOverallStatusCode().hashCode();
        }
        if (getRequestType() != null) {
            _hashCode += getRequestType().hashCode();
        }
        if (getResultType() != null) {
            _hashCode += getResultType().hashCode();
        }
        if (getResultDetailXML() != null) {
            _hashCode += getResultDetailXML().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Result.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Result"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statusCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "StatusCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statusMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "StatusMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ordinalID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "OrdinalID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ErrorCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RequestID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("conversationID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ConversationID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("overallStatusCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "OverallStatusCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RequestType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RequestType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ResultType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultDetailXML");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ResultDetailXML"));
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
