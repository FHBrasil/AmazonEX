
package br.hering.webservices.message.product.attribute;

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
 *         &lt;element ref="{http://hering.fliegersoftware.de/interface/soap/}atributo" maxOccurs="unbounded"/>
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
    "atributo"
})
@XmlRootElement(name = "importarAtributosProdutoRequest", namespace = "http://hering.fliegersoftware.de/interface/soap/")
public class ImportarAtributosProdutoRequest {

    @XmlElement(namespace = "http://hering.fliegersoftware.de/interface/soap/", required = true)
    protected List<Atributo> atributo;

    /**
     * Gets the value of the atributo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the atributo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAtributo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Atributo }
     * 
     * 
     */
    public List<Atributo> getAtributo() {
        if (atributo == null) {
            atributo = new ArrayList<Atributo>();
        }
        return this.atributo;
    }

}
