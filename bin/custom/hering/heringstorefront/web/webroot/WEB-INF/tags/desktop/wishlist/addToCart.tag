<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ attribute name="product" type="de.hybris.platform.commercefacades.product.data.ProductData" %>

<c:url var="addUrl" value="/w/${product.code}/addToCart"/>
<a class="add addToCartButton" href="${addUrl}">
	<spring:theme code="cart.page.add"/>
</a>