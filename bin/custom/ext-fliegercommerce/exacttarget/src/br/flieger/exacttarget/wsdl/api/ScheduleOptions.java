/**
 * ScheduleOptions.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.flieger.exacttarget.wsdl.api;

public class ScheduleOptions  extends br.flieger.exacttarget.wsdl.api.Options  implements java.io.Serializable {
    public ScheduleOptions() {
    }

    public ScheduleOptions(
           br.flieger.exacttarget.wsdl.api.ClientID client,
           br.flieger.exacttarget.wsdl.api.AsyncResponse[] sendResponseTo,
           br.flieger.exacttarget.wsdl.api.SaveOption[] saveOptions,
           java.lang.Byte priority,
           java.lang.String conversationID,
           java.lang.Integer sequenceCode,
           java.lang.Integer callsInConversation,
           java.util.Calendar scheduledTime,
           br.flieger.exacttarget.wsdl.api.RequestType requestType,
           br.flieger.exacttarget.wsdl.api.Priority queuePriority) {
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
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ScheduleOptions)) return false;
        ScheduleOptions other = (ScheduleOptions) obj;
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
        new org.apache.axis.description.TypeDesc(ScheduleOptions.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ScheduleOptions"));
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
