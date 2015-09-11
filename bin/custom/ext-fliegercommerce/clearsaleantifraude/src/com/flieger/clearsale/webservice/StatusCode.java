
package com.flieger.clearsale.webservice;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StatusCode.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="StatusCode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Invalido"/>
 *     &lt;enumeration value="OK"/>
 *     &lt;enumeration value="UserNotFound"/>
 *     &lt;enumeration value="XMLValidation"/>
 *     &lt;enumeration value="XMLTransform"/>
 *     &lt;enumeration value="UnexpectedError"/>
 *     &lt;enumeration value="OrderExist"/>
 *     &lt;enumeration value="InputPlugin"/>
 *     &lt;enumeration value="OutputPlugin"/>
 *     &lt;enumeration value="PaymentTypeNotFound"/>
 *     &lt;enumeration value="CardTypeNotFound"/>
 *     &lt;enumeration value="StatusNotPermited"/>
 *     &lt;enumeration value="OrderNotFound"/>
 *     &lt;enumeration value="OKComErros"/>
 *     &lt;enumeration value="FilaNaoEncontrada"/>
 *     &lt;enumeration value="InvalidRequest"/>
 *     &lt;enumeration value="PaymentNotFound"/>
 *     &lt;enumeration value="NsuExists"/>
 *     &lt;enumeration value="NsuNotFound"/>
 *     &lt;enumeration value="NsuDuplicate"/>
 *     &lt;enumeration value="QuizInactive"/>
 *     &lt;enumeration value="GenericFieldNotFound"/>
 *     &lt;enumeration value="QuizNotFound"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "StatusCode")
@XmlEnum
public enum StatusCode {

    @XmlEnumValue("Invalido")
    INVALIDO("Invalido"),
    OK("OK"),
    @XmlEnumValue("UserNotFound")
    USER_NOT_FOUND("UserNotFound"),
    @XmlEnumValue("XMLValidation")
    XML_VALIDATION("XMLValidation"),
    @XmlEnumValue("XMLTransform")
    XML_TRANSFORM("XMLTransform"),
    @XmlEnumValue("UnexpectedError")
    UNEXPECTED_ERROR("UnexpectedError"),
    @XmlEnumValue("OrderExist")
    ORDER_EXIST("OrderExist"),
    @XmlEnumValue("InputPlugin")
    INPUT_PLUGIN("InputPlugin"),
    @XmlEnumValue("OutputPlugin")
    OUTPUT_PLUGIN("OutputPlugin"),
    @XmlEnumValue("PaymentTypeNotFound")
    PAYMENT_TYPE_NOT_FOUND("PaymentTypeNotFound"),
    @XmlEnumValue("CardTypeNotFound")
    CARD_TYPE_NOT_FOUND("CardTypeNotFound"),
    @XmlEnumValue("StatusNotPermited")
    STATUS_NOT_PERMITED("StatusNotPermited"),
    @XmlEnumValue("OrderNotFound")
    ORDER_NOT_FOUND("OrderNotFound"),
    @XmlEnumValue("OKComErros")
    OK_COM_ERROS("OKComErros"),
    @XmlEnumValue("FilaNaoEncontrada")
    FILA_NAO_ENCONTRADA("FilaNaoEncontrada"),
    @XmlEnumValue("InvalidRequest")
    INVALID_REQUEST("InvalidRequest"),
    @XmlEnumValue("PaymentNotFound")
    PAYMENT_NOT_FOUND("PaymentNotFound"),
    @XmlEnumValue("NsuExists")
    NSU_EXISTS("NsuExists"),
    @XmlEnumValue("NsuNotFound")
    NSU_NOT_FOUND("NsuNotFound"),
    @XmlEnumValue("NsuDuplicate")
    NSU_DUPLICATE("NsuDuplicate"),
    @XmlEnumValue("QuizInactive")
    QUIZ_INACTIVE("QuizInactive"),
    @XmlEnumValue("GenericFieldNotFound")
    GENERIC_FIELD_NOT_FOUND("GenericFieldNotFound"),
    @XmlEnumValue("QuizNotFound")
    QUIZ_NOT_FOUND("QuizNotFound");
    private final String value;

    StatusCode(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static StatusCode fromValue(String v) {
        for (StatusCode c: StatusCode.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
