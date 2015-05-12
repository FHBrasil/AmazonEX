/**
 * ListAttribute.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class ListAttribute  extends com.exacttarget.wsdl.partnerAPI.APIObject  implements java.io.Serializable {
    private com.exacttarget.wsdl.partnerAPI.List list;

    private java.lang.String name;

    private java.lang.String description;

    private com.exacttarget.wsdl.partnerAPI.ListAttributeFieldType fieldType;

    private java.lang.Integer fieldLength;

    private java.lang.Integer scale;

    private java.lang.String minValue;

    private java.lang.String maxValue;

    private java.lang.String defaultValue;

    private java.lang.Boolean isNullable;

    private java.lang.Boolean isHidden;

    private java.lang.Boolean isReadOnly;

    private java.lang.Boolean inheritable;

    private java.lang.Boolean overridable;

    private java.lang.Boolean mustOverride;

    private com.exacttarget.wsdl.partnerAPI.OverrideType overrideType;

    private java.lang.Integer ordinal;

    private com.exacttarget.wsdl.partnerAPI.ListAttributeRestrictedValue[] restrictedValues;

    private com.exacttarget.wsdl.partnerAPI.ListAttribute baseAttribute;

    public ListAttribute() {
    }

    public ListAttribute(
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
           com.exacttarget.wsdl.partnerAPI.List list,
           java.lang.String name,
           java.lang.String description,
           com.exacttarget.wsdl.partnerAPI.ListAttributeFieldType fieldType,
           java.lang.Integer fieldLength,
           java.lang.Integer scale,
           java.lang.String minValue,
           java.lang.String maxValue,
           java.lang.String defaultValue,
           java.lang.Boolean isNullable,
           java.lang.Boolean isHidden,
           java.lang.Boolean isReadOnly,
           java.lang.Boolean inheritable,
           java.lang.Boolean overridable,
           java.lang.Boolean mustOverride,
           com.exacttarget.wsdl.partnerAPI.OverrideType overrideType,
           java.lang.Integer ordinal,
           com.exacttarget.wsdl.partnerAPI.ListAttributeRestrictedValue[] restrictedValues,
           com.exacttarget.wsdl.partnerAPI.ListAttribute baseAttribute) {
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
        this.list = list;
        this.name = name;
        this.description = description;
        this.fieldType = fieldType;
        this.fieldLength = fieldLength;
        this.scale = scale;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.defaultValue = defaultValue;
        this.isNullable = isNullable;
        this.isHidden = isHidden;
        this.isReadOnly = isReadOnly;
        this.inheritable = inheritable;
        this.overridable = overridable;
        this.mustOverride = mustOverride;
        this.overrideType = overrideType;
        this.ordinal = ordinal;
        this.restrictedValues = restrictedValues;
        this.baseAttribute = baseAttribute;
    }


    /**
     * Gets the list value for this ListAttribute.
     * 
     * @return list
     */
    public com.exacttarget.wsdl.partnerAPI.List getList() {
        return list;
    }


    /**
     * Sets the list value for this ListAttribute.
     * 
     * @param list
     */
    public void setList(com.exacttarget.wsdl.partnerAPI.List list) {
        this.list = list;
    }


    /**
     * Gets the name value for this ListAttribute.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this ListAttribute.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the description value for this ListAttribute.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this ListAttribute.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the fieldType value for this ListAttribute.
     * 
     * @return fieldType
     */
    public com.exacttarget.wsdl.partnerAPI.ListAttributeFieldType getFieldType() {
        return fieldType;
    }


    /**
     * Sets the fieldType value for this ListAttribute.
     * 
     * @param fieldType
     */
    public void setFieldType(com.exacttarget.wsdl.partnerAPI.ListAttributeFieldType fieldType) {
        this.fieldType = fieldType;
    }


    /**
     * Gets the fieldLength value for this ListAttribute.
     * 
     * @return fieldLength
     */
    public java.lang.Integer getFieldLength() {
        return fieldLength;
    }


    /**
     * Sets the fieldLength value for this ListAttribute.
     * 
     * @param fieldLength
     */
    public void setFieldLength(java.lang.Integer fieldLength) {
        this.fieldLength = fieldLength;
    }


    /**
     * Gets the scale value for this ListAttribute.
     * 
     * @return scale
     */
    public java.lang.Integer getScale() {
        return scale;
    }


    /**
     * Sets the scale value for this ListAttribute.
     * 
     * @param scale
     */
    public void setScale(java.lang.Integer scale) {
        this.scale = scale;
    }


    /**
     * Gets the minValue value for this ListAttribute.
     * 
     * @return minValue
     */
    public java.lang.String getMinValue() {
        return minValue;
    }


    /**
     * Sets the minValue value for this ListAttribute.
     * 
     * @param minValue
     */
    public void setMinValue(java.lang.String minValue) {
        this.minValue = minValue;
    }


    /**
     * Gets the maxValue value for this ListAttribute.
     * 
     * @return maxValue
     */
    public java.lang.String getMaxValue() {
        return maxValue;
    }


    /**
     * Sets the maxValue value for this ListAttribute.
     * 
     * @param maxValue
     */
    public void setMaxValue(java.lang.String maxValue) {
        this.maxValue = maxValue;
    }


    /**
     * Gets the defaultValue value for this ListAttribute.
     * 
     * @return defaultValue
     */
    public java.lang.String getDefaultValue() {
        return defaultValue;
    }


    /**
     * Sets the defaultValue value for this ListAttribute.
     * 
     * @param defaultValue
     */
    public void setDefaultValue(java.lang.String defaultValue) {
        this.defaultValue = defaultValue;
    }


    /**
     * Gets the isNullable value for this ListAttribute.
     * 
     * @return isNullable
     */
    public java.lang.Boolean getIsNullable() {
        return isNullable;
    }


    /**
     * Sets the isNullable value for this ListAttribute.
     * 
     * @param isNullable
     */
    public void setIsNullable(java.lang.Boolean isNullable) {
        this.isNullable = isNullable;
    }


    /**
     * Gets the isHidden value for this ListAttribute.
     * 
     * @return isHidden
     */
    public java.lang.Boolean getIsHidden() {
        return isHidden;
    }


    /**
     * Sets the isHidden value for this ListAttribute.
     * 
     * @param isHidden
     */
    public void setIsHidden(java.lang.Boolean isHidden) {
        this.isHidden = isHidden;
    }


    /**
     * Gets the isReadOnly value for this ListAttribute.
     * 
     * @return isReadOnly
     */
    public java.lang.Boolean getIsReadOnly() {
        return isReadOnly;
    }


    /**
     * Sets the isReadOnly value for this ListAttribute.
     * 
     * @param isReadOnly
     */
    public void setIsReadOnly(java.lang.Boolean isReadOnly) {
        this.isReadOnly = isReadOnly;
    }


    /**
     * Gets the inheritable value for this ListAttribute.
     * 
     * @return inheritable
     */
    public java.lang.Boolean getInheritable() {
        return inheritable;
    }


    /**
     * Sets the inheritable value for this ListAttribute.
     * 
     * @param inheritable
     */
    public void setInheritable(java.lang.Boolean inheritable) {
        this.inheritable = inheritable;
    }


    /**
     * Gets the overridable value for this ListAttribute.
     * 
     * @return overridable
     */
    public java.lang.Boolean getOverridable() {
        return overridable;
    }


    /**
     * Sets the overridable value for this ListAttribute.
     * 
     * @param overridable
     */
    public void setOverridable(java.lang.Boolean overridable) {
        this.overridable = overridable;
    }


    /**
     * Gets the mustOverride value for this ListAttribute.
     * 
     * @return mustOverride
     */
    public java.lang.Boolean getMustOverride() {
        return mustOverride;
    }


    /**
     * Sets the mustOverride value for this ListAttribute.
     * 
     * @param mustOverride
     */
    public void setMustOverride(java.lang.Boolean mustOverride) {
        this.mustOverride = mustOverride;
    }


    /**
     * Gets the overrideType value for this ListAttribute.
     * 
     * @return overrideType
     */
    public com.exacttarget.wsdl.partnerAPI.OverrideType getOverrideType() {
        return overrideType;
    }


    /**
     * Sets the overrideType value for this ListAttribute.
     * 
     * @param overrideType
     */
    public void setOverrideType(com.exacttarget.wsdl.partnerAPI.OverrideType overrideType) {
        this.overrideType = overrideType;
    }


    /**
     * Gets the ordinal value for this ListAttribute.
     * 
     * @return ordinal
     */
    public java.lang.Integer getOrdinal() {
        return ordinal;
    }


    /**
     * Sets the ordinal value for this ListAttribute.
     * 
     * @param ordinal
     */
    public void setOrdinal(java.lang.Integer ordinal) {
        this.ordinal = ordinal;
    }


    /**
     * Gets the restrictedValues value for this ListAttribute.
     * 
     * @return restrictedValues
     */
    public com.exacttarget.wsdl.partnerAPI.ListAttributeRestrictedValue[] getRestrictedValues() {
        return restrictedValues;
    }


    /**
     * Sets the restrictedValues value for this ListAttribute.
     * 
     * @param restrictedValues
     */
    public void setRestrictedValues(com.exacttarget.wsdl.partnerAPI.ListAttributeRestrictedValue[] restrictedValues) {
        this.restrictedValues = restrictedValues;
    }

    public com.exacttarget.wsdl.partnerAPI.ListAttributeRestrictedValue getRestrictedValues(int i) {
        return this.restrictedValues[i];
    }

    public void setRestrictedValues(int i, com.exacttarget.wsdl.partnerAPI.ListAttributeRestrictedValue _value) {
        this.restrictedValues[i] = _value;
    }


    /**
     * Gets the baseAttribute value for this ListAttribute.
     * 
     * @return baseAttribute
     */
    public com.exacttarget.wsdl.partnerAPI.ListAttribute getBaseAttribute() {
        return baseAttribute;
    }


    /**
     * Sets the baseAttribute value for this ListAttribute.
     * 
     * @param baseAttribute
     */
    public void setBaseAttribute(com.exacttarget.wsdl.partnerAPI.ListAttribute baseAttribute) {
        this.baseAttribute = baseAttribute;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListAttribute)) return false;
        ListAttribute other = (ListAttribute) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.list==null && other.getList()==null) || 
             (this.list!=null &&
              this.list.equals(other.getList()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.fieldType==null && other.getFieldType()==null) || 
             (this.fieldType!=null &&
              this.fieldType.equals(other.getFieldType()))) &&
            ((this.fieldLength==null && other.getFieldLength()==null) || 
             (this.fieldLength!=null &&
              this.fieldLength.equals(other.getFieldLength()))) &&
            ((this.scale==null && other.getScale()==null) || 
             (this.scale!=null &&
              this.scale.equals(other.getScale()))) &&
            ((this.minValue==null && other.getMinValue()==null) || 
             (this.minValue!=null &&
              this.minValue.equals(other.getMinValue()))) &&
            ((this.maxValue==null && other.getMaxValue()==null) || 
             (this.maxValue!=null &&
              this.maxValue.equals(other.getMaxValue()))) &&
            ((this.defaultValue==null && other.getDefaultValue()==null) || 
             (this.defaultValue!=null &&
              this.defaultValue.equals(other.getDefaultValue()))) &&
            ((this.isNullable==null && other.getIsNullable()==null) || 
             (this.isNullable!=null &&
              this.isNullable.equals(other.getIsNullable()))) &&
            ((this.isHidden==null && other.getIsHidden()==null) || 
             (this.isHidden!=null &&
              this.isHidden.equals(other.getIsHidden()))) &&
            ((this.isReadOnly==null && other.getIsReadOnly()==null) || 
             (this.isReadOnly!=null &&
              this.isReadOnly.equals(other.getIsReadOnly()))) &&
            ((this.inheritable==null && other.getInheritable()==null) || 
             (this.inheritable!=null &&
              this.inheritable.equals(other.getInheritable()))) &&
            ((this.overridable==null && other.getOverridable()==null) || 
             (this.overridable!=null &&
              this.overridable.equals(other.getOverridable()))) &&
            ((this.mustOverride==null && other.getMustOverride()==null) || 
             (this.mustOverride!=null &&
              this.mustOverride.equals(other.getMustOverride()))) &&
            ((this.overrideType==null && other.getOverrideType()==null) || 
             (this.overrideType!=null &&
              this.overrideType.equals(other.getOverrideType()))) &&
            ((this.ordinal==null && other.getOrdinal()==null) || 
             (this.ordinal!=null &&
              this.ordinal.equals(other.getOrdinal()))) &&
            ((this.restrictedValues==null && other.getRestrictedValues()==null) || 
             (this.restrictedValues!=null &&
              java.util.Arrays.equals(this.restrictedValues, other.getRestrictedValues()))) &&
            ((this.baseAttribute==null && other.getBaseAttribute()==null) || 
             (this.baseAttribute!=null &&
              this.baseAttribute.equals(other.getBaseAttribute())));
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
        if (getList() != null) {
            _hashCode += getList().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getFieldType() != null) {
            _hashCode += getFieldType().hashCode();
        }
        if (getFieldLength() != null) {
            _hashCode += getFieldLength().hashCode();
        }
        if (getScale() != null) {
            _hashCode += getScale().hashCode();
        }
        if (getMinValue() != null) {
            _hashCode += getMinValue().hashCode();
        }
        if (getMaxValue() != null) {
            _hashCode += getMaxValue().hashCode();
        }
        if (getDefaultValue() != null) {
            _hashCode += getDefaultValue().hashCode();
        }
        if (getIsNullable() != null) {
            _hashCode += getIsNullable().hashCode();
        }
        if (getIsHidden() != null) {
            _hashCode += getIsHidden().hashCode();
        }
        if (getIsReadOnly() != null) {
            _hashCode += getIsReadOnly().hashCode();
        }
        if (getInheritable() != null) {
            _hashCode += getInheritable().hashCode();
        }
        if (getOverridable() != null) {
            _hashCode += getOverridable().hashCode();
        }
        if (getMustOverride() != null) {
            _hashCode += getMustOverride().hashCode();
        }
        if (getOverrideType() != null) {
            _hashCode += getOverrideType().hashCode();
        }
        if (getOrdinal() != null) {
            _hashCode += getOrdinal().hashCode();
        }
        if (getRestrictedValues() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRestrictedValues());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRestrictedValues(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getBaseAttribute() != null) {
            _hashCode += getBaseAttribute().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ListAttribute.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ListAttribute"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("list");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "List"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "List"));
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
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fieldType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FieldType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ListAttributeFieldType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fieldLength");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FieldLength"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("scale");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Scale"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("minValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MinValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maxValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MaxValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("defaultValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DefaultValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isNullable");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsNullable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isHidden");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsHidden"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isReadOnly");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsReadOnly"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inheritable");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Inheritable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("overridable");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Overridable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mustOverride");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MustOverride"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("overrideType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "OverrideType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "OverrideType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ordinal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Ordinal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("restrictedValues");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RestrictedValues"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ListAttributeRestrictedValue"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("baseAttribute");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "BaseAttribute"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ListAttribute"));
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
