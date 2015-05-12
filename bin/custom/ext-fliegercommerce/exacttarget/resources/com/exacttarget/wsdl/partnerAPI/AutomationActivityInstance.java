/**
 * AutomationActivityInstance.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class AutomationActivityInstance  extends com.exacttarget.wsdl.partnerAPI.AutomationActivity  implements java.io.Serializable {
    private java.lang.String activityID;

    private java.lang.String automationID;

    private java.lang.Integer sequenceID;

    private java.lang.Integer status;

    private java.util.Calendar statusLastUpdate;

    private java.lang.String statusMessage;

    private com.exacttarget.wsdl.partnerAPI.AutomationActivity activityDefinition;

    private com.exacttarget.wsdl.partnerAPI.AutomationInstance automationInstance;

    private com.exacttarget.wsdl.partnerAPI.AutomationTaskInstance automationTaskInstance;

    private java.util.Calendar scheduledTime;

    private java.util.Calendar startTime;

    private java.util.Calendar completedTime;

    public AutomationActivityInstance() {
    }

    public AutomationActivityInstance(
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
           com.exacttarget.wsdl.partnerAPI.APIObject activityObject,
           java.lang.String activityID,
           java.lang.String automationID,
           java.lang.Integer sequenceID,
           java.lang.Integer status,
           java.util.Calendar statusLastUpdate,
           java.lang.String statusMessage,
           com.exacttarget.wsdl.partnerAPI.AutomationActivity activityDefinition,
           com.exacttarget.wsdl.partnerAPI.AutomationInstance automationInstance,
           com.exacttarget.wsdl.partnerAPI.AutomationTaskInstance automationTaskInstance,
           java.util.Calendar scheduledTime,
           java.util.Calendar startTime,
           java.util.Calendar completedTime) {
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
            name,
            description,
            isActive,
            definition,
            automation,
            automationTask,
            sequence,
            activityObject);
        this.activityID = activityID;
        this.automationID = automationID;
        this.sequenceID = sequenceID;
        this.status = status;
        this.statusLastUpdate = statusLastUpdate;
        this.statusMessage = statusMessage;
        this.activityDefinition = activityDefinition;
        this.automationInstance = automationInstance;
        this.automationTaskInstance = automationTaskInstance;
        this.scheduledTime = scheduledTime;
        this.startTime = startTime;
        this.completedTime = completedTime;
    }


    /**
     * Gets the activityID value for this AutomationActivityInstance.
     * 
     * @return activityID
     */
    public java.lang.String getActivityID() {
        return activityID;
    }


    /**
     * Sets the activityID value for this AutomationActivityInstance.
     * 
     * @param activityID
     */
    public void setActivityID(java.lang.String activityID) {
        this.activityID = activityID;
    }


    /**
     * Gets the automationID value for this AutomationActivityInstance.
     * 
     * @return automationID
     */
    public java.lang.String getAutomationID() {
        return automationID;
    }


    /**
     * Sets the automationID value for this AutomationActivityInstance.
     * 
     * @param automationID
     */
    public void setAutomationID(java.lang.String automationID) {
        this.automationID = automationID;
    }


    /**
     * Gets the sequenceID value for this AutomationActivityInstance.
     * 
     * @return sequenceID
     */
    public java.lang.Integer getSequenceID() {
        return sequenceID;
    }


    /**
     * Sets the sequenceID value for this AutomationActivityInstance.
     * 
     * @param sequenceID
     */
    public void setSequenceID(java.lang.Integer sequenceID) {
        this.sequenceID = sequenceID;
    }


    /**
     * Gets the status value for this AutomationActivityInstance.
     * 
     * @return status
     */
    public java.lang.Integer getStatus() {
        return status;
    }


    /**
     * Sets the status value for this AutomationActivityInstance.
     * 
     * @param status
     */
    public void setStatus(java.lang.Integer status) {
        this.status = status;
    }


    /**
     * Gets the statusLastUpdate value for this AutomationActivityInstance.
     * 
     * @return statusLastUpdate
     */
    public java.util.Calendar getStatusLastUpdate() {
        return statusLastUpdate;
    }


    /**
     * Sets the statusLastUpdate value for this AutomationActivityInstance.
     * 
     * @param statusLastUpdate
     */
    public void setStatusLastUpdate(java.util.Calendar statusLastUpdate) {
        this.statusLastUpdate = statusLastUpdate;
    }


    /**
     * Gets the statusMessage value for this AutomationActivityInstance.
     * 
     * @return statusMessage
     */
    public java.lang.String getStatusMessage() {
        return statusMessage;
    }


    /**
     * Sets the statusMessage value for this AutomationActivityInstance.
     * 
     * @param statusMessage
     */
    public void setStatusMessage(java.lang.String statusMessage) {
        this.statusMessage = statusMessage;
    }


    /**
     * Gets the activityDefinition value for this AutomationActivityInstance.
     * 
     * @return activityDefinition
     */
    public com.exacttarget.wsdl.partnerAPI.AutomationActivity getActivityDefinition() {
        return activityDefinition;
    }


    /**
     * Sets the activityDefinition value for this AutomationActivityInstance.
     * 
     * @param activityDefinition
     */
    public void setActivityDefinition(com.exacttarget.wsdl.partnerAPI.AutomationActivity activityDefinition) {
        this.activityDefinition = activityDefinition;
    }


    /**
     * Gets the automationInstance value for this AutomationActivityInstance.
     * 
     * @return automationInstance
     */
    public com.exacttarget.wsdl.partnerAPI.AutomationInstance getAutomationInstance() {
        return automationInstance;
    }


    /**
     * Sets the automationInstance value for this AutomationActivityInstance.
     * 
     * @param automationInstance
     */
    public void setAutomationInstance(com.exacttarget.wsdl.partnerAPI.AutomationInstance automationInstance) {
        this.automationInstance = automationInstance;
    }


    /**
     * Gets the automationTaskInstance value for this AutomationActivityInstance.
     * 
     * @return automationTaskInstance
     */
    public com.exacttarget.wsdl.partnerAPI.AutomationTaskInstance getAutomationTaskInstance() {
        return automationTaskInstance;
    }


    /**
     * Sets the automationTaskInstance value for this AutomationActivityInstance.
     * 
     * @param automationTaskInstance
     */
    public void setAutomationTaskInstance(com.exacttarget.wsdl.partnerAPI.AutomationTaskInstance automationTaskInstance) {
        this.automationTaskInstance = automationTaskInstance;
    }


    /**
     * Gets the scheduledTime value for this AutomationActivityInstance.
     * 
     * @return scheduledTime
     */
    public java.util.Calendar getScheduledTime() {
        return scheduledTime;
    }


    /**
     * Sets the scheduledTime value for this AutomationActivityInstance.
     * 
     * @param scheduledTime
     */
    public void setScheduledTime(java.util.Calendar scheduledTime) {
        this.scheduledTime = scheduledTime;
    }


    /**
     * Gets the startTime value for this AutomationActivityInstance.
     * 
     * @return startTime
     */
    public java.util.Calendar getStartTime() {
        return startTime;
    }


    /**
     * Sets the startTime value for this AutomationActivityInstance.
     * 
     * @param startTime
     */
    public void setStartTime(java.util.Calendar startTime) {
        this.startTime = startTime;
    }


    /**
     * Gets the completedTime value for this AutomationActivityInstance.
     * 
     * @return completedTime
     */
    public java.util.Calendar getCompletedTime() {
        return completedTime;
    }


    /**
     * Sets the completedTime value for this AutomationActivityInstance.
     * 
     * @param completedTime
     */
    public void setCompletedTime(java.util.Calendar completedTime) {
        this.completedTime = completedTime;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AutomationActivityInstance)) return false;
        AutomationActivityInstance other = (AutomationActivityInstance) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.activityID==null && other.getActivityID()==null) || 
             (this.activityID!=null &&
              this.activityID.equals(other.getActivityID()))) &&
            ((this.automationID==null && other.getAutomationID()==null) || 
             (this.automationID!=null &&
              this.automationID.equals(other.getAutomationID()))) &&
            ((this.sequenceID==null && other.getSequenceID()==null) || 
             (this.sequenceID!=null &&
              this.sequenceID.equals(other.getSequenceID()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.statusLastUpdate==null && other.getStatusLastUpdate()==null) || 
             (this.statusLastUpdate!=null &&
              this.statusLastUpdate.equals(other.getStatusLastUpdate()))) &&
            ((this.statusMessage==null && other.getStatusMessage()==null) || 
             (this.statusMessage!=null &&
              this.statusMessage.equals(other.getStatusMessage()))) &&
            ((this.activityDefinition==null && other.getActivityDefinition()==null) || 
             (this.activityDefinition!=null &&
              this.activityDefinition.equals(other.getActivityDefinition()))) &&
            ((this.automationInstance==null && other.getAutomationInstance()==null) || 
             (this.automationInstance!=null &&
              this.automationInstance.equals(other.getAutomationInstance()))) &&
            ((this.automationTaskInstance==null && other.getAutomationTaskInstance()==null) || 
             (this.automationTaskInstance!=null &&
              this.automationTaskInstance.equals(other.getAutomationTaskInstance()))) &&
            ((this.scheduledTime==null && other.getScheduledTime()==null) || 
             (this.scheduledTime!=null &&
              this.scheduledTime.equals(other.getScheduledTime()))) &&
            ((this.startTime==null && other.getStartTime()==null) || 
             (this.startTime!=null &&
              this.startTime.equals(other.getStartTime()))) &&
            ((this.completedTime==null && other.getCompletedTime()==null) || 
             (this.completedTime!=null &&
              this.completedTime.equals(other.getCompletedTime())));
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
        if (getActivityID() != null) {
            _hashCode += getActivityID().hashCode();
        }
        if (getAutomationID() != null) {
            _hashCode += getAutomationID().hashCode();
        }
        if (getSequenceID() != null) {
            _hashCode += getSequenceID().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getStatusLastUpdate() != null) {
            _hashCode += getStatusLastUpdate().hashCode();
        }
        if (getStatusMessage() != null) {
            _hashCode += getStatusMessage().hashCode();
        }
        if (getActivityDefinition() != null) {
            _hashCode += getActivityDefinition().hashCode();
        }
        if (getAutomationInstance() != null) {
            _hashCode += getAutomationInstance().hashCode();
        }
        if (getAutomationTaskInstance() != null) {
            _hashCode += getAutomationTaskInstance().hashCode();
        }
        if (getScheduledTime() != null) {
            _hashCode += getScheduledTime().hashCode();
        }
        if (getStartTime() != null) {
            _hashCode += getStartTime().hashCode();
        }
        if (getCompletedTime() != null) {
            _hashCode += getCompletedTime().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AutomationActivityInstance.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationActivityInstance"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activityID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ActivityID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("automationID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sequenceID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SequenceID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statusLastUpdate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "StatusLastUpdate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statusMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "StatusMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activityDefinition");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ActivityDefinition"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationActivity"));
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
        elemField.setFieldName("automationTaskInstance");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationTaskInstance"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationTaskInstance"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("scheduledTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ScheduledTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("startTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "StartTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("completedTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "CompletedTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
