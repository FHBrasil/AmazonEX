
package br.hering.webservices.message.client;

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
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}codigoCliente"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}nome"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}codigoSite"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}pfPj"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}rgIe"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}cpfCgc"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}ddd"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}telefone"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}aniversario"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}dataCadastramento"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}sexo"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}email"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}senha" minOccurs="0"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}newsletter"/>
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
    "codigoCliente",
    "nome",
    "codigoSite",
    "pfPj",
    "rgIe",
    "cpfCgc",
    "ddd",
    "telefone",
    "aniversario",
    "dataCadastramento",
    "sexo",
    "email",
    "senha",
    "newsletter"
})
@XmlRootElement(name = "cliente", namespace = "http://hering.fliegersoftware.de/interface/soap/")
public class Cliente {

    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String codigoCliente;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String nome;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String codigoSite;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/")
    protected boolean pfPj;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String rgIe;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String cpfCgc;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String ddd;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String telefone;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String aniversario;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String dataCadastramento;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String sexo;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String email;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/")
    protected String senha;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/")
    protected boolean newsletter;

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
     * Gets the value of the nome property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNome() {
        return nome;
    }

    /**
     * Sets the value of the nome property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNome(String value) {
        this.nome = value;
    }

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
     * Gets the value of the pfPj property.
     * 
     */
    public boolean isPfPj() {
        return pfPj;
    }

    /**
     * Sets the value of the pfPj property.
     * 
     */
    public void setPfPj(boolean value) {
        this.pfPj = value;
    }

    /**
     * Gets the value of the rgIe property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRgIe() {
        return rgIe;
    }

    /**
     * Sets the value of the rgIe property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRgIe(String value) {
        this.rgIe = value;
    }

    /**
     * Gets the value of the cpfCgc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCpfCgc() {
        return cpfCgc;
    }

    /**
     * Sets the value of the cpfCgc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCpfCgc(String value) {
        this.cpfCgc = value;
    }

    /**
     * Gets the value of the ddd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDdd() {
        return ddd;
    }

    /**
     * Sets the value of the ddd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDdd(String value) {
        this.ddd = value;
    }

    /**
     * Gets the value of the telefone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Sets the value of the telefone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelefone(String value) {
        this.telefone = value;
    }

    /**
     * Gets the value of the aniversario property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAniversario() {
        return aniversario;
    }

    /**
     * Sets the value of the aniversario property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAniversario(String value) {
        this.aniversario = value;
    }

    /**
     * Gets the value of the dataCadastramento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataCadastramento() {
        return dataCadastramento;
    }

    /**
     * Sets the value of the dataCadastramento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataCadastramento(String value) {
        this.dataCadastramento = value;
    }

    /**
     * Gets the value of the sexo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * Sets the value of the sexo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSexo(String value) {
        this.sexo = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the senha property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSenha() {
        return senha;
    }

    /**
     * Sets the value of the senha property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSenha(String value) {
        this.senha = value;
    }

    /**
     * Gets the value of the newsletter property.
     * 
     */
    public boolean isNewsletter() {
        return newsletter;
    }

    /**
     * Sets the value of the newsletter property.
     * 
     */
    public void setNewsletter(boolean value) {
        this.newsletter = value;
    }

}
