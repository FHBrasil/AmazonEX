<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="ycommerce" uri="/WEB-INF/tld/ycommercetags.tld" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<spring:url value="/checkout/single/summary/createUpdateDeliveryAddress.json" var="createUpdateDeliveryAddressUrl" />

<div class="title_holder">
	<div class="title">
		<div class="title-top">
			<span></span>
		</div>
	</div>

	<h2><spring:theme code="checkout.summary.deliveryAddress"/></h2>
</div>

<div class="item_container">
	<p><spring:theme code="checkout.summary.deliveryAddress.useForNewAddress"/></p>

	<p><spring:theme code="address.required"/></p>

	<form:form method="post" commandName="addressForm" action="${createUpdateDeliveryAddressUrl}" class="create_update_address_form" >
		<dl>
			<form:hidden path="addressId" class="create_update_address_id" status="${not empty createUpdateStatus ? createUpdateStatus : ''}"/>
			<form:hidden path="phone"/>
			<c:if test="${showPNO}">			
				<strong><div id="pno_klarna_link_to_invoice"></div></strong>		
	
				<template:errorSpanField path="pno">
						<ycommerce:testId code="LoginPage_Item_address.pno">
							<dt>
								<label class="" for="address.pno">
									<spring:theme code="address.pno"/>
									<c:if test="${true}">
										<span class="mandatory">
											<spring:theme code="login.required" var="loginrequiredText" />
											<img width="5" height="6" alt="" title="" src="${commonResourcePath}/images/mandatory.gif">
										</span>
									</c:if>
									<c:choose>
										<c:when test="${currentKlarnaCountryIsoCode eq 'SE'}">
											<spring:theme code="address.pno.se"/>
										</c:when>
										<c:when test="${currentKlarnaCountryIsoCode eq 'NO'}">
											<spring:theme code="address.pno.no"/>
										</c:when>
										<c:when test="${currentKlarnaCountryIsoCode eq 'DK'}">
											<spring:theme code="address.pno.dk"/>
										</c:when>
										<c:when test="${currentKlarnaCountryIsoCode eq 'FI'}">
											<spring:theme code="address.pno.fi"/>
										</c:when>
									</c:choose>
									
									<span class="skip"><form:errors path="pno"/></span>
								</label>
							</dt>							
							<dd>
								<form:input cssClass="text" id="address.pno" path="pno"/>
							</dd>
						</ycommerce:testId>
				</template:errorSpanField>
			</c:if>
			<c:if test="${showGetAddress}">
				<button class="form" name="fetch"><spring:theme code="address.getaddress.label"/></button><br />
			</c:if>
			<formElement:formSelectBox idKey="address.title" labelKey="address.title" path="titleCode" mandatory="true" skipBlank="false" skipBlankMessageKey="address.title.pleaseSelect" items="${titles}"/>
			<formElement:formInputBox idKey="address.firstName" labelKey="address.firstName" path="firstName" inputCSS="text" mandatory="true"/>
			<formElement:formInputBox idKey="address.surname" labelKey="address.surname" path="lastName" inputCSS="text" mandatory="true"/>
			<c:if test="${numberInStreet}">
				<formElement:formInputBox idKey="address.streetAndHousenumber" labelKey="address.streetAndHousenumber" path="line1" inputCSS="text" mandatory="true"/>
			</c:if>
			<c:if test="${not numberInStreet}">
				<formElement:formInputBox idKey="address.street" labelKey="address.street" path="line1" inputCSS="text" mandatory="true"/>
				<formElement:formInputBox idKey="address.housenumber" labelKey="address.housenumber" path="line2" inputCSS="text" mandatory="false"/>
				<c:if test="${showHouseExtension}">
					<formElement:formInputBox idKey="address.houseExtension" labelKey="address.houseExtension" path="houseExtension" inputCSS="text" mandatory="false"/>
				</c:if>
			</c:if>
			<formElement:formInputBox idKey="address.townCity" labelKey="address.townCity" path="townCity" inputCSS="text" mandatory="true"/>
			<formElement:formInputBox idKey="address.postcode" labelKey="address.postcode" path="postcode" inputCSS="text" mandatory="true"/>
			<formElement:formSelectBox idKey="address.country" labelKey="address.country" path="countryIso" mandatory="true" skipBlank="false" skipBlankMessageKey="address.selectCountry" items="${countries}" itemValue="isocode"/>

			<form:hidden path="shippingAddress"/>
			<form:hidden path="billingAddress"/>
			<c:if test="${!edit}">
				<dt class="left">
					<form:checkbox id="SaveAddress" path="saveInAddressBook"/>
					<label for="SaveAddress"><spring:theme code="checkout.summary.deliveryAddress.saveAddressInMyAddressBook"/></label>
				</dt>
				<dd></dd>
			</c:if>
		</dl>

		<span style="display: block; clear: both;">
			<button class="form"><spring:theme code="${edit ? 'checkout.summary.deliveryAddress.saveAndUseThisAddress' : 'checkout.summary.deliveryAddress.useThisAddress'}"/></button>
		</span>
	</form:form>
</div>
