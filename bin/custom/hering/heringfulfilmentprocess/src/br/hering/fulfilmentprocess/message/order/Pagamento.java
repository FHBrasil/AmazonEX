
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
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}parcela"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}tipoPagamento"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}valor"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}vencimento"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}numeroTitulo"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}moeda"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}valorMoeda"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}codigoAdministradora"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}numeroAprovacaoCartao"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}parcelasCartao"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}cotacao"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}capturado"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}codigoAntifraude"/>
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
    "parcela",
    "tipoPagamento",
    "valor",
    "vencimento",
    "numeroTitulo",
    "moeda",
    "valorMoeda",
    "codigoAdministradora",
    "numeroAprovacaoCartao",
    "parcelasCartao",
    "cotacao",
    "capturado",
    "codigoAntifraude"
})
@XmlRootElement(name = "pagamento", namespace = "http://hering.fliegersoftware.de/interface/soap/")
public class Pagamento {

    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String codigoSite;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String codigoPedido;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/")
    protected int parcela;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String tipoPagamento;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected BigDecimal valor;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String vencimento;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String numeroTitulo;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String moeda;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected BigDecimal valorMoeda;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/")
    protected int codigoAdministradora;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String numeroAprovacaoCartao;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/")
    protected int parcelasCartao;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected BigDecimal cotacao;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/")
    protected int capturado;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String codigoAntifraude;

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
     * Gets the value of the parcela property.
     * 
     */
    public int getParcela() {
        return parcela;
    }

    /**
     * Sets the value of the parcela property.
     * 
     */
    public void setParcela(int value) {
        this.parcela = value;
    }

    /**
     * Gets the value of the tipoPagamento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoPagamento() {
        return tipoPagamento;
    }

    /**
     * Sets the value of the tipoPagamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoPagamento(String value) {
        this.tipoPagamento = value;
    }

    /**
     * Gets the value of the valor property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getValor() {
        return valor;
    }

    /**
     * Sets the value of the valor property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setValor(BigDecimal value) {
        this.valor = value;
    }

    /**
     * Gets the value of the vencimento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVencimento() {
        return vencimento;
    }

    /**
     * Sets the value of the vencimento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVencimento(String value) {
        this.vencimento = value;
    }

    /**
     * Gets the value of the numeroTitulo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroTitulo() {
        return numeroTitulo;
    }

    /**
     * Sets the value of the numeroTitulo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroTitulo(String value) {
        this.numeroTitulo = value;
    }

    /**
     * Gets the value of the moeda property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMoeda() {
        return moeda;
    }

    /**
     * Sets the value of the moeda property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMoeda(String value) {
        this.moeda = value;
    }

    /**
     * Gets the value of the valorMoeda property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getValorMoeda() {
        return valorMoeda;
    }

    /**
     * Sets the value of the valorMoeda property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setValorMoeda(BigDecimal value) {
        this.valorMoeda = value;
    }

    /**
     * Gets the value of the codigoAdministradora property.
     * 
     */
    public int getCodigoAdministradora() {
        return codigoAdministradora;
    }

    /**
     * Sets the value of the codigoAdministradora property.
     * 
     */
    public void setCodigoAdministradora(int value) {
        this.codigoAdministradora = value;
    }

    /**
     * Gets the value of the numeroAprovacaoCartao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroAprovacaoCartao() {
        return numeroAprovacaoCartao;
    }

    /**
     * Sets the value of the numeroAprovacaoCartao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroAprovacaoCartao(String value) {
        this.numeroAprovacaoCartao = value;
    }

    /**
     * Gets the value of the parcelasCartao property.
     * 
     */
    public int getParcelasCartao() {
        return parcelasCartao;
    }

    /**
     * Sets the value of the parcelasCartao property.
     * 
     */
    public void setParcelasCartao(int value) {
        this.parcelasCartao = value;
    }

    /**
     * Gets the value of the cotacao property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCotacao() {
        return cotacao;
    }

    /**
     * Sets the value of the cotacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCotacao(BigDecimal value) {
        this.cotacao = value;
    }

    /**
     * Gets the value of the capturado property.
     * 
     */
    public int getCapturado() {
        return capturado;
    }

    /**
     * Sets the value of the capturado property.
     * 
     */
    public void setCapturado(int value) {
        this.capturado = value;
    }

    /**
     * Gets the value of the codigoAntifraude property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoAntifraude() {
        return codigoAntifraude;
    }

    /**
     * Sets the value of the codigoAntifraude property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoAntifraude(String value) {
        this.codigoAntifraude = value;
    }

}
