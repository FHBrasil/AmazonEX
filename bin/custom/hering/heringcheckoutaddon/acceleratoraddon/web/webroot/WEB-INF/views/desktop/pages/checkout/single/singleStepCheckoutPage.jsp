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

<%-- DZARM STORE --%>
<c:if test="${themeName == 'black'}">
	<spring:url value="/checkout/single/placeOrder" var="placeOrderUrl" />
	<template:page pageTitle="${pageTitle}" hideHeaderLinks="true">
		<div id="globalMessages">
			<common:globalMessages />
		</div>
		<div class="clearfix">
	        <div id="leftDiv" style="float: left;">
	    		<div id="heringaddEditDeliveryAddress"
	    				class="span-6 append-1 clearfix is_disabled">
	    			<single-checkout-hering:addEditDeliveryAddress />
	            </div>
	    		<div id="heringDeliveryMethodSelector"
	    				style="">
	    			<single-checkout-hering:deliveryMethodSelector
	    				deliveryMethods="${deliveryMethods}"
	    				selectedDeliveryMethodId="${cartData.deliveryMode.code}"/>
	    		</div>
			</div>
	        <div id="centerDiv" style="float: left;">
	    		<div id="heringCustomAddPaymentMethod"
	                    class="span-6 append-1 clearfix">
	    			<single-checkout-hering:customAddPaymentMethod />
	    		</div>
	        </div>
	        <div id="rightDiv" style="float: left;">
	    		<div id="heringCheckoutOrderDetails" class="span-6 append-1 clearfix"
	    				style="clear: right; float: right;">
	    			<single-checkout-hering:checkoutOrderDetails
	                        cartData="${cartData}" showShipDeliveryEntries="true"
	                        showTax="false" showPickupDeliveryEntries="true"/>
	    		</div>
	            <div id="singleStepCheckoutConfirmButton" style="float: right;">
	                <button type="button" class="confirm-purchase">
	                    Finalizar Compra
	                </button>
	            </div>
	        </div>
	    </div>
	</template:page>
</c:if>

<%-- DZARM STORE --%>
<c:if test="${themeName == 'hering' || themeName == 'dzarm' || themeName == 'foryou'}">
	<spring:url value="/checkout/single/placeOrder" var="placeOrderUrl" />
	<template:page pageTitle="${pageTitle}" hideHeaderLinks="true">
		<div id="globalMessages">
			<common:globalMessages />
		</div>
		<div class="columns-wrapper singleStepCheckout">
	    
	    	<%-- LEFT SECTION --%>
	    	<section id="co-left" class="shipping-info checkout-column heringaddEditDeliveryAddress">
	    		<single-checkout-hering:addressFormSelector regions="${regions}" country="${country}"/>
	   			<single-checkout-hering:addEditBillingAddress regions="${regions}" country="${country}" />
	   			<single-checkout-hering:deliveryMethodSelector deliveryMethods="${deliveryMethods}"	selectedDeliveryMethodId="${cartData.deliveryMode.code}"/>
			</section>

	    
	    	${themeName == 'hering' ? "<div style='display:inline'>" : ""}
	    		
	    		<%-- CENTER SECTION --%>
                <form:form method="post" commandName="paymentDetailsForm"
                        class="create_update_payment_form display-inline"
                        action="${request.contextPath}/checkout/single/placeOrder">
    	    		<section id="co-middle" class="payment-methods checkout-column">
    			        <div id="centerDiv" style="float: left;">
    		    			   <single-checkout-hering:customAddPaymentMethod cartData="${cartData}" />
    			        </div>
    		        </section>
    		    
    		    	<%-- RIGHT SECTION --%>
    		    	<section id="co-right" class="invoice-review checkout-column">
    			        <div id="rightDiv" style="float: left;">
    			    		<div id="heringCheckoutOrderDetails" class="span-6 append-1 clearfix"
    			    				style="clear: right; float: right;">
    			    			<single-checkout-hering:checkoutOrderDetails
    			                        cartData="${cartData}" showShipDeliveryEntries="true"
    			                        showTax="false" showPickupDeliveryEntries="true"/>
    			    		</div>
    			        </div>
    		        </section>
                </form:form>
		        
	        </div>
	        
	   ${themeName == 'hering' ? "</div>" : ""}
	</template:page>
</c:if>