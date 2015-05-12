/**
 * DataExtensionUpdateResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class DataExtensionUpdateResult  extends com.exacttarget.wsdl.partnerAPI.UpdateResult  implements java.io.Serializable {
    private java.lang.String errorMessage;

    private com.exacttarget.wsdl.partnerAPI.DataExtensionError[] keyErrors;

    private com.exacttarget.wsdl.partnerAPI.DataExtensionError[] valueErrors;

    public DataExtensionUpdateResult() {
    }

    public DataExtensionUpdateResult(
           java.lang.String statusCode,
           java.lang.String statusMessage,
           java.lang.Integer ordinalID,
           java.lang.Integer errorCode,
           java.lang.String requestID,
           java.lang.String conversationID,
           java.lang.String overallStatusCode,
           com.exacttarget.wsdl.partnerAPI.RequestType requestType,
           java.lang.String resultType,
           java.lang.String resultDetailXML,
           com.exacttarget.wsdl.partnerAPI.APIObject object,
           com.exacttarget.wsdl.partnerAPI.UpdateResult[] updateResults,
           java.lang.String parentPropertyName,
           java.lang.String errorMessage,
           com.exacttarget.wsdl.partnerAPI.DataExtensionError[] keyErrors,
           com.exacttarget.wsdl.partnerAPI.DataExtensionError[] valueErrors) {
        super(
            statusCode,
            statusMessage,
            ordinalID,
            errorCode,
            requestID,
            conversationID,
            overallStatusCode,
            requestType,
            resultType,
            resultDetailXML,
            object,
            updateResults,
            parentPropertyName);
        this.errorMessage = errorMessage;
        this.keyErrors = keyErrors;
        this.valueErrors = valueErrors;
    }


    /**
     * Gets the errorMessage value for this DataExtensionUpdateResult.
     * 
     * @return errorMessage
     */
    public java.lang.String getErrorMessage() {
        return errorMessage;
    }


    /**
     * Sets the errorMessage value for this DataExtensionUpdateResult.
     * 
     * @param errorMessage
     */
    public void setErrorMessage(java.lang.String errorMessage) {
        this.errorMessage = errorMessage;
    }


    /**
     * Gets the keyErrors value for this DataExtensionUpdateResult.
     * 
     * @return keyErrors
     */
    public com.exacttarget.wsdl.partnerAPI.DataExtensionError[] getKeyErrors() {
        return keyErrors;
    }


    /**
     * Sets the keyErrors value for this DataExtensionUpdateResult.
     * 
     * @param keyErrors
     */
    public void setKeyErrors(com.exacttarget.wsdl.partnerAPI.DataExtensionError[] keyErrors) {
        this.keyErrors = keyErrors;
    }


    /**
     * Gets the valueErrors value for this DataExtensionUpdateResult.
     * 
     * @return valueErrors
     */
    public com.exacttarget.wsdl.partnerAPI.DataExtensionError[] getValueErrors() {
        return valueErrors;
    }


    /**
     * Sets the valueErrors value for this DataExtensionUpdateResult.
     * 
     * @param valueErrors
     */
    public void setValueErrors(com.exacttarget.wsdl.partnerAPI.DataExtensionError[] valueErrors) {
        this.valueErrors = valueErrors;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DataExtensionUpdateResult)) return false;
        DataExtensionUpdateResult other = (DataExtensionUpdateResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.errorMessage==null && other.getErrorMessage()==null) || 
             (this.errorMessage!=null &&
              this.errorMessage.equals(other.getErrorMessage()))) &&
            ((this.keyErrors==null && other.getKeyErrors()==null) || 
             (this.keyErrors!=null &&
              java.util.Arrays.equals(this.keyErrors, other.getKeyErrors()))) &&
            ((this.valueErrors==null && other.getValueErrors()==null) || 
             (this.valueErrors!=null &&
              java.util.Arrays.equals(this.valueErrors, other.getValueErrors())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getErrorMessage() != null) {
            _hashCode += getErrorMessage().hashCode();
        }
        if (getKeyErrors() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getKeyErrors());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getKeyErrors(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getValueErrors() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getValueErrors());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getValueErrors(), i);
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
        new org.apache.axis.description.TypeDesc(DataExtensionUpdateResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataExtensionUpdateResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ErrorMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("keyErrors");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "KeyErrors"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataExtensionError"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "KeyError"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valueErrors");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ValueErrors"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataExtensionError"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ValueError"));
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
