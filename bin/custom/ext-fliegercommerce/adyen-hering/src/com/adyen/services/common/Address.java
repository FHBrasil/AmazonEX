/**
 * Address.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.adyen.services.common;

public class Address  implements java.io.Serializable {
    private java.lang.String city;

    private java.lang.String country;

    private java.lang.String houseNumberOrName;

    private java.lang.String postalCode;

    private java.lang.String stateOrProvince;

    private java.lang.String street;

    public Address() {
    }

    public Address(
           java.lang.String city,
           java.lang.String country,
           java.lang.String houseNumberOrName,
           java.lang.String postalCode,
           java.lang.String stateOrProvince,
           java.lang.String street) {
           this.city = city;
           this.country = country;
           this.houseNumberOrName = houseNumberOrName;
           this.postalCode = postalCode;
           this.stateOrProvince = stateOrProvince;
           this.street = street;
    }


    /**
     * Gets the city value for this Address.
     * 
     * @return city
     */
    public java.lang.String getCity() {
        return city;
    }


    /**
     * Sets the city value for this Address.
     * 
     * @param city
     */
    public void setCity(java.lang.String city) {
        this.city = city;
    }


    /**
     * Gets the country value for this Address.
     * 
     * @return country
     */
    public java.lang.String getCountry() {
        return country;
    }


    /**
     * Sets the country value for this Address.
     * 
     * @param country
     */
    public void setCountry(java.lang.String country) {
        this.country = country;
    }


    /**
     * Gets the houseNumberOrName value for this Address.
     * 
     * @return houseNumberOrName
     */
    public java.lang.String getHouseNumberOrName() {
        return houseNumberOrName;
    }


    /**
     * Sets the houseNumberOrName value for this Address.
     * 
     * @param houseNumberOrName
     */
    public void setHouseNumberOrName(java.lang.String houseNumberOrName) {
        this.houseNumberOrName = houseNumberOrName;
    }


    /**
     * Gets the postalCode value for this Address.
     * 
     * @return postalCode
     */
    public java.lang.String getPostalCode() {
        return postalCode;
    }


    /**
     * Sets the postalCode value for this Address.
     * 
     * @param postalCode
     */
    public void setPostalCode(java.lang.String postalCode) {
        this.postalCode = postalCode;
    }


    /**
     * Gets the stateOrProvince value for this Address.
     * 
     * @return stateOrProvince
     */
    public java.lang.String getStateOrProvince() {
        return stateOrProvince;
    }


    /**
     * Sets the stateOrProvince value for this Address.
     * 
     * @param stateOrProvince
     */
    public void setStateOrProvince(java.lang.String stateOrProvince) {
        this.stateOrProvince = stateOrProvince;
    }


    /**
     * Gets the street value for this Address.
     * 
     * @return street
     */
    public java.lang.String getStreet() {
        return street;
    }


    /**
     * Sets the street value for this Address.
     * 
     * @param street
     */
    public void setStreet(java.lang.String street) {
        this.street = street;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Address)) return false;
        Address other = (Address) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.city==null && other.getCity()==null) || 
             (this.city!=null &&
              this.city.equals(other.getCity()))) &&
            ((this.country==null && other.getCountry()==null) || 
             (this.country!=null &&
              this.country.equals(other.getCountry()))) &&
            ((this.houseNumberOrName==null && other.getHouseNumberOrName()==null) || 
             (this.houseNumberOrName!=null &&
              this.houseNumberOrName.equals(other.getHouseNumberOrName()))) &&
            ((this.postalCode==null && other.getPostalCode()==null) || 
             (this.postalCode!=null &&
              this.postalCode.equals(other.getPostalCode()))) &&
            ((this.stateOrProvince==null && other.getStateOrProvince()==null) || 
             (this.stateOrProvince!=null &&
              this.stateOrProvince.equals(other.getStateOrProvince()))) &&
            ((this.street==null && other.getStreet()==null) || 
             (this.street!=null &&
              this.street.equals(other.getStreet())));
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
        if (getCity() != null) {
            _hashCode += getCity().hashCode();
        }
        if (getCountry() != null) {
            _hashCode += getCountry().hashCode();
        }
        if (getHouseNumberOrName() != null) {
            _hashCode += getHouseNumberOrName().hashCode();
        }
        if (getPostalCode() != null) {
            _hashCode += getPostalCode().hashCode();
        }
        if (getStateOrProvince() != null) {
            _hashCode += getStateOrProvince().hashCode();
        }
        if (getStreet() != null) {
            _hashCode += getStreet().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Address.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://common.services.adyen.com", "Address"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("city");
        elemField.setXmlName(new javax.xml.namespace.QName("http://common.services.adyen.com", "city"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("country");
        elemField.setXmlName(new javax.xml.namespace.QName("http://common.services.adyen.com", "country"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("houseNumberOrName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://common.services.adyen.com", "houseNumberOrName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("postalCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://common.services.adyen.com", "postalCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stateOrProvince");
        elemField.setXmlName(new javax.xml.namespace.QName("http://common.services.adyen.com", "stateOrProvince"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("street");
        elemField.setXmlName(new javax.xml.namespace.QName("http://common.services.adyen.com", "street"));
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
