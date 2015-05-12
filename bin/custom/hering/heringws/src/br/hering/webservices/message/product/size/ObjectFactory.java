
package br.hering.webservices.message.product.size;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.hering.webservices.message.product.size package. 
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

    private final static QName _CodigoProduto_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "codigoProduto");
    private final static QName _Tipo_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "tipo");
    private final static QName _Medida_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "medida");
    private final static QName _CodigoSite_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "codigoSite");
    private final static QName _Tamanho_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "tamanho");
    private final static QName _Imagem_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "imagem");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.hering.webservices.message.product.size
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ImportarMedidasProdutoRequest }
     * 
     */
    public ImportarMedidasProdutoRequest createImportarMedidasProdutoRequest() {
        return new ImportarMedidasProdutoRequest();
    }

    /**
     * Create an instance of {@link MedidaProduto }
     * 
     */
    public MedidaProduto createMedidaProduto() {
        return new MedidaProduto();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "codigoProduto")
    public JAXBElement<String> createCodigoProduto(String value) {
        return new JAXBElement<String>(_CodigoProduto_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "tipo")
    public JAXBElement<String> createTipo(String value) {
        return new JAXBElement<String>(_Tipo_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "medida")
    public JAXBElement<BigDecimal> createMedida(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_Medida_QNAME, BigDecimal.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "tamanho")
    public JAXBElement<String> createTamanho(String value) {
        return new JAXBElement<String>(_Tamanho_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "imagem")
    public JAXBElement<String> createImagem(String value) {
        return new JAXBElement<String>(_Imagem_QNAME, String.class, null, value);
    }

}
