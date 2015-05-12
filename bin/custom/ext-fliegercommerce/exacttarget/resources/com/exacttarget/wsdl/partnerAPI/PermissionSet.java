/**
 * PermissionSet.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class PermissionSet  extends com.exacttarget.wsdl.partnerAPI.APIObject  implements java.io.Serializable {
    private java.lang.String name;

    private java.lang.String description;

    private java.lang.Boolean isAllowed;

    private java.lang.Boolean isDenied;

    private com.exacttarget.wsdl.partnerAPI.PermissionSet[] permissionSets;

    private com.exacttarget.wsdl.partnerAPI.Permission[] permissions;

    public PermissionSet() {
    }

    public PermissionSet(
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
           java.lang.String description,
           java.lang.Boolean isAllowed,
           java.lang.Boolean isDenied,
           com.exacttarget.wsdl.partnerAPI.PermissionSet[] permissionSets,
           com.exacttarget.wsdl.partnerAPI.Permission[] permissions) {
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
        this.description = description;
        this.isAllowed = isAllowed;
        this.isDenied = isDenied;
        this.permissionSets = permissionSets;
        this.permissions = permissions;
    }


    /**
     * Gets the name value for this PermissionSet.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this PermissionSet.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the description value for this PermissionSet.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this PermissionSet.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the isAllowed value for this PermissionSet.
     * 
     * @return isAllowed
     */
    public java.lang.Boolean getIsAllowed() {
        return isAllowed;
    }


    /**
     * Sets the isAllowed value for this PermissionSet.
     * 
     * @param isAllowed
     */
    public void setIsAllowed(java.lang.Boolean isAllowed) {
        this.isAllowed = isAllowed;
    }


    /**
     * Gets the isDenied value for this PermissionSet.
     * 
     * @return isDenied
     */
    public java.lang.Boolean getIsDenied() {
        return isDenied;
    }


    /**
     * Sets the isDenied value for this PermissionSet.
     * 
     * @param isDenied
     */
    public void setIsDenied(java.lang.Boolean isDenied) {
        this.isDenied = isDenied;
    }


    /**
     * Gets the permissionSets value for this PermissionSet.
     * 
     * @return permissionSets
     */
    public com.exacttarget.wsdl.partnerAPI.PermissionSet[] getPermissionSets() {
        return permissionSets;
    }


    /**
     * Sets the permissionSets value for this PermissionSet.
     * 
     * @param permissionSets
     */
    public void setPermissionSets(com.exacttarget.wsdl.partnerAPI.PermissionSet[] permissionSets) {
        this.permissionSets = permissionSets;
    }


    /**
     * Gets the permissions value for this PermissionSet.
     * 
     * @return permissions
     */
    public com.exacttarget.wsdl.partnerAPI.Permission[] getPermissions() {
        return permissions;
    }


    /**
     * Sets the permissions value for this PermissionSet.
     * 
     * @param permissions
     */
    public void setPermissions(com.exacttarget.wsdl.partnerAPI.Permission[] permissions) {
        this.permissions = permissions;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PermissionSet)) return false;
        PermissionSet other = (PermissionSet) obj;
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
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.isAllowed==null && other.getIsAllowed()==null) || 
             (this.isAllowed!=null &&
              this.isAllowed.equals(other.getIsAllowed()))) &&
            ((this.isDenied==null && other.getIsDenied()==null) || 
             (this.isDenied!=null &&
              this.isDenied.equals(other.getIsDenied()))) &&
            ((this.permissionSets==null && other.getPermissionSets()==null) || 
             (this.permissionSets!=null &&
              java.util.Arrays.equals(this.permissionSets, other.getPermissionSets()))) &&
            ((this.permissions==null && other.getPermissions()==null) || 
             (this.permissions!=null &&
              java.util.Arrays.equals(this.permissions, other.getPermissions())));
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
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getIsAllowed() != null) {
            _hashCode += getIsAllowed().hashCode();
        }
        if (getIsDenied() != null) {
            _hashCode += getIsDenied().hashCode();
        }
        if (getPermissionSets() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPermissionSets());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPermissionSets(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPermissions() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPermissions());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPermissions(), i);
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
        new org.apache.axis.description.TypeDesc(PermissionSet.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PermissionSet"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setFieldName("isAllowed");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsAllowed"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isDenied");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsDenied"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("permissionSets");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PermissionSets"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PermissionSet"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PermissionSet"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("permissions");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Permissions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Permission"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Permission"));
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
