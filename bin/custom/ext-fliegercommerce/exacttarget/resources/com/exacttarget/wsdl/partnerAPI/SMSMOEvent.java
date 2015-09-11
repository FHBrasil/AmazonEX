/**
 * SMSMOEvent.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class SMSMOEvent  extends com.exacttarget.wsdl.partnerAPI.APIObject  implements java.io.Serializable {
    private com.exacttarget.wsdl.partnerAPI.BaseMOKeyword keyword;

    private java.lang.String mobileTelephoneNumber;

    private java.lang.String MOCode;

    private java.util.Calendar eventDate;

    private java.lang.String MOMessage;

    private java.lang.String MTMessage;

    private java.lang.String carrier;

    public SMSMOEvent() {
    }

    public SMSMOEvent(
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
           com.exacttarget.wsdl.partnerAPI.BaseMOKeyword keyword,
           java.lang.String mobileTelephoneNumber,
           java.lang.String MOCode,
           java.util.Calendar eventDate,
           java.lang.String MOMessage,
           java.lang.String MTMessage,
           java.lang.String carrier) {
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
        this.keyword = keyword;
        this.mobileTelephoneNumber = mobileTelephoneNumber;
        this.MOCode = MOCode;
        this.eventDate = eventDate;
        this.MOMessage = MOMessage;
        this.MTMessage = MTMessage;
        this.carrier = carrier;
    }


    /**
     * Gets the keyword value for this SMSMOEvent.
     * 
     * @return keyword
     */
    public com.exacttarget.wsdl.partnerAPI.BaseMOKeyword getKeyword() {
        return keyword;
    }


    /**
     * Sets the keyword value for this SMSMOEvent.
     * 
     * @param keyword
     */
    public void setKeyword(com.exacttarget.wsdl.partnerAPI.BaseMOKeyword keyword) {
        this.keyword = keyword;
    }


    /**
     * Gets the mobileTelephoneNumber value for this SMSMOEvent.
     * 
     * @return mobileTelephoneNumber
     */
    public java.lang.String getMobileTelephoneNumber() {
        return mobileTelephoneNumber;
    }


    /**
     * Sets the mobileTelephoneNumber value for this SMSMOEvent.
     * 
     * @param mobileTelephoneNumber
     */
    public void setMobileTelephoneNumber(java.lang.String mobileTelephoneNumber) {
        this.mobileTelephoneNumber = mobileTelephoneNumber;
    }


    /**
     * Gets the MOCode value for this SMSMOEvent.
     * 
     * @return MOCode
     */
    public java.lang.String getMOCode() {
        return MOCode;
    }


    /**
     * Sets the MOCode value for this SMSMOEvent.
     * 
     * @param MOCode
     */
    public void setMOCode(java.lang.String MOCode) {
        this.MOCode = MOCode;
    }


    /**
     * Gets the eventDate value for this SMSMOEvent.
     * 
     * @return eventDate
     */
    public java.util.Calendar getEventDate() {
        return eventDate;
    }


    /**
     * Sets the eventDate value for this SMSMOEvent.
     * 
     * @param eventDate
     */
    public void setEventDate(java.util.Calendar eventDate) {
        this.eventDate = eventDate;
    }


    /**
     * Gets the MOMessage value for this SMSMOEvent.
     * 
     * @return MOMessage
     */
    public java.lang.String getMOMessage() {
        return MOMessage;
    }


    /**
     * Sets the MOMessage value for this SMSMOEvent.
     * 
     * @param MOMessage
     */
    public void setMOMessage(java.lang.String MOMessage) {
        this.MOMessage = MOMessage;
    }


    /**
     * Gets the MTMessage value for this SMSMOEvent.
     * 
     * @return MTMessage
     */
    public java.lang.String getMTMessage() {
        return MTMessage;
    }


    /**
     * Sets the MTMessage value for this SMSMOEvent.
     * 
     * @param MTMessage
     */
    public void setMTMessage(java.lang.String MTMessage) {
        this.MTMessage = MTMessage;
    }


    /**
     * Gets the carrier value for this SMSMOEvent.
     * 
     * @return carrier
     */
    public java.lang.String getCarrier() {
        return carrier;
    }


    /**
     * Sets the carrier value for this SMSMOEvent.
     * 
     * @param carrier
     */
    public void setCarrier(java.lang.String carrier) {
        this.carrier = carrier;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SMSMOEvent)) return false;
        SMSMOEvent other = (SMSMOEvent) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.keyword==null && other.getKeyword()==null) || 
             (this.keyword!=null &&
              this.keyword.equals(other.getKeyword()))) &&
            ((this.mobileTelephoneNumber==null && other.getMobileTelephoneNumber()==null) || 
             (this.mobileTelephoneNumber!=null &&
              this.mobileTelephoneNumber.equals(other.getMobileTelephoneNumber()))) &&
            ((this.MOCode==null && other.getMOCode()==null) || 
             (this.MOCode!=null &&
              this.MOCode.equals(other.getMOCode()))) &&
            ((this.eventDate==null && other.getEventDate()==null) || 
             (this.eventDate!=null &&
              this.eventDate.equals(other.getEventDate()))) &&
            ((this.MOMessage==null && other.getMOMessage()==null) || 
             (this.MOMessage!=null &&
              this.MOMessage.equals(other.getMOMessage()))) &&
            ((this.MTMessage==null && other.getMTMessage()==null) || 
             (this.MTMessage!=null &&
              this.MTMessage.equals(other.getMTMessage()))) &&
            ((this.carrier==null && other.getCarrier()==null) || 
             (this.carrier!=null &&
              this.carrier.equals(other.getCarrier())));
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
        if (getKeyword() != null) {
            _hashCode += getKeyword().hashCode();
        }
        if (getMobileTelephoneNumber() != null) {
            _hashCode += getMobileTelephoneNumber().hashCode();
        }
        if (getMOCode() != null) {
            _hashCode += getMOCode().hashCode();
        }
        if (getEventDate() != null) {
            _hashCode += getEventDate().hashCode();
        }
        if (getMOMessage() != null) {
            _hashCode += getMOMessage().hashCode();
        }
        if (getMTMessage() != null) {
            _hashCode += getMTMessage().hashCode();
        }
        if (getCarrier() != null) {
            _hashCode += getCarrier().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SMSMOEvent.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SMSMOEvent"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("keyword");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Keyword"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "BaseMOKeyword"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mobileTelephoneNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MobileTelephoneNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MOCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MOCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("eventDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "EventDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MOMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MOMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MTMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MTMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("carrier");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Carrier"));
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
