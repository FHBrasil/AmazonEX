
package br.hering.webservices.message.price;

import java.math.BigDecimal;
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
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}codigoSite"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}sku"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}preco"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}precoDe"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}ativacao"/>
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
    "codigoSite",
    "sku",
    "preco",
    "precoDe",
    "ativacao"
})
@XmlRootElement(name = "precoProduto", namespace = "http://hering.fliegersoftware.de/interface/soap/")
public class PrecoProduto {

    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String codigoSite;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String sku;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected BigDecimal preco;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected BigDecimal precoDe;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String ativacao;

    /**
     * Gets the value of the codigoSite property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoSite() {
        return codigoSite;
    }

    /**
     * Sets the value of the codigoSite property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoSite(String value) {
        this.codigoSite = value;
    }

    /**
     * Gets the value of the sku property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSku() {
        return sku;
    }

    /**
     * Sets the value of the sku property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSku(String value) {
        this.sku = value;
    }

    /**
     * Gets the value of the preco property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPreco() {
        return preco;
    }

    /**
     * Sets the value of the preco property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPreco(BigDecimal value) {
        this.preco = value;
    }

    /**
     * Gets the value of the precoDe property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPrecoDe() {
        return precoDe;
    }

    /**
     * Sets the value of the precoDe property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPrecoDe(BigDecimal value) {
        this.precoDe = value;
    }

    /**
     * Gets the value of the ativacao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAtivacao() {
        return ativacao;
    }

    /**
     * Sets the value of the ativacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAtivacao(String value) {
        this.ativacao = value;
    }

}
