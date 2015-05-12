<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ attribute name="format" required="true" type="java.lang.String" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<c:set var="count" value="0"/>
<c:set value="${ycommerce:productImageFormat(product, format)}" var="images"/>

<c:choose>
	<c:when test="${not empty images}">

		<c:forEach items="${images}" var="primaryImage">
			
			<c:set var="className" value="${count > 0 ? 'hide' : 'show'}"></c:set>
			<c:choose>
				<c:when test="${not empty primaryImage.altText}">
					<img src="${primaryImage.url}"
						 class="${className}" 
						 alt="${fn:escapeXml(primaryImage.altText)}" 
						 title="${fn:escapeXml(primaryImage.altText)}"/>
				</c:when>
				<c:otherwise>
					<img src="${primaryImage.url}" 
						 class="${className}"
						 alt="${fn:escapeXml(product.name)}" 
						 title="${fn:escapeXml(product.name)}"/>
				</c:otherwise>
			</c:choose>
			
			<c:set var="count" value="${count + 1}"/>
		</c:forEach>
		
	</c:when>
	<c:otherwise>
		<theme:image code="img.missingProductImage.${format}" 
			alt="${fn:escapeXml(product.name)}" 
			title="${fn:escapeXml(product.name)}"/>
	</c:otherwise>
</c:choose>