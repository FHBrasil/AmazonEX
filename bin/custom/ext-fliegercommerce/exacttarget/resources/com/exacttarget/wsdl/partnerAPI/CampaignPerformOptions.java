/**
 * CampaignPerformOptions.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class CampaignPerformOptions  extends com.exacttarget.wsdl.partnerAPI.PerformOptions  implements java.io.Serializable {
    private java.lang.String[] occurrenceIDs;

    private java.lang.Integer occurrenceIDsIndex;

    public CampaignPerformOptions() {
    }

    public CampaignPerformOptions(
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
           java.lang.String explanation,
           java.lang.String[] occurrenceIDs,
           java.lang.Integer occurrenceIDsIndex) {
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
            queuePriority,
            explanation);
        this.occurrenceIDs = occurrenceIDs;
        this.occurrenceIDsIndex = occurrenceIDsIndex;
    }


    /**
     * Gets the occurrenceIDs value for this CampaignPerformOptions.
     * 
     * @return occurrenceIDs
     */
    public java.lang.String[] getOccurrenceIDs() {
        return occurrenceIDs;
    }


    /**
     * Sets the occurrenceIDs value for this CampaignPerformOptions.
     * 
     * @param occurrenceIDs
     */
    public void setOccurrenceIDs(java.lang.String[] occurrenceIDs) {
        this.occurrenceIDs = occurrenceIDs;
    }

    public java.lang.String getOccurrenceIDs(int i) {
        return this.occurrenceIDs[i];
    }

    public void setOccurrenceIDs(int i, java.lang.String _value) {
        this.occurrenceIDs[i] = _value;
    }


    /**
     * Gets the occurrenceIDsIndex value for this CampaignPerformOptions.
     * 
     * @return occurrenceIDsIndex
     */
    public java.lang.Integer getOccurrenceIDsIndex() {
        return occurrenceIDsIndex;
    }


    /**
     * Sets the occurrenceIDsIndex value for this CampaignPerformOptions.
     * 
     * @param occurrenceIDsIndex
     */
    public void setOccurrenceIDsIndex(java.lang.Integer occurrenceIDsIndex) {
        this.occurrenceIDsIndex = occurrenceIDsIndex;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CampaignPerformOptions)) return false;
        CampaignPerformOptions other = (CampaignPerformOptions) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.occurrenceIDs==null && other.getOccurrenceIDs()==null) || 
             (this.occurrenceIDs!=null &&
              java.util.Arrays.equals(this.occurrenceIDs, other.getOccurrenceIDs()))) &&
            ((this.occurrenceIDsIndex==null && other.getOccurrenceIDsIndex()==null) || 
             (this.occurrenceIDsIndex!=null &&
              this.occurrenceIDsIndex.equals(other.getOccurrenceIDsIndex())));
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
        if (getOccurrenceIDs() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getOccurrenceIDs());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getOccurrenceIDs(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getOccurrenceIDsIndex() != null) {
            _hashCode += getOccurrenceIDsIndex().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CampaignPerformOptions.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "CampaignPerformOptions"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("occurrenceIDs");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "OccurrenceIDs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("occurrenceIDsIndex");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "OccurrenceIDsIndex"));
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
