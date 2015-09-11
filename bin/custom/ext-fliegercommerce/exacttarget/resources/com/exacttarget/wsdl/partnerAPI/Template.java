/**
 * Template.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class Template  extends com.exacttarget.wsdl.partnerAPI.APIObject  implements java.io.Serializable {
    private java.lang.String templateName;

    private java.lang.String layoutHTML;

    private java.lang.String backgroundColor;

    private java.lang.String borderColor;

    private java.lang.Integer borderWidth;

    private java.lang.Integer cellpadding;

    private java.lang.Integer cellspacing;

    private java.lang.Integer width;

    private java.lang.String align;

    private java.lang.Integer activeFlag;

    private java.lang.Integer categoryID;

    private java.lang.String categoryType;

    private java.lang.Integer ownerID;

    private com.exacttarget.wsdl.partnerAPI.ContentArea headerContent;

    private com.exacttarget.wsdl.partnerAPI.Layout layout;

    private java.lang.String templateSubject;

    private java.lang.Boolean isTemplateSubjectLocked;

    private java.lang.String preHeader;

    public Template() {
    }

    public Template(
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
           java.lang.String templateName,
           java.lang.String layoutHTML,
           java.lang.String backgroundColor,
           java.lang.String borderColor,
           java.lang.Integer borderWidth,
           java.lang.Integer cellpadding,
           java.lang.Integer cellspacing,
           java.lang.Integer width,
           java.lang.String align,
           java.lang.Integer activeFlag,
           java.lang.Integer categoryID,
           java.lang.String categoryType,
           java.lang.Integer ownerID,
           com.exacttarget.wsdl.partnerAPI.ContentArea headerContent,
           com.exacttarget.wsdl.partnerAPI.Layout layout,
           java.lang.String templateSubject,
           java.lang.Boolean isTemplateSubjectLocked,
           java.lang.String preHeader) {
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
        this.templateName = templateName;
        this.layoutHTML = layoutHTML;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
        this.borderWidth = borderWidth;
        this.cellpadding = cellpadding;
        this.cellspacing = cellspacing;
        this.width = width;
        this.align = align;
        this.activeFlag = activeFlag;
        this.categoryID = categoryID;
        this.categoryType = categoryType;
        this.ownerID = ownerID;
        this.headerContent = headerContent;
        this.layout = layout;
        this.templateSubject = templateSubject;
        this.isTemplateSubjectLocked = isTemplateSubjectLocked;
        this.preHeader = preHeader;
    }


    /**
     * Gets the templateName value for this Template.
     * 
     * @return templateName
     */
    public java.lang.String getTemplateName() {
        return templateName;
    }


    /**
     * Sets the templateName value for this Template.
     * 
     * @param templateName
     */
    public void setTemplateName(java.lang.String templateName) {
        this.templateName = templateName;
    }


    /**
     * Gets the layoutHTML value for this Template.
     * 
     * @return layoutHTML
     */
    public java.lang.String getLayoutHTML() {
        return layoutHTML;
    }


    /**
     * Sets the layoutHTML value for this Template.
     * 
     * @param layoutHTML
     */
    public void setLayoutHTML(java.lang.String layoutHTML) {
        this.layoutHTML = layoutHTML;
    }


    /**
     * Gets the backgroundColor value for this Template.
     * 
     * @return backgroundColor
     */
    public java.lang.String getBackgroundColor() {
        return backgroundColor;
    }


    /**
     * Sets the backgroundColor value for this Template.
     * 
     * @param backgroundColor
     */
    public void setBackgroundColor(java.lang.String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }


    /**
     * Gets the borderColor value for this Template.
     * 
     * @return borderColor
     */
    public java.lang.String getBorderColor() {
        return borderColor;
    }


    /**
     * Sets the borderColor value for this Template.
     * 
     * @param borderColor
     */
    public void setBorderColor(java.lang.String borderColor) {
        this.borderColor = borderColor;
    }


    /**
     * Gets the borderWidth value for this Template.
     * 
     * @return borderWidth
     */
    public java.lang.Integer getBorderWidth() {
        return borderWidth;
    }


    /**
     * Sets the borderWidth value for this Template.
     * 
     * @param borderWidth
     */
    public void setBorderWidth(java.lang.Integer borderWidth) {
        this.borderWidth = borderWidth;
    }


    /**
     * Gets the cellpadding value for this Template.
     * 
     * @return cellpadding
     */
    public java.lang.Integer getCellpadding() {
        return cellpadding;
    }


    /**
     * Sets the cellpadding value for this Template.
     * 
     * @param cellpadding
     */
    public void setCellpadding(java.lang.Integer cellpadding) {
        this.cellpadding = cellpadding;
    }


    /**
     * Gets the cellspacing value for this Template.
     * 
     * @return cellspacing
     */
    public java.lang.Integer getCellspacing() {
        return cellspacing;
    }


    /**
     * Sets the cellspacing value for this Template.
     * 
     * @param cellspacing
     */
    public void setCellspacing(java.lang.Integer cellspacing) {
        this.cellspacing = cellspacing;
    }


    /**
     * Gets the width value for this Template.
     * 
     * @return width
     */
    public java.lang.Integer getWidth() {
        return width;
    }


    /**
     * Sets the width value for this Template.
     * 
     * @param width
     */
    public void setWidth(java.lang.Integer width) {
        this.width = width;
    }


    /**
     * Gets the align value for this Template.
     * 
     * @return align
     */
    public java.lang.String getAlign() {
        return align;
    }


    /**
     * Sets the align value for this Template.
     * 
     * @param align
     */
    public void setAlign(java.lang.String align) {
        this.align = align;
    }


    /**
     * Gets the activeFlag value for this Template.
     * 
     * @return activeFlag
     */
    public java.lang.Integer getActiveFlag() {
        return activeFlag;
    }


    /**
     * Sets the activeFlag value for this Template.
     * 
     * @param activeFlag
     */
    public void setActiveFlag(java.lang.Integer activeFlag) {
        this.activeFlag = activeFlag;
    }


    /**
     * Gets the categoryID value for this Template.
     * 
     * @return categoryID
     */
    public java.lang.Integer getCategoryID() {
        return categoryID;
    }


    /**
     * Sets the categoryID value for this Template.
     * 
     * @param categoryID
     */
    public void setCategoryID(java.lang.Integer categoryID) {
        this.categoryID = categoryID;
    }


    /**
     * Gets the categoryType value for this Template.
     * 
     * @return categoryType
     */
    public java.lang.String getCategoryType() {
        return categoryType;
    }


    /**
     * Sets the categoryType value for this Template.
     * 
     * @param categoryType
     */
    public void setCategoryType(java.lang.String categoryType) {
        this.categoryType = categoryType;
    }


    /**
     * Gets the ownerID value for this Template.
     * 
     * @return ownerID
     */
    public java.lang.Integer getOwnerID() {
        return ownerID;
    }


    /**
     * Sets the ownerID value for this Template.
     * 
     * @param ownerID
     */
    public void setOwnerID(java.lang.Integer ownerID) {
        this.ownerID = ownerID;
    }


    /**
     * Gets the headerContent value for this Template.
     * 
     * @return headerContent
     */
    public com.exacttarget.wsdl.partnerAPI.ContentArea getHeaderContent() {
        return headerContent;
    }


    /**
     * Sets the headerContent value for this Template.
     * 
     * @param headerContent
     */
    public void setHeaderContent(com.exacttarget.wsdl.partnerAPI.ContentArea headerContent) {
        this.headerContent = headerContent;
    }


    /**
     * Gets the layout value for this Template.
     * 
     * @return layout
     */
    public com.exacttarget.wsdl.partnerAPI.Layout getLayout() {
        return layout;
    }


    /**
     * Sets the layout value for this Template.
     * 
     * @param layout
     */
    public void setLayout(com.exacttarget.wsdl.partnerAPI.Layout layout) {
        this.layout = layout;
    }


    /**
     * Gets the templateSubject value for this Template.
     * 
     * @return templateSubject
     */
    public java.lang.String getTemplateSubject() {
        return templateSubject;
    }


    /**
     * Sets the templateSubject value for this Template.
     * 
     * @param templateSubject
     */
    public void setTemplateSubject(java.lang.String templateSubject) {
        this.templateSubject = templateSubject;
    }


    /**
     * Gets the isTemplateSubjectLocked value for this Template.
     * 
     * @return isTemplateSubjectLocked
     */
    public java.lang.Boolean getIsTemplateSubjectLocked() {
        return isTemplateSubjectLocked;
    }


    /**
     * Sets the isTemplateSubjectLocked value for this Template.
     * 
     * @param isTemplateSubjectLocked
     */
    public void setIsTemplateSubjectLocked(java.lang.Boolean isTemplateSubjectLocked) {
        this.isTemplateSubjectLocked = isTemplateSubjectLocked;
    }


    /**
     * Gets the preHeader value for this Template.
     * 
     * @return preHeader
     */
    public java.lang.String getPreHeader() {
        return preHeader;
    }


    /**
     * Sets the preHeader value for this Template.
     * 
     * @param preHeader
     */
    public void setPreHeader(java.lang.String preHeader) {
        this.preHeader = preHeader;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Template)) return false;
        Template other = (Template) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.templateName==null && other.getTemplateName()==null) || 
             (this.templateName!=null &&
              this.templateName.equals(other.getTemplateName()))) &&
            ((this.layoutHTML==null && other.getLayoutHTML()==null) || 
             (this.layoutHTML!=null &&
              this.layoutHTML.equals(other.getLayoutHTML()))) &&
            ((this.backgroundColor==null && other.getBackgroundColor()==null) || 
             (this.backgroundColor!=null &&
              this.backgroundColor.equals(other.getBackgroundColor()))) &&
            ((this.borderColor==null && other.getBorderColor()==null) || 
             (this.borderColor!=null &&
              this.borderColor.equals(other.getBorderColor()))) &&
            ((this.borderWidth==null && other.getBorderWidth()==null) || 
             (this.borderWidth!=null &&
              this.borderWidth.equals(other.getBorderWidth()))) &&
            ((this.cellpadding==null && other.getCellpadding()==null) || 
             (this.cellpadding!=null &&
              this.cellpadding.equals(other.getCellpadding()))) &&
            ((this.cellspacing==null && other.getCellspacing()==null) || 
             (this.cellspacing!=null &&
              this.cellspacing.equals(other.getCellspacing()))) &&
            ((this.width==null && other.getWidth()==null) || 
             (this.width!=null &&
              this.width.equals(other.getWidth()))) &&
            ((this.align==null && other.getAlign()==null) || 
             (this.align!=null &&
              this.align.equals(other.getAlign()))) &&
            ((this.activeFlag==null && other.getActiveFlag()==null) || 
             (this.activeFlag!=null &&
              this.activeFlag.equals(other.getActiveFlag()))) &&
            ((this.categoryID==null && other.getCategoryID()==null) || 
             (this.categoryID!=null &&
              this.categoryID.equals(other.getCategoryID()))) &&
            ((this.categoryType==null && other.getCategoryType()==null) || 
             (this.categoryType!=null &&
              this.categoryType.equals(other.getCategoryType()))) &&
            ((this.ownerID==null && other.getOwnerID()==null) || 
             (this.ownerID!=null &&
              this.ownerID.equals(other.getOwnerID()))) &&
            ((this.headerContent==null && other.getHeaderContent()==null) || 
             (this.headerContent!=null &&
              this.headerContent.equals(other.getHeaderContent()))) &&
            ((this.layout==null && other.getLayout()==null) || 
             (this.layout!=null &&
              this.layout.equals(other.getLayout()))) &&
            ((this.templateSubject==null && other.getTemplateSubject()==null) || 
             (this.templateSubject!=null &&
              this.templateSubject.equals(other.getTemplateSubject()))) &&
            ((this.isTemplateSubjectLocked==null && other.getIsTemplateSubjectLocked()==null) || 
             (this.isTemplateSubjectLocked!=null &&
              this.isTemplateSubjectLocked.equals(other.getIsTemplateSubjectLocked()))) &&
            ((this.preHeader==null && other.getPreHeader()==null) || 
             (this.preHeader!=null &&
              this.preHeader.equals(other.getPreHeader())));
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
        if (getTemplateName() != null) {
            _hashCode += getTemplateName().hashCode();
        }
        if (getLayoutHTML() != null) {
            _hashCode += getLayoutHTML().hashCode();
        }
        if (getBackgroundColor() != null) {
            _hashCode += getBackgroundColor().hashCode();
        }
        if (getBorderColor() != null) {
            _hashCode += getBorderColor().hashCode();
        }
        if (getBorderWidth() != null) {
            _hashCode += getBorderWidth().hashCode();
        }
        if (getCellpadding() != null) {
            _hashCode += getCellpadding().hashCode();
        }
        if (getCellspacing() != null) {
            _hashCode += getCellspacing().hashCode();
        }
        if (getWidth() != null) {
            _hashCode += getWidth().hashCode();
        }
        if (getAlign() != null) {
            _hashCode += getAlign().hashCode();
        }
        if (getActiveFlag() != null) {
            _hashCode += getActiveFlag().hashCode();
        }
        if (getCategoryID() != null) {
            _hashCode += getCategoryID().hashCode();
        }
        if (getCategoryType() != null) {
            _hashCode += getCategoryType().hashCode();
        }
        if (getOwnerID() != null) {
            _hashCode += getOwnerID().hashCode();
        }
        if (getHeaderContent() != null) {
            _hashCode += getHeaderContent().hashCode();
        }
        if (getLayout() != null) {
            _hashCode += getLayout().hashCode();
        }
        if (getTemplateSubject() != null) {
            _hashCode += getTemplateSubject().hashCode();
        }
        if (getIsTemplateSubjectLocked() != null) {
            _hashCode += getIsTemplateSubjectLocked().hashCode();
        }
        if (getPreHeader() != null) {
            _hashCode += getPreHeader().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Template.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Template"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("templateName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TemplateName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("layoutHTML");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "LayoutHTML"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("backgroundColor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "BackgroundColor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("borderColor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "BorderColor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("borderWidth");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "BorderWidth"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cellpadding");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Cellpadding"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cellspacing");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Cellspacing"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("width");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Width"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("align");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Align"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activeFlag");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ActiveFlag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
        elemField.setFieldName("categoryType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "CategoryType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ownerID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "OwnerID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("headerContent");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "HeaderContent"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ContentArea"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("layout");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Layout"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Layout"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("templateSubject");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TemplateSubject"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isTemplateSubjectLocked");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsTemplateSubjectLocked"));
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
