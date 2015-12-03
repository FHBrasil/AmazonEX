package de.fliegersoftware.amazon.payment.model;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.util.StreamReaderDelegate;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.XMLReader;

import com.amazonservices.mws.offamazonpayments.model.CancelOrderReferenceRequest;
import com.amazonservices.mws.offamazonpayments.model.CaptureRequest;
import com.amazonservices.mws.offamazonpayments.model.CloseAuthorizationRequest;
import com.amazonservices.mws.offamazonpayments.model.CloseOrderReferenceRequest;
import com.amazonservices.mws.offamazonpayments.model.GetCaptureDetailsRequest;
import com.amazonservices.mws.offamazonpayments.model.GetRefundDetailsRequest;
import com.amazonservices.mws.offamazonpayments.model.RefundRequest;
import com.sun.xml.bind.marshaller.NamespacePrefixMapper;
import com.sun.xml.bind.v2.runtime.JAXBContextImpl;

public class AdapterCDATA extends XmlAdapter<String, Object>{

	private JAXBContext getContext() throws JAXBException {
		return JAXBContext.newInstance(CancelOrderReferenceRequest.class
				, CloseOrderReferenceRequest.class
				, CloseAuthorizationRequest.class
				, CaptureRequest.class
				, GetCaptureDetailsRequest.class
				, RefundRequest.class
				, GetRefundDetailsRequest.class);
	}

	@Override
	public String marshal(Object v) throws Exception {
		StringWriter stringWriter = new StringWriter();
		XMLStreamWriter writer = XMLOutputFactory.newInstance().createXMLStreamWriter(stringWriter);
		writer.setDefaultNamespace("http://mws.amazonservices.com/schema/OffAmazonPayments/2013-01-01");
		Marshaller marshaller = getContext().createMarshaller();
		marshaller.setProperty("com.sun.xml.bind.xmlDeclaration", Boolean.FALSE);
		marshaller.marshal(v, writer);
		writer.flush();
		return "<![CDATA[" + stringWriter.toString() + "]]>";
	}

	@Override
	public Object unmarshal(String v) throws Exception {
		Unmarshaller unmarshaller = getContext().createUnmarshaller();
		XMLStreamReader reader = new DefaultNamespaceXMLReader(
				XMLInputFactory.newFactory().createXMLStreamReader(new StringReader(v))
				, "http://mws.amazonservices.com/schema/OffAmazonPayments/2013-01-01");
		return unmarshaller.unmarshal(reader);
	}

	class DefaultNamespaceXMLReader extends StreamReaderDelegate {
		private String defaultNamespaceURI;
		public DefaultNamespaceXMLReader(XMLStreamReader reader, String defaultNamespaceURI) {
			super(reader);
			this.defaultNamespaceURI = defaultNamespaceURI;
		}
		@Override
		public String getNamespaceURI() {
			return defaultNamespaceURI;
		}
	}
}
