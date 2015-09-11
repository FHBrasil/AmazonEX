/**
 * VoiceTriggeredSend.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class VoiceTriggeredSend  extends com.exacttarget.wsdl.partnerAPI.APIObject  implements java.io.Serializable {
    private com.exacttarget.wsdl.partnerAPI.VoiceTriggeredSendDefinition voiceTriggeredSendDefinition;

    private com.exacttarget.wsdl.partnerAPI.Subscriber subscriber;

    private java.lang.String message;

    private java.lang.String number;

    private java.lang.String transferMessage;

    private java.lang.String transferNumber;

    public VoiceTriggeredSend() {
    }

    public VoiceTriggeredSend(
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
           com.exacttarget.wsdl.partnerAPI.VoiceTriggeredSendDefinition voiceTriggeredSendDefinition,
           com.exacttarget.wsdl.partnerAPI.Subscriber subscriber,
           java.lang.String message,
           java.lang.String number,
           java.lang.String transferMessage,
           java.lang.String transferNumber) {
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
        this.voiceTriggeredSendDefinition = voiceTriggeredSendDefinition;
        this.subscriber = subscriber;
        this.message = message;
        this.number = number;
        this.transferMessage = transferMessage;
        this.transferNumber = transferNumber;
    }


    /**
     * Gets the voiceTriggeredSendDefinition value for this VoiceTriggeredSend.
     * 
     * @return voiceTriggeredSendDefinition
     */
    public com.exacttarget.wsdl.partnerAPI.VoiceTriggeredSendDefinition getVoiceTriggeredSendDefinition() {
        return voiceTriggeredSendDefinition;
    }


    /**
     * Sets the voiceTriggeredSendDefinition value for this VoiceTriggeredSend.
     * 
     * @param voiceTriggeredSendDefinition
     */
    public void setVoiceTriggeredSendDefinition(com.exacttarget.wsdl.partnerAPI.VoiceTriggeredSendDefinition voiceTriggeredSendDefinition) {
        this.voiceTriggeredSendDefinition = voiceTriggeredSendDefinition;
    }


    /**
     * Gets the subscriber value for this VoiceTriggeredSend.
     * 
     * @return subscriber
     */
    public com.exacttarget.wsdl.partnerAPI.Subscriber getSubscriber() {
        return subscriber;
    }


    /**
     * Sets the subscriber value for this VoiceTriggeredSend.
     * 
     * @param subscriber
     */
    public void setSubscriber(com.exacttarget.wsdl.partnerAPI.Subscriber subscriber) {
        this.subscriber = subscriber;
    }


    /**
     * Gets the message value for this VoiceTriggeredSend.
     * 
     * @return message
     */
    public java.lang.String getMessage() {
        return message;
    }


    /**
     * Sets the message value for this VoiceTriggeredSend.
     * 
     * @param message
     */
    public void setMessage(java.lang.String message) {
        this.message = message;
    }


    /**
     * Gets the number value for this VoiceTriggeredSend.
     * 
     * @return number
     */
    public java.lang.String getNumber() {
        return number;
    }


    /**
     * Sets the number value for this VoiceTriggeredSend.
     * 
     * @param number
     */
    public void setNumber(java.lang.String number) {
        this.number = number;
    }


    /**
     * Gets the transferMessage value for this VoiceTriggeredSend.
     * 
     * @return transferMessage
     */
    public java.lang.String getTransferMessage() {
        return transferMessage;
    }


    /**
     * Sets the transferMessage value for this VoiceTriggeredSend.
     * 
     * @param transferMessage
     */
    public void setTransferMessage(java.lang.String transferMessage) {
        this.transferMessage = transferMessage;
    }


    /**
     * Gets the transferNumber value for this VoiceTriggeredSend.
     * 
     * @return transferNumber
     */
    public java.lang.String getTransferNumber() {
        return transferNumber;
    }


    /**
     * Sets the transferNumber value for this VoiceTriggeredSend.
     * 
     * @param transferNumber
     */
    public void setTransferNumber(java.lang.String transferNumber) {
        this.transferNumber = transferNumber;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof VoiceTriggeredSend)) return false;
        VoiceTriggeredSend other = (VoiceTriggeredSend) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.voiceTriggeredSendDefinition==null && other.getVoiceTriggeredSendDefinition()==null) || 
             (this.voiceTriggeredSendDefinition!=null &&
              this.voiceTriggeredSendDefinition.equals(other.getVoiceTriggeredSendDefinition()))) &&
            ((this.subscriber==null && other.getSubscriber()==null) || 
             (this.subscriber!=null &&
              this.subscriber.equals(other.getSubscriber()))) &&
            ((this.message==null && other.getMessage()==null) || 
             (this.message!=null &&
              this.message.equals(other.getMessage()))) &&
            ((this.number==null && other.getNumber()==null) || 
             (this.number!=null &&
              this.number.equals(other.getNumber()))) &&
            ((this.transferMessage==null && other.getTransferMessage()==null) || 
             (this.transferMessage!=null &&
              this.transferMessage.equals(other.getTransferMessage()))) &&
            ((this.transferNumber==null && other.getTransferNumber()==null) || 
             (this.transferNumber!=null &&
              this.transferNumber.equals(other.getTransferNumber())));
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
        if (getVoiceTriggeredSendDefinition() != null) {
            _hashCode += getVoiceTriggeredSendDefinition().hashCode();
        }
        if (getSubscriber() != null) {
            _hashCode += getSubscriber().hashCode();
        }
        if (getMessage() != null) {
            _hashCode += getMessage().hashCode();
        }
        if (getNumber() != null) {
            _hashCode += getNumber().hashCode();
        }
        if (getTransferMessage() != null) {
            _hashCode += getTransferMessage().hashCode();
        }
        if (getTransferNumber() != null) {
            _hashCode += getTransferNumber().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(VoiceTriggeredSend.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "VoiceTriggeredSend"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("voiceTriggeredSendDefinition");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "VoiceTriggeredSendDefinition"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "VoiceTriggeredSendDefinition"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subscriber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Subscriber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Subscriber"));
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
        elemField.setFieldName("number");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Number"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transferMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TransferMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transferNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TransferNumber"));
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
