<%@ page trimDirectiveWhitespaces="true" contentType="application/json"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format"%>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product"%>
<%@ taglib prefix="json" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common"%>
<%@ taglib prefix="grid" tagdir="/WEB-INF/tags/desktop/grid"%>
{ "pagination" : { "currentPage": "${searchResultsData.pagination.currentPage}", "numberOfPages":
"${searchResultsData.pagination.numberOfPages}", "totalNumberOfResults" :
"${searchResultsData.pagination.totalNumberOfResults}", "searchResultsType" : "${searchResultType}"
}, "productListerHtml": "
<spring:escapeBody javaScriptEscape="true">
    <c:forEach items="${searchResultsData.results}" var="product" varStatus="status">
        <c:choose>
            <c:when test="${isShowListEnabled eq true}">
                <product:productListerItem product="${product}" />
            </c:when>
            <c:otherwise>
                <div
                    class="span-6 productDetailsHolder ${(status.index+1)%3 == 0 ? ' last' : ''}${(status.index)%3 == 0 ? ' first clear' : ''}">
                    <input type="hidden" name="productCode" value="${product.code}">
                    <product:productListerGridItem product="${product}" />
                </div>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</spring:escapeBody>
" }
