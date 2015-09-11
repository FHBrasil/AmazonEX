/**
 * ResultItem.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class ResultItem  extends com.exacttarget.wsdl.partnerAPI.APIObject  implements java.io.Serializable {
    private java.lang.String requestID;

    private java.lang.String conversationID;

    private java.lang.String statusCode;

    private java.lang.String statusMessage;

    private java.lang.Integer ordinalID;

    private java.lang.Integer errorCode;

    private com.exacttarget.wsdl.partnerAPI.RequestType requestType;

    private java.lang.String requestObjectType;

    public ResultItem() {
    }

    public ResultItem(
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
           java.lang.String requestID,
           java.lang.String conversationID,
           java.lang.String statusCode,
           java.lang.String statusMessage,
           java.lang.Integer ordinalID,
           java.lang.Integer errorCode,
           com.exacttarget.wsdl.partnerAPI.RequestType requestType,
           java.lang.String requestObjectType) {
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
        this.requestID = requestID;
        this.conversationID = conversationID;
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.ordinalID = ordinalID;
        this.errorCode = errorCode;
        this.requestType = requestType;
        this.requestObjectType = requestObjectType;
    }


    /**
     * Gets the requestID value for this ResultItem.
     * 
     * @return requestID
     */
    public java.lang.String getRequestID() {
        return requestID;
    }


    /**
     * Sets the requestID value for this ResultItem.
     * 
     * @param requestID
     */
    public void setRequestID(java.lang.String requestID) {
        this.requestID = requestID;
    }


    /**
     * Gets the conversationID value for this ResultItem.
     * 
     * @return conversationID
     */
    public java.lang.String getConversationID() {
        return conversationID;
    }


    /**
     * Sets the conversationID value for this ResultItem.
     * 
     * @param conversationID
     */
    public void setConversationID(java.lang.String conversationID) {
        this.conversationID = conversationID;
    }


    /**
     * Gets the statusCode value for this ResultItem.
     * 
     * @return statusCode
     */
    public java.lang.String getStatusCode() {
        return statusCode;
    }


    /**
     * Sets the statusCode value for this ResultItem.
     * 
     * @param statusCode
     */
    public void setStatusCode(java.lang.String statusCode) {
        this.statusCode = statusCode;
    }


    /**
     * Gets the statusMessage value for this ResultItem.
     * 
     * @return statusMessage
     */
    public java.lang.String getStatusMessage() {
        return statusMessage;
    }


    /**
     * Sets the statusMessage value for this ResultItem.
     * 
     * @param statusMessage
     */
    public void setStatusMessage(java.lang.String statusMessage) {
        this.statusMessage = statusMessage;
    }


    /**
     * Gets the ordinalID value for this ResultItem.
     * 
     * @return ordinalID
     */
    public java.lang.Integer getOrdinalID() {
        return ordinalID;
    }


    /**
     * Sets the ordinalID value for this ResultItem.
     * 
     * @param ordinalID
     */
    public void setOrdinalID(java.lang.Integer ordinalID) {
        this.ordinalID = ordinalID;
    }


    /**
     * Gets the errorCode value for this ResultItem.
     * 
     * @return errorCode
     */
    public java.lang.Integer getErrorCode() {
        return errorCode;
    }


    /**
     * Sets the errorCode value for this ResultItem.
     * 
     * @param errorCode
     */
    public void setErrorCode(java.lang.Integer errorCode) {
        this.errorCode = errorCode;
    }


    /**
     * Gets the requestType value for this ResultItem.
     * 
     * @return requestType
     */
    public com.exacttarget.wsdl.partnerAPI.RequestType getRequestType() {
        return requestType;
    }


    /**
     * Sets the requestType value for this ResultItem.
     * 
     * @param requestType
     */
    public void setRequestType(com.exacttarget.wsdl.partnerAPI.RequestType requestType) {
        this.requestType = requestType;
    }


    /**
     * Gets the requestObjectType value for this ResultItem.
     * 
     * @return requestObjectType
     */
    public java.lang.String getRequestObjectType() {
        return requestObjectType;
    }


    /**
     * Sets the requestObjectType value for this ResultItem.
     * 
     * @param requestObjectType
     */
    public void setRequestObjectType(java.lang.String requestObjectType) {
        this.requestObjectType = requestObjectType;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultItem)) return false;
        ResultItem other = (ResultItem) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.requestID==null && other.getRequestID()==null) || 
             (this.requestID!=null &&
              this.requestID.equals(other.getRequestID()))) &&
            ((this.conversationID==null && other.getConversationID()==null) || 
             (this.conversationID!=null &&
              this.conversationID.equals(other.getConversationID()))) &&
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
            ((this.requestType==null && other.getRequestType()==null) || 
             (this.requestType!=null &&
              this.requestType.equals(other.getRequestType()))) &&
            ((this.requestObjectType==null && other.getRequestObjectType()==null) || 
             (this.requestObjectType!=null &&
              this.requestObjectType.equals(other.getRequestObjectType())));
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
        if (getRequestID() != null) {
            _hashCode += getRequestID().hashCode();
        }
        if (getConversationID() != null) {
            _hashCode += getConversationID().hashCode();
        }
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
        if (getRequestType() != null) {
            _hashCode += getRequestType().hashCode();
        }
        if (getRequestObjectType() != null) {
            _hashCode += getRequestObjectType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultItem.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ResultItem"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setFieldName("requestType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RequestType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RequestType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestObjectType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RequestObjectType"));
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
