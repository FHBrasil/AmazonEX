<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="checkout" tagdir="/WEB-INF/tags/addons/b2ccheckoutaddon/desktop/checkout" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="multi-checkout" 			tagdir="/WEB-INF/tags/addons/b2ccheckoutaddon/desktop/checkout/multi" %>
<%@ taglib prefix="multi-checkout-hering"   tagdir="/WEB-INF/tags/addons/heringcheckoutaddon/desktop/checkout/multi" %>

<c:url value="/checkout/multi/select-delivery-method" var="continueSelectDeliveryMethodUrl"/>
<c:url value="/checkout/multi/add-address" var="addDeliveryAddressUrl"/>
<template:page pageTitle="${pageTitle}" hideHeaderLinks="true">

	<div id="globalMessages">
		<common:globalMessages/>
	</div>
	


	<multi-checkout:checkoutProgressBar steps="${checkoutSteps}" currentStep="2" stepName="deliveryMethod"/>
	

	<div class="span-14 append-1">
		<div id="checkoutContentPanel" class="clearfix">
			<c:if test="${not empty cartData.deliveryAddress}">
				<c:set var="deliveryAddress" value="${cartData.deliveryAddress}"/>
				<h1 class="headline_entrega"><spring:theme code="checkout.entrega" text="deliver"/></h1>
				<div class="endereco_entrega">
					<span><spring:theme code="checkout.entrega"/></span>
					<br/>
					<ul>
						<li><spring:theme code="text.fliegercommerce.texto18"/>:&nbsp; ${fn:escapeXml(deliveryAddress.firstName)}&nbsp; ${fn:escapeXml(deliveryAddress.lastName)}</li>
						<li><c:if test="${not empty deliveryAddress.receiver}"><spring:theme code="text.fliegercommerce.texto19"/>:&nbsp; ${fn:escapeXml(deliveryAddress.receiver)}</c:if></li>
						<li><spring:theme code="text.fliegercommerce.texto20"/>:&nbsp; (${fn:escapeXml(deliveryAddress.dddPhone)})${fn:escapeXml(deliveryAddress.phone)}</li>
						<li><c:if test="${not empty deliveryAddress.celPhone}"><spring:theme code="text.fliegercommerce.texto21"/>:&nbsp; (${fn:escapeXml(deliveryAddress.dddCelPhone)})${fn:escapeXml(deliveryAddress.celPhone)}</c:if></li>
						<li><spring:theme code="text.fliegercommerce.texto22"/>:&nbsp; ${fn:escapeXml(deliveryAddress.line1)}&nbsp; ${fn:escapeXml(deliveryAddress.number)}</li>
						<li><spring:theme code="text.fliegercommerce.texto91"/>:&nbsp; ${fn:escapeXml(deliveryAddress.complement)}</li>
						<li><c:if test="${not empty deliveryAddress.reference}"><spring:theme code="text.fliegercommerce.texto23"/>:&nbsp; ${fn:escapeXml(deliveryAddress.reference)}</c:if></li>
						<li><spring:theme code="text.fliegercommerce.texto92"/>:&nbsp; ${fn:escapeXml(deliveryAddress.postalCode)}</li>
						<li><spring:theme code="text.fliegercommerce.texto93"/>:&nbsp; ${fn:escapeXml(deliveryAddress.district)}</li>
						<li><spring:theme code="text.fliegercommerce.texto94"/>:&nbsp; ${fn:escapeXml(deliveryAddress.town)}<c:if test="${not empty deliveryAddress.region.name}">-${fn:escapeXml(deliveryAddress.region.name)}</c:if></li>
						<li><spring:theme code="text.fliegercommerce.texto95"/>:&nbsp; ${fn:escapeXml(deliveryAddress.country.name)}</li>						
					</ul>
					
					<form:form action="${request.contextPath}/checkout/multi/edit-delivery-address" method="GET">
						<input type="hidden" name="editAddressCode" value="${deliveryAddress.id}"/>
						<button type="submit" class="positive left bt-editar"><spring:theme code="text.fliegercommerce.texto90"/></button>
					</form:form>
				</div>
			</c:if>
			<h3 class="headline3_entrega"><spring:theme code="text.fliegercommerce.texto89"/></h3>
			
			<form:form id="selectDeliveryMethodForm" action="${continueSelectDeliveryMethodUrl}" method="get">
				<multi-checkout:deliveryMethodSelector deliveryMethods="${deliveryMethods}" selectedDeliveryMethodId="${cartData.deliveryMode.code}"/>
				
				<c:if test="${not empty cartData.deliveryMode.code}">
					<div class="form-actions">
						<c:if test="false">
							<div id="addressform_button_cancel" class="bt-cancelar">
								<a class="button" href="${addDeliveryAddressUrl}"><spring:theme code="checkout.multi.cancel"/></a>
							</div>
						</c:if>
						<button id="chooseDeliveryMethod_continue_button" class="positive right change_address_button show_processing_message">
							<spring:theme code="checkout.multi.deliveryMethod.continue"/>
						</button>
					</div>
				</c:if>
			</form:form>
			
		</div>
	</div>
	
	<multi-checkout-hering:checkoutOrderDetails cartData="${cartData}" showShipDeliveryEntries="true" showPickupDeliveryEntries="false" showTax="false"/>
	
	
	
	<c:if test="false">
		<cms:pageSlot position="SideContent" var="feature" element="div" class="span-24 side-content-slot cms_disp-img_slot">
			<cms:component component="${feature}"/>
		</cms:pageSlot>
	</c:if>

</template:page>
