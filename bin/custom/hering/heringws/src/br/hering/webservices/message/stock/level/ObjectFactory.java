
package br.hering.webservices.message.stock.level;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.hering.webservices.message.stock.level package. 
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
    private final static QName _PermiteVirtual_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "permiteVirtual");
    private final static QName _TempoEntregaVirtual_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "tempoEntregaVirtual");
    private final static QName _EstoqueVirtual_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "estoqueVirtual");
    private final static QName _Sku_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "sku");
    private final static QName _Estoque_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "estoque");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.hering.webservices.message.stock.level
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EstoqueSaldoProduto }
     * 
     */
    public EstoqueSaldoProduto createEstoqueSaldoProduto() {
        return new EstoqueSaldoProduto();
    }

    /**
     * Create an instance of {@link ImportarEstoqueSaldoProdutoRequest }
     * 
     */
    public ImportarEstoqueSaldoProdutoRequest createImportarEstoqueSaldoProdutoRequest() {
        return new ImportarEstoqueSaldoProdutoRequest();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "permiteVirtual")
    public JAXBElement<Boolean> createPermiteVirtual(Boolean value) {
        return new JAXBElement<Boolean>(_PermiteVirtual_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "tempoEntregaVirtual")
    public JAXBElement<Integer> createTempoEntregaVirtual(Integer value) {
        return new JAXBElement<Integer>(_TempoEntregaVirtual_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "estoqueVirtual")
    public JAXBElement<Integer> createEstoqueVirtual(Integer value) {
        return new JAXBElement<Integer>(_EstoqueVirtual_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "sku")
    public JAXBElement<String> createSku(String value) {
        return new JAXBElement<String>(_Sku_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "estoque")
    public JAXBElement<Integer> createEstoque(Integer value) {
        return new JAXBElement<Integer>(_Estoque_QNAME, Integer.class, null, value);
    }

}
