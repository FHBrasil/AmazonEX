
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
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}dadosPedido"/>
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
    "dadosPedido"
})
@XmlRootElement(name = "importarPedidoRequest", namespace = "http://hering.fliegersoftware.de/interface/soap/")
public class ImportarPedidoRequest {

    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected DadosPedido dadosPedido;

    /**
     * Gets the value of the dadosPedido property.
     * 
     * @return
     *     possible object is
     *     {@link DadosPedido }
     *     
     */
    public DadosPedido getDadosPedido() {
        return dadosPedido;
    }

    /**
     * Sets the value of the dadosPedido property.
     * 
     * @param value
     *     allowed object is
     *     {@link DadosPedido }
     *     
     */
    public void setDadosPedido(DadosPedido value) {
        this.dadosPedido = value;
    }

}
