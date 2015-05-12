
package br.hering.fulfilmentprocess.message.order;

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
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}codigoEndereco"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}descricaoEndereco"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}pais"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}cep"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}bairro"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}cidade"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}complemento" minOccurs="0"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}numero"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}logradouro"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}uf"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}enderecoPrincipal"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}tipoEndereco"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}estadoExterior" minOccurs="0"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}ddd"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}telefone"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}dddCelular" minOccurs="0"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}celular" minOccurs="0"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}codigoCliente"/>
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
    "codigoEndereco",
    "descricaoEndereco",
    "pais",
    "cep",
    "bairro",
    "cidade",
    "complemento",
    "numero",
    "logradouro",
    "uf",
    "enderecoPrincipal",
    "tipoEndereco",
    "estadoExterior",
    "ddd",
    "telefone",
    "dddCelular",
    "celular",
    "codigoCliente"
})
@XmlRootElement(name = "endereco", namespace = "http://hering.fliegersoftware.de/interface/soap/")
public class Endereco {

    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String codigoEndereco;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String descricaoEndereco;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String pais;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String cep;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String bairro;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String cidade;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/")
    protected String complemento;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String numero;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String logradouro;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String uf;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/")
    protected boolean enderecoPrincipal;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String tipoEndereco;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/")
    protected String estadoExterior;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String ddd;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String telefone;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/")
    protected String dddCelular;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/")
    protected String celular;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String codigoCliente;

    /**
     * Gets the value of the codigoEndereco property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoEndereco() {
        return codigoEndereco;
    }

    /**
     * Sets the value of the codigoEndereco property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoEndereco(String value) {
        this.codigoEndereco = value;
    }

    /**
     * Gets the value of the descricaoEndereco property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescricaoEndereco() {
        return descricaoEndereco;
    }

    /**
     * Sets the value of the descricaoEndereco property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescricaoEndereco(String value) {
        this.descricaoEndereco = value;
    }

    /**
     * Gets the value of the pais property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPais() {
        return pais;
    }

    /**
     * Sets the value of the pais property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPais(String value) {
        this.pais = value;
    }

    /**
     * Gets the value of the cep property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCep() {
        return cep;
    }

    /**
     * Sets the value of the cep property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCep(String value) {
        this.cep = value;
    }

    /**
     * Gets the value of the bairro property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * Sets the value of the bairro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBairro(String value) {
        this.bairro = value;
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
     * Gets the value of the complemento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComplemento() {
        return complemento;
    }

    /**
     * Sets the value of the complemento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComplemento(String value) {
        this.complemento = value;
    }

    /**
     * Gets the value of the numero property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Sets the value of the numero property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumero(String value) {
        this.numero = value;
    }

    /**
     * Gets the value of the logradouro property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogradouro() {
        return logradouro;
    }

    /**
     * Sets the value of the logradouro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogradouro(String value) {
        this.logradouro = value;
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
     * Gets the value of the enderecoPrincipal property.
     * 
     */
    public boolean isEnderecoPrincipal() {
        return enderecoPrincipal;
    }

    /**
     * Sets the value of the enderecoPrincipal property.
     * 
     */
    public void setEnderecoPrincipal(boolean value) {
        this.enderecoPrincipal = value;
    }

    /**
     * Gets the value of the tipoEndereco property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoEndereco() {
        return tipoEndereco;
    }

    /**
     * Sets the value of the tipoEndereco property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoEndereco(String value) {
        this.tipoEndereco = value;
    }

    /**
     * Gets the value of the estadoExterior property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstadoExterior() {
        return estadoExterior;
    }

    /**
     * Sets the value of the estadoExterior property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstadoExterior(String value) {
        this.estadoExterior = value;
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
     * Gets the value of the dddCelular property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDddCelular() {
        return dddCelular;
    }

    /**
     * Sets the value of the dddCelular property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDddCelular(String value) {
        this.dddCelular = value;
    }

    /**
     * Gets the value of the celular property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCelular() {
        return celular;
    }

    /**
     * Sets the value of the celular property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCelular(String value) {
        this.celular = value;
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

	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoEndereco == null) ? 0 : codigoEndereco.hashCode());
		return result;
	}

	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Endereco other = (Endereco) obj;
		if (codigoEndereco == null)
		{
			if (other.codigoEndereco != null)
				return false;
		}
		else if (!codigoEndereco.equals(other.codigoEndereco))
			return false;
		return true;
	}

    
    
}
