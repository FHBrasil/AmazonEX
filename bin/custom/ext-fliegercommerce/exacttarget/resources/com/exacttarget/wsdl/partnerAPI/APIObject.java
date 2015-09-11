/**
 * APIObject.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class APIObject  implements java.io.Serializable {
    private com.exacttarget.wsdl.partnerAPI.ClientID client;

    private java.lang.String partnerKey;

    private com.exacttarget.wsdl.partnerAPI.APIProperty[] partnerProperties;

    private java.util.Calendar createdDate;

    private java.util.Calendar modifiedDate;

    private java.lang.Integer ID;

    private java.lang.String objectID;

    private java.lang.String customerKey;

    private com.exacttarget.wsdl.partnerAPI.Owner owner;

    private java.lang.String correlationID;

    private java.lang.String objectState;

    public APIObject() {
    }

    public APIObject(
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
           java.lang.String objectState) {
           this.client = client;
           this.partnerKey = partnerKey;
           this.partnerProperties = partnerProperties;
           this.createdDate = createdDate;
           this.modifiedDate = modifiedDate;
           this.ID = ID;
           this.objectID = objectID;
           this.customerKey = customerKey;
           this.owner = owner;
           this.correlationID = correlationID;
           this.objectState = objectState;
    }


    /**
     * Gets the client value for this APIObject.
     * 
     * @return client
     */
    public com.exacttarget.wsdl.partnerAPI.ClientID getClient() {
        return client;
    }


    /**
     * Sets the client value for this APIObject.
     * 
     * @param client
     */
    public void setClient(com.exacttarget.wsdl.partnerAPI.ClientID client) {
        this.client = client;
    }


    /**
     * Gets the partnerKey value for this APIObject.
     * 
     * @return partnerKey
     */
    public java.lang.String getPartnerKey() {
        return partnerKey;
    }


    /**
     * Sets the partnerKey value for this APIObject.
     * 
     * @param partnerKey
     */
    public void setPartnerKey(java.lang.String partnerKey) {
        this.partnerKey = partnerKey;
    }


    /**
     * Gets the partnerProperties value for this APIObject.
     * 
     * @return partnerProperties
     */
    public com.exacttarget.wsdl.partnerAPI.APIProperty[] getPartnerProperties() {
        return partnerProperties;
    }


    /**
     * Sets the partnerProperties value for this APIObject.
     * 
     * @param partnerProperties
     */
    public void setPartnerProperties(com.exacttarget.wsdl.partnerAPI.APIProperty[] partnerProperties) {
        this.partnerProperties = partnerProperties;
    }

    public com.exacttarget.wsdl.partnerAPI.APIProperty getPartnerProperties(int i) {
        return this.partnerProperties[i];
    }

    public void setPartnerProperties(int i, com.exacttarget.wsdl.partnerAPI.APIProperty _value) {
        this.partnerProperties[i] = _value;
    }


    /**
     * Gets the createdDate value for this APIObject.
     * 
     * @return createdDate
     */
    public java.util.Calendar getCreatedDate() {
        return createdDate;
    }


    /**
     * Sets the createdDate value for this APIObject.
     * 
     * @param createdDate
     */
    public void setCreatedDate(java.util.Calendar createdDate) {
        this.createdDate = createdDate;
    }


    /**
     * Gets the modifiedDate value for this APIObject.
     * 
     * @return modifiedDate
     */
    public java.util.Calendar getModifiedDate() {
        return modifiedDate;
    }


    /**
     * Sets the modifiedDate value for this APIObject.
     * 
     * @param modifiedDate
     */
    public void setModifiedDate(java.util.Calendar modifiedDate) {
        this.modifiedDate = modifiedDate;
    }


    /**
     * Gets the ID value for this APIObject.
     * 
     * @return ID
     */
    public java.lang.Integer getID() {
        return ID;
    }


    /**
     * Sets the ID value for this APIObject.
     * 
     * @param ID
     */
    public void setID(java.lang.Integer ID) {
        this.ID = ID;
    }


    /**
     * Gets the objectID value for this APIObject.
     * 
     * @return objectID
     */
    public java.lang.String getObjectID() {
        return objectID;
    }


    /**
     * Sets the objectID value for this APIObject.
     * 
     * @param objectID
     */
    public void setObjectID(java.lang.String objectID) {
        this.objectID = objectID;
    }


    /**
     * Gets the customerKey value for this APIObject.
     * 
     * @return customerKey
     */
    public java.lang.String getCustomerKey() {
        return customerKey;
    }


    /**
     * Sets the customerKey value for this APIObject.
     * 
     * @param customerKey
     */
    public void setCustomerKey(java.lang.String customerKey) {
        this.customerKey = customerKey;
    }


    /**
     * Gets the owner value for this APIObject.
     * 
     * @return owner
     */
    public com.exacttarget.wsdl.partnerAPI.Owner getOwner() {
        return owner;
    }


    /**
     * Sets the owner value for this APIObject.
     * 
     * @param owner
     */
    public void setOwner(com.exacttarget.wsdl.partnerAPI.Owner owner) {
        this.owner = owner;
    }


    /**
     * Gets the correlationID value for this APIObject.
     * 
     * @return correlationID
     */
    public java.lang.String getCorrelationID() {
        return correlationID;
    }


    /**
     * Sets the correlationID value for this APIObject.
     * 
     * @param correlationID
     */
    public void setCorrelationID(java.lang.String correlationID) {
        this.correlationID = correlationID;
    }


    /**
     * Gets the objectState value for this APIObject.
     * 
     * @return objectState
     */
    public java.lang.String getObjectState() {
        return objectState;
    }


    /**
     * Sets the objectState value for this APIObject.
     * 
     * @param objectState
     */
    public void setObjectState(java.lang.String objectState) {
        this.objectState = objectState;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof APIObject)) return false;
        APIObject other = (APIObject) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.client==null && other.getClient()==null) || 
             (this.client!=null &&
              this.client.equals(other.getClient()))) &&
            ((this.partnerKey==null && other.getPartnerKey()==null) || 
             (this.partnerKey!=null &&
              this.partnerKey.equals(other.getPartnerKey()))) &&
            ((this.partnerProperties==null && other.getPartnerProperties()==null) || 
             (this.partnerProperties!=null &&
              java.util.Arrays.equals(this.partnerProperties, other.getPartnerProperties()))) &&
            ((this.createdDate==null && other.getCreatedDate()==null) || 
             (this.createdDate!=null &&
              this.createdDate.equals(other.getCreatedDate()))) &&
            ((this.modifiedDate==null && other.getModifiedDate()==null) || 
             (this.modifiedDate!=null &&
              this.modifiedDate.equals(other.getModifiedDate()))) &&
            ((this.ID==null && other.getID()==null) || 
             (this.ID!=null &&
              this.ID.equals(other.getID()))) &&
            ((this.objectID==null && other.getObjectID()==null) || 
             (this.objectID!=null &&
              this.objectID.equals(other.getObjectID()))) &&
            ((this.customerKey==null && other.getCustomerKey()==null) || 
             (this.customerKey!=null &&
              this.customerKey.equals(other.getCustomerKey()))) &&
            ((this.owner==null && other.getOwner()==null) || 
             (this.owner!=null &&
              this.owner.equals(other.getOwner()))) &&
            ((this.correlationID==null && other.getCorrelationID()==null) || 
             (this.correlationID!=null &&
              this.correlationID.equals(other.getCorrelationID()))) &&
            ((this.objectState==null && other.getObjectState()==null) || 
             (this.objectState!=null &&
              this.objectState.equals(other.getObjectState())));
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
        if (getClient() != null) {
            _hashCode += getClient().hashCode();
        }
        if (getPartnerKey() != null) {
            _hashCode += getPartnerKey().hashCode();
        }
        if (getPartnerProperties() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPartnerProperties());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPartnerProperties(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCreatedDate() != null) {
            _hashCode += getCreatedDate().hashCode();
        }
        if (getModifiedDate() != null) {
            _hashCode += getModifiedDate().hashCode();
        }
        if (getID() != null) {
            _hashCode += getID().hashCode();
        }
        if (getObjectID() != null) {
            _hashCode += getObjectID().hashCode();
        }
        if (getCustomerKey() != null) {
            _hashCode += getCustomerKey().hashCode();
        }
        if (getOwner() != null) {
            _hashCode += getOwner().hashCode();
        }
        if (getCorrelationID() != null) {
            _hashCode += getCorrelationID().hashCode();
        }
        if (getObjectState() != null) {
            _hashCode += getObjectState().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(APIObject.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "APIObject"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("client");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Client"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ClientID"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("partnerKey");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PartnerKey"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("partnerProperties");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PartnerProperties"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "APIProperty"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("createdDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "CreatedDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modifiedDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ModifiedDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("objectID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ObjectID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customerKey");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "CustomerKey"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("owner");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Owner"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Owner"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("correlationID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "CorrelationID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("objectState");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ObjectState"));
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
