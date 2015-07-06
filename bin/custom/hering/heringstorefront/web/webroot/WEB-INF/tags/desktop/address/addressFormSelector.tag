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

<input type="hidden" class="contextPath" value="${request.contextPath}" />
	<div id="deliveryAddressModal" class="addressbook150706 modal fade">
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
           					<c:url var="urlDeleteAddress" value="/my-account/remove-address/${deliveryAddress.id}"/>
           					<c:url var="urlEditAddress" value="/my-account/edit-address/${deliveryAddress.id}"/>
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
							<div class="text-right">
								<input type="hidden" name="addressId" value="${deliveryAddress.id}"/>						
								<a href="${urlEditAddress}" class="btn btn-default btn-sm editAddress">
									<span class="glyphicon glyphicon-pencil"></span>
									<span class="hidden-xs">bearbeiten</span>
								</a>
								<c:if test="${quantityAddress > 1}">						
									<a href="${urlDeleteAddress}" class="btn btn-default btn-sm deleteAddress">
										<span class="glyphicon glyphicon-trash"></span>
										<span class="hidden-xs">l&ouml;schen</span>
									</a>
								</c:if>                    	
	                        </div>							                		
                    	</c:forEach>                    	
                    </form>
                 </div>
                 <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><spring:theme code="checkout.single.address.abort"/></button>
                    <button type="button" class="btn btn-primary"><spring:theme code="checkout.single.address.acceptAddress"/></button>
                </div>
			</div>                    		
        </div>
    </div>
    <address:newAddress regions="${regions}" country="${country}" />

<%-- OLD CODE
<c:if test="${not empty deliveryAddresses}">
    <div id="savedAddressListHolder" style="display: block" class="clear">
        <div id="savedAddressList" class="summaryOverlay clearfix">
            <div class="headline">
                <spring:theme code="address.headline" text="Addressbook" />
            </div>
            <div class="addressList">
                <c:forEach items="${deliveryAddresses}" var="deliveryAddress" varStatus="status">
                    <div class="addressEntry">
                        <ul>
                            <li>${fn:escapeXml(deliveryAddress.type.code)}</li>
                            <li>${fn:escapeXml(deliveryAddress.firstName)}&nbsp;
                                ${fn:escapeXml(deliveryAddress.lastName)}</li>
                            <li><c:if test="${not empty deliveryAddress.receiver}">${fn:escapeXml(deliveryAddress.receiver)}</c:if></li>
                            <li>(${fn:escapeXml(deliveryAddress.dddPhone)})${fn:escapeXml(deliveryAddress.phone)}</li>
                            <li><c:if test="${not empty deliveryAddress.celPhone}">(${fn:escapeXml(deliveryAddress.dddCelPhone)})${fn:escapeXml(deliveryAddress.celPhone)}</c:if></li>
                            <li>${fn:escapeXml(deliveryAddress.line1)}&nbsp;
                                ${fn:escapeXml(deliveryAddress.number)}</li>
                            <li>${fn:escapeXml(deliveryAddress.complement)}</li>
                            <li><c:if test="${not empty deliveryAddress.reference}">${fn:escapeXml(deliveryAddress.reference)}</c:if></li>
                            <li>${fn:escapeXml(deliveryAddress.postalCode)}-${fn:escapeXml(deliveryAddress.district)}</li>
                            <li>${fn:escapeXml(deliveryAddress.town)}<c:if
                                    test="${not empty deliveryAddress.region.name}">-${fn:escapeXml(deliveryAddress.region.name)}</c:if>-${fn:escapeXml(deliveryAddress.country.name)}
                            </li>
                        </ul>
                        <form:form
                            action="${request.contextPath}/checkout/multi/edit-delivery-address"
                            method="GET">
                            <input type="hidden" name="editAddressCode"
                                value="${deliveryAddress.id}" />
                            <button type="submit" class="adressButtonEditAddress"
                                tabindex="${status.count + 21}"><spring:theme code="text.fliegercommerce.texto80"/></button>
                        </form:form>
                        <c:if test="${fn:length(deliveryAddresses) > 1}">
                            <form:form action="${request.contextPath}/checkout/multi/remove-address"
                                method="POST">
                                <input type="hidden" name="addressCode"
                                    value="${deliveryAddress.id}" />
                                <button type="submit" class="adressButtonEraseAddress"
                                    tabindex="${status.count + 21}"><spring:theme code="text.fliegercommerce.texto81"/></button>
                            </form:form>
                        </c:if>
                        <form action="${request.contextPath}/checkout/multi/select-delivery-address"
                            method="GET">
                            <input type="hidden" name="selectedAddressCode"
                                value="${deliveryAddress.id}" />
                            <button type="submit" class="adressButtonThisAddress"
                                tabindex="${status.count + 21}">
                                <spring:theme code="checkout.deliverThisAddress"/>
                            </button>
                        </form>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</c:if>
