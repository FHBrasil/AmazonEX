<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="order" required="true" type="de.hybris.platform.commercefacades.order.data.OrderData"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div data-theme="b" data-role="content">
	<div data-theme="b">
		<h4 class="subItemHeader">
			<spring:theme code="text.paymentMethod" text="Payment Method" />
		</h4>
	</div>
	<div data-theme="b">
		<ul class="mFormList">
			<c:choose>
				<c:when test="${order.paymentInfo.cardType eq 'PAYPAL'}">
					<li><img
						src="https://www.paypalobjects.com/webstatic/en_US/i/buttons/pp-acceptance-small.png"
						alt="PayPal icon" /></li>
					<br />
					<li><spring:theme code="paymentMethod.type" /></li>
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
	</div>
</div>
