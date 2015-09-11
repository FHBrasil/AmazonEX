/**
 * PerformOptions.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class PerformOptions  extends com.exacttarget.wsdl.partnerAPI.Options  implements java.io.Serializable {
    private java.lang.String explanation;

    public PerformOptions() {
    }

    public PerformOptions(
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
           java.lang.String explanation) {
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
        this.explanation = explanation;
    }


    /**
     * Gets the explanation value for this PerformOptions.
     * 
     * @return explanation
     */
    public java.lang.String getExplanation() {
        return explanation;
    }


    /**
     * Sets the explanation value for this PerformOptions.
     * 
     * @param explanation
     */
    public void setExplanation(java.lang.String explanation) {
        this.explanation = explanation;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PerformOptions)) return false;
        PerformOptions other = (PerformOptions) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.explanation==null && other.getExplanation()==null) || 
             (this.explanation!=null &&
              this.explanation.equals(other.getExplanation())));
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
        if (getExplanation() != null) {
            _hashCode += getExplanation().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PerformOptions.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PerformOptions"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("explanation");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Explanation"));
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
