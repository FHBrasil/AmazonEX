/**
 * AutomationInstance.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class AutomationInstance  extends com.exacttarget.wsdl.partnerAPI.Automation  implements java.io.Serializable {
    private java.lang.String automationID;

    private java.lang.String statusMessage;

    private java.util.Calendar statusLastUpdate;

    private com.exacttarget.wsdl.partnerAPI.AutomationTaskInstance[] taskInstances;

    private java.util.Calendar startTime;

    private java.util.Calendar completedTime;

    public AutomationInstance() {
    }

    public AutomationInstance(
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
           java.lang.String keyword,
           java.lang.String interactionObjectID,
           com.exacttarget.wsdl.partnerAPI.ScheduleDefinition schedule,
           com.exacttarget.wsdl.partnerAPI.AutomationTask[] automationTasks,
           java.lang.Boolean isActive,
           com.exacttarget.wsdl.partnerAPI.AutomationSource automationSource,
           java.lang.Integer status,
           com.exacttarget.wsdl.partnerAPI.AutomationNotification[] notifications,
           java.util.Calendar scheduledTime,
           java.lang.String automationType,
           java.lang.String automationID,
           java.lang.String statusMessage,
           java.util.Calendar statusLastUpdate,
           com.exacttarget.wsdl.partnerAPI.AutomationTaskInstance[] taskInstances,
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
            keyword,
            interactionObjectID,
            schedule,
            automationTasks,
            isActive,
            automationSource,
            status,
            notifications,
            scheduledTime,
            automationType);
        this.automationID = automationID;
        this.statusMessage = statusMessage;
        this.statusLastUpdate = statusLastUpdate;
        this.taskInstances = taskInstances;
        this.startTime = startTime;
        this.completedTime = completedTime;
    }


    /**
     * Gets the automationID value for this AutomationInstance.
     * 
     * @return automationID
     */
    public java.lang.String getAutomationID() {
        return automationID;
    }


    /**
     * Sets the automationID value for this AutomationInstance.
     * 
     * @param automationID
     */
    public void setAutomationID(java.lang.String automationID) {
        this.automationID = automationID;
    }


    /**
     * Gets the statusMessage value for this AutomationInstance.
     * 
     * @return statusMessage
     */
    public java.lang.String getStatusMessage() {
        return statusMessage;
    }


    /**
     * Sets the statusMessage value for this AutomationInstance.
     * 
     * @param statusMessage
     */
    public void setStatusMessage(java.lang.String statusMessage) {
        this.statusMessage = statusMessage;
    }


    /**
     * Gets the statusLastUpdate value for this AutomationInstance.
     * 
     * @return statusLastUpdate
     */
    public java.util.Calendar getStatusLastUpdate() {
        return statusLastUpdate;
    }


    /**
     * Sets the statusLastUpdate value for this AutomationInstance.
     * 
     * @param statusLastUpdate
     */
    public void setStatusLastUpdate(java.util.Calendar statusLastUpdate) {
        this.statusLastUpdate = statusLastUpdate;
    }


    /**
     * Gets the taskInstances value for this AutomationInstance.
     * 
     * @return taskInstances
     */
    public com.exacttarget.wsdl.partnerAPI.AutomationTaskInstance[] getTaskInstances() {
        return taskInstances;
    }


    /**
     * Sets the taskInstances value for this AutomationInstance.
     * 
     * @param taskInstances
     */
    public void setTaskInstances(com.exacttarget.wsdl.partnerAPI.AutomationTaskInstance[] taskInstances) {
        this.taskInstances = taskInstances;
    }


    /**
     * Gets the startTime value for this AutomationInstance.
     * 
     * @return startTime
     */
    public java.util.Calendar getStartTime() {
        return startTime;
    }


    /**
     * Sets the startTime value for this AutomationInstance.
     * 
     * @param startTime
     */
    public void setStartTime(java.util.Calendar startTime) {
        this.startTime = startTime;
    }


    /**
     * Gets the completedTime value for this AutomationInstance.
     * 
     * @return completedTime
     */
    public java.util.Calendar getCompletedTime() {
        return completedTime;
    }


    /**
     * Sets the completedTime value for this AutomationInstance.
     * 
     * @param completedTime
     */
    public void setCompletedTime(java.util.Calendar completedTime) {
        this.completedTime = completedTime;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AutomationInstance)) return false;
        AutomationInstance other = (AutomationInstance) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.automationID==null && other.getAutomationID()==null) || 
             (this.automationID!=null &&
              this.automationID.equals(other.getAutomationID()))) &&
            ((this.statusMessage==null && other.getStatusMessage()==null) || 
             (this.statusMessage!=null &&
              this.statusMessage.equals(other.getStatusMessage()))) &&
            ((this.statusLastUpdate==null && other.getStatusLastUpdate()==null) || 
             (this.statusLastUpdate!=null &&
              this.statusLastUpdate.equals(other.getStatusLastUpdate()))) &&
            ((this.taskInstances==null && other.getTaskInstances()==null) || 
             (this.taskInstances!=null &&
              java.util.Arrays.equals(this.taskInstances, other.getTaskInstances()))) &&
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
        if (getAutomationID() != null) {
            _hashCode += getAutomationID().hashCode();
        }
        if (getStatusMessage() != null) {
            _hashCode += getStatusMessage().hashCode();
        }
        if (getStatusLastUpdate() != null) {
            _hashCode += getStatusLastUpdate().hashCode();
        }
        if (getTaskInstances() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTaskInstances());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTaskInstances(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
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
        new org.apache.axis.description.TypeDesc(AutomationInstance.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationInstance"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("automationID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setFieldName("statusLastUpdate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "StatusLastUpdate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taskInstances");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TaskInstances"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationTaskInstance"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationTaskInstance"));
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
