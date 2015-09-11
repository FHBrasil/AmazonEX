/**
 * PaymentResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.adyen.services.payment;

public class PaymentResult  implements java.io.Serializable {
    private com.adyen.services.payment.AnyType2AnyTypeMapEntry[] additionalData;

    private java.lang.String authCode;

    private com.adyen.services.common.Amount dccAmount;

    private java.lang.String dccSignature;

    private com.adyen.services.payment.FraudResult fraudResult;

    private java.lang.String issuerUrl;

    private java.lang.String md;

    private java.lang.String paRequest;

    private java.lang.String pspReference;

    private java.lang.String refusalReason;

    private java.lang.String resultCode;

    public PaymentResult() {
    }

    public PaymentResult(
           com.adyen.services.payment.AnyType2AnyTypeMapEntry[] additionalData,
           java.lang.String authCode,
           com.adyen.services.common.Amount dccAmount,
           java.lang.String dccSignature,
           com.adyen.services.payment.FraudResult fraudResult,
           java.lang.String issuerUrl,
           java.lang.String md,
           java.lang.String paRequest,
           java.lang.String pspReference,
           java.lang.String refusalReason,
           java.lang.String resultCode) {
           this.additionalData = additionalData;
           this.authCode = authCode;
           this.dccAmount = dccAmount;
           this.dccSignature = dccSignature;
           this.fraudResult = fraudResult;
           this.issuerUrl = issuerUrl;
           this.md = md;
           this.paRequest = paRequest;
           this.pspReference = pspReference;
           this.refusalReason = refusalReason;
           this.resultCode = resultCode;
    }


    /**
     * Gets the additionalData value for this PaymentResult.
     * 
     * @return additionalData
     */
    public com.adyen.services.payment.AnyType2AnyTypeMapEntry[] getAdditionalData() {
        return additionalData;
    }


    /**
     * Sets the additionalData value for this PaymentResult.
     * 
     * @param additionalData
     */
    public void setAdditionalData(com.adyen.services.payment.AnyType2AnyTypeMapEntry[] additionalData) {
        this.additionalData = additionalData;
    }


    /**
     * Gets the authCode value for this PaymentResult.
     * 
     * @return authCode
     */
    public java.lang.String getAuthCode() {
        return authCode;
    }


    /**
     * Sets the authCode value for this PaymentResult.
     * 
     * @param authCode
     */
    public void setAuthCode(java.lang.String authCode) {
        this.authCode = authCode;
    }


    /**
     * Gets the dccAmount value for this PaymentResult.
     * 
     * @return dccAmount
     */
    public com.adyen.services.common.Amount getDccAmount() {
        return dccAmount;
    }


    /**
     * Sets the dccAmount value for this PaymentResult.
     * 
     * @param dccAmount
     */
    public void setDccAmount(com.adyen.services.common.Amount dccAmount) {
        this.dccAmount = dccAmount;
    }


    /**
     * Gets the dccSignature value for this PaymentResult.
     * 
     * @return dccSignature
     */
    public java.lang.String getDccSignature() {
        return dccSignature;
    }


    /**
     * Sets the dccSignature value for this PaymentResult.
     * 
     * @param dccSignature
     */
    public void setDccSignature(java.lang.String dccSignature) {
        this.dccSignature = dccSignature;
    }


    /**
     * Gets the fraudResult value for this PaymentResult.
     * 
     * @return fraudResult
     */
    public com.adyen.services.payment.FraudResult getFraudResult() {
        return fraudResult;
    }


    /**
     * Sets the fraudResult value for this PaymentResult.
     * 
     * @param fraudResult
     */
    public void setFraudResult(com.adyen.services.payment.FraudResult fraudResult) {
        this.fraudResult = fraudResult;
    }


    /**
     * Gets the issuerUrl value for this PaymentResult.
     * 
     * @return issuerUrl
     */
    public java.lang.String getIssuerUrl() {
        return issuerUrl;
    }


    /**
     * Sets the issuerUrl value for this PaymentResult.
     * 
     * @param issuerUrl
     */
    public void setIssuerUrl(java.lang.String issuerUrl) {
        this.issuerUrl = issuerUrl;
    }


    /**
     * Gets the md value for this PaymentResult.
     * 
     * @return md
     */
    public java.lang.String getMd() {
        return md;
    }


    /**
     * Sets the md value for this PaymentResult.
     * 
     * @param md
     */
    public void setMd(java.lang.String md) {
        this.md = md;
    }


    /**
     * Gets the paRequest value for this PaymentResult.
     * 
     * @return paRequest
     */
    public java.lang.String getPaRequest() {
        return paRequest;
    }


    /**
     * Sets the paRequest value for this PaymentResult.
     * 
     * @param paRequest
     */
    public void setPaRequest(java.lang.String paRequest) {
        this.paRequest = paRequest;
    }


    /**
     * Gets the pspReference value for this PaymentResult.
     * 
     * @return pspReference
     */
    public java.lang.String getPspReference() {
        return pspReference;
    }


    /**
     * Sets the pspReference value for this PaymentResult.
     * 
     * @param pspReference
     */
    public void setPspReference(java.lang.String pspReference) {
        this.pspReference = pspReference;
    }


    /**
     * Gets the refusalReason value for this PaymentResult.
     * 
     * @return refusalReason
     */
    public java.lang.String getRefusalReason() {
        return refusalReason;
    }


    /**
     * Sets the refusalReason value for this PaymentResult.
     * 
     * @param refusalReason
     */
    public void setRefusalReason(java.lang.String refusalReason) {
        this.refusalReason = refusalReason;
    }


    /**
     * Gets the resultCode value for this PaymentResult.
     * 
     * @return resultCode
     */
    public java.lang.String getResultCode() {
        return resultCode;
    }


    /**
     * Sets the resultCode value for this PaymentResult.
     * 
     * @param resultCode
     */
    public void setResultCode(java.lang.String resultCode) {
        this.resultCode = resultCode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PaymentResult)) return false;
        PaymentResult other = (PaymentResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.additionalData==null && other.getAdditionalData()==null) || 
             (this.additionalData!=null &&
              java.util.Arrays.equals(this.additionalData, other.getAdditionalData()))) &&
            ((this.authCode==null && other.getAuthCode()==null) || 
             (this.authCode!=null &&
              this.authCode.equals(other.getAuthCode()))) &&
            ((this.dccAmount==null && other.getDccAmount()==null) || 
             (this.dccAmount!=null &&
              this.dccAmount.equals(other.getDccAmount()))) &&
            ((this.dccSignature==null && other.getDccSignature()==null) || 
             (this.dccSignature!=null &&
              this.dccSignature.equals(other.getDccSignature()))) &&
            ((this.fraudResult==null && other.getFraudResult()==null) || 
             (this.fraudResult!=null &&
              this.fraudResult.equals(other.getFraudResult()))) &&
            ((this.issuerUrl==null && other.getIssuerUrl()==null) || 
             (this.issuerUrl!=null &&
              this.issuerUrl.equals(other.getIssuerUrl()))) &&
            ((this.md==null && other.getMd()==null) || 
             (this.md!=null &&
              this.md.equals(other.getMd()))) &&
            ((this.paRequest==null && other.getPaRequest()==null) || 
             (this.paRequest!=null &&
              this.paRequest.equals(other.getPaRequest()))) &&
            ((this.pspReference==null && other.getPspReference()==null) || 
             (this.pspReference!=null &&
              this.pspReference.equals(other.getPspReference()))) &&
            ((this.refusalReason==null && other.getRefusalReason()==null) || 
             (this.refusalReason!=null &&
              this.refusalReason.equals(other.getRefusalReason()))) &&
            ((this.resultCode==null && other.getResultCode()==null) || 
             (this.resultCode!=null &&
              this.resultCode.equals(other.getResultCode())));
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
        if (getAdditionalData() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAdditionalData());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAdditionalData(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAuthCode() != null) {
            _hashCode += getAuthCode().hashCode();
        }
        if (getDccAmount() != null) {
            _hashCode += getDccAmount().hashCode();
        }
        if (getDccSignature() != null) {
            _hashCode += getDccSignature().hashCode();
        }
        if (getFraudResult() != null) {
            _hashCode += getFraudResult().hashCode();
        }
        if (getIssuerUrl() != null) {
            _hashCode += getIssuerUrl().hashCode();
        }
        if (getMd() != null) {
            _hashCode += getMd().hashCode();
        }
        if (getPaRequest() != null) {
            _hashCode += getPaRequest().hashCode();
        }
        if (getPspReference() != null) {
            _hashCode += getPspReference().hashCode();
        }
        if (getRefusalReason() != null) {
            _hashCode += getRefusalReason().hashCode();
        }
        if (getResultCode() != null) {
            _hashCode += getResultCode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PaymentResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://payment.services.adyen.com", "PaymentResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("additionalData");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "additionalData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://payment.services.adyen.com", ">anyType2anyTypeMap>entry"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "entry"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "authCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dccAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "dccAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://common.services.adyen.com", "Amount"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dccSignature");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "dccSignature"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fraudResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "fraudResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://payment.services.adyen.com", "FraudResult"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("issuerUrl");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "issuerUrl"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("md");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "md"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paRequest");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "paRequest"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pspReference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "pspReference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("refusalReason");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "refusalReason"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://payment.services.adyen.com", "resultCode"));
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
