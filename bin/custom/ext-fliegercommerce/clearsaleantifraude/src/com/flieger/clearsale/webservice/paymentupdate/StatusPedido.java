
package com.flieger.clearsale.webservice.paymentupdate;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StatusPedido.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="StatusPedido">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Reprovado"/>
 *     &lt;enumeration value="Aprovado"/>
 *     &lt;enumeration value="AguardandoPagamento"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "StatusPedido")
@XmlEnum
public enum StatusPedido {

    @XmlEnumValue("Reprovado")
    REPROVADO("Reprovado"),
    @XmlEnumValue("Aprovado")
    APROVADO("Aprovado"),
    @XmlEnumValue("AguardandoPagamento")
    AGUARDANDO_PAGAMENTO("AguardandoPagamento");
    private final String value;

    StatusPedido(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static StatusPedido fromValue(String v) {
        for (StatusPedido c: StatusPedido.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
