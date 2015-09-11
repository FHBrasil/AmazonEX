/**
 * TrackingEvent.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class TrackingEvent  extends com.exacttarget.wsdl.partnerAPI.APIObject  implements java.io.Serializable {
    private java.lang.Integer sendID;

    private java.lang.String subscriberKey;

    private java.util.Calendar eventDate;

    private com.exacttarget.wsdl.partnerAPI.EventType eventType;

    private java.lang.String triggeredSendDefinitionObjectID;

    private java.lang.Integer batchID;

    public TrackingEvent() {
    }

    public TrackingEvent(
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
           java.lang.Integer sendID,
           java.lang.String subscriberKey,
           java.util.Calendar eventDate,
           com.exacttarget.wsdl.partnerAPI.EventType eventType,
           java.lang.String triggeredSendDefinitionObjectID,
           java.lang.Integer batchID) {
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
        this.sendID = sendID;
        this.subscriberKey = subscriberKey;
        this.eventDate = eventDate;
        this.eventType = eventType;
        this.triggeredSendDefinitionObjectID = triggeredSendDefinitionObjectID;
        this.batchID = batchID;
    }


    /**
     * Gets the sendID value for this TrackingEvent.
     * 
     * @return sendID
     */
    public java.lang.Integer getSendID() {
        return sendID;
    }


    /**
     * Sets the sendID value for this TrackingEvent.
     * 
     * @param sendID
     */
    public void setSendID(java.lang.Integer sendID) {
        this.sendID = sendID;
    }


    /**
     * Gets the subscriberKey value for this TrackingEvent.
     * 
     * @return subscriberKey
     */
    public java.lang.String getSubscriberKey() {
        return subscriberKey;
    }


    /**
     * Sets the subscriberKey value for this TrackingEvent.
     * 
     * @param subscriberKey
     */
    public void setSubscriberKey(java.lang.String subscriberKey) {
        this.subscriberKey = subscriberKey;
    }


    /**
     * Gets the eventDate value for this TrackingEvent.
     * 
     * @return eventDate
     */
    public java.util.Calendar getEventDate() {
        return eventDate;
    }


    /**
     * Sets the eventDate value for this TrackingEvent.
     * 
     * @param eventDate
     */
    public void setEventDate(java.util.Calendar eventDate) {
        this.eventDate = eventDate;
    }


    /**
     * Gets the eventType value for this TrackingEvent.
     * 
     * @return eventType
     */
    public com.exacttarget.wsdl.partnerAPI.EventType getEventType() {
        return eventType;
    }


    /**
     * Sets the eventType value for this TrackingEvent.
     * 
     * @param eventType
     */
    public void setEventType(com.exacttarget.wsdl.partnerAPI.EventType eventType) {
        this.eventType = eventType;
    }


    /**
     * Gets the triggeredSendDefinitionObjectID value for this TrackingEvent.
     * 
     * @return triggeredSendDefinitionObjectID
     */
    public java.lang.String getTriggeredSendDefinitionObjectID() {
        return triggeredSendDefinitionObjectID;
    }


    /**
     * Sets the triggeredSendDefinitionObjectID value for this TrackingEvent.
     * 
     * @param triggeredSendDefinitionObjectID
     */
    public void setTriggeredSendDefinitionObjectID(java.lang.String triggeredSendDefinitionObjectID) {
        this.triggeredSendDefinitionObjectID = triggeredSendDefinitionObjectID;
    }


    /**
     * Gets the batchID value for this TrackingEvent.
     * 
     * @return batchID
     */
    public java.lang.Integer getBatchID() {
        return batchID;
    }


    /**
     * Sets the batchID value for this TrackingEvent.
     * 
     * @param batchID
     */
    public void setBatchID(java.lang.Integer batchID) {
        this.batchID = batchID;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TrackingEvent)) return false;
        TrackingEvent other = (TrackingEvent) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.sendID==null && other.getSendID()==null) || 
             (this.sendID!=null &&
              this.sendID.equals(other.getSendID()))) &&
            ((this.subscriberKey==null && other.getSubscriberKey()==null) || 
             (this.subscriberKey!=null &&
              this.subscriberKey.equals(other.getSubscriberKey()))) &&
            ((this.eventDate==null && other.getEventDate()==null) || 
             (this.eventDate!=null &&
              this.eventDate.equals(other.getEventDate()))) &&
            ((this.eventType==null && other.getEventType()==null) || 
             (this.eventType!=null &&
              this.eventType.equals(other.getEventType()))) &&
            ((this.triggeredSendDefinitionObjectID==null && other.getTriggeredSendDefinitionObjectID()==null) || 
             (this.triggeredSendDefinitionObjectID!=null &&
              this.triggeredSendDefinitionObjectID.equals(other.getTriggeredSendDefinitionObjectID()))) &&
            ((this.batchID==null && other.getBatchID()==null) || 
             (this.batchID!=null &&
              this.batchID.equals(other.getBatchID())));
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
        if (getSendID() != null) {
            _hashCode += getSendID().hashCode();
        }
        if (getSubscriberKey() != null) {
            _hashCode += getSubscriberKey().hashCode();
        }
        if (getEventDate() != null) {
            _hashCode += getEventDate().hashCode();
        }
        if (getEventType() != null) {
            _hashCode += getEventType().hashCode();
        }
        if (getTriggeredSendDefinitionObjectID() != null) {
            _hashCode += getTriggeredSendDefinitionObjectID().hashCode();
        }
        if (getBatchID() != null) {
            _hashCode += getBatchID().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TrackingEvent.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TrackingEvent"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sendID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subscriberKey");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SubscriberKey"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("eventDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "EventDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("eventType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "EventType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "EventType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("triggeredSendDefinitionObjectID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TriggeredSendDefinitionObjectID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("batchID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "BatchID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
