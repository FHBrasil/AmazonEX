/**
 * PrivateLabel.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class PrivateLabel  implements java.io.Serializable {
    private java.lang.Integer ID;

    private java.lang.String name;

    private java.lang.String colorPaletteXML;

    private java.lang.String logoFile;

    private int delete;

    private java.lang.Boolean setActive;

    public PrivateLabel() {
    }

    public PrivateLabel(
           java.lang.Integer ID,
           java.lang.String name,
           java.lang.String colorPaletteXML,
           java.lang.String logoFile,
           int delete,
           java.lang.Boolean setActive) {
           this.ID = ID;
           this.name = name;
           this.colorPaletteXML = colorPaletteXML;
           this.logoFile = logoFile;
           this.delete = delete;
           this.setActive = setActive;
    }


    /**
     * Gets the ID value for this PrivateLabel.
     * 
     * @return ID
     */
    public java.lang.Integer getID() {
        return ID;
    }


    /**
     * Sets the ID value for this PrivateLabel.
     * 
     * @param ID
     */
    public void setID(java.lang.Integer ID) {
        this.ID = ID;
    }


    /**
     * Gets the name value for this PrivateLabel.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this PrivateLabel.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the colorPaletteXML value for this PrivateLabel.
     * 
     * @return colorPaletteXML
     */
    public java.lang.String getColorPaletteXML() {
        return colorPaletteXML;
    }


    /**
     * Sets the colorPaletteXML value for this PrivateLabel.
     * 
     * @param colorPaletteXML
     */
    public void setColorPaletteXML(java.lang.String colorPaletteXML) {
        this.colorPaletteXML = colorPaletteXML;
    }


    /**
     * Gets the logoFile value for this PrivateLabel.
     * 
     * @return logoFile
     */
    public java.lang.String getLogoFile() {
        return logoFile;
    }


    /**
     * Sets the logoFile value for this PrivateLabel.
     * 
     * @param logoFile
     */
    public void setLogoFile(java.lang.String logoFile) {
        this.logoFile = logoFile;
    }


    /**
     * Gets the delete value for this PrivateLabel.
     * 
     * @return delete
     */
    public int getDelete() {
        return delete;
    }


    /**
     * Sets the delete value for this PrivateLabel.
     * 
     * @param delete
     */
    public void setDelete(int delete) {
        this.delete = delete;
    }


    /**
     * Gets the setActive value for this PrivateLabel.
     * 
     * @return setActive
     */
    public java.lang.Boolean getSetActive() {
        return setActive;
    }


    /**
     * Sets the setActive value for this PrivateLabel.
     * 
     * @param setActive
     */
    public void setSetActive(java.lang.Boolean setActive) {
        this.setActive = setActive;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PrivateLabel)) return false;
        PrivateLabel other = (PrivateLabel) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ID==null && other.getID()==null) || 
             (this.ID!=null &&
              this.ID.equals(other.getID()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.colorPaletteXML==null && other.getColorPaletteXML()==null) || 
             (this.colorPaletteXML!=null &&
              this.colorPaletteXML.equals(other.getColorPaletteXML()))) &&
            ((this.logoFile==null && other.getLogoFile()==null) || 
             (this.logoFile!=null &&
              this.logoFile.equals(other.getLogoFile()))) &&
            this.delete == other.getDelete() &&
            ((this.setActive==null && other.getSetActive()==null) || 
             (this.setActive!=null &&
              this.setActive.equals(other.getSetActive())));
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
        if (getID() != null) {
            _hashCode += getID().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getColorPaletteXML() != null) {
            _hashCode += getColorPaletteXML().hashCode();
        }
        if (getLogoFile() != null) {
            _hashCode += getLogoFile().hashCode();
        }
        _hashCode += getDelete();
        if (getSetActive() != null) {
            _hashCode += getSetActive().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PrivateLabel.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PrivateLabel"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("colorPaletteXML");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ColorPaletteXML"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("logoFile");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "LogoFile"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("delete");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Delete"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("setActive");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SetActive"));
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
