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
<%@ taglib prefix="single-checkout-hering"
    tagdir="/WEB-INF/tags/addons/heringcheckoutaddon/desktop/checkout/single"%>    

<div id="savedAddressListHolder" style="display: block;" class="clear">
	<input type="hidden" class="contextPath" value="${request.contextPath}" />
    <%--input type="hidden" class="selectedDeliveryAddressCodeId" value="${selectedDeliveryAddress.id}" /--%>
    <header>
    	<h2><spring:theme code="checkout.single.address.title"/></h2>
    </header>
    <div class="section-container">
    	<section id="selected-address">
    		<c:choose>
        	<c:when test="${not empty selectedDeliveryAddress.id}">
                <div class="address-info">
                	<span id="e-tipo" class="h4"><b>                		
                		<spring:theme code="checkout.single.address"/>
                	</b></span><br /><br />
                    <input type="hidden" class="e-code" value="${selectedDeliveryAddress.id}"/>
                    <span class="e-receiver">${selectedDeliveryAddress.receiver}</span>
                    <div class="btn-group">
                    	<a href="#deliveryAddressModal" class="btn-editar" data-toggle="modal"><small><span class="glyphicon glyphicon-pencil"></span>&nbsp;<spring:theme code="checkout.single.address.edit"/></small></a>
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
                    <spring:theme code="checkout.single.address.phone"/>
                    <span class="e-phone">:&nbsp;${selectedDeliveryAddress.dddPhone}&nbsp;${selectedDeliveryAddress.phone}</span><br />
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
					<a href="#editAddressModal" data-toggle="modal"  data-dismiss="modal" class="btn btn-default"><span class="glyphicon glyphicon-plus"></span> <spring:theme code="checkout.single.address.addNewAddress"/></a>
				</c:otherwise>
			</c:choose>
            <div id="deliveryAddressModal" class="modal fade">
            	<div class="modal-dialog">
            		<div class="modal-content">
                		<div class="modal-header">
                			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                    <h4 class="modal-title"><spring:theme code="checkout.single.address.chooseAddress"/></h4>
		                </div>
		                <div class="modal-body">
		                	<div class="text-right">
								<a href="#editAddressModal" data-toggle="modal"  data-dismiss="modal" class="btn btn-default"><span class="glyphicon glyphicon-plus"></span> <spring:theme code="checkout.single.address.addNewAddress"/></a>
							</div>
                			<form id="selectAddress">
                				<input type="hidden" name="deliveryOrBilling"/>
                				<c:forEach items="${addressData}" var="deliveryAddress" varStatus="status">
                					<div class="radio">
										<input type="radio" name="chooseDeliveryAddress" value="${deliveryAddress.id}" id="${deliveryAddress.id}" ${status.index == '0' ? 'checked' : ''}>
										<label class="btn btn-default btn-address050609" for="${deliveryAddress.id}">
											<small>${deliveryAddress.receiver}<br /></small>
											<small><c:if test="${not empty deliveryAddress.reference}">
												<spring:theme code="packstation.postNumber"/> ${deliveryAddress.reference}<br />
											</c:if></small>
											<small><c:if test="${not empty deliveryAddress.complement}">
												${deliveryAddress.complement}<br />
											</c:if></small>
											<b>${deliveryAddress.line1}, ${deliveryAddress.number}<br /></b>
											<small>${deliveryAddress.postalCode} ${deliveryAddress.town}, ${deliveryAddress.country.name}</small>
										</label>
									</div>                		
                    			</c:forEach>
                    			<div class="modal-footer">
                    				<button type="button" class="btn btn-default" data-dismiss="modal"><spring:theme code="checkout.single.address.abort"/></button>
                    				<button type="button" class="btn btn-primary"><spring:theme code="checkout.single.address.acceptAddress"/></button>
                				</div>
                    		</form>
                    	</div>
                    </div>                    		
                 </div>
            </div>
        </section>
        <single-checkout-hering:newAddress regions="${regions}" country="${country}" page="${page}" suggestedAddresses="${suggestedAddresses}" />
	</div>
</div>

