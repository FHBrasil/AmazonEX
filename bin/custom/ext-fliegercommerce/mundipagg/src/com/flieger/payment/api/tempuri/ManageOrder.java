
package com.flieger.payment.api.tempuri;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.flieger.payment.api.data.schemas.ManageOrderRequest;


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
 *         &lt;element name="manageOrderRequest" type="{http://schemas.datacontract.org/2004/07/MundiPagg.One.Service.DataContracts}ManageOrderRequest" minOccurs="0"/>
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
    "manageOrderRequest"
})
@XmlRootElement(name = "ManageOrder")
public class ManageOrder {

    @XmlElementRef(name = "manageOrderRequest", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<ManageOrderRequest> manageOrderRequest;

    /**
     * Gets the value of the manageOrderRequest property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ManageOrderRequest }{@code >}
     *     
     */
    public JAXBElement<ManageOrderRequest> getManageOrderRequest() {
        return manageOrderRequest;
    }

    /**
     * Sets the value of the manageOrderRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ManageOrderRequest }{@code >}
     *     
     */
    public void setManageOrderRequest(JAXBElement<ManageOrderRequest> value) {
        this.manageOrderRequest = value;
    }

}
