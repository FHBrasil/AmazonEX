<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>

<%-- BRAND IMAGE --%>
<c:choose>
	<c:when test="${product.brand == 'dzarm'}">
		<img src="/store/_ui/desktop/theme-dzarm/images/table-brand-dzarm.png" alt="Dzarm">
	</c:when>
	<c:when test="${product.brand == 'foryou'}">
		<img src="/store/_ui/desktop/theme-foryou/images/table-brand-hering.png" alt="Hering For You">
	</c:when>
	<c:when test="${product.brand == 'hering'}">
		<img src="/store/_ui/desktop/theme-hering/images/table-brand-hering.png" alt="Hering">
	</c:when>
	<c:otherwise>
	</c:otherwise>
</c:choose>