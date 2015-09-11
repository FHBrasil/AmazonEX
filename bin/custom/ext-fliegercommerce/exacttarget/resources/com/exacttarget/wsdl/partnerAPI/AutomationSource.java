/**
 * AutomationSource.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class AutomationSource  implements java.io.Serializable {
    private java.lang.String automationSourceID;

    private java.lang.String automationSourceType;

    public AutomationSource() {
    }

    public AutomationSource(
           java.lang.String automationSourceID,
           java.lang.String automationSourceType) {
           this.automationSourceID = automationSourceID;
           this.automationSourceType = automationSourceType;
    }


    /**
     * Gets the automationSourceID value for this AutomationSource.
     * 
     * @return automationSourceID
     */
    public java.lang.String getAutomationSourceID() {
        return automationSourceID;
    }


    /**
     * Sets the automationSourceID value for this AutomationSource.
     * 
     * @param automationSourceID
     */
    public void setAutomationSourceID(java.lang.String automationSourceID) {
        this.automationSourceID = automationSourceID;
    }


    /**
     * Gets the automationSourceType value for this AutomationSource.
     * 
     * @return automationSourceType
     */
    public java.lang.String getAutomationSourceType() {
        return automationSourceType;
    }


    /**
     * Sets the automationSourceType value for this AutomationSource.
     * 
     * @param automationSourceType
     */
    public void setAutomationSourceType(java.lang.String automationSourceType) {
        this.automationSourceType = automationSourceType;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AutomationSource)) return false;
        AutomationSource other = (AutomationSource) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.automationSourceID==null && other.getAutomationSourceID()==null) || 
             (this.automationSourceID!=null &&
              this.automationSourceID.equals(other.getAutomationSourceID()))) &&
            ((this.automationSourceType==null && other.getAutomationSourceType()==null) || 
             (this.automationSourceType!=null &&
              this.automationSourceType.equals(other.getAutomationSourceType())));
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
        if (getAutomationSourceID() != null) {
            _hashCode += getAutomationSourceID().hashCode();
        }
        if (getAutomationSourceType() != null) {
            _hashCode += getAutomationSourceType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AutomationSource.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationSource"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("automationSourceID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationSourceID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("automationSourceType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationSourceType"));
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
