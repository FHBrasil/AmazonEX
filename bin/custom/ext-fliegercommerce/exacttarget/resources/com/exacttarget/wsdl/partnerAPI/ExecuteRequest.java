/**
 * ExecuteRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class ExecuteRequest  implements java.io.Serializable {
    private com.exacttarget.wsdl.partnerAPI.ClientID client;

    private java.lang.String name;

    private com.exacttarget.wsdl.partnerAPI.APIProperty[] parameters;

    public ExecuteRequest() {
    }

    public ExecuteRequest(
           com.exacttarget.wsdl.partnerAPI.ClientID client,
           java.lang.String name,
           com.exacttarget.wsdl.partnerAPI.APIProperty[] parameters) {
           this.client = client;
           this.name = name;
           this.parameters = parameters;
    }


    /**
     * Gets the client value for this ExecuteRequest.
     * 
     * @return client
     */
    public com.exacttarget.wsdl.partnerAPI.ClientID getClient() {
        return client;
    }


    /**
     * Sets the client value for this ExecuteRequest.
     * 
     * @param client
     */
    public void setClient(com.exacttarget.wsdl.partnerAPI.ClientID client) {
        this.client = client;
    }


    /**
     * Gets the name value for this ExecuteRequest.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this ExecuteRequest.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the parameters value for this ExecuteRequest.
     * 
     * @return parameters
     */
    public com.exacttarget.wsdl.partnerAPI.APIProperty[] getParameters() {
        return parameters;
    }


    /**
     * Sets the parameters value for this ExecuteRequest.
     * 
     * @param parameters
     */
    public void setParameters(com.exacttarget.wsdl.partnerAPI.APIProperty[] parameters) {
        this.parameters = parameters;
    }

    public com.exacttarget.wsdl.partnerAPI.APIProperty getParameters(int i) {
        return this.parameters[i];
    }

    public void setParameters(int i, com.exacttarget.wsdl.partnerAPI.APIProperty _value) {
        this.parameters[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ExecuteRequest)) return false;
        ExecuteRequest other = (ExecuteRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.client==null && other.getClient()==null) || 
             (this.client!=null &&
              this.client.equals(other.getClient()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.parameters==null && other.getParameters()==null) || 
             (this.parameters!=null &&
              java.util.Arrays.equals(this.parameters, other.getParameters())));
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
        if (getClient() != null) {
            _hashCode += getClient().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getParameters() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getParameters());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getParameters(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ExecuteRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExecuteRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("client");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Client"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ClientID"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parameters");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Parameters"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "APIProperty"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
