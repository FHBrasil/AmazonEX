<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ attribute name="product" type="de.hybris.platform.commercefacades.product.data.ProductData" %>

<a class="${themeName != 'kids' ? 'btn btn-cta' : ''} add addToCartButton" href="/store/w/${product.code}/addToCart">
	<spring:theme code="cart.page.add"/>
</a>