<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="breadcrumbs" required="true" type="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:url value="/" var="homeUrl" />
<header id="page-header">
    <div>
        <ul class="breadcrumb">
            <li><a href="${homeUrl}"><spring:theme code="breadcrumb.home" /></a></li>
            <c:forEach items="${breadcrumbs}" var="breadcrumb" varStatus="status">
                <li><c:choose>
                        <c:when test="${breadcrumb.url eq '#'}">
                            <a href="#" onclick="return false;"
                                <c:if test="${status.last}">class="last"</c:if>>${breadcrumb.name}</a>
                        </c:when>
                        <c:otherwise>
                            <c:url value="${breadcrumb.url}" var="breadcrumbUrl" />
                            <c:choose>
                                <c:when test="${status.last}">${breadcrumb.name}</c:when>
                                <c:otherwise>
                                    <a href="${breadcrumbUrl}">${breadcrumb.name}</a>
                                </c:otherwise>
                            </c:choose>
                        </c:otherwise>
                    </c:choose></li>
            </c:forEach>
        </ul>
    </div>
</header>
