
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
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}codigoCliente"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}codigoEnderecoEntrega"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}codigoEnderecoCobranca"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}codigoPedido"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}data"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}tipo"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}quantidadeTotal"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}valorTotal"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}desconto"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}formaEntrega"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}transportadora"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}peso"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}prazoMaximoEntrega"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}valorFrete"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}mensagemCartao" minOccurs="0"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}codigoDesconto" minOccurs="0"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}dataHoraFinalizacaoPedido"/>
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
    "codigoEnderecoEntrega",
    "codigoEnderecoCobranca",
    "codigoPedido",
    "data",
    "tipo",
    "quantidadeTotal",
    "valorTotal",
    "desconto",
    "formaEntrega",
    "transportadora",
    "peso",
    "prazoMaximoEntrega",
    "valorFrete",
    "mensagemCartao",
    "codigoDesconto",
    "dataHoraFinalizacaoPedido"
})
@XmlRootElement(name = "pedido", namespace = "http://hering.fliegersoftware.de/interface/soap/")
public class Pedido {

    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String codigoSite;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String codigoCliente;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String codigoEnderecoEntrega;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String codigoEnderecoCobranca;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String codigoPedido;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String data;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String tipo;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/")
    protected int quantidadeTotal;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected BigDecimal valorTotal;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected BigDecimal desconto;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String formaEntrega;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String transportadora;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected BigDecimal peso;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/")
    protected int prazoMaximoEntrega;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected BigDecimal valorFrete;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/")
    protected String mensagemCartao;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/")
    protected String codigoDesconto;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected String dataHoraFinalizacaoPedido;

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
     * Gets the value of the codigoEnderecoEntrega property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoEnderecoEntrega() {
        return codigoEnderecoEntrega;
    }

    /**
     * Sets the value of the codigoEnderecoEntrega property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoEnderecoEntrega(String value) {
        this.codigoEnderecoEntrega = value;
    }

    /**
     * Gets the value of the codigoEnderecoCobranca property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoEnderecoCobranca() {
        return codigoEnderecoCobranca;
    }

    /**
     * Sets the value of the codigoEnderecoCobranca property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoEnderecoCobranca(String value) {
        this.codigoEnderecoCobranca = value;
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
     * Gets the value of the data property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getData() {
        return data;
    }

    /**
     * Sets the value of the data property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setData(String value) {
        this.data = value;
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
     * Gets the value of the quantidadeTotal property.
     * 
     */
    public int getQuantidadeTotal() {
        return quantidadeTotal;
    }

    /**
     * Sets the value of the quantidadeTotal property.
     * 
     */
    public void setQuantidadeTotal(int value) {
        this.quantidadeTotal = value;
    }

    /**
     * Gets the value of the valorTotal property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    /**
     * Sets the value of the valorTotal property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setValorTotal(BigDecimal value) {
        this.valorTotal = value;
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
     * Gets the value of the formaEntrega property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormaEntrega() {
        return formaEntrega;
    }

    /**
     * Sets the value of the formaEntrega property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormaEntrega(String value) {
        this.formaEntrega = value;
    }

    /**
     * Gets the value of the transportadora property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransportadora() {
        return transportadora;
    }

    /**
     * Sets the value of the transportadora property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransportadora(String value) {
        this.transportadora = value;
    }

    /**
     * Gets the value of the peso property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPeso() {
        return peso;
    }

    /**
     * Sets the value of the peso property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPeso(BigDecimal value) {
        this.peso = value;
    }

    /**
     * Gets the value of the prazoMaximoEntrega property.
     * 
     */
    public int getPrazoMaximoEntrega() {
        return prazoMaximoEntrega;
    }

    /**
     * Sets the value of the prazoMaximoEntrega property.
     * 
     */
    public void setPrazoMaximoEntrega(int value) {
        this.prazoMaximoEntrega = value;
    }

    /**
     * Gets the value of the valorFrete property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getValorFrete() {
        return valorFrete;
    }

    /**
     * Sets the value of the valorFrete property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setValorFrete(BigDecimal value) {
        this.valorFrete = value;
    }

    /**
     * Gets the value of the mensagemCartao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMensagemCartao() {
        return mensagemCartao;
    }

    /**
     * Sets the value of the mensagemCartao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMensagemCartao(String value) {
        this.mensagemCartao = value;
    }

    /**
     * Gets the value of the codigoDesconto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoDesconto() {
        return codigoDesconto;
    }

    /**
     * Sets the value of the codigoDesconto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoDesconto(String value) {
        this.codigoDesconto = value;
    }

    /**
     * Gets the value of the dataHoraFinalizacaoPedido property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataHoraFinalizacaoPedido() {
        return dataHoraFinalizacaoPedido;
    }

    /**
     * Sets the value of the dataHoraFinalizacaoPedido property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataHoraFinalizacaoPedido(String value) {
        this.dataHoraFinalizacaoPedido = value;
    }

}
