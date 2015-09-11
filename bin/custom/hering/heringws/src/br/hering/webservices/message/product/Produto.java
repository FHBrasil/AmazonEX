
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
 *         &lt;element name="codigoSite" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codigo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="titulo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descricaoFiscal" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descricaoB2C" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="griffe" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="linha" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipoTecido" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="composicao" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sexo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="parte" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tags" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigoCategoria" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codigoSubCategoria" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codigoGrupo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codigoSubGrupo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ocasiao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tendencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoMaterial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}colecao"/>
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
    "codigo",
    "titulo",
    "descricaoFiscal",
    "descricaoB2C",
    "griffe",
    "linha",
    "tipoTecido",
    "composicao",
    "sexo",
    "parte",
    "tags",
    "codigoCategoria",
    "codigoSubCategoria",
    "codigoGrupo",
    "codigoSubGrupo",
    "ocasiao",
    "tendencia",
    "tipoMaterial",
    "colecao"
})
@XmlRootElement(name = "produto", namespace = "http://hering.fliegersoftware.de/interface/soap/")
public class Produto {

    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String codigoSite;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String codigo;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String titulo;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String descricaoFiscal;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String descricaoB2C;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String griffe;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String linha;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String tipoTecido;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String composicao;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String sexo;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String parte;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/")
    protected String tags;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String codigoCategoria;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String codigoSubCategoria;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String codigoGrupo;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String codigoSubGrupo;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/")
    protected String ocasiao;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/")
    protected String tendencia;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/")
    protected String tipoMaterial;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected Colecao colecao;

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
     * Gets the value of the codigo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Sets the value of the codigo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigo(String value) {
        this.codigo = value;
    }

    /**
     * Gets the value of the titulo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Sets the value of the titulo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitulo(String value) {
        this.titulo = value;
    }

    /**
     * Gets the value of the descricaoFiscal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescricaoFiscal() {
        return descricaoFiscal;
    }

    /**
     * Sets the value of the descricaoFiscal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescricaoFiscal(String value) {
        this.descricaoFiscal = value;
    }

    /**
     * Gets the value of the descricaoB2C property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescricaoB2C() {
        return descricaoB2C;
    }

    /**
     * Sets the value of the descricaoB2C property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescricaoB2C(String value) {
        this.descricaoB2C = value;
    }

    /**
     * Gets the value of the griffe property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGriffe() {
        return griffe;
    }

    /**
     * Sets the value of the griffe property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGriffe(String value) {
        this.griffe = value;
    }

    /**
     * Gets the value of the linha property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLinha() {
        return linha;
    }

    /**
     * Sets the value of the linha property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLinha(String value) {
        this.linha = value;
    }

    /**
     * Gets the value of the tipoTecido property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoTecido() {
        return tipoTecido;
    }

    /**
     * Sets the value of the tipoTecido property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoTecido(String value) {
        this.tipoTecido = value;
    }

    /**
     * Gets the value of the composicao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComposicao() {
        return composicao;
    }

    /**
     * Sets the value of the composicao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComposicao(String value) {
        this.composicao = value;
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
     * Gets the value of the parte property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParte() {
        return parte;
    }

    /**
     * Sets the value of the parte property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParte(String value) {
        this.parte = value;
    }

    /**
     * Gets the value of the tags property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTags() {
        return tags;
    }

    /**
     * Sets the value of the tags property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTags(String value) {
        this.tags = value;
    }

    /**
     * Gets the value of the codigoCategoria property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoCategoria() {
        return codigoCategoria;
    }

    /**
     * Sets the value of the codigoCategoria property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoCategoria(String value) {
        this.codigoCategoria = value;
    }

    /**
     * Gets the value of the codigoSubCategoria property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoSubCategoria() {
        return codigoSubCategoria;
    }

    /**
     * Sets the value of the codigoSubCategoria property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoSubCategoria(String value) {
        this.codigoSubCategoria = value;
    }

    /**
     * Gets the value of the codigoGrupo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoGrupo() {
        return codigoGrupo;
    }

    /**
     * Sets the value of the codigoGrupo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoGrupo(String value) {
        this.codigoGrupo = value;
    }

    /**
     * Gets the value of the codigoSubGrupo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoSubGrupo() {
        return codigoSubGrupo;
    }

    /**
     * Sets the value of the codigoSubGrupo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoSubGrupo(String value) {
        this.codigoSubGrupo = value;
    }

    /**
     * Gets the value of the ocasiao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOcasiao() {
        return ocasiao;
    }

    /**
     * Sets the value of the ocasiao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOcasiao(String value) {
        this.ocasiao = value;
    }

    /**
     * Gets the value of the tendencia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTendencia() {
        return tendencia;
    }

    /**
     * Sets the value of the tendencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTendencia(String value) {
        this.tendencia = value;
    }

    /**
     * Gets the value of the tipoMaterial property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoMaterial() {
        return tipoMaterial;
    }

    /**
     * Sets the value of the tipoMaterial property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoMaterial(String value) {
        this.tipoMaterial = value;
    }

    /**
     * Gets the value of the colecao property.
     * 
     * @return
     *     possible object is
     *     {@link Colecao }
     *     
     */
    public Colecao getColecao() {
        return colecao;
    }

    /**
     * Sets the value of the colecao property.
     * 
     * @param value
     *     allowed object is
     *     {@link Colecao }
     *     
     */
    public void setColecao(Colecao value) {
        this.colecao = value;
    }

}
