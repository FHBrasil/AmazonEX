package de.fliegersoftware.amazon.payment.model;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.junit.Test;

import com.amazonservices.mws.offamazonpayments.model.CancelOrderReferenceRequest;
import com.amazonservices.mws.offamazonpayments.model.CaptureRequest;
import com.amazonservices.mws.offamazonpayments.model.CloseAuthorizationRequest;
import com.amazonservices.mws.offamazonpayments.model.CloseOrderReferenceRequest;
import com.amazonservices.mws.offamazonpayments.model.GetCaptureDetailsRequest;
import com.amazonservices.mws.offamazonpayments.model.GetRefundDetailsRequest;
import com.amazonservices.mws.offamazonpayments.model.Price;
import com.amazonservices.mws.offamazonpayments.model.RefundRequest;
import com.sun.xml.bind.marshaller.CharacterEscapeHandler;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class BatchRequestTest {

	private String testXml = "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\"?>"
			+ "<Batch>"
			+ "<Product>Amazon Payments</Product>"
			+ "<Connection>"
			+ "<Version>2013-01-01</Version>"
			+ "</Connection>"
			+ "<Requests>"
			+ "<Request>"
			+ "<SellerRequestId>8</SellerRequestId>"
			+ "<RequestData>"
			+ "<![CDATA["
			+ "<CaptureRequest>"
			+ "<SellerId>A0677412396XWXEXAMPLE</SellerId>"
			+ "<AmazonAuthorizationId>"
			+ "S01-1238558-3179098-EXAMPLE"
			+ "</AmazonAuthorizationId>"
			+ "<CaptureReferenceId>cap06</CaptureReferenceId>"
			+ "<CaptureAmount>"
			+ "<Amount>10.00</Amount>"
			+ "<CurrencyCode>EUR</CurrencyCode>"
			+ "</CaptureAmount>"
			+ "</CaptureRequest>"
			+ "]]>"
			+ "</RequestData>"
			+ "</Request>"
			+ "<Request>"
			+ "<SellerRequestId>9</SellerRequestId>"
			+ "<RequestData>"
			+ "<![CDATA["
			+ "<CaptureRequest>"
			+ "<SellerId>A0677412396XWXEXAMPLE</SellerId>"
			+ "<AmazonAuthorizationId>"
			+ "S01-8438558-3173458-EXAMPLE"
			+ "</AmazonAuthorizationId>" 
			+ "<CaptureReferenceId>cap06</CaptureReferenceId>"
			+ "<CaptureAmount>"
			+ "<Amount>11.00</Amount>"
			+ "<CurrencyCode>EUR</CurrencyCode>"
			+ "</CaptureAmount>"
			+ "</CaptureRequest>"
			+ "]]>"
			+ "</RequestData>"
			+ "</Request>"
			+ "<Request>"
			+ "<SellerRequestId>10</SellerRequestId>"
			+ "<RequestData>"
			+ "<![CDATA["
			+ "<CaptureRequest>"
			+ "<SellerId>A0677412396XWXWRWHDJQ</SellerId>"
			+ "<AmazonAuthorizationId>"
			+ "S01-8438558-3179098-A024424"
			+ "</AmazonAuthorizationId>"
			+ "<CaptureReferenceId>cap06</CaptureReferenceId>"
			+ "<CaptureAmount>"
			+ "<Amount>154.00</Amount>"
			+ "<CurrencyCode>EUR</CurrencyCode>"
			+ "</CaptureAmount>"
			+ "</CaptureRequest>"
			+ "]]>"
			+ "</RequestData>"
			+ "</Request>"
			+ "</Requests>"
			+ "</Batch>";

	private JAXBContext getContext() throws JAXBException {
		return JAXBContext.newInstance(BatchRequest.class);
	}

	@Test
	public void marshalTest() throws Exception {
		BatchRequest r = new BatchRequest();
		r.setProduct("Amazon Payments");
		Connection connection = new Connection();
		connection.setVersion("2013-01-01");
		r.setConnection(connection);
		List<Request> elements = new ArrayList<>();
		r.setRequests(new RequestList(elements));
		// capture 1
		Request r1 = new Request();
		elements.add(r1);
		CaptureRequest capture1 = new CaptureRequest();
		r1.setRequestData(capture1);
		Price price1 = new Price();
		r1.setSellerRequestId("8");
		capture1.setCaptureAmount(price1);
		capture1.setSellerId("A0677412396XWXEXAMPLE");
		capture1.setAmazonAuthorizationId("S01-1238558-3179098-EXAMPLE");
		capture1.setCaptureReferenceId("cap06");
		price1.setAmount("10.00");
		price1.setCurrencyCode("EUR");
		// capture 2
		Request r2 = new Request();
		elements.add(r2);
		CaptureRequest capture2 = new CaptureRequest();
		r2.setRequestData(capture2);
		Price price2 = new Price();
		r2.setSellerRequestId("9");
		capture2.setCaptureAmount(price2);
		capture2.setSellerId("A0677412396XWXEXAMPLE");
		capture2.setAmazonAuthorizationId("S01-8438558-3173458-EXAMPLE");
		capture2.setCaptureReferenceId("cap06");
		price2.setAmount("11.00");
		price2.setCurrencyCode("EUR");
		// capture 3
		Request r3 = new Request();
		elements.add(r3);
		CaptureRequest capture3 = new CaptureRequest();
		r3.setRequestData(capture3);
		Price price3 = new Price();
		r3.setSellerRequestId("10");
		capture3.setCaptureAmount(price3);
		capture3.setSellerId("A0677412396XWXWRWHDJQ");
		capture3.setAmazonAuthorizationId("S01-8438558-3179098-A024424");
		capture3.setCaptureReferenceId("cap06");
		price3.setAmount("154.00");
		price3.setCurrencyCode("EUR");

		StringWriter writer = new StringWriter();
		Marshaller marshaller = getContext().createMarshaller();
		marshaller.setProperty(CharacterEscapeHandler.class.getName(),
				new CharacterEscapeHandler() {
					@Override
					public void escape(char[] ac, int i, int j, boolean flag,
							Writer writer) throws IOException {
						writer.write(ac, i, j);
					}
				});

		marshaller.marshal(r, writer);
		assertThat(writer.toString().toLowerCase(), is(testXml.toLowerCase()));
	}

	@Test
	public void unmarshalTest() throws Exception {
		BatchRequest r = (BatchRequest) getContext().createUnmarshaller().unmarshal(new StringReader(testXml));
		assertThat(r.getProduct(), is("Amazon Payments"));
		assertThat(r.getConnection().getVersion(), is("2013-01-01"));
		assertThat(r.getRequests().getElements().size(), is(3));
		assertThat(r.getRequests().getElements().get(0).getSellerRequestId(), is("8"));
		assertThat(r.getRequests().getElements().get(0).getRequestData(), is(CaptureRequest.class));
	}
}