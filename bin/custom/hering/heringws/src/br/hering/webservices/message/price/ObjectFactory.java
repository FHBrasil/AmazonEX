
package br.hering.webservices.message.price;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.hering.webservices.message.price package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CodigoSite_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "codigoSite");
    private final static QName _PrecoDe_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "precoDe");
    private final static QName _Preco_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "preco");
    private final static QName _Ativacao_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "ativacao");
    private final static QName _Sku_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "sku");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.hering.webservices.message.price
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PrecoProduto }
     * 
     */
    public PrecoProduto createPrecoProduto() {
        return new PrecoProduto();
    }

    /**
     * Create an instance of {@link ImportarPrecoProdutoRequest }
     * 
     */
    public ImportarPrecoProdutoRequest createImportarPrecoProdutoRequest() {
        return new ImportarPrecoProdutoRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "codigoSite")
    public JAXBElement<String> createCodigoSite(String value) {
        return new JAXBElement<String>(_CodigoSite_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "precoDe")
    public JAXBElement<BigDecimal> createPrecoDe(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_PrecoDe_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "preco")
    public JAXBElement<BigDecimal> createPreco(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_Preco_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "ativacao")
    public JAXBElement<String> createAtivacao(String value) {
        return new JAXBElement<String>(_Ativacao_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "sku")
    public JAXBElement<String> createSku(String value) {
        return new JAXBElement<String>(_Sku_QNAME, String.class, null, value);
    }

}
