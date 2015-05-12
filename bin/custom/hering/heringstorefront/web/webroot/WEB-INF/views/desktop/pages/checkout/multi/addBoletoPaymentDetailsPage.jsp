<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="multi-checkout" tagdir="/WEB-INF/tags/desktop/checkout/multi" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:url value="/checkout/multi/add-boleto-info" var="choosePaymentMethodUrl"/>
<template:page pageTitle="${pageTitle}" hideHeaderLinks="true">
	<div id="breadcrumb" class="breadcrumb">
		<breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}"/>
	</div>
	<div id="globalMessages">
		<common:globalMessages/>
	</div>

	<multi-checkout:checkoutProgressBar steps="${checkoutSteps}" currentStep="3" stepName="paymentMethod"/>
	
	<div class="span-20 last multicheckout">
		<div class="item_container_holder">
			<div class="title_holder">
				<h2>
					<spring:theme code="checkout.multi.paymentMethod.addPaymentDetails.header" text="Payment Details"/></h2>
			</div>
			<div class="item_container">
				<div class="payment_details_left_col">
						<form:form method="post" commandName="mundipaggBoletoDetailsForm" class="create_update_payment_form">
							

							<div class="payment_details_left_col-card">
								<h1><spring:theme code="checkout.multi.paymentMethod.addPaymentDetails.boleto"/></h1>

						<p>
							<spring:theme code="checkout.multi.paymentMethod.addPaymentDetails.enterYourCardDetails"/></p>

						<p><spring:theme code="form.required"/></p>
						<div>

							<formElement:formInputBox idKey="CPFforMundipagg" labelKey="CPF" path="CPFforMundipagg" inputCSS="text" mandatory="true" tabindex="3"/>
							
						</div>
							</div>

							<div class="payment_details_left_col-billing">
								<h1><spring:theme code="checkout.multi.paymentMethod.addPaymentDetails.billingAddress"/></h1>
						<p><spring:theme code="checkout.multi.paymentMethod.addPaymentDetails.billingAddressDiffersFromDeliveryAddress"/></p>
						<div>
							
								<c:if test="${cartData.deliveryItemsQuantity > 0}">
									<form:checkbox id="differentAddress" path="newBillingAddress" tabindex="9"/>
									<label for="differentAddress"><spring:theme code="checkout.multi.paymentMethod.addPaymentDetails.enterDifferentBillingAddress"/></label>
								</c:if>
							
						</div>

						<p><spring:theme code="form.required"/></p>
						<div id="newBillingAddressFields">
							<form:hidden path="billingAddress.addressId" class="create_update_address_id"/>
							
							<formElement:formSelectBox idKey="address.title" labelKey="address.title" path="billingAddress.titleCode" mandatory="true" skipBlank="false" skipBlankMessageKey="address.title.pleaseSelect" items="${titles}" tabindex="10"/>
							<formElement:formInputBox idKey="address.firstName" labelKey="address.firstName" path="billingAddress.firstName" inputCSS="text" mandatory="true" tabindex="11"/>
							<formElement:formInputBox idKey="address.surname" labelKey="address.surname" path="billingAddress.lastName" inputCSS="text" mandatory="true" tabindex="12"/>
							<formElement:formInputBox idKey="address.line1" labelKey="address.line1" path="billingAddress.line1" inputCSS="text" mandatory="true" tabindex="14"/>
							<formElement:formInputBox idKey="address.line2" labelKey="address.line2" path="billingAddress.line2" inputCSS="text" mandatory="false" tabindex="15"/>
							<formElement:formInputBox idKey="address.townCity" labelKey="address.townCity" path="billingAddress.townCity" inputCSS="text" mandatory="true" tabindex="16"/>
                            <formElement:formInputBox idKey="address.phone" labelKey="address.phone" path="billingAddress.phone" inputCSS="text" mandatory="true"/>
							<formElement:formInputBox idKey="address.postcode" labelKey="address.postcode" path="billingAddress.postcode" inputCSS="text" mandatory="true" tabindex="17"/>
                                                        
							<formElement:formSelectBox idKey="address.country" labelKey="address.country" path="billingAddress.countryIso" mandatory="true" skipBlank="false" skipBlankMessageKey="address.selectCountry" items="${countries}" itemValue="isocode" tabindex="18"/>
							<form:hidden path="billingAddress.shippingAddress"/>
							<form:hidden path="billingAddress.billingAddress"/>
						</div>
							</div>
							<div class="save_payment_details">
							<sec:authorize ifNotGranted="ROLE_ANONYMOUS">
							
							
						</sec:authorize>
						<span class="clear_fix">
							<ycommerce:testId code="editPaymentMethod_savePaymentMethod_button">
								<button class="form" tabindex="20" id="lastInTheForm" type="submit">
									<spring:theme code="checkout.multi.paymentMethod.addPaymentDetails.useThesePaymentDetails"/>
								</button>
							</ycommerce:testId>
						</span>
							</div>
						</form:form>
					</div>
				</div>
			</div>
			<multi-checkout:checkoutOrderDetails cartData="${cartData}" showShipDeliveryEntries="true" showPickupDeliveryEntries="true"/>
		</div>
	
	<cms:pageSlot position="SideContent" var="feature" element="div" class="span-24 side-content-slot cms_disp-img_slot">
		<cms:component component="${feature}"/>
	</cms:pageSlot>
</template:page>














