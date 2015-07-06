
package com.flieger.payment.api.tempuri;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.flieger.payment.api.data.schemas.CreateOrderRequest;


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
 *         &lt;element name="createOrderRequest" type="{http://schemas.datacontract.org/2004/07/MundiPagg.One.Service.DataContracts}CreateOrderRequest" minOccurs="0"/>
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
    "createOrderRequest"
})
@XmlRootElement(name = "CreateOrder")
public class CreateOrder {

    @XmlElementRef(name = "createOrderRequest", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<CreateOrderRequest> createOrderRequest;

    /**
     * Gets the value of the createOrderRequest property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link CreateOrderRequest }{@code >}
     *     
     */
    public JAXBElement<CreateOrderRequest> getCreateOrderRequest() {
        return createOrderRequest;
    }

    /**
     * Sets the value of the createOrderRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link CreateOrderRequest }{@code >}
     *     
     */
    public void setCreateOrderRequest(JAXBElement<CreateOrderRequest> value) {
        this.createOrderRequest = value;
    }

}
