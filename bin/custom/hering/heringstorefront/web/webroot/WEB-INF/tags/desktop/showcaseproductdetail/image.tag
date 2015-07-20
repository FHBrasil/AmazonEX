<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="images" required="true" type="java.util.ArrayList" %>
<%@ attribute name="format" required="true" type="java.lang.String" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>

<c:choose>
	<c:when test="${not empty images}">
		<c:forEach items="${images}" var="primaryImage" end="0">

			<c:if test="${not empty primaryImage.altText}">
				<c:set var="imgText" value="${fn:escapeXml(primaryImage.altText)}"/>
			</c:if>
			<c:if test="${empty primaryImage.altText}">
				<c:set var="imgText" value="${fn:escapeXml(product.name)}"/>
			</c:if>
			
			<img src="${primaryImage.url}" class="show"
			 	alt="${imgText}" title="${imgText}"/>
			
		</c:forEach>
		<c:forEach items="${images}" var="primaryImage">
			<span class="hide" data-img="${primaryImage.url}"></span>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<theme:image code="img.missingProductImage.${format}" 
			alt="${fn:escapeXml(product.name)}" 
			title="${fn:escapeXml(product.name)}"/>
	</c:otherwise>
</c:choose>

