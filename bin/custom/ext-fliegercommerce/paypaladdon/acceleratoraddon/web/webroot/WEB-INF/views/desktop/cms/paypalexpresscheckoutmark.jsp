<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

<spring:url value="${urlLink}" var="expressCheckoutMarkUrl"/>
<div style="display: none;" id="expressCheckoutUrl">${expressCheckoutMarkUrl}</div>

<img src="${media.url}" alt="${media.altText}" />

<span id="whatIsPayPalLink" style="padding-left: 10px;"><cms:component component="${link}" /></span>
