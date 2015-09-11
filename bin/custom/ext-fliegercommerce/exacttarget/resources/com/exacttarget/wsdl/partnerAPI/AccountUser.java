/**
 * AccountUser.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class AccountUser  extends com.exacttarget.wsdl.partnerAPI.APIObject  implements java.io.Serializable {
    private java.lang.Integer accountUserID;

    private java.lang.String userID;

    private java.lang.String password;

    private java.lang.String name;

    private java.lang.String email;

    private java.lang.Boolean mustChangePassword;

    private java.lang.Boolean activeFlag;

    private java.lang.String challengePhrase;

    private java.lang.String challengeAnswer;

    private com.exacttarget.wsdl.partnerAPI.UserAccess[] userPermissions;

    private int delete;

    private java.util.Calendar lastSuccessfulLogin;

    private java.lang.Boolean isAPIUser;

    private java.lang.String notificationEmailAddress;

    private java.lang.Boolean isLocked;

    private java.lang.Boolean unlock;

    private java.lang.Integer businessUnit;

    private java.lang.Integer defaultBusinessUnit;

    private java.lang.String defaultApplication;

    private com.exacttarget.wsdl.partnerAPI.Locale locale;

    private com.exacttarget.wsdl.partnerAPI.TimeZone timeZone;

    private com.exacttarget.wsdl.partnerAPI.BusinessUnit defaultBusinessUnitObject;

    private com.exacttarget.wsdl.partnerAPI.BusinessUnit[] associatedBusinessUnits;

    private com.exacttarget.wsdl.partnerAPI.Role[] roles;

    private com.exacttarget.wsdl.partnerAPI.Locale languageLocale;

    private com.exacttarget.wsdl.partnerAPI.SsoIdentity[] ssoIdentities;

    public AccountUser() {
    }

    public AccountUser(
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
           java.lang.Integer accountUserID,
           java.lang.String userID,
           java.lang.String password,
           java.lang.String name,
           java.lang.String email,
           java.lang.Boolean mustChangePassword,
           java.lang.Boolean activeFlag,
           java.lang.String challengePhrase,
           java.lang.String challengeAnswer,
           com.exacttarget.wsdl.partnerAPI.UserAccess[] userPermissions,
           int delete,
           java.util.Calendar lastSuccessfulLogin,
           java.lang.Boolean isAPIUser,
           java.lang.String notificationEmailAddress,
           java.lang.Boolean isLocked,
           java.lang.Boolean unlock,
           java.lang.Integer businessUnit,
           java.lang.Integer defaultBusinessUnit,
           java.lang.String defaultApplication,
           com.exacttarget.wsdl.partnerAPI.Locale locale,
           com.exacttarget.wsdl.partnerAPI.TimeZone timeZone,
           com.exacttarget.wsdl.partnerAPI.BusinessUnit defaultBusinessUnitObject,
           com.exacttarget.wsdl.partnerAPI.BusinessUnit[] associatedBusinessUnits,
           com.exacttarget.wsdl.partnerAPI.Role[] roles,
           com.exacttarget.wsdl.partnerAPI.Locale languageLocale,
           com.exacttarget.wsdl.partnerAPI.SsoIdentity[] ssoIdentities) {
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
        this.accountUserID = accountUserID;
        this.userID = userID;
        this.password = password;
        this.name = name;
        this.email = email;
        this.mustChangePassword = mustChangePassword;
        this.activeFlag = activeFlag;
        this.challengePhrase = challengePhrase;
        this.challengeAnswer = challengeAnswer;
        this.userPermissions = userPermissions;
        this.delete = delete;
        this.lastSuccessfulLogin = lastSuccessfulLogin;
        this.isAPIUser = isAPIUser;
        this.notificationEmailAddress = notificationEmailAddress;
        this.isLocked = isLocked;
        this.unlock = unlock;
        this.businessUnit = businessUnit;
        this.defaultBusinessUnit = defaultBusinessUnit;
        this.defaultApplication = defaultApplication;
        this.locale = locale;
        this.timeZone = timeZone;
        this.defaultBusinessUnitObject = defaultBusinessUnitObject;
        this.associatedBusinessUnits = associatedBusinessUnits;
        this.roles = roles;
        this.languageLocale = languageLocale;
        this.ssoIdentities = ssoIdentities;
    }


    /**
     * Gets the accountUserID value for this AccountUser.
     * 
     * @return accountUserID
     */
    public java.lang.Integer getAccountUserID() {
        return accountUserID;
    }


    /**
     * Sets the accountUserID value for this AccountUser.
     * 
     * @param accountUserID
     */
    public void setAccountUserID(java.lang.Integer accountUserID) {
        this.accountUserID = accountUserID;
    }


    /**
     * Gets the userID value for this AccountUser.
     * 
     * @return userID
     */
    public java.lang.String getUserID() {
        return userID;
    }


    /**
     * Sets the userID value for this AccountUser.
     * 
     * @param userID
     */
    public void setUserID(java.lang.String userID) {
        this.userID = userID;
    }


    /**
     * Gets the password value for this AccountUser.
     * 
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this AccountUser.
     * 
     * @param password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the name value for this AccountUser.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this AccountUser.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the email value for this AccountUser.
     * 
     * @return email
     */
    public java.lang.String getEmail() {
        return email;
    }


    /**
     * Sets the email value for this AccountUser.
     * 
     * @param email
     */
    public void setEmail(java.lang.String email) {
        this.email = email;
    }


    /**
     * Gets the mustChangePassword value for this AccountUser.
     * 
     * @return mustChangePassword
     */
    public java.lang.Boolean getMustChangePassword() {
        return mustChangePassword;
    }


    /**
     * Sets the mustChangePassword value for this AccountUser.
     * 
     * @param mustChangePassword
     */
    public void setMustChangePassword(java.lang.Boolean mustChangePassword) {
        this.mustChangePassword = mustChangePassword;
    }


    /**
     * Gets the activeFlag value for this AccountUser.
     * 
     * @return activeFlag
     */
    public java.lang.Boolean getActiveFlag() {
        return activeFlag;
    }


    /**
     * Sets the activeFlag value for this AccountUser.
     * 
     * @param activeFlag
     */
    public void setActiveFlag(java.lang.Boolean activeFlag) {
        this.activeFlag = activeFlag;
    }


    /**
     * Gets the challengePhrase value for this AccountUser.
     * 
     * @return challengePhrase
     */
    public java.lang.String getChallengePhrase() {
        return challengePhrase;
    }


    /**
     * Sets the challengePhrase value for this AccountUser.
     * 
     * @param challengePhrase
     */
    public void setChallengePhrase(java.lang.String challengePhrase) {
        this.challengePhrase = challengePhrase;
    }


    /**
     * Gets the challengeAnswer value for this AccountUser.
     * 
     * @return challengeAnswer
     */
    public java.lang.String getChallengeAnswer() {
        return challengeAnswer;
    }


    /**
     * Sets the challengeAnswer value for this AccountUser.
     * 
     * @param challengeAnswer
     */
    public void setChallengeAnswer(java.lang.String challengeAnswer) {
        this.challengeAnswer = challengeAnswer;
    }


    /**
     * Gets the userPermissions value for this AccountUser.
     * 
     * @return userPermissions
     */
    public com.exacttarget.wsdl.partnerAPI.UserAccess[] getUserPermissions() {
        return userPermissions;
    }


    /**
     * Sets the userPermissions value for this AccountUser.
     * 
     * @param userPermissions
     */
    public void setUserPermissions(com.exacttarget.wsdl.partnerAPI.UserAccess[] userPermissions) {
        this.userPermissions = userPermissions;
    }

    public com.exacttarget.wsdl.partnerAPI.UserAccess getUserPermissions(int i) {
        return this.userPermissions[i];
    }

    public void setUserPermissions(int i, com.exacttarget.wsdl.partnerAPI.UserAccess _value) {
        this.userPermissions[i] = _value;
    }


    /**
     * Gets the delete value for this AccountUser.
     * 
     * @return delete
     */
    public int getDelete() {
        return delete;
    }


    /**
     * Sets the delete value for this AccountUser.
     * 
     * @param delete
     */
    public void setDelete(int delete) {
        this.delete = delete;
    }


    /**
     * Gets the lastSuccessfulLogin value for this AccountUser.
     * 
     * @return lastSuccessfulLogin
     */
    public java.util.Calendar getLastSuccessfulLogin() {
        return lastSuccessfulLogin;
    }


    /**
     * Sets the lastSuccessfulLogin value for this AccountUser.
     * 
     * @param lastSuccessfulLogin
     */
    public void setLastSuccessfulLogin(java.util.Calendar lastSuccessfulLogin) {
        this.lastSuccessfulLogin = lastSuccessfulLogin;
    }


    /**
     * Gets the isAPIUser value for this AccountUser.
     * 
     * @return isAPIUser
     */
    public java.lang.Boolean getIsAPIUser() {
        return isAPIUser;
    }


    /**
     * Sets the isAPIUser value for this AccountUser.
     * 
     * @param isAPIUser
     */
    public void setIsAPIUser(java.lang.Boolean isAPIUser) {
        this.isAPIUser = isAPIUser;
    }


    /**
     * Gets the notificationEmailAddress value for this AccountUser.
     * 
     * @return notificationEmailAddress
     */
    public java.lang.String getNotificationEmailAddress() {
        return notificationEmailAddress;
    }


    /**
     * Sets the notificationEmailAddress value for this AccountUser.
     * 
     * @param notificationEmailAddress
     */
    public void setNotificationEmailAddress(java.lang.String notificationEmailAddress) {
        this.notificationEmailAddress = notificationEmailAddress;
    }


    /**
     * Gets the isLocked value for this AccountUser.
     * 
     * @return isLocked
     */
    public java.lang.Boolean getIsLocked() {
        return isLocked;
    }


    /**
     * Sets the isLocked value for this AccountUser.
     * 
     * @param isLocked
     */
    public void setIsLocked(java.lang.Boolean isLocked) {
        this.isLocked = isLocked;
    }


    /**
     * Gets the unlock value for this AccountUser.
     * 
     * @return unlock
     */
    public java.lang.Boolean getUnlock() {
        return unlock;
    }


    /**
     * Sets the unlock value for this AccountUser.
     * 
     * @param unlock
     */
    public void setUnlock(java.lang.Boolean unlock) {
        this.unlock = unlock;
    }


    /**
     * Gets the businessUnit value for this AccountUser.
     * 
     * @return businessUnit
     */
    public java.lang.Integer getBusinessUnit() {
        return businessUnit;
    }


    /**
     * Sets the businessUnit value for this AccountUser.
     * 
     * @param businessUnit
     */
    public void setBusinessUnit(java.lang.Integer businessUnit) {
        this.businessUnit = businessUnit;
    }


    /**
     * Gets the defaultBusinessUnit value for this AccountUser.
     * 
     * @return defaultBusinessUnit
     */
    public java.lang.Integer getDefaultBusinessUnit() {
        return defaultBusinessUnit;
    }


    /**
     * Sets the defaultBusinessUnit value for this AccountUser.
     * 
     * @param defaultBusinessUnit
     */
    public void setDefaultBusinessUnit(java.lang.Integer defaultBusinessUnit) {
        this.defaultBusinessUnit = defaultBusinessUnit;
    }


    /**
     * Gets the defaultApplication value for this AccountUser.
     * 
     * @return defaultApplication
     */
    public java.lang.String getDefaultApplication() {
        return defaultApplication;
    }


    /**
     * Sets the defaultApplication value for this AccountUser.
     * 
     * @param defaultApplication
     */
    public void setDefaultApplication(java.lang.String defaultApplication) {
        this.defaultApplication = defaultApplication;
    }


    /**
     * Gets the locale value for this AccountUser.
     * 
     * @return locale
     */
    public com.exacttarget.wsdl.partnerAPI.Locale getLocale() {
        return locale;
    }


    /**
     * Sets the locale value for this AccountUser.
     * 
     * @param locale
     */
    public void setLocale(com.exacttarget.wsdl.partnerAPI.Locale locale) {
        this.locale = locale;
    }


    /**
     * Gets the timeZone value for this AccountUser.
     * 
     * @return timeZone
     */
    public com.exacttarget.wsdl.partnerAPI.TimeZone getTimeZone() {
        return timeZone;
    }


    /**
     * Sets the timeZone value for this AccountUser.
     * 
     * @param timeZone
     */
    public void setTimeZone(com.exacttarget.wsdl.partnerAPI.TimeZone timeZone) {
        this.timeZone = timeZone;
    }


    /**
     * Gets the defaultBusinessUnitObject value for this AccountUser.
     * 
     * @return defaultBusinessUnitObject
     */
    public com.exacttarget.wsdl.partnerAPI.BusinessUnit getDefaultBusinessUnitObject() {
        return defaultBusinessUnitObject;
    }


    /**
     * Sets the defaultBusinessUnitObject value for this AccountUser.
     * 
     * @param defaultBusinessUnitObject
     */
    public void setDefaultBusinessUnitObject(com.exacttarget.wsdl.partnerAPI.BusinessUnit defaultBusinessUnitObject) {
        this.defaultBusinessUnitObject = defaultBusinessUnitObject;
    }


    /**
     * Gets the associatedBusinessUnits value for this AccountUser.
     * 
     * @return associatedBusinessUnits
     */
    public com.exacttarget.wsdl.partnerAPI.BusinessUnit[] getAssociatedBusinessUnits() {
        return associatedBusinessUnits;
    }


    /**
     * Sets the associatedBusinessUnits value for this AccountUser.
     * 
     * @param associatedBusinessUnits
     */
    public void setAssociatedBusinessUnits(com.exacttarget.wsdl.partnerAPI.BusinessUnit[] associatedBusinessUnits) {
        this.associatedBusinessUnits = associatedBusinessUnits;
    }


    /**
     * Gets the roles value for this AccountUser.
     * 
     * @return roles
     */
    public com.exacttarget.wsdl.partnerAPI.Role[] getRoles() {
        return roles;
    }


    /**
     * Sets the roles value for this AccountUser.
     * 
     * @param roles
     */
    public void setRoles(com.exacttarget.wsdl.partnerAPI.Role[] roles) {
        this.roles = roles;
    }


    /**
     * Gets the languageLocale value for this AccountUser.
     * 
     * @return languageLocale
     */
    public com.exacttarget.wsdl.partnerAPI.Locale getLanguageLocale() {
        return languageLocale;
    }


    /**
     * Sets the languageLocale value for this AccountUser.
     * 
     * @param languageLocale
     */
    public void setLanguageLocale(com.exacttarget.wsdl.partnerAPI.Locale languageLocale) {
        this.languageLocale = languageLocale;
    }


    /**
     * Gets the ssoIdentities value for this AccountUser.
     * 
     * @return ssoIdentities
     */
    public com.exacttarget.wsdl.partnerAPI.SsoIdentity[] getSsoIdentities() {
        return ssoIdentities;
    }


    /**
     * Sets the ssoIdentities value for this AccountUser.
     * 
     * @param ssoIdentities
     */
    public void setSsoIdentities(com.exacttarget.wsdl.partnerAPI.SsoIdentity[] ssoIdentities) {
        this.ssoIdentities = ssoIdentities;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AccountUser)) return false;
        AccountUser other = (AccountUser) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.accountUserID==null && other.getAccountUserID()==null) || 
             (this.accountUserID!=null &&
              this.accountUserID.equals(other.getAccountUserID()))) &&
            ((this.userID==null && other.getUserID()==null) || 
             (this.userID!=null &&
              this.userID.equals(other.getUserID()))) &&
            ((this.password==null && other.getPassword()==null) || 
             (this.password!=null &&
              this.password.equals(other.getPassword()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.email==null && other.getEmail()==null) || 
             (this.email!=null &&
              this.email.equals(other.getEmail()))) &&
            ((this.mustChangePassword==null && other.getMustChangePassword()==null) || 
             (this.mustChangePassword!=null &&
              this.mustChangePassword.equals(other.getMustChangePassword()))) &&
            ((this.activeFlag==null && other.getActiveFlag()==null) || 
             (this.activeFlag!=null &&
              this.activeFlag.equals(other.getActiveFlag()))) &&
            ((this.challengePhrase==null && other.getChallengePhrase()==null) || 
             (this.challengePhrase!=null &&
              this.challengePhrase.equals(other.getChallengePhrase()))) &&
            ((this.challengeAnswer==null && other.getChallengeAnswer()==null) || 
             (this.challengeAnswer!=null &&
              this.challengeAnswer.equals(other.getChallengeAnswer()))) &&
            ((this.userPermissions==null && other.getUserPermissions()==null) || 
             (this.userPermissions!=null &&
              java.util.Arrays.equals(this.userPermissions, other.getUserPermissions()))) &&
            this.delete == other.getDelete() &&
            ((this.lastSuccessfulLogin==null && other.getLastSuccessfulLogin()==null) || 
             (this.lastSuccessfulLogin!=null &&
              this.lastSuccessfulLogin.equals(other.getLastSuccessfulLogin()))) &&
            ((this.isAPIUser==null && other.getIsAPIUser()==null) || 
             (this.isAPIUser!=null &&
              this.isAPIUser.equals(other.getIsAPIUser()))) &&
            ((this.notificationEmailAddress==null && other.getNotificationEmailAddress()==null) || 
             (this.notificationEmailAddress!=null &&
              this.notificationEmailAddress.equals(other.getNotificationEmailAddress()))) &&
            ((this.isLocked==null && other.getIsLocked()==null) || 
             (this.isLocked!=null &&
              this.isLocked.equals(other.getIsLocked()))) &&
            ((this.unlock==null && other.getUnlock()==null) || 
             (this.unlock!=null &&
              this.unlock.equals(other.getUnlock()))) &&
            ((this.businessUnit==null && other.getBusinessUnit()==null) || 
             (this.businessUnit!=null &&
              this.businessUnit.equals(other.getBusinessUnit()))) &&
            ((this.defaultBusinessUnit==null && other.getDefaultBusinessUnit()==null) || 
             (this.defaultBusinessUnit!=null &&
              this.defaultBusinessUnit.equals(other.getDefaultBusinessUnit()))) &&
            ((this.defaultApplication==null && other.getDefaultApplication()==null) || 
             (this.defaultApplication!=null &&
              this.defaultApplication.equals(other.getDefaultApplication()))) &&
            ((this.locale==null && other.getLocale()==null) || 
             (this.locale!=null &&
              this.locale.equals(other.getLocale()))) &&
            ((this.timeZone==null && other.getTimeZone()==null) || 
             (this.timeZone!=null &&
              this.timeZone.equals(other.getTimeZone()))) &&
            ((this.defaultBusinessUnitObject==null && other.getDefaultBusinessUnitObject()==null) || 
             (this.defaultBusinessUnitObject!=null &&
              this.defaultBusinessUnitObject.equals(other.getDefaultBusinessUnitObject()))) &&
            ((this.associatedBusinessUnits==null && other.getAssociatedBusinessUnits()==null) || 
             (this.associatedBusinessUnits!=null &&
              java.util.Arrays.equals(this.associatedBusinessUnits, other.getAssociatedBusinessUnits()))) &&
            ((this.roles==null && other.getRoles()==null) || 
             (this.roles!=null &&
              java.util.Arrays.equals(this.roles, other.getRoles()))) &&
            ((this.languageLocale==null && other.getLanguageLocale()==null) || 
             (this.languageLocale!=null &&
              this.languageLocale.equals(other.getLanguageLocale()))) &&
            ((this.ssoIdentities==null && other.getSsoIdentities()==null) || 
             (this.ssoIdentities!=null &&
              java.util.Arrays.equals(this.ssoIdentities, other.getSsoIdentities())));
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
        if (getAccountUserID() != null) {
            _hashCode += getAccountUserID().hashCode();
        }
        if (getUserID() != null) {
            _hashCode += getUserID().hashCode();
        }
        if (getPassword() != null) {
            _hashCode += getPassword().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getEmail() != null) {
            _hashCode += getEmail().hashCode();
        }
        if (getMustChangePassword() != null) {
            _hashCode += getMustChangePassword().hashCode();
        }
        if (getActiveFlag() != null) {
            _hashCode += getActiveFlag().hashCode();
        }
        if (getChallengePhrase() != null) {
            _hashCode += getChallengePhrase().hashCode();
        }
        if (getChallengeAnswer() != null) {
            _hashCode += getChallengeAnswer().hashCode();
        }
        if (getUserPermissions() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUserPermissions());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUserPermissions(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += getDelete();
        if (getLastSuccessfulLogin() != null) {
            _hashCode += getLastSuccessfulLogin().hashCode();
        }
        if (getIsAPIUser() != null) {
            _hashCode += getIsAPIUser().hashCode();
        }
        if (getNotificationEmailAddress() != null) {
            _hashCode += getNotificationEmailAddress().hashCode();
        }
        if (getIsLocked() != null) {
            _hashCode += getIsLocked().hashCode();
        }
        if (getUnlock() != null) {
            _hashCode += getUnlock().hashCode();
        }
        if (getBusinessUnit() != null) {
            _hashCode += getBusinessUnit().hashCode();
        }
        if (getDefaultBusinessUnit() != null) {
            _hashCode += getDefaultBusinessUnit().hashCode();
        }
        if (getDefaultApplication() != null) {
            _hashCode += getDefaultApplication().hashCode();
        }
        if (getLocale() != null) {
            _hashCode += getLocale().hashCode();
        }
        if (getTimeZone() != null) {
            _hashCode += getTimeZone().hashCode();
        }
        if (getDefaultBusinessUnitObject() != null) {
            _hashCode += getDefaultBusinessUnitObject().hashCode();
        }
        if (getAssociatedBusinessUnits() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAssociatedBusinessUnits());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAssociatedBusinessUnits(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
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
        if (getSsoIdentities() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSsoIdentities());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSsoIdentities(), i);
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
        new org.apache.axis.description.TypeDesc(AccountUser.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AccountUser"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountUserID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AccountUserID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "UserID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("password");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Password"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("email");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Email"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mustChangePassword");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MustChangePassword"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activeFlag");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ActiveFlag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("challengePhrase");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ChallengePhrase"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("challengeAnswer");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ChallengeAnswer"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userPermissions");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "UserPermissions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "UserAccess"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("delete");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Delete"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastSuccessfulLogin");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "LastSuccessfulLogin"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isAPIUser");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsAPIUser"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("notificationEmailAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "NotificationEmailAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isLocked");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsLocked"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unlock");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Unlock"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("businessUnit");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "BusinessUnit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("defaultBusinessUnit");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DefaultBusinessUnit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("defaultApplication");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DefaultApplication"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setFieldName("timeZone");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TimeZone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TimeZone"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("defaultBusinessUnitObject");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DefaultBusinessUnitObject"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "BusinessUnit"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("associatedBusinessUnits");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AssociatedBusinessUnits"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "BusinessUnit"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "BusinessUnit"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ssoIdentities");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SsoIdentities"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SsoIdentity"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SsoIdentity"));
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
