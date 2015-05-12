/**
 * Portfolio.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class Portfolio  extends com.exacttarget.wsdl.partnerAPI.APIObject  implements java.io.Serializable {
    private com.exacttarget.wsdl.partnerAPI.ResourceSpecification source;

    private java.lang.Integer categoryID;

    private java.lang.String fileName;

    private java.lang.String displayName;

    private java.lang.String description;

    private java.lang.String typeDescription;

    private java.lang.Boolean isUploaded;

    private java.lang.Boolean isActive;

    private java.lang.Integer fileSizeKB;

    private java.lang.Integer thumbSizeKB;

    private java.lang.Integer fileWidthPX;

    private java.lang.Integer fileHeightPX;

    private java.lang.String fileURL;

    private java.lang.String thumbURL;

    private java.util.Calendar cacheClearTime;

    private java.lang.String categoryType;

    public Portfolio() {
    }

    public Portfolio(
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
           com.exacttarget.wsdl.partnerAPI.ResourceSpecification source,
           java.lang.Integer categoryID,
           java.lang.String fileName,
           java.lang.String displayName,
           java.lang.String description,
           java.lang.String typeDescription,
           java.lang.Boolean isUploaded,
           java.lang.Boolean isActive,
           java.lang.Integer fileSizeKB,
           java.lang.Integer thumbSizeKB,
           java.lang.Integer fileWidthPX,
           java.lang.Integer fileHeightPX,
           java.lang.String fileURL,
           java.lang.String thumbURL,
           java.util.Calendar cacheClearTime,
           java.lang.String categoryType) {
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
        this.source = source;
        this.categoryID = categoryID;
        this.fileName = fileName;
        this.displayName = displayName;
        this.description = description;
        this.typeDescription = typeDescription;
        this.isUploaded = isUploaded;
        this.isActive = isActive;
        this.fileSizeKB = fileSizeKB;
        this.thumbSizeKB = thumbSizeKB;
        this.fileWidthPX = fileWidthPX;
        this.fileHeightPX = fileHeightPX;
        this.fileURL = fileURL;
        this.thumbURL = thumbURL;
        this.cacheClearTime = cacheClearTime;
        this.categoryType = categoryType;
    }


    /**
     * Gets the source value for this Portfolio.
     * 
     * @return source
     */
    public com.exacttarget.wsdl.partnerAPI.ResourceSpecification getSource() {
        return source;
    }


    /**
     * Sets the source value for this Portfolio.
     * 
     * @param source
     */
    public void setSource(com.exacttarget.wsdl.partnerAPI.ResourceSpecification source) {
        this.source = source;
    }


    /**
     * Gets the categoryID value for this Portfolio.
     * 
     * @return categoryID
     */
    public java.lang.Integer getCategoryID() {
        return categoryID;
    }


    /**
     * Sets the categoryID value for this Portfolio.
     * 
     * @param categoryID
     */
    public void setCategoryID(java.lang.Integer categoryID) {
        this.categoryID = categoryID;
    }


    /**
     * Gets the fileName value for this Portfolio.
     * 
     * @return fileName
     */
    public java.lang.String getFileName() {
        return fileName;
    }


    /**
     * Sets the fileName value for this Portfolio.
     * 
     * @param fileName
     */
    public void setFileName(java.lang.String fileName) {
        this.fileName = fileName;
    }


    /**
     * Gets the displayName value for this Portfolio.
     * 
     * @return displayName
     */
    public java.lang.String getDisplayName() {
        return displayName;
    }


    /**
     * Sets the displayName value for this Portfolio.
     * 
     * @param displayName
     */
    public void setDisplayName(java.lang.String displayName) {
        this.displayName = displayName;
    }


    /**
     * Gets the description value for this Portfolio.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this Portfolio.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the typeDescription value for this Portfolio.
     * 
     * @return typeDescription
     */
    public java.lang.String getTypeDescription() {
        return typeDescription;
    }


    /**
     * Sets the typeDescription value for this Portfolio.
     * 
     * @param typeDescription
     */
    public void setTypeDescription(java.lang.String typeDescription) {
        this.typeDescription = typeDescription;
    }


    /**
     * Gets the isUploaded value for this Portfolio.
     * 
     * @return isUploaded
     */
    public java.lang.Boolean getIsUploaded() {
        return isUploaded;
    }


    /**
     * Sets the isUploaded value for this Portfolio.
     * 
     * @param isUploaded
     */
    public void setIsUploaded(java.lang.Boolean isUploaded) {
        this.isUploaded = isUploaded;
    }


    /**
     * Gets the isActive value for this Portfolio.
     * 
     * @return isActive
     */
    public java.lang.Boolean getIsActive() {
        return isActive;
    }


    /**
     * Sets the isActive value for this Portfolio.
     * 
     * @param isActive
     */
    public void setIsActive(java.lang.Boolean isActive) {
        this.isActive = isActive;
    }


    /**
     * Gets the fileSizeKB value for this Portfolio.
     * 
     * @return fileSizeKB
     */
    public java.lang.Integer getFileSizeKB() {
        return fileSizeKB;
    }


    /**
     * Sets the fileSizeKB value for this Portfolio.
     * 
     * @param fileSizeKB
     */
    public void setFileSizeKB(java.lang.Integer fileSizeKB) {
        this.fileSizeKB = fileSizeKB;
    }


    /**
     * Gets the thumbSizeKB value for this Portfolio.
     * 
     * @return thumbSizeKB
     */
    public java.lang.Integer getThumbSizeKB() {
        return thumbSizeKB;
    }


    /**
     * Sets the thumbSizeKB value for this Portfolio.
     * 
     * @param thumbSizeKB
     */
    public void setThumbSizeKB(java.lang.Integer thumbSizeKB) {
        this.thumbSizeKB = thumbSizeKB;
    }


    /**
     * Gets the fileWidthPX value for this Portfolio.
     * 
     * @return fileWidthPX
     */
    public java.lang.Integer getFileWidthPX() {
        return fileWidthPX;
    }


    /**
     * Sets the fileWidthPX value for this Portfolio.
     * 
     * @param fileWidthPX
     */
    public void setFileWidthPX(java.lang.Integer fileWidthPX) {
        this.fileWidthPX = fileWidthPX;
    }


    /**
     * Gets the fileHeightPX value for this Portfolio.
     * 
     * @return fileHeightPX
     */
    public java.lang.Integer getFileHeightPX() {
        return fileHeightPX;
    }


    /**
     * Sets the fileHeightPX value for this Portfolio.
     * 
     * @param fileHeightPX
     */
    public void setFileHeightPX(java.lang.Integer fileHeightPX) {
        this.fileHeightPX = fileHeightPX;
    }


    /**
     * Gets the fileURL value for this Portfolio.
     * 
     * @return fileURL
     */
    public java.lang.String getFileURL() {
        return fileURL;
    }


    /**
     * Sets the fileURL value for this Portfolio.
     * 
     * @param fileURL
     */
    public void setFileURL(java.lang.String fileURL) {
        this.fileURL = fileURL;
    }


    /**
     * Gets the thumbURL value for this Portfolio.
     * 
     * @return thumbURL
     */
    public java.lang.String getThumbURL() {
        return thumbURL;
    }


    /**
     * Sets the thumbURL value for this Portfolio.
     * 
     * @param thumbURL
     */
    public void setThumbURL(java.lang.String thumbURL) {
        this.thumbURL = thumbURL;
    }


    /**
     * Gets the cacheClearTime value for this Portfolio.
     * 
     * @return cacheClearTime
     */
    public java.util.Calendar getCacheClearTime() {
        return cacheClearTime;
    }


    /**
     * Sets the cacheClearTime value for this Portfolio.
     * 
     * @param cacheClearTime
     */
    public void setCacheClearTime(java.util.Calendar cacheClearTime) {
        this.cacheClearTime = cacheClearTime;
    }


    /**
     * Gets the categoryType value for this Portfolio.
     * 
     * @return categoryType
     */
    public java.lang.String getCategoryType() {
        return categoryType;
    }


    /**
     * Sets the categoryType value for this Portfolio.
     * 
     * @param categoryType
     */
    public void setCategoryType(java.lang.String categoryType) {
        this.categoryType = categoryType;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Portfolio)) return false;
        Portfolio other = (Portfolio) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.source==null && other.getSource()==null) || 
             (this.source!=null &&
              this.source.equals(other.getSource()))) &&
            ((this.categoryID==null && other.getCategoryID()==null) || 
             (this.categoryID!=null &&
              this.categoryID.equals(other.getCategoryID()))) &&
            ((this.fileName==null && other.getFileName()==null) || 
             (this.fileName!=null &&
              this.fileName.equals(other.getFileName()))) &&
            ((this.displayName==null && other.getDisplayName()==null) || 
             (this.displayName!=null &&
              this.displayName.equals(other.getDisplayName()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.typeDescription==null && other.getTypeDescription()==null) || 
             (this.typeDescription!=null &&
              this.typeDescription.equals(other.getTypeDescription()))) &&
            ((this.isUploaded==null && other.getIsUploaded()==null) || 
             (this.isUploaded!=null &&
              this.isUploaded.equals(other.getIsUploaded()))) &&
            ((this.isActive==null && other.getIsActive()==null) || 
             (this.isActive!=null &&
              this.isActive.equals(other.getIsActive()))) &&
            ((this.fileSizeKB==null && other.getFileSizeKB()==null) || 
             (this.fileSizeKB!=null &&
              this.fileSizeKB.equals(other.getFileSizeKB()))) &&
            ((this.thumbSizeKB==null && other.getThumbSizeKB()==null) || 
             (this.thumbSizeKB!=null &&
              this.thumbSizeKB.equals(other.getThumbSizeKB()))) &&
            ((this.fileWidthPX==null && other.getFileWidthPX()==null) || 
             (this.fileWidthPX!=null &&
              this.fileWidthPX.equals(other.getFileWidthPX()))) &&
            ((this.fileHeightPX==null && other.getFileHeightPX()==null) || 
             (this.fileHeightPX!=null &&
              this.fileHeightPX.equals(other.getFileHeightPX()))) &&
            ((this.fileURL==null && other.getFileURL()==null) || 
             (this.fileURL!=null &&
              this.fileURL.equals(other.getFileURL()))) &&
            ((this.thumbURL==null && other.getThumbURL()==null) || 
             (this.thumbURL!=null &&
              this.thumbURL.equals(other.getThumbURL()))) &&
            ((this.cacheClearTime==null && other.getCacheClearTime()==null) || 
             (this.cacheClearTime!=null &&
              this.cacheClearTime.equals(other.getCacheClearTime()))) &&
            ((this.categoryType==null && other.getCategoryType()==null) || 
             (this.categoryType!=null &&
              this.categoryType.equals(other.getCategoryType())));
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
        if (getSource() != null) {
            _hashCode += getSource().hashCode();
        }
        if (getCategoryID() != null) {
            _hashCode += getCategoryID().hashCode();
        }
        if (getFileName() != null) {
            _hashCode += getFileName().hashCode();
        }
        if (getDisplayName() != null) {
            _hashCode += getDisplayName().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getTypeDescription() != null) {
            _hashCode += getTypeDescription().hashCode();
        }
        if (getIsUploaded() != null) {
            _hashCode += getIsUploaded().hashCode();
        }
        if (getIsActive() != null) {
            _hashCode += getIsActive().hashCode();
        }
        if (getFileSizeKB() != null) {
            _hashCode += getFileSizeKB().hashCode();
        }
        if (getThumbSizeKB() != null) {
            _hashCode += getThumbSizeKB().hashCode();
        }
        if (getFileWidthPX() != null) {
            _hashCode += getFileWidthPX().hashCode();
        }
        if (getFileHeightPX() != null) {
            _hashCode += getFileHeightPX().hashCode();
        }
        if (getFileURL() != null) {
            _hashCode += getFileURL().hashCode();
        }
        if (getThumbURL() != null) {
            _hashCode += getThumbURL().hashCode();
        }
        if (getCacheClearTime() != null) {
            _hashCode += getCacheClearTime().hashCode();
        }
        if (getCategoryType() != null) {
            _hashCode += getCategoryType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Portfolio.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Portfolio"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("source");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Source"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ResourceSpecification"));
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
        elemField.setFieldName("fileName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FileName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("displayName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DisplayName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("typeDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TypeDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isUploaded");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsUploaded"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
        elemField.setFieldName("fileSizeKB");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FileSizeKB"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("thumbSizeKB");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ThumbSizeKB"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fileWidthPX");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FileWidthPX"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fileHeightPX");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FileHeightPX"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fileURL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FileURL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("thumbURL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ThumbURL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cacheClearTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "CacheClearTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
