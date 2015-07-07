<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="searchUrl" required="true"%>
<%@ attribute name="searchPageData" required="true"
    type="de.hybris.platform.commerceservices.search.pagedata.SearchPageData"%>
<%@ attribute name="numberPagesShown" required="true" type="java.lang.Integer"%>
<%@ attribute name="themeMsgKey" required="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags"%>
<ul class="pagination">
    <c:set var="hasPreviousPage" value="${searchPageData.pagination.currentPage > 0}" />
    <c:set var="hasNextPage"
        value="${(searchPageData.pagination.currentPage + 1) < searchPageData.pagination.numberOfPages}" />
    <c:if test="${hasPreviousPage}">
        <li><spring:url value="${searchUrl}" var="previousPageUrl" htmlEscape="true">
                <spring:param name="page" value="${searchPageData.pagination.currentPage - 1}" />
            </spring:url> <c:if test="${isShowListHeringEnabled}">
                <c:set var="previousPageUrl" value="${previousPageUrl}&isShowListHeringEnabled=true"></c:set>
            </c:if> <ycommerce:testId code="searchResults_previousPage_link">
                <a href="${previousPageUrl}" rel="prev" class="prev"><spring:theme code="search.page.linkPreviousPage"/></a>
            </ycommerce:testId></li>
    </c:if>
    <c:set var="limit" value="${numberPagesShown}" />
    <c:set var="halfLimit">
        <fmt:formatNumber value="${limit/2}" maxFractionDigits="0" />
    </c:set>
    <c:set var="beginPage">
        <c:choose>
            <%-- Limit is higher than number of pages --%>
            <c:when test="${limit gt searchPageData.pagination.numberOfPages}">1</c:when>
            <%-- Start shifting page numbers once currentPage reaches halfway point--%>
            <c:when test="${searchPageData.pagination.currentPage + halfLimit ge limit}">
                <c:choose>
                    <c:when
                        test="${searchPageData.pagination.currentPage + halfLimit lt searchPageData.pagination.numberOfPages}">
								${searchPageData.pagination.currentPage + 1 - halfLimit}
							</c:when>
                    <c:otherwise>${searchPageData.pagination.numberOfPages + 1 - limit}</c:otherwise>
                </c:choose>
            </c:when>
            <c:otherwise>1</c:otherwise>
        </c:choose>
    </c:set>
    <c:set var="endPage">
        <c:choose>
            <c:when test="${limit gt searchPageData.pagination.numberOfPages}">
						${searchPageData.pagination.numberOfPages}
					</c:when>
            <c:when test="${hasNextPage}">
						${beginPage + limit - 1}
					</c:when>
            <c:otherwise>
						${searchPageData.pagination.numberOfPages}
					</c:otherwise>
        </c:choose>
    </c:set>
    <c:forEach begin="${beginPage}" end="${endPage}" var="pageNumber">
        <c:choose>
            <c:when test="${searchPageData.pagination.currentPage + 1 ne pageNumber}">
                <spring:url value="${searchUrl}" var="pageNumberUrl" htmlEscape="true">
                    <spring:param name="page" value="${pageNumber - 1}" />
                </spring:url>
                <c:if test="${isShowListHeringEnabled}">
                    <c:set var="pageNumberUrl" value="${pageNumberUrl}&isShowListHeringEnabled=true"></c:set>
                </c:if>
                <ycommerce:testId code="pageNumber_link">
                    <li id="${( pageNumber -1 )}"><a href="${pageNumberUrl}">${pageNumber}</a></li>
                </ycommerce:testId>
            </c:when>
            <c:otherwise>
                <li id="${( pageNumber -1 )}"><a class="active">${pageNumber}</a></li>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <c:if test="${hasNextPage}">
        <li><spring:url value="${searchUrl}" var="nextPageUrl" htmlEscape="true">
                <spring:param name="page" value="${searchPageData.pagination.currentPage + 1}" />
            </spring:url> <c:if test="${isShowListHeringEnabled}">
                <c:set var="nextPageUrl" value="${nextPageUrl}&isShowListHeringEnabled=true"></c:set>
            </c:if> <ycommerce:testId code="searchResults_nextPage_link">
                <a href="${nextPageUrl}" rel="next" class="next"><spring:theme code="search.page.linkNextPage" /> </a>
            </ycommerce:testId></li>
    </c:if>
</ul>
