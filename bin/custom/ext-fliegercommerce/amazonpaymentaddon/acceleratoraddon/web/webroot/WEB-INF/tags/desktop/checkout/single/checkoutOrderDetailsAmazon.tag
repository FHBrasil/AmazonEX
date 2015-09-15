<%@ attribute name="cartData" required="true"
    type="de.hybris.platform.commercefacades.order.data.CartData"%>
<%@ attribute name="showShipDeliveryEntries" required="false" type="java.lang.Boolean"%>
<%@ attribute name="showPickupDeliveryEntries" required="false" type="java.lang.Boolean"%>
<%@ attribute name="showTax" required="false" type="java.lang.Boolean"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="checkout" tagdir="/WEB-INF/tags/addons/b2ccheckoutaddon/desktop/checkout"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="single-checkout-hering"
    tagdir="/WEB-INF/tags/addons/heringcheckoutaddon/desktop/checkout/single"%>
<%@ taglib prefix="single-checkout-amazon"
    tagdir="/WEB-INF/tags/addons/amazonpaymentaddon/desktop/checkout/single"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>

<h2>
	<spring:theme code="checkout.single.details.title" />
</h2>
<p class="underlinelinks150212">
		<spring:theme code="checkout.single.pleaseReferOur"/>&nbsp;<a href="#"><spring:theme code="checkout.single.termsAndConditions"/></a>
		<spring:theme code="checkout.single.youHaveA"/>&nbsp;<a href="#"><spring:theme code="checkout.single.daysReturnPolicy"/></a>
		<spring:theme code="checkout.single.hereYouWillFind"/>&nbsp;<a href="#"><spring:theme code="checkout.single.privacyPolicy"/></a>
</p>
<single-checkout-amazon:deliveryCartItemsAmazon cartData="${cartData}" />

<div class="row">
	<div class="col-xs-7 text-right charge150127">
		<spring:theme code="checkout.single.details.subtotal" /> 
	</div>
	<div class="col-xs-5 text-right charge150127">
		<format:price priceData="${cartData.subTotal}" />
	</div>
	<div class="col-xs-7 text-right charge150127">
		<spring:theme code="basket.page.totals.savings" />
	</div>
	<div class="col-xs-5 text-right charge150127">
		<ycommerce:testId code="Order_Totals_Savings">
	      	<format:price priceData="${cartData.totalDiscounts}" />
	    </ycommerce:testId>
	</div>
	<c:if test="${not empty cartData.deliveryCost}">
		<div class="col-xs-7 text-right charge150127">
			<spring:theme code="basket.page.totals.delivery" />
		</div>
		<div class="col-xs-5 text-right charge150127">
			<c:choose>
		      	<c:when test="${isVoucherFreeShipping}">
		           	<spring:theme code="order.free" text="FREE" />
		        </c:when>
		        <c:otherwise>
		           	<format:price priceData="${cartData.deliveryCost}" displayFreeForZero="TRUE" />
		        </c:otherwise>
		    </c:choose>
		</div>
	</c:if>
	<div class="col-xs-7 text-right charge150127 h3">
		<b><spring:theme code="checkout.single.details.orderTotal" /></b>
	</div>
	<div class="col-xs-5 text-right charge150127 h3">
		<b><format:price priceData="${cartData.totalPrice}" /></b>	
	</div>
</div>
<div class="text-right" style="margin-bottom:30px;">
	<button type="button" class="btn btn-primary btn-lg btn-checkout confirm-purchaseAmazon" disabled style="background-color: #54AB56;border-color: #54AB56;">
	  	<spring:theme code="checkout.single.button.purchase" />
	</button>
	<div class="btn waiting-order-confirm" style="display: none;">
	  	<spring:theme code="checkout.single.button.purchase.pleaseWait" />
	</div>
</div>

<%-- TODO parametrizar condicao --%>
<c:if test="${false}">
	<section id="docs">
		<h3>
	    	<spring:theme code="checkout.single.summary.message.title" />
		</h3>
	    <p>
	    	<spring:theme code="checkout.single.summary.message.1" />
	    </p>
	    <ol>
	    	<li><spring:theme code="checkout.single.summary.message.2" /></li>
	        <li><spring:theme code="checkout.single.summary.message.3" /></li>
	        <li><spring:theme code="checkout.single.summary.message.4" /></li>
	        <li><spring:theme code="checkout.single.summary.message.5" /></li>
	        <li><spring:theme code="checkout.single.summary.message.6" /></li>
	    </ol>
	    <h3>
	    	<spring:theme code="checkout.single.summary.company.procedures.title" />
	    </h3>
	    <ol>
	    	<li><spring:theme code="checkout.single.summary.company.procedure.1" /></li>
	        <li><spring:theme code="checkout.single.summary.company.procedure.2" /></li>
	        <li><spring:theme code="checkout.single.summary.company.procedure.3" /></li>
	        <li><spring:theme code="checkout.single.summary.company.procedure.4" /></li>
	        <li><spring:theme code="checkout.single.summary.company.procedure.5" /></li>
	    </ol>
	</section>
</c:if>