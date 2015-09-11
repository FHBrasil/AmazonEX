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
<%@ taglib prefix="single-checkout-hering" tagdir="/WEB-INF/tags/addons/heringcheckoutaddon/desktop/checkout/single"%>
<%@ taglib prefix="single-checkout-amazon" tagdir="/WEB-INF/tags/addons/amazonpaymentaddon/desktop/checkout/single"%>
<%@ taglib prefix="amazon-controls" tagdir="/WEB-INF/tags/addons/amazonpaymentaddon/desktop/checkout/controls" %>

<spring:url value="/checkout/amazon/placeOrder" var="placeOrderUrl" />
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
        <cms:pageSlot position="Section1B" var="component">
                <cms:component component="${component}"/>
        </cms:pageSlot>
        <cms:pageSlot position="Section2A" var="component" element="div" class="col-sm-4">
        		<h2>
					<spring:theme code="checkout.single.address.title" />
				</h2>
                <cms:component component="${component}"/>
        </cms:pageSlot>
        <cms:pageSlot position="Section2B" var="component" element="div" class="col-sm-4">
	        	<h2>
					<spring:theme code="checkout.single.payment.title" />
				</h2>
                <cms:component component="${component}"/>
        </cms:pageSlot>
        <form:form action="${request.contextPath}/checkout/amazon/placeOrder" id="amazonPlaceOrderForm" commandName="amazonPlaceOrderForm">
        	 <input type="hidden" name="amazonOrderReferenceId"/>
        </form:form>
		<div class="col-sm-4 cart150529">
			<single-checkout-amazon:checkoutOrderDetailsAmazon cartData="${cartData}"
		   	showShipDeliveryEntries="true" showTax="false" showPickupDeliveryEntries="true" />            
		</div>
		<c:if test="${sendGuestInformation}">
                <form:form id="amazonGuestInformation" action="/login/checkout/amazon/guest" method="POST">
                        <input type="hidden" name="amazonOrderReferenceId"/>
                        <input type="hidden" name="amazonGuestId"/>
                        <input type="hidden" name="amazonGuestName"/>
                        <input type="hidden" name="amazonGuestEmail"/>
                </form:form>
        </c:if>
        <%-- 
        <div class="col-sm-4">
                <div id="addressBookWidgetDiv" class="totalOrder">
                        <amazon-controls:totalOrder cartData="${cartData}"/>
                </div>
        </div>
        
		<div class="col-sm-12">
	        <div class="col-sm-12 amazonLookalikeDiv">
	                <amazon-controls:deliveryItemCheckoutAmazon cartData="${cartData}"/>
	                <c:forEach items="${cartData.pickupOrderGroups}" var="groupData" varStatus="status">
	                        <multi-checkout:pickupCartItems cartData="${cartData}" groupData="${groupData}" index="${status.index}" showHead="true" />
	                </c:forEach>
	        </div>
        </div>
        <div class="col-sm-12">
        <div class="col-sm-12 amazonLookalikeDiv">
                <c:url value="/checkout/amazon/placeOrder" var="placeOrderUrl" />
                <form:form action="${placeOrderUrl}" id="amazonPlaceOrderForm" commandName="amazonPlaceOrderForm">
                        <input type="hidden" name="amazonOrderReferenceId"/>
                        <c:if test="${sandboxMode}">
                                <amazon-controls:sandboxModeControls showAuthorizeError="true" />
                        </c:if>
                        <c:if test="${chargeOnOrder}">
                                <amazon-controls:confirmChargeOnOrderControls />
                        </c:if>
                        <button id="amazonPlaceOrder" type="submit" class="positive right place-order" disabled>
                                <spring:theme code="checkout.summary.placeOrder"/>
                        </button>
                </form:form>
        </div>
        </div>
        <cms:pageSlot position="Section3" var="component">
                <cms:component component="${component}" class="col-sm-12"/>
        </cms:pageSlot>
         --%>
</template:page>