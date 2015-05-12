
package br.hering.webservices.message.transacao;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.hering.webservices.message.transacao package. 
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

    private final static QName _Mensagem_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "mensagem");
    private final static QName _Transacao_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "transacao");
    private final static QName _Chave_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "chave");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.hering.webservices.message.transacao
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ResultadoTransacaoRequest }
     * 
     */
    public ResultadoTransacaoRequest createResultadoTransacaoRequest() {
        return new ResultadoTransacaoRequest();
    }

    /**
     * Create an instance of {@link Resultado }
     * 
     */
    public Resultado createResultado() {
        return new Resultado();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "mensagem")
    public JAXBElement<String> createMensagem(String value) {
        return new JAXBElement<String>(_Mensagem_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "transacao")
    public JAXBElement<String> createTransacao(String value) {
        return new JAXBElement<String>(_Transacao_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "chave")
    public JAXBElement<String> createChave(String value) {
        return new JAXBElement<String>(_Chave_QNAME, String.class, null, value);
    }

}
