/**
 * TriggeredSend.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.flieger.exacttarget.wsdl.api;

public class TriggeredSend  extends br.flieger.exacttarget.wsdl.api.APIObject  implements java.io.Serializable {
    private br.flieger.exacttarget.wsdl.api.TriggeredSendDefinition triggeredSendDefinition;

    private br.flieger.exacttarget.wsdl.api.Subscriber[] subscribers;

    private br.flieger.exacttarget.wsdl.api.Attribute[] attributes;

    public TriggeredSend() {
    }

    public TriggeredSend(
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
           br.flieger.exacttarget.wsdl.api.TriggeredSendDefinition triggeredSendDefinition,
           br.flieger.exacttarget.wsdl.api.Subscriber[] subscribers,
           br.flieger.exacttarget.wsdl.api.Attribute[] attributes) {
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
        this.triggeredSendDefinition = triggeredSendDefinition;
        this.subscribers = subscribers;
        this.attributes = attributes;
    }


    /**
     * Gets the triggeredSendDefinition value for this TriggeredSend.
     * 
     * @return triggeredSendDefinition
     */
    public br.flieger.exacttarget.wsdl.api.TriggeredSendDefinition getTriggeredSendDefinition() {
        return triggeredSendDefinition;
    }


    /**
     * Sets the triggeredSendDefinition value for this TriggeredSend.
     * 
     * @param triggeredSendDefinition
     */
    public void setTriggeredSendDefinition(br.flieger.exacttarget.wsdl.api.TriggeredSendDefinition triggeredSendDefinition) {
        this.triggeredSendDefinition = triggeredSendDefinition;
    }


    /**
     * Gets the subscribers value for this TriggeredSend.
     * 
     * @return subscribers
     */
    public br.flieger.exacttarget.wsdl.api.Subscriber[] getSubscribers() {
        return subscribers;
    }


    /**
     * Sets the subscribers value for this TriggeredSend.
     * 
     * @param subscribers
     */
    public void setSubscribers(br.flieger.exacttarget.wsdl.api.Subscriber[] subscribers) {
        this.subscribers = subscribers;
    }

    public br.flieger.exacttarget.wsdl.api.Subscriber getSubscribers(int i) {
        return this.subscribers[i];
    }

    public void setSubscribers(int i, br.flieger.exacttarget.wsdl.api.Subscriber _value) {
        this.subscribers[i] = _value;
    }


    /**
     * Gets the attributes value for this TriggeredSend.
     * 
     * @return attributes
     */
    public br.flieger.exacttarget.wsdl.api.Attribute[] getAttributes() {
        return attributes;
    }


    /**
     * Sets the attributes value for this TriggeredSend.
     * 
     * @param attributes
     */
    public void setAttributes(br.flieger.exacttarget.wsdl.api.Attribute[] attributes) {
        this.attributes = attributes;
    }

    public br.flieger.exacttarget.wsdl.api.Attribute getAttributes(int i) {
        return this.attributes[i];
    }

    public void setAttributes(int i, br.flieger.exacttarget.wsdl.api.Attribute _value) {
        this.attributes[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TriggeredSend)) return false;
        TriggeredSend other = (TriggeredSend) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.triggeredSendDefinition==null && other.getTriggeredSendDefinition()==null) || 
             (this.triggeredSendDefinition!=null &&
              this.triggeredSendDefinition.equals(other.getTriggeredSendDefinition()))) &&
            ((this.subscribers==null && other.getSubscribers()==null) || 
             (this.subscribers!=null &&
              java.util.Arrays.equals(this.subscribers, other.getSubscribers()))) &&
            ((this.attributes==null && other.getAttributes()==null) || 
             (this.attributes!=null &&
              java.util.Arrays.equals(this.attributes, other.getAttributes())));
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
        if (getTriggeredSendDefinition() != null) {
            _hashCode += getTriggeredSendDefinition().hashCode();
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
        if (getAttributes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAttributes());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAttributes(), i);
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
        new org.apache.axis.description.TypeDesc(TriggeredSend.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TriggeredSend"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("triggeredSendDefinition");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TriggeredSendDefinition"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TriggeredSendDefinition"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subscribers");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Subscribers"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Subscriber"));
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("attributes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Attributes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Attribute"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
