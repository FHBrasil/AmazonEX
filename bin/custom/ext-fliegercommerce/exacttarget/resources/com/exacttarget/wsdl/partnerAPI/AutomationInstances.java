/**
 * AutomationInstances.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class AutomationInstances  extends com.exacttarget.wsdl.partnerAPI.APIObject  implements java.io.Serializable {
    private java.lang.Integer instanceCount;

    private com.exacttarget.wsdl.partnerAPI.AutomationInstance[] automationInstanceCollection;

    public AutomationInstances() {
    }

    public AutomationInstances(
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
           java.lang.Integer instanceCount,
           com.exacttarget.wsdl.partnerAPI.AutomationInstance[] automationInstanceCollection) {
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
            objectState);
        this.instanceCount = instanceCount;
        this.automationInstanceCollection = automationInstanceCollection;
    }


    /**
     * Gets the instanceCount value for this AutomationInstances.
     * 
     * @return instanceCount
     */
    public java.lang.Integer getInstanceCount() {
        return instanceCount;
    }


    /**
     * Sets the instanceCount value for this AutomationInstances.
     * 
     * @param instanceCount
     */
    public void setInstanceCount(java.lang.Integer instanceCount) {
        this.instanceCount = instanceCount;
    }


    /**
     * Gets the automationInstanceCollection value for this AutomationInstances.
     * 
     * @return automationInstanceCollection
     */
    public com.exacttarget.wsdl.partnerAPI.AutomationInstance[] getAutomationInstanceCollection() {
        return automationInstanceCollection;
    }


    /**
     * Sets the automationInstanceCollection value for this AutomationInstances.
     * 
     * @param automationInstanceCollection
     */
    public void setAutomationInstanceCollection(com.exacttarget.wsdl.partnerAPI.AutomationInstance[] automationInstanceCollection) {
        this.automationInstanceCollection = automationInstanceCollection;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AutomationInstances)) return false;
        AutomationInstances other = (AutomationInstances) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.instanceCount==null && other.getInstanceCount()==null) || 
             (this.instanceCount!=null &&
              this.instanceCount.equals(other.getInstanceCount()))) &&
            ((this.automationInstanceCollection==null && other.getAutomationInstanceCollection()==null) || 
             (this.automationInstanceCollection!=null &&
              java.util.Arrays.equals(this.automationInstanceCollection, other.getAutomationInstanceCollection())));
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
        if (getInstanceCount() != null) {
            _hashCode += getInstanceCount().hashCode();
        }
        if (getAutomationInstanceCollection() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAutomationInstanceCollection());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAutomationInstanceCollection(), i);
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
        new org.apache.axis.description.TypeDesc(AutomationInstances.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationInstances"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("instanceCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "InstanceCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("automationInstanceCollection");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationInstanceCollection"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationInstance"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationInstance"));
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
