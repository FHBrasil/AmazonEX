
package com.flieger.clearsale.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TransactionStatusCbk complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TransactionStatusCbk">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.clearsale.com.br/integration}TransactionStatus">
 *       &lt;sequence>
 *         &lt;element name="Orders" type="{http://www.clearsale.com.br/integration}ArrayOfOrder" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TransactionStatusCbk", propOrder = {
    "orders"
})
public class TransactionStatusCbk
    extends TransactionStatus
{

    @XmlElement(name = "Orders")
    protected ArrayOfOrder orders;

    /**
     * Gets the value of the orders property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfOrder }
     *     
     */
    public ArrayOfOrder getOrders() {
        return orders;
    }

    /**
     * Sets the value of the orders property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfOrder }
     *     
     */
    public void setOrders(ArrayOfOrder value) {
        this.orders = value;
    }

}
