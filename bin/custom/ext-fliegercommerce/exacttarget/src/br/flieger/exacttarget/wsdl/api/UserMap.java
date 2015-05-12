/**
 * UserMap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.flieger.exacttarget.wsdl.api;

public class UserMap  extends br.flieger.exacttarget.wsdl.api.APIProperty  implements java.io.Serializable {
    private br.flieger.exacttarget.wsdl.api.AccountUser ETAccountUser;

    private br.flieger.exacttarget.wsdl.api.APIProperty[] additionalData;

    public UserMap() {
    }

    public UserMap(
           java.lang.String name,
           java.lang.String value,
           br.flieger.exacttarget.wsdl.api.AccountUser ETAccountUser,
           br.flieger.exacttarget.wsdl.api.APIProperty[] additionalData) {
        super(
            name,
            value);
        this.ETAccountUser = ETAccountUser;
        this.additionalData = additionalData;
    }


    /**
     * Gets the ETAccountUser value for this UserMap.
     * 
     * @return ETAccountUser
     */
    public br.flieger.exacttarget.wsdl.api.AccountUser getETAccountUser() {
        return ETAccountUser;
    }


    /**
     * Sets the ETAccountUser value for this UserMap.
     * 
     * @param ETAccountUser
     */
    public void setETAccountUser(br.flieger.exacttarget.wsdl.api.AccountUser ETAccountUser) {
        this.ETAccountUser = ETAccountUser;
    }


    /**
     * Gets the additionalData value for this UserMap.
     * 
     * @return additionalData
     */
    public br.flieger.exacttarget.wsdl.api.APIProperty[] getAdditionalData() {
        return additionalData;
    }


    /**
     * Sets the additionalData value for this UserMap.
     * 
     * @param additionalData
     */
    public void setAdditionalData(br.flieger.exacttarget.wsdl.api.APIProperty[] additionalData) {
        this.additionalData = additionalData;
    }

    public br.flieger.exacttarget.wsdl.api.APIProperty getAdditionalData(int i) {
        return this.additionalData[i];
    }

    public void setAdditionalData(int i, br.flieger.exacttarget.wsdl.api.APIProperty _value) {
        this.additionalData[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UserMap)) return false;
        UserMap other = (UserMap) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.ETAccountUser==null && other.getETAccountUser()==null) || 
             (this.ETAccountUser!=null &&
              this.ETAccountUser.equals(other.getETAccountUser()))) &&
            ((this.additionalData==null && other.getAdditionalData()==null) || 
             (this.additionalData!=null &&
              java.util.Arrays.equals(this.additionalData, other.getAdditionalData())));
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
        if (getETAccountUser() != null) {
            _hashCode += getETAccountUser().hashCode();
        }
        if (getAdditionalData() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAdditionalData());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAdditionalData(), i);
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
        new org.apache.axis.description.TypeDesc(UserMap.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "UserMap"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ETAccountUser");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ETAccountUser"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AccountUser"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("additionalData");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AdditionalData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "APIProperty"));
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
