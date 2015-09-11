/**
 * BusinessRule.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class BusinessRule  extends com.exacttarget.wsdl.partnerAPI.APIObject  implements java.io.Serializable {
    private int memberBusinessRuleID;

    private int businessRuleID;

    private int data;

    private java.lang.String quality;

    private java.lang.String name;

    private java.lang.String type;

    private java.lang.String description;

    private java.lang.Boolean isViewable;

    private java.lang.Boolean isInheritedFromParent;

    private java.lang.String displayName;

    private java.lang.String productCode;

    public BusinessRule() {
    }

    public BusinessRule(
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
           int memberBusinessRuleID,
           int businessRuleID,
           int data,
           java.lang.String quality,
           java.lang.String name,
           java.lang.String type,
           java.lang.String description,
           java.lang.Boolean isViewable,
           java.lang.Boolean isInheritedFromParent,
           java.lang.String displayName,
           java.lang.String productCode) {
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
        this.memberBusinessRuleID = memberBusinessRuleID;
        this.businessRuleID = businessRuleID;
        this.data = data;
        this.quality = quality;
        this.name = name;
        this.type = type;
        this.description = description;
        this.isViewable = isViewable;
        this.isInheritedFromParent = isInheritedFromParent;
        this.displayName = displayName;
        this.productCode = productCode;
    }


    /**
     * Gets the memberBusinessRuleID value for this BusinessRule.
     * 
     * @return memberBusinessRuleID
     */
    public int getMemberBusinessRuleID() {
        return memberBusinessRuleID;
    }


    /**
     * Sets the memberBusinessRuleID value for this BusinessRule.
     * 
     * @param memberBusinessRuleID
     */
    public void setMemberBusinessRuleID(int memberBusinessRuleID) {
        this.memberBusinessRuleID = memberBusinessRuleID;
    }


    /**
     * Gets the businessRuleID value for this BusinessRule.
     * 
     * @return businessRuleID
     */
    public int getBusinessRuleID() {
        return businessRuleID;
    }


    /**
     * Sets the businessRuleID value for this BusinessRule.
     * 
     * @param businessRuleID
     */
    public void setBusinessRuleID(int businessRuleID) {
        this.businessRuleID = businessRuleID;
    }


    /**
     * Gets the data value for this BusinessRule.
     * 
     * @return data
     */
    public int getData() {
        return data;
    }


    /**
     * Sets the data value for this BusinessRule.
     * 
     * @param data
     */
    public void setData(int data) {
        this.data = data;
    }


    /**
     * Gets the quality value for this BusinessRule.
     * 
     * @return quality
     */
    public java.lang.String getQuality() {
        return quality;
    }


    /**
     * Sets the quality value for this BusinessRule.
     * 
     * @param quality
     */
    public void setQuality(java.lang.String quality) {
        this.quality = quality;
    }


    /**
     * Gets the name value for this BusinessRule.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this BusinessRule.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the type value for this BusinessRule.
     * 
     * @return type
     */
    public java.lang.String getType() {
        return type;
    }


    /**
     * Sets the type value for this BusinessRule.
     * 
     * @param type
     */
    public void setType(java.lang.String type) {
        this.type = type;
    }


    /**
     * Gets the description value for this BusinessRule.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this BusinessRule.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the isViewable value for this BusinessRule.
     * 
     * @return isViewable
     */
    public java.lang.Boolean getIsViewable() {
        return isViewable;
    }


    /**
     * Sets the isViewable value for this BusinessRule.
     * 
     * @param isViewable
     */
    public void setIsViewable(java.lang.Boolean isViewable) {
        this.isViewable = isViewable;
    }


    /**
     * Gets the isInheritedFromParent value for this BusinessRule.
     * 
     * @return isInheritedFromParent
     */
    public java.lang.Boolean getIsInheritedFromParent() {
        return isInheritedFromParent;
    }


    /**
     * Sets the isInheritedFromParent value for this BusinessRule.
     * 
     * @param isInheritedFromParent
     */
    public void setIsInheritedFromParent(java.lang.Boolean isInheritedFromParent) {
        this.isInheritedFromParent = isInheritedFromParent;
    }


    /**
     * Gets the displayName value for this BusinessRule.
     * 
     * @return displayName
     */
    public java.lang.String getDisplayName() {
        return displayName;
    }


    /**
     * Sets the displayName value for this BusinessRule.
     * 
     * @param displayName
     */
    public void setDisplayName(java.lang.String displayName) {
        this.displayName = displayName;
    }


    /**
     * Gets the productCode value for this BusinessRule.
     * 
     * @return productCode
     */
    public java.lang.String getProductCode() {
        return productCode;
    }


    /**
     * Sets the productCode value for this BusinessRule.
     * 
     * @param productCode
     */
    public void setProductCode(java.lang.String productCode) {
        this.productCode = productCode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BusinessRule)) return false;
        BusinessRule other = (BusinessRule) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            this.memberBusinessRuleID == other.getMemberBusinessRuleID() &&
            this.businessRuleID == other.getBusinessRuleID() &&
            this.data == other.getData() &&
            ((this.quality==null && other.getQuality()==null) || 
             (this.quality!=null &&
              this.quality.equals(other.getQuality()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.type==null && other.getType()==null) || 
             (this.type!=null &&
              this.type.equals(other.getType()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.isViewable==null && other.getIsViewable()==null) || 
             (this.isViewable!=null &&
              this.isViewable.equals(other.getIsViewable()))) &&
            ((this.isInheritedFromParent==null && other.getIsInheritedFromParent()==null) || 
             (this.isInheritedFromParent!=null &&
              this.isInheritedFromParent.equals(other.getIsInheritedFromParent()))) &&
            ((this.displayName==null && other.getDisplayName()==null) || 
             (this.displayName!=null &&
              this.displayName.equals(other.getDisplayName()))) &&
            ((this.productCode==null && other.getProductCode()==null) || 
             (this.productCode!=null &&
              this.productCode.equals(other.getProductCode())));
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
        _hashCode += getMemberBusinessRuleID();
        _hashCode += getBusinessRuleID();
        _hashCode += getData();
        if (getQuality() != null) {
            _hashCode += getQuality().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getType() != null) {
            _hashCode += getType().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getIsViewable() != null) {
            _hashCode += getIsViewable().hashCode();
        }
        if (getIsInheritedFromParent() != null) {
            _hashCode += getIsInheritedFromParent().hashCode();
        }
        if (getDisplayName() != null) {
            _hashCode += getDisplayName().hashCode();
        }
        if (getProductCode() != null) {
            _hashCode += getProductCode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BusinessRule.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "BusinessRule"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("memberBusinessRuleID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MemberBusinessRuleID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("businessRuleID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "BusinessRuleID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("data");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Data"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quality");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Quality"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("type");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Type"));
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
        elemField.setFieldName("isViewable");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsViewable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isInheritedFromParent");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsInheritedFromParent"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("displayName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DisplayName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("productCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ProductCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
