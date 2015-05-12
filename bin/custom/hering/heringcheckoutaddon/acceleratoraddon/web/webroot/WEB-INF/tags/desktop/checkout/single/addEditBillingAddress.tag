<%@ attribute name="regions" required="true" type="java.util.List"%>
<%@ attribute name="country" required="true" type="java.lang.String"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="single-checkout-hering"
	tagdir="/WEB-INF/tags/addons/heringcheckoutaddon/desktop/checkout/single"%>

<%-- ADD EDIT BILLING ADDRESS FOR DZARM STORE --%> 
<c:if test="${themeName == 'black'}">
	<div class="paymentDetailsBillingAddress">
	<form:form method="post" commandName="heringBillingAddressForm"
	        class="create_update_payment_form">
	    <div class="endereco_entrega">
	        <div class="same-address-for-billing">
	            <formElement:formCheckbox idKey="differentAddress"
	                    labelKey="checkout.multi.mesmo.endereco"
	                    path="billing" inputCSS="" labelCSS=""
	                    mandatory="false" />
	            <input type="hidden" name="_newBillingAddress" value="on" />
	        </div>
	    </div>
	    <div id="newBillingAddressFields" class="cardForm" style="display: none;">
	        <form:hidden path="addressId"
	                class="create_update_address_id" />
	        <single-checkout-hering:addressFormElements regions="${regions}"
	                    country="${country}" page="${page}" />
	                    <input type="hidden" id="saveInAddressBook" name="saveInAddressBook" value="true">
<%--  	        <formElement:formCheckbox idKey="saveAddressInMyAddressBook" --%>
<%-- 	                labelKey="checkout.summary.deliveryAddress.saveAddressInMyAddressBook" --%>
<%-- 	                path="saveInAddressBook" inputCSS="add-address-left-input" --%>
<%-- 	                labelCSS="add-address-left-label" mandatory="false"/>  --%>
            <input type="hidden" name="address.shippingAddress" value="false"/>
            <input type="hidden" name="address.billingAddress" value="true"/>
	        <div class="form-actions">
	            <button class="buttonBillingAddressFormSendForm positive right"
	                    type="button">
	                <spring:theme code="checkout.multi.saveAddress"
	                        text="Save address"/>
	            </button>
	        </div>
	    </div>
	</form:form>
	</div>
</c:if>

<%-- ADD EDIT BILLING ADDRESS FOR HERING STORE --%>
<c:if test="${themeName == 'hering' || themeName == 'dzarm' || themeName == 'foryou'}">

	<div class="paymentDetailsBillingAddress">
		<form:form method="post" commandName="heringBillingAddressForm" class="create_update_payment_form">
		    <div id="newBillingAddressFields" class="cardForm" style="display: none;">
		        <form:hidden path="addressId"
		                class="create_update_address_id" />
		        <single-checkout-hering:addressFormElements regions="${regions}"
		                    country="${country}" page="${page}" />
		                    <input type="hidden" id="saveInAddressBook" name="saveInAddressBook" value="true">		                    
<%-- 		        <formElement:formCheckbox idKey="saveAddressInMyAddressBook" --%>
<%-- 		                labelKey="checkout.summary.deliveryAddress.saveAddressInMyAddressBook" --%>
<%-- 		                path="saveInAddressBook" inputCSS="add-address-left-input" --%>
<%-- 		                labelCSS="add-address-left-label" mandatory="false"/> --%>
                <input type="hidden" name="address.shippingAddress" value="false"/>
                <input type="hidden" name="address.billingAddress" value="true"/>
		        <div class="form-actions">
		            <button class="buttonBillingAddressFormSendForm positive right btn btn-addr-cadastrar"
		                    type="button">
		                <spring:theme code="checkout.multi.saveAddress"
		                        text="Save address"/>
		            </button>
		        </div>
		    </div>
		</form:form>
	</div>

</c:if>