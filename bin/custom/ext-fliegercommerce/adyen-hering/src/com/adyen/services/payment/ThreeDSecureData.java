/**
 * ThreeDSecureData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.adyen.services.payment;

public class ThreeDSecureData  implements java.io.Serializable {
    private java.lang.String authenticationResponse;

    private byte[] cavv;

    private java.lang.String cavvAlgorithm;

    private java.lang.String directoryResponse;

    private java.lang.String eci;

    private byte[] xid;

    public ThreeDSecureData() {
    }

    public ThreeDSecureData(
           java.lang.String authenticationResponse,
           byte[] cavv,
           java.lang.String cavvAlgorithm,
           java.lang.String directoryResponse,
           java.lang.String eci,
           byte[] xid) {
           this.authenticationResponse = authenticationResponse;
           this.cavv = cavv;
           this.cavvAlgorithm = cavvAlgorithm;
           this.directoryResponse = directoryResponse;
           this.eci = eci;
           this.xid = xid;
    }


    /**
     * Gets the authenticationResponse value for this ThreeDSecureData.
     * 
     * @return authenticationResponse
     */
    public java.lang.String getAuthenticationResponse() {
        return authenticationResponse;
    }


    /**
     * Sets the authenticationResponse value for this ThreeDSecureData.
     * 
     * @param authenticationResponse
     */
    public void setAuthenticationResponse(java.lang.String authenticationResponse) {
        this.authenticationResponse = authenticationResponse;
    }


    /**
     * Gets the cavv value for this ThreeDSecureData.
     * 
     * @return cavv
     */
    public byte[] getCavv() {
        return cavv;
    }


    /**
     * Sets the cavv value for this ThreeDSecureData.
     * 
     * @param cavv
     */
    public void setCavv(byte[] cavv) {
        this.cavv = cavv;
    }


    /**
     * Gets the cavvAlgorithm value for this ThreeDSecureData.
     * 
     * @return cavvAlgorithm
     */
    public java.lang.String getCavvAlgorithm() {
        return cavvAlgorithm;
    }


    /**
     * Sets the cavvAlgorithm value for this ThreeDSecureData.
     * 
     * @param cavvAlgorithm
     */
    public void setCavvAlgorithm(java.lang.String cavvAlgorithm) {
        this.cavvAlgorithm = cavvAlgorithm;
    }


    /**
     * Gets the directoryResponse value for this ThreeDSecureData.
     * 
     * @return directoryResponse
     */
    public java.lang.String getDirectoryResponse() {
        return directoryResponse;
    }


    /**
     * Sets the directoryResponse value for this ThreeDSecureData.
     * 
     * @param directoryResponse
     */
    public void setDirectoryResponse(java.lang.String directoryResponse) {
        this.directoryResponse = directoryResponse;
    }


    /**
     * Gets the eci value for this ThreeDSecureData.
     * 
     * @return eci
     */
    public java.lang.String getEci() {
        return eci;
    }


    /**
     * Sets the eci value for this ThreeDSecureData.
     * 
     * @param eci
     */
    public void setEci(java.lang.String eci) {
        this.eci = eci;
    }


    /**
     * Gets the xid value for this ThreeDSecureData.
     * 
     * @return xid
     */
    public byte[] getXid() {
        return xid;
    }


    /**
     * Sets the xid value for this ThreeDSecureData.
     * 
     * @param xid
     */
    public void setXid(byte[] xid) {
        this.xid = xid;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ThreeDSecureData)) return false;
        ThreeDSecureData other = (ThreeDSecureData) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.authenticationResponse==null && other.getAuthenticationResponse()==null) || 
             (this.authenticationResponse!=null &&
              this.authenticationResponse.equals(other.getAuthenticationResponse()))) &&
            ((this.cavv==null && other.getCavv()==null) || 
             (this.cavv!=null &&
              java.util.Arrays.equals(this.cavv, other.getCavv()))) &&
            ((this.cavvAlgorithm==null && other.getCavvAlgorithm()==null) || 
             (this.cavvAlgorithm!=null &&
              this.cavvAlgorithm.equals(other.getCavvAlgorithm()))) &&
            ((this.directoryResponse==null && other.getDirectoryResponse()==null) || 
             (this.directoryResponse!=null &&
              this.directoryResponse.equals(other.getDirectoryResponse()))) &&
            ((this.eci==null && other.getEci()==null) || 
             (this.eci!=null &&
              this.eci.equals(other.getEci()))) &&
            ((this.xid==null && other.getXid()==null) || 
             (this.xid!=null &&
              java.util.Arrays.equals(this.xid, other.getXid())));
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
        if (getAuthenticationResponse() != null) {
            _hashCode += getAuthenticationResponse().hashCode();
        }
        if (getCavv() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCavv());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCavv(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCavvAlgorithm() != null) {
            _hashCode += getCavvAlgorithm().hashCode();
        }
        if (getDirectoryResponse() != null) {
            _hashCode += getDirectoryResponse().hashCode();
        }
        if (getEci() != null) {
            _hashCode += getEci().hashCode();
        }
        if (getXid() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getXid());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getXid(), i);
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
        new org.apache.axis.description.TypeDesc(ThreeDSecureData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://payment.services.adyen.com", "ThreeDSecureData"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authenticationResponse");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "authenticationResponse"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cavv");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "cavv"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cavvAlgorithm");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "cavvAlgorithm"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("directoryResponse");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "directoryResponse"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("eci");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "eci"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("xid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "xid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
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
