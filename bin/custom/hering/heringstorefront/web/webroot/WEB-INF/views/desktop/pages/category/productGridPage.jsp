<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="showcase" tagdir="/WEB-INF/tags/desktop/showcase"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb"%>

<template:page pageTitle="${pageTitle}" showBV="true">
    <div id="globalMessages">
        <common:globalMessages />
    </div>
<div>
        <cms:pageSlot position="Section1" var="feature">
            <cms:component component="${feature}" element="div"
                class="span-24 section1 cms_disp-img_slot" />
        </cms:pageSlot>
        <div class="container">
            <breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}" />
        </div>
        <div class="pagination150224 container">
            <h1 class="col-xs-12">
                <c:if test="${!(searchPageData.freeTextSearch == '')}">
                    <spring:theme code="search.page.searchText" arguments="${searchPageData.freeTextSearch}" />
                    <%--<nav:searchSpellingSuggestion spellingSuggestion="${searchPageData.spellingSuggestion}" />--%>
                </c:if>
                <c:set var="themeMsgKey" value="${not empty msgKey ? msgKey : 'search.page'}" />
                <ycommerce:testId code="searchResults_productsFound_label">
                    <small>
                        <spring:theme code="${themeMsgKey}.totalResults" arguments="${searchPageData.pagination.totalNumberOfResults}" />
                    </small>
                </ycommerce:testId>
            </h1>
        </div>

        <div class="searchData">
            <input type="hidden" class="userCode" value="${searchPageData.freeTextSearch}" /> 
            <input type="hidden" class="textSearch" value="${searchPageData.freeTextSearch}" />
        </div>

    <div class="container">
        <div class="col-xs-12 col-sm-7 margin-top filters150630">
            <form>
                <div class="btn-group">
                    <nav:facetNavAppliedFilters pageData="${searchPageData}" />
                    <button type="button" data-toggle="collapse" data-parent="#accordionFilter" href="#collapseFilter" class="btn btn-link"><span class="glyphicon glyphicon-filter"></span> Filter &auml;ndern</button>
                </div>
            </form>
        </div>
        <div class="col-xs-12 col-sm-5 text-right pagination150224 margin-top">
            <nav:pagination top="true" supportShowPaged="${isShowPageAllowed}"
                        supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}"
                        searchUrl="${searchPageData.currentQuery.url}"
                        numberPagesShown="${numberPagesShown}" />
        </div>
    </div>

    <!-- FOLLOWING CONTAINER MUST BE LOADED BY AJAX -->
    <div class="container filter150219 panel-collapse collapse" id="collapseFilter">
        <%--<div class="text-center margin-top margin-bottom">
            <button type="button" class="btn btn-lg" data-toggle="collapse" href="#collapseFilter"><span class="glyphicon glyphicon-refresh"></span> Aktualisieren</button>
        </div>--%>
        <div class="panel-group" id="accordion">
            <div class="row">
                <div class="col-sm-3">
                    <nav:facetNavRefinements pageData="${searchPageData}" />
                </div>
            </div>
        </div>      
    </div>
            

    <div class="container">
        <c:if test="${(searchPageData.freeTextSearch == '' )}">
            <div chaordic="top"></div>
        </c:if>
        <c:if test="${(searchPageData.freeTextSearch == '' && searchPageData.pagination.currentPage == 0)}">
            <div class="disp-banner">
                <cms:pageSlot position="Section2" var="feature">
                    <cms:component component="${feature}" />
                </cms:pageSlot>
            </div>
        </c:if>
        <div id="productGrid" class="text-center panel150102">
            <div id="resultsList">
                <c:set var="classNameDisplay"
                    value="${isShowInfo ? 'product-info' : 'resumed-info'}" />
                <showcase:productsToShow className="${classNameDisplay}"
                    products="${searchPageData.results}" />
            </div>
        </div>
    </div>
    <div class="container">
        <div class="col-xs-12 col-sm-7 margin-top filters150630"> 
        </div>
        <div class="col-xs-12 col-sm-5 text-right pagination150224 margin-top">
            <nav:pagination top="false" hideSort="true" supportShowPaged="${isShowPageAllowed}"
                        supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}"
                        searchUrl="${searchPageData.currentQuery.url}"
                        numberPagesShown="${numberPagesShown}" />
        </div>
    </div>
</div>

</div>
    <c:url value="${requestScope['javax.servlet.forward.servlet_path']}" var="checkoutUrl" />
    <div id="currentPath" data-current-path="${checkoutUrl }"></div>
</template:page>



<%-- old code <template:page pageTitle="${pageTitle}" showBV="true">
    <div id="globalMessages">
        <common:globalMessages />
    </div>
    <div class="container">
        <div chaordic="top"></div>
        <cms:pageSlot position="Section1" var="feature">
            <cms:component component="${feature}" element="div"
                class="span-24 section1 cms_disp-img_slot" />
        </cms:pageSlot>
        <div class="filters borders">
            <c:choose>
                <c:when test="${pageType == 'CATEGORY'}">
                    <breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}" />
                </c:when>
                <c:otherwise>
                    <breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}" />
                </c:otherwise>
            </c:choose>
            <nav:paginationTop top="true" isShowInfo="${isShowInfo}"
                isShowListHeringEnabled="${isShowListHeringEnabled}"
                supportShowPaged="${isShowPageAllowed}" supportShowAll="${isShowAllAllowed}"
                searchPageData="${searchPageData}" searchUrl="${searchPageData.currentQuery.url}"
                numberPagesShown="${numberPagesShown}" />
        </div>
        <div class="categoryData">
            <input type="hidden" class="category" value="${categoryName}" /> <input type="hidden"
                class="categoryGender" value="${categoryGender}" />
        </div>
        <section class="page with-sidebar">
            <div class="left categories">
                <nav:facetNavAppliedFilters pageData="${searchPageData}" />
                <nav:facetNavRefinements pageData="${searchPageData}" />
                <cms:pageSlot position="Section4" var="feature">
                    <cms:component component="${feature}" element="div"
                        class="section2 cms_disp-img_slot" />
                </cms:pageSlot>
            </div>
            <div class="right">
                <div class="disp-banner">
                    <cms:pageSlot position="Section2" var="feature">
                        <cms:component component="${feature}" element="div"
                            class="section2 cms_disp-img_slot" />
                    </cms:pageSlot>
                </div>
                <div chaordic="middle"></div>
                <cms:pageSlot position="Section3" var="feature" element="div" class="span-20 last">
                    <cms:component component="${feature}" element="div"
                        class="span-5 section3 small_detail" />
                </cms:pageSlot>
                <div class="infinite-product-scroll showcase" id="results">
                    <div class="product-wrapper infinite-scroll-content">
                        <c:set var="classNameDisplay"
                            value="${isShowInfo ? 'product-info' : 'resumed-info'}" />
                        <showcase:productsToShow className="${classNameDisplay}"
                            products="${searchPageData.results}" />
                    </div>
                    <c:if test="${false}">
                        <common:infiniteScroll />
                    </c:if>
                </div>
                <nav:pagination top="false" supportShowPaged="${isShowPageAllowed}"
                    supportShowAll="${isShowAllAllowed}" searchPageData="${searchPageData}"
                    searchUrl="${searchPageData.currentQuery.url}"
                    numberPagesShown="${numberPagesShown}" />
                <c:url value="${requestScope['javax.servlet.forward.servlet_path']}"
                    var="checkoutUrl" />
                <div id="currentPath" data-current-path="${checkoutUrl }"></div>
            </div>
            <div chaordic="bottom"></div>
        </section>
    </div>
</template:page>--%>
