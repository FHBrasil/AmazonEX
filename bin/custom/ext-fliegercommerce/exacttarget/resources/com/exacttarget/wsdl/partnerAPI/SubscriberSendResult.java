/**
 * SubscriberSendResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class SubscriberSendResult  extends com.exacttarget.wsdl.partnerAPI.APIObject  implements java.io.Serializable {
    private com.exacttarget.wsdl.partnerAPI.Send send;

    private com.exacttarget.wsdl.partnerAPI.Email email;

    private com.exacttarget.wsdl.partnerAPI.Subscriber subscriber;

    private java.util.Calendar clickDate;

    private java.util.Calendar bounceDate;

    private java.util.Calendar openDate;

    private java.util.Calendar sentDate;

    private java.lang.String lastAction;

    private java.util.Calendar unsubscribeDate;

    private java.lang.String fromAddress;

    private java.lang.String fromName;

    private java.lang.Integer totalClicks;

    private java.lang.Integer uniqueClicks;

    private java.lang.String subject;

    private java.lang.String viewSentEmailURL;

    private java.lang.Integer hardBounces;

    private java.lang.Integer softBounces;

    private java.lang.Integer otherBounces;

    public SubscriberSendResult() {
    }

    public SubscriberSendResult(
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
           com.exacttarget.wsdl.partnerAPI.Send send,
           com.exacttarget.wsdl.partnerAPI.Email email,
           com.exacttarget.wsdl.partnerAPI.Subscriber subscriber,
           java.util.Calendar clickDate,
           java.util.Calendar bounceDate,
           java.util.Calendar openDate,
           java.util.Calendar sentDate,
           java.lang.String lastAction,
           java.util.Calendar unsubscribeDate,
           java.lang.String fromAddress,
           java.lang.String fromName,
           java.lang.Integer totalClicks,
           java.lang.Integer uniqueClicks,
           java.lang.String subject,
           java.lang.String viewSentEmailURL,
           java.lang.Integer hardBounces,
           java.lang.Integer softBounces,
           java.lang.Integer otherBounces) {
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
        this.send = send;
        this.email = email;
        this.subscriber = subscriber;
        this.clickDate = clickDate;
        this.bounceDate = bounceDate;
        this.openDate = openDate;
        this.sentDate = sentDate;
        this.lastAction = lastAction;
        this.unsubscribeDate = unsubscribeDate;
        this.fromAddress = fromAddress;
        this.fromName = fromName;
        this.totalClicks = totalClicks;
        this.uniqueClicks = uniqueClicks;
        this.subject = subject;
        this.viewSentEmailURL = viewSentEmailURL;
        this.hardBounces = hardBounces;
        this.softBounces = softBounces;
        this.otherBounces = otherBounces;
    }


    /**
     * Gets the send value for this SubscriberSendResult.
     * 
     * @return send
     */
    public com.exacttarget.wsdl.partnerAPI.Send getSend() {
        return send;
    }


    /**
     * Sets the send value for this SubscriberSendResult.
     * 
     * @param send
     */
    public void setSend(com.exacttarget.wsdl.partnerAPI.Send send) {
        this.send = send;
    }


    /**
     * Gets the email value for this SubscriberSendResult.
     * 
     * @return email
     */
    public com.exacttarget.wsdl.partnerAPI.Email getEmail() {
        return email;
    }


    /**
     * Sets the email value for this SubscriberSendResult.
     * 
     * @param email
     */
    public void setEmail(com.exacttarget.wsdl.partnerAPI.Email email) {
        this.email = email;
    }


    /**
     * Gets the subscriber value for this SubscriberSendResult.
     * 
     * @return subscriber
     */
    public com.exacttarget.wsdl.partnerAPI.Subscriber getSubscriber() {
        return subscriber;
    }


    /**
     * Sets the subscriber value for this SubscriberSendResult.
     * 
     * @param subscriber
     */
    public void setSubscriber(com.exacttarget.wsdl.partnerAPI.Subscriber subscriber) {
        this.subscriber = subscriber;
    }


    /**
     * Gets the clickDate value for this SubscriberSendResult.
     * 
     * @return clickDate
     */
    public java.util.Calendar getClickDate() {
        return clickDate;
    }


    /**
     * Sets the clickDate value for this SubscriberSendResult.
     * 
     * @param clickDate
     */
    public void setClickDate(java.util.Calendar clickDate) {
        this.clickDate = clickDate;
    }


    /**
     * Gets the bounceDate value for this SubscriberSendResult.
     * 
     * @return bounceDate
     */
    public java.util.Calendar getBounceDate() {
        return bounceDate;
    }


    /**
     * Sets the bounceDate value for this SubscriberSendResult.
     * 
     * @param bounceDate
     */
    public void setBounceDate(java.util.Calendar bounceDate) {
        this.bounceDate = bounceDate;
    }


    /**
     * Gets the openDate value for this SubscriberSendResult.
     * 
     * @return openDate
     */
    public java.util.Calendar getOpenDate() {
        return openDate;
    }


    /**
     * Sets the openDate value for this SubscriberSendResult.
     * 
     * @param openDate
     */
    public void setOpenDate(java.util.Calendar openDate) {
        this.openDate = openDate;
    }


    /**
     * Gets the sentDate value for this SubscriberSendResult.
     * 
     * @return sentDate
     */
    public java.util.Calendar getSentDate() {
        return sentDate;
    }


    /**
     * Sets the sentDate value for this SubscriberSendResult.
     * 
     * @param sentDate
     */
    public void setSentDate(java.util.Calendar sentDate) {
        this.sentDate = sentDate;
    }


    /**
     * Gets the lastAction value for this SubscriberSendResult.
     * 
     * @return lastAction
     */
    public java.lang.String getLastAction() {
        return lastAction;
    }


    /**
     * Sets the lastAction value for this SubscriberSendResult.
     * 
     * @param lastAction
     */
    public void setLastAction(java.lang.String lastAction) {
        this.lastAction = lastAction;
    }


    /**
     * Gets the unsubscribeDate value for this SubscriberSendResult.
     * 
     * @return unsubscribeDate
     */
    public java.util.Calendar getUnsubscribeDate() {
        return unsubscribeDate;
    }


    /**
     * Sets the unsubscribeDate value for this SubscriberSendResult.
     * 
     * @param unsubscribeDate
     */
    public void setUnsubscribeDate(java.util.Calendar unsubscribeDate) {
        this.unsubscribeDate = unsubscribeDate;
    }


    /**
     * Gets the fromAddress value for this SubscriberSendResult.
     * 
     * @return fromAddress
     */
    public java.lang.String getFromAddress() {
        return fromAddress;
    }


    /**
     * Sets the fromAddress value for this SubscriberSendResult.
     * 
     * @param fromAddress
     */
    public void setFromAddress(java.lang.String fromAddress) {
        this.fromAddress = fromAddress;
    }


    /**
     * Gets the fromName value for this SubscriberSendResult.
     * 
     * @return fromName
     */
    public java.lang.String getFromName() {
        return fromName;
    }


    /**
     * Sets the fromName value for this SubscriberSendResult.
     * 
     * @param fromName
     */
    public void setFromName(java.lang.String fromName) {
        this.fromName = fromName;
    }


    /**
     * Gets the totalClicks value for this SubscriberSendResult.
     * 
     * @return totalClicks
     */
    public java.lang.Integer getTotalClicks() {
        return totalClicks;
    }


    /**
     * Sets the totalClicks value for this SubscriberSendResult.
     * 
     * @param totalClicks
     */
    public void setTotalClicks(java.lang.Integer totalClicks) {
        this.totalClicks = totalClicks;
    }


    /**
     * Gets the uniqueClicks value for this SubscriberSendResult.
     * 
     * @return uniqueClicks
     */
    public java.lang.Integer getUniqueClicks() {
        return uniqueClicks;
    }


    /**
     * Sets the uniqueClicks value for this SubscriberSendResult.
     * 
     * @param uniqueClicks
     */
    public void setUniqueClicks(java.lang.Integer uniqueClicks) {
        this.uniqueClicks = uniqueClicks;
    }


    /**
     * Gets the subject value for this SubscriberSendResult.
     * 
     * @return subject
     */
    public java.lang.String getSubject() {
        return subject;
    }


    /**
     * Sets the subject value for this SubscriberSendResult.
     * 
     * @param subject
     */
    public void setSubject(java.lang.String subject) {
        this.subject = subject;
    }


    /**
     * Gets the viewSentEmailURL value for this SubscriberSendResult.
     * 
     * @return viewSentEmailURL
     */
    public java.lang.String getViewSentEmailURL() {
        return viewSentEmailURL;
    }


    /**
     * Sets the viewSentEmailURL value for this SubscriberSendResult.
     * 
     * @param viewSentEmailURL
     */
    public void setViewSentEmailURL(java.lang.String viewSentEmailURL) {
        this.viewSentEmailURL = viewSentEmailURL;
    }


    /**
     * Gets the hardBounces value for this SubscriberSendResult.
     * 
     * @return hardBounces
     */
    public java.lang.Integer getHardBounces() {
        return hardBounces;
    }


    /**
     * Sets the hardBounces value for this SubscriberSendResult.
     * 
     * @param hardBounces
     */
    public void setHardBounces(java.lang.Integer hardBounces) {
        this.hardBounces = hardBounces;
    }


    /**
     * Gets the softBounces value for this SubscriberSendResult.
     * 
     * @return softBounces
     */
    public java.lang.Integer getSoftBounces() {
        return softBounces;
    }


    /**
     * Sets the softBounces value for this SubscriberSendResult.
     * 
     * @param softBounces
     */
    public void setSoftBounces(java.lang.Integer softBounces) {
        this.softBounces = softBounces;
    }


    /**
     * Gets the otherBounces value for this SubscriberSendResult.
     * 
     * @return otherBounces
     */
    public java.lang.Integer getOtherBounces() {
        return otherBounces;
    }


    /**
     * Sets the otherBounces value for this SubscriberSendResult.
     * 
     * @param otherBounces
     */
    public void setOtherBounces(java.lang.Integer otherBounces) {
        this.otherBounces = otherBounces;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SubscriberSendResult)) return false;
        SubscriberSendResult other = (SubscriberSendResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.send==null && other.getSend()==null) || 
             (this.send!=null &&
              this.send.equals(other.getSend()))) &&
            ((this.email==null && other.getEmail()==null) || 
             (this.email!=null &&
              this.email.equals(other.getEmail()))) &&
            ((this.subscriber==null && other.getSubscriber()==null) || 
             (this.subscriber!=null &&
              this.subscriber.equals(other.getSubscriber()))) &&
            ((this.clickDate==null && other.getClickDate()==null) || 
             (this.clickDate!=null &&
              this.clickDate.equals(other.getClickDate()))) &&
            ((this.bounceDate==null && other.getBounceDate()==null) || 
             (this.bounceDate!=null &&
              this.bounceDate.equals(other.getBounceDate()))) &&
            ((this.openDate==null && other.getOpenDate()==null) || 
             (this.openDate!=null &&
              this.openDate.equals(other.getOpenDate()))) &&
            ((this.sentDate==null && other.getSentDate()==null) || 
             (this.sentDate!=null &&
              this.sentDate.equals(other.getSentDate()))) &&
            ((this.lastAction==null && other.getLastAction()==null) || 
             (this.lastAction!=null &&
              this.lastAction.equals(other.getLastAction()))) &&
            ((this.unsubscribeDate==null && other.getUnsubscribeDate()==null) || 
             (this.unsubscribeDate!=null &&
              this.unsubscribeDate.equals(other.getUnsubscribeDate()))) &&
            ((this.fromAddress==null && other.getFromAddress()==null) || 
             (this.fromAddress!=null &&
              this.fromAddress.equals(other.getFromAddress()))) &&
            ((this.fromName==null && other.getFromName()==null) || 
             (this.fromName!=null &&
              this.fromName.equals(other.getFromName()))) &&
            ((this.totalClicks==null && other.getTotalClicks()==null) || 
             (this.totalClicks!=null &&
              this.totalClicks.equals(other.getTotalClicks()))) &&
            ((this.uniqueClicks==null && other.getUniqueClicks()==null) || 
             (this.uniqueClicks!=null &&
              this.uniqueClicks.equals(other.getUniqueClicks()))) &&
            ((this.subject==null && other.getSubject()==null) || 
             (this.subject!=null &&
              this.subject.equals(other.getSubject()))) &&
            ((this.viewSentEmailURL==null && other.getViewSentEmailURL()==null) || 
             (this.viewSentEmailURL!=null &&
              this.viewSentEmailURL.equals(other.getViewSentEmailURL()))) &&
            ((this.hardBounces==null && other.getHardBounces()==null) || 
             (this.hardBounces!=null &&
              this.hardBounces.equals(other.getHardBounces()))) &&
            ((this.softBounces==null && other.getSoftBounces()==null) || 
             (this.softBounces!=null &&
              this.softBounces.equals(other.getSoftBounces()))) &&
            ((this.otherBounces==null && other.getOtherBounces()==null) || 
             (this.otherBounces!=null &&
              this.otherBounces.equals(other.getOtherBounces())));
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
        if (getSend() != null) {
            _hashCode += getSend().hashCode();
        }
        if (getEmail() != null) {
            _hashCode += getEmail().hashCode();
        }
        if (getSubscriber() != null) {
            _hashCode += getSubscriber().hashCode();
        }
        if (getClickDate() != null) {
            _hashCode += getClickDate().hashCode();
        }
        if (getBounceDate() != null) {
            _hashCode += getBounceDate().hashCode();
        }
        if (getOpenDate() != null) {
            _hashCode += getOpenDate().hashCode();
        }
        if (getSentDate() != null) {
            _hashCode += getSentDate().hashCode();
        }
        if (getLastAction() != null) {
            _hashCode += getLastAction().hashCode();
        }
        if (getUnsubscribeDate() != null) {
            _hashCode += getUnsubscribeDate().hashCode();
        }
        if (getFromAddress() != null) {
            _hashCode += getFromAddress().hashCode();
        }
        if (getFromName() != null) {
            _hashCode += getFromName().hashCode();
        }
        if (getTotalClicks() != null) {
            _hashCode += getTotalClicks().hashCode();
        }
        if (getUniqueClicks() != null) {
            _hashCode += getUniqueClicks().hashCode();
        }
        if (getSubject() != null) {
            _hashCode += getSubject().hashCode();
        }
        if (getViewSentEmailURL() != null) {
            _hashCode += getViewSentEmailURL().hashCode();
        }
        if (getHardBounces() != null) {
            _hashCode += getHardBounces().hashCode();
        }
        if (getSoftBounces() != null) {
            _hashCode += getSoftBounces().hashCode();
        }
        if (getOtherBounces() != null) {
            _hashCode += getOtherBounces().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SubscriberSendResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SubscriberSendResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("send");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Send"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Send"));
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
        elemField.setFieldName("subscriber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Subscriber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Subscriber"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clickDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ClickDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bounceDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "BounceDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("openDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "OpenDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sentDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SentDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastAction");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "LastAction"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unsubscribeDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "UnsubscribeDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fromAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FromAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fromName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FromName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalClicks");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TotalClicks"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("uniqueClicks");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "UniqueClicks"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subject");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Subject"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("viewSentEmailURL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ViewSentEmailURL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hardBounces");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "HardBounces"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("softBounces");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SoftBounces"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("otherBounces");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "OtherBounces"));
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
