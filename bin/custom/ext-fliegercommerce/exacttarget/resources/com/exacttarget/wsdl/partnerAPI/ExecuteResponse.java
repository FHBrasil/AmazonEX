/**
 * ExecuteResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class ExecuteResponse  implements java.io.Serializable {
    private java.lang.String statusCode;

    private java.lang.String statusMessage;

    private java.lang.Integer ordinalID;

    private com.exacttarget.wsdl.partnerAPI.APIProperty[] results;

    private java.lang.Integer errorCode;

    public ExecuteResponse() {
    }

    public ExecuteResponse(
           java.lang.String statusCode,
           java.lang.String statusMessage,
           java.lang.Integer ordinalID,
           com.exacttarget.wsdl.partnerAPI.APIProperty[] results,
           java.lang.Integer errorCode) {
           this.statusCode = statusCode;
           this.statusMessage = statusMessage;
           this.ordinalID = ordinalID;
           this.results = results;
           this.errorCode = errorCode;
    }


    /**
     * Gets the statusCode value for this ExecuteResponse.
     * 
     * @return statusCode
     */
    public java.lang.String getStatusCode() {
        return statusCode;
    }


    /**
     * Sets the statusCode value for this ExecuteResponse.
     * 
     * @param statusCode
     */
    public void setStatusCode(java.lang.String statusCode) {
        this.statusCode = statusCode;
    }


    /**
     * Gets the statusMessage value for this ExecuteResponse.
     * 
     * @return statusMessage
     */
    public java.lang.String getStatusMessage() {
        return statusMessage;
    }


    /**
     * Sets the statusMessage value for this ExecuteResponse.
     * 
     * @param statusMessage
     */
    public void setStatusMessage(java.lang.String statusMessage) {
        this.statusMessage = statusMessage;
    }


    /**
     * Gets the ordinalID value for this ExecuteResponse.
     * 
     * @return ordinalID
     */
    public java.lang.Integer getOrdinalID() {
        return ordinalID;
    }


    /**
     * Sets the ordinalID value for this ExecuteResponse.
     * 
     * @param ordinalID
     */
    public void setOrdinalID(java.lang.Integer ordinalID) {
        this.ordinalID = ordinalID;
    }


    /**
     * Gets the results value for this ExecuteResponse.
     * 
     * @return results
     */
    public com.exacttarget.wsdl.partnerAPI.APIProperty[] getResults() {
        return results;
    }


    /**
     * Sets the results value for this ExecuteResponse.
     * 
     * @param results
     */
    public void setResults(com.exacttarget.wsdl.partnerAPI.APIProperty[] results) {
        this.results = results;
    }

    public com.exacttarget.wsdl.partnerAPI.APIProperty getResults(int i) {
        return this.results[i];
    }

    public void setResults(int i, com.exacttarget.wsdl.partnerAPI.APIProperty _value) {
        this.results[i] = _value;
    }


    /**
     * Gets the errorCode value for this ExecuteResponse.
     * 
     * @return errorCode
     */
    public java.lang.Integer getErrorCode() {
        return errorCode;
    }


    /**
     * Sets the errorCode value for this ExecuteResponse.
     * 
     * @param errorCode
     */
    public void setErrorCode(java.lang.Integer errorCode) {
        this.errorCode = errorCode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ExecuteResponse)) return false;
        ExecuteResponse other = (ExecuteResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.statusCode==null && other.getStatusCode()==null) || 
             (this.statusCode!=null &&
              this.statusCode.equals(other.getStatusCode()))) &&
            ((this.statusMessage==null && other.getStatusMessage()==null) || 
             (this.statusMessage!=null &&
              this.statusMessage.equals(other.getStatusMessage()))) &&
            ((this.ordinalID==null && other.getOrdinalID()==null) || 
             (this.ordinalID!=null &&
              this.ordinalID.equals(other.getOrdinalID()))) &&
            ((this.results==null && other.getResults()==null) || 
             (this.results!=null &&
              java.util.Arrays.equals(this.results, other.getResults()))) &&
            ((this.errorCode==null && other.getErrorCode()==null) || 
             (this.errorCode!=null &&
              this.errorCode.equals(other.getErrorCode())));
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
        if (getStatusCode() != null) {
            _hashCode += getStatusCode().hashCode();
        }
        if (getStatusMessage() != null) {
            _hashCode += getStatusMessage().hashCode();
        }
        if (getOrdinalID() != null) {
            _hashCode += getOrdinalID().hashCode();
        }
        if (getResults() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getResults());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getResults(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getErrorCode() != null) {
            _hashCode += getErrorCode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ExecuteResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExecuteResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statusCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "StatusCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statusMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "StatusMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ordinalID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "OrdinalID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("results");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Results"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "APIProperty"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ErrorCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
