<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/desktop/user"%>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url value="/register/confirm-associate-account" var="urlAssociateAccount"/>


<template:page pageTitle="${pageTitle}">

	<div id="breadcrumb" class="breadcrumb">
		<breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}" />
	</div>
	
	<div id="globalMessages">
		<common:globalMessages />
	</div>
		
	<div class="clearfix">
	
		<form:form id="amazonForm" name="amazonForm" action="${urlAssociateAccount}" method="post" commandName="amazonLoginForm">
		
		<label for="amazon.login.associate.account">
				<spring:theme code="amazon.login.associate.account"/>
		</label>
		
		<br />
		
		<label for="amazon.login.already.exists">
				<spring:theme code="amazon.login.already.exists"/>
		</label>
		
		<br />
		
		<label for="amazon.login.already.exists.enter.password">
				<spring:theme code="amazon.login.already.exists.enter.password"/>
		</label>

		<br />
			<form:hidden path="amazonId"/>
			<form:hidden path="name"/>
			
			<label for="amazon.login.email" class="required">
				<spring:theme code="amazon.login.email"/>
			</label>
			
			<br />
			
			<form:input idKey="email" path="email" inputCSS="text password" mandatory="true" />
			
			<br />
			
			<label for="amazon.login.pwd" class="required">
				<spring:theme code="amazon.login.pwd"/>
			</label>
			
			<br />

			<form:password idKey="pwd" path="pwd" inputCSS="text password" mandatory="true" />
				
			<div class="form-actions">
				<button class="positive" id="matchAmazonAccount" type="button">
					<spring:theme code="text.amazon.associate.account"
						text="Associate Account" />
				</button>
				<button class="positive" id="noMatchAmazonAccount" type="button">
					<spring:theme code="text.amazon.no.associate.account"
						text="No Merge" />
				</button>
			</div>
		</form:form>
	</div>
</template:page>
