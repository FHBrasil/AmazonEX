/**
 * PublicationSubscriber.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class PublicationSubscriber  extends com.exacttarget.wsdl.partnerAPI.APIObject  implements java.io.Serializable {
    private com.exacttarget.wsdl.partnerAPI.Publication publication;

    private com.exacttarget.wsdl.partnerAPI.Subscriber subscriber;

    public PublicationSubscriber() {
    }

    public PublicationSubscriber(
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
           com.exacttarget.wsdl.partnerAPI.Publication publication,
           com.exacttarget.wsdl.partnerAPI.Subscriber subscriber) {
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
        this.publication = publication;
        this.subscriber = subscriber;
    }


    /**
     * Gets the publication value for this PublicationSubscriber.
     * 
     * @return publication
     */
    public com.exacttarget.wsdl.partnerAPI.Publication getPublication() {
        return publication;
    }


    /**
     * Sets the publication value for this PublicationSubscriber.
     * 
     * @param publication
     */
    public void setPublication(com.exacttarget.wsdl.partnerAPI.Publication publication) {
        this.publication = publication;
    }


    /**
     * Gets the subscriber value for this PublicationSubscriber.
     * 
     * @return subscriber
     */
    public com.exacttarget.wsdl.partnerAPI.Subscriber getSubscriber() {
        return subscriber;
    }


    /**
     * Sets the subscriber value for this PublicationSubscriber.
     * 
     * @param subscriber
     */
    public void setSubscriber(com.exacttarget.wsdl.partnerAPI.Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PublicationSubscriber)) return false;
        PublicationSubscriber other = (PublicationSubscriber) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.publication==null && other.getPublication()==null) || 
             (this.publication!=null &&
              this.publication.equals(other.getPublication()))) &&
            ((this.subscriber==null && other.getSubscriber()==null) || 
             (this.subscriber!=null &&
              this.subscriber.equals(other.getSubscriber())));
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
        if (getPublication() != null) {
            _hashCode += getPublication().hashCode();
        }
        if (getSubscriber() != null) {
            _hashCode += getSubscriber().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PublicationSubscriber.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PublicationSubscriber"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("publication");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Publication"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Publication"));
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
