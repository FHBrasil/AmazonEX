/**
 * SuppressionListDefinition.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class SuppressionListDefinition  extends com.exacttarget.wsdl.partnerAPI.APIObject  implements java.io.Serializable {
    private java.lang.String name;

    private java.lang.Long category;

    private java.lang.String description;

    private com.exacttarget.wsdl.partnerAPI.SuppressionListContext[] contexts;

    private com.exacttarget.wsdl.partnerAPI.DataExtensionField[] fields;

    private java.lang.Long subscriberCount;

    private java.lang.String notifyEmail;

    public SuppressionListDefinition() {
    }

    public SuppressionListDefinition(
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
           java.lang.Long category,
           java.lang.String description,
           com.exacttarget.wsdl.partnerAPI.SuppressionListContext[] contexts,
           com.exacttarget.wsdl.partnerAPI.DataExtensionField[] fields,
           java.lang.Long subscriberCount,
           java.lang.String notifyEmail) {
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
        this.category = category;
        this.description = description;
        this.contexts = contexts;
        this.fields = fields;
        this.subscriberCount = subscriberCount;
        this.notifyEmail = notifyEmail;
    }


    /**
     * Gets the name value for this SuppressionListDefinition.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this SuppressionListDefinition.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the category value for this SuppressionListDefinition.
     * 
     * @return category
     */
    public java.lang.Long getCategory() {
        return category;
    }


    /**
     * Sets the category value for this SuppressionListDefinition.
     * 
     * @param category
     */
    public void setCategory(java.lang.Long category) {
        this.category = category;
    }


    /**
     * Gets the description value for this SuppressionListDefinition.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this SuppressionListDefinition.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the contexts value for this SuppressionListDefinition.
     * 
     * @return contexts
     */
    public com.exacttarget.wsdl.partnerAPI.SuppressionListContext[] getContexts() {
        return contexts;
    }


    /**
     * Sets the contexts value for this SuppressionListDefinition.
     * 
     * @param contexts
     */
    public void setContexts(com.exacttarget.wsdl.partnerAPI.SuppressionListContext[] contexts) {
        this.contexts = contexts;
    }


    /**
     * Gets the fields value for this SuppressionListDefinition.
     * 
     * @return fields
     */
    public com.exacttarget.wsdl.partnerAPI.DataExtensionField[] getFields() {
        return fields;
    }


    /**
     * Sets the fields value for this SuppressionListDefinition.
     * 
     * @param fields
     */
    public void setFields(com.exacttarget.wsdl.partnerAPI.DataExtensionField[] fields) {
        this.fields = fields;
    }


    /**
     * Gets the subscriberCount value for this SuppressionListDefinition.
     * 
     * @return subscriberCount
     */
    public java.lang.Long getSubscriberCount() {
        return subscriberCount;
    }


    /**
     * Sets the subscriberCount value for this SuppressionListDefinition.
     * 
     * @param subscriberCount
     */
    public void setSubscriberCount(java.lang.Long subscriberCount) {
        this.subscriberCount = subscriberCount;
    }


    /**
     * Gets the notifyEmail value for this SuppressionListDefinition.
     * 
     * @return notifyEmail
     */
    public java.lang.String getNotifyEmail() {
        return notifyEmail;
    }


    /**
     * Sets the notifyEmail value for this SuppressionListDefinition.
     * 
     * @param notifyEmail
     */
    public void setNotifyEmail(java.lang.String notifyEmail) {
        this.notifyEmail = notifyEmail;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SuppressionListDefinition)) return false;
        SuppressionListDefinition other = (SuppressionListDefinition) obj;
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
            ((this.category==null && other.getCategory()==null) || 
             (this.category!=null &&
              this.category.equals(other.getCategory()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.contexts==null && other.getContexts()==null) || 
             (this.contexts!=null &&
              java.util.Arrays.equals(this.contexts, other.getContexts()))) &&
            ((this.fields==null && other.getFields()==null) || 
             (this.fields!=null &&
              java.util.Arrays.equals(this.fields, other.getFields()))) &&
            ((this.subscriberCount==null && other.getSubscriberCount()==null) || 
             (this.subscriberCount!=null &&
              this.subscriberCount.equals(other.getSubscriberCount()))) &&
            ((this.notifyEmail==null && other.getNotifyEmail()==null) || 
             (this.notifyEmail!=null &&
              this.notifyEmail.equals(other.getNotifyEmail())));
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
        if (getCategory() != null) {
            _hashCode += getCategory().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getContexts() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getContexts());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getContexts(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getFields() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getFields());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getFields(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSubscriberCount() != null) {
            _hashCode += getSubscriberCount().hashCode();
        }
        if (getNotifyEmail() != null) {
            _hashCode += getNotifyEmail().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SuppressionListDefinition.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SuppressionListDefinition"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("category");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Category"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
        elemField.setFieldName("contexts");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Contexts"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SuppressionListContext"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Context"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fields");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Fields"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataExtensionField"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Field"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subscriberCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SubscriberCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("notifyEmail");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "NotifyEmail"));
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
