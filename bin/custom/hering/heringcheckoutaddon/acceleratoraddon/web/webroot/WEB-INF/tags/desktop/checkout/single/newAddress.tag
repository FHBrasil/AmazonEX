<%@ attribute name="regions" required="true" type="java.util.List"%>
<%@ attribute name="country" required="false" type="java.lang.String"%>
<%@ attribute name="page" required="false" type="java.lang.String"%>
<%@ attribute name="suggestedAddresses" required="false" type="java.lang.String"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="address" tagdir="/WEB-INF/tags/desktop/address"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="single-checkout-hering"
    tagdir="/WEB-INF/tags/addons/heringcheckoutaddon/desktop/checkout/single"%>
     
<section id="new-address"
    style="${empty showEditAddress || showEditAddress eq false ? 'display: none' : 'display: block'};">
    <c:url value="/checkout/single/add-address" var="addNewAddressUrl"/>
    <form:form method="post"
            action="${addNewAddressUrl}"
            commandName="heringAddressForm" cssClass="addEditDeliveryAddressForm">
        <input type="hidden" id="saveInAddressBook" name="saveInAddressBook" value="true">
        <form:hidden path="addressId" class="add_edit_delivery_address_id"
                status="${not empty suggestedAddresses ? 'hasSuggestedAddresses' : ''}" />
        <form:hidden path="shippingAddress" value="true" />
        <input type="hidden" name="bill_state" value="bill_state"
                id="address.billstate" />
        <div id="i18nAddressForm" class="i18nAddressForm">
            <single-checkout-hering:addressFormElements regions="${regions}"
                    country="${country}" page="${page}" />
        </div>
        <sec:authorize ifNotGranted="ROLE_ANONYMOUS">
            <div class="form-additionals">
                <div class="required right">
                    <spring:theme code="form.required"
                            text="Fields marked * are required" />
                </div>
<%--                 <formElement:formCheckbox idKey="saveAddressInMyAddressBook" --%>
<%--                         labelKey="checkout.summary.deliveryAddress.saveAddressInMyAddressBook" --%>
<%--                         path="saveInAddressBook" inputCSS="add-address-left-input" --%>
<%--                         labelCSS="add-address-left-label" mandatory="false" /> --%>
            </div>
        </sec:authorize>
        <div id="addressform_button_panel" class="form-actions">
            <button class="btn btn-addr-cadastrar" type="submit">
                <spring:theme code="checkout.multi.saveAddress"
                        text="Save address" />
            </button>
        </div>
    </form:form>
</section>