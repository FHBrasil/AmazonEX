/**
 * ReplyMailManagementConfiguration.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class ReplyMailManagementConfiguration  extends com.exacttarget.wsdl.partnerAPI.APIObject  implements java.io.Serializable {
    private java.lang.String emailDisplayName;

    private java.lang.String replySubdomain;

    private java.lang.String emailReplyAddress;

    private java.lang.Boolean DNSRedirectComplete;

    private java.lang.Boolean deleteAutoReplies;

    private java.lang.Boolean supportUnsubscribes;

    private java.lang.Boolean supportUnsubKeyword;

    private java.lang.Boolean supportUnsubscribeKeyword;

    private java.lang.Boolean supportRemoveKeyword;

    private java.lang.Boolean supportOptOutKeyword;

    private java.lang.Boolean supportLeaveKeyword;

    private java.lang.Boolean supportMisspelledKeywords;

    private java.lang.Boolean sendAutoReplies;

    private java.lang.String autoReplySubject;

    private java.lang.String autoReplyBody;

    private java.lang.String forwardingAddress;

    public ReplyMailManagementConfiguration() {
    }

    public ReplyMailManagementConfiguration(
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
           java.lang.String emailDisplayName,
           java.lang.String replySubdomain,
           java.lang.String emailReplyAddress,
           java.lang.Boolean DNSRedirectComplete,
           java.lang.Boolean deleteAutoReplies,
           java.lang.Boolean supportUnsubscribes,
           java.lang.Boolean supportUnsubKeyword,
           java.lang.Boolean supportUnsubscribeKeyword,
           java.lang.Boolean supportRemoveKeyword,
           java.lang.Boolean supportOptOutKeyword,
           java.lang.Boolean supportLeaveKeyword,
           java.lang.Boolean supportMisspelledKeywords,
           java.lang.Boolean sendAutoReplies,
           java.lang.String autoReplySubject,
           java.lang.String autoReplyBody,
           java.lang.String forwardingAddress) {
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
        this.emailDisplayName = emailDisplayName;
        this.replySubdomain = replySubdomain;
        this.emailReplyAddress = emailReplyAddress;
        this.DNSRedirectComplete = DNSRedirectComplete;
        this.deleteAutoReplies = deleteAutoReplies;
        this.supportUnsubscribes = supportUnsubscribes;
        this.supportUnsubKeyword = supportUnsubKeyword;
        this.supportUnsubscribeKeyword = supportUnsubscribeKeyword;
        this.supportRemoveKeyword = supportRemoveKeyword;
        this.supportOptOutKeyword = supportOptOutKeyword;
        this.supportLeaveKeyword = supportLeaveKeyword;
        this.supportMisspelledKeywords = supportMisspelledKeywords;
        this.sendAutoReplies = sendAutoReplies;
        this.autoReplySubject = autoReplySubject;
        this.autoReplyBody = autoReplyBody;
        this.forwardingAddress = forwardingAddress;
    }


    /**
     * Gets the emailDisplayName value for this ReplyMailManagementConfiguration.
     * 
     * @return emailDisplayName
     */
    public java.lang.String getEmailDisplayName() {
        return emailDisplayName;
    }


    /**
     * Sets the emailDisplayName value for this ReplyMailManagementConfiguration.
     * 
     * @param emailDisplayName
     */
    public void setEmailDisplayName(java.lang.String emailDisplayName) {
        this.emailDisplayName = emailDisplayName;
    }


    /**
     * Gets the replySubdomain value for this ReplyMailManagementConfiguration.
     * 
     * @return replySubdomain
     */
    public java.lang.String getReplySubdomain() {
        return replySubdomain;
    }


    /**
     * Sets the replySubdomain value for this ReplyMailManagementConfiguration.
     * 
     * @param replySubdomain
     */
    public void setReplySubdomain(java.lang.String replySubdomain) {
        this.replySubdomain = replySubdomain;
    }


    /**
     * Gets the emailReplyAddress value for this ReplyMailManagementConfiguration.
     * 
     * @return emailReplyAddress
     */
    public java.lang.String getEmailReplyAddress() {
        return emailReplyAddress;
    }


    /**
     * Sets the emailReplyAddress value for this ReplyMailManagementConfiguration.
     * 
     * @param emailReplyAddress
     */
    public void setEmailReplyAddress(java.lang.String emailReplyAddress) {
        this.emailReplyAddress = emailReplyAddress;
    }


    /**
     * Gets the DNSRedirectComplete value for this ReplyMailManagementConfiguration.
     * 
     * @return DNSRedirectComplete
     */
    public java.lang.Boolean getDNSRedirectComplete() {
        return DNSRedirectComplete;
    }


    /**
     * Sets the DNSRedirectComplete value for this ReplyMailManagementConfiguration.
     * 
     * @param DNSRedirectComplete
     */
    public void setDNSRedirectComplete(java.lang.Boolean DNSRedirectComplete) {
        this.DNSRedirectComplete = DNSRedirectComplete;
    }


    /**
     * Gets the deleteAutoReplies value for this ReplyMailManagementConfiguration.
     * 
     * @return deleteAutoReplies
     */
    public java.lang.Boolean getDeleteAutoReplies() {
        return deleteAutoReplies;
    }


    /**
     * Sets the deleteAutoReplies value for this ReplyMailManagementConfiguration.
     * 
     * @param deleteAutoReplies
     */
    public void setDeleteAutoReplies(java.lang.Boolean deleteAutoReplies) {
        this.deleteAutoReplies = deleteAutoReplies;
    }


    /**
     * Gets the supportUnsubscribes value for this ReplyMailManagementConfiguration.
     * 
     * @return supportUnsubscribes
     */
    public java.lang.Boolean getSupportUnsubscribes() {
        return supportUnsubscribes;
    }


    /**
     * Sets the supportUnsubscribes value for this ReplyMailManagementConfiguration.
     * 
     * @param supportUnsubscribes
     */
    public void setSupportUnsubscribes(java.lang.Boolean supportUnsubscribes) {
        this.supportUnsubscribes = supportUnsubscribes;
    }


    /**
     * Gets the supportUnsubKeyword value for this ReplyMailManagementConfiguration.
     * 
     * @return supportUnsubKeyword
     */
    public java.lang.Boolean getSupportUnsubKeyword() {
        return supportUnsubKeyword;
    }


    /**
     * Sets the supportUnsubKeyword value for this ReplyMailManagementConfiguration.
     * 
     * @param supportUnsubKeyword
     */
    public void setSupportUnsubKeyword(java.lang.Boolean supportUnsubKeyword) {
        this.supportUnsubKeyword = supportUnsubKeyword;
    }


    /**
     * Gets the supportUnsubscribeKeyword value for this ReplyMailManagementConfiguration.
     * 
     * @return supportUnsubscribeKeyword
     */
    public java.lang.Boolean getSupportUnsubscribeKeyword() {
        return supportUnsubscribeKeyword;
    }


    /**
     * Sets the supportUnsubscribeKeyword value for this ReplyMailManagementConfiguration.
     * 
     * @param supportUnsubscribeKeyword
     */
    public void setSupportUnsubscribeKeyword(java.lang.Boolean supportUnsubscribeKeyword) {
        this.supportUnsubscribeKeyword = supportUnsubscribeKeyword;
    }


    /**
     * Gets the supportRemoveKeyword value for this ReplyMailManagementConfiguration.
     * 
     * @return supportRemoveKeyword
     */
    public java.lang.Boolean getSupportRemoveKeyword() {
        return supportRemoveKeyword;
    }


    /**
     * Sets the supportRemoveKeyword value for this ReplyMailManagementConfiguration.
     * 
     * @param supportRemoveKeyword
     */
    public void setSupportRemoveKeyword(java.lang.Boolean supportRemoveKeyword) {
        this.supportRemoveKeyword = supportRemoveKeyword;
    }


    /**
     * Gets the supportOptOutKeyword value for this ReplyMailManagementConfiguration.
     * 
     * @return supportOptOutKeyword
     */
    public java.lang.Boolean getSupportOptOutKeyword() {
        return supportOptOutKeyword;
    }


    /**
     * Sets the supportOptOutKeyword value for this ReplyMailManagementConfiguration.
     * 
     * @param supportOptOutKeyword
     */
    public void setSupportOptOutKeyword(java.lang.Boolean supportOptOutKeyword) {
        this.supportOptOutKeyword = supportOptOutKeyword;
    }


    /**
     * Gets the supportLeaveKeyword value for this ReplyMailManagementConfiguration.
     * 
     * @return supportLeaveKeyword
     */
    public java.lang.Boolean getSupportLeaveKeyword() {
        return supportLeaveKeyword;
    }


    /**
     * Sets the supportLeaveKeyword value for this ReplyMailManagementConfiguration.
     * 
     * @param supportLeaveKeyword
     */
    public void setSupportLeaveKeyword(java.lang.Boolean supportLeaveKeyword) {
        this.supportLeaveKeyword = supportLeaveKeyword;
    }


    /**
     * Gets the supportMisspelledKeywords value for this ReplyMailManagementConfiguration.
     * 
     * @return supportMisspelledKeywords
     */
    public java.lang.Boolean getSupportMisspelledKeywords() {
        return supportMisspelledKeywords;
    }


    /**
     * Sets the supportMisspelledKeywords value for this ReplyMailManagementConfiguration.
     * 
     * @param supportMisspelledKeywords
     */
    public void setSupportMisspelledKeywords(java.lang.Boolean supportMisspelledKeywords) {
        this.supportMisspelledKeywords = supportMisspelledKeywords;
    }


    /**
     * Gets the sendAutoReplies value for this ReplyMailManagementConfiguration.
     * 
     * @return sendAutoReplies
     */
    public java.lang.Boolean getSendAutoReplies() {
        return sendAutoReplies;
    }


    /**
     * Sets the sendAutoReplies value for this ReplyMailManagementConfiguration.
     * 
     * @param sendAutoReplies
     */
    public void setSendAutoReplies(java.lang.Boolean sendAutoReplies) {
        this.sendAutoReplies = sendAutoReplies;
    }


    /**
     * Gets the autoReplySubject value for this ReplyMailManagementConfiguration.
     * 
     * @return autoReplySubject
     */
    public java.lang.String getAutoReplySubject() {
        return autoReplySubject;
    }


    /**
     * Sets the autoReplySubject value for this ReplyMailManagementConfiguration.
     * 
     * @param autoReplySubject
     */
    public void setAutoReplySubject(java.lang.String autoReplySubject) {
        this.autoReplySubject = autoReplySubject;
    }


    /**
     * Gets the autoReplyBody value for this ReplyMailManagementConfiguration.
     * 
     * @return autoReplyBody
     */
    public java.lang.String getAutoReplyBody() {
        return autoReplyBody;
    }


    /**
     * Sets the autoReplyBody value for this ReplyMailManagementConfiguration.
     * 
     * @param autoReplyBody
     */
    public void setAutoReplyBody(java.lang.String autoReplyBody) {
        this.autoReplyBody = autoReplyBody;
    }


    /**
     * Gets the forwardingAddress value for this ReplyMailManagementConfiguration.
     * 
     * @return forwardingAddress
     */
    public java.lang.String getForwardingAddress() {
        return forwardingAddress;
    }


    /**
     * Sets the forwardingAddress value for this ReplyMailManagementConfiguration.
     * 
     * @param forwardingAddress
     */
    public void setForwardingAddress(java.lang.String forwardingAddress) {
        this.forwardingAddress = forwardingAddress;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReplyMailManagementConfiguration)) return false;
        ReplyMailManagementConfiguration other = (ReplyMailManagementConfiguration) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.emailDisplayName==null && other.getEmailDisplayName()==null) || 
             (this.emailDisplayName!=null &&
              this.emailDisplayName.equals(other.getEmailDisplayName()))) &&
            ((this.replySubdomain==null && other.getReplySubdomain()==null) || 
             (this.replySubdomain!=null &&
              this.replySubdomain.equals(other.getReplySubdomain()))) &&
            ((this.emailReplyAddress==null && other.getEmailReplyAddress()==null) || 
             (this.emailReplyAddress!=null &&
              this.emailReplyAddress.equals(other.getEmailReplyAddress()))) &&
            ((this.DNSRedirectComplete==null && other.getDNSRedirectComplete()==null) || 
             (this.DNSRedirectComplete!=null &&
              this.DNSRedirectComplete.equals(other.getDNSRedirectComplete()))) &&
            ((this.deleteAutoReplies==null && other.getDeleteAutoReplies()==null) || 
             (this.deleteAutoReplies!=null &&
              this.deleteAutoReplies.equals(other.getDeleteAutoReplies()))) &&
            ((this.supportUnsubscribes==null && other.getSupportUnsubscribes()==null) || 
             (this.supportUnsubscribes!=null &&
              this.supportUnsubscribes.equals(other.getSupportUnsubscribes()))) &&
            ((this.supportUnsubKeyword==null && other.getSupportUnsubKeyword()==null) || 
             (this.supportUnsubKeyword!=null &&
              this.supportUnsubKeyword.equals(other.getSupportUnsubKeyword()))) &&
            ((this.supportUnsubscribeKeyword==null && other.getSupportUnsubscribeKeyword()==null) || 
             (this.supportUnsubscribeKeyword!=null &&
              this.supportUnsubscribeKeyword.equals(other.getSupportUnsubscribeKeyword()))) &&
            ((this.supportRemoveKeyword==null && other.getSupportRemoveKeyword()==null) || 
             (this.supportRemoveKeyword!=null &&
              this.supportRemoveKeyword.equals(other.getSupportRemoveKeyword()))) &&
            ((this.supportOptOutKeyword==null && other.getSupportOptOutKeyword()==null) || 
             (this.supportOptOutKeyword!=null &&
              this.supportOptOutKeyword.equals(other.getSupportOptOutKeyword()))) &&
            ((this.supportLeaveKeyword==null && other.getSupportLeaveKeyword()==null) || 
             (this.supportLeaveKeyword!=null &&
              this.supportLeaveKeyword.equals(other.getSupportLeaveKeyword()))) &&
            ((this.supportMisspelledKeywords==null && other.getSupportMisspelledKeywords()==null) || 
             (this.supportMisspelledKeywords!=null &&
              this.supportMisspelledKeywords.equals(other.getSupportMisspelledKeywords()))) &&
            ((this.sendAutoReplies==null && other.getSendAutoReplies()==null) || 
             (this.sendAutoReplies!=null &&
              this.sendAutoReplies.equals(other.getSendAutoReplies()))) &&
            ((this.autoReplySubject==null && other.getAutoReplySubject()==null) || 
             (this.autoReplySubject!=null &&
              this.autoReplySubject.equals(other.getAutoReplySubject()))) &&
            ((this.autoReplyBody==null && other.getAutoReplyBody()==null) || 
             (this.autoReplyBody!=null &&
              this.autoReplyBody.equals(other.getAutoReplyBody()))) &&
            ((this.forwardingAddress==null && other.getForwardingAddress()==null) || 
             (this.forwardingAddress!=null &&
              this.forwardingAddress.equals(other.getForwardingAddress())));
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
        if (getEmailDisplayName() != null) {
            _hashCode += getEmailDisplayName().hashCode();
        }
        if (getReplySubdomain() != null) {
            _hashCode += getReplySubdomain().hashCode();
        }
        if (getEmailReplyAddress() != null) {
            _hashCode += getEmailReplyAddress().hashCode();
        }
        if (getDNSRedirectComplete() != null) {
            _hashCode += getDNSRedirectComplete().hashCode();
        }
        if (getDeleteAutoReplies() != null) {
            _hashCode += getDeleteAutoReplies().hashCode();
        }
        if (getSupportUnsubscribes() != null) {
            _hashCode += getSupportUnsubscribes().hashCode();
        }
        if (getSupportUnsubKeyword() != null) {
            _hashCode += getSupportUnsubKeyword().hashCode();
        }
        if (getSupportUnsubscribeKeyword() != null) {
            _hashCode += getSupportUnsubscribeKeyword().hashCode();
        }
        if (getSupportRemoveKeyword() != null) {
            _hashCode += getSupportRemoveKeyword().hashCode();
        }
        if (getSupportOptOutKeyword() != null) {
            _hashCode += getSupportOptOutKeyword().hashCode();
        }
        if (getSupportLeaveKeyword() != null) {
            _hashCode += getSupportLeaveKeyword().hashCode();
        }
        if (getSupportMisspelledKeywords() != null) {
            _hashCode += getSupportMisspelledKeywords().hashCode();
        }
        if (getSendAutoReplies() != null) {
            _hashCode += getSendAutoReplies().hashCode();
        }
        if (getAutoReplySubject() != null) {
            _hashCode += getAutoReplySubject().hashCode();
        }
        if (getAutoReplyBody() != null) {
            _hashCode += getAutoReplyBody().hashCode();
        }
        if (getForwardingAddress() != null) {
            _hashCode += getForwardingAddress().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ReplyMailManagementConfiguration.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ReplyMailManagementConfiguration"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("emailDisplayName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "EmailDisplayName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("replySubdomain");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ReplySubdomain"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("emailReplyAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "EmailReplyAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DNSRedirectComplete");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DNSRedirectComplete"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deleteAutoReplies");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DeleteAutoReplies"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("supportUnsubscribes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SupportUnsubscribes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("supportUnsubKeyword");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SupportUnsubKeyword"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("supportUnsubscribeKeyword");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SupportUnsubscribeKeyword"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("supportRemoveKeyword");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SupportRemoveKeyword"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("supportOptOutKeyword");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SupportOptOutKeyword"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("supportLeaveKeyword");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SupportLeaveKeyword"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("supportMisspelledKeywords");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SupportMisspelledKeywords"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sendAutoReplies");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendAutoReplies"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("autoReplySubject");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutoReplySubject"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("autoReplyBody");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutoReplyBody"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("forwardingAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ForwardingAddress"));
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
