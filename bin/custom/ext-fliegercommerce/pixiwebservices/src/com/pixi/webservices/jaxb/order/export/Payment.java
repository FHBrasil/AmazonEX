//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/com.pixi.webservices.jaxb/jaxb">http://java.sun.com/com.pixi.webservices.jaxb/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.30 at 04:28:15 PM CEST 
//


package com.pixi.webservices.jaxb.order.export;

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
 *         &lt;element name="PAGAMENTO_TEMP" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
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
    "pagamentotemp"
})
@XmlRootElement(name = "PAYMENT")
public class Payment {

    @XmlElement(name = "PAGAMENTO_TEMP", required = true)
    protected Object pagamentotemp;

    /**
     * Gets the value of the pagamentotemp property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getPAGAMENTOTEMP() {
        return pagamentotemp;
    }

    /**
     * Sets the value of the pagamentotemp property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setPAGAMENTOTEMP(Object value) {
        this.pagamentotemp = value;
    }

}
