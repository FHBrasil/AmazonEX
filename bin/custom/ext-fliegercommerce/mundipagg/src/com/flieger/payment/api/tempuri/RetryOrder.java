
package com.flieger.payment.api.tempuri;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.flieger.payment.api.data.schemas.RetryOrderRequest;


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
 *         &lt;element name="manualRetryRequest" type="{http://schemas.datacontract.org/2004/07/MundiPagg.One.Service.DataContracts}RetryOrderRequest" minOccurs="0"/>
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
    "manualRetryRequest"
})
@XmlRootElement(name = "RetryOrder")
public class RetryOrder {

    @XmlElementRef(name = "manualRetryRequest", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<RetryOrderRequest> manualRetryRequest;

    /**
     * Gets the value of the manualRetryRequest property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link RetryOrderRequest }{@code >}
     *     
     */
    public JAXBElement<RetryOrderRequest> getManualRetryRequest() {
        return manualRetryRequest;
    }

    /**
     * Sets the value of the manualRetryRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link RetryOrderRequest }{@code >}
     *     
     */
    public void setManualRetryRequest(JAXBElement<RetryOrderRequest> value) {
        this.manualRetryRequest = value;
    }

}
