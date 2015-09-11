/**
 * SendSMSMOKeyword.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class SendSMSMOKeyword  extends com.exacttarget.wsdl.partnerAPI.BaseMOKeyword  implements java.io.Serializable {
    private com.exacttarget.wsdl.partnerAPI.BaseMOKeyword nextMOKeyword;

    private java.lang.String message;

    private java.lang.String scriptErrorMessage;

    public SendSMSMOKeyword() {
    }

    public SendSMSMOKeyword(
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
           java.lang.Boolean isDefaultKeyword,
           com.exacttarget.wsdl.partnerAPI.BaseMOKeyword nextMOKeyword,
           java.lang.String message,
           java.lang.String scriptErrorMessage) {
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
            objectState,
            isDefaultKeyword);
        this.nextMOKeyword = nextMOKeyword;
        this.message = message;
        this.scriptErrorMessage = scriptErrorMessage;
    }


    /**
     * Gets the nextMOKeyword value for this SendSMSMOKeyword.
     * 
     * @return nextMOKeyword
     */
    public com.exacttarget.wsdl.partnerAPI.BaseMOKeyword getNextMOKeyword() {
        return nextMOKeyword;
    }


    /**
     * Sets the nextMOKeyword value for this SendSMSMOKeyword.
     * 
     * @param nextMOKeyword
     */
    public void setNextMOKeyword(com.exacttarget.wsdl.partnerAPI.BaseMOKeyword nextMOKeyword) {
        this.nextMOKeyword = nextMOKeyword;
    }


    /**
     * Gets the message value for this SendSMSMOKeyword.
     * 
     * @return message
     */
    public java.lang.String getMessage() {
        return message;
    }


    /**
     * Sets the message value for this SendSMSMOKeyword.
     * 
     * @param message
     */
    public void setMessage(java.lang.String message) {
        this.message = message;
    }


    /**
     * Gets the scriptErrorMessage value for this SendSMSMOKeyword.
     * 
     * @return scriptErrorMessage
     */
    public java.lang.String getScriptErrorMessage() {
        return scriptErrorMessage;
    }


    /**
     * Sets the scriptErrorMessage value for this SendSMSMOKeyword.
     * 
     * @param scriptErrorMessage
     */
    public void setScriptErrorMessage(java.lang.String scriptErrorMessage) {
        this.scriptErrorMessage = scriptErrorMessage;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SendSMSMOKeyword)) return false;
        SendSMSMOKeyword other = (SendSMSMOKeyword) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.nextMOKeyword==null && other.getNextMOKeyword()==null) || 
             (this.nextMOKeyword!=null &&
              this.nextMOKeyword.equals(other.getNextMOKeyword()))) &&
            ((this.message==null && other.getMessage()==null) || 
             (this.message!=null &&
              this.message.equals(other.getMessage()))) &&
            ((this.scriptErrorMessage==null && other.getScriptErrorMessage()==null) || 
             (this.scriptErrorMessage!=null &&
              this.scriptErrorMessage.equals(other.getScriptErrorMessage())));
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
        if (getNextMOKeyword() != null) {
            _hashCode += getNextMOKeyword().hashCode();
        }
        if (getMessage() != null) {
            _hashCode += getMessage().hashCode();
        }
        if (getScriptErrorMessage() != null) {
            _hashCode += getScriptErrorMessage().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SendSMSMOKeyword.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendSMSMOKeyword"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nextMOKeyword");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "NextMOKeyword"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "BaseMOKeyword"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("message");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Message"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("scriptErrorMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ScriptErrorMessage"));
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
