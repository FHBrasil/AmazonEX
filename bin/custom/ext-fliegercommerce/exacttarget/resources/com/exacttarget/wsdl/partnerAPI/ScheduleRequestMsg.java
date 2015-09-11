/**
 * ScheduleRequestMsg.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class ScheduleRequestMsg  implements java.io.Serializable {
    private com.exacttarget.wsdl.partnerAPI.ScheduleOptions options;

    private java.lang.String action;

    private com.exacttarget.wsdl.partnerAPI.ScheduleDefinition schedule;

    private com.exacttarget.wsdl.partnerAPI.APIObject[] interactions;

    public ScheduleRequestMsg() {
    }

    public ScheduleRequestMsg(
           com.exacttarget.wsdl.partnerAPI.ScheduleOptions options,
           java.lang.String action,
           com.exacttarget.wsdl.partnerAPI.ScheduleDefinition schedule,
           com.exacttarget.wsdl.partnerAPI.APIObject[] interactions) {
           this.options = options;
           this.action = action;
           this.schedule = schedule;
           this.interactions = interactions;
    }


    /**
     * Gets the options value for this ScheduleRequestMsg.
     * 
     * @return options
     */
    public com.exacttarget.wsdl.partnerAPI.ScheduleOptions getOptions() {
        return options;
    }


    /**
     * Sets the options value for this ScheduleRequestMsg.
     * 
     * @param options
     */
    public void setOptions(com.exacttarget.wsdl.partnerAPI.ScheduleOptions options) {
        this.options = options;
    }


    /**
     * Gets the action value for this ScheduleRequestMsg.
     * 
     * @return action
     */
    public java.lang.String getAction() {
        return action;
    }


    /**
     * Sets the action value for this ScheduleRequestMsg.
     * 
     * @param action
     */
    public void setAction(java.lang.String action) {
        this.action = action;
    }


    /**
     * Gets the schedule value for this ScheduleRequestMsg.
     * 
     * @return schedule
     */
    public com.exacttarget.wsdl.partnerAPI.ScheduleDefinition getSchedule() {
        return schedule;
    }


    /**
     * Sets the schedule value for this ScheduleRequestMsg.
     * 
     * @param schedule
     */
    public void setSchedule(com.exacttarget.wsdl.partnerAPI.ScheduleDefinition schedule) {
        this.schedule = schedule;
    }


    /**
     * Gets the interactions value for this ScheduleRequestMsg.
     * 
     * @return interactions
     */
    public com.exacttarget.wsdl.partnerAPI.APIObject[] getInteractions() {
        return interactions;
    }


    /**
     * Sets the interactions value for this ScheduleRequestMsg.
     * 
     * @param interactions
     */
    public void setInteractions(com.exacttarget.wsdl.partnerAPI.APIObject[] interactions) {
        this.interactions = interactions;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ScheduleRequestMsg)) return false;
        ScheduleRequestMsg other = (ScheduleRequestMsg) obj;
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
            ((this.schedule==null && other.getSchedule()==null) || 
             (this.schedule!=null &&
              this.schedule.equals(other.getSchedule()))) &&
            ((this.interactions==null && other.getInteractions()==null) || 
             (this.interactions!=null &&
              java.util.Arrays.equals(this.interactions, other.getInteractions())));
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
        if (getSchedule() != null) {
            _hashCode += getSchedule().hashCode();
        }
        if (getInteractions() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getInteractions());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getInteractions(), i);
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
        new org.apache.axis.description.TypeDesc(ScheduleRequestMsg.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">ScheduleRequestMsg"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("options");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Options"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ScheduleOptions"));
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
        elemField.setFieldName("schedule");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Schedule"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ScheduleDefinition"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("interactions");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Interactions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "APIObject"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Interaction"));
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
