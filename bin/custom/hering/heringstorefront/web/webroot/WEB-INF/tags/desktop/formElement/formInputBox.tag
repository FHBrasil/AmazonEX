<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="idKey" required="true" type="java.lang.String" %>
<%@ attribute name="labelKey" required="true" type="java.lang.String" %>
<%@ attribute name="path" required="true" type="java.lang.String" %>
<%@ attribute name="mandatory" required="false" type="java.lang.Boolean" %>
<%@ attribute name="labelCSS" required="false" type="java.lang.String" %>
<%@ attribute name="inputCSS" required="false" type="java.lang.String" %>
<%@ attribute name="size" required="false" type="java.lang.Integer" %>
<%@ attribute name="tabindex" required="false" rtexprvalue="true" %>
<%@ attribute name="hideInputErrorOnField" required="false" type="java.lang.Boolean" %>
<%@ attribute name="autocomplete" required="false" type="java.lang.String" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ attribute name="placeholder" required="false" type="java.lang.String" %> 



<c:if test="${themeName == 'black'}">
<template:errorSpanField path="${path}">
	<ycommerce:testId code="LoginPage_Item_${idKey}">

	<label class="control-label ${labelCSS}" for="${idKey}">
		<spring:theme code="${labelKey}"/>
		<c:if test="${mandatory != null && mandatory == true && idKey != 'j_username'}">
			<span class="mandatory">
				*
			</span>
		</c:if>
		<span class="skip"><form:errors path="${path}"/></span>
	</label>
		
	<div class="controls">
		<form:input cssClass="${inputCSS} ${mandatory ? 'required' : '' }"
                id="${idKey}" path="${path}" maxlength="${size}"
                tabindex="${tabindex}" autocomplete="${autocomplete}"/>		
	</div>

	</ycommerce:testId>
</template:errorSpanField>
</c:if>

<c:if test="${themeName == 'hering' || themeName == 'dzarm' || themeName == 'foryou'}">
	<template:errorSpanField path="${path}">
		<ycommerce:testId code="LoginPage_Item_${idKey}">
			<%-- placeholder nao e obrigatorio. caso seja passado esta label nao ira aparecer--%>
			<c:if test="${empty placeholder}">
				<label for="${idKey}">
					<spring:theme code="${labelKey}"/>
					<c:if test="${not empty labelKey && mandatory != null && mandatory == true}">
						*:
					</c:if>
					<c:if test="${not empty labelKey && (mandatory == null || mandatory == false)}">
						:
					</c:if>
					
				</label>
			</c:if>
			<c:if test="${idKey == 'address.postcode'}">
				<form:input cssClass="${inputCSS} ${mandatory ? 'required' : ''}" id="${idKey}" path="${path}" maxlength="${size}"  tabindex="${tabindex}" autocomplete="${autocomplete}"/>
			</c:if>
			<c:if test="${idKey == 'profile.birthday'}">
				<form:input cssClass="${inputCSS} ${mandatory ? 'required' : ''}" id="${idKey}" path="${path}" maxlength="10" tabindex="${tabindex}" autocomplete="${autocomplete}"/>
			</c:if>
			<c:if test="${idKey == 'address.phone1' || idKey == 'address.phone2'}">
				<form:input cssClass="${inputCSS} ${mandatory ? 'required' : ''}" id="${idKey}" path="${path}" maxlength="11" tabindex="${tabindex}" autocomplete="${autocomplete}"/>
			</c:if>
			<c:if test="${idKey != 'address.postcode' && idKey != 'address.phone1' && idKey != 'address.phone2' && idKey != 'profile.birthday'}">
				<c:if test="${not empty placeholder}">
					<form:input cssClass="${inputCSS} ${mandatory ? 'required' : ''}" id="${idKey}" path="${path}" placeholder="${placeholder}" maxlength="${size}" tabindex="${tabindex}" autocomplete="${autocomplete}"/>
				</c:if>
				<c:if test="${empty placeholder}">
					<form:input cssClass="${inputCSS} ${mandatory ? 'required' : ''}" id="${idKey}" path="${path}" maxlength="${size}" tabindex="${tabindex}" autocomplete="${autocomplete}"/>
				</c:if>
			</c:if>
			<c:if test="${!hideInputErrorOnField}">
				<span class="skip"><form:errors path="${path}"/></span>
			</c:if>
		</ycommerce:testId>
	</template:errorSpanField>
</c:if>