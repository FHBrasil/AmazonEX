<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart" %>
<%@ taglib prefix="checkout" tagdir="/WEB-INF/tags/desktop/checkout" %>
<%@ taglib prefix="single-checkout" tagdir="/WEB-INF/tags/desktop/checkout/single" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="/cms2lib/cmstags/cmstags.tld" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<spring:url value="/checkout/single/placeOrder" var="placeOrderUrl" />

<template:page pageTitle="${pageTitle}">

<script type="text/javascript">
/*<![CDATA[*/
	function placeOrderWithSecurityCode()
	{
		var securityCode = $("#SecurityCode").val();
		$(".securityCodeClass").val(securityCode);
		document.getElementById("placeOrderForm1").submit();
	}
	
	$(document).ready(function(){
		$("#Terms1").click(function() {
			var terms1enable = $('#Terms1').attr("checked");
			if(terms1enable == undefined || terms1enable == 'false'){
				$('#Terms2').attr("checked",false);
			} else {
				$('#Terms2').attr("checked",true);
			}
		});
		
		$("#Terms2").click(function() {
			var terms2enable = $('#Terms2').attr("checked");
			if(terms2enable == undefined || terms2enable == 'false')
			{				
				$('#Terms1').attr("checked",false);
			} else {
				$('#Terms1').attr("checked",true);
			}
		});
	});
	
	$(document).ready(function(){
		$("#KlarnaConsent1").click(function() {
			var klarnaConsent1enable = $('#KlarnaConsent1').attr("checked");
			if(klarnaConsent1enable == undefined || klarnaConsent1enable == 'false'){
				$('#KlarnaConsent2').attr("checked",false);
			} else {
				$('#KlarnaConsent2').attr("checked",true);
			}
		});
		
		$("#KlarnaConsent2").click(function() {
			var klarnaConsent2enable = $('#KlarnaConsent2').attr("checked");
			if(klarnaConsent2enable == undefined || klarnaConsent2enable == 'false')
			{				
				$('#KlarnaConsent1').attr("checked",false);
			} else {
				$('#KlarnaConsent1').attr("checked",true);
			}
		});
	});

	$(document).ready(function() {
		updatePlaceOrderButton();
	});

	function updatePlaceOrderButton()
	{
		var deliveryAddress = $("#checkout_summary_deliveryaddress_div").hasClass("complete");
		var deliveryMode = $("#checkout_summary_deliverymode_div").hasClass("complete");
		var paymentDetails = $("#checkout_summary_payment_div").hasClass("complete");

		if (deliveryAddress && deliveryMode && paymentDetails)
		{
			$(".place-order").removeAttr('disabled');
		}
		else
		{
			$(".place-order").attr('disabled', true);
		}
	}
/*]]>*/
</script>
	<div id="breadcrumb" class="breadcrumb"></div>

	<div id="globalMessages">
		<common:globalMessages/>
		
		<c:if test="${not empty reservationKlarnaException}">
			<div class="span-24">
				<div class="information_message negative">
					<span class="single"></span>
					<p><spring:theme code="${reservationKlarnaException}"/></p>
				</div>
			</div>
		</c:if>
	</div>

	<div class="span-4 side-content-slot cms_banner_slot">
		<cms:slot var="feature" contentSlot="${slots.SideContent}">
			<cms:component component="${feature}"/>
		</cms:slot>
	</div>

	<div class="span-20 last">

		<div class="span-20 last">
			<single-checkout:summaryFlow />
		</div>
		
		<div class="span-20 last place-order-top">
			<form:form action="${placeOrderUrl}" id="placeOrderForm1" commandName="placeOrderForm">
				<form:input type="hidden" name="securityCode" class="securityCodeClass" path="securityCode"/>
				<button type="submit" class="positive right pad_right place-order" onclick="placeOrderWithSecurityCode();return false;">
					<spring:theme code="checkout.summary.placeOrder"/>
				</button>
				<dl class="terms right">
					<dt class="left">
						<form:checkbox id="Terms1" name="Terms1" path="termsCheck" />
						<label for="Terms1"><spring:theme code="checkout.summary.placeOrder.readTermsAndConditions"/></label>
					</dt>
					<c:if test="${checkKlarnaConsent}">					
					<dt class="left">
						<form:checkbox id="KlarnaConsent1" name="KlarnaConsent1" path="klarnaConsent" />
						<c:choose>
							<c:when test="${currentKlarnaCountryIsoCode eq 'DE'}">
								<label for="KlarnaConsent1"><spring:theme code="checkout.summary.placeOrder.readKlarnaConsent.de"/></label>
							</c:when>
							<c:when test="${currentKlarnaCountryIsoCode eq 'AT'}">
								<label for="KlarnaConsent1"><spring:theme code="checkout.summary.placeOrder.readKlarnaConsent.at"/></label>
							</c:when>
							<c:otherwise>
								<label for="KlarnaConsent1"><spring:theme code="checkout.summary.placeOrder.readKlarnaConsent"/></label>
							</c:otherwise>
						</c:choose>
					</dt>
					</c:if>
					<dd></dd>
				</dl>
			</form:form>
		</div>

		<div class="span-20 last">
			<checkout:summaryCartItems cartData="${cartData}"/>
		</div>

		<div class="span-12">
			<cart:cartPromotions cartData="${cartData}"/>
		</div>

		<div class="span-8 right last place-order-cart-total">
			<cart:ajaxCartTotals/>
		</div>

		<div class="span-20 place-order-bottom">
			<form:form action="${placeOrderUrl}" id="placeOrderForm2" commandName="placeOrderForm">
				<form:input type="hidden" name="securityCode" class="securityCodeClass" path="securityCode"/>
				<button type="submit" class="positive right pad_right place-order"  onclick="placeOrderWithSecurityCode();return false;">
					<spring:theme code="checkout.summary.placeOrder"/>
				</button>
				<dl class="terms right">
					<dt class="left">
						<form:checkbox id="Terms2" name="Terms2" path="termsCheck" />
						<label for="Terms2"><spring:theme code="checkout.summary.placeOrder.readTermsAndConditions"/></label>
					</dt>
					<c:if test="${checkKlarnaConsent}">
					<dt class="left">
						<form:checkbox id="KlarnaConsent1" name="KlarnaConsent1" path="klarnaConsent" />
						<c:choose>
							<c:when test="${currentKlarnaCountryIsoCode eq 'DE'}">
								<label for="KlarnaConsent1"><spring:theme code="checkout.summary.placeOrder.readKlarnaConsent.de"/></label>
							</c:when>
							<c:when test="${currentKlarnaCountryIsoCode eq 'AT'}">
								<label for="KlarnaConsent1"><spring:theme code="checkout.summary.placeOrder.readKlarnaConsent.at"/></label>
							</c:when>
							<c:otherwise>
								<label for="KlarnaConsent1"><spring:theme code="checkout.summary.placeOrder.readKlarnaConsent"/></label>
							</c:otherwise>
						</c:choose>
					</dt>
					</c:if>
					<dd></dd>
				</dl>
			</form:form>
		</div>
	</div>
</template:page>