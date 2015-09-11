
package br.hering.webservices.message.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.hering.webservices.message.client package. 
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

    private final static QName _Email_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "email");
    private final static QName _CpfCgc_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "cpfCgc");
    private final static QName _Telefone_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "telefone");
    private final static QName _CodigoSite_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "codigoSite");
    private final static QName _TipoEndereco_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "tipoEndereco");
    private final static QName _Cep_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "cep");
    private final static QName _Cidade_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "cidade");
    private final static QName _Bairro_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "bairro");
    private final static QName _CodigoCliente_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "codigoCliente");
    private final static QName _DataCadastramento_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "dataCadastramento");
    private final static QName _Celular_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "celular");
    private final static QName _Logradouro_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "logradouro");
    private final static QName _CodigoEndereco_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "codigoEndereco");
    private final static QName _Aniversario_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "aniversario");
    private final static QName _Nome_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "nome");
    private final static QName _Uf_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "uf");
    private final static QName _Ddd_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "ddd");
    private final static QName _Pais_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "pais");
    private final static QName _Newsletter_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "newsletter");
    private final static QName _EnderecoPrincipal_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "enderecoPrincipal");
    private final static QName _Complemento_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "complemento");
    private final static QName _Senha_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "senha");
    private final static QName _Numero_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "numero");
    private final static QName _Sexo_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "sexo");
    private final static QName _DescricaoEndereco_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "descricaoEndereco");
    private final static QName _EstadoExterior_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "estadoExterior");
    private final static QName _DddCelular_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "dddCelular");
    private final static QName _RgIe_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "rgIe");
    private final static QName _PfPj_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "pfPj");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.hering.webservices.message.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ImportacaoClienteRequest }
     * 
     */
    public ImportacaoClienteRequest createImportacaoClienteRequest() {
        return new ImportacaoClienteRequest();
    }

    /**
     * Create an instance of {@link Cliente }
     * 
     */
    public Cliente createCliente() {
        return new Cliente();
    }

    /**
     * Create an instance of {@link Endereco }
     * 
     */
    public Endereco createEndereco() {
        return new Endereco();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "email")
    public JAXBElement<String> createEmail(String value) {
        return new JAXBElement<String>(_Email_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "cpfCgc")
    public JAXBElement<String> createCpfCgc(String value) {
        return new JAXBElement<String>(_CpfCgc_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "telefone")
    public JAXBElement<String> createTelefone(String value) {
        return new JAXBElement<String>(_Telefone_QNAME, String.class, null, value);
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
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "tipoEndereco")
    public JAXBElement<String> createTipoEndereco(String value) {
        return new JAXBElement<String>(_TipoEndereco_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "cep")
    public JAXBElement<String> createCep(String value) {
        return new JAXBElement<String>(_Cep_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "cidade")
    public JAXBElement<String> createCidade(String value) {
        return new JAXBElement<String>(_Cidade_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "bairro")
    public JAXBElement<String> createBairro(String value) {
        return new JAXBElement<String>(_Bairro_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "codigoCliente")
    public JAXBElement<String> createCodigoCliente(String value) {
        return new JAXBElement<String>(_CodigoCliente_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "dataCadastramento")
    public JAXBElement<String> createDataCadastramento(String value) {
        return new JAXBElement<String>(_DataCadastramento_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "celular")
    public JAXBElement<String> createCelular(String value) {
        return new JAXBElement<String>(_Celular_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "logradouro")
    public JAXBElement<String> createLogradouro(String value) {
        return new JAXBElement<String>(_Logradouro_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "codigoEndereco")
    public JAXBElement<String> createCodigoEndereco(String value) {
        return new JAXBElement<String>(_CodigoEndereco_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "aniversario")
    public JAXBElement<String> createAniversario(String value) {
        return new JAXBElement<String>(_Aniversario_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "nome")
    public JAXBElement<String> createNome(String value) {
        return new JAXBElement<String>(_Nome_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "uf")
    public JAXBElement<String> createUf(String value) {
        return new JAXBElement<String>(_Uf_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "ddd")
    public JAXBElement<String> createDdd(String value) {
        return new JAXBElement<String>(_Ddd_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "pais")
    public JAXBElement<String> createPais(String value) {
        return new JAXBElement<String>(_Pais_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "newsletter")
    public JAXBElement<Boolean> createNewsletter(Boolean value) {
        return new JAXBElement<Boolean>(_Newsletter_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "enderecoPrincipal")
    public JAXBElement<Boolean> createEnderecoPrincipal(Boolean value) {
        return new JAXBElement<Boolean>(_EnderecoPrincipal_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "complemento")
    public JAXBElement<String> createComplemento(String value) {
        return new JAXBElement<String>(_Complemento_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "senha")
    public JAXBElement<String> createSenha(String value) {
        return new JAXBElement<String>(_Senha_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "numero")
    public JAXBElement<String> createNumero(String value) {
        return new JAXBElement<String>(_Numero_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "sexo")
    public JAXBElement<String> createSexo(String value) {
        return new JAXBElement<String>(_Sexo_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "descricaoEndereco")
    public JAXBElement<String> createDescricaoEndereco(String value) {
        return new JAXBElement<String>(_DescricaoEndereco_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "estadoExterior")
    public JAXBElement<String> createEstadoExterior(String value) {
        return new JAXBElement<String>(_EstadoExterior_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "dddCelular")
    public JAXBElement<String> createDddCelular(String value) {
        return new JAXBElement<String>(_DddCelular_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "rgIe")
    public JAXBElement<String> createRgIe(String value) {
        return new JAXBElement<String>(_RgIe_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "pfPj")
    public JAXBElement<Boolean> createPfPj(Boolean value) {
        return new JAXBElement<Boolean>(_PfPj_QNAME, Boolean.class, null, value);
    }

}
