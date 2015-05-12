<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="voucher" tagdir="/WEB-INF/tags/desktop/multistep" %>
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
<%@ taglib prefix="multi-checkout" tagdir="/WEB-INF/tags/addons/b2ccheckoutaddon/desktop/checkout/multi" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="multi-checkout-hering" tagdir="/WEB-INF/tags/addons/heringcheckoutaddon/desktop/checkout/multi" %>

<c:url value="/checkout/multi/add-payment-method" var="choosePaymentMethodUrl"/>
<template:page pageTitle="${pageTitle}" hideHeaderLinks="true">

	<div id="globalMessages">
		<common:globalMessages/>
	</div>

	<multi-checkout:checkoutProgressBar steps="${checkoutSteps}" currentStep="3" stepName="paymentMethod"/>

	<div class="span-14 append-1">
		<div id="checkoutContentPanel" class="clearfix">
			<form:form method="post" commandName="heringPaymentDetailsForm" class="create_update_payment_form">
				<c:set var="deliveryAddress" value="${cartData.deliveryAddress}"/>
				<div id="checkoutContentPanel1" class="clearfix">
					<ul>
												
						<c:set var="count" value="0" scope="page" />						
						
						<c:forEach items="${paymentModes}" var="paymentMode">
							<c:set var="count" value="${count + 1}" scope="page"/>
							<div class="payment_method${count}" style="width:466px">
								<c:if test="${true}">
									<c:set var="checked" value="${!(paymentMode.code eq selectedPaymentMode) ? 'checked=\"checked\"' : ''}" />
								</c:if>
								<c:if test="${paymentMode.code == 'Boleto'}">
									<div class="payment">
										<input type="radio"  name="paymentMode"  value="${paymentMode.code}" id="${paymentMode.name}" >
   										<label for="${paymentMode.name}">${paymentMode.name}</label>
									</div>
								</c:if>
								<c:if test="${paymentMode.code == 'Voucher'}" >
    						        <c:if test="${false}">
                                           <c:if test="${true}">
    											<c:set var="checked" value="${!(paymentMode.code eq selectedPaymentMode) ? 'checked=\"checked\"' : ''}" />
    										</c:if>
    										<input type="radio" name="paymentMode" value="${paymentMode.code}" id="${paymentMode.name}" >
       										<label for="${paymentMode.name}">${paymentMode.name}</label>
    								<div id="voucherPayment" class="payment" style="display: none;">	
    										<c:choose>
    											<c:when test="${not hasAppliedValeCredito}">
    												
    													<div class="item_container">
    													<input type="text" id="voucher" name="voucher"
    														value="DIGITE AQUI SEU CUPOM DE DESCONTO/VALE CREDITO"
    														style="color: #aaa"
    														onfocus="this.value='';this.style.color='#000';this.onfocus='';"
    														placeholder="DIGITE AQUI SEU CUPOM DE DESCONTO/VALE CREDITO" />
    													
    												</div>
    											
    												<c:set var="removeVoucher" value="false" />
    												
    											</c:when>
    											<c:otherwise>
    
    												
    												
    												<div class="item_container">
    													<button class="positive" type="submit" name="removeVoucher" value="RemoveVoucher">
    														<spring:theme code="voucher.remove" />
    													</button>
    												</div>
    											
    											</c:otherwise>
    										</c:choose>
    									</div>
    								</c:if>
                                </c:if>
								<c:if test="${paymentMode.code == 'Debit'}">
                                    <c:if test="${false}">
									<style>#startDate,#issueNum{display:none!important};</style>
									<input type="radio" name="paymentMode" value="${paymentMode.code}" id="${paymentMode.name}" >
  									<label for="${paymentMode.name}">${paymentMode.name}</label>
                                    </c:if>
  								</c:if>
  								<c:if test="${paymentMode.code == 'CreditCard'}">
									<style>#startDate,#issueNum{display:none!important};</style>
									<input type="radio"  name="paymentMode" checked="checked" value="${paymentMode.code}">
										<label for="cardTypeCode"><spring:theme code="payment.paymentmode.credit"/></label> 
									<div id="cardDetails" class="cardForm" style="display: none;">
										<div style="display:none"></div>
										<form:hidden path="paymentId" class="create_update_payment_id"/>
										<formElement:formInputBox idKey="cardNumber" labelKey="payment.cardNumber" path="cardNumber" inputCSS="text" mandatory="true" tabindex="2" autocomplete="off"/>
										<div id="creditCardsPayment">
											<ul>
												<li>
												<input type="radio" name="cardTypeCode" id="visa" value="visa">
												<label for="visa">Visa</label>
												</li>
												
												<li>
												<input type="radio" name="cardTypeCode" id="mastercard" value="master">
												<label for=master>Mastercard</label>
												</li>
												
												<li>
												<input type="radio" name="cardTypeCode" id="diners" value="diners">
												<label for="diners">Diners</label>
												</li>
												
												<li>
												<input type="radio" name="cardTypeCode" id="american" value="amex">
												<label for="amex">American</label>
												</li>
											</ul>
										</div>
										<formElement:formInputBox idKey="nameOnCard" labelKey="payment.nameOnCard" path="nameOnCard" inputCSS="text" mandatory="true" tabindex="3"/>
										<fieldset id="startDate" class="cardDate">
											<legend><spring:theme code="payment.startDate"/></legend>
											<formElement:formSelectBox idKey="StartMonth" labelKey="payment.month" path="startMonth" mandatory="true" skipBlank="false" skipBlankMessageKey="" items="${months}" tabindex="4"/>
											<formElement:formSelectBox idKey="StartYear" labelKey="payment.year" path="startYear" mandatory="true" skipBlank="false" skipBlankMessageKey="" items="${startYears}" tabindex="5"/>
										</fieldset>
										<fieldset class="cardDate">
											<legend><spring:theme code="payment.expiryDate"/></legend>
											<formElement:formSelectBox idKey="ExpiryMonth" labelKey="payment.month" path="expiryMonth" mandatory="true" skipBlank="false" skipBlankMessageKey="" items="${months}" tabindex="6"/>
											<formElement:formSelectBox idKey="ExpiryYear" labelKey="payment.year" path="expiryYear" mandatory="true" skipBlank="false" skipBlankMessageKey="" items="${expiryYears}" tabindex="7"/>
										</fieldset>
										<div id="issueNum">
											<formElement:formInputBox idKey="payment.issueNumber" labelKey="payment.issueNumber" path="issueNumber" inputCSS="text" mandatory="false" tabindex="9"/>
										</div>
										<formElement:formSelectBox idKey="instalment" labelKey="payment.instalment" path="instalment" mandatory="true" skipBlank="true"  items="${instalments}" tabindex="8"/>
										<div class="control-group ">
											<label class="control-label " for="cv2Number">
												<spring:theme code="payment.cv2Number"/><span class="mandatory">*</span><span class="skip"></span>
												<div class="tooltip">
											 		<span class="tooltip-link"> (?) </span>
											 		<span class="tooltip-content"> </span>
												</div>
											</label>
											<div class="controls">
												<input id="cv2Number" name="cv2Number" class="text" tabindex="10" type="text" value="" autocomplete="off">
											</div>
										</div>
										<span class="cvcImage"></span>
										<c:if test="${false}">
										<div class="save_payment_details">
											<sec:authorize ifNotGranted="ROLE_ANONYMOUS">
												<form:checkbox id="SaveDetails" path="saveInAccount" tabindex="19"/>
												<label for="SaveDetails"><spring:theme code="checkout.multi.paymentMethod.addPaymentDetails.savePaymentDetailsInAccount"/></label>
											</sec:authorize>
										</div>
										</c:if>
										
									</div>
								</c:if>
							</div>
						</c:forEach>
					</ul>
				</div>
			
				
				
				<c:if test="${not empty cartData.deliveryAddress}">
						<div class="endereco_entrega">
						
