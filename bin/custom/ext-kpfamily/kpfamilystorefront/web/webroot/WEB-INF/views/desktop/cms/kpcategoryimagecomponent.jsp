<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${component.visible}">
    <div class="${cssClass}">
        <c:choose>
            <c:when test="${not empty hyperlink}">
                <c:url var="customHyperlink" value="${hyperlink}" />
            </c:when>
            <c:otherwise>
                <c:set var="customHyperlink" value="#" />
            </c:otherwise>
        </c:choose>
        <a href="${customHyperlink}">
            <img src="${media.url}" title="${media.altText}" alt="${media.altText}"/>
            <div>
                <h3>${title}</h3>
                <span>${text}</span>
            </div>
        </a>
    </div>
</c:if>