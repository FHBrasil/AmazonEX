/**
 * ExtractRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class ExtractRequest  extends com.exacttarget.wsdl.partnerAPI.Request  implements java.io.Serializable {
    private com.exacttarget.wsdl.partnerAPI.ClientID client;

    private java.lang.String ID;

    private com.exacttarget.wsdl.partnerAPI.ExtractOptions options;

    private com.exacttarget.wsdl.partnerAPI.ExtractParameter[] parameters;

    private com.exacttarget.wsdl.partnerAPI.ExtractDescription description;

    private com.exacttarget.wsdl.partnerAPI.ExtractDefinition definition;

    public ExtractRequest() {
    }

    public ExtractRequest(
           com.exacttarget.wsdl.partnerAPI.ClientID client,
           java.lang.String ID,
           com.exacttarget.wsdl.partnerAPI.ExtractOptions options,
           com.exacttarget.wsdl.partnerAPI.ExtractParameter[] parameters,
           com.exacttarget.wsdl.partnerAPI.ExtractDescription description,
           com.exacttarget.wsdl.partnerAPI.ExtractDefinition definition) {
        this.client = client;
        this.ID = ID;
        this.options = options;
        this.parameters = parameters;
        this.description = description;
        this.definition = definition;
    }


    /**
     * Gets the client value for this ExtractRequest.
     * 
     * @return client
     */
    public com.exacttarget.wsdl.partnerAPI.ClientID getClient() {
        return client;
    }


    /**
     * Sets the client value for this ExtractRequest.
     * 
     * @param client
     */
    public void setClient(com.exacttarget.wsdl.partnerAPI.ClientID client) {
        this.client = client;
    }


    /**
     * Gets the ID value for this ExtractRequest.
     * 
     * @return ID
     */
    public java.lang.String getID() {
        return ID;
    }


    /**
     * Sets the ID value for this ExtractRequest.
     * 
     * @param ID
     */
    public void setID(java.lang.String ID) {
        this.ID = ID;
    }


    /**
     * Gets the options value for this ExtractRequest.
     * 
     * @return options
     */
    public com.exacttarget.wsdl.partnerAPI.ExtractOptions getOptions() {
        return options;
    }


    /**
     * Sets the options value for this ExtractRequest.
     * 
     * @param options
     */
    public void setOptions(com.exacttarget.wsdl.partnerAPI.ExtractOptions options) {
        this.options = options;
    }


    /**
     * Gets the parameters value for this ExtractRequest.
     * 
     * @return parameters
     */
    public com.exacttarget.wsdl.partnerAPI.ExtractParameter[] getParameters() {
        return parameters;
    }


    /**
     * Sets the parameters value for this ExtractRequest.
     * 
     * @param parameters
     */
    public void setParameters(com.exacttarget.wsdl.partnerAPI.ExtractParameter[] parameters) {
        this.parameters = parameters;
    }


    /**
     * Gets the description value for this ExtractRequest.
     * 
     * @return description
     */
    public com.exacttarget.wsdl.partnerAPI.ExtractDescription getDescription() {
        return description;
    }


    /**
     * Sets the description value for this ExtractRequest.
     * 
     * @param description
     */
    public void setDescription(com.exacttarget.wsdl.partnerAPI.ExtractDescription description) {
        this.description = description;
    }


    /**
     * Gets the definition value for this ExtractRequest.
     * 
     * @return definition
     */
    public com.exacttarget.wsdl.partnerAPI.ExtractDefinition getDefinition() {
        return definition;
    }


    /**
     * Sets the definition value for this ExtractRequest.
     * 
     * @param definition
     */
    public void setDefinition(com.exacttarget.wsdl.partnerAPI.ExtractDefinition definition) {
        this.definition = definition;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ExtractRequest)) return false;
        ExtractRequest other = (ExtractRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.client==null && other.getClient()==null) || 
             (this.client!=null &&
              this.client.equals(other.getClient()))) &&
            ((this.ID==null && other.getID()==null) || 
             (this.ID!=null &&
              this.ID.equals(other.getID()))) &&
            ((this.options==null && other.getOptions()==null) || 
             (this.options!=null &&
              this.options.equals(other.getOptions()))) &&
            ((this.parameters==null && other.getParameters()==null) || 
             (this.parameters!=null &&
              java.util.Arrays.equals(this.parameters, other.getParameters()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.definition==null && other.getDefinition()==null) || 
             (this.definition!=null &&
              this.definition.equals(other.getDefinition())));
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
        if (getClient() != null) {
            _hashCode += getClient().hashCode();
        }
        if (getID() != null) {
            _hashCode += getID().hashCode();
        }
        if (getOptions() != null) {
            _hashCode += getOptions().hashCode();
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
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getDefinition() != null) {
            _hashCode += getDefinition().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ExtractRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExtractRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("client");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Client"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ClientID"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("options");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Options"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExtractOptions"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parameters");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Parameters"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExtractParameter"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Parameter"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExtractDescription"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("definition");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Definition"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExtractDefinition"));
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
