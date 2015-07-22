<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/mobile/template"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/mobile/nav"%>
<%@ taglib prefix="category" tagdir="/WEB-INF/tags/mobile/category"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/mobile/product"%>
<%@ taglib prefix="cms" uri="/cms2lib/cmstags/cmstags.tld"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>

<template:page pageTitle="${pageTitle}">
    <jsp:attribute name="pageScripts">
        <script type="text/javascript" src="${commonResourcePath}/js/accmob.facets.js"></script>
    </jsp:attribute>
    <jsp:body>
        <div id="globalMessages">
            <common:globalMessages/>
        </div>
        <div class="accmob-navigationHolder">
            <div class="accmob-navigationContent">
                <div id="breadcrumb" class="accmobBackLink">
                    <nav:breadcrumb breadcrumbs="${breadcrumbs}"/>
                </div>
            </div>
        </div>
        <div id="top-banner" class="homebanner">
            <cms:slot var="feature" contentSlot="${slots.Section2}">
                <div class="span-24 section1 advert">
                    <cms:component component="${feature}"/>
                </div>
            </cms:slot>
        </div>
        <c:if test="${not empty searchPageData.results}">
            <div class="sortingBar item_container_holder">
                <nav:searchTermAndSortingBar pageData="${searchPageData}" top="true" showSearchTerm="false"/>
            </div>
        </c:if>
        <nav:pagination searchPageData="${searchPageData}" searchUrl="${searchPageData.currentQuery.url}"/>
        <div class="productResultsList">
            <c:if test="${empty searchPageData.results}">
                <ul id="categoryResultsList" data-role="listview" data-inset="true" data-theme="e" data-content-theme="e" class="mainNavigation">
                    <category:categoryList pageData="${searchPageData}"/>
                </ul>
            </c:if>
            <c:if test="${not empty searchPageData.results}">
                <ul data-role="listview" data-inset="true" data-theme="e" data-content-theme="e" class="mainNavigation">
                    <c:forEach items="${searchPageData.results}" var="product" varStatus="status">
                        <product:productListerItem product="${product}"/>
                    </c:forEach>
                </ul>
            </c:if>
        </div>
        <nav:pagination searchPageData="${searchPageData}" searchUrl="${searchPageData.currentQuery.url}"/>
        <div id="bottom-banner" class="homebanner">
            <cms:slot var="feature" contentSlot="${slots.Section4}">
                <div class="span-24 section1 advert">
                    <cms:component component="${feature}"/>
                </div>
            </cms:slot>
        </div>
    </jsp:body>
</template:page>
<nav:facetNavRefinementsJQueryTemplates pageData="${searchPageData}"/>
<nav:popupMenu/>
