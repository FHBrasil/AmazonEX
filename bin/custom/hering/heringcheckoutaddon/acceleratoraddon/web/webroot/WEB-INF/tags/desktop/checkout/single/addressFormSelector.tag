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
<%-- ADDRESS FORM FOR DZARM --%>
<c:if test="${themeName == 'black'}">
    <c:if test="${not empty selectedDeliveryAddress}">
        <div id="savedAddressListHolder" style="display: block;" class="clear">
            <input type="hidden" class="contextPath"
                value="${request.contextPath}" /> <input type="hidden"
                class="selectedDeliveryAddressCodeId"
                value="${selectedDeliveryAddress.id}" />
            <div id="savedAddressList" class="summaryOverlay clearfix">
                <div class="headline">
                    <%--spring:theme code="address.headline" text="Addressbook"/--%>
                    1 - Endereço de Entrega
                </div>
                <div class="selectedDeliveryAddress">
                    <ul>
                        <li>Endereço:
                            ${selectedDeliveryAddress.line1},&nbsp;
                            ${selectedDeliveryAddress.number} <br />
                            ${selectedDeliveryAddress.town} <c:if
                                test="${not empty selectedDeliveryAddress.region.name}">
		                        - ${selectedDeliveryAddress.region.name}
		                    </c:if> <br /> ${selectedDeliveryAddress.postalCode}
                        </li>
                        <li>Tipo: ${selectedDeliveryAddress.type.code}</li>
                        <c:if
                            test="${not empty selectedDeliveryAddress.reference}">
                            <li>Referência -
                                ${selectedDeliveryAddress.reference}</li>
                        </c:if>
                    </ul>
                </div>
                <div class="btn-group"
                    style="font-weight: bold; text-decoration: underline;">
                    <a
                        href="${request.contextPath}/checkout/single/edit-delivery-address?addressCode=${selectedDeliveryAddress.id}"
                        class="btn-editar">Editar</a> <a
                        href="${request.contextPath}/checkout/single/remove-address?addressCode=${selectedDeliveryAddress.id}"
                        class="btn-excluir">Excluir</a> <a href="#"
                        class="btn btn-outro-endereco addressButtonUseAnotherAddress">Usar
                        outro endereço</a>
                </div>
            </div>
        </div>
    </c:if>
    <div class="addressList"
        style="display: ${empty selectedDeliveryAddress ? 'block' : 'none'}">
        <c:forEach items="${deliveryAddresses}" var="deliveryAddress"
            varStatus="status">
            <div class="">
                <input type="hidden" name="selectedAddressCode"
                    value="${deliveryAddress.id}" />
                <button type="button" class="adressButtonThisAddress"
                    tabindex="${status.count + 21}"
                    style="text-decoration: underline; background: none !important; color: #000;">
                    ${deliveryAddress.type.code}</button>
                <ul class="ulDeliveryAddress" style="display: none;">
                    <li>Endereço: ${deliveryAddress.line1},&nbsp;
                        ${deliveryAddress.number} <br />
                        ${deliveryAddress.town} <c:if
                            test="${not empty deliveryAddress.region.name}">
	                        - ${deliveryAddress.region.name}
	                    </c:if> <br /> ${deliveryAddress.postalCode}
                    </li>
                    <li>Tipo: ${deliveryAddress.type.code}</li>
                    <c:if test="${not empty deliveryAddress.reference}">
                        <li>Referência - ${deliveryAddress.reference}</li>
                    </c:if>
                </ul>
            </div>
            <br />
        </c:forEach>
        <br /> <br />
        <button type="button" class="buttonAddNewAddress">Adicionar
            Novo Endereço</button>
        <br />
    </div>
    <c:url value="/checkout/single/add-address"
        var="addAddressURL" />
    <div class="novoEndereco"
        style="${empty showEditAddress || showEditAddress eq false ? 'display: none' : 'display: block'}; clear:both;">
        <form:form method="post" action="${addAddressURL}"
            commandName="heringAddressForm"
            cssClass="addEditDeliveryAddressForm">
            <form:hidden path="addressId" class="add_edit_delivery_address_id"
                status="${not empty suggestedAddresses ? 'hasSuggestedAddresses' : ''}" />
            <form:hidden path="shippingAddress" value="true" />
            <input type="hidden" name="bill_state" id="address.billstate" />
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
                    <input type="hidden" id="saveInAddressBook" name="saveInAddressBook" value="true">
<%--                     <formElement:formCheckbox idKey="saveAddressInMyAddressBook" --%>
<%--                         labelKey="checkout.summary.deliveryAddress.saveAddressInMyAddressBook" --%>
<%--                         path="saveInAddressBook" --%>
<%--                         inputCSS="add-address-left-input" --%>
<%--                         labelCSS="add-address-left-label" mandatory="false" /> --%>
                </div>
            </sec:authorize>
            <div id="addressform_button_panel" class="form-actions">
                <button class="buttonAddressFormSendForm positive right"
                    type="submit">
                    <spring:theme code="checkout.multi.saveAddress"
                        text="Save address" />
                </button>
            </div>
        </form:form>
    </div>
