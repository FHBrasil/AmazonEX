/**
 * MinutelyRecurrence.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class MinutelyRecurrence  extends com.exacttarget.wsdl.partnerAPI.Recurrence  implements java.io.Serializable {
    private com.exacttarget.wsdl.partnerAPI.MinutelyRecurrencePatternTypeEnum minutelyRecurrencePatternType;

    private java.lang.Integer minuteInterval;

    public MinutelyRecurrence() {
    }

    public MinutelyRecurrence(
           com.exacttarget.wsdl.partnerAPI.MinutelyRecurrencePatternTypeEnum minutelyRecurrencePatternType,
           java.lang.Integer minuteInterval) {
        this.minutelyRecurrencePatternType = minutelyRecurrencePatternType;
        this.minuteInterval = minuteInterval;
    }


    /**
     * Gets the minutelyRecurrencePatternType value for this MinutelyRecurrence.
     * 
     * @return minutelyRecurrencePatternType
     */
    public com.exacttarget.wsdl.partnerAPI.MinutelyRecurrencePatternTypeEnum getMinutelyRecurrencePatternType() {
        return minutelyRecurrencePatternType;
    }


    /**
     * Sets the minutelyRecurrencePatternType value for this MinutelyRecurrence.
     * 
     * @param minutelyRecurrencePatternType
     */
    public void setMinutelyRecurrencePatternType(com.exacttarget.wsdl.partnerAPI.MinutelyRecurrencePatternTypeEnum minutelyRecurrencePatternType) {
        this.minutelyRecurrencePatternType = minutelyRecurrencePatternType;
    }


    /**
     * Gets the minuteInterval value for this MinutelyRecurrence.
     * 
     * @return minuteInterval
     */
    public java.lang.Integer getMinuteInterval() {
        return minuteInterval;
    }


    /**
     * Sets the minuteInterval value for this MinutelyRecurrence.
     * 
     * @param minuteInterval
     */
    public void setMinuteInterval(java.lang.Integer minuteInterval) {
        this.minuteInterval = minuteInterval;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MinutelyRecurrence)) return false;
        MinutelyRecurrence other = (MinutelyRecurrence) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.minutelyRecurrencePatternType==null && other.getMinutelyRecurrencePatternType()==null) || 
             (this.minutelyRecurrencePatternType!=null &&
              this.minutelyRecurrencePatternType.equals(other.getMinutelyRecurrencePatternType()))) &&
            ((this.minuteInterval==null && other.getMinuteInterval()==null) || 
             (this.minuteInterval!=null &&
              this.minuteInterval.equals(other.getMinuteInterval())));
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
        if (getMinutelyRecurrencePatternType() != null) {
            _hashCode += getMinutelyRecurrencePatternType().hashCode();
        }
        if (getMinuteInterval() != null) {
            _hashCode += getMinuteInterval().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MinutelyRecurrence.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MinutelyRecurrence"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("minutelyRecurrencePatternType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MinutelyRecurrencePatternType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MinutelyRecurrencePatternTypeEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("minuteInterval");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MinuteInterval"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
