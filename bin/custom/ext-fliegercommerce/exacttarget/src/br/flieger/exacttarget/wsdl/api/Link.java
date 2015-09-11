/**
 * Link.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.flieger.exacttarget.wsdl.api;

public class Link  extends br.flieger.exacttarget.wsdl.api.APIObject  implements java.io.Serializable {
    private java.util.Calendar lastClicked;

    private java.lang.String alias;

    private java.lang.Integer totalClicks;

    private java.lang.Integer uniqueClicks;

    private java.lang.String URL;

    private br.flieger.exacttarget.wsdl.api.TrackingEvent[] subscribers;

    public Link() {
    }

    public Link(
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
           java.util.Calendar lastClicked,
           java.lang.String alias,
           java.lang.Integer totalClicks,
           java.lang.Integer uniqueClicks,
           java.lang.String URL,
           br.flieger.exacttarget.wsdl.api.TrackingEvent[] subscribers) {
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
        this.lastClicked = lastClicked;
        this.alias = alias;
        this.totalClicks = totalClicks;
        this.uniqueClicks = uniqueClicks;
        this.URL = URL;
        this.subscribers = subscribers;
    }


    /**
     * Gets the lastClicked value for this Link.
     * 
     * @return lastClicked
     */
    public java.util.Calendar getLastClicked() {
        return lastClicked;
    }


    /**
     * Sets the lastClicked value for this Link.
     * 
     * @param lastClicked
     */
    public void setLastClicked(java.util.Calendar lastClicked) {
        this.lastClicked = lastClicked;
    }


    /**
     * Gets the alias value for this Link.
     * 
     * @return alias
     */
    public java.lang.String getAlias() {
        return alias;
    }


    /**
     * Sets the alias value for this Link.
     * 
     * @param alias
     */
    public void setAlias(java.lang.String alias) {
        this.alias = alias;
    }


    /**
     * Gets the totalClicks value for this Link.
     * 
     * @return totalClicks
     */
    public java.lang.Integer getTotalClicks() {
        return totalClicks;
    }


    /**
     * Sets the totalClicks value for this Link.
     * 
     * @param totalClicks
     */
    public void setTotalClicks(java.lang.Integer totalClicks) {
        this.totalClicks = totalClicks;
    }


    /**
     * Gets the uniqueClicks value for this Link.
     * 
     * @return uniqueClicks
     */
    public java.lang.Integer getUniqueClicks() {
        return uniqueClicks;
    }


    /**
     * Sets the uniqueClicks value for this Link.
     * 
     * @param uniqueClicks
     */
    public void setUniqueClicks(java.lang.Integer uniqueClicks) {
        this.uniqueClicks = uniqueClicks;
    }


    /**
     * Gets the URL value for this Link.
     * 
     * @return URL
     */
    public java.lang.String getURL() {
        return URL;
    }


    /**
     * Sets the URL value for this Link.
     * 
     * @param URL
     */
    public void setURL(java.lang.String URL) {
        this.URL = URL;
    }


    /**
     * Gets the subscribers value for this Link.
     * 
     * @return subscribers
     */
    public br.flieger.exacttarget.wsdl.api.TrackingEvent[] getSubscribers() {
        return subscribers;
    }


    /**
     * Sets the subscribers value for this Link.
     * 
     * @param subscribers
     */
    public void setSubscribers(br.flieger.exacttarget.wsdl.api.TrackingEvent[] subscribers) {
        this.subscribers = subscribers;
    }

    public br.flieger.exacttarget.wsdl.api.TrackingEvent getSubscribers(int i) {
        return this.subscribers[i];
    }

    public void setSubscribers(int i, br.flieger.exacttarget.wsdl.api.TrackingEvent _value) {
        this.subscribers[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Link)) return false;
        Link other = (Link) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.lastClicked==null && other.getLastClicked()==null) || 
             (this.lastClicked!=null &&
              this.lastClicked.equals(other.getLastClicked()))) &&
            ((this.alias==null && other.getAlias()==null) || 
             (this.alias!=null &&
              this.alias.equals(other.getAlias()))) &&
            ((this.totalClicks==null && other.getTotalClicks()==null) || 
             (this.totalClicks!=null &&
              this.totalClicks.equals(other.getTotalClicks()))) &&
            ((this.uniqueClicks==null && other.getUniqueClicks()==null) || 
             (this.uniqueClicks!=null &&
              this.uniqueClicks.equals(other.getUniqueClicks()))) &&
            ((this.URL==null && other.getURL()==null) || 
             (this.URL!=null &&
              this.URL.equals(other.getURL()))) &&
            ((this.subscribers==null && other.getSubscribers()==null) || 
             (this.subscribers!=null &&
              java.util.Arrays.equals(this.subscribers, other.getSubscribers())));
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
        if (getLastClicked() != null) {
            _hashCode += getLastClicked().hashCode();
        }
        if (getAlias() != null) {
            _hashCode += getAlias().hashCode();
        }
        if (getTotalClicks() != null) {
            _hashCode += getTotalClicks().hashCode();
        }
        if (getUniqueClicks() != null) {
            _hashCode += getUniqueClicks().hashCode();
        }
        if (getURL() != null) {
            _hashCode += getURL().hashCode();
        }
        if (getSubscribers() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSubscribers());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSubscribers(), i);
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
        new org.apache.axis.description.TypeDesc(Link.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Link"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastClicked");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "LastClicked"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("alias");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Alias"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalClicks");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TotalClicks"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("uniqueClicks");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "UniqueClicks"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("URL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "URL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subscribers");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Subscribers"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TrackingEvent"));
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
