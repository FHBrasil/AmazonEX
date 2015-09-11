/**
 * CreateRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.flieger.exacttarget.wsdl.api;

public class CreateRequest  implements java.io.Serializable {
    private br.flieger.exacttarget.wsdl.api.CreateOptions options;

    private br.flieger.exacttarget.wsdl.api.APIObject[] objects;

    public CreateRequest() {
    }

    public CreateRequest(
           br.flieger.exacttarget.wsdl.api.CreateOptions options,
           br.flieger.exacttarget.wsdl.api.APIObject[] objects) {
           this.options = options;
           this.objects = objects;
    }


    /**
     * Gets the options value for this CreateRequest.
     * 
     * @return options
     */
    public br.flieger.exacttarget.wsdl.api.CreateOptions getOptions() {
        return options;
    }


    /**
     * Sets the options value for this CreateRequest.
     * 
     * @param options
     */
    public void setOptions(br.flieger.exacttarget.wsdl.api.CreateOptions options) {
        this.options = options;
    }


    /**
     * Gets the objects value for this CreateRequest.
     * 
     * @return objects
     */
    public br.flieger.exacttarget.wsdl.api.APIObject[] getObjects() {
        return objects;
    }


    /**
     * Sets the objects value for this CreateRequest.
     * 
     * @param objects
     */
    public void setObjects(br.flieger.exacttarget.wsdl.api.APIObject[] objects) {
        this.objects = objects;
    }

    public br.flieger.exacttarget.wsdl.api.APIObject getObjects(int i) {
        return this.objects[i];
    }

    public void setObjects(int i, br.flieger.exacttarget.wsdl.api.APIObject _value) {
        this.objects[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CreateRequest)) return false;
        CreateRequest other = (CreateRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.options==null && other.getOptions()==null) || 
             (this.options!=null &&
              this.options.equals(other.getOptions()))) &&
            ((this.objects==null && other.getObjects()==null) || 
             (this.objects!=null &&
              java.util.Arrays.equals(this.objects, other.getObjects())));
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
        if (getOptions() != null) {
            _hashCode += getOptions().hashCode();
        }
        if (getObjects() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getObjects());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getObjects(), i);
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
        new org.apache.axis.description.TypeDesc(CreateRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", ">CreateRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("options");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Options"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "CreateOptions"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("objects");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Objects"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "APIObject"));
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
