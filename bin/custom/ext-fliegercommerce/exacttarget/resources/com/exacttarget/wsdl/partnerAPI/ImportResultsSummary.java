/**
 * ImportResultsSummary.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class ImportResultsSummary  extends com.exacttarget.wsdl.partnerAPI.APIObject  implements java.io.Serializable {
    private java.lang.String importDefinitionCustomerKey;

    private java.lang.String startDate;

    private java.lang.String endDate;

    private java.lang.String destinationID;

    private java.lang.Integer numberSuccessful;

    private java.lang.Integer numberDuplicated;

    private java.lang.Integer numberErrors;

    private java.lang.Integer totalRows;

    private java.lang.String importType;

    private java.lang.String importStatus;

    private java.lang.Integer taskResultID;

    public ImportResultsSummary() {
    }

    public ImportResultsSummary(
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
           java.lang.String importDefinitionCustomerKey,
           java.lang.String startDate,
           java.lang.String endDate,
           java.lang.String destinationID,
           java.lang.Integer numberSuccessful,
           java.lang.Integer numberDuplicated,
           java.lang.Integer numberErrors,
           java.lang.Integer totalRows,
           java.lang.String importType,
           java.lang.String importStatus,
           java.lang.Integer taskResultID) {
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
        this.importDefinitionCustomerKey = importDefinitionCustomerKey;
        this.startDate = startDate;
        this.endDate = endDate;
        this.destinationID = destinationID;
        this.numberSuccessful = numberSuccessful;
        this.numberDuplicated = numberDuplicated;
        this.numberErrors = numberErrors;
        this.totalRows = totalRows;
        this.importType = importType;
        this.importStatus = importStatus;
        this.taskResultID = taskResultID;
    }


    /**
     * Gets the importDefinitionCustomerKey value for this ImportResultsSummary.
     * 
     * @return importDefinitionCustomerKey
     */
    public java.lang.String getImportDefinitionCustomerKey() {
        return importDefinitionCustomerKey;
    }


    /**
     * Sets the importDefinitionCustomerKey value for this ImportResultsSummary.
     * 
     * @param importDefinitionCustomerKey
     */
    public void setImportDefinitionCustomerKey(java.lang.String importDefinitionCustomerKey) {
        this.importDefinitionCustomerKey = importDefinitionCustomerKey;
    }


    /**
     * Gets the startDate value for this ImportResultsSummary.
     * 
     * @return startDate
     */
    public java.lang.String getStartDate() {
        return startDate;
    }


    /**
     * Sets the startDate value for this ImportResultsSummary.
     * 
     * @param startDate
     */
    public void setStartDate(java.lang.String startDate) {
        this.startDate = startDate;
    }


    /**
     * Gets the endDate value for this ImportResultsSummary.
     * 
     * @return endDate
     */
    public java.lang.String getEndDate() {
        return endDate;
    }


    /**
     * Sets the endDate value for this ImportResultsSummary.
     * 
     * @param endDate
     */
    public void setEndDate(java.lang.String endDate) {
        this.endDate = endDate;
    }


    /**
     * Gets the destinationID value for this ImportResultsSummary.
     * 
     * @return destinationID
     */
    public java.lang.String getDestinationID() {
        return destinationID;
    }


    /**
     * Sets the destinationID value for this ImportResultsSummary.
     * 
     * @param destinationID
     */
    public void setDestinationID(java.lang.String destinationID) {
        this.destinationID = destinationID;
    }


    /**
     * Gets the numberSuccessful value for this ImportResultsSummary.
     * 
     * @return numberSuccessful
     */
    public java.lang.Integer getNumberSuccessful() {
        return numberSuccessful;
    }


    /**
     * Sets the numberSuccessful value for this ImportResultsSummary.
     * 
     * @param numberSuccessful
     */
    public void setNumberSuccessful(java.lang.Integer numberSuccessful) {
        this.numberSuccessful = numberSuccessful;
    }


    /**
     * Gets the numberDuplicated value for this ImportResultsSummary.
     * 
     * @return numberDuplicated
     */
    public java.lang.Integer getNumberDuplicated() {
        return numberDuplicated;
    }


    /**
     * Sets the numberDuplicated value for this ImportResultsSummary.
     * 
     * @param numberDuplicated
     */
    public void setNumberDuplicated(java.lang.Integer numberDuplicated) {
        this.numberDuplicated = numberDuplicated;
    }


    /**
     * Gets the numberErrors value for this ImportResultsSummary.
     * 
     * @return numberErrors
     */
    public java.lang.Integer getNumberErrors() {
        return numberErrors;
    }


    /**
     * Sets the numberErrors value for this ImportResultsSummary.
     * 
     * @param numberErrors
     */
    public void setNumberErrors(java.lang.Integer numberErrors) {
        this.numberErrors = numberErrors;
    }


    /**
     * Gets the totalRows value for this ImportResultsSummary.
     * 
     * @return totalRows
     */
    public java.lang.Integer getTotalRows() {
        return totalRows;
    }


    /**
     * Sets the totalRows value for this ImportResultsSummary.
     * 
     * @param totalRows
     */
    public void setTotalRows(java.lang.Integer totalRows) {
        this.totalRows = totalRows;
    }


    /**
     * Gets the importType value for this ImportResultsSummary.
     * 
     * @return importType
     */
    public java.lang.String getImportType() {
        return importType;
    }


    /**
     * Sets the importType value for this ImportResultsSummary.
     * 
     * @param importType
     */
    public void setImportType(java.lang.String importType) {
        this.importType = importType;
    }


    /**
     * Gets the importStatus value for this ImportResultsSummary.
     * 
     * @return importStatus
     */
    public java.lang.String getImportStatus() {
        return importStatus;
    }


    /**
     * Sets the importStatus value for this ImportResultsSummary.
     * 
     * @param importStatus
     */
    public void setImportStatus(java.lang.String importStatus) {
        this.importStatus = importStatus;
    }


    /**
     * Gets the taskResultID value for this ImportResultsSummary.
     * 
     * @return taskResultID
     */
    public java.lang.Integer getTaskResultID() {
        return taskResultID;
    }


    /**
     * Sets the taskResultID value for this ImportResultsSummary.
     * 
     * @param taskResultID
     */
    public void setTaskResultID(java.lang.Integer taskResultID) {
        this.taskResultID = taskResultID;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ImportResultsSummary)) return false;
        ImportResultsSummary other = (ImportResultsSummary) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.importDefinitionCustomerKey==null && other.getImportDefinitionCustomerKey()==null) || 
             (this.importDefinitionCustomerKey!=null &&
              this.importDefinitionCustomerKey.equals(other.getImportDefinitionCustomerKey()))) &&
            ((this.startDate==null && other.getStartDate()==null) || 
             (this.startDate!=null &&
              this.startDate.equals(other.getStartDate()))) &&
            ((this.endDate==null && other.getEndDate()==null) || 
             (this.endDate!=null &&
              this.endDate.equals(other.getEndDate()))) &&
            ((this.destinationID==null && other.getDestinationID()==null) || 
             (this.destinationID!=null &&
              this.destinationID.equals(other.getDestinationID()))) &&
            ((this.numberSuccessful==null && other.getNumberSuccessful()==null) || 
             (this.numberSuccessful!=null &&
              this.numberSuccessful.equals(other.getNumberSuccessful()))) &&
            ((this.numberDuplicated==null && other.getNumberDuplicated()==null) || 
             (this.numberDuplicated!=null &&
              this.numberDuplicated.equals(other.getNumberDuplicated()))) &&
            ((this.numberErrors==null && other.getNumberErrors()==null) || 
             (this.numberErrors!=null &&
              this.numberErrors.equals(other.getNumberErrors()))) &&
            ((this.totalRows==null && other.getTotalRows()==null) || 
             (this.totalRows!=null &&
              this.totalRows.equals(other.getTotalRows()))) &&
            ((this.importType==null && other.getImportType()==null) || 
             (this.importType!=null &&
              this.importType.equals(other.getImportType()))) &&
            ((this.importStatus==null && other.getImportStatus()==null) || 
             (this.importStatus!=null &&
              this.importStatus.equals(other.getImportStatus()))) &&
            ((this.taskResultID==null && other.getTaskResultID()==null) || 
             (this.taskResultID!=null &&
              this.taskResultID.equals(other.getTaskResultID())));
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
        if (getImportDefinitionCustomerKey() != null) {
            _hashCode += getImportDefinitionCustomerKey().hashCode();
        }
        if (getStartDate() != null) {
            _hashCode += getStartDate().hashCode();
        }
        if (getEndDate() != null) {
            _hashCode += getEndDate().hashCode();
        }
        if (getDestinationID() != null) {
            _hashCode += getDestinationID().hashCode();
        }
        if (getNumberSuccessful() != null) {
            _hashCode += getNumberSuccessful().hashCode();
        }
        if (getNumberDuplicated() != null) {
            _hashCode += getNumberDuplicated().hashCode();
        }
        if (getNumberErrors() != null) {
            _hashCode += getNumberErrors().hashCode();
        }
        if (getTotalRows() != null) {
            _hashCode += getTotalRows().hashCode();
        }
        if (getImportType() != null) {
            _hashCode += getImportType().hashCode();
        }
        if (getImportStatus() != null) {
            _hashCode += getImportStatus().hashCode();
        }
        if (getTaskResultID() != null) {
            _hashCode += getTaskResultID().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ImportResultsSummary.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ImportResultsSummary"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("importDefinitionCustomerKey");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ImportDefinitionCustomerKey"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("startDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "StartDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("endDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "EndDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("destinationID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DestinationID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numberSuccessful");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "NumberSuccessful"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numberDuplicated");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "NumberDuplicated"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numberErrors");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "NumberErrors"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalRows");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TotalRows"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("importType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ImportType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("importStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ImportStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taskResultID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "TaskResultID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
