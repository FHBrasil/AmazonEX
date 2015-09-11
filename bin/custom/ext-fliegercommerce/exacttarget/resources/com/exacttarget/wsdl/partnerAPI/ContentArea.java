/**
 * ContentArea.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class ContentArea  extends com.exacttarget.wsdl.partnerAPI.APIObject  implements java.io.Serializable {
    private java.lang.String key;

    private java.lang.String content;

    private java.lang.Boolean isBlank;

    private java.lang.Integer categoryID;

    private java.lang.String name;

    private com.exacttarget.wsdl.partnerAPI.LayoutType layout;

    private java.lang.Boolean isDynamicContent;

    private java.lang.Boolean isSurvey;

    private java.lang.String backgroundColor;

    private java.lang.String borderColor;

    private java.lang.Integer borderWidth;

    private java.lang.Integer cellpadding;

    private java.lang.Integer cellspacing;

    private java.lang.String width;

    private java.lang.String fontFamily;

    private java.lang.Boolean hasFontSize;

    private java.lang.Boolean isLocked;

    public ContentArea() {
    }

    public ContentArea(
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
           java.lang.String key,
           java.lang.String content,
           java.lang.Boolean isBlank,
           java.lang.Integer categoryID,
           java.lang.String name,
           com.exacttarget.wsdl.partnerAPI.LayoutType layout,
           java.lang.Boolean isDynamicContent,
           java.lang.Boolean isSurvey,
           java.lang.String backgroundColor,
           java.lang.String borderColor,
           java.lang.Integer borderWidth,
           java.lang.Integer cellpadding,
           java.lang.Integer cellspacing,
           java.lang.String width,
           java.lang.String fontFamily,
           java.lang.Boolean hasFontSize,
           java.lang.Boolean isLocked) {
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
        this.key = key;
        this.content = content;
        this.isBlank = isBlank;
        this.categoryID = categoryID;
        this.name = name;
        this.layout = layout;
        this.isDynamicContent = isDynamicContent;
        this.isSurvey = isSurvey;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
        this.borderWidth = borderWidth;
        this.cellpadding = cellpadding;
        this.cellspacing = cellspacing;
        this.width = width;
        this.fontFamily = fontFamily;
        this.hasFontSize = hasFontSize;
        this.isLocked = isLocked;
    }


    /**
     * Gets the key value for this ContentArea.
     * 
     * @return key
     */
    public java.lang.String getKey() {
        return key;
    }


    /**
     * Sets the key value for this ContentArea.
     * 
     * @param key
     */
    public void setKey(java.lang.String key) {
        this.key = key;
    }


    /**
     * Gets the content value for this ContentArea.
     * 
     * @return content
     */
    public java.lang.String getContent() {
        return content;
    }


    /**
     * Sets the content value for this ContentArea.
     * 
     * @param content
     */
    public void setContent(java.lang.String content) {
        this.content = content;
    }


    /**
     * Gets the isBlank value for this ContentArea.
     * 
     * @return isBlank
     */
    public java.lang.Boolean getIsBlank() {
        return isBlank;
    }


    /**
     * Sets the isBlank value for this ContentArea.
     * 
     * @param isBlank
     */
    public void setIsBlank(java.lang.Boolean isBlank) {
        this.isBlank = isBlank;
    }


    /**
     * Gets the categoryID value for this ContentArea.
     * 
     * @return categoryID
     */
    public java.lang.Integer getCategoryID() {
        return categoryID;
    }


    /**
     * Sets the categoryID value for this ContentArea.
     * 
     * @param categoryID
     */
    public void setCategoryID(java.lang.Integer categoryID) {
        this.categoryID = categoryID;
    }


    /**
     * Gets the name value for this ContentArea.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this ContentArea.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the layout value for this ContentArea.
     * 
     * @return layout
     */
    public com.exacttarget.wsdl.partnerAPI.LayoutType getLayout() {
        return layout;
    }


    /**
     * Sets the layout value for this ContentArea.
     * 
     * @param layout
     */
    public void setLayout(com.exacttarget.wsdl.partnerAPI.LayoutType layout) {
        this.layout = layout;
    }


    /**
     * Gets the isDynamicContent value for this ContentArea.
     * 
     * @return isDynamicContent
     */
    public java.lang.Boolean getIsDynamicContent() {
        return isDynamicContent;
    }


    /**
     * Sets the isDynamicContent value for this ContentArea.
     * 
     * @param isDynamicContent
     */
    public void setIsDynamicContent(java.lang.Boolean isDynamicContent) {
        this.isDynamicContent = isDynamicContent;
    }


    /**
     * Gets the isSurvey value for this ContentArea.
     * 
     * @return isSurvey
     */
    public java.lang.Boolean getIsSurvey() {
        return isSurvey;
    }


    /**
     * Sets the isSurvey value for this ContentArea.
     * 
     * @param isSurvey
     */
    public void setIsSurvey(java.lang.Boolean isSurvey) {
        this.isSurvey = isSurvey;
    }


    /**
     * Gets the backgroundColor value for this ContentArea.
     * 
     * @return backgroundColor
     */
    public java.lang.String getBackgroundColor() {
        return backgroundColor;
    }


    /**
     * Sets the backgroundColor value for this ContentArea.
     * 
     * @param backgroundColor
     */
    public void setBackgroundColor(java.lang.String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }


    /**
     * Gets the borderColor value for this ContentArea.
     * 
     * @return borderColor
     */
    public java.lang.String getBorderColor() {
        return borderColor;
    }


    /**
     * Sets the borderColor value for this ContentArea.
     * 
     * @param borderColor
     */
    public void setBorderColor(java.lang.String borderColor) {
        this.borderColor = borderColor;
    }


    /**
     * Gets the borderWidth value for this ContentArea.
     * 
     * @return borderWidth
     */
    public java.lang.Integer getBorderWidth() {
        return borderWidth;
    }


    /**
     * Sets the borderWidth value for this ContentArea.
     * 
     * @param borderWidth
     */
    public void setBorderWidth(java.lang.Integer borderWidth) {
        this.borderWidth = borderWidth;
    }


    /**
     * Gets the cellpadding value for this ContentArea.
     * 
     * @return cellpadding
     */
    public java.lang.Integer getCellpadding() {
        return cellpadding;
    }


    /**
     * Sets the cellpadding value for this ContentArea.
     * 
     * @param cellpadding
     */
    public void setCellpadding(java.lang.Integer cellpadding) {
        this.cellpadding = cellpadding;
    }


    /**
     * Gets the cellspacing value for this ContentArea.
     * 
     * @return cellspacing
     */
    public java.lang.Integer getCellspacing() {
        return cellspacing;
    }


    /**
     * Sets the cellspacing value for this ContentArea.
     * 
     * @param cellspacing
     */
    public void setCellspacing(java.lang.Integer cellspacing) {
        this.cellspacing = cellspacing;
    }


    /**
     * Gets the width value for this ContentArea.
     * 
     * @return width
     */
    public java.lang.String getWidth() {
        return width;
    }


    /**
     * Sets the width value for this ContentArea.
     * 
     * @param width
     */
    public void setWidth(java.lang.String width) {
        this.width = width;
    }


    /**
     * Gets the fontFamily value for this ContentArea.
     * 
     * @return fontFamily
     */
    public java.lang.String getFontFamily() {
        return fontFamily;
    }


    /**
     * Sets the fontFamily value for this ContentArea.
     * 
     * @param fontFamily
     */
    public void setFontFamily(java.lang.String fontFamily) {
        this.fontFamily = fontFamily;
    }


    /**
     * Gets the hasFontSize value for this ContentArea.
     * 
     * @return hasFontSize
     */
    public java.lang.Boolean getHasFontSize() {
        return hasFontSize;
    }


    /**
     * Sets the hasFontSize value for this ContentArea.
     * 
     * @param hasFontSize
     */
    public void setHasFontSize(java.lang.Boolean hasFontSize) {
        this.hasFontSize = hasFontSize;
    }


    /**
     * Gets the isLocked value for this ContentArea.
     * 
     * @return isLocked
     */
    public java.lang.Boolean getIsLocked() {
        return isLocked;
    }


    /**
     * Sets the isLocked value for this ContentArea.
     * 
     * @param isLocked
     */
    public void setIsLocked(java.lang.Boolean isLocked) {
        this.isLocked = isLocked;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ContentArea)) return false;
        ContentArea other = (ContentArea) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.key==null && other.getKey()==null) || 
             (this.key!=null &&
              this.key.equals(other.getKey()))) &&
            ((this.content==null && other.getContent()==null) || 
             (this.content!=null &&
              this.content.equals(other.getContent()))) &&
            ((this.isBlank==null && other.getIsBlank()==null) || 
             (this.isBlank!=null &&
              this.isBlank.equals(other.getIsBlank()))) &&
            ((this.categoryID==null && other.getCategoryID()==null) || 
             (this.categoryID!=null &&
              this.categoryID.equals(other.getCategoryID()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.layout==null && other.getLayout()==null) || 
             (this.layout!=null &&
              this.layout.equals(other.getLayout()))) &&
            ((this.isDynamicContent==null && other.getIsDynamicContent()==null) || 
             (this.isDynamicContent!=null &&
              this.isDynamicContent.equals(other.getIsDynamicContent()))) &&
            ((this.isSurvey==null && other.getIsSurvey()==null) || 
             (this.isSurvey!=null &&
              this.isSurvey.equals(other.getIsSurvey()))) &&
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
            ((this.fontFamily==null && other.getFontFamily()==null) || 
             (this.fontFamily!=null &&
              this.fontFamily.equals(other.getFontFamily()))) &&
            ((this.hasFontSize==null && other.getHasFontSize()==null) || 
             (this.hasFontSize!=null &&
              this.hasFontSize.equals(other.getHasFontSize()))) &&
            ((this.isLocked==null && other.getIsLocked()==null) || 
             (this.isLocked!=null &&
              this.isLocked.equals(other.getIsLocked())));
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
        if (getKey() != null) {
            _hashCode += getKey().hashCode();
        }
        if (getContent() != null) {
            _hashCode += getContent().hashCode();
        }
        if (getIsBlank() != null) {
            _hashCode += getIsBlank().hashCode();
        }
        if (getCategoryID() != null) {
            _hashCode += getCategoryID().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getLayout() != null) {
            _hashCode += getLayout().hashCode();
        }
        if (getIsDynamicContent() != null) {
            _hashCode += getIsDynamicContent().hashCode();
        }
        if (getIsSurvey() != null) {
            _hashCode += getIsSurvey().hashCode();
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
        if (getFontFamily() != null) {
            _hashCode += getFontFamily().hashCode();
        }
        if (getHasFontSize() != null) {
            _hashCode += getHasFontSize().hashCode();
        }
        if (getIsLocked() != null) {
            _hashCode += getIsLocked().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ContentArea.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ContentArea"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("key");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Key"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("content");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Content"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isBlank");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsBlank"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("layout");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Layout"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "LayoutType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isDynamicContent");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsDynamicContent"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isSurvey");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsSurvey"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fontFamily");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FontFamily"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hasFontSize");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "HasFontSize"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
