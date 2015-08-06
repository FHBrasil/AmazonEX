<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb"%>
<%@ taglib prefix="multi-checkout" tagdir="/WEB-INF/tags/addons/b2ccheckoutaddon/desktop/checkout/multi" %>

<template:page pageTitle="${pageTitle}" hideHeaderLinks="true">
	<div id="globalMessages">
		<common:globalMessages />
	</div>
	<cms:pageSlot position="BottomHeaderSlot" var="component">
		<cms:component component="${component}" />
	</cms:pageSlot>
	<cms:pageSlot position="Section1" var="component">
		<cms:component component="${component}"/>
	</cms:pageSlot>
	<cms:pageSlot position="Section2A" var="component" element="div" class="col-sm-4">
		<cms:component component="${component}"/>
	</cms:pageSlot>
	<cms:pageSlot position="Section2B" var="component" element="div" class="col-sm-4">
		<cms:component component="${component}"/>
	</cms:pageSlot>
	<div class="col-sm-4">
		<multi-checkout:deliveryMethodSelector
			deliveryMethods="${deliveryMethods}"
			selectedDeliveryMethodId="${cartData.deliveryMode.code}" />

		<c:url value="/checkout/multi/placeOrder" var="placeOrderUrl" />
		<form:form action="${placeOrderUrl}" id="amazonPlaceOrderForm" commandName="amazonPlaceOrderForm">
			<c:if test="${requestSecurityCode}">
				<form:input type="hidden" class="securityCodeClass" path="securityCode"/>
				<button type="submit" class="positive right pad_right place-order placeOrderWithSecurityCode">
					<spring:theme code="checkout.summary.placeOrder"/>
				</button>
			</c:if>
			
			<c:if test="${not requestSecurityCode}">
				<button type="submit" class="positive right place-order">
					<spring:theme code="checkout.summary.placeOrder"/>
				</button>
			</c:if>
			<div class="terms">
				<form:checkbox id="Terms1" path="termsCheck" />
				<label for="Terms1"><spring:theme code="checkout.summary.placeOrder.readTermsAndConditions" arguments="${getTermsAndConditionsUrl}" /></label>
			</div>
		</form:form>
	</div>
	<cms:pageSlot position="Section3" var="component">
		<cms:component component="${component}" class="col-sm-4"/>
	</cms:pageSlot>
	
</template:page>