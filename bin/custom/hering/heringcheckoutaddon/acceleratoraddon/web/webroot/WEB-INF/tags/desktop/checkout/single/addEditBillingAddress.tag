<%@ attribute name="regions" required="true" type="java.util.List"%>
<%@ attribute name="country" required="true" type="java.lang.String"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="single-checkout-hering" tagdir="/WEB-INF/tags/addons/heringcheckoutaddon/desktop/checkout/single"%>
    
<div id="newBillingAddressFields" class="cardForm" style="display: none;">
	<div class="section-container">
    	<section id="selected-address">
        	<c:if test="${not empty selectedDeliveryAddress.id}">
                <div class="address-info">
                	<span id="e-tipo" class="h4">
                		<b>
                			<spring:theme code="checkout.single.shippingAddress"/>
                		</b>
                	</span><br /><br />
                    <input type="hidden" class="e-code" value="${selectedDeliveryAddress.id}"/>
                    <span class="e-receiver">${selectedDeliveryAddress.receiver}</span>
                    <div class="btn-group">
                    	<a href="#deliveryAddressModaldelivery" class="btn-editar" data-toggle="modal"><small><span class="glyphicon glyphicon-pencil"></span><spring:theme code="checkout.single.address.edit"/></small></a>
                    </div><br />
                    <span class="e-addr">${selectedDeliveryAddress.line1}</span>,
                    <span class="e-numero">${selectedDeliveryAddress.number}</span><br />
                    <c:if test="${not empty selectedDeliveryAddress.complement}">
                    	<span class="e-complemento">${selectedDeliveryAddress.complement}</span><br />
                    </c:if>
                    <c:if test="${not empty selectedDeliveryAddress.reference}">
                       	<span class="e-ref">${selectedDeliveryAddress.reference}</span><br />
                    </c:if>
                    <span class="e-cep">${selectedDeliveryAddress.postalCode}</span>&nbsp;<span class="e-cidade">${selectedDeliveryAddress.town}</span><br />
                    <c:if test="${not empty selectedDeliveryAddress.region.name}">
                    	<span class="e-estado">${selectedDeliveryAddress.region.name}</span><br />
                    </c:if>                
                    <span class="e-pais">${selectedDeliveryAddress.country.name}</span><br /><br />
                    <%-- <spring:theme code="checkout.single.address.phone"/>
                    <span class="e-phone">:&nbsp;${selectedDeliveryAddress.dddPhone}&nbsp;${selectedDeliveryAddress.phone}</span>                    
					<a href="#editAddressModal" data-toggle="modal"  data-dismiss="modal" class="btn btn-default"><span class="glyphicon glyphicon-plus"></span> <spring:theme code="checkout.single.address.addNewAddress"/></a>	--%>				
           		</div>
        	</c:if>
    	</section>
    	<single-checkout-hering:chooseAddress type="delivery" address="${selectedDeliveryAddress}"/>
    </div>
</div>
<%-- 
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
 <%--            <input type="hidden" name="address.shippingAddress" value="false" /> 
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
--%>