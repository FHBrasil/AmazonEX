/**
 * AccountDataItem.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class AccountDataItem  implements java.io.Serializable {
    private java.lang.Integer childAccountID;

    private java.lang.Integer brandID;

    private java.lang.Integer privateLabelID;

    private java.lang.Integer accountType;

    public AccountDataItem() {
    }

    public AccountDataItem(
           java.lang.Integer childAccountID,
           java.lang.Integer brandID,
           java.lang.Integer privateLabelID,
           java.lang.Integer accountType) {
           this.childAccountID = childAccountID;
           this.brandID = brandID;
           this.privateLabelID = privateLabelID;
           this.accountType = accountType;
    }


    /**
     * Gets the childAccountID value for this AccountDataItem.
     * 
     * @return childAccountID
     */
    public java.lang.Integer getChildAccountID() {
        return childAccountID;
    }


    /**
     * Sets the childAccountID value for this AccountDataItem.
     * 
     * @param childAccountID
     */
    public void setChildAccountID(java.lang.Integer childAccountID) {
        this.childAccountID = childAccountID;
    }


    /**
     * Gets the brandID value for this AccountDataItem.
     * 
     * @return brandID
     */
    public java.lang.Integer getBrandID() {
        return brandID;
    }


    /**
     * Sets the brandID value for this AccountDataItem.
     * 
     * @param brandID
     */
    public void setBrandID(java.lang.Integer brandID) {
        this.brandID = brandID;
    }


    /**
     * Gets the privateLabelID value for this AccountDataItem.
     * 
     * @return privateLabelID
     */
    public java.lang.Integer getPrivateLabelID() {
        return privateLabelID;
    }


    /**
     * Sets the privateLabelID value for this AccountDataItem.
     * 
     * @param privateLabelID
     */
    public void setPrivateLabelID(java.lang.Integer privateLabelID) {
        this.privateLabelID = privateLabelID;
    }


    /**
     * Gets the accountType value for this AccountDataItem.
     * 
     * @return accountType
     */
    public java.lang.Integer getAccountType() {
        return accountType;
    }


    /**
     * Sets the accountType value for this AccountDataItem.
     * 
     * @param accountType
     */
    public void setAccountType(java.lang.Integer accountType) {
        this.accountType = accountType;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AccountDataItem)) return false;
        AccountDataItem other = (AccountDataItem) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.childAccountID==null && other.getChildAccountID()==null) || 
             (this.childAccountID!=null &&
              this.childAccountID.equals(other.getChildAccountID()))) &&
            ((this.brandID==null && other.getBrandID()==null) || 
             (this.brandID!=null &&
              this.brandID.equals(other.getBrandID()))) &&
            ((this.privateLabelID==null && other.getPrivateLabelID()==null) || 
             (this.privateLabelID!=null &&
              this.privateLabelID.equals(other.getPrivateLabelID()))) &&
            ((this.accountType==null && other.getAccountType()==null) || 
             (this.accountType!=null &&
              this.accountType.equals(other.getAccountType())));
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
        if (getChildAccountID() != null) {
            _hashCode += getChildAccountID().hashCode();
        }
        if (getBrandID() != null) {
            _hashCode += getBrandID().hashCode();
        }
        if (getPrivateLabelID() != null) {
            _hashCode += getPrivateLabelID().hashCode();
        }
        if (getAccountType() != null) {
            _hashCode += getAccountType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AccountDataItem.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AccountDataItem"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("childAccountID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ChildAccountID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("brandID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "BrandID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("privateLabelID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PrivateLabelID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AccountType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
