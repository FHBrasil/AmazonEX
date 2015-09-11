/**
 * UnsubscribeFromSMSPublicationMOKeyword.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class UnsubscribeFromSMSPublicationMOKeyword  extends com.exacttarget.wsdl.partnerAPI.BaseMOKeyword  implements java.io.Serializable {
    private com.exacttarget.wsdl.partnerAPI.BaseMOKeyword nextMOKeyword;

    private java.lang.String allUnsubSuccessMessage;

    private java.lang.String invalidPublicationMessage;

    private java.lang.String singleUnsubSuccessMessage;

    public UnsubscribeFromSMSPublicationMOKeyword() {
    }

    public UnsubscribeFromSMSPublicationMOKeyword(
           com.exacttarget.wsdl.partnerAPI.ClientID client,
           java.lang.String partnerKey,
           com.exacttarget.wsdl.partnerAPI.APIProperty[] partnerProperties,
           java.util.Calendar createdDate,
           java.util.Calendar modifiedDate,
           java.lang.Integer ID,
           java.lang.String objectID,
           java.lang.String customerKey,
           com.exacttarget.wsdl.partnerAPI.Owner owner,
           java.lang.String correlationID,
           java.lang.String objectState,
           java.lang.Boolean isDefaultKeyword,
           com.exacttarget.wsdl.partnerAPI.BaseMOKeyword nextMOKeyword,
           java.lang.String allUnsubSuccessMessage,
           java.lang.String invalidPublicationMessage,
           java.lang.String singleUnsubSuccessMessage) {
        super(
            client,
            partnerKey,
            partnerProperties,
            createdDate,
            modifiedDate,
            ID,
            objectID,
            customerKey,
            owner,
            correlationID,
            objectState,
            isDefaultKeyword);
        this.nextMOKeyword = nextMOKeyword;
        this.allUnsubSuccessMessage = allUnsubSuccessMessage;
        this.invalidPublicationMessage = invalidPublicationMessage;
        this.singleUnsubSuccessMessage = singleUnsubSuccessMessage;
    }


    /**
     * Gets the nextMOKeyword value for this UnsubscribeFromSMSPublicationMOKeyword.
     * 
     * @return nextMOKeyword
     */
    public com.exacttarget.wsdl.partnerAPI.BaseMOKeyword getNextMOKeyword() {
        return nextMOKeyword;
    }


    /**
     * Sets the nextMOKeyword value for this UnsubscribeFromSMSPublicationMOKeyword.
     * 
     * @param nextMOKeyword
     */
    public void setNextMOKeyword(com.exacttarget.wsdl.partnerAPI.BaseMOKeyword nextMOKeyword) {
        this.nextMOKeyword = nextMOKeyword;
    }


    /**
     * Gets the allUnsubSuccessMessage value for this UnsubscribeFromSMSPublicationMOKeyword.
     * 
     * @return allUnsubSuccessMessage
     */
    public java.lang.String getAllUnsubSuccessMessage() {
        return allUnsubSuccessMessage;
    }


    /**
     * Sets the allUnsubSuccessMessage value for this UnsubscribeFromSMSPublicationMOKeyword.
     * 
     * @param allUnsubSuccessMessage
     */
    public void setAllUnsubSuccessMessage(java.lang.String allUnsubSuccessMessage) {
        this.allUnsubSuccessMessage = allUnsubSuccessMessage;
    }


    /**
     * Gets the invalidPublicationMessage value for this UnsubscribeFromSMSPublicationMOKeyword.
     * 
     * @return invalidPublicationMessage
     */
    public java.lang.String getInvalidPublicationMessage() {
        return invalidPublicationMessage;
    }


    /**
     * Sets the invalidPublicationMessage value for this UnsubscribeFromSMSPublicationMOKeyword.
     * 
     * @param invalidPublicationMessage
     */
    public void setInvalidPublicationMessage(java.lang.String invalidPublicationMessage) {
        this.invalidPublicationMessage = invalidPublicationMessage;
    }


    /**
     * Gets the singleUnsubSuccessMessage value for this UnsubscribeFromSMSPublicationMOKeyword.
     * 
     * @return singleUnsubSuccessMessage
     */
    public java.lang.String getSingleUnsubSuccessMessage() {
        return singleUnsubSuccessMessage;
    }


    /**
     * Sets the singleUnsubSuccessMessage value for this UnsubscribeFromSMSPublicationMOKeyword.
     * 
     * @param singleUnsubSuccessMessage
     */
    public void setSingleUnsubSuccessMessage(java.lang.String singleUnsubSuccessMessage) {
        this.singleUnsubSuccessMessage = singleUnsubSuccessMessage;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UnsubscribeFromSMSPublicationMOKeyword)) return false;
        UnsubscribeFromSMSPublicationMOKeyword other = (UnsubscribeFromSMSPublicationMOKeyword) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.nextMOKeyword==null && other.getNextMOKeyword()==null) || 
             (this.nextMOKeyword!=null &&
              this.nextMOKeyword.equals(other.getNextMOKeyword()))) &&
            ((this.allUnsubSuccessMessage==null && other.getAllUnsubSuccessMessage()==null) || 
             (this.allUnsubSuccessMessage!=null &&
              this.allUnsubSuccessMessage.equals(other.getAllUnsubSuccessMessage()))) &&
            ((this.invalidPublicationMessage==null && other.getInvalidPublicationMessage()==null) || 
             (this.invalidPublicationMessage!=null &&
              this.invalidPublicationMessage.equals(other.getInvalidPublicationMessage()))) &&
            ((this.singleUnsubSuccessMessage==null && other.getSingleUnsubSuccessMessage()==null) || 
             (this.singleUnsubSuccessMessage!=null &&
              this.singleUnsubSuccessMessage.equals(other.getSingleUnsubSuccessMessage())));
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
        if (getNextMOKeyword() != null) {
            _hashCode += getNextMOKeyword().hashCode();
        }
        if (getAllUnsubSuccessMessage() != null) {
            _hashCode += getAllUnsubSuccessMessage().hashCode();
        }
        if (getInvalidPublicationMessage() != null) {
            _hashCode += getInvalidPublicationMessage().hashCode();
        }
        if (getSingleUnsubSuccessMessage() != null) {
            _hashCode += getSingleUnsubSuccessMessage().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UnsubscribeFromSMSPublicationMOKeyword.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "UnsubscribeFromSMSPublicationMOKeyword"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nextMOKeyword");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "NextMOKeyword"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "BaseMOKeyword"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("allUnsubSuccessMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AllUnsubSuccessMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("invalidPublicationMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "InvalidPublicationMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("singleUnsubSuccessMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SingleUnsubSuccessMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
