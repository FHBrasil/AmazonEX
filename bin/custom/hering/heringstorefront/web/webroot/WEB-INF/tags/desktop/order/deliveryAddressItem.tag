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
<%-- <c:set var="hasShippedItems" value="${order.deliveryItemsQuantity > 0}" /> --%>
<div class="panel-heading"><span class="glyphicon glyphicon-map-marker"></span> Lieferadresse
</div>
<div class="panel-body">
	${fn:escapeXml(order.deliveryAddress.firstName)}&nbsp;
    ${fn:escapeXml(order.deliveryAddress.lastName)}<br>
	${fn:escapeXml(order.deliveryAddress.line1)}&nbsp;
    ${fn:escapeXml(order.deliveryAddress.number)}<br>
    ${fn:escapeXml(order.deliveryAddress.complement)}
    <c:if test="${not empty order.deliveryAddress.reference}">
		${fn:escapeXml(order.deliveryAddress.reference)}
    </c:if>
    ${fn:escapeXml(order.deliveryAddress.postalCode)}
    - ${fn:escapeXml(order.deliveryAddress.town)}
    <c:if test="${not empty order.deliveryAddress.region.name}">
    	-${fn:escapeXml(order.deliveryAddress.region.isocodeShort)}
   	</c:if>
   	<br>
    ${fn:escapeXml(order.deliveryAddress.district)}<br>
	${order.deliveryAddress.country.name}
</div>
<!-- <section id="shipping-address" class="section-block"> -->
<!--     <header> -->
<%--         <h2><spring:theme code="text.fliegercommerce.texto113"/>:</h2> --%>
<!--     </header> -->
<%--     <c:if test="${not hasShippedItems}"> --%>
<%--         <spring:theme code="checkout.pickup.no.delivery.required" /> --%>
<%--     </c:if> --%>
<%--     <c:if test="${hasShippedItems}"> --%>
<!--         <dl> -->
<%--             <dt><spring:theme code="text.fliegercommerce.texto18"/>:</dt> --%>
<%--             <dd>${fn:escapeXml(order.deliveryAddress.firstName)}&nbsp; --%>
<%--                 ${fn:escapeXml(order.deliveryAddress.lastName)}</dd> --%>
<%--             <c:if test="${not empty order.deliveryAddress.receiver}"> --%>
<%--                 <dt><spring:theme code="text.fliegercommerce.texto19"/>:</dt> --%>
<%--                 <dd>${fn:escapeXml(order.deliveryAddress.receiver)}</dd> --%>
<%--             </c:if> --%>
<%--             <dt><spring:theme code="text.fliegercommerce.texto20"/>:</dt> --%>
<%--             <dd>(${fn:escapeXml(order.deliveryAddress.dddPhone)})${fn:escapeXml(order.deliveryAddress.phone)}</dd> --%>
<%--             <c:if test="${not empty order.deliveryAddress.celPhone}"> --%>
<%--                 <dt><spring:theme code="text.fliegercommerce.texto21"/>:</dt> --%>
<%--                 <dd>(${fn:escapeXml(order.deliveryAddress.dddCelPhone)})${fn:escapeXml(order.deliveryAddress.celPhone)}</dd> --%>
<%--             </c:if> --%>
<%--             <dt><spring:theme code="text.fliegercommerce.texto22"/>:</dt> --%>
<%--             <dd>${fn:escapeXml(order.deliveryAddress.line1)}&nbsp; --%>
<%--                 ${fn:escapeXml(order.deliveryAddress.number)}</dd> --%>
<%--             <dt><spring:theme code="text.fliegercommerce.texto91"/>:</dt> --%>
<%--             <dd>${fn:escapeXml(order.deliveryAddress.complement)}</dd> --%>
<%--             <c:if test="${not empty order.deliveryAddress.reference}"> --%>
<%--                 <dt><spring:theme code="text.fliegercommerce.texto23"/>:</dt> --%>
<%--                 <dd>${fn:escapeXml(order.deliveryAddress.reference)}</dd> --%>
<%--             </c:if> --%>
<%--             <dt><spring:theme code="text.fliegercommerce.texto92"/>:</dt> --%>
<%--             <dd>${fn:escapeXml(order.deliveryAddress.postalCode)} --%>
<%--                 - ${fn:escapeXml(order.deliveryAddress.town)} --%>
<%--                 <c:if test="${not empty order.deliveryAddress.region.name}">-${fn:escapeXml(order.deliveryAddress.region.isocodeShort)}</c:if> --%>
<!--             </dd> -->
<%--             <dt><spring:theme code="text.fliegercommerce.texto93"/>:</dt> --%>
<%--             <dd>${fn:escapeXml(order.deliveryAddress.district)}</dd> --%>
<!--         </dl> -->
<%--     </c:if> --%>
<!-- </section> -->
