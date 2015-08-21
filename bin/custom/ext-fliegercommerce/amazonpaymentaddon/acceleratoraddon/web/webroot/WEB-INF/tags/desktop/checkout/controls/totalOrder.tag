<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="cartData" required="true" type="de.hybris.platform.commercefacades.order.data.CartData"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="widgetTotalOrder">
	<div class="col-xs-6 text-right charge150127">
		<spring:theme code="basket.page.totals.sum" />
	</div>
	<div class="col-xs-5 text-right charge150127">
		<format:price priceData="${cartData.subTotal}" />
	</div>
	<div class="col-xs-6 text-right charge150127">
		<spring:theme code="basket.page.totals.savings" />
	</div>
	<div class="col-xs-5 text-right charge150127">
		<format:price priceData="${cartData.totalDiscounts}" />
	</div>
	<div class="col-xs-6 text-right charge150127">
		<spring:theme code="basket.page.totals.delivery" />
	</div>
	<div class="col-xs-5 text-right charge150127">
		<format:price priceData="${cartData.deliveryCost}" displayFreeForZero="TRUE" />
	</div>
	<div class="col-xs-6 text-right charge150127 h3">		
		<b><spring:theme code="basket.page.total" /></b>
	</div>
	<div class="col-xs-5 text-right charge150127 h3">
		<b><format:price priceData="${cartData.totalPrice}" /></b>
	</div>
</div>