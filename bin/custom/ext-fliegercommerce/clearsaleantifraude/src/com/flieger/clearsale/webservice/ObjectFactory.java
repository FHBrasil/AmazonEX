
package com.flieger.clearsale.webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the de.com.fliegersoftware.clearsale.webserviceclient package. 
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

    private final static QName _TransactionStatusCbk_QNAME = new QName("http://www.clearsale.com.br/integration", "TransactionStatusCbk");
    private final static QName _String_QNAME = new QName("http://www.clearsale.com.br/integration", "string");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: de.com.fliegersoftware.clearsale.webserviceclient
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SendOrders }
     * 
     */
    public SendOrders createSendOrders() {
        return new SendOrders();
    }

    /**
     * Create an instance of {@link SubmitInfo }
     * 
     */
    public SubmitInfo createSubmitInfo() {
        return new SubmitInfo();
    }

    /**
     * Create an instance of {@link GetQuizURLResponse }
     * 
     */
    public GetQuizURLResponse createGetQuizURLResponse() {
        return new GetQuizURLResponse();
    }

    /**
     * Create an instance of {@link SetOrderAsReturnedResponse }
     * 
     */
    public SetOrderAsReturnedResponse createSetOrderAsReturnedResponse() {
        return new SetOrderAsReturnedResponse();
    }

    /**
     * Create an instance of {@link GetPackageStatusResponse }
     * 
     */
    public GetPackageStatusResponse createGetPackageStatusResponse() {
        return new GetPackageStatusResponse();
    }

    /**
     * Create an instance of {@link GetOrdersStatus }
     * 
     */
    public GetOrdersStatus createGetOrdersStatus() {
        return new GetOrdersStatus();
    }

    /**
     * Create an instance of {@link OrderChargeBackByNsuResponse }
     * 
     */
    public OrderChargeBackByNsuResponse createOrderChargeBackByNsuResponse() {
        return new OrderChargeBackByNsuResponse();
    }

    /**
     * Create an instance of {@link TransactionStatusCbk }
     * 
     */
    public TransactionStatusCbk createTransactionStatusCbk() {
        return new TransactionStatusCbk();
    }

    /**
     * Create an instance of {@link GetOrdersStatusResponse }
     * 
     */
    public GetOrdersStatusResponse createGetOrdersStatusResponse() {
        return new GetOrdersStatusResponse();
    }

    /**
     * Create an instance of {@link OrderChargeBackByNsu }
     * 
     */
    public OrderChargeBackByNsu createOrderChargeBackByNsu() {
        return new OrderChargeBackByNsu();
    }

    /**
     * Create an instance of {@link CheckOrderStatusResponse }
     * 
     */
    public CheckOrderStatusResponse createCheckOrderStatusResponse() {
        return new CheckOrderStatusResponse();
    }

    /**
     * Create an instance of {@link CheckOrderStatus }
     * 
     */
    public CheckOrderStatus createCheckOrderStatus() {
        return new CheckOrderStatus();
    }

    /**
     * Create an instance of {@link GetReturnAnalysisResponse }
     * 
     */
    public GetReturnAnalysisResponse createGetReturnAnalysisResponse() {
        return new GetReturnAnalysisResponse();
    }

    /**
     * Create an instance of {@link SetOrderAsReturned }
     * 
     */
    public SetOrderAsReturned createSetOrderAsReturned() {
        return new SetOrderAsReturned();
    }

    /**
     * Create an instance of {@link SendOrders2 }
     * 
     */
    public SendOrders2 createSendOrders2() {
        return new SendOrders2();
    }

    /**
     * Create an instance of {@link GetQuizURL }
     * 
     */
    public GetQuizURL createGetQuizURL() {
        return new GetQuizURL();
    }

    /**
     * Create an instance of {@link GetAnalystComments }
     * 
     */
    public GetAnalystComments createGetAnalystComments() {
        return new GetAnalystComments();
    }

    /**
     * Create an instance of {@link GetAnalystCommentsResponse }
     * 
     */
    public GetAnalystCommentsResponse createGetAnalystCommentsResponse() {
        return new GetAnalystCommentsResponse();
    }

    /**
     * Create an instance of {@link SendOrders2Response }
     * 
     */
    public SendOrders2Response createSendOrders2Response() {
        return new SendOrders2Response();
    }

    /**
     * Create an instance of {@link GetOrderStatus }
     * 
     */
    public GetOrderStatus createGetOrderStatus() {
        return new GetOrderStatus();
    }

    /**
     * Create an instance of {@link SendOrdersResponse }
     * 
     */
    public SendOrdersResponse createSendOrdersResponse() {
        return new SendOrdersResponse();
    }

    /**
     * Create an instance of {@link OrderChargeBack }
     * 
     */
    public OrderChargeBack createOrderChargeBack() {
        return new OrderChargeBack();
    }

    /**
     * Create an instance of {@link SubmitInfoResponse }
     * 
     */
    public SubmitInfoResponse createSubmitInfoResponse() {
        return new SubmitInfoResponse();
    }

    /**
     * Create an instance of {@link GetReturnAnalysis }
     * 
     */
    public GetReturnAnalysis createGetReturnAnalysis() {
        return new GetReturnAnalysis();
    }

    /**
     * Create an instance of {@link OrderChargeBackResponse }
     * 
     */
    public OrderChargeBackResponse createOrderChargeBackResponse() {
        return new OrderChargeBackResponse();
    }

    /**
     * Create an instance of {@link GetPackageStatus }
     * 
     */
    public GetPackageStatus createGetPackageStatus() {
        return new GetPackageStatus();
    }

    /**
     * Create an instance of {@link GetOrderStatusResponse }
     * 
     */
    public GetOrderStatusResponse createGetOrderStatusResponse() {
        return new GetOrderStatusResponse();
    }

    /**
     * Create an instance of {@link ArrayOfOrder }
     * 
     */
    public ArrayOfOrder createArrayOfOrder() {
        return new ArrayOfOrder();
    }

    /**
     * Create an instance of {@link TransactionStatus }
     * 
     */
    public TransactionStatus createTransactionStatus() {
        return new TransactionStatus();
    }

    /**
     * Create an instance of {@link Order }
     * 
     */
    public Order createOrder() {
        return new Order();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TransactionStatusCbk }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.clearsale.com.br/integration", name = "TransactionStatusCbk")
    public JAXBElement<TransactionStatusCbk> createTransactionStatusCbk(TransactionStatusCbk value) {
        return new JAXBElement<TransactionStatusCbk>(_TransactionStatusCbk_QNAME, TransactionStatusCbk.class, null, value);
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
