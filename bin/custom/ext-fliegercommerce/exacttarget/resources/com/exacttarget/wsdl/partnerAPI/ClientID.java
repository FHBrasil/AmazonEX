/**
 * ClientID.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class ClientID  implements java.io.Serializable {
    /* Deprecated.  Use ID. */
    private java.lang.Integer clientID;

    private java.lang.Integer ID;

    private java.lang.String partnerClientKey;

    private java.lang.Integer userID;

    private java.lang.String partnerUserKey;

    private java.lang.Integer createdBy;

    private java.lang.Integer modifiedBy;

    private java.lang.Long enterpriseID;

    private java.lang.String customerKey;

    public ClientID() {
    }

    public ClientID(
           java.lang.Integer clientID,
           java.lang.Integer ID,
           java.lang.String partnerClientKey,
           java.lang.Integer userID,
           java.lang.String partnerUserKey,
           java.lang.Integer createdBy,
           java.lang.Integer modifiedBy,
           java.lang.Long enterpriseID,
           java.lang.String customerKey) {
           this.clientID = clientID;
           this.ID = ID;
           this.partnerClientKey = partnerClientKey;
           this.userID = userID;
           this.partnerUserKey = partnerUserKey;
           this.createdBy = createdBy;
           this.modifiedBy = modifiedBy;
           this.enterpriseID = enterpriseID;
           this.customerKey = customerKey;
    }


    /**
     * Gets the clientID value for this ClientID.
     * 
     * @return clientID   * Deprecated.  Use ID.
     */
    public java.lang.Integer getClientID() {
        return clientID;
    }


    /**
     * Sets the clientID value for this ClientID.
     * 
     * @param clientID   * Deprecated.  Use ID.
     */
    public void setClientID(java.lang.Integer clientID) {
        this.clientID = clientID;
    }


    /**
     * Gets the ID value for this ClientID.
     * 
     * @return ID
     */
    public java.lang.Integer getID() {
        return ID;
    }


    /**
     * Sets the ID value for this ClientID.
     * 
     * @param ID
     */
    public void setID(java.lang.Integer ID) {
        this.ID = ID;
    }


    /**
     * Gets the partnerClientKey value for this ClientID.
     * 
     * @return partnerClientKey
     */
    public java.lang.String getPartnerClientKey() {
        return partnerClientKey;
    }


    /**
     * Sets the partnerClientKey value for this ClientID.
     * 
     * @param partnerClientKey
     */
    public void setPartnerClientKey(java.lang.String partnerClientKey) {
        this.partnerClientKey = partnerClientKey;
    }


    /**
     * Gets the userID value for this ClientID.
     * 
     * @return userID
     */
    public java.lang.Integer getUserID() {
        return userID;
    }


    /**
     * Sets the userID value for this ClientID.
     * 
     * @param userID
     */
    public void setUserID(java.lang.Integer userID) {
        this.userID = userID;
    }


    /**
     * Gets the partnerUserKey value for this ClientID.
     * 
     * @return partnerUserKey
     */
    public java.lang.String getPartnerUserKey() {
        return partnerUserKey;
    }


    /**
     * Sets the partnerUserKey value for this ClientID.
     * 
     * @param partnerUserKey
     */
    public void setPartnerUserKey(java.lang.String partnerUserKey) {
        this.partnerUserKey = partnerUserKey;
    }


    /**
     * Gets the createdBy value for this ClientID.
     * 
     * @return createdBy
     */
    public java.lang.Integer getCreatedBy() {
        return createdBy;
    }


    /**
     * Sets the createdBy value for this ClientID.
     * 
     * @param createdBy
     */
    public void setCreatedBy(java.lang.Integer createdBy) {
        this.createdBy = createdBy;
    }


    /**
     * Gets the modifiedBy value for this ClientID.
     * 
     * @return modifiedBy
     */
    public java.lang.Integer getModifiedBy() {
        return modifiedBy;
    }


    /**
     * Sets the modifiedBy value for this ClientID.
     * 
     * @param modifiedBy
     */
    public void setModifiedBy(java.lang.Integer modifiedBy) {
        this.modifiedBy = modifiedBy;
    }


    /**
     * Gets the enterpriseID value for this ClientID.
     * 
     * @return enterpriseID
     */
    public java.lang.Long getEnterpriseID() {
        return enterpriseID;
    }


    /**
     * Sets the enterpriseID value for this ClientID.
     * 
     * @param enterpriseID
     */
    public void setEnterpriseID(java.lang.Long enterpriseID) {
        this.enterpriseID = enterpriseID;
    }


    /**
     * Gets the customerKey value for this ClientID.
     * 
     * @return customerKey
     */
    public java.lang.String getCustomerKey() {
        return customerKey;
    }


    /**
     * Sets the customerKey value for this ClientID.
     * 
     * @param customerKey
     */
    public void setCustomerKey(java.lang.String customerKey) {
        this.customerKey = customerKey;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ClientID)) return false;
        ClientID other = (ClientID) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.clientID==null && other.getClientID()==null) || 
             (this.clientID!=null &&
              this.clientID.equals(other.getClientID()))) &&
            ((this.ID==null && other.getID()==null) || 
             (this.ID!=null &&
              this.ID.equals(other.getID()))) &&
            ((this.partnerClientKey==null && other.getPartnerClientKey()==null) || 
             (this.partnerClientKey!=null &&
              this.partnerClientKey.equals(other.getPartnerClientKey()))) &&
            ((this.userID==null && other.getUserID()==null) || 
             (this.userID!=null &&
              this.userID.equals(other.getUserID()))) &&
            ((this.partnerUserKey==null && other.getPartnerUserKey()==null) || 
             (this.partnerUserKey!=null &&
              this.partnerUserKey.equals(other.getPartnerUserKey()))) &&
            ((this.createdBy==null && other.getCreatedBy()==null) || 
             (this.createdBy!=null &&
              this.createdBy.equals(other.getCreatedBy()))) &&
            ((this.modifiedBy==null && other.getModifiedBy()==null) || 
             (this.modifiedBy!=null &&
              this.modifiedBy.equals(other.getModifiedBy()))) &&
            ((this.enterpriseID==null && other.getEnterpriseID()==null) || 
             (this.enterpriseID!=null &&
              this.enterpriseID.equals(other.getEnterpriseID()))) &&
            ((this.customerKey==null && other.getCustomerKey()==null) || 
             (this.customerKey!=null &&
              this.customerKey.equals(other.getCustomerKey())));
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
        if (getClientID() != null) {
            _hashCode += getClientID().hashCode();
        }
        if (getID() != null) {
            _hashCode += getID().hashCode();
        }
        if (getPartnerClientKey() != null) {
            _hashCode += getPartnerClientKey().hashCode();
        }
        if (getUserID() != null) {
            _hashCode += getUserID().hashCode();
        }
        if (getPartnerUserKey() != null) {
            _hashCode += getPartnerUserKey().hashCode();
        }
        if (getCreatedBy() != null) {
            _hashCode += getCreatedBy().hashCode();
        }
        if (getModifiedBy() != null) {
            _hashCode += getModifiedBy().hashCode();
        }
        if (getEnterpriseID() != null) {
            _hashCode += getEnterpriseID().hashCode();
        }
        if (getCustomerKey() != null) {
            _hashCode += getCustomerKey().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ClientID.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ClientID"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clientID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ClientID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("partnerClientKey");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PartnerClientKey"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "UserID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("partnerUserKey");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PartnerUserKey"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("createdBy");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "CreatedBy"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modifiedBy");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ModifiedBy"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("enterpriseID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "EnterpriseID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customerKey");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "CustomerKey"));
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