<!-- 							<input id="differentAddress" name="newBillingAddress" tabindex="9" type="checkbox" value="true" checked="checked"> -->
<%-- 							<label for="differentAddress"><spring:theme code="checkout.multi.mesmo.endereco" text="Utilizar o mesmo endereco de entrega como o de cobrança"/></label> --%>
								<div class="same-address-for-billing">
									<formElement:formCheckbox idKey="differentAddress" labelKey="checkout.multi.mesmo.endereco" path="newBillingAddress" inputCSS="" labelCSS="" mandatory="false"/>
									<input type="hidden" name="_newBillingAddress" value="on">
		 						</div>
						</div>
						<div id="newBillingAddressFields" class="cardForm" style="display: none">
							<form:hidden path="billingAddress.addressId" class="create_update_address_id"/>
							<form:hidden path="billingAddress.countryIso" />
							<formElement:formInputBox idKey="address.firstName" labelKey="address.firstName" path="billingAddress.firstName" inputCSS="text" mandatory="true"/>
							<formElement:formInputBox idKey="address.surname" labelKey="address.surname" path="billingAddress.lastName" inputCSS="text" mandatory="true"/>
							<formElement:formInputBox idKey="address.phone1" labelKey="address.phone1" path="billingAddress.phone" inputCSS="text" mandatory="true" autocomplete="off"/>
							<formElement:formInputBox idKey="address.phone2" labelKey="address.phone2" path="billingAddress.celPhone" inputCSS="text" mandatory="false" autocomplete="off"/>
							<formElement:formInputBox idKey="address.receiver" labelKey="address.receiver" path="billingAddress.receiver" inputCSS="text" mandatory="true"/>
							<formElement:formInputBox idKey="address.postcode" labelKey="address.postcode" path="billingAddress.postcode" inputCSS="text cep" mandatory="true" autocomplete="off"/>
							<formElement:formInputBox idKey="address.line1" labelKey="address.line1" path="billingAddress.line1" inputCSS="text" mandatory="true"/>
							<formElement:formInputBox idKey="address.number" labelKey="address.number" path="billingAddress.number" inputCSS="text" mandatory="true"/>
							<formElement:formInputBox idKey="address.complement" labelKey="address.complement" path="billingAddress.complement" inputCSS="text" mandatory="false"/>
							<formElement:formInputBox idKey="address.district" labelKey="address.district" path="billingAddress.neighborhood" inputCSS="text" mandatory="true"/>
							<formElement:formInputBox idKey="address.townCity" labelKey="address.townCity" path="billingAddress.townCity" inputCSS="text" mandatory="true"/>
							<formElement:formSelectBox idKey="address.state" labelKey="address.state" items="${regions}" path="billingAddress.regionIso" itemValue="${useShortRegionIso ? 'isocodeShort' : 'isocode'}" mandatory="true"/>
							<formElement:formSelectBox idKey="address.addressType" labelKey="address.addressType" path="billingAddress.addressType" mandatory="true" skipBlank="true" items="${addressTypes}" selectedValue="${heringAddressForm.addressType}"/>
							<formElement:formInputBox idKey="address.reference" labelKey="address.reference" path="billingAddress.reference" inputCSS="text" mandatory="false"/>
							<form:hidden path="billingAddress.shippingAddress"/>
							<form:hidden path="billingAddress.billingAddress"/>
						</div>
					</c:if>
				
				
				<div class="form-actions">
					<c:if test="${not hasNoPaymentInfo and false}">
							<a class="button" href="${choosePaymentMethodUrl}"><spring:theme code="checkout.multi.cancel" text="Cancel"/></a>
					</c:if>
					<ycommerce:testId code="editPaymentMethod_savePaymentMethod_button">
						<button class="positive right change_address_button show_processing_message" tabindex="20" id="lastInTheForm" type="submit">
							Continuar
						</button>
					</ycommerce:testId>
				</div>
			</form:form>
		</div>
	</div>
	<multi-checkout-hering:checkoutOrderDetails cartData="${cartData}" showShipDeliveryEntries="true" showPickupDeliveryEntries="true" showTax="true"/>
 
    <c:if test="${false}">
		<cms:pageSlot position="SideContent" var="feature" element="div" class="span-24 side-content-slot cms_disp-img_slot">
			<cms:component component="${feature}"/>
		</cms:pageSlot>
	</c:if>

</template:page>
