
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
 *         &lt;element name="SendOrders2Result" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "sendOrders2Result"
})
@XmlRootElement(name = "SendOrders2Response")
public class SendOrders2Response {

    @XmlElement(name = "SendOrders2Result")
    protected String sendOrders2Result;

    /**
     * Gets the value of the sendOrders2Result property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSendOrders2Result() {
        return sendOrders2Result;
    }

    /**
     * Sets the value of the sendOrders2Result property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSendOrders2Result(String value) {
        this.sendOrders2Result = value;
    }

}
