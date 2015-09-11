
package br.hering.webservices.message.product;

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
 *         &lt;element name="sku" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ean" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ordem" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="tamanho" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="peso" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}cor"/>
 *         &lt;element name="dataOnline" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dataOffline" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "sku",
    "ean",
    "ordem",
    "tamanho",
    "peso",
    "cor",
    "dataOnline",
    "dataOffline"
})
@XmlRootElement(name = "variacao", namespace = "http://hering.fliegersoftware.de/interface/soap/")
public class Variacao {

    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String sku;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String ean;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/")
    protected int ordem;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String tamanho;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/")
    protected double peso;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected Cor cor;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String dataOnline;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String dataOffline;

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
     * Gets the value of the ean property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEan() {
        return ean;
    }

    /**
     * Sets the value of the ean property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEan(String value) {
        this.ean = value;
    }

    /**
     * Gets the value of the ordem property.
     * 
     */
    public int getOrdem() {
        return ordem;
    }

    /**
     * Sets the value of the ordem property.
     * 
     */
    public void setOrdem(int value) {
        this.ordem = value;
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
     * Gets the value of the peso property.
     * 
     */
    public double getPeso() {
        return peso;
    }

    /**
     * Sets the value of the peso property.
     * 
     */
    public void setPeso(double value) {
        this.peso = value;
    }

    /**
     * Gets the value of the cor property.
     * 
     * @return
     *     possible object is
     *     {@link Cor }
     *     
     */
    public Cor getCor() {
        return cor;
    }

    /**
     * Sets the value of the cor property.
     * 
     * @param value
     *     allowed object is
     *     {@link Cor }
     *     
     */
    public void setCor(Cor value) {
        this.cor = value;
    }

    /**
     * Gets the value of the dataOnline property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataOnline() {
        return dataOnline;
    }

    /**
     * Sets the value of the dataOnline property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataOnline(String value) {
        this.dataOnline = value;
    }

    /**
     * Gets the value of the dataOffline property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataOffline() {
        return dataOffline;
    }

    /**
     * Sets the value of the dataOffline property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataOffline(String value) {
        this.dataOffline = value;
    }

}
