<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="order" required="true" type="de.hybris.platform.commercefacades.order.data.OrderData"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="headline">
	<spring:theme code="text.paymentDetails" text="Payment Details" />
</div>
<ul>
	<c:choose>
		<c:when test="${order.paymentInfo.cardType eq 'PAYPAL'}">
			<li><img src="https://www.paypalobjects.com/webstatic/en_US/i/buttons/pp-acceptance-small.png" alt="PayPal icon"/></li>
			<br/>
			<li><spring:theme code="paymentMethod.type"/></li>
			<li>${fn:escapeXml(order.paymentInfo.subscriptionId)}</li>
		</c:when>
		<c:otherwise>
			<li>${fn:escapeXml(order.paymentInfo.cardNumber)}</li>
			<li>${fn:escapeXml(order.paymentInfo.cardTypeData.name)}</li>
			<li><spring:theme code="paymentMethod.paymentDetails.expires"
					arguments="${fn:escapeXml(order.paymentInfo.expiryMonth)},${fn:escapeXml(order.paymentInfo.expiryYear)}" /></li>
		</c:otherwise>
	</c:choose>
</ul>