<c:choose>
    <c:when test="${edit eq true}">
        <header>
            <h2><spring:theme code="text.fliegercommerce.texto82"/></h2>
        </header>
    </c:when>
    <c:otherwise>
        <header>
            <h2><spring:theme code="text.fliegercommerce.texto83"/></h2>
        </header>
    </c:otherwise>
</c:choose>
<form:form method="post" commandName="heringAddressForm">
    <form:hidden path="addressId" class="add_edit_delivery_address_id"
        status="${not empty suggestedAddresses ? 'hasSuggestedAddresses' : ''}" />
    <input type="hidden" name="bill_state" id="address.billstate" />
    <address:addressFormElements regions="${regions}" country="DE" page="${page}" />
    <sec:authorize ifNotGranted="ROLE_ANONYMOUS">
        <div class="form-additionals">
            <c:choose>
                <c:when test="${showSaveToAddressBook}">
                    <input type="hidden" id="saveInAddressBook" name="saveInAddressBook"
                        value="true">
                    <%-- 						<formElement:formCheckbox idKey="saveAddressInMyAddressBook"  --%>
                    <%-- 							labelKey="checkout.summary.deliveryAddress.saveAddressInMyAddressBook"  --%>
                    <%-- 							path="saveInAddressBook"  --%>
                    <%-- 							inputCSS="add-address-left-input"  --%>
                    <%-- 							labelCSS="add-address-left-label"  --%>
                    <%-- 							mandatory="false"/> --%>
                    <%-- <input type="hidden" name="defaultAddress" value="${isDefaultAddress }">  
                </c:when>
                <c:when test="${not addressBookEmpty && not isDefaultAddress}">
                    <c:if test="${false}">
                        <formElement:formCheckbox idKey="defaultAddress" labelKey="address.default"
                            path="defaultAddress" inputCSS="add-address-left-input"
                            labelCSS="add-address-left-label" mandatory="false" />
                    </c:if>
                    <!-- <input type="hidden" name="defaultAddress" value="${isDefaultAddress }"> 
                </c:when>
            </c:choose>
        </div>
    </sec:authorize>
    <div id="addressform_button_panel" class="form-actions">
        <c:if test="${not noAddress}">
            <ycommerce:testId code="multicheckout_cancel_button">
                <c:url value="${cancelUrl}" var="cancel" />
                <c:if test="false">
                    <div id="addressform_button_cancel" class="bt-cancelar">
                        <a class="btn btn-cancelar" href="${cancel}"><spring:theme
                                code="checkout.multi.cancel" text="Cancel" /></a>
                    </div>
                </c:if>
            </ycommerce:testId>
        </c:if>
        <c:choose>
            <c:when test="${edit eq true}">
                <ycommerce:testId code="multicheckout_saveAddress_button">
                    <button class="btn btn-confirmar" type="submit">
                        <spring:theme code="checkout.multi.saveAddress" text="Save address" />
                    </button>
                </ycommerce:testId>
            </c:when>
            <c:otherwise>
                <ycommerce:testId code="multicheckout_saveAddress_button">
                    <button class="btn btn-confirmar" type="submit">
                        <spring:theme code="checkout.checkout.multi.next"/>
                    </button>
                </ycommerce:testId>
            </c:otherwise>
        </c:choose>
        <a class="btn btn-cancelar" href="${cancel}"><spring:theme code="checkout.multi.cancel"
                text="Cancel" /></a>
    </div>
</form:form>
--%>