<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb"%>
<template:page pageTitle="${pageTitle}">
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
            <section class="orders page with-sidebar">
                <nav:accountNav />
                <div class="right">
                    <section id="acc-profile">
                        <header>
                            <h2>
                                <spring:theme code="text.account.myProfile" text="My Profile" />
                            </h2>
                        </header>
                        <form:form action="update-profile" method="post"
                            commandName="updateProfileForm">
                            <section id="profile-info">
                                <ul>
                                    <li><span class="label"><spring:theme
                                                code="${pf?'profile.firstName':'register.razaoSocial'}" />:</span><span>${fn:escapeXml(customerData.firstName)}</span></li>
                                    <li><span class="label"><spring:theme
                                                code="${pf?'profile.lastName':'register.nomeFantasia'}" />:</span><span>${fn:escapeXml(customerData.lastName)}</span></li>
                                    <c:if test="${pf}">
                                        <li><span class="label"><spring:theme code="text.fliegercommerce.texto33"/></span><span><spring:theme
                                                    code="profile.gender.${fn:escapeXml(customerData.gender)}" /></span></li>
                                    </c:if>
                                    <li><span class="label"><spring:theme
                                                code="${pf?'profile.birthday':'register.dataFundacao'}" />:</span><span><fmt:formatDate
                                                pattern="dd/MM/yyyy"
                                                value="${customerData.birthday}" /></span></li>
                                    <li><span class="label"><spring:theme
                                                code="register.${pf?'cpf':'cnpj'}" />:</span><span>${fn:escapeXml(customerData.cpfcnpj)}</span></li>
                                    <c:if test="${not pf}">
                                        <li><span class="label"><spring:theme code="text.fliegercommerce.texto36"/></span><span>${fn:escapeXml(customerData.rgIe)}</span></li>
                                        <li><span class="label"><spring:theme code="text.fliegercommerce.texto37"/></span><span>${fn:substring(customerData.ufIe, 3, 5)}</span></li>
                                    </c:if>
                                    <li><span class="label">E-mail:</span><span>${fn:escapeXml(customerData.displayUid)}</span></li>
                                    <li><span class="label"><spring:theme code="text.fliegercommerce.texto38"/></span>
                                        
                                </ul>
                                <div class="btn-group">
                                    <a href="update-password" class="btn"><spring:theme code="text.fliegercommerce.texto39"/></a> 
                                    <a href="update-profile" class="btn"> <spring:theme code="text.fliegercommerce.texto40"/> </a> 
                                    <a href="update-email" class="btn"><spring:theme code="text.fliegercommerce.texto41"/></a>
									<!-- testing delete account -->
                                    <a href="delete-account" class="btn"><spring:theme code="text.fliegercommerce.texto131"/></a>
                                </div>
                            </section>
                        </form:form>
                    </section>
                </div>
            </section>
        </div>
    </div>
</template:page>
