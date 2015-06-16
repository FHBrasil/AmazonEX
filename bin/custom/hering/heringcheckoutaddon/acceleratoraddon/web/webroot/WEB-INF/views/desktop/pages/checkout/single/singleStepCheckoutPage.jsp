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
<%@ taglib prefix="single-checkout-hering"
    tagdir="/WEB-INF/tags/addons/heringcheckoutaddon/desktop/checkout/single"%>
    
<spring:url value="/checkout/single/placeOrder" var="placeOrderUrl" />
<template:page pageTitle="${pageTitle}" hideHeaderLinks="true">
    <div id="globalMessages">
        <common:globalMessages />
    </div>
    <div class="col-sm-4">
    	<single-checkout-hering:addressFormSelector regions="${regions}" country="${country}" />
        <single-checkout-hering:addEditBillingAddress regions="${regions}" country="${country}" />
        <single-checkout-hering:deliveryMethodSelector deliveryMethods="${deliveryMethods}"
        	selectedDeliveryMethodId="${cartData.deliveryMode.code}" />
    </div>
     <div class="col-sm-4 payments150526">
   	 	<form:form method="post" commandName="paymentDetailsForm" class="create_update_payment_form display-inline" 
   	 		action="${request.contextPath}/checkout/single/placeOrder">
            	<single-checkout-hering:customAddPaymentMethod cartData="${cartData}" />
        </form:form>
     </div>
     <div class="col-sm-4 cart150529">
     	<single-checkout-hering:checkoutOrderDetails cartData="${cartData}"
        	showShipDeliveryEntries="true" showTax="false"
           	showPickupDeliveryEntries="true" />            
     </div>
</template:page>
