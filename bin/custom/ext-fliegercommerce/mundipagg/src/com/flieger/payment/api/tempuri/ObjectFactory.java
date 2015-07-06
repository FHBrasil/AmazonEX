
package com.flieger.payment.api.tempuri;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import com.flieger.payment.api.data.schemas.CreateOrderRequest;
import com.flieger.payment.api.data.schemas.GetInstantBuyDataRequest;
import com.flieger.payment.api.data.schemas.ManageOrderRequest;
import com.flieger.payment.api.data.schemas.QueryOrderRequest;
import com.flieger.payment.api.data.schemas.RetryOrderRequest;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.tempuri package. 
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

    private final static QName _ManageOrderResponseManageOrderResult_QNAME = new QName("http://tempuri.org/", "ManageOrderResult");
    private final static QName _CreateOrderCreateOrderRequest_QNAME = new QName("http://tempuri.org/", "createOrderRequest");
    private final static QName _CreateOrderResponseCreateOrderResult_QNAME = new QName("http://tempuri.org/", "CreateOrderResult");
    private final static QName _RetryOrderManualRetryRequest_QNAME = new QName("http://tempuri.org/", "manualRetryRequest");
    private final static QName _GetInstantBuyDataQueryCreditCardDataRequest_QNAME = new QName("http://tempuri.org/", "queryCreditCardDataRequest");
    private final static QName _ManageOrderManageOrderRequest_QNAME = new QName("http://tempuri.org/", "manageOrderRequest");
    private final static QName _RetryOrderResponseRetryOrderResult_QNAME = new QName("http://tempuri.org/", "RetryOrderResult");
    private final static QName _QueryOrderQueryOrderRequest_QNAME = new QName("http://tempuri.org/", "queryOrderRequest");
    private final static QName _GetInstantBuyDataResponseGetInstantBuyDataResult_QNAME = new QName("http://tempuri.org/", "GetInstantBuyDataResult");
    private final static QName _QueryOrderResponseQueryOrderResult_QNAME = new QName("http://tempuri.org/", "QueryOrderResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.tempuri
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link org.tempuri.CreateOrderResponse }
     * 
     */
    public com.flieger.payment.api.tempuri.CreateOrderResponse createCreateOrderResponse() {
        return new com.flieger.payment.api.tempuri.CreateOrderResponse();
    }

    /**
     * Create an instance of {@link org.tempuri.RetryOrderResponse }
     * 
     */
    public com.flieger.payment.api.tempuri.RetryOrderResponse createRetryOrderResponse() {
        return new com.flieger.payment.api.tempuri.RetryOrderResponse();
    }

    /**
     * Create an instance of {@link org.tempuri.GetInstantBuyDataResponse }
     * 
     */
    public com.flieger.payment.api.tempuri.GetInstantBuyDataResponse createGetInstantBuyDataResponse() {
        return new com.flieger.payment.api.tempuri.GetInstantBuyDataResponse();
    }

    /**
     * Create an instance of {@link QueryOrder }
     * 
     */
    public QueryOrder createQueryOrder() {
        return new QueryOrder();
    }

    /**
     * Create an instance of {@link org.tempuri.ManageOrderResponse }
     * 
     */
    public com.flieger.payment.api.tempuri.ManageOrderResponse createManageOrderResponse() {
        return new com.flieger.payment.api.tempuri.ManageOrderResponse();
    }

    /**
     * Create an instance of {@link CreateOrder }
     * 
     */
    public CreateOrder createCreateOrder() {
        return new CreateOrder();
    }

    /**
     * Create an instance of {@link GetInstantBuyData }
     * 
     */
    public GetInstantBuyData createGetInstantBuyData() {
        return new GetInstantBuyData();
    }

    /**
     * Create an instance of {@link org.tempuri.QueryOrderResponse }
     * 
     */
    public com.flieger.payment.api.tempuri.QueryOrderResponse createQueryOrderResponse() {
        return new com.flieger.payment.api.tempuri.QueryOrderResponse();
    }

    /**
     * Create an instance of {@link ManageOrder }
     * 
     */
    public ManageOrder createManageOrder() {
        return new ManageOrder();
    }

    /**
     * Create an instance of {@link RetryOrder }
     * 
     */
    public RetryOrder createRetryOrder() {
        return new RetryOrder();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link org.datacontract.schemas._2004._07.mundipagg_one_service.ManageOrderResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "ManageOrderResult", scope = com.flieger.payment.api.tempuri.ManageOrderResponse.class)
    public JAXBElement<com.flieger.payment.api.data.schemas.ManageOrderResponse> createManageOrderResponseManageOrderResult(com.flieger.payment.api.data.schemas.ManageOrderResponse value) {
        return new JAXBElement<com.flieger.payment.api.data.schemas.ManageOrderResponse>(_ManageOrderResponseManageOrderResult_QNAME, com.flieger.payment.api.data.schemas.ManageOrderResponse.class, com.flieger.payment.api.tempuri.ManageOrderResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateOrderRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "createOrderRequest", scope = CreateOrder.class)
    public JAXBElement<CreateOrderRequest> createCreateOrderCreateOrderRequest(CreateOrderRequest value) {
        return new JAXBElement<CreateOrderRequest>(_CreateOrderCreateOrderRequest_QNAME, CreateOrderRequest.class, CreateOrder.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link org.datacontract.schemas._2004._07.mundipagg_one_service.CreateOrderResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "CreateOrderResult", scope = com.flieger.payment.api.tempuri.CreateOrderResponse.class)
    public JAXBElement<com.flieger.payment.api.data.schemas.CreateOrderResponse> createCreateOrderResponseCreateOrderResult(com.flieger.payment.api.data.schemas.CreateOrderResponse value) {
        return new JAXBElement<com.flieger.payment.api.data.schemas.CreateOrderResponse>(_CreateOrderResponseCreateOrderResult_QNAME, com.flieger.payment.api.data.schemas.CreateOrderResponse.class, com.flieger.payment.api.tempuri.CreateOrderResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetryOrderRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "manualRetryRequest", scope = RetryOrder.class)
    public JAXBElement<RetryOrderRequest> createRetryOrderManualRetryRequest(RetryOrderRequest value) {
        return new JAXBElement<RetryOrderRequest>(_RetryOrderManualRetryRequest_QNAME, RetryOrderRequest.class, RetryOrder.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetInstantBuyDataRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "queryCreditCardDataRequest", scope = GetInstantBuyData.class)
    public JAXBElement<GetInstantBuyDataRequest> createGetInstantBuyDataQueryCreditCardDataRequest(GetInstantBuyDataRequest value) {
        return new JAXBElement<GetInstantBuyDataRequest>(_GetInstantBuyDataQueryCreditCardDataRequest_QNAME, GetInstantBuyDataRequest.class, GetInstantBuyData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ManageOrderRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "manageOrderRequest", scope = ManageOrder.class)
    public JAXBElement<ManageOrderRequest> createManageOrderManageOrderRequest(ManageOrderRequest value) {
        return new JAXBElement<ManageOrderRequest>(_ManageOrderManageOrderRequest_QNAME, ManageOrderRequest.class, ManageOrder.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link org.datacontract.schemas._2004._07.mundipagg_one_service.RetryOrderResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "RetryOrderResult", scope = com.flieger.payment.api.tempuri.RetryOrderResponse.class)
    public JAXBElement<com.flieger.payment.api.data.schemas.RetryOrderResponse> createRetryOrderResponseRetryOrderResult(com.flieger.payment.api.data.schemas.RetryOrderResponse value) {
        return new JAXBElement<com.flieger.payment.api.data.schemas.RetryOrderResponse>(_RetryOrderResponseRetryOrderResult_QNAME, com.flieger.payment.api.data.schemas.RetryOrderResponse.class, com.flieger.payment.api.tempuri.RetryOrderResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryOrderRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "queryOrderRequest", scope = QueryOrder.class)
    public JAXBElement<QueryOrderRequest> createQueryOrderQueryOrderRequest(QueryOrderRequest value) {
        return new JAXBElement<QueryOrderRequest>(_QueryOrderQueryOrderRequest_QNAME, QueryOrderRequest.class, QueryOrder.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link org.datacontract.schemas._2004._07.mundipagg_one_service.GetInstantBuyDataResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "GetInstantBuyDataResult", scope = com.flieger.payment.api.tempuri.GetInstantBuyDataResponse.class)
    public JAXBElement<com.flieger.payment.api.data.schemas.GetInstantBuyDataResponse> createGetInstantBuyDataResponseGetInstantBuyDataResult(com.flieger.payment.api.data.schemas.GetInstantBuyDataResponse value) {
        return new JAXBElement<com.flieger.payment.api.data.schemas.GetInstantBuyDataResponse>(_GetInstantBuyDataResponseGetInstantBuyDataResult_QNAME, com.flieger.payment.api.data.schemas.GetInstantBuyDataResponse.class, com.flieger.payment.api.tempuri.GetInstantBuyDataResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link org.datacontract.schemas._2004._07.mundipagg_one_service.QueryOrderResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "QueryOrderResult", scope = com.flieger.payment.api.tempuri.QueryOrderResponse.class)
    public JAXBElement<com.flieger.payment.api.data.schemas.QueryOrderResponse> createQueryOrderResponseQueryOrderResult(com.flieger.payment.api.data.schemas.QueryOrderResponse value) {
        return new JAXBElement<com.flieger.payment.api.data.schemas.QueryOrderResponse>(_QueryOrderResponseQueryOrderResult_QNAME, com.flieger.payment.api.data.schemas.QueryOrderResponse.class, com.flieger.payment.api.tempuri.QueryOrderResponse.class, value);
    }

}
