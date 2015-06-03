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
        <dt><spring:theme code="text.fliegercommerce.texto85"/> (${order.totalItems})</dt>
        <dd class="subtotal">
            <strong><format:price priceData="${order.subTotal}" displayFreeForZero="true" /></strong>
        </dd>
        <dt class="freight"><spring:theme code="text.fliegercommerce.texto59"/></dt>
        <dd>
            <format:price priceData="${order.deliveryCost}" displayFreeForZero="true" />
            <small><spring:theme code="text.fliegercommerce.texto86"/> ${order.estimatedDeliveryDays} <spring:theme code="text.fliegercommerce.texto87"/></small>
        </dd>
        <dt class="method"><spring:theme code="text.fliegercommerce.texto75"/></dt>
        <dd class="method">${orderHistory.paymentMode}</dd>
    </dl>
    <dl class="total">
        <dt class="total"><spring:theme code="text.fliegercommerce.texto77"/></dt>
        <dd class="total">
            <strong><format:price priceData="${order.totalPrice}" /></strong>
        </dd>
    </dl>
</section>
