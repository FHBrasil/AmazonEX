<%@ attribute name="regions" required="true" type="java.util.List"%>
<%@ attribute name="country" required="true" type="java.lang.String"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="single-checkout-hering"
    tagdir="/WEB-INF/tags/addons/heringcheckoutaddon/desktop/checkout/single"%>
    
<div id="newBillingAddressFields" class="cardForm" style="display: none;">
	<div class="section-container">
    	<section id="selected-address">
        	<c:if test="${not empty selectedBillingAddress.id}">
                <div class="address-info">
                	<span id="e-tipo" class="h4"><b>
                		<c:if test="${selectedBillingAddress.type.code == 'PACKSTATION'}">
                			<spring:theme code="checkout.single.address.packStation"/>
                		</c:if>
                		<c:if test="${selectedBillingAddress.type.code != 'PACKSTATION'}">
                			<spring:theme code="checkout.single.address"/>
                		</c:if>
                		</b>
                	</span><br /><br />
                    <input type="hidden" class="e-code" value="${selectedBillingAddress.id}"/>
                    <span class="e-receiver">${selectedBillingAddress.receiver}</span>
                    <div class="btn-group">
                    	<a href="#deliveryAddressModal" class="billing btn-editar" data-toggle="modal"><small><span class="glyphicon glyphicon-pencil"></span><spring:theme code="checkout.single.address.edit"/></small></a>
                    </div><br />
                    <span class="e-addr">${selectedBillingAddress.line1}</span>,
                    <span class="e-numero">${selectedBillingAddress.number}</span><br />
                    <c:if test="${not empty selectedBillingAddress.complement}">
                    	<span class="e-complemento">${selectedBillingAddress.complement}</span><br />
                    </c:if>
                    <c:if test="${not empty selectedBillingAddress.reference}">
                       	<span class="e-ref">${selectedBillingAddress.reference}</span><br />
                    </c:if>
                    <span class="e-cep">${selectedBillingAddress.postalCode}</span>&nbsp;<span class="e-cidade">${selectedBillingAddress.town}</span><br />
                    <c:if test="${not empty selectedBillingAddress.region.name}">
                    	<span class="e-estado">${selectedBillingAddress.region.name}</span><br />
                    </c:if>                
                    <span class="e-pais">${selectedBillingAddress.country.name}</span><br /><br />
                    <spring:theme code="checkout.single.address.phone"/>
                    <span class="e-phone">:&nbsp;${selectedBillingAddress.dddPhone}&nbsp;${selectedBillingAddress.phone}</span>                    
					<a href="#editAddressModal" data-toggle="modal"  data-dismiss="modal" class="btn btn-default"><span class="glyphicon glyphicon-plus"></span> <spring:theme code="checkout.single.address.addNewAddress"/></a>					
           		</div>
        	</c:if>
    	</section>
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