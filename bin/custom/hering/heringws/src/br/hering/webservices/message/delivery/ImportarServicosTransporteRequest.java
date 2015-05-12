
package br.hering.webservices.message.delivery;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}servicoTransporte"/>
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}frete" maxOccurs="unbounded"/>
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
    "servicoTransporte",
    "frete"
})
@XmlRootElement(name = "importarServicosTransporteRequest", namespace = "http://hering.fliegersoftware.de/interface/soap/")
public class ImportarServicosTransporteRequest {

    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected ServicoTransporte servicoTransporte;
    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected List<Frete> frete;

    /**
     * Gets the value of the servicoTransporte property.
     * 
     * @return
     *     possible object is
     *     {@link ServicoTransporte }
     *     
     */
    public ServicoTransporte getServicoTransporte() {
        return servicoTransporte;
    }

    /**
     * Sets the value of the servicoTransporte property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServicoTransporte }
     *     
     */
    public void setServicoTransporte(ServicoTransporte value) {
        this.servicoTransporte = value;
    }

    /**
     * Gets the value of the frete property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the frete property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFrete().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Frete }
     * 
     * 
     */
    public List<Frete> getFrete() {
        if (frete == null) {
            frete = new ArrayList<Frete>();
        }
        return this.frete;
    }

}
