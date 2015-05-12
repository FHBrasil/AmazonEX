/**
 * ForexQuote.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.adyen.services.payment;

public class ForexQuote  implements java.io.Serializable {
    private java.lang.String account;

    private java.lang.String accountType;

    private com.adyen.services.common.Amount baseAmount;

    private java.lang.Integer basePoints;

    private com.adyen.services.common.Amount buy;

    private com.adyen.services.common.Amount interbank;

    private java.lang.String reference;

    private com.adyen.services.common.Amount sell;

    private java.lang.String signature;

    private java.lang.String source;

    private java.lang.String type;

    private java.util.Calendar validTill;

    public ForexQuote() {
    }

    public ForexQuote(
           java.lang.String account,
           java.lang.String accountType,
           com.adyen.services.common.Amount baseAmount,
           java.lang.Integer basePoints,
           com.adyen.services.common.Amount buy,
           com.adyen.services.common.Amount interbank,
           java.lang.String reference,
           com.adyen.services.common.Amount sell,
           java.lang.String signature,
           java.lang.String source,
           java.lang.String type,
           java.util.Calendar validTill) {
           this.account = account;
           this.accountType = accountType;
           this.baseAmount = baseAmount;
           this.basePoints = basePoints;
           this.buy = buy;
           this.interbank = interbank;
           this.reference = reference;
           this.sell = sell;
           this.signature = signature;
           this.source = source;
           this.type = type;
           this.validTill = validTill;
    }


    /**
     * Gets the account value for this ForexQuote.
     * 
     * @return account
     */
    public java.lang.String getAccount() {
        return account;
    }


    /**
     * Sets the account value for this ForexQuote.
     * 
     * @param account
     */
    public void setAccount(java.lang.String account) {
        this.account = account;
    }


    /**
     * Gets the accountType value for this ForexQuote.
     * 
     * @return accountType
     */
    public java.lang.String getAccountType() {
        return accountType;
    }


    /**
     * Sets the accountType value for this ForexQuote.
     * 
     * @param accountType
     */
    public void setAccountType(java.lang.String accountType) {
        this.accountType = accountType;
    }


    /**
     * Gets the baseAmount value for this ForexQuote.
     * 
     * @return baseAmount
     */
    public com.adyen.services.common.Amount getBaseAmount() {
        return baseAmount;
    }


    /**
     * Sets the baseAmount value for this ForexQuote.
     * 
     * @param baseAmount
     */
    public void setBaseAmount(com.adyen.services.common.Amount baseAmount) {
        this.baseAmount = baseAmount;
    }


    /**
     * Gets the basePoints value for this ForexQuote.
     * 
     * @return basePoints
     */
    public java.lang.Integer getBasePoints() {
        return basePoints;
    }


    /**
     * Sets the basePoints value for this ForexQuote.
     * 
     * @param basePoints
     */
    public void setBasePoints(java.lang.Integer basePoints) {
        this.basePoints = basePoints;
    }


    /**
     * Gets the buy value for this ForexQuote.
     * 
     * @return buy
     */
    public com.adyen.services.common.Amount getBuy() {
        return buy;
    }


    /**
     * Sets the buy value for this ForexQuote.
     * 
     * @param buy
     */
    public void setBuy(com.adyen.services.common.Amount buy) {
        this.buy = buy;
    }


    /**
     * Gets the interbank value for this ForexQuote.
     * 
     * @return interbank
     */
    public com.adyen.services.common.Amount getInterbank() {
        return interbank;
    }


    /**
     * Sets the interbank value for this ForexQuote.
     * 
     * @param interbank
     */
    public void setInterbank(com.adyen.services.common.Amount interbank) {
        this.interbank = interbank;
    }


    /**
     * Gets the reference value for this ForexQuote.
     * 
     * @return reference
     */
    public java.lang.String getReference() {
        return reference;
    }


    /**
     * Sets the reference value for this ForexQuote.
     * 
     * @param reference
     */
    public void setReference(java.lang.String reference) {
        this.reference = reference;
    }


    /**
     * Gets the sell value for this ForexQuote.
     * 
     * @return sell
     */
    public com.adyen.services.common.Amount getSell() {
        return sell;
    }


    /**
     * Sets the sell value for this ForexQuote.
     * 
     * @param sell
     */
    public void setSell(com.adyen.services.common.Amount sell) {
        this.sell = sell;
    }


    /**
     * Gets the signature value for this ForexQuote.
     * 
     * @return signature
     */
    public java.lang.String getSignature() {
        return signature;
    }


    /**
     * Sets the signature value for this ForexQuote.
     * 
     * @param signature
     */
    public void setSignature(java.lang.String signature) {
        this.signature = signature;
    }


    /**
     * Gets the source value for this ForexQuote.
     * 
     * @return source
     */
    public java.lang.String getSource() {
        return source;
    }


    /**
     * Sets the source value for this ForexQuote.
     * 
     * @param source
     */
    public void setSource(java.lang.String source) {
        this.source = source;
    }


    /**
     * Gets the type value for this ForexQuote.
     * 
     * @return type
     */
    public java.lang.String getType() {
        return type;
    }


    /**
     * Sets the type value for this ForexQuote.
     * 
     * @param type
     */
    public void setType(java.lang.String type) {
        this.type = type;
    }


    /**
     * Gets the validTill value for this ForexQuote.
     * 
     * @return validTill
     */
    public java.util.Calendar getValidTill() {
        return validTill;
    }


    /**
     * Sets the validTill value for this ForexQuote.
     * 
     * @param validTill
     */
    public void setValidTill(java.util.Calendar validTill) {
        this.validTill = validTill;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ForexQuote)) return false;
        ForexQuote other = (ForexQuote) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.account==null && other.getAccount()==null) || 
             (this.account!=null &&
              this.account.equals(other.getAccount()))) &&
            ((this.accountType==null && other.getAccountType()==null) || 
             (this.accountType!=null &&
              this.accountType.equals(other.getAccountType()))) &&
            ((this.baseAmount==null && other.getBaseAmount()==null) || 
             (this.baseAmount!=null &&
              this.baseAmount.equals(other.getBaseAmount()))) &&
            ((this.basePoints==null && other.getBasePoints()==null) || 
             (this.basePoints!=null &&
              this.basePoints.equals(other.getBasePoints()))) &&
            ((this.buy==null && other.getBuy()==null) || 
             (this.buy!=null &&
              this.buy.equals(other.getBuy()))) &&
            ((this.interbank==null && other.getInterbank()==null) || 
             (this.interbank!=null &&
              this.interbank.equals(other.getInterbank()))) &&
            ((this.reference==null && other.getReference()==null) || 
             (this.reference!=null &&
              this.reference.equals(other.getReference()))) &&
            ((this.sell==null && other.getSell()==null) || 
             (this.sell!=null &&
              this.sell.equals(other.getSell()))) &&
            ((this.signature==null && other.getSignature()==null) || 
             (this.signature!=null &&
              this.signature.equals(other.getSignature()))) &&
            ((this.source==null && other.getSource()==null) || 
             (this.source!=null &&
              this.source.equals(other.getSource()))) &&
            ((this.type==null && other.getType()==null) || 
             (this.type!=null &&
              this.type.equals(other.getType()))) &&
            ((this.validTill==null && other.getValidTill()==null) || 
             (this.validTill!=null &&
              this.validTill.equals(other.getValidTill())));
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
        if (getAccount() != null) {
            _hashCode += getAccount().hashCode();
        }
        if (getAccountType() != null) {
            _hashCode += getAccountType().hashCode();
        }
        if (getBaseAmount() != null) {
            _hashCode += getBaseAmount().hashCode();
        }
        if (getBasePoints() != null) {
            _hashCode += getBasePoints().hashCode();
        }
        if (getBuy() != null) {
            _hashCode += getBuy().hashCode();
        }
        if (getInterbank() != null) {
            _hashCode += getInterbank().hashCode();
        }
        if (getReference() != null) {
            _hashCode += getReference().hashCode();
        }
        if (getSell() != null) {
            _hashCode += getSell().hashCode();
        }
        if (getSignature() != null) {
            _hashCode += getSignature().hashCode();
        }
        if (getSource() != null) {
            _hashCode += getSource().hashCode();
        }
        if (getType() != null) {
            _hashCode += getType().hashCode();
        }
        if (getValidTill() != null) {
            _hashCode += getValidTill().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ForexQuote.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://payment.services.adyen.com", "ForexQuote"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("account");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "account"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "accountType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("baseAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "baseAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://common.services.adyen.com", "Amount"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("basePoints");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "basePoints"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("buy");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "buy"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://common.services.adyen.com", "Amount"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("interbank");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "interbank"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://common.services.adyen.com", "Amount"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "reference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sell");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "sell"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://common.services.adyen.com", "Amount"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("signature");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "signature"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("source");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "source"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("type");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("validTill");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "validTill"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
