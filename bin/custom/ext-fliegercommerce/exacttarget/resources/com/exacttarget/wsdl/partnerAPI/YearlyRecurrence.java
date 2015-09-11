/**
 * YearlyRecurrence.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class YearlyRecurrence  extends com.exacttarget.wsdl.partnerAPI.Recurrence  implements java.io.Serializable {
    private com.exacttarget.wsdl.partnerAPI.YearlyRecurrencePatternTypeEnum yearlyRecurrencePatternType;

    private java.lang.Integer scheduledDay;

    private com.exacttarget.wsdl.partnerAPI.WeekOfMonthEnum scheduledWeek;

    private com.exacttarget.wsdl.partnerAPI.MonthOfYearEnum scheduledMonth;

    private com.exacttarget.wsdl.partnerAPI.DayOfWeekEnum scheduledDayOfWeek;

    public YearlyRecurrence() {
    }

    public YearlyRecurrence(
           com.exacttarget.wsdl.partnerAPI.YearlyRecurrencePatternTypeEnum yearlyRecurrencePatternType,
           java.lang.Integer scheduledDay,
           com.exacttarget.wsdl.partnerAPI.WeekOfMonthEnum scheduledWeek,
           com.exacttarget.wsdl.partnerAPI.MonthOfYearEnum scheduledMonth,
           com.exacttarget.wsdl.partnerAPI.DayOfWeekEnum scheduledDayOfWeek) {
        this.yearlyRecurrencePatternType = yearlyRecurrencePatternType;
        this.scheduledDay = scheduledDay;
        this.scheduledWeek = scheduledWeek;
        this.scheduledMonth = scheduledMonth;
        this.scheduledDayOfWeek = scheduledDayOfWeek;
    }


    /**
     * Gets the yearlyRecurrencePatternType value for this YearlyRecurrence.
     * 
     * @return yearlyRecurrencePatternType
     */
    public com.exacttarget.wsdl.partnerAPI.YearlyRecurrencePatternTypeEnum getYearlyRecurrencePatternType() {
        return yearlyRecurrencePatternType;
    }


    /**
     * Sets the yearlyRecurrencePatternType value for this YearlyRecurrence.
     * 
     * @param yearlyRecurrencePatternType
     */
    public void setYearlyRecurrencePatternType(com.exacttarget.wsdl.partnerAPI.YearlyRecurrencePatternTypeEnum yearlyRecurrencePatternType) {
        this.yearlyRecurrencePatternType = yearlyRecurrencePatternType;
    }


    /**
     * Gets the scheduledDay value for this YearlyRecurrence.
     * 
     * @return scheduledDay
     */
    public java.lang.Integer getScheduledDay() {
        return scheduledDay;
    }


    /**
     * Sets the scheduledDay value for this YearlyRecurrence.
     * 
     * @param scheduledDay
     */
    public void setScheduledDay(java.lang.Integer scheduledDay) {
        this.scheduledDay = scheduledDay;
    }


    /**
     * Gets the scheduledWeek value for this YearlyRecurrence.
     * 
     * @return scheduledWeek
     */
    public com.exacttarget.wsdl.partnerAPI.WeekOfMonthEnum getScheduledWeek() {
        return scheduledWeek;
    }


    /**
     * Sets the scheduledWeek value for this YearlyRecurrence.
     * 
     * @param scheduledWeek
     */
    public void setScheduledWeek(com.exacttarget.wsdl.partnerAPI.WeekOfMonthEnum scheduledWeek) {
        this.scheduledWeek = scheduledWeek;
    }


    /**
     * Gets the scheduledMonth value for this YearlyRecurrence.
     * 
     * @return scheduledMonth
     */
    public com.exacttarget.wsdl.partnerAPI.MonthOfYearEnum getScheduledMonth() {
        return scheduledMonth;
    }


    /**
     * Sets the scheduledMonth value for this YearlyRecurrence.
     * 
     * @param scheduledMonth
     */
    public void setScheduledMonth(com.exacttarget.wsdl.partnerAPI.MonthOfYearEnum scheduledMonth) {
        this.scheduledMonth = scheduledMonth;
    }


    /**
     * Gets the scheduledDayOfWeek value for this YearlyRecurrence.
     * 
     * @return scheduledDayOfWeek
     */
    public com.exacttarget.wsdl.partnerAPI.DayOfWeekEnum getScheduledDayOfWeek() {
        return scheduledDayOfWeek;
    }


    /**
     * Sets the scheduledDayOfWeek value for this YearlyRecurrence.
     * 
     * @param scheduledDayOfWeek
     */
    public void setScheduledDayOfWeek(com.exacttarget.wsdl.partnerAPI.DayOfWeekEnum scheduledDayOfWeek) {
        this.scheduledDayOfWeek = scheduledDayOfWeek;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof YearlyRecurrence)) return false;
        YearlyRecurrence other = (YearlyRecurrence) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.yearlyRecurrencePatternType==null && other.getYearlyRecurrencePatternType()==null) || 
             (this.yearlyRecurrencePatternType!=null &&
              this.yearlyRecurrencePatternType.equals(other.getYearlyRecurrencePatternType()))) &&
            ((this.scheduledDay==null && other.getScheduledDay()==null) || 
             (this.scheduledDay!=null &&
              this.scheduledDay.equals(other.getScheduledDay()))) &&
            ((this.scheduledWeek==null && other.getScheduledWeek()==null) || 
             (this.scheduledWeek!=null &&
              this.scheduledWeek.equals(other.getScheduledWeek()))) &&
            ((this.scheduledMonth==null && other.getScheduledMonth()==null) || 
             (this.scheduledMonth!=null &&
              this.scheduledMonth.equals(other.getScheduledMonth()))) &&
            ((this.scheduledDayOfWeek==null && other.getScheduledDayOfWeek()==null) || 
             (this.scheduledDayOfWeek!=null &&
              this.scheduledDayOfWeek.equals(other.getScheduledDayOfWeek())));
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
        if (getYearlyRecurrencePatternType() != null) {
            _hashCode += getYearlyRecurrencePatternType().hashCode();
        }
        if (getScheduledDay() != null) {
            _hashCode += getScheduledDay().hashCode();
        }
        if (getScheduledWeek() != null) {
            _hashCode += getScheduledWeek().hashCode();
        }
        if (getScheduledMonth() != null) {
            _hashCode += getScheduledMonth().hashCode();
        }
        if (getScheduledDayOfWeek() != null) {
            _hashCode += getScheduledDayOfWeek().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(YearlyRecurrence.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "YearlyRecurrence"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("yearlyRecurrencePatternType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "YearlyRecurrencePatternType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "YearlyRecurrencePatternTypeEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("scheduledDay");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ScheduledDay"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("scheduledWeek");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ScheduledWeek"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "WeekOfMonthEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("scheduledMonth");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ScheduledMonth"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MonthOfYearEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("scheduledDayOfWeek");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ScheduledDayOfWeek"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DayOfWeekEnum"));
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
