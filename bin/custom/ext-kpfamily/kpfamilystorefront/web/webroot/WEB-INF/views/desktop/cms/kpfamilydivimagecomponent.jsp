<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="imageUrl" value="${image.url}"/>

<div class="${cssClass}">
    <a href="${hyperlink}">
       <img src="${imageUrl}">
        <div>
            <h3>${title}</h3>
            <span>${text}</span>
        </div>
    </a>
</div>