/**
 * HourlyRecurrence.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class HourlyRecurrence  extends com.exacttarget.wsdl.partnerAPI.Recurrence  implements java.io.Serializable {
    private com.exacttarget.wsdl.partnerAPI.HourlyRecurrencePatternTypeEnum hourlyRecurrencePatternType;

    private java.lang.Integer hourInterval;

    public HourlyRecurrence() {
    }

    public HourlyRecurrence(
           com.exacttarget.wsdl.partnerAPI.HourlyRecurrencePatternTypeEnum hourlyRecurrencePatternType,
           java.lang.Integer hourInterval) {
        this.hourlyRecurrencePatternType = hourlyRecurrencePatternType;
        this.hourInterval = hourInterval;
    }


    /**
     * Gets the hourlyRecurrencePatternType value for this HourlyRecurrence.
     * 
     * @return hourlyRecurrencePatternType
     */
    public com.exacttarget.wsdl.partnerAPI.HourlyRecurrencePatternTypeEnum getHourlyRecurrencePatternType() {
        return hourlyRecurrencePatternType;
    }


    /**
     * Sets the hourlyRecurrencePatternType value for this HourlyRecurrence.
     * 
     * @param hourlyRecurrencePatternType
     */
    public void setHourlyRecurrencePatternType(com.exacttarget.wsdl.partnerAPI.HourlyRecurrencePatternTypeEnum hourlyRecurrencePatternType) {
        this.hourlyRecurrencePatternType = hourlyRecurrencePatternType;
    }


    /**
     * Gets the hourInterval value for this HourlyRecurrence.
     * 
     * @return hourInterval
     */
    public java.lang.Integer getHourInterval() {
        return hourInterval;
    }


    /**
     * Sets the hourInterval value for this HourlyRecurrence.
     * 
     * @param hourInterval
     */
    public void setHourInterval(java.lang.Integer hourInterval) {
        this.hourInterval = hourInterval;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof HourlyRecurrence)) return false;
        HourlyRecurrence other = (HourlyRecurrence) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.hourlyRecurrencePatternType==null && other.getHourlyRecurrencePatternType()==null) || 
             (this.hourlyRecurrencePatternType!=null &&
              this.hourlyRecurrencePatternType.equals(other.getHourlyRecurrencePatternType()))) &&
            ((this.hourInterval==null && other.getHourInterval()==null) || 
             (this.hourInterval!=null &&
              this.hourInterval.equals(other.getHourInterval())));
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
        if (getHourlyRecurrencePatternType() != null) {
            _hashCode += getHourlyRecurrencePatternType().hashCode();
        }
        if (getHourInterval() != null) {
            _hashCode += getHourInterval().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(HourlyRecurrence.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "HourlyRecurrence"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hourlyRecurrencePatternType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "HourlyRecurrencePatternType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "HourlyRecurrencePatternTypeEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hourInterval");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "HourInterval"));
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
