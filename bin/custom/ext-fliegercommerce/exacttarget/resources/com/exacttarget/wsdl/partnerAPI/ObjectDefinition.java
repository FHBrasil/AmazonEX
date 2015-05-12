/**
 * ObjectDefinition.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class ObjectDefinition  implements java.io.Serializable {
    private java.lang.String objectType;

    private java.lang.String name;

    private java.lang.Boolean isCreatable;

    private java.lang.Boolean isUpdatable;

    private java.lang.Boolean isRetrievable;

    private java.lang.Boolean isQueryable;

    private java.lang.Boolean isReference;

    private java.lang.String referencedType;

    private java.lang.String isPropertyCollection;

    private java.lang.Boolean isObjectCollection;

    private com.exacttarget.wsdl.partnerAPI.PropertyDefinition[] properties;

    private com.exacttarget.wsdl.partnerAPI.PropertyDefinition[] extendedProperties;

    private com.exacttarget.wsdl.partnerAPI.ObjectDefinition[] childObjects;

    public ObjectDefinition() {
    }

    public ObjectDefinition(
           java.lang.String objectType,
           java.lang.String name,
           java.lang.Boolean isCreatable,
           java.lang.Boolean isUpdatable,
           java.lang.Boolean isRetrievable,
           java.lang.Boolean isQueryable,
           java.lang.Boolean isReference,
           java.lang.String referencedType,
           java.lang.String isPropertyCollection,
           java.lang.Boolean isObjectCollection,
           com.exacttarget.wsdl.partnerAPI.PropertyDefinition[] properties,
           com.exacttarget.wsdl.partnerAPI.PropertyDefinition[] extendedProperties,
           com.exacttarget.wsdl.partnerAPI.ObjectDefinition[] childObjects) {
           this.objectType = objectType;
           this.name = name;
           this.isCreatable = isCreatable;
           this.isUpdatable = isUpdatable;
           this.isRetrievable = isRetrievable;
           this.isQueryable = isQueryable;
           this.isReference = isReference;
           this.referencedType = referencedType;
           this.isPropertyCollection = isPropertyCollection;
           this.isObjectCollection = isObjectCollection;
           this.properties = properties;
           this.extendedProperties = extendedProperties;
           this.childObjects = childObjects;
    }


    /**
     * Gets the objectType value for this ObjectDefinition.
     * 
     * @return objectType
     */
    public java.lang.String getObjectType() {
        return objectType;
    }


    /**
     * Sets the objectType value for this ObjectDefinition.
     * 
     * @param objectType
     */
    public void setObjectType(java.lang.String objectType) {
        this.objectType = objectType;
    }


    /**
     * Gets the name value for this ObjectDefinition.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this ObjectDefinition.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the isCreatable value for this ObjectDefinition.
     * 
     * @return isCreatable
     */
    public java.lang.Boolean getIsCreatable() {
        return isCreatable;
    }


    /**
     * Sets the isCreatable value for this ObjectDefinition.
     * 
     * @param isCreatable
     */
    public void setIsCreatable(java.lang.Boolean isCreatable) {
        this.isCreatable = isCreatable;
    }


    /**
     * Gets the isUpdatable value for this ObjectDefinition.
     * 
     * @return isUpdatable
     */
    public java.lang.Boolean getIsUpdatable() {
        return isUpdatable;
    }


    /**
     * Sets the isUpdatable value for this ObjectDefinition.
     * 
     * @param isUpdatable
     */
    public void setIsUpdatable(java.lang.Boolean isUpdatable) {
        this.isUpdatable = isUpdatable;
    }


    /**
     * Gets the isRetrievable value for this ObjectDefinition.
     * 
     * @return isRetrievable
     */
    public java.lang.Boolean getIsRetrievable() {
        return isRetrievable;
    }


    /**
     * Sets the isRetrievable value for this ObjectDefinition.
     * 
     * @param isRetrievable
     */
    public void setIsRetrievable(java.lang.Boolean isRetrievable) {
        this.isRetrievable = isRetrievable;
    }


    /**
     * Gets the isQueryable value for this ObjectDefinition.
     * 
     * @return isQueryable
     */
    public java.lang.Boolean getIsQueryable() {
        return isQueryable;
    }


    /**
     * Sets the isQueryable value for this ObjectDefinition.
     * 
     * @param isQueryable
     */
    public void setIsQueryable(java.lang.Boolean isQueryable) {
        this.isQueryable = isQueryable;
    }


    /**
     * Gets the isReference value for this ObjectDefinition.
     * 
     * @return isReference
     */
    public java.lang.Boolean getIsReference() {
        return isReference;
    }


    /**
     * Sets the isReference value for this ObjectDefinition.
     * 
     * @param isReference
     */
    public void setIsReference(java.lang.Boolean isReference) {
        this.isReference = isReference;
    }


    /**
     * Gets the referencedType value for this ObjectDefinition.
     * 
     * @return referencedType
     */
    public java.lang.String getReferencedType() {
        return referencedType;
    }


    /**
     * Sets the referencedType value for this ObjectDefinition.
     * 
     * @param referencedType
     */
    public void setReferencedType(java.lang.String referencedType) {
        this.referencedType = referencedType;
    }


    /**
     * Gets the isPropertyCollection value for this ObjectDefinition.
     * 
     * @return isPropertyCollection
     */
    public java.lang.String getIsPropertyCollection() {
        return isPropertyCollection;
    }


    /**
     * Sets the isPropertyCollection value for this ObjectDefinition.
     * 
     * @param isPropertyCollection
     */
    public void setIsPropertyCollection(java.lang.String isPropertyCollection) {
        this.isPropertyCollection = isPropertyCollection;
    }


    /**
     * Gets the isObjectCollection value for this ObjectDefinition.
     * 
     * @return isObjectCollection
     */
    public java.lang.Boolean getIsObjectCollection() {
        return isObjectCollection;
    }


    /**
     * Sets the isObjectCollection value for this ObjectDefinition.
     * 
     * @param isObjectCollection
     */
    public void setIsObjectCollection(java.lang.Boolean isObjectCollection) {
        this.isObjectCollection = isObjectCollection;
    }


    /**
     * Gets the properties value for this ObjectDefinition.
     * 
     * @return properties
     */
    public com.exacttarget.wsdl.partnerAPI.PropertyDefinition[] getProperties() {
        return properties;
    }


    /**
     * Sets the properties value for this ObjectDefinition.
     * 
     * @param properties
     */
    public void setProperties(com.exacttarget.wsdl.partnerAPI.PropertyDefinition[] properties) {
        this.properties = properties;
    }

    public com.exacttarget.wsdl.partnerAPI.PropertyDefinition getProperties(int i) {
        return this.properties[i];
    }

    public void setProperties(int i, com.exacttarget.wsdl.partnerAPI.PropertyDefinition _value) {
        this.properties[i] = _value;
    }


    /**
     * Gets the extendedProperties value for this ObjectDefinition.
     * 
     * @return extendedProperties
     */
    public com.exacttarget.wsdl.partnerAPI.PropertyDefinition[] getExtendedProperties() {
        return extendedProperties;
    }


    /**
     * Sets the extendedProperties value for this ObjectDefinition.
     * 
     * @param extendedProperties
     */
    public void setExtendedProperties(com.exacttarget.wsdl.partnerAPI.PropertyDefinition[] extendedProperties) {
        this.extendedProperties = extendedProperties;
    }


    /**
     * Gets the childObjects value for this ObjectDefinition.
     * 
     * @return childObjects
     */
    public com.exacttarget.wsdl.partnerAPI.ObjectDefinition[] getChildObjects() {
        return childObjects;
    }


    /**
     * Sets the childObjects value for this ObjectDefinition.
     * 
     * @param childObjects
     */
    public void setChildObjects(com.exacttarget.wsdl.partnerAPI.ObjectDefinition[] childObjects) {
        this.childObjects = childObjects;
    }

    public com.exacttarget.wsdl.partnerAPI.ObjectDefinition getChildObjects(int i) {
        return this.childObjects[i];
    }

    public void setChildObjects(int i, com.exacttarget.wsdl.partnerAPI.ObjectDefinition _value) {
        this.childObjects[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ObjectDefinition)) return false;
        ObjectDefinition other = (ObjectDefinition) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.objectType==null && other.getObjectType()==null) || 
             (this.objectType!=null &&
              this.objectType.equals(other.getObjectType()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
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
            ((this.isReference==null && other.getIsReference()==null) || 
             (this.isReference!=null &&
              this.isReference.equals(other.getIsReference()))) &&
            ((this.referencedType==null && other.getReferencedType()==null) || 
             (this.referencedType!=null &&
              this.referencedType.equals(other.getReferencedType()))) &&
            ((this.isPropertyCollection==null && other.getIsPropertyCollection()==null) || 
             (this.isPropertyCollection!=null &&
              this.isPropertyCollection.equals(other.getIsPropertyCollection()))) &&
            ((this.isObjectCollection==null && other.getIsObjectCollection()==null) || 
             (this.isObjectCollection!=null &&
              this.isObjectCollection.equals(other.getIsObjectCollection()))) &&
            ((this.properties==null && other.getProperties()==null) || 
             (this.properties!=null &&
              java.util.Arrays.equals(this.properties, other.getProperties()))) &&
            ((this.extendedProperties==null && other.getExtendedProperties()==null) || 
             (this.extendedProperties!=null &&
              java.util.Arrays.equals(this.extendedProperties, other.getExtendedProperties()))) &&
            ((this.childObjects==null && other.getChildObjects()==null) || 
             (this.childObjects!=null &&
              java.util.Arrays.equals(this.childObjects, other.getChildObjects())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getObjectType() != null) {
            _hashCode += getObjectType().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
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
        if (getIsReference() != null) {
            _hashCode += getIsReference().hashCode();
        }
        if (getReferencedType() != null) {
            _hashCode += getReferencedType().hashCode();
        }
        if (getIsPropertyCollection() != null) {
            _hashCode += getIsPropertyCollection().hashCode();
        }
        if (getIsObjectCollection() != null) {
            _hashCode += getIsObjectCollection().hashCode();
        }
        if (getProperties() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getProperties());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getProperties(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getExtendedProperties() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getExtendedProperties());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getExtendedProperties(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getChildObjects() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getChildObjects());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getChildObjects(), i);
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
        new org.apache.axis.description.TypeDesc(ObjectDefinition.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ObjectDefinition"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("objectType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ObjectType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setFieldName("isReference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsReference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("referencedType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ReferencedType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isPropertyCollection");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsPropertyCollection"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isObjectCollection");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsObjectCollection"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("properties");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Properties"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PropertyDefinition"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extendedProperties");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExtendedProperties"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PropertyDefinition"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExtendedProperty"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("childObjects");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ChildObjects"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ObjectDefinition"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
