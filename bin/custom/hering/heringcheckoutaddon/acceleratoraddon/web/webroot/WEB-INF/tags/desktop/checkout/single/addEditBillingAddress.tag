<%@ attribute name="regions" required="true" type="java.util.List"%>
<%@ attribute name="country" required="true" type="java.lang.String"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="single-checkout-hering"
    tagdir="/WEB-INF/tags/addons/heringcheckoutaddon/desktop/checkout/single"%>

<div class="paymentDetailsBillingAddress">
    <form:form method="post" commandName="heringBillingAddressForm" class="create_update_payment_form">
        <div id="newBillingAddressFields" class="cardForm" style="display: none;">
            <form:hidden path="addressId" class="create_update_address_id" />
            <single-checkout-hering:addressFormElements regions="${regions}" country="${country}" page="${page}" />
            <input type="hidden" id="saveInAddressBook" name="saveInAddressBook" value="true">
            <%-- 		        <formElement:formCheckbox idKey="saveAddressInMyAddressBook" --%>
            <%-- 		                labelKey="checkout.summary.deliveryAddress.saveAddressInMyAddressBook" --%>
            <%-- 		                path="saveInAddressBook" inputCSS="add-address-left-input" --%>
            <%-- 		                labelCSS="add-address-left-label" mandatory="false"/> --%>
            <input type="hidden" name="address.shippingAddress" value="false" /> 
            <input type="hidden" name="address.billingAddress" value="true" />
            <div class="form-actions">
                <button
                    class="buttonBillingAddressFormSendForm positive right btn btn-primary btn-sm btn-addr-cadastrar"
                    type="button">
                    <spring:theme code="checkout.multi.saveAddress" text="Save address" />
                </button>
            </div>
        </div>
    </form:form>
</div>
