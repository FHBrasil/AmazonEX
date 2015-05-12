
package br.hering.webservices.message.product.size;

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
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}codigoProduto"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}tipo"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}medida"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}tamanho"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}imagem"/>
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
    "codigoProduto",
    "tipo",
    "medida",
    "tamanho",
    "imagem"
})
@XmlRootElement(name = "medidaProduto", namespace = "http://hering.fliegersoftware.de/interface/soap/")
public class MedidaProduto {

    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String codigoSite;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String codigoProduto;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String tipo;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected BigDecimal medida;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String tamanho;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String imagem;

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
     * Gets the value of the tipo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Sets the value of the tipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipo(String value) {
        this.tipo = value;
    }

    /**
     * Gets the value of the medida property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMedida() {
        return medida;
    }

    /**
     * Sets the value of the medida property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMedida(BigDecimal value) {
        this.medida = value;
    }

    /**
     * Gets the value of the tamanho property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTamanho() {
        return tamanho;
    }

    /**
     * Sets the value of the tamanho property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTamanho(String value) {
        this.tamanho = value;
    }

    /**
     * Gets the value of the imagem property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImagem() {
        return imagem;
    }

    /**
     * Sets the value of the imagem property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImagem(String value) {
        this.imagem = value;
    }

}
