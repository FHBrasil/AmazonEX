/**
 * ListSend.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class ListSend  extends com.exacttarget.wsdl.partnerAPI.APIObject  implements java.io.Serializable {
    private java.lang.Integer sendID;

    private com.exacttarget.wsdl.partnerAPI.List list;

    private java.lang.Integer duplicates;

    private java.lang.Integer invalidAddresses;

    private java.lang.Integer existingUndeliverables;

    private java.lang.Integer existingUnsubscribes;

    private java.lang.Integer hardBounces;

    private java.lang.Integer softBounces;

    private java.lang.Integer otherBounces;

    private java.lang.Integer forwardedEmails;

    private java.lang.Integer uniqueClicks;

    private java.lang.Integer uniqueOpens;

    private java.lang.Integer numberSent;

    private java.lang.Integer numberDelivered;

    private java.lang.Integer unsubscribes;

    private java.lang.Integer missingAddresses;

    private java.lang.String previewURL;

    private com.exacttarget.wsdl.partnerAPI.Link[] links;

    private com.exacttarget.wsdl.partnerAPI.TrackingEvent[] events;

    public ListSend() {
    }

    public ListSend(
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
           java.lang.Integer sendID,
           com.exacttarget.wsdl.partnerAPI.List list,
           java.lang.Integer duplicates,
           java.lang.Integer invalidAddresses,
           java.lang.Integer existingUndeliverables,
           java.lang.Integer existingUnsubscribes,
           java.lang.Integer hardBounces,
           java.lang.Integer softBounces,
           java.lang.Integer otherBounces,
           java.lang.Integer forwardedEmails,
           java.lang.Integer uniqueClicks,
           java.lang.Integer uniqueOpens,
           java.lang.Integer numberSent,
           java.lang.Integer numberDelivered,
           java.lang.Integer unsubscribes,
           java.lang.Integer missingAddresses,
           java.lang.String previewURL,
           com.exacttarget.wsdl.partnerAPI.Link[] links,
           com.exacttarget.wsdl.partnerAPI.TrackingEvent[] events) {
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
        this.sendID = sendID;
        this.list = list;
        this.duplicates = duplicates;
        this.invalidAddresses = invalidAddresses;
        this.existingUndeliverables = existingUndeliverables;
        this.existingUnsubscribes = existingUnsubscribes;
        this.hardBounces = hardBounces;
        this.softBounces = softBounces;
        this.otherBounces = otherBounces;
        this.forwardedEmails = forwardedEmails;
        this.uniqueClicks = uniqueClicks;
        this.uniqueOpens = uniqueOpens;
        this.numberSent = numberSent;
        this.numberDelivered = numberDelivered;
        this.unsubscribes = unsubscribes;
        this.missingAddresses = missingAddresses;
        this.previewURL = previewURL;
        this.links = links;
        this.events = events;
    }


    /**
     * Gets the sendID value for this ListSend.
     * 
     * @return sendID
     */
    public java.lang.Integer getSendID() {
        return sendID;
    }


    /**
     * Sets the sendID value for this ListSend.
     * 
     * @param sendID
     */
    public void setSendID(java.lang.Integer sendID) {
        this.sendID = sendID;
    }


    /**
     * Gets the list value for this ListSend.
     * 
     * @return list
     */
    public com.exacttarget.wsdl.partnerAPI.List getList() {
        return list;
    }


    /**
     * Sets the list value for this ListSend.
     * 
     * @param list
     */
    public void setList(com.exacttarget.wsdl.partnerAPI.List list) {
        this.list = list;
    }


    /**
     * Gets the duplicates value for this ListSend.
     * 
     * @return duplicates
     */
    public java.lang.Integer getDuplicates() {
        return duplicates;
    }


    /**
     * Sets the duplicates value for this ListSend.
     * 
     * @param duplicates
     */
    public void setDuplicates(java.lang.Integer duplicates) {
        this.duplicates = duplicates;
    }


    /**
     * Gets the invalidAddresses value for this ListSend.
     * 
     * @return invalidAddresses
     */
    public java.lang.Integer getInvalidAddresses() {
        return invalidAddresses;
    }


    /**
     * Sets the invalidAddresses value for this ListSend.
     * 
     * @param invalidAddresses
     */
    public void setInvalidAddresses(java.lang.Integer invalidAddresses) {
        this.invalidAddresses = invalidAddresses;
    }


    /**
     * Gets the existingUndeliverables value for this ListSend.
     * 
     * @return existingUndeliverables
     */
    public java.lang.Integer getExistingUndeliverables() {
        return existingUndeliverables;
    }


    /**
     * Sets the existingUndeliverables value for this ListSend.
     * 
     * @param existingUndeliverables
     */
    public void setExistingUndeliverables(java.lang.Integer existingUndeliverables) {
        this.existingUndeliverables = existingUndeliverables;
    }


    /**
     * Gets the existingUnsubscribes value for this ListSend.
     * 
     * @return existingUnsubscribes
     */
    public java.lang.Integer getExistingUnsubscribes() {
        return existingUnsubscribes;
    }


    /**
     * Sets the existingUnsubscribes value for this ListSend.
     * 
     * @param existingUnsubscribes
     */
    public void setExistingUnsubscribes(java.lang.Integer existingUnsubscribes) {
        this.existingUnsubscribes = existingUnsubscribes;
    }


    /**
     * Gets the hardBounces value for this ListSend.
     * 
     * @return hardBounces
     */
    public java.lang.Integer getHardBounces() {
        return hardBounces;
    }


    /**
     * Sets the hardBounces value for this ListSend.
     * 
     * @param hardBounces
     */
    public void setHardBounces(java.lang.Integer hardBounces) {
        this.hardBounces = hardBounces;
    }


    /**
     * Gets the softBounces value for this ListSend.
     * 
     * @return softBounces
     */
    public java.lang.Integer getSoftBounces() {
        return softBounces;
    }


    /**
     * Sets the softBounces value for this ListSend.
     * 
     * @param softBounces
     */
    public void setSoftBounces(java.lang.Integer softBounces) {
        this.softBounces = softBounces;
    }


    /**
     * Gets the otherBounces value for this ListSend.
     * 
     * @return otherBounces
     */
    public java.lang.Integer getOtherBounces() {
        return otherBounces;
    }


    /**
     * Sets the otherBounces value for this ListSend.
     * 
     * @param otherBounces
     */
    public void setOtherBounces(java.lang.Integer otherBounces) {
        this.otherBounces = otherBounces;
    }


    /**
     * Gets the forwardedEmails value for this ListSend.
     * 
     * @return forwardedEmails
     */
    public java.lang.Integer getForwardedEmails() {
        return forwardedEmails;
    }


    /**
     * Sets the forwardedEmails value for this ListSend.
     * 
     * @param forwardedEmails
     */
    public void setForwardedEmails(java.lang.Integer forwardedEmails) {
        this.forwardedEmails = forwardedEmails;
    }


    /**
     * Gets the uniqueClicks value for this ListSend.
     * 
     * @return uniqueClicks
     */
    public java.lang.Integer getUniqueClicks() {
        return uniqueClicks;
    }


    /**
     * Sets the uniqueClicks value for this ListSend.
     * 
     * @param uniqueClicks
     */
    public void setUniqueClicks(java.lang.Integer uniqueClicks) {
        this.uniqueClicks = uniqueClicks;
    }


    /**
     * Gets the uniqueOpens value for this ListSend.
     * 
     * @return uniqueOpens
     */
    public java.lang.Integer getUniqueOpens() {
        return uniqueOpens;
    }


    /**
     * Sets the uniqueOpens value for this ListSend.
     * 
     * @param uniqueOpens
     */
    public void setUniqueOpens(java.lang.Integer uniqueOpens) {
        this.uniqueOpens = uniqueOpens;
    }


    /**
     * Gets the numberSent value for this ListSend.
     * 
     * @return numberSent
     */
    public java.lang.Integer getNumberSent() {
        return numberSent;
    }


    /**
     * Sets the numberSent value for this ListSend.
     * 
     * @param numberSent
     */
    public void setNumberSent(java.lang.Integer numberSent) {
        this.numberSent = numberSent;
    }


    /**
     * Gets the numberDelivered value for this ListSend.
     * 
     * @return numberDelivered
     */
    public java.lang.Integer getNumberDelivered() {
        return numberDelivered;
    }


    /**
     * Sets the numberDelivered value for this ListSend.
     * 
     * @param numberDelivered
     */
    public void setNumberDelivered(java.lang.Integer numberDelivered) {
        this.numberDelivered = numberDelivered;
    }


    /**
     * Gets the unsubscribes value for this ListSend.
     * 
     * @return unsubscribes
     */
    public java.lang.Integer getUnsubscribes() {
        return unsubscribes;
    }


    /**
     * Sets the unsubscribes value for this ListSend.
     * 
     * @param unsubscribes
     */
    public void setUnsubscribes(java.lang.Integer unsubscribes) {
        this.unsubscribes = unsubscribes;
    }


    /**
     * Gets the missingAddresses value for this ListSend.
     * 
     * @return missingAddresses
     */
    public java.lang.Integer getMissingAddresses() {
        return missingAddresses;
    }


    /**
     * Sets the missingAddresses value for this ListSend.
     * 
     * @param missingAddresses
     */
    public void setMissingAddresses(java.lang.Integer missingAddresses) {
        this.missingAddresses = missingAddresses;
    }


    /**
     * Gets the previewURL value for this ListSend.
     * 
     * @return previewURL
     */
    public java.lang.String getPreviewURL() {
        return previewURL;
    }


    /**
     * Sets the previewURL value for this ListSend.
     * 
     * @param previewURL
     */
    public void setPreviewURL(java.lang.String previewURL) {
        this.previewURL = previewURL;
    }


    /**
     * Gets the links value for this ListSend.
     * 
     * @return links
     */
    public com.exacttarget.wsdl.partnerAPI.Link[] getLinks() {
        return links;
    }


    /**
     * Sets the links value for this ListSend.
     * 
     * @param links
     */
    public void setLinks(com.exacttarget.wsdl.partnerAPI.Link[] links) {
        this.links = links;
    }

    public com.exacttarget.wsdl.partnerAPI.Link getLinks(int i) {
        return this.links[i];
    }

    public void setLinks(int i, com.exacttarget.wsdl.partnerAPI.Link _value) {
        this.links[i] = _value;
    }


    /**
     * Gets the events value for this ListSend.
     * 
     * @return events
     */
    public com.exacttarget.wsdl.partnerAPI.TrackingEvent[] getEvents() {
        return events;
    }


    /**
     * Sets the events value for this ListSend.
     * 
     * @param events
     */
    public void setEvents(com.exacttarget.wsdl.partnerAPI.TrackingEvent[] events) {
        this.events = events;
    }

    public com.exacttarget.wsdl.partnerAPI.TrackingEvent getEvents(int i) {
        return this.events[i];
    }

    public void setEvents(int i, com.exacttarget.wsdl.partnerAPI.TrackingEvent _value) {
        this.events[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListSend)) return false;
        ListSend other = (ListSend) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.sendID==null && other.getSendID()==null) || 
             (this.sendID!=null &&
              this.sendID.equals(other.getSendID()))) &&
            ((this.list==null && other.getList()==null) || 
             (this.list!=null &&
              this.list.equals(other.getList()))) &&
            ((this.duplicates==null && other.getDuplicates()==null) || 
             (this.duplicates!=null &&
              this.duplicates.equals(other.getDuplicates()))) &&
            ((this.invalidAddresses==null && other.getInvalidAddresses()==null) || 
             (this.invalidAddresses!=null &&
              this.invalidAddresses.equals(other.getInvalidAddresses()))) &&
            ((this.existingUndeliverables==null && other.getExistingUndeliverables()==null) || 
             (this.existingUndeliverables!=null &&
              this.existingUndeliverables.equals(other.getExistingUndeliverables()))) &&
            ((this.existingUnsubscribes==null && other.getExistingUnsubscribes()==null) || 
             (this.existingUnsubscribes!=null &&
              this.existingUnsubscribes.equals(other.getExistingUnsubscribes()))) &&
            ((this.hardBounces==null && other.getHardBounces()==null) || 
             (this.hardBounces!=null &&
              this.hardBounces.equals(other.getHardBounces()))) &&
            ((this.softBounces==null && other.getSoftBounces()==null) || 
             (this.softBounces!=null &&
              this.softBounces.equals(other.getSoftBounces()))) &&
            ((this.otherBounces==null && other.getOtherBounces()==null) || 
             (this.otherBounces!=null &&
              this.otherBounces.equals(other.getOtherBounces()))) &&
            ((this.forwardedEmails==null && other.getForwardedEmails()==null) || 
             (this.forwardedEmails!=null &&
              this.forwardedEmails.equals(other.getForwardedEmails()))) &&
            ((this.uniqueClicks==null && other.getUniqueClicks()==null) || 
             (this.uniqueClicks!=null &&
              this.uniqueClicks.equals(other.getUniqueClicks()))) &&
            ((this.uniqueOpens==null && other.getUniqueOpens()==null) || 
             (this.uniqueOpens!=null &&
              this.uniqueOpens.equals(other.getUniqueOpens()))) &&
            ((this.numberSent==null && other.getNumberSent()==null) || 
             (this.numberSent!=null &&
              this.numberSent.equals(other.getNumberSent()))) &&
            ((this.numberDelivered==null && other.getNumberDelivered()==null) || 
             (this.numberDelivered!=null &&
              this.numberDelivered.equals(other.getNumberDelivered()))) &&
            ((this.unsubscribes==null && other.getUnsubscribes()==null) || 
             (this.unsubscribes!=null &&
              this.unsubscribes.equals(other.getUnsubscribes()))) &&
            ((this.missingAddresses==null && other.getMissingAddresses()==null) || 
             (this.missingAddresses!=null &&
              this.missingAddresses.equals(other.getMissingAddresses()))) &&
            ((this.previewURL==null && other.getPreviewURL()==null) || 
             (this.previewURL!=null &&
              this.previewURL.equals(other.getPreviewURL()))) &&
            ((this.links==null && other.getLinks()==null) || 
             (this.links!=null &&
              java.util.Arrays.equals(this.links, other.getLinks()))) &&
            ((this.events==null && other.getEvents()==null) || 
             (this.events!=null &&
              java.util.Arrays.equals(this.events, other.getEvents())));
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
        if (getSendID() != null) {
            _hashCode += getSendID().hashCode();
        }
        if (getList() != null) {
            _hashCode += getList().hashCode();
        }
        if (getDuplicates() != null) {
            _hashCode += getDuplicates().hashCode();
        }
        if (getInvalidAddresses() != null) {
            _hashCode += getInvalidAddresses().hashCode();
        }
        if (getExistingUndeliverables() != null) {
            _hashCode += getExistingUndeliverables().hashCode();
        }
        if (getExistingUnsubscribes() != null) {
            _hashCode += getExistingUnsubscribes().hashCode();
        }
        if (getHardBounces() != null) {
            _hashCode += getHardBounces().hashCode();
        }
        if (getSoftBounces() != null) {
            _hashCode += getSoftBounces().hashCode();
        }
        if (getOtherBounces() != null) {
            _hashCode += getOtherBounces().hashCode();
        }
        if (getForwardedEmails() != null) {
            _hashCode += getForwardedEmails().hashCode();
        }
        if (getUniqueClicks() != null) {
            _hashCode += getUniqueClicks().hashCode();
        }
        if (getUniqueOpens() != null) {
            _hashCode += getUniqueOpens().hashCode();
        }
        if (getNumberSent() != null) {
            _hashCode += getNumberSent().hashCode();
        }
        if (getNumberDelivered() != null) {
            _hashCode += getNumberDelivered().hashCode();
        }
        if (getUnsubscribes() != null) {
            _hashCode += getUnsubscribes().hashCode();
        }
        if (getMissingAddresses() != null) {
            _hashCode += getMissingAddresses().hashCode();
        }
        if (getPreviewURL() != null) {
            _hashCode += getPreviewURL().hashCode();
        }
        if (getLinks() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getLinks());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getLinks(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getEvents() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getEvents());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getEvents(), i);
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
        new org.apache.axis.description.TypeDesc(ListSend.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ListSend"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sendID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("list");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "List"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "List"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("duplicates");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Duplicates"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("invalidAddresses");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "InvalidAddresses"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("existingUndeliverables");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExistingUndeliverables"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("existingUnsubscribes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ExistingUnsubscribes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hardBounces");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "HardBounces"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("softBounces");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SoftBounces"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("otherBounces");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "OtherBounces"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("forwardedEmails");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ForwardedEmails"));
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
        elemField.setFieldName("uniqueOpens");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "UniqueOpens"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numberSent");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "NumberSent"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numberDelivered");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "NumberDelivered"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unsubscribes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Unsubscribes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("missingAddresses");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "MissingAddresses"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("previewURL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PreviewURL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("links");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Links"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Link"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("events");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Events"));
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
