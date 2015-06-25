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
     
<div id="editAddressModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title"><spring:theme code="checkout.single.address.newAddress"/></h4>
                </div>
                <div class="modal-body">
					<div class="form-inline" style="margin-bottom:15px;">
						<div class="radio">
							<input type="radio" name="addresstype" id="addresstype-address" class="closeNewAddress" checked="true">
							<label for="addresstype-address" class="closeNewAddress" data-toggle="collapse" data-target=".toggleNewAddress">
								<spring:theme code="checkout.single.address"/>
							</label>
						</div>
						<div class="radio"style="margin-left:15px;">
							<input type="radio" name="addresstype" id="addresstype-packstation" class="closeNewAddress" >
							<label for="addresstype-packstation" class="closeNewAddress" data-toggle="collapse" data-target=".toggleNewAddress">
								<spring:theme code="checkout.single.address.packStation"/>
							</label>
						</div>
					</div>
					<c:url value="/checkout/single/add-address" var="addNewAddressUrl"/>
    				<form:form method="post" action="${addNewAddressUrl}" commandName="heringAddressForm" cssClass="addEditDeliveryAddressForm toggleNewAddress collapse addresscollapse in">
				        <input type="hidden" id="saveInAddressBook" name="saveInAddressBook" value="true">
				        <form:hidden path="addressId" class="add_edit_delivery_address_id" status="${not empty suggestedAddresses ? 'hasSuggestedAddresses' : ''}" />
				        <form:hidden path="shippingAddress" value="true" />
				        <input type="hidden" name="bill_state" value="bill_state" id="address.billstate" />
				        <div id="i18nAddressForm" class="i18nAddressForm">
				            <single-checkout-hering:addressFormElements regions="${regions}" country="${country}" page="${page}" />
				        </div>
				        <sec:authorize ifNotGranted="ROLE_ANONYMOUS">
				        	<%-- 
				            <div class="form-additionals">
				                <div class="required right">
				                    <small><spring:theme code="form.required" text="Fields marked * are required" /></small>
				                </div>
				<%--                 <formElement:formCheckbox idKey="saveAddressInMyAddressBook" --%>
				<%--                         labelKey="checkout.summary.deliveryAddress.saveAddressInMyAddressBook" --%>
				<%--                         path="saveInAddressBook" inputCSS="add-address-left-input" --%>
				<%--                         labelCSS="add-address-left-label" mandatory="false" /> 
				            </div>
				            --%>
				        </sec:authorize>
				        <div class="modal-footer">
					       	<button type="button" class="btn btn-default" data-dismiss="modal">
					       		<spring:theme code="checkout.single.address.abort" text="Abort" />
					       	</button>
					        <button class="btn btn-primary btn-sm" type="submit">
					        	<spring:theme code="checkout.multi.saveAddress" text="Save address" />
					        </button>					        
				        </div>
				    </form:form>
				    <c:url value="/checkout/single/add-address" var="addNewAddressUrl"/>
				    <form:form method="post" action="${addNewAddressUrl}" commandName="packstationAddressForm" cssClass="addEditDeliveryAddressForm toggleNewAddress collapse addresscollapse out">
				       	<input type="hidden" id="saveInAddressBook" name="saveInAddressBook" value="true">
				       	<form:hidden path="addressId" class="add_edit_delivery_address_id" status="${not empty suggestedAddresses ? 'hasSuggestedAddresses' : ''}" />
				        <form:hidden path="shippingAddress" value="true" />
				        <input type="hidden" name="bill_state" value="bill_state" id="address.billstate" />
				        <div id="i18nAddressForm" class="i18nAddressForm">
				            <single-checkout-hering:addressFormElements regions="${regions}" country="${country}" page="${page}" type="packStation" />
				        </div>
				        <sec:authorize ifNotGranted="ROLE_ANONYMOUS">
				        	<%-- 
				            <div class="form-additionals">
				                <div class="required right">
				                    <small><spring:theme code="form.required" text="Fields marked * are required" /></small>
				                </div>
				           </div>
				           --%>
				        </sec:authorize>
				        <div class="modal-footer">
					       	<button type="button" class="btn btn-default" data-dismiss="modal">
					       		<spring:theme code="checkout.single.address.abort" text="Abort" />
					       	</button>
					        <button class="btn btn-primary btn-sm" type="submit">
					        	<spring:theme code="checkout.multi.saveAddress" text="Save address" />
					        </button>					        
				        </div>
				    </form:form>
			</div>
		</div>
	</div>
</div>
<%-- 				    
<section id="new-address"
    style="${empty showEditAddress || showEditAddress eq false ? 'display: none' : 'display: block'};">
    <c:url value="/checkout/single/add-address" var="addNewAddressUrl"/>
    <form:form method="post" action="${addNewAddressUrl}" commandName="heringAddressForm" cssClass="addEditDeliveryAddressForm">
        <input type="hidden" id="saveInAddressBook" name="saveInAddressBook" value="true">
        <form:hidden path="addressId" class="add_edit_delivery_address_id" status="${not empty suggestedAddresses ? 'hasSuggestedAddresses' : ''}" />
        <form:hidden path="shippingAddress" value="true" />
        <input type="hidden" name="bill_state" value="bill_state" id="address.billstate" />
        <div id="i18nAddressForm" class="i18nAddressForm">
            <single-checkout-hering:addressFormElements regions="${regions}" country="${country}" page="${page}" />
        </div>
        <sec:authorize ifNotGranted="ROLE_ANONYMOUS">
            <div class="form-additionals">
                <div class="required right">
                    <spring:theme code="form.required" text="Fields marked * are required" />
                </div>
<%--                 <formElement:formCheckbox idKey="saveAddressInMyAddressBook" --%>
<%--                         labelKey="checkout.summary.deliveryAddress.saveAddressInMyAddressBook" --%>
<%--                         path="saveInAddressBook" inputCSS="add-address-left-input" --%>
<%--                         labelCSS="add-address-left-label" mandatory="false" /> --%>
   <%--         </div>
        </sec:authorize>
        <div id="addressform_button_panel" class="form-actions">
            <button class="btn btn-primary btn-sm btn-addr-cadastrar" type="submit">
                <spring:theme code="checkout.multi.saveAddress" text="Save address" />
            </button>
        </div>
    </form:form>
</section>
--%>