/**
 * List.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class List  extends com.exacttarget.wsdl.partnerAPI.APIObject  implements java.io.Serializable {
    private java.lang.String listName;

    private java.lang.Integer category;

    private com.exacttarget.wsdl.partnerAPI.ListTypeEnum type;

    private java.lang.String description;

    private com.exacttarget.wsdl.partnerAPI.Subscriber[] subscribers;

    private com.exacttarget.wsdl.partnerAPI.ListClassificationEnum listClassification;

    private com.exacttarget.wsdl.partnerAPI.Email automatedEmail;

    private com.exacttarget.wsdl.partnerAPI.SendClassification sendClassification;

    public List() {
    }

    public List(
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
           java.lang.String listName,
           java.lang.Integer category,
           com.exacttarget.wsdl.partnerAPI.ListTypeEnum type,
           java.lang.String description,
           com.exacttarget.wsdl.partnerAPI.Subscriber[] subscribers,
           com.exacttarget.wsdl.partnerAPI.ListClassificationEnum listClassification,
           com.exacttarget.wsdl.partnerAPI.Email automatedEmail,
           com.exacttarget.wsdl.partnerAPI.SendClassification sendClassification) {
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
        this.listName = listName;
        this.category = category;
        this.type = type;
        this.description = description;
        this.subscribers = subscribers;
        this.listClassification = listClassification;
        this.automatedEmail = automatedEmail;
        this.sendClassification = sendClassification;
    }


    /**
     * Gets the listName value for this List.
     * 
     * @return listName
     */
    public java.lang.String getListName() {
        return listName;
    }


    /**
     * Sets the listName value for this List.
     * 
     * @param listName
     */
    public void setListName(java.lang.String listName) {
        this.listName = listName;
    }


    /**
     * Gets the category value for this List.
     * 
     * @return category
     */
    public java.lang.Integer getCategory() {
        return category;
    }


    /**
     * Sets the category value for this List.
     * 
     * @param category
     */
    public void setCategory(java.lang.Integer category) {
        this.category = category;
    }


    /**
     * Gets the type value for this List.
     * 
     * @return type
     */
    public com.exacttarget.wsdl.partnerAPI.ListTypeEnum getType() {
        return type;
    }


    /**
     * Sets the type value for this List.
     * 
     * @param type
     */
    public void setType(com.exacttarget.wsdl.partnerAPI.ListTypeEnum type) {
        this.type = type;
    }


    /**
     * Gets the description value for this List.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this List.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the subscribers value for this List.
     * 
     * @return subscribers
     */
    public com.exacttarget.wsdl.partnerAPI.Subscriber[] getSubscribers() {
        return subscribers;
    }


    /**
     * Sets the subscribers value for this List.
     * 
     * @param subscribers
     */
    public void setSubscribers(com.exacttarget.wsdl.partnerAPI.Subscriber[] subscribers) {
        this.subscribers = subscribers;
    }

    public com.exacttarget.wsdl.partnerAPI.Subscriber getSubscribers(int i) {
        return this.subscribers[i];
    }

    public void setSubscribers(int i, com.exacttarget.wsdl.partnerAPI.Subscriber _value) {
        this.subscribers[i] = _value;
    }


    /**
     * Gets the listClassification value for this List.
     * 
     * @return listClassification
     */
    public com.exacttarget.wsdl.partnerAPI.ListClassificationEnum getListClassification() {
        return listClassification;
    }


    /**
     * Sets the listClassification value for this List.
     * 
     * @param listClassification
     */
    public void setListClassification(com.exacttarget.wsdl.partnerAPI.ListClassificationEnum listClassification) {
        this.listClassification = listClassification;
    }


    /**
     * Gets the automatedEmail value for this List.
     * 
     * @return automatedEmail
     */
    public com.exacttarget.wsdl.partnerAPI.Email getAutomatedEmail() {
        return automatedEmail;
    }


    /**
     * Sets the automatedEmail value for this List.
     * 
     * @param automatedEmail
     */
    public void setAutomatedEmail(com.exacttarget.wsdl.partnerAPI.Email automatedEmail) {
        this.automatedEmail = automatedEmail;
    }


    /**
     * Gets the sendClassification value for this List.
     * 
     * @return sendClassification
     */
    public com.exacttarget.wsdl.partnerAPI.SendClassification getSendClassification() {
        return sendClassification;
    }


    /**
     * Sets the sendClassification value for this List.
     * 
     * @param sendClassification
     */
    public void setSendClassification(com.exacttarget.wsdl.partnerAPI.SendClassification sendClassification) {
        this.sendClassification = sendClassification;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof List)) return false;
        List other = (List) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.listName==null && other.getListName()==null) || 
             (this.listName!=null &&
              this.listName.equals(other.getListName()))) &&
            ((this.category==null && other.getCategory()==null) || 
             (this.category!=null &&
              this.category.equals(other.getCategory()))) &&
            ((this.type==null && other.getType()==null) || 
             (this.type!=null &&
              this.type.equals(other.getType()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.subscribers==null && other.getSubscribers()==null) || 
             (this.subscribers!=null &&
              java.util.Arrays.equals(this.subscribers, other.getSubscribers()))) &&
            ((this.listClassification==null && other.getListClassification()==null) || 
             (this.listClassification!=null &&
              this.listClassification.equals(other.getListClassification()))) &&
            ((this.automatedEmail==null && other.getAutomatedEmail()==null) || 
             (this.automatedEmail!=null &&
              this.automatedEmail.equals(other.getAutomatedEmail()))) &&
            ((this.sendClassification==null && other.getSendClassification()==null) || 
             (this.sendClassification!=null &&
              this.sendClassification.equals(other.getSendClassification())));
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
        if (getListName() != null) {
            _hashCode += getListName().hashCode();
        }
        if (getCategory() != null) {
            _hashCode += getCategory().hashCode();
        }
        if (getType() != null) {
            _hashCode += getType().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getSubscribers() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSubscribers());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSubscribers(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getListClassification() != null) {
            _hashCode += getListClassification().hashCode();
        }
        if (getAutomatedEmail() != null) {
            _hashCode += getAutomatedEmail().hashCode();
        }
        if (getSendClassification() != null) {
            _hashCode += getSendClassification().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(List.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "List"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ListName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("category");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Category"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("type");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ListTypeEnum"));
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
        elemField.setFieldName("subscribers");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Subscribers"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Subscriber"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listClassification");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ListClassification"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ListClassificationEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("automatedEmail");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AutomatedEmail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Email"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sendClassification");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendClassification"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendClassification"));
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
