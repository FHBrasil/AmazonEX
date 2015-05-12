/**
 * SendEmailMOKeyword.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class SendEmailMOKeyword  extends com.exacttarget.wsdl.partnerAPI.BaseMOKeyword  implements java.io.Serializable {
    private java.lang.String successMessage;

    private java.lang.String missingEmailMessage;

    private java.lang.String failureMessage;

    private com.exacttarget.wsdl.partnerAPI.TriggeredSendDefinition triggeredSend;

    private com.exacttarget.wsdl.partnerAPI.BaseMOKeyword nextMOKeyword;

    public SendEmailMOKeyword() {
    }

    public SendEmailMOKeyword(
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
           java.lang.String successMessage,
           java.lang.String missingEmailMessage,
           java.lang.String failureMessage,
           com.exacttarget.wsdl.partnerAPI.TriggeredSendDefinition triggeredSend,
           com.exacttarget.wsdl.partnerAPI.BaseMOKeyword nextMOKeyword) {
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
        this.successMessage = successMessage;
        this.missingEmailMessage = missingEmailMessage;
        this.failureMessage = failureMessage;
        this.triggeredSend = triggeredSend;
        this.nextMOKeyword = nextMOKeyword;
    }


    /**
     * Gets the successMessage value for this SendEmailMOKeyword.
     * 
     * @return successMessage
     */
    public java.lang.String getSuccessMessage() {
        return successMessage;
    }


    /**
     * Sets the successMessage value for this SendEmailMOKeyword.
     * 
     * @param successMessage
     */
    public void setSuccessMessage(java.lang.String successMessage) {
        this.successMessage = successMessage;
    }


    /**
     * Gets the missingEmailMessage value for this SendEmailMOKeyword.
     * 
     * @return missingEmailMessage
     */
    public java.lang.String getMissingEmailMessage() {
        return missingEmailMessage;
    }


    /**
     * Sets the missingEmailMessage value for this SendEmailMOKeyword.
     * 
     * @param missingEmailMessage
     */
    public void setMissingEmailMessage(java.lang.String missingEmailMessage) {
        this.missingEmailMessage = missingEmailMessage;
    }


    /**
     * Gets the failureMessage value for this SendEmailMOKeyword.
     * 
     * @return failureMessage
     */
    public java.lang.String getFailureMessage() {
        return failureMessage;
    }


    /**
     * Sets the failureMessage value for this SendEmailMOKeyword.
     * 
     * @param failureMessage
     */
    public void setFailureMessage(java.lang.String failureMessage) {
        this.failureMessage = failureMessage;
    }


    /**
     * Gets the triggeredSend value for this SendEmailMOKeyword.
     * 
     * @return triggeredSend
     */
    public com.exacttarget.wsdl.partnerAPI.TriggeredSendDefinition getTriggeredSend() {
        return triggeredSend;
    }


    /**
     * Sets the triggeredSend value for this SendEmailMOKeyword.
     * 
     * @param triggeredSend
     */
    public void setTriggeredSend(com.exacttarget.wsdl.partnerAPI.TriggeredSendDefinition triggeredSend) {
        this.triggeredSend = triggeredSend;
    }


    /**
     * Gets the nextMOKeyword value for this SendEmailMOKeyword.
     * 
     * @return nextMOKeyword
     */
    public com.exacttarget.wsdl.partnerAPI.BaseMOKeyword getNextMOKeyword() {
        return nextMOKeyword;
    }


    /**
     * Sets the nextMOKeyword value for this SendEmailMOKeyword.
     * 
     * @param nextMOKeyword
     */
    public void setNextMOKeyword(com.exacttarget.wsdl.partnerAPI.BaseMOKeyword nextMOKeyword) {
        this.nextMOKeyword = nextMOKeyword;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SendEmailMOKeyword)) return false;
        SendEmailMOKeyword other = (SendEmailMOKeyword) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.successMessage==null && other.getSuccessMessage()==null) || 
             (this.successMessage!=null &&
              this.successMessage.equals(other.getSuccessMessage()))) &&
            ((this.missingEmailMessage==null && other.getMissingEmailMessage()==null) || 
             (this.missingEmailMessage!=null &&
              this.missingEmailMessage.equals(other.getMissingEmailMessage()))) &&
            ((this.failureMessage==null && other.getFailureMessage()==null) || 
             (this.failureMessage!=null &&
              this.failureMessage.equals(other.getFailureMessage()))) &&
            ((this.triggeredSend==null && other.getTriggeredSend()==null) || 
             (this.triggeredSend!=null &&
              this.triggeredSend.equals(other.getTriggeredSend()))) &&
            ((this.nextMOKeyword==null && other.getNextMOKeyword()==null) || 
             (this.nextMOKeyword!=null &&
              this.nextMOKeyword.equals(other.getNextMOKeyword())));
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
        if (getSuccessMessage() != null) {
            _hashCode += getSuccessMessage().hashCode();
        }
        if (getMissingEmailMessage() != null) {
            _hashCode += getMissingEmailMessage().hashCode();
        }
        if (getFailureMessage() != null) {
            _hashCode += getFailureMessage().hashCode();
        }
        if (getTriggeredSend() != null) {
            _hashCode += getTriggeredSend().hashCode();
        }
        if (getNextMOKeyword() != null) {
            _hashCode += getNextMOKeyword().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SendEmailMOKeyword.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendEmailMOKeyword"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("successMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SuccessMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("missingEmailMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MissingEmailMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("failureMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FailureMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("triggeredSend");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TriggeredSend"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TriggeredSendDefinition"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nextMOKeyword");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "NextMOKeyword"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "BaseMOKeyword"));
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
