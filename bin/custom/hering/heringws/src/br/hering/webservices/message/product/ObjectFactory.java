
package br.hering.webservices.message.product;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.hering.webservices.message.product package. 
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.hering.webservices.message.product
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Produto }
     * 
     */
    public Produto createProduto() {
        return new Produto();
    }

    /**
     * Create an instance of {@link Colecao }
     * 
     */
    public Colecao createColecao() {
        return new Colecao();
    }

    /**
     * Create an instance of {@link Cor }
     * 
     */
    public Cor createCor() {
        return new Cor();
    }

    /**
     * Create an instance of {@link Variacao }
     * 
     */
    public Variacao createVariacao() {
        return new Variacao();
    }

    /**
     * Create an instance of {@link ImportarProdutoRequest }
     * 
     */
    public ImportarProdutoRequest createImportarProdutoRequest() {
        return new ImportarProdutoRequest();
    }

}
