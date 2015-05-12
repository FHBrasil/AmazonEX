/**
 * SuppressionListContext.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.flieger.exacttarget.wsdl.api;

public class SuppressionListContext  extends br.flieger.exacttarget.wsdl.api.APIObject  implements java.io.Serializable {
    private br.flieger.exacttarget.wsdl.api.SuppressionListContextEnum context;

    private br.flieger.exacttarget.wsdl.api.SendClassificationTypeEnum sendClassificationType;

    private br.flieger.exacttarget.wsdl.api.SendClassification sendClassification;

    private br.flieger.exacttarget.wsdl.api.Send send;

    private br.flieger.exacttarget.wsdl.api.SuppressionListDefinition definition;

    private java.lang.Boolean appliesToAllSends;

    private br.flieger.exacttarget.wsdl.api.SenderProfile senderProfile;

    public SuppressionListContext() {
    }

    public SuppressionListContext(
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
           br.flieger.exacttarget.wsdl.api.SuppressionListContextEnum context,
           br.flieger.exacttarget.wsdl.api.SendClassificationTypeEnum sendClassificationType,
           br.flieger.exacttarget.wsdl.api.SendClassification sendClassification,
           br.flieger.exacttarget.wsdl.api.Send send,
           br.flieger.exacttarget.wsdl.api.SuppressionListDefinition definition,
           java.lang.Boolean appliesToAllSends,
           br.flieger.exacttarget.wsdl.api.SenderProfile senderProfile) {
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
        this.context = context;
        this.sendClassificationType = sendClassificationType;
        this.sendClassification = sendClassification;
        this.send = send;
        this.definition = definition;
        this.appliesToAllSends = appliesToAllSends;
        this.senderProfile = senderProfile;
    }


    /**
     * Gets the context value for this SuppressionListContext.
     * 
     * @return context
     */
    public br.flieger.exacttarget.wsdl.api.SuppressionListContextEnum getContext() {
        return context;
    }


    /**
     * Sets the context value for this SuppressionListContext.
     * 
     * @param context
     */
    public void setContext(br.flieger.exacttarget.wsdl.api.SuppressionListContextEnum context) {
        this.context = context;
    }


    /**
     * Gets the sendClassificationType value for this SuppressionListContext.
     * 
     * @return sendClassificationType
     */
    public br.flieger.exacttarget.wsdl.api.SendClassificationTypeEnum getSendClassificationType() {
        return sendClassificationType;
    }


    /**
     * Sets the sendClassificationType value for this SuppressionListContext.
     * 
     * @param sendClassificationType
     */
    public void setSendClassificationType(br.flieger.exacttarget.wsdl.api.SendClassificationTypeEnum sendClassificationType) {
        this.sendClassificationType = sendClassificationType;
    }


    /**
     * Gets the sendClassification value for this SuppressionListContext.
     * 
     * @return sendClassification
     */
    public br.flieger.exacttarget.wsdl.api.SendClassification getSendClassification() {
        return sendClassification;
    }


    /**
     * Sets the sendClassification value for this SuppressionListContext.
     * 
     * @param sendClassification
     */
    public void setSendClassification(br.flieger.exacttarget.wsdl.api.SendClassification sendClassification) {
        this.sendClassification = sendClassification;
    }


    /**
     * Gets the send value for this SuppressionListContext.
     * 
     * @return send
     */
    public br.flieger.exacttarget.wsdl.api.Send getSend() {
        return send;
    }


    /**
     * Sets the send value for this SuppressionListContext.
     * 
     * @param send
     */
    public void setSend(br.flieger.exacttarget.wsdl.api.Send send) {
        this.send = send;
    }


    /**
     * Gets the definition value for this SuppressionListContext.
     * 
     * @return definition
     */
    public br.flieger.exacttarget.wsdl.api.SuppressionListDefinition getDefinition() {
        return definition;
    }


    /**
     * Sets the definition value for this SuppressionListContext.
     * 
     * @param definition
     */
    public void setDefinition(br.flieger.exacttarget.wsdl.api.SuppressionListDefinition definition) {
        this.definition = definition;
    }


    /**
     * Gets the appliesToAllSends value for this SuppressionListContext.
     * 
     * @return appliesToAllSends
     */
    public java.lang.Boolean getAppliesToAllSends() {
        return appliesToAllSends;
    }


    /**
     * Sets the appliesToAllSends value for this SuppressionListContext.
     * 
     * @param appliesToAllSends
     */
    public void setAppliesToAllSends(java.lang.Boolean appliesToAllSends) {
        this.appliesToAllSends = appliesToAllSends;
    }


    /**
     * Gets the senderProfile value for this SuppressionListContext.
     * 
     * @return senderProfile
     */
    public br.flieger.exacttarget.wsdl.api.SenderProfile getSenderProfile() {
        return senderProfile;
    }


    /**
     * Sets the senderProfile value for this SuppressionListContext.
     * 
     * @param senderProfile
     */
    public void setSenderProfile(br.flieger.exacttarget.wsdl.api.SenderProfile senderProfile) {
        this.senderProfile = senderProfile;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SuppressionListContext)) return false;
        SuppressionListContext other = (SuppressionListContext) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.context==null && other.getContext()==null) || 
             (this.context!=null &&
              this.context.equals(other.getContext()))) &&
            ((this.sendClassificationType==null && other.getSendClassificationType()==null) || 
             (this.sendClassificationType!=null &&
              this.sendClassificationType.equals(other.getSendClassificationType()))) &&
            ((this.sendClassification==null && other.getSendClassification()==null) || 
             (this.sendClassification!=null &&
              this.sendClassification.equals(other.getSendClassification()))) &&
            ((this.send==null && other.getSend()==null) || 
             (this.send!=null &&
              this.send.equals(other.getSend()))) &&
            ((this.definition==null && other.getDefinition()==null) || 
             (this.definition!=null &&
              this.definition.equals(other.getDefinition()))) &&
            ((this.appliesToAllSends==null && other.getAppliesToAllSends()==null) || 
             (this.appliesToAllSends!=null &&
              this.appliesToAllSends.equals(other.getAppliesToAllSends()))) &&
            ((this.senderProfile==null && other.getSenderProfile()==null) || 
             (this.senderProfile!=null &&
              this.senderProfile.equals(other.getSenderProfile())));
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
        if (getContext() != null) {
            _hashCode += getContext().hashCode();
        }
        if (getSendClassificationType() != null) {
            _hashCode += getSendClassificationType().hashCode();
        }
        if (getSendClassification() != null) {
            _hashCode += getSendClassification().hashCode();
        }
        if (getSend() != null) {
            _hashCode += getSend().hashCode();
        }
        if (getDefinition() != null) {
            _hashCode += getDefinition().hashCode();
        }
        if (getAppliesToAllSends() != null) {
            _hashCode += getAppliesToAllSends().hashCode();
        }
        if (getSenderProfile() != null) {
            _hashCode += getSenderProfile().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SuppressionListContext.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SuppressionListContext"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("context");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Context"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SuppressionListContextEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sendClassificationType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendClassificationType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendClassificationTypeEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sendClassification");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendClassification"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendClassification"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("send");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Send"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Send"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("definition");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Definition"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SuppressionListDefinition"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("appliesToAllSends");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AppliesToAllSends"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("senderProfile");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SenderProfile"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SenderProfile"));
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
