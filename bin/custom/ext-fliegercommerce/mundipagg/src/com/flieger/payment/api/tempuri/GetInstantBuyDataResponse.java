
package com.flieger.payment.api.tempuri;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
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
 *         &lt;element name="GetInstantBuyDataResult" type="{http://schemas.datacontract.org/2004/07/MundiPagg.One.Service.DataContracts}GetInstantBuyDataResponse" minOccurs="0"/>
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
    "getInstantBuyDataResult"
})
@XmlRootElement(name = "GetInstantBuyDataResponse")
public class GetInstantBuyDataResponse {

    @XmlElementRef(name = "GetInstantBuyDataResult", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<com.flieger.payment.api.data.schemas.GetInstantBuyDataResponse> getInstantBuyDataResult;

    /**
     * Gets the value of the getInstantBuyDataResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link org.datacontract.schemas._2004._07.mundipagg_one_service.GetInstantBuyDataResponse }{@code >}
     *     
     */
    public JAXBElement<com.flieger.payment.api.data.schemas.GetInstantBuyDataResponse> getGetInstantBuyDataResult() {
        return getInstantBuyDataResult;
    }

    /**
     * Sets the value of the getInstantBuyDataResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link org.datacontract.schemas._2004._07.mundipagg_one_service.GetInstantBuyDataResponse }{@code >}
     *     
     */
    public void setGetInstantBuyDataResult(JAXBElement<com.flieger.payment.api.data.schemas.GetInstantBuyDataResponse> value) {
        this.getInstantBuyDataResult = value;
    }

}
