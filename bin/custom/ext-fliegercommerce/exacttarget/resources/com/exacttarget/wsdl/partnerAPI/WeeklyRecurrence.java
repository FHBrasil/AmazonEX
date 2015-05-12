/**
 * WeeklyRecurrence.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class WeeklyRecurrence  extends com.exacttarget.wsdl.partnerAPI.Recurrence  implements java.io.Serializable {
    private com.exacttarget.wsdl.partnerAPI.WeeklyRecurrencePatternTypeEnum weeklyRecurrencePatternType;

    private java.lang.Integer weekInterval;

    private java.lang.Boolean sunday;

    private java.lang.Boolean monday;

    private java.lang.Boolean tuesday;

    private java.lang.Boolean wednesday;

    private java.lang.Boolean thursday;

    private java.lang.Boolean friday;

    private java.lang.Boolean saturday;

    public WeeklyRecurrence() {
    }

    public WeeklyRecurrence(
           com.exacttarget.wsdl.partnerAPI.WeeklyRecurrencePatternTypeEnum weeklyRecurrencePatternType,
           java.lang.Integer weekInterval,
           java.lang.Boolean sunday,
           java.lang.Boolean monday,
           java.lang.Boolean tuesday,
           java.lang.Boolean wednesday,
           java.lang.Boolean thursday,
           java.lang.Boolean friday,
           java.lang.Boolean saturday) {
        this.weeklyRecurrencePatternType = weeklyRecurrencePatternType;
        this.weekInterval = weekInterval;
        this.sunday = sunday;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
    }


    /**
     * Gets the weeklyRecurrencePatternType value for this WeeklyRecurrence.
     * 
     * @return weeklyRecurrencePatternType
     */
    public com.exacttarget.wsdl.partnerAPI.WeeklyRecurrencePatternTypeEnum getWeeklyRecurrencePatternType() {
        return weeklyRecurrencePatternType;
    }


    /**
     * Sets the weeklyRecurrencePatternType value for this WeeklyRecurrence.
     * 
     * @param weeklyRecurrencePatternType
     */
    public void setWeeklyRecurrencePatternType(com.exacttarget.wsdl.partnerAPI.WeeklyRecurrencePatternTypeEnum weeklyRecurrencePatternType) {
        this.weeklyRecurrencePatternType = weeklyRecurrencePatternType;
    }


    /**
     * Gets the weekInterval value for this WeeklyRecurrence.
     * 
     * @return weekInterval
     */
    public java.lang.Integer getWeekInterval() {
        return weekInterval;
    }


    /**
     * Sets the weekInterval value for this WeeklyRecurrence.
     * 
     * @param weekInterval
     */
    public void setWeekInterval(java.lang.Integer weekInterval) {
        this.weekInterval = weekInterval;
    }


    /**
     * Gets the sunday value for this WeeklyRecurrence.
     * 
     * @return sunday
     */
    public java.lang.Boolean getSunday() {
        return sunday;
    }


    /**
     * Sets the sunday value for this WeeklyRecurrence.
     * 
     * @param sunday
     */
    public void setSunday(java.lang.Boolean sunday) {
        this.sunday = sunday;
    }


    /**
     * Gets the monday value for this WeeklyRecurrence.
     * 
     * @return monday
     */
    public java.lang.Boolean getMonday() {
        return monday;
    }


    /**
     * Sets the monday value for this WeeklyRecurrence.
     * 
     * @param monday
     */
    public void setMonday(java.lang.Boolean monday) {
        this.monday = monday;
    }


    /**
     * Gets the tuesday value for this WeeklyRecurrence.
     * 
     * @return tuesday
     */
    public java.lang.Boolean getTuesday() {
        return tuesday;
    }


    /**
     * Sets the tuesday value for this WeeklyRecurrence.
     * 
     * @param tuesday
     */
    public void setTuesday(java.lang.Boolean tuesday) {
        this.tuesday = tuesday;
    }


    /**
     * Gets the wednesday value for this WeeklyRecurrence.
     * 
     * @return wednesday
     */
    public java.lang.Boolean getWednesday() {
        return wednesday;
    }


    /**
     * Sets the wednesday value for this WeeklyRecurrence.
     * 
     * @param wednesday
     */
    public void setWednesday(java.lang.Boolean wednesday) {
        this.wednesday = wednesday;
    }


    /**
     * Gets the thursday value for this WeeklyRecurrence.
     * 
     * @return thursday
     */
    public java.lang.Boolean getThursday() {
        return thursday;
    }


    /**
     * Sets the thursday value for this WeeklyRecurrence.
     * 
     * @param thursday
     */
    public void setThursday(java.lang.Boolean thursday) {
        this.thursday = thursday;
    }


    /**
     * Gets the friday value for this WeeklyRecurrence.
     * 
     * @return friday
     */
    public java.lang.Boolean getFriday() {
        return friday;
    }


    /**
     * Sets the friday value for this WeeklyRecurrence.
     * 
     * @param friday
     */
    public void setFriday(java.lang.Boolean friday) {
        this.friday = friday;
    }


    /**
     * Gets the saturday value for this WeeklyRecurrence.
     * 
     * @return saturday
     */
    public java.lang.Boolean getSaturday() {
        return saturday;
    }


    /**
     * Sets the saturday value for this WeeklyRecurrence.
     * 
     * @param saturday
     */
    public void setSaturday(java.lang.Boolean saturday) {
        this.saturday = saturday;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof WeeklyRecurrence)) return false;
        WeeklyRecurrence other = (WeeklyRecurrence) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.weeklyRecurrencePatternType==null && other.getWeeklyRecurrencePatternType()==null) || 
             (this.weeklyRecurrencePatternType!=null &&
              this.weeklyRecurrencePatternType.equals(other.getWeeklyRecurrencePatternType()))) &&
            ((this.weekInterval==null && other.getWeekInterval()==null) || 
             (this.weekInterval!=null &&
              this.weekInterval.equals(other.getWeekInterval()))) &&
            ((this.sunday==null && other.getSunday()==null) || 
             (this.sunday!=null &&
              this.sunday.equals(other.getSunday()))) &&
            ((this.monday==null && other.getMonday()==null) || 
             (this.monday!=null &&
              this.monday.equals(other.getMonday()))) &&
            ((this.tuesday==null && other.getTuesday()==null) || 
             (this.tuesday!=null &&
              this.tuesday.equals(other.getTuesday()))) &&
            ((this.wednesday==null && other.getWednesday()==null) || 
             (this.wednesday!=null &&
              this.wednesday.equals(other.getWednesday()))) &&
            ((this.thursday==null && other.getThursday()==null) || 
             (this.thursday!=null &&
              this.thursday.equals(other.getThursday()))) &&
            ((this.friday==null && other.getFriday()==null) || 
             (this.friday!=null &&
              this.friday.equals(other.getFriday()))) &&
            ((this.saturday==null && other.getSaturday()==null) || 
             (this.saturday!=null &&
              this.saturday.equals(other.getSaturday())));
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
        if (getWeeklyRecurrencePatternType() != null) {
            _hashCode += getWeeklyRecurrencePatternType().hashCode();
        }
        if (getWeekInterval() != null) {
            _hashCode += getWeekInterval().hashCode();
        }
        if (getSunday() != null) {
            _hashCode += getSunday().hashCode();
        }
        if (getMonday() != null) {
            _hashCode += getMonday().hashCode();
        }
        if (getTuesday() != null) {
            _hashCode += getTuesday().hashCode();
        }
        if (getWednesday() != null) {
            _hashCode += getWednesday().hashCode();
        }
        if (getThursday() != null) {
            _hashCode += getThursday().hashCode();
        }
        if (getFriday() != null) {
            _hashCode += getFriday().hashCode();
        }
        if (getSaturday() != null) {
            _hashCode += getSaturday().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(WeeklyRecurrence.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "WeeklyRecurrence"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("weeklyRecurrencePatternType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "WeeklyRecurrencePatternType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "WeeklyRecurrencePatternTypeEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("weekInterval");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "WeekInterval"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sunday");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Sunday"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("monday");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Monday"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tuesday");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Tuesday"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("wednesday");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Wednesday"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("thursday");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Thursday"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("friday");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Friday"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("saturday");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Saturday"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
