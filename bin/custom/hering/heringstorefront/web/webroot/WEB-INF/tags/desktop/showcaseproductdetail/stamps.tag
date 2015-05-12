<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>

<div class="selos">
	<ul>
		<c:if test="${product.freeShipping}">
		<li class="frete"><spring:theme code="product.attractions.freeshipping"/></li>
		</c:if>
		
		<c:if test="${product.newProduct && !(product.oldPrice > product.price.value)}">
		<li class="lancamento"><spring:theme code="product.attractions.new"/></li>
		</c:if>
		
		<c:choose>
			<c:when test="${product.blackfriday}">
				<li class="blackfriday"><spring:theme code="product.attractions.blackfriday"/></li>
			</c:when>
			<c:otherwise>
				<c:if test="${product.oldPrice > product.price.value}">
				<li class="promocao"><spring:theme code="product.attractions.sale.${themeName}"/></li>
				</c:if>
			</c:otherwise>
		</c:choose>
	</ul>
</div>
