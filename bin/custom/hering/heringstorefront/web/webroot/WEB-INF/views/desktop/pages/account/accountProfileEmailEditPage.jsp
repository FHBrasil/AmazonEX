<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav"%>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<c:url var="profileUrl" value="/my-account/profile" />
<template:page pageTitle="${pageTitle}">
    <div id="globalMessages">
        <common:globalMessages />
    </div>
    <div id="main-wrapper">
        <div class="container">
            <header id="page-header">
                <h1>
                    <spring:theme code="text.account.yourAccount" text="Your Account" />
                </h1>
                <div class="breadcrumb">
                    <breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}" />
                </div>
            </header>
            <section class="account-edit-email page with-sidebar">
                <nav:accountNav />
                <div class="right">
                    <section id="acc-email-form">
                        <header>
                            <h2>ALTERAR E-MAIL DO CADASTRO</h2>
                        </header>
                        <form:form action="update-email" method="post" commandName="updateEmailForm">
                            <div class="form-row">
                                <formElement:formInputBox idKey="profile.email"
                                    labelKey="profile.emailNew" path="email" inputCSS="text"
                                    mandatory="true" />
                            </div>
                            <div class="form-row">
                                <formElement:formInputBox idKey="profile.checkEmail"
                                    labelKey="profile.checkEmailNew" path="chkEmail" inputCSS="text"
                                    mandatory="true" />
                            </div>
                            <div class="form-row">
                                <formElement:formPasswordBox idKey="profile.pwd"
                                    labelKey="profile.pwd" path="password" inputCSS="text"
                                    mandatory="true" />
                            </div>
                            <div class="required-info">
                                <spring:theme code="form.required"
                                    text="Fields marked * are required" />
                            </div>
                            <div class="btn-group">
                                <button type="button" class="btn btn-cancelar"
                                    onclick="window.location='${profileUrl}'">
                                    <spring:theme code="text.account.profile.cancel" text="Cancel" />
                                </button>
                                <ycommerce:testId code="myAccount_profile_SaveUpdates_button">
                                    <button class="btn btn-confirmar" type="submit">
                                        <spring:theme code="text.account.profile.saveUpdates"
                                            text="Save Updates" />
                                    </button>
                                </ycommerce:testId>
                            </div>
                        </form:form>
                    </section>
                </div>
            </section>
        </div>
    </div>
</template:page>
