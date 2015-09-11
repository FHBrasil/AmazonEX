/**
 * QueryRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class QueryRequest  implements java.io.Serializable {
    private com.exacttarget.wsdl.partnerAPI.ClientID[] clientIDs;

    private com.exacttarget.wsdl.partnerAPI.Query query;

    private com.exacttarget.wsdl.partnerAPI.AsyncResponse[] respondTo;

    private com.exacttarget.wsdl.partnerAPI.APIProperty[] partnerProperties;

    private java.lang.String continueRequest;

    private java.lang.Boolean queryAllAccounts;

    private java.lang.Boolean retrieveAllSinceLastBatch;

    public QueryRequest() {
    }

    public QueryRequest(
           com.exacttarget.wsdl.partnerAPI.ClientID[] clientIDs,
           com.exacttarget.wsdl.partnerAPI.Query query,
           com.exacttarget.wsdl.partnerAPI.AsyncResponse[] respondTo,
           com.exacttarget.wsdl.partnerAPI.APIProperty[] partnerProperties,
           java.lang.String continueRequest,
           java.lang.Boolean queryAllAccounts,
           java.lang.Boolean retrieveAllSinceLastBatch) {
           this.clientIDs = clientIDs;
           this.query = query;
           this.respondTo = respondTo;
           this.partnerProperties = partnerProperties;
           this.continueRequest = continueRequest;
           this.queryAllAccounts = queryAllAccounts;
           this.retrieveAllSinceLastBatch = retrieveAllSinceLastBatch;
    }


    /**
     * Gets the clientIDs value for this QueryRequest.
     * 
     * @return clientIDs
     */
    public com.exacttarget.wsdl.partnerAPI.ClientID[] getClientIDs() {
        return clientIDs;
    }


    /**
     * Sets the clientIDs value for this QueryRequest.
     * 
     * @param clientIDs
     */
    public void setClientIDs(com.exacttarget.wsdl.partnerAPI.ClientID[] clientIDs) {
        this.clientIDs = clientIDs;
    }

    public com.exacttarget.wsdl.partnerAPI.ClientID getClientIDs(int i) {
        return this.clientIDs[i];
    }

    public void setClientIDs(int i, com.exacttarget.wsdl.partnerAPI.ClientID _value) {
        this.clientIDs[i] = _value;
    }


    /**
     * Gets the query value for this QueryRequest.
     * 
     * @return query
     */
    public com.exacttarget.wsdl.partnerAPI.Query getQuery() {
        return query;
    }


    /**
     * Sets the query value for this QueryRequest.
     * 
     * @param query
     */
    public void setQuery(com.exacttarget.wsdl.partnerAPI.Query query) {
        this.query = query;
    }


    /**
     * Gets the respondTo value for this QueryRequest.
     * 
     * @return respondTo
     */
    public com.exacttarget.wsdl.partnerAPI.AsyncResponse[] getRespondTo() {
        return respondTo;
    }


    /**
     * Sets the respondTo value for this QueryRequest.
     * 
     * @param respondTo
     */
    public void setRespondTo(com.exacttarget.wsdl.partnerAPI.AsyncResponse[] respondTo) {
        this.respondTo = respondTo;
    }

    public com.exacttarget.wsdl.partnerAPI.AsyncResponse getRespondTo(int i) {
        return this.respondTo[i];
    }

    public void setRespondTo(int i, com.exacttarget.wsdl.partnerAPI.AsyncResponse _value) {
        this.respondTo[i] = _value;
    }


    /**
     * Gets the partnerProperties value for this QueryRequest.
     * 
     * @return partnerProperties
     */
    public com.exacttarget.wsdl.partnerAPI.APIProperty[] getPartnerProperties() {
        return partnerProperties;
    }


    /**
     * Sets the partnerProperties value for this QueryRequest.
     * 
     * @param partnerProperties
     */
    public void setPartnerProperties(com.exacttarget.wsdl.partnerAPI.APIProperty[] partnerProperties) {
        this.partnerProperties = partnerProperties;
    }

    public com.exacttarget.wsdl.partnerAPI.APIProperty getPartnerProperties(int i) {
        return this.partnerProperties[i];
    }

    public void setPartnerProperties(int i, com.exacttarget.wsdl.partnerAPI.APIProperty _value) {
        this.partnerProperties[i] = _value;
    }


    /**
     * Gets the continueRequest value for this QueryRequest.
     * 
     * @return continueRequest
     */
    public java.lang.String getContinueRequest() {
        return continueRequest;
    }


    /**
     * Sets the continueRequest value for this QueryRequest.
     * 
     * @param continueRequest
     */
    public void setContinueRequest(java.lang.String continueRequest) {
        this.continueRequest = continueRequest;
    }


    /**
     * Gets the queryAllAccounts value for this QueryRequest.
     * 
     * @return queryAllAccounts
     */
    public java.lang.Boolean getQueryAllAccounts() {
        return queryAllAccounts;
    }


    /**
     * Sets the queryAllAccounts value for this QueryRequest.
     * 
     * @param queryAllAccounts
     */
    public void setQueryAllAccounts(java.lang.Boolean queryAllAccounts) {
        this.queryAllAccounts = queryAllAccounts;
    }


    /**
     * Gets the retrieveAllSinceLastBatch value for this QueryRequest.
     * 
     * @return retrieveAllSinceLastBatch
     */
    public java.lang.Boolean getRetrieveAllSinceLastBatch() {
        return retrieveAllSinceLastBatch;
    }


    /**
     * Sets the retrieveAllSinceLastBatch value for this QueryRequest.
     * 
     * @param retrieveAllSinceLastBatch
     */
    public void setRetrieveAllSinceLastBatch(java.lang.Boolean retrieveAllSinceLastBatch) {
        this.retrieveAllSinceLastBatch = retrieveAllSinceLastBatch;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof QueryRequest)) return false;
        QueryRequest other = (QueryRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.clientIDs==null && other.getClientIDs()==null) || 
             (this.clientIDs!=null &&
              java.util.Arrays.equals(this.clientIDs, other.getClientIDs()))) &&
            ((this.query==null && other.getQuery()==null) || 
             (this.query!=null &&
              this.query.equals(other.getQuery()))) &&
            ((this.respondTo==null && other.getRespondTo()==null) || 
             (this.respondTo!=null &&
              java.util.Arrays.equals(this.respondTo, other.getRespondTo()))) &&
            ((this.partnerProperties==null && other.getPartnerProperties()==null) || 
             (this.partnerProperties!=null &&
              java.util.Arrays.equals(this.partnerProperties, other.getPartnerProperties()))) &&
            ((this.continueRequest==null && other.getContinueRequest()==null) || 
             (this.continueRequest!=null &&
              this.continueRequest.equals(other.getContinueRequest()))) &&
            ((this.queryAllAccounts==null && other.getQueryAllAccounts()==null) || 
             (this.queryAllAccounts!=null &&
              this.queryAllAccounts.equals(other.getQueryAllAccounts()))) &&
            ((this.retrieveAllSinceLastBatch==null && other.getRetrieveAllSinceLastBatch()==null) || 
             (this.retrieveAllSinceLastBatch!=null &&
              this.retrieveAllSinceLastBatch.equals(other.getRetrieveAllSinceLastBatch())));
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
        if (getClientIDs() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getClientIDs());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getClientIDs(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getQuery() != null) {
            _hashCode += getQuery().hashCode();
        }
        if (getRespondTo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRespondTo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRespondTo(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPartnerProperties() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPartnerProperties());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPartnerProperties(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getContinueRequest() != null) {
            _hashCode += getContinueRequest().hashCode();
        }
        if (getQueryAllAccounts() != null) {
            _hashCode += getQueryAllAccounts().hashCode();
        }
        if (getRetrieveAllSinceLastBatch() != null) {
            _hashCode += getRetrieveAllSinceLastBatch().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(QueryRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "QueryRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clientIDs");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ClientIDs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ClientID"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("query");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Query"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Query"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("respondTo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RespondTo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "AsyncResponse"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("partnerProperties");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "PartnerProperties"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "APIProperty"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("continueRequest");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ContinueRequest"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("queryAllAccounts");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "QueryAllAccounts"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("retrieveAllSinceLastBatch");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RetrieveAllSinceLastBatch"));
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
