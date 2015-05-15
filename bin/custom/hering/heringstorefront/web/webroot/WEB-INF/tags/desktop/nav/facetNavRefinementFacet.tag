<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="facetData" required="true"
    type="de.hybris.platform.commerceservices.search.facetdata.FacetData"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:if test="${not empty facetData.values}">
    <spring:theme code="text.hideFacet" var="hideFacetText" />
    <spring:theme code="text.showFacet" var="showFacetText" />
    <div
        class="category ${facetData.name eq 'cor' ? 'cores' : ''} ${fn:containsIgnoreCase(facetData.name, 'Tamanho') ? 'sizes' : ''} ${fn:containsIgnoreCase(facetData.name, 'subcategoria') ? 'with-search-box' : ''}">
        <dl>
            <spring:theme code="search.nav.facetTitle" arguments="${facetData.name}" />
        </dl>
        <div class="cat-links">
            <ycommerce:testId code="facetNav_facet${facetData.name}_links">
                <c:if test="${not empty facetData.topValues}">
                    <ul class="topFacetValues">
                        <c:forEach items="${facetData.topValues}" var="facetValue">
                            <c:if test="${facetData.multiSelect}">
                                <c:if test="${facetData.name eq 'cor'}">
                                    <c:set var="colorStyle"
                                        value="background-color:${facetValue.name}" />
                                </c:if>
                                <li>
                                    <form action="#" method="get">
                                        <input type="hidden" name="q"
                                            value="${facetValue.query.query.value}" />
                                        <c:choose>
                                            <c:when
                                                test="${not fn:containsIgnoreCase(facetData.name, 'subcategoria')}">
                                                <label
                                                    class="facet_block-label tipo${facetData.name}"
                                                    style="${colorStyle}"
                                                    onclick="$(this).closest('form').submit()">
                                                    <input class="InStock-class" type="checkbox"
                                                    ${facetValue.selected ? 'checked="checked"' : ''} />
                                                    ${facetValue.name}
                                                </label>
                                            </c:when>
                                        </c:choose>
                                    </form>
                                </li>
                            </c:if>
                            <c:if test="${not facetData.multiSelect}">
                                <li><c:url value="${facetValue.query.url}"
                                        var="facetValueQueryUrl" /> <a
                                    href="${facetValueQueryUrl}&amp;text=${searchPageData.freeTextSearch}">${facetValue.name}</a>
                                <li>
                            </c:if>
                            </li>
                        </c:forEach>
                        <c:forEach items="${facetData.values}" var="facetValue">
                            <c:if test="${fn:containsIgnoreCase(facetData.name, 'subcategoria')}">
                                <input type="search" placeholder="Buscar" class="search-box">
                                <li><c:if test="${facetData.multiSelect}">
                                        <form action="#" method="get">
                                            <input type="hidden" name="q"
                                                value="${facetValue.query.query.value}" />
                                            <c:choose>
                                                <c:when
                                                    test="${fn:containsIgnoreCase(facetData.name, 'subcategoria')}">
                                                    <a onclick="$(this).closest('form').submit()">
                                                        <input type="checkbox"
                                                        ${facetValue.selected ? 'checked="checked"' : ''}
                                                        onchange="$(this).closest('form').submit()" />
                                                        ${facetValue.name}
                                                    </a>
                                                </c:when>
                                            </c:choose>
                                        </form>
                                    </c:if> <c:if test="${not facetData.multiSelect}">
                                        <c:url value="${facetValue.query.url}"
                                            var="facetValueQueryUrl" />
                                        <a href="${facetValueQueryUrl}">${facetValue.name}</a>
                                    </c:if></li>
                            </c:if>
                        </c:forEach>
                    </ul>
                </c:if>
                <c:if test="${not empty facetData.topValues}">
                    <c:choose>
                        <c:when test="${fn:containsIgnoreCase(facetData.name, 'subcategoria')}">
                            <ul>
                        </c:when>
                        <c:otherwise>
                            <ul
                                style="${fn:containsIgnoreCase(facetData.name, 'cor') ? 'display: none;' : ''}  ${fn:containsIgnoreCase(facetData.name, 'categoria') ? 'display: none;' : ''}  ">
                        </c:otherwise>
                    </c:choose>
                </c:if>
                <c:if test="${empty facetData.topValues}">
                    <ul>
                </c:if>
                <c:if test="${fn:containsIgnoreCase(facetData.name, 'subcategoria')}">
                    <input type="search" placeholder="Buscar" class="search-box">
                </c:if>
                <c:forEach items="${facetData.values}" var="facetValue">
                    <c:if test="${facetData.name eq 'cor'}">
                        <c:set var="colorStyle" value="background-color:${facetValue.name}" />
                    </c:if>
                    <li class="${facetData.name eq 'cor' ? 'cores' : ''}"><c:if
                            test="${facetData.multiSelect}">
                            <form action="#" method="get">
                                <input type="hidden" name="q"
                                    value="${facetValue.query.query.value}" />
                                <c:choose>
                                    <c:when
                                        test="${fn:containsIgnoreCase(facetData.name, 'subcategoria')}">
                                        <a onclick="$(this).closest('form').submit()"> <input
                                            type="checkbox"
                                            ${facetValue.selected ? 'checked="checked"' : ''}
                                            onchange="$(this).closest('form').submit()" />
                                            ${facetValue.name}
                                        </a>
                                    </c:when>
                                    <c:when
                                        test="${fn:containsIgnoreCase(facetData.name, 'Tamanho')}">
                                        <c:if test="${!facetValue.selected}">
                                            <a onclick="$(this).closest('form').submit()">
                                                ${facetValue.name} </a>
                                        </c:if>
                                    </c:when>
                                    <c:when test="${facetData.name eq 'cor'}">
                                        <label class="facet_block-label tipo${facetData.name}"
                                            style="${colorStyle}"
                                            onclick="$(this).closest('form').submit()"> <input
                                            class="InStock-class" type="checkbox"
                                            ${facetValue.selected ? 'checked="checked"' : ''} />
                                        </label>
                                    </c:when>
                                    <c:when test="${facetData.name eq 'cor'}">
                                        <label class="facet_block-label tipo${facetData.name}"
                                            style="${colorStyle}"
                                            onclick="$(this).closest('form').submit()"> <input
                                            class="InStock-class" type="checkbox"
                                            ${facetValue.selected ? 'checked="checked"' : ''} />
                                        </label>
                                    </c:when>
                                    <c:otherwise>
                                        <a onclick="$(this).closest('form').submit()"> <input
                                            type="checkbox"
                                            ${facetValue.selected ? 'checked="checked"' : ''}
                                            onchange="$(this).closest('form').submit()" />
                                            ${facetValue.name}
                                        </a>
                                    </c:otherwise>
                                </c:choose>
                            </form>
                        </c:if> <c:if test="${not facetData.multiSelect}">
                            <c:url value="${facetValue.query.url}" var="facetValueQueryUrl" />
                            <a href="${facetValueQueryUrl}">${facetValue.name}</a>
                        </c:if></li>
                </c:forEach>
                </ul>
                <c:if test="${fn:containsIgnoreCase(facetData.name, 'Tamanho')}">
                    <a class="fancybox tabela-medidas guide" href="#modal-tabela-medidas"><spring:theme
                            code="product.variants.size.guide" /></a>
                    <nav:categoryPesosMedidas />
                </c:if>
        </div>
        </ycommerce:testId>
    </div>
</c:if>
