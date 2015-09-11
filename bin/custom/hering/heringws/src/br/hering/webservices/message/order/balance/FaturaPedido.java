
package br.hering.webservices.message.order.balance;

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
 *         &lt;element name="codigoSite" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codigoCliente" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codigoPedido" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="statusPedido" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dataFaturamento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codigoRastreio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroNotaFiscal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="chaveNotaFiscal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "codigoCliente",
    "codigoPedido",
    "statusPedido",
    "dataFaturamento",
    "codigoRastreio",
    "numeroNotaFiscal",
    "chaveNotaFiscal"
})
@XmlRootElement(name = "faturaPedido", namespace = "http://hering.fliegersoftware.de/interface/soap/")
public class FaturaPedido {

    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String codigoSite;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String codigoCliente;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String codigoPedido;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String statusPedido;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String dataFaturamento;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/")
    protected String codigoRastreio;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/")
    protected String numeroNotaFiscal;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/")
    protected String chaveNotaFiscal;

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
     * Gets the value of the codigoCliente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoCliente() {
        return codigoCliente;
    }

    /**
     * Sets the value of the codigoCliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoCliente(String value) {
        this.codigoCliente = value;
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
     * Gets the value of the statusPedido property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusPedido() {
        return statusPedido;
    }

    /**
     * Sets the value of the statusPedido property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusPedido(String value) {
        this.statusPedido = value;
    }

    /**
     * Gets the value of the dataFaturamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataFaturamento() {
        return dataFaturamento;
    }

    /**
     * Sets the value of the dataFaturamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataFaturamento(String value) {
        this.dataFaturamento = value;
    }

    /**
     * Gets the value of the codigoRastreio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoRastreio() {
        return codigoRastreio;
    }

    /**
     * Sets the value of the codigoRastreio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoRastreio(String value) {
        this.codigoRastreio = value;
    }

    /**
     * Gets the value of the numeroNotaFiscal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroNotaFiscal() {
        return numeroNotaFiscal;
    }

    /**
     * Sets the value of the numeroNotaFiscal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroNotaFiscal(String value) {
        this.numeroNotaFiscal = value;
    }

    /**
     * Gets the value of the chaveNotaFiscal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChaveNotaFiscal() {
        return chaveNotaFiscal;
    }

    /**
     * Sets the value of the chaveNotaFiscal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChaveNotaFiscal(String value) {
        this.chaveNotaFiscal = value;
    }

}
