<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb"%>
<template:page pageTitle="${pageTitle}">
    <div id="main-wrapper">
        <div class="container">
            <c:if test="${not empty message}">
                <spring:theme code="${message}" />
            </c:if>
            <div id="globalMessages">
                <common:globalMessages />
            </div>
            <header id="page-header" class="nao-encontrado">
                <div class="breadcrumb">
                    <breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}" />
                </div>
                <h1>
                    <spring:theme code="text.fliegercommerce.texto47"/> "<b>${searchText}</b>" <spring:theme code="text.fliegercommerce.texto48"/>
                </h1>
                <h2><spring:theme code="text.fliegercommerce.texto49"/></h2>
            </header>
        </div>
        <section class="page vertical-sections">
            <div class="container">
                <section>
                    <header>
                        <h1><spring:theme code="text.fliegercommerce.texto50"/></h1>
                    </header>
                    <ul class="categorias-tile">
                        <li><a href="/" title="">
                        	<img alt="" src="/"></a>
                       	</li>
                    </ul>
                </section>
            </div>
            <section>
                <div class="container">
                    <header>
                        <%-- <h1>Recomendados para voc�</h1> --%>
                    </header>
                </div>
                <div class="product-infinite-wrapper no-before">
                    <div class="product-slider">
                        <nav:searchSpellingSuggestion
                            spellingSuggestion="${searchPageData.spellingSuggestion}" />
                    </div>
                </div>
            </section>
    </div>
</template:page>
