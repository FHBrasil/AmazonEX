/**
 * AutomationTaskInstance.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class AutomationTaskInstance  extends com.exacttarget.wsdl.partnerAPI.AutomationTask  implements java.io.Serializable {
    private com.exacttarget.wsdl.partnerAPI.AutomationTask stepDefinition;

    private com.exacttarget.wsdl.partnerAPI.AutomationInstance automationInstance;

    private com.exacttarget.wsdl.partnerAPI.AutomationActivityInstance[] activityInstances;

    public AutomationTaskInstance() {
    }

    public AutomationTaskInstance(
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
           java.lang.String automationTaskType,
           java.lang.String name,
           java.lang.String description,
           com.exacttarget.wsdl.partnerAPI.Automation automation,
           java.lang.Integer sequence,
           com.exacttarget.wsdl.partnerAPI.AutomationActivity[] activities,
           com.exacttarget.wsdl.partnerAPI.AutomationTask stepDefinition,
           com.exacttarget.wsdl.partnerAPI.AutomationInstance automationInstance,
           com.exacttarget.wsdl.partnerAPI.AutomationActivityInstance[] activityInstances) {
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
            automationTaskType,
            name,
            description,
            automation,
            sequence,
            activities);
        this.stepDefinition = stepDefinition;
        this.automationInstance = automationInstance;
        this.activityInstances = activityInstances;
    }


    /**
     * Gets the stepDefinition value for this AutomationTaskInstance.
     * 
     * @return stepDefinition
     */
    public com.exacttarget.wsdl.partnerAPI.AutomationTask getStepDefinition() {
        return stepDefinition;
    }


    /**
     * Sets the stepDefinition value for this AutomationTaskInstance.
     * 
     * @param stepDefinition
     */
    public void setStepDefinition(com.exacttarget.wsdl.partnerAPI.AutomationTask stepDefinition) {
        this.stepDefinition = stepDefinition;
    }


    /**
     * Gets the automationInstance value for this AutomationTaskInstance.
     * 
     * @return automationInstance
     */
    public com.exacttarget.wsdl.partnerAPI.AutomationInstance getAutomationInstance() {
        return automationInstance;
    }


    /**
     * Sets the automationInstance value for this AutomationTaskInstance.
     * 
     * @param automationInstance
     */
    public void setAutomationInstance(com.exacttarget.wsdl.partnerAPI.AutomationInstance automationInstance) {
        this.automationInstance = automationInstance;
    }


    /**
     * Gets the activityInstances value for this AutomationTaskInstance.
     * 
     * @return activityInstances
     */
    public com.exacttarget.wsdl.partnerAPI.AutomationActivityInstance[] getActivityInstances() {
        return activityInstances;
    }


    /**
     * Sets the activityInstances value for this AutomationTaskInstance.
     * 
     * @param activityInstances
     */
    public void setActivityInstances(com.exacttarget.wsdl.partnerAPI.AutomationActivityInstance[] activityInstances) {
        this.activityInstances = activityInstances;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AutomationTaskInstance)) return false;
        AutomationTaskInstance other = (AutomationTaskInstance) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.stepDefinition==null && other.getStepDefinition()==null) || 
             (this.stepDefinition!=null &&
              this.stepDefinition.equals(other.getStepDefinition()))) &&
            ((this.automationInstance==null && other.getAutomationInstance()==null) || 
             (this.automationInstance!=null &&
              this.automationInstance.equals(other.getAutomationInstance()))) &&
            ((this.activityInstances==null && other.getActivityInstances()==null) || 
             (this.activityInstances!=null &&
              java.util.Arrays.equals(this.activityInstances, other.getActivityInstances())));
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
        if (getStepDefinition() != null) {
            _hashCode += getStepDefinition().hashCode();
        }
        if (getAutomationInstance() != null) {
            _hashCode += getAutomationInstance().hashCode();
        }
        if (getActivityInstances() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getActivityInstances());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getActivityInstances(), i);
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
        new org.apache.axis.description.TypeDesc(AutomationTaskInstance.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationTaskInstance"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stepDefinition");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "StepDefinition"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationTask"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("automationInstance");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationInstance"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationInstance"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activityInstances");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ActivityInstances"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationActivityInstance"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ActivityInstance"));
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
