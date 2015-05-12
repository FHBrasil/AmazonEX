<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>

<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>

<div class="features">
	<div class="f-row">
		<div class="left"><i class="fa fa-check"></i> <b>Recomendar</b></div>
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
	<!--
	<div class="f-row">
		<div class="left"><i class="fa fa-user"></i> <b>Consultora<br>Online</b></div>
		<div class="right">
			<a href="#">n�s ajudamos a montar seu look</a>
			<a href="#">d�vidas sobre nossas pe�as</a>
		</div>
	</div>
	-->
</div>
