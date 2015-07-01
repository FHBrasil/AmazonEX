<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="pageData" required="true"
    type="de.hybris.platform.commerceservices.search.facetdata.ProductSearchPageData"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>

<c:if test="${not empty pageData.breadcrumbs}">
    <c:forEach items="${pageData.breadcrumbs}" var="breadcrumb" varStatus="status">
        <c:if test="${status.first}">
            <input type="hidden" value="${breadcrumb.facetValueName}" class="chaordicSearchCategory" />
        </c:if>
        <%-- <c:if test="${fn:startsWith(breadcrumb.facetValueName, '#')}">
							<div class="remove_item_left_name facet_block-label itemSelecionadoBreadcrumb faceta-${breadcrumb.facetValueName}" style="background-color:${breadcrumb.facetValueName}; width:15px; height:15px;"></div>
						</c:if> --%>
        <c:if test="${ not fn:startsWith(breadcrumb.facetValueName, '#')}">
            <c:if
                test="${breadcrumb.facetValueName eq 'Feminino' or breadcrumb.facetValueName eq 'Masculino' or breadcrumb.facetValueName eq 'Jeans' or breadcrumb.facetValueName eq 'Acess�rios' or breadcrumb.facetValueName eq 'Sales'}">
                <header>
                    <h1>
                        <span
                            class="remove_item_left_name itemSelecionadoBreadcrumb faceta-${breadcrumb.facetValueName}">${breadcrumb.facetValueName}</span>
                    </h1>
                </header>
            </c:if>
        </c:if>
        <div class="clear"></div>
    </c:forEach>

        
    <c:forEach items="${pageData.breadcrumbs}" var="breadcrumb" varStatus="status">
        <c:url value="${breadcrumb.removeQuery.url}" var="removeQueryUrl" />
        <c:if
            test="${breadcrumb.facetValueName != 'Activewear' && breadcrumb.facetValueName != 'Beachwear' && breadcrumb.facetValueName != 'Underwear' && breadcrumb.facetValueName != 'Loungewear' && breadcrumb.facetValueName != 'Sleepwear' && breadcrumb.facetValueName != 'Promo��o' &&
			breadcrumb.facetValueName != 'Feminino' && breadcrumb.facetValueName != 'Masculino' && breadcrumb.facetValueName != 'Jeans' && breadcrumb.facetValueName != 'Acess�rios' && breadcrumb.facetValueName != 'Sales'}">
            <c:choose>
                <c:when test="${fn:startsWith(breadcrumb.facetValueName, '#')}">
                    <div class="btn btn-d">
                        <a href="${removeQueryUrl}"><b class="close">x</b> <span>${breadcrumb.facetName}:
                                <div class="remove_item_left_name facet_block-label itemSelecionadoBreadcrumb faceta-${breadcrumb.facetValueName}" style="background-color:${breadcrumb.facetValueName}; width:15px; height:15px;display:inline!important;position: absolute;top: 9px;left: 35px"></div>
                        </span></a>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="btn btn-d">
                        <a href="${removeQueryUrl}"><b class="close">x</b> <span>${breadcrumb.facetName}:${breadcrumb.facetValueName}</span></a>
                    </div>
                </c:otherwise>
            </c:choose>
        </c:if>
    </c:forEach>
       
</c:if>
