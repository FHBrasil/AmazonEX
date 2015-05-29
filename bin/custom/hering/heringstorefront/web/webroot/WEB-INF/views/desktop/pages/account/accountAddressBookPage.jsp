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
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
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
            <section class="account-edit-addresses page with-sidebar">
                <nav:accountNav />
                <div class="right">
                    <header>
                        <h2><spring:theme code="text.fliegercommerce.texto14"/></h2>
                    </header>
                    <section id="account-addresses">
                        <div id="globalMessages">
                            <common:globalMessages />
                        </div>
                        <ycommerce:testId code="addressBook_addNewAddress_button">
                            <div class="newAddressButton">
                                <a href="add-address" class="btn btn-enderecos"> <spring:theme
                                        code="text.account.addressBook.addAddress"
                                        text="Add new address" />
                                </a>
                            </div>
                        </ycommerce:testId>
                        <c:choose>
                            <c:when test="${not empty addressData}">
                                <ul>
                                    <c:forEach items="${addressData}" var="address">
                                        <li><c:if test="${address.defaultAddress}">
                                                <ycommerce:testId code="addressBook_isDefault_label">
                                                    <h3><spring:theme code="text.fliegercommerce.texto26"/></h3>
                                                </ycommerce:testId>
                                            </c:if> <c:if test="${address.billingAddress}">
                                                <ycommerce:testId code="addressBook_isDefault_label">
                                                    <h3><spring:theme code="text.fliegercommerce.texto27"/></h3>
                                                </ycommerce:testId>
                                            </c:if>
                                            <h3>${address.type}
                                                <ycommerce:testId
                                                    code="addressBook_addressOptions_label">
                                                    <ycommerce:testId
                                                        code="addressBook_editAddress_button">
                                                        <a class="adressButtonEditAddress"
                                                            href="edit-address/${address.id}"> <spring:theme
                                                                code="text.account.addressEdit"
                                                                text="Address Edit" />
                                                        </a>
                                                    </ycommerce:testId>
                                                    <c:if test="${fn:length(addressData) > 1}">
                                                        <ycommerce:testId
                                                            code="addressBook_removeAddress_button">
                                                            <a class="adressButtonEraseAddress"
                                                                title="Remover endere�o"
                                                                data-address-id="${address.id}"
                                                                href="remove-address/${address.id}">
                                                                <spring:theme code="text.fliegercommerce.texto28"/> </a>
                                                        </ycommerce:testId>
                                                    </c:if>
                                                </ycommerce:testId>
                                            </h3> <ycommerce:testId code="addressBook_address_label">
                                                <p>
                                                    <strong><spring:theme code="text.fliegercommerce.texto18"/></strong>&nbsp;
                                                    ${fn:escapeXml(address.firstName)}&nbsp;
                                                    ${fn:escapeXml(address.lastName)}
                                                </p>
                                                <p>
                                                    <c:if test="${not empty address.receiver}">
                                                        <strong><spring:theme code="text.fliegercommerce.texto19"/></strong>&nbsp; ${fn:escapeXml(address.receiver)}</c:if>
                                                </p>
                                                <p>
                                                    <strong><spring:theme code="text.fliegercommerce.texto20"/></strong>&nbsp;
                                                    (${fn:escapeXml(address.dddPhone)})${fn:escapeXml(address.phone)}
                                                </p>
                                                <p>
                                                    <c:if test="${not empty address.celPhone}">
                                                        <strong><spring:theme code="text.fliegercommerce.texto21"/></strong>&nbsp; (${fn:escapeXml(address.dddCelPhone)})${fn:escapeXml(address.celPhone)}</c:if>
                                                </p>
                                                <p>
                                                    <strong><spring:theme code="text.fliegercommerce.texto22"/>&nbsp;</strong>
                                                    ${fn:escapeXml(address.line1)},
                                                    ${fn:escapeXml(address.number)} -
                                                    ${fn:escapeXml(address.complement)} &nbsp;
                                                    ${fn:escapeXml(address.district)}<br />
                                                    ${fn:escapeXml(address.town)}&nbsp;
                                                    <c:if test="${not empty address.region.name}">-&nbsp;${fn:escapeXml(address.region.isocodeShort)}</c:if>
                                                    - ${fn:escapeXml(address.postalCode)}
                                                </p>
                                                <p>
                                                    <c:if test="${not empty address.reference}">
                                                        <strong><spring:theme code="text.fliegercommerce.texto23"/></strong>&nbsp; ${fn:escapeXml(address.reference)}</c:if>
                                                </p>
                                            </ycommerce:testId>
                                            <div class="btns">
                                                <c:if test="${not address.defaultAddress}">
                                                    <ycommerce:testId
                                                        code="addressBook_isDefault_button">
                                                        <a class="btn btn-enderecos"
                                                            style="font-size: 13px;"
                                                            href="set-default-address/${address.id}">
                                                            <spring:theme code="text.fliegercommerce.texto29"/> </a>
                                                    </ycommerce:testId>
                                                </c:if>
                                                <c:if test="${not address.billingAddress}">
                                                    <ycommerce:testId
                                                        code="addressBook_isDefault_button">
                                                        <a class="btn btn-enderecos"
                                                            style="font-size: 13px;"
                                                            href="set-default-billing-address/${address.id}">
                                                            <spring:theme code="text.fliegercommerce.texto30"/></a>
                                                    </ycommerce:testId>
                                                </c:if>
                                            </div></li>
                                    </c:forEach>
                                </ul>
                            </c:when>
                            <c:otherwise>
                                <p class="emptyMessage">
                                    <spring:theme code="text.account.addressBook.noSavedAddresses" />
                                </p>
                            </c:otherwise>
                        </c:choose>
                    </section>
                </div>
            </section>
        </div>
    </div>
</template:page>
