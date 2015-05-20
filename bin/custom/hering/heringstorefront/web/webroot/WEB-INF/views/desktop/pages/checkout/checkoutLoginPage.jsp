<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="user" tagdir="/WEB-INF/tags/desktop/user"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<template:page pageTitle="${pageTitle}">
    <div id="globalMessages">
        <common:globalMessages />
    </div>
    <cms:pageSlot position="TopContent" var="feature" element="div"
        class="span-24 cms_disp-img_slot">
        <cms:component component="${feature}" />
    </cms:pageSlot>
    <header id="page-header">
        <h1>Identificaï¿½ï¿½o</h1>
    </header>
    <section class="identificacao-checkout page">
        <%-- REGISTER --%>
        <%-- register.tag --%>
        <c:url value="/login/checkout/register" var="registerAndCheckoutActionUrl" />
        <user:register actionNameKey="checkout.login.registerAndCheckout"
            action="${registerAndCheckoutActionUrl}" />
        <section class="col-2 column">
            <div class="container half">
                <%-- LOGIN --%>
                <%-- login.tag --%>
                <div class="left">
                    <c:url value="/checkout/j_spring_security_check" var="loginAndCheckoutActionUrl" />
                    <user:login actionNameKey="checkout.login.loginAndCheckout"
                        action="${loginAndCheckoutActionUrl}" />
                </div>
                <%-- GUEST REGISTER --%>
                <%-- guestCheckout.tag --%>
                <c:if test="false">
                    <sec:authorize ifAnyGranted="ROLE_ANONYMOUS">
                        <div class="right">
                            <c:url value="/login/checkout/guest" var="guestCheckoutUrl" />
                            <user:guestCheckout actionNameKey="checkout.login.guestCheckout"
                                action="${guestCheckoutUrl}" />
                        </div>
                    </sec:authorize>
                </c:if>
            </div>
            <div class="banner">
                <img src="/store/_ui/desktop/theme-${themeName}/images/identificacao-banner-wide.jpg">
                <p>Faça o seu cadastro e aproveite as vantagens em ser o nosso cliente.</p>
                <p>
                    <strong>Seguranï¿½a:</strong> Site 100% seguro.
                </p>
            </div>
        </section>
    </section>
</template:page>
