/**
 * ELV.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.adyen.services.payment;

public class ELV  implements java.io.Serializable {
    private java.lang.String accountHolderName;

    private java.lang.String bankAccountNumber;

    private java.lang.String bankLocation;

    private java.lang.String bankLocationId;

    private java.lang.String bankName;

    public ELV() {
    }

    public ELV(
           java.lang.String accountHolderName,
           java.lang.String bankAccountNumber,
           java.lang.String bankLocation,
           java.lang.String bankLocationId,
           java.lang.String bankName) {
           this.accountHolderName = accountHolderName;
           this.bankAccountNumber = bankAccountNumber;
           this.bankLocation = bankLocation;
           this.bankLocationId = bankLocationId;
           this.bankName = bankName;
    }


    /**
     * Gets the accountHolderName value for this ELV.
     * 
     * @return accountHolderName
     */
    public java.lang.String getAccountHolderName() {
        return accountHolderName;
    }


    /**
     * Sets the accountHolderName value for this ELV.
     * 
     * @param accountHolderName
     */
    public void setAccountHolderName(java.lang.String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }


    /**
     * Gets the bankAccountNumber value for this ELV.
     * 
     * @return bankAccountNumber
     */
    public java.lang.String getBankAccountNumber() {
        return bankAccountNumber;
    }


    /**
     * Sets the bankAccountNumber value for this ELV.
     * 
     * @param bankAccountNumber
     */
    public void setBankAccountNumber(java.lang.String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }


    /**
     * Gets the bankLocation value for this ELV.
     * 
     * @return bankLocation
     */
    public java.lang.String getBankLocation() {
        return bankLocation;
    }


    /**
     * Sets the bankLocation value for this ELV.
     * 
     * @param bankLocation
     */
    public void setBankLocation(java.lang.String bankLocation) {
        this.bankLocation = bankLocation;
    }


    /**
     * Gets the bankLocationId value for this ELV.
     * 
     * @return bankLocationId
     */
    public java.lang.String getBankLocationId() {
        return bankLocationId;
    }


    /**
     * Sets the bankLocationId value for this ELV.
     * 
     * @param bankLocationId
     */
    public void setBankLocationId(java.lang.String bankLocationId) {
        this.bankLocationId = bankLocationId;
    }


    /**
     * Gets the bankName value for this ELV.
     * 
     * @return bankName
     */
    public java.lang.String getBankName() {
        return bankName;
    }


    /**
     * Sets the bankName value for this ELV.
     * 
     * @param bankName
     */
    public void setBankName(java.lang.String bankName) {
        this.bankName = bankName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ELV)) return false;
        ELV other = (ELV) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.accountHolderName==null && other.getAccountHolderName()==null) || 
             (this.accountHolderName!=null &&
              this.accountHolderName.equals(other.getAccountHolderName()))) &&
            ((this.bankAccountNumber==null && other.getBankAccountNumber()==null) || 
             (this.bankAccountNumber!=null &&
              this.bankAccountNumber.equals(other.getBankAccountNumber()))) &&
            ((this.bankLocation==null && other.getBankLocation()==null) || 
             (this.bankLocation!=null &&
              this.bankLocation.equals(other.getBankLocation()))) &&
            ((this.bankLocationId==null && other.getBankLocationId()==null) || 
             (this.bankLocationId!=null &&
              this.bankLocationId.equals(other.getBankLocationId()))) &&
            ((this.bankName==null && other.getBankName()==null) || 
             (this.bankName!=null &&
              this.bankName.equals(other.getBankName())));
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
        if (getAccountHolderName() != null) {
            _hashCode += getAccountHolderName().hashCode();
        }
        if (getBankAccountNumber() != null) {
            _hashCode += getBankAccountNumber().hashCode();
        }
        if (getBankLocation() != null) {
            _hashCode += getBankLocation().hashCode();
        }
        if (getBankLocationId() != null) {
            _hashCode += getBankLocationId().hashCode();
        }
        if (getBankName() != null) {
            _hashCode += getBankName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ELV.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://payment.services.adyen.com", "ELV"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountHolderName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "accountHolderName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bankAccountNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "bankAccountNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bankLocation");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "bankLocation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bankLocationId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "bankLocationId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bankName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "bankName"));
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
