//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/com.pixi.webservices.jaxb/jaxb">http://java.sun.com/com.pixi.webservices.jaxb/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.30 at 04:28:40 PM CEST 
//


package com.pixi.webservices.jaxb.orderstatus.request;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.pixi.webservices.jaxb.orderstatus.request package. 
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

    private final static QName _FULLPRICE_QNAME = new QName("", "FULL_PRICE");
    private final static QName _TRACKINGID_QNAME = new QName("", "TRACKINGID");
    private final static QName _PRICEAMOUNT_QNAME = new QName("", "PRICE_AMOUNT");
    private final static QName _ITEMNOTE_QNAME = new QName("", "ITEM_NOTE");
    private final static QName _DISCOUNTVALUE_QNAME = new QName("", "DISCOUNT_VALUE");
    private final static QName _STATUS_QNAME = new QName("", "STATUS");
    private final static QName _LINEITEMID_QNAME = new QName("", "LINE_ITEM_ID");
    private final static QName _QUANTITY_QNAME = new QName("", "QUANTITY");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.pixi.webservices.jaxb.orderstatus.request
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ImportOrderStatusRequest }
     * 
     */
    public ImportOrderStatusRequest createORDERITEM() {
        return new ImportOrderStatusRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "FULL_PRICE")
    public JAXBElement<BigDecimal> createFULLPRICE(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_FULLPRICE_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "TRACKINGID")
    public JAXBElement<String> createTRACKINGID(String value) {
        return new JAXBElement<String>(_TRACKINGID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "PRICE_AMOUNT")
    public JAXBElement<BigDecimal> createPRICEAMOUNT(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_PRICEAMOUNT_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ITEM_NOTE")
    public JAXBElement<String> createITEMNOTE(String value) {
        return new JAXBElement<String>(_ITEMNOTE_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "DISCOUNT_VALUE")
    public JAXBElement<BigDecimal> createDISCOUNTVALUE(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_DISCOUNTVALUE_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "STATUS")
    public JAXBElement<String> createSTATUS(String value) {
        return new JAXBElement<String>(_STATUS_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "LINE_ITEM_ID")
    public JAXBElement<String> createLINEITEMID(String value) {
        return new JAXBElement<String>(_LINEITEMID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "QUANTITY")
    public JAXBElement<Integer> createQUANTITY(Integer value) {
        return new JAXBElement<Integer>(_QUANTITY_QNAME, Integer.class, null, value);
    }

}
