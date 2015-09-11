/**
 * MonthlyRecurrence.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.flieger.exacttarget.wsdl.api;

public class MonthlyRecurrence  extends br.flieger.exacttarget.wsdl.api.Recurrence  implements java.io.Serializable {
    private br.flieger.exacttarget.wsdl.api.MonthlyRecurrencePatternTypeEnum monthlyRecurrencePatternType;

    private java.lang.Integer monthlyInterval;

    private java.lang.Integer scheduledDay;

    private br.flieger.exacttarget.wsdl.api.WeekOfMonthEnum scheduledWeek;

    private br.flieger.exacttarget.wsdl.api.DayOfWeekEnum scheduledDayOfWeek;

    public MonthlyRecurrence() {
    }

    public MonthlyRecurrence(
           br.flieger.exacttarget.wsdl.api.MonthlyRecurrencePatternTypeEnum monthlyRecurrencePatternType,
           java.lang.Integer monthlyInterval,
           java.lang.Integer scheduledDay,
           br.flieger.exacttarget.wsdl.api.WeekOfMonthEnum scheduledWeek,
           br.flieger.exacttarget.wsdl.api.DayOfWeekEnum scheduledDayOfWeek) {
        this.monthlyRecurrencePatternType = monthlyRecurrencePatternType;
        this.monthlyInterval = monthlyInterval;
        this.scheduledDay = scheduledDay;
        this.scheduledWeek = scheduledWeek;
        this.scheduledDayOfWeek = scheduledDayOfWeek;
    }


    /**
     * Gets the monthlyRecurrencePatternType value for this MonthlyRecurrence.
     * 
     * @return monthlyRecurrencePatternType
     */
    public br.flieger.exacttarget.wsdl.api.MonthlyRecurrencePatternTypeEnum getMonthlyRecurrencePatternType() {
        return monthlyRecurrencePatternType;
    }


    /**
     * Sets the monthlyRecurrencePatternType value for this MonthlyRecurrence.
     * 
     * @param monthlyRecurrencePatternType
     */
    public void setMonthlyRecurrencePatternType(br.flieger.exacttarget.wsdl.api.MonthlyRecurrencePatternTypeEnum monthlyRecurrencePatternType) {
        this.monthlyRecurrencePatternType = monthlyRecurrencePatternType;
    }


    /**
     * Gets the monthlyInterval value for this MonthlyRecurrence.
     * 
     * @return monthlyInterval
     */
    public java.lang.Integer getMonthlyInterval() {
        return monthlyInterval;
    }


    /**
     * Sets the monthlyInterval value for this MonthlyRecurrence.
     * 
     * @param monthlyInterval
     */
    public void setMonthlyInterval(java.lang.Integer monthlyInterval) {
        this.monthlyInterval = monthlyInterval;
    }


    /**
     * Gets the scheduledDay value for this MonthlyRecurrence.
     * 
     * @return scheduledDay
     */
    public java.lang.Integer getScheduledDay() {
        return scheduledDay;
    }


    /**
     * Sets the scheduledDay value for this MonthlyRecurrence.
     * 
     * @param scheduledDay
     */
    public void setScheduledDay(java.lang.Integer scheduledDay) {
        this.scheduledDay = scheduledDay;
    }


    /**
     * Gets the scheduledWeek value for this MonthlyRecurrence.
     * 
     * @return scheduledWeek
     */
    public br.flieger.exacttarget.wsdl.api.WeekOfMonthEnum getScheduledWeek() {
        return scheduledWeek;
    }


    /**
     * Sets the scheduledWeek value for this MonthlyRecurrence.
     * 
     * @param scheduledWeek
     */
    public void setScheduledWeek(br.flieger.exacttarget.wsdl.api.WeekOfMonthEnum scheduledWeek) {
        this.scheduledWeek = scheduledWeek;
    }


    /**
     * Gets the scheduledDayOfWeek value for this MonthlyRecurrence.
     * 
     * @return scheduledDayOfWeek
     */
    public br.flieger.exacttarget.wsdl.api.DayOfWeekEnum getScheduledDayOfWeek() {
        return scheduledDayOfWeek;
    }


    /**
     * Sets the scheduledDayOfWeek value for this MonthlyRecurrence.
     * 
     * @param scheduledDayOfWeek
     */
    public void setScheduledDayOfWeek(br.flieger.exacttarget.wsdl.api.DayOfWeekEnum scheduledDayOfWeek) {
        this.scheduledDayOfWeek = scheduledDayOfWeek;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MonthlyRecurrence)) return false;
        MonthlyRecurrence other = (MonthlyRecurrence) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.monthlyRecurrencePatternType==null && other.getMonthlyRecurrencePatternType()==null) || 
             (this.monthlyRecurrencePatternType!=null &&
              this.monthlyRecurrencePatternType.equals(other.getMonthlyRecurrencePatternType()))) &&
            ((this.monthlyInterval==null && other.getMonthlyInterval()==null) || 
             (this.monthlyInterval!=null &&
              this.monthlyInterval.equals(other.getMonthlyInterval()))) &&
            ((this.scheduledDay==null && other.getScheduledDay()==null) || 
             (this.scheduledDay!=null &&
              this.scheduledDay.equals(other.getScheduledDay()))) &&
            ((this.scheduledWeek==null && other.getScheduledWeek()==null) || 
             (this.scheduledWeek!=null &&
              this.scheduledWeek.equals(other.getScheduledWeek()))) &&
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
        if (getMonthlyRecurrencePatternType() != null) {
            _hashCode += getMonthlyRecurrencePatternType().hashCode();
        }
        if (getMonthlyInterval() != null) {
            _hashCode += getMonthlyInterval().hashCode();
        }
        if (getScheduledDay() != null) {
            _hashCode += getScheduledDay().hashCode();
        }
        if (getScheduledWeek() != null) {
            _hashCode += getScheduledWeek().hashCode();
        }
        if (getScheduledDayOfWeek() != null) {
            _hashCode += getScheduledDayOfWeek().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MonthlyRecurrence.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MonthlyRecurrence"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("monthlyRecurrencePatternType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MonthlyRecurrencePatternType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MonthlyRecurrencePatternTypeEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("monthlyInterval");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MonthlyInterval"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
