<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/desktop/user"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav"%>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb"%>
<template:page pageTitle="${pageTitle}">
    <%-- globalMessages.tag --%>
    <common:globalMessages />
    <cms:pageSlot position="SideContent" var="feature" element="div">
        <cms:component component="${feature}" />
    </cms:pageSlot>
    <cms:pageSlot position="TopContent" var="feature" element="div">
        <cms:component component="${feature}" />
    </cms:pageSlot>
    <div class="container">
        <section class="identificacao page">
            <div class="col-sm-4">
                <%-- register.tag --%>
                <sec:authorize ifAnyGranted="ROLE_ANONYMOUS">
		            <div class="right">
		                <c:url value="/login/checkout/guest" var="guestCheckoutUrl" />
		                <user:guestCheckout actionNameKey="checkout.login.guestCheckout"
		                      action="${guestCheckoutUrl}" /> 
		            </div>
        		</sec:authorize>
<%--                 <c:url value="/login/register" var="registerActionUrl" /> --%>
<%--                 <user:register actionNameKey="register.submit" action="${registerActionUrl}" /> --%>
            </div>
            <div class="col-sm-4">
                <%-- login.tag --%>
                <c:url value="/login/loginOver" var="loginActionUrl" />
                <user:login actionNameKey="login.login" action="${loginActionUrl}" />
            </div>
            <div class="col-sm-4">
                <%-- loginBanner.tag --%>
                <user:loginBanner />
            </div>
        </section>
    </div>
</template:page>
