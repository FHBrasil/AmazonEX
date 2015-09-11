/**
 * UnsubEvent.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class UnsubEvent  extends com.exacttarget.wsdl.partnerAPI.TrackingEvent  implements java.io.Serializable {
    private com.exacttarget.wsdl.partnerAPI.List list;

    private java.lang.Boolean isMasterUnsubscribed;

    public UnsubEvent() {
    }

    public UnsubEvent(
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
           com.exacttarget.wsdl.partnerAPI.List list,
           java.lang.Boolean isMasterUnsubscribed) {
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
        this.list = list;
        this.isMasterUnsubscribed = isMasterUnsubscribed;
    }


    /**
     * Gets the list value for this UnsubEvent.
     * 
     * @return list
     */
    public com.exacttarget.wsdl.partnerAPI.List getList() {
        return list;
    }


    /**
     * Sets the list value for this UnsubEvent.
     * 
     * @param list
     */
    public void setList(com.exacttarget.wsdl.partnerAPI.List list) {
        this.list = list;
    }


    /**
     * Gets the isMasterUnsubscribed value for this UnsubEvent.
     * 
     * @return isMasterUnsubscribed
     */
    public java.lang.Boolean getIsMasterUnsubscribed() {
        return isMasterUnsubscribed;
    }


    /**
     * Sets the isMasterUnsubscribed value for this UnsubEvent.
     * 
     * @param isMasterUnsubscribed
     */
    public void setIsMasterUnsubscribed(java.lang.Boolean isMasterUnsubscribed) {
        this.isMasterUnsubscribed = isMasterUnsubscribed;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UnsubEvent)) return false;
        UnsubEvent other = (UnsubEvent) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.list==null && other.getList()==null) || 
             (this.list!=null &&
              this.list.equals(other.getList()))) &&
            ((this.isMasterUnsubscribed==null && other.getIsMasterUnsubscribed()==null) || 
             (this.isMasterUnsubscribed!=null &&
              this.isMasterUnsubscribed.equals(other.getIsMasterUnsubscribed())));
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
        if (getList() != null) {
            _hashCode += getList().hashCode();
        }
        if (getIsMasterUnsubscribed() != null) {
            _hashCode += getIsMasterUnsubscribed().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UnsubEvent.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "UnsubEvent"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("list");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "List"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "List"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isMasterUnsubscribed");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsMasterUnsubscribed"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
