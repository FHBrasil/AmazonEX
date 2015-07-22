<%@ attribute name="cartData" required="true" type="de.hybris.platform.commercefacades.order.data.CartData" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="checkout" tagdir="/WEB-INF/tags/desktop/checkout" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart" %>

<script type="text/javascript">
	$(document).ready(function () {
		$("#checkout-cart-details").hide();
		
		var showText = '<spring:message code="checkout.orderDetails.show"/>';
		var hideText = '<spring:message code="checkout.orderDetails.hide"/>';
		
		$("#checkout-cart-details-btn").click(function(e) {
			e.preventDefault();
			$("#checkout-cart-details").toggle();
			if($("#checkout-cart-details").is(":visible")) {
				$("#checkout-cart-details-btn").html(hideText);
			} else {
				$("#checkout-cart-details-btn").html(showText);
			}
		});
	});
	
</script>

<a href="#" id="checkout-cart-details-btn"><spring:message code="checkout.orderDetails.show"/></a>
<div id="checkout-cart-details">
	<div class="span-20 last">
		<checkout:summaryCartItems cartData="${cartData}"/>
	</div>
	<div class="span-12">
		<cart:cartPromotions cartData="${cartData}"/>
	</div>
	<div class="span-8 last right">
		<cart:cartTotals cartData="${cartData}"/>
	</div>
</div>