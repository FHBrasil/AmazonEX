/**
 * FieldMap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class FieldMap  implements java.io.Serializable {
    private java.lang.String sourceName;

    private java.lang.Integer sourceOrdinal;

    private java.lang.String destinationName;

    public FieldMap() {
    }

    public FieldMap(
           java.lang.String sourceName,
           java.lang.Integer sourceOrdinal,
           java.lang.String destinationName) {
           this.sourceName = sourceName;
           this.sourceOrdinal = sourceOrdinal;
           this.destinationName = destinationName;
    }


    /**
     * Gets the sourceName value for this FieldMap.
     * 
     * @return sourceName
     */
    public java.lang.String getSourceName() {
        return sourceName;
    }


    /**
     * Sets the sourceName value for this FieldMap.
     * 
     * @param sourceName
     */
    public void setSourceName(java.lang.String sourceName) {
        this.sourceName = sourceName;
    }


    /**
     * Gets the sourceOrdinal value for this FieldMap.
     * 
     * @return sourceOrdinal
     */
    public java.lang.Integer getSourceOrdinal() {
        return sourceOrdinal;
    }


    /**
     * Sets the sourceOrdinal value for this FieldMap.
     * 
     * @param sourceOrdinal
     */
    public void setSourceOrdinal(java.lang.Integer sourceOrdinal) {
        this.sourceOrdinal = sourceOrdinal;
    }


    /**
     * Gets the destinationName value for this FieldMap.
     * 
     * @return destinationName
     */
    public java.lang.String getDestinationName() {
        return destinationName;
    }


    /**
     * Sets the destinationName value for this FieldMap.
     * 
     * @param destinationName
     */
    public void setDestinationName(java.lang.String destinationName) {
        this.destinationName = destinationName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FieldMap)) return false;
        FieldMap other = (FieldMap) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.sourceName==null && other.getSourceName()==null) || 
             (this.sourceName!=null &&
              this.sourceName.equals(other.getSourceName()))) &&
            ((this.sourceOrdinal==null && other.getSourceOrdinal()==null) || 
             (this.sourceOrdinal!=null &&
              this.sourceOrdinal.equals(other.getSourceOrdinal()))) &&
            ((this.destinationName==null && other.getDestinationName()==null) || 
             (this.destinationName!=null &&
              this.destinationName.equals(other.getDestinationName())));
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
        if (getSourceName() != null) {
            _hashCode += getSourceName().hashCode();
        }
        if (getSourceOrdinal() != null) {
            _hashCode += getSourceOrdinal().hashCode();
        }
        if (getDestinationName() != null) {
            _hashCode += getDestinationName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FieldMap.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FieldMap"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sourceName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SourceName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sourceOrdinal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SourceOrdinal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("destinationName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DestinationName"));
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
