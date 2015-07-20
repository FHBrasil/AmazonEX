
package br.hering.webservices.message.stock;

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
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}estoqueProduto"/>
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
    "estoqueProduto"
})
@XmlRootElement(name = "importarEstoqueProduto", namespace = "http://hering.fliegersoftware.de/interface/soap/")
public class ImportarEstoqueProduto {

    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected EstoqueProduto estoqueProduto;

    /**
     * Gets the value of the estoqueProduto property.
     * 
     * @return
     *     possible object is
     *     {@link EstoqueProduto }
     *     
     */
    public EstoqueProduto getEstoqueProduto() {
        return estoqueProduto;
    }

    /**
     * Sets the value of the estoqueProduto property.
     * 
     * @param value
     *     allowed object is
     *     {@link EstoqueProduto }
     *     
     */
    public void setEstoqueProduto(EstoqueProduto value) {
        this.estoqueProduto = value;
    }

}
