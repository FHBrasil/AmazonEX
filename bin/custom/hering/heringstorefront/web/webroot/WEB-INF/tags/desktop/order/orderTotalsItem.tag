<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="order" required="true"
    type="de.hybris.platform.commercefacades.order.data.OrderData"%>
<%@ attribute name="orderHistory" required="false"
    type="de.hybris.platform.commercefacades.order.data.OrderHistoryData"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/desktop/order"%>
<%@ attribute name="containerCSS" required="false" type="java.lang.String"%>
<section id="values" class="right">
    <dl>
        <dt>Itens (${order.totalItems})</dt>
        <dd class="subtotal">
            <strong><format:price priceData="${order.subTotal}" displayFreeForZero="true" /></strong>
        </dd>
        <dt class="freight">Frete</dt>
        <dd>
            <format:price priceData="${order.deliveryCost}" displayFreeForZero="true" />
            <small>Entrega em at&eacute; ${order.estimatedDeliveryDays} dias &uacute;teis
                ap&oacute;s a emiss&atilde;o da nota fiscal</small>
        </dd>
        <dt class="method">Pagamento</dt>
        <dd class="method">${orderHistory.paymentMode}</dd>
    </dl>
    <dl class="total">
        <dt class="total">Total</dt>
        <dd class="total">
            <strong><format:price priceData="${order.totalPrice}" /></strong>
        </dd>
    </dl>
</section>
