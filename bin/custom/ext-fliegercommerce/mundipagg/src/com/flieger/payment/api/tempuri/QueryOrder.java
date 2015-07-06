
package com.flieger.payment.api.tempuri;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.flieger.payment.api.data.schemas.QueryOrderRequest;


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
 *         &lt;element name="queryOrderRequest" type="{http://schemas.datacontract.org/2004/07/MundiPagg.One.Service.DataContracts}QueryOrderRequest" minOccurs="0"/>
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
    "queryOrderRequest"
})
@XmlRootElement(name = "QueryOrder")
public class QueryOrder {

    @XmlElementRef(name = "queryOrderRequest", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<QueryOrderRequest> queryOrderRequest;

    /**
     * Gets the value of the queryOrderRequest property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link QueryOrderRequest }{@code >}
     *     
     */
    public JAXBElement<QueryOrderRequest> getQueryOrderRequest() {
        return queryOrderRequest;
    }

    /**
     * Sets the value of the queryOrderRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link QueryOrderRequest }{@code >}
     *     
     */
    public void setQueryOrderRequest(JAXBElement<QueryOrderRequest> value) {
        this.queryOrderRequest = value;
    }

}
