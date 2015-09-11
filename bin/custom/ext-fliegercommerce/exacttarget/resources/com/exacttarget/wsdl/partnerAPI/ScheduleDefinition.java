/**
 * ScheduleDefinition.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class ScheduleDefinition  extends com.exacttarget.wsdl.partnerAPI.APIObject  implements java.io.Serializable {
    private java.lang.String name;

    private java.lang.String description;

    private com.exacttarget.wsdl.partnerAPI.Recurrence recurrence;

    private com.exacttarget.wsdl.partnerAPI.RecurrenceTypeEnum recurrenceType;

    private com.exacttarget.wsdl.partnerAPI.RecurrenceRangeTypeEnum recurrenceRangeType;

    private java.util.Calendar startDateTime;

    private java.util.Calendar endDateTime;

    private java.lang.Integer occurrences;

    private java.lang.String keyword;

    private com.exacttarget.wsdl.partnerAPI.TimeZone timeZone;

    public ScheduleDefinition() {
    }

    public ScheduleDefinition(
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
           com.exacttarget.wsdl.partnerAPI.Recurrence recurrence,
           com.exacttarget.wsdl.partnerAPI.RecurrenceTypeEnum recurrenceType,
           com.exacttarget.wsdl.partnerAPI.RecurrenceRangeTypeEnum recurrenceRangeType,
           java.util.Calendar startDateTime,
           java.util.Calendar endDateTime,
           java.lang.Integer occurrences,
           java.lang.String keyword,
           com.exacttarget.wsdl.partnerAPI.TimeZone timeZone) {
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
            objectState);
        this.name = name;
        this.description = description;
        this.recurrence = recurrence;
        this.recurrenceType = recurrenceType;
        this.recurrenceRangeType = recurrenceRangeType;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.occurrences = occurrences;
        this.keyword = keyword;
        this.timeZone = timeZone;
    }


    /**
     * Gets the name value for this ScheduleDefinition.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this ScheduleDefinition.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the description value for this ScheduleDefinition.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this ScheduleDefinition.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the recurrence value for this ScheduleDefinition.
     * 
     * @return recurrence
     */
    public com.exacttarget.wsdl.partnerAPI.Recurrence getRecurrence() {
        return recurrence;
    }


    /**
     * Sets the recurrence value for this ScheduleDefinition.
     * 
     * @param recurrence
     */
    public void setRecurrence(com.exacttarget.wsdl.partnerAPI.Recurrence recurrence) {
        this.recurrence = recurrence;
    }


    /**
     * Gets the recurrenceType value for this ScheduleDefinition.
     * 
     * @return recurrenceType
     */
    public com.exacttarget.wsdl.partnerAPI.RecurrenceTypeEnum getRecurrenceType() {
        return recurrenceType;
    }


    /**
     * Sets the recurrenceType value for this ScheduleDefinition.
     * 
     * @param recurrenceType
     */
    public void setRecurrenceType(com.exacttarget.wsdl.partnerAPI.RecurrenceTypeEnum recurrenceType) {
        this.recurrenceType = recurrenceType;
    }


    /**
     * Gets the recurrenceRangeType value for this ScheduleDefinition.
     * 
     * @return recurrenceRangeType
     */
    public com.exacttarget.wsdl.partnerAPI.RecurrenceRangeTypeEnum getRecurrenceRangeType() {
        return recurrenceRangeType;
    }


    /**
     * Sets the recurrenceRangeType value for this ScheduleDefinition.
     * 
     * @param recurrenceRangeType
     */
    public void setRecurrenceRangeType(com.exacttarget.wsdl.partnerAPI.RecurrenceRangeTypeEnum recurrenceRangeType) {
        this.recurrenceRangeType = recurrenceRangeType;
    }


    /**
     * Gets the startDateTime value for this ScheduleDefinition.
     * 
     * @return startDateTime
     */
    public java.util.Calendar getStartDateTime() {
        return startDateTime;
    }


    /**
     * Sets the startDateTime value for this ScheduleDefinition.
     * 
     * @param startDateTime
     */
    public void setStartDateTime(java.util.Calendar startDateTime) {
        this.startDateTime = startDateTime;
    }


    /**
     * Gets the endDateTime value for this ScheduleDefinition.
     * 
     * @return endDateTime
     */
    public java.util.Calendar getEndDateTime() {
        return endDateTime;
    }


    /**
     * Sets the endDateTime value for this ScheduleDefinition.
     * 
     * @param endDateTime
     */
    public void setEndDateTime(java.util.Calendar endDateTime) {
        this.endDateTime = endDateTime;
    }


    /**
     * Gets the occurrences value for this ScheduleDefinition.
     * 
     * @return occurrences
     */
    public java.lang.Integer getOccurrences() {
        return occurrences;
    }


    /**
     * Sets the occurrences value for this ScheduleDefinition.
     * 
     * @param occurrences
     */
    public void setOccurrences(java.lang.Integer occurrences) {
        this.occurrences = occurrences;
    }


    /**
     * Gets the keyword value for this ScheduleDefinition.
     * 
     * @return keyword
     */
    public java.lang.String getKeyword() {
        return keyword;
    }


    /**
     * Sets the keyword value for this ScheduleDefinition.
     * 
     * @param keyword
     */
    public void setKeyword(java.lang.String keyword) {
        this.keyword = keyword;
    }


    /**
     * Gets the timeZone value for this ScheduleDefinition.
     * 
     * @return timeZone
     */
    public com.exacttarget.wsdl.partnerAPI.TimeZone getTimeZone() {
        return timeZone;
    }


    /**
     * Sets the timeZone value for this ScheduleDefinition.
     * 
     * @param timeZone
     */
    public void setTimeZone(com.exacttarget.wsdl.partnerAPI.TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ScheduleDefinition)) return false;
        ScheduleDefinition other = (ScheduleDefinition) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.recurrence==null && other.getRecurrence()==null) || 
             (this.recurrence!=null &&
              this.recurrence.equals(other.getRecurrence()))) &&
            ((this.recurrenceType==null && other.getRecurrenceType()==null) || 
             (this.recurrenceType!=null &&
              this.recurrenceType.equals(other.getRecurrenceType()))) &&
            ((this.recurrenceRangeType==null && other.getRecurrenceRangeType()==null) || 
             (this.recurrenceRangeType!=null &&
              this.recurrenceRangeType.equals(other.getRecurrenceRangeType()))) &&
            ((this.startDateTime==null && other.getStartDateTime()==null) || 
             (this.startDateTime!=null &&
              this.startDateTime.equals(other.getStartDateTime()))) &&
            ((this.endDateTime==null && other.getEndDateTime()==null) || 
             (this.endDateTime!=null &&
              this.endDateTime.equals(other.getEndDateTime()))) &&
            ((this.occurrences==null && other.getOccurrences()==null) || 
             (this.occurrences!=null &&
              this.occurrences.equals(other.getOccurrences()))) &&
            ((this.keyword==null && other.getKeyword()==null) || 
             (this.keyword!=null &&
              this.keyword.equals(other.getKeyword()))) &&
            ((this.timeZone==null && other.getTimeZone()==null) || 
             (this.timeZone!=null &&
              this.timeZone.equals(other.getTimeZone())));
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
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getRecurrence() != null) {
            _hashCode += getRecurrence().hashCode();
        }
        if (getRecurrenceType() != null) {
            _hashCode += getRecurrenceType().hashCode();
        }
        if (getRecurrenceRangeType() != null) {
            _hashCode += getRecurrenceRangeType().hashCode();
        }
        if (getStartDateTime() != null) {
            _hashCode += getStartDateTime().hashCode();
        }
        if (getEndDateTime() != null) {
            _hashCode += getEndDateTime().hashCode();
        }
        if (getOccurrences() != null) {
            _hashCode += getOccurrences().hashCode();
        }
        if (getKeyword() != null) {
            _hashCode += getKeyword().hashCode();
        }
        if (getTimeZone() != null) {
            _hashCode += getTimeZone().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ScheduleDefinition.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ScheduleDefinition"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recurrence");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Recurrence"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Recurrence"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recurrenceType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RecurrenceType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RecurrenceTypeEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recurrenceRangeType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RecurrenceRangeType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RecurrenceRangeTypeEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("startDateTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "StartDateTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("endDateTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "EndDateTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("occurrences");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Occurrences"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("keyword");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Keyword"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timeZone");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TimeZone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TimeZone"));
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
