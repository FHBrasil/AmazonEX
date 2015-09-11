/**
 * RecurringDetailsResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.adyen.services.recurring;

public class RecurringDetailsResult  implements java.io.Serializable {
    private java.util.Calendar creationDate;

    private com.adyen.services.recurring.RecurringDetail[] details;

    private java.lang.String lastKnownShopperEmail;

    private java.lang.String shopperReference;

    public RecurringDetailsResult() {
    }

    public RecurringDetailsResult(
           java.util.Calendar creationDate,
           com.adyen.services.recurring.RecurringDetail[] details,
           java.lang.String lastKnownShopperEmail,
           java.lang.String shopperReference) {
           this.creationDate = creationDate;
           this.details = details;
           this.lastKnownShopperEmail = lastKnownShopperEmail;
           this.shopperReference = shopperReference;
    }


    /**
     * Gets the creationDate value for this RecurringDetailsResult.
     * 
     * @return creationDate
     */
    public java.util.Calendar getCreationDate() {
        return creationDate;
    }


    /**
     * Sets the creationDate value for this RecurringDetailsResult.
     * 
     * @param creationDate
     */
    public void setCreationDate(java.util.Calendar creationDate) {
        this.creationDate = creationDate;
    }


    /**
     * Gets the details value for this RecurringDetailsResult.
     * 
     * @return details
     */
    public com.adyen.services.recurring.RecurringDetail[] getDetails() {
        return details;
    }


    /**
     * Sets the details value for this RecurringDetailsResult.
     * 
     * @param details
     */
    public void setDetails(com.adyen.services.recurring.RecurringDetail[] details) {
        this.details = details;
    }


    /**
     * Gets the lastKnownShopperEmail value for this RecurringDetailsResult.
     * 
     * @return lastKnownShopperEmail
     */
    public java.lang.String getLastKnownShopperEmail() {
        return lastKnownShopperEmail;
    }


    /**
     * Sets the lastKnownShopperEmail value for this RecurringDetailsResult.
     * 
     * @param lastKnownShopperEmail
     */
    public void setLastKnownShopperEmail(java.lang.String lastKnownShopperEmail) {
        this.lastKnownShopperEmail = lastKnownShopperEmail;
    }


    /**
     * Gets the shopperReference value for this RecurringDetailsResult.
     * 
     * @return shopperReference
     */
    public java.lang.String getShopperReference() {
        return shopperReference;
    }


    /**
     * Sets the shopperReference value for this RecurringDetailsResult.
     * 
     * @param shopperReference
     */
    public void setShopperReference(java.lang.String shopperReference) {
        this.shopperReference = shopperReference;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RecurringDetailsResult)) return false;
        RecurringDetailsResult other = (RecurringDetailsResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.creationDate==null && other.getCreationDate()==null) || 
             (this.creationDate!=null &&
              this.creationDate.equals(other.getCreationDate()))) &&
            ((this.details==null && other.getDetails()==null) || 
             (this.details!=null &&
              java.util.Arrays.equals(this.details, other.getDetails()))) &&
            ((this.lastKnownShopperEmail==null && other.getLastKnownShopperEmail()==null) || 
             (this.lastKnownShopperEmail!=null &&
              this.lastKnownShopperEmail.equals(other.getLastKnownShopperEmail()))) &&
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
        if (getCreationDate() != null) {
            _hashCode += getCreationDate().hashCode();
        }
        if (getDetails() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDetails());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDetails(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getLastKnownShopperEmail() != null) {
            _hashCode += getLastKnownShopperEmail().hashCode();
        }
        if (getShopperReference() != null) {
            _hashCode += getShopperReference().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RecurringDetailsResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "RecurringDetailsResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creationDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "creationDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("details");
        elemField.setXmlName(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "details"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "RecurringDetail"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "RecurringDetail"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastKnownShopperEmail");
        elemField.setXmlName(new javax.xml.namespace.QName("http://recurring.services.adyen.com", "lastKnownShopperEmail"));
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
