/**
 * Options.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public abstract class Options  implements java.io.Serializable {
    private com.exacttarget.wsdl.partnerAPI.ClientID client;

    private com.exacttarget.wsdl.partnerAPI.AsyncResponse[] sendResponseTo;

    private com.exacttarget.wsdl.partnerAPI.SaveOption[] saveOptions;

    private java.lang.Byte priority;

    private java.lang.String conversationID;

    private java.lang.Integer sequenceCode;

    private java.lang.Integer callsInConversation;

    private java.util.Calendar scheduledTime;

    private com.exacttarget.wsdl.partnerAPI.RequestType requestType;

    private com.exacttarget.wsdl.partnerAPI.Priority queuePriority;

    public Options() {
    }

    public Options(
           com.exacttarget.wsdl.partnerAPI.ClientID client,
           com.exacttarget.wsdl.partnerAPI.AsyncResponse[] sendResponseTo,
           com.exacttarget.wsdl.partnerAPI.SaveOption[] saveOptions,
           java.lang.Byte priority,
           java.lang.String conversationID,
           java.lang.Integer sequenceCode,
           java.lang.Integer callsInConversation,
           java.util.Calendar scheduledTime,
           com.exacttarget.wsdl.partnerAPI.RequestType requestType,
           com.exacttarget.wsdl.partnerAPI.Priority queuePriority) {
           this.client = client;
           this.sendResponseTo = sendResponseTo;
           this.saveOptions = saveOptions;
           this.priority = priority;
           this.conversationID = conversationID;
           this.sequenceCode = sequenceCode;
           this.callsInConversation = callsInConversation;
           this.scheduledTime = scheduledTime;
           this.requestType = requestType;
           this.queuePriority = queuePriority;
    }


    /**
     * Gets the client value for this Options.
     * 
     * @return client
     */
    public com.exacttarget.wsdl.partnerAPI.ClientID getClient() {
        return client;
    }


    /**
     * Sets the client value for this Options.
     * 
     * @param client
     */
    public void setClient(com.exacttarget.wsdl.partnerAPI.ClientID client) {
        this.client = client;
    }


    /**
     * Gets the sendResponseTo value for this Options.
     * 
     * @return sendResponseTo
     */
    public com.exacttarget.wsdl.partnerAPI.AsyncResponse[] getSendResponseTo() {
        return sendResponseTo;
    }


    /**
     * Sets the sendResponseTo value for this Options.
     * 
     * @param sendResponseTo
     */
    public void setSendResponseTo(com.exacttarget.wsdl.partnerAPI.AsyncResponse[] sendResponseTo) {
        this.sendResponseTo = sendResponseTo;
    }

    public com.exacttarget.wsdl.partnerAPI.AsyncResponse getSendResponseTo(int i) {
        return this.sendResponseTo[i];
    }

    public void setSendResponseTo(int i, com.exacttarget.wsdl.partnerAPI.AsyncResponse _value) {
        this.sendResponseTo[i] = _value;
    }


    /**
     * Gets the saveOptions value for this Options.
     * 
     * @return saveOptions
     */
    public com.exacttarget.wsdl.partnerAPI.SaveOption[] getSaveOptions() {
        return saveOptions;
    }


    /**
     * Sets the saveOptions value for this Options.
     * 
     * @param saveOptions
     */
    public void setSaveOptions(com.exacttarget.wsdl.partnerAPI.SaveOption[] saveOptions) {
        this.saveOptions = saveOptions;
    }


    /**
     * Gets the priority value for this Options.
     * 
     * @return priority
     */
    public java.lang.Byte getPriority() {
        return priority;
    }


    /**
     * Sets the priority value for this Options.
     * 
     * @param priority
     */
    public void setPriority(java.lang.Byte priority) {
        this.priority = priority;
    }


    /**
     * Gets the conversationID value for this Options.
     * 
     * @return conversationID
     */
    public java.lang.String getConversationID() {
        return conversationID;
    }


    /**
     * Sets the conversationID value for this Options.
     * 
     * @param conversationID
     */
    public void setConversationID(java.lang.String conversationID) {
        this.conversationID = conversationID;
    }


    /**
     * Gets the sequenceCode value for this Options.
     * 
     * @return sequenceCode
     */
    public java.lang.Integer getSequenceCode() {
        return sequenceCode;
    }


    /**
     * Sets the sequenceCode value for this Options.
     * 
     * @param sequenceCode
     */
    public void setSequenceCode(java.lang.Integer sequenceCode) {
        this.sequenceCode = sequenceCode;
    }


    /**
     * Gets the callsInConversation value for this Options.
     * 
     * @return callsInConversation
     */
    public java.lang.Integer getCallsInConversation() {
        return callsInConversation;
    }


    /**
     * Sets the callsInConversation value for this Options.
     * 
     * @param callsInConversation
     */
    public void setCallsInConversation(java.lang.Integer callsInConversation) {
        this.callsInConversation = callsInConversation;
    }


    /**
     * Gets the scheduledTime value for this Options.
     * 
     * @return scheduledTime
     */
    public java.util.Calendar getScheduledTime() {
        return scheduledTime;
    }


    /**
     * Sets the scheduledTime value for this Options.
     * 
     * @param scheduledTime
     */
    public void setScheduledTime(java.util.Calendar scheduledTime) {
        this.scheduledTime = scheduledTime;
    }


    /**
     * Gets the requestType value for this Options.
     * 
     * @return requestType
     */
    public com.exacttarget.wsdl.partnerAPI.RequestType getRequestType() {
        return requestType;
    }


    /**
     * Sets the requestType value for this Options.
     * 
     * @param requestType
     */
    public void setRequestType(com.exacttarget.wsdl.partnerAPI.RequestType requestType) {
        this.requestType = requestType;
    }


    /**
     * Gets the queuePriority value for this Options.
     * 
     * @return queuePriority
     */
    public com.exacttarget.wsdl.partnerAPI.Priority getQueuePriority() {
        return queuePriority;
    }


    /**
     * Sets the queuePriority value for this Options.
     * 
     * @param queuePriority
     */
    public void setQueuePriority(com.exacttarget.wsdl.partnerAPI.Priority queuePriority) {
        this.queuePriority = queuePriority;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Options)) return false;
        Options other = (Options) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.client==null && other.getClient()==null) || 
             (this.client!=null &&
              this.client.equals(other.getClient()))) &&
            ((this.sendResponseTo==null && other.getSendResponseTo()==null) || 
             (this.sendResponseTo!=null &&
              java.util.Arrays.equals(this.sendResponseTo, other.getSendResponseTo()))) &&
            ((this.saveOptions==null && other.getSaveOptions()==null) || 
             (this.saveOptions!=null &&
              java.util.Arrays.equals(this.saveOptions, other.getSaveOptions()))) &&
            ((this.priority==null && other.getPriority()==null) || 
             (this.priority!=null &&
              this.priority.equals(other.getPriority()))) &&
            ((this.conversationID==null && other.getConversationID()==null) || 
             (this.conversationID!=null &&
              this.conversationID.equals(other.getConversationID()))) &&
            ((this.sequenceCode==null && other.getSequenceCode()==null) || 
             (this.sequenceCode!=null &&
              this.sequenceCode.equals(other.getSequenceCode()))) &&
            ((this.callsInConversation==null && other.getCallsInConversation()==null) || 
             (this.callsInConversation!=null &&
              this.callsInConversation.equals(other.getCallsInConversation()))) &&
            ((this.scheduledTime==null && other.getScheduledTime()==null) || 
             (this.scheduledTime!=null &&
              this.scheduledTime.equals(other.getScheduledTime()))) &&
            ((this.requestType==null && other.getRequestType()==null) || 
             (this.requestType!=null &&
              this.requestType.equals(other.getRequestType()))) &&
            ((this.queuePriority==null && other.getQueuePriority()==null) || 
             (this.queuePriority!=null &&
              this.queuePriority.equals(other.getQueuePriority())));
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
        if (getClient() != null) {
            _hashCode += getClient().hashCode();
        }
        if (getSendResponseTo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSendResponseTo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSendResponseTo(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSaveOptions() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSaveOptions());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSaveOptions(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPriority() != null) {
            _hashCode += getPriority().hashCode();
        }
        if (getConversationID() != null) {
            _hashCode += getConversationID().hashCode();
        }
        if (getSequenceCode() != null) {
            _hashCode += getSequenceCode().hashCode();
        }
        if (getCallsInConversation() != null) {
            _hashCode += getCallsInConversation().hashCode();
        }
        if (getScheduledTime() != null) {
            _hashCode += getScheduledTime().hashCode();
        }
        if (getRequestType() != null) {
            _hashCode += getRequestType().hashCode();
        }
        if (getQueuePriority() != null) {
            _hashCode += getQueuePriority().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Options.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Options"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("client");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Client"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ClientID"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sendResponseTo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendResponseTo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AsyncResponse"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("saveOptions");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SaveOptions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SaveOption"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SaveOption"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("priority");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Priority"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "byte"));
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
        elemField.setFieldName("sequenceCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SequenceCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("callsInConversation");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "CallsInConversation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("scheduledTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ScheduledTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
        elemField.setFieldName("queuePriority");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "QueuePriority"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Priority"));
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
