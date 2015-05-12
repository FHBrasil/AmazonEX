/**
 * ValidationResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.exacttarget.wsdl.partnerAPI;

public class ValidationResult  implements java.io.Serializable {
    private com.exacttarget.wsdl.partnerAPI.Subscriber subscriber;

    private java.util.Calendar checkTime;

    private java.util.Calendar checkTimeUTC;

    private java.lang.Boolean isResultValid;

    private java.lang.Boolean isSpam;

    private java.lang.Double score;

    private java.lang.Double threshold;

    private java.lang.String message;

    public ValidationResult() {
    }

    public ValidationResult(
           com.exacttarget.wsdl.partnerAPI.Subscriber subscriber,
           java.util.Calendar checkTime,
           java.util.Calendar checkTimeUTC,
           java.lang.Boolean isResultValid,
           java.lang.Boolean isSpam,
           java.lang.Double score,
           java.lang.Double threshold,
           java.lang.String message) {
           this.subscriber = subscriber;
           this.checkTime = checkTime;
           this.checkTimeUTC = checkTimeUTC;
           this.isResultValid = isResultValid;
           this.isSpam = isSpam;
           this.score = score;
           this.threshold = threshold;
           this.message = message;
    }


    /**
     * Gets the subscriber value for this ValidationResult.
     * 
     * @return subscriber
     */
    public com.exacttarget.wsdl.partnerAPI.Subscriber getSubscriber() {
        return subscriber;
    }


    /**
     * Sets the subscriber value for this ValidationResult.
     * 
     * @param subscriber
     */
    public void setSubscriber(com.exacttarget.wsdl.partnerAPI.Subscriber subscriber) {
        this.subscriber = subscriber;
    }


    /**
     * Gets the checkTime value for this ValidationResult.
     * 
     * @return checkTime
     */
    public java.util.Calendar getCheckTime() {
        return checkTime;
    }


    /**
     * Sets the checkTime value for this ValidationResult.
     * 
     * @param checkTime
     */
    public void setCheckTime(java.util.Calendar checkTime) {
        this.checkTime = checkTime;
    }


    /**
     * Gets the checkTimeUTC value for this ValidationResult.
     * 
     * @return checkTimeUTC
     */
    public java.util.Calendar getCheckTimeUTC() {
        return checkTimeUTC;
    }


    /**
     * Sets the checkTimeUTC value for this ValidationResult.
     * 
     * @param checkTimeUTC
     */
    public void setCheckTimeUTC(java.util.Calendar checkTimeUTC) {
        this.checkTimeUTC = checkTimeUTC;
    }


    /**
     * Gets the isResultValid value for this ValidationResult.
     * 
     * @return isResultValid
     */
    public java.lang.Boolean getIsResultValid() {
        return isResultValid;
    }


    /**
     * Sets the isResultValid value for this ValidationResult.
     * 
     * @param isResultValid
     */
    public void setIsResultValid(java.lang.Boolean isResultValid) {
        this.isResultValid = isResultValid;
    }


    /**
     * Gets the isSpam value for this ValidationResult.
     * 
     * @return isSpam
     */
    public java.lang.Boolean getIsSpam() {
        return isSpam;
    }


    /**
     * Sets the isSpam value for this ValidationResult.
     * 
     * @param isSpam
     */
    public void setIsSpam(java.lang.Boolean isSpam) {
        this.isSpam = isSpam;
    }


    /**
     * Gets the score value for this ValidationResult.
     * 
     * @return score
     */
    public java.lang.Double getScore() {
        return score;
    }


    /**
     * Sets the score value for this ValidationResult.
     * 
     * @param score
     */
    public void setScore(java.lang.Double score) {
        this.score = score;
    }


    /**
     * Gets the threshold value for this ValidationResult.
     * 
     * @return threshold
     */
    public java.lang.Double getThreshold() {
        return threshold;
    }


    /**
     * Sets the threshold value for this ValidationResult.
     * 
     * @param threshold
     */
    public void setThreshold(java.lang.Double threshold) {
        this.threshold = threshold;
    }


    /**
     * Gets the message value for this ValidationResult.
     * 
     * @return message
     */
    public java.lang.String getMessage() {
        return message;
    }


    /**
     * Sets the message value for this ValidationResult.
     * 
     * @param message
     */
    public void setMessage(java.lang.String message) {
        this.message = message;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ValidationResult)) return false;
        ValidationResult other = (ValidationResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.subscriber==null && other.getSubscriber()==null) || 
             (this.subscriber!=null &&
              this.subscriber.equals(other.getSubscriber()))) &&
            ((this.checkTime==null && other.getCheckTime()==null) || 
             (this.checkTime!=null &&
              this.checkTime.equals(other.getCheckTime()))) &&
            ((this.checkTimeUTC==null && other.getCheckTimeUTC()==null) || 
             (this.checkTimeUTC!=null &&
              this.checkTimeUTC.equals(other.getCheckTimeUTC()))) &&
            ((this.isResultValid==null && other.getIsResultValid()==null) || 
             (this.isResultValid!=null &&
              this.isResultValid.equals(other.getIsResultValid()))) &&
            ((this.isSpam==null && other.getIsSpam()==null) || 
             (this.isSpam!=null &&
              this.isSpam.equals(other.getIsSpam()))) &&
            ((this.score==null && other.getScore()==null) || 
             (this.score!=null &&
              this.score.equals(other.getScore()))) &&
            ((this.threshold==null && other.getThreshold()==null) || 
             (this.threshold!=null &&
              this.threshold.equals(other.getThreshold()))) &&
            ((this.message==null && other.getMessage()==null) || 
             (this.message!=null &&
              this.message.equals(other.getMessage())));
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
        if (getSubscriber() != null) {
            _hashCode += getSubscriber().hashCode();
        }
        if (getCheckTime() != null) {
            _hashCode += getCheckTime().hashCode();
        }
        if (getCheckTimeUTC() != null) {
            _hashCode += getCheckTimeUTC().hashCode();
        }
        if (getIsResultValid() != null) {
            _hashCode += getIsResultValid().hashCode();
        }
        if (getIsSpam() != null) {
            _hashCode += getIsSpam().hashCode();
        }
        if (getScore() != null) {
            _hashCode += getScore().hashCode();
        }
        if (getThreshold() != null) {
            _hashCode += getThreshold().hashCode();
        }
        if (getMessage() != null) {
            _hashCode += getMessage().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ValidationResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "ValidationResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subscriber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Subscriber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Subscriber"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("checkTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "CheckTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("checkTimeUTC");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "CheckTimeUTC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isResultValid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsResultValid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isSpam");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "IsSpam"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("score");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Score"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("threshold");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Threshold"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("message");
        elemField.setXmlName(new javax.xml.namespace.QName("http://exacttarget.com/wsdl/partnerAPI", "Message"));
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
