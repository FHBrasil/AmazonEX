/**
 * ContentValidationTaskResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class ContentValidationTaskResult  extends com.exacttarget.wsdl.partnerAPI.TaskResult  implements java.io.Serializable {
    private com.exacttarget.wsdl.partnerAPI.ValidationResult[] validationResults;

    public ContentValidationTaskResult() {
    }

    public ContentValidationTaskResult(
           java.lang.String statusCode,
           java.lang.String statusMessage,
           java.lang.Integer ordinalID,
           java.lang.Integer errorCode,
           java.lang.String ID,
           java.lang.String interactionObjectID,
           com.exacttarget.wsdl.partnerAPI.ValidationResult[] validationResults) {
        super(
            statusCode,
            statusMessage,
            ordinalID,
            errorCode,
            ID,
            interactionObjectID);
        this.validationResults = validationResults;
    }


    /**
     * Gets the validationResults value for this ContentValidationTaskResult.
     * 
     * @return validationResults
     */
    public com.exacttarget.wsdl.partnerAPI.ValidationResult[] getValidationResults() {
        return validationResults;
    }


    /**
     * Sets the validationResults value for this ContentValidationTaskResult.
     * 
     * @param validationResults
     */
    public void setValidationResults(com.exacttarget.wsdl.partnerAPI.ValidationResult[] validationResults) {
        this.validationResults = validationResults;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ContentValidationTaskResult)) return false;
        ContentValidationTaskResult other = (ContentValidationTaskResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.validationResults==null && other.getValidationResults()==null) || 
             (this.validationResults!=null &&
              java.util.Arrays.equals(this.validationResults, other.getValidationResults())));
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
        if (getValidationResults() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getValidationResults());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getValidationResults(), i);
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
        new org.apache.axis.description.TypeDesc(ContentValidationTaskResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ContentValidationTaskResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("validationResults");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ValidationResults"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ValidationResult"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ValidationResult"));
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
