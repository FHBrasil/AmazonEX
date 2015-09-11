/**
 * ComplexFilterPart.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class ComplexFilterPart  extends com.exacttarget.wsdl.partnerAPI.FilterPart  implements java.io.Serializable {
    private com.exacttarget.wsdl.partnerAPI.FilterPart leftOperand;

    private com.exacttarget.wsdl.partnerAPI.LogicalOperators logicalOperator;

    private com.exacttarget.wsdl.partnerAPI.FilterPart rightOperand;

    private com.exacttarget.wsdl.partnerAPI.FilterPart[] additionalOperands;

    public ComplexFilterPart() {
    }

    public ComplexFilterPart(
           com.exacttarget.wsdl.partnerAPI.FilterPart leftOperand,
           com.exacttarget.wsdl.partnerAPI.LogicalOperators logicalOperator,
           com.exacttarget.wsdl.partnerAPI.FilterPart rightOperand,
           com.exacttarget.wsdl.partnerAPI.FilterPart[] additionalOperands) {
        this.leftOperand = leftOperand;
        this.logicalOperator = logicalOperator;
        this.rightOperand = rightOperand;
        this.additionalOperands = additionalOperands;
    }


    /**
     * Gets the leftOperand value for this ComplexFilterPart.
     * 
     * @return leftOperand
     */
    public com.exacttarget.wsdl.partnerAPI.FilterPart getLeftOperand() {
        return leftOperand;
    }


    /**
     * Sets the leftOperand value for this ComplexFilterPart.
     * 
     * @param leftOperand
     */
    public void setLeftOperand(com.exacttarget.wsdl.partnerAPI.FilterPart leftOperand) {
        this.leftOperand = leftOperand;
    }


    /**
     * Gets the logicalOperator value for this ComplexFilterPart.
     * 
     * @return logicalOperator
     */
    public com.exacttarget.wsdl.partnerAPI.LogicalOperators getLogicalOperator() {
        return logicalOperator;
    }


    /**
     * Sets the logicalOperator value for this ComplexFilterPart.
     * 
     * @param logicalOperator
     */
    public void setLogicalOperator(com.exacttarget.wsdl.partnerAPI.LogicalOperators logicalOperator) {
        this.logicalOperator = logicalOperator;
    }


    /**
     * Gets the rightOperand value for this ComplexFilterPart.
     * 
     * @return rightOperand
     */
    public com.exacttarget.wsdl.partnerAPI.FilterPart getRightOperand() {
        return rightOperand;
    }


    /**
     * Sets the rightOperand value for this ComplexFilterPart.
     * 
     * @param rightOperand
     */
    public void setRightOperand(com.exacttarget.wsdl.partnerAPI.FilterPart rightOperand) {
        this.rightOperand = rightOperand;
    }


    /**
     * Gets the additionalOperands value for this ComplexFilterPart.
     * 
     * @return additionalOperands
     */
    public com.exacttarget.wsdl.partnerAPI.FilterPart[] getAdditionalOperands() {
        return additionalOperands;
    }


    /**
     * Sets the additionalOperands value for this ComplexFilterPart.
     * 
     * @param additionalOperands
     */
    public void setAdditionalOperands(com.exacttarget.wsdl.partnerAPI.FilterPart[] additionalOperands) {
        this.additionalOperands = additionalOperands;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ComplexFilterPart)) return false;
        ComplexFilterPart other = (ComplexFilterPart) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.leftOperand==null && other.getLeftOperand()==null) || 
             (this.leftOperand!=null &&
              this.leftOperand.equals(other.getLeftOperand()))) &&
            ((this.logicalOperator==null && other.getLogicalOperator()==null) || 
             (this.logicalOperator!=null &&
              this.logicalOperator.equals(other.getLogicalOperator()))) &&
            ((this.rightOperand==null && other.getRightOperand()==null) || 
             (this.rightOperand!=null &&
              this.rightOperand.equals(other.getRightOperand()))) &&
            ((this.additionalOperands==null && other.getAdditionalOperands()==null) || 
             (this.additionalOperands!=null &&
              java.util.Arrays.equals(this.additionalOperands, other.getAdditionalOperands())));
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
        if (getLeftOperand() != null) {
            _hashCode += getLeftOperand().hashCode();
        }
        if (getLogicalOperator() != null) {
            _hashCode += getLogicalOperator().hashCode();
        }
        if (getRightOperand() != null) {
            _hashCode += getRightOperand().hashCode();
        }
        if (getAdditionalOperands() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAdditionalOperands());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAdditionalOperands(), i);
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
        new org.apache.axis.description.TypeDesc(ComplexFilterPart.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ComplexFilterPart"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("leftOperand");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "LeftOperand"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FilterPart"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("logicalOperator");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "LogicalOperator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "LogicalOperators"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rightOperand");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RightOperand"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FilterPart"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("additionalOperands");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AdditionalOperands"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FilterPart"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Operand"));
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
