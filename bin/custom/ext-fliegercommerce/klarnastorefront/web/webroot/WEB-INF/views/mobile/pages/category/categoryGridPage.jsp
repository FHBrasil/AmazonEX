<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/mobile/template" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/mobile/nav" %>
<%@ taglib prefix="category" tagdir="/WEB-INF/tags/mobile/category" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cms" uri="/cms2lib/cmstags/cmstags.tld" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ycommerce" uri="/WEB-INF/tld/ycommercetags.tld" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/mobile/common" %>

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
        <div id="resultsGrid" class="span-24 productResultsGrid">
        <c:forEach items="${searchPageData.subCategories}" var="category" varStatus="status">
            <c:choose>
                <c:when test="${status.first}">
                    <div class="ui-grid-a">
                    <div class='ui-block-a left'>
                        <category:categoryGridNav category="${category}"/>
                    </div>
                    <c:if test="${status.last}">
                        </div>
                    </c:if>
                </c:when>
                <c:otherwise>
                    <c:choose>
                        <c:when test="${(status.count % 2) == 0}">
                            <div class='ui-block-b right'>
                                <category:categoryGridNav category="${category}"/>
                            </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="ui-grid-a">
                            <div class='ui-block-a left'>
                                <category:categoryGridNav category="${category}"/>
                            </div>
                            <c:if test="${status.last}">
                                </div>
                            </c:if>
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        </div>
        <div id="bottom-banner" class="homebanner">
            <cms:slot var="feature" contentSlot="${slots.Section4}">
                <div class="span-24 section1 advert">
                    <cms:component component="${feature}"/>
                </div>
            </cms:slot>
        </div>
    </jsp:body>
</template:page>
<nav:popupMenu/>