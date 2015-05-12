/**
 * CreateResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class CreateResult  extends com.exacttarget.wsdl.partnerAPI.Result  implements java.io.Serializable {
    private int newID;

    private java.lang.String newObjectID;

    private java.lang.String partnerKey;

    private com.exacttarget.wsdl.partnerAPI.APIObject object;

    private com.exacttarget.wsdl.partnerAPI.CreateResult[] createResults;

    private java.lang.String parentPropertyName;

    public CreateResult() {
    }

    public CreateResult(
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
           int newID,
           java.lang.String newObjectID,
           java.lang.String partnerKey,
           com.exacttarget.wsdl.partnerAPI.APIObject object,
           com.exacttarget.wsdl.partnerAPI.CreateResult[] createResults,
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
        this.newID = newID;
        this.newObjectID = newObjectID;
        this.partnerKey = partnerKey;
        this.object = object;
        this.createResults = createResults;
        this.parentPropertyName = parentPropertyName;
    }


    /**
     * Gets the newID value for this CreateResult.
     * 
     * @return newID
     */
    public int getNewID() {
        return newID;
    }


    /**
     * Sets the newID value for this CreateResult.
     * 
     * @param newID
     */
    public void setNewID(int newID) {
        this.newID = newID;
    }


    /**
     * Gets the newObjectID value for this CreateResult.
     * 
     * @return newObjectID
     */
    public java.lang.String getNewObjectID() {
        return newObjectID;
    }


    /**
     * Sets the newObjectID value for this CreateResult.
     * 
     * @param newObjectID
     */
    public void setNewObjectID(java.lang.String newObjectID) {
        this.newObjectID = newObjectID;
    }


    /**
     * Gets the partnerKey value for this CreateResult.
     * 
     * @return partnerKey
     */
    public java.lang.String getPartnerKey() {
        return partnerKey;
    }


    /**
     * Sets the partnerKey value for this CreateResult.
     * 
     * @param partnerKey
     */
    public void setPartnerKey(java.lang.String partnerKey) {
        this.partnerKey = partnerKey;
    }


    /**
     * Gets the object value for this CreateResult.
     * 
     * @return object
     */
    public com.exacttarget.wsdl.partnerAPI.APIObject getObject() {
        return object;
    }


    /**
     * Sets the object value for this CreateResult.
     * 
     * @param object
     */
    public void setObject(com.exacttarget.wsdl.partnerAPI.APIObject object) {
        this.object = object;
    }


    /**
     * Gets the createResults value for this CreateResult.
     * 
     * @return createResults
     */
    public com.exacttarget.wsdl.partnerAPI.CreateResult[] getCreateResults() {
        return createResults;
    }


    /**
     * Sets the createResults value for this CreateResult.
     * 
     * @param createResults
     */
    public void setCreateResults(com.exacttarget.wsdl.partnerAPI.CreateResult[] createResults) {
        this.createResults = createResults;
    }

    public com.exacttarget.wsdl.partnerAPI.CreateResult getCreateResults(int i) {
        return this.createResults[i];
    }

    public void setCreateResults(int i, com.exacttarget.wsdl.partnerAPI.CreateResult _value) {
        this.createResults[i] = _value;
    }


    /**
     * Gets the parentPropertyName value for this CreateResult.
     * 
     * @return parentPropertyName
     */
    public java.lang.String getParentPropertyName() {
        return parentPropertyName;
    }


    /**
     * Sets the parentPropertyName value for this CreateResult.
     * 
     * @param parentPropertyName
     */
    public void setParentPropertyName(java.lang.String parentPropertyName) {
        this.parentPropertyName = parentPropertyName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CreateResult)) return false;
        CreateResult other = (CreateResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            this.newID == other.getNewID() &&
            ((this.newObjectID==null && other.getNewObjectID()==null) || 
             (this.newObjectID!=null &&
              this.newObjectID.equals(other.getNewObjectID()))) &&
            ((this.partnerKey==null && other.getPartnerKey()==null) || 
             (this.partnerKey!=null &&
              this.partnerKey.equals(other.getPartnerKey()))) &&
            ((this.object==null && other.getObject()==null) || 
             (this.object!=null &&
              this.object.equals(other.getObject()))) &&
            ((this.createResults==null && other.getCreateResults()==null) || 
             (this.createResults!=null &&
              java.util.Arrays.equals(this.createResults, other.getCreateResults()))) &&
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
        _hashCode += getNewID();
        if (getNewObjectID() != null) {
            _hashCode += getNewObjectID().hashCode();
        }
        if (getPartnerKey() != null) {
            _hashCode += getPartnerKey().hashCode();
        }
        if (getObject() != null) {
            _hashCode += getObject().hashCode();
        }
        if (getCreateResults() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCreateResults());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCreateResults(), i);
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
        new org.apache.axis.description.TypeDesc(CreateResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "CreateResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("newID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "NewID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("newObjectID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "NewObjectID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("partnerKey");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PartnerKey"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("object");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Object"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "APIObject"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("createResults");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "CreateResults"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "CreateResult"));
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
