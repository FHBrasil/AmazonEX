/**
 * AutomationActivity.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class AutomationActivity  extends com.exacttarget.wsdl.partnerAPI.APIObject  implements java.io.Serializable {
    private java.lang.String name;

    private java.lang.String description;

    private java.lang.Boolean isActive;

    private com.exacttarget.wsdl.partnerAPI.APIObject definition;

    private com.exacttarget.wsdl.partnerAPI.Automation automation;

    private com.exacttarget.wsdl.partnerAPI.AutomationTask automationTask;

    private java.lang.Integer sequence;

    private com.exacttarget.wsdl.partnerAPI.APIObject activityObject;

    public AutomationActivity() {
    }

    public AutomationActivity(
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
           java.lang.String name,
           java.lang.String description,
           java.lang.Boolean isActive,
           com.exacttarget.wsdl.partnerAPI.APIObject definition,
           com.exacttarget.wsdl.partnerAPI.Automation automation,
           com.exacttarget.wsdl.partnerAPI.AutomationTask automationTask,
           java.lang.Integer sequence,
           com.exacttarget.wsdl.partnerAPI.APIObject activityObject) {
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
        this.name = name;
        this.description = description;
        this.isActive = isActive;
        this.definition = definition;
        this.automation = automation;
        this.automationTask = automationTask;
        this.sequence = sequence;
        this.activityObject = activityObject;
    }


    /**
     * Gets the name value for this AutomationActivity.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this AutomationActivity.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the description value for this AutomationActivity.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this AutomationActivity.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the isActive value for this AutomationActivity.
     * 
     * @return isActive
     */
    public java.lang.Boolean getIsActive() {
        return isActive;
    }


    /**
     * Sets the isActive value for this AutomationActivity.
     * 
     * @param isActive
     */
    public void setIsActive(java.lang.Boolean isActive) {
        this.isActive = isActive;
    }


    /**
     * Gets the definition value for this AutomationActivity.
     * 
     * @return definition
     */
    public com.exacttarget.wsdl.partnerAPI.APIObject getDefinition() {
        return definition;
    }


    /**
     * Sets the definition value for this AutomationActivity.
     * 
     * @param definition
     */
    public void setDefinition(com.exacttarget.wsdl.partnerAPI.APIObject definition) {
        this.definition = definition;
    }


    /**
     * Gets the automation value for this AutomationActivity.
     * 
     * @return automation
     */
    public com.exacttarget.wsdl.partnerAPI.Automation getAutomation() {
        return automation;
    }


    /**
     * Sets the automation value for this AutomationActivity.
     * 
     * @param automation
     */
    public void setAutomation(com.exacttarget.wsdl.partnerAPI.Automation automation) {
        this.automation = automation;
    }


    /**
     * Gets the automationTask value for this AutomationActivity.
     * 
     * @return automationTask
     */
    public com.exacttarget.wsdl.partnerAPI.AutomationTask getAutomationTask() {
        return automationTask;
    }


    /**
     * Sets the automationTask value for this AutomationActivity.
     * 
     * @param automationTask
     */
    public void setAutomationTask(com.exacttarget.wsdl.partnerAPI.AutomationTask automationTask) {
        this.automationTask = automationTask;
    }


    /**
     * Gets the sequence value for this AutomationActivity.
     * 
     * @return sequence
     */
    public java.lang.Integer getSequence() {
        return sequence;
    }


    /**
     * Sets the sequence value for this AutomationActivity.
     * 
     * @param sequence
     */
    public void setSequence(java.lang.Integer sequence) {
        this.sequence = sequence;
    }


    /**
     * Gets the activityObject value for this AutomationActivity.
     * 
     * @return activityObject
     */
    public com.exacttarget.wsdl.partnerAPI.APIObject getActivityObject() {
        return activityObject;
    }


    /**
     * Sets the activityObject value for this AutomationActivity.
     * 
     * @param activityObject
     */
    public void setActivityObject(com.exacttarget.wsdl.partnerAPI.APIObject activityObject) {
        this.activityObject = activityObject;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AutomationActivity)) return false;
        AutomationActivity other = (AutomationActivity) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.isActive==null && other.getIsActive()==null) || 
             (this.isActive!=null &&
              this.isActive.equals(other.getIsActive()))) &&
            ((this.definition==null && other.getDefinition()==null) || 
             (this.definition!=null &&
              this.definition.equals(other.getDefinition()))) &&
            ((this.automation==null && other.getAutomation()==null) || 
             (this.automation!=null &&
              this.automation.equals(other.getAutomation()))) &&
            ((this.automationTask==null && other.getAutomationTask()==null) || 
             (this.automationTask!=null &&
              this.automationTask.equals(other.getAutomationTask()))) &&
            ((this.sequence==null && other.getSequence()==null) || 
             (this.sequence!=null &&
              this.sequence.equals(other.getSequence()))) &&
            ((this.activityObject==null && other.getActivityObject()==null) || 
             (this.activityObject!=null &&
              this.activityObject.equals(other.getActivityObject())));
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
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getIsActive() != null) {
            _hashCode += getIsActive().hashCode();
        }
        if (getDefinition() != null) {
            _hashCode += getDefinition().hashCode();
        }
        if (getAutomation() != null) {
            _hashCode += getAutomation().hashCode();
        }
        if (getAutomationTask() != null) {
            _hashCode += getAutomationTask().hashCode();
        }
        if (getSequence() != null) {
            _hashCode += getSequence().hashCode();
        }
        if (getActivityObject() != null) {
            _hashCode += getActivityObject().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AutomationActivity.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationActivity"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isActive");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsActive"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("definition");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Definition"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "APIObject"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("automation");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Automation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Automation"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("automationTask");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationTask"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationTask"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sequence");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Sequence"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activityObject");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ActivityObject"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "APIObject"));
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
