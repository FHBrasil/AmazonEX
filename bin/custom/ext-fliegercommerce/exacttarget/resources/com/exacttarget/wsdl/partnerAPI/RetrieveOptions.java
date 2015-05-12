/**
 * RetrieveOptions.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class RetrieveOptions  extends com.exacttarget.wsdl.partnerAPI.Options  implements java.io.Serializable {
    private java.lang.Integer batchSize;

    private java.lang.Boolean includeObjects;

    private java.lang.Boolean onlyIncludeBase;

    public RetrieveOptions() {
    }

    public RetrieveOptions(
           com.exacttarget.wsdl.partnerAPI.ClientID client,
           com.exacttarget.wsdl.partnerAPI.AsyncResponse[] sendResponseTo,
           com.exacttarget.wsdl.partnerAPI.SaveOption[] saveOptions,
           java.lang.Byte priority,
           java.lang.String conversationID,
           java.lang.Integer sequenceCode,
           java.lang.Integer callsInConversation,
           java.util.Calendar scheduledTime,
           com.exacttarget.wsdl.partnerAPI.RequestType requestType,
           com.exacttarget.wsdl.partnerAPI.Priority queuePriority,
           java.lang.Integer batchSize,
           java.lang.Boolean includeObjects,
           java.lang.Boolean onlyIncludeBase) {
        super(
            client,
            sendResponseTo,
            saveOptions,
            priority,
            conversationID,
            sequenceCode,
            callsInConversation,
            scheduledTime,
            requestType,
            queuePriority);
        this.batchSize = batchSize;
        this.includeObjects = includeObjects;
        this.onlyIncludeBase = onlyIncludeBase;
    }


    /**
     * Gets the batchSize value for this RetrieveOptions.
     * 
     * @return batchSize
     */
    public java.lang.Integer getBatchSize() {
        return batchSize;
    }


    /**
     * Sets the batchSize value for this RetrieveOptions.
     * 
     * @param batchSize
     */
    public void setBatchSize(java.lang.Integer batchSize) {
        this.batchSize = batchSize;
    }


    /**
     * Gets the includeObjects value for this RetrieveOptions.
     * 
     * @return includeObjects
     */
    public java.lang.Boolean getIncludeObjects() {
        return includeObjects;
    }


    /**
     * Sets the includeObjects value for this RetrieveOptions.
     * 
     * @param includeObjects
     */
    public void setIncludeObjects(java.lang.Boolean includeObjects) {
        this.includeObjects = includeObjects;
    }


    /**
     * Gets the onlyIncludeBase value for this RetrieveOptions.
     * 
     * @return onlyIncludeBase
     */
    public java.lang.Boolean getOnlyIncludeBase() {
        return onlyIncludeBase;
    }


    /**
     * Sets the onlyIncludeBase value for this RetrieveOptions.
     * 
     * @param onlyIncludeBase
     */
    public void setOnlyIncludeBase(java.lang.Boolean onlyIncludeBase) {
        this.onlyIncludeBase = onlyIncludeBase;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RetrieveOptions)) return false;
        RetrieveOptions other = (RetrieveOptions) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.batchSize==null && other.getBatchSize()==null) || 
             (this.batchSize!=null &&
              this.batchSize.equals(other.getBatchSize()))) &&
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
        int _hashCode = super.hashCode();
        if (getBatchSize() != null) {
            _hashCode += getBatchSize().hashCode();
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
        new org.apache.axis.description.TypeDesc(RetrieveOptions.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RetrieveOptions"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("batchSize");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "BatchSize"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
