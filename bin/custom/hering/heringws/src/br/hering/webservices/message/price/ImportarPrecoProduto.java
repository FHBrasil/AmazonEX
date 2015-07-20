
package br.hering.webservices.message.price;

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
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}precoProduto"/>
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
    "precoProduto"
})
@XmlRootElement(name = "importarPrecoProduto", namespace = "http://hering.fliegersoftware.de/interface/soap/")
public class ImportarPrecoProduto {

    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected PrecoProduto precoProduto;

    /**
     * Gets the value of the precoProduto property.
     * 
     * @return
     *     possible object is
     *     {@link PrecoProduto }
     *     
     */
    public PrecoProduto getPrecoProduto() {
        return precoProduto;
    }

    /**
     * Sets the value of the precoProduto property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrecoProduto }
     *     
     */
    public void setPrecoProduto(PrecoProduto value) {
        this.precoProduto = value;
    }

}
