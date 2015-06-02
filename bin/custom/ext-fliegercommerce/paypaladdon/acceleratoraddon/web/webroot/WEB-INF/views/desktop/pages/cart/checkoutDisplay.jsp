<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

<cart:cartExpressCheckoutEnabled />

<div class="left">
	<a class="button" href="${continueShoppingUrl}"> <spring:theme
			text="Continue Shopping" code="cart.page.continue" />
	</a>
</div>
<div class="right cartButtonsSection">
	<span>
		<cms:component component="${feature}" />
	</span>
	<c:if test="${not empty feature}">
		<span class="orSeparator">
			<spring:theme code="cart.or" />
		</span>
	</c:if>
	<span class="center">
		<cms:component component="${feature1}" />
	</span>
	<c:if test="${not empty feature1}">
		<span class="orSeparator">
			<spring:theme code="cart.or" />
		</span>
	</c:if>
	<span>
		<button id="checkoutButtonBottom"
			class="doCheckoutBut positive right continueCheckout noMarginTop"
			type="button" data-checkout-url="${checkoutUrl}">
			<spring:theme code="checkout.checkout" />
		</button>
	</span>
</div>
<div class="clearfix"></div>

<c:if test="${showCheckoutStrategies && not empty cartData.entries}" >
	<div class="span-24">
		<div class="right">
			<input type="hidden" name="flow" id="flow"/>
			<input type="hidden" name="pci" id="pci"/>
			<select id="selectAltCheckoutFlow" class="doFlowSelectedChange">
				<option value="multistep"><spring:theme code="checkout.checkout.flow.select"/></option>
				<option value="multistep"><spring:theme code="checkout.checkout.multi"/></option>
				<option value="multistep-pci"><spring:theme code="checkout.checkout.multi.pci"/></option>
			</select>
			<select id="selectPciOption" style="margin-left: 10px; display: none;">
				<option value=""><spring:theme code="checkout.checkout.multi.pci.select"/></option>
				<c:if test="${!isOmsEnabled}">
					<option value="hop"><spring:theme code="checkout.checkout.multi.pci-hop"/></option>
				</c:if>
				<option value="sop"><spring:theme code="checkout.checkout.multi.pci-sop" text="PCI-SOP" /></option>
			</select>
		</div>
	</div>
</c:if>

