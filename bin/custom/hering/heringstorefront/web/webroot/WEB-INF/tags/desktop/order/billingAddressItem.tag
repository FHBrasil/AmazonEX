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

<div class="panel-heading"><span class="glyphicon glyphicon-file"></span> Rechnungsadresse
</div>
<div class="panel-body">
	${fn:escapeXml(order.billingAddress.firstName)}&nbsp;
    ${fn:escapeXml(order.billingAddress.lastName)}<br>
	${fn:escapeXml(order.billingAddress.line1)}&nbsp;
    ${fn:escapeXml(order.billingAddress.number)}<br>
    ${fn:escapeXml(order.billingAddress.complement)}
    <c:if test="${not empty order.billingAddress.reference}">
		${fn:escapeXml(order.billingAddress.reference)}
    </c:if>
    ${fn:escapeXml(order.billingAddress.postalCode)}
    - ${fn:escapeXml(order.billingAddress.town)}
    <c:if test="${not empty order.billingAddress.region.name}">
    	-${fn:escapeXml(order.billingAddress.region.isocodeShort)}
   	</c:if>
   	<br>
    ${fn:escapeXml(order.billingAddress.district)}<br>
	${order.billingAddress.country.name}
</div>
<!-- <section id="payment-address" class="section-block"> -->
<!--     <header> -->
<%--         <h2><spring:theme code="text.fliegercommerce.texto116"/>:</h2> --%>
<!--     </header> -->
<!--     <dl> -->
<%--         <c:if test="${not empty order.billingAddress.firstName}"> --%>
<%--             <dt><spring:theme code="text.fliegercommerce.texto18"/>:</dt> --%>
<%--             <dd>${fn:escapeXml(order.billingAddress.firstName)}&nbsp; --%>
<%--                 ${fn:escapeXml(order.billingAddress.lastName)}</dd> --%>
<%--         </c:if> --%>
<%--         <c:if test="${not empty order.billingAddress.receiver}"> --%>
<%--             <dt><spring:theme code="text.fliegercommerce.texto19"/>:</dt> --%>
<%--             <dd>${fn:escapeXml(order.billingAddress.receiver)}</dd> --%>
<%--         </c:if> --%>
<%--         <c:if test="${not empty order.billingAddress.dddPhone}"> --%>
<%--             <dt><spring:theme code="text.fliegercommerce.texto20"/>:</dt> --%>
<%--             <dd>(${fn:escapeXml(order.billingAddress.dddPhone)})${fn:escapeXml(order.billingAddress.phone)}</dd> --%>
<%--         </c:if> --%>
<%--         <c:if test="${not empty order.billingAddress.celPhone}"> --%>
<%--             <dt><spring:theme code="text.fliegercommerce.texto21"/>:</dt> --%>
<%--             <dd>(${fn:escapeXml(order.billingAddress.dddCelPhone)})${fn:escapeXml(order.billingAddress.celPhone)}</dd> --%>
<%--         </c:if> --%>
<%--         <dt><spring:theme code="text.fliegercommerce.texto22"/>:</dt> --%>
<%--         <dd>${fn:escapeXml(order.billingAddress.line1)}&nbsp; --%>
<%--             ${fn:escapeXml(order.billingAddress.number)}</dd> --%>
<%--         <dt><spring:theme code="text.fliegercommerce.texto91"/>:</dt> --%>
<%--         <dd>${fn:escapeXml(order.billingAddress.complement)}</dd> --%>
<%--         <c:if test="${not empty order.billingAddress.reference}"> --%>
<%--             <dt><spring:theme code="text.fliegercommerce.texto23"/>:</dt> --%>
<%--             <dd>${fn:escapeXml(order.billingAddress.reference)}</dd> --%>
<%--         </c:if> --%>
<%--         <dt><spring:theme code="text.fliegercommerce.texto92"/>:</dt> --%>
<%--         <dd>${fn:escapeXml(order.billingAddress.postalCode)} --%>
<%--             - ${fn:escapeXml(order.billingAddress.town)} --%>
<%--             <c:if test="${not empty order.billingAddress.region.name}">-${fn:escapeXml(order.billingAddress.region.isocodeShort)}</c:if> --%>
<!--         </dd> -->
<%--         <dt><spring:theme code="text.fliegercommerce.texto93"/>:</dt> --%>
<%--         <dd>${fn:escapeXml(order.billingAddress.district)}</dd> --%>
<!--     </dl> -->
<!-- </section> -->
