/**
 * VoiceTriggeredSendDefinition.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class VoiceTriggeredSendDefinition  extends com.exacttarget.wsdl.partnerAPI.SendDefinition  implements java.io.Serializable {
    public VoiceTriggeredSendDefinition() {
    }

    public VoiceTriggeredSendDefinition(
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
           java.lang.String name,
           java.lang.String description,
           java.lang.String keyword,
           java.lang.String interactionObjectID,
           java.lang.Integer categoryID,
           com.exacttarget.wsdl.partnerAPI.SendClassification sendClassification,
           com.exacttarget.wsdl.partnerAPI.SenderProfile senderProfile,
           java.lang.String fromName,
           java.lang.String fromAddress,
           com.exacttarget.wsdl.partnerAPI.DeliveryProfile deliveryProfile,
           com.exacttarget.wsdl.partnerAPI.DeliveryProfileSourceAddressTypeEnum sourceAddressType,
           com.exacttarget.wsdl.partnerAPI.PrivateIP privateIP,
           com.exacttarget.wsdl.partnerAPI.DeliveryProfileDomainTypeEnum domainType,
           com.exacttarget.wsdl.partnerAPI.PrivateDomain privateDomain,
           com.exacttarget.wsdl.partnerAPI.SalutationSourceEnum headerSalutationSource,
           com.exacttarget.wsdl.partnerAPI.ContentArea headerContentArea,
           com.exacttarget.wsdl.partnerAPI.SalutationSourceEnum footerSalutationSource,
           com.exacttarget.wsdl.partnerAPI.ContentArea footerContentArea,
           java.lang.Boolean suppressTracking,
           java.lang.Boolean isSendLogging) {
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
            name,
            description,
            keyword,
            interactionObjectID,
            categoryID,
            sendClassification,
            senderProfile,
            fromName,
            fromAddress,
            deliveryProfile,
            sourceAddressType,
            privateIP,
            domainType,
            privateDomain,
            headerSalutationSource,
            headerContentArea,
            footerSalutationSource,
            footerContentArea,
            suppressTracking,
            isSendLogging);
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof VoiceTriggeredSendDefinition)) return false;
        VoiceTriggeredSendDefinition other = (VoiceTriggeredSendDefinition) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj);
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(VoiceTriggeredSendDefinition.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "VoiceTriggeredSendDefinition"));
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
