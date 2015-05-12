
package br.hering.fulfilmentprocess.message.order;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.hering.webservices.message.order package. 
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

    private final static QName _CpfCgc_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "cpfCgc");
    private final static QName _Telefone_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "telefone");
    private final static QName _Tipo_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "tipo");
    private final static QName _Item_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "item");
    private final static QName _CodigoSite_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "codigoSite");
    private final static QName _MensagemCartao_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "mensagemCartao");
    private final static QName _Cidade_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "cidade");
    private final static QName _Bairro_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "bairro");
    private final static QName _Moeda_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "moeda");
    private final static QName _CorProduto_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "corProduto");
    private final static QName _PrazoMaximoEntrega_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "prazoMaximoEntrega");
    private final static QName _Uf_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "uf");
    private final static QName _CodigoProduto_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "codigoProduto");
    private final static QName _NumeroTitulo_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "numeroTitulo");
    private final static QName _Complemento_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "complemento");
    private final static QName _ParcelasCartao_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "parcelasCartao");
    private final static QName _EstadoExterior_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "estadoExterior");
    private final static QName _OrigemEstoque_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "origemEstoque");
    private final static QName _Cep_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "cep");
    private final static QName _Parcela_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "parcela");
    private final static QName _CodigoEndereco_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "codigoEndereco");
    private final static QName _Aniversario_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "aniversario");
    private final static QName _CodigoEnderecoEntrega_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "codigoEnderecoEntrega");
    private final static QName _Pais_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "pais");
    private final static QName _Ddd_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "ddd");
    private final static QName _Senha_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "senha");
    private final static QName _Transportadora_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "transportadora");
    private final static QName _ValorMoeda_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "valorMoeda");
    private final static QName _Data_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "data");
    private final static QName _Email_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "email");
    private final static QName _Valor_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "valor");
    private final static QName _Capturado_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "capturado");
    private final static QName _TipoEndereco_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "tipoEndereco");
    private final static QName _Peso_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "peso");
    private final static QName _PrecoLiquido_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "precoLiquido");
    private final static QName _Celular_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "celular");
    private final static QName _ValorTotal_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "valorTotal");
    private final static QName _Logradouro_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "logradouro");
    private final static QName _CodigoEnderecoCobranca_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "codigoEnderecoCobranca");
    private final static QName _Nome_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "nome");
    private final static QName _Status_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "status");
    private final static QName _NumeroAprovacaoCartao_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "numeroAprovacaoCartao");
    private final static QName _CodigoDesconto_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "codigoDesconto");
    private final static QName _Newsletter_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "newsletter");
    private final static QName _QuantidadeTotal_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "quantidadeTotal");
    private final static QName _Desconto_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "desconto");
    private final static QName _Sku_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "sku");
    private final static QName _Numero_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "numero");
    private final static QName _DataHoraFinalizacaoPedido_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "dataHoraFinalizacaoPedido");
    private final static QName _PfPj_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "pfPj");
    private final static QName _TipoPagamento_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "tipoPagamento");
    private final static QName _Quantidade_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "quantidade");
    private final static QName _CodigoCliente_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "codigoCliente");
    private final static QName _FormaEntrega_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "formaEntrega");
    private final static QName _CodigoAdministradora_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "codigoAdministradora");
    private final static QName _DataCadastramento_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "dataCadastramento");
    private final static QName _CodigoAntifraude_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "codigoAntifraude");
    private final static QName _Cotacao_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "cotacao");
    private final static QName _ValorFrete_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "valorFrete");
    private final static QName _EnderecoPrincipal_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "enderecoPrincipal");
    private final static QName _CodigoPedido_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "codigoPedido");
    private final static QName _DescricaoEndereco_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "descricaoEndereco");
    private final static QName _Sexo_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "sexo");
    private final static QName _DddCelular_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "dddCelular");
    private final static QName _Vencimento_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "vencimento");
    private final static QName _RgIe_QNAME = new QName("http://hering.fliegersoftware.de/interface/soap/", "rgIe");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.hering.webservices.message.order
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Pagamento }
     * 
     */
    public Pagamento createPagamento() {
        return new Pagamento();
    }

    /**
     * Create an instance of {@link Pedido }
     * 
     */
    public Pedido createPedido() {
        return new Pedido();
    }

    /**
     * Create an instance of {@link ImportarPedidoRequest }
     * 
     */
    public ImportarPedidoRequest createImportarPedidoRequest() {
        return new ImportarPedidoRequest();
    }

    /**
     * Create an instance of {@link DadosPedido }
     * 
     */
    public DadosPedido createDadosPedido() {
        return new DadosPedido();
    }

    /**
     * Create an instance of {@link Itens }
     * 
     */
    public Itens createItens() {
        return new Itens();
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
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "tipo")
    public JAXBElement<String> createTipo(String value) {
        return new JAXBElement<String>(_Tipo_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "item")
    public JAXBElement<String> createItem(String value) {
        return new JAXBElement<String>(_Item_QNAME, String.class, null, value);
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
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "mensagemCartao")
    public JAXBElement<String> createMensagemCartao(String value) {
        return new JAXBElement<String>(_MensagemCartao_QNAME, String.class, null, value);
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
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "moeda")
    public JAXBElement<String> createMoeda(String value) {
        return new JAXBElement<String>(_Moeda_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "corProduto")
    public JAXBElement<String> createCorProduto(String value) {
        return new JAXBElement<String>(_CorProduto_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "prazoMaximoEntrega")
    public JAXBElement<Integer> createPrazoMaximoEntrega(Integer value) {
        return new JAXBElement<Integer>(_PrazoMaximoEntrega_QNAME, Integer.class, null, value);
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
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "codigoProduto")
    public JAXBElement<String> createCodigoProduto(String value) {
        return new JAXBElement<String>(_CodigoProduto_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "numeroTitulo")
    public JAXBElement<String> createNumeroTitulo(String value) {
        return new JAXBElement<String>(_NumeroTitulo_QNAME, String.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "parcelasCartao")
    public JAXBElement<Integer> createParcelasCartao(Integer value) {
        return new JAXBElement<Integer>(_ParcelasCartao_QNAME, Integer.class, null, value);
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
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "origemEstoque")
    public JAXBElement<String> createOrigemEstoque(String value) {
        return new JAXBElement<String>(_OrigemEstoque_QNAME, String.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "parcela")
    public JAXBElement<Integer> createParcela(Integer value) {
        return new JAXBElement<Integer>(_Parcela_QNAME, Integer.class, null, value);
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
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "codigoEnderecoEntrega")
    public JAXBElement<String> createCodigoEnderecoEntrega(String value) {
        return new JAXBElement<String>(_CodigoEnderecoEntrega_QNAME, String.class, null, value);
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
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "senha")
    public JAXBElement<String> createSenha(String value) {
        return new JAXBElement<String>(_Senha_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "transportadora")
    public JAXBElement<String> createTransportadora(String value) {
        return new JAXBElement<String>(_Transportadora_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "valorMoeda")
    public JAXBElement<BigDecimal> createValorMoeda(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_ValorMoeda_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "data")
    public JAXBElement<String> createData(String value) {
        return new JAXBElement<String>(_Data_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "email")
    public JAXBElement<Object> createEmail(Object value) {
        return new JAXBElement<Object>(_Email_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "valor")
    public JAXBElement<BigDecimal> createValor(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_Valor_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "capturado")
    public JAXBElement<Integer> createCapturado(Integer value) {
        return new JAXBElement<Integer>(_Capturado_QNAME, Integer.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "peso")
    public JAXBElement<BigDecimal> createPeso(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_Peso_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "precoLiquido")
    public JAXBElement<BigDecimal> createPrecoLiquido(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_PrecoLiquido_QNAME, BigDecimal.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "valorTotal")
    public JAXBElement<BigDecimal> createValorTotal(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_ValorTotal_QNAME, BigDecimal.class, null, value);
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
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "codigoEnderecoCobranca")
    public JAXBElement<String> createCodigoEnderecoCobranca(String value) {
        return new JAXBElement<String>(_CodigoEnderecoCobranca_QNAME, String.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "status")
    public JAXBElement<Integer> createStatus(Integer value) {
        return new JAXBElement<Integer>(_Status_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "numeroAprovacaoCartao")
    public JAXBElement<String> createNumeroAprovacaoCartao(String value) {
        return new JAXBElement<String>(_NumeroAprovacaoCartao_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "codigoDesconto")
    public JAXBElement<String> createCodigoDesconto(String value) {
        return new JAXBElement<String>(_CodigoDesconto_QNAME, String.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "quantidadeTotal")
    public JAXBElement<Integer> createQuantidadeTotal(Integer value) {
        return new JAXBElement<Integer>(_QuantidadeTotal_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "desconto")
    public JAXBElement<BigDecimal> createDesconto(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_Desconto_QNAME, BigDecimal.class, null, value);
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
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "dataHoraFinalizacaoPedido")
    public JAXBElement<String> createDataHoraFinalizacaoPedido(String value) {
        return new JAXBElement<String>(_DataHoraFinalizacaoPedido_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "pfPj")
    public JAXBElement<Boolean> createPfPj(Boolean value) {
        return new JAXBElement<Boolean>(_PfPj_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "tipoPagamento")
    public JAXBElement<String> createTipoPagamento(String value) {
        return new JAXBElement<String>(_TipoPagamento_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "quantidade")
    public JAXBElement<Integer> createQuantidade(Integer value) {
        return new JAXBElement<Integer>(_Quantidade_QNAME, Integer.class, null, value);
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
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "formaEntrega")
    public JAXBElement<String> createFormaEntrega(String value) {
        return new JAXBElement<String>(_FormaEntrega_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "codigoAdministradora")
    public JAXBElement<Integer> createCodigoAdministradora(Integer value) {
        return new JAXBElement<Integer>(_CodigoAdministradora_QNAME, Integer.class, null, value);
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
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "codigoAntifraude")
    public JAXBElement<String> createCodigoAntifraude(String value) {
        return new JAXBElement<String>(_CodigoAntifraude_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "cotacao")
    public JAXBElement<BigDecimal> createCotacao(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_Cotacao_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "valorFrete")
    public JAXBElement<BigDecimal> createValorFrete(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_ValorFrete_QNAME, BigDecimal.class, null, value);
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
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "codigoPedido")
    public JAXBElement<String> createCodigoPedido(String value) {
        return new JAXBElement<String>(_CodigoPedido_QNAME, String.class, null, value);
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
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "sexo")
    public JAXBElement<String> createSexo(String value) {
        return new JAXBElement<String>(_Sexo_QNAME, String.class, null, value);
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
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "vencimento")
    public JAXBElement<String> createVencimento(String value) {
        return new JAXBElement<String>(_Vencimento_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hering.fliegersoftware.de/interface/soap/", name = "rgIe")
    public JAXBElement<String> createRgIe(String value) {
        return new JAXBElement<String>(_RgIe_QNAME, String.class, null, value);
    }

}
