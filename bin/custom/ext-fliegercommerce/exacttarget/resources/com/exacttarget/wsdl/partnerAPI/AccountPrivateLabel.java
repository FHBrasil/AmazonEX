/**
 * AccountPrivateLabel.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class AccountPrivateLabel  extends com.exacttarget.wsdl.partnerAPI.APIObject  implements java.io.Serializable {
    private java.lang.String name;

    private int ownerMemberID;

    private java.lang.String colorPaletteXML;

    public AccountPrivateLabel() {
    }

    public AccountPrivateLabel(
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
           int ownerMemberID,
           java.lang.String colorPaletteXML) {
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
        this.ownerMemberID = ownerMemberID;
        this.colorPaletteXML = colorPaletteXML;
    }


    /**
     * Gets the name value for this AccountPrivateLabel.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this AccountPrivateLabel.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the ownerMemberID value for this AccountPrivateLabel.
     * 
     * @return ownerMemberID
     */
    public int getOwnerMemberID() {
        return ownerMemberID;
    }


    /**
     * Sets the ownerMemberID value for this AccountPrivateLabel.
     * 
     * @param ownerMemberID
     */
    public void setOwnerMemberID(int ownerMemberID) {
        this.ownerMemberID = ownerMemberID;
    }


    /**
     * Gets the colorPaletteXML value for this AccountPrivateLabel.
     * 
     * @return colorPaletteXML
     */
    public java.lang.String getColorPaletteXML() {
        return colorPaletteXML;
    }


    /**
     * Sets the colorPaletteXML value for this AccountPrivateLabel.
     * 
     * @param colorPaletteXML
     */
    public void setColorPaletteXML(java.lang.String colorPaletteXML) {
        this.colorPaletteXML = colorPaletteXML;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AccountPrivateLabel)) return false;
        AccountPrivateLabel other = (AccountPrivateLabel) obj;
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
            this.ownerMemberID == other.getOwnerMemberID() &&
            ((this.colorPaletteXML==null && other.getColorPaletteXML()==null) || 
             (this.colorPaletteXML!=null &&
              this.colorPaletteXML.equals(other.getColorPaletteXML())));
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
        _hashCode += getOwnerMemberID();
        if (getColorPaletteXML() != null) {
            _hashCode += getColorPaletteXML().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AccountPrivateLabel.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AccountPrivateLabel"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ownerMemberID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "OwnerMemberID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("colorPaletteXML");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ColorPaletteXML"));
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
