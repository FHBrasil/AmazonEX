
package com.flieger.payment.api.tempuri;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.flieger.payment.api.data.schemas.GetInstantBuyDataRequest;


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
 *         &lt;element name="queryCreditCardDataRequest" type="{http://schemas.datacontract.org/2004/07/MundiPagg.One.Service.DataContracts}GetInstantBuyDataRequest" minOccurs="0"/>
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
    "queryCreditCardDataRequest"
})
@XmlRootElement(name = "GetInstantBuyData")
public class GetInstantBuyData {

    @XmlElementRef(name = "queryCreditCardDataRequest", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<GetInstantBuyDataRequest> queryCreditCardDataRequest;

    /**
     * Gets the value of the queryCreditCardDataRequest property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link GetInstantBuyDataRequest }{@code >}
     *     
     */
    public JAXBElement<GetInstantBuyDataRequest> getQueryCreditCardDataRequest() {
        return queryCreditCardDataRequest;
    }

    /**
     * Sets the value of the queryCreditCardDataRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link GetInstantBuyDataRequest }{@code >}
     *     
     */
    public void setQueryCreditCardDataRequest(JAXBElement<GetInstantBuyDataRequest> value) {
        this.queryCreditCardDataRequest = value;
    }

}
