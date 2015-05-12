/**
 * Subscription.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class Subscription  implements java.io.Serializable {
    private java.lang.Integer subscriptionID;

    private int emailsPurchased;

    private int accountsPurchased;

    private int advAccountsPurchased;

    private int LPAccountsPurchased;

    private int DOTOAccountsPurchased;

    private int BUAccountsPurchased;

    private java.util.Calendar beginDate;

    private java.util.Calendar endDate;

    private java.lang.String notes;

    private java.lang.String period;

    private java.lang.String notificationTitle;

    private java.lang.String notificationMessage;

    private java.lang.String notificationFlag;

    private java.util.Calendar notificationExpDate;

    private java.lang.String forAccounting;

    private boolean hasPurchasedEmails;

    private java.lang.String contractNumber;

    private java.lang.String contractModifier;

    private java.lang.Boolean isRenewal;

    private long numberofEmails;

    public Subscription() {
    }

    public Subscription(
           java.lang.Integer subscriptionID,
           int emailsPurchased,
           int accountsPurchased,
           int advAccountsPurchased,
           int LPAccountsPurchased,
           int DOTOAccountsPurchased,
           int BUAccountsPurchased,
           java.util.Calendar beginDate,
           java.util.Calendar endDate,
           java.lang.String notes,
           java.lang.String period,
           java.lang.String notificationTitle,
           java.lang.String notificationMessage,
           java.lang.String notificationFlag,
           java.util.Calendar notificationExpDate,
           java.lang.String forAccounting,
           boolean hasPurchasedEmails,
           java.lang.String contractNumber,
           java.lang.String contractModifier,
           java.lang.Boolean isRenewal,
           long numberofEmails) {
           this.subscriptionID = subscriptionID;
           this.emailsPurchased = emailsPurchased;
           this.accountsPurchased = accountsPurchased;
           this.advAccountsPurchased = advAccountsPurchased;
           this.LPAccountsPurchased = LPAccountsPurchased;
           this.DOTOAccountsPurchased = DOTOAccountsPurchased;
           this.BUAccountsPurchased = BUAccountsPurchased;
           this.beginDate = beginDate;
           this.endDate = endDate;
           this.notes = notes;
           this.period = period;
           this.notificationTitle = notificationTitle;
           this.notificationMessage = notificationMessage;
           this.notificationFlag = notificationFlag;
           this.notificationExpDate = notificationExpDate;
           this.forAccounting = forAccounting;
           this.hasPurchasedEmails = hasPurchasedEmails;
           this.contractNumber = contractNumber;
           this.contractModifier = contractModifier;
           this.isRenewal = isRenewal;
           this.numberofEmails = numberofEmails;
    }


    /**
     * Gets the subscriptionID value for this Subscription.
     * 
     * @return subscriptionID
     */
    public java.lang.Integer getSubscriptionID() {
        return subscriptionID;
    }


    /**
     * Sets the subscriptionID value for this Subscription.
     * 
     * @param subscriptionID
     */
    public void setSubscriptionID(java.lang.Integer subscriptionID) {
        this.subscriptionID = subscriptionID;
    }


    /**
     * Gets the emailsPurchased value for this Subscription.
     * 
     * @return emailsPurchased
     */
    public int getEmailsPurchased() {
        return emailsPurchased;
    }


    /**
     * Sets the emailsPurchased value for this Subscription.
     * 
     * @param emailsPurchased
     */
    public void setEmailsPurchased(int emailsPurchased) {
        this.emailsPurchased = emailsPurchased;
    }


    /**
     * Gets the accountsPurchased value for this Subscription.
     * 
     * @return accountsPurchased
     */
    public int getAccountsPurchased() {
        return accountsPurchased;
    }


    /**
     * Sets the accountsPurchased value for this Subscription.
     * 
     * @param accountsPurchased
     */
    public void setAccountsPurchased(int accountsPurchased) {
        this.accountsPurchased = accountsPurchased;
    }


    /**
     * Gets the advAccountsPurchased value for this Subscription.
     * 
     * @return advAccountsPurchased
     */
    public int getAdvAccountsPurchased() {
        return advAccountsPurchased;
    }


    /**
     * Sets the advAccountsPurchased value for this Subscription.
     * 
     * @param advAccountsPurchased
     */
    public void setAdvAccountsPurchased(int advAccountsPurchased) {
        this.advAccountsPurchased = advAccountsPurchased;
    }


    /**
     * Gets the LPAccountsPurchased value for this Subscription.
     * 
     * @return LPAccountsPurchased
     */
    public int getLPAccountsPurchased() {
        return LPAccountsPurchased;
    }


    /**
     * Sets the LPAccountsPurchased value for this Subscription.
     * 
     * @param LPAccountsPurchased
     */
    public void setLPAccountsPurchased(int LPAccountsPurchased) {
        this.LPAccountsPurchased = LPAccountsPurchased;
    }


    /**
     * Gets the DOTOAccountsPurchased value for this Subscription.
     * 
     * @return DOTOAccountsPurchased
     */
    public int getDOTOAccountsPurchased() {
        return DOTOAccountsPurchased;
    }


    /**
     * Sets the DOTOAccountsPurchased value for this Subscription.
     * 
     * @param DOTOAccountsPurchased
     */
    public void setDOTOAccountsPurchased(int DOTOAccountsPurchased) {
        this.DOTOAccountsPurchased = DOTOAccountsPurchased;
    }


    /**
     * Gets the BUAccountsPurchased value for this Subscription.
     * 
     * @return BUAccountsPurchased
     */
    public int getBUAccountsPurchased() {
        return BUAccountsPurchased;
    }


    /**
     * Sets the BUAccountsPurchased value for this Subscription.
     * 
     * @param BUAccountsPurchased
     */
    public void setBUAccountsPurchased(int BUAccountsPurchased) {
        this.BUAccountsPurchased = BUAccountsPurchased;
    }


    /**
     * Gets the beginDate value for this Subscription.
     * 
     * @return beginDate
     */
    public java.util.Calendar getBeginDate() {
        return beginDate;
    }


    /**
     * Sets the beginDate value for this Subscription.
     * 
     * @param beginDate
     */
    public void setBeginDate(java.util.Calendar beginDate) {
        this.beginDate = beginDate;
    }


    /**
     * Gets the endDate value for this Subscription.
     * 
     * @return endDate
     */
    public java.util.Calendar getEndDate() {
        return endDate;
    }


    /**
     * Sets the endDate value for this Subscription.
     * 
     * @param endDate
     */
    public void setEndDate(java.util.Calendar endDate) {
        this.endDate = endDate;
    }


    /**
     * Gets the notes value for this Subscription.
     * 
     * @return notes
     */
    public java.lang.String getNotes() {
        return notes;
    }


    /**
     * Sets the notes value for this Subscription.
     * 
     * @param notes
     */
    public void setNotes(java.lang.String notes) {
        this.notes = notes;
    }


    /**
     * Gets the period value for this Subscription.
     * 
     * @return period
     */
    public java.lang.String getPeriod() {
        return period;
    }


    /**
     * Sets the period value for this Subscription.
     * 
     * @param period
     */
    public void setPeriod(java.lang.String period) {
        this.period = period;
    }


    /**
     * Gets the notificationTitle value for this Subscription.
     * 
     * @return notificationTitle
     */
    public java.lang.String getNotificationTitle() {
        return notificationTitle;
    }


    /**
     * Sets the notificationTitle value for this Subscription.
     * 
     * @param notificationTitle
     */
    public void setNotificationTitle(java.lang.String notificationTitle) {
        this.notificationTitle = notificationTitle;
    }


    /**
     * Gets the notificationMessage value for this Subscription.
     * 
     * @return notificationMessage
     */
    public java.lang.String getNotificationMessage() {
        return notificationMessage;
    }


    /**
     * Sets the notificationMessage value for this Subscription.
     * 
     * @param notificationMessage
     */
    public void setNotificationMessage(java.lang.String notificationMessage) {
        this.notificationMessage = notificationMessage;
    }


    /**
     * Gets the notificationFlag value for this Subscription.
     * 
     * @return notificationFlag
     */
    public java.lang.String getNotificationFlag() {
        return notificationFlag;
    }


    /**
     * Sets the notificationFlag value for this Subscription.
     * 
     * @param notificationFlag
     */
    public void setNotificationFlag(java.lang.String notificationFlag) {
        this.notificationFlag = notificationFlag;
    }


    /**
     * Gets the notificationExpDate value for this Subscription.
     * 
     * @return notificationExpDate
     */
    public java.util.Calendar getNotificationExpDate() {
        return notificationExpDate;
    }


    /**
     * Sets the notificationExpDate value for this Subscription.
     * 
     * @param notificationExpDate
     */
    public void setNotificationExpDate(java.util.Calendar notificationExpDate) {
        this.notificationExpDate = notificationExpDate;
    }


    /**
     * Gets the forAccounting value for this Subscription.
     * 
     * @return forAccounting
     */
    public java.lang.String getForAccounting() {
        return forAccounting;
    }


    /**
     * Sets the forAccounting value for this Subscription.
     * 
     * @param forAccounting
     */
    public void setForAccounting(java.lang.String forAccounting) {
        this.forAccounting = forAccounting;
    }


    /**
     * Gets the hasPurchasedEmails value for this Subscription.
     * 
     * @return hasPurchasedEmails
     */
    public boolean isHasPurchasedEmails() {
        return hasPurchasedEmails;
    }


    /**
     * Sets the hasPurchasedEmails value for this Subscription.
     * 
     * @param hasPurchasedEmails
     */
    public void setHasPurchasedEmails(boolean hasPurchasedEmails) {
        this.hasPurchasedEmails = hasPurchasedEmails;
    }


    /**
     * Gets the contractNumber value for this Subscription.
     * 
     * @return contractNumber
     */
    public java.lang.String getContractNumber() {
        return contractNumber;
    }


    /**
     * Sets the contractNumber value for this Subscription.
     * 
     * @param contractNumber
     */
    public void setContractNumber(java.lang.String contractNumber) {
        this.contractNumber = contractNumber;
    }


    /**
     * Gets the contractModifier value for this Subscription.
     * 
     * @return contractModifier
     */
    public java.lang.String getContractModifier() {
        return contractModifier;
    }


    /**
     * Sets the contractModifier value for this Subscription.
     * 
     * @param contractModifier
     */
    public void setContractModifier(java.lang.String contractModifier) {
        this.contractModifier = contractModifier;
    }


    /**
     * Gets the isRenewal value for this Subscription.
     * 
     * @return isRenewal
     */
    public java.lang.Boolean getIsRenewal() {
        return isRenewal;
    }


    /**
     * Sets the isRenewal value for this Subscription.
     * 
     * @param isRenewal
     */
    public void setIsRenewal(java.lang.Boolean isRenewal) {
        this.isRenewal = isRenewal;
    }


    /**
     * Gets the numberofEmails value for this Subscription.
     * 
     * @return numberofEmails
     */
    public long getNumberofEmails() {
        return numberofEmails;
    }


    /**
     * Sets the numberofEmails value for this Subscription.
     * 
     * @param numberofEmails
     */
    public void setNumberofEmails(long numberofEmails) {
        this.numberofEmails = numberofEmails;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Subscription)) return false;
        Subscription other = (Subscription) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.subscriptionID==null && other.getSubscriptionID()==null) || 
             (this.subscriptionID!=null &&
              this.subscriptionID.equals(other.getSubscriptionID()))) &&
            this.emailsPurchased == other.getEmailsPurchased() &&
            this.accountsPurchased == other.getAccountsPurchased() &&
            this.advAccountsPurchased == other.getAdvAccountsPurchased() &&
            this.LPAccountsPurchased == other.getLPAccountsPurchased() &&
            this.DOTOAccountsPurchased == other.getDOTOAccountsPurchased() &&
            this.BUAccountsPurchased == other.getBUAccountsPurchased() &&
            ((this.beginDate==null && other.getBeginDate()==null) || 
             (this.beginDate!=null &&
              this.beginDate.equals(other.getBeginDate()))) &&
            ((this.endDate==null && other.getEndDate()==null) || 
             (this.endDate!=null &&
              this.endDate.equals(other.getEndDate()))) &&
            ((this.notes==null && other.getNotes()==null) || 
             (this.notes!=null &&
              this.notes.equals(other.getNotes()))) &&
            ((this.period==null && other.getPeriod()==null) || 
             (this.period!=null &&
              this.period.equals(other.getPeriod()))) &&
            ((this.notificationTitle==null && other.getNotificationTitle()==null) || 
             (this.notificationTitle!=null &&
              this.notificationTitle.equals(other.getNotificationTitle()))) &&
            ((this.notificationMessage==null && other.getNotificationMessage()==null) || 
             (this.notificationMessage!=null &&
              this.notificationMessage.equals(other.getNotificationMessage()))) &&
            ((this.notificationFlag==null && other.getNotificationFlag()==null) || 
             (this.notificationFlag!=null &&
              this.notificationFlag.equals(other.getNotificationFlag()))) &&
            ((this.notificationExpDate==null && other.getNotificationExpDate()==null) || 
             (this.notificationExpDate!=null &&
              this.notificationExpDate.equals(other.getNotificationExpDate()))) &&
            ((this.forAccounting==null && other.getForAccounting()==null) || 
             (this.forAccounting!=null &&
              this.forAccounting.equals(other.getForAccounting()))) &&
            this.hasPurchasedEmails == other.isHasPurchasedEmails() &&
            ((this.contractNumber==null && other.getContractNumber()==null) || 
             (this.contractNumber!=null &&
              this.contractNumber.equals(other.getContractNumber()))) &&
            ((this.contractModifier==null && other.getContractModifier()==null) || 
             (this.contractModifier!=null &&
              this.contractModifier.equals(other.getContractModifier()))) &&
            ((this.isRenewal==null && other.getIsRenewal()==null) || 
             (this.isRenewal!=null &&
              this.isRenewal.equals(other.getIsRenewal()))) &&
            this.numberofEmails == other.getNumberofEmails();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getSubscriptionID() != null) {
            _hashCode += getSubscriptionID().hashCode();
        }
        _hashCode += getEmailsPurchased();
        _hashCode += getAccountsPurchased();
        _hashCode += getAdvAccountsPurchased();
        _hashCode += getLPAccountsPurchased();
        _hashCode += getDOTOAccountsPurchased();
        _hashCode += getBUAccountsPurchased();
        if (getBeginDate() != null) {
            _hashCode += getBeginDate().hashCode();
        }
        if (getEndDate() != null) {
            _hashCode += getEndDate().hashCode();
        }
        if (getNotes() != null) {
            _hashCode += getNotes().hashCode();
        }
        if (getPeriod() != null) {
            _hashCode += getPeriod().hashCode();
        }
        if (getNotificationTitle() != null) {
            _hashCode += getNotificationTitle().hashCode();
        }
        if (getNotificationMessage() != null) {
            _hashCode += getNotificationMessage().hashCode();
        }
        if (getNotificationFlag() != null) {
            _hashCode += getNotificationFlag().hashCode();
        }
        if (getNotificationExpDate() != null) {
            _hashCode += getNotificationExpDate().hashCode();
        }
        if (getForAccounting() != null) {
            _hashCode += getForAccounting().hashCode();
        }
        _hashCode += (isHasPurchasedEmails() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getContractNumber() != null) {
            _hashCode += getContractNumber().hashCode();
        }
        if (getContractModifier() != null) {
            _hashCode += getContractModifier().hashCode();
        }
        if (getIsRenewal() != null) {
            _hashCode += getIsRenewal().hashCode();
        }
        _hashCode += new Long(getNumberofEmails()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Subscription.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Subscription"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subscriptionID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SubscriptionID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("emailsPurchased");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "EmailsPurchased"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountsPurchased");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AccountsPurchased"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("advAccountsPurchased");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AdvAccountsPurchased"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("LPAccountsPurchased");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "LPAccountsPurchased"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DOTOAccountsPurchased");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DOTOAccountsPurchased"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BUAccountsPurchased");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "BUAccountsPurchased"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("beginDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "BeginDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("endDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "EndDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("notes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Notes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("period");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Period"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("notificationTitle");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "NotificationTitle"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("notificationMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "NotificationMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("notificationFlag");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "NotificationFlag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("notificationExpDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "NotificationExpDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("forAccounting");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ForAccounting"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hasPurchasedEmails");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "HasPurchasedEmails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contractNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ContractNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contractModifier");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ContractModifier"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isRenewal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsRenewal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numberofEmails");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "NumberofEmails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
