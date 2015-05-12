/**
 * StoreTokenRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.adyen.services.recurring;

public class StoreTokenRequest  implements java.io.Serializable {
    private com.adyen.services.payment.BankAccount bank;

    private com.adyen.services.payment.Card card;

    private com.adyen.services.payment.ELV elv;

    private java.lang.String merchantAccount;

    private java.lang.String name;

    private com.adyen.services.payment.Recurring recurring;

    private java.lang.String shopperEmail;

    private java.lang.String shopperReference;

    public StoreTokenRequest() {
    }

    public StoreTokenRequest(
           com.adyen.services.payment.BankAccount bank,
           com.adyen.services.payment.Card card,
           com.adyen.services.payment.ELV elv,
           java.lang.String merchantAccount,
           java.lang.String name,
           com.adyen.services.payment.Recurring recurring,
           java.lang.String shopperEmail,
           java.lang.String shopperReference) {
           this.bank = bank;
           this.card = card;
           this.elv = elv;
           this.merchantAccount = merchantAccount;
           this.name = name;
           this.recurring = recurring;
           this.shopperEmail = shopperEmail;
           this.shopperReference = shopperReference;
    }


    /**
     * Gets the bank value for this StoreTokenRequest.
     * 
     * @return bank
     */
    public com.adyen.services.payment.BankAccount getBank() {
        return bank;
    }


    /**
     * Sets the bank value for this StoreTokenRequest.
     * 
     * @param bank
     */
    public void setBank(com.adyen.services.payment.BankAccount bank) {
        this.bank = bank;
    }


    /**
     * Gets the card value for this StoreTokenRequest.
     * 
     * @return card
     */
    public com.adyen.services.payment.Card getCard() {
        return card;
    }


    /**
     * Sets the card value for this StoreTokenRequest.
     * 
     * @param card
     */
    public void setCard(com.adyen.services.payment.Card card) {
        this.card = card;
    }


    /**
     * Gets the elv value for this StoreTokenRequest.
     * 
     * @return elv
     */
    public com.adyen.services.payment.ELV getElv() {
        return elv;
    }


    /**
     * Sets the elv value for this StoreTokenRequest.
     * 
     * @param elv
     */
    public void setElv(com.adyen.services.payment.ELV elv) {
        this.elv = elv;
    }


    /**
     * Gets the merchantAccount value for this StoreTokenRequest.
     * 
     * @return merchantAccount
     */
    public java.lang.String getMerchantAccount() {
        return merchantAccount;
    }


    /**
     * Sets the merchantAccount value for this StoreTokenRequest.
     * 
     * @param merchantAccount
     */
    public void setMerchantAccount(java.lang.String merchantAccount) {
        this.merchantAccount = merchantAccount;
    }


    /**
     * Gets the name value for this StoreTokenRequest.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this StoreTokenRequest.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the recurring value for this StoreTokenRequest.
     * 
     * @return recurring
     */
    public com.adyen.services.payment.Recurring getRecurring() {
        return recurring;
    }


    /**
     * Sets the recurring value for this StoreTokenRequest.
     * 
     * @param recurring
     */
    public void setRecurring(com.adyen.services.payment.Recurring recurring) {
        this.recurring = recurring;
    }


    /**
     * Gets the shopperEmail value for this StoreTokenRequest.
     * 
     * @return shopperEmail
     */
    public java.lang.String getShopperEmail() {
        return shopperEmail;
    }


    /**
     * Sets the shopperEmail value for this StoreTokenRequest.
     * 
     * @param shopperEmail
     */
    public void setShopperEmail(java.lang.String shopperEmail) {
        this.shopperEmail = shopperEmail;
    }


    /**
     * Gets the shopperReference value for this StoreTokenRequest.
     * 
     * @return shopperReference
     */
    public java.lang.String getShopperReference() {
        return shopperReference;
    }


    /**
     * Sets the shopperReference value for this StoreTokenRequest.
     * 
     * @param shopperReference
     */
    public void setShopperReference(java.lang.String shopperReference) {
        this.shopperReference = shopperReference;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof StoreTokenRequest)) return false;
        StoreTokenRequest other = (StoreTokenRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.bank==null && other.getBank()==null) || 
             (this.bank!=null &&
              this.bank.equals(other.getBank()))) &&
            ((this.card==null && other.getCard()==null) || 
             (this.card!=null &&
              this.card.equals(other.getCard()))) &&
            ((this.elv==null && other.getElv()==null) || 
             (this.elv!=null &&
              this.elv.equals(other.getElv()))) &&
            ((this.merchantAccount==null && other.getMerchantAccount()==null) || 
             (this.merchantAccount!=null &&
              this.merchantAccount.equals(other.getMerchantAccount()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.recurring==null && other.getRecurring()==null) || 
             (this.recurring!=null &&
              this.recurring.equals(other.getRecurring()))) &&
            ((this.shopperEmail==null && other.getShopperEmail()==null) || 
             (this.shopperEmail!=null &&
              this.shopperEmail.equals(other.getShopperEmail()))) &&
            ((this.shopperReference==null && other.getShopperReference()==null) || 
             (this.shopperReference!=null &&
              this.shopperReference.equals(other.getShopperReference())));
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
        if (getBank() != null) {
            _hashCode += getBank().hashCode();
        }
        if (getCard() != null) {
            _hashCode += getCard().hashCode();
        }
        if (getElv() != null) {
            _hashCode += getElv().hashCode();
        }
        if (getMerchantAccount() != null) {
            _hashCode += getMerchantAccount().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getRecurring() != null) {
            _hashCode += getRecurring().hashCode();
        }
        if (getShopperEmail() != null) {
            _hashCode += getShopperEmail().hashCode();
        }
        if (getShopperReference() != null) {
            _hashCode += getShopperReference().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(StoreTokenRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "StoreTokenRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bank");
        elemField.setXmlName(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "bank"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://payment.services.adyen.com", "BankAccount"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("card");
        elemField.setXmlName(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "card"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://payment.services.adyen.com", "Card"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("elv");
        elemField.setXmlName(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "elv"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://payment.services.adyen.com", "ELV"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("merchantAccount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "merchantAccount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recurring");
        elemField.setXmlName(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "recurring"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://payment.services.adyen.com", "Recurring"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shopperEmail");
        elemField.setXmlName(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "shopperEmail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shopperReference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "shopperReference"));
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
