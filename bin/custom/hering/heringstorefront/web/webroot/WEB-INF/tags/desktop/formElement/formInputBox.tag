<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="idKey" required="true" type="java.lang.String"%>
<%@ attribute name="labelKey" required="true" type="java.lang.String"%>
<%@ attribute name="path" required="true" type="java.lang.String"%>
<%@ attribute name="mandatory" required="false" type="java.lang.Boolean"%>
<%@ attribute name="labelCSS" required="false" type="java.lang.String"%>
<%@ attribute name="inputCSS" required="false" type="java.lang.String"%>
<%@ attribute name="size" required="false" type="java.lang.Integer"%>
<%@ attribute name="tabindex" required="false" rtexprvalue="true"%>
<%@ attribute name="hideInputErrorOnField" required="false" type="java.lang.Boolean"%>
<%@ attribute name="autocomplete" required="false" type="java.lang.String"%>
<%@ attribute name="readOnly" required="false" type="java.lang.Boolean"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ attribute name="placeholder" required="false" type="java.lang.String"%>
<template:errorSpanField path="${path}">
    <ycommerce:testId code="LoginPage_Item_${idKey}">
        <%-- placeholder nao e obrigatorio. caso seja passado esta label nao ira aparecer--%>
        <c:if test="${empty placeholder}">
            <label for="${idKey}"> <spring:theme code="${labelKey}" /> 
            	<%-- 
            	<c:if test="${not empty labelKey && mandatory != null && mandatory == true}">
						*:
				</c:if> 
				<c:if test="${not empty labelKey && (mandatory == null || mandatory == false)}">
						:
				</c:if>
				--%>
            </label>
        </c:if>
        <c:if test="${idKey == 'address.postcode'}">
            <form:input cssClass="${inputCSS}" id="${idKey}" path="${path}" maxlength="${size}" tabindex="${tabindex}" autocomplete="${autocomplete}" required="${mandatory ? 'required' : ''}" readonly="${readOnly}"/>
        </c:if>
        <c:if test="${idKey == 'profile.birthday'}">
            <form:input cssClass="${inputCSS}" id="${idKey}" path="${path}" maxlength="10" tabindex="${tabindex}" autocomplete="${autocomplete}" required="${mandatory ? 'required' : ''}" readonly="${readOnly}"/>
        </c:if>
        <%-- 
        <c:if test="${idKey == 'address.phone1' || idKey == 'address.phone2'}">
            <form:input cssClass="${inputCSS}" id="${idKey}" path="${path}" maxlength="11" tabindex="${tabindex}" autocomplete="${autocomplete}" required="${mandatory ? 'required' : ''}" readonly="${readOnly}"/>
        </c:if>
        --%>
        <c:if test="${idKey != 'address.postcode' && idKey != 'profile.birthday'}">
            <c:if test="${not empty placeholder}">
                <form:input cssClass="${inputCSS}" id="${idKey}" path="${path}" placeholder="${placeholder}" maxlength="${size}" tabindex="${tabindex}" autocomplete="${autocomplete}" required="${mandatory ? 'required' : ''}" readonly="${readOnly}" />
            </c:if>
            <c:if test="${empty placeholder}">
                <form:input cssClass="${inputCSS}" id="${idKey}" path="${path}" maxlength="${size}" tabindex="${tabindex}" autocomplete="${autocomplete}" required="${mandatory ? 'required' : ''}" readonly="${readOnly}" />
            </c:if>
        </c:if>
        <c:if test="${!hideInputErrorOnField}">
            <span class="skip"><form:errors path="${path}" /></span>
        </c:if>
    </ycommerce:testId>
</template:errorSpanField>
