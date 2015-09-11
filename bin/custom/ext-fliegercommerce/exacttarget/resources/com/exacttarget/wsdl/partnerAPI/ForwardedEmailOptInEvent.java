/**
 * ForwardedEmailOptInEvent.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class ForwardedEmailOptInEvent  extends com.exacttarget.wsdl.partnerAPI.TrackingEvent  implements java.io.Serializable {
    private java.lang.String optInSubscriberKey;

    public ForwardedEmailOptInEvent() {
    }

    public ForwardedEmailOptInEvent(
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
           java.lang.Integer batchID,
           java.lang.String optInSubscriberKey) {
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
            sendID,
            subscriberKey,
            eventDate,
            eventType,
            triggeredSendDefinitionObjectID,
            batchID);
        this.optInSubscriberKey = optInSubscriberKey;
    }


    /**
     * Gets the optInSubscriberKey value for this ForwardedEmailOptInEvent.
     * 
     * @return optInSubscriberKey
     */
    public java.lang.String getOptInSubscriberKey() {
        return optInSubscriberKey;
    }


    /**
     * Sets the optInSubscriberKey value for this ForwardedEmailOptInEvent.
     * 
     * @param optInSubscriberKey
     */
    public void setOptInSubscriberKey(java.lang.String optInSubscriberKey) {
        this.optInSubscriberKey = optInSubscriberKey;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ForwardedEmailOptInEvent)) return false;
        ForwardedEmailOptInEvent other = (ForwardedEmailOptInEvent) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.optInSubscriberKey==null && other.getOptInSubscriberKey()==null) || 
             (this.optInSubscriberKey!=null &&
              this.optInSubscriberKey.equals(other.getOptInSubscriberKey())));
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
        if (getOptInSubscriberKey() != null) {
            _hashCode += getOptInSubscriberKey().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ForwardedEmailOptInEvent.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ForwardedEmailOptInEvent"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("optInSubscriberKey");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "OptInSubscriberKey"));
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
