
package br.hering.fulfilmentprocess.message.order;

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
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}codigoPedido"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}codigoProduto"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}corProduto"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}item"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}sku"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}quantidade"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}precoLiquido"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}desconto"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}status"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}origemEstoque" minOccurs="0"/>
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
    "codigoPedido",
    "codigoProduto",
    "corProduto",
    "item",
    "sku",
    "quantidade",
    "precoLiquido",
    "desconto",
    "status",
    "origemEstoque"
})
@XmlRootElement(name = "itens", namespace = "http://hering.fliegersoftware.de/interface/soap/")
public class Itens {

    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String codigoSite;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String codigoPedido;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String codigoProduto;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String corProduto;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String item;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String sku;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/")
    protected int quantidade;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected BigDecimal precoLiquido;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected BigDecimal desconto;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/")
    protected int status;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/")
    protected String origemEstoque;

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
     * Gets the value of the codigoPedido property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoPedido() {
        return codigoPedido;
    }

    /**
     * Sets the value of the codigoPedido property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoPedido(String value) {
        this.codigoPedido = value;
    }

    /**
     * Gets the value of the codigoProduto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoProduto() {
        return codigoProduto;
    }

    /**
     * Sets the value of the codigoProduto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoProduto(String value) {
        this.codigoProduto = value;
    }

    /**
     * Gets the value of the corProduto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorProduto() {
        return corProduto;
    }

    /**
     * Sets the value of the corProduto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorProduto(String value) {
        this.corProduto = value;
    }

    /**
     * Gets the value of the item property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItem() {
        return item;
    }

    /**
     * Sets the value of the item property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItem(String value) {
        this.item = value;
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
     * Gets the value of the quantidade property.
     * 
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * Sets the value of the quantidade property.
     * 
     */
    public void setQuantidade(int value) {
        this.quantidade = value;
    }

    /**
     * Gets the value of the precoLiquido property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPrecoLiquido() {
        return precoLiquido;
    }

    /**
     * Sets the value of the precoLiquido property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPrecoLiquido(BigDecimal value) {
        this.precoLiquido = value;
    }

    /**
     * Gets the value of the desconto property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDesconto() {
        return desconto;
    }

    /**
     * Sets the value of the desconto property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDesconto(BigDecimal value) {
        this.desconto = value;
    }

    /**
     * Gets the value of the status property.
     * 
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     */
    public void setStatus(int value) {
        this.status = value;
    }

    /**
     * Gets the value of the origemEstoque property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrigemEstoque() {
        return origemEstoque;
    }

    /**
     * Sets the value of the origemEstoque property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrigemEstoque(String value) {
        this.origemEstoque = value;
    }

}
