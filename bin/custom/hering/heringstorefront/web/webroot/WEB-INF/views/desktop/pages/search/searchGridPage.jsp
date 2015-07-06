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
        <div class="container">
            <header>
                <breadcrumb:breadcrumb breadcrumbs="${breadcrumbs}" />
            </header>
        </div>
        <div class="pagination150224 container">
            <h1 class="col-xs-12 col-sm-5">
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

            <div class="col-xs-12 col-sm-7 text-right pagination150224">
                <ul class="pagination">
                    <li><a data-toggle="collapse" data-parent="#accordionFilter" href="#collapseFilter"><span class="glyphicon glyphicon-filter"></span> Filter</a></li>
                    <%--<div class="input-group-btn">
                        <li>
                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-sort"></span> <span class="hidden-xs">Topseller</span> <span class="caret"></span></button>
                            <ul class="dropdown-menu">
                                <li><a href="#">Topseller</a></li>
                                <li><a href="#">Kundenbewertung</a></li>
                                <li><a href="#">Preis, aufsteigend</a></li>
                                <li><a href="#">Preis, absteigend</a></li>
                                <li><a href="#">Angebote zuerst</a></li>
                                <li><a href="#">Name, A-Z</a></li>
                            </ul>
                        </li>
                    </div>--%>
                    <%--<li class="active"><a href="#"><span class="glyphicon glyphicon-th"></span> <span class="hidden-xs">Raster</span></a></li>
                    <li><a href="#"><span class="glyphicon glyphicon-th-list"></span> <span class="hidden-xs">Liste</span></a></li>--%>
                </ul>
            </div>
        </div>

        <div class="searchData">
            <input type="hidden" class="userCode" value="${searchPageData.freeTextSearch}" /> 
            <input type="hidden" class="textSearch" value="${searchPageData.freeTextSearch}" />
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
        <div class="col-xs-12 col-sm-7 margin-top pagination150224">
            <form>
                <div class="btn-group">
                    <nav:facetNavAppliedFilters pageData="${searchPageData}" />
                    <button type="button" data-toggle="collapse" data-parent="#accordionFilter" href="#collapseFilter" class="btn btn-link">Filter &auml;ndern</button>
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
        <div class="col-xs-12 text-right">
            <nav:pagination top="false" supportShowPaged="${isShowPageAllowed}"
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