<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData"%>
<%@ attribute name="upper" required="false" type="java.lang.Boolean" %>
<%@ attribute name="cart" required="false" type="java.lang.Boolean" %>

<c:choose>
	<c:when test="${cart}">
		<c:set var="sharp" value="#"/>
		<a href="${product.url}"><b>[BRAND]</b>&nbsp;${product.name}</a>
		<br /><small>${sharp}${product.code}</small>
		<br /><small><span class="stock150619 onstock">sofort lieferbar</span></small>
	</c:when>
	<c:otherwise>
		<c:if test="${upper}">
			<h1><b>[BRAND]</b>&nbsp;${product.name}</h1>
		</c:if>
		<c:if test="${!upper}">
			<h2><b>[BRAND]</b>&nbsp;${product.name}</h2>
		</c:if>
	</c:otherwise>
</c:choose>