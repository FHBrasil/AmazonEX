/**
 * Card.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.adyen.services.payment;

public class Card  implements java.io.Serializable {
    private com.adyen.services.common.Address billingAddress;

    private java.lang.String brand;

    private java.lang.String cvc;

    private java.lang.String expiryMonth;

    private java.lang.String expiryYear;

    private java.lang.String holderName;

    private java.lang.String issueNumber;

    private java.lang.String number;

    private java.lang.String startMonth;

    private java.lang.String startYear;

    public Card() {
    }

    public Card(
           com.adyen.services.common.Address billingAddress,
           java.lang.String brand,
           java.lang.String cvc,
           java.lang.String expiryMonth,
           java.lang.String expiryYear,
           java.lang.String holderName,
           java.lang.String issueNumber,
           java.lang.String number,
           java.lang.String startMonth,
           java.lang.String startYear) {
           this.billingAddress = billingAddress;
           this.brand = brand;
           this.cvc = cvc;
           this.expiryMonth = expiryMonth;
           this.expiryYear = expiryYear;
           this.holderName = holderName;
           this.issueNumber = issueNumber;
           this.number = number;
           this.startMonth = startMonth;
           this.startYear = startYear;
    }


    /**
     * Gets the billingAddress value for this Card.
     * 
     * @return billingAddress
     */
    public com.adyen.services.common.Address getBillingAddress() {
        return billingAddress;
    }


    /**
     * Sets the billingAddress value for this Card.
     * 
     * @param billingAddress
     */
    public void setBillingAddress(com.adyen.services.common.Address billingAddress) {
        this.billingAddress = billingAddress;
    }


    /**
     * Gets the brand value for this Card.
     * 
     * @return brand
     */
    public java.lang.String getBrand() {
        return brand;
    }


    /**
     * Sets the brand value for this Card.
     * 
     * @param brand
     */
    public void setBrand(java.lang.String brand) {
        this.brand = brand;
    }


    /**
     * Gets the cvc value for this Card.
     * 
     * @return cvc
     */
    public java.lang.String getCvc() {
        return cvc;
    }


    /**
     * Sets the cvc value for this Card.
     * 
     * @param cvc
     */
    public void setCvc(java.lang.String cvc) {
        this.cvc = cvc;
    }


    /**
     * Gets the expiryMonth value for this Card.
     * 
     * @return expiryMonth
     */
    public java.lang.String getExpiryMonth() {
        return expiryMonth;
    }


    /**
     * Sets the expiryMonth value for this Card.
     * 
     * @param expiryMonth
     */
    public void setExpiryMonth(java.lang.String expiryMonth) {
        this.expiryMonth = expiryMonth;
    }


    /**
     * Gets the expiryYear value for this Card.
     * 
     * @return expiryYear
     */
    public java.lang.String getExpiryYear() {
        return expiryYear;
    }


    /**
     * Sets the expiryYear value for this Card.
     * 
     * @param expiryYear
     */
    public void setExpiryYear(java.lang.String expiryYear) {
        this.expiryYear = expiryYear;
    }


    /**
     * Gets the holderName value for this Card.
     * 
     * @return holderName
     */
    public java.lang.String getHolderName() {
        return holderName;
    }


    /**
     * Sets the holderName value for this Card.
     * 
     * @param holderName
     */
    public void setHolderName(java.lang.String holderName) {
        this.holderName = holderName;
    }


    /**
     * Gets the issueNumber value for this Card.
     * 
     * @return issueNumber
     */
    public java.lang.String getIssueNumber() {
        return issueNumber;
    }


    /**
     * Sets the issueNumber value for this Card.
     * 
     * @param issueNumber
     */
    public void setIssueNumber(java.lang.String issueNumber) {
        this.issueNumber = issueNumber;
    }


    /**
     * Gets the number value for this Card.
     * 
     * @return number
     */
    public java.lang.String getNumber() {
        return number;
    }


    /**
     * Sets the number value for this Card.
     * 
     * @param number
     */
    public void setNumber(java.lang.String number) {
        this.number = number;
    }


    /**
     * Gets the startMonth value for this Card.
     * 
     * @return startMonth
     */
    public java.lang.String getStartMonth() {
        return startMonth;
    }


    /**
     * Sets the startMonth value for this Card.
     * 
     * @param startMonth
     */
    public void setStartMonth(java.lang.String startMonth) {
        this.startMonth = startMonth;
    }


    /**
     * Gets the startYear value for this Card.
     * 
     * @return startYear
     */
    public java.lang.String getStartYear() {
        return startYear;
    }


    /**
     * Sets the startYear value for this Card.
     * 
     * @param startYear
     */
    public void setStartYear(java.lang.String startYear) {
        this.startYear = startYear;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Card)) return false;
        Card other = (Card) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.billingAddress==null && other.getBillingAddress()==null) || 
             (this.billingAddress!=null &&
              this.billingAddress.equals(other.getBillingAddress()))) &&
            ((this.brand==null && other.getBrand()==null) || 
             (this.brand!=null &&
              this.brand.equals(other.getBrand()))) &&
            ((this.cvc==null && other.getCvc()==null) || 
             (this.cvc!=null &&
              this.cvc.equals(other.getCvc()))) &&
            ((this.expiryMonth==null && other.getExpiryMonth()==null) || 
             (this.expiryMonth!=null &&
              this.expiryMonth.equals(other.getExpiryMonth()))) &&
            ((this.expiryYear==null && other.getExpiryYear()==null) || 
             (this.expiryYear!=null &&
              this.expiryYear.equals(other.getExpiryYear()))) &&
            ((this.holderName==null && other.getHolderName()==null) || 
             (this.holderName!=null &&
              this.holderName.equals(other.getHolderName()))) &&
            ((this.issueNumber==null && other.getIssueNumber()==null) || 
             (this.issueNumber!=null &&
              this.issueNumber.equals(other.getIssueNumber()))) &&
            ((this.number==null && other.getNumber()==null) || 
             (this.number!=null &&
              this.number.equals(other.getNumber()))) &&
            ((this.startMonth==null && other.getStartMonth()==null) || 
             (this.startMonth!=null &&
              this.startMonth.equals(other.getStartMonth()))) &&
            ((this.startYear==null && other.getStartYear()==null) || 
             (this.startYear!=null &&
              this.startYear.equals(other.getStartYear())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getBillingAddress() != null) {
            _hashCode += getBillingAddress().hashCode();
        }
        if (getBrand() != null) {
            _hashCode += getBrand().hashCode();
        }
        if (getCvc() != null) {
            _hashCode += getCvc().hashCode();
        }
        if (getExpiryMonth() != null) {
            _hashCode += getExpiryMonth().hashCode();
        }
        if (getExpiryYear() != null) {
            _hashCode += getExpiryYear().hashCode();
        }
        if (getHolderName() != null) {
            _hashCode += getHolderName().hashCode();
        }
        if (getIssueNumber() != null) {
            _hashCode += getIssueNumber().hashCode();
        }
        if (getNumber() != null) {
            _hashCode += getNumber().hashCode();
        }
        if (getStartMonth() != null) {
            _hashCode += getStartMonth().hashCode();
        }
        if (getStartYear() != null) {
            _hashCode += getStartYear().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Card.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://payment.services.adyen.com", "Card"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("billingAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "billingAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://common.services.adyen.com", "Address"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("brand");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "brand"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cvc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "cvc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("expiryMonth");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "expiryMonth"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("expiryYear");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "expiryYear"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("holderName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "holderName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("issueNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "issueNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("number");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "number"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("startMonth");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "startMonth"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("startYear");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "startYear"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
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
