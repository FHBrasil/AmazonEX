/**
 * DailyRecurrence.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class DailyRecurrence  extends com.exacttarget.wsdl.partnerAPI.Recurrence  implements java.io.Serializable {
    private com.exacttarget.wsdl.partnerAPI.DailyRecurrencePatternTypeEnum dailyRecurrencePatternType;

    private java.lang.Integer dayInterval;

    public DailyRecurrence() {
    }

    public DailyRecurrence(
           com.exacttarget.wsdl.partnerAPI.DailyRecurrencePatternTypeEnum dailyRecurrencePatternType,
           java.lang.Integer dayInterval) {
        this.dailyRecurrencePatternType = dailyRecurrencePatternType;
        this.dayInterval = dayInterval;
    }


    /**
     * Gets the dailyRecurrencePatternType value for this DailyRecurrence.
     * 
     * @return dailyRecurrencePatternType
     */
    public com.exacttarget.wsdl.partnerAPI.DailyRecurrencePatternTypeEnum getDailyRecurrencePatternType() {
        return dailyRecurrencePatternType;
    }


    /**
     * Sets the dailyRecurrencePatternType value for this DailyRecurrence.
     * 
     * @param dailyRecurrencePatternType
     */
    public void setDailyRecurrencePatternType(com.exacttarget.wsdl.partnerAPI.DailyRecurrencePatternTypeEnum dailyRecurrencePatternType) {
        this.dailyRecurrencePatternType = dailyRecurrencePatternType;
    }


    /**
     * Gets the dayInterval value for this DailyRecurrence.
     * 
     * @return dayInterval
     */
    public java.lang.Integer getDayInterval() {
        return dayInterval;
    }


    /**
     * Sets the dayInterval value for this DailyRecurrence.
     * 
     * @param dayInterval
     */
    public void setDayInterval(java.lang.Integer dayInterval) {
        this.dayInterval = dayInterval;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DailyRecurrence)) return false;
        DailyRecurrence other = (DailyRecurrence) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.dailyRecurrencePatternType==null && other.getDailyRecurrencePatternType()==null) || 
             (this.dailyRecurrencePatternType!=null &&
              this.dailyRecurrencePatternType.equals(other.getDailyRecurrencePatternType()))) &&
            ((this.dayInterval==null && other.getDayInterval()==null) || 
             (this.dayInterval!=null &&
              this.dayInterval.equals(other.getDayInterval())));
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
        if (getDailyRecurrencePatternType() != null) {
            _hashCode += getDailyRecurrencePatternType().hashCode();
        }
        if (getDayInterval() != null) {
            _hashCode += getDayInterval().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DailyRecurrence.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DailyRecurrence"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dailyRecurrencePatternType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DailyRecurrencePatternType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DailyRecurrencePatternTypeEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dayInterval");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DayInterval"));
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
