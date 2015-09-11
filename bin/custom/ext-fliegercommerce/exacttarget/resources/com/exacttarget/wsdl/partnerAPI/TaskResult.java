/**
 * TaskResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class TaskResult  implements java.io.Serializable {
    private java.lang.String statusCode;

    private java.lang.String statusMessage;

    private java.lang.Integer ordinalID;

    private java.lang.Integer errorCode;

    private java.lang.String ID;

    private java.lang.String interactionObjectID;

    public TaskResult() {
    }

    public TaskResult(
           java.lang.String statusCode,
           java.lang.String statusMessage,
           java.lang.Integer ordinalID,
           java.lang.Integer errorCode,
           java.lang.String ID,
           java.lang.String interactionObjectID) {
           this.statusCode = statusCode;
           this.statusMessage = statusMessage;
           this.ordinalID = ordinalID;
           this.errorCode = errorCode;
           this.ID = ID;
           this.interactionObjectID = interactionObjectID;
    }


    /**
     * Gets the statusCode value for this TaskResult.
     * 
     * @return statusCode
     */
    public java.lang.String getStatusCode() {
        return statusCode;
    }


    /**
     * Sets the statusCode value for this TaskResult.
     * 
     * @param statusCode
     */
    public void setStatusCode(java.lang.String statusCode) {
        this.statusCode = statusCode;
    }


    /**
     * Gets the statusMessage value for this TaskResult.
     * 
     * @return statusMessage
     */
    public java.lang.String getStatusMessage() {
        return statusMessage;
    }


    /**
     * Sets the statusMessage value for this TaskResult.
     * 
     * @param statusMessage
     */
    public void setStatusMessage(java.lang.String statusMessage) {
        this.statusMessage = statusMessage;
    }


    /**
     * Gets the ordinalID value for this TaskResult.
     * 
     * @return ordinalID
     */
    public java.lang.Integer getOrdinalID() {
        return ordinalID;
    }


    /**
     * Sets the ordinalID value for this TaskResult.
     * 
     * @param ordinalID
     */
    public void setOrdinalID(java.lang.Integer ordinalID) {
        this.ordinalID = ordinalID;
    }


    /**
     * Gets the errorCode value for this TaskResult.
     * 
     * @return errorCode
     */
    public java.lang.Integer getErrorCode() {
        return errorCode;
    }


    /**
     * Sets the errorCode value for this TaskResult.
     * 
     * @param errorCode
     */
    public void setErrorCode(java.lang.Integer errorCode) {
        this.errorCode = errorCode;
    }


    /**
     * Gets the ID value for this TaskResult.
     * 
     * @return ID
     */
    public java.lang.String getID() {
        return ID;
    }


    /**
     * Sets the ID value for this TaskResult.
     * 
     * @param ID
     */
    public void setID(java.lang.String ID) {
        this.ID = ID;
    }


    /**
     * Gets the interactionObjectID value for this TaskResult.
     * 
     * @return interactionObjectID
     */
    public java.lang.String getInteractionObjectID() {
        return interactionObjectID;
    }


    /**
     * Sets the interactionObjectID value for this TaskResult.
     * 
     * @param interactionObjectID
     */
    public void setInteractionObjectID(java.lang.String interactionObjectID) {
        this.interactionObjectID = interactionObjectID;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TaskResult)) return false;
        TaskResult other = (TaskResult) obj;
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
            ((this.ID==null && other.getID()==null) || 
             (this.ID!=null &&
              this.ID.equals(other.getID()))) &&
            ((this.interactionObjectID==null && other.getInteractionObjectID()==null) || 
             (this.interactionObjectID!=null &&
              this.interactionObjectID.equals(other.getInteractionObjectID())));
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
        if (getID() != null) {
            _hashCode += getID().hashCode();
        }
        if (getInteractionObjectID() != null) {
            _hashCode += getInteractionObjectID().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TaskResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TaskResult"));
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
        elemField.setFieldName("ID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("interactionObjectID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "InteractionObjectID"));
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
