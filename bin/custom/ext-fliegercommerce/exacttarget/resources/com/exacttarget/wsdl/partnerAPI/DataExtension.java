/**
 * DataExtension.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class DataExtension  extends com.exacttarget.wsdl.partnerAPI.APIObject  implements java.io.Serializable {
    private java.lang.String name;

    private java.lang.String description;

    private java.lang.Boolean isSendable;

    private java.lang.Boolean isTestable;

    private com.exacttarget.wsdl.partnerAPI.DataExtensionField sendableDataExtensionField;

    private com.exacttarget.wsdl.partnerAPI.Attribute sendableSubscriberField;

    private com.exacttarget.wsdl.partnerAPI.DataExtensionTemplate template;

    private java.lang.Integer dataRetentionPeriodLength;

    /* Deprecated.  Use DataRetentionPeriod instead. */
    private java.lang.Integer dataRetentionPeriodUnitOfMeasure;

    private java.lang.Boolean rowBasedRetention;

    private java.lang.Boolean resetRetentionPeriodOnImport;

    private java.lang.Boolean deleteAtEndOfRetentionPeriod;

    private java.lang.String retainUntil;

    private com.exacttarget.wsdl.partnerAPI.DataExtensionField[] fields;

    private com.exacttarget.wsdl.partnerAPI.DateTimeUnitOfMeasure dataRetentionPeriod;

    private java.lang.Long categoryID;

    private java.lang.String status;

    public DataExtension() {
    }

    public DataExtension(
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
           java.lang.String name,
           java.lang.String description,
           java.lang.Boolean isSendable,
           java.lang.Boolean isTestable,
           com.exacttarget.wsdl.partnerAPI.DataExtensionField sendableDataExtensionField,
           com.exacttarget.wsdl.partnerAPI.Attribute sendableSubscriberField,
           com.exacttarget.wsdl.partnerAPI.DataExtensionTemplate template,
           java.lang.Integer dataRetentionPeriodLength,
           java.lang.Integer dataRetentionPeriodUnitOfMeasure,
           java.lang.Boolean rowBasedRetention,
           java.lang.Boolean resetRetentionPeriodOnImport,
           java.lang.Boolean deleteAtEndOfRetentionPeriod,
           java.lang.String retainUntil,
           com.exacttarget.wsdl.partnerAPI.DataExtensionField[] fields,
           com.exacttarget.wsdl.partnerAPI.DateTimeUnitOfMeasure dataRetentionPeriod,
           java.lang.Long categoryID,
           java.lang.String status) {
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
        this.name = name;
        this.description = description;
        this.isSendable = isSendable;
        this.isTestable = isTestable;
        this.sendableDataExtensionField = sendableDataExtensionField;
        this.sendableSubscriberField = sendableSubscriberField;
        this.template = template;
        this.dataRetentionPeriodLength = dataRetentionPeriodLength;
        this.dataRetentionPeriodUnitOfMeasure = dataRetentionPeriodUnitOfMeasure;
        this.rowBasedRetention = rowBasedRetention;
        this.resetRetentionPeriodOnImport = resetRetentionPeriodOnImport;
        this.deleteAtEndOfRetentionPeriod = deleteAtEndOfRetentionPeriod;
        this.retainUntil = retainUntil;
        this.fields = fields;
        this.dataRetentionPeriod = dataRetentionPeriod;
        this.categoryID = categoryID;
        this.status = status;
    }


    /**
     * Gets the name value for this DataExtension.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this DataExtension.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the description value for this DataExtension.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this DataExtension.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the isSendable value for this DataExtension.
     * 
     * @return isSendable
     */
    public java.lang.Boolean getIsSendable() {
        return isSendable;
    }


    /**
     * Sets the isSendable value for this DataExtension.
     * 
     * @param isSendable
     */
    public void setIsSendable(java.lang.Boolean isSendable) {
        this.isSendable = isSendable;
    }


    /**
     * Gets the isTestable value for this DataExtension.
     * 
     * @return isTestable
     */
    public java.lang.Boolean getIsTestable() {
        return isTestable;
    }


    /**
     * Sets the isTestable value for this DataExtension.
     * 
     * @param isTestable
     */
    public void setIsTestable(java.lang.Boolean isTestable) {
        this.isTestable = isTestable;
    }


    /**
     * Gets the sendableDataExtensionField value for this DataExtension.
     * 
     * @return sendableDataExtensionField
     */
    public com.exacttarget.wsdl.partnerAPI.DataExtensionField getSendableDataExtensionField() {
        return sendableDataExtensionField;
    }


    /**
     * Sets the sendableDataExtensionField value for this DataExtension.
     * 
     * @param sendableDataExtensionField
     */
    public void setSendableDataExtensionField(com.exacttarget.wsdl.partnerAPI.DataExtensionField sendableDataExtensionField) {
        this.sendableDataExtensionField = sendableDataExtensionField;
    }


    /**
     * Gets the sendableSubscriberField value for this DataExtension.
     * 
     * @return sendableSubscriberField
     */
    public com.exacttarget.wsdl.partnerAPI.Attribute getSendableSubscriberField() {
        return sendableSubscriberField;
    }


    /**
     * Sets the sendableSubscriberField value for this DataExtension.
     * 
     * @param sendableSubscriberField
     */
    public void setSendableSubscriberField(com.exacttarget.wsdl.partnerAPI.Attribute sendableSubscriberField) {
        this.sendableSubscriberField = sendableSubscriberField;
    }


    /**
     * Gets the template value for this DataExtension.
     * 
     * @return template
     */
    public com.exacttarget.wsdl.partnerAPI.DataExtensionTemplate getTemplate() {
        return template;
    }


    /**
     * Sets the template value for this DataExtension.
     * 
     * @param template
     */
    public void setTemplate(com.exacttarget.wsdl.partnerAPI.DataExtensionTemplate template) {
        this.template = template;
    }


    /**
     * Gets the dataRetentionPeriodLength value for this DataExtension.
     * 
     * @return dataRetentionPeriodLength
     */
    public java.lang.Integer getDataRetentionPeriodLength() {
        return dataRetentionPeriodLength;
    }


    /**
     * Sets the dataRetentionPeriodLength value for this DataExtension.
     * 
     * @param dataRetentionPeriodLength
     */
    public void setDataRetentionPeriodLength(java.lang.Integer dataRetentionPeriodLength) {
        this.dataRetentionPeriodLength = dataRetentionPeriodLength;
    }


    /**
     * Gets the dataRetentionPeriodUnitOfMeasure value for this DataExtension.
     * 
     * @return dataRetentionPeriodUnitOfMeasure   * Deprecated.  Use DataRetentionPeriod instead.
     */
    public java.lang.Integer getDataRetentionPeriodUnitOfMeasure() {
        return dataRetentionPeriodUnitOfMeasure;
    }


    /**
     * Sets the dataRetentionPeriodUnitOfMeasure value for this DataExtension.
     * 
     * @param dataRetentionPeriodUnitOfMeasure   * Deprecated.  Use DataRetentionPeriod instead.
     */
    public void setDataRetentionPeriodUnitOfMeasure(java.lang.Integer dataRetentionPeriodUnitOfMeasure) {
        this.dataRetentionPeriodUnitOfMeasure = dataRetentionPeriodUnitOfMeasure;
    }


    /**
     * Gets the rowBasedRetention value for this DataExtension.
     * 
     * @return rowBasedRetention
     */
    public java.lang.Boolean getRowBasedRetention() {
        return rowBasedRetention;
    }


    /**
     * Sets the rowBasedRetention value for this DataExtension.
     * 
     * @param rowBasedRetention
     */
    public void setRowBasedRetention(java.lang.Boolean rowBasedRetention) {
        this.rowBasedRetention = rowBasedRetention;
    }


    /**
     * Gets the resetRetentionPeriodOnImport value for this DataExtension.
     * 
     * @return resetRetentionPeriodOnImport
     */
    public java.lang.Boolean getResetRetentionPeriodOnImport() {
        return resetRetentionPeriodOnImport;
    }


    /**
     * Sets the resetRetentionPeriodOnImport value for this DataExtension.
     * 
     * @param resetRetentionPeriodOnImport
     */
    public void setResetRetentionPeriodOnImport(java.lang.Boolean resetRetentionPeriodOnImport) {
        this.resetRetentionPeriodOnImport = resetRetentionPeriodOnImport;
    }


    /**
     * Gets the deleteAtEndOfRetentionPeriod value for this DataExtension.
     * 
     * @return deleteAtEndOfRetentionPeriod
     */
    public java.lang.Boolean getDeleteAtEndOfRetentionPeriod() {
        return deleteAtEndOfRetentionPeriod;
    }


    /**
     * Sets the deleteAtEndOfRetentionPeriod value for this DataExtension.
     * 
     * @param deleteAtEndOfRetentionPeriod
     */
    public void setDeleteAtEndOfRetentionPeriod(java.lang.Boolean deleteAtEndOfRetentionPeriod) {
        this.deleteAtEndOfRetentionPeriod = deleteAtEndOfRetentionPeriod;
    }


    /**
     * Gets the retainUntil value for this DataExtension.
     * 
     * @return retainUntil
     */
    public java.lang.String getRetainUntil() {
        return retainUntil;
    }


    /**
     * Sets the retainUntil value for this DataExtension.
     * 
     * @param retainUntil
     */
    public void setRetainUntil(java.lang.String retainUntil) {
        this.retainUntil = retainUntil;
    }


    /**
     * Gets the fields value for this DataExtension.
     * 
     * @return fields
     */
    public com.exacttarget.wsdl.partnerAPI.DataExtensionField[] getFields() {
        return fields;
    }


    /**
     * Sets the fields value for this DataExtension.
     * 
     * @param fields
     */
    public void setFields(com.exacttarget.wsdl.partnerAPI.DataExtensionField[] fields) {
        this.fields = fields;
    }


    /**
     * Gets the dataRetentionPeriod value for this DataExtension.
     * 
     * @return dataRetentionPeriod
     */
    public com.exacttarget.wsdl.partnerAPI.DateTimeUnitOfMeasure getDataRetentionPeriod() {
        return dataRetentionPeriod;
    }


    /**
     * Sets the dataRetentionPeriod value for this DataExtension.
     * 
     * @param dataRetentionPeriod
     */
    public void setDataRetentionPeriod(com.exacttarget.wsdl.partnerAPI.DateTimeUnitOfMeasure dataRetentionPeriod) {
        this.dataRetentionPeriod = dataRetentionPeriod;
    }


    /**
     * Gets the categoryID value for this DataExtension.
     * 
     * @return categoryID
     */
    public java.lang.Long getCategoryID() {
        return categoryID;
    }


    /**
     * Sets the categoryID value for this DataExtension.
     * 
     * @param categoryID
     */
    public void setCategoryID(java.lang.Long categoryID) {
        this.categoryID = categoryID;
    }


    /**
     * Gets the status value for this DataExtension.
     * 
     * @return status
     */
    public java.lang.String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this DataExtension.
     * 
     * @param status
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DataExtension)) return false;
        DataExtension other = (DataExtension) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.isSendable==null && other.getIsSendable()==null) || 
             (this.isSendable!=null &&
              this.isSendable.equals(other.getIsSendable()))) &&
            ((this.isTestable==null && other.getIsTestable()==null) || 
             (this.isTestable!=null &&
              this.isTestable.equals(other.getIsTestable()))) &&
            ((this.sendableDataExtensionField==null && other.getSendableDataExtensionField()==null) || 
             (this.sendableDataExtensionField!=null &&
              this.sendableDataExtensionField.equals(other.getSendableDataExtensionField()))) &&
            ((this.sendableSubscriberField==null && other.getSendableSubscriberField()==null) || 
             (this.sendableSubscriberField!=null &&
              this.sendableSubscriberField.equals(other.getSendableSubscriberField()))) &&
            ((this.template==null && other.getTemplate()==null) || 
             (this.template!=null &&
              this.template.equals(other.getTemplate()))) &&
            ((this.dataRetentionPeriodLength==null && other.getDataRetentionPeriodLength()==null) || 
             (this.dataRetentionPeriodLength!=null &&
              this.dataRetentionPeriodLength.equals(other.getDataRetentionPeriodLength()))) &&
            ((this.dataRetentionPeriodUnitOfMeasure==null && other.getDataRetentionPeriodUnitOfMeasure()==null) || 
             (this.dataRetentionPeriodUnitOfMeasure!=null &&
              this.dataRetentionPeriodUnitOfMeasure.equals(other.getDataRetentionPeriodUnitOfMeasure()))) &&
            ((this.rowBasedRetention==null && other.getRowBasedRetention()==null) || 
             (this.rowBasedRetention!=null &&
              this.rowBasedRetention.equals(other.getRowBasedRetention()))) &&
            ((this.resetRetentionPeriodOnImport==null && other.getResetRetentionPeriodOnImport()==null) || 
             (this.resetRetentionPeriodOnImport!=null &&
              this.resetRetentionPeriodOnImport.equals(other.getResetRetentionPeriodOnImport()))) &&
            ((this.deleteAtEndOfRetentionPeriod==null && other.getDeleteAtEndOfRetentionPeriod()==null) || 
             (this.deleteAtEndOfRetentionPeriod!=null &&
              this.deleteAtEndOfRetentionPeriod.equals(other.getDeleteAtEndOfRetentionPeriod()))) &&
            ((this.retainUntil==null && other.getRetainUntil()==null) || 
             (this.retainUntil!=null &&
              this.retainUntil.equals(other.getRetainUntil()))) &&
            ((this.fields==null && other.getFields()==null) || 
             (this.fields!=null &&
              java.util.Arrays.equals(this.fields, other.getFields()))) &&
            ((this.dataRetentionPeriod==null && other.getDataRetentionPeriod()==null) || 
             (this.dataRetentionPeriod!=null &&
              this.dataRetentionPeriod.equals(other.getDataRetentionPeriod()))) &&
            ((this.categoryID==null && other.getCategoryID()==null) || 
             (this.categoryID!=null &&
              this.categoryID.equals(other.getCategoryID()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus())));
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
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getIsSendable() != null) {
            _hashCode += getIsSendable().hashCode();
        }
        if (getIsTestable() != null) {
            _hashCode += getIsTestable().hashCode();
        }
        if (getSendableDataExtensionField() != null) {
            _hashCode += getSendableDataExtensionField().hashCode();
        }
        if (getSendableSubscriberField() != null) {
            _hashCode += getSendableSubscriberField().hashCode();
        }
        if (getTemplate() != null) {
            _hashCode += getTemplate().hashCode();
        }
        if (getDataRetentionPeriodLength() != null) {
            _hashCode += getDataRetentionPeriodLength().hashCode();
        }
        if (getDataRetentionPeriodUnitOfMeasure() != null) {
            _hashCode += getDataRetentionPeriodUnitOfMeasure().hashCode();
        }
        if (getRowBasedRetention() != null) {
            _hashCode += getRowBasedRetention().hashCode();
        }
        if (getResetRetentionPeriodOnImport() != null) {
            _hashCode += getResetRetentionPeriodOnImport().hashCode();
        }
        if (getDeleteAtEndOfRetentionPeriod() != null) {
            _hashCode += getDeleteAtEndOfRetentionPeriod().hashCode();
        }
        if (getRetainUntil() != null) {
            _hashCode += getRetainUntil().hashCode();
        }
        if (getFields() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getFields());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getFields(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDataRetentionPeriod() != null) {
            _hashCode += getDataRetentionPeriod().hashCode();
        }
        if (getCategoryID() != null) {
            _hashCode += getCategoryID().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DataExtension.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataExtension"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isSendable");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsSendable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isTestable");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsTestable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sendableDataExtensionField");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendableDataExtensionField"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataExtensionField"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sendableSubscriberField");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "SendableSubscriberField"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Attribute"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("template");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Template"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataExtensionTemplate"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataRetentionPeriodLength");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataRetentionPeriodLength"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataRetentionPeriodUnitOfMeasure");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataRetentionPeriodUnitOfMeasure"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rowBasedRetention");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RowBasedRetention"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resetRetentionPeriodOnImport");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ResetRetentionPeriodOnImport"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deleteAtEndOfRetentionPeriod");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DeleteAtEndOfRetentionPeriod"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("retainUntil");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "RetainUntil"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fields");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Fields"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataExtensionField"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Field"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataRetentionPeriod");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DataRetentionPeriod"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "DateTimeUnitOfMeasure"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("categoryID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "CategoryID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Status"));
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
