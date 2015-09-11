/**
 * DefinitionResponseMsg.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class DefinitionResponseMsg  implements java.io.Serializable {
    private com.exacttarget.wsdl.partnerAPI.ObjectDefinition[] objectDefinition;

    private java.lang.String requestID;

    public DefinitionResponseMsg() {
    }

    public DefinitionResponseMsg(
           com.exacttarget.wsdl.partnerAPI.ObjectDefinition[] objectDefinition,
           java.lang.String requestID) {
           this.objectDefinition = objectDefinition;
           this.requestID = requestID;
    }


    /**
     * Gets the objectDefinition value for this DefinitionResponseMsg.
     * 
     * @return objectDefinition
     */
    public com.exacttarget.wsdl.partnerAPI.ObjectDefinition[] getObjectDefinition() {
        return objectDefinition;
    }


    /**
     * Sets the objectDefinition value for this DefinitionResponseMsg.
     * 
     * @param objectDefinition
     */
    public void setObjectDefinition(com.exacttarget.wsdl.partnerAPI.ObjectDefinition[] objectDefinition) {
        this.objectDefinition = objectDefinition;
    }

    public com.exacttarget.wsdl.partnerAPI.ObjectDefinition getObjectDefinition(int i) {
        return this.objectDefinition[i];
    }

    public void setObjectDefinition(int i, com.exacttarget.wsdl.partnerAPI.ObjectDefinition _value) {
        this.objectDefinition[i] = _value;
    }


    /**
     * Gets the requestID value for this DefinitionResponseMsg.
     * 
     * @return requestID
     */
    public java.lang.String getRequestID() {
        return requestID;
    }


    /**
     * Sets the requestID value for this DefinitionResponseMsg.
     * 
     * @param requestID
     */
    public void setRequestID(java.lang.String requestID) {
        this.requestID = requestID;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DefinitionResponseMsg)) return false;
        DefinitionResponseMsg other = (DefinitionResponseMsg) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.objectDefinition==null && other.getObjectDefinition()==null) || 
             (this.objectDefinition!=null &&
              java.util.Arrays.equals(this.objectDefinition, other.getObjectDefinition()))) &&
            ((this.requestID==null && other.getRequestID()==null) || 
             (this.requestID!=null &&
              this.requestID.equals(other.getRequestID())));
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
        if (getObjectDefinition() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getObjectDefinition());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getObjectDefinition(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getRequestID() != null) {
            _hashCode += getRequestID().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DefinitionResponseMsg.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">DefinitionResponseMsg"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("objectDefinition");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ObjectDefinition"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ObjectDefinition"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RequestID"));
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
