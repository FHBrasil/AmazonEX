<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>

<div class="precos">
	<c:choose>
		<c:when test="${product.oldPrice > product.price.value}">
			<s> 
				<spring:theme code="product.volumePrices.from"/>
				<spring:theme code="product.currencynotation" /> 
				<fmt:formatNumber value="${product.oldPrice}" type="number" minFractionDigits="2"/>
			</s>
			<strong> 
				<spring:theme code="product.volumePrices.to"/> 
				<format:fromPrice priceData="${product.price}"/>
			</strong>
		</c:when>
		<c:otherwise>
			<strong><format:fromPrice priceData="${product.price}"/></strong>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${product.priceParcels > 1}">
			<p>
				${product.priceParcels}
				<spring:theme code="product.volumePrices.parcel"/>
				<b>${product.parcelUnitPrice}</b>
			</p>
		</c:when>
		<c:otherwise>
			<spring:theme code="product.payment.methods.not.parceled"/>
		</c:otherwise>
	</c:choose>
</div>
