<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>

<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>

<div class="features">
	<div class="f-row">
		<div class="left"><i class="fa fa-check"></i> <b><spring:theme code="text.fliegercommerce.texto46"/></b></div>
		<div class="right">
			<c:choose>
			<c:when test="${product.purchasable and product.stock.stockLevelStatus.code ne 'outOfStock' }">
				<product:productPaymentMethods product="${product}"/>
				<product:productShippingPrice product="${product}"/>
			</c:when>
			<c:otherwise>
				<product:productSocialSharing />
			</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>
