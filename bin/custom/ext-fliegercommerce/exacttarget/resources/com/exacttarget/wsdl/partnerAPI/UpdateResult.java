/**
 * UpdateResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class UpdateResult  extends com.exacttarget.wsdl.partnerAPI.Result  implements java.io.Serializable {
    private com.exacttarget.wsdl.partnerAPI.APIObject object;

    private com.exacttarget.wsdl.partnerAPI.UpdateResult[] updateResults;

    private java.lang.String parentPropertyName;

    public UpdateResult() {
    }

    public UpdateResult(
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
           java.lang.String parentPropertyName) {
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
            resultDetailXML);
        this.object = object;
        this.updateResults = updateResults;
        this.parentPropertyName = parentPropertyName;
    }


    /**
     * Gets the object value for this UpdateResult.
     * 
     * @return object
     */
    public com.exacttarget.wsdl.partnerAPI.APIObject getObject() {
        return object;
    }


    /**
     * Sets the object value for this UpdateResult.
     * 
     * @param object
     */
    public void setObject(com.exacttarget.wsdl.partnerAPI.APIObject object) {
        this.object = object;
    }


    /**
     * Gets the updateResults value for this UpdateResult.
     * 
     * @return updateResults
     */
    public com.exacttarget.wsdl.partnerAPI.UpdateResult[] getUpdateResults() {
        return updateResults;
    }


    /**
     * Sets the updateResults value for this UpdateResult.
     * 
     * @param updateResults
     */
    public void setUpdateResults(com.exacttarget.wsdl.partnerAPI.UpdateResult[] updateResults) {
        this.updateResults = updateResults;
    }

    public com.exacttarget.wsdl.partnerAPI.UpdateResult getUpdateResults(int i) {
        return this.updateResults[i];
    }

    public void setUpdateResults(int i, com.exacttarget.wsdl.partnerAPI.UpdateResult _value) {
        this.updateResults[i] = _value;
    }


    /**
     * Gets the parentPropertyName value for this UpdateResult.
     * 
     * @return parentPropertyName
     */
    public java.lang.String getParentPropertyName() {
        return parentPropertyName;
    }


    /**
     * Sets the parentPropertyName value for this UpdateResult.
     * 
     * @param parentPropertyName
     */
    public void setParentPropertyName(java.lang.String parentPropertyName) {
        this.parentPropertyName = parentPropertyName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UpdateResult)) return false;
        UpdateResult other = (UpdateResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.object==null && other.getObject()==null) || 
             (this.object!=null &&
              this.object.equals(other.getObject()))) &&
            ((this.updateResults==null && other.getUpdateResults()==null) || 
             (this.updateResults!=null &&
              java.util.Arrays.equals(this.updateResults, other.getUpdateResults()))) &&
            ((this.parentPropertyName==null && other.getParentPropertyName()==null) || 
             (this.parentPropertyName!=null &&
              this.parentPropertyName.equals(other.getParentPropertyName())));
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
        if (getObject() != null) {
            _hashCode += getObject().hashCode();
        }
        if (getUpdateResults() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUpdateResults());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUpdateResults(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getParentPropertyName() != null) {
            _hashCode += getParentPropertyName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UpdateResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "UpdateResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("object");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Object"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "APIObject"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("updateResults");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "UpdateResults"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "UpdateResult"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parentPropertyName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ParentPropertyName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
