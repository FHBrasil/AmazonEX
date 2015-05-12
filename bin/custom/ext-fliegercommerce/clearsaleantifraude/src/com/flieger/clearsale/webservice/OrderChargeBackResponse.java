
package com.flieger.clearsale.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OrderChargeBackResult" type="{http://www.clearsale.com.br/integration}TransactionStatusCbk" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "orderChargeBackResult"
})
@XmlRootElement(name = "OrderChargeBackResponse")
public class OrderChargeBackResponse {

    @XmlElement(name = "OrderChargeBackResult")
    protected TransactionStatusCbk orderChargeBackResult;

    /**
     * Gets the value of the orderChargeBackResult property.
     * 
     * @return
     *     possible object is
     *     {@link TransactionStatusCbk }
     *     
     */
    public TransactionStatusCbk getOrderChargeBackResult() {
        return orderChargeBackResult;
    }

    /**
     * Sets the value of the orderChargeBackResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransactionStatusCbk }
     *     
     */
    public void setOrderChargeBackResult(TransactionStatusCbk value) {
        this.orderChargeBackResult = value;
    }

}
