<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav"%>
<%@ taglib prefix="showcase" tagdir="/WEB-INF/tags/desktop/showcase"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb"%>
<template:page pageTitle="${pageTitle}">
    <div id="globalMessages">
        <common:globalMessages />
    </div>
    <div>
        <cms:pageSlot position="Section1" var="feature">
            <cms:component component="${feature}" element="div"
                class="span-24 section1 cms_disp-img_slot" />
        </cms:pageSlot>
        <header id="page-header">
            <breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}" />
            <c:if test="${!(searchPageData.freeTextSearch == '')}">
                <h1>
                    <spring:theme code="search.page.searchText"
                        arguments="${searchPageData.freeTextSearch}" />
                </h1>
                <h2></h2>
                <nav:searchSpellingSuggestion
                    spellingSuggestion="${searchPageData.spellingSuggestion}" />
            </c:if>
        </header>
        <div class="searchData">
            <input type="hidden" class="userCode" value="${searchPageData.freeTextSearch}" /> <input
                type="hidden" class="textSearch" value="${searchPageData.freeTextSearch}" />
        </div>
        <c:if test="${(searchPageData.freeTextSearch != '' )}">
            <div chaordic="top"></div>
        </c:if>
        <div class="filters borders">
            <nav:paginationTop top="true" isShowInfo="${isShowInfo}"
                isShowListHeringEnabled="${isShowListHeringEnabled}"
                supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}"
                searchPageData="${searchPageData}" searchUrl="${searchPageData.currentQuery.url}"
                numberPagesShown="${numberPagesShown}" />
        </div>
        <section class="page with-sidebar">
            <div class="left categories" style="margin-top: 7px;">
                <nav:facetNavAppliedFilters pageData="${searchPageData}" />
                <nav:facetNavRefinements pageData="${searchPageData}" />
                <cms:pageSlot position="Section4" var="feature">
                    <cms:component component="${feature}" element="div"
                        class="span-24 section1 cms_disp-img_slot" />
                </cms:pageSlot>
            </div>
            <div>
                <c:if test="${(searchPageData.freeTextSearch == '' )}">
                    <div chaordic="top"></div>
                </c:if>
                <c:if
                    test="${(searchPageData.freeTextSearch == '' && searchPageData.pagination.currentPage == 0)}">
                    <div class="disp-banner">
                        <cms:pageSlot position="Section2" var="feature">
                            <cms:component component="${feature}" />
                        </cms:pageSlot>
                    </div>
                </c:if>
                <div chaordic="middle"></div>
                <div id="productGrid" class="text-center panel150102">
                    <div id="resultsList">
                        <c:set var="classNameDisplay"
                            value="${isShowInfo ? 'product-info' : 'resumed-info'}" />
                        <showcase:productsToShow className="${classNameDisplay}"
                            products="${searchPageData.results}" />
                    </div>
                </div>
                <nav:pagination top="false" supportShowPaged="${isShowPageAllowed}"
                    supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}"
                    searchUrl="${searchPageData.currentQuery.url}"
                    numberPagesShown="${numberPagesShown}" />
                <div chaordic="bottom"></div>
            </div>
        </section>
    </div>
    <c:url value="${requestScope['javax.servlet.forward.servlet_path']}" var="checkoutUrl" />
    <div id="currentPath" data-current-path="${checkoutUrl }"></div>
</template:page>



<%--

<template:page pageTitle="${pageTitle}">
    <div id="globalMessages">
        <common:globalMessages />
    </div>
    <div class="container">
        <cms:pageSlot position="Section1" var="feature">
            <cms:component component="${feature}" element="div"
                class="span-24 section1 cms_disp-img_slot" />
        </cms:pageSlot>
        <header id="page-header">
            <breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}" />
            <c:if test="${!(searchPageData.freeTextSearch == '')}">
                <h1>
                    <spring:theme code="search.page.searchText"
                        arguments="${searchPageData.freeTextSearch}" />
                </h1>
                <h2></h2>
                <nav:searchSpellingSuggestion
                    spellingSuggestion="${searchPageData.spellingSuggestion}" />
            </c:if>
        </header>
        <div class="searchData">
            <input type="hidden" class="userCode" value="${searchPageData.freeTextSearch}" /> <input
                type="hidden" class="textSearch" value="${searchPageData.freeTextSearch}" />
        </div>
        <c:if test="${(searchPageData.freeTextSearch != '' )}">
            <div chaordic="top"></div>
        </c:if>
        <div class="filters borders">
            <nav:paginationTop top="true" isShowInfo="${isShowInfo}"
                isShowListHeringEnabled="${isShowListHeringEnabled}"
                supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}"
                searchPageData="${searchPageData}" searchUrl="${searchPageData.currentQuery.url}"
                numberPagesShown="${numberPagesShown}" />
        </div>
        <section class="page with-sidebar">
            <div class="left categories" style="margin-top: 7px;">
                <nav:facetNavAppliedFilters pageData="${searchPageData}" />
                <nav:facetNavRefinements pageData="${searchPageData}" />
                <cms:pageSlot position="Section4" var="feature">
                    <cms:component component="${feature}" element="div"
                        class="span-24 section1 cms_disp-img_slot" />
                </cms:pageSlot>
            </div>
            <div class="right">
                <c:if test="${(searchPageData.freeTextSearch == '' )}">
                    <div chaordic="top"></div>
                </c:if>
                <c:if
                    test="${(searchPageData.freeTextSearch == '' && searchPageData.pagination.currentPage == 0)}">
                    <div class="disp-banner">
                        <cms:pageSlot position="Section2" var="feature">
                            <cms:component component="${feature}" />
                        </cms:pageSlot>
                    </div>
                </c:if>
                <div chaordic="middle"></div>
                <div class="infinite-product-scroll showcase" id="productGrid">
                    <div class="product-wrapper infinite-scroll-content" id="resultsList">
                        <c:set var="classNameDisplay"
                            value="${isShowInfo ? 'product-info' : 'resumed-info'}" />
                        <showcase:productsToShow className="${classNameDisplay}"
                            products="${searchPageData.results}" />
                    </div>
                </div>
                <nav:pagination top="false" supportShowPaged="${isShowPageAllowed}"
                    supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}"
                    searchUrl="${searchPageData.currentQuery.url}"
                    numberPagesShown="${numberPagesShown}" />
                <div chaordic="bottom"></div>
            </div>
        </section>
    </div>
    <c:url value="${requestScope['javax.servlet.forward.servlet_path']}" var="checkoutUrl" />
    <div id="currentPath" data-current-path="${checkoutUrl }"></div>
</template:page>--%>