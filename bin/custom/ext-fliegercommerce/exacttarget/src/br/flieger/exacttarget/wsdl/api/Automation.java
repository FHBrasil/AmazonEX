/**
 * Automation.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.flieger.exacttarget.wsdl.api;

public class Automation  extends br.flieger.exacttarget.wsdl.api.InteractionDefinition  implements java.io.Serializable {
    private br.flieger.exacttarget.wsdl.api.ScheduleDefinition schedule;

    private br.flieger.exacttarget.wsdl.api.AutomationTask[] automationTasks;

    private java.lang.Boolean isActive;

    private br.flieger.exacttarget.wsdl.api.AutomationSource automationSource;

    private java.lang.Integer status;

    private br.flieger.exacttarget.wsdl.api.AutomationNotification[] notifications;

    private java.util.Calendar scheduledTime;

    private java.lang.String automationType;

    public Automation() {
    }

    public Automation(
           br.flieger.exacttarget.wsdl.api.ClientID client,
           java.lang.String partnerKey,
           br.flieger.exacttarget.wsdl.api.APIProperty[] partnerProperties,
           java.util.Calendar createdDate,
           java.util.Calendar modifiedDate,
           java.lang.Integer ID,
           java.lang.String objectID,
           java.lang.String customerKey,
           br.flieger.exacttarget.wsdl.api.Owner owner,
           java.lang.String correlationID,
           java.lang.String objectState,
           java.lang.String name,
           java.lang.String description,
           java.lang.String keyword,
           java.lang.String interactionObjectID,
           br.flieger.exacttarget.wsdl.api.ScheduleDefinition schedule,
           br.flieger.exacttarget.wsdl.api.AutomationTask[] automationTasks,
           java.lang.Boolean isActive,
           br.flieger.exacttarget.wsdl.api.AutomationSource automationSource,
           java.lang.Integer status,
           br.flieger.exacttarget.wsdl.api.AutomationNotification[] notifications,
           java.util.Calendar scheduledTime,
           java.lang.String automationType) {
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
            interactionObjectID);
        this.schedule = schedule;
        this.automationTasks = automationTasks;
        this.isActive = isActive;
        this.automationSource = automationSource;
        this.status = status;
        this.notifications = notifications;
        this.scheduledTime = scheduledTime;
        this.automationType = automationType;
    }


    /**
     * Gets the schedule value for this Automation.
     * 
     * @return schedule
     */
    public br.flieger.exacttarget.wsdl.api.ScheduleDefinition getSchedule() {
        return schedule;
    }


    /**
     * Sets the schedule value for this Automation.
     * 
     * @param schedule
     */
    public void setSchedule(br.flieger.exacttarget.wsdl.api.ScheduleDefinition schedule) {
        this.schedule = schedule;
    }


    /**
     * Gets the automationTasks value for this Automation.
     * 
     * @return automationTasks
     */
    public br.flieger.exacttarget.wsdl.api.AutomationTask[] getAutomationTasks() {
        return automationTasks;
    }


    /**
     * Sets the automationTasks value for this Automation.
     * 
     * @param automationTasks
     */
    public void setAutomationTasks(br.flieger.exacttarget.wsdl.api.AutomationTask[] automationTasks) {
        this.automationTasks = automationTasks;
    }


    /**
     * Gets the isActive value for this Automation.
     * 
     * @return isActive
     */
    public java.lang.Boolean getIsActive() {
        return isActive;
    }


    /**
     * Sets the isActive value for this Automation.
     * 
     * @param isActive
     */
    public void setIsActive(java.lang.Boolean isActive) {
        this.isActive = isActive;
    }


    /**
     * Gets the automationSource value for this Automation.
     * 
     * @return automationSource
     */
    public br.flieger.exacttarget.wsdl.api.AutomationSource getAutomationSource() {
        return automationSource;
    }


    /**
     * Sets the automationSource value for this Automation.
     * 
     * @param automationSource
     */
    public void setAutomationSource(br.flieger.exacttarget.wsdl.api.AutomationSource automationSource) {
        this.automationSource = automationSource;
    }


    /**
     * Gets the status value for this Automation.
     * 
     * @return status
     */
    public java.lang.Integer getStatus() {
        return status;
    }


    /**
     * Sets the status value for this Automation.
     * 
     * @param status
     */
    public void setStatus(java.lang.Integer status) {
        this.status = status;
    }


    /**
     * Gets the notifications value for this Automation.
     * 
     * @return notifications
     */
    public br.flieger.exacttarget.wsdl.api.AutomationNotification[] getNotifications() {
        return notifications;
    }


    /**
     * Sets the notifications value for this Automation.
     * 
     * @param notifications
     */
    public void setNotifications(br.flieger.exacttarget.wsdl.api.AutomationNotification[] notifications) {
        this.notifications = notifications;
    }


    /**
     * Gets the scheduledTime value for this Automation.
     * 
     * @return scheduledTime
     */
    public java.util.Calendar getScheduledTime() {
        return scheduledTime;
    }


    /**
     * Sets the scheduledTime value for this Automation.
     * 
     * @param scheduledTime
     */
    public void setScheduledTime(java.util.Calendar scheduledTime) {
        this.scheduledTime = scheduledTime;
    }


    /**
     * Gets the automationType value for this Automation.
     * 
     * @return automationType
     */
    public java.lang.String getAutomationType() {
        return automationType;
    }


    /**
     * Sets the automationType value for this Automation.
     * 
     * @param automationType
     */
    public void setAutomationType(java.lang.String automationType) {
        this.automationType = automationType;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Automation)) return false;
        Automation other = (Automation) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.schedule==null && other.getSchedule()==null) || 
             (this.schedule!=null &&
              this.schedule.equals(other.getSchedule()))) &&
            ((this.automationTasks==null && other.getAutomationTasks()==null) || 
             (this.automationTasks!=null &&
              java.util.Arrays.equals(this.automationTasks, other.getAutomationTasks()))) &&
            ((this.isActive==null && other.getIsActive()==null) || 
             (this.isActive!=null &&
              this.isActive.equals(other.getIsActive()))) &&
            ((this.automationSource==null && other.getAutomationSource()==null) || 
             (this.automationSource!=null &&
              this.automationSource.equals(other.getAutomationSource()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.notifications==null && other.getNotifications()==null) || 
             (this.notifications!=null &&
              java.util.Arrays.equals(this.notifications, other.getNotifications()))) &&
            ((this.scheduledTime==null && other.getScheduledTime()==null) || 
             (this.scheduledTime!=null &&
              this.scheduledTime.equals(other.getScheduledTime()))) &&
            ((this.automationType==null && other.getAutomationType()==null) || 
             (this.automationType!=null &&
              this.automationType.equals(other.getAutomationType())));
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
        if (getSchedule() != null) {
            _hashCode += getSchedule().hashCode();
        }
        if (getAutomationTasks() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAutomationTasks());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAutomationTasks(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getIsActive() != null) {
            _hashCode += getIsActive().hashCode();
        }
        if (getAutomationSource() != null) {
            _hashCode += getAutomationSource().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getNotifications() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getNotifications());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getNotifications(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getScheduledTime() != null) {
            _hashCode += getScheduledTime().hashCode();
        }
        if (getAutomationType() != null) {
            _hashCode += getAutomationType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Automation.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Automation"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("schedule");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Schedule"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ScheduleDefinition"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("automationTasks");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationTasks"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationTask"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationTask"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isActive");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsActive"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("automationSource");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationSource"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationSource"));
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
        elemField.setFieldName("notifications");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Notifications"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationNotification"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Notification"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("scheduledTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ScheduledTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("automationType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomationType"));
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
