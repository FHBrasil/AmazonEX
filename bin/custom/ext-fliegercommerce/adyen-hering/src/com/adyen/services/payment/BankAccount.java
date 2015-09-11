/**
 * BankAccount.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.adyen.services.payment;

public class BankAccount  implements java.io.Serializable {
    private java.lang.String bankAccountNumber;

    private java.lang.String bankLocationId;

    private java.lang.String bankName;

    private java.lang.String bic;

    private java.lang.String countryCode;

    private java.lang.String iban;

    private java.lang.String ownerName;

    public BankAccount() {
    }

    public BankAccount(
           java.lang.String bankAccountNumber,
           java.lang.String bankLocationId,
           java.lang.String bankName,
           java.lang.String bic,
           java.lang.String countryCode,
           java.lang.String iban,
           java.lang.String ownerName) {
           this.bankAccountNumber = bankAccountNumber;
           this.bankLocationId = bankLocationId;
           this.bankName = bankName;
           this.bic = bic;
           this.countryCode = countryCode;
           this.iban = iban;
           this.ownerName = ownerName;
    }


    /**
     * Gets the bankAccountNumber value for this BankAccount.
     * 
     * @return bankAccountNumber
     */
    public java.lang.String getBankAccountNumber() {
        return bankAccountNumber;
    }


    /**
     * Sets the bankAccountNumber value for this BankAccount.
     * 
     * @param bankAccountNumber
     */
    public void setBankAccountNumber(java.lang.String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }


    /**
     * Gets the bankLocationId value for this BankAccount.
     * 
     * @return bankLocationId
     */
    public java.lang.String getBankLocationId() {
        return bankLocationId;
    }


    /**
     * Sets the bankLocationId value for this BankAccount.
     * 
     * @param bankLocationId
     */
    public void setBankLocationId(java.lang.String bankLocationId) {
        this.bankLocationId = bankLocationId;
    }


    /**
     * Gets the bankName value for this BankAccount.
     * 
     * @return bankName
     */
    public java.lang.String getBankName() {
        return bankName;
    }


    /**
     * Sets the bankName value for this BankAccount.
     * 
     * @param bankName
     */
    public void setBankName(java.lang.String bankName) {
        this.bankName = bankName;
    }


    /**
     * Gets the bic value for this BankAccount.
     * 
     * @return bic
     */
    public java.lang.String getBic() {
        return bic;
    }


    /**
     * Sets the bic value for this BankAccount.
     * 
     * @param bic
     */
    public void setBic(java.lang.String bic) {
        this.bic = bic;
    }


    /**
     * Gets the countryCode value for this BankAccount.
     * 
     * @return countryCode
     */
    public java.lang.String getCountryCode() {
        return countryCode;
    }


    /**
     * Sets the countryCode value for this BankAccount.
     * 
     * @param countryCode
     */
    public void setCountryCode(java.lang.String countryCode) {
        this.countryCode = countryCode;
    }


    /**
     * Gets the iban value for this BankAccount.
     * 
     * @return iban
     */
    public java.lang.String getIban() {
        return iban;
    }


    /**
     * Sets the iban value for this BankAccount.
     * 
     * @param iban
     */
    public void setIban(java.lang.String iban) {
        this.iban = iban;
    }


    /**
     * Gets the ownerName value for this BankAccount.
     * 
     * @return ownerName
     */
    public java.lang.String getOwnerName() {
        return ownerName;
    }


    /**
     * Sets the ownerName value for this BankAccount.
     * 
     * @param ownerName
     */
    public void setOwnerName(java.lang.String ownerName) {
        this.ownerName = ownerName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BankAccount)) return false;
        BankAccount other = (BankAccount) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.bankAccountNumber==null && other.getBankAccountNumber()==null) || 
             (this.bankAccountNumber!=null &&
              this.bankAccountNumber.equals(other.getBankAccountNumber()))) &&
            ((this.bankLocationId==null && other.getBankLocationId()==null) || 
             (this.bankLocationId!=null &&
              this.bankLocationId.equals(other.getBankLocationId()))) &&
            ((this.bankName==null && other.getBankName()==null) || 
             (this.bankName!=null &&
              this.bankName.equals(other.getBankName()))) &&
            ((this.bic==null && other.getBic()==null) || 
             (this.bic!=null &&
              this.bic.equals(other.getBic()))) &&
            ((this.countryCode==null && other.getCountryCode()==null) || 
             (this.countryCode!=null &&
              this.countryCode.equals(other.getCountryCode()))) &&
            ((this.iban==null && other.getIban()==null) || 
             (this.iban!=null &&
              this.iban.equals(other.getIban()))) &&
            ((this.ownerName==null && other.getOwnerName()==null) || 
             (this.ownerName!=null &&
              this.ownerName.equals(other.getOwnerName())));
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
        if (getBankAccountNumber() != null) {
            _hashCode += getBankAccountNumber().hashCode();
        }
        if (getBankLocationId() != null) {
            _hashCode += getBankLocationId().hashCode();
        }
        if (getBankName() != null) {
            _hashCode += getBankName().hashCode();
        }
        if (getBic() != null) {
            _hashCode += getBic().hashCode();
        }
        if (getCountryCode() != null) {
            _hashCode += getCountryCode().hashCode();
        }
        if (getIban() != null) {
            _hashCode += getIban().hashCode();
        }
        if (getOwnerName() != null) {
            _hashCode += getOwnerName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BankAccount.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://payment.services.adyen.com", "BankAccount"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bankAccountNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "bankAccountNumber"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bic");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "bic"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("countryCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "countryCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("iban");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "iban"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ownerName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "ownerName"));
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
