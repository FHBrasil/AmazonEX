<%@ attribute name="regions" required="true" type="java.util.List"%>
<%@ attribute name="country" required="false" type="java.lang.String"%>
<%@ attribute name="page" required="false" type="java.lang.String"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="address" tagdir="/WEB-INF/tags/desktop/address"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="single-checkout-hering" tagdir="/WEB-INF/tags/addons/heringcheckoutaddon/desktop/checkout/single"%> 

<div id="savedAddressListHolder" style="display: block;" class="clear">
	<input type="hidden" class="contextPath" value="${request.contextPath}" />
    <%--input type="hidden" class="selectedDeliveryAddressCodeId" value="${selectedDeliveryAddress.id}" /--%>
    <header>
    	<h2><spring:theme code="checkout.single.address.title"/></h2>
    </header>
    <div class="section-container">
    	<section id="selected-address">
    		<c:choose>
        	<c:when test="${not empty selectedBillingAddress.id}">
                <div class="address-info">
                	<span id="e-tipo" class="h4"><b>                		
                		<spring:theme code="checkout.single.address"/>
                	</b></span><br /><br />
                    <input type="hidden" class="e-code" value="${selectedBillingAddress.id}"/>
                    <span class="e-receiver">${selectedBillingAddress.receiver}</span>
                    <div class="btn-group">
                    	<a href="#deliveryAddressModalbilling" class="billing btn-editar" data-toggle="modal"><small><span class="glyphicon glyphicon-pencil"></span>&nbsp;<spring:theme code="checkout.single.address.edit"/></small></a>
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
                    <c:if test="${not empty customer.defaultPhoneNumber}">
                    	<spring:theme code="checkout.single.address.phone"/>
                    	<span class="e-phone">:&nbsp;${customer.defaultPhoneNumber}</span><br />
                    </c:if>
                    <spring:theme code="checkout.single.address.email"/><span class="e-email">:&nbsp;${customer.uid}</span><br />                  	
                        
                    <%-- 
                    <div class="btn-group">
                        <a href="#" class="btn btn-primary btn-sm btn-outro-endereco" ${themeName == 'dzarm' ? "style='font-size: 12px;'" : ""}>
                        	<spring:theme code="checkout.single.address.useAnotherAddress"/>
                        </a>
                    </div><br />
                    --%>
                    <div class="checkbox">
	                    <label>
	                        <input type="checkbox" name="billing" id="differentAddress" ${checkedDifferingBilling ? 'checked' : ''}/>
	                    	<spring:theme code="checkout.single.address.useDeliveryAddressAsBillingAddress"/>
	                    </label> 
                    </div>                   
                </div>
			</c:when>
				<c:otherwise>
					<a href="#editAddressModal" data-toggle="modal"  data-dismiss="modal" class="btn btn-default zeroAddress"><span class="glyphicon glyphicon-plus"></span> <spring:theme code="checkout.single.address.addNewAddress"/></a>
				</c:otherwise>
			</c:choose>
            <single-checkout-hering:chooseAddress type="billing" address="${selectedBillingAddress}"/>
        </section>
        <single-checkout-hering:newAddress regions="${regions}" country="${country}" page="${page}" suggestedAddresses="${suggestedAddresses}" />
	</div>
</div>

