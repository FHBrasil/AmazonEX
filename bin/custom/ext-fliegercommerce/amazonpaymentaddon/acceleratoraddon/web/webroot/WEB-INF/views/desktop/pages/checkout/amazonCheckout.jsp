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

		<c:url value="/checkout/amazon/placeOrder" var="placeOrderUrl" />
		<form:form action="${placeOrderUrl}" id="amazonPlaceOrderForm" commandName="amazonPlaceOrderForm">
			<input type="hidden" name="amazonOrderReferenceId"/>
			<button type="submit" class="positive right place-order">
				<spring:theme code="checkout.summary.placeOrder"/>
			</button>
		</form:form>
	</div>
	<cms:pageSlot position="Section3" var="component">
		<cms:component component="${component}" class="col-sm-4"/>
	</cms:pageSlot>
	
</template:page>