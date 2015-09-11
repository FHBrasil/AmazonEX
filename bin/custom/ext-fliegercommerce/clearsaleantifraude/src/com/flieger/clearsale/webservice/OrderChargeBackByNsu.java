
package com.flieger.clearsale.webservice;

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
 *         &lt;element name="cartaoBandeira" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="nsu" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="conciliadorCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "cartaoBandeira",
    "nsu",
    "conciliadorCode"
})
@XmlRootElement(name = "OrderChargeBackByNsu")
public class OrderChargeBackByNsu {

    protected String entityCode;
    protected int cartaoBandeira;
    protected String nsu;
    protected String conciliadorCode;

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
     * Gets the value of the cartaoBandeira property.
     * 
     */
    public int getCartaoBandeira() {
        return cartaoBandeira;
    }

    /**
     * Sets the value of the cartaoBandeira property.
     * 
     */
    public void setCartaoBandeira(int value) {
        this.cartaoBandeira = value;
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
     * Gets the value of the conciliadorCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConciliadorCode() {
        return conciliadorCode;
    }

    /**
     * Sets the value of the conciliadorCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConciliadorCode(String value) {
        this.conciliadorCode = value;
    }

}
