<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url value="${urlLink}" var="expressCheckoutMarkUrl"/>
<div style="display: none;" id="expressCheckoutUrl">${expressCheckoutMarkUrl}</div>

<img src="${media.url}" alt="${media.altText}" />
