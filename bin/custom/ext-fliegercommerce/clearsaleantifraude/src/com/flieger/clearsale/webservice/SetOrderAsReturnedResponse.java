
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
 *         &lt;element name="SetOrderAsReturnedResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "setOrderAsReturnedResult"
})
@XmlRootElement(name = "SetOrderAsReturnedResponse")
public class SetOrderAsReturnedResponse {

    @XmlElement(name = "SetOrderAsReturnedResult")
    protected String setOrderAsReturnedResult;

    /**
     * Gets the value of the setOrderAsReturnedResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSetOrderAsReturnedResult() {
        return setOrderAsReturnedResult;
    }

    /**
     * Sets the value of the setOrderAsReturnedResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSetOrderAsReturnedResult(String value) {
        this.setOrderAsReturnedResult = value;
    }

}
