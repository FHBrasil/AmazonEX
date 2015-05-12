<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<c:if test="${themeName == 'dzarm'}">
	<product:productDzarmVariantSelector product="${product}"/>
</c:if>
<c:if test="${themeName != 'dzarm'}">
	<product:productVariantSelector product="${product}"/>
</c:if>
