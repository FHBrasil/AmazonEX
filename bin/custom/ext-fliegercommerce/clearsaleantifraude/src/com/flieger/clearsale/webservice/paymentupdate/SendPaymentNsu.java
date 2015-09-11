
package com.flieger.clearsale.webservice.paymentupdate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="entityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nsu" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cardType" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="cardBin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cardEndNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "entityCode",
    "orderID",
    "nsu",
    "cardType",
    "cardBin",
    "cardEndNumber"
})
@XmlRootElement(name = "SendPaymentNsu")
public class SendPaymentNsu {

    protected String entityCode;
    protected String orderID;
    protected String nsu;
    protected int cardType;
    protected String cardBin;
    protected String cardEndNumber;

    /**
     * Gets the value of the entityCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntityCode() {
        return entityCode;
    }

    /**
     * Sets the value of the entityCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntityCode(String value) {
        this.entityCode = value;
    }

    /**
     * Gets the value of the orderID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderID() {
        return orderID;
    }

    /**
     * Sets the value of the orderID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderID(String value) {
        this.orderID = value;
    }

    /**
     * Gets the value of the nsu property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNsu() {
        return nsu;
    }

    /**
     * Sets the value of the nsu property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNsu(String value) {
        this.nsu = value;
    }

    /**
     * Gets the value of the cardType property.
     * 
     */
    public int getCardType() {
        return cardType;
    }

    /**
     * Sets the value of the cardType property.
     * 
     */
    public void setCardType(int value) {
        this.cardType = value;
    }

    /**
     * Gets the value of the cardBin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardBin() {
        return cardBin;
    }

    /**
     * Sets the value of the cardBin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardBin(String value) {
        this.cardBin = value;
    }

    /**
     * Gets the value of the cardEndNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardEndNumber() {
        return cardEndNumber;
    }

    /**
     * Sets the value of the cardEndNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardEndNumber(String value) {
        this.cardEndNumber = value;
    }

}
