<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ attribute name="format" required="true" type="java.lang.String" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="ycommerce" uri="/WEB-INF/tld/ycommercetags.tld" %>

<c:set value="${ycommerce:productImage(product, format)}" var="primaryImage"/>

<c:choose>
	<c:when test="${not empty primaryImage}">
		<c:choose>
			<c:when test="${not empty primaryImage.altText}">
				<img src="${primaryImage.url}" alt="${primaryImage.altText}" title="${primaryImage.altText}"/>
			</c:when>
			<c:otherwise>
				<img src="${primaryImage.url}" alt="${product.name}" title="${product.name}"/>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:otherwise>
		<theme:image code="img.missingProductImage.${format}" alt="${product.name}" title="${product.name}"/>
	</c:otherwise>
</c:choose>
