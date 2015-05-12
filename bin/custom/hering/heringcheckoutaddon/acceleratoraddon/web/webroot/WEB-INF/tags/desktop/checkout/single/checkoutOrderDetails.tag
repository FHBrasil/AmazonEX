<%@ attribute name="cartData" required="true"
	type="de.hybris.platform.commercefacades.order.data.CartData"%>
<%@ attribute name="showShipDeliveryEntries" required="false"
	type="java.lang.Boolean"%>
<%@ attribute name="showPickupDeliveryEntries" required="false"
	type="java.lang.Boolean"%>
<%@ attribute name="showTax" required="false" type="java.lang.Boolean"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="checkout"
	tagdir="/WEB-INF/tags/addons/b2ccheckoutaddon/desktop/checkout"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="single-checkout-hering"
	tagdir="/WEB-INF/tags/addons/heringcheckoutaddon/desktop/checkout/single"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
 
<c:if test="${themeName == 'black'}">
	<c:if test="${not empty cartData}">
		<div id="checkoutOrderDetails" class="span-10 last" style="border: 1px solid #000;">
			<single-checkout-hering:deliveryCartItems cartData="${cartData}" />
			<c:forEach items="${cartData.pickupOrderGroups}" var="groupData"
					varStatus="status">
				<single-checkout-hering:pickupCartItems cartData="${cartData}"
						groupData="${groupData}" index="${status.index}"
						showHead="true" />
			</c:forEach>
			<cart:cartTotalsSide cartData="${cartData}" showTaxEstimate="false"
					showTax="${showTax}" />
			<cart:cartPromotions cartData="${cartData}" />
		</div>
	</c:if>
</c:if>

<c:if test="${themeName == 'hering' || themeName == 'dzarm' || themeName == 'foryou'}">
	<header>
		<h2><spring:theme code="checkout.single.details.title"/></h2>
	</header>
	<div class="section-container">
		<section id="general-info">
			<dl class="price-info">
				<dt class="items-title">
					<spring:theme code="checkout.single.details.showProducts"/>
				</dt>
				<dd class="items-content">
					<section id="products-list">
						<single-checkout-hering:deliveryCartItems cartData="${cartData}" />
					</section>
				</dd>
				<div id="checkoutSubTotals">
					<dt><spring:theme code="checkout.single.details.subtotal"/></dt>
					<dd class="subtotal">
						<strong><format:price priceData="${cartData.subTotal}" /></strong>
					</dd>
					<dt class="freight" ${not empty cartData.deliveryCost ? '' : 'style=\"display:none;\"'}>
						<spring:theme code="basket.page.totals.delivery"/>
					</dt>
					<dd>
						<c:if test="${not empty cartData.deliveryCost}">
							<c:choose>
	                            <c:when test="${isVoucherFreeShipping}">
                                	<spring:theme code="order.free" text="FREE" />
                            	</c:when>
                            	<c:otherwise>
                                	<format:price priceData="${cartData.deliveryCost}"
                                    	    displayFreeForZero="TRUE" />
                            	</c:otherwise>
                        	</c:choose>
					   		<small>${cartData.deliveryMode.description}</small>
						</c:if>
					</dd>
					<c:if test="${cartData.totalDiscounts.value > 0}">
						<dt class="discount">
							<spring:theme code="basket.page.totals.savings" />
						</dt>
						<dd>
							<ycommerce:testId code="Order_Totals_Savings">
    	                        <format:price priceData="${cartData.totalDiscounts}" />
							</ycommerce:testId>
						</dd>
					</c:if>
				</div>
			</dl>
			<div id="checkoutPaymentTotals">
				<dl class="payment-info">
					<dt class="method"><spring:theme code="checkout.single.details.payment"/></dt>
					<dd class="method"></dd>
					<dt class="total"><spring:theme code="checkout.single.details.orderTotal"/></dt>
					<dd class="total">
						<strong><format:price priceData="${cartData.totalPrice}" /></strong>
					</dd>
				</dl>
			</div>
			<button type="button" class="btn btn-checkout confirm-purchase">
				<spring:theme code="checkout.single.button.purchase"/>
			</button>
			
			<div class="btn waiting-order-confirm" style="display:none;">
				<spring:theme code="checkout.single.button.purchase.pleaseWait"/>
			</div>
		</section>
		<section id="docs">
			<h3><spring:theme code="checkout.single.summary.message.title"/></h3>
			<p><spring:theme code="checkout.single.summary.message.1"/></p>
			<ol>
				<li><spring:theme code="checkout.single.summary.message.2"/></li>
				<li><spring:theme code="checkout.single.summary.message.3"/></li>
				<li><spring:theme code="checkout.single.summary.message.4"/></li>
				<li><spring:theme code="checkout.single.summary.message.5"/></li>
				<li><spring:theme code="checkout.single.summary.message.6"/></li>
			</ol>
			<h3><spring:theme code="checkout.single.summary.company.procedures.title"/></h3>
			<ol>
				<li><spring:theme code="checkout.single.summary.company.procedure.1"/></li>
				<li><spring:theme code="checkout.single.summary.company.procedure.2"/></li>
				<li><spring:theme code="checkout.single.summary.company.procedure.3"/></li>
				<li><spring:theme code="checkout.single.summary.company.procedure.4"/></li>
				<li><spring:theme code="checkout.single.summary.company.procedure.5"/></li>
			</ol>
		</section>
	</div>
</c:if>