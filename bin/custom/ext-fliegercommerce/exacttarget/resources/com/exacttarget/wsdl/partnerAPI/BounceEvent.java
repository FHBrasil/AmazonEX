/**
 * BounceEvent.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class BounceEvent  extends com.exacttarget.wsdl.partnerAPI.TrackingEvent  implements java.io.Serializable {
    private java.lang.String SMTPCode;

    private java.lang.String bounceCategory;

    private java.lang.String SMTPReason;

    private java.lang.String bounceType;

    public BounceEvent() {
    }

    public BounceEvent(
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
           java.lang.Integer sendID,
           java.lang.String subscriberKey,
           java.util.Calendar eventDate,
           com.exacttarget.wsdl.partnerAPI.EventType eventType,
           java.lang.String triggeredSendDefinitionObjectID,
           java.lang.Integer batchID,
           java.lang.String SMTPCode,
           java.lang.String bounceCategory,
           java.lang.String SMTPReason,
           java.lang.String bounceType) {
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
            sendID,
            subscriberKey,
            eventDate,
            eventType,
            triggeredSendDefinitionObjectID,
            batchID);
        this.SMTPCode = SMTPCode;
        this.bounceCategory = bounceCategory;
        this.SMTPReason = SMTPReason;
        this.bounceType = bounceType;
    }


    /**
     * Gets the SMTPCode value for this BounceEvent.
     * 
     * @return SMTPCode
     */
    public java.lang.String getSMTPCode() {
        return SMTPCode;
    }


    /**
     * Sets the SMTPCode value for this BounceEvent.
     * 
     * @param SMTPCode
     */
    public void setSMTPCode(java.lang.String SMTPCode) {
        this.SMTPCode = SMTPCode;
    }


    /**
     * Gets the bounceCategory value for this BounceEvent.
     * 
     * @return bounceCategory
     */
    public java.lang.String getBounceCategory() {
        return bounceCategory;
    }


    /**
     * Sets the bounceCategory value for this BounceEvent.
     * 
     * @param bounceCategory
     */
    public void setBounceCategory(java.lang.String bounceCategory) {
        this.bounceCategory = bounceCategory;
    }


    /**
     * Gets the SMTPReason value for this BounceEvent.
     * 
     * @return SMTPReason
     */
    public java.lang.String getSMTPReason() {
        return SMTPReason;
    }


    /**
     * Sets the SMTPReason value for this BounceEvent.
     * 
     * @param SMTPReason
     */
    public void setSMTPReason(java.lang.String SMTPReason) {
        this.SMTPReason = SMTPReason;
    }


    /**
     * Gets the bounceType value for this BounceEvent.
     * 
     * @return bounceType
     */
    public java.lang.String getBounceType() {
        return bounceType;
    }


    /**
     * Sets the bounceType value for this BounceEvent.
     * 
     * @param bounceType
     */
    public void setBounceType(java.lang.String bounceType) {
        this.bounceType = bounceType;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BounceEvent)) return false;
        BounceEvent other = (BounceEvent) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.SMTPCode==null && other.getSMTPCode()==null) || 
             (this.SMTPCode!=null &&
              this.SMTPCode.equals(other.getSMTPCode()))) &&
            ((this.bounceCategory==null && other.getBounceCategory()==null) || 
             (this.bounceCategory!=null &&
              this.bounceCategory.equals(other.getBounceCategory()))) &&
            ((this.SMTPReason==null && other.getSMTPReason()==null) || 
             (this.SMTPReason!=null &&
              this.SMTPReason.equals(other.getSMTPReason()))) &&
            ((this.bounceType==null && other.getBounceType()==null) || 
             (this.bounceType!=null &&
              this.bounceType.equals(other.getBounceType())));
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
        if (getSMTPCode() != null) {
            _hashCode += getSMTPCode().hashCode();
        }
        if (getBounceCategory() != null) {
            _hashCode += getBounceCategory().hashCode();
        }
        if (getSMTPReason() != null) {
            _hashCode += getSMTPReason().hashCode();
        }
        if (getBounceType() != null) {
            _hashCode += getBounceType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BounceEvent.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "BounceEvent"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SMTPCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SMTPCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bounceCategory");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "BounceCategory"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SMTPReason");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SMTPReason"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bounceType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "BounceType"));
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
