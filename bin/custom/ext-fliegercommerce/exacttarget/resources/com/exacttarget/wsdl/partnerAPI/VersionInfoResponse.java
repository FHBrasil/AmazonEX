/**
 * VersionInfoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class VersionInfoResponse  implements java.io.Serializable {
    private java.lang.String version;

    private java.util.Calendar versionDate;

    private java.lang.String notes;

    private com.exacttarget.wsdl.partnerAPI.VersionInfoResponse[] versionHistory;

    public VersionInfoResponse() {
    }

    public VersionInfoResponse(
           java.lang.String version,
           java.util.Calendar versionDate,
           java.lang.String notes,
           com.exacttarget.wsdl.partnerAPI.VersionInfoResponse[] versionHistory) {
           this.version = version;
           this.versionDate = versionDate;
           this.notes = notes;
           this.versionHistory = versionHistory;
    }


    /**
     * Gets the version value for this VersionInfoResponse.
     * 
     * @return version
     */
    public java.lang.String getVersion() {
        return version;
    }


    /**
     * Sets the version value for this VersionInfoResponse.
     * 
     * @param version
     */
    public void setVersion(java.lang.String version) {
        this.version = version;
    }


    /**
     * Gets the versionDate value for this VersionInfoResponse.
     * 
     * @return versionDate
     */
    public java.util.Calendar getVersionDate() {
        return versionDate;
    }


    /**
     * Sets the versionDate value for this VersionInfoResponse.
     * 
     * @param versionDate
     */
    public void setVersionDate(java.util.Calendar versionDate) {
        this.versionDate = versionDate;
    }


    /**
     * Gets the notes value for this VersionInfoResponse.
     * 
     * @return notes
     */
    public java.lang.String getNotes() {
        return notes;
    }


    /**
     * Sets the notes value for this VersionInfoResponse.
     * 
     * @param notes
     */
    public void setNotes(java.lang.String notes) {
        this.notes = notes;
    }


    /**
     * Gets the versionHistory value for this VersionInfoResponse.
     * 
     * @return versionHistory
     */
    public com.exacttarget.wsdl.partnerAPI.VersionInfoResponse[] getVersionHistory() {
        return versionHistory;
    }


    /**
     * Sets the versionHistory value for this VersionInfoResponse.
     * 
     * @param versionHistory
     */
    public void setVersionHistory(com.exacttarget.wsdl.partnerAPI.VersionInfoResponse[] versionHistory) {
        this.versionHistory = versionHistory;
    }

    public com.exacttarget.wsdl.partnerAPI.VersionInfoResponse getVersionHistory(int i) {
        return this.versionHistory[i];
    }

    public void setVersionHistory(int i, com.exacttarget.wsdl.partnerAPI.VersionInfoResponse _value) {
        this.versionHistory[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof VersionInfoResponse)) return false;
        VersionInfoResponse other = (VersionInfoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.version==null && other.getVersion()==null) || 
             (this.version!=null &&
              this.version.equals(other.getVersion()))) &&
            ((this.versionDate==null && other.getVersionDate()==null) || 
             (this.versionDate!=null &&
              this.versionDate.equals(other.getVersionDate()))) &&
            ((this.notes==null && other.getNotes()==null) || 
             (this.notes!=null &&
              this.notes.equals(other.getNotes()))) &&
            ((this.versionHistory==null && other.getVersionHistory()==null) || 
             (this.versionHistory!=null &&
              java.util.Arrays.equals(this.versionHistory, other.getVersionHistory())));
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
        if (getVersion() != null) {
            _hashCode += getVersion().hashCode();
        }
        if (getVersionDate() != null) {
            _hashCode += getVersionDate().hashCode();
        }
        if (getNotes() != null) {
            _hashCode += getNotes().hashCode();
        }
        if (getVersionHistory() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getVersionHistory());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getVersionHistory(), i);
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
        new org.apache.axis.description.TypeDesc(VersionInfoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "VersionInfoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("version");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Version"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("versionDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "VersionDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("notes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Notes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("versionHistory");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "VersionHistory"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "VersionInfoResponse"));
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
