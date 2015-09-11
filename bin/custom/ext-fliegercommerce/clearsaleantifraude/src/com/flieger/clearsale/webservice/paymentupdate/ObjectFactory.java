
package com.flieger.clearsale.webservice.paymentupdate;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.com.clearsale.integration package. 
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

    private final static QName _String_QNAME = new QName("http://www.clearsale.com.br/integration", "string");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.com.clearsale.integration
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UpdateOrderStatusResponse }
     * 
     */
    public UpdateOrderStatusResponse createUpdateOrderStatusResponse() {
        return new UpdateOrderStatusResponse();
    }

    /**
     * Create an instance of {@link UpdateOrderStatus }
     * 
     */
    public UpdateOrderStatus createUpdateOrderStatus() {
        return new UpdateOrderStatus();
    }

    /**
     * Create an instance of {@link UpdateOrderStatusID }
     * 
     */
    public UpdateOrderStatusID createUpdateOrderStatusID() {
        return new UpdateOrderStatusID();
    }

    /**
     * Create an instance of {@link UpdateOrderStatusIDResponse }
     * 
     */
    public UpdateOrderStatusIDResponse createUpdateOrderStatusIDResponse() {
        return new UpdateOrderStatusIDResponse();
    }

    /**
     * Create an instance of {@link SendPaymentNsu }
     * 
     */
    public SendPaymentNsu createSendPaymentNsu() {
        return new SendPaymentNsu();
    }

    /**
     * Create an instance of {@link SendPaymentNsuResponse }
     * 
     */
    public SendPaymentNsuResponse createSendPaymentNsuResponse() {
        return new SendPaymentNsuResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.clearsale.com.br/integration", name = "string")
    public JAXBElement<String> createString(String value) {
        return new JAXBElement<String>(_String_QNAME, String.class, null, value);
    }

}
