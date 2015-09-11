/**
 * TriggeredSendSummary.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class TriggeredSendSummary  extends com.exacttarget.wsdl.partnerAPI.APIObject  implements java.io.Serializable {
    private com.exacttarget.wsdl.partnerAPI.TriggeredSendDefinition triggeredSendDefinition;

    private java.lang.Long sent;

    private java.lang.Long notSentDueToOptOut;

    private java.lang.Long notSentDueToUndeliverable;

    private java.lang.Long bounces;

    private java.lang.Long opens;

    private java.lang.Long clicks;

    private java.lang.Long uniqueOpens;

    private java.lang.Long uniqueClicks;

    private java.lang.Long optOuts;

    private java.lang.Long surveyResponses;

    private java.lang.Long FTAFRequests;

    private java.lang.Long FTAFEmailsSent;

    private java.lang.Long FTAFOptIns;

    private java.lang.Long conversions;

    private java.lang.Long uniqueConversions;

    private java.lang.Long inProcess;

    private java.lang.Long notSentDueToError;

    private java.lang.Long queued;

    public TriggeredSendSummary() {
    }

    public TriggeredSendSummary(
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
           com.exacttarget.wsdl.partnerAPI.TriggeredSendDefinition triggeredSendDefinition,
           java.lang.Long sent,
           java.lang.Long notSentDueToOptOut,
           java.lang.Long notSentDueToUndeliverable,
           java.lang.Long bounces,
           java.lang.Long opens,
           java.lang.Long clicks,
           java.lang.Long uniqueOpens,
           java.lang.Long uniqueClicks,
           java.lang.Long optOuts,
           java.lang.Long surveyResponses,
           java.lang.Long FTAFRequests,
           java.lang.Long FTAFEmailsSent,
           java.lang.Long FTAFOptIns,
           java.lang.Long conversions,
           java.lang.Long uniqueConversions,
           java.lang.Long inProcess,
           java.lang.Long notSentDueToError,
           java.lang.Long queued) {
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
        this.triggeredSendDefinition = triggeredSendDefinition;
        this.sent = sent;
        this.notSentDueToOptOut = notSentDueToOptOut;
        this.notSentDueToUndeliverable = notSentDueToUndeliverable;
        this.bounces = bounces;
        this.opens = opens;
        this.clicks = clicks;
        this.uniqueOpens = uniqueOpens;
        this.uniqueClicks = uniqueClicks;
        this.optOuts = optOuts;
        this.surveyResponses = surveyResponses;
        this.FTAFRequests = FTAFRequests;
        this.FTAFEmailsSent = FTAFEmailsSent;
        this.FTAFOptIns = FTAFOptIns;
        this.conversions = conversions;
        this.uniqueConversions = uniqueConversions;
        this.inProcess = inProcess;
        this.notSentDueToError = notSentDueToError;
        this.queued = queued;
    }


    /**
     * Gets the triggeredSendDefinition value for this TriggeredSendSummary.
     * 
     * @return triggeredSendDefinition
     */
    public com.exacttarget.wsdl.partnerAPI.TriggeredSendDefinition getTriggeredSendDefinition() {
        return triggeredSendDefinition;
    }


    /**
     * Sets the triggeredSendDefinition value for this TriggeredSendSummary.
     * 
     * @param triggeredSendDefinition
     */
    public void setTriggeredSendDefinition(com.exacttarget.wsdl.partnerAPI.TriggeredSendDefinition triggeredSendDefinition) {
        this.triggeredSendDefinition = triggeredSendDefinition;
    }


    /**
     * Gets the sent value for this TriggeredSendSummary.
     * 
     * @return sent
     */
    public java.lang.Long getSent() {
        return sent;
    }


    /**
     * Sets the sent value for this TriggeredSendSummary.
     * 
     * @param sent
     */
    public void setSent(java.lang.Long sent) {
        this.sent = sent;
    }


    /**
     * Gets the notSentDueToOptOut value for this TriggeredSendSummary.
     * 
     * @return notSentDueToOptOut
     */
    public java.lang.Long getNotSentDueToOptOut() {
        return notSentDueToOptOut;
    }


    /**
     * Sets the notSentDueToOptOut value for this TriggeredSendSummary.
     * 
     * @param notSentDueToOptOut
     */
    public void setNotSentDueToOptOut(java.lang.Long notSentDueToOptOut) {
        this.notSentDueToOptOut = notSentDueToOptOut;
    }


    /**
     * Gets the notSentDueToUndeliverable value for this TriggeredSendSummary.
     * 
     * @return notSentDueToUndeliverable
     */
    public java.lang.Long getNotSentDueToUndeliverable() {
        return notSentDueToUndeliverable;
    }


    /**
     * Sets the notSentDueToUndeliverable value for this TriggeredSendSummary.
     * 
     * @param notSentDueToUndeliverable
     */
    public void setNotSentDueToUndeliverable(java.lang.Long notSentDueToUndeliverable) {
        this.notSentDueToUndeliverable = notSentDueToUndeliverable;
    }


    /**
     * Gets the bounces value for this TriggeredSendSummary.
     * 
     * @return bounces
     */
    public java.lang.Long getBounces() {
        return bounces;
    }


    /**
     * Sets the bounces value for this TriggeredSendSummary.
     * 
     * @param bounces
     */
    public void setBounces(java.lang.Long bounces) {
        this.bounces = bounces;
    }


    /**
     * Gets the opens value for this TriggeredSendSummary.
     * 
     * @return opens
     */
    public java.lang.Long getOpens() {
        return opens;
    }


    /**
     * Sets the opens value for this TriggeredSendSummary.
     * 
     * @param opens
     */
    public void setOpens(java.lang.Long opens) {
        this.opens = opens;
    }


    /**
     * Gets the clicks value for this TriggeredSendSummary.
     * 
     * @return clicks
     */
    public java.lang.Long getClicks() {
        return clicks;
    }


    /**
     * Sets the clicks value for this TriggeredSendSummary.
     * 
     * @param clicks
     */
    public void setClicks(java.lang.Long clicks) {
        this.clicks = clicks;
    }


    /**
     * Gets the uniqueOpens value for this TriggeredSendSummary.
     * 
     * @return uniqueOpens
     */
    public java.lang.Long getUniqueOpens() {
        return uniqueOpens;
    }


    /**
     * Sets the uniqueOpens value for this TriggeredSendSummary.
     * 
     * @param uniqueOpens
     */
    public void setUniqueOpens(java.lang.Long uniqueOpens) {
        this.uniqueOpens = uniqueOpens;
    }


    /**
     * Gets the uniqueClicks value for this TriggeredSendSummary.
     * 
     * @return uniqueClicks
     */
    public java.lang.Long getUniqueClicks() {
        return uniqueClicks;
    }


    /**
     * Sets the uniqueClicks value for this TriggeredSendSummary.
     * 
     * @param uniqueClicks
     */
    public void setUniqueClicks(java.lang.Long uniqueClicks) {
        this.uniqueClicks = uniqueClicks;
    }


    /**
     * Gets the optOuts value for this TriggeredSendSummary.
     * 
     * @return optOuts
     */
    public java.lang.Long getOptOuts() {
        return optOuts;
    }


    /**
     * Sets the optOuts value for this TriggeredSendSummary.
     * 
     * @param optOuts
     */
    public void setOptOuts(java.lang.Long optOuts) {
        this.optOuts = optOuts;
    }


    /**
     * Gets the surveyResponses value for this TriggeredSendSummary.
     * 
     * @return surveyResponses
     */
    public java.lang.Long getSurveyResponses() {
        return surveyResponses;
    }


    /**
     * Sets the surveyResponses value for this TriggeredSendSummary.
     * 
     * @param surveyResponses
     */
    public void setSurveyResponses(java.lang.Long surveyResponses) {
        this.surveyResponses = surveyResponses;
    }


    /**
     * Gets the FTAFRequests value for this TriggeredSendSummary.
     * 
     * @return FTAFRequests
     */
    public java.lang.Long getFTAFRequests() {
        return FTAFRequests;
    }


    /**
     * Sets the FTAFRequests value for this TriggeredSendSummary.
     * 
     * @param FTAFRequests
     */
    public void setFTAFRequests(java.lang.Long FTAFRequests) {
        this.FTAFRequests = FTAFRequests;
    }


    /**
     * Gets the FTAFEmailsSent value for this TriggeredSendSummary.
     * 
     * @return FTAFEmailsSent
     */
    public java.lang.Long getFTAFEmailsSent() {
        return FTAFEmailsSent;
    }


    /**
     * Sets the FTAFEmailsSent value for this TriggeredSendSummary.
     * 
     * @param FTAFEmailsSent
     */
    public void setFTAFEmailsSent(java.lang.Long FTAFEmailsSent) {
        this.FTAFEmailsSent = FTAFEmailsSent;
    }


    /**
     * Gets the FTAFOptIns value for this TriggeredSendSummary.
     * 
     * @return FTAFOptIns
     */
    public java.lang.Long getFTAFOptIns() {
        return FTAFOptIns;
    }


    /**
     * Sets the FTAFOptIns value for this TriggeredSendSummary.
     * 
     * @param FTAFOptIns
     */
    public void setFTAFOptIns(java.lang.Long FTAFOptIns) {
        this.FTAFOptIns = FTAFOptIns;
    }


    /**
     * Gets the conversions value for this TriggeredSendSummary.
     * 
     * @return conversions
     */
    public java.lang.Long getConversions() {
        return conversions;
    }


    /**
     * Sets the conversions value for this TriggeredSendSummary.
     * 
     * @param conversions
     */
    public void setConversions(java.lang.Long conversions) {
        this.conversions = conversions;
    }


    /**
     * Gets the uniqueConversions value for this TriggeredSendSummary.
     * 
     * @return uniqueConversions
     */
    public java.lang.Long getUniqueConversions() {
        return uniqueConversions;
    }


    /**
     * Sets the uniqueConversions value for this TriggeredSendSummary.
     * 
     * @param uniqueConversions
     */
    public void setUniqueConversions(java.lang.Long uniqueConversions) {
        this.uniqueConversions = uniqueConversions;
    }


    /**
     * Gets the inProcess value for this TriggeredSendSummary.
     * 
     * @return inProcess
     */
    public java.lang.Long getInProcess() {
        return inProcess;
    }


    /**
     * Sets the inProcess value for this TriggeredSendSummary.
     * 
     * @param inProcess
     */
    public void setInProcess(java.lang.Long inProcess) {
        this.inProcess = inProcess;
    }


    /**
     * Gets the notSentDueToError value for this TriggeredSendSummary.
     * 
     * @return notSentDueToError
     */
    public java.lang.Long getNotSentDueToError() {
        return notSentDueToError;
    }


    /**
     * Sets the notSentDueToError value for this TriggeredSendSummary.
     * 
     * @param notSentDueToError
     */
    public void setNotSentDueToError(java.lang.Long notSentDueToError) {
        this.notSentDueToError = notSentDueToError;
    }


    /**
     * Gets the queued value for this TriggeredSendSummary.
     * 
     * @return queued
     */
    public java.lang.Long getQueued() {
        return queued;
    }


    /**
     * Sets the queued value for this TriggeredSendSummary.
     * 
     * @param queued
     */
    public void setQueued(java.lang.Long queued) {
        this.queued = queued;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TriggeredSendSummary)) return false;
        TriggeredSendSummary other = (TriggeredSendSummary) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.triggeredSendDefinition==null && other.getTriggeredSendDefinition()==null) || 
             (this.triggeredSendDefinition!=null &&
              this.triggeredSendDefinition.equals(other.getTriggeredSendDefinition()))) &&
            ((this.sent==null && other.getSent()==null) || 
             (this.sent!=null &&
              this.sent.equals(other.getSent()))) &&
            ((this.notSentDueToOptOut==null && other.getNotSentDueToOptOut()==null) || 
             (this.notSentDueToOptOut!=null &&
              this.notSentDueToOptOut.equals(other.getNotSentDueToOptOut()))) &&
            ((this.notSentDueToUndeliverable==null && other.getNotSentDueToUndeliverable()==null) || 
             (this.notSentDueToUndeliverable!=null &&
              this.notSentDueToUndeliverable.equals(other.getNotSentDueToUndeliverable()))) &&
            ((this.bounces==null && other.getBounces()==null) || 
             (this.bounces!=null &&
              this.bounces.equals(other.getBounces()))) &&
            ((this.opens==null && other.getOpens()==null) || 
             (this.opens!=null &&
              this.opens.equals(other.getOpens()))) &&
            ((this.clicks==null && other.getClicks()==null) || 
             (this.clicks!=null &&
              this.clicks.equals(other.getClicks()))) &&
            ((this.uniqueOpens==null && other.getUniqueOpens()==null) || 
             (this.uniqueOpens!=null &&
              this.uniqueOpens.equals(other.getUniqueOpens()))) &&
            ((this.uniqueClicks==null && other.getUniqueClicks()==null) || 
             (this.uniqueClicks!=null &&
              this.uniqueClicks.equals(other.getUniqueClicks()))) &&
            ((this.optOuts==null && other.getOptOuts()==null) || 
             (this.optOuts!=null &&
              this.optOuts.equals(other.getOptOuts()))) &&
            ((this.surveyResponses==null && other.getSurveyResponses()==null) || 
             (this.surveyResponses!=null &&
              this.surveyResponses.equals(other.getSurveyResponses()))) &&
            ((this.FTAFRequests==null && other.getFTAFRequests()==null) || 
             (this.FTAFRequests!=null &&
              this.FTAFRequests.equals(other.getFTAFRequests()))) &&
            ((this.FTAFEmailsSent==null && other.getFTAFEmailsSent()==null) || 
             (this.FTAFEmailsSent!=null &&
              this.FTAFEmailsSent.equals(other.getFTAFEmailsSent()))) &&
            ((this.FTAFOptIns==null && other.getFTAFOptIns()==null) || 
             (this.FTAFOptIns!=null &&
              this.FTAFOptIns.equals(other.getFTAFOptIns()))) &&
            ((this.conversions==null && other.getConversions()==null) || 
             (this.conversions!=null &&
              this.conversions.equals(other.getConversions()))) &&
            ((this.uniqueConversions==null && other.getUniqueConversions()==null) || 
             (this.uniqueConversions!=null &&
              this.uniqueConversions.equals(other.getUniqueConversions()))) &&
            ((this.inProcess==null && other.getInProcess()==null) || 
             (this.inProcess!=null &&
              this.inProcess.equals(other.getInProcess()))) &&
            ((this.notSentDueToError==null && other.getNotSentDueToError()==null) || 
             (this.notSentDueToError!=null &&
              this.notSentDueToError.equals(other.getNotSentDueToError()))) &&
            ((this.queued==null && other.getQueued()==null) || 
             (this.queued!=null &&
              this.queued.equals(other.getQueued())));
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
        if (getTriggeredSendDefinition() != null) {
            _hashCode += getTriggeredSendDefinition().hashCode();
        }
        if (getSent() != null) {
            _hashCode += getSent().hashCode();
        }
        if (getNotSentDueToOptOut() != null) {
            _hashCode += getNotSentDueToOptOut().hashCode();
        }
        if (getNotSentDueToUndeliverable() != null) {
            _hashCode += getNotSentDueToUndeliverable().hashCode();
        }
        if (getBounces() != null) {
            _hashCode += getBounces().hashCode();
        }
        if (getOpens() != null) {
            _hashCode += getOpens().hashCode();
        }
        if (getClicks() != null) {
            _hashCode += getClicks().hashCode();
        }
        if (getUniqueOpens() != null) {
            _hashCode += getUniqueOpens().hashCode();
        }
        if (getUniqueClicks() != null) {
            _hashCode += getUniqueClicks().hashCode();
        }
        if (getOptOuts() != null) {
            _hashCode += getOptOuts().hashCode();
        }
        if (getSurveyResponses() != null) {
            _hashCode += getSurveyResponses().hashCode();
        }
        if (getFTAFRequests() != null) {
            _hashCode += getFTAFRequests().hashCode();
        }
        if (getFTAFEmailsSent() != null) {
            _hashCode += getFTAFEmailsSent().hashCode();
        }
        if (getFTAFOptIns() != null) {
            _hashCode += getFTAFOptIns().hashCode();
        }
        if (getConversions() != null) {
            _hashCode += getConversions().hashCode();
        }
        if (getUniqueConversions() != null) {
            _hashCode += getUniqueConversions().hashCode();
        }
        if (getInProcess() != null) {
            _hashCode += getInProcess().hashCode();
        }
        if (getNotSentDueToError() != null) {
            _hashCode += getNotSentDueToError().hashCode();
        }
        if (getQueued() != null) {
            _hashCode += getQueued().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TriggeredSendSummary.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TriggeredSendSummary"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("triggeredSendDefinition");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TriggeredSendDefinition"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TriggeredSendDefinition"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sent");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Sent"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("notSentDueToOptOut");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "NotSentDueToOptOut"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("notSentDueToUndeliverable");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "NotSentDueToUndeliverable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bounces");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Bounces"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("opens");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Opens"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clicks");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Clicks"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("uniqueOpens");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "UniqueOpens"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("uniqueClicks");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "UniqueClicks"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("optOuts");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "OptOuts"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("surveyResponses");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SurveyResponses"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FTAFRequests");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FTAFRequests"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FTAFEmailsSent");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FTAFEmailsSent"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FTAFOptIns");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FTAFOptIns"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("conversions");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Conversions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("uniqueConversions");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "UniqueConversions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inProcess");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "InProcess"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("notSentDueToError");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "NotSentDueToError"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("queued");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Queued"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
