/**
 * TriggeredSendDefinition.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class TriggeredSendDefinition  extends com.exacttarget.wsdl.partnerAPI.SendDefinition  implements java.io.Serializable {
    /* Always will be set to Continuous. For additional fee, TriggeredSendDefinition.Priority
     * can be used to adjust priority. */
    private com.exacttarget.wsdl.partnerAPI.TriggeredSendTypeEnum triggeredSendType;

    private com.exacttarget.wsdl.partnerAPI.TriggeredSendStatusEnum triggeredSendStatus;

    private com.exacttarget.wsdl.partnerAPI.Email email;

    private com.exacttarget.wsdl.partnerAPI.List list;

    private java.lang.Boolean autoAddSubscribers;

    private java.lang.Boolean autoUpdateSubscribers;

    /* Always will be set to 1.  For additional fee, TriggeredSendDefinition.Priority
     * can be used to adjust priority. */
    private java.lang.Integer batchInterval;

    private java.lang.String bccEmail;

    private java.lang.String emailSubject;

    private java.lang.String dynamicEmailSubject;

    private java.lang.Boolean isMultipart;

    private java.lang.Boolean isWrapped;

    private java.lang.Short allowedSlots;

    private java.lang.Integer newSlotTrigger;

    private java.lang.Integer sendLimit;

    private org.apache.axis.types.Time sendWindowOpen;

    private org.apache.axis.types.Time sendWindowClose;

    private java.lang.Boolean sendWindowDelete;

    private java.lang.Boolean refreshContent;

    private java.lang.String exclusionFilter;

    private java.lang.String priority;

    /* Deprecated.  Use SendSourceDataExtension instead. */
    private java.lang.String sendSourceCustomerKey;

    private com.exacttarget.wsdl.partnerAPI.TriggeredSendExclusionList[] exclusionListCollection;

    private java.lang.String CCEmail;

    private com.exacttarget.wsdl.partnerAPI.DataExtension sendSourceDataExtension;

    private java.lang.Boolean isAlwaysOn;

    private java.lang.Boolean disableOnEmailBuildError;

    private java.lang.String preHeader;

    private java.lang.String replyToAddress;

    private java.lang.String replyToDisplayName;

    public TriggeredSendDefinition() {
    }

    public TriggeredSendDefinition(
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
           com.exacttarget.wsdl.partnerAPI.TriggeredSendTypeEnum triggeredSendType,
           com.exacttarget.wsdl.partnerAPI.TriggeredSendStatusEnum triggeredSendStatus,
           com.exacttarget.wsdl.partnerAPI.Email email,
           com.exacttarget.wsdl.partnerAPI.List list,
           java.lang.Boolean autoAddSubscribers,
           java.lang.Boolean autoUpdateSubscribers,
           java.lang.Integer batchInterval,
           java.lang.String bccEmail,
           java.lang.String emailSubject,
           java.lang.String dynamicEmailSubject,
           java.lang.Boolean isMultipart,
           java.lang.Boolean isWrapped,
           java.lang.Short allowedSlots,
           java.lang.Integer newSlotTrigger,
           java.lang.Integer sendLimit,
           org.apache.axis.types.Time sendWindowOpen,
           org.apache.axis.types.Time sendWindowClose,
           java.lang.Boolean sendWindowDelete,
           java.lang.Boolean refreshContent,
           java.lang.String exclusionFilter,
           java.lang.String priority,
           java.lang.String sendSourceCustomerKey,
           com.exacttarget.wsdl.partnerAPI.TriggeredSendExclusionList[] exclusionListCollection,
           java.lang.String CCEmail,
           com.exacttarget.wsdl.partnerAPI.DataExtension sendSourceDataExtension,
           java.lang.Boolean isAlwaysOn,
           java.lang.Boolean disableOnEmailBuildError,
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
        this.triggeredSendType = triggeredSendType;
        this.triggeredSendStatus = triggeredSendStatus;
        this.email = email;
        this.list = list;
        this.autoAddSubscribers = autoAddSubscribers;
        this.autoUpdateSubscribers = autoUpdateSubscribers;
        this.batchInterval = batchInterval;
        this.bccEmail = bccEmail;
        this.emailSubject = emailSubject;
        this.dynamicEmailSubject = dynamicEmailSubject;
        this.isMultipart = isMultipart;
        this.isWrapped = isWrapped;
        this.allowedSlots = allowedSlots;
        this.newSlotTrigger = newSlotTrigger;
        this.sendLimit = sendLimit;
        this.sendWindowOpen = sendWindowOpen;
        this.sendWindowClose = sendWindowClose;
        this.sendWindowDelete = sendWindowDelete;
        this.refreshContent = refreshContent;
        this.exclusionFilter = exclusionFilter;
        this.priority = priority;
        this.sendSourceCustomerKey = sendSourceCustomerKey;
        this.exclusionListCollection = exclusionListCollection;
        this.CCEmail = CCEmail;
        this.sendSourceDataExtension = sendSourceDataExtension;
        this.isAlwaysOn = isAlwaysOn;
        this.disableOnEmailBuildError = disableOnEmailBuildError;
        this.preHeader = preHeader;
        this.replyToAddress = replyToAddress;
        this.replyToDisplayName = replyToDisplayName;
    }


    /**
     * Gets the triggeredSendType value for this TriggeredSendDefinition.
     * 
     * @return triggeredSendType   * Always will be set to Continuous. For additional fee, TriggeredSendDefinition.Priority
     * can be used to adjust priority.
     */
    public com.exacttarget.wsdl.partnerAPI.TriggeredSendTypeEnum getTriggeredSendType() {
        return triggeredSendType;
    }


    /**
     * Sets the triggeredSendType value for this TriggeredSendDefinition.
     * 
     * @param triggeredSendType   * Always will be set to Continuous. For additional fee, TriggeredSendDefinition.Priority
     * can be used to adjust priority.
     */
    public void setTriggeredSendType(com.exacttarget.wsdl.partnerAPI.TriggeredSendTypeEnum triggeredSendType) {
        this.triggeredSendType = triggeredSendType;
    }


    /**
     * Gets the triggeredSendStatus value for this TriggeredSendDefinition.
     * 
     * @return triggeredSendStatus
     */
    public com.exacttarget.wsdl.partnerAPI.TriggeredSendStatusEnum getTriggeredSendStatus() {
        return triggeredSendStatus;
    }


    /**
     * Sets the triggeredSendStatus value for this TriggeredSendDefinition.
     * 
     * @param triggeredSendStatus
     */
    public void setTriggeredSendStatus(com.exacttarget.wsdl.partnerAPI.TriggeredSendStatusEnum triggeredSendStatus) {
        this.triggeredSendStatus = triggeredSendStatus;
    }


    /**
     * Gets the email value for this TriggeredSendDefinition.
     * 
     * @return email
     */
    public com.exacttarget.wsdl.partnerAPI.Email getEmail() {
        return email;
    }


    /**
     * Sets the email value for this TriggeredSendDefinition.
     * 
     * @param email
     */
    public void setEmail(com.exacttarget.wsdl.partnerAPI.Email email) {
        this.email = email;
    }


    /**
     * Gets the list value for this TriggeredSendDefinition.
     * 
     * @return list
     */
    public com.exacttarget.wsdl.partnerAPI.List getList() {
        return list;
    }


    /**
     * Sets the list value for this TriggeredSendDefinition.
     * 
     * @param list
     */
    public void setList(com.exacttarget.wsdl.partnerAPI.List list) {
        this.list = list;
    }


    /**
     * Gets the autoAddSubscribers value for this TriggeredSendDefinition.
     * 
     * @return autoAddSubscribers
     */
    public java.lang.Boolean getAutoAddSubscribers() {
        return autoAddSubscribers;
    }


    /**
     * Sets the autoAddSubscribers value for this TriggeredSendDefinition.
     * 
     * @param autoAddSubscribers
     */
    public void setAutoAddSubscribers(java.lang.Boolean autoAddSubscribers) {
        this.autoAddSubscribers = autoAddSubscribers;
    }


    /**
     * Gets the autoUpdateSubscribers value for this TriggeredSendDefinition.
     * 
     * @return autoUpdateSubscribers
     */
    public java.lang.Boolean getAutoUpdateSubscribers() {
        return autoUpdateSubscribers;
    }


    /**
     * Sets the autoUpdateSubscribers value for this TriggeredSendDefinition.
     * 
     * @param autoUpdateSubscribers
     */
    public void setAutoUpdateSubscribers(java.lang.Boolean autoUpdateSubscribers) {
        this.autoUpdateSubscribers = autoUpdateSubscribers;
    }


    /**
     * Gets the batchInterval value for this TriggeredSendDefinition.
     * 
     * @return batchInterval   * Always will be set to 1.  For additional fee, TriggeredSendDefinition.Priority
     * can be used to adjust priority.
     */
    public java.lang.Integer getBatchInterval() {
        return batchInterval;
    }


    /**
     * Sets the batchInterval value for this TriggeredSendDefinition.
     * 
     * @param batchInterval   * Always will be set to 1.  For additional fee, TriggeredSendDefinition.Priority
     * can be used to adjust priority.
     */
    public void setBatchInterval(java.lang.Integer batchInterval) {
        this.batchInterval = batchInterval;
    }


    /**
     * Gets the bccEmail value for this TriggeredSendDefinition.
     * 
     * @return bccEmail
     */
    public java.lang.String getBccEmail() {
        return bccEmail;
    }


    /**
     * Sets the bccEmail value for this TriggeredSendDefinition.
     * 
     * @param bccEmail
     */
    public void setBccEmail(java.lang.String bccEmail) {
        this.bccEmail = bccEmail;
    }


    /**
     * Gets the emailSubject value for this TriggeredSendDefinition.
     * 
     * @return emailSubject
     */
    public java.lang.String getEmailSubject() {
        return emailSubject;
    }


    /**
     * Sets the emailSubject value for this TriggeredSendDefinition.
     * 
     * @param emailSubject
     */
    public void setEmailSubject(java.lang.String emailSubject) {
        this.emailSubject = emailSubject;
    }


    /**
     * Gets the dynamicEmailSubject value for this TriggeredSendDefinition.
     * 
     * @return dynamicEmailSubject
     */
    public java.lang.String getDynamicEmailSubject() {
        return dynamicEmailSubject;
    }


    /**
     * Sets the dynamicEmailSubject value for this TriggeredSendDefinition.
     * 
     * @param dynamicEmailSubject
     */
    public void setDynamicEmailSubject(java.lang.String dynamicEmailSubject) {
        this.dynamicEmailSubject = dynamicEmailSubject;
    }


    /**
     * Gets the isMultipart value for this TriggeredSendDefinition.
     * 
     * @return isMultipart
     */
    public java.lang.Boolean getIsMultipart() {
        return isMultipart;
    }


    /**
     * Sets the isMultipart value for this TriggeredSendDefinition.
     * 
     * @param isMultipart
     */
    public void setIsMultipart(java.lang.Boolean isMultipart) {
        this.isMultipart = isMultipart;
    }


    /**
     * Gets the isWrapped value for this TriggeredSendDefinition.
     * 
     * @return isWrapped
     */
    public java.lang.Boolean getIsWrapped() {
        return isWrapped;
    }


    /**
     * Sets the isWrapped value for this TriggeredSendDefinition.
     * 
     * @param isWrapped
     */
    public void setIsWrapped(java.lang.Boolean isWrapped) {
        this.isWrapped = isWrapped;
    }


    /**
     * Gets the allowedSlots value for this TriggeredSendDefinition.
     * 
     * @return allowedSlots
     */
    public java.lang.Short getAllowedSlots() {
        return allowedSlots;
    }


    /**
     * Sets the allowedSlots value for this TriggeredSendDefinition.
     * 
     * @param allowedSlots
     */
    public void setAllowedSlots(java.lang.Short allowedSlots) {
        this.allowedSlots = allowedSlots;
    }


    /**
     * Gets the newSlotTrigger value for this TriggeredSendDefinition.
     * 
     * @return newSlotTrigger
     */
    public java.lang.Integer getNewSlotTrigger() {
        return newSlotTrigger;
    }


    /**
     * Sets the newSlotTrigger value for this TriggeredSendDefinition.
     * 
     * @param newSlotTrigger
     */
    public void setNewSlotTrigger(java.lang.Integer newSlotTrigger) {
        this.newSlotTrigger = newSlotTrigger;
    }


    /**
     * Gets the sendLimit value for this TriggeredSendDefinition.
     * 
     * @return sendLimit
     */
    public java.lang.Integer getSendLimit() {
        return sendLimit;
    }


    /**
     * Sets the sendLimit value for this TriggeredSendDefinition.
     * 
     * @param sendLimit
     */
    public void setSendLimit(java.lang.Integer sendLimit) {
        this.sendLimit = sendLimit;
    }


    /**
     * Gets the sendWindowOpen value for this TriggeredSendDefinition.
     * 
     * @return sendWindowOpen
     */
    public org.apache.axis.types.Time getSendWindowOpen() {
        return sendWindowOpen;
    }


    /**
     * Sets the sendWindowOpen value for this TriggeredSendDefinition.
     * 
     * @param sendWindowOpen
     */
    public void setSendWindowOpen(org.apache.axis.types.Time sendWindowOpen) {
        this.sendWindowOpen = sendWindowOpen;
    }


    /**
     * Gets the sendWindowClose value for this TriggeredSendDefinition.
     * 
     * @return sendWindowClose
     */
    public org.apache.axis.types.Time getSendWindowClose() {
        return sendWindowClose;
    }


    /**
     * Sets the sendWindowClose value for this TriggeredSendDefinition.
     * 
     * @param sendWindowClose
     */
    public void setSendWindowClose(org.apache.axis.types.Time sendWindowClose) {
        this.sendWindowClose = sendWindowClose;
    }


    /**
     * Gets the sendWindowDelete value for this TriggeredSendDefinition.
     * 
     * @return sendWindowDelete
     */
    public java.lang.Boolean getSendWindowDelete() {
        return sendWindowDelete;
    }


    /**
     * Sets the sendWindowDelete value for this TriggeredSendDefinition.
     * 
     * @param sendWindowDelete
     */
    public void setSendWindowDelete(java.lang.Boolean sendWindowDelete) {
        this.sendWindowDelete = sendWindowDelete;
    }


    /**
     * Gets the refreshContent value for this TriggeredSendDefinition.
     * 
     * @return refreshContent
     */
    public java.lang.Boolean getRefreshContent() {
        return refreshContent;
    }


    /**
     * Sets the refreshContent value for this TriggeredSendDefinition.
     * 
     * @param refreshContent
     */
    public void setRefreshContent(java.lang.Boolean refreshContent) {
        this.refreshContent = refreshContent;
    }


    /**
     * Gets the exclusionFilter value for this TriggeredSendDefinition.
     * 
     * @return exclusionFilter
     */
    public java.lang.String getExclusionFilter() {
        return exclusionFilter;
    }


    /**
     * Sets the exclusionFilter value for this TriggeredSendDefinition.
     * 
     * @param exclusionFilter
     */
    public void setExclusionFilter(java.lang.String exclusionFilter) {
        this.exclusionFilter = exclusionFilter;
    }


    /**
     * Gets the priority value for this TriggeredSendDefinition.
     * 
     * @return priority
     */
    public java.lang.String getPriority() {
        return priority;
    }


    /**
     * Sets the priority value for this TriggeredSendDefinition.
     * 
     * @param priority
     */
    public void setPriority(java.lang.String priority) {
        this.priority = priority;
    }


    /**
     * Gets the sendSourceCustomerKey value for this TriggeredSendDefinition.
     * 
     * @return sendSourceCustomerKey   * Deprecated.  Use SendSourceDataExtension instead.
     */
    public java.lang.String getSendSourceCustomerKey() {
        return sendSourceCustomerKey;
    }


    /**
     * Sets the sendSourceCustomerKey value for this TriggeredSendDefinition.
     * 
     * @param sendSourceCustomerKey   * Deprecated.  Use SendSourceDataExtension instead.
     */
    public void setSendSourceCustomerKey(java.lang.String sendSourceCustomerKey) {
        this.sendSourceCustomerKey = sendSourceCustomerKey;
    }


    /**
     * Gets the exclusionListCollection value for this TriggeredSendDefinition.
     * 
     * @return exclusionListCollection
     */
    public com.exacttarget.wsdl.partnerAPI.TriggeredSendExclusionList[] getExclusionListCollection() {
        return exclusionListCollection;
    }


    /**
     * Sets the exclusionListCollection value for this TriggeredSendDefinition.
     * 
     * @param exclusionListCollection
     */
    public void setExclusionListCollection(com.exacttarget.wsdl.partnerAPI.TriggeredSendExclusionList[] exclusionListCollection) {
        this.exclusionListCollection = exclusionListCollection;
    }

    public com.exacttarget.wsdl.partnerAPI.TriggeredSendExclusionList getExclusionListCollection(int i) {
        return this.exclusionListCollection[i];
    }

    public void setExclusionListCollection(int i, com.exacttarget.wsdl.partnerAPI.TriggeredSendExclusionList _value) {
        this.exclusionListCollection[i] = _value;
    }


    /**
     * Gets the CCEmail value for this TriggeredSendDefinition.
     * 
     * @return CCEmail
     */
    public java.lang.String getCCEmail() {
        return CCEmail;
    }


    /**
     * Sets the CCEmail value for this TriggeredSendDefinition.
     * 
     * @param CCEmail
     */
    public void setCCEmail(java.lang.String CCEmail) {
        this.CCEmail = CCEmail;
    }


    /**
     * Gets the sendSourceDataExtension value for this TriggeredSendDefinition.
     * 
     * @return sendSourceDataExtension
     */
    public com.exacttarget.wsdl.partnerAPI.DataExtension getSendSourceDataExtension() {
        return sendSourceDataExtension;
    }


    /**
     * Sets the sendSourceDataExtension value for this TriggeredSendDefinition.
     * 
     * @param sendSourceDataExtension
     */
    public void setSendSourceDataExtension(com.exacttarget.wsdl.partnerAPI.DataExtension sendSourceDataExtension) {
        this.sendSourceDataExtension = sendSourceDataExtension;
    }


    /**
     * Gets the isAlwaysOn value for this TriggeredSendDefinition.
     * 
     * @return isAlwaysOn
     */
    public java.lang.Boolean getIsAlwaysOn() {
        return isAlwaysOn;
    }


    /**
     * Sets the isAlwaysOn value for this TriggeredSendDefinition.
     * 
     * @param isAlwaysOn
     */
    public void setIsAlwaysOn(java.lang.Boolean isAlwaysOn) {
        this.isAlwaysOn = isAlwaysOn;
    }


    /**
     * Gets the disableOnEmailBuildError value for this TriggeredSendDefinition.
     * 
     * @return disableOnEmailBuildError
     */
    public java.lang.Boolean getDisableOnEmailBuildError() {
        return disableOnEmailBuildError;
    }


    /**
     * Sets the disableOnEmailBuildError value for this TriggeredSendDefinition.
     * 
     * @param disableOnEmailBuildError
     */
    public void setDisableOnEmailBuildError(java.lang.Boolean disableOnEmailBuildError) {
        this.disableOnEmailBuildError = disableOnEmailBuildError;
    }


    /**
     * Gets the preHeader value for this TriggeredSendDefinition.
     * 
     * @return preHeader
     */
    public java.lang.String getPreHeader() {
        return preHeader;
    }


    /**
     * Sets the preHeader value for this TriggeredSendDefinition.
     * 
     * @param preHeader
     */
    public void setPreHeader(java.lang.String preHeader) {
        this.preHeader = preHeader;
    }


    /**
     * Gets the replyToAddress value for this TriggeredSendDefinition.
     * 
     * @return replyToAddress
     */
    public java.lang.String getReplyToAddress() {
        return replyToAddress;
    }


    /**
     * Sets the replyToAddress value for this TriggeredSendDefinition.
     * 
     * @param replyToAddress
     */
    public void setReplyToAddress(java.lang.String replyToAddress) {
        this.replyToAddress = replyToAddress;
    }


    /**
     * Gets the replyToDisplayName value for this TriggeredSendDefinition.
     * 
     * @return replyToDisplayName
     */
    public java.lang.String getReplyToDisplayName() {
        return replyToDisplayName;
    }


    /**
     * Sets the replyToDisplayName value for this TriggeredSendDefinition.
     * 
     * @param replyToDisplayName
     */
    public void setReplyToDisplayName(java.lang.String replyToDisplayName) {
        this.replyToDisplayName = replyToDisplayName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TriggeredSendDefinition)) return false;
        TriggeredSendDefinition other = (TriggeredSendDefinition) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.triggeredSendType==null && other.getTriggeredSendType()==null) || 
             (this.triggeredSendType!=null &&
              this.triggeredSendType.equals(other.getTriggeredSendType()))) &&
            ((this.triggeredSendStatus==null && other.getTriggeredSendStatus()==null) || 
             (this.triggeredSendStatus!=null &&
              this.triggeredSendStatus.equals(other.getTriggeredSendStatus()))) &&
            ((this.email==null && other.getEmail()==null) || 
             (this.email!=null &&
              this.email.equals(other.getEmail()))) &&
            ((this.list==null && other.getList()==null) || 
             (this.list!=null &&
              this.list.equals(other.getList()))) &&
            ((this.autoAddSubscribers==null && other.getAutoAddSubscribers()==null) || 
             (this.autoAddSubscribers!=null &&
              this.autoAddSubscribers.equals(other.getAutoAddSubscribers()))) &&
            ((this.autoUpdateSubscribers==null && other.getAutoUpdateSubscribers()==null) || 
             (this.autoUpdateSubscribers!=null &&
              this.autoUpdateSubscribers.equals(other.getAutoUpdateSubscribers()))) &&
            ((this.batchInterval==null && other.getBatchInterval()==null) || 
             (this.batchInterval!=null &&
              this.batchInterval.equals(other.getBatchInterval()))) &&
            ((this.bccEmail==null && other.getBccEmail()==null) || 
             (this.bccEmail!=null &&
              this.bccEmail.equals(other.getBccEmail()))) &&
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
            ((this.allowedSlots==null && other.getAllowedSlots()==null) || 
             (this.allowedSlots!=null &&
              this.allowedSlots.equals(other.getAllowedSlots()))) &&
            ((this.newSlotTrigger==null && other.getNewSlotTrigger()==null) || 
             (this.newSlotTrigger!=null &&
              this.newSlotTrigger.equals(other.getNewSlotTrigger()))) &&
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
            ((this.refreshContent==null && other.getRefreshContent()==null) || 
             (this.refreshContent!=null &&
              this.refreshContent.equals(other.getRefreshContent()))) &&
            ((this.exclusionFilter==null && other.getExclusionFilter()==null) || 
             (this.exclusionFilter!=null &&
              this.exclusionFilter.equals(other.getExclusionFilter()))) &&
            ((this.priority==null && other.getPriority()==null) || 
             (this.priority!=null &&
              this.priority.equals(other.getPriority()))) &&
            ((this.sendSourceCustomerKey==null && other.getSendSourceCustomerKey()==null) || 
             (this.sendSourceCustomerKey!=null &&
              this.sendSourceCustomerKey.equals(other.getSendSourceCustomerKey()))) &&
            ((this.exclusionListCollection==null && other.getExclusionListCollection()==null) || 
             (this.exclusionListCollection!=null &&
              java.util.Arrays.equals(this.exclusionListCollection, other.getExclusionListCollection()))) &&
            ((this.CCEmail==null && other.getCCEmail()==null) || 
             (this.CCEmail!=null &&
              this.CCEmail.equals(other.getCCEmail()))) &&
            ((this.sendSourceDataExtension==null && other.getSendSourceDataExtension()==null) || 
             (this.sendSourceDataExtension!=null &&
              this.sendSourceDataExtension.equals(other.getSendSourceDataExtension()))) &&
            ((this.isAlwaysOn==null && other.getIsAlwaysOn()==null) || 
             (this.isAlwaysOn!=null &&
              this.isAlwaysOn.equals(other.getIsAlwaysOn()))) &&
            ((this.disableOnEmailBuildError==null && other.getDisableOnEmailBuildError()==null) || 
             (this.disableOnEmailBuildError!=null &&
              this.disableOnEmailBuildError.equals(other.getDisableOnEmailBuildError()))) &&
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
        if (getTriggeredSendType() != null) {
            _hashCode += getTriggeredSendType().hashCode();
        }
        if (getTriggeredSendStatus() != null) {
            _hashCode += getTriggeredSendStatus().hashCode();
        }
        if (getEmail() != null) {
            _hashCode += getEmail().hashCode();
        }
        if (getList() != null) {
            _hashCode += getList().hashCode();
        }
        if (getAutoAddSubscribers() != null) {
            _hashCode += getAutoAddSubscribers().hashCode();
        }
        if (getAutoUpdateSubscribers() != null) {
            _hashCode += getAutoUpdateSubscribers().hashCode();
        }
        if (getBatchInterval() != null) {
            _hashCode += getBatchInterval().hashCode();
        }
        if (getBccEmail() != null) {
            _hashCode += getBccEmail().hashCode();
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
        if (getAllowedSlots() != null) {
            _hashCode += getAllowedSlots().hashCode();
        }
        if (getNewSlotTrigger() != null) {
            _hashCode += getNewSlotTrigger().hashCode();
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
        if (getRefreshContent() != null) {
            _hashCode += getRefreshContent().hashCode();
        }
        if (getExclusionFilter() != null) {
            _hashCode += getExclusionFilter().hashCode();
        }
        if (getPriority() != null) {
            _hashCode += getPriority().hashCode();
        }
        if (getSendSourceCustomerKey() != null) {
            _hashCode += getSendSourceCustomerKey().hashCode();
        }
        if (getExclusionListCollection() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getExclusionListCollection());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getExclusionListCollection(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCCEmail() != null) {
            _hashCode += getCCEmail().hashCode();
        }
        if (getSendSourceDataExtension() != null) {
            _hashCode += getSendSourceDataExtension().hashCode();
        }
        if (getIsAlwaysOn() != null) {
            _hashCode += getIsAlwaysOn().hashCode();
        }
        if (getDisableOnEmailBuildError() != null) {
            _hashCode += getDisableOnEmailBuildError().hashCode();
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
        new org.apache.axis.description.TypeDesc(TriggeredSendDefinition.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TriggeredSendDefinition"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("triggeredSendType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TriggeredSendType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TriggeredSendTypeEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("triggeredSendStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TriggeredSendStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TriggeredSendStatusEnum"));
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
        elemField.setFieldName("list");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "List"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "List"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("autoAddSubscribers");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutoAddSubscribers"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("autoUpdateSubscribers");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutoUpdateSubscribers"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("batchInterval");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "BatchInterval"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
        elemField.setFieldName("allowedSlots");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AllowedSlots"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("newSlotTrigger");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "NewSlotTrigger"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
        elemField.setFieldName("refreshContent");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RefreshContent"));
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
        elemField.setFieldName("priority");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Priority"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sendSourceCustomerKey");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendSourceCustomerKey"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("exclusionListCollection");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExclusionListCollection"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TriggeredSendExclusionList"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CCEmail");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "CCEmail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sendSourceDataExtension");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendSourceDataExtension"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataExtension"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isAlwaysOn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsAlwaysOn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("disableOnEmailBuildError");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DisableOnEmailBuildError"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
