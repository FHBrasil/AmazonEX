<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="idKey" required="true"  type="java.lang.String" %>
<%@ attribute name="labelKey" required="true"  type="java.lang.String" %>
<%@ attribute name="path" required="true"  type="java.lang.String" %>
<%@ attribute name="mandatory" required="false"  type="java.lang.Boolean" %>
<%@ attribute name="labelCSS" required="false"  type="java.lang.String" %>
<%@ attribute name="areaCSS" required="false"  type="java.lang.String" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>


 
<c:if test="${themeName == 'black'}">
<template:errorSpanField path="${path}">

		<label for="${idKey}">
			<spring:theme code="${labelKey}"/>
			<c:if test="${mandatory != null && mandatory == true}">
				<span class="mandatory">
					*
				</span>
			</c:if>
			<span class="skip"><form:errors path="${path}"/></span>
		</label>
		<div class="controls">
			<form:textarea cssClass="${areaCSS}" id="${idKey}" path="${path}" />
		</div>

</template:errorSpanField>
</c:if>

<c:if test="${themeName == 'hering' || themeName == 'dzarm' || themeName == 'foryou'}">
	<template:errorSpanField path="${path}">
		
		<label for="${idKey}">
			<spring:theme code="${labelKey}"/>
			<c:if test="${mandatory != null && mandatory == true}">
				*:				
			</c:if>
			<c:if test="${mandatory == null || mandatory == false}">
				:
			</c:if>
			
		</label>
		<form:textarea cssClass="${areaCSS} ${mandatory ? 'required' : ''}" id="${idKey}" path="${path}" />
		
	</template:errorSpanField>
</c:if>
