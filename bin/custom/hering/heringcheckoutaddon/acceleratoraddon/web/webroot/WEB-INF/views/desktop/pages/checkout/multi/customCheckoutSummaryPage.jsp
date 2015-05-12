<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart" %>
<%@ taglib prefix="checkout" tagdir="/WEB-INF/tags/addons/b2ccheckoutaddon/desktop/checkout" %>
<%@ taglib prefix="multi-checkout" tagdir="/WEB-INF/tags/addons/b2ccheckoutaddon/desktop/checkout/multi" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ taglib prefix="multi-checkout-hering" tagdir="/WEB-INF/tags/addons/heringcheckoutaddon/desktop/checkout/multi" %>

<spring:url value="/checkout/multi/placeOrder" var="placeOrderUrl" />
<spring:url value="/checkout/multi/termsAndConditions" var="getTermsAndConditionsUrl" />

<template:page pageTitle="${pageTitle}" hideHeaderLinks="true">

	<div id="globalMessages">
		<common:globalMessages/>
	</div>



	<multi-checkout:checkoutProgressBar steps="${checkoutSteps}" currentStep="4" stepName="confirmOrder"/>
<div class="span-14 append-1">
<div class="addresses">
	
	
		<c:if test="${not empty paymentInfo}">
	
				<div class="delivery">
					<multi-checkout-hering:summaryFlowDeliveryAddress deliveryAddress="${cartData.deliveryAddress}" />
				</div>
				
	       
			<multi-checkout-hering:summaryFlow deliveryAddress="${cartData.deliveryAddress}" deliveryMode="${deliveryMode}" paymentInfo="${paymentInfo}" requestSecurityCode="${requestSecurityCode}"  cartData="${cartData}"/>
		</c:if>
		
		<c:if test="${not empty customPaymentInfo}">
				
				<div class="delivery">
					<multi-checkout-hering:summaryFlowDeliveryAddress deliveryAddress="${cartData.deliveryAddress}" />
				</div>
				<div class="billing">
					<multi-checkout-hering:boletoSummaryFlow deliveryAddress="${cartData.deliveryAddress}" deliveryMode="${deliveryMode}" customPaymentInfo="${customPaymentInfo}" cartData="${cartData}"/>
				</div>		
				
		</c:if>
	   <c:if test="${not empty voucherPaymentInfo and  empty customPaymentInfo and empty paymentInfo}">

				<div class="delivery">
					<multi-checkout-hering:summaryFlowDeliveryAddress
						deliveryAddress="${cartData.deliveryAddress}" />
				</div>
				<div class="billing">
					<multi-checkout-hering:voucherSummaryFlow
						deliveryAddress="${cartData.deliveryAddress}"
						deliveryMode="${deliveryMode}"
						voucherPaymentInfo="${voucherPaymentInfo}" cartData="${cartData}" />
				</div>

		</c:if>

			<cart:cartPromotions cartData="${cartData}"/>

		<form:form action="${placeOrderUrl}" id="placeOrderForm1" commandName="placeOrderForm">
			<c:if test="${requestSecurityCode}">
				<form:input type="hidden" class="securityCodeClass" path="securityCode"/>
				<button type="submit" class="positive right pad_right place-order placeOrderWithSecurityCode">
					<spring:theme code="checkout.summary.placeOrder"/>
				</button>
			</c:if>
			
			<c:if test="${not requestSecurityCode}">
				<button type="submit" class="concluir-pedido positive right change_address_button show_processing_message">
					<spring:theme code="checkout.summary.placeOrder"/>
				</button>
			</c:if>
			<input id="Terms1" name="termsCheck" type="hidden" value="true">
			
		</form:form>
</div>
</div>
		<multi-checkout-hering:checkoutOrderDetails  cartData="${cartData}" showShipDeliveryEntries="true" showPickupDeliveryEntries="true" showTax="true"/>

	<cms:pageSlot position="SideContent" var="feature" element="div" class="span-24 side-content-slot cms_disp-img_slot">
		<cms:component component="${feature}"/>
	</cms:pageSlot>

</template:page>
