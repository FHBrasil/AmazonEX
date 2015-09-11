
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
 *         &lt;element name="codigoSite" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codigoTransportadora" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nomeTransportadora" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="formaEnvio" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dataInicialVigencia" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dataFinalVigencia" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "codigoTransportadora",
    "nomeTransportadora",
    "formaEnvio",
    "dataInicialVigencia",
    "dataFinalVigencia"
})
@XmlRootElement(name = "servicoTransporte", namespace = "http://hering.fliegersoftware.de/interface/soap/")
public class ServicoTransporte {

    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String codigoSite;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String codigoTransportadora;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String nomeTransportadora;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String formaEnvio;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String dataInicialVigencia;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String dataFinalVigencia;

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
     * Gets the value of the codigoTransportadora property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoTransportadora() {
        return codigoTransportadora;
    }

    /**
     * Sets the value of the codigoTransportadora property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoTransportadora(String value) {
        this.codigoTransportadora = value;
    }

    /**
     * Gets the value of the nomeTransportadora property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeTransportadora() {
        return nomeTransportadora;
    }

    /**
     * Sets the value of the nomeTransportadora property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeTransportadora(String value) {
        this.nomeTransportadora = value;
    }

    /**
     * Gets the value of the formaEnvio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormaEnvio() {
        return formaEnvio;
    }

    /**
     * Sets the value of the formaEnvio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormaEnvio(String value) {
        this.formaEnvio = value;
    }

    /**
     * Gets the value of the dataInicialVigencia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataInicialVigencia() {
        return dataInicialVigencia;
    }

    /**
     * Sets the value of the dataInicialVigencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataInicialVigencia(String value) {
        this.dataInicialVigencia = value;
    }

    /**
     * Gets the value of the dataFinalVigencia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataFinalVigencia() {
        return dataFinalVigencia;
    }

    /**
     * Sets the value of the dataFinalVigencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataFinalVigencia(String value) {
        this.dataFinalVigencia = value;
    }

}
