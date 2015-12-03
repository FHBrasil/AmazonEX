<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/desktop/user"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url value="/register/manual-addition" var="manualAddition"/>

<template:page pageTitle="${pageTitle}">

	<div id="globalMessages">
		<common:globalMessages />
	</div>
		
	<div class="clearfix">
	
		<form:form id="manualAdditionForm" name="manualAdditionForm" action="${manualAddition}" method="post" commandName="amazonManualAdditionFirstLoginForm">
		
		<form:hidden path="email"/>
		
		<label for="amazon.manual.addition.first.login.salutation">
				<spring:theme code="amazon.manual.addition.first.login.salutation"/>
		</label>
		
		<br />
		
		<form:select path="salutation">
			<c:forEach items="${titles}" var="title">
				<form:option value="${title.code}">${title.name}</form:option>
			</c:forEach>
		</form:select>
			
		<br />
			
		<div>
			<spring:theme code="amazon.manual.addition.first.login.save" var="submitText"/>
			<input type="submit" id="matchAmazonAccount" value="${submitText}"/>
		</div>
		
		<br />
		
		</form:form>
	</div>
</template:page>