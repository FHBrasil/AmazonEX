<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb"%>
<template:page pageTitle="${pageTitle}">
    <div id="globalMessages">
        <common:globalMessages />
    </div>
    <div class="container">
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
        <div chaordic="top"></div>
        <section class="page with-sidebar">
            <div class="left categories">
                <nav:facetNavAppliedFilters pageData="${searchPageData}" />
                <nav:facetNavRefinements pageData="${searchPageData}" />
                <div id="imgItem" style="height: auto; width: 255px; margin-top: 6px;">
                    <%-- ESTAVA NO LOCAL ERRADO --%>
                    <cms:pageSlot position="Section4" var="feature">
                        <cms:component component="${feature}" element="div"
                            class="span-4 section4 cms_disp-img_slot" />
                    </cms:pageSlot>
                </div>
            </div>
            <div class="right">
                <div chaordic="middle"></div>
                <c:if test="${searchPageData.pagination.currentPage == 0}">
                    <cms:pageSlot position="Section2" var="feature">
                        <cms:component component="${feature}" element="div"
                            class="section2 cms_disp-img_slot" />
                    </cms:pageSlot>
                </c:if>
                <cms:pageSlot position="Section3" var="feature" element="div" class="span-20 last">
                    <cms:component component="${feature}" element="div"
                        class="span-5 section3 small_detail" />
                </cms:pageSlot>
                <div class="list-mode infinite-product-scroll" id="results">
                    <div class="product-wrapper infinite-scroll-content">
                        <c:forEach items="${searchPageData.results}" var="product"
                            varStatus="status">
                            <div class="product productDetailsHolder" id="${product.code}">
                                <input type="hidden" id="productCode" name="productCode"
                                    value="${product.code}">
                                <product:productListerGridItem product="${product}" />
                            </div>
                        </c:forEach>
                    </div>
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
</template:page>
