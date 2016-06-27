package de.fliegersoftware.amazon.payment.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="", propOrder={"elements"})
@XmlRootElement(name="Requests")
public class RequestList {

	@XmlElement(name="Request")
	protected List<Request> elements;

	public RequestList() {
	}

	public RequestList(List<Request> elements) {
		this.elements = elements;
	}

	public List<Request> getElements() {
		return elements;
	}

	public void setElements(List<Request> elements) {
		this.elements = elements;
	}
}