<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="order" required="true"
    type="de.hybris.platform.commercefacades.order.data.OrderData"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<section id="payment-address" class="section-block">
    <header>
        <h2>Endere�o de faturamento:</h2>
    </header>
    <dl>
        <c:if test="${not empty order.billingAddress.firstName}">
            <dt>Nome:</dt>
            <dd>${fn:escapeXml(order.billingAddress.firstName)}&nbsp;
                ${fn:escapeXml(order.billingAddress.lastName)}</dd>
        </c:if>
        <c:if test="${not empty order.billingAddress.receiver}">
            <dt>Destinat�rio:</dt>
            <dd>${fn:escapeXml(order.billingAddress.receiver)}</dd>
        </c:if>
        <c:if test="${not empty order.billingAddress.dddPhone}">
            <dt>Telefone:</dt>
            <dd>(${fn:escapeXml(order.billingAddress.dddPhone)})${fn:escapeXml(order.billingAddress.phone)}</dd>
        </c:if>
        <c:if test="${not empty order.billingAddress.celPhone}">
            <dt>Tel Celular:</dt>
            <dd>(${fn:escapeXml(order.billingAddress.dddCelPhone)})${fn:escapeXml(order.billingAddress.celPhone)}</dd>
        </c:if>
        <dt>Endere�o:</dt>
        <dd>${fn:escapeXml(order.billingAddress.line1)}&nbsp;
            ${fn:escapeXml(order.billingAddress.number)}</dd>
        <dt>Complemento:</dt>
        <dd>${fn:escapeXml(order.billingAddress.complement)}</dd>
        <c:if test="${not empty order.billingAddress.reference}">
            <dt>Refer�ncia:</dt>
            <dd>${fn:escapeXml(order.billingAddress.reference)}</dd>
        </c:if>
        <dt>Cep:</dt>
        <dd>${fn:escapeXml(order.billingAddress.postalCode)}
            - ${fn:escapeXml(order.billingAddress.town)}
            <c:if test="${not empty order.billingAddress.region.name}">-${fn:escapeXml(order.billingAddress.region.isocodeShort)}</c:if>
        </dd>
        <dt>Bairro:</dt>
        <dd>${fn:escapeXml(order.billingAddress.district)}</dd>
    </dl>
</section>
