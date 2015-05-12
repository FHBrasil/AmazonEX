<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<c:url var="profileUrl" value="/my-account/profile" />
<template:page pageTitle="${pageTitle}">

	<div id="breadcrumb" class="breadcrumb">
		<breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}"/>
	</div>

	<div id="globalMessages">
		<common:globalMessages/>
	</div>
	<nav:accountNav selected="profile" />
	<div class="column accountContentPane clearfix">
		<div class="headline"><spring:theme code="text.account.profile" text="Profile"/></div>		
		
		<form:form action="profile-facebook" method="post" commandName="heringUpdateProfileForm">${profile.subscribeNewsletter }
			<formElement:formInputBox idKey="profile.firstName" labelKey="profile.firstName" path="firstName" inputCSS="text" mandatory="true"/>
			<formElement:formInputBox idKey="profile.lastName" labelKey="profile.lastName" path="lastName" inputCSS="text" mandatory="true"/>
            <formElement:formInputBox idKey="profile.cpfcnpj"  labelKey="profile.cpfcnpj" path="cpfcnpj" inputCSS="text" mandatory="true"/>
			<formElement:formInputBox idKey="profile.birthday" labelKey="profile.birthday" path="birthday" inputCSS="text" mandatory="false"/>
			<formElement:formCheckbox idKey="profile.subscribeNewsletter" labelKey="profile.subscribeNewsletter" path="subscribeNewsletter"/>
			<span class="sexo">Sexo</span>
			<fieldset>
			<form:radiobutton idKey="register.gender" labelKey="register.gender" path="gender" value="FEMALE" /><spring:theme code="profile.gender.FEMALE"
				text="Feminino" />
			<form:radiobutton idKey="register.gender" labelKey="register.gender" path="gender" value="MALE" /><spring:theme code="profile.gender.MALE"
				text="Masculino" />
			</fieldset>
			<div class="obrigatorio required"><spring:theme code="form.required" text="Fields marked * are required"/></div>
			<div class="form-actions">
				<button type="button" class="negative bt-cancelar" onclick="window.location='${profileUrl}'"><spring:theme code="text.account.profile.cancel" text="Cancel"/></button>
				<ycommerce:testId code="profilePage_SaveUpdatesButton">
					<button class="positive" type="submit"><spring:theme code="text.account.profile.saveUpdates" text="Save Updates"/></button>
				</ycommerce:testId>
			</div>
		</form:form>
		
	</div>
	

</template:page>

