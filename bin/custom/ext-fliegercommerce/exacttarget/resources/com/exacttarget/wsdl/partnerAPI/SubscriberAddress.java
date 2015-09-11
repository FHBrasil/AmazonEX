/**
 * SubscriberAddress.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class SubscriberAddress  implements java.io.Serializable {
    private java.lang.String addressType;

    private java.lang.String address;

    private com.exacttarget.wsdl.partnerAPI.AddressStatus[] statuses;

    public SubscriberAddress() {
    }

    public SubscriberAddress(
           java.lang.String addressType,
           java.lang.String address,
           com.exacttarget.wsdl.partnerAPI.AddressStatus[] statuses) {
           this.addressType = addressType;
           this.address = address;
           this.statuses = statuses;
    }


    /**
     * Gets the addressType value for this SubscriberAddress.
     * 
     * @return addressType
     */
    public java.lang.String getAddressType() {
        return addressType;
    }


    /**
     * Sets the addressType value for this SubscriberAddress.
     * 
     * @param addressType
     */
    public void setAddressType(java.lang.String addressType) {
        this.addressType = addressType;
    }


    /**
     * Gets the address value for this SubscriberAddress.
     * 
     * @return address
     */
    public java.lang.String getAddress() {
        return address;
    }


    /**
     * Sets the address value for this SubscriberAddress.
     * 
     * @param address
     */
    public void setAddress(java.lang.String address) {
        this.address = address;
    }


    /**
     * Gets the statuses value for this SubscriberAddress.
     * 
     * @return statuses
     */
    public com.exacttarget.wsdl.partnerAPI.AddressStatus[] getStatuses() {
        return statuses;
    }


    /**
     * Sets the statuses value for this SubscriberAddress.
     * 
     * @param statuses
     */
    public void setStatuses(com.exacttarget.wsdl.partnerAPI.AddressStatus[] statuses) {
        this.statuses = statuses;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SubscriberAddress)) return false;
        SubscriberAddress other = (SubscriberAddress) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.addressType==null && other.getAddressType()==null) || 
             (this.addressType!=null &&
              this.addressType.equals(other.getAddressType()))) &&
            ((this.address==null && other.getAddress()==null) || 
             (this.address!=null &&
              this.address.equals(other.getAddress()))) &&
            ((this.statuses==null && other.getStatuses()==null) || 
             (this.statuses!=null &&
              java.util.Arrays.equals(this.statuses, other.getStatuses())));
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
        if (getAddressType() != null) {
            _hashCode += getAddressType().hashCode();
        }
        if (getAddress() != null) {
            _hashCode += getAddress().hashCode();
        }
        if (getStatuses() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getStatuses());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getStatuses(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SubscriberAddress.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SubscriberAddress"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addressType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AddressType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("address");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Address"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statuses");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Statuses"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AddressStatus"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Status"));
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
