/**
 * Account.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class Account  extends com.exacttarget.wsdl.partnerAPI.APIObject  implements java.io.Serializable {
    private com.exacttarget.wsdl.partnerAPI.AccountTypeEnum accountType;

    private java.lang.Integer parentID;

    private java.lang.Integer brandID;

    private java.lang.Integer privateLabelID;

    private java.lang.Integer reportingParentID;

    private java.lang.String name;

    private java.lang.String email;

    private java.lang.String fromName;

    private java.lang.String businessName;

    private java.lang.String phone;

    private java.lang.String address;

    private java.lang.String fax;

    private java.lang.String city;

    private java.lang.String state;

    private java.lang.String zip;

    private java.lang.String country;

    private java.lang.Integer isActive;

    private java.lang.Boolean isTestAccount;

    private java.lang.Integer orgID;

    private java.lang.Integer DBID;

    private java.lang.String parentName;

    private java.lang.Long customerID;

    private java.util.Calendar deletedDate;

    private java.lang.Integer editionID;

    private com.exacttarget.wsdl.partnerAPI.AccountDataItem[] children;

    private com.exacttarget.wsdl.partnerAPI.Subscription subscription;

    private com.exacttarget.wsdl.partnerAPI.PrivateLabel[] privateLabels;

    private com.exacttarget.wsdl.partnerAPI.BusinessRule[] businessRules;

    private com.exacttarget.wsdl.partnerAPI.AccountUser[] accountUsers;

    private java.lang.Boolean inheritAddress;

    private java.lang.Boolean isTrialAccount;

    private com.exacttarget.wsdl.partnerAPI.Locale locale;

    private com.exacttarget.wsdl.partnerAPI.Account parentAccount;

    private com.exacttarget.wsdl.partnerAPI.TimeZone timeZone;

    private com.exacttarget.wsdl.partnerAPI.Role[] roles;

    private com.exacttarget.wsdl.partnerAPI.Locale languageLocale;

    public Account() {
    }

    public Account(
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
           com.exacttarget.wsdl.partnerAPI.AccountTypeEnum accountType,
           java.lang.Integer parentID,
           java.lang.Integer brandID,
           java.lang.Integer privateLabelID,
           java.lang.Integer reportingParentID,
           java.lang.String name,
           java.lang.String email,
           java.lang.String fromName,
           java.lang.String businessName,
           java.lang.String phone,
           java.lang.String address,
           java.lang.String fax,
           java.lang.String city,
           java.lang.String state,
           java.lang.String zip,
           java.lang.String country,
           java.lang.Integer isActive,
           java.lang.Boolean isTestAccount,
           java.lang.Integer orgID,
           java.lang.Integer DBID,
           java.lang.String parentName,
           java.lang.Long customerID,
           java.util.Calendar deletedDate,
           java.lang.Integer editionID,
           com.exacttarget.wsdl.partnerAPI.AccountDataItem[] children,
           com.exacttarget.wsdl.partnerAPI.Subscription subscription,
           com.exacttarget.wsdl.partnerAPI.PrivateLabel[] privateLabels,
           com.exacttarget.wsdl.partnerAPI.BusinessRule[] businessRules,
           com.exacttarget.wsdl.partnerAPI.AccountUser[] accountUsers,
           java.lang.Boolean inheritAddress,
           java.lang.Boolean isTrialAccount,
           com.exacttarget.wsdl.partnerAPI.Locale locale,
           com.exacttarget.wsdl.partnerAPI.Account parentAccount,
           com.exacttarget.wsdl.partnerAPI.TimeZone timeZone,
           com.exacttarget.wsdl.partnerAPI.Role[] roles,
           com.exacttarget.wsdl.partnerAPI.Locale languageLocale) {
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
        this.accountType = accountType;
        this.parentID = parentID;
        this.brandID = brandID;
        this.privateLabelID = privateLabelID;
        this.reportingParentID = reportingParentID;
        this.name = name;
        this.email = email;
        this.fromName = fromName;
        this.businessName = businessName;
        this.phone = phone;
        this.address = address;
        this.fax = fax;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
        this.isActive = isActive;
        this.isTestAccount = isTestAccount;
        this.orgID = orgID;
        this.DBID = DBID;
        this.parentName = parentName;
        this.customerID = customerID;
        this.deletedDate = deletedDate;
        this.editionID = editionID;
        this.children = children;
        this.subscription = subscription;
        this.privateLabels = privateLabels;
        this.businessRules = businessRules;
        this.accountUsers = accountUsers;
        this.inheritAddress = inheritAddress;
        this.isTrialAccount = isTrialAccount;
        this.locale = locale;
        this.parentAccount = parentAccount;
        this.timeZone = timeZone;
        this.roles = roles;
        this.languageLocale = languageLocale;
    }


    /**
     * Gets the accountType value for this Account.
     * 
     * @return accountType
     */
    public com.exacttarget.wsdl.partnerAPI.AccountTypeEnum getAccountType() {
        return accountType;
    }


    /**
     * Sets the accountType value for this Account.
     * 
     * @param accountType
     */
    public void setAccountType(com.exacttarget.wsdl.partnerAPI.AccountTypeEnum accountType) {
        this.accountType = accountType;
    }


    /**
     * Gets the parentID value for this Account.
     * 
     * @return parentID
     */
    public java.lang.Integer getParentID() {
        return parentID;
    }


    /**
     * Sets the parentID value for this Account.
     * 
     * @param parentID
     */
    public void setParentID(java.lang.Integer parentID) {
        this.parentID = parentID;
    }


    /**
     * Gets the brandID value for this Account.
     * 
     * @return brandID
     */
    public java.lang.Integer getBrandID() {
        return brandID;
    }


    /**
     * Sets the brandID value for this Account.
     * 
     * @param brandID
     */
    public void setBrandID(java.lang.Integer brandID) {
        this.brandID = brandID;
    }


    /**
     * Gets the privateLabelID value for this Account.
     * 
     * @return privateLabelID
     */
    public java.lang.Integer getPrivateLabelID() {
        return privateLabelID;
    }


    /**
     * Sets the privateLabelID value for this Account.
     * 
     * @param privateLabelID
     */
    public void setPrivateLabelID(java.lang.Integer privateLabelID) {
        this.privateLabelID = privateLabelID;
    }


    /**
     * Gets the reportingParentID value for this Account.
     * 
     * @return reportingParentID
     */
    public java.lang.Integer getReportingParentID() {
        return reportingParentID;
    }


    /**
     * Sets the reportingParentID value for this Account.
     * 
     * @param reportingParentID
     */
    public void setReportingParentID(java.lang.Integer reportingParentID) {
        this.reportingParentID = reportingParentID;
    }


    /**
     * Gets the name value for this Account.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this Account.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the email value for this Account.
     * 
     * @return email
     */
    public java.lang.String getEmail() {
        return email;
    }


    /**
     * Sets the email value for this Account.
     * 
     * @param email
     */
    public void setEmail(java.lang.String email) {
        this.email = email;
    }


    /**
     * Gets the fromName value for this Account.
     * 
     * @return fromName
     */
    public java.lang.String getFromName() {
        return fromName;
    }


    /**
     * Sets the fromName value for this Account.
     * 
     * @param fromName
     */
    public void setFromName(java.lang.String fromName) {
        this.fromName = fromName;
    }


    /**
     * Gets the businessName value for this Account.
     * 
     * @return businessName
     */
    public java.lang.String getBusinessName() {
        return businessName;
    }


    /**
     * Sets the businessName value for this Account.
     * 
     * @param businessName
     */
    public void setBusinessName(java.lang.String businessName) {
        this.businessName = businessName;
    }


    /**
     * Gets the phone value for this Account.
     * 
     * @return phone
     */
    public java.lang.String getPhone() {
        return phone;
    }


    /**
     * Sets the phone value for this Account.
     * 
     * @param phone
     */
    public void setPhone(java.lang.String phone) {
        this.phone = phone;
    }


    /**
     * Gets the address value for this Account.
     * 
     * @return address
     */
    public java.lang.String getAddress() {
        return address;
    }


    /**
     * Sets the address value for this Account.
     * 
     * @param address
     */
    public void setAddress(java.lang.String address) {
        this.address = address;
    }


    /**
     * Gets the fax value for this Account.
     * 
     * @return fax
     */
    public java.lang.String getFax() {
        return fax;
    }


    /**
     * Sets the fax value for this Account.
     * 
     * @param fax
     */
    public void setFax(java.lang.String fax) {
        this.fax = fax;
    }


    /**
     * Gets the city value for this Account.
     * 
     * @return city
     */
    public java.lang.String getCity() {
        return city;
    }


    /**
     * Sets the city value for this Account.
     * 
     * @param city
     */
    public void setCity(java.lang.String city) {
        this.city = city;
    }


    /**
     * Gets the state value for this Account.
     * 
     * @return state
     */
    public java.lang.String getState() {
        return state;
    }


    /**
     * Sets the state value for this Account.
     * 
     * @param state
     */
    public void setState(java.lang.String state) {
        this.state = state;
    }


    /**
     * Gets the zip value for this Account.
     * 
     * @return zip
     */
    public java.lang.String getZip() {
        return zip;
    }


    /**
     * Sets the zip value for this Account.
     * 
     * @param zip
     */
    public void setZip(java.lang.String zip) {
        this.zip = zip;
    }


    /**
     * Gets the country value for this Account.
     * 
     * @return country
     */
    public java.lang.String getCountry() {
        return country;
    }


    /**
     * Sets the country value for this Account.
     * 
     * @param country
     */
    public void setCountry(java.lang.String country) {
        this.country = country;
    }


    /**
     * Gets the isActive value for this Account.
     * 
     * @return isActive
     */
    public java.lang.Integer getIsActive() {
        return isActive;
    }


    /**
     * Sets the isActive value for this Account.
     * 
     * @param isActive
     */
    public void setIsActive(java.lang.Integer isActive) {
        this.isActive = isActive;
    }


    /**
     * Gets the isTestAccount value for this Account.
     * 
     * @return isTestAccount
     */
    public java.lang.Boolean getIsTestAccount() {
        return isTestAccount;
    }


    /**
     * Sets the isTestAccount value for this Account.
     * 
     * @param isTestAccount
     */
    public void setIsTestAccount(java.lang.Boolean isTestAccount) {
        this.isTestAccount = isTestAccount;
    }


    /**
     * Gets the orgID value for this Account.
     * 
     * @return orgID
     */
    public java.lang.Integer getOrgID() {
        return orgID;
    }


    /**
     * Sets the orgID value for this Account.
     * 
     * @param orgID
     */
    public void setOrgID(java.lang.Integer orgID) {
        this.orgID = orgID;
    }


    /**
     * Gets the DBID value for this Account.
     * 
     * @return DBID
     */
    public java.lang.Integer getDBID() {
        return DBID;
    }


    /**
     * Sets the DBID value for this Account.
     * 
     * @param DBID
     */
    public void setDBID(java.lang.Integer DBID) {
        this.DBID = DBID;
    }


    /**
     * Gets the parentName value for this Account.
     * 
     * @return parentName
     */
    public java.lang.String getParentName() {
        return parentName;
    }


    /**
     * Sets the parentName value for this Account.
     * 
     * @param parentName
     */
    public void setParentName(java.lang.String parentName) {
        this.parentName = parentName;
    }


    /**
     * Gets the customerID value for this Account.
     * 
     * @return customerID
     */
    public java.lang.Long getCustomerID() {
        return customerID;
    }


    /**
     * Sets the customerID value for this Account.
     * 
     * @param customerID
     */
    public void setCustomerID(java.lang.Long customerID) {
        this.customerID = customerID;
    }


    /**
     * Gets the deletedDate value for this Account.
     * 
     * @return deletedDate
     */
    public java.util.Calendar getDeletedDate() {
        return deletedDate;
    }


    /**
     * Sets the deletedDate value for this Account.
     * 
     * @param deletedDate
     */
    public void setDeletedDate(java.util.Calendar deletedDate) {
        this.deletedDate = deletedDate;
    }


    /**
     * Gets the editionID value for this Account.
     * 
     * @return editionID
     */
    public java.lang.Integer getEditionID() {
        return editionID;
    }


    /**
     * Sets the editionID value for this Account.
     * 
     * @param editionID
     */
    public void setEditionID(java.lang.Integer editionID) {
        this.editionID = editionID;
    }


    /**
     * Gets the children value for this Account.
     * 
     * @return children
     */
    public com.exacttarget.wsdl.partnerAPI.AccountDataItem[] getChildren() {
        return children;
    }


    /**
     * Sets the children value for this Account.
     * 
     * @param children
     */
    public void setChildren(com.exacttarget.wsdl.partnerAPI.AccountDataItem[] children) {
        this.children = children;
    }

    public com.exacttarget.wsdl.partnerAPI.AccountDataItem getChildren(int i) {
        return this.children[i];
    }

    public void setChildren(int i, com.exacttarget.wsdl.partnerAPI.AccountDataItem _value) {
        this.children[i] = _value;
    }


    /**
     * Gets the subscription value for this Account.
     * 
     * @return subscription
     */
    public com.exacttarget.wsdl.partnerAPI.Subscription getSubscription() {
        return subscription;
    }


    /**
     * Sets the subscription value for this Account.
     * 
     * @param subscription
     */
    public void setSubscription(com.exacttarget.wsdl.partnerAPI.Subscription subscription) {
        this.subscription = subscription;
    }


    /**
     * Gets the privateLabels value for this Account.
     * 
     * @return privateLabels
     */
    public com.exacttarget.wsdl.partnerAPI.PrivateLabel[] getPrivateLabels() {
        return privateLabels;
    }


    /**
     * Sets the privateLabels value for this Account.
     * 
     * @param privateLabels
     */
    public void setPrivateLabels(com.exacttarget.wsdl.partnerAPI.PrivateLabel[] privateLabels) {
        this.privateLabels = privateLabels;
    }

    public com.exacttarget.wsdl.partnerAPI.PrivateLabel getPrivateLabels(int i) {
        return this.privateLabels[i];
    }

    public void setPrivateLabels(int i, com.exacttarget.wsdl.partnerAPI.PrivateLabel _value) {
        this.privateLabels[i] = _value;
    }


    /**
     * Gets the businessRules value for this Account.
     * 
     * @return businessRules
     */
    public com.exacttarget.wsdl.partnerAPI.BusinessRule[] getBusinessRules() {
        return businessRules;
    }


    /**
     * Sets the businessRules value for this Account.
     * 
     * @param businessRules
     */
    public void setBusinessRules(com.exacttarget.wsdl.partnerAPI.BusinessRule[] businessRules) {
        this.businessRules = businessRules;
    }

    public com.exacttarget.wsdl.partnerAPI.BusinessRule getBusinessRules(int i) {
        return this.businessRules[i];
    }

    public void setBusinessRules(int i, com.exacttarget.wsdl.partnerAPI.BusinessRule _value) {
        this.businessRules[i] = _value;
    }


    /**
     * Gets the accountUsers value for this Account.
     * 
     * @return accountUsers
     */
    public com.exacttarget.wsdl.partnerAPI.AccountUser[] getAccountUsers() {
        return accountUsers;
    }


    /**
     * Sets the accountUsers value for this Account.
     * 
     * @param accountUsers
     */
    public void setAccountUsers(com.exacttarget.wsdl.partnerAPI.AccountUser[] accountUsers) {
        this.accountUsers = accountUsers;
    }

    public com.exacttarget.wsdl.partnerAPI.AccountUser getAccountUsers(int i) {
        return this.accountUsers[i];
    }

    public void setAccountUsers(int i, com.exacttarget.wsdl.partnerAPI.AccountUser _value) {
        this.accountUsers[i] = _value;
    }


    /**
     * Gets the inheritAddress value for this Account.
     * 
     * @return inheritAddress
     */
    public java.lang.Boolean getInheritAddress() {
        return inheritAddress;
    }


    /**
     * Sets the inheritAddress value for this Account.
     * 
     * @param inheritAddress
     */
    public void setInheritAddress(java.lang.Boolean inheritAddress) {
        this.inheritAddress = inheritAddress;
    }


    /**
     * Gets the isTrialAccount value for this Account.
     * 
     * @return isTrialAccount
     */
    public java.lang.Boolean getIsTrialAccount() {
        return isTrialAccount;
    }


    /**
     * Sets the isTrialAccount value for this Account.
     * 
     * @param isTrialAccount
     */
    public void setIsTrialAccount(java.lang.Boolean isTrialAccount) {
        this.isTrialAccount = isTrialAccount;
    }


    /**
     * Gets the locale value for this Account.
     * 
     * @return locale
     */
    public com.exacttarget.wsdl.partnerAPI.Locale getLocale() {
        return locale;
    }


    /**
     * Sets the locale value for this Account.
     * 
     * @param locale
     */
    public void setLocale(com.exacttarget.wsdl.partnerAPI.Locale locale) {
        this.locale = locale;
    }


    /**
     * Gets the parentAccount value for this Account.
     * 
     * @return parentAccount
     */
    public com.exacttarget.wsdl.partnerAPI.Account getParentAccount() {
        return parentAccount;
    }


    /**
     * Sets the parentAccount value for this Account.
     * 
     * @param parentAccount
     */
    public void setParentAccount(com.exacttarget.wsdl.partnerAPI.Account parentAccount) {
        this.parentAccount = parentAccount;
    }


    /**
     * Gets the timeZone value for this Account.
     * 
     * @return timeZone
     */
    public com.exacttarget.wsdl.partnerAPI.TimeZone getTimeZone() {
        return timeZone;
    }


    /**
     * Sets the timeZone value for this Account.
     * 
     * @param timeZone
     */
    public void setTimeZone(com.exacttarget.wsdl.partnerAPI.TimeZone timeZone) {
        this.timeZone = timeZone;
    }


    /**
     * Gets the roles value for this Account.
     * 
     * @return roles
     */
    public com.exacttarget.wsdl.partnerAPI.Role[] getRoles() {
        return roles;
    }


    /**
     * Sets the roles value for this Account.
     * 
     * @param roles
     */
    public void setRoles(com.exacttarget.wsdl.partnerAPI.Role[] roles) {
        this.roles = roles;
    }


    /**
     * Gets the languageLocale value for this Account.
     * 
     * @return languageLocale
     */
    public com.exacttarget.wsdl.partnerAPI.Locale getLanguageLocale() {
        return languageLocale;
    }


    /**
     * Sets the languageLocale value for this Account.
     * 
     * @param languageLocale
     */
    public void setLanguageLocale(com.exacttarget.wsdl.partnerAPI.Locale languageLocale) {
        this.languageLocale = languageLocale;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Account)) return false;
        Account other = (Account) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.accountType==null && other.getAccountType()==null) || 
             (this.accountType!=null &&
              this.accountType.equals(other.getAccountType()))) &&
            ((this.parentID==null && other.getParentID()==null) || 
             (this.parentID!=null &&
              this.parentID.equals(other.getParentID()))) &&
            ((this.brandID==null && other.getBrandID()==null) || 
             (this.brandID!=null &&
              this.brandID.equals(other.getBrandID()))) &&
            ((this.privateLabelID==null && other.getPrivateLabelID()==null) || 
             (this.privateLabelID!=null &&
              this.privateLabelID.equals(other.getPrivateLabelID()))) &&
            ((this.reportingParentID==null && other.getReportingParentID()==null) || 
             (this.reportingParentID!=null &&
              this.reportingParentID.equals(other.getReportingParentID()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.email==null && other.getEmail()==null) || 
             (this.email!=null &&
              this.email.equals(other.getEmail()))) &&
            ((this.fromName==null && other.getFromName()==null) || 
             (this.fromName!=null &&
              this.fromName.equals(other.getFromName()))) &&
            ((this.businessName==null && other.getBusinessName()==null) || 
             (this.businessName!=null &&
              this.businessName.equals(other.getBusinessName()))) &&
            ((this.phone==null && other.getPhone()==null) || 
             (this.phone!=null &&
              this.phone.equals(other.getPhone()))) &&
            ((this.address==null && other.getAddress()==null) || 
             (this.address!=null &&
              this.address.equals(other.getAddress()))) &&
            ((this.fax==null && other.getFax()==null) || 
             (this.fax!=null &&
              this.fax.equals(other.getFax()))) &&
            ((this.city==null && other.getCity()==null) || 
             (this.city!=null &&
              this.city.equals(other.getCity()))) &&
            ((this.state==null && other.getState()==null) || 
             (this.state!=null &&
              this.state.equals(other.getState()))) &&
            ((this.zip==null && other.getZip()==null) || 
             (this.zip!=null &&
              this.zip.equals(other.getZip()))) &&
            ((this.country==null && other.getCountry()==null) || 
             (this.country!=null &&
              this.country.equals(other.getCountry()))) &&
            ((this.isActive==null && other.getIsActive()==null) || 
             (this.isActive!=null &&
              this.isActive.equals(other.getIsActive()))) &&
            ((this.isTestAccount==null && other.getIsTestAccount()==null) || 
             (this.isTestAccount!=null &&
              this.isTestAccount.equals(other.getIsTestAccount()))) &&
            ((this.orgID==null && other.getOrgID()==null) || 
             (this.orgID!=null &&
              this.orgID.equals(other.getOrgID()))) &&
            ((this.DBID==null && other.getDBID()==null) || 
             (this.DBID!=null &&
              this.DBID.equals(other.getDBID()))) &&
            ((this.parentName==null && other.getParentName()==null) || 
             (this.parentName!=null &&
              this.parentName.equals(other.getParentName()))) &&
            ((this.customerID==null && other.getCustomerID()==null) || 
             (this.customerID!=null &&
              this.customerID.equals(other.getCustomerID()))) &&
            ((this.deletedDate==null && other.getDeletedDate()==null) || 
             (this.deletedDate!=null &&
              this.deletedDate.equals(other.getDeletedDate()))) &&
            ((this.editionID==null && other.getEditionID()==null) || 
             (this.editionID!=null &&
              this.editionID.equals(other.getEditionID()))) &&
            ((this.children==null && other.getChildren()==null) || 
             (this.children!=null &&
              java.util.Arrays.equals(this.children, other.getChildren()))) &&
            ((this.subscription==null && other.getSubscription()==null) || 
             (this.subscription!=null &&
              this.subscription.equals(other.getSubscription()))) &&
            ((this.privateLabels==null && other.getPrivateLabels()==null) || 
             (this.privateLabels!=null &&
              java.util.Arrays.equals(this.privateLabels, other.getPrivateLabels()))) &&
            ((this.businessRules==null && other.getBusinessRules()==null) || 
             (this.businessRules!=null &&
              java.util.Arrays.equals(this.businessRules, other.getBusinessRules()))) &&
            ((this.accountUsers==null && other.getAccountUsers()==null) || 
             (this.accountUsers!=null &&
              java.util.Arrays.equals(this.accountUsers, other.getAccountUsers()))) &&
            ((this.inheritAddress==null && other.getInheritAddress()==null) || 
             (this.inheritAddress!=null &&
              this.inheritAddress.equals(other.getInheritAddress()))) &&
            ((this.isTrialAccount==null && other.getIsTrialAccount()==null) || 
             (this.isTrialAccount!=null &&
              this.isTrialAccount.equals(other.getIsTrialAccount()))) &&
            ((this.locale==null && other.getLocale()==null) || 
             (this.locale!=null &&
              this.locale.equals(other.getLocale()))) &&
            ((this.parentAccount==null && other.getParentAccount()==null) || 
             (this.parentAccount!=null &&
              this.parentAccount.equals(other.getParentAccount()))) &&
            ((this.timeZone==null && other.getTimeZone()==null) || 
             (this.timeZone!=null &&
              this.timeZone.equals(other.getTimeZone()))) &&
            ((this.roles==null && other.getRoles()==null) || 
             (this.roles!=null &&
              java.util.Arrays.equals(this.roles, other.getRoles()))) &&
            ((this.languageLocale==null && other.getLanguageLocale()==null) || 
             (this.languageLocale!=null &&
              this.languageLocale.equals(other.getLanguageLocale())));
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
        if (getAccountType() != null) {
            _hashCode += getAccountType().hashCode();
        }
        if (getParentID() != null) {
            _hashCode += getParentID().hashCode();
        }
        if (getBrandID() != null) {
            _hashCode += getBrandID().hashCode();
        }
        if (getPrivateLabelID() != null) {
            _hashCode += getPrivateLabelID().hashCode();
        }
        if (getReportingParentID() != null) {
            _hashCode += getReportingParentID().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getEmail() != null) {
            _hashCode += getEmail().hashCode();
        }
        if (getFromName() != null) {
            _hashCode += getFromName().hashCode();
        }
        if (getBusinessName() != null) {
            _hashCode += getBusinessName().hashCode();
        }
        if (getPhone() != null) {
            _hashCode += getPhone().hashCode();
        }
        if (getAddress() != null) {
            _hashCode += getAddress().hashCode();
        }
        if (getFax() != null) {
            _hashCode += getFax().hashCode();
        }
        if (getCity() != null) {
            _hashCode += getCity().hashCode();
        }
        if (getState() != null) {
            _hashCode += getState().hashCode();
        }
        if (getZip() != null) {
            _hashCode += getZip().hashCode();
        }
        if (getCountry() != null) {
            _hashCode += getCountry().hashCode();
        }
        if (getIsActive() != null) {
            _hashCode += getIsActive().hashCode();
        }
        if (getIsTestAccount() != null) {
            _hashCode += getIsTestAccount().hashCode();
        }
        if (getOrgID() != null) {
            _hashCode += getOrgID().hashCode();
        }
        if (getDBID() != null) {
            _hashCode += getDBID().hashCode();
        }
        if (getParentName() != null) {
            _hashCode += getParentName().hashCode();
        }
        if (getCustomerID() != null) {
            _hashCode += getCustomerID().hashCode();
        }
        if (getDeletedDate() != null) {
            _hashCode += getDeletedDate().hashCode();
        }
        if (getEditionID() != null) {
            _hashCode += getEditionID().hashCode();
        }
        if (getChildren() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getChildren());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getChildren(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSubscription() != null) {
            _hashCode += getSubscription().hashCode();
        }
        if (getPrivateLabels() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPrivateLabels());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPrivateLabels(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getBusinessRules() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getBusinessRules());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getBusinessRules(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAccountUsers() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAccountUsers());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAccountUsers(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getInheritAddress() != null) {
            _hashCode += getInheritAddress().hashCode();
        }
        if (getIsTrialAccount() != null) {
            _hashCode += getIsTrialAccount().hashCode();
        }
        if (getLocale() != null) {
            _hashCode += getLocale().hashCode();
        }
        if (getParentAccount() != null) {
            _hashCode += getParentAccount().hashCode();
        }
        if (getTimeZone() != null) {
            _hashCode += getTimeZone().hashCode();
        }
        if (getRoles() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRoles());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRoles(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getLanguageLocale() != null) {
            _hashCode += getLanguageLocale().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Account.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Account"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AccountType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AccountTypeEnum"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parentID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ParentID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("brandID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "BrandID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("privateLabelID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PrivateLabelID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reportingParentID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ReportingParentID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("email");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Email"));
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
        elemField.setFieldName("businessName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "BusinessName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("phone");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Phone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("address");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Address"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fax");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Fax"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("city");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "City"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("state");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "State"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("zip");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Zip"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("country");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Country"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isActive");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsActive"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isTestAccount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsTestAccount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orgID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "OrgID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DBID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DBID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parentName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ParentName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customerID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "CustomerID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deletedDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DeletedDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("editionID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "EditionID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("children");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Children"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AccountDataItem"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subscription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Subscription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Subscription"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("privateLabels");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PrivateLabels"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PrivateLabel"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("businessRules");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "BusinessRules"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "BusinessRule"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountUsers");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AccountUsers"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AccountUser"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inheritAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "InheritAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isTrialAccount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsTrialAccount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("locale");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Locale"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Locale"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parentAccount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ParentAccount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Account"));
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
        elemField.setFieldName("roles");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Roles"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Role"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Role"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("languageLocale");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "LanguageLocale"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Locale"));
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
