
package br.hering.webservices.message.stock.level;

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
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}estoqueSaldoProduto"/>
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
    "estoqueSaldoProduto"
})
@XmlRootElement(name = "importarEstoqueSaldoProduto", namespace = "http://hering.fliegersoftware.de/interface/soap/")
public class ImportarEstoqueSaldoProduto {

    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected EstoqueSaldoProduto estoqueSaldoProduto;

    /**
     * Gets the value of the estoqueSaldoProduto property.
     * 
     * @return
     *     possible object is
     *     {@link EstoqueSaldoProduto }
     *     
     */
    public EstoqueSaldoProduto getEstoqueSaldoProduto() {
        return estoqueSaldoProduto;
    }

    /**
     * Sets the value of the estoqueSaldoProduto property.
     * 
     * @param value
     *     allowed object is
     *     {@link EstoqueSaldoProduto }
     *     
     */
    public void setEstoqueSaldoProduto(EstoqueSaldoProduto value) {
        this.estoqueSaldoProduto = value;
    }

}
