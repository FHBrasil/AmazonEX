<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="idKey" required="true" type="java.lang.String"%>
<%@ attribute name="labelKey" required="true" type="java.lang.String"%>
<%@ attribute name="path" required="true" type="java.lang.String"%>
<%@ attribute name="mandatory" required="false" type="java.lang.Boolean"%>
<%@ attribute name="labelCSS" required="false" type="java.lang.String"%>
<%@ attribute name="inputCSS" required="false" type="java.lang.String"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<template:errorSpanField path="${path}">
    <spring:theme code="${idKey}" var="themeIdKey" />
    <label for="${themeIdKey}"> <form:checkbox value="${checked}" id="${themeIdKey}"
            path="${path}" /> <spring:theme code="${labelKey}" /> <c:if
            test="${mandatory != null && mandatory == true}">
            <span class="mandatory"> *: </span>
        </c:if>
    </label>
    <span class="skip"><form:errors path="${path}" /></span>
</template:errorSpanField>
