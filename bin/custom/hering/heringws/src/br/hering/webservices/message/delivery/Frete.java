
package br.hering.webservices.message.delivery;

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
 *         &lt;element name="cepInicial" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cepFinal" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="faixaPesoInicial" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="faixaPesoFinal" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="cidade" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="uf" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="prazoEntrega" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="valorPeso" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="valorPedagio" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="valorPercentual" type="{http://www.w3.org/2001/XMLSchema}double"/>
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
    "cepInicial",
    "cepFinal",
    "faixaPesoInicial",
    "faixaPesoFinal",
    "cidade",
    "uf",
    "prazoEntrega",
    "valorPeso",
    "valorPedagio",
    "valorPercentual"
})
@XmlRootElement(name = "frete", namespace = "http://hering.fliegersoftware.de/interface/soap/")
public class Frete {

    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String cepInicial;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String cepFinal;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/")
    protected int faixaPesoInicial;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/")
    protected int faixaPesoFinal;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/")
    protected String cidade;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/")
    protected String uf;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/")
    protected int prazoEntrega;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/")
    protected double valorPeso;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/")
    protected double valorPedagio;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/")
    protected double valorPercentual;

    /**
     * Gets the value of the cepInicial property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCepInicial() {
        return cepInicial;
    }

    /**
     * Sets the value of the cepInicial property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCepInicial(String value) {
        this.cepInicial = value;
    }

    /**
     * Gets the value of the cepFinal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCepFinal() {
        return cepFinal;
    }

    /**
     * Sets the value of the cepFinal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCepFinal(String value) {
        this.cepFinal = value;
    }

    /**
     * Gets the value of the faixaPesoInicial property.
     * 
     */
    public int getFaixaPesoInicial() {
        return faixaPesoInicial;
    }

    /**
     * Sets the value of the faixaPesoInicial property.
     * 
     */
    public void setFaixaPesoInicial(int value) {
        this.faixaPesoInicial = value;
    }

    /**
     * Gets the value of the faixaPesoFinal property.
     * 
     */
    public int getFaixaPesoFinal() {
        return faixaPesoFinal;
    }

    /**
     * Sets the value of the faixaPesoFinal property.
     * 
     */
    public void setFaixaPesoFinal(int value) {
        this.faixaPesoFinal = value;
    }

    /**
     * Gets the value of the cidade property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * Sets the value of the cidade property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCidade(String value) {
        this.cidade = value;
    }

    /**
     * Gets the value of the uf property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUf() {
        return uf;
    }

    /**
     * Sets the value of the uf property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUf(String value) {
        this.uf = value;
    }

    /**
     * Gets the value of the prazoEntrega property.
     * 
     */
    public int getPrazoEntrega() {
        return prazoEntrega;
    }

    /**
     * Sets the value of the prazoEntrega property.
     * 
     */
    public void setPrazoEntrega(int value) {
        this.prazoEntrega = value;
    }

    /**
     * Gets the value of the valorPeso property.
     * 
     */
    public double getValorPeso() {
        return valorPeso;
    }

    /**
     * Sets the value of the valorPeso property.
     * 
     */
    public void setValorPeso(double value) {
        this.valorPeso = value;
    }

    /**
     * Gets the value of the valorPedagio property.
     * 
     */
    public double getValorPedagio() {
        return valorPedagio;
    }

    /**
     * Sets the value of the valorPedagio property.
     * 
     */
    public void setValorPedagio(double value) {
        this.valorPedagio = value;
    }

    /**
     * Gets the value of the valorPercentual property.
     * 
     */
    public double getValorPercentual() {
        return valorPercentual;
    }

    /**
     * Sets the value of the valorPercentual property.
     * 
     */
    public void setValorPercentual(double value) {
        this.valorPercentual = value;
    }

}
