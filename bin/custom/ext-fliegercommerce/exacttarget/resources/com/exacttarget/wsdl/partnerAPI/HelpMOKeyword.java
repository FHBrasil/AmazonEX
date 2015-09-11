/**
 * HelpMOKeyword.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class HelpMOKeyword  extends com.exacttarget.wsdl.partnerAPI.BaseMOKeyword  implements java.io.Serializable {
    private java.lang.String friendlyName;

    private java.lang.String defaultHelpMessage;

    private java.lang.String menuText;

    private java.lang.String moreChoicesPrompt;

    public HelpMOKeyword() {
    }

    public HelpMOKeyword(
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
           java.lang.Boolean isDefaultKeyword,
           java.lang.String friendlyName,
           java.lang.String defaultHelpMessage,
           java.lang.String menuText,
           java.lang.String moreChoicesPrompt) {
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
            isDefaultKeyword);
        this.friendlyName = friendlyName;
        this.defaultHelpMessage = defaultHelpMessage;
        this.menuText = menuText;
        this.moreChoicesPrompt = moreChoicesPrompt;
    }


    /**
     * Gets the friendlyName value for this HelpMOKeyword.
     * 
     * @return friendlyName
     */
    public java.lang.String getFriendlyName() {
        return friendlyName;
    }


    /**
     * Sets the friendlyName value for this HelpMOKeyword.
     * 
     * @param friendlyName
     */
    public void setFriendlyName(java.lang.String friendlyName) {
        this.friendlyName = friendlyName;
    }


    /**
     * Gets the defaultHelpMessage value for this HelpMOKeyword.
     * 
     * @return defaultHelpMessage
     */
    public java.lang.String getDefaultHelpMessage() {
        return defaultHelpMessage;
    }


    /**
     * Sets the defaultHelpMessage value for this HelpMOKeyword.
     * 
     * @param defaultHelpMessage
     */
    public void setDefaultHelpMessage(java.lang.String defaultHelpMessage) {
        this.defaultHelpMessage = defaultHelpMessage;
    }


    /**
     * Gets the menuText value for this HelpMOKeyword.
     * 
     * @return menuText
     */
    public java.lang.String getMenuText() {
        return menuText;
    }


    /**
     * Sets the menuText value for this HelpMOKeyword.
     * 
     * @param menuText
     */
    public void setMenuText(java.lang.String menuText) {
        this.menuText = menuText;
    }


    /**
     * Gets the moreChoicesPrompt value for this HelpMOKeyword.
     * 
     * @return moreChoicesPrompt
     */
    public java.lang.String getMoreChoicesPrompt() {
        return moreChoicesPrompt;
    }


    /**
     * Sets the moreChoicesPrompt value for this HelpMOKeyword.
     * 
     * @param moreChoicesPrompt
     */
    public void setMoreChoicesPrompt(java.lang.String moreChoicesPrompt) {
        this.moreChoicesPrompt = moreChoicesPrompt;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof HelpMOKeyword)) return false;
        HelpMOKeyword other = (HelpMOKeyword) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.friendlyName==null && other.getFriendlyName()==null) || 
             (this.friendlyName!=null &&
              this.friendlyName.equals(other.getFriendlyName()))) &&
            ((this.defaultHelpMessage==null && other.getDefaultHelpMessage()==null) || 
             (this.defaultHelpMessage!=null &&
              this.defaultHelpMessage.equals(other.getDefaultHelpMessage()))) &&
            ((this.menuText==null && other.getMenuText()==null) || 
             (this.menuText!=null &&
              this.menuText.equals(other.getMenuText()))) &&
            ((this.moreChoicesPrompt==null && other.getMoreChoicesPrompt()==null) || 
             (this.moreChoicesPrompt!=null &&
              this.moreChoicesPrompt.equals(other.getMoreChoicesPrompt())));
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
        if (getFriendlyName() != null) {
            _hashCode += getFriendlyName().hashCode();
        }
        if (getDefaultHelpMessage() != null) {
            _hashCode += getDefaultHelpMessage().hashCode();
        }
        if (getMenuText() != null) {
            _hashCode += getMenuText().hashCode();
        }
        if (getMoreChoicesPrompt() != null) {
            _hashCode += getMoreChoicesPrompt().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(HelpMOKeyword.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "HelpMOKeyword"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("friendlyName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FriendlyName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("defaultHelpMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DefaultHelpMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("menuText");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MenuText"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("moreChoicesPrompt");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MoreChoicesPrompt"));
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
