/**
 * VoiceTriggeredSendDefinition.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.flieger.exacttarget.wsdl.api;

public class VoiceTriggeredSendDefinition  extends br.flieger.exacttarget.wsdl.api.SendDefinition  implements java.io.Serializable {
    public VoiceTriggeredSendDefinition() {
    }

    public VoiceTriggeredSendDefinition(
           br.flieger.exacttarget.wsdl.api.ClientID client,
           java.lang.String partnerKey,
           br.flieger.exacttarget.wsdl.api.APIProperty[] partnerProperties,
           java.util.Calendar createdDate,
           java.util.Calendar modifiedDate,
           java.lang.Integer ID,
           java.lang.String objectID,
           java.lang.String customerKey,
           br.flieger.exacttarget.wsdl.api.Owner owner,
           java.lang.String correlationID,
           java.lang.String objectState,
           java.lang.String name,
           java.lang.String description,
           java.lang.String keyword,
           java.lang.String interactionObjectID,
           java.lang.Integer categoryID,
           br.flieger.exacttarget.wsdl.api.SendClassification sendClassification,
           br.flieger.exacttarget.wsdl.api.SenderProfile senderProfile,
           java.lang.String fromName,
           java.lang.String fromAddress,
           br.flieger.exacttarget.wsdl.api.DeliveryProfile deliveryProfile,
           br.flieger.exacttarget.wsdl.api.DeliveryProfileSourceAddressTypeEnum sourceAddressType,
           br.flieger.exacttarget.wsdl.api.PrivateIP privateIP,
           br.flieger.exacttarget.wsdl.api.DeliveryProfileDomainTypeEnum domainType,
           br.flieger.exacttarget.wsdl.api.PrivateDomain privateDomain,
           br.flieger.exacttarget.wsdl.api.SalutationSourceEnum headerSalutationSource,
           br.flieger.exacttarget.wsdl.api.ContentArea headerContentArea,
           br.flieger.exacttarget.wsdl.api.SalutationSourceEnum footerSalutationSource,
           br.flieger.exacttarget.wsdl.api.ContentArea footerContentArea,
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
