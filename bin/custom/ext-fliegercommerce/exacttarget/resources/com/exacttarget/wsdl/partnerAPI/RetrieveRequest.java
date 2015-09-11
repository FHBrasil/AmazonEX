/**
 * RetrieveRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class RetrieveRequest  implements java.io.Serializable {
    private com.exacttarget.wsdl.partnerAPI.ClientID[] clientIDs;

    private java.lang.String objectType;

    private java.lang.String[] properties;

    private com.exacttarget.wsdl.partnerAPI.FilterPart filter;

    private com.exacttarget.wsdl.partnerAPI.AsyncResponse[] respondTo;

    private com.exacttarget.wsdl.partnerAPI.APIProperty[] partnerProperties;

    private java.lang.String continueRequest;

    private java.lang.Boolean queryAllAccounts;

    private java.lang.Boolean retrieveAllSinceLastBatch;

    private java.lang.Boolean repeatLastResult;

    private com.exacttarget.wsdl.partnerAPI.Request[] retrieves;

    private com.exacttarget.wsdl.partnerAPI.RetrieveOptions options;

    public RetrieveRequest() {
    }

    public RetrieveRequest(
           com.exacttarget.wsdl.partnerAPI.ClientID[] clientIDs,
           java.lang.String objectType,
           java.lang.String[] properties,
           com.exacttarget.wsdl.partnerAPI.FilterPart filter,
           com.exacttarget.wsdl.partnerAPI.AsyncResponse[] respondTo,
           com.exacttarget.wsdl.partnerAPI.APIProperty[] partnerProperties,
           java.lang.String continueRequest,
           java.lang.Boolean queryAllAccounts,
           java.lang.Boolean retrieveAllSinceLastBatch,
           java.lang.Boolean repeatLastResult,
           com.exacttarget.wsdl.partnerAPI.Request[] retrieves,
           com.exacttarget.wsdl.partnerAPI.RetrieveOptions options) {
           this.clientIDs = clientIDs;
           this.objectType = objectType;
           this.properties = properties;
           this.filter = filter;
           this.respondTo = respondTo;
           this.partnerProperties = partnerProperties;
           this.continueRequest = continueRequest;
           this.queryAllAccounts = queryAllAccounts;
           this.retrieveAllSinceLastBatch = retrieveAllSinceLastBatch;
           this.repeatLastResult = repeatLastResult;
           this.retrieves = retrieves;
           this.options = options;
    }


    /**
     * Gets the clientIDs value for this RetrieveRequest.
     * 
     * @return clientIDs
     */
    public com.exacttarget.wsdl.partnerAPI.ClientID[] getClientIDs() {
        return clientIDs;
    }


    /**
     * Sets the clientIDs value for this RetrieveRequest.
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
     * Gets the objectType value for this RetrieveRequest.
     * 
     * @return objectType
     */
    public java.lang.String getObjectType() {
        return objectType;
    }


    /**
     * Sets the objectType value for this RetrieveRequest.
     * 
     * @param objectType
     */
    public void setObjectType(java.lang.String objectType) {
        this.objectType = objectType;
    }


    /**
     * Gets the properties value for this RetrieveRequest.
     * 
     * @return properties
     */
    public java.lang.String[] getProperties() {
        return properties;
    }


    /**
     * Sets the properties value for this RetrieveRequest.
     * 
     * @param properties
     */
    public void setProperties(java.lang.String[] properties) {
        this.properties = properties;
    }

    public java.lang.String getProperties(int i) {
        return this.properties[i];
    }

    public void setProperties(int i, java.lang.String _value) {
        this.properties[i] = _value;
    }


    /**
     * Gets the filter value for this RetrieveRequest.
     * 
     * @return filter
     */
    public com.exacttarget.wsdl.partnerAPI.FilterPart getFilter() {
        return filter;
    }


    /**
     * Sets the filter value for this RetrieveRequest.
     * 
     * @param filter
     */
    public void setFilter(com.exacttarget.wsdl.partnerAPI.FilterPart filter) {
        this.filter = filter;
    }


    /**
     * Gets the respondTo value for this RetrieveRequest.
     * 
     * @return respondTo
     */
    public com.exacttarget.wsdl.partnerAPI.AsyncResponse[] getRespondTo() {
        return respondTo;
    }


    /**
     * Sets the respondTo value for this RetrieveRequest.
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
     * Gets the partnerProperties value for this RetrieveRequest.
     * 
     * @return partnerProperties
     */
    public com.exacttarget.wsdl.partnerAPI.APIProperty[] getPartnerProperties() {
        return partnerProperties;
    }


    /**
     * Sets the partnerProperties value for this RetrieveRequest.
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
     * Gets the continueRequest value for this RetrieveRequest.
     * 
     * @return continueRequest
     */
    public java.lang.String getContinueRequest() {
        return continueRequest;
    }


    /**
     * Sets the continueRequest value for this RetrieveRequest.
     * 
     * @param continueRequest
     */
    public void setContinueRequest(java.lang.String continueRequest) {
        this.continueRequest = continueRequest;
    }


    /**
     * Gets the queryAllAccounts value for this RetrieveRequest.
     * 
     * @return queryAllAccounts
     */
    public java.lang.Boolean getQueryAllAccounts() {
        return queryAllAccounts;
    }


    /**
     * Sets the queryAllAccounts value for this RetrieveRequest.
     * 
     * @param queryAllAccounts
     */
    public void setQueryAllAccounts(java.lang.Boolean queryAllAccounts) {
        this.queryAllAccounts = queryAllAccounts;
    }


    /**
     * Gets the retrieveAllSinceLastBatch value for this RetrieveRequest.
     * 
     * @return retrieveAllSinceLastBatch
     */
    public java.lang.Boolean getRetrieveAllSinceLastBatch() {
        return retrieveAllSinceLastBatch;
    }


    /**
     * Sets the retrieveAllSinceLastBatch value for this RetrieveRequest.
     * 
     * @param retrieveAllSinceLastBatch
     */
    public void setRetrieveAllSinceLastBatch(java.lang.Boolean retrieveAllSinceLastBatch) {
        this.retrieveAllSinceLastBatch = retrieveAllSinceLastBatch;
    }


    /**
     * Gets the repeatLastResult value for this RetrieveRequest.
     * 
     * @return repeatLastResult
     */
    public java.lang.Boolean getRepeatLastResult() {
        return repeatLastResult;
    }


    /**
     * Sets the repeatLastResult value for this RetrieveRequest.
     * 
     * @param repeatLastResult
     */
    public void setRepeatLastResult(java.lang.Boolean repeatLastResult) {
        this.repeatLastResult = repeatLastResult;
    }


    /**
     * Gets the retrieves value for this RetrieveRequest.
     * 
     * @return retrieves
     */
    public com.exacttarget.wsdl.partnerAPI.Request[] getRetrieves() {
        return retrieves;
    }


    /**
     * Sets the retrieves value for this RetrieveRequest.
     * 
     * @param retrieves
     */
    public void setRetrieves(com.exacttarget.wsdl.partnerAPI.Request[] retrieves) {
        this.retrieves = retrieves;
    }


    /**
     * Gets the options value for this RetrieveRequest.
     * 
     * @return options
     */
    public com.exacttarget.wsdl.partnerAPI.RetrieveOptions getOptions() {
        return options;
    }


    /**
     * Sets the options value for this RetrieveRequest.
     * 
     * @param options
     */
    public void setOptions(com.exacttarget.wsdl.partnerAPI.RetrieveOptions options) {
        this.options = options;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RetrieveRequest)) return false;
        RetrieveRequest other = (RetrieveRequest) obj;
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
            ((this.objectType==null && other.getObjectType()==null) || 
             (this.objectType!=null &&
              this.objectType.equals(other.getObjectType()))) &&
            ((this.properties==null && other.getProperties()==null) || 
             (this.properties!=null &&
              java.util.Arrays.equals(this.properties, other.getProperties()))) &&
            ((this.filter==null && other.getFilter()==null) || 
             (this.filter!=null &&
              this.filter.equals(other.getFilter()))) &&
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
              this.retrieveAllSinceLastBatch.equals(other.getRetrieveAllSinceLastBatch()))) &&
            ((this.repeatLastResult==null && other.getRepeatLastResult()==null) || 
             (this.repeatLastResult!=null &&
              this.repeatLastResult.equals(other.getRepeatLastResult()))) &&
            ((this.retrieves==null && other.getRetrieves()==null) || 
             (this.retrieves!=null &&
              java.util.Arrays.equals(this.retrieves, other.getRetrieves()))) &&
            ((this.options==null && other.getOptions()==null) || 
             (this.options!=null &&
              this.options.equals(other.getOptions())));
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
        if (getObjectType() != null) {
            _hashCode += getObjectType().hashCode();
        }
        if (getProperties() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getProperties());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getProperties(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getFilter() != null) {
            _hashCode += getFilter().hashCode();
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
        if (getRepeatLastResult() != null) {
            _hashCode += getRepeatLastResult().hashCode();
        }
        if (getRetrieves() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRetrieves());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRetrieves(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getOptions() != null) {
            _hashCode += getOptions().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RetrieveRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RetrieveRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clientIDs");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ClientIDs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ClientID"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("objectType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ObjectType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("properties");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Properties"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("filter");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Filter"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "FilterPart"));
        elemField.setMinOccurs(0);
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("repeatLastResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RepeatLastResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("retrieves");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Retrieves"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Request"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Request"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("options");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Options"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RetrieveOptions"));
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
