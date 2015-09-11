/**
 * TriggeredSendCreateResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.flieger.exacttarget.wsdl.api;

public class TriggeredSendCreateResult  extends br.flieger.exacttarget.wsdl.api.CreateResult  implements java.io.Serializable {
    private br.flieger.exacttarget.wsdl.api.SubscriberResult[] subscriberFailures;

    public TriggeredSendCreateResult() {
    }

    public TriggeredSendCreateResult(
           java.lang.String statusCode,
           java.lang.String statusMessage,
           java.lang.Integer ordinalID,
           java.lang.Integer errorCode,
           java.lang.String requestID,
           java.lang.String conversationID,
           java.lang.String overallStatusCode,
           br.flieger.exacttarget.wsdl.api.RequestType requestType,
           java.lang.String resultType,
           java.lang.String resultDetailXML,
           int newID,
           java.lang.String newObjectID,
           java.lang.String partnerKey,
           br.flieger.exacttarget.wsdl.api.APIObject object,
           br.flieger.exacttarget.wsdl.api.CreateResult[] createResults,
           java.lang.String parentPropertyName,
           br.flieger.exacttarget.wsdl.api.SubscriberResult[] subscriberFailures) {
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
            resultDetailXML,
            newID,
            newObjectID,
            partnerKey,
            object,
            createResults,
            parentPropertyName);
        this.subscriberFailures = subscriberFailures;
    }


    /**
     * Gets the subscriberFailures value for this TriggeredSendCreateResult.
     * 
     * @return subscriberFailures
     */
    public br.flieger.exacttarget.wsdl.api.SubscriberResult[] getSubscriberFailures() {
        return subscriberFailures;
    }


    /**
     * Sets the subscriberFailures value for this TriggeredSendCreateResult.
     * 
     * @param subscriberFailures
     */
    public void setSubscriberFailures(br.flieger.exacttarget.wsdl.api.SubscriberResult[] subscriberFailures) {
        this.subscriberFailures = subscriberFailures;
    }

    public br.flieger.exacttarget.wsdl.api.SubscriberResult getSubscriberFailures(int i) {
        return this.subscriberFailures[i];
    }

    public void setSubscriberFailures(int i, br.flieger.exacttarget.wsdl.api.SubscriberResult _value) {
        this.subscriberFailures[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TriggeredSendCreateResult)) return false;
        TriggeredSendCreateResult other = (TriggeredSendCreateResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.subscriberFailures==null && other.getSubscriberFailures()==null) || 
             (this.subscriberFailures!=null &&
              java.util.Arrays.equals(this.subscriberFailures, other.getSubscriberFailures())));
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
        if (getSubscriberFailures() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSubscriberFailures());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSubscriberFailures(), i);
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
        new org.apache.axis.description.TypeDesc(TriggeredSendCreateResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TriggeredSendCreateResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subscriberFailures");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SubscriberFailures"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SubscriberResult"));
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
