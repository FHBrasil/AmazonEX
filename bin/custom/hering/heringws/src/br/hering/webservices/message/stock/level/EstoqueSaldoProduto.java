
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
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}codigoSite"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}sku"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}estoque"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}estoqueVirtual"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}permiteVirtual"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}tempoEntregaVirtual"/>
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
    "estoque",
    "estoqueVirtual",
    "permiteVirtual",
    "tempoEntregaVirtual"
})
@XmlRootElement(name = "estoqueSaldoProduto", namespace = "http://hering.fliegersoftware.de/interface/soap/")
public class EstoqueSaldoProduto {

    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String codigoSite;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String sku;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/")
    protected int estoque;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/")
    protected int estoqueVirtual;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/")
    protected boolean permiteVirtual;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/")
    protected int tempoEntregaVirtual;

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
     * Gets the value of the estoque property.
     * 
     */
    public int getEstoque() {
        return estoque;
    }

    /**
     * Sets the value of the estoque property.
     * 
     */
    public void setEstoque(int value) {
        this.estoque = value;
    }

    /**
     * Gets the value of the estoqueVirtual property.
     * 
     */
    public int getEstoqueVirtual() {
        return estoqueVirtual;
    }

    /**
     * Sets the value of the estoqueVirtual property.
     * 
     */
    public void setEstoqueVirtual(int value) {
        this.estoqueVirtual = value;
    }

    /**
     * Gets the value of the permiteVirtual property.
     * 
     */
    public boolean isPermiteVirtual() {
        return permiteVirtual;
    }

    /**
     * Sets the value of the permiteVirtual property.
     * 
     */
    public void setPermiteVirtual(boolean value) {
        this.permiteVirtual = value;
    }

    /**
     * Gets the value of the tempoEntregaVirtual property.
     * 
     */
    public int getTempoEntregaVirtual() {
        return tempoEntregaVirtual;
    }

    /**
     * Sets the value of the tempoEntregaVirtual property.
     * 
     */
    public void setTempoEntregaVirtual(int value) {
        this.tempoEntregaVirtual = value;
    }

}
