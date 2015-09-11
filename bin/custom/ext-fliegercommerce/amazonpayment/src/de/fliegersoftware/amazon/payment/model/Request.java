package de.fliegersoftware.amazon.payment.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="", propOrder={"sellerRequestId", "requestData"})
@XmlRootElement(name="Request")
public class Request {

	@XmlElement(name="SellerRequestId")
	protected String sellerRequestId;

	@XmlJavaTypeAdapter(AdapterCDATA.class)
	@XmlElement(name="RequestData")
	protected Object requestData;

	public String getSellerRequestId() {
		return sellerRequestId;
	}

	public void setSellerRequestId(String sellerRequestId) {
		this.sellerRequestId = sellerRequestId;
	}

	public Object getRequestData() {
		return requestData;
	}

	public void setRequestData(Object requestData) {
		this.requestData = requestData;
	}
}
