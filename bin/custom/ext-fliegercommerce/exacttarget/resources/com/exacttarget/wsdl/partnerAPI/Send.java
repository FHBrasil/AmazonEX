/**
 * Send.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class Send  extends com.exacttarget.wsdl.partnerAPI.APIObject  implements java.io.Serializable {
    private com.exacttarget.wsdl.partnerAPI.Email email;

    private com.exacttarget.wsdl.partnerAPI.List[] list;

    private java.util.Calendar sendDate;

    private java.lang.String fromAddress;

    private java.lang.String fromName;

    private java.lang.Integer duplicates;

    private java.lang.Integer invalidAddresses;

    private java.lang.Integer existingUndeliverables;

    private java.lang.Integer existingUnsubscribes;

    private java.lang.Integer hardBounces;

    private java.lang.Integer softBounces;

    private java.lang.Integer otherBounces;

    private java.lang.Integer forwardedEmails;

    private java.lang.Integer uniqueClicks;

    private java.lang.Integer uniqueOpens;

    private java.lang.Integer numberSent;

    private java.lang.Integer numberDelivered;

    private java.lang.Integer unsubscribes;

    private java.lang.Integer missingAddresses;

    private java.lang.String subject;

    private java.lang.String previewURL;

    private com.exacttarget.wsdl.partnerAPI.Link[] links;

    private com.exacttarget.wsdl.partnerAPI.TrackingEvent[] events;

    private java.util.Calendar sentDate;

    private java.lang.String emailName;

    private java.lang.String status;

    private java.lang.Boolean isMultipart;

    private java.lang.Integer sendLimit;

    private org.apache.axis.types.Time sendWindowOpen;

    private org.apache.axis.types.Time sendWindowClose;

    private java.lang.Boolean isAlwaysOn;

    private com.exacttarget.wsdl.partnerAPI.APIObject[] sources;

    private java.lang.Integer numberTargeted;

    private java.lang.Integer numberErrored;

    private java.lang.Integer numberExcluded;

    private java.lang.String additional;

    private java.lang.String bccEmail;

    private com.exacttarget.wsdl.partnerAPI.EmailSendDefinition emailSendDefinition;

    private com.exacttarget.wsdl.partnerAPI.AudienceItem[] suppressionLists;

    public Send() {
    }

    public Send(
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
           com.exacttarget.wsdl.partnerAPI.Email email,
           com.exacttarget.wsdl.partnerAPI.List[] list,
           java.util.Calendar sendDate,
           java.lang.String fromAddress,
           java.lang.String fromName,
           java.lang.Integer duplicates,
           java.lang.Integer invalidAddresses,
           java.lang.Integer existingUndeliverables,
           java.lang.Integer existingUnsubscribes,
           java.lang.Integer hardBounces,
           java.lang.Integer softBounces,
           java.lang.Integer otherBounces,
           java.lang.Integer forwardedEmails,
           java.lang.Integer uniqueClicks,
           java.lang.Integer uniqueOpens,
           java.lang.Integer numberSent,
           java.lang.Integer numberDelivered,
           java.lang.Integer unsubscribes,
           java.lang.Integer missingAddresses,
           java.lang.String subject,
           java.lang.String previewURL,
           com.exacttarget.wsdl.partnerAPI.Link[] links,
           com.exacttarget.wsdl.partnerAPI.TrackingEvent[] events,
           java.util.Calendar sentDate,
           java.lang.String emailName,
           java.lang.String status,
           java.lang.Boolean isMultipart,
           java.lang.Integer sendLimit,
           org.apache.axis.types.Time sendWindowOpen,
           org.apache.axis.types.Time sendWindowClose,
           java.lang.Boolean isAlwaysOn,
           com.exacttarget.wsdl.partnerAPI.APIObject[] sources,
           java.lang.Integer numberTargeted,
           java.lang.Integer numberErrored,
           java.lang.Integer numberExcluded,
           java.lang.String additional,
           java.lang.String bccEmail,
           com.exacttarget.wsdl.partnerAPI.EmailSendDefinition emailSendDefinition,
           com.exacttarget.wsdl.partnerAPI.AudienceItem[] suppressionLists) {
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
        this.email = email;
        this.list = list;
        this.sendDate = sendDate;
        this.fromAddress = fromAddress;
        this.fromName = fromName;
        this.duplicates = duplicates;
        this.invalidAddresses = invalidAddresses;
        this.existingUndeliverables = existingUndeliverables;
        this.existingUnsubscribes = existingUnsubscribes;
        this.hardBounces = hardBounces;
        this.softBounces = softBounces;
        this.otherBounces = otherBounces;
        this.forwardedEmails = forwardedEmails;
        this.uniqueClicks = uniqueClicks;
        this.uniqueOpens = uniqueOpens;
        this.numberSent = numberSent;
        this.numberDelivered = numberDelivered;
        this.unsubscribes = unsubscribes;
        this.missingAddresses = missingAddresses;
        this.subject = subject;
        this.previewURL = previewURL;
        this.links = links;
        this.events = events;
        this.sentDate = sentDate;
        this.emailName = emailName;
        this.status = status;
        this.isMultipart = isMultipart;
        this.sendLimit = sendLimit;
        this.sendWindowOpen = sendWindowOpen;
        this.sendWindowClose = sendWindowClose;
        this.isAlwaysOn = isAlwaysOn;
        this.sources = sources;
        this.numberTargeted = numberTargeted;
        this.numberErrored = numberErrored;
        this.numberExcluded = numberExcluded;
        this.additional = additional;
        this.bccEmail = bccEmail;
        this.emailSendDefinition = emailSendDefinition;
        this.suppressionLists = suppressionLists;
    }


    /**
     * Gets the email value for this Send.
     * 
     * @return email
     */
    public com.exacttarget.wsdl.partnerAPI.Email getEmail() {
        return email;
    }


    /**
     * Sets the email value for this Send.
     * 
     * @param email
     */
    public void setEmail(com.exacttarget.wsdl.partnerAPI.Email email) {
        this.email = email;
    }


    /**
     * Gets the list value for this Send.
     * 
     * @return list
     */
    public com.exacttarget.wsdl.partnerAPI.List[] getList() {
        return list;
    }


    /**
     * Sets the list value for this Send.
     * 
     * @param list
     */
    public void setList(com.exacttarget.wsdl.partnerAPI.List[] list) {
        this.list = list;
    }

    public com.exacttarget.wsdl.partnerAPI.List getList(int i) {
        return this.list[i];
    }

    public void setList(int i, com.exacttarget.wsdl.partnerAPI.List _value) {
        this.list[i] = _value;
    }


    /**
     * Gets the sendDate value for this Send.
     * 
     * @return sendDate
     */
    public java.util.Calendar getSendDate() {
        return sendDate;
    }


    /**
     * Sets the sendDate value for this Send.
     * 
     * @param sendDate
     */
    public void setSendDate(java.util.Calendar sendDate) {
        this.sendDate = sendDate;
    }


    /**
     * Gets the fromAddress value for this Send.
     * 
     * @return fromAddress
     */
    public java.lang.String getFromAddress() {
        return fromAddress;
    }


    /**
     * Sets the fromAddress value for this Send.
     * 
     * @param fromAddress
     */
    public void setFromAddress(java.lang.String fromAddress) {
        this.fromAddress = fromAddress;
    }


    /**
     * Gets the fromName value for this Send.
     * 
     * @return fromName
     */
    public java.lang.String getFromName() {
        return fromName;
    }


    /**
     * Sets the fromName value for this Send.
     * 
     * @param fromName
     */
    public void setFromName(java.lang.String fromName) {
        this.fromName = fromName;
    }


    /**
     * Gets the duplicates value for this Send.
     * 
     * @return duplicates
     */
    public java.lang.Integer getDuplicates() {
        return duplicates;
    }


    /**
     * Sets the duplicates value for this Send.
     * 
     * @param duplicates
     */
    public void setDuplicates(java.lang.Integer duplicates) {
        this.duplicates = duplicates;
    }


    /**
     * Gets the invalidAddresses value for this Send.
     * 
     * @return invalidAddresses
     */
    public java.lang.Integer getInvalidAddresses() {
        return invalidAddresses;
    }


    /**
     * Sets the invalidAddresses value for this Send.
     * 
     * @param invalidAddresses
     */
    public void setInvalidAddresses(java.lang.Integer invalidAddresses) {
        this.invalidAddresses = invalidAddresses;
    }


    /**
     * Gets the existingUndeliverables value for this Send.
     * 
     * @return existingUndeliverables
     */
    public java.lang.Integer getExistingUndeliverables() {
        return existingUndeliverables;
    }


    /**
     * Sets the existingUndeliverables value for this Send.
     * 
     * @param existingUndeliverables
     */
    public void setExistingUndeliverables(java.lang.Integer existingUndeliverables) {
        this.existingUndeliverables = existingUndeliverables;
    }


    /**
     * Gets the existingUnsubscribes value for this Send.
     * 
     * @return existingUnsubscribes
     */
    public java.lang.Integer getExistingUnsubscribes() {
        return existingUnsubscribes;
    }


    /**
     * Sets the existingUnsubscribes value for this Send.
     * 
     * @param existingUnsubscribes
     */
    public void setExistingUnsubscribes(java.lang.Integer existingUnsubscribes) {
        this.existingUnsubscribes = existingUnsubscribes;
    }


    /**
     * Gets the hardBounces value for this Send.
     * 
     * @return hardBounces
     */
    public java.lang.Integer getHardBounces() {
        return hardBounces;
    }


    /**
     * Sets the hardBounces value for this Send.
     * 
     * @param hardBounces
     */
    public void setHardBounces(java.lang.Integer hardBounces) {
        this.hardBounces = hardBounces;
    }


    /**
     * Gets the softBounces value for this Send.
     * 
     * @return softBounces
     */
    public java.lang.Integer getSoftBounces() {
        return softBounces;
    }


    /**
     * Sets the softBounces value for this Send.
     * 
     * @param softBounces
     */
    public void setSoftBounces(java.lang.Integer softBounces) {
        this.softBounces = softBounces;
    }


    /**
     * Gets the otherBounces value for this Send.
     * 
     * @return otherBounces
     */
    public java.lang.Integer getOtherBounces() {
        return otherBounces;
    }


    /**
     * Sets the otherBounces value for this Send.
     * 
     * @param otherBounces
     */
    public void setOtherBounces(java.lang.Integer otherBounces) {
        this.otherBounces = otherBounces;
    }


    /**
     * Gets the forwardedEmails value for this Send.
     * 
     * @return forwardedEmails
     */
    public java.lang.Integer getForwardedEmails() {
        return forwardedEmails;
    }


    /**
     * Sets the forwardedEmails value for this Send.
     * 
     * @param forwardedEmails
     */
    public void setForwardedEmails(java.lang.Integer forwardedEmails) {
        this.forwardedEmails = forwardedEmails;
    }


    /**
     * Gets the uniqueClicks value for this Send.
     * 
     * @return uniqueClicks
     */
    public java.lang.Integer getUniqueClicks() {
        return uniqueClicks;
    }


    /**
     * Sets the uniqueClicks value for this Send.
     * 
     * @param uniqueClicks
     */
    public void setUniqueClicks(java.lang.Integer uniqueClicks) {
        this.uniqueClicks = uniqueClicks;
    }


    /**
     * Gets the uniqueOpens value for this Send.
     * 
     * @return uniqueOpens
     */
    public java.lang.Integer getUniqueOpens() {
        return uniqueOpens;
    }


    /**
     * Sets the uniqueOpens value for this Send.
     * 
     * @param uniqueOpens
     */
    public void setUniqueOpens(java.lang.Integer uniqueOpens) {
        this.uniqueOpens = uniqueOpens;
    }


    /**
     * Gets the numberSent value for this Send.
     * 
     * @return numberSent
     */
    public java.lang.Integer getNumberSent() {
        return numberSent;
    }


    /**
     * Sets the numberSent value for this Send.
     * 
     * @param numberSent
     */
    public void setNumberSent(java.lang.Integer numberSent) {
        this.numberSent = numberSent;
    }


    /**
     * Gets the numberDelivered value for this Send.
     * 
     * @return numberDelivered
     */
    public java.lang.Integer getNumberDelivered() {
        return numberDelivered;
    }


    /**
     * Sets the numberDelivered value for this Send.
     * 
     * @param numberDelivered
     */
    public void setNumberDelivered(java.lang.Integer numberDelivered) {
        this.numberDelivered = numberDelivered;
    }


    /**
     * Gets the unsubscribes value for this Send.
     * 
     * @return unsubscribes
     */
    public java.lang.Integer getUnsubscribes() {
        return unsubscribes;
    }


    /**
     * Sets the unsubscribes value for this Send.
     * 
     * @param unsubscribes
     */
    public void setUnsubscribes(java.lang.Integer unsubscribes) {
        this.unsubscribes = unsubscribes;
    }


    /**
     * Gets the missingAddresses value for this Send.
     * 
     * @return missingAddresses
     */
    public java.lang.Integer getMissingAddresses() {
        return missingAddresses;
    }


    /**
     * Sets the missingAddresses value for this Send.
     * 
     * @param missingAddresses
     */
    public void setMissingAddresses(java.lang.Integer missingAddresses) {
        this.missingAddresses = missingAddresses;
    }


    /**
     * Gets the subject value for this Send.
     * 
     * @return subject
     */
    public java.lang.String getSubject() {
        return subject;
    }


    /**
     * Sets the subject value for this Send.
     * 
     * @param subject
     */
    public void setSubject(java.lang.String subject) {
        this.subject = subject;
    }


    /**
     * Gets the previewURL value for this Send.
     * 
     * @return previewURL
     */
    public java.lang.String getPreviewURL() {
        return previewURL;
    }


    /**
     * Sets the previewURL value for this Send.
     * 
     * @param previewURL
     */
    public void setPreviewURL(java.lang.String previewURL) {
        this.previewURL = previewURL;
    }


    /**
     * Gets the links value for this Send.
     * 
     * @return links
     */
    public com.exacttarget.wsdl.partnerAPI.Link[] getLinks() {
        return links;
    }


    /**
     * Sets the links value for this Send.
     * 
     * @param links
     */
    public void setLinks(com.exacttarget.wsdl.partnerAPI.Link[] links) {
        this.links = links;
    }

    public com.exacttarget.wsdl.partnerAPI.Link getLinks(int i) {
        return this.links[i];
    }

    public void setLinks(int i, com.exacttarget.wsdl.partnerAPI.Link _value) {
        this.links[i] = _value;
    }


    /**
     * Gets the events value for this Send.
     * 
     * @return events
     */
    public com.exacttarget.wsdl.partnerAPI.TrackingEvent[] getEvents() {
        return events;
    }


    /**
     * Sets the events value for this Send.
     * 
     * @param events
     */
    public void setEvents(com.exacttarget.wsdl.partnerAPI.TrackingEvent[] events) {
        this.events = events;
    }

    public com.exacttarget.wsdl.partnerAPI.TrackingEvent getEvents(int i) {
        return this.events[i];
    }

    public void setEvents(int i, com.exacttarget.wsdl.partnerAPI.TrackingEvent _value) {
        this.events[i] = _value;
    }


    /**
     * Gets the sentDate value for this Send.
     * 
     * @return sentDate
     */
    public java.util.Calendar getSentDate() {
        return sentDate;
    }


    /**
     * Sets the sentDate value for this Send.
     * 
     * @param sentDate
     */
    public void setSentDate(java.util.Calendar sentDate) {
        this.sentDate = sentDate;
    }


    /**
     * Gets the emailName value for this Send.
     * 
     * @return emailName
     */
    public java.lang.String getEmailName() {
        return emailName;
    }


    /**
     * Sets the emailName value for this Send.
     * 
     * @param emailName
     */
    public void setEmailName(java.lang.String emailName) {
        this.emailName = emailName;
    }


    /**
     * Gets the status value for this Send.
     * 
     * @return status
     */
    public java.lang.String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this Send.
     * 
     * @param status
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }


    /**
     * Gets the isMultipart value for this Send.
     * 
     * @return isMultipart
     */
    public java.lang.Boolean getIsMultipart() {
        return isMultipart;
    }


    /**
     * Sets the isMultipart value for this Send.
     * 
     * @param isMultipart
     */
    public void setIsMultipart(java.lang.Boolean isMultipart) {
        this.isMultipart = isMultipart;
    }


    /**
     * Gets the sendLimit value for this Send.
     * 
     * @return sendLimit
     */
    public java.lang.Integer getSendLimit() {
        return sendLimit;
    }


    /**
     * Sets the sendLimit value for this Send.
     * 
     * @param sendLimit
     */
    public void setSendLimit(java.lang.Integer sendLimit) {
        this.sendLimit = sendLimit;
    }


    /**
     * Gets the sendWindowOpen value for this Send.
     * 
     * @return sendWindowOpen
     */
    public org.apache.axis.types.Time getSendWindowOpen() {
        return sendWindowOpen;
    }


    /**
     * Sets the sendWindowOpen value for this Send.
     * 
     * @param sendWindowOpen
     */
    public void setSendWindowOpen(org.apache.axis.types.Time sendWindowOpen) {
        this.sendWindowOpen = sendWindowOpen;
    }


    /**
     * Gets the sendWindowClose value for this Send.
     * 
     * @return sendWindowClose
     */
    public org.apache.axis.types.Time getSendWindowClose() {
        return sendWindowClose;
    }


    /**
     * Sets the sendWindowClose value for this Send.
     * 
     * @param sendWindowClose
     */
    public void setSendWindowClose(org.apache.axis.types.Time sendWindowClose) {
        this.sendWindowClose = sendWindowClose;
    }


    /**
     * Gets the isAlwaysOn value for this Send.
     * 
     * @return isAlwaysOn
     */
    public java.lang.Boolean getIsAlwaysOn() {
        return isAlwaysOn;
    }


    /**
     * Sets the isAlwaysOn value for this Send.
     * 
     * @param isAlwaysOn
     */
    public void setIsAlwaysOn(java.lang.Boolean isAlwaysOn) {
        this.isAlwaysOn = isAlwaysOn;
    }


    /**
     * Gets the sources value for this Send.
     * 
     * @return sources
     */
    public com.exacttarget.wsdl.partnerAPI.APIObject[] getSources() {
        return sources;
    }


    /**
     * Sets the sources value for this Send.
     * 
     * @param sources
     */
    public void setSources(com.exacttarget.wsdl.partnerAPI.APIObject[] sources) {
        this.sources = sources;
    }


    /**
     * Gets the numberTargeted value for this Send.
     * 
     * @return numberTargeted
     */
    public java.lang.Integer getNumberTargeted() {
        return numberTargeted;
    }


    /**
     * Sets the numberTargeted value for this Send.
     * 
     * @param numberTargeted
     */
    public void setNumberTargeted(java.lang.Integer numberTargeted) {
        this.numberTargeted = numberTargeted;
    }


    /**
     * Gets the numberErrored value for this Send.
     * 
     * @return numberErrored
     */
    public java.lang.Integer getNumberErrored() {
        return numberErrored;
    }


    /**
     * Sets the numberErrored value for this Send.
     * 
     * @param numberErrored
     */
    public void setNumberErrored(java.lang.Integer numberErrored) {
        this.numberErrored = numberErrored;
    }


    /**
     * Gets the numberExcluded value for this Send.
     * 
     * @return numberExcluded
     */
    public java.lang.Integer getNumberExcluded() {
        return numberExcluded;
    }


    /**
     * Sets the numberExcluded value for this Send.
     * 
     * @param numberExcluded
     */
    public void setNumberExcluded(java.lang.Integer numberExcluded) {
        this.numberExcluded = numberExcluded;
    }


    /**
     * Gets the additional value for this Send.
     * 
     * @return additional
     */
    public java.lang.String getAdditional() {
        return additional;
    }


    /**
     * Sets the additional value for this Send.
     * 
     * @param additional
     */
    public void setAdditional(java.lang.String additional) {
        this.additional = additional;
    }


    /**
     * Gets the bccEmail value for this Send.
     * 
     * @return bccEmail
     */
    public java.lang.String getBccEmail() {
        return bccEmail;
    }


    /**
     * Sets the bccEmail value for this Send.
     * 
     * @param bccEmail
     */
    public void setBccEmail(java.lang.String bccEmail) {
        this.bccEmail = bccEmail;
    }


    /**
     * Gets the emailSendDefinition value for this Send.
     * 
     * @return emailSendDefinition
     */
    public com.exacttarget.wsdl.partnerAPI.EmailSendDefinition getEmailSendDefinition() {
        return emailSendDefinition;
    }


    /**
     * Sets the emailSendDefinition value for this Send.
     * 
     * @param emailSendDefinition
     */
    public void setEmailSendDefinition(com.exacttarget.wsdl.partnerAPI.EmailSendDefinition emailSendDefinition) {
        this.emailSendDefinition = emailSendDefinition;
    }


    /**
     * Gets the suppressionLists value for this Send.
     * 
     * @return suppressionLists
     */
    public com.exacttarget.wsdl.partnerAPI.AudienceItem[] getSuppressionLists() {
        return suppressionLists;
    }


    /**
     * Sets the suppressionLists value for this Send.
     * 
     * @param suppressionLists
     */
    public void setSuppressionLists(com.exacttarget.wsdl.partnerAPI.AudienceItem[] suppressionLists) {
        this.suppressionLists = suppressionLists;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Send)) return false;
        Send other = (Send) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.email==null && other.getEmail()==null) || 
             (this.email!=null &&
              this.email.equals(other.getEmail()))) &&
            ((this.list==null && other.getList()==null) || 
             (this.list!=null &&
              java.util.Arrays.equals(this.list, other.getList()))) &&
            ((this.sendDate==null && other.getSendDate()==null) || 
             (this.sendDate!=null &&
              this.sendDate.equals(other.getSendDate()))) &&
            ((this.fromAddress==null && other.getFromAddress()==null) || 
             (this.fromAddress!=null &&
              this.fromAddress.equals(other.getFromAddress()))) &&
            ((this.fromName==null && other.getFromName()==null) || 
             (this.fromName!=null &&
              this.fromName.equals(other.getFromName()))) &&
            ((this.duplicates==null && other.getDuplicates()==null) || 
             (this.duplicates!=null &&
              this.duplicates.equals(other.getDuplicates()))) &&
            ((this.invalidAddresses==null && other.getInvalidAddresses()==null) || 
             (this.invalidAddresses!=null &&
              this.invalidAddresses.equals(other.getInvalidAddresses()))) &&
            ((this.existingUndeliverables==null && other.getExistingUndeliverables()==null) || 
             (this.existingUndeliverables!=null &&
              this.existingUndeliverables.equals(other.getExistingUndeliverables()))) &&
            ((this.existingUnsubscribes==null && other.getExistingUnsubscribes()==null) || 
             (this.existingUnsubscribes!=null &&
              this.existingUnsubscribes.equals(other.getExistingUnsubscribes()))) &&
            ((this.hardBounces==null && other.getHardBounces()==null) || 
             (this.hardBounces!=null &&
              this.hardBounces.equals(other.getHardBounces()))) &&
            ((this.softBounces==null && other.getSoftBounces()==null) || 
             (this.softBounces!=null &&
              this.softBounces.equals(other.getSoftBounces()))) &&
            ((this.otherBounces==null && other.getOtherBounces()==null) || 
             (this.otherBounces!=null &&
              this.otherBounces.equals(other.getOtherBounces()))) &&
            ((this.forwardedEmails==null && other.getForwardedEmails()==null) || 
             (this.forwardedEmails!=null &&
              this.forwardedEmails.equals(other.getForwardedEmails()))) &&
            ((this.uniqueClicks==null && other.getUniqueClicks()==null) || 
             (this.uniqueClicks!=null &&
              this.uniqueClicks.equals(other.getUniqueClicks()))) &&
            ((this.uniqueOpens==null && other.getUniqueOpens()==null) || 
             (this.uniqueOpens!=null &&
              this.uniqueOpens.equals(other.getUniqueOpens()))) &&
            ((this.numberSent==null && other.getNumberSent()==null) || 
             (this.numberSent!=null &&
              this.numberSent.equals(other.getNumberSent()))) &&
            ((this.numberDelivered==null && other.getNumberDelivered()==null) || 
             (this.numberDelivered!=null &&
              this.numberDelivered.equals(other.getNumberDelivered()))) &&
            ((this.unsubscribes==null && other.getUnsubscribes()==null) || 
             (this.unsubscribes!=null &&
              this.unsubscribes.equals(other.getUnsubscribes()))) &&
            ((this.missingAddresses==null && other.getMissingAddresses()==null) || 
             (this.missingAddresses!=null &&
              this.missingAddresses.equals(other.getMissingAddresses()))) &&
            ((this.subject==null && other.getSubject()==null) || 
             (this.subject!=null &&
              this.subject.equals(other.getSubject()))) &&
            ((this.previewURL==null && other.getPreviewURL()==null) || 
             (this.previewURL!=null &&
              this.previewURL.equals(other.getPreviewURL()))) &&
            ((this.links==null && other.getLinks()==null) || 
             (this.links!=null &&
              java.util.Arrays.equals(this.links, other.getLinks()))) &&
            ((this.events==null && other.getEvents()==null) || 
             (this.events!=null &&
              java.util.Arrays.equals(this.events, other.getEvents()))) &&
            ((this.sentDate==null && other.getSentDate()==null) || 
             (this.sentDate!=null &&
              this.sentDate.equals(other.getSentDate()))) &&
            ((this.emailName==null && other.getEmailName()==null) || 
             (this.emailName!=null &&
              this.emailName.equals(other.getEmailName()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.isMultipart==null && other.getIsMultipart()==null) || 
             (this.isMultipart!=null &&
              this.isMultipart.equals(other.getIsMultipart()))) &&
            ((this.sendLimit==null && other.getSendLimit()==null) || 
             (this.sendLimit!=null &&
              this.sendLimit.equals(other.getSendLimit()))) &&
            ((this.sendWindowOpen==null && other.getSendWindowOpen()==null) || 
             (this.sendWindowOpen!=null &&
              this.sendWindowOpen.equals(other.getSendWindowOpen()))) &&
            ((this.sendWindowClose==null && other.getSendWindowClose()==null) || 
             (this.sendWindowClose!=null &&
              this.sendWindowClose.equals(other.getSendWindowClose()))) &&
            ((this.isAlwaysOn==null && other.getIsAlwaysOn()==null) || 
             (this.isAlwaysOn!=null &&
              this.isAlwaysOn.equals(other.getIsAlwaysOn()))) &&
            ((this.sources==null && other.getSources()==null) || 
             (this.sources!=null &&
              java.util.Arrays.equals(this.sources, other.getSources()))) &&
            ((this.numberTargeted==null && other.getNumberTargeted()==null) || 
             (this.numberTargeted!=null &&
              this.numberTargeted.equals(other.getNumberTargeted()))) &&
            ((this.numberErrored==null && other.getNumberErrored()==null) || 
             (this.numberErrored!=null &&
              this.numberErrored.equals(other.getNumberErrored()))) &&
            ((this.numberExcluded==null && other.getNumberExcluded()==null) || 
             (this.numberExcluded!=null &&
              this.numberExcluded.equals(other.getNumberExcluded()))) &&
            ((this.additional==null && other.getAdditional()==null) || 
             (this.additional!=null &&
              this.additional.equals(other.getAdditional()))) &&
            ((this.bccEmail==null && other.getBccEmail()==null) || 
             (this.bccEmail!=null &&
              this.bccEmail.equals(other.getBccEmail()))) &&
            ((this.emailSendDefinition==null && other.getEmailSendDefinition()==null) || 
             (this.emailSendDefinition!=null &&
              this.emailSendDefinition.equals(other.getEmailSendDefinition()))) &&
            ((this.suppressionLists==null && other.getSuppressionLists()==null) || 
             (this.suppressionLists!=null &&
              java.util.Arrays.equals(this.suppressionLists, other.getSuppressionLists())));
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
        if (getEmail() != null) {
            _hashCode += getEmail().hashCode();
        }
        if (getList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSendDate() != null) {
            _hashCode += getSendDate().hashCode();
        }
        if (getFromAddress() != null) {
            _hashCode += getFromAddress().hashCode();
        }
        if (getFromName() != null) {
            _hashCode += getFromName().hashCode();
        }
        if (getDuplicates() != null) {
            _hashCode += getDuplicates().hashCode();
        }
        if (getInvalidAddresses() != null) {
            _hashCode += getInvalidAddresses().hashCode();
        }
        if (getExistingUndeliverables() != null) {
            _hashCode += getExistingUndeliverables().hashCode();
        }
        if (getExistingUnsubscribes() != null) {
            _hashCode += getExistingUnsubscribes().hashCode();
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
        if (getForwardedEmails() != null) {
            _hashCode += getForwardedEmails().hashCode();
        }
        if (getUniqueClicks() != null) {
            _hashCode += getUniqueClicks().hashCode();
        }
        if (getUniqueOpens() != null) {
            _hashCode += getUniqueOpens().hashCode();
        }
        if (getNumberSent() != null) {
            _hashCode += getNumberSent().hashCode();
        }
        if (getNumberDelivered() != null) {
            _hashCode += getNumberDelivered().hashCode();
        }
        if (getUnsubscribes() != null) {
            _hashCode += getUnsubscribes().hashCode();
        }
        if (getMissingAddresses() != null) {
            _hashCode += getMissingAddresses().hashCode();
        }
        if (getSubject() != null) {
            _hashCode += getSubject().hashCode();
        }
        if (getPreviewURL() != null) {
            _hashCode += getPreviewURL().hashCode();
        }
        if (getLinks() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getLinks());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getLinks(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getEvents() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getEvents());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getEvents(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSentDate() != null) {
            _hashCode += getSentDate().hashCode();
        }
        if (getEmailName() != null) {
            _hashCode += getEmailName().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getIsMultipart() != null) {
            _hashCode += getIsMultipart().hashCode();
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
        if (getIsAlwaysOn() != null) {
            _hashCode += getIsAlwaysOn().hashCode();
        }
        if (getSources() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSources());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSources(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getNumberTargeted() != null) {
            _hashCode += getNumberTargeted().hashCode();
        }
        if (getNumberErrored() != null) {
            _hashCode += getNumberErrored().hashCode();
        }
        if (getNumberExcluded() != null) {
            _hashCode += getNumberExcluded().hashCode();
        }
        if (getAdditional() != null) {
            _hashCode += getAdditional().hashCode();
        }
        if (getBccEmail() != null) {
            _hashCode += getBccEmail().hashCode();
        }
        if (getEmailSendDefinition() != null) {
            _hashCode += getEmailSendDefinition().hashCode();
        }
        if (getSuppressionLists() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSuppressionLists());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSuppressionLists(), i);
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
        new org.apache.axis.description.TypeDesc(Send.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Send"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sendDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendDate"));
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
        elemField.setFieldName("duplicates");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Duplicates"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("invalidAddresses");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "InvalidAddresses"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("existingUndeliverables");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExistingUndeliverables"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("existingUnsubscribes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExistingUnsubscribes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("forwardedEmails");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ForwardedEmails"));
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
        elemField.setFieldName("uniqueOpens");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "UniqueOpens"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numberSent");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "NumberSent"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numberDelivered");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "NumberDelivered"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unsubscribes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Unsubscribes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("missingAddresses");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MissingAddresses"));
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
        elemField.setFieldName("previewURL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PreviewURL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("links");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Links"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Link"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("events");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Events"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TrackingEvent"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sentDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SentDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("emailName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "EmailName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Status"));
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
        elemField.setFieldName("isAlwaysOn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsAlwaysOn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sources");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Sources"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "APIObject"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Source"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numberTargeted");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "NumberTargeted"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numberErrored");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "NumberErrored"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numberExcluded");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "NumberExcluded"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("additional");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Additional"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setFieldName("emailSendDefinition");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "EmailSendDefinition"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "EmailSendDefinition"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("suppressionLists");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SuppressionLists"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AudienceItem"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SuppressionList"));
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
