/**
 * SMSSharedKeyword.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class SMSSharedKeyword  extends com.exacttarget.wsdl.partnerAPI.APIObject  implements java.io.Serializable {
    private java.lang.String shortCode;

    private java.lang.String sharedKeyword;

    private java.util.Calendar requestDate;

    private java.util.Calendar effectiveDate;

    private java.util.Calendar expireDate;

    private java.util.Calendar returnToPoolDate;

    private java.lang.String countryCode;

    public SMSSharedKeyword() {
    }

    public SMSSharedKeyword(
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
           java.lang.String shortCode,
           java.lang.String sharedKeyword,
           java.util.Calendar requestDate,
           java.util.Calendar effectiveDate,
           java.util.Calendar expireDate,
           java.util.Calendar returnToPoolDate,
           java.lang.String countryCode) {
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
        this.shortCode = shortCode;
        this.sharedKeyword = sharedKeyword;
        this.requestDate = requestDate;
        this.effectiveDate = effectiveDate;
        this.expireDate = expireDate;
        this.returnToPoolDate = returnToPoolDate;
        this.countryCode = countryCode;
    }


    /**
     * Gets the shortCode value for this SMSSharedKeyword.
     * 
     * @return shortCode
     */
    public java.lang.String getShortCode() {
        return shortCode;
    }


    /**
     * Sets the shortCode value for this SMSSharedKeyword.
     * 
     * @param shortCode
     */
    public void setShortCode(java.lang.String shortCode) {
        this.shortCode = shortCode;
    }


    /**
     * Gets the sharedKeyword value for this SMSSharedKeyword.
     * 
     * @return sharedKeyword
     */
    public java.lang.String getSharedKeyword() {
        return sharedKeyword;
    }


    /**
     * Sets the sharedKeyword value for this SMSSharedKeyword.
     * 
     * @param sharedKeyword
     */
    public void setSharedKeyword(java.lang.String sharedKeyword) {
        this.sharedKeyword = sharedKeyword;
    }


    /**
     * Gets the requestDate value for this SMSSharedKeyword.
     * 
     * @return requestDate
     */
    public java.util.Calendar getRequestDate() {
        return requestDate;
    }


    /**
     * Sets the requestDate value for this SMSSharedKeyword.
     * 
     * @param requestDate
     */
    public void setRequestDate(java.util.Calendar requestDate) {
        this.requestDate = requestDate;
    }


    /**
     * Gets the effectiveDate value for this SMSSharedKeyword.
     * 
     * @return effectiveDate
     */
    public java.util.Calendar getEffectiveDate() {
        return effectiveDate;
    }


    /**
     * Sets the effectiveDate value for this SMSSharedKeyword.
     * 
     * @param effectiveDate
     */
    public void setEffectiveDate(java.util.Calendar effectiveDate) {
        this.effectiveDate = effectiveDate;
    }


    /**
     * Gets the expireDate value for this SMSSharedKeyword.
     * 
     * @return expireDate
     */
    public java.util.Calendar getExpireDate() {
        return expireDate;
    }


    /**
     * Sets the expireDate value for this SMSSharedKeyword.
     * 
     * @param expireDate
     */
    public void setExpireDate(java.util.Calendar expireDate) {
        this.expireDate = expireDate;
    }


    /**
     * Gets the returnToPoolDate value for this SMSSharedKeyword.
     * 
     * @return returnToPoolDate
     */
    public java.util.Calendar getReturnToPoolDate() {
        return returnToPoolDate;
    }


    /**
     * Sets the returnToPoolDate value for this SMSSharedKeyword.
     * 
     * @param returnToPoolDate
     */
    public void setReturnToPoolDate(java.util.Calendar returnToPoolDate) {
        this.returnToPoolDate = returnToPoolDate;
    }


    /**
     * Gets the countryCode value for this SMSSharedKeyword.
     * 
     * @return countryCode
     */
    public java.lang.String getCountryCode() {
        return countryCode;
    }


    /**
     * Sets the countryCode value for this SMSSharedKeyword.
     * 
     * @param countryCode
     */
    public void setCountryCode(java.lang.String countryCode) {
        this.countryCode = countryCode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SMSSharedKeyword)) return false;
        SMSSharedKeyword other = (SMSSharedKeyword) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.shortCode==null && other.getShortCode()==null) || 
             (this.shortCode!=null &&
              this.shortCode.equals(other.getShortCode()))) &&
            ((this.sharedKeyword==null && other.getSharedKeyword()==null) || 
             (this.sharedKeyword!=null &&
              this.sharedKeyword.equals(other.getSharedKeyword()))) &&
            ((this.requestDate==null && other.getRequestDate()==null) || 
             (this.requestDate!=null &&
              this.requestDate.equals(other.getRequestDate()))) &&
            ((this.effectiveDate==null && other.getEffectiveDate()==null) || 
             (this.effectiveDate!=null &&
              this.effectiveDate.equals(other.getEffectiveDate()))) &&
            ((this.expireDate==null && other.getExpireDate()==null) || 
             (this.expireDate!=null &&
              this.expireDate.equals(other.getExpireDate()))) &&
            ((this.returnToPoolDate==null && other.getReturnToPoolDate()==null) || 
             (this.returnToPoolDate!=null &&
              this.returnToPoolDate.equals(other.getReturnToPoolDate()))) &&
            ((this.countryCode==null && other.getCountryCode()==null) || 
             (this.countryCode!=null &&
              this.countryCode.equals(other.getCountryCode())));
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
        if (getShortCode() != null) {
            _hashCode += getShortCode().hashCode();
        }
        if (getSharedKeyword() != null) {
            _hashCode += getSharedKeyword().hashCode();
        }
        if (getRequestDate() != null) {
            _hashCode += getRequestDate().hashCode();
        }
        if (getEffectiveDate() != null) {
            _hashCode += getEffectiveDate().hashCode();
        }
        if (getExpireDate() != null) {
            _hashCode += getExpireDate().hashCode();
        }
        if (getReturnToPoolDate() != null) {
            _hashCode += getReturnToPoolDate().hashCode();
        }
        if (getCountryCode() != null) {
            _hashCode += getCountryCode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SMSSharedKeyword.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SMSSharedKeyword"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shortCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ShortCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sharedKeyword");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SharedKeyword"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RequestDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("effectiveDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "EffectiveDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("expireDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExpireDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("returnToPoolDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ReturnToPoolDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("countryCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "CountryCode"));
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
