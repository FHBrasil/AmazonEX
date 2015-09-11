/**
 * ListSubscriber.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.flieger.exacttarget.wsdl.api;

public class ListSubscriber  extends br.flieger.exacttarget.wsdl.api.APIObject  implements java.io.Serializable {
    private br.flieger.exacttarget.wsdl.api.SubscriberStatus status;

    private java.lang.Integer listID;

    private java.lang.String subscriberKey;

    public ListSubscriber() {
    }

    public ListSubscriber(
           br.flieger.exacttarget.wsdl.api.ClientID client,
           java.lang.String partnerKey,
           br.flieger.exacttarget.wsdl.api.APIProperty[] partnerProperties,
           java.util.Calendar createdDate,
           java.util.Calendar modifiedDate,
           java.lang.Integer ID,
           java.lang.String objectID,
           java.lang.String customerKey,
           br.flieger.exacttarget.wsdl.api.Owner owner,
           java.lang.String correlationID,
           java.lang.String objectState,
           br.flieger.exacttarget.wsdl.api.SubscriberStatus status,
           java.lang.Integer listID,
           java.lang.String subscriberKey) {
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
        this.status = status;
        this.listID = listID;
        this.subscriberKey = subscriberKey;
    }


    /**
     * Gets the status value for this ListSubscriber.
     * 
     * @return status
     */
    public br.flieger.exacttarget.wsdl.api.SubscriberStatus getStatus() {
        return status;
    }


    /**
     * Sets the status value for this ListSubscriber.
     * 
     * @param status
     */
    public void setStatus(br.flieger.exacttarget.wsdl.api.SubscriberStatus status) {
        this.status = status;
    }


    /**
     * Gets the listID value for this ListSubscriber.
     * 
     * @return listID
     */
    public java.lang.Integer getListID() {
        return listID;
    }


    /**
     * Sets the listID value for this ListSubscriber.
     * 
     * @param listID
     */
    public void setListID(java.lang.Integer listID) {
        this.listID = listID;
    }


    /**
     * Gets the subscriberKey value for this ListSubscriber.
     * 
     * @return subscriberKey
     */
    public java.lang.String getSubscriberKey() {
        return subscriberKey;
    }


    /**
     * Sets the subscriberKey value for this ListSubscriber.
     * 
     * @param subscriberKey
     */
    public void setSubscriberKey(java.lang.String subscriberKey) {
        this.subscriberKey = subscriberKey;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListSubscriber)) return false;
        ListSubscriber other = (ListSubscriber) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.listID==null && other.getListID()==null) || 
             (this.listID!=null &&
              this.listID.equals(other.getListID()))) &&
            ((this.subscriberKey==null && other.getSubscriberKey()==null) || 
             (this.subscriberKey!=null &&
              this.subscriberKey.equals(other.getSubscriberKey())));
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
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getListID() != null) {
            _hashCode += getListID().hashCode();
        }
        if (getSubscriberKey() != null) {
            _hashCode += getSubscriberKey().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ListSubscriber.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ListSubscriber"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SubscriberStatus"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ListID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subscriberKey");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SubscriberKey"));
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
