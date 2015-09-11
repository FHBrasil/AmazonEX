<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb"%>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/desktop/order"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
                    <section id="orders-list">
                        <header>
                            <h2>
                                <spring:theme code="text.account.orderHistory.myOrders"
                                    text="My Orders" />
                            </h2>
                            <div id="search-bar">
                                <form:form class="pesquisa-numero">
                                    <label for="pesquisa-numero"> <spring:theme
                                            code="text.account.orderHistory.searchByNumber"
                                            text="Search By Number" /><br> <input type="text"
                                        id="pesquisa-numero" name="orderSearch" class="required">
                                        <button type="submit" class="btn btn-ok">ok</button>
                                    </label>
                                </form:form>
                            </div>
                        </header>
                        <table>
                            <colgroup>
                                <col class="col-item-toggle-button">
                            </colgroup>
                            <thead>
                                <tr>
                                    <th><spring:theme
                                            code="text.account.orderHistory.orderNumber"
                                            text="Number of Order" /></th>
                                    <th><spring:theme
                                            code="text.account.orderHistory.orderDate"
                                            text="Date of Order" /></th>
                                    <th><spring:theme
                                            code="text.account.orderHistory.orderTotalPrice"
                                            text="Total Price" /></th>
                                    <th><spring:theme code="text.account.formOfPayment"
                                            text="Form of Payment" /></th>
                                    <th><spring:theme
                                            code="text.account.orderHistory.orderStatus"
                                            text="Status" /></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:if test="${not empty searchPageData.results && empty orderFind}">
                                    <nav:pagination top="true"
                                        supportShowPaged="${isShowPageAllowed}"
                                        supportShowAll="${isShowAllAllowed}"
                                        searchPageData="${searchPageData}"
                                        searchUrl="/my-account/orders?sort=${searchPageData.pagination.sort}"
                                        msgKey="text.account.orderHistory.page" orderHistory="true"
                                        numberPagesShown="${numberPagesShown}" />
                                    <c:forEach items="${searchPageData.results}" var="order">
                                        <c:url value="/my-account/order/${order.code}"
                                            var="myAccountOrderDetailsUrl" />
                                        <tr class="has-sub-item" data-sub-item="${order.code}">
                                            <td><a href="#" class="sub-item-toggle"></a> <big>${order.code}</big>
                                            </td>
                                            <td><fmt:formatDate value="${order.placed}"
                                                    dateStyle="short" type="date" /><br> �s <fmt:formatDate
                                                    value="${order.placed}" timeStyle="short"
                                                    type="time" /></td>
                                            <td><big>${order.total.formattedValue}</big></td>
                                            <c:forEach items="${orderData}" var="orderDataCustomer">
                                                <c:if test="${order.code == orderDataCustomer.code}">
                                                    <td>${order.paymentMode}<%-- <order:paymentDetailsItem order="${orderDataCustomer}"/> --%>
                                                    </td>
                                                </c:if>
                                            </c:forEach>
                                            <td><spring:theme
                                                    code="text.account.order.status.display.${order.statusDisplay}" /><br>
                                                <c:if test="${order.deliveryMode == 'TFA'}">
                                                    <spring:theme
                                                        code="text.account.orderHistory.orderTracking"
                                                        text="Tracking" />
                                                    <a class="tracking"
                                                        href="http://www.transfolha.com.br/outros/pesquisahttpentrega.asp?scliente=000539&schave=6b7e676029&spesquisa=generico&sdado=${order.trackingID}"
                                                        target="_blank"> ${order.trackingID} </a>
                                                </c:if> <c:if test="${order.deliveryMode == 'CORREIOS'}">
                                                    <spring:theme
                                                        code="text.account.orderHistory.orderTracking"
                                                        text="Tracking" />
                                                    <a class="tracking"
                                                        href="http://websro.correios.com.br/sro_bin/txect01$.querylist?p_lingua=001&p_tipo=001&p_cod_uni=${order.trackingID}"
                                                        target="_blank"> ${order.trackingID} </a>
                                                </c:if> <c:if test="${order.deliveryMode == 'JTT'}">
                                                    <spring:theme
                                                        code="text.account.orderHistory.orderTracking"
                                                        text="Tracking" />
                                                    <a class="tracking"
                                                        href="http://www.jttlog.com.br/consulta_nf.php?nf=${order.trackingID}"
                                                        target="_blank"> <%-- ${order.trackingID} --%>
                                                    </a>
                                                </c:if></td>
                                        </tr>
                                        <c:forEach items="${orderData}" var="orderDataCustomer">
                                            <c:if test="${order.code == orderDataCustomer.code}">
                                                <order:orderUnconsignedEntries
                                                    order="${orderDataCustomer}"
                                                    searchPageData="${order}" page="historyPage" />
                                            </c:if>
                                        </c:forEach>
                                    </c:forEach>
                                </c:if>
                                <c:if
                                    test="${empty searchPageData.results && not empty orderFind && orderFind != 'notFound'}">
                                    <c:url value="/my-account/order/${orderFind.code}"
                                        var="myAccountOrderDetailsUrl" />
                                    <tr class="has-sub-item" data-sub-item="${orderFind.code}">
                                        <td><a href="#" class="sub-item-toggle"></a> <big>${orderFind.code}</big>
                                        </td>
                                        <td><fmt:formatDate value="${orderFind.placed}"
                                                dateStyle="short" type="date" /><br> �s <fmt:formatDate
                                                value="${orderFind.placed}" timeStyle="short"
                                                type="time" /></td>
                                        <td><big>${orderFind.total.formattedValue}</big></td>
                                        <c:forEach items="${orderData}" var="orderDataCustomer">
                                            <c:if test="${orderFind.code == orderDataCustomer.code}">
                                                <td>${orderFind.paymentMode}<%-- <order:paymentDetailsItem order="${orderDataCustomer}"/> --%>
                                                </td>
                                            </c:if>
                                        </c:forEach>
                                        <td><spring:theme
                                                code="text.account.order.status.display.${orderFind.statusDisplay}" /><br>
                                            <c:if test="${orderFind.deliveryMode == 'TFA'}">
                                                <spring:theme
                                                    code="text.account.orderHistory.orderTracking"
                                                    text="Tracking" />
                                                <a class="tracking"
                                                    href="http://www.transfolha.com.br/outros/pesquisahttpentrega.asp?scliente=000539&schave=6b7e676029&spesquisa=generico&sdado=${orderFind.trackingID}"
                                                    target="_blank"> ${orderFind.trackingID} </a>
                                            </c:if> <c:if test="${orderFind.deliveryMode == 'CORREIOS'}">
                                                <spring:theme
                                                    code="text.account.orderHistory.orderTracking"
                                                    text="Tracking" />
                                                <a class="tracking"
                                                    href="http://websro.correios.com.br/sro_bin/txect01$.querylist?p_lingua=001&p_tipo=001&p_cod_uni=${orderFind.trackingID}"
                                                    target="_blank"> ${orderFind.trackingID} </a>
                                            </c:if> <c:if test="${orderFind.deliveryMode == 'JTT'}">
                                                <spring:theme
                                                    code="text.account.orderHistory.orderTracking"
                                                    text="Tracking" />
                                                <a class="tracking"
                                                    href="http://www.jttlog.com.br/consulta_nf.php?nf=${orderFind.trackingID}"
                                                    target="_blank"> <%-- ${orderFind.trackingID} --%>
                                                </a>
                                            </c:if></td>
                                    </tr>
                                    <c:forEach items="${orderData}" var="orderDataCustomer">
                                        <c:if test="${orderFind.code == orderDataCustomer.code}">
                                            <order:orderUnconsignedEntries
                                                order="${orderDataCustomer}"
                                                searchPageData="${orderFind}" page="historyPage" />
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                            </tbody>
                        </table>
                        <c:if test="${empty searchPageData.results && orderFind == 'notFound'}">
                            <common:globalMessages />
                        </c:if>
                        <c:if test="${not empty searchPageData.results && empty orderFind}">
                            <div style="margin: 3% 0 0 45%;">
                                <nav:pagination top="false" supportShowPaged="${isShowPageAllowed}"
                                    supportShowAll="${isShowAllAllowed}"
                                    searchPageData="${searchPageData}"
                                    searchUrl="/my-account/orders?sort=${searchPageData.pagination.sort}"
                                    msgKey="text.account.orderHistory.page" orderHistory="true"
                                    numberPagesShown="${numberPagesShown}" />
                            </div>
                        </c:if>
                    </section>
                </div>
            </section>
        </div>
    </div>
</template:page>
