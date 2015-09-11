/**
 * PropertyDefinition.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class PropertyDefinition  extends com.exacttarget.wsdl.partnerAPI.APIObject  implements java.io.Serializable {
    private java.lang.String name;

    /* Deprecated.  Please use ValueType. */
    private java.lang.String dataType;

    /* Actual data type of the value that will be stored in this property */
    private com.exacttarget.wsdl.partnerAPI.SoapType valueType;

    /* ExactTarget data type of the property */
    private com.exacttarget.wsdl.partnerAPI.PropertyType propertyType;

    /* Indicates whether the property can be created.  If true, then
     * this property value can be set in a create call. */
    private java.lang.Boolean isCreatable;

    /* Indicates whether the property can be updated.  If true, then
     * this property value can be set in an update call. */
    private java.lang.Boolean isUpdatable;

    /* Indicates whether the object can be retrieved via the retrieve
     * call. */
    private java.lang.Boolean isRetrievable;

    /* Indicates whether the object can be queried via the query call. */
    private java.lang.Boolean isQueryable;

    /* Indicates whether the property is filterable.  If true, then
     * this property can be specified in a SimpleFilterPart in a retrieve
     * call. */
    private java.lang.Boolean isFilterable;

    /* Indicates if the property is specific to a partner. */
    private java.lang.Boolean isPartnerProperty;

    /* Indicates if the property is specific to the Account. */
    private java.lang.Boolean isAccountProperty;

    /* Deprecated. */
    private java.lang.String partnerMap;

    private com.exacttarget.wsdl.partnerAPI.AttributeMap[] attributeMaps;

    /* Deprecated. */
    private com.exacttarget.wsdl.partnerAPI.APIProperty[] markups;

    /* Reserved for future use. */
    private java.lang.Integer precision;

    /* Reserved for future use. */
    private java.lang.Integer scale;

    /* Text label that is displayed next to the field in the user
     * interface. */
    private java.lang.String label;

    private java.lang.String description;

    private java.lang.String defaultValue;

    /* Minimum length of the data. */
    private java.lang.Integer minLength;

    /* Maximum length of the data. */
    private java.lang.Integer maxLength;

    /* Minimum value that this property can be set to. */
    private java.lang.String minValue;

    /* Maximum value that this property can be set to. */
    private java.lang.String maxValue;

    /* Indicates whether the property must have a value specified. */
    private java.lang.Boolean isRequired;

    /* Indicates whether the property is viewable to the end-user
     * in the profile center. */
    private java.lang.Boolean isViewable;

    /* Indicates whether the property is editable by the end-user
     * in the profile center. */
    private java.lang.Boolean isEditable;

    /* Indicates whether the property can contain a null value. */
    private java.lang.Boolean isNillable;

    /* Indicates if the property has a restricted list of valid values. */
    private java.lang.Boolean isRestrictedPicklist;

    /* List of valid values. */
    private com.exacttarget.wsdl.partnerAPI.PicklistItem[] picklistItems;

    /* Indicates whether the property is a send time attribute. */
    private java.lang.Boolean isSendTime;

    /* Indicates the placement of this property within the list of
     * properties. */
    private java.lang.Integer displayOrder;

    /* Indicates the object types of the referenced objects. */
    private com.exacttarget.wsdl.partnerAPI.APIObject[] references;

    /* The name of the relationship (e.g. One-to-One, One-to-Many). */
    private java.lang.String relationshipName;

    /* Reserved for future use. */
    private java.lang.String status;

    /* Reserved for future use. */
    private java.lang.Boolean isContextSpecific;

    public PropertyDefinition() {
    }

    public PropertyDefinition(
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
           java.lang.String dataType,
           com.exacttarget.wsdl.partnerAPI.SoapType valueType,
           com.exacttarget.wsdl.partnerAPI.PropertyType propertyType,
           java.lang.Boolean isCreatable,
           java.lang.Boolean isUpdatable,
           java.lang.Boolean isRetrievable,
           java.lang.Boolean isQueryable,
           java.lang.Boolean isFilterable,
           java.lang.Boolean isPartnerProperty,
           java.lang.Boolean isAccountProperty,
           java.lang.String partnerMap,
           com.exacttarget.wsdl.partnerAPI.AttributeMap[] attributeMaps,
           com.exacttarget.wsdl.partnerAPI.APIProperty[] markups,
           java.lang.Integer precision,
           java.lang.Integer scale,
           java.lang.String label,
           java.lang.String description,
           java.lang.String defaultValue,
           java.lang.Integer minLength,
           java.lang.Integer maxLength,
           java.lang.String minValue,
           java.lang.String maxValue,
           java.lang.Boolean isRequired,
           java.lang.Boolean isViewable,
           java.lang.Boolean isEditable,
           java.lang.Boolean isNillable,
           java.lang.Boolean isRestrictedPicklist,
           com.exacttarget.wsdl.partnerAPI.PicklistItem[] picklistItems,
           java.lang.Boolean isSendTime,
           java.lang.Integer displayOrder,
           com.exacttarget.wsdl.partnerAPI.APIObject[] references,
           java.lang.String relationshipName,
           java.lang.String status,
           java.lang.Boolean isContextSpecific) {
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
        this.dataType = dataType;
        this.valueType = valueType;
        this.propertyType = propertyType;
        this.isCreatable = isCreatable;
        this.isUpdatable = isUpdatable;
        this.isRetrievable = isRetrievable;
        this.isQueryable = isQueryable;
        this.isFilterable = isFilterable;
        this.isPartnerProperty = isPartnerProperty;
        this.isAccountProperty = isAccountProperty;
        this.partnerMap = partnerMap;
        this.attributeMaps = attributeMaps;
        this.markups = markups;
        this.precision = precision;
        this.scale = scale;
        this.label = label;
        this.description = description;
        this.defaultValue = defaultValue;
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.isRequired = isRequired;
        this.isViewable = isViewable;
        this.isEditable = isEditable;
        this.isNillable = isNillable;
        this.isRestrictedPicklist = isRestrictedPicklist;
        this.picklistItems = picklistItems;
        this.isSendTime = isSendTime;
        this.displayOrder = displayOrder;
        this.references = references;
        this.relationshipName = relationshipName;
        this.status = status;
        this.isContextSpecific = isContextSpecific;
    }


    /**
     * Gets the name value for this PropertyDefinition.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this PropertyDefinition.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the dataType value for this PropertyDefinition.
     * 
     * @return dataType   * Deprecated.  Please use ValueType.
     */
    public java.lang.String getDataType() {
        return dataType;
    }


    /**
     * Sets the dataType value for this PropertyDefinition.
     * 
     * @param dataType   * Deprecated.  Please use ValueType.
     */
    public void setDataType(java.lang.String dataType) {
        this.dataType = dataType;
    }


    /**
     * Gets the valueType value for this PropertyDefinition.
     * 
     * @return valueType   * Actual data type of the value that will be stored in this property
     */
    public com.exacttarget.wsdl.partnerAPI.SoapType getValueType() {
        return valueType;
    }


    /**
     * Sets the valueType value for this PropertyDefinition.
     * 
     * @param valueType   * Actual data type of the value that will be stored in this property
     */
    public void setValueType(com.exacttarget.wsdl.partnerAPI.SoapType valueType) {
        this.valueType = valueType;
    }


    /**
     * Gets the propertyType value for this PropertyDefinition.
     * 
     * @return propertyType   * ExactTarget data type of the property
     */
    public com.exacttarget.wsdl.partnerAPI.PropertyType getPropertyType() {
        return propertyType;
    }


    /**
     * Sets the propertyType value for this PropertyDefinition.
     * 
     * @param propertyType   * ExactTarget data type of the property
     */
    public void setPropertyType(com.exacttarget.wsdl.partnerAPI.PropertyType propertyType) {
        this.propertyType = propertyType;
    }


    /**
     * Gets the isCreatable value for this PropertyDefinition.
     * 
     * @return isCreatable   * Indicates whether the property can be created.  If true, then
     * this property value can be set in a create call.
     */
    public java.lang.Boolean getIsCreatable() {
        return isCreatable;
    }


    /**
     * Sets the isCreatable value for this PropertyDefinition.
     * 
     * @param isCreatable   * Indicates whether the property can be created.  If true, then
     * this property value can be set in a create call.
     */
    public void setIsCreatable(java.lang.Boolean isCreatable) {
        this.isCreatable = isCreatable;
    }


    /**
     * Gets the isUpdatable value for this PropertyDefinition.
     * 
     * @return isUpdatable   * Indicates whether the property can be updated.  If true, then
     * this property value can be set in an update call.
     */
    public java.lang.Boolean getIsUpdatable() {
        return isUpdatable;
    }


    /**
     * Sets the isUpdatable value for this PropertyDefinition.
     * 
     * @param isUpdatable   * Indicates whether the property can be updated.  If true, then
     * this property value can be set in an update call.
     */
    public void setIsUpdatable(java.lang.Boolean isUpdatable) {
        this.isUpdatable = isUpdatable;
    }


    /**
     * Gets the isRetrievable value for this PropertyDefinition.
     * 
     * @return isRetrievable   * Indicates whether the object can be retrieved via the retrieve
     * call.
     */
    public java.lang.Boolean getIsRetrievable() {
        return isRetrievable;
    }


    /**
     * Sets the isRetrievable value for this PropertyDefinition.
     * 
     * @param isRetrievable   * Indicates whether the object can be retrieved via the retrieve
     * call.
     */
    public void setIsRetrievable(java.lang.Boolean isRetrievable) {
        this.isRetrievable = isRetrievable;
    }


    /**
     * Gets the isQueryable value for this PropertyDefinition.
     * 
     * @return isQueryable   * Indicates whether the object can be queried via the query call.
     */
    public java.lang.Boolean getIsQueryable() {
        return isQueryable;
    }


    /**
     * Sets the isQueryable value for this PropertyDefinition.
     * 
     * @param isQueryable   * Indicates whether the object can be queried via the query call.
     */
    public void setIsQueryable(java.lang.Boolean isQueryable) {
        this.isQueryable = isQueryable;
    }


    /**
     * Gets the isFilterable value for this PropertyDefinition.
     * 
     * @return isFilterable   * Indicates whether the property is filterable.  If true, then
     * this property can be specified in a SimpleFilterPart in a retrieve
     * call.
     */
    public java.lang.Boolean getIsFilterable() {
        return isFilterable;
    }


    /**
     * Sets the isFilterable value for this PropertyDefinition.
     * 
     * @param isFilterable   * Indicates whether the property is filterable.  If true, then
     * this property can be specified in a SimpleFilterPart in a retrieve
     * call.
     */
    public void setIsFilterable(java.lang.Boolean isFilterable) {
        this.isFilterable = isFilterable;
    }


    /**
     * Gets the isPartnerProperty value for this PropertyDefinition.
     * 
     * @return isPartnerProperty   * Indicates if the property is specific to a partner.
     */
    public java.lang.Boolean getIsPartnerProperty() {
        return isPartnerProperty;
    }


    /**
     * Sets the isPartnerProperty value for this PropertyDefinition.
     * 
     * @param isPartnerProperty   * Indicates if the property is specific to a partner.
     */
    public void setIsPartnerProperty(java.lang.Boolean isPartnerProperty) {
        this.isPartnerProperty = isPartnerProperty;
    }


    /**
     * Gets the isAccountProperty value for this PropertyDefinition.
     * 
     * @return isAccountProperty   * Indicates if the property is specific to the Account.
     */
    public java.lang.Boolean getIsAccountProperty() {
        return isAccountProperty;
    }


    /**
     * Sets the isAccountProperty value for this PropertyDefinition.
     * 
     * @param isAccountProperty   * Indicates if the property is specific to the Account.
     */
    public void setIsAccountProperty(java.lang.Boolean isAccountProperty) {
        this.isAccountProperty = isAccountProperty;
    }


    /**
     * Gets the partnerMap value for this PropertyDefinition.
     * 
     * @return partnerMap   * Deprecated.
     */
    public java.lang.String getPartnerMap() {
        return partnerMap;
    }


    /**
     * Sets the partnerMap value for this PropertyDefinition.
     * 
     * @param partnerMap   * Deprecated.
     */
    public void setPartnerMap(java.lang.String partnerMap) {
        this.partnerMap = partnerMap;
    }


    /**
     * Gets the attributeMaps value for this PropertyDefinition.
     * 
     * @return attributeMaps
     */
    public com.exacttarget.wsdl.partnerAPI.AttributeMap[] getAttributeMaps() {
        return attributeMaps;
    }


    /**
     * Sets the attributeMaps value for this PropertyDefinition.
     * 
     * @param attributeMaps
     */
    public void setAttributeMaps(com.exacttarget.wsdl.partnerAPI.AttributeMap[] attributeMaps) {
        this.attributeMaps = attributeMaps;
    }

    public com.exacttarget.wsdl.partnerAPI.AttributeMap getAttributeMaps(int i) {
        return this.attributeMaps[i];
    }

    public void setAttributeMaps(int i, com.exacttarget.wsdl.partnerAPI.AttributeMap _value) {
        this.attributeMaps[i] = _value;
    }


    /**
     * Gets the markups value for this PropertyDefinition.
     * 
     * @return markups   * Deprecated.
     */
    public com.exacttarget.wsdl.partnerAPI.APIProperty[] getMarkups() {
        return markups;
    }


    /**
     * Sets the markups value for this PropertyDefinition.
     * 
     * @param markups   * Deprecated.
     */
    public void setMarkups(com.exacttarget.wsdl.partnerAPI.APIProperty[] markups) {
        this.markups = markups;
    }

    public com.exacttarget.wsdl.partnerAPI.APIProperty getMarkups(int i) {
        return this.markups[i];
    }

    public void setMarkups(int i, com.exacttarget.wsdl.partnerAPI.APIProperty _value) {
        this.markups[i] = _value;
    }


    /**
     * Gets the precision value for this PropertyDefinition.
     * 
     * @return precision   * Reserved for future use.
     */
    public java.lang.Integer getPrecision() {
        return precision;
    }


    /**
     * Sets the precision value for this PropertyDefinition.
     * 
     * @param precision   * Reserved for future use.
     */
    public void setPrecision(java.lang.Integer precision) {
        this.precision = precision;
    }


    /**
     * Gets the scale value for this PropertyDefinition.
     * 
     * @return scale   * Reserved for future use.
     */
    public java.lang.Integer getScale() {
        return scale;
    }


    /**
     * Sets the scale value for this PropertyDefinition.
     * 
     * @param scale   * Reserved for future use.
     */
    public void setScale(java.lang.Integer scale) {
        this.scale = scale;
    }


    /**
     * Gets the label value for this PropertyDefinition.
     * 
     * @return label   * Text label that is displayed next to the field in the user
     * interface.
     */
    public java.lang.String getLabel() {
        return label;
    }


    /**
     * Sets the label value for this PropertyDefinition.
     * 
     * @param label   * Text label that is displayed next to the field in the user
     * interface.
     */
    public void setLabel(java.lang.String label) {
        this.label = label;
    }


    /**
     * Gets the description value for this PropertyDefinition.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this PropertyDefinition.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the defaultValue value for this PropertyDefinition.
     * 
     * @return defaultValue
     */
    public java.lang.String getDefaultValue() {
        return defaultValue;
    }


    /**
     * Sets the defaultValue value for this PropertyDefinition.
     * 
     * @param defaultValue
     */
    public void setDefaultValue(java.lang.String defaultValue) {
        this.defaultValue = defaultValue;
    }


    /**
     * Gets the minLength value for this PropertyDefinition.
     * 
     * @return minLength   * Minimum length of the data.
     */
    public java.lang.Integer getMinLength() {
        return minLength;
    }


    /**
     * Sets the minLength value for this PropertyDefinition.
     * 
     * @param minLength   * Minimum length of the data.
     */
    public void setMinLength(java.lang.Integer minLength) {
        this.minLength = minLength;
    }


    /**
     * Gets the maxLength value for this PropertyDefinition.
     * 
     * @return maxLength   * Maximum length of the data.
     */
    public java.lang.Integer getMaxLength() {
        return maxLength;
    }


    /**
     * Sets the maxLength value for this PropertyDefinition.
     * 
     * @param maxLength   * Maximum length of the data.
     */
    public void setMaxLength(java.lang.Integer maxLength) {
        this.maxLength = maxLength;
    }


    /**
     * Gets the minValue value for this PropertyDefinition.
     * 
     * @return minValue   * Minimum value that this property can be set to.
     */
    public java.lang.String getMinValue() {
        return minValue;
    }


    /**
     * Sets the minValue value for this PropertyDefinition.
     * 
     * @param minValue   * Minimum value that this property can be set to.
     */
    public void setMinValue(java.lang.String minValue) {
        this.minValue = minValue;
    }


    /**
     * Gets the maxValue value for this PropertyDefinition.
     * 
     * @return maxValue   * Maximum value that this property can be set to.
     */
    public java.lang.String getMaxValue() {
        return maxValue;
    }


    /**
     * Sets the maxValue value for this PropertyDefinition.
     * 
     * @param maxValue   * Maximum value that this property can be set to.
     */
    public void setMaxValue(java.lang.String maxValue) {
        this.maxValue = maxValue;
    }


    /**
     * Gets the isRequired value for this PropertyDefinition.
     * 
     * @return isRequired   * Indicates whether the property must have a value specified.
     */
    public java.lang.Boolean getIsRequired() {
        return isRequired;
    }


    /**
     * Sets the isRequired value for this PropertyDefinition.
     * 
     * @param isRequired   * Indicates whether the property must have a value specified.
     */
    public void setIsRequired(java.lang.Boolean isRequired) {
        this.isRequired = isRequired;
    }


    /**
     * Gets the isViewable value for this PropertyDefinition.
     * 
     * @return isViewable   * Indicates whether the property is viewable to the end-user
     * in the profile center.
     */
    public java.lang.Boolean getIsViewable() {
        return isViewable;
    }


    /**
     * Sets the isViewable value for this PropertyDefinition.
     * 
     * @param isViewable   * Indicates whether the property is viewable to the end-user
     * in the profile center.
     */
    public void setIsViewable(java.lang.Boolean isViewable) {
        this.isViewable = isViewable;
    }


    /**
     * Gets the isEditable value for this PropertyDefinition.
     * 
     * @return isEditable   * Indicates whether the property is editable by the end-user
     * in the profile center.
     */
    public java.lang.Boolean getIsEditable() {
        return isEditable;
    }


    /**
     * Sets the isEditable value for this PropertyDefinition.
     * 
     * @param isEditable   * Indicates whether the property is editable by the end-user
     * in the profile center.
     */
    public void setIsEditable(java.lang.Boolean isEditable) {
        this.isEditable = isEditable;
    }


    /**
     * Gets the isNillable value for this PropertyDefinition.
     * 
     * @return isNillable   * Indicates whether the property can contain a null value.
     */
    public java.lang.Boolean getIsNillable() {
        return isNillable;
    }


    /**
     * Sets the isNillable value for this PropertyDefinition.
     * 
     * @param isNillable   * Indicates whether the property can contain a null value.
     */
    public void setIsNillable(java.lang.Boolean isNillable) {
        this.isNillable = isNillable;
    }


    /**
     * Gets the isRestrictedPicklist value for this PropertyDefinition.
     * 
     * @return isRestrictedPicklist   * Indicates if the property has a restricted list of valid values.
     */
    public java.lang.Boolean getIsRestrictedPicklist() {
        return isRestrictedPicklist;
    }


    /**
     * Sets the isRestrictedPicklist value for this PropertyDefinition.
     * 
     * @param isRestrictedPicklist   * Indicates if the property has a restricted list of valid values.
     */
    public void setIsRestrictedPicklist(java.lang.Boolean isRestrictedPicklist) {
        this.isRestrictedPicklist = isRestrictedPicklist;
    }


    /**
     * Gets the picklistItems value for this PropertyDefinition.
     * 
     * @return picklistItems   * List of valid values.
     */
    public com.exacttarget.wsdl.partnerAPI.PicklistItem[] getPicklistItems() {
        return picklistItems;
    }


    /**
     * Sets the picklistItems value for this PropertyDefinition.
     * 
     * @param picklistItems   * List of valid values.
     */
    public void setPicklistItems(com.exacttarget.wsdl.partnerAPI.PicklistItem[] picklistItems) {
        this.picklistItems = picklistItems;
    }


    /**
     * Gets the isSendTime value for this PropertyDefinition.
     * 
     * @return isSendTime   * Indicates whether the property is a send time attribute.
     */
    public java.lang.Boolean getIsSendTime() {
        return isSendTime;
    }


    /**
     * Sets the isSendTime value for this PropertyDefinition.
     * 
     * @param isSendTime   * Indicates whether the property is a send time attribute.
     */
    public void setIsSendTime(java.lang.Boolean isSendTime) {
        this.isSendTime = isSendTime;
    }


    /**
     * Gets the displayOrder value for this PropertyDefinition.
     * 
     * @return displayOrder   * Indicates the placement of this property within the list of
     * properties.
     */
    public java.lang.Integer getDisplayOrder() {
        return displayOrder;
    }


    /**
     * Sets the displayOrder value for this PropertyDefinition.
     * 
     * @param displayOrder   * Indicates the placement of this property within the list of
     * properties.
     */
    public void setDisplayOrder(java.lang.Integer displayOrder) {
        this.displayOrder = displayOrder;
    }


    /**
     * Gets the references value for this PropertyDefinition.
     * 
     * @return references   * Indicates the object types of the referenced objects.
     */
    public com.exacttarget.wsdl.partnerAPI.APIObject[] getReferences() {
        return references;
    }


    /**
     * Sets the references value for this PropertyDefinition.
     * 
     * @param references   * Indicates the object types of the referenced objects.
     */
    public void setReferences(com.exacttarget.wsdl.partnerAPI.APIObject[] references) {
        this.references = references;
    }


    /**
     * Gets the relationshipName value for this PropertyDefinition.
     * 
     * @return relationshipName   * The name of the relationship (e.g. One-to-One, One-to-Many).
     */
    public java.lang.String getRelationshipName() {
        return relationshipName;
    }


    /**
     * Sets the relationshipName value for this PropertyDefinition.
     * 
     * @param relationshipName   * The name of the relationship (e.g. One-to-One, One-to-Many).
     */
    public void setRelationshipName(java.lang.String relationshipName) {
        this.relationshipName = relationshipName;
    }


    /**
     * Gets the status value for this PropertyDefinition.
     * 
     * @return status   * Reserved for future use.
     */
    public java.lang.String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this PropertyDefinition.
     * 
     * @param status   * Reserved for future use.
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }


    /**
     * Gets the isContextSpecific value for this PropertyDefinition.
     * 
     * @return isContextSpecific   * Reserved for future use.
     */
    public java.lang.Boolean getIsContextSpecific() {
        return isContextSpecific;
    }


    /**
     * Sets the isContextSpecific value for this PropertyDefinition.
     * 
     * @param isContextSpecific   * Reserved for future use.
     */
    public void setIsContextSpecific(java.lang.Boolean isContextSpecific) {
        this.isContextSpecific = isContextSpecific;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PropertyDefinition)) return false;
        PropertyDefinition other = (PropertyDefinition) obj;
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
            ((this.dataType==null && other.getDataType()==null) || 
             (this.dataType!=null &&
              this.dataType.equals(other.getDataType()))) &&
            ((this.valueType==null && other.getValueType()==null) || 
             (this.valueType!=null &&
              this.valueType.equals(other.getValueType()))) &&
            ((this.propertyType==null && other.getPropertyType()==null) || 
             (this.propertyType!=null &&
              this.propertyType.equals(other.getPropertyType()))) &&
            ((this.isCreatable==null && other.getIsCreatable()==null) || 
             (this.isCreatable!=null &&
              this.isCreatable.equals(other.getIsCreatable()))) &&
            ((this.isUpdatable==null && other.getIsUpdatable()==null) || 
             (this.isUpdatable!=null &&
              this.isUpdatable.equals(other.getIsUpdatable()))) &&
            ((this.isRetrievable==null && other.getIsRetrievable()==null) || 
             (this.isRetrievable!=null &&
              this.isRetrievable.equals(other.getIsRetrievable()))) &&
            ((this.isQueryable==null && other.getIsQueryable()==null) || 
             (this.isQueryable!=null &&
              this.isQueryable.equals(other.getIsQueryable()))) &&
            ((this.isFilterable==null && other.getIsFilterable()==null) || 
             (this.isFilterable!=null &&
              this.isFilterable.equals(other.getIsFilterable()))) &&
            ((this.isPartnerProperty==null && other.getIsPartnerProperty()==null) || 
             (this.isPartnerProperty!=null &&
              this.isPartnerProperty.equals(other.getIsPartnerProperty()))) &&
            ((this.isAccountProperty==null && other.getIsAccountProperty()==null) || 
             (this.isAccountProperty!=null &&
              this.isAccountProperty.equals(other.getIsAccountProperty()))) &&
            ((this.partnerMap==null && other.getPartnerMap()==null) || 
             (this.partnerMap!=null &&
              this.partnerMap.equals(other.getPartnerMap()))) &&
            ((this.attributeMaps==null && other.getAttributeMaps()==null) || 
             (this.attributeMaps!=null &&
              java.util.Arrays.equals(this.attributeMaps, other.getAttributeMaps()))) &&
            ((this.markups==null && other.getMarkups()==null) || 
             (this.markups!=null &&
              java.util.Arrays.equals(this.markups, other.getMarkups()))) &&
            ((this.precision==null && other.getPrecision()==null) || 
             (this.precision!=null &&
              this.precision.equals(other.getPrecision()))) &&
            ((this.scale==null && other.getScale()==null) || 
             (this.scale!=null &&
              this.scale.equals(other.getScale()))) &&
            ((this.label==null && other.getLabel()==null) || 
             (this.label!=null &&
              this.label.equals(other.getLabel()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.defaultValue==null && other.getDefaultValue()==null) || 
             (this.defaultValue!=null &&
              this.defaultValue.equals(other.getDefaultValue()))) &&
            ((this.minLength==null && other.getMinLength()==null) || 
             (this.minLength!=null &&
              this.minLength.equals(other.getMinLength()))) &&
            ((this.maxLength==null && other.getMaxLength()==null) || 
             (this.maxLength!=null &&
              this.maxLength.equals(other.getMaxLength()))) &&
            ((this.minValue==null && other.getMinValue()==null) || 
             (this.minValue!=null &&
              this.minValue.equals(other.getMinValue()))) &&
            ((this.maxValue==null && other.getMaxValue()==null) || 
             (this.maxValue!=null &&
              this.maxValue.equals(other.getMaxValue()))) &&
            ((this.isRequired==null && other.getIsRequired()==null) || 
             (this.isRequired!=null &&
              this.isRequired.equals(other.getIsRequired()))) &&
            ((this.isViewable==null && other.getIsViewable()==null) || 
             (this.isViewable!=null &&
              this.isViewable.equals(other.getIsViewable()))) &&
            ((this.isEditable==null && other.getIsEditable()==null) || 
             (this.isEditable!=null &&
              this.isEditable.equals(other.getIsEditable()))) &&
            ((this.isNillable==null && other.getIsNillable()==null) || 
             (this.isNillable!=null &&
              this.isNillable.equals(other.getIsNillable()))) &&
            ((this.isRestrictedPicklist==null && other.getIsRestrictedPicklist()==null) || 
             (this.isRestrictedPicklist!=null &&
              this.isRestrictedPicklist.equals(other.getIsRestrictedPicklist()))) &&
            ((this.picklistItems==null && other.getPicklistItems()==null) || 
             (this.picklistItems!=null &&
              java.util.Arrays.equals(this.picklistItems, other.getPicklistItems()))) &&
            ((this.isSendTime==null && other.getIsSendTime()==null) || 
             (this.isSendTime!=null &&
              this.isSendTime.equals(other.getIsSendTime()))) &&
            ((this.displayOrder==null && other.getDisplayOrder()==null) || 
             (this.displayOrder!=null &&
              this.displayOrder.equals(other.getDisplayOrder()))) &&
            ((this.references==null && other.getReferences()==null) || 
             (this.references!=null &&
              java.util.Arrays.equals(this.references, other.getReferences()))) &&
            ((this.relationshipName==null && other.getRelationshipName()==null) || 
             (this.relationshipName!=null &&
              this.relationshipName.equals(other.getRelationshipName()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.isContextSpecific==null && other.getIsContextSpecific()==null) || 
             (this.isContextSpecific!=null &&
              this.isContextSpecific.equals(other.getIsContextSpecific())));
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
        if (getDataType() != null) {
            _hashCode += getDataType().hashCode();
        }
        if (getValueType() != null) {
            _hashCode += getValueType().hashCode();
        }
        if (getPropertyType() != null) {
            _hashCode += getPropertyType().hashCode();
        }
        if (getIsCreatable() != null) {
            _hashCode += getIsCreatable().hashCode();
        }
        if (getIsUpdatable() != null) {
            _hashCode += getIsUpdatable().hashCode();
        }
        if (getIsRetrievable() != null) {
            _hashCode += getIsRetrievable().hashCode();
        }
        if (getIsQueryable() != null) {
            _hashCode += getIsQueryable().hashCode();
        }
        if (getIsFilterable() != null) {
            _hashCode += getIsFilterable().hashCode();
        }
        if (getIsPartnerProperty() != null) {
            _hashCode += getIsPartnerProperty().hashCode();
        }
        if (getIsAccountProperty() != null) {
            _hashCode += getIsAccountProperty().hashCode();
        }
        if (getPartnerMap() != null) {
            _hashCode += getPartnerMap().hashCode();
        }
        if (getAttributeMaps() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAttributeMaps());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAttributeMaps(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getMarkups() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMarkups());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMarkups(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPrecision() != null) {
            _hashCode += getPrecision().hashCode();
        }
        if (getScale() != null) {
            _hashCode += getScale().hashCode();
        }
        if (getLabel() != null) {
            _hashCode += getLabel().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getDefaultValue() != null) {
            _hashCode += getDefaultValue().hashCode();
        }
        if (getMinLength() != null) {
            _hashCode += getMinLength().hashCode();
        }
        if (getMaxLength() != null) {
            _hashCode += getMaxLength().hashCode();
        }
        if (getMinValue() != null) {
            _hashCode += getMinValue().hashCode();
        }
        if (getMaxValue() != null) {
            _hashCode += getMaxValue().hashCode();
        }
        if (getIsRequired() != null) {
            _hashCode += getIsRequired().hashCode();
        }
        if (getIsViewable() != null) {
            _hashCode += getIsViewable().hashCode();
        }
        if (getIsEditable() != null) {
            _hashCode += getIsEditable().hashCode();
        }
        if (getIsNillable() != null) {
            _hashCode += getIsNillable().hashCode();
        }
        if (getIsRestrictedPicklist() != null) {
            _hashCode += getIsRestrictedPicklist().hashCode();
        }
        if (getPicklistItems() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPicklistItems());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPicklistItems(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getIsSendTime() != null) {
            _hashCode += getIsSendTime().hashCode();
        }
        if (getDisplayOrder() != null) {
            _hashCode += getDisplayOrder().hashCode();
        }
        if (getReferences() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getReferences());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getReferences(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getRelationshipName() != null) {
            _hashCode += getRelationshipName().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getIsContextSpecific() != null) {
            _hashCode += getIsContextSpecific().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PropertyDefinition.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PropertyDefinition"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valueType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ValueType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SoapType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("propertyType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PropertyType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PropertyType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isCreatable");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsCreatable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isUpdatable");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsUpdatable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isRetrievable");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsRetrievable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isQueryable");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsQueryable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isFilterable");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsFilterable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isPartnerProperty");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsPartnerProperty"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isAccountProperty");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsAccountProperty"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("partnerMap");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PartnerMap"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("attributeMaps");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AttributeMaps"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AttributeMap"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("markups");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Markups"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "APIProperty"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("precision");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Precision"));
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
        elemField.setFieldName("label");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Label"));
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
        elemField.setFieldName("defaultValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DefaultValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("minLength");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MinLength"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maxLength");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MaxLength"));
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
        elemField.setFieldName("isRequired");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsRequired"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
        elemField.setFieldName("isEditable");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsEditable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isNillable");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsNillable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isRestrictedPicklist");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsRestrictedPicklist"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("picklistItems");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PicklistItems"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PicklistItem"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PicklistItem"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isSendTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsSendTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("displayOrder");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DisplayOrder"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("references");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "References"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "APIObject"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Reference"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("relationshipName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RelationshipName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setFieldName("isContextSpecific");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsContextSpecific"));
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
