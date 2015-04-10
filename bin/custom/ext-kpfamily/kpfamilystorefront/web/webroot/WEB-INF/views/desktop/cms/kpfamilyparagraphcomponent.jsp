<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${component.visible}">
<div class="${cssClass}">
    <c:if test="${not empty hyperlink}">
        <c:url var="customHyperlink" value="${hyperlink}" />
        <a href="${customHyperlink}">
    </c:if>
    <img src="${backgroundImage.url}">
    <div>
        <h3>${title}</h3>
        <span>${text}</span>
    </div>
    <c:if test="${not empty hyperlink}">
        </a>
    </c:if>
</div>
</c:if>