/**
 * DoubleOptInMOKeyword.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class DoubleOptInMOKeyword  extends com.exacttarget.wsdl.partnerAPI.BaseMOKeyword  implements java.io.Serializable {
    private com.exacttarget.wsdl.partnerAPI.List defaultPublication;

    private java.lang.String invalidPublicationMessage;

    private java.lang.String invalidResponseMessage;

    private java.lang.String missingPublicationMessage;

    private java.lang.String needPublicationMessage;

    private java.lang.String promptMessage;

    private java.lang.String successMessage;

    private java.lang.String unexpectedErrorMessage;

    private com.exacttarget.wsdl.partnerAPI.List[] validPublications;

    private java.lang.String[] validResponses;

    public DoubleOptInMOKeyword() {
    }

    public DoubleOptInMOKeyword(
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
           com.exacttarget.wsdl.partnerAPI.List defaultPublication,
           java.lang.String invalidPublicationMessage,
           java.lang.String invalidResponseMessage,
           java.lang.String missingPublicationMessage,
           java.lang.String needPublicationMessage,
           java.lang.String promptMessage,
           java.lang.String successMessage,
           java.lang.String unexpectedErrorMessage,
           com.exacttarget.wsdl.partnerAPI.List[] validPublications,
           java.lang.String[] validResponses) {
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
        this.defaultPublication = defaultPublication;
        this.invalidPublicationMessage = invalidPublicationMessage;
        this.invalidResponseMessage = invalidResponseMessage;
        this.missingPublicationMessage = missingPublicationMessage;
        this.needPublicationMessage = needPublicationMessage;
        this.promptMessage = promptMessage;
        this.successMessage = successMessage;
        this.unexpectedErrorMessage = unexpectedErrorMessage;
        this.validPublications = validPublications;
        this.validResponses = validResponses;
    }


    /**
     * Gets the defaultPublication value for this DoubleOptInMOKeyword.
     * 
     * @return defaultPublication
     */
    public com.exacttarget.wsdl.partnerAPI.List getDefaultPublication() {
        return defaultPublication;
    }


    /**
     * Sets the defaultPublication value for this DoubleOptInMOKeyword.
     * 
     * @param defaultPublication
     */
    public void setDefaultPublication(com.exacttarget.wsdl.partnerAPI.List defaultPublication) {
        this.defaultPublication = defaultPublication;
    }


    /**
     * Gets the invalidPublicationMessage value for this DoubleOptInMOKeyword.
     * 
     * @return invalidPublicationMessage
     */
    public java.lang.String getInvalidPublicationMessage() {
        return invalidPublicationMessage;
    }


    /**
     * Sets the invalidPublicationMessage value for this DoubleOptInMOKeyword.
     * 
     * @param invalidPublicationMessage
     */
    public void setInvalidPublicationMessage(java.lang.String invalidPublicationMessage) {
        this.invalidPublicationMessage = invalidPublicationMessage;
    }


    /**
     * Gets the invalidResponseMessage value for this DoubleOptInMOKeyword.
     * 
     * @return invalidResponseMessage
     */
    public java.lang.String getInvalidResponseMessage() {
        return invalidResponseMessage;
    }


    /**
     * Sets the invalidResponseMessage value for this DoubleOptInMOKeyword.
     * 
     * @param invalidResponseMessage
     */
    public void setInvalidResponseMessage(java.lang.String invalidResponseMessage) {
        this.invalidResponseMessage = invalidResponseMessage;
    }


    /**
     * Gets the missingPublicationMessage value for this DoubleOptInMOKeyword.
     * 
     * @return missingPublicationMessage
     */
    public java.lang.String getMissingPublicationMessage() {
        return missingPublicationMessage;
    }


    /**
     * Sets the missingPublicationMessage value for this DoubleOptInMOKeyword.
     * 
     * @param missingPublicationMessage
     */
    public void setMissingPublicationMessage(java.lang.String missingPublicationMessage) {
        this.missingPublicationMessage = missingPublicationMessage;
    }


    /**
     * Gets the needPublicationMessage value for this DoubleOptInMOKeyword.
     * 
     * @return needPublicationMessage
     */
    public java.lang.String getNeedPublicationMessage() {
        return needPublicationMessage;
    }


    /**
     * Sets the needPublicationMessage value for this DoubleOptInMOKeyword.
     * 
     * @param needPublicationMessage
     */
    public void setNeedPublicationMessage(java.lang.String needPublicationMessage) {
        this.needPublicationMessage = needPublicationMessage;
    }


    /**
     * Gets the promptMessage value for this DoubleOptInMOKeyword.
     * 
     * @return promptMessage
     */
    public java.lang.String getPromptMessage() {
        return promptMessage;
    }


    /**
     * Sets the promptMessage value for this DoubleOptInMOKeyword.
     * 
     * @param promptMessage
     */
    public void setPromptMessage(java.lang.String promptMessage) {
        this.promptMessage = promptMessage;
    }


    /**
     * Gets the successMessage value for this DoubleOptInMOKeyword.
     * 
     * @return successMessage
     */
    public java.lang.String getSuccessMessage() {
        return successMessage;
    }


    /**
     * Sets the successMessage value for this DoubleOptInMOKeyword.
     * 
     * @param successMessage
     */
    public void setSuccessMessage(java.lang.String successMessage) {
        this.successMessage = successMessage;
    }


    /**
     * Gets the unexpectedErrorMessage value for this DoubleOptInMOKeyword.
     * 
     * @return unexpectedErrorMessage
     */
    public java.lang.String getUnexpectedErrorMessage() {
        return unexpectedErrorMessage;
    }


    /**
     * Sets the unexpectedErrorMessage value for this DoubleOptInMOKeyword.
     * 
     * @param unexpectedErrorMessage
     */
    public void setUnexpectedErrorMessage(java.lang.String unexpectedErrorMessage) {
        this.unexpectedErrorMessage = unexpectedErrorMessage;
    }


    /**
     * Gets the validPublications value for this DoubleOptInMOKeyword.
     * 
     * @return validPublications
     */
    public com.exacttarget.wsdl.partnerAPI.List[] getValidPublications() {
        return validPublications;
    }


    /**
     * Sets the validPublications value for this DoubleOptInMOKeyword.
     * 
     * @param validPublications
     */
    public void setValidPublications(com.exacttarget.wsdl.partnerAPI.List[] validPublications) {
        this.validPublications = validPublications;
    }


    /**
     * Gets the validResponses value for this DoubleOptInMOKeyword.
     * 
     * @return validResponses
     */
    public java.lang.String[] getValidResponses() {
        return validResponses;
    }


    /**
     * Sets the validResponses value for this DoubleOptInMOKeyword.
     * 
     * @param validResponses
     */
    public void setValidResponses(java.lang.String[] validResponses) {
        this.validResponses = validResponses;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DoubleOptInMOKeyword)) return false;
        DoubleOptInMOKeyword other = (DoubleOptInMOKeyword) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.defaultPublication==null && other.getDefaultPublication()==null) || 
             (this.defaultPublication!=null &&
              this.defaultPublication.equals(other.getDefaultPublication()))) &&
            ((this.invalidPublicationMessage==null && other.getInvalidPublicationMessage()==null) || 
             (this.invalidPublicationMessage!=null &&
              this.invalidPublicationMessage.equals(other.getInvalidPublicationMessage()))) &&
            ((this.invalidResponseMessage==null && other.getInvalidResponseMessage()==null) || 
             (this.invalidResponseMessage!=null &&
              this.invalidResponseMessage.equals(other.getInvalidResponseMessage()))) &&
            ((this.missingPublicationMessage==null && other.getMissingPublicationMessage()==null) || 
             (this.missingPublicationMessage!=null &&
              this.missingPublicationMessage.equals(other.getMissingPublicationMessage()))) &&
            ((this.needPublicationMessage==null && other.getNeedPublicationMessage()==null) || 
             (this.needPublicationMessage!=null &&
              this.needPublicationMessage.equals(other.getNeedPublicationMessage()))) &&
            ((this.promptMessage==null && other.getPromptMessage()==null) || 
             (this.promptMessage!=null &&
              this.promptMessage.equals(other.getPromptMessage()))) &&
            ((this.successMessage==null && other.getSuccessMessage()==null) || 
             (this.successMessage!=null &&
              this.successMessage.equals(other.getSuccessMessage()))) &&
            ((this.unexpectedErrorMessage==null && other.getUnexpectedErrorMessage()==null) || 
             (this.unexpectedErrorMessage!=null &&
              this.unexpectedErrorMessage.equals(other.getUnexpectedErrorMessage()))) &&
            ((this.validPublications==null && other.getValidPublications()==null) || 
             (this.validPublications!=null &&
              java.util.Arrays.equals(this.validPublications, other.getValidPublications()))) &&
            ((this.validResponses==null && other.getValidResponses()==null) || 
             (this.validResponses!=null &&
              java.util.Arrays.equals(this.validResponses, other.getValidResponses())));
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
        if (getDefaultPublication() != null) {
            _hashCode += getDefaultPublication().hashCode();
        }
        if (getInvalidPublicationMessage() != null) {
            _hashCode += getInvalidPublicationMessage().hashCode();
        }
        if (getInvalidResponseMessage() != null) {
            _hashCode += getInvalidResponseMessage().hashCode();
        }
        if (getMissingPublicationMessage() != null) {
            _hashCode += getMissingPublicationMessage().hashCode();
        }
        if (getNeedPublicationMessage() != null) {
            _hashCode += getNeedPublicationMessage().hashCode();
        }
        if (getPromptMessage() != null) {
            _hashCode += getPromptMessage().hashCode();
        }
        if (getSuccessMessage() != null) {
            _hashCode += getSuccessMessage().hashCode();
        }
        if (getUnexpectedErrorMessage() != null) {
            _hashCode += getUnexpectedErrorMessage().hashCode();
        }
        if (getValidPublications() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getValidPublications());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getValidPublications(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getValidResponses() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getValidResponses());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getValidResponses(), i);
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
        new org.apache.axis.description.TypeDesc(DoubleOptInMOKeyword.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DoubleOptInMOKeyword"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("defaultPublication");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DefaultPublication"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "List"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("invalidPublicationMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "InvalidPublicationMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("invalidResponseMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "InvalidResponseMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("missingPublicationMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MissingPublicationMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("needPublicationMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "NeedPublicationMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("promptMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PromptMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("successMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SuccessMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unexpectedErrorMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "UnexpectedErrorMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("validPublications");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ValidPublications"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "List"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ValidPublication"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("validResponses");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ValidResponses"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ValidResponse"));
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
