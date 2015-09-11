/**
 * ConfigureRequestMsg.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class ConfigureRequestMsg  implements java.io.Serializable {
    private com.exacttarget.wsdl.partnerAPI.ConfigureOptions options;

    private java.lang.String action;

    private com.exacttarget.wsdl.partnerAPI.APIObject[] configurations;

    public ConfigureRequestMsg() {
    }

    public ConfigureRequestMsg(
           com.exacttarget.wsdl.partnerAPI.ConfigureOptions options,
           java.lang.String action,
           com.exacttarget.wsdl.partnerAPI.APIObject[] configurations) {
           this.options = options;
           this.action = action;
           this.configurations = configurations;
    }


    /**
     * Gets the options value for this ConfigureRequestMsg.
     * 
     * @return options
     */
    public com.exacttarget.wsdl.partnerAPI.ConfigureOptions getOptions() {
        return options;
    }


    /**
     * Sets the options value for this ConfigureRequestMsg.
     * 
     * @param options
     */
    public void setOptions(com.exacttarget.wsdl.partnerAPI.ConfigureOptions options) {
        this.options = options;
    }


    /**
     * Gets the action value for this ConfigureRequestMsg.
     * 
     * @return action
     */
    public java.lang.String getAction() {
        return action;
    }


    /**
     * Sets the action value for this ConfigureRequestMsg.
     * 
     * @param action
     */
    public void setAction(java.lang.String action) {
        this.action = action;
    }


    /**
     * Gets the configurations value for this ConfigureRequestMsg.
     * 
     * @return configurations
     */
    public com.exacttarget.wsdl.partnerAPI.APIObject[] getConfigurations() {
        return configurations;
    }


    /**
     * Sets the configurations value for this ConfigureRequestMsg.
     * 
     * @param configurations
     */
    public void setConfigurations(com.exacttarget.wsdl.partnerAPI.APIObject[] configurations) {
        this.configurations = configurations;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ConfigureRequestMsg)) return false;
        ConfigureRequestMsg other = (ConfigureRequestMsg) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.options==null && other.getOptions()==null) || 
             (this.options!=null &&
              this.options.equals(other.getOptions()))) &&
            ((this.action==null && other.getAction()==null) || 
             (this.action!=null &&
              this.action.equals(other.getAction()))) &&
            ((this.configurations==null && other.getConfigurations()==null) || 
             (this.configurations!=null &&
              java.util.Arrays.equals(this.configurations, other.getConfigurations())));
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
        if (getOptions() != null) {
            _hashCode += getOptions().hashCode();
        }
        if (getAction() != null) {
            _hashCode += getAction().hashCode();
        }
        if (getConfigurations() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getConfigurations());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getConfigurations(), i);
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
        new org.apache.axis.description.TypeDesc(ConfigureRequestMsg.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ConfigureRequestMsg"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("options");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Options"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ConfigureOptions"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("action");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Action"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("configurations");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Configurations"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "APIObject"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Configuration"));
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
