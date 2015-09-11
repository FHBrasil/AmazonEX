/**
 * ContentValidation.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.flieger.exacttarget.wsdl.api;

public class ContentValidation  extends br.flieger.exacttarget.wsdl.api.APIObject  implements java.io.Serializable {
    private br.flieger.exacttarget.wsdl.api.ValidationAction validationAction;

    private br.flieger.exacttarget.wsdl.api.Email email;

    private br.flieger.exacttarget.wsdl.api.Subscriber[] subscribers;

    public ContentValidation() {
    }

    public ContentValidation(
           br.flieger.exacttarget.wsdl.api.ClientID client,
           java.lang.String partnerKey,
           br.flieger.exacttarget.wsdl.api.APIProperty[] partnerProperties,
           java.util.Calendar createdDate,
           java.util.Calendar modifiedDate,
           java.lang.Integer ID,
           java.lang.String objectID,
           java.lang.String customerKey,
           br.flieger.exacttarget.wsdl.api.Owner owner,
           java.lang.String correlationID,
           java.lang.String objectState,
           br.flieger.exacttarget.wsdl.api.ValidationAction validationAction,
           br.flieger.exacttarget.wsdl.api.Email email,
           br.flieger.exacttarget.wsdl.api.Subscriber[] subscribers) {
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
            objectState);
        this.validationAction = validationAction;
        this.email = email;
        this.subscribers = subscribers;
    }


    /**
     * Gets the validationAction value for this ContentValidation.
     * 
     * @return validationAction
     */
    public br.flieger.exacttarget.wsdl.api.ValidationAction getValidationAction() {
        return validationAction;
    }


    /**
     * Sets the validationAction value for this ContentValidation.
     * 
     * @param validationAction
     */
    public void setValidationAction(br.flieger.exacttarget.wsdl.api.ValidationAction validationAction) {
        this.validationAction = validationAction;
    }


    /**
     * Gets the email value for this ContentValidation.
     * 
     * @return email
     */
    public br.flieger.exacttarget.wsdl.api.Email getEmail() {
        return email;
    }


    /**
     * Sets the email value for this ContentValidation.
     * 
     * @param email
     */
    public void setEmail(br.flieger.exacttarget.wsdl.api.Email email) {
        this.email = email;
    }


    /**
     * Gets the subscribers value for this ContentValidation.
     * 
     * @return subscribers
     */
    public br.flieger.exacttarget.wsdl.api.Subscriber[] getSubscribers() {
        return subscribers;
    }


    /**
     * Sets the subscribers value for this ContentValidation.
     * 
     * @param subscribers
     */
    public void setSubscribers(br.flieger.exacttarget.wsdl.api.Subscriber[] subscribers) {
        this.subscribers = subscribers;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ContentValidation)) return false;
        ContentValidation other = (ContentValidation) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.validationAction==null && other.getValidationAction()==null) || 
             (this.validationAction!=null &&
              this.validationAction.equals(other.getValidationAction()))) &&
            ((this.email==null && other.getEmail()==null) || 
             (this.email!=null &&
              this.email.equals(other.getEmail()))) &&
            ((this.subscribers==null && other.getSubscribers()==null) || 
             (this.subscribers!=null &&
              java.util.Arrays.equals(this.subscribers, other.getSubscribers())));
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
        if (getValidationAction() != null) {
            _hashCode += getValidationAction().hashCode();
        }
        if (getEmail() != null) {
            _hashCode += getEmail().hashCode();
        }
        if (getSubscribers() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSubscribers());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSubscribers(), i);
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
        new org.apache.axis.description.TypeDesc(ContentValidation.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ContentValidation"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("validationAction");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ValidationAction"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ValidationAction"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("email");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Email"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Email"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subscribers");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Subscribers"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Subscriber"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Subscriber"));
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
