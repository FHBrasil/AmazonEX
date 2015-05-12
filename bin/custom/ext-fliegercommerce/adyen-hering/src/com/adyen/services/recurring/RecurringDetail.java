/**
 * RecurringDetail.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.adyen.services.recurring;

public class RecurringDetail  implements java.io.Serializable {
    private com.adyen.services.payment.BankAccount bank;

    private com.adyen.services.payment.Card card;

    private java.util.Calendar creationDate;

    private com.adyen.services.payment.ELV elv;

    private java.lang.String name;

    private java.lang.String recurringDetailReference;

    private java.lang.String variant;

    public RecurringDetail() {
    }

    public RecurringDetail(
           com.adyen.services.payment.BankAccount bank,
           com.adyen.services.payment.Card card,
           java.util.Calendar creationDate,
           com.adyen.services.payment.ELV elv,
           java.lang.String name,
           java.lang.String recurringDetailReference,
           java.lang.String variant) {
           this.bank = bank;
           this.card = card;
           this.creationDate = creationDate;
           this.elv = elv;
           this.name = name;
           this.recurringDetailReference = recurringDetailReference;
           this.variant = variant;
    }


    /**
     * Gets the bank value for this RecurringDetail.
     * 
     * @return bank
     */
    public com.adyen.services.payment.BankAccount getBank() {
        return bank;
    }


    /**
     * Sets the bank value for this RecurringDetail.
     * 
     * @param bank
     */
    public void setBank(com.adyen.services.payment.BankAccount bank) {
        this.bank = bank;
    }


    /**
     * Gets the card value for this RecurringDetail.
     * 
     * @return card
     */
    public com.adyen.services.payment.Card getCard() {
        return card;
    }


    /**
     * Sets the card value for this RecurringDetail.
     * 
     * @param card
     */
    public void setCard(com.adyen.services.payment.Card card) {
        this.card = card;
    }


    /**
     * Gets the creationDate value for this RecurringDetail.
     * 
     * @return creationDate
     */
    public java.util.Calendar getCreationDate() {
        return creationDate;
    }


    /**
     * Sets the creationDate value for this RecurringDetail.
     * 
     * @param creationDate
     */
    public void setCreationDate(java.util.Calendar creationDate) {
        this.creationDate = creationDate;
    }


    /**
     * Gets the elv value for this RecurringDetail.
     * 
     * @return elv
     */
    public com.adyen.services.payment.ELV getElv() {
        return elv;
    }


    /**
     * Sets the elv value for this RecurringDetail.
     * 
     * @param elv
     */
    public void setElv(com.adyen.services.payment.ELV elv) {
        this.elv = elv;
    }


    /**
     * Gets the name value for this RecurringDetail.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this RecurringDetail.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the recurringDetailReference value for this RecurringDetail.
     * 
     * @return recurringDetailReference
     */
    public java.lang.String getRecurringDetailReference() {
        return recurringDetailReference;
    }


    /**
     * Sets the recurringDetailReference value for this RecurringDetail.
     * 
     * @param recurringDetailReference
     */
    public void setRecurringDetailReference(java.lang.String recurringDetailReference) {
        this.recurringDetailReference = recurringDetailReference;
    }


    /**
     * Gets the variant value for this RecurringDetail.
     * 
     * @return variant
     */
    public java.lang.String getVariant() {
        return variant;
    }


    /**
     * Sets the variant value for this RecurringDetail.
     * 
     * @param variant
     */
    public void setVariant(java.lang.String variant) {
        this.variant = variant;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RecurringDetail)) return false;
        RecurringDetail other = (RecurringDetail) obj;
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
            ((this.creationDate==null && other.getCreationDate()==null) || 
             (this.creationDate!=null &&
              this.creationDate.equals(other.getCreationDate()))) &&
            ((this.elv==null && other.getElv()==null) || 
             (this.elv!=null &&
              this.elv.equals(other.getElv()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.recurringDetailReference==null && other.getRecurringDetailReference()==null) || 
             (this.recurringDetailReference!=null &&
              this.recurringDetailReference.equals(other.getRecurringDetailReference()))) &&
            ((this.variant==null && other.getVariant()==null) || 
             (this.variant!=null &&
              this.variant.equals(other.getVariant())));
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
        if (getCreationDate() != null) {
            _hashCode += getCreationDate().hashCode();
        }
        if (getElv() != null) {
            _hashCode += getElv().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getRecurringDetailReference() != null) {
            _hashCode += getRecurringDetailReference().hashCode();
        }
        if (getVariant() != null) {
            _hashCode += getVariant().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RecurringDetail.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "RecurringDetail"));
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
        elemField.setFieldName("creationDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "creationDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recurringDetailReference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "recurringDetailReference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("variant");
        elemField.setXmlName(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "variant"));
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
