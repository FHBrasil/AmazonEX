<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ attribute name="productUrl" required="true" type="java.lang.String" %>

<span class="detalhes">
	<a class="productMainLink" href="${productUrl}"><spring:theme code="product.viewmore"/></a>
</span>