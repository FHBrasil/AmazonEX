//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/com.pixi.webservices.jaxb/jaxb">http://java.sun.com/com.pixi.webservices.jaxb/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.30 at 04:14:01 PM CEST 
//


package com.pixi.webservices.jaxb.response;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.pixi.webservices.jaxb.response package. 
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

    private final static QName _Message_QNAME = new QName("", "message");
    private final static QName _Code_QNAME = new QName("", "code");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.pixi.webservices.jaxb.response
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GlobalResponse }
     * 
     */
    public GlobalResponse createANSWER() {
        return new GlobalResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "message")
    public JAXBElement<String> createMessage(String value) {
        return new JAXBElement<String>(_Message_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "code")
    public JAXBElement<String> createCode(String value) {
        return new JAXBElement<String>(_Code_QNAME, String.class, null, value);
    }

}
