<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="order" required="true" type="de.hybris.platform.commercefacades.order.data.OrderData" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="/WEB-INF/tld/ycommercetags.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="item_container_holder positive">
	<div class="title_holder">
		<div class="title">
			<div class="title-top">
				<span></span>
			</div>
		</div>
		<h2><spring:theme code="text.paymentMethod" text="Payment Method"/></h2>
	</div>
	<div class="item_container">
		<div class="left">
			<ul>
				<c:if test="${klarnaPaymentMode.code == 'klarnaInvoice'}">
					<li>${fn:escapeXml(klarnaPaymentMode.name)} - <spring:theme code="checkout.summary.selected.paymentMethod.description.klarnaInvoice" arguments="${formattedKlarnaInvoiceFee}" argumentSeparator="#~/@!£$%^" /></li>
				</c:if>
				<c:if test="${klarnaPaymentMode.code != 'klarnaInvoice'}">		
					<li>${fn:escapeXml(klarnaPaymentMode.name)} - <spring:theme code="checkout.summary.selected.paymentMethod.description.${klarnaPaymentMode.code}"/></li>
				</c:if>
			</ul>
		</div>
		<div class="left">
			<ul>
				<li><spring:theme code="checkout.summary.paymentMethod.klarna.paymentadr.equals.deliveryadr"/></li>
			</ul>
		</div>
	</div>
</div>