/**
 * CreateOptions.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class CreateOptions  extends com.exacttarget.wsdl.partnerAPI.Options  implements java.io.Serializable {
    private com.exacttarget.wsdl.partnerAPI.ContainerID container;

    public CreateOptions() {
    }

    public CreateOptions(
           com.exacttarget.wsdl.partnerAPI.ClientID client,
           com.exacttarget.wsdl.partnerAPI.AsyncResponse[] sendResponseTo,
           com.exacttarget.wsdl.partnerAPI.SaveOption[] saveOptions,
           java.lang.Byte priority,
           java.lang.String conversationID,
           java.lang.Integer sequenceCode,
           java.lang.Integer callsInConversation,
           java.util.Calendar scheduledTime,
           com.exacttarget.wsdl.partnerAPI.RequestType requestType,
           com.exacttarget.wsdl.partnerAPI.Priority queuePriority,
           com.exacttarget.wsdl.partnerAPI.ContainerID container) {
        super(
            client,
            sendResponseTo,
            saveOptions,
            priority,
            conversationID,
            sequenceCode,
            callsInConversation,
            scheduledTime,
            requestType,
            queuePriority);
        this.container = container;
    }


    /**
     * Gets the container value for this CreateOptions.
     * 
     * @return container
     */
    public com.exacttarget.wsdl.partnerAPI.ContainerID getContainer() {
        return container;
    }


    /**
     * Sets the container value for this CreateOptions.
     * 
     * @param container
     */
    public void setContainer(com.exacttarget.wsdl.partnerAPI.ContainerID container) {
        this.container = container;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CreateOptions)) return false;
        CreateOptions other = (CreateOptions) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.container==null && other.getContainer()==null) || 
             (this.container!=null &&
              this.container.equals(other.getContainer())));
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
        if (getContainer() != null) {
            _hashCode += getContainer().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CreateOptions.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "CreateOptions"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("container");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Container"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ContainerID"));
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