</c:if>
<%-- ADDRESS FORM FOR HERING --%>
<c:if test="${themeName == 'hering' || themeName == 'dzarm' || themeName == 'foryou'}">
    <div id="savedAddressListHolder" style="display: block;" class="clear">
        <input type="hidden" class="contextPath" value="${request.contextPath}" />
        <%--input type="hidden" class="selectedDeliveryAddressCodeId"
            value="${selectedDeliveryAddress.id}" /--%>
        <header>
            <h2><spring:theme code="checkout.single.address.title"/></h2>
        </header>
        <div class="section-container">
            <section id="selected-address">
                <div class="address-info">
                    <dl>
                        <dt><spring:theme code="checkout.single.address.title"/></dt>
                        <dd>
                            <input type="hidden" class="e-code"
                                    value="${selectedDeliveryAddress.id}"/>
                            <span class="e-addr">${selectedDeliveryAddress.line1}</span>,
                            <span class="e-numero">${selectedDeliveryAddress.number}</span>
                            - <span class="e-complemento">${selectedDeliveryAddress.complement}</span>
                            - <span class="e-cidade">${selectedDeliveryAddress.town}</span>
                            - <span class="e-estado">${selectedDeliveryAddress.region.name}</span>
                            <br> <spring:theme code="checkout.single.address.postalCode"/>
                            	<span class="e-cep">${selectedDeliveryAddress.postalCode}</span>
                        </dd>
                        <dt><spring:theme code="checkout.single.address.type"/>:</dt>
                        <dd>
                            <span id="e-tipo">${selectedDeliveryAddress.type.code}</span>
                        </dd>
                        <dt><spring:theme code="checkout.single.address.reference"/>:</dt>
                        <dd>${selectedDeliveryAddress.reference}</dd>
                    </dl>
                    <label>
                        <input type="checkbox" name="billing" id="differentAddress"
                                checked="checked"/>
                    	<spring:theme code="checkout.single.address.useDeliveryAddressAsBillingAddress"/>
                    </label>
                    <div class="btn-group">
                        <a href="#" class="btn-editar">
                        	<spring:theme code="checkout.single.address.edit"/>
                    	</a>
                        <a href="#" class="btn-excluir">
                        	<spring:theme code="checkout.single.address.delete"/>
                        </a>
                        <a href="#" class="btn btn-outro-endereco" ${themeName == 'dzarm' ? "style='font-size: 12px;'" : ""}>
                        	<spring:theme code="checkout.single.address.useAnotherAddress"/>
                        </a>
                    </div>
                </div>
                <div class="address-prompt" style="display: none;">
                    <p><spring:theme code="checkout.single.address.chooseAddress"/>:</p>
                    <ul>
                        <c:forEach items="${deliveryAddresses}"
                                var="deliveryAddress" varStatus="status">
                            <li>
                            	<a class="btn-select-address" href="${request.contextPath}/checkout/single/select-delivery-address/${deliveryAddress.id}">
                                    ${deliveryAddress.type.code}
                                    <small>- ${deliveryAddress.postalCode}</small>
                                </a>
                                <dl class="dlDeliveryAddress" 
                                        style="display: none;">
                                    <dt><spring:theme code="checkout.single.address"/>:</dt>
                                    <dd>
                                        <input type="hidden" class="e-code"
                                                value="${deliveryAddress.id}" >
                                        <span class="e-addr">${deliveryAddress.line1}</span>,
                                        <span class="e-numero">${deliveryAddress.number}</span>
                                        - <span class="e-complemento">${deliveryAddress.complement}</span>
                                        - <span class="e-cidade">${deliveryAddress.town}</span>
                                        - <span class="e-estado">${deliveryAddress.region.name}</span>
                                        <br> <spring:theme code="checkout.single.address.postalCode"/>
                                        	<span id="e-cep">${deliveryAddress.postalCode}</span>
                                    </dd>
                                    <dt><spring:theme code="checkout.single.address.type"/>:</dt>
                                    <dd>
                                        <span id="e-nome">${deliveryAddress.type.code}</span>
                                    </dd>
                                    <dt><spring:theme code="checkout.single.address.reference"/>:</dt>
                                    <dd>${deliveryAddress.reference}</dd>
                                </dl>
                            </li>
                        </c:forEach>
                    </ul>
                    <a href="#" class="btn btn-novo-endereco">
                    	<spring:theme code="checkout.single.address.addNewAddress"/>
                    </a>
                </div>
            </section>
            <single-checkout-hering:newAddress regions="${regions}"
                    country="${country}" page="${page}"
                    suggestedAddresses="${suggestedAddresses}" />
        </div>
    </div>
</c:if>