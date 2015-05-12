/**
 * AsyncResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class AsyncResponse  implements java.io.Serializable {
    private com.exacttarget.wsdl.partnerAPI.AsyncResponseType responseType;

    private java.lang.String responseAddress;

    private com.exacttarget.wsdl.partnerAPI.RespondWhen respondWhen;

    private java.lang.Boolean includeResults;

    private java.lang.Boolean includeObjects;

    private java.lang.Boolean onlyIncludeBase;

    public AsyncResponse() {
    }

    public AsyncResponse(
           com.exacttarget.wsdl.partnerAPI.AsyncResponseType responseType,
           java.lang.String responseAddress,
           com.exacttarget.wsdl.partnerAPI.RespondWhen respondWhen,
           java.lang.Boolean includeResults,
           java.lang.Boolean includeObjects,
           java.lang.Boolean onlyIncludeBase) {
           this.responseType = responseType;
           this.responseAddress = responseAddress;
           this.respondWhen = respondWhen;
           this.includeResults = includeResults;
           this.includeObjects = includeObjects;
           this.onlyIncludeBase = onlyIncludeBase;
    }


    /**
     * Gets the responseType value for this AsyncResponse.
     * 
     * @return responseType
     */
    public com.exacttarget.wsdl.partnerAPI.AsyncResponseType getResponseType() {
        return responseType;
    }


    /**
     * Sets the responseType value for this AsyncResponse.
     * 
     * @param responseType
     */
    public void setResponseType(com.exacttarget.wsdl.partnerAPI.AsyncResponseType responseType) {
        this.responseType = responseType;
    }


    /**
     * Gets the responseAddress value for this AsyncResponse.
     * 
     * @return responseAddress
     */
    public java.lang.String getResponseAddress() {
        return responseAddress;
    }


    /**
     * Sets the responseAddress value for this AsyncResponse.
     * 
     * @param responseAddress
     */
    public void setResponseAddress(java.lang.String responseAddress) {
        this.responseAddress = responseAddress;
    }


    /**
     * Gets the respondWhen value for this AsyncResponse.
     * 
     * @return respondWhen
     */
    public com.exacttarget.wsdl.partnerAPI.RespondWhen getRespondWhen() {
        return respondWhen;
    }


    /**
     * Sets the respondWhen value for this AsyncResponse.
     * 
     * @param respondWhen
     */
    public void setRespondWhen(com.exacttarget.wsdl.partnerAPI.RespondWhen respondWhen) {
        this.respondWhen = respondWhen;
    }


    /**
     * Gets the includeResults value for this AsyncResponse.
     * 
     * @return includeResults
     */
    public java.lang.Boolean getIncludeResults() {
        return includeResults;
    }


    /**
     * Sets the includeResults value for this AsyncResponse.
     * 
     * @param includeResults
     */
    public void setIncludeResults(java.lang.Boolean includeResults) {
        this.includeResults = includeResults;
    }


    /**
     * Gets the includeObjects value for this AsyncResponse.
     * 
     * @return includeObjects
     */
    public java.lang.Boolean getIncludeObjects() {
        return includeObjects;
    }


    /**
     * Sets the includeObjects value for this AsyncResponse.
     * 
     * @param includeObjects
     */
    public void setIncludeObjects(java.lang.Boolean includeObjects) {
        this.includeObjects = includeObjects;
    }


    /**
     * Gets the onlyIncludeBase value for this AsyncResponse.
     * 
     * @return onlyIncludeBase
     */
    public java.lang.Boolean getOnlyIncludeBase() {
        return onlyIncludeBase;
    }


    /**
     * Sets the onlyIncludeBase value for this AsyncResponse.
     * 
     * @param onlyIncludeBase
     */
    public void setOnlyIncludeBase(java.lang.Boolean onlyIncludeBase) {
        this.onlyIncludeBase = onlyIncludeBase;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AsyncResponse)) return false;
        AsyncResponse other = (AsyncResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.responseType==null && other.getResponseType()==null) || 
             (this.responseType!=null &&
              this.responseType.equals(other.getResponseType()))) &&
            ((this.responseAddress==null && other.getResponseAddress()==null) || 
             (this.responseAddress!=null &&
              this.responseAddress.equals(other.getResponseAddress()))) &&
            ((this.respondWhen==null && other.getRespondWhen()==null) || 
             (this.respondWhen!=null &&
              this.respondWhen.equals(other.getRespondWhen()))) &&
            ((this.includeResults==null && other.getIncludeResults()==null) || 
             (this.includeResults!=null &&
              this.includeResults.equals(other.getIncludeResults()))) &&
            ((this.includeObjects==null && other.getIncludeObjects()==null) || 
             (this.includeObjects!=null &&
              this.includeObjects.equals(other.getIncludeObjects()))) &&
            ((this.onlyIncludeBase==null && other.getOnlyIncludeBase()==null) || 
             (this.onlyIncludeBase!=null &&
              this.onlyIncludeBase.equals(other.getOnlyIncludeBase())));
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
        if (getResponseType() != null) {
            _hashCode += getResponseType().hashCode();
        }
        if (getResponseAddress() != null) {
            _hashCode += getResponseAddress().hashCode();
        }
        if (getRespondWhen() != null) {
            _hashCode += getRespondWhen().hashCode();
        }
        if (getIncludeResults() != null) {
            _hashCode += getIncludeResults().hashCode();
        }
        if (getIncludeObjects() != null) {
            _hashCode += getIncludeObjects().hashCode();
        }
        if (getOnlyIncludeBase() != null) {
            _hashCode += getOnlyIncludeBase().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AsyncResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AsyncResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("responseType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ResponseType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AsyncResponseType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("responseAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ResponseAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("respondWhen");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RespondWhen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RespondWhen"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("includeResults");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IncludeResults"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("includeObjects");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IncludeObjects"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("onlyIncludeBase");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "OnlyIncludeBase"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
