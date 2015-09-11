/**
 * Email.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class Email  extends com.exacttarget.wsdl.partnerAPI.APIObject  implements java.io.Serializable {
    private java.lang.String name;

    private java.lang.String folder;

    private java.lang.Integer categoryID;

    private java.lang.String HTMLBody;

    private java.lang.String textBody;

    private com.exacttarget.wsdl.partnerAPI.ContentArea[] contentAreas;

    private java.lang.String subject;

    private java.lang.Boolean isActive;

    private java.lang.Boolean isHTMLPaste;

    private java.lang.Integer clonedFromID;

    private java.lang.String status;

    private java.lang.String emailType;

    private java.lang.String characterSet;

    private java.lang.Boolean hasDynamicSubjectLine;

    private java.lang.String contentCheckStatus;

    private java.lang.Boolean syncTextWithHTML;

    private java.lang.String preHeader;

    private java.lang.Boolean isApproved;

    public Email() {
    }

    public Email(
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
           java.lang.String folder,
           java.lang.Integer categoryID,
           java.lang.String HTMLBody,
           java.lang.String textBody,
           com.exacttarget.wsdl.partnerAPI.ContentArea[] contentAreas,
           java.lang.String subject,
           java.lang.Boolean isActive,
           java.lang.Boolean isHTMLPaste,
           java.lang.Integer clonedFromID,
           java.lang.String status,
           java.lang.String emailType,
           java.lang.String characterSet,
           java.lang.Boolean hasDynamicSubjectLine,
           java.lang.String contentCheckStatus,
           java.lang.Boolean syncTextWithHTML,
           java.lang.String preHeader,
           java.lang.Boolean isApproved) {
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
        this.name = name;
        this.folder = folder;
        this.categoryID = categoryID;
        this.HTMLBody = HTMLBody;
        this.textBody = textBody;
        this.contentAreas = contentAreas;
        this.subject = subject;
        this.isActive = isActive;
        this.isHTMLPaste = isHTMLPaste;
        this.clonedFromID = clonedFromID;
        this.status = status;
        this.emailType = emailType;
        this.characterSet = characterSet;
        this.hasDynamicSubjectLine = hasDynamicSubjectLine;
        this.contentCheckStatus = contentCheckStatus;
        this.syncTextWithHTML = syncTextWithHTML;
        this.preHeader = preHeader;
        this.isApproved = isApproved;
    }


    /**
     * Gets the name value for this Email.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this Email.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the folder value for this Email.
     * 
     * @return folder
     */
    public java.lang.String getFolder() {
        return folder;
    }


    /**
     * Sets the folder value for this Email.
     * 
     * @param folder
     */
    public void setFolder(java.lang.String folder) {
        this.folder = folder;
    }


    /**
     * Gets the categoryID value for this Email.
     * 
     * @return categoryID
     */
    public java.lang.Integer getCategoryID() {
        return categoryID;
    }


    /**
     * Sets the categoryID value for this Email.
     * 
     * @param categoryID
     */
    public void setCategoryID(java.lang.Integer categoryID) {
        this.categoryID = categoryID;
    }


    /**
     * Gets the HTMLBody value for this Email.
     * 
     * @return HTMLBody
     */
    public java.lang.String getHTMLBody() {
        return HTMLBody;
    }


    /**
     * Sets the HTMLBody value for this Email.
     * 
     * @param HTMLBody
     */
    public void setHTMLBody(java.lang.String HTMLBody) {
        this.HTMLBody = HTMLBody;
    }


    /**
     * Gets the textBody value for this Email.
     * 
     * @return textBody
     */
    public java.lang.String getTextBody() {
        return textBody;
    }


    /**
     * Sets the textBody value for this Email.
     * 
     * @param textBody
     */
    public void setTextBody(java.lang.String textBody) {
        this.textBody = textBody;
    }


    /**
     * Gets the contentAreas value for this Email.
     * 
     * @return contentAreas
     */
    public com.exacttarget.wsdl.partnerAPI.ContentArea[] getContentAreas() {
        return contentAreas;
    }


    /**
     * Sets the contentAreas value for this Email.
     * 
     * @param contentAreas
     */
    public void setContentAreas(com.exacttarget.wsdl.partnerAPI.ContentArea[] contentAreas) {
        this.contentAreas = contentAreas;
    }

    public com.exacttarget.wsdl.partnerAPI.ContentArea getContentAreas(int i) {
        return this.contentAreas[i];
    }

    public void setContentAreas(int i, com.exacttarget.wsdl.partnerAPI.ContentArea _value) {
        this.contentAreas[i] = _value;
    }


    /**
     * Gets the subject value for this Email.
     * 
     * @return subject
     */
    public java.lang.String getSubject() {
        return subject;
    }


    /**
     * Sets the subject value for this Email.
     * 
     * @param subject
     */
    public void setSubject(java.lang.String subject) {
        this.subject = subject;
    }


    /**
     * Gets the isActive value for this Email.
     * 
     * @return isActive
     */
    public java.lang.Boolean getIsActive() {
        return isActive;
    }


    /**
     * Sets the isActive value for this Email.
     * 
     * @param isActive
     */
    public void setIsActive(java.lang.Boolean isActive) {
        this.isActive = isActive;
    }


    /**
     * Gets the isHTMLPaste value for this Email.
     * 
     * @return isHTMLPaste
     */
    public java.lang.Boolean getIsHTMLPaste() {
        return isHTMLPaste;
    }


    /**
     * Sets the isHTMLPaste value for this Email.
     * 
     * @param isHTMLPaste
     */
    public void setIsHTMLPaste(java.lang.Boolean isHTMLPaste) {
        this.isHTMLPaste = isHTMLPaste;
    }


    /**
     * Gets the clonedFromID value for this Email.
     * 
     * @return clonedFromID
     */
    public java.lang.Integer getClonedFromID() {
        return clonedFromID;
    }


    /**
     * Sets the clonedFromID value for this Email.
     * 
     * @param clonedFromID
     */
    public void setClonedFromID(java.lang.Integer clonedFromID) {
        this.clonedFromID = clonedFromID;
    }


    /**
     * Gets the status value for this Email.
     * 
     * @return status
     */
    public java.lang.String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this Email.
     * 
     * @param status
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }


    /**
     * Gets the emailType value for this Email.
     * 
     * @return emailType
     */
    public java.lang.String getEmailType() {
        return emailType;
    }


    /**
     * Sets the emailType value for this Email.
     * 
     * @param emailType
     */
    public void setEmailType(java.lang.String emailType) {
        this.emailType = emailType;
    }


    /**
     * Gets the characterSet value for this Email.
     * 
     * @return characterSet
     */
    public java.lang.String getCharacterSet() {
        return characterSet;
    }


    /**
     * Sets the characterSet value for this Email.
     * 
     * @param characterSet
     */
    public void setCharacterSet(java.lang.String characterSet) {
        this.characterSet = characterSet;
    }


    /**
     * Gets the hasDynamicSubjectLine value for this Email.
     * 
     * @return hasDynamicSubjectLine
     */
    public java.lang.Boolean getHasDynamicSubjectLine() {
        return hasDynamicSubjectLine;
    }


    /**
     * Sets the hasDynamicSubjectLine value for this Email.
     * 
     * @param hasDynamicSubjectLine
     */
    public void setHasDynamicSubjectLine(java.lang.Boolean hasDynamicSubjectLine) {
        this.hasDynamicSubjectLine = hasDynamicSubjectLine;
    }


    /**
     * Gets the contentCheckStatus value for this Email.
     * 
     * @return contentCheckStatus
     */
    public java.lang.String getContentCheckStatus() {
        return contentCheckStatus;
    }


    /**
     * Sets the contentCheckStatus value for this Email.
     * 
     * @param contentCheckStatus
     */
    public void setContentCheckStatus(java.lang.String contentCheckStatus) {
        this.contentCheckStatus = contentCheckStatus;
    }


    /**
     * Gets the syncTextWithHTML value for this Email.
     * 
     * @return syncTextWithHTML
     */
    public java.lang.Boolean getSyncTextWithHTML() {
        return syncTextWithHTML;
    }


    /**
     * Sets the syncTextWithHTML value for this Email.
     * 
     * @param syncTextWithHTML
     */
    public void setSyncTextWithHTML(java.lang.Boolean syncTextWithHTML) {
        this.syncTextWithHTML = syncTextWithHTML;
    }


    /**
     * Gets the preHeader value for this Email.
     * 
     * @return preHeader
     */
    public java.lang.String getPreHeader() {
        return preHeader;
    }


    /**
     * Sets the preHeader value for this Email.
     * 
     * @param preHeader
     */
    public void setPreHeader(java.lang.String preHeader) {
        this.preHeader = preHeader;
    }


    /**
     * Gets the isApproved value for this Email.
     * 
     * @return isApproved
     */
    public java.lang.Boolean getIsApproved() {
        return isApproved;
    }


    /**
     * Sets the isApproved value for this Email.
     * 
     * @param isApproved
     */
    public void setIsApproved(java.lang.Boolean isApproved) {
        this.isApproved = isApproved;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Email)) return false;
        Email other = (Email) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.folder==null && other.getFolder()==null) || 
             (this.folder!=null &&
              this.folder.equals(other.getFolder()))) &&
            ((this.categoryID==null && other.getCategoryID()==null) || 
             (this.categoryID!=null &&
              this.categoryID.equals(other.getCategoryID()))) &&
            ((this.HTMLBody==null && other.getHTMLBody()==null) || 
             (this.HTMLBody!=null &&
              this.HTMLBody.equals(other.getHTMLBody()))) &&
            ((this.textBody==null && other.getTextBody()==null) || 
             (this.textBody!=null &&
              this.textBody.equals(other.getTextBody()))) &&
            ((this.contentAreas==null && other.getContentAreas()==null) || 
             (this.contentAreas!=null &&
              java.util.Arrays.equals(this.contentAreas, other.getContentAreas()))) &&
            ((this.subject==null && other.getSubject()==null) || 
             (this.subject!=null &&
              this.subject.equals(other.getSubject()))) &&
            ((this.isActive==null && other.getIsActive()==null) || 
             (this.isActive!=null &&
              this.isActive.equals(other.getIsActive()))) &&
            ((this.isHTMLPaste==null && other.getIsHTMLPaste()==null) || 
             (this.isHTMLPaste!=null &&
              this.isHTMLPaste.equals(other.getIsHTMLPaste()))) &&
            ((this.clonedFromID==null && other.getClonedFromID()==null) || 
             (this.clonedFromID!=null &&
              this.clonedFromID.equals(other.getClonedFromID()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.emailType==null && other.getEmailType()==null) || 
             (this.emailType!=null &&
              this.emailType.equals(other.getEmailType()))) &&
            ((this.characterSet==null && other.getCharacterSet()==null) || 
             (this.characterSet!=null &&
              this.characterSet.equals(other.getCharacterSet()))) &&
            ((this.hasDynamicSubjectLine==null && other.getHasDynamicSubjectLine()==null) || 
             (this.hasDynamicSubjectLine!=null &&
              this.hasDynamicSubjectLine.equals(other.getHasDynamicSubjectLine()))) &&
            ((this.contentCheckStatus==null && other.getContentCheckStatus()==null) || 
             (this.contentCheckStatus!=null &&
              this.contentCheckStatus.equals(other.getContentCheckStatus()))) &&
            ((this.syncTextWithHTML==null && other.getSyncTextWithHTML()==null) || 
             (this.syncTextWithHTML!=null &&
              this.syncTextWithHTML.equals(other.getSyncTextWithHTML()))) &&
            ((this.preHeader==null && other.getPreHeader()==null) || 
             (this.preHeader!=null &&
              this.preHeader.equals(other.getPreHeader()))) &&
            ((this.isApproved==null && other.getIsApproved()==null) || 
             (this.isApproved!=null &&
              this.isApproved.equals(other.getIsApproved())));
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
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getFolder() != null) {
            _hashCode += getFolder().hashCode();
        }
        if (getCategoryID() != null) {
            _hashCode += getCategoryID().hashCode();
        }
        if (getHTMLBody() != null) {
            _hashCode += getHTMLBody().hashCode();
        }
        if (getTextBody() != null) {
            _hashCode += getTextBody().hashCode();
        }
        if (getContentAreas() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getContentAreas());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getContentAreas(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSubject() != null) {
            _hashCode += getSubject().hashCode();
        }
        if (getIsActive() != null) {
            _hashCode += getIsActive().hashCode();
        }
        if (getIsHTMLPaste() != null) {
            _hashCode += getIsHTMLPaste().hashCode();
        }
        if (getClonedFromID() != null) {
            _hashCode += getClonedFromID().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getEmailType() != null) {
            _hashCode += getEmailType().hashCode();
        }
        if (getCharacterSet() != null) {
            _hashCode += getCharacterSet().hashCode();
        }
        if (getHasDynamicSubjectLine() != null) {
            _hashCode += getHasDynamicSubjectLine().hashCode();
        }
        if (getContentCheckStatus() != null) {
            _hashCode += getContentCheckStatus().hashCode();
        }
        if (getSyncTextWithHTML() != null) {
            _hashCode += getSyncTextWithHTML().hashCode();
        }
        if (getPreHeader() != null) {
            _hashCode += getPreHeader().hashCode();
        }
        if (getIsApproved() != null) {
            _hashCode += getIsApproved().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Email.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Email"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("folder");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Folder"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("categoryID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "CategoryID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("HTMLBody");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "HTMLBody"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("textBody");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TextBody"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contentAreas");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ContentAreas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ContentArea"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subject");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Subject"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isActive");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsActive"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isHTMLPaste");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsHTMLPaste"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clonedFromID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ClonedFromID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
        elemField.setFieldName("emailType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "EmailType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("characterSet");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "CharacterSet"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hasDynamicSubjectLine");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "HasDynamicSubjectLine"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contentCheckStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ContentCheckStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("syncTextWithHTML");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SyncTextWithHTML"));
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
        elemField.setFieldName("isApproved");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsApproved"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
