package de.fliegersoftware.amazon.payment.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "product", "connection", "requests" })
@XmlRootElement(name = "Batch")
public class BatchRequest {

	@XmlElement(name = "Product")
	protected String product;
	@XmlElement(name = "Connection")
	protected Connection connection;
	@XmlElement(name = "Requests")
	protected RequestList requests;

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public RequestList getRequests() {
		return requests;
	}

	public void setRequests(RequestList requests) {
		this.requests = requests;
	}
}
