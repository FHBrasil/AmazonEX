/**
 * EmailSendDefinition.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class EmailSendDefinition  extends com.exacttarget.wsdl.partnerAPI.SendDefinition  implements java.io.Serializable {
    private com.exacttarget.wsdl.partnerAPI.SendDefinitionList[] sendDefinitionList;

    private com.exacttarget.wsdl.partnerAPI.Email email;

    private java.lang.String bccEmail;

    private java.lang.String autoBccEmail;

    private java.lang.String testEmailAddr;

    private java.lang.String emailSubject;

    private java.lang.String dynamicEmailSubject;

    private java.lang.Boolean isMultipart;

    private java.lang.Boolean isWrapped;

    private java.lang.Integer sendLimit;

    private org.apache.axis.types.Time sendWindowOpen;

    private org.apache.axis.types.Time sendWindowClose;

    private java.lang.Boolean sendWindowDelete;

    private java.lang.Boolean deduplicateByEmail;

    private java.lang.String exclusionFilter;

    private com.exacttarget.wsdl.partnerAPI.TrackingUser[] trackingUsers;

    private java.lang.String additional;

    private java.lang.String CCEmail;

    private org.apache.axis.types.Time deliveryScheduledTime;

    private com.exacttarget.wsdl.partnerAPI.MessageDeliveryTypeEnum messageDeliveryType;

    private java.lang.Boolean isSeedListSend;

    private com.exacttarget.wsdl.partnerAPI.TimeZone timeZone;

    private java.lang.Integer seedListOccurance;

    private java.lang.String preHeader;

    private java.lang.String replyToAddress;

    private java.lang.String replyToDisplayName;

    public EmailSendDefinition() {
    }

    public EmailSendDefinition(
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
           java.lang.String name,
           java.lang.String description,
           java.lang.String keyword,
           java.lang.String interactionObjectID,
           java.lang.Integer categoryID,
           com.exacttarget.wsdl.partnerAPI.SendClassification sendClassification,
           com.exacttarget.wsdl.partnerAPI.SenderProfile senderProfile,
           java.lang.String fromName,
           java.lang.String fromAddress,
           com.exacttarget.wsdl.partnerAPI.DeliveryProfile deliveryProfile,
           com.exacttarget.wsdl.partnerAPI.DeliveryProfileSourceAddressTypeEnum sourceAddressType,
           com.exacttarget.wsdl.partnerAPI.PrivateIP privateIP,
           com.exacttarget.wsdl.partnerAPI.DeliveryProfileDomainTypeEnum domainType,
           com.exacttarget.wsdl.partnerAPI.PrivateDomain privateDomain,
           com.exacttarget.wsdl.partnerAPI.SalutationSourceEnum headerSalutationSource,
           com.exacttarget.wsdl.partnerAPI.ContentArea headerContentArea,
           com.exacttarget.wsdl.partnerAPI.SalutationSourceEnum footerSalutationSource,
           com.exacttarget.wsdl.partnerAPI.ContentArea footerContentArea,
           java.lang.Boolean suppressTracking,
           java.lang.Boolean isSendLogging,
           com.exacttarget.wsdl.partnerAPI.SendDefinitionList[] sendDefinitionList,
           com.exacttarget.wsdl.partnerAPI.Email email,
           java.lang.String bccEmail,
           java.lang.String autoBccEmail,
           java.lang.String testEmailAddr,
           java.lang.String emailSubject,
           java.lang.String dynamicEmailSubject,
           java.lang.Boolean isMultipart,
           java.lang.Boolean isWrapped,
           java.lang.Integer sendLimit,
           org.apache.axis.types.Time sendWindowOpen,
           org.apache.axis.types.Time sendWindowClose,
           java.lang.Boolean sendWindowDelete,
           java.lang.Boolean deduplicateByEmail,
           java.lang.String exclusionFilter,
           com.exacttarget.wsdl.partnerAPI.TrackingUser[] trackingUsers,
           java.lang.String additional,
           java.lang.String CCEmail,
           org.apache.axis.types.Time deliveryScheduledTime,
           com.exacttarget.wsdl.partnerAPI.MessageDeliveryTypeEnum messageDeliveryType,
           java.lang.Boolean isSeedListSend,
           com.exacttarget.wsdl.partnerAPI.TimeZone timeZone,
           java.lang.Integer seedListOccurance,
           java.lang.String preHeader,
           java.lang.String replyToAddress,
           java.lang.String replyToDisplayName) {
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
            name,
            description,
            keyword,
            interactionObjectID,
            categoryID,
            sendClassification,
            senderProfile,
            fromName,
            fromAddress,
            deliveryProfile,
            sourceAddressType,
            privateIP,
            domainType,
            privateDomain,
            headerSalutationSource,
            headerContentArea,
            footerSalutationSource,
            footerContentArea,
            suppressTracking,
            isSendLogging);
        this.sendDefinitionList = sendDefinitionList;
        this.email = email;
        this.bccEmail = bccEmail;
        this.autoBccEmail = autoBccEmail;
        this.testEmailAddr = testEmailAddr;
        this.emailSubject = emailSubject;
        this.dynamicEmailSubject = dynamicEmailSubject;
        this.isMultipart = isMultipart;
        this.isWrapped = isWrapped;
        this.sendLimit = sendLimit;
        this.sendWindowOpen = sendWindowOpen;
        this.sendWindowClose = sendWindowClose;
        this.sendWindowDelete = sendWindowDelete;
        this.deduplicateByEmail = deduplicateByEmail;
        this.exclusionFilter = exclusionFilter;
        this.trackingUsers = trackingUsers;
        this.additional = additional;
        this.CCEmail = CCEmail;
        this.deliveryScheduledTime = deliveryScheduledTime;
        this.messageDeliveryType = messageDeliveryType;
        this.isSeedListSend = isSeedListSend;
        this.timeZone = timeZone;
        this.seedListOccurance = seedListOccurance;
        this.preHeader = preHeader;
        this.replyToAddress = replyToAddress;
        this.replyToDisplayName = replyToDisplayName;
    }


    /**
     * Gets the sendDefinitionList value for this EmailSendDefinition.
     * 
     * @return sendDefinitionList
     */
    public com.exacttarget.wsdl.partnerAPI.SendDefinitionList[] getSendDefinitionList() {
        return sendDefinitionList;
    }


    /**
     * Sets the sendDefinitionList value for this EmailSendDefinition.
     * 
     * @param sendDefinitionList
     */
    public void setSendDefinitionList(com.exacttarget.wsdl.partnerAPI.SendDefinitionList[] sendDefinitionList) {
        this.sendDefinitionList = sendDefinitionList;
    }

    public com.exacttarget.wsdl.partnerAPI.SendDefinitionList getSendDefinitionList(int i) {
        return this.sendDefinitionList[i];
    }

    public void setSendDefinitionList(int i, com.exacttarget.wsdl.partnerAPI.SendDefinitionList _value) {
        this.sendDefinitionList[i] = _value;
    }


    /**
     * Gets the email value for this EmailSendDefinition.
     * 
     * @return email
     */
    public com.exacttarget.wsdl.partnerAPI.Email getEmail() {
        return email;
    }


    /**
     * Sets the email value for this EmailSendDefinition.
     * 
     * @param email
     */
    public void setEmail(com.exacttarget.wsdl.partnerAPI.Email email) {
        this.email = email;
    }


    /**
     * Gets the bccEmail value for this EmailSendDefinition.
     * 
     * @return bccEmail
     */
    public java.lang.String getBccEmail() {
        return bccEmail;
    }


    /**
     * Sets the bccEmail value for this EmailSendDefinition.
     * 
     * @param bccEmail
     */
    public void setBccEmail(java.lang.String bccEmail) {
        this.bccEmail = bccEmail;
    }


    /**
     * Gets the autoBccEmail value for this EmailSendDefinition.
     * 
     * @return autoBccEmail
     */
    public java.lang.String getAutoBccEmail() {
        return autoBccEmail;
    }


    /**
     * Sets the autoBccEmail value for this EmailSendDefinition.
     * 
     * @param autoBccEmail
     */
    public void setAutoBccEmail(java.lang.String autoBccEmail) {
        this.autoBccEmail = autoBccEmail;
    }


    /**
     * Gets the testEmailAddr value for this EmailSendDefinition.
     * 
     * @return testEmailAddr
     */
    public java.lang.String getTestEmailAddr() {
        return testEmailAddr;
    }


    /**
     * Sets the testEmailAddr value for this EmailSendDefinition.
     * 
     * @param testEmailAddr
     */
    public void setTestEmailAddr(java.lang.String testEmailAddr) {
        this.testEmailAddr = testEmailAddr;
    }


    /**
     * Gets the emailSubject value for this EmailSendDefinition.
     * 
     * @return emailSubject
     */
    public java.lang.String getEmailSubject() {
        return emailSubject;
    }


    /**
     * Sets the emailSubject value for this EmailSendDefinition.
     * 
     * @param emailSubject
     */
    public void setEmailSubject(java.lang.String emailSubject) {
        this.emailSubject = emailSubject;
    }


    /**
     * Gets the dynamicEmailSubject value for this EmailSendDefinition.
     * 
     * @return dynamicEmailSubject
     */
    public java.lang.String getDynamicEmailSubject() {
        return dynamicEmailSubject;
    }


    /**
     * Sets the dynamicEmailSubject value for this EmailSendDefinition.
     * 
     * @param dynamicEmailSubject
     */
    public void setDynamicEmailSubject(java.lang.String dynamicEmailSubject) {
        this.dynamicEmailSubject = dynamicEmailSubject;
    }


    /**
     * Gets the isMultipart value for this EmailSendDefinition.
     * 
     * @return isMultipart
     */
    public java.lang.Boolean getIsMultipart() {
        return isMultipart;
    }


    /**
     * Sets the isMultipart value for this EmailSendDefinition.
     * 
     * @param isMultipart
     */
    public void setIsMultipart(java.lang.Boolean isMultipart) {
        this.isMultipart = isMultipart;
    }


    /**
     * Gets the isWrapped value for this EmailSendDefinition.
     * 
     * @return isWrapped
     */
    public java.lang.Boolean getIsWrapped() {
        return isWrapped;
    }


    /**
     * Sets the isWrapped value for this EmailSendDefinition.
     * 
     * @param isWrapped
     */
    public void setIsWrapped(java.lang.Boolean isWrapped) {
        this.isWrapped = isWrapped;
    }


    /**
     * Gets the sendLimit value for this EmailSendDefinition.
     * 
     * @return sendLimit
     */
    public java.lang.Integer getSendLimit() {
        return sendLimit;
    }


    /**
     * Sets the sendLimit value for this EmailSendDefinition.
     * 
     * @param sendLimit
     */
    public void setSendLimit(java.lang.Integer sendLimit) {
        this.sendLimit = sendLimit;
    }


    /**
     * Gets the sendWindowOpen value for this EmailSendDefinition.
     * 
     * @return sendWindowOpen
     */
    public org.apache.axis.types.Time getSendWindowOpen() {
        return sendWindowOpen;
    }


    /**
     * Sets the sendWindowOpen value for this EmailSendDefinition.
     * 
     * @param sendWindowOpen
     */
    public void setSendWindowOpen(org.apache.axis.types.Time sendWindowOpen) {
        this.sendWindowOpen = sendWindowOpen;
    }


    /**
     * Gets the sendWindowClose value for this EmailSendDefinition.
     * 
     * @return sendWindowClose
     */
    public org.apache.axis.types.Time getSendWindowClose() {
        return sendWindowClose;
    }


    /**
     * Sets the sendWindowClose value for this EmailSendDefinition.
     * 
     * @param sendWindowClose
     */
    public void setSendWindowClose(org.apache.axis.types.Time sendWindowClose) {
        this.sendWindowClose = sendWindowClose;
    }


    /**
     * Gets the sendWindowDelete value for this EmailSendDefinition.
     * 
     * @return sendWindowDelete
     */
    public java.lang.Boolean getSendWindowDelete() {
        return sendWindowDelete;
    }


    /**
     * Sets the sendWindowDelete value for this EmailSendDefinition.
     * 
     * @param sendWindowDelete
     */
    public void setSendWindowDelete(java.lang.Boolean sendWindowDelete) {
        this.sendWindowDelete = sendWindowDelete;
    }


    /**
     * Gets the deduplicateByEmail value for this EmailSendDefinition.
     * 
     * @return deduplicateByEmail
     */
    public java.lang.Boolean getDeduplicateByEmail() {
        return deduplicateByEmail;
    }


    /**
     * Sets the deduplicateByEmail value for this EmailSendDefinition.
     * 
     * @param deduplicateByEmail
     */
    public void setDeduplicateByEmail(java.lang.Boolean deduplicateByEmail) {
        this.deduplicateByEmail = deduplicateByEmail;
    }


    /**
     * Gets the exclusionFilter value for this EmailSendDefinition.
     * 
     * @return exclusionFilter
     */
    public java.lang.String getExclusionFilter() {
        return exclusionFilter;
    }


    /**
     * Sets the exclusionFilter value for this EmailSendDefinition.
     * 
     * @param exclusionFilter
     */
    public void setExclusionFilter(java.lang.String exclusionFilter) {
        this.exclusionFilter = exclusionFilter;
    }


    /**
     * Gets the trackingUsers value for this EmailSendDefinition.
     * 
     * @return trackingUsers
     */
    public com.exacttarget.wsdl.partnerAPI.TrackingUser[] getTrackingUsers() {
        return trackingUsers;
    }


    /**
     * Sets the trackingUsers value for this EmailSendDefinition.
     * 
     * @param trackingUsers
     */
    public void setTrackingUsers(com.exacttarget.wsdl.partnerAPI.TrackingUser[] trackingUsers) {
        this.trackingUsers = trackingUsers;
    }


    /**
     * Gets the additional value for this EmailSendDefinition.
     * 
     * @return additional
     */
    public java.lang.String getAdditional() {
        return additional;
    }


    /**
     * Sets the additional value for this EmailSendDefinition.
     * 
     * @param additional
     */
    public void setAdditional(java.lang.String additional) {
        this.additional = additional;
    }


    /**
     * Gets the CCEmail value for this EmailSendDefinition.
     * 
     * @return CCEmail
     */
    public java.lang.String getCCEmail() {
        return CCEmail;
    }


    /**
     * Sets the CCEmail value for this EmailSendDefinition.
     * 
     * @param CCEmail
     */
    public void setCCEmail(java.lang.String CCEmail) {
        this.CCEmail = CCEmail;
    }


    /**
     * Gets the deliveryScheduledTime value for this EmailSendDefinition.
     * 
     * @return deliveryScheduledTime
     */
    public org.apache.axis.types.Time getDeliveryScheduledTime() {
        return deliveryScheduledTime;
    }


    /**
     * Sets the deliveryScheduledTime value for this EmailSendDefinition.
     * 
     * @param deliveryScheduledTime
     */
    public void setDeliveryScheduledTime(org.apache.axis.types.Time deliveryScheduledTime) {
        this.deliveryScheduledTime = deliveryScheduledTime;
    }


    /**
     * Gets the messageDeliveryType value for this EmailSendDefinition.
     * 
     * @return messageDeliveryType
     */
    public com.exacttarget.wsdl.partnerAPI.MessageDeliveryTypeEnum getMessageDeliveryType() {
        return messageDeliveryType;
    }


    /**
     * Sets the messageDeliveryType value for this EmailSendDefinition.
     * 
     * @param messageDeliveryType
     */
    public void setMessageDeliveryType(com.exacttarget.wsdl.partnerAPI.MessageDeliveryTypeEnum messageDeliveryType) {
        this.messageDeliveryType = messageDeliveryType;
    }


    /**
     * Gets the isSeedListSend value for this EmailSendDefinition.
     * 
     * @return isSeedListSend
     */
    public java.lang.Boolean getIsSeedListSend() {
        return isSeedListSend;
    }


    /**
     * Sets the isSeedListSend value for this EmailSendDefinition.
     * 
     * @param isSeedListSend
     */
    public void setIsSeedListSend(java.lang.Boolean isSeedListSend) {
        this.isSeedListSend = isSeedListSend;
    }


    /**
     * Gets the timeZone value for this EmailSendDefinition.
     * 
     * @return timeZone
     */
    public com.exacttarget.wsdl.partnerAPI.TimeZone getTimeZone() {
        return timeZone;
    }


    /**
     * Sets the timeZone value for this EmailSendDefinition.
     * 
     * @param timeZone
     */
    public void setTimeZone(com.exacttarget.wsdl.partnerAPI.TimeZone timeZone) {
        this.timeZone = timeZone;
    }


    /**
     * Gets the seedListOccurance value for this EmailSendDefinition.
     * 
     * @return seedListOccurance
     */
    public java.lang.Integer getSeedListOccurance() {
        return seedListOccurance;
    }


    /**
     * Sets the seedListOccurance value for this EmailSendDefinition.
     * 
     * @param seedListOccurance
     */
    public void setSeedListOccurance(java.lang.Integer seedListOccurance) {
        this.seedListOccurance = seedListOccurance;
    }


    /**
     * Gets the preHeader value for this EmailSendDefinition.
     * 
     * @return preHeader
     */
    public java.lang.String getPreHeader() {
        return preHeader;
    }


    /**
     * Sets the preHeader value for this EmailSendDefinition.
     * 
     * @param preHeader
     */
    public void setPreHeader(java.lang.String preHeader) {
        this.preHeader = preHeader;
    }


    /**
     * Gets the replyToAddress value for this EmailSendDefinition.
     * 
     * @return replyToAddress
     */
    public java.lang.String getReplyToAddress() {
        return replyToAddress;
    }


    /**
     * Sets the replyToAddress value for this EmailSendDefinition.
     * 
     * @param replyToAddress
     */
    public void setReplyToAddress(java.lang.String replyToAddress) {
        this.replyToAddress = replyToAddress;
    }


    /**
     * Gets the replyToDisplayName value for this EmailSendDefinition.
     * 
     * @return replyToDisplayName
     */
    public java.lang.String getReplyToDisplayName() {
        return replyToDisplayName;
    }


    /**
     * Sets the replyToDisplayName value for this EmailSendDefinition.
     * 
     * @param replyToDisplayName
     */
    public void setReplyToDisplayName(java.lang.String replyToDisplayName) {
        this.replyToDisplayName = replyToDisplayName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EmailSendDefinition)) return false;
        EmailSendDefinition other = (EmailSendDefinition) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.sendDefinitionList==null && other.getSendDefinitionList()==null) || 
             (this.sendDefinitionList!=null &&
              java.util.Arrays.equals(this.sendDefinitionList, other.getSendDefinitionList()))) &&
            ((this.email==null && other.getEmail()==null) || 
             (this.email!=null &&
              this.email.equals(other.getEmail()))) &&
            ((this.bccEmail==null && other.getBccEmail()==null) || 
             (this.bccEmail!=null &&
              this.bccEmail.equals(other.getBccEmail()))) &&
            ((this.autoBccEmail==null && other.getAutoBccEmail()==null) || 
             (this.autoBccEmail!=null &&
              this.autoBccEmail.equals(other.getAutoBccEmail()))) &&
            ((this.testEmailAddr==null && other.getTestEmailAddr()==null) || 
             (this.testEmailAddr!=null &&
              this.testEmailAddr.equals(other.getTestEmailAddr()))) &&
            ((this.emailSubject==null && other.getEmailSubject()==null) || 
             (this.emailSubject!=null &&
              this.emailSubject.equals(other.getEmailSubject()))) &&
            ((this.dynamicEmailSubject==null && other.getDynamicEmailSubject()==null) || 
             (this.dynamicEmailSubject!=null &&
              this.dynamicEmailSubject.equals(other.getDynamicEmailSubject()))) &&
            ((this.isMultipart==null && other.getIsMultipart()==null) || 
             (this.isMultipart!=null &&
              this.isMultipart.equals(other.getIsMultipart()))) &&
            ((this.isWrapped==null && other.getIsWrapped()==null) || 
             (this.isWrapped!=null &&
              this.isWrapped.equals(other.getIsWrapped()))) &&
            ((this.sendLimit==null && other.getSendLimit()==null) || 
             (this.sendLimit!=null &&
              this.sendLimit.equals(other.getSendLimit()))) &&
            ((this.sendWindowOpen==null && other.getSendWindowOpen()==null) || 
             (this.sendWindowOpen!=null &&
              this.sendWindowOpen.equals(other.getSendWindowOpen()))) &&
            ((this.sendWindowClose==null && other.getSendWindowClose()==null) || 
             (this.sendWindowClose!=null &&
              this.sendWindowClose.equals(other.getSendWindowClose()))) &&
            ((this.sendWindowDelete==null && other.getSendWindowDelete()==null) || 
             (this.sendWindowDelete!=null &&
              this.sendWindowDelete.equals(other.getSendWindowDelete()))) &&
            ((this.deduplicateByEmail==null && other.getDeduplicateByEmail()==null) || 
             (this.deduplicateByEmail!=null &&
              this.deduplicateByEmail.equals(other.getDeduplicateByEmail()))) &&
            ((this.exclusionFilter==null && other.getExclusionFilter()==null) || 
             (this.exclusionFilter!=null &&
              this.exclusionFilter.equals(other.getExclusionFilter()))) &&
            ((this.trackingUsers==null && other.getTrackingUsers()==null) || 
             (this.trackingUsers!=null &&
              java.util.Arrays.equals(this.trackingUsers, other.getTrackingUsers()))) &&
            ((this.additional==null && other.getAdditional()==null) || 
             (this.additional!=null &&
              this.additional.equals(other.getAdditional()))) &&
            ((this.CCEmail==null && other.getCCEmail()==null) || 
             (this.CCEmail!=null &&
              this.CCEmail.equals(other.getCCEmail()))) &&
            ((this.deliveryScheduledTime==null && other.getDeliveryScheduledTime()==null) || 
             (this.deliveryScheduledTime!=null &&
              this.deliveryScheduledTime.equals(other.getDeliveryScheduledTime()))) &&
            ((this.messageDeliveryType==null && other.getMessageDeliveryType()==null) || 
             (this.messageDeliveryType!=null &&
              this.messageDeliveryType.equals(other.getMessageDeliveryType()))) &&
            ((this.isSeedListSend==null && other.getIsSeedListSend()==null) || 
             (this.isSeedListSend!=null &&
              this.isSeedListSend.equals(other.getIsSeedListSend()))) &&
            ((this.timeZone==null && other.getTimeZone()==null) || 
             (this.timeZone!=null &&
              this.timeZone.equals(other.getTimeZone()))) &&
            ((this.seedListOccurance==null && other.getSeedListOccurance()==null) || 
             (this.seedListOccurance!=null &&
              this.seedListOccurance.equals(other.getSeedListOccurance()))) &&
            ((this.preHeader==null && other.getPreHeader()==null) || 
             (this.preHeader!=null &&
              this.preHeader.equals(other.getPreHeader()))) &&
            ((this.replyToAddress==null && other.getReplyToAddress()==null) || 
             (this.replyToAddress!=null &&
              this.replyToAddress.equals(other.getReplyToAddress()))) &&
            ((this.replyToDisplayName==null && other.getReplyToDisplayName()==null) || 
             (this.replyToDisplayName!=null &&
              this.replyToDisplayName.equals(other.getReplyToDisplayName())));
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
        if (getSendDefinitionList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSendDefinitionList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSendDefinitionList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getEmail() != null) {
            _hashCode += getEmail().hashCode();
        }
        if (getBccEmail() != null) {
            _hashCode += getBccEmail().hashCode();
        }
        if (getAutoBccEmail() != null) {
            _hashCode += getAutoBccEmail().hashCode();
        }
        if (getTestEmailAddr() != null) {
            _hashCode += getTestEmailAddr().hashCode();
        }
        if (getEmailSubject() != null) {
            _hashCode += getEmailSubject().hashCode();
        }
        if (getDynamicEmailSubject() != null) {
            _hashCode += getDynamicEmailSubject().hashCode();
        }
        if (getIsMultipart() != null) {
            _hashCode += getIsMultipart().hashCode();
        }
        if (getIsWrapped() != null) {
            _hashCode += getIsWrapped().hashCode();
        }
        if (getSendLimit() != null) {
            _hashCode += getSendLimit().hashCode();
        }
        if (getSendWindowOpen() != null) {
            _hashCode += getSendWindowOpen().hashCode();
        }
        if (getSendWindowClose() != null) {
            _hashCode += getSendWindowClose().hashCode();
        }
        if (getSendWindowDelete() != null) {
            _hashCode += getSendWindowDelete().hashCode();
        }
        if (getDeduplicateByEmail() != null) {
            _hashCode += getDeduplicateByEmail().hashCode();
        }
        if (getExclusionFilter() != null) {
            _hashCode += getExclusionFilter().hashCode();
        }
        if (getTrackingUsers() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTrackingUsers());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTrackingUsers(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAdditional() != null) {
            _hashCode += getAdditional().hashCode();
        }
        if (getCCEmail() != null) {
            _hashCode += getCCEmail().hashCode();
        }
        if (getDeliveryScheduledTime() != null) {
            _hashCode += getDeliveryScheduledTime().hashCode();
        }
        if (getMessageDeliveryType() != null) {
            _hashCode += getMessageDeliveryType().hashCode();
        }
        if (getIsSeedListSend() != null) {
            _hashCode += getIsSeedListSend().hashCode();
        }
        if (getTimeZone() != null) {
            _hashCode += getTimeZone().hashCode();
        }
        if (getSeedListOccurance() != null) {
            _hashCode += getSeedListOccurance().hashCode();
        }
        if (getPreHeader() != null) {
            _hashCode += getPreHeader().hashCode();
        }
        if (getReplyToAddress() != null) {
            _hashCode += getReplyToAddress().hashCode();
        }
        if (getReplyToDisplayName() != null) {
            _hashCode += getReplyToDisplayName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EmailSendDefinition.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "EmailSendDefinition"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sendDefinitionList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendDefinitionList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendDefinitionList"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("email");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Email"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Email"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bccEmail");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "BccEmail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("autoBccEmail");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutoBccEmail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testEmailAddr");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TestEmailAddr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("emailSubject");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "EmailSubject"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dynamicEmailSubject");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DynamicEmailSubject"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isMultipart");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsMultipart"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isWrapped");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsWrapped"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sendLimit");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendLimit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sendWindowOpen");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendWindowOpen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "time"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sendWindowClose");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendWindowClose"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "time"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sendWindowDelete");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendWindowDelete"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deduplicateByEmail");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DeduplicateByEmail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("exclusionFilter");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExclusionFilter"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trackingUsers");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TrackingUsers"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TrackingUser"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TrackingUser"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("additional");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Additional"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CCEmail");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "CCEmail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deliveryScheduledTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DeliveryScheduledTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "time"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messageDeliveryType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MessageDeliveryType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MessageDeliveryTypeEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isSeedListSend");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsSeedListSend"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timeZone");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TimeZone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TimeZone"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("seedListOccurance");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SeedListOccurance"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("preHeader");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PreHeader"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("replyToAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ReplyToAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("replyToDisplayName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ReplyToDisplayName"));
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
