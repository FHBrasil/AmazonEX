<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav"%>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/desktop/order"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<template:page pageTitle="${pageTitle}">
    <div id="main-wrapper">
        <div>
            <header id="page-header">
                <h1>
                    <spring:theme code="text.account.yourAccount" text="Your Account" />
                </h1>
                <div class="breadcrumb">
                    <breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}" />
                </div>
            </header>
            <section class="order-details page with-sidebar">
                <nav:accountNav />
                <div class="right">
                    <section id="general-info" class="section-block">
                        <header>
                            <h2><spring:theme code="text.fliegercommerce.texto109"/></h2>
                        </header>
                        <div class="container">
                            <section class="review left">
                                <div class="left">
                                    <p>
                                        <spring:theme code="text.account.order.orderNumber"
                                            arguments="${orderData.code}" />
                                    </p>
                                    <p>
                                        <spring:theme code="text.account.order.orderPlaced"
                                            arguments="${orderData.created}" />
                                    </p>
                                    <c:if test="${not empty orderData.statusDisplay}">
                                        <p>
                                            <spring:theme
                                                code="text.account.order.status.display.${orderData.statusDisplay}"
                                                var="orderStatus" />
                                            <spring:theme code="text.account.order.orderStatus"
                                                arguments="${orderStatus}" />
                                            <br />
                                        </p>
                                    </c:if>
                                </div>
                                <c:if test="${not empty orderHistoryPreview}">
                                    <c:forEach items="${orderHistoryPreview}" var="orderHistory">
                                        <c:if test="${orderHistory.code == orderData.code}">
                                            <order:paymentDetailsItem order="${orderData}"
                                                orderHistory="${orderHistory}" />
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                            </section>
                            <c:if test="${not empty orderHistoryPreview}">
                                <c:forEach items="${orderHistoryPreview}" var="orderHistory">
                                    <c:if test="${orderHistory.code == orderData.code}">
                                        <order:orderTotalsItem order="${orderData}"
                                            orderHistory="${orderHistory}" />
                                    </c:if>
                                </c:forEach>
                            </c:if>
                        </div>
                    </section>
                    <order:deliveryAddressItem order="${orderData}" />
                    <order:deliveryMethodItem order="${orderData}" />
                    <order:billingAddressItem order="${orderData}" />
                    <c:if test="${not empty orderData.unconsignedEntries}">
                        <order:orderUnconsignedEntries order="${orderData}" page='orderPage' />
                    </c:if>
                </div>
            </section>
        </div>
    </div>
</template:page>
